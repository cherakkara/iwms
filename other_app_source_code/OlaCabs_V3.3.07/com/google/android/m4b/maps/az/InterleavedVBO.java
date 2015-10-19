package com.google.android.m4b.maps.az;

import com.google.android.m4b.maps.ay.GLState;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.microedition.khronos.opengles.GL11;

/* renamed from: com.google.android.m4b.maps.az.f */
public class InterleavedVBO {
    private ByteBuffer f4976a;
    private final boolean f4977b;
    private final boolean f4978c;
    private int f4979d;
    private final int[] f4980e;
    private boolean f4981f;
    private int f4982g;

    /* renamed from: com.google.android.m4b.maps.az.f.a */
    public static final class InterleavedVBO extends InterleavedVBO {
        public InterleavedVBO(float[] fArr, int i) {
            super(9);
            ByteBuffer order = ByteBuffer.allocateDirect(80).order(ByteOrder.nativeOrder());
            order.asFloatBuffer().put(fArr);
            super.m7684a(order);
        }

        public final void m7688a(ByteBuffer byteBuffer) {
            throw new UnsupportedOperationException("Immutable");
        }

        public final void m7689b(GLState gLState) {
            throw new UnsupportedOperationException("Immutable");
        }
    }

    public InterleavedVBO(int i) {
        boolean z = true;
        this.f4980e = new int[1];
        this.f4981f = false;
        this.f4977b = (i & 8) != 0;
        if ((i & 4) == 0) {
            z = false;
        }
        this.f4978c = z;
        this.f4979d = 12;
        if (this.f4978c) {
            this.f4979d += 16;
        }
        if (this.f4977b) {
            this.f4979d += 8;
        }
    }

    public void m7684a(ByteBuffer byteBuffer) {
        this.f4976a = byteBuffer;
        this.f4981f = true;
        this.f4982g = this.f4976a.capacity() / this.f4979d;
    }

    public final int m7682a() {
        return this.f4982g;
    }

    public final void m7683a(GLState gLState) {
        int i;
        GL11 gl11 = (GL11) gLState.m7541x();
        if (this.f4980e[0] == 0) {
            GL11 gl112 = (GL11) gLState.m7541x();
            gl112.glGenBuffers(1, this.f4980e, 0);
            gl112.glBindBuffer(34962, this.f4980e[0]);
        } else {
            gl11.glBindBuffer(34962, this.f4980e[0]);
        }
        if (this.f4981f) {
            this.f4976a.rewind();
            gl11.glBufferData(34962, this.f4976a.capacity(), this.f4976a, 35044);
            this.f4981f = false;
        }
        gl11.glVertexPointer(3, 5126, this.f4979d, 0);
        if (this.f4978c) {
            gl11.glColorPointer(4, 5126, this.f4979d, 12);
            i = 28;
        } else {
            i = 12;
        }
        if (this.f4977b) {
            gl11.glTexCoordPointer(2, 5126, this.f4979d, i);
        }
        gl11.glBindBuffer(34962, 0);
    }

    public void m7685b(GLState gLState) {
        m7687d(gLState);
        this.f4976a = null;
    }

    public final void m7686c(GLState gLState) {
        m7687d(gLState);
    }

    public final void m7687d(GLState gLState) {
        if (this.f4980e[0] != 0) {
            if (gLState != null) {
                ((GL11) gLState.m7541x()).glDeleteBuffers(1, this.f4980e, 0);
            }
            this.f4980e[0] = 0;
            if (this.f4976a != null) {
                this.f4976a.clear();
            }
        }
    }
}
