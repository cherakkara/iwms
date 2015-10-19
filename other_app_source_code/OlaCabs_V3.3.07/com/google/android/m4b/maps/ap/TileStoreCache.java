package com.google.android.m4b.maps.ap;

import com.google.android.m4b.maps.ar.DiskTileCache;
import com.google.android.m4b.maps.ar.TileCache;
import com.google.android.m4b.maps.p058v.Util;
import java.io.File;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.ap.k */
public final class TileStoreCache {
    TileCache f3832a;
    DiskTileCache f3833b;
    private volatile boolean f3834c;
    private int f3835d;
    private final boolean f3836e;
    private Locale f3837f;
    private final String f3838g;
    private File f3839h;

    public TileStoreCache(String str, TileCache tileCache, DiskTileCache diskTileCache, boolean z, Locale locale, File file) {
        this.f3838g = str;
        this.f3832a = tileCache;
        this.f3833b = diskTileCache;
        this.f3835d = -1;
        this.f3836e = z;
        this.f3837f = locale;
        this.f3839h = file;
    }

    final void m6306a() {
        if (!(this.f3833b == null || this.f3833b.m6356a(this.f3839h))) {
            this.f3833b = null;
        }
        if (this.f3833b != null) {
            if (!this.f3837f.equals(this.f3833b.m6361d())) {
                this.f3833b.m6357a(this.f3837f);
            }
            this.f3834c = true;
        }
        synchronized (this) {
            notifyAll();
        }
    }

    public final DiskTileCache m6308b() {
        if (!(this.f3833b == null || this.f3834c)) {
            synchronized (this) {
                while (this.f3833b != null && !this.f3834c) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return null;
                    }
                }
            }
        }
        return this.f3833b;
    }

    protected final void m6309c() {
        if (this.f3832a != null) {
            this.f3832a.m6349a();
        }
        DiskTileCache b = m6308b();
        if (b != null && !b.m6349a()) {
            b.m6362e();
            Util.m11550a(this.f3838g, "Unable to clear disk cache");
            this.f3833b = null;
        }
    }

    final int m6310d() {
        DiskTileCache b = m6308b();
        if (b != null) {
            return b.m6360c();
        }
        return this.f3835d;
    }

    final boolean m6307a(int i) {
        DiskTileCache b = m6308b();
        if (!(b == null || b.m6355a(i))) {
            b = null;
        }
        this.f3835d = i;
        if (!this.f3836e) {
            return false;
        }
        if (b != null) {
            b.m6349a();
        }
        if (this.f3832a != null) {
            this.f3832a.m6349a();
        }
        return true;
    }
}
