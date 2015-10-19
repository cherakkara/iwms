package com.google.gson.b;

/* renamed from: com.google.gson.b.a */
public final class C$Gson$Preconditions {
    public static <T> T m12214a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static void m12215a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }
}
