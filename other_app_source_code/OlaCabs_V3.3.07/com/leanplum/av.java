package com.leanplum;

import android.content.res.Configuration;

final class av extends aH {
    av() {
    }

    public final Object m12754a(String str) {
        if (str.equals("nokeys")) {
            return Integer.valueOf(1);
        }
        if (str.equals("qwerty")) {
            return Integer.valueOf(2);
        }
        if (str.equals("12key")) {
            return Integer.valueOf(3);
        }
        return null;
    }

    public final boolean m12755a(Object obj, Configuration configuration) {
        return configuration.keyboard == ((Integer) obj).intValue();
    }
}
