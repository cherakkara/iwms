package com.leanplum;

import android.content.res.Configuration;

final class ao extends aH {
    ao() {
    }

    public final Object m12739a(String str) {
        if (str.equals("long")) {
            return Integer.valueOf(32);
        }
        if (str.equals("notlong")) {
            return Integer.valueOf(16);
        }
        return null;
    }

    public final boolean m12740a(Object obj, Configuration configuration) {
        return (configuration.screenLayout & 48) == ((Integer) obj).intValue();
    }
}
