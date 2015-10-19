package com.google.android.m4b.maps.ak;

import com.apsalar.sdk.Constants;
import com.google.android.m4b.maps.p038a.Request;
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

/* renamed from: com.google.android.m4b.maps.ak.f */
public class HurlStack implements HttpStack {
    private final HurlStack f3264a;
    private final SSLSocketFactory f3265b;

    /* renamed from: com.google.android.m4b.maps.ak.f.a */
    public interface HurlStack {
        String m5243a();
    }

    public HurlStack() {
        this(null);
    }

    private HurlStack(HurlStack hurlStack) {
        this(null, null);
    }

    private HurlStack(HurlStack hurlStack, SSLSocketFactory sSLSocketFactory) {
        this.f3264a = hurlStack;
        this.f3265b = null;
    }

    public HttpResponse m5246a(Request<?> request, Map<String, String> map) {
        String a;
        String c = request.m4707c();
        HashMap hashMap = new HashMap();
        hashMap.putAll(Request.m4695h());
        hashMap.putAll(map);
        if (this.f3264a != null) {
            a = this.f3264a.m5243a();
            if (a == null) {
                throw new IOException("URL blocked by rewriter: " + c);
            }
        }
        a = c;
        URL url = new URL(a);
        HttpURLConnection httpURLConnection = (HttpURLConnection) HttpInstrumentation.openConnection(url.openConnection());
        int n = request.m4717n();
        httpURLConnection.setConnectTimeout(n);
        httpURLConnection.setReadTimeout(n);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        if (Constants.API_PROTOCOL.equals(url.getProtocol()) && this.f3265b != null) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.f3265b);
        }
        for (String str : hashMap.keySet()) {
            httpURLConnection.addRequestProperty(str, (String) hashMap.get(str));
        }
        switch (request.m4696a()) {
            case ContentLengthStrategy.IDENTITY /*-1*/:
                byte[] j = request.m4713j();
                if (j != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty(HTTP.CONTENT_TYPE, request.m4712i());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(j);
                    dataOutputStream.close();
                    break;
                }
                break;
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                httpURLConnection.setRequestMethod("GET");
                break;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                httpURLConnection.setRequestMethod("POST");
                HurlStack.m5245a(httpURLConnection, (Request) request);
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                httpURLConnection.setRequestMethod("PUT");
                HurlStack.m5245a(httpURLConnection, (Request) request);
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                httpURLConnection.setRequestMethod("DELETE");
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        ProtocolVersion protocolVersion = new ProtocolVersion(HttpVersion.HTTP, 1, 1);
        if (httpURLConnection.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        HttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage()));
        basicHttpResponse.setEntity(HurlStack.m5244a(httpURLConnection));
        for (Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }

    private static HttpEntity m5244a(HttpURLConnection httpURLConnection) {
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

    private static void m5245a(HttpURLConnection httpURLConnection, Request<?> request) {
        if (null != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty(HTTP.CONTENT_TYPE, request.m4714k());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(null);
            dataOutputStream.close();
        }
    }
}
