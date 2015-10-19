package com.android.volley;

import com.android.volley.Cache.Cache;

/* renamed from: com.android.volley.n */
public class Response<T> {
    public final T f522a;
    public final Cache f523b;
    public final VolleyError f524c;
    public boolean f525d;

    /* renamed from: com.android.volley.n.a */
    public interface Response {
        void m584a(VolleyError volleyError);
    }

    /* renamed from: com.android.volley.n.b */
    public interface Response<T> {
        void m585a(T t);
    }

    public static <T> Response<T> m587a(T t, Cache cache) {
        return new Response(t, cache);
    }

    public static <T> Response<T> m586a(VolleyError volleyError) {
        return new Response(volleyError);
    }

    public boolean m588a() {
        return this.f524c == null;
    }

    private Response(T t, Cache cache) {
        this.f525d = false;
        this.f522a = t;
        this.f523b = cache;
        this.f524c = null;
    }

    private Response(VolleyError volleyError) {
        this.f525d = false;
        this.f522a = null;
        this.f523b = null;
        this.f524c = volleyError;
    }
}
