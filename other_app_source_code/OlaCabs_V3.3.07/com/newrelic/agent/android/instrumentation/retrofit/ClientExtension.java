package com.newrelic.agent.android.instrumentation.retrofit;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;

public class ClientExtension implements Client {
    private static final AgentLog log;
    private Client impl;
    private Request request;
    private TransactionState transactionState;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public ClientExtension(Client client) {
        this.impl = client;
    }

    public Response execute(Request request) throws IOException {
        this.request = request;
        getTransactionState();
        try {
            Response execute = this.impl.execute(setCrossProcessHeader(request));
            Response response = new Response(execute.getUrl(), execute.getStatus(), execute.getReason(), execute.getHeaders(), new ContentBufferingTypedInput(execute.getBody()));
            checkResponse(response);
            return response;
        } catch (Exception e) {
            error(e);
            throw e;
        }
    }

    private Request setCrossProcessHeader(Request request) {
        String crossProcessId = Agent.getCrossProcessId();
        if (crossProcessId == null) {
            return request;
        }
        List arrayList = new ArrayList(request.getHeaders());
        arrayList.add(new Header(TransactionStateUtil.CROSS_PROCESS_ID_HEADER.toLowerCase(Locale.ENGLISH), crossProcessId));
        return new Request(request.getMethod(), request.getUrl(), arrayList, request.getBody());
    }

    private void checkResponse(Response response) {
        if (!getTransactionState().isComplete()) {
            RetrofitTransactionStateUtil.inspectAndInstrumentResponse(getTransactionState(), response);
        }
    }

    private TransactionState getTransactionState() {
        if (this.transactionState == null) {
            this.transactionState = new TransactionState();
            RetrofitTransactionStateUtil.inspectAndInstrument(this.transactionState, this.request);
        }
        return this.transactionState;
    }

    private void error(Exception exception) {
        log.debug("handling exception: " + exception.toString());
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
