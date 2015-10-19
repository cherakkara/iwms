package com.google.android.m4b.maps.au;

import com.google.android.m4b.maps.p040u.ExceptionReporter;

/* renamed from: com.google.android.m4b.maps.au.b */
public abstract class DriveAboutThread extends Thread {
    public abstract void m6170f();

    public DriveAboutThread(String str) {
        super(str);
    }

    public final void run() {
        try {
            m6170f();
        } catch (Throwable e) {
            DriveAboutThread.m6169a(getName(), e);
            throw e;
        } catch (Throwable e2) {
            DriveAboutThread.m6169a(getName(), e2);
            throw e2;
        }
    }

    private static void m6169a(String str, Throwable th) {
        ExceptionReporter.m11467a("DA:CRASH:" + str, th);
    }
}
