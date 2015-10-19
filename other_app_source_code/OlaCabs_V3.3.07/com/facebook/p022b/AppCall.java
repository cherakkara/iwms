package com.facebook.p022b;

import android.content.Intent;
import java.util.UUID;

/* renamed from: com.facebook.b.a */
public class AppCall {
    private static AppCall f727a;
    private UUID f728b;
    private Intent f729c;
    private int f730d;

    public static AppCall m875a() {
        return f727a;
    }

    private static synchronized boolean m876a(AppCall appCall) {
        boolean z;
        synchronized (AppCall.class) {
            AppCall a = AppCall.m875a();
            f727a = appCall;
            z = a != null;
        }
        return z;
    }

    public AppCall(int i) {
        this(i, UUID.randomUUID());
    }

    public AppCall(int i, UUID uuid) {
        this.f728b = uuid;
        this.f730d = i;
    }

    public Intent m878b() {
        return this.f729c;
    }

    public UUID m879c() {
        return this.f728b;
    }

    public int m880d() {
        return this.f730d;
    }

    public void m877a(Intent intent) {
        this.f729c = intent;
    }

    public boolean m881e() {
        return AppCall.m876a(this);
    }
}
