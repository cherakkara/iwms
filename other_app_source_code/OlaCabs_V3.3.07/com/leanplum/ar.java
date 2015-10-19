package com.leanplum;

import android.content.res.Configuration;

final class ar extends aH {
    ar() {
    }

    public final Object m12745a(String str) {
        if (str.equals("night")) {
            return Integer.valueOf(32);
        }
        if (str.equals("notnight")) {
            return Integer.valueOf(16);
        }
        return null;
    }

    public final boolean m12746a(Object obj, Configuration configuration) {
        return (configuration.uiMode & 48) == ((Integer) obj).intValue();
    }
}
