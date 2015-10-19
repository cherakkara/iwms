package com.google.android.m4b.maps.be;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

/* compiled from: AccessibilityHelper */
public final class ab {
    public static void m8296a(View view, String str) {
        if (VERSION.SDK_INT >= 16) {
            view.announceForAccessibility(str);
        } else if (VERSION.SDK_INT >= 14) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(8);
            obtain.getText().add(str);
            obtain.setEnabled(view.isEnabled());
            obtain.setClassName(view.getClass().getName());
            obtain.setPackageName(view.getContext().getPackageName());
            view.getParent().requestSendAccessibilityEvent(view, obtain);
        }
    }

    public static boolean m8297a(Context context) {
        if (context.getSystemService("accessibility") == null) {
            return false;
        }
        return ((AccessibilityManager) context.getSystemService("accessibility")).isEnabled();
    }
}
