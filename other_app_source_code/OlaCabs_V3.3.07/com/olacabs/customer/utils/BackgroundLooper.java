package com.olacabs.customer.utils;

import android.os.HandlerThread;
import android.os.Looper;

/* renamed from: com.olacabs.customer.utils.a */
public class BackgroundLooper {
    private static Looper f11476a;

    public static synchronized Looper m14896a() {
        Looper looper;
        synchronized (BackgroundLooper.class) {
            if (f11476a == null) {
                HandlerThread handlerThread = new HandlerThread(BackgroundLooper.class.getName(), 10);
                handlerThread.start();
                f11476a = handlerThread.getLooper();
            }
            looper = f11476a;
        }
        return looper;
    }
}
