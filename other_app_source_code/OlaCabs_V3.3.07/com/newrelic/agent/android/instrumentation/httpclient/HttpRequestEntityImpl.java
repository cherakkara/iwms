package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import com.newrelic.agent.android.instrumentation.io.CountingOutputStream;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListener;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListenerSource;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public final class HttpRequestEntityImpl implements StreamCompleteListener, HttpEntity {
    private final HttpEntity impl;
    private final TransactionState transactionState;

    public HttpRequestEntityImpl(HttpEntity httpEntity, TransactionState transactionState) {
        this.impl = httpEntity;
        this.transactionState = transactionState;
    }

    public void consumeContent() throws IOException {
        try {
            this.impl.consumeContent();
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        try {
            if (this.transactionState.isSent()) {
                return this.impl.getContent();
            }
            InputStream countingInputStream = new CountingInputStream(this.impl.getContent());
            countingInputStream.addStreamCompleteListener(this);
            return countingInputStream;
        } catch (Exception e) {
            handleException(e);
            throw e;
        } catch (Exception e2) {
            handleException(e2);
            throw e2;
        }
    }

    public Header getContentEncoding() {
        return this.impl.getContentEncoding();
    }

    public long getContentLength() {
        return this.impl.getContentLength();
    }

    public Header getContentType() {
        return this.impl.getContentType();
    }

    public boolean isChunked() {
        return this.impl.isChunked();
    }

    public boolean isRepeatable() {
        return this.impl.isRepeatable();
    }

    public boolean isStreaming() {
        return this.impl.isStreaming();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        try {
            if (this.transactionState.isSent()) {
                this.impl.writeTo(outputStream);
                return;
            }
            OutputStream countingOutputStream = new CountingOutputStream(outputStream);
            this.impl.writeTo(countingOutputStream);
            this.transactionState.setBytesSent(countingOutputStream.getCount());
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }

    public void streamComplete(StreamCompleteEvent streamCompleteEvent) {
        ((StreamCompleteListenerSource) streamCompleteEvent.getSource()).removeStreamCompleteListener(this);
        this.transactionState.setBytesSent(streamCompleteEvent.getBytes());
    }

    public void streamError(StreamCompleteEvent streamCompleteEvent) {
        ((StreamCompleteListenerSource) streamCompleteEvent.getSource()).removeStreamCompleteListener(this);
        handleException(streamCompleteEvent.getException(), Long.valueOf(streamCompleteEvent.getBytes()));
    }

    private void handleException(Exception exception) {
        handleException(exception, null);
    }

    private void handleException(Exception exception, Long l) {
        TransactionStateUtil.setErrorCodeFromException(this.transactionState, exception);
        if (!this.transactionState.isComplete()) {
            if (l != null) {
                this.transactionState.setBytesSent(l.longValue());
            }
            TransactionData end = this.transactionState.end();
            if (end != null) {
                TaskQueue.queue(new HttpTransactionMeasurement(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), end.getErrorCode(), end.getTimestamp(), (double) end.getTime(), end.getBytesSent(), end.getBytesReceived(), end.getAppData()));
            }
        }
    }
}
