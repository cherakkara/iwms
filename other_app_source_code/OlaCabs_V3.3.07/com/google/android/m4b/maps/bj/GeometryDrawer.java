package com.google.android.m4b.maps.bj;

import com.google.android.m4b.maps.be.ar;
import com.google.android.m4b.maps.bj.LevelInfo.LevelInfo;
import com.google.android.m4b.maps.bj.ad.PanoramaManager;
import com.google.android.m4b.maps.bj.ag.Renderer;
import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.bj.m */
abstract class GeometryDrawer implements Renderer, LevelInfo {
    protected PanoramaConfig f6257a;
    private TextureCache f6258b;
    private int f6259c;
    private float f6260d;
    private int f6261e;
    private int f6262f;
    private int f6263g;
    private int f6264h;
    private final ad f6265i;
    private final PanoramaManager f6266j;
    private LevelInfo[] f6267k;
    private final VisibilityTester f6268l;
    private int[] f6269m;
    private int[] f6270n;
    private Renderer f6271o;
    private float f6272p;

    public GeometryDrawer(ad adVar, PanoramaManager panoramaManager) {
        this.f6265i = adVar;
        this.f6266j = panoramaManager;
        this.f6268l = new VisibilityTester();
    }

    protected final void m9710a(PanoramaConfig panoramaConfig, TextureCache textureCache, int i, int i2, float f, int i3) {
        this.f6257a = panoramaConfig;
        this.f6258b = textureCache;
        this.f6259c = i2;
        this.f6260d = ((float) i) / ((float) i2);
        this.f6262f = panoramaConfig.f6589e;
        this.f6263g = panoramaConfig.f6590f;
        this.f6264h = panoramaConfig.f6591g;
        this.f6261e = ar.m8595a(panoramaConfig.f6588d, panoramaConfig.f6589e, panoramaConfig.f6590f, panoramaConfig.f6591g);
        int min = Math.min(panoramaConfig.f6581A, this.f6261e);
        this.f6267k = new LevelInfo[min];
        for (int i4 = 0; i4 < min; i4++) {
            this.f6267k[i4] = new LevelInfo(i4, (this.f6261e - 1) - i4, this, panoramaConfig.f6588d, panoramaConfig.f6589e, this.f6263g, this.f6264h, br.DEFAULT_BACKOFF_MULT, i3);
        }
        this.f6272p = ar.m8591a(-this.f6257a.f6603s, 360.0f);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m9711a(javax.microedition.khronos.opengles.GL10 r22, com.google.android.m4b.maps.be.ay r23, com.google.android.m4b.maps.bj.ag.Renderer r24, com.google.android.m4b.maps.bj.ag.Renderer r25) {
        /*
        r21 = this;
        r0 = r21;
        r2 = r0.f6257a;
        if (r2 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = r24;
        r1 = r21;
        r1.f6271o = r0;
        r2 = r23.m8756f();
        r3 = 1040187392; // 0x3e000000 float:0.125 double:5.139208556E-315;
        r3 = r3 * r2;
        r0 = r21;
        r4 = r0.f6260d;
        r4 = 5889; // 0x1701 float:8.252E-42 double:2.9096E-320;
        r0 = r22;
        r0.glMatrixMode(r4);
        r22.glLoadIdentity();
        r0 = r21;
        r4 = r0.f6260d;
        r4 = com.google.android.m4b.maps.bj.ag.m9796a(r4);
        r2 = r2 * r4;
        r0 = r21;
        r4 = r0.f6260d;
        r5 = 1036831949; // 0x3dcccccd float:0.1 double:5.122630465E-315;
        r6 = 1137180672; // 0x43c80000 float:400.0 double:5.61841903E-315;
        r0 = r22;
        android.opengl.GLU.gluPerspective(r0, r2, r4, r5, r6);
        r2 = 5888; // 0x1700 float:8.251E-42 double:2.909E-320;
        r0 = r22;
        r0.glMatrixMode(r2);
        r22.glLoadIdentity();
        r2 = r23.m8749a();
        r4 = 0;
        r0 = r22;
        r0.glMultMatrixf(r2, r4);
        r2 = 1127481344; // 0x43340000 float:180.0 double:5.570497984E-315;
        r0 = r21;
        r4 = r0.f6257a;
        r4 = r4.f6604t;
        r2 = r2 - r4;
        r2 = com.google.android.m4b.maps.be.ar.m8614o(r2);
        r4 = android.util.FloatMath.cos(r2);
        r2 = android.util.FloatMath.sin(r2);
        r0 = r21;
        r5 = r0.f6257a;
        r5 = r5.f6605u;
        r5 = -r5;
        r6 = 0;
        r2 = -r2;
        r0 = r22;
        r0.glRotatef(r5, r4, r6, r2);
        r0 = r21;
        r2 = r0.f6272p;
        r4 = 0;
        r5 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r6 = 0;
        r0 = r22;
        r0.glRotatef(r2, r4, r5, r6);
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = r3 * r2;
        r0 = r21;
        r2 = r0.f6259c;
        r4 = (float) r2;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5 = r23.m8755e();
        r0 = r21;
        r6 = r0.f6262f;
        r6 = (float) r6;
        r3 = r3 * r6;
        r3 = r3 / r4;
        r0 = r21;
        r4 = r0.f6260d;
        r6 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 <= 0) goto L_0x009e;
    L_0x009c:
        r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
    L_0x009e:
        r3 = com.google.android.m4b.maps.be.ar.m8602d(r3);
        r2 = r2 + r3;
        r2 = android.util.FloatMath.floor(r2);
        r2 = (int) r2;
        r0 = r21;
        r3 = r0.f6261e;
        r3 = r3 + -1;
        r2 = r3 - r2;
        r3 = 0;
        r0 = r21;
        r4 = r0.f6267k;
        r4 = r4.length;
        r4 = r4 + -1;
        r2 = com.google.android.m4b.maps.be.ar.m8594a(r2, r3, r4);
        r3 = 0;
        r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1));
        if (r3 != 0) goto L_0x00c6;
    L_0x00c1:
        r3 = 3;
        r2 = java.lang.Math.min(r2, r3);
    L_0x00c6:
        r3 = 5888; // 0x1700 float:8.251E-42 double:2.909E-320;
        r0 = r22;
        r0.glMatrixMode(r3);
        r22.glPushMatrix();
        if (r25 == 0) goto L_0x017a;
    L_0x00d2:
        r3 = 0;
        r4 = 1036831949; // 0x3dcccccd float:0.1 double:5.122630465E-315;
        r5 = 1064514355; // 0x3f733333 float:0.95 double:5.259399723E-315;
        r6 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r7 = r25.m9791a();
        r3 = com.google.android.m4b.maps.be.ar.m8593a(r3, r4, r5, r6, r7);
        r4 = r25.m9794c();
        r4 = -r4;
        r4 = r4 * r3;
        r5 = 0;
        r6 = r25.m9795d();
        r3 = r3 * r6;
        r0 = r22;
        r0.glTranslatef(r4, r5, r3);
        r3 = 2929; // 0xb71 float:4.104E-42 double:1.447E-320;
        r0 = r22;
        r0.glEnable(r3);
        r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r0 = r22;
        r0.glClear(r3);
    L_0x0102:
        r0 = r21;
        r3 = r0.f6268l;
        r0 = r22;
        r3.m9947a(r0);
        r9 = r2;
    L_0x010c:
        r0 = r21;
        r2 = r0.f6267k;
        r16 = r2[r9];
        r5 = r16.m10024a();
        r0 = r21;
        r2 = r0.f6269m;
        if (r2 == 0) goto L_0x0123;
    L_0x011c:
        r0 = r21;
        r2 = r0.f6269m;
        r2 = r2.length;
        if (r5 <= r2) goto L_0x012f;
    L_0x0123:
        r2 = new int[r5];
        r0 = r21;
        r0.f6269m = r2;
        r2 = new int[r5];
        r0 = r21;
        r0.f6270n = r2;
    L_0x012f:
        r0 = r21;
        r2 = r0.f6268l;
        if (r25 == 0) goto L_0x0182;
    L_0x0135:
        r3 = 1;
    L_0x0136:
        r0 = r16;
        r3 = r0.m10027a(r3);
        r4 = 0;
        r0 = r21;
        r6 = r0.f6269m;
        r7 = 0;
        r0 = r21;
        r8 = r0.f6269m;
        r8 = r8.length;
        r5 = r2.m9945a(r3, r4, r5, r6, r7, r8);
        r3 = 0;
        r2 = 0;
        r4 = r2;
    L_0x014e:
        if (r4 >= r5) goto L_0x02c0;
    L_0x0150:
        r0 = r21;
        r2 = r0.f6269m;
        r6 = r2[r4];
        if (r25 == 0) goto L_0x0184;
    L_0x0158:
        r2 = 1;
    L_0x0159:
        r0 = r16;
        r2 = r0.m10026a(r6, r2);
        r0 = r21;
        r7 = r0.f6268l;
        r2 = r2.m9976a(r7);
        if (r2 <= 0) goto L_0x02bd;
    L_0x0169:
        r0 = r21;
        r7 = r0.f6270n;
        r2 = r3 + 1;
        r7[r3] = r6;
        r3 = 16;
        if (r2 > r3) goto L_0x0186;
    L_0x0175:
        r3 = r4 + 1;
        r4 = r3;
        r3 = r2;
        goto L_0x014e;
    L_0x017a:
        r3 = 2929; // 0xb71 float:4.104E-42 double:1.447E-320;
        r0 = r22;
        r0.glDisable(r3);
        goto L_0x0102;
    L_0x0182:
        r3 = 0;
        goto L_0x0136;
    L_0x0184:
        r2 = 0;
        goto L_0x0159;
    L_0x0186:
        r15 = r2;
    L_0x0187:
        r2 = 16;
        if (r15 <= r2) goto L_0x0192;
    L_0x018b:
        if (r9 <= 0) goto L_0x0192;
    L_0x018d:
        r2 = r9 + -1;
        r9 = r2;
        goto L_0x010c;
    L_0x0192:
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r0 = r22;
        r0.glColor4x(r2, r3, r4, r5);
        r0 = r21;
        r2 = r0.f6271o;
        r3 = 0;
        r2.f6361a = r3;
        r3 = 0;
        r2.f6362b = r3;
        r0 = r21;
        r2 = r0.f6257a;
        r2 = r2.f6602r;
        r3 = r2.iterator();
    L_0x01af:
        r2 = r3.hasNext();
        if (r2 == 0) goto L_0x01d1;
    L_0x01b5:
        r2 = r3.next();
        r2 = (com.google.android.m4b.maps.bj.ac) r2;
        r0 = r21;
        r4 = r0.f6258b;
        r4 = r4.m9921a(r2);
        if (r4 != 0) goto L_0x01af;
    L_0x01c5:
        r4 = com.google.android.m4b.maps.p040u.DataRequestDispatcher.m11393a();
        if (r4 == 0) goto L_0x01af;
    L_0x01cb:
        r0 = r21;
        r0.m9709a(r2);
        goto L_0x01af;
    L_0x01d1:
        r0 = r16;
        r0 = r0.f6645a;
        r17 = r0;
        r0 = r16;
        r0 = r0.f6646b;
        r18 = r0;
        r2 = 0;
        r11 = r2;
    L_0x01df:
        if (r11 >= r15) goto L_0x02af;
    L_0x01e1:
        r0 = r21;
        r2 = r0.f6270n;
        r2 = r2[r11];
        r3 = r17 * r18;
        r3 = r2 / r3;
        r0 = r16;
        r6 = r0.m10025a(r3);
        r3 = r17 * r18;
        r2 = r2 % r3;
        r5 = r2 / r17;
        r7 = r2 % r17;
        r0 = r21;
        r2 = r0.f6270n;
        r19 = r2[r11];
        if (r25 == 0) goto L_0x026e;
    L_0x0200:
        r2 = 1;
        r8 = r2;
    L_0x0202:
        r0 = r21;
        r2 = r0.f6267k;
        r20 = r2[r9];
        r2 = 5890; // 0x1702 float:8.254E-42 double:2.91E-320;
        r0 = r22;
        r0.glMatrixMode(r2);
        r22.glPushMatrix();
        r4 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = 0;
        r2 = 0;
        r10 = 0;
        r12 = r2;
        r13 = r3;
        r14 = r4;
        r4 = r7;
        r7 = r9;
    L_0x021c:
        if (r7 < 0) goto L_0x02a5;
    L_0x021e:
        r2 = new com.google.android.m4b.maps.bj.ac;
        r0 = r21;
        r3 = r0.f6257a;
        r3 = r3.f6592h;
        r2.<init>(r3, r4, r5, r6, r7);
        r0 = r21;
        r3 = r0.f6258b;
        r3 = r3.m9927a(r2);
        if (r3 == 0) goto L_0x0271;
    L_0x0233:
        r2 = 3553; // 0xde1 float:4.979E-42 double:1.7554E-320;
        r0 = r22;
        r0.glBindTexture(r2, r3);
        r22.glLoadIdentity();
        r2 = 0;
        r0 = r22;
        r0.glTranslatef(r13, r12, r2);
        r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r0 = r22;
        r0.glScalef(r14, r14, r2);
        r0 = r21;
        r2 = r0.f6271o;
        r2.m9788a(r14);
        r2 = 1;
    L_0x0252:
        r0 = r20;
        r1 = r19;
        r3 = r0.m10026a(r1, r8);
        r0 = r22;
        r3.m9978a(r0, r2);
        r2 = 5890; // 0x1702 float:8.254E-42 double:2.91E-320;
        r0 = r22;
        r0.glMatrixMode(r2);
        r22.glPopMatrix();
        r2 = r11 + 1;
        r11 = r2;
        goto L_0x01df;
    L_0x026e:
        r2 = 0;
        r8 = r2;
        goto L_0x0202;
    L_0x0271:
        if (r10 != 0) goto L_0x02bb;
    L_0x0273:
        r3 = com.google.android.m4b.maps.p040u.DataRequestDispatcher.m11393a();
        if (r3 == 0) goto L_0x02bb;
    L_0x0279:
        r0 = r21;
        r0.m9709a(r2);
        r2 = 1;
    L_0x027f:
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = r13 / r3;
        r10 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r13 = r4 & 1;
        r13 = (float) r13;
        r10 = r10 * r13;
        r10 = r10 + r3;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = r12 / r3;
        r12 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r13 = r5 & 1;
        r13 = (float) r13;
        r12 = r12 * r13;
        r3 = r3 + r12;
        r12 = r4 >> 1;
        r5 = r5 >> 1;
        r7 = r7 + -1;
        r4 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r4 = r4 * r14;
        r13 = r10;
        r14 = r4;
        r10 = r2;
        r4 = r12;
        r12 = r3;
        goto L_0x021c;
    L_0x02a5:
        r0 = r21;
        r2 = r0.f6271o;
        r3 = 0;
        r2.m9788a(r3);
        r2 = 0;
        goto L_0x0252;
    L_0x02af:
        r2 = 5888; // 0x1700 float:8.251E-42 double:2.909E-320;
        r0 = r22;
        r0.glMatrixMode(r2);
        r22.glPopMatrix();
        goto L_0x0006;
    L_0x02bb:
        r2 = r10;
        goto L_0x027f;
    L_0x02bd:
        r2 = r3;
        goto L_0x0175;
    L_0x02c0:
        r15 = r3;
        goto L_0x0187;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.bj.m.a(javax.microedition.khronos.opengles.GL10, com.google.android.m4b.maps.be.ay, com.google.android.m4b.maps.bj.ag$f, com.google.android.m4b.maps.bj.ag$i):void");
    }

    private void m9709a(ac acVar) {
        this.f6265i.m9768a(this.f6266j, acVar, true);
    }
}
