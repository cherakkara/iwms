package com.newrelic.agent.android.instrumentation.okhttp2;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

public class CallExtension extends Call {
    private static final AgentLog log;
    private OkHttpClient client;
    private Call impl;
    private Request request;
    private TransactionState transactionState;

    static {
        log = AgentLogManager.getAgentLog();
    }

    CallExtension(OkHttpClient okHttpClient, Request request, Call call) {
        super(okHttpClient, request);
        this.client = okHttpClient;
        this.request = request;
        this.impl = call;
    }

    public Response execute() throws IOException {
        getTransactionState();
        try {
            Response execute = this.impl.execute();
            checkResponse(execute);
            return execute;
        } catch (Exception e) {
            error(e);
            throw e;
        }
    }

    public void enqueue(Callback callback) {
        getTransactionState();
        this.impl.enqueue(new CallbackExtension(callback, this.transactionState));
    }

    public void cancel() {
        this.impl.cancel();
    }

    public boolean isCanceled() {
        return this.impl.isCanceled();
    }

    private void checkResponse(Response response) {
        if (!getTransactionState().isComplete()) {
            OkHttp2TransactionStateUtil.inspectAndInstrumentResponse(getTransactionState(), response);
        }
    }

    private TransactionState getTransactionState() {
        if (this.transactionState == null) {
            this.transactionState = new TransactionState();
            OkHttp2TransactionStateUtil.inspectAndInstrument(this.transactionState, this.request);
        }
        return this.transactionState;
    }

    private void error(Exception exception) {
        TransactionState transactionState = getTransactionState();
        TransactionStateUtil.setErrorCodeFromException(transactionState, exception);
        if (!transactionState.isComplete()) {
            TransactionData end = transactionState.end();
            if (end != null) {
                TaskQueue.queue(new HttpTransactionMeasurement(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), end.getErrorCode(), end.getTimestamp(), (double) end.getTime(), end.getBytesSent(), end.getBytesReceived(), end.getAppData()));
            }
        }
    }
}
