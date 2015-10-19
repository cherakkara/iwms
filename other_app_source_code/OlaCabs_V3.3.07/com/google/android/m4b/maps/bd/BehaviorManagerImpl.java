package com.google.android.m4b.maps.bd;

import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.bd.d */
public final class BehaviorManagerImpl implements BehaviorManager {
    private static boolean f5468c;
    private int f5469a;
    private int f5470b;

    final void m8220a() {
        /* JADX: method processing error */
/*
        Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r8 = this;
        r3 = 0;
        r7 = 0;
        monitor-enter(r7);
        r0 = r8.f5469a;
        r8.f5470b = r0;
        r0 = r8.f5469a;
        r0 = r0 + 1;
        r0 = r0 % 2;
        r8.f5469a = r0;
        r0 = r8.f5470b;
        r0 = 0;
        r0.clear();
        r0 = 0;
        monitor-exit(r0);
        r0 = r8.f5469a;
        r4 = com.google.p025a.p028c.ar.m2525b(r7);
        r1 = r7.iterator();
    L_0x0021:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0038;
    L_0x0027:
        r0 = r1.next();
        r0 = (com.google.android.m4b.maps.bd.Behavior) r0;
        r2 = java.lang.Integer.valueOf(r3);
        r7.put(r0, r2);
        goto L_0x0021;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x0038:
        r0 = r4.isEmpty();
        if (r0 != 0) goto L_0x0090;
    L_0x003e:
        r0 = r4.pop();
        r1 = r0;
        r1 = (com.google.android.m4b.maps.bd.Behavior) r1;
        r0 = r7.get(r1);
        r0 = (java.lang.Integer) r0;
        r5 = r0.intValue();
        monitor-enter(r7);
        r0 = 0;
        r0 = r0.remove(r1);
        r0 = (java.util.Set) r0;
        r1 = 0;
        monitor-exit(r1);
        if (r0 == 0) goto L_0x0038;
    L_0x005b:
        r6 = r0.iterator();
    L_0x005f:
        r0 = r6.hasNext();
        if (r0 == 0) goto L_0x0038;
    L_0x0065:
        r0 = r6.next();
        r0 = (com.google.android.m4b.maps.bd.Behavior) r0;
        r2 = r5 + 1;
        r1 = r7.containsKey(r0);
        if (r1 == 0) goto L_0x008e;
    L_0x0073:
        r1 = r7.get(r0);
        r1 = (java.lang.Integer) r1;
        r1 = r1.intValue();
    L_0x007d:
        if (r1 >= r2) goto L_0x0080;
    L_0x007f:
        r1 = r2;
    L_0x0080:
        r1 = java.lang.Integer.valueOf(r1);
        r7.put(r0, r1);
        r4.push(r0);
        goto L_0x005f;
    L_0x008b:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x008e:
        r1 = r2;
        goto L_0x007d;
    L_0x0090:
        r0 = r7.keySet();
        r1 = r0.iterator();
    L_0x0098:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x00a8;
    L_0x009e:
        r0 = r1.next();
        r0 = (com.google.android.m4b.maps.bd.Behavior) r0;
        r7.add(r0);
        goto L_0x0098;
    L_0x00a8:
        r0 = r7.isEmpty();
        if (r0 == 0) goto L_0x00f0;
    L_0x00ae:
        r0 = r3;
    L_0x00af:
        r1 = r7.isEmpty();
        if (r1 != 0) goto L_0x0106;
    L_0x00b5:
        r1 = r0;
        r0 = r7.poll();
        r0 = (com.google.android.m4b.maps.bd.Behavior) r0;
        r7.add(r0);
        r0 = r7.peek();
        r0 = (com.google.android.m4b.maps.bd.Behavior) r0;
        if (r0 == 0) goto L_0x00d4;
    L_0x00c7:
        r0 = r7.get(r0);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        if (r0 == r1) goto L_0x00b5;
    L_0x00d3:
        r1 = r0;
    L_0x00d4:
        r0 = 1;
        f5468c = r0;
        r0 = 0;
        r2 = r0.iterator();	 Catch:{ all -> 0x00ec }
    L_0x00dc:
        r0 = r2.hasNext();	 Catch:{ all -> 0x00ec }
        if (r0 == 0) goto L_0x00ff;	 Catch:{ all -> 0x00ec }
    L_0x00e2:
        r0 = r2.next();	 Catch:{ all -> 0x00ec }
        r0 = (com.google.android.m4b.maps.bd.Behavior) r0;	 Catch:{ all -> 0x00ec }
        r0.m8218b(r8);	 Catch:{ all -> 0x00ec }
        goto L_0x00dc;
    L_0x00ec:
        r0 = move-exception;
        f5468c = r3;
        throw r0;
    L_0x00f0:
        r0 = r7.peek();
        r0 = r7.get(r0);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        goto L_0x00af;
    L_0x00ff:
        f5468c = r3;
        r7.clear();
        r0 = r1;
        goto L_0x00af;
    L_0x0106:
        r7.clear();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.bd.d.a():void");
    }

    static {
        f5468c = false;
    }

    final void m8221a(Behavior behavior) {
        f5468c = true;
        try {
            behavior.m8217a(this);
        } finally {
            f5468c = false;
        }
    }

    final void m8222b(Behavior behavior) {
        f5468c = true;
        try {
            synchronized (null) {
                int i = this.f5470b;
                Set set = null;
                set.remove(behavior);
            }
            synchronized (null) {
                Map map = null;
                map.remove(behavior);
                map = null;
                for (Behavior behavior2 : map.keySet()) {
                    Map map2 = null;
                    ((Set) map2.get(behavior2)).remove(behavior);
                }
            }
            f5468c = false;
        } catch (Throwable th) {
            f5468c = false;
        }
    }

    static void m8219b() {
        if (!f5468c) {
            throw new RuntimeException("Attempt to update live data from outside a Behavior");
        }
    }
}
