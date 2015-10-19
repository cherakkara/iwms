package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

public final class ResponseHandlerImpl<T> implements ResponseHandler<T> {
    private final ResponseHandler<T> impl;
    private final TransactionState transactionState;

    private ResponseHandlerImpl(ResponseHandler<T> responseHandler, TransactionState transactionState) {
        this.impl = responseHandler;
        this.transactionState = transactionState;
    }

    public T handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        TransactionStateUtil.inspectAndInstrument(this.transactionState, httpResponse);
        return this.impl.handleResponse(httpResponse);
    }

    public static <T> ResponseHandler<? extends T> wrap(ResponseHandler<? extends T> responseHandler, TransactionState transactionState) {
        return new ResponseHandlerImpl(responseHandler, transactionState);
    }
}
