package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ba.GLLabel;
import com.google.android.m4b.maps.bc.Labeler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.av.c */
public final class LabelOverlay extends BubbleBlowerOverlay {
    private final ArrayList<GLLabel> f4498b;
    private Labeler f4499c;
    private final int f4500d;

    public LabelOverlay(int i, ai aiVar) {
        super(aiVar);
        this.f4500d = i;
        this.f4498b = new ArrayList();
    }

    public final void m7148a(Labeler labeler) {
        this.f4499c = labeler;
    }

    public final GLOverlay m7151d() {
        return this.f4500d == 1 ? GLOverlay.IMPORTANT_LABELS : GLOverlay.LABELS;
    }

    public final boolean m7150a(Camera camera, GLState gLState) {
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m7147a(com.google.android.m4b.maps.ay.GLState r6, com.google.android.m4b.maps.ax.Camera r7, com.google.android.m4b.maps.av.ad r8) {
        /*
        r5 = this;
        r4 = 1;
        r0 = r5.f4499c;
        if (r0 == 0) goto L_0x001b;
    L_0x0005:
        r0 = r8.m6704b();
        if (r0 > 0) goto L_0x001b;
    L_0x000b:
        r0 = r8.m6701a();
        r1 = com.google.android.m4b.maps.av.ac.NONE;
        if (r0 == r1) goto L_0x001b;
    L_0x0013:
        r0 = r8.m6701a();
        r1 = com.google.android.m4b.maps.av.ac.RASTER_ONLY;
        if (r0 != r1) goto L_0x001c;
    L_0x001b:
        return;
    L_0x001c:
        r6.m7533p();
        r0 = r6.m7541x();
        r1 = 771; // 0x303 float:1.08E-42 double:3.81E-321;
        r0.glBlendFunc(r4, r1);
        r0 = r6.m7541x();
        r1 = 8960; // 0x2300 float:1.2556E-41 double:4.427E-320;
        r2 = 8704; // 0x2200 float:1.2197E-41 double:4.3003E-320;
        r3 = 8448; // 0x2100 float:1.1838E-41 double:4.174E-320;
        r0.glTexEnvx(r1, r2, r3);
        monitor-enter(r5);
        r0 = r5.f4498b;	 Catch:{ all -> 0x0066 }
        r0.clear();	 Catch:{ all -> 0x0066 }
        r0 = r5.f4499c;	 Catch:{ all -> 0x0066 }
        r0 = r0.m8215d();	 Catch:{ all -> 0x0066 }
    L_0x0041:
        r1 = r0.hasNext();	 Catch:{ all -> 0x0066 }
        if (r1 == 0) goto L_0x0069;
    L_0x0047:
        r1 = r0.m8190a();	 Catch:{ all -> 0x0066 }
        r2 = r5.f4500d;	 Catch:{ all -> 0x0066 }
        if (r2 != r4) goto L_0x0055;
    L_0x004f:
        r2 = r1.m7918v();	 Catch:{ all -> 0x0066 }
        if (r2 == 0) goto L_0x0041;
    L_0x0055:
        r2 = r5.f4500d;	 Catch:{ all -> 0x0066 }
        r3 = 2;
        if (r2 != r3) goto L_0x0060;
    L_0x005a:
        r2 = r1.m7918v();	 Catch:{ all -> 0x0066 }
        if (r2 != 0) goto L_0x0041;
    L_0x0060:
        r2 = r5.f4498b;	 Catch:{ all -> 0x0066 }
        r2.add(r1);	 Catch:{ all -> 0x0066 }
        goto L_0x0041;
    L_0x0066:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x0069:
        r0 = 0;
        r1 = r0;
    L_0x006b:
        r0 = r5.f4498b;	 Catch:{ all -> 0x0066 }
        r0 = r0.size();	 Catch:{ all -> 0x0066 }
        if (r1 >= r0) goto L_0x0088;
    L_0x0073:
        r0 = r5.f4498b;	 Catch:{ all -> 0x0066 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x0066 }
        r0 = (com.google.android.m4b.maps.ba.GLLabel) r0;	 Catch:{ all -> 0x0066 }
        r6.m7543z();	 Catch:{ all -> 0x0066 }
        r0.m6669a(r6, r7, r8);	 Catch:{ all -> 0x0066 }
        r6.m7503A();	 Catch:{ all -> 0x0066 }
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x006b;
    L_0x0088:
        monitor-exit(r5);	 Catch:{ all -> 0x0066 }
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.c.a(com.google.android.m4b.maps.ay.e, com.google.android.m4b.maps.ax.b, com.google.android.m4b.maps.av.ad):void");
    }

    public final synchronized void m7149a(List<af> list, float f, float f2, Point point, Camera camera, int i) {
        Iterator it = this.f4498b.iterator();
        while (it.hasNext()) {
            GLLabel gLLabel = (GLLabel) it.next();
            if (gLLabel instanceof BubbleBlower) {
                BubbleBlower bubbleBlower = (BubbleBlower) gLLabel;
                int a = bubbleBlower.m7159a(f, f2, camera);
                if (a < i) {
                    list.add(new af(bubbleBlower, this, a));
                }
            }
        }
    }
}
