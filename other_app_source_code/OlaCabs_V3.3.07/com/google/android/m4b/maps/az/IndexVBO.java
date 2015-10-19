package com.google.android.m4b.maps.az;

import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import javax.microedition.khronos.opengles.GL11;

/* renamed from: com.google.android.m4b.maps.az.e */
public final class IndexVBO extends IndexBuffer {
    private final int[] f4974f;
    private volatile long f4975g;

    public IndexVBO(int i) {
        super(i);
        this.f4974f = new int[1];
        this.f4975g = -1;
    }

    public IndexVBO(int i, boolean z) {
        super(i, true);
        this.f4974f = new int[1];
        this.f4975g = -1;
    }

    public final void m7677a(GLState gLState) {
        super.m7664a(gLState);
        int[] iArr = this.f4974f;
        this.f4974f[0] = 0;
    }

    public final void m7679b(GLState gLState) {
        int[] iArr = this.f4974f;
        if (this.f4974f[0] != 0) {
            GLState b = GLState.m7502b(this.f4975g);
            if (b == gLState && b != null) {
                ((GL11) b.m7541x()).glDeleteBuffers(1, this.f4974f, 0);
            }
            this.f4974f[0] = 0;
            this.d = 0;
        }
        this.f4975g = GLState.m7500a(gLState);
    }

    protected final void m7681d(GLState gLState) {
        if (gLState.m7508F()) {
            this.c = gLState.m7512J().m7722b();
            if (this.e == null) {
                this.c.put(this.a, 0, this.b);
            } else {
                m7661a();
                this.e.m4845a(this.c);
            }
            this.c.limit(this.b);
            this.c.position(0);
            if (!DeviceSpecificInfo.f8006a) {
                if (this.e != null) {
                    this.e.m4832c();
                    this.e = null;
                }
                this.a = null;
                return;
            }
            return;
        }
        super.m7676d(gLState);
    }

    public final void m7678a(GLState gLState, int i) {
        this.f4975g = GLState.m7500a(gLState);
        if (gLState.m7508F()) {
            GL11 gl11 = (GL11) gLState.m7541x();
            if (this.f4974f[0] == 0) {
                if (this.c == null) {
                    m7681d(gLState);
                }
                if (this.c.limit() != 0) {
                    gl11.glGenBuffers(1, this.f4974f, 0);
                    gl11.glBindBuffer(34963, this.f4974f[0]);
                    this.d = this.c.limit() * 2;
                    gl11.glBufferData(34963, this.d, this.c, 35044);
                    this.c = null;
                } else {
                    return;
                }
            }
            gl11.glBindBuffer(34963, this.f4974f[0]);
            gl11.glDrawElements(i, this.b, 5123, 0);
            gl11.glBindBuffer(34963, 0);
            return;
        }
        super.m7665a(gLState, i);
    }

    public final int m7680d() {
        if (this.e != null) {
            return (this.e.m4830b() * 2) + 56;
        }
        if (this.a != null) {
            return ((this.a.length * 2) + 16) + 56;
        }
        return 56;
    }
}
