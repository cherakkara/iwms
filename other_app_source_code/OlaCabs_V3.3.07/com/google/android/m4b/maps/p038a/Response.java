package com.google.android.m4b.maps.p038a;

import com.google.android.m4b.maps.p038a.Cache.Cache;

/* renamed from: com.google.android.m4b.maps.a.m */
public final class Response<T> {
    public final T f2928a;
    public final Cache f2929b;
    public final VolleyError f2930c;
    public boolean f2931d;

    /* renamed from: com.google.android.m4b.maps.a.m.a */
    public interface Response {
        void m4725a(VolleyError volleyError);
    }

    /* renamed from: com.google.android.m4b.maps.a.m.b */
    public interface Response<T> {
        void m4726a(T t);
    }

    public static <T> Response<T> m4728a(T t, Cache cache) {
        return new Response(t, cache);
    }

    public static <T> Response<T> m4727a(VolleyError volleyError) {
        return new Response(volleyError);
    }

    private Response(T t, Cache cache) {
        this.f2931d = false;
        this.f2928a = t;
        this.f2929b = cache;
        this.f2930c = null;
    }

    private Response(VolleyError volleyError) {
        this.f2931d = false;
        this.f2928a = null;
        this.f2929b = null;
        this.f2930c = volleyError;
    }
}
