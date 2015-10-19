package com.google.android.m4b.maps.p038a;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.m4b.maps.p038a.Cache.Cache;
import com.google.android.m4b.maps.p038a.Response.Response;
import com.google.android.m4b.maps.p038a.VolleyLog.VolleyLog;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.a.k */
public abstract class Request<T> implements Comparable<Request<T>> {
    private final VolleyLog f2905a;
    private final int f2906b;
    private final String f2907c;
    private final int f2908d;
    private final Response f2909e;
    private Integer f2910f;
    private RequestQueue f2911g;
    private boolean f2912h;
    private boolean f2913i;
    private boolean f2914j;
    private long f2915k;
    private RetryPolicy f2916l;
    private Cache f2917m;

    /* renamed from: com.google.android.m4b.maps.a.k.1 */
    class Request implements Runnable {
        private /* synthetic */ String f2897a;
        private /* synthetic */ long f2898b;
        private /* synthetic */ Request f2899c;

        Request(Request request, String str, long j) {
            this.f2899c = request;
            this.f2897a = str;
            this.f2898b = j;
        }

        public final void run() {
            this.f2899c.f2905a.m4734a(this.f2897a, this.f2898b);
            this.f2899c.f2905a.m4733a(toString());
        }
    }

    /* renamed from: com.google.android.m4b.maps.a.k.a */
    public enum Request {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    protected abstract Response<T> m4697a(NetworkResponse networkResponse);

    protected abstract void m4702a(T t);

    public /* synthetic */ int compareTo(Object obj) {
        Request request = (Request) obj;
        Request m = m4716m();
        Request m2 = request.m4716m();
        return m == m2 ? this.f2910f.intValue() - request.f2910f.intValue() : m2.ordinal() - m.ordinal();
    }

    public Request(int i, String str, Response response) {
        VolleyLog volleyLog;
        if (VolleyLog.f2939a) {
            volleyLog = new VolleyLog();
        } else {
            volleyLog = null;
        }
        this.f2905a = volleyLog;
        this.f2912h = true;
        this.f2913i = false;
        this.f2914j = false;
        this.f2915k = 0;
        this.f2917m = null;
        this.f2906b = i;
        this.f2907c = str;
        this.f2909e = response;
        this.f2916l = new RetryPolicy();
        this.f2908d = TextUtils.isEmpty(str) ? 0 : Uri.parse(str).getHost().hashCode();
    }

    public final int m4696a() {
        return this.f2906b;
    }

    public final int m4704b() {
        return this.f2908d;
    }

    public final void m4701a(RetryPolicy retryPolicy) {
        this.f2916l = retryPolicy;
    }

    public final void m4703a(String str) {
        if (VolleyLog.f2939a) {
            this.f2905a.m4734a(str, Thread.currentThread().getId());
        } else if (this.f2915k == 0) {
            this.f2915k = SystemClock.elapsedRealtime();
        }
    }

    final void m4706b(String str) {
        if (this.f2911g != null) {
            this.f2911g.m4724b(this);
        }
        if (VolleyLog.f2939a) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Request(this, str, id));
                return;
            }
            this.f2905a.m4734a(str, id);
            this.f2905a.m4733a(toString());
            return;
        }
        if (SystemClock.elapsedRealtime() - this.f2915k >= 3000) {
            VolleyLog.m4737b("%d ms: %s", Long.valueOf(SystemClock.elapsedRealtime() - this.f2915k), toString());
        }
    }

    public final void m4700a(RequestQueue requestQueue) {
        this.f2911g = requestQueue;
    }

    public final void m4698a(int i) {
        this.f2910f = Integer.valueOf(i);
    }

    public final String m4707c() {
        return this.f2907c;
    }

    public final String m4708d() {
        return this.f2907c;
    }

    public final void m4699a(Cache cache) {
        this.f2917m = cache;
    }

    public final Cache m4709e() {
        return this.f2917m;
    }

    public final void m4710f() {
        this.f2913i = true;
    }

    public final boolean m4711g() {
        return this.f2913i;
    }

    public static Map<String, String> m4695h() {
        return Collections.emptyMap();
    }

    public final String m4712i() {
        return m4714k();
    }

    public final byte[] m4713j() {
        byte[] bArr = null;
        if (bArr == null || bArr.size() <= 0) {
            return bArr;
        }
        return Request.m4694a(bArr, HTTP.UTF_8);
    }

    public final String m4714k() {
        return "application/x-www-form-urlencoded; charset=" + HTTP.UTF_8;
    }

    private static byte[] m4694a(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (Throwable e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    public final boolean m4715l() {
        return this.f2912h;
    }

    public Request m4716m() {
        return Request.NORMAL;
    }

    public final int m4717n() {
        return this.f2916l.m4729a();
    }

    public final RetryPolicy m4718o() {
        return this.f2916l;
    }

    public final void m4719p() {
        this.f2914j = true;
    }

    public final boolean m4720q() {
        return this.f2914j;
    }

    protected static VolleyError m4692a(VolleyError volleyError) {
        return volleyError;
    }

    public final void m4705b(VolleyError volleyError) {
        if (this.f2909e != null) {
            this.f2909e.m4725a(volleyError);
        }
    }

    public String toString() {
        return (this.f2913i ? "[X] " : "[ ] ") + this.f2907c + " " + ("0x" + Integer.toHexString(this.f2908d)) + " " + m4716m() + " " + this.f2910f;
    }
}
