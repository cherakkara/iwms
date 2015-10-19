package com.leanplum;

import android.content.res.Configuration;

final class aw extends aH {
    aw() {
    }

    public final Object m12756a(String str) {
        if (str.equals("navexposed")) {
            return Integer.valueOf(1);
        }
        if (str.equals("navhidden")) {
            return Integer.valueOf(2);
        }
        return null;
    }

    public final boolean m12757a(Object obj, Configuration configuration) {
        return configuration.navigationHidden == ((Integer) obj).intValue();
    }
}
