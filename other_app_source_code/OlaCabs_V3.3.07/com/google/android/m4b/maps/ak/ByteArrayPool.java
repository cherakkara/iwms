package com.google.android.m4b.maps.ak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.ak.b */
public final class ByteArrayPool {
    private static Comparator<byte[]> f3247e;
    private List<byte[]> f3248a;
    private List<byte[]> f3249b;
    private int f3250c;
    private final int f3251d;

    /* renamed from: com.google.android.m4b.maps.ak.b.1 */
    static class ByteArrayPool implements Comparator<byte[]> {
        ByteArrayPool() {
        }

        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            return ((byte[]) obj).length - ((byte[]) obj2).length;
        }
    }

    static {
        f3247e = new ByteArrayPool();
    }

    public ByteArrayPool(int i) {
        this.f3248a = new LinkedList();
        this.f3249b = new ArrayList(64);
        this.f3250c = 0;
        this.f3251d = i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized byte[] m5219a(int r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = 0;
        r1 = r0;
    L_0x0003:
        r0 = r4.f3249b;	 Catch:{ all -> 0x002f }
        r0 = r0.size();	 Catch:{ all -> 0x002f }
        if (r1 >= r0) goto L_0x002c;
    L_0x000b:
        r0 = r4.f3249b;	 Catch:{ all -> 0x002f }
        r0 = r0.get(r1);	 Catch:{ all -> 0x002f }
        r0 = (byte[]) r0;	 Catch:{ all -> 0x002f }
        r2 = r0.length;	 Catch:{ all -> 0x002f }
        if (r2 < r5) goto L_0x0028;
    L_0x0016:
        r2 = r4.f3250c;	 Catch:{ all -> 0x002f }
        r3 = r0.length;	 Catch:{ all -> 0x002f }
        r2 = r2 - r3;
        r4.f3250c = r2;	 Catch:{ all -> 0x002f }
        r2 = r4.f3249b;	 Catch:{ all -> 0x002f }
        r2.remove(r1);	 Catch:{ all -> 0x002f }
        r1 = r4.f3248a;	 Catch:{ all -> 0x002f }
        r1.remove(r0);	 Catch:{ all -> 0x002f }
    L_0x0026:
        monitor-exit(r4);
        return r0;
    L_0x0028:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0003;
    L_0x002c:
        r0 = new byte[r5];	 Catch:{ all -> 0x002f }
        goto L_0x0026;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ak.b.a(int):byte[]");
    }

    public final synchronized void m5218a(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.f3251d) {
                this.f3248a.add(bArr);
                int binarySearch = Collections.binarySearch(this.f3249b, bArr, f3247e);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.f3249b.add(binarySearch, bArr);
                this.f3250c += bArr.length;
                m5217a();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m5217a() {
        /*
        r2 = this;
        monitor-enter(r2);
    L_0x0001:
        r0 = r2.f3250c;	 Catch:{ all -> 0x001d }
        r1 = r2.f3251d;	 Catch:{ all -> 0x001d }
        if (r0 <= r1) goto L_0x0020;
    L_0x0007:
        r0 = r2.f3248a;	 Catch:{ all -> 0x001d }
        r1 = 0;
        r0 = r0.remove(r1);	 Catch:{ all -> 0x001d }
        r0 = (byte[]) r0;	 Catch:{ all -> 0x001d }
        r1 = r2.f3249b;	 Catch:{ all -> 0x001d }
        r1.remove(r0);	 Catch:{ all -> 0x001d }
        r1 = r2.f3250c;	 Catch:{ all -> 0x001d }
        r0 = r0.length;	 Catch:{ all -> 0x001d }
        r0 = r1 - r0;
        r2.f3250c = r0;	 Catch:{ all -> 0x001d }
        goto L_0x0001;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
    L_0x0020:
        monitor-exit(r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ak.b.a():void");
    }
}
