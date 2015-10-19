package com.olacabs.p067a.p068a.p069a.p074d;

import android.os.HandlerThread;
import android.os.Looper;

/* renamed from: com.olacabs.a.a.a.d.a */
public class BackgroundLooper {
    private static Looper f8917a;

    public static synchronized Looper m12832a() {
        Looper looper;
        synchronized (BackgroundLooper.class) {
            if (f8917a == null) {
                HandlerThread handlerThread = new HandlerThread(BackgroundLooper.class.getName(), 10);
                handlerThread.start();
                f8917a = handlerThread.getLooper();
            }
            looper = f8917a;
        }
        return looper;
    }
}
