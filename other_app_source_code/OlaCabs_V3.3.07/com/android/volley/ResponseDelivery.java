package com.android.volley;

/* renamed from: com.android.volley.o */
public interface ResponseDelivery {
    void m565a(Request<?> request, Response<?> response);

    void m566a(Request<?> request, Response<?> response, Runnable runnable);

    void m567a(Request<?> request, VolleyError volleyError);
}
