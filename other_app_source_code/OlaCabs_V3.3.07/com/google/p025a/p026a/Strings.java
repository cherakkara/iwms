package com.google.p025a.p026a;

import com.newrelic.agent.android.instrumentation.Trace;
import javax.annotation.Nullable;

/* renamed from: com.google.a.a.n */
public final class Strings {
    public static String m1865a(@Nullable String str) {
        return str == null ? Trace.NULL : str;
    }

    public static boolean m1866b(@Nullable String str) {
        return str == null || str.length() == 0;
    }
}
