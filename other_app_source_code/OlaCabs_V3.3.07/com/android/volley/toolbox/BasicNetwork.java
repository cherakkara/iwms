package com.android.volley.toolbox;

import com.android.volley.Cache.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.v1.Defaults;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.protocol.HttpRequestExecutor;

/* renamed from: com.android.volley.toolbox.a */
public class BasicNetwork implements Network {
    protected static final boolean f543a;
    private static int f544d;
    private static int f545e;
    protected final HttpStack f546b;
    protected final ByteArrayPool f547c;

    static {
        f543a = VolleyLog.f533b;
        f544d = HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE;
        f545e = AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH;
    }

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(f545e));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.f546b = httpStack;
        this.f547c = byteArrayPool;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.NetworkResponse m611a(com.android.volley.Request<?> r19) throws com.android.volley.VolleyError {
        /*
        r18 = this;
        r16 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r3 = 0;
        r14 = 0;
        r6 = java.util.Collections.emptyMap();
        r2 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r4 = r19.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r0 = r18;
        r0.m609a(r2, r4);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r0 = r18;
        r4 = r0.f546b;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r0 = r19;
        r15 = r4.m639a(r0, r2);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r12 = r15.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r4 = r12.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r2 = r15.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r6 = com.android.volley.toolbox.BasicNetwork.m606a(r2);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r4 != r2) goto L_0x0065;
    L_0x0036:
        r2 = r19.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        if (r2 != 0) goto L_0x004c;
    L_0x003c:
        r3 = new com.android.volley.i;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r4 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r5 = 0;
        r7 = 1;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
    L_0x004b:
        return r3;
    L_0x004c:
        r3 = r2.f472f;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r3.putAll(r6);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r7 = new com.android.volley.i;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r8 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r9 = r2.f467a;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r10 = r2.f472f;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r11 = 1;
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r12 = r2 - r16;
        r7.<init>(r8, r9, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r3 = r7;
        goto L_0x004b;
    L_0x0065:
        r2 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
        if (r4 == r2) goto L_0x006d;
    L_0x0069:
        r2 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r4 != r2) goto L_0x007a;
    L_0x006d:
        r2 = "Location";
        r2 = r6.get(r2);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r2 = (java.lang.String) r2;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r0 = r19;
        r0.setRedirectUrl(r2);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
    L_0x007a:
        r2 = r15.getEntity();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        if (r2 == 0) goto L_0x00b4;
    L_0x0080:
        r2 = r15.getEntity();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        r0 = r18;
        r11 = r0.m610a(r2);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
    L_0x008a:
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0186 }
        r8 = r2 - r16;
        r7 = r18;
        r10 = r19;
        r7.m607a(r8, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0186 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 < r2) goto L_0x009f;
    L_0x009b:
        r2 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r4 <= r2) goto L_0x00b8;
    L_0x009f:
        r2 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0186 }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0186 }
        throw r2;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0186 }
    L_0x00a5:
        r2 = move-exception;
        r2 = "socket";
        r3 = new com.android.volley.r;
        r3.<init>();
        r0 = r19;
        com.android.volley.toolbox.BasicNetwork.m608a(r2, r0, r3);
        goto L_0x0004;
    L_0x00b4:
        r2 = 0;
        r11 = new byte[r2];	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0181 }
        goto L_0x008a;
    L_0x00b8:
        r3 = new com.android.volley.i;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0186 }
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0186 }
        r8 = r8 - r16;
        r5 = r11;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0186 }
        goto L_0x004b;
    L_0x00c6:
        r2 = move-exception;
        r2 = "connection";
        r3 = new com.android.volley.r;
        r3.<init>();
        r0 = r19;
        com.android.volley.toolbox.BasicNetwork.m608a(r2, r0, r3);
        goto L_0x0004;
    L_0x00d5:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Bad URL ";
        r4 = r4.append(r5);
        r5 = r19.getUrl();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3.<init>(r4, r2);
        throw r3;
    L_0x00f3:
        r2 = move-exception;
        r5 = r14;
    L_0x00f5:
        r7 = 0;
        if (r3 == 0) goto L_0x0142;
    L_0x00f8:
        r2 = r3.getStatusLine();
        r4 = r2.getStatusCode();
        r2 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
        if (r4 == r2) goto L_0x0108;
    L_0x0104:
        r2 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r4 != r2) goto L_0x0148;
    L_0x0108:
        r2 = "Request at %s has been redirected to %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r8 = 0;
        r9 = r19.getOriginUrl();
        r3[r8] = r9;
        r8 = 1;
        r9 = r19.getUrl();
        r3[r8] = r9;
        com.android.volley.VolleyLog.m595c(r2, r3);
    L_0x011e:
        if (r5 == 0) goto L_0x017b;
    L_0x0120:
        r3 = new com.android.volley.i;
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);
        r2 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r4 == r2) goto L_0x0134;
    L_0x0130:
        r2 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r4 != r2) goto L_0x015f;
    L_0x0134:
        r2 = "auth";
        r4 = new com.android.volley.a;
        r4.<init>(r3);
        r0 = r19;
        com.android.volley.toolbox.BasicNetwork.m608a(r2, r0, r4);
        goto L_0x0004;
    L_0x0142:
        r3 = new com.android.volley.j;
        r3.<init>(r2);
        throw r3;
    L_0x0148:
        r2 = "Unexpected response code %d for %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r8 = 0;
        r9 = java.lang.Integer.valueOf(r4);
        r3[r8] = r9;
        r8 = 1;
        r9 = r19.getUrl();
        r3[r8] = r9;
        com.android.volley.VolleyLog.m595c(r2, r3);
        goto L_0x011e;
    L_0x015f:
        r2 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
        if (r4 == r2) goto L_0x0167;
    L_0x0163:
        r2 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r4 != r2) goto L_0x0175;
    L_0x0167:
        r2 = "redirect";
        r4 = new com.android.volley.a;
        r4.<init>(r3);
        r0 = r19;
        com.android.volley.toolbox.BasicNetwork.m608a(r2, r0, r4);
        goto L_0x0004;
    L_0x0175:
        r2 = new com.android.volley.q;
        r2.<init>(r3);
        throw r2;
    L_0x017b:
        r2 = new com.android.volley.h;
        r2.<init>(r7);
        throw r2;
    L_0x0181:
        r2 = move-exception;
        r5 = r14;
        r3 = r15;
        goto L_0x00f5;
    L_0x0186:
        r2 = move-exception;
        r5 = r11;
        r3 = r15;
        goto L_0x00f5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.a.a(com.android.volley.l):com.android.volley.i");
    }

    private void m607a(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (f543a || j > ((long) f544d)) {
            String str = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.m594b(str, objArr);
        }
    }

    private static void m608a(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
            throw e;
        }
    }

    private void m609a(Map<String, String> map, Cache cache) {
        if (cache != null) {
            if (cache.f468b != null) {
                map.put(HttpHeaders.IF_NONE_MATCH, cache.f468b);
            }
            if (cache.f469c > 0) {
                map.put(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.formatDate(new Date(cache.f469c)));
            }
        }
    }

    private byte[] m610a(HttpEntity httpEntity) throws IOException, ServerError {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.f547c, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new ServerError();
            }
            bArr = this.f547c.m615a((int) Defaults.RESPONSE_BODY_LIMIT);
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                poolingByteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] toByteArray = poolingByteArrayOutputStream.toByteArray();
            return toByteArray;
        } finally {
            try {
                httpEntity.consumeContent();
            } catch (IOException e) {
                VolleyLog.m592a("Error occured when calling consumingContent", new Object[0]);
            }
            this.f547c.m614a(bArr);
            poolingByteArrayOutputStream.close();
        }
    }

    protected static Map<String, String> m606a(Header[] headerArr) {
        Map<String, String> treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }
}
