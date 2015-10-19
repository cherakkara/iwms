package com.google.android.m4b.maps.ak;

import com.google.android.m4b.maps.p038a.Network;
import com.google.android.m4b.maps.p038a.Request;
import com.google.android.m4b.maps.p038a.RetryPolicy;
import com.google.android.m4b.maps.p038a.ServerError;
import com.google.android.m4b.maps.p038a.VolleyError;
import com.google.android.m4b.maps.p038a.VolleyLog;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.v1.Defaults;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.protocol.HttpRequestExecutor;

/* renamed from: com.google.android.m4b.maps.ak.a */
public final class BasicNetwork implements Network {
    private static boolean f3242a;
    private static int f3243b;
    private static int f3244c;
    private HttpStack f3245d;
    private ByteArrayPool f3246e;

    static {
        f3242a = VolleyLog.f2942a;
        f3243b = HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE;
        f3244c = AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH;
    }

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(f3244c));
    }

    private BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.f3245d = httpStack;
        this.f3246e = byteArrayPool;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.m4b.maps.p038a.NetworkResponse m5216a(com.google.android.m4b.maps.p038a.Request<?> r13) {
        /*
        r12 = this;
        r4 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r3 = 0;
        r2 = 0;
        r1 = new java.util.HashMap;
        r1.<init>();
        r0 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0.<init>();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r6 = r13.m4709e();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        if (r6 == 0) goto L_0x0039;
    L_0x0016:
        r7 = r6.f2871b;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        if (r7 == 0) goto L_0x0021;
    L_0x001a:
        r7 = "If-None-Match";
        r8 = r6.f2871b;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0.put(r7, r8);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
    L_0x0021:
        r8 = r6.f2872c;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r10 = 0;
        r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r7 <= 0) goto L_0x0039;
    L_0x0029:
        r7 = new java.util.Date;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r8 = r6.f2872c;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r7.<init>(r8);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r6 = "If-Modified-Since";
        r7 = org.apache.http.impl.cookie.DateUtils.formatDate(r7);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0.put(r6, r7);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
    L_0x0039:
        r6 = r12.f3245d;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r3 = r6.m5242a(r13, r0);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r6 = r3.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r7 = r6.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0 = r3.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r1 = com.google.android.m4b.maps.ak.BasicNetwork.m5213a(r0);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r7 != r0) goto L_0x0062;
    L_0x0053:
        r0 = new com.google.android.m4b.maps.a.h;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r6 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r7 = r13.m4709e();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r7 = r7.f2870a;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r8 = 1;
        r0.<init>(r6, r7, r1, r8);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
    L_0x0061:
        return r0;
    L_0x0062:
        r0 = r3.getEntity();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        if (r0 == 0) goto L_0x00d1;
    L_0x0068:
        r0 = r3.getEntity();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r2 = r12.m5215a(r0);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
    L_0x0070:
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r8 = r8 - r4;
        r0 = f3242a;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        if (r0 != 0) goto L_0x0080;
    L_0x0079:
        r0 = f3243b;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r10 = (long) r0;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r0 <= 0) goto L_0x00b6;
    L_0x0080:
        r10 = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
        r0 = 5;
        r11 = new java.lang.Object[r0];	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0 = 0;
        r11[r0] = r13;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0 = 1;
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r11[r0] = r8;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r8 = 2;
        if (r2 == 0) goto L_0x00d5;
    L_0x0092:
        r0 = r2.length;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
    L_0x0097:
        r11[r8] = r0;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0 = 3;
        r6 = r6.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r11[r0] = r6;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0 = 4;
        r6 = r13.m4718o();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r6 = r6.m4731b();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r11[r0] = r6;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        com.google.android.m4b.maps.p038a.VolleyLog.m4737b(r10, r11);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
    L_0x00b6:
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r7 == r0) goto L_0x00d8;
    L_0x00ba:
        r0 = 204; // 0xcc float:2.86E-43 double:1.01E-321;
        if (r7 == r0) goto L_0x00d8;
    L_0x00be:
        r0 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r0.<init>();	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
    L_0x00c4:
        r0 = move-exception;
        r0 = "socket";
        r1 = new com.google.android.m4b.maps.a.q;
        r1.<init>();
        com.google.android.m4b.maps.ak.BasicNetwork.m5214a(r0, r13, r1);
        goto L_0x0004;
    L_0x00d1:
        r0 = 0;
        r2 = new byte[r0];	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        goto L_0x0070;
    L_0x00d5:
        r0 = "null";
        goto L_0x0097;
    L_0x00d8:
        r0 = new com.google.android.m4b.maps.a.h;	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        r6 = 0;
        r0.<init>(r7, r2, r1, r6);	 Catch:{ SocketTimeoutException -> 0x00c4, ConnectTimeoutException -> 0x00df, MalformedURLException -> 0x00ec, IOException -> 0x0106 }
        goto L_0x0061;
    L_0x00df:
        r0 = move-exception;
        r0 = "connection";
        r1 = new com.google.android.m4b.maps.a.q;
        r1.<init>();
        com.google.android.m4b.maps.ak.BasicNetwork.m5214a(r0, r13, r1);
        goto L_0x0004;
    L_0x00ec:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r2 = new java.lang.StringBuilder;
        r3 = "Bad URL ";
        r2.<init>(r3);
        r3 = r13.m4707c();
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
    L_0x0106:
        r0 = move-exception;
        if (r3 == 0) goto L_0x0143;
    L_0x0109:
        r0 = r3.getStatusLine();
        r0 = r0.getStatusCode();
        r3 = "Unexpected response code %d for %s";
        r6 = 2;
        r6 = new java.lang.Object[r6];
        r7 = 0;
        r8 = java.lang.Integer.valueOf(r0);
        r6[r7] = r8;
        r7 = 1;
        r8 = r13.m4707c();
        r6[r7] = r8;
        com.google.android.m4b.maps.p038a.VolleyLog.m4738c(r3, r6);
        if (r2 == 0) goto L_0x014f;
    L_0x0129:
        r3 = new com.google.android.m4b.maps.a.h;
        r6 = 0;
        r3.<init>(r0, r2, r1, r6);
        r1 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r0 == r1) goto L_0x0137;
    L_0x0133:
        r1 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r0 != r1) goto L_0x0149;
    L_0x0137:
        r0 = "auth";
        r1 = new com.google.android.m4b.maps.a.a;
        r1.<init>(r3);
        com.google.android.m4b.maps.ak.BasicNetwork.m5214a(r0, r13, r1);
        goto L_0x0004;
    L_0x0143:
        r1 = new com.google.android.m4b.maps.a.i;
        r1.<init>(r0);
        throw r1;
    L_0x0149:
        r0 = new com.google.android.m4b.maps.a.p;
        r0.<init>(r3);
        throw r0;
    L_0x014f:
        r0 = new com.google.android.m4b.maps.a.g;
        r1 = 0;
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ak.a.a(com.google.android.m4b.maps.a.k):com.google.android.m4b.maps.a.h");
    }

    private static void m5214a(String str, Request<?> request, VolleyError volleyError) {
        RetryPolicy o = request.m4718o();
        int n = request.m4717n();
        try {
            o.m4730a(volleyError);
            request.m4703a(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(n)}));
        } catch (VolleyError e) {
            request.m4703a(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(n)}));
            throw e;
        }
    }

    private byte[] m5215a(HttpEntity httpEntity) {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.f3246e, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new ServerError();
            }
            bArr = this.f3246e.m5219a((int) Defaults.RESPONSE_BODY_LIMIT);
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
                VolleyLog.m4735a("Error occured when calling consumingContent", new Object[0]);
            }
            this.f3246e.m5218a(bArr);
            poolingByteArrayOutputStream.close();
        }
    }

    private static Map<String, String> m5213a(Header[] headerArr) {
        Map<String, String> hashMap = new HashMap();
        for (int i = 0; i < headerArr.length; i++) {
            hashMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return hashMap;
    }
}
