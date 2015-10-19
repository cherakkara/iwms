package com.google.android.m4b.maps.p040u;

import com.google.android.m4b.maps.cm.Clock;

/* renamed from: com.google.android.m4b.maps.u.b */
public abstract class BaseDataRequest implements DataRequest {
    private static Clock f2999c;
    private volatile boolean f3000a;
    private int f3001b;

    public BaseDataRequest() {
        this.f3000a = false;
        this.f3001b = 0;
        Long.valueOf(Long.MIN_VALUE);
    }

    static {
        f2999c = new Clock();
    }

    public boolean m4779a() {
        return true;
    }

    public boolean m4780b() {
        return true;
    }

    public final boolean m4781c() {
        return false;
    }

    public boolean m4782d() {
        if (this.f3001b >= 3) {
            return false;
        }
        return true;
    }

    public boolean m4783e() {
        return false;
    }

    public final void m4784f() {
        this.f3001b++;
    }

    public void m4785g() {
    }

    public void m4786h() {
    }
}
