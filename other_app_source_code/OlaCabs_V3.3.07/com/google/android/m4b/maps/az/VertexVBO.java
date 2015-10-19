package com.google.android.m4b.maps.az;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import javax.microedition.khronos.opengles.GL11;

/* renamed from: com.google.android.m4b.maps.az.m */
public class VertexVBO extends VertexBuffer {
    private final int[] f5007h;
    private volatile long f5008i;

    /* renamed from: com.google.android.m4b.maps.az.m.a */
    public static final class VertexVBO extends VertexVBO {
        public VertexVBO(int[] iArr) {
            super(4, true);
            this.b = iArr;
            this.c = 4;
            this.d = this.c;
            this.f = 12;
        }

        public final void m7744a(Point point, int i) {
            throw new UnsupportedOperationException("Immutable");
        }

        public final void m7743a(float f, float f2, float f3) {
            throw new UnsupportedOperationException("Immutable");
        }

        public final void m7745a(GLState gLState) {
            throw new UnsupportedOperationException("Immutable");
        }
    }

    public VertexVBO(int i) {
        super(i);
        this.f5007h = new int[1];
        this.f5008i = -1;
    }

    public VertexVBO(int i, boolean z) {
        super(i, true);
        this.f5007h = new int[1];
        this.f5008i = -1;
    }

    public void m7738a(GLState gLState) {
        super.m7730a(gLState);
        int[] iArr = this.f5007h;
        this.f5007h[0] = 0;
    }

    public final void m7739b(GLState gLState) {
        int[] iArr = this.f5007h;
        if (this.f5007h[0] != 0) {
            GLState b = GLState.m7502b(this.f5008i);
            if (b == gLState && b != null) {
                ((GL11) b.m7541x()).glDeleteBuffers(1, this.f5007h, 0);
            }
            this.f5007h[0] = 0;
            this.a = 0;
        }
        this.f5008i = GLState.m7500a(gLState);
    }

    protected final void m7742e(GLState gLState) {
        if (gLState.m7508F()) {
            int i = this.d * 3;
            this.e = gLState.m7512J().m7723c();
            if (this.g == null) {
                this.e.put(this.b, 0, i);
            } else {
                m7731b();
                this.g.m4842a(this.e);
            }
            this.e.limit(i);
            this.e.position(0);
            if (!DeviceSpecificInfo.f8006a) {
                if (this.g != null) {
                    this.g.m4832c();
                    this.g = null;
                }
                this.b = null;
                return;
            }
            return;
        }
        super.m7737e(gLState);
    }

    public final void m7741d(GLState gLState) {
        this.f5008i = GLState.m7500a(gLState);
        if (gLState.m7508F()) {
            GL11 gl11 = (GL11) gLState.m7541x();
            if (this.f5007h[0] == 0) {
                if (this.e == null) {
                    m7742e(gLState);
                }
                if (this.e.limit() != 0) {
                    gl11.glGenBuffers(1, this.f5007h, 0);
                    gl11.glBindBuffer(34962, this.f5007h[0]);
                    this.a = this.e.limit() * 4;
                    gl11.glBufferData(34962, this.a, this.e, 35044);
                    this.e = null;
                } else {
                    return;
                }
            }
            gl11.glBindBuffer(34962, this.f5007h[0]);
            gl11.glVertexPointer(3, 5132, 0, 0);
            gl11.glBindBuffer(34962, 0);
            return;
        }
        super.m7736d(gLState);
    }

    public final int m7740d() {
        if (this.g != null) {
            return (this.g.m4830b() * 4) + 56;
        }
        if (this.b != null) {
            return ((this.b.length * 4) + 16) + 56;
        }
        return 56;
    }
}
