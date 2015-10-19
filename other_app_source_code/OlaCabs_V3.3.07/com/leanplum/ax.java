package com.leanplum;

import android.content.res.Configuration;

final class ax extends aH {
    ax() {
    }

    public final Object m12758a(String str) {
        if (str.equals("nonav")) {
            return Integer.valueOf(1);
        }
        if (str.equals("dpad")) {
            return Integer.valueOf(2);
        }
        if (str.equals("trackball")) {
            return Integer.valueOf(3);
        }
        if (str.equals("wheel")) {
            return Integer.valueOf(4);
        }
        return null;
    }

    public final boolean m12759a(Object obj, Configuration configuration) {
        return configuration.navigation == ((Integer) obj).intValue();
    }
}
