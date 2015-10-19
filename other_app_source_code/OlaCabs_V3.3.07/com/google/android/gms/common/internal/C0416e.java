package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;

/* renamed from: com.google.android.gms.common.internal.e */
public final class C0416e {
    public static void m3566a(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("null reference");
        }
    }

    public static void m3567a(String str) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            Log.e("Asserts", "checkMainThread: current thread " + Thread.currentThread() + " IS NOT the main thread " + Looper.getMainLooper().getThread() + "!");
            throw new IllegalStateException(str);
        }
    }

    public static void m3568b(String str) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Log.e("Asserts", "checkNotMainThread: current thread " + Thread.currentThread() + " IS the main thread " + Looper.getMainLooper().getThread() + "!");
            throw new IllegalStateException(str);
        }
    }
}
