package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.apsalar.sdk.Constants;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.protocol.HTTP;

/* renamed from: com.android.volley.toolbox.g */
public class HurlStack implements HttpStack {
    private final HurlStack f566a;
    private final SSLSocketFactory f567b;

    /* renamed from: com.android.volley.toolbox.g.a */
    public interface HurlStack {
        String m648a(String str);
    }

    public HurlStack() {
        this(null);
    }

    public HurlStack(HurlStack hurlStack) {
        this(hurlStack, null);
    }

    public HurlStack(HurlStack hurlStack, SSLSocketFactory sSLSocketFactory) {
        this.f566a = hurlStack;
        this.f567b = sSLSocketFactory;
    }

    public HttpResponse m654a(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        String a;
        String url = request.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(request.getHeaders());
        hashMap.putAll(map);
        if (this.f566a != null) {
            a = this.f566a.m648a(url);
            if (a == null) {
                throw new IOException("URL blocked by rewriter: " + url);
            }
        }
        a = url;
        HttpURLConnection a2 = m649a(new URL(a), (Request) request);
        for (String a3 : hashMap.keySet()) {
            a2.addRequestProperty(a3, (String) hashMap.get(a3));
        }
        HurlStack.m651a(a2, (Request) request);
        ProtocolVersion protocolVersion = new ProtocolVersion(HttpVersion.HTTP, 1, 1);
        if (a2.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        HttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, a2.getResponseCode(), a2.getResponseMessage()));
        basicHttpResponse.setEntity(HurlStack.m650a(a2));
        for (Entry entry : a2.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }

    private static HttpEntity m650a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    protected HttpURLConnection m653a(URL url) throws IOException {
        return (HttpURLConnection) HttpInstrumentation.openConnection(url.openConnection());
    }

    private HttpURLConnection m649a(URL url, Request<?> request) throws IOException {
        HttpURLConnection a = m653a(url);
        int timeoutMs = request.getTimeoutMs();
        a.setConnectTimeout(timeoutMs);
        a.setReadTimeout(timeoutMs);
        a.setUseCaches(false);
        a.setDoInput(true);
        if (Constants.API_PROTOCOL.equals(url.getProtocol()) && this.f567b != null) {
            ((HttpsURLConnection) a).setSSLSocketFactory(this.f567b);
        }
        return a;
    }

    static void m651a(HttpURLConnection httpURLConnection, Request<?> request) throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            case ContentLengthStrategy.IDENTITY /*-1*/:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty(HTTP.CONTENT_TYPE, request.getPostBodyContentType());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(postBody);
                    dataOutputStream.close();
                }
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                httpURLConnection.setRequestMethod("GET");
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                httpURLConnection.setRequestMethod("POST");
                HurlStack.m652b(httpURLConnection, request);
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                httpURLConnection.setRequestMethod("PUT");
                HurlStack.m652b(httpURLConnection, request);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                httpURLConnection.setRequestMethod("DELETE");
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                httpURLConnection.setRequestMethod("HEAD");
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                httpURLConnection.setRequestMethod("OPTIONS");
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                httpURLConnection.setRequestMethod("TRACE");
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                httpURLConnection.setRequestMethod("PATCH");
                HurlStack.m652b(httpURLConnection, request);
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static void m652b(HttpURLConnection httpURLConnection, Request<?> request) throws IOException, AuthFailureError {
        byte[] body = request.getBody();
        if (body != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty(HTTP.CONTENT_TYPE, request.getBodyContentType());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(body);
            dataOutputStream.close();
        }
    }
}
