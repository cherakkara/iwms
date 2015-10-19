package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.av.as.GmmGLSurfaceView;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/* compiled from: GmmEglConfigChooser */
public final class ar implements GmmGLSurfaceView {
    private int[] f4387a;
    private final GmmEglConfigChooser[] f4388b;

    /* renamed from: com.google.android.m4b.maps.av.ar.a */
    public static class GmmEglConfigChooser {
        private final int f4379a;
        private final int f4380b;
        private final int f4381c;
        private final int f4382d;
        private final int f4383e;
        private final int f4384f;
        private int[] f4385g;
        private boolean f4386h;

        GmmEglConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
            this.f4385g = null;
            this.f4386h = false;
            this.f4379a = i;
            this.f4380b = i2;
            this.f4381c = i3;
            this.f4382d = i4;
            this.f4383e = i5;
            this.f4384f = i6;
        }

        GmmEglConfigChooser(GmmEglConfigChooser gmmEglConfigChooser) {
            this(gmmEglConfigChooser.f4379a, gmmEglConfigChooser.f4380b, gmmEglConfigChooser.f4381c, gmmEglConfigChooser.f4382d, gmmEglConfigChooser.f4383e, gmmEglConfigChooser.f4384f);
        }

        final void m7018a(boolean z) {
            this.f4386h = z;
        }

        final int[] m7019a() {
            if (this.f4385g == null) {
                if (this.f4386h) {
                    this.f4385g = new int[]{12324, this.f4379a, 12323, this.f4380b, 12322, this.f4381c, 12321, this.f4382d, 12325, this.f4383e, 12326, this.f4384f, 12352, 4, 12344};
                } else {
                    this.f4385g = new int[]{12324, this.f4379a, 12323, this.f4380b, 12322, this.f4381c, 12321, this.f4382d, 12325, this.f4383e, 12326, this.f4384f, 12344};
                }
            }
            return this.f4385g;
        }
    }

    public ar(GmmEglConfigChooser[] gmmEglConfigChooserArr) {
        this.f4387a = new int[1];
        this.f4388b = gmmEglConfigChooserArr;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final javax.microedition.khronos.egl.EGLConfig m7023a(javax.microedition.khronos.egl.EGL10 r14, javax.microedition.khronos.egl.EGLDisplay r15) {
        /*
        r13 = this;
        r3 = 0;
        r6 = 1;
        r4 = 0;
        r5 = new int[r6];
        r0 = new com.google.android.m4b.maps.av.ar$a;
        r1 = r13.f4388b;
        r1 = r1[r4];
        r0.<init>(r1);
        r0.m7018a(r6);
        r2 = r0.m7019a();
        r0 = r14;
        r1 = r15;
        r0 = r0.eglChooseConfig(r1, r2, r3, r4, r5);
        if (r0 != 0) goto L_0x0025;
    L_0x001d:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "eglChooseConfig failed";
        r0.<init>(r1);
        throw r0;
    L_0x0025:
        r0 = r5[r4];
        if (r0 <= 0) goto L_0x0047;
    L_0x0029:
        r0 = r6;
    L_0x002a:
        com.google.android.m4b.maps.p059w.DeviceSpecificInfo.m11564a(r0);
        r12 = r4;
        r6 = r3;
    L_0x002f:
        r0 = r13.f4388b;
        r0 = r0[r12];
        r2 = r0.m7019a();
        r0 = r14;
        r1 = r15;
        r0 = r0.eglChooseConfig(r1, r2, r3, r4, r5);
        if (r0 != 0) goto L_0x0049;
    L_0x003f:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "eglChooseConfig failed";
        r0.<init>(r1);
        throw r0;
    L_0x0047:
        r0 = r4;
        goto L_0x002a;
    L_0x0049:
        r10 = r5[r4];
        if (r10 <= 0) goto L_0x007d;
    L_0x004d:
        r9 = new javax.microedition.khronos.egl.EGLConfig[r10];
        r6 = r14;
        r7 = r15;
        r8 = r2;
        r11 = r5;
        r0 = r6.eglChooseConfig(r7, r8, r9, r10, r11);
        if (r0 != 0) goto L_0x0061;
    L_0x0059:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "eglChooseConfig#2 failed";
        r0.<init>(r1);
        throw r0;
    L_0x0061:
        r0 = r13.f4388b;
        r0 = r0[r12];
        r1 = r13.m7022a(r14, r15, r9, r0);
    L_0x0069:
        if (r1 != 0) goto L_0x0072;
    L_0x006b:
        r0 = r12 + 1;
        r2 = r13.f4388b;
        r2 = r2.length;
        if (r0 < r2) goto L_0x007f;
    L_0x0072:
        if (r1 != 0) goto L_0x007c;
    L_0x0074:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "No config chosen";
        r0.<init>(r1);
        throw r0;
    L_0x007c:
        return r1;
    L_0x007d:
        r1 = r6;
        goto L_0x0069;
    L_0x007f:
        r12 = r0;
        r6 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.ar.a(javax.microedition.khronos.egl.EGL10, javax.microedition.khronos.egl.EGLDisplay):javax.microedition.khronos.egl.EGLConfig");
    }

    private int m7021a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
        if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.f4387a)) {
            return this.f4387a[0];
        }
        return 0;
    }

    private EGLConfig m7022a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr, GmmEglConfigChooser gmmEglConfigChooser) {
        for (EGLConfig eGLConfig : eGLConfigArr) {
            int a = m7021a(egl10, eGLDisplay, eGLConfig, 12325, 0);
            int a2 = m7021a(egl10, eGLDisplay, eGLConfig, 12326, 0);
            if (a >= gmmEglConfigChooser.f4383e && a2 >= gmmEglConfigChooser.f4384f) {
                a = m7021a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                int a3 = m7021a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                int a4 = m7021a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                a2 = m7021a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                if (a == gmmEglConfigChooser.f4379a && a3 == gmmEglConfigChooser.f4380b && a4 == gmmEglConfigChooser.f4381c && a2 == gmmEglConfigChooser.f4382d) {
                    return eGLConfig;
                }
            }
        }
        return null;
    }
}
