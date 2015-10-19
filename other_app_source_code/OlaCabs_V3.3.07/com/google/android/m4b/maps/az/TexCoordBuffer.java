package com.google.android.m4b.maps.az;

import com.google.android.m4b.maps.ab.IntChunkArray;
import com.google.android.m4b.maps.ay.GLState;
import com.newrelic.agent.android.api.v1.Defaults;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/* renamed from: com.google.android.m4b.maps.az.g */
public class TexCoordBuffer implements TexCoordBufferInterface {
    protected int f4983a;
    int[] f4984b;
    int f4985c;
    int f4986d;
    Buffer f4987e;
    protected final int f4988f;
    protected final int f4989g;
    protected final int f4990h;
    int f4991i;
    protected IntChunkArray f4992j;
    private boolean f4993k;

    public TexCoordBuffer(int i) {
        this(i, false);
    }

    public TexCoordBuffer(int i, boolean z) {
        this.f4983a = 0;
        this.f4993k = z;
        this.f4985c = i;
        this.f4988f = 5132;
        this.f4989g = 4;
        this.f4990h = 1;
        m7690d();
    }

    TexCoordBuffer() {
        this.f4983a = 0;
        this.f4988f = 5132;
        this.f4989g = 4;
        this.f4990h = 1;
    }

    protected TexCoordBuffer(int i, int i2, int i3, boolean z) {
        this.f4983a = 0;
        this.f4993k = true;
        this.f4985c = i;
        switch (i2) {
            case 5120:
            case 5121:
                this.f4989g = 1;
                break;
            case 5122:
                this.f4989g = 2;
                break;
            case 5132:
                this.f4989g = 4;
                break;
            default:
                throw new IllegalArgumentException("glNativeType must be one of GL_FIXED, GL_SHORT or GL_BYTE");
        }
        this.f4988f = i2;
        this.f4990h = i3;
        m7690d();
    }

    protected final void m7691a() {
        if (this.f4992j != null) {
            this.f4992j.m4831b(this.f4991i);
            this.f4984b = (int[]) this.f4992j.c;
            this.f4991i = this.f4992j.d;
        }
    }

    public void m7693a(int i, int i2) {
        this.f4986d++;
        int[] iArr = this.f4984b;
        int i3 = this.f4991i;
        this.f4991i = i3 + 1;
        iArr[i3] = i;
        iArr = this.f4984b;
        i3 = this.f4991i;
        this.f4991i = i3 + 1;
        iArr[i3] = i2;
        if (this.f4991i >= Defaults.RESPONSE_BODY_LIMIT) {
            m7691a();
        }
    }

    public void m7692a(float f, float f2) {
        this.f4986d++;
        int[] iArr = this.f4984b;
        int i = this.f4991i;
        this.f4991i = i + 1;
        iArr[i] = Math.round(f * 65536.0f);
        iArr = this.f4984b;
        i = this.f4991i;
        this.f4991i = i + 1;
        iArr[i] = Math.round(f2 * 65536.0f);
        if (this.f4991i >= Defaults.RESPONSE_BODY_LIMIT) {
            m7691a();
        }
    }

    public void m7700a(int[] iArr, int i, int i2) {
        if (this.f4992j == null || this.f4991i + i2 < Defaults.RESPONSE_BODY_LIMIT) {
            System.arraycopy(iArr, i, this.f4984b, this.f4991i, i2);
            this.f4991i += i2;
        } else {
            int i3 = i + i2;
            while (i < i3) {
                int min = Math.min(i3 - i, 1024 - this.f4991i);
                System.arraycopy(iArr, i, this.f4984b, this.f4991i, min);
                i += min;
                this.f4991i = min + this.f4991i;
                m7691a();
            }
        }
        this.f4986d += i2 / 2;
    }

    public final void m7694a(int i, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int[] iArr = this.f4984b;
            int i5 = this.f4991i;
            this.f4991i = i5 + 1;
            iArr[i5] = i;
            iArr = this.f4984b;
            i5 = this.f4991i;
            this.f4991i = i5 + 1;
            iArr[i5] = i2;
            if (this.f4991i >= Defaults.RESPONSE_BODY_LIMIT) {
                m7691a();
            }
        }
        this.f4986d += i3;
    }

    public final void m7699a(int[] iArr) {
        m7700a(iArr, 0, iArr.length);
    }

    public final int m7708g() {
        return this.f4986d;
    }

    private void m7690d() {
        this.f4991i = 0;
        if (this.f4984b == null) {
            int i = this.f4985c * 2;
            if (i < Defaults.RESPONSE_BODY_LIMIT || this.f4993k) {
                this.f4984b = new int[i];
            } else {
                this.f4992j = new IntChunkArray(i);
                m7691a();
            }
        } else if (this.f4992j != null) {
            this.f4992j.m4828a();
            m7691a();
        }
        this.f4986d = 0;
        this.f4987e = null;
    }

    public void m7695a(GLState gLState) {
        m7702b(gLState);
        m7690d();
    }

    public void m7702b(GLState gLState) {
    }

    public final void m7705c(GLState gLState) {
        m7702b(gLState);
        if (this.f4992j != null) {
            this.f4992j.m4832c();
            this.f4992j = null;
        }
        this.f4984b = null;
    }

    public final void m7704c(int i) {
        if (i > this.f4985c) {
            int max = Math.max(i, this.f4985c * 2);
            int i2 = max * 2;
            if (this.f4992j != null) {
                this.f4992j.m4833c(i2);
            } else if (i2 < Defaults.RESPONSE_BODY_LIMIT || this.f4993k) {
                boolean z = this.f4993k;
                Object obj = new int[i2];
                System.arraycopy(this.f4984b, 0, obj, 0, this.f4991i);
                this.f4984b = obj;
            } else {
                this.f4992j = new IntChunkArray(i2);
                this.f4992j.m4829a(this.f4984b, this.f4991i);
                this.f4984b = (int[]) this.f4992j.c;
                this.f4991i = this.f4992j.d;
            }
            this.f4985c = max;
        }
    }

    public void m7706d(GLState gLState) {
        if (this.f4987e == null) {
            m7707e(gLState);
        }
        this.f4987e.position(0);
        this.f4983a = this.f4987e.limit() * this.f4989g;
        gLState.m7541x().glTexCoordPointer(2, this.f4988f, 0, this.f4987e);
    }

    public void m7696a(GLState gLState, int i) {
        if (this.f4987e == null) {
            m7707e(gLState);
        }
        this.f4987e.position(i * 2);
        this.f4983a = (this.f4987e.limit() - i) * this.f4989g;
        gLState.m7541x().glTexCoordPointer(2, this.f4988f, 0, this.f4987e);
    }

    protected final void m7697a(ByteBuffer byteBuffer, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            byteBuffer.put((byte) (this.f4984b[i2] / this.f4990h));
        }
    }

    protected final void m7698a(ShortBuffer shortBuffer, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            shortBuffer.put((short) (this.f4984b[i2] / this.f4990h));
        }
    }

    protected void m7707e(GLState gLState) {
        int i = this.f4986d * 2;
        Buffer a = gLState.m7529l().m7593a(this.f4989g * i);
        a.order(ByteOrder.nativeOrder());
        if (this.f4988f == 5122) {
            this.f4987e = a.asShortBuffer();
            if (this.f4992j == null) {
                m7698a((ShortBuffer) this.f4987e, i);
            } else {
                m7691a();
                this.f4992j.m4843a((ShortBuffer) this.f4987e, this.f4990h);
            }
        } else if (this.f4988f == 5120 || this.f4988f == 5121) {
            this.f4987e = a;
            if (this.f4992j == null) {
                m7697a((ByteBuffer) this.f4987e, i);
            } else {
                m7691a();
                this.f4992j.m4841a((ByteBuffer) this.f4987e, this.f4990h);
            }
        } else {
            this.f4987e = a.asIntBuffer();
            if (this.f4992j == null) {
                ((IntBuffer) this.f4987e).put(this.f4984b, 0, i);
            } else {
                m7691a();
                this.f4992j.m4842a((IntBuffer) this.f4987e);
            }
        }
        if (this.f4992j != null) {
            this.f4992j.m4832c();
            this.f4992j = null;
        }
        this.f4984b = null;
    }

    public final int m7701b() {
        return this.f4983a;
    }

    public int m7703c() {
        int i = 44;
        if (this.f4992j != null) {
            i = (this.f4992j.m4830b() * 4) + 44;
        } else if (this.f4984b != null) {
            i = ((this.f4984b.length * 4) + 16) + 44;
        }
        if (this.f4987e != null) {
            return i + (this.f4987e.capacity() * this.f4989g);
        }
        return i;
    }
}
