package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import com.newrelic.agent.android.instrumentation.io.CountingOutputStream;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListener;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListenerSource;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.message.AbstractHttpMessage;

public final class HttpResponseEntityImpl implements StreamCompleteListener, HttpEntity {
    private static final String ENCODING_CHUNKED = "chunked";
    private static final String TRANSFER_ENCODING_HEADER = "Transfer-Encoding";
    private static final AgentLog log;
    private final long contentLengthFromHeader;
    private CountingInputStream contentStream;
    private final HttpEntity impl;
    private final TransactionState transactionState;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public HttpResponseEntityImpl(HttpEntity httpEntity, TransactionState transactionState, long j) {
        this.impl = httpEntity;
        this.transactionState = transactionState;
        this.contentLengthFromHeader = j;
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
        boolean z = true;
        if (this.contentStream != null) {
            return this.contentStream;
        }
        try {
            if (this.impl instanceof AbstractHttpMessage) {
                boolean z2;
                Header lastHeader = ((AbstractHttpMessage) this.impl).getLastHeader(TRANSFER_ENCODING_HEADER);
                if (lastHeader == null || !ENCODING_CHUNKED.equalsIgnoreCase(lastHeader.getValue())) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = z2;
            } else if ((this.impl instanceof HttpEntityWrapper) && ((HttpEntityWrapper) this.impl).isChunked()) {
                z = false;
            }
            this.contentStream = new CountingInputStream(this.impl.getContent(), z);
            this.contentStream.addStreamCompleteListener(this);
            return this.contentStream;
        } catch (Exception e) {
            handleException(e);
            throw e;
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
        if (this.transactionState.isComplete()) {
            this.impl.writeTo(outputStream);
            return;
        }
        OutputStream countingOutputStream = new CountingOutputStream(outputStream);
        try {
            this.impl.writeTo(countingOutputStream);
            if (!this.transactionState.isComplete()) {
                if (this.contentLengthFromHeader >= 0) {
                    this.transactionState.setBytesReceived(this.contentLengthFromHeader);
                } else {
                    this.transactionState.setBytesReceived(countingOutputStream.getCount());
                }
                addTransactionAndErrorData(this.transactionState);
            }
        } catch (Exception e) {
            handleException(e, Long.valueOf(countingOutputStream.getCount()));
            e.printStackTrace();
            throw e;
        }
    }

    public void streamComplete(StreamCompleteEvent streamCompleteEvent) {
        ((StreamCompleteListenerSource) streamCompleteEvent.getSource()).removeStreamCompleteListener(this);
        if (!this.transactionState.isComplete()) {
            if (this.contentLengthFromHeader >= 0) {
                this.transactionState.setBytesReceived(this.contentLengthFromHeader);
            } else {
                this.transactionState.setBytesReceived(streamCompleteEvent.getBytes());
            }
            addTransactionAndErrorData(this.transactionState);
        }
    }

    public void streamError(StreamCompleteEvent streamCompleteEvent) {
        ((StreamCompleteListenerSource) streamCompleteEvent.getSource()).removeStreamCompleteListener(this);
        TransactionStateUtil.setErrorCodeFromException(this.transactionState, streamCompleteEvent.getException());
        if (!this.transactionState.isComplete()) {
            this.transactionState.setBytesReceived(streamCompleteEvent.getBytes());
        }
    }

    private void addTransactionAndErrorData(TransactionState transactionState) {
        TransactionData end = transactionState.end();
        if (end != null) {
            TaskQueue.queue(new HttpTransactionMeasurement(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), end.getErrorCode(), end.getTimestamp(), (double) end.getTime(), end.getBytesSent(), end.getBytesReceived(), end.getAppData()));
            if (((long) transactionState.getStatusCode()) >= 400) {
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    InputStream content = getContent();
                    if (content instanceof CountingInputStream) {
                        stringBuilder.append(((CountingInputStream) content).getBufferAsString());
                    }
                } catch (Exception e) {
                    log.error(e.toString());
                }
                Header contentType = this.impl.getContentType();
                Map treeMap = new TreeMap();
                if (!(contentType == null || contentType.getValue() == null || Trace.NULL.equals(contentType.getValue()))) {
                    treeMap.put("content_type", contentType.getValue());
                }
                treeMap.put("content_length", transactionState.getBytesReceived() + Trace.NULL);
                Measurements.addHttpError(end, stringBuilder.toString(), treeMap);
            }
        }
    }

    private void handleException(Exception exception) {
        handleException(exception, null);
    }

    private void handleException(Exception exception, Long l) {
        TransactionStateUtil.setErrorCodeFromException(this.transactionState, exception);
        if (!this.transactionState.isComplete()) {
            if (l != null) {
                this.transactionState.setBytesReceived(l.longValue());
            }
            TransactionData end = this.transactionState.end();
            if (end != null) {
                TaskQueue.queue(new HttpTransactionMeasurement(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), end.getErrorCode(), end.getTimestamp(), (double) end.getTime(), end.getBytesSent(), end.getBytesReceived(), end.getAppData()));
            }
        }
    }
}
