package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.olacabs.customer.p076d.by;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

/* renamed from: com.android.volley.toolbox.d */
public class HttpClientStack implements HttpStack {
    protected final HttpClient f565a;

    /* renamed from: com.android.volley.toolbox.d.a */
    public static final class HttpClientStack extends HttpEntityEnclosingRequestBase {
        public HttpClientStack(String str) {
            setURI(URI.create(str));
        }

        public String getMethod() {
            return "PATCH";
        }
    }

    public HttpClientStack(HttpClient httpClient) {
        this.f565a = httpClient;
    }

    private static void m641a(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, (String) map.get(str));
        }
    }

    public HttpResponse m643a(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        HttpUriRequest b = HttpClientStack.m642b(request, map);
        HttpClientStack.m641a(b, (Map) map);
        HttpClientStack.m641a(b, request.getHeaders());
        m644a(b);
        HttpParams params = b.getParams();
        int timeoutMs = request.getTimeoutMs();
        HttpConnectionParams.setConnectionTimeout(params, by.DEFAULT_TIMEOUT_MS);
        HttpConnectionParams.setSoTimeout(params, timeoutMs);
        HttpClient httpClient = this.f565a;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(b) : HttpInstrumentation.execute(httpClient, b);
    }

    static HttpUriRequest m642b(Request<?> request, Map<String, String> map) throws AuthFailureError {
        HttpEntityEnclosingRequestBase httpPost;
        switch (request.getMethod()) {
            case ContentLengthStrategy.IDENTITY /*-1*/:
                byte[] postBody = request.getPostBody();
                if (postBody == null) {
                    return new HttpGet(request.getUrl());
                }
                HttpUriRequest httpPost2 = new HttpPost(request.getUrl());
                httpPost2.addHeader(HTTP.CONTENT_TYPE, request.getPostBodyContentType());
                httpPost2.setEntity(new ByteArrayEntity(postBody));
                return httpPost2;
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return new HttpGet(request.getUrl());
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                httpPost = new HttpPost(request.getUrl());
                httpPost.addHeader(HTTP.CONTENT_TYPE, request.getBodyContentType());
                HttpClientStack.m640a(httpPost, (Request) request);
                return httpPost;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                httpPost = new HttpPut(request.getUrl());
                httpPost.addHeader(HTTP.CONTENT_TYPE, request.getBodyContentType());
                HttpClientStack.m640a(httpPost, (Request) request);
                return httpPost;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return new HttpDelete(request.getUrl());
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return new HttpHead(request.getUrl());
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return new HttpOptions(request.getUrl());
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                return new HttpTrace(request.getUrl());
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                httpPost = new HttpClientStack(request.getUrl());
                httpPost.addHeader(HTTP.CONTENT_TYPE, request.getBodyContentType());
                HttpClientStack.m640a(httpPost, (Request) request);
                return httpPost;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
    }

    private static void m640a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, Request<?> request) throws AuthFailureError {
        byte[] body = request.getBody();
        if (body != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(body));
        }
    }

    protected void m644a(HttpUriRequest httpUriRequest) throws IOException {
    }
}
