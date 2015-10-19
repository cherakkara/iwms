package com.leanplum;

import android.content.res.Configuration;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;

final class aC extends aH {
    aC() {
    }

    public final Object m12621a(String str) {
        if (str.equals("ldrtl")) {
            return Integer.valueOf(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        }
        if (str.equals("ldltr")) {
            return Integer.valueOf(64);
        }
        return null;
    }

    public final boolean m12622a(Object obj, Configuration configuration) {
        return (configuration.screenLayout & 192) == ((Integer) obj).intValue();
    }
}
