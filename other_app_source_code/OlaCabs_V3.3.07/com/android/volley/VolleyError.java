package com.android.volley;

/* renamed from: com.android.volley.s */
public class VolleyError extends Exception {
    public final NetworkResponse f464a;
    private long f465b;

    public VolleyError() {
        this.f464a = null;
    }

    public VolleyError(NetworkResponse networkResponse) {
        this.f464a = networkResponse;
    }

    public VolleyError(String str) {
        super(str);
        this.f464a = null;
    }

    public VolleyError(Throwable th) {
        super(th);
        this.f464a = null;
    }

    void m556a(long j) {
        this.f465b = j;
    }
}
