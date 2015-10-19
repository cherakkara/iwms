package com.google.android.m4b.maps.p054p;

/* renamed from: com.google.android.m4b.maps.p.a */
public final class Assert {
    static {
        Object obj = new Object();
    }

    private static void m11063a(String str, String str2) {
        if (str != null) {
            str2 = str + " :: " + str2;
        }
        try {
            throw new Throwable();
        } catch (Throwable th) {
            System.out.println("Assert common");
            th.printStackTrace();
            AssertionError assertionError = new AssertionError(str2);
        }
    }

    public static void m11064a(boolean z) {
        if (!z) {
            Assert.m11063a(null, "condition was false");
        }
    }
}
