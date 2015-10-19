package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.httpclient.ResponseHandlerImpl;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public final class HttpInstrumentation {
    private HttpInstrumentation() {
    }

    @WrapReturn(className = "java/net/URL", methodDesc = "()Ljava/net/URLConnection;", methodName = "openConnection")
    public static URLConnection openConnection(URLConnection uRLConnection) {
        if (uRLConnection instanceof HttpsURLConnection) {
            return new HttpsURLConnectionExtension((HttpsURLConnection) uRLConnection);
        }
        if (uRLConnection instanceof HttpURLConnection) {
            return new HttpURLConnectionExtension((HttpURLConnection) uRLConnection);
        }
        return uRLConnection;
    }

    @WrapReturn(className = "java.net.URL", methodDesc = "(Ljava/net/Proxy;)Ljava/net/URLConnection;", methodName = "openConnection")
    public static URLConnection openConnectionWithProxy(URLConnection uRLConnection) {
        if (uRLConnection instanceof HttpsURLConnection) {
            return new HttpsURLConnectionExtension((HttpsURLConnection) uRLConnection);
        }
        if (uRLConnection instanceof HttpURLConnection) {
            return new HttpURLConnectionExtension((HttpURLConnection) uRLConnection);
        }
        return uRLConnection;
    }

    @ReplaceCallSite
    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        TransactionState transactionState = new TransactionState();
        try {
            return m12805_(httpClient.execute(httpHost, m12804_(httpHost, httpRequest, transactionState), httpContext), transactionState);
        } catch (Exception e) {
            httpClientError(transactionState, e);
            throw e;
        }
    }

    @ReplaceCallSite
    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        TransactionState transactionState = new TransactionState();
        try {
            return httpClient.execute(httpHost, m12804_(httpHost, httpRequest, transactionState), m12806_((ResponseHandler) responseHandler, transactionState), httpContext);
        } catch (Exception e) {
            httpClientError(transactionState, e);
            throw e;
        } catch (Exception e2) {
            httpClientError(transactionState, e2);
            throw e2;
        }
    }

    @ReplaceCallSite
    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        TransactionState transactionState = new TransactionState();
        try {
            return httpClient.execute(httpHost, m12804_(httpHost, httpRequest, transactionState), m12806_((ResponseHandler) responseHandler, transactionState));
        } catch (Exception e) {
            httpClientError(transactionState, e);
            throw e;
        } catch (Exception e2) {
            httpClientError(transactionState, e2);
            throw e2;
        }
    }

    @ReplaceCallSite
    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        TransactionState transactionState = new TransactionState();
        try {
            return m12805_(httpClient.execute(httpHost, m12804_(httpHost, httpRequest, transactionState)), transactionState);
        } catch (Exception e) {
            httpClientError(transactionState, e);
            throw e;
        }
    }

    @ReplaceCallSite
    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        TransactionState transactionState = new TransactionState();
        try {
            return m12805_(httpClient.execute(m12807_(httpUriRequest, transactionState), httpContext), transactionState);
        } catch (Exception e) {
            httpClientError(transactionState, e);
            throw e;
        }
    }

    @ReplaceCallSite
    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        TransactionState transactionState = new TransactionState();
        try {
            return httpClient.execute(m12807_(httpUriRequest, transactionState), m12806_((ResponseHandler) responseHandler, transactionState), httpContext);
        } catch (Exception e) {
            httpClientError(transactionState, e);
            throw e;
        } catch (Exception e2) {
            httpClientError(transactionState, e2);
            throw e2;
        }
    }

    @ReplaceCallSite
    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        TransactionState transactionState = new TransactionState();
        try {
            return httpClient.execute(m12807_(httpUriRequest, transactionState), m12806_((ResponseHandler) responseHandler, transactionState));
        } catch (Exception e) {
            httpClientError(transactionState, e);
            throw e;
        } catch (Exception e2) {
            httpClientError(transactionState, e2);
            throw e2;
        }
    }

    @ReplaceCallSite
    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest) throws IOException {
        TransactionState transactionState = new TransactionState();
        try {
            return m12805_(httpClient.execute(m12807_(httpUriRequest, transactionState)), transactionState);
        } catch (Exception e) {
            httpClientError(transactionState, e);
            throw e;
        }
    }

    private static void httpClientError(TransactionState transactionState, Exception exception) {
        if (!transactionState.isComplete()) {
            TransactionStateUtil.setErrorCodeFromException(transactionState, exception);
            TransactionData end = transactionState.end();
            if (end != null) {
                TaskQueue.queue(new HttpTransactionMeasurement(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), end.getErrorCode(), end.getTimestamp(), (double) end.getTime(), end.getBytesSent(), end.getBytesReceived(), end.getAppData()));
            }
        }
    }

    private static HttpUriRequest m12807_(HttpUriRequest httpUriRequest, TransactionState transactionState) {
        return TransactionStateUtil.inspectAndInstrument(transactionState, httpUriRequest);
    }

    private static HttpRequest m12804_(HttpHost httpHost, HttpRequest httpRequest, TransactionState transactionState) {
        return TransactionStateUtil.inspectAndInstrument(transactionState, httpHost, httpRequest);
    }

    private static HttpResponse m12805_(HttpResponse httpResponse, TransactionState transactionState) {
        return TransactionStateUtil.inspectAndInstrument(transactionState, httpResponse);
    }

    private static <T> ResponseHandler<? extends T> m12806_(ResponseHandler<? extends T> responseHandler, TransactionState transactionState) {
        return ResponseHandlerImpl.wrap(responseHandler, transactionState);
    }
}
