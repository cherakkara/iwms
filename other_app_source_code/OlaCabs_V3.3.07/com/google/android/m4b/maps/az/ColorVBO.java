package com.google.android.m4b.maps.az;

import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import javax.microedition.khronos.opengles.GL11;

/* renamed from: com.google.android.m4b.maps.az.b */
public final class ColorVBO extends ColorBuffer {
    private final int[] f4964f;
    private volatile long f4965g;

    public ColorVBO(int i) {
        super(i);
        this.f4964f = new int[1];
        this.f4965g = -1;
    }

    public ColorVBO(int i, boolean z) {
        super(i, true);
        this.f4964f = new int[1];
        this.f4965g = -1;
    }

    public final void m7654a(GLState gLState) {
        int[] iArr = this.f4964f;
        if (this.f4964f[0] != 0) {
            GLState b = GLState.m7502b(this.f4965g);
            if (b == gLState && b != null) {
                ((GL11) b.m7541x()).glDeleteBuffers(1, this.f4964f, 0);
            }
            this.f4964f[0] = 0;
            this.d = 0;
        }
        this.f4965g = GLState.m7500a(gLState);
    }

    protected final void m7657d(GLState gLState) {
        if (gLState.m7508F()) {
            int i = this.b * 4;
            this.c = gLState.m7512J().m7721a();
            m7647a(i, !DeviceSpecificInfo.f8006a);
            return;
        }
        super.m7653d(gLState);
    }

    public final void m7656c(GLState gLState) {
        this.f4965g = GLState.m7500a(gLState);
        if (gLState.m7508F()) {
            GL11 gl11 = (GL11) gLState.m7541x();
            if (this.f4964f[0] == 0) {
                if (this.c == null) {
                    m7657d(gLState);
                }
                if (this.c.limit() != 0) {
                    gl11.glGenBuffers(1, this.f4964f, 0);
                    gl11.glBindBuffer(34962, this.f4964f[0]);
                    this.d = this.c.limit();
                    gl11.glBufferData(34962, this.d, this.c, 35044);
                    this.c = null;
                } else {
                    return;
                }
            }
            gl11.glBindBuffer(34962, this.f4964f[0]);
            gl11.glColorPointer(4, 5121, 0, 0);
            gl11.glBindBuffer(34962, 0);
            return;
        }
        super.m7652c(gLState);
    }

    public final int m7655b() {
        if (this.e != null) {
            return this.e.m4830b() + 56;
        }
        if (this.a != null) {
            return (this.a.length + 16) + 56;
        }
        return 56;
    }
}
