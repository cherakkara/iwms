package com.leanplum;

import android.content.res.Configuration;

final class an extends aH {
    an() {
    }

    public final Object m12735a(String str) {
        if (str.startsWith("mcc")) {
            return Integer.getInteger(str.substring(3));
        }
        return null;
    }

    public final boolean m12736a(Object obj, Configuration configuration) {
        return configuration.mcc == ((Integer) obj).intValue();
    }
}
