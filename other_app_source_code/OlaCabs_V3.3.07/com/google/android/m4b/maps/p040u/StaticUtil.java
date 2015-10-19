package com.google.android.m4b.maps.p040u;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.cm.Clock;
import java.lang.ref.WeakReference;
import java.util.Vector;

/* renamed from: com.google.android.m4b.maps.u.p */
public final class StaticUtil {
    private static Clock f7949a;
    private static final Vector f7950b;
    private static byte[] f7951c;
    private static long f7952d;

    static {
        f7949a = new Clock();
        f7950b = new Vector();
        f7952d = 0;
        StaticUtil.m11487b();
    }

    private static void m11487b() {
        if (f7951c == null) {
            try {
                f7951c = new byte[AccessibilityNodeInfoCompat.ACTION_PASTE];
            } catch (OutOfMemoryError e) {
            }
        }
    }

    public static void m11486a() {
        int size;
        f7951c = null;
        System.err.println("OutOfMemory");
        long b = f7949a.m10152b();
        if (f7952d == 0 || b - f7952d >= 10000) {
            for (size = f7950b.size() - 1; size >= 0; size--) {
                if (((WeakReference) f7950b.elementAt(size)).get() == null) {
                    f7950b.removeElementAt(size);
                }
            }
            f7952d = b;
        }
        for (size = 0; size < f7950b.size(); size++) {
            ((WeakReference) f7950b.elementAt(size)).get();
        }
        StaticUtil.m11487b();
    }
}
