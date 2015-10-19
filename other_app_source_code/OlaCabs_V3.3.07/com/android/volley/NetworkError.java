package com.android.volley;

/* renamed from: com.android.volley.h */
public class NetworkError extends VolleyError {
    public NetworkError(Throwable th) {
        super(th);
    }

    public NetworkError(NetworkResponse networkResponse) {
        super(networkResponse);
    }
}
