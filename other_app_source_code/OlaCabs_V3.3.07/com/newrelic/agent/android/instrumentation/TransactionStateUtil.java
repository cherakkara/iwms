package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import com.newrelic.agent.android.instrumentation.httpclient.ContentBufferingResponseEntityImpl;
import com.newrelic.agent.android.instrumentation.httpclient.HttpRequestEntityImpl;
import com.newrelic.agent.android.instrumentation.httpclient.HttpResponseEntityImpl;
import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.ExceptionHelper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpUriRequest;

public class TransactionStateUtil implements HarvestErrorCodes {
    public static final String APP_DATA_HEADER = "X-NewRelic-App-Data";
    private static final String CONTENT_LENGTH_HEADER = "Content-Length";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String CROSS_PROCESS_ID_HEADER = "X-NewRelic-ID";
    private static final AgentLog log;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public static void inspectAndInstrument(TransactionState transactionState, String str, String str2) {
        transactionState.setUrl(str);
        transactionState.setHttpMethod(str2);
        transactionState.setCarrier(Agent.getActiveNetworkCarrier());
        transactionState.setWanType(Agent.getActiveNetworkWanType());
    }

    public static void inspectAndInstrument(TransactionState transactionState, HttpURLConnection httpURLConnection) {
        inspectAndInstrument(transactionState, httpURLConnection.getURL().toString(), httpURLConnection.getRequestMethod());
    }

    public static void setCrossProcessHeader(HttpURLConnection httpURLConnection) {
        String crossProcessId = Agent.getCrossProcessId();
        if (crossProcessId != null) {
            httpURLConnection.setRequestProperty(CROSS_PROCESS_ID_HEADER.toLowerCase(Locale.ENGLISH), crossProcessId);
        }
    }

    public static void inspectAndInstrumentResponse(TransactionState transactionState, String str, int i, int i2) {
        if (!(str == null || Trace.NULL.equals(str))) {
            transactionState.setAppData(str);
        }
        if (i >= 0) {
            transactionState.setBytesReceived((long) i);
        }
        transactionState.setStatusCode(i2);
    }

    public static void inspectAndInstrumentResponse(TransactionState transactionState, HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField(APP_DATA_HEADER);
        int contentLength = httpURLConnection.getContentLength();
        int i = 0;
        try {
            i = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            log.debug("Failed to retrieve response code due to an I/O exception: " + e.getMessage());
        } catch (Throwable e2) {
            log.error("Failed to retrieve response code due to underlying (Harmony?) NPE", e2);
        }
        inspectAndInstrumentResponse(transactionState, headerField, contentLength, i);
    }

    public static HttpRequest inspectAndInstrument(TransactionState transactionState, HttpHost httpHost, HttpRequest httpRequest) {
        addCrossProcessIdHeader(httpRequest);
        String str = null;
        RequestLine requestLine = httpRequest.getRequestLine();
        if (requestLine != null) {
            String uri = requestLine.getUri();
            int i = (uri == null || uri.length() < 10 || uri.substring(0, 10).indexOf("://") < 0) ? 0 : 1;
            if (i == 0 && uri != null && httpHost != null) {
                str = httpHost.toURI().toString();
                StringBuilder append = new StringBuilder().append(str);
                str = (str.endsWith("/") || uri.startsWith("/")) ? Trace.NULL : "/";
                str = append.append(str).append(uri).toString();
            } else if (i != 0) {
                str = uri;
            }
        }
        if (transactionState.getUrl() == null || transactionState.getHttpMethod() == null) {
            try {
                throw new Exception("TransactionData constructor was not provided with a valid URL, host or HTTP method");
            } catch (Throwable e) {
                AgentLogManager.getAgentLog().error(MessageFormat.format("TransactionStateUtil.inspectAndInstrument(...) for {0} could not determine request URL or HTTP method [host={1}, requestLine={2}]", new Object[]{httpRequest.getClass().getCanonicalName(), httpHost, requestLine}), e);
            }
        } else {
            inspectAndInstrument(transactionState, str, requestLine.getMethod());
            wrapRequestEntity(transactionState, httpRequest);
            return httpRequest;
        }
    }

    public static HttpUriRequest inspectAndInstrument(TransactionState transactionState, HttpUriRequest httpUriRequest) {
        addCrossProcessIdHeader(httpUriRequest);
        inspectAndInstrument(transactionState, httpUriRequest.getURI().toString(), httpUriRequest.getMethod());
        wrapRequestEntity(transactionState, httpUriRequest);
        return httpUriRequest;
    }

    private static void addCrossProcessIdHeader(HttpRequest httpRequest) {
        String crossProcessId = Agent.getCrossProcessId();
        if (crossProcessId != null) {
            TraceMachine.setCurrentTraceParam("cross_process_data", crossProcessId);
            httpRequest.setHeader(CROSS_PROCESS_ID_HEADER, crossProcessId);
        }
    }

    private static void wrapRequestEntity(TransactionState transactionState, HttpRequest httpRequest) {
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest) httpRequest;
            if (httpEntityEnclosingRequest.getEntity() != null) {
                httpEntityEnclosingRequest.setEntity(new HttpRequestEntityImpl(httpEntityEnclosingRequest.getEntity(), transactionState));
            }
        }
    }

    public static HttpResponse inspectAndInstrument(TransactionState transactionState, HttpResponse httpResponse) {
        transactionState.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        Header[] headers = httpResponse.getHeaders(APP_DATA_HEADER);
        if (!(headers == null || headers.length <= 0 || Trace.NULL.equals(headers[0].getValue()))) {
            transactionState.setAppData(headers[0].getValue());
        }
        headers = httpResponse.getHeaders(CONTENT_LENGTH_HEADER);
        if (headers != null && headers.length > 0) {
            try {
                transactionState.setBytesReceived(Long.parseLong(headers[0].getValue()));
                addTransactionAndErrorData(transactionState, httpResponse);
            } catch (NumberFormatException e) {
                log.warning("Failed to parse content length: " + e.toString());
            }
        } else if (httpResponse.getEntity() != null) {
            httpResponse.setEntity(new HttpResponseEntityImpl(httpResponse.getEntity(), transactionState, -1));
        } else {
            transactionState.setBytesReceived(0);
            addTransactionAndErrorData(transactionState, null);
        }
        return httpResponse;
    }

    public static void setErrorCodeFromException(TransactionState transactionState, Exception exception) {
        int exceptionToErrorCode = ExceptionHelper.exceptionToErrorCode(exception);
        log.error("TransactionStateUtil: Attempting to convert network exception " + exception.getClass().getName() + " to error code.");
        transactionState.setErrorCode(exceptionToErrorCode);
    }

    private static void addTransactionAndErrorData(TransactionState transactionState, HttpResponse httpResponse) {
        TransactionData end = transactionState.end();
        if (end != null) {
            TaskQueue.queue(new HttpTransactionMeasurement(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), end.getErrorCode(), end.getTimestamp(), (double) end.getTime(), end.getBytesSent(), end.getBytesReceived(), end.getAppData()));
            if (((long) transactionState.getStatusCode()) >= 400) {
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    Header[] headers;
                    String str;
                    Map treeMap;
                    if (httpResponse.getEntity() != null) {
                        if (!(httpResponse.getEntity() instanceof HttpRequestEntityImpl)) {
                            httpResponse.setEntity(new ContentBufferingResponseEntityImpl(httpResponse.getEntity()));
                        }
                        InputStream content = httpResponse.getEntity().getContent();
                        if (content instanceof CountingInputStream) {
                            stringBuilder.append(((CountingInputStream) content).getBufferAsString());
                        } else {
                            log.error("Unable to wrap content stream for entity");
                        }
                        headers = httpResponse.getHeaders(CONTENT_TYPE_HEADER);
                        str = null;
                        if (!(headers == null || headers.length <= 0 || Trace.NULL.equals(headers[0].getValue()))) {
                            str = headers[0].getValue();
                        }
                        treeMap = new TreeMap();
                        if (str != null && str.length() > 0) {
                            treeMap.put("content_type", str);
                        }
                        treeMap.put("content_length", transactionState.getBytesReceived() + Trace.NULL);
                        Measurements.addHttpError(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), stringBuilder.toString(), treeMap);
                    }
                    log.debug("null response entity. response-body will be reported empty");
                    headers = httpResponse.getHeaders(CONTENT_TYPE_HEADER);
                    str = null;
                    str = headers[0].getValue();
                    treeMap = new TreeMap();
                    treeMap.put("content_type", str);
                    treeMap.put("content_length", transactionState.getBytesReceived() + Trace.NULL);
                    Measurements.addHttpError(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), stringBuilder.toString(), treeMap);
                } catch (IllegalStateException e) {
                    log.error(e.toString());
                } catch (IOException e2) {
                    log.error(e2.toString());
                }
            }
        }
    }
}
