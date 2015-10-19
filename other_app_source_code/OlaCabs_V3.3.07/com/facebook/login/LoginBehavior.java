package com.facebook.login;

/* renamed from: com.facebook.login.c */
public enum LoginBehavior {
    SSO_WITH_FALLBACK(true, true),
    SSO_ONLY(true, false),
    SUPPRESS_SSO(false, true);
    
    private final boolean f1000d;
    private final boolean f1001e;

    private LoginBehavior(boolean z, boolean z2) {
        this.f1000d = z;
        this.f1001e = z2;
    }

    boolean m1312a() {
        return this.f1000d;
    }

    boolean m1313b() {
        return this.f1001e;
    }
}
