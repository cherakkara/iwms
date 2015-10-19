package com.leanplum;

import android.content.res.Configuration;
import android.os.Build.VERSION;

final class az extends aH {
    az() {
    }

    public final Object m12762a(String str) {
        if (str.startsWith("v")) {
            return Integer.getInteger(str.substring(1));
        }
        return null;
    }

    public final boolean m12763a(Object obj, Configuration configuration) {
        return VERSION.SDK_INT >= ((Integer) obj).intValue();
    }
}
