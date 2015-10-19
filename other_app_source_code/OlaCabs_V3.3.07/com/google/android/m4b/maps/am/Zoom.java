package com.google.android.m4b.maps.am;

import com.newrelic.agent.android.analytics.AnalyticAttribute;

/* renamed from: com.google.android.m4b.maps.am.c */
public final class Zoom {
    private static int f3367b;
    private static int f3368c;
    private static final Zoom[] f3369d;
    private final int f3370a;

    static {
        int i = 1;
        f3367b = 1;
        f3368c = 22;
        f3369d = new Zoom[22];
        int i2 = AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH;
        while (i <= 22) {
            f3369d[i - 1] = new Zoom(i, i2);
            i2 *= 2;
            i++;
        }
    }

    private Zoom(int i, int i2) {
        this.f3370a = i2;
    }

    public static Zoom m5402a(int i) {
        if (22 < f3367b) {
            return null;
        }
        if (22 > f3368c) {
            i = f3368c;
        }
        return f3369d[i - 1];
    }

    public final int m5403a() {
        return this.f3370a;
    }

    public final String toString() {
        return super.toString();
    }
}
