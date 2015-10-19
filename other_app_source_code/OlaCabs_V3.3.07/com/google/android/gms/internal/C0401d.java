package com.google.android.gms.internal;

import java.util.LinkedHashMap;

/* renamed from: com.google.android.gms.internal.d */
public class C0401d<K, V> {
    private final LinkedHashMap<K, V> f2194a;
    private int f2195b;
    private int f2196c;
    private int f2197d;
    private int f2198e;
    private int f2199f;
    private int f2200g;
    private int f2201h;

    private int m3517c(K k, V v) {
        int a = m3518a(k, v);
        if (a >= 0) {
            return a;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    protected int m3518a(K k, V v) {
        return 1;
    }

    public final V m3519a(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v = this.f2194a.get(k);
            if (v != null) {
                this.f2200g++;
                return v;
            }
            this.f2201h++;
            V b = m3523b(k);
            if (b == null) {
                return null;
            }
            synchronized (this) {
                this.f2198e++;
                v = this.f2194a.put(k, b);
                if (v != null) {
                    this.f2194a.put(k, v);
                } else {
                    this.f2195b += m3517c(k, b);
                }
            }
            if (v != null) {
                m3522a(false, k, b, v);
                return v;
            }
            m3521a(this.f2196c);
            return b;
        }
    }

    public final void m3520a() {
        m3521a(-1);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m3521a(int r5) {
        /*
        r4 = this;
    L_0x0000:
        monitor-enter(r4);
        r0 = r4.f2195b;	 Catch:{ all -> 0x0032 }
        if (r0 < 0) goto L_0x0011;
    L_0x0005:
        r0 = r4.f2194a;	 Catch:{ all -> 0x0032 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0035;
    L_0x000d:
        r0 = r4.f2195b;	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0035;
    L_0x0011:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0032 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0032 }
        r1.<init>();	 Catch:{ all -> 0x0032 }
        r2 = r4.getClass();	 Catch:{ all -> 0x0032 }
        r2 = r2.getName();	 Catch:{ all -> 0x0032 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0032 }
        r2 = ".sizeOf() is reporting inconsistent results!";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0032 }
        r1 = r1.toString();	 Catch:{ all -> 0x0032 }
        r0.<init>(r1);	 Catch:{ all -> 0x0032 }
        throw r0;	 Catch:{ all -> 0x0032 }
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        throw r0;
    L_0x0035:
        r0 = r4.f2195b;	 Catch:{ all -> 0x0032 }
        if (r0 <= r5) goto L_0x0041;
    L_0x0039:
        r0 = r4.f2194a;	 Catch:{ all -> 0x0032 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0043;
    L_0x0041:
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        return;
    L_0x0043:
        r0 = r4.f2194a;	 Catch:{ all -> 0x0032 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0032 }
        r0 = r0.iterator();	 Catch:{ all -> 0x0032 }
        r0 = r0.next();	 Catch:{ all -> 0x0032 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0032 }
        r1 = r0.getKey();	 Catch:{ all -> 0x0032 }
        r0 = r0.getValue();	 Catch:{ all -> 0x0032 }
        r2 = r4.f2194a;	 Catch:{ all -> 0x0032 }
        r2.remove(r1);	 Catch:{ all -> 0x0032 }
        r2 = r4.f2195b;	 Catch:{ all -> 0x0032 }
        r3 = r4.m3517c(r1, r0);	 Catch:{ all -> 0x0032 }
        r2 = r2 - r3;
        r4.f2195b = r2;	 Catch:{ all -> 0x0032 }
        r2 = r4.f2199f;	 Catch:{ all -> 0x0032 }
        r2 = r2 + 1;
        r4.f2199f = r2;	 Catch:{ all -> 0x0032 }
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        r2 = 1;
        r3 = 0;
        r4.m3522a(r2, r1, r0, r3);
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.d.a(int):void");
    }

    protected void m3522a(boolean z, K k, V v, V v2) {
    }

    protected V m3523b(K k) {
        return null;
    }

    public final V m3524b(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        V put;
        synchronized (this) {
            this.f2197d++;
            this.f2195b += m3517c(k, v);
            put = this.f2194a.put(k, v);
            if (put != null) {
                this.f2195b -= m3517c(k, put);
            }
        }
        if (put != null) {
            m3522a(false, k, put, v);
        }
        m3521a(this.f2196c);
        return put;
    }

    public final synchronized String toString() {
        String format;
        int i = 0;
        synchronized (this) {
            int i2 = this.f2200g + this.f2201h;
            if (i2 != 0) {
                i = (this.f2200g * 100) / i2;
            }
            format = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.f2196c), Integer.valueOf(this.f2200g), Integer.valueOf(this.f2201h), Integer.valueOf(i)});
        }
        return format;
    }
}
