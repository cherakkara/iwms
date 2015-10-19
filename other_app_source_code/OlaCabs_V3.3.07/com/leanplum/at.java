package com.leanplum;

import android.content.res.Configuration;

final class at extends aH {
    at() {
    }

    public final Object m12750a(String str) {
        if (str.equals("notouch")) {
            return Integer.valueOf(1);
        }
        if (str.equals("finger")) {
            return Integer.valueOf(3);
        }
        return null;
    }

    public final boolean m12751a(Object obj, Configuration configuration) {
        return configuration.touchscreen == ((Integer) obj).intValue();
    }
}
