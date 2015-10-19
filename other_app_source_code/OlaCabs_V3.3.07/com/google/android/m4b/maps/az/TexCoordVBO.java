package com.google.android.m4b.maps.az;

import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL11;

/* renamed from: com.google.android.m4b.maps.az.i */
public class TexCoordVBO extends TexCoordBuffer {
    private final int[] f4994k;
    private volatile long f4995l;

    /* renamed from: com.google.android.m4b.maps.az.i.a */
    public static final class TexCoordVBO extends TexCoordVBO {
        public TexCoordVBO(int[] iArr) {
            super(4, true);
            this.b = iArr;
            this.c = 4;
            this.d = this.c;
            this.i = 8;
        }

        public final void m7718a(int i, int i2) {
            throw new UnsupportedOperationException("Immutable");
        }

        public final void m7717a(float f, float f2) {
            throw new UnsupportedOperationException("Immutable");
        }

        public final void m7720a(int[] iArr, int i, int i2) {
            throw new UnsupportedOperationException("Immutable");
        }

        public final void m7719a(GLState gLState) {
            throw new UnsupportedOperationException("Immutable");
        }
    }

    public TexCoordVBO(int i) {
        super(i);
        this.f4994k = new int[1];
        this.f4995l = -1;
    }

    public TexCoordVBO(int i, boolean z) {
        super(i, true);
        this.f4994k = new int[1];
        this.f4995l = -1;
    }

    private TexCoordVBO(int i, int i2, int i3) {
        super(i, 5122, i3, true);
        this.f4994k = new int[1];
        this.f4995l = -1;
    }

    public static TexCoordVBO m7709b(int i, int i2) {
        return new TexCoordVBO(i, 5122, 1);
    }

    public void m7711a(GLState gLState) {
        super.m7695a(gLState);
        int[] iArr = this.f4994k;
        this.f4994k[0] = 0;
    }

    public final void m7713b(GLState gLState) {
        int[] iArr = this.f4994k;
        if (this.f4994k[0] != 0) {
            GLState b = GLState.m7502b(this.f4995l);
            if (b == gLState && b != null) {
                ((GL11) b.m7541x()).glDeleteBuffers(1, this.f4994k, 0);
            }
            this.f4994k[0] = 0;
            this.a = 0;
        }
        this.f4995l = GLState.m7500a(gLState);
    }

    protected final void m7716e(GLState gLState) {
        if (gLState.m7508F()) {
            int i = this.d * 2;
            if (this.f == 5122) {
                this.e = gLState.m7512J().m7722b();
                if (this.j == null) {
                    m7698a((ShortBuffer) this.e, i);
                } else {
                    m7691a();
                    this.j.m4843a((ShortBuffer) this.e, this.h);
                }
            } else if (this.f == 5121 || this.f == 5120) {
                this.e = gLState.m7512J().m7721a();
                if (this.j == null) {
                    m7697a((ByteBuffer) this.e, i);
                } else {
                    m7691a();
                    this.j.m4841a((ByteBuffer) this.e, this.h);
                }
            } else {
                this.e = gLState.m7512J().m7723c();
                if (this.j == null) {
                    ((IntBuffer) this.e).put(this.b, 0, i);
                } else {
                    m7691a();
                    this.j.m4842a((IntBuffer) this.e);
                }
            }
            this.e.limit(i);
            this.e.position(0);
            if (!DeviceSpecificInfo.f8006a) {
                if (this.j != null) {
                    this.j.m4832c();
                    this.j = null;
                }
                this.b = null;
                return;
            }
            return;
        }
        super.m7707e(gLState);
    }

    public final void m7715d(GLState gLState) {
        this.f4995l = GLState.m7500a(gLState);
        if (!gLState.m7508F()) {
            super.m7706d(gLState);
        } else if (this.f4994k[0] != 0 || m7710f(gLState)) {
            GL11 gl11 = (GL11) gLState.m7541x();
            gl11.glBindBuffer(34962, this.f4994k[0]);
            gl11.glTexCoordPointer(2, this.f, 0, 0);
            gl11.glBindBuffer(34962, 0);
        }
    }

    public final void m7712a(GLState gLState, int i) {
        this.f4995l = GLState.m7500a(gLState);
        if (!gLState.m7508F()) {
            super.m7696a(gLState, i);
        } else if (this.f4994k[0] != 0 || m7710f(gLState)) {
            GL11 gl11 = (GL11) gLState.m7541x();
            gl11.glBindBuffer(34962, this.f4994k[0]);
            gl11.glTexCoordPointer(2, this.f, 0, (i * 2) * this.g);
            gl11.glBindBuffer(34962, 0);
        }
    }

    private boolean m7710f(GLState gLState) {
        if (this.e == null) {
            m7716e(gLState);
        }
        if (this.e.limit() == 0) {
            return false;
        }
        GL11 gl11 = (GL11) gLState.m7541x();
        gl11.glGenBuffers(1, this.f4994k, 0);
        gl11.glBindBuffer(34962, this.f4994k[0]);
        this.a = this.e.limit() * this.g;
        gl11.glBufferData(34962, this.a, this.e, 35044);
        this.e = null;
        return true;
    }

    public final int m7714c() {
        if (this.j != null) {
            return (this.j.m4830b() * 4) + 56;
        }
        if (this.b != null) {
            return ((this.b.length * 4) + 16) + 56;
        }
        return 56;
    }
}
