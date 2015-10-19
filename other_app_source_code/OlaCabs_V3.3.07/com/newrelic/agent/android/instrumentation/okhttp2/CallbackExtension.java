package com.newrelic.agent.android.instrumentation.okhttp2;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

public class CallbackExtension implements Callback {
    private static final AgentLog log;
    private Callback impl;
    private TransactionState transactionState;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public CallbackExtension(Callback callback, TransactionState transactionState) {
        this.impl = callback;
        this.transactionState = transactionState;
    }

    public void onFailure(Request request, IOException iOException) {
        if (log.getLevel() >= 4) {
            log.verbose("CallbackExtension.onFailure() - logging error.");
        }
        error(iOException);
        this.impl.onFailure(request, iOException);
    }

    public void onResponse(Response response) throws IOException {
        if (log.getLevel() >= 4) {
            log.verbose("CallbackExtension.onResponse() - checking response.");
        }
        checkResponse(response);
        this.impl.onResponse(response);
    }

    private void checkResponse(Response response) {
        if (!getTransactionState().isComplete()) {
            if (log.getLevel() >= 4) {
                log.verbose("CallbackExtension.checkResponse() - transaction is not complete.  Inspecting and instrumenting response.");
            }
            OkHttp2TransactionStateUtil.inspectAndInstrumentResponse(getTransactionState(), response);
        }
    }

    private TransactionState getTransactionState() {
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
