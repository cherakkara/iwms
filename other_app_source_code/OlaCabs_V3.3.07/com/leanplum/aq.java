package com.leanplum;

import android.content.res.Configuration;

final class aq extends aH {
    aq() {
    }

    public final Object m12743a(String str) {
        if (str.equals("car")) {
            return Integer.valueOf(3);
        }
        if (str.equals("desk")) {
            return Integer.valueOf(2);
        }
        if (str.equals("television")) {
            return Integer.valueOf(4);
        }
        if (str.equals("appliance")) {
            return Integer.valueOf(5);
        }
        return null;
    }

    public final boolean m12744a(Object obj, Configuration configuration) {
        return (configuration.uiMode & 15) == ((Integer) obj).intValue();
    }
}
