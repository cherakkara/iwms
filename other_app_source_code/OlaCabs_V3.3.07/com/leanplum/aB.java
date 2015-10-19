package com.leanplum;

import android.content.res.Configuration;

final class aB extends aH {
    aB() {
    }

    public final Object m12619a(String str) {
        if (str.startsWith("r") && str.length() == 3) {
            return str.substring(1);
        }
        return null;
    }

    public final boolean m12620a(Object obj, Configuration configuration) {
        return configuration.locale.getCountry().toLowerCase().equals(obj);
    }
}
