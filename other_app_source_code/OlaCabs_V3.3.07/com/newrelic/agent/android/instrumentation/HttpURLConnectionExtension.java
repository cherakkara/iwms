package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import com.newrelic.agent.android.instrumentation.io.CountingOutputStream;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListener;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HttpURLConnectionExtension extends HttpURLConnection {
    private static final AgentLog log;
    private HttpURLConnection impl;
    private TransactionState transactionState;

    /* renamed from: com.newrelic.agent.android.instrumentation.HttpURLConnectionExtension.1 */
    class C07381 implements StreamCompleteListener {
        final /* synthetic */ TransactionState val$transactionState;

        C07381(TransactionState transactionState) {
            this.val$transactionState = transactionState;
        }

        public void streamError(StreamCompleteEvent streamCompleteEvent) {
            if (!this.val$transactionState.isComplete()) {
                this.val$transactionState.setBytesReceived(streamCompleteEvent.getBytes());
            }
            HttpURLConnectionExtension.this.error(streamCompleteEvent.getException());
        }

        public void streamComplete(StreamCompleteEvent streamCompleteEvent) {
            if (!this.val$transactionState.isComplete()) {
                long contentLength = (long) HttpURLConnectionExtension.this.impl.getContentLength();
                long bytes = streamCompleteEvent.getBytes();
                if (contentLength < 0) {
                    contentLength = bytes;
                }
                this.val$transactionState.setBytesReceived(contentLength);
                HttpURLConnectionExtension.this.addTransactionAndErrorData(this.val$transactionState);
            }
        }
    }

    /* renamed from: com.newrelic.agent.android.instrumentation.HttpURLConnectionExtension.2 */
    class C07392 implements StreamCompleteListener {
        final /* synthetic */ TransactionState val$transactionState;

        C07392(TransactionState transactionState) {
            this.val$transactionState = transactionState;
        }

        public void streamError(StreamCompleteEvent streamCompleteEvent) {
            if (!this.val$transactionState.isComplete()) {
                this.val$transactionState.setBytesSent(streamCompleteEvent.getBytes());
            }
            HttpURLConnectionExtension.this.error(streamCompleteEvent.getException());
        }

        public void streamComplete(StreamCompleteEvent streamCompleteEvent) {
            if (!this.val$transactionState.isComplete()) {
                String requestProperty = HttpURLConnectionExtension.this.impl.getRequestProperty("content-length");
                long bytes = streamCompleteEvent.getBytes();
                if (requestProperty != null) {
                    try {
                        bytes = Long.parseLong(requestProperty);
                    } catch (NumberFormatException e) {
                    }
                }
                this.val$transactionState.setBytesSent(bytes);
                HttpURLConnectionExtension.this.addTransactionAndErrorData(this.val$transactionState);
            }
        }
    }

    static {
        log = AgentLogManager.getAgentLog();
    }

    public HttpURLConnectionExtension(HttpURLConnection httpURLConnection) {
        super(httpURLConnection.getURL());
        this.impl = httpURLConnection;
        TransactionStateUtil.setCrossProcessHeader(httpURLConnection);
    }

    public void addRequestProperty(String str, String str2) {
        this.impl.addRequestProperty(str, str2);
    }

    public void disconnect() {
        if (!(this.transactionState == null || this.transactionState.isComplete())) {
            addTransactionAndErrorData(this.transactionState);
        }
        this.impl.disconnect();
    }

    public boolean usingProxy() {
        return this.impl.usingProxy();
    }

    public void connect() throws IOException {
        getTransactionState();
        try {
            this.impl.connect();
        } catch (Exception e) {
            error(e);
            throw e;
        }
    }

    public boolean getAllowUserInteraction() {
        return this.impl.getAllowUserInteraction();
    }

    public int getConnectTimeout() {
        return this.impl.getConnectTimeout();
    }

    public Object getContent() throws IOException {
        getTransactionState();
        try {
            Object content = this.impl.getContent();
            int contentLength = this.impl.getContentLength();
            if (contentLength >= 0) {
                TransactionState transactionState = getTransactionState();
                if (!transactionState.isComplete()) {
                    transactionState.setBytesReceived((long) contentLength);
                    addTransactionAndErrorData(transactionState);
                }
            }
            return content;
        } catch (Exception e) {
            error(e);
            throw e;
        }
    }

    public Object getContent(Class[] clsArr) throws IOException {
        getTransactionState();
        try {
            Object content = this.impl.getContent(clsArr);
            checkResponse();
            return content;
        } catch (Exception e) {
            error(e);
            throw e;
        }
    }

    public String getContentEncoding() {
        getTransactionState();
        String contentEncoding = this.impl.getContentEncoding();
        checkResponse();
        return contentEncoding;
    }

    public int getContentLength() {
        getTransactionState();
        int contentLength = this.impl.getContentLength();
        checkResponse();
        return contentLength;
    }

    public String getContentType() {
        getTransactionState();
        String contentType = this.impl.getContentType();
        checkResponse();
        return contentType;
    }

    public long getDate() {
        getTransactionState();
        long date = this.impl.getDate();
        checkResponse();
        return date;
    }

    public InputStream getErrorStream() {
        getTransactionState();
        try {
            return new CountingInputStream(this.impl.getErrorStream(), true);
        } catch (Exception e) {
            log.error(e.toString());
            return this.impl.getErrorStream();
        }
    }

    public long getHeaderFieldDate(String str, long j) {
        getTransactionState();
        long headerFieldDate = this.impl.getHeaderFieldDate(str, j);
        checkResponse();
        return headerFieldDate;
    }

    public boolean getInstanceFollowRedirects() {
        return this.impl.getInstanceFollowRedirects();
    }

    public Permission getPermission() throws IOException {
        return this.impl.getPermission();
    }

    public String getRequestMethod() {
        return this.impl.getRequestMethod();
    }

    public int getResponseCode() throws IOException {
        getTransactionState();
        try {
            int responseCode = this.impl.getResponseCode();
            checkResponse();
            return responseCode;
        } catch (Exception e) {
            error(e);
            throw e;
        }
    }

    public String getResponseMessage() throws IOException {
        getTransactionState();
        try {
            String responseMessage = this.impl.getResponseMessage();
            checkResponse();
            return responseMessage;
        } catch (Exception e) {
            error(e);
            throw e;
        }
    }

    public void setChunkedStreamingMode(int i) {
        this.impl.setChunkedStreamingMode(i);
    }

    public void setFixedLengthStreamingMode(int i) {
        this.impl.setFixedLengthStreamingMode(i);
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.impl.setInstanceFollowRedirects(z);
    }

    public void setRequestMethod(String str) throws ProtocolException {
        getTransactionState();
        try {
            this.impl.setRequestMethod(str);
        } catch (Exception e) {
            error(e);
            throw e;
        }
    }

    public boolean getDefaultUseCaches() {
        return this.impl.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return this.impl.getDoInput();
    }

    public boolean getDoOutput() {
        return this.impl.getDoOutput();
    }

    public long getExpiration() {
        getTransactionState();
        long expiration = this.impl.getExpiration();
        checkResponse();
        return expiration;
    }

    public String getHeaderField(int i) {
        getTransactionState();
        String headerField = this.impl.getHeaderField(i);
        checkResponse();
        return headerField;
    }

    public String getHeaderField(String str) {
        getTransactionState();
        String headerField = this.impl.getHeaderField(str);
        checkResponse();
        return headerField;
    }

    public int getHeaderFieldInt(String str, int i) {
        getTransactionState();
        int headerFieldInt = this.impl.getHeaderFieldInt(str, i);
        checkResponse();
        return headerFieldInt;
    }

    public String getHeaderFieldKey(int i) {
        getTransactionState();
        String headerFieldKey = this.impl.getHeaderFieldKey(i);
        checkResponse();
        return headerFieldKey;
    }

    public Map<String, List<String>> getHeaderFields() {
        getTransactionState();
        Map<String, List<String>> headerFields = this.impl.getHeaderFields();
        checkResponse();
        return headerFields;
    }

    public long getIfModifiedSince() {
        getTransactionState();
        long ifModifiedSince = this.impl.getIfModifiedSince();
        checkResponse();
        return ifModifiedSince;
    }

    public InputStream getInputStream() throws IOException {
        TransactionState transactionState = getTransactionState();
        try {
            InputStream countingInputStream = new CountingInputStream(this.impl.getInputStream());
            TransactionStateUtil.inspectAndInstrumentResponse(transactionState, this.impl);
            countingInputStream.addStreamCompleteListener(new C07381(transactionState));
            return countingInputStream;
        } catch (Exception e) {
            error(e);
            throw e;
        }
    }

    public long getLastModified() {
        getTransactionState();
        long lastModified = this.impl.getLastModified();
        checkResponse();
        return lastModified;
    }

    public OutputStream getOutputStream() throws IOException {
        TransactionState transactionState = getTransactionState();
        try {
            OutputStream countingOutputStream = new CountingOutputStream(this.impl.getOutputStream());
            countingOutputStream.addStreamCompleteListener(new C07392(transactionState));
            return countingOutputStream;
        } catch (Exception e) {
            error(e);
            throw e;
        }
    }

    public int getReadTimeout() {
        return this.impl.getReadTimeout();
    }

    public Map<String, List<String>> getRequestProperties() {
        return this.impl.getRequestProperties();
    }

    public String getRequestProperty(String str) {
        return this.impl.getRequestProperty(str);
    }

    public URL getURL() {
        return this.impl.getURL();
    }

    public boolean getUseCaches() {
        return this.impl.getUseCaches();
    }

    public void setAllowUserInteraction(boolean z) {
        this.impl.setAllowUserInteraction(z);
    }

    public void setConnectTimeout(int i) {
        this.impl.setConnectTimeout(i);
    }

    public void setDefaultUseCaches(boolean z) {
        this.impl.setDefaultUseCaches(z);
    }

    public void setDoInput(boolean z) {
        this.impl.setDoInput(z);
    }

    public void setDoOutput(boolean z) {
        this.impl.setDoOutput(z);
    }

    public void setIfModifiedSince(long j) {
        this.impl.setIfModifiedSince(j);
    }

    public void setReadTimeout(int i) {
        this.impl.setReadTimeout(i);
    }

    public void setRequestProperty(String str, String str2) {
        this.impl.setRequestProperty(str, str2);
    }

    public void setUseCaches(boolean z) {
        this.impl.setUseCaches(z);
    }

    public String toString() {
        return this.impl.toString();
    }

    private void checkResponse() {
        if (!getTransactionState().isComplete()) {
            TransactionStateUtil.inspectAndInstrumentResponse(getTransactionState(), this.impl);
        }
    }

    private TransactionState getTransactionState() {
        if (this.transactionState == null) {
            this.transactionState = new TransactionState();
            TransactionStateUtil.inspectAndInstrument(this.transactionState, this.impl);
        }
        return this.transactionState;
    }

    private void error(Exception exception) {
        TransactionState transactionState = getTransactionState();
        TransactionStateUtil.setErrorCodeFromException(transactionState, exception);
        if (!transactionState.isComplete()) {
            TransactionStateUtil.inspectAndInstrumentResponse(transactionState, this.impl);
            TransactionData end = transactionState.end();
            if (end != null) {
                TaskQueue.queue(new HttpTransactionMeasurement(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), end.getErrorCode(), end.getTimestamp(), (double) end.getTime(), end.getBytesSent(), end.getBytesReceived(), end.getAppData()));
            }
        }
    }

    private void addTransactionAndErrorData(TransactionState transactionState) {
        TransactionData end = transactionState.end();
        if (end != null) {
            TaskQueue.queue(new HttpTransactionMeasurement(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), end.getErrorCode(), end.getTimestamp(), (double) end.getTime(), end.getBytesSent(), end.getBytesReceived(), end.getAppData()));
            if (((long) transactionState.getStatusCode()) >= 400) {
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    InputStream errorStream = getErrorStream();
                    if (errorStream instanceof CountingInputStream) {
                        stringBuilder.append(((CountingInputStream) errorStream).getBufferAsString());
                    }
                } catch (Exception e) {
                    log.error(e.toString());
                }
                Map treeMap = new TreeMap();
                String contentType = this.impl.getContentType();
                if (!(contentType == null || Trace.NULL.equals(contentType))) {
                    treeMap.put("content_type", contentType);
                }
                treeMap.put("content_length", transactionState.getBytesReceived() + Trace.NULL);
                Measurements.addHttpError(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), stringBuilder.toString(), treeMap);
            }
        }
    }
}
