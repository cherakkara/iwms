package com.google.android.m4b.maps.az;

import com.google.android.m4b.maps.ab.IntChunkArray;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.ay.GLState;
import com.newrelic.agent.android.api.v1.Defaults;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/* renamed from: com.google.android.m4b.maps.az.k */
public class VertexBuffer implements VertexBufferInterface {
    protected int f4999a;
    int[] f5000b;
    int f5001c;
    int f5002d;
    IntBuffer f5003e;
    int f5004f;
    protected IntChunkArray f5005g;
    private boolean f5006h;

    public VertexBuffer(int i) {
        this(i, false);
    }

    public VertexBuffer(int i, boolean z) {
        this.f4999a = 0;
        this.f5006h = z;
        this.f5001c = i;
        m7724e();
    }

    VertexBuffer() {
        this.f4999a = 0;
    }

    protected final void m7731b() {
        if (this.f5005g != null) {
            this.f5005g.m4831b(this.f5004f);
            this.f5000b = (int[]) this.f5005g.c;
            this.f5004f = this.f5005g.d;
        }
    }

    public void m7728a(Point point, int i) {
        this.f5002d++;
        point.m5946a(i, this.f5000b, this.f5004f);
        this.f5004f += 3;
        if (this.f5004f >= Defaults.RESPONSE_BODY_LIMIT) {
            m7731b();
        }
    }

    public final void m7729a(Point point, int i, byte b) {
        m7728a(point, i);
    }

    public void m7726a(float f, float f2, float f3) {
        this.f5002d++;
        int[] iArr = this.f5000b;
        int i = this.f5004f;
        this.f5004f = i + 1;
        iArr[i] = (int) (f * 65536.0f);
        iArr = this.f5000b;
        i = this.f5004f;
        this.f5004f = i + 1;
        iArr[i] = (int) (f2 * 65536.0f);
        iArr = this.f5000b;
        i = this.f5004f;
        this.f5004f = i + 1;
        iArr[i] = (int) (f3 * 65536.0f);
        if (this.f5004f >= Defaults.RESPONSE_BODY_LIMIT) {
            m7731b();
        }
    }

    public final int m7725a() {
        return this.f5002d;
    }

    private void m7724e() {
        this.f5004f = 0;
        if (this.f5000b == null) {
            int i = this.f5001c * 3;
            if (i < Defaults.RESPONSE_BODY_LIMIT || this.f5006h) {
                this.f5000b = new int[i];
            } else {
                this.f5005g = new IntChunkArray(i);
                m7731b();
            }
        } else if (this.f5005g != null) {
            this.f5005g.m4828a();
            m7731b();
        }
        this.f5002d = 0;
        this.f5003e = null;
    }

    public void m7730a(GLState gLState) {
        m7732b(gLState);
        m7724e();
    }

    public void m7732b(GLState gLState) {
    }

    public final void m7734c(GLState gLState) {
        m7732b(gLState);
        if (this.f5005g != null) {
            this.f5005g.m4832c();
            this.f5005g = null;
        }
        this.f5000b = null;
    }

    public final void m7727a(int i) {
        if (i > this.f5001c) {
            int max = Math.max(i, this.f5001c * 2);
            int i2 = max * 3;
            if (this.f5005g != null) {
                this.f5005g.m4833c(i2);
            } else if (i2 < Defaults.RESPONSE_BODY_LIMIT || this.f5006h) {
                boolean z = this.f5006h;
                Object obj = new int[i2];
                System.arraycopy(this.f5000b, 0, obj, 0, this.f5004f);
                this.f5000b = obj;
            } else {
                this.f5005g = new IntChunkArray(i2);
                this.f5005g.m4829a(this.f5000b, this.f5004f);
                this.f5000b = (int[]) this.f5005g.c;
                this.f5004f = this.f5005g.d;
            }
            this.f5001c = max;
        }
    }

    public void m7736d(GLState gLState) {
        synchronized (this) {
            if (this.f5003e == null) {
                m7737e(gLState);
            }
        }
        this.f4999a = this.f5003e.limit() * 4;
        gLState.m7541x().glVertexPointer(3, 5132, 0, this.f5003e);
    }

    protected void m7737e(GLState gLState) {
        int i = this.f5002d * 3;
        ByteBuffer a = gLState.m7529l().m7593a(i * 4);
        a.order(ByteOrder.nativeOrder());
        this.f5003e = a.asIntBuffer();
        if (this.f5005g == null) {
            this.f5003e.put(this.f5000b, 0, i);
        } else {
            m7731b();
            this.f5005g.m4842a(this.f5003e);
            this.f5005g.m4832c();
            this.f5005g = null;
        }
        this.f5003e.position(0);
        this.f5000b = null;
    }

    public final int m7733c() {
        return this.f4999a;
    }

    public int m7735d() {
        int i = 32;
        if (this.f5005g != null) {
            i = (this.f5005g.m4830b() * 4) + 32;
        } else if (this.f5000b != null) {
            i = ((this.f5000b.length * 4) + 16) + 32;
        }
        if (this.f5003e != null) {
            return i + (this.f5003e.capacity() * 4);
        }
        return i;
    }
}
