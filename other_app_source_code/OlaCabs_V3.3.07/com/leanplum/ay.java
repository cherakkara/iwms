package com.leanplum;

import android.content.res.Configuration;

final class ay extends aH {
    ay() {
    }

    public final Object m12760a(String str) {
        if (str.startsWith("mnc")) {
            return Integer.getInteger(str.substring(3));
        }
        return null;
    }

    public final boolean m12761a(Object obj, Configuration configuration) {
        return configuration.mnc == ((Integer) obj).intValue();
    }
}
