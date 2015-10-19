package com.google.android.gms.internal;

import android.support.v4.util.SimpleArrayMap;
import com.olacabs.customer.utils.Constants;

/* renamed from: com.google.android.gms.internal.r */
public class C0513r {
    private final long f2436a;
    private final int f2437b;
    private final SimpleArrayMap<Long, Long> f2438c;

    public C0513r() {
        this.f2436a = Constants.MILLIS_IN_A_MINUTE;
        this.f2437b = 10;
        this.f2438c = new SimpleArrayMap(10);
    }

    public C0513r(int i, long j) {
        this.f2436a = j;
        this.f2437b = i;
        this.f2438c = new SimpleArrayMap();
    }

    private void m4153a(long j, long j2) {
        for (int size = this.f2438c.size() - 1; size >= 0; size--) {
            if (j2 - ((Long) this.f2438c.valueAt(size)).longValue() > j) {
                this.f2438c.removeAt(size);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Long m4154a(java.lang.Long r8) {
        /*
        r7 = this;
        r2 = android.os.SystemClock.elapsedRealtime();
        r0 = r7.f2436a;
        monitor-enter(r7);
    L_0x0007:
        r4 = r7.f2438c;	 Catch:{ all -> 0x003c }
        r4 = r4.size();	 Catch:{ all -> 0x003c }
        r5 = r7.f2437b;	 Catch:{ all -> 0x003c }
        if (r4 < r5) goto L_0x003f;
    L_0x0011:
        r7.m4153a(r0, r2);	 Catch:{ all -> 0x003c }
        r4 = 2;
        r0 = r0 / r4;
        r4 = "PassiveTimedConnectionMap";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003c }
        r5.<init>();	 Catch:{ all -> 0x003c }
        r6 = "The max capacity ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x003c }
        r6 = r7.f2437b;	 Catch:{ all -> 0x003c }
        r5 = r5.append(r6);	 Catch:{ all -> 0x003c }
        r6 = " is not enough. Current durationThreshold is: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x003c }
        r5 = r5.append(r0);	 Catch:{ all -> 0x003c }
        r5 = r5.toString();	 Catch:{ all -> 0x003c }
        android.util.Log.w(r4, r5);	 Catch:{ all -> 0x003c }
        goto L_0x0007;
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x003c }
        throw r0;
    L_0x003f:
        r0 = r7.f2438c;	 Catch:{ all -> 0x003c }
        r1 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x003c }
        r0 = r0.put(r8, r1);	 Catch:{ all -> 0x003c }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x003c }
        monitor-exit(r7);	 Catch:{ all -> 0x003c }
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.r.a(java.lang.Long):java.lang.Long");
    }

    public boolean m4155a(long j) {
        boolean z;
        synchronized (this) {
            z = this.f2438c.remove(Long.valueOf(j)) != null;
        }
        return z;
    }
}
