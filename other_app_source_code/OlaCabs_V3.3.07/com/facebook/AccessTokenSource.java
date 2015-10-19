package com.facebook;

/* renamed from: com.facebook.c */
public enum AccessTokenSource {
    NONE(false),
    FACEBOOK_APPLICATION_WEB(true),
    FACEBOOK_APPLICATION_NATIVE(true),
    FACEBOOK_APPLICATION_SERVICE(true),
    WEB_VIEW(false),
    TEST_USER(true),
    CLIENT_TOKEN(true);
    
    private final boolean f897h;

    private AccessTokenSource(boolean z) {
        this.f897h = z;
    }

    boolean m1180a() {
        return this.f897h;
    }
}
