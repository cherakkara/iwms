package com.newrelic.com.google.gson.internal;

/* renamed from: com.newrelic.com.google.gson.internal.$Gson$Preconditions */
public final class C$Gson$Preconditions {
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }
}
