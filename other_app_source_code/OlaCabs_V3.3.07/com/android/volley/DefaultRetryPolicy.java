package com.android.volley;

import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.android.volley.d */
public class DefaultRetryPolicy implements RetryPolicy {
    private int f481a;
    private int f482b;
    private final int f483c;
    private final float f484d;

    public DefaultRetryPolicy() {
        this(dm.DEFAULT_TIMEOUT_MS, 1, br.DEFAULT_BACKOFF_MULT);
    }

    public DefaultRetryPolicy(int i, int i2, float f) {
        this.f481a = i;
        this.f483c = i2;
        this.f484d = f;
    }

    public int getCurrentTimeout() {
        return this.f481a;
    }

    public int getCurrentRetryCount() {
        return this.f482b;
    }

    public void retry(VolleyError volleyError) throws VolleyError {
        this.f482b++;
        this.f481a = (int) (((float) this.f481a) + (((float) this.f481a) * this.f484d));
        if (!m564a()) {
            throw volleyError;
        }
    }

    protected boolean m564a() {
        return this.f482b <= this.f483c;
    }
}
