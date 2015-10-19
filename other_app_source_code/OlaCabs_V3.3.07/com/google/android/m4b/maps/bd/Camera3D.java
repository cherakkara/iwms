package com.google.android.m4b.maps.bd;

import android.opengl.Matrix;
import com.google.android.m4b.maps.bd.Entity.Entity;
import com.olacabs.customer.p076d.br;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.bd.e */
public class Camera3D {
    private RenderTarget f4764a;
    private volatile float[] f4765b;
    private float[] f4766c;
    private float[] f4767d;
    private float[] f4768e;
    private boolean f4769f;
    private int f4770g;
    private byte f4771h;
    private boolean f4772i;
    private final float f4773j;
    private final float f4774k;
    private final float f4775l;
    private final Camera3D f4776m;
    private List f4777n;

    /* renamed from: com.google.android.m4b.maps.bd.e.1 */
    static /* synthetic */ class Camera3D {
        static final /* synthetic */ int[] f5471a;

        static {
            f5471a = new int[Camera3D.values().length];
            try {
                f5471a[Camera3D.PERSPECTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5471a[Camera3D.ORTHOGRAPHIC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f5471a[Camera3D.USER_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.bd.e.a */
    enum Camera3D {
        PERSPECTIVE,
        ORTHOGRAPHIC,
        USER_SET
    }

    public Camera3D(RenderTarget renderTarget, int i, float[] fArr) {
        this.f4765b = new float[16];
        this.f4766c = new float[16];
        this.f4767d = new float[16];
        this.f4768e = new float[16];
        this.f4769f = false;
        this.f4770g = 0;
        this.f4772i = false;
        this.f4777n = new ArrayList();
        this.f4764a = renderTarget;
        this.f4771h = (byte) i;
        this.f4773j = -1.0f;
        this.f4774k = br.DEFAULT_BACKOFF_MULT;
        this.f4775l = 0.0f;
        this.f4776m = Camera3D.USER_SET;
        System.arraycopy(fArr, 0, this.f4765b, 0, 16);
        this.f4769f = true;
    }

    protected final void m7404w() {
        Matrix.multiplyMM(this.f4767d, 0, this.f4766c, 0, this.f4765b, 0);
        this.f4770g++;
    }

    public final RenderTarget m7405x() {
        return this.f4764a;
    }

    final boolean m7403a(EntityRenderer entityRenderer, Entity entity) {
        if (entity.f5481d == this.f4772i && !entity.f5482e) {
            return false;
        }
        this.f4772i = entity.f5481d;
        if (this.f4772i) {
            this.f4764a.m8240a(this);
            RenderTarget renderTarget = this.f4764a;
            m7402a(this.f4764a.m8242b(), this.f4764a.m8244c());
        } else {
            this.f4764a.m8243b(this);
        }
        this.f4764a.m8241a(entity);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void m7402a(int r12, int r13) {
        /*
        r11 = this;
        r10 = -1073741824; // 0xffffffffc0000000 float:-2.0 double:NaN;
        r9 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r8 = 0;
        r1 = r11.f4768e;
        monitor-enter(r1);
        r2 = com.google.android.m4b.maps.bd.Camera3D.Camera3D.f5471a;	 Catch:{ all -> 0x002e }
        r3 = r11.f4776m;	 Catch:{ all -> 0x002e }
        r3 = r3.ordinal();	 Catch:{ all -> 0x002e }
        r2 = r2[r3];	 Catch:{ all -> 0x002e }
        switch(r2) {
            case 1: goto L_0x0031;
            case 2: goto L_0x00ae;
            case 3: goto L_0x00a2;
            default: goto L_0x0017;
        };	 Catch:{ all -> 0x002e }
    L_0x0017:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x002e }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x002e }
        r3 = "Unimplemented projection type ";
        r2.<init>(r3);	 Catch:{ all -> 0x002e }
        r3 = r11.f4776m;	 Catch:{ all -> 0x002e }
        r2 = r2.append(r3);	 Catch:{ all -> 0x002e }
        r2 = r2.toString();	 Catch:{ all -> 0x002e }
        r0.<init>(r2);	 Catch:{ all -> 0x002e }
        throw r0;	 Catch:{ all -> 0x002e }
    L_0x002e:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x0031:
        r2 = r11.f4768e;	 Catch:{ all -> 0x002e }
        if (r13 != 0) goto L_0x00aa;
    L_0x0035:
        r4 = 0;
        r4 = java.lang.Math.toRadians(r4);	 Catch:{ all -> 0x002e }
        r6 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r4 = r4 / r6;
        r4 = java.lang.Math.tan(r4);	 Catch:{ all -> 0x002e }
        r3 = (float) r4;	 Catch:{ all -> 0x002e }
        r3 = r3 * r9;
        r4 = -r3;
        r5 = -r3;
        r5 = r5 / r0;
        r0 = r3 / r0;
        r6 = 0;
        r7 = r3 - r4;
        r7 = r10 / r7;
        r2[r6] = r7;	 Catch:{ all -> 0x002e }
        r6 = 1;
        r7 = 0;
        r2[r6] = r7;	 Catch:{ all -> 0x002e }
        r6 = 2;
        r7 = 0;
        r2[r6] = r7;	 Catch:{ all -> 0x002e }
        r6 = 3;
        r7 = 0;
        r2[r6] = r7;	 Catch:{ all -> 0x002e }
        r6 = 4;
        r7 = 0;
        r2[r6] = r7;	 Catch:{ all -> 0x002e }
        r6 = 5;
        r7 = r0 - r5;
        r7 = r10 / r7;
        r2[r6] = r7;	 Catch:{ all -> 0x002e }
        r6 = 6;
        r7 = 0;
        r2[r6] = r7;	 Catch:{ all -> 0x002e }
        r6 = 7;
        r7 = 0;
        r2[r6] = r7;	 Catch:{ all -> 0x002e }
        r6 = 8;
        r7 = r3 + r4;
        r3 = r3 - r4;
        r3 = r7 / r3;
        r2[r6] = r3;	 Catch:{ all -> 0x002e }
        r3 = 9;
        r4 = r0 + r5;
        r0 = r0 - r5;
        r0 = r4 / r0;
        r2[r3] = r0;	 Catch:{ all -> 0x002e }
        r0 = 10;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2[r0] = r3;	 Catch:{ all -> 0x002e }
        r0 = 11;
        r3 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r2[r0] = r3;	 Catch:{ all -> 0x002e }
        r0 = 12;
        r3 = 0;
        r2[r0] = r3;	 Catch:{ all -> 0x002e }
        r0 = 13;
        r3 = 0;
        r2[r0] = r3;	 Catch:{ all -> 0x002e }
        r0 = 14;
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r2[r0] = r3;	 Catch:{ all -> 0x002e }
        r0 = 15;
        r3 = 0;
        r2[r0] = r3;	 Catch:{ all -> 0x002e }
    L_0x00a2:
        r0 = 1;
        r11.f4769f = r0;	 Catch:{ all -> 0x002e }
        r11.m7401a();	 Catch:{ all -> 0x002e }
        monitor-exit(r1);	 Catch:{ all -> 0x002e }
        return;
    L_0x00aa:
        r0 = (float) r12;	 Catch:{ all -> 0x002e }
        r3 = (float) r13;	 Catch:{ all -> 0x002e }
        r0 = r0 / r3;
        goto L_0x0035;
    L_0x00ae:
        r2 = r11.f4768e;	 Catch:{ all -> 0x002e }
        r3 = (float) r12;	 Catch:{ all -> 0x002e }
        r4 = (float) r13;	 Catch:{ all -> 0x002e }
        r5 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1));
        if (r5 == 0) goto L_0x00a2;
    L_0x00b6:
        r5 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r5 == 0) goto L_0x00a2;
    L_0x00ba:
        r5 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));
        if (r5 == 0) goto L_0x00a2;
    L_0x00be:
        r5 = 0;
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = r3 - r8;
        r6 = r6 / r7;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 1;
        r6 = 0;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 2;
        r6 = 0;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 3;
        r6 = 0;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 4;
        r6 = 0;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 5;
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = r4 - r8;
        r6 = r6 / r7;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 6;
        r6 = 0;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 7;
        r6 = 0;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 8;
        r6 = 0;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 9;
        r6 = 0;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 10;
        r6 = r0 - r9;
        r6 = r10 / r6;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 11;
        r6 = 0;
        r2[r5] = r6;	 Catch:{ all -> 0x002e }
        r5 = 12;
        r6 = r3 + r8;
        r6 = -r6;
        r3 = r3 - r8;
        r3 = r6 / r3;
        r2[r5] = r3;	 Catch:{ all -> 0x002e }
        r3 = 13;
        r5 = r4 + r8;
        r5 = -r5;
        r4 = r4 - r8;
        r4 = r5 / r4;
        r2[r3] = r4;	 Catch:{ all -> 0x002e }
        r3 = 14;
        r4 = r0 + r9;
        r4 = -r4;
        r0 = r0 - r9;
        r0 = r4 / r0;
        r2[r3] = r0;	 Catch:{ all -> 0x002e }
        r0 = 15;
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r2[r0] = r3;	 Catch:{ all -> 0x002e }
        goto L_0x00a2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.bd.e.a(int, int):void");
    }

    final void m7406y() {
        synchronized (this.f4768e) {
            if (this.f4769f) {
                System.arraycopy(this.f4768e, 0, this.f4766c, 0, 16);
                m7404w();
                this.f4769f = false;
            }
        }
    }

    public final byte m7407z() {
        return this.f4771h;
    }

    private void m7401a() {
        Iterator it = this.f4777n.iterator();
        while (it.hasNext()) {
            it.next();
            float[] fArr = this.f4768e;
        }
    }
}
