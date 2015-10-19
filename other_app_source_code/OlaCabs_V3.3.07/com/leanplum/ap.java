package com.leanplum;

import android.content.res.Configuration;

final class ap extends aH {
    ap() {
    }

    public final Object m12741a(String str) {
        if (str.equals("port")) {
            return Integer.valueOf(1);
        }
        if (str.equals("land")) {
            return Integer.valueOf(2);
        }
        return null;
    }

    public final boolean m12742a(Object obj, Configuration configuration) {
        return configuration.orientation == ((Integer) obj).intValue();
    }
}
