package com.google.android.m4b.maps.p040u;

import com.google.android.m4b.maps.p050j.ThreadFactory;

/* renamed from: com.google.android.m4b.maps.u.a */
public final class BackgroundThreadFactory implements ThreadFactory {
    private final ThreadFactory f7859a;

    public BackgroundThreadFactory(ThreadFactory threadFactory) {
        this.f7859a = threadFactory;
    }

    public final Thread m11312a(String str, Runnable runnable) {
        Thread a = this.f7859a.m10534a(str, runnable);
        a.setPriority(1);
        return a;
    }
}
