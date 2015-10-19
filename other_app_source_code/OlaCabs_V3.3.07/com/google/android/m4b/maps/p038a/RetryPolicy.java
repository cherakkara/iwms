package com.google.android.m4b.maps.p038a;

import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.a.o */
public class RetryPolicy {
    private int f2932a;
    private int f2933b;
    private final int f2934c;
    private final float f2935d;

    public RetryPolicy() {
        this(dm.DEFAULT_TIMEOUT_MS, 1, br.DEFAULT_BACKOFF_MULT);
    }

    public RetryPolicy(int i, int i2, float f) {
        this.f2932a = i;
        this.f2934c = i2;
        this.f2935d = f;
    }

    public int m4729a() {
        return this.f2932a;
    }

    public int m4731b() {
        return this.f2933b;
    }

    public void m4730a(VolleyError volleyError) {
        this.f2933b++;
        this.f2932a = (int) (((float) this.f2932a) + (((float) this.f2932a) * this.f2935d));
        if (!m4732c()) {
            throw volleyError;
        }
    }

    protected boolean m4732c() {
        return this.f2933b <= this.f2934c;
    }
}
