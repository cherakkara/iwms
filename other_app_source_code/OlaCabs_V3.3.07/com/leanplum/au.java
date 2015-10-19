package com.leanplum;

import android.content.res.Configuration;

final class au extends aH {
    au() {
    }

    public final Object m12752a(String str) {
        if (str.equals("keysexposed")) {
            return Integer.valueOf(1);
        }
        if (str.equals("keyshidden")) {
            return Integer.valueOf(2);
        }
        if (str.equals("keyssoft")) {
            return Integer.valueOf(0);
        }
        return null;
    }

    public final boolean m12753a(Object obj, Configuration configuration) {
        if (((Integer) obj).intValue() == 0) {
            return true;
        }
        return configuration.keyboardHidden == ((Integer) obj).intValue();
    }
}
