package com.google.android.m4b.maps.az;

import android.support.v4.view.MotionEventCompat;
import com.google.android.m4b.maps.ab.ByteChunkArray;
import com.google.android.m4b.maps.ay.GLState;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.nio.ByteBuffer;

/* renamed from: com.google.android.m4b.maps.az.a */
public class ColorBuffer {
    protected byte[] f4955a;
    protected int f4956b;
    ByteBuffer f4957c;
    protected int f4958d;
    protected ByteChunkArray f4959e;
    private int f4960f;
    private int f4961g;
    private boolean f4962h;
    private int f4963i;

    /* renamed from: com.google.android.m4b.maps.az.a.1 */
    class ColorBuffer implements ByteChunkArray.ByteChunkArray {
        private /* synthetic */ ColorBuffer f4954a;

        ColorBuffer(ColorBuffer colorBuffer) {
            this.f4954a = colorBuffer;
        }

        public final void m7640a(byte[] bArr, int i) {
            this.f4954a.m7642a(bArr, i);
        }
    }

    public ColorBuffer(int i) {
        this(i, false);
    }

    public ColorBuffer(int i, boolean z) {
        this.f4958d = 0;
        this.f4963i = 0;
        this.f4962h = z;
        this.f4960f = i;
        this.f4961g = 0;
        if (this.f4955a == null) {
            int i2 = this.f4960f * 4;
            if (i2 < AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH || this.f4962h) {
                this.f4955a = new byte[i2];
            } else {
                this.f4959e = new ByteChunkArray(i2);
                m7643c();
            }
        } else if (this.f4959e != null) {
            this.f4959e.m4828a();
            m7643c();
        }
        this.f4956b = 0;
        this.f4957c = null;
    }

    private void m7643c() {
        if (this.f4959e != null) {
            this.f4959e.m4831b(this.f4961g);
            this.f4955a = (byte[]) this.f4959e.c;
            this.f4961g = this.f4959e.d;
        }
    }

    public final void m7646a(int i, int i2) {
        byte b = (byte) (i >>> 24);
        byte b2 = (byte) (i >>> 16);
        byte b3 = (byte) (i >>> 8);
        byte b4 = (byte) i;
        for (int i3 = 0; i3 < i2; i3++) {
            byte[] bArr = this.f4955a;
            int i4 = this.f4961g;
            this.f4961g = i4 + 1;
            bArr[i4] = b;
            bArr = this.f4955a;
            i4 = this.f4961g;
            this.f4961g = i4 + 1;
            bArr[i4] = b2;
            bArr = this.f4955a;
            i4 = this.f4961g;
            this.f4961g = i4 + 1;
            bArr[i4] = b3;
            bArr = this.f4955a;
            i4 = this.f4961g;
            this.f4961g = i4 + 1;
            bArr[i4] = b4;
            if (this.f4961g >= AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH) {
                m7643c();
            }
        }
        this.f4956b += i2;
    }

    public final void m7650b(int i, int i2) {
        byte b = (byte) (i >>> 24);
        byte b2 = (byte) (i >>> 16);
        byte b3 = (byte) (i >>> 8);
        byte b4 = (byte) i;
        for (int i3 = 0; i3 < i2; i3++) {
            byte[] bArr = this.f4955a;
            int i4 = this.f4961g;
            this.f4961g = i4 + 1;
            bArr[i4] = b2;
            bArr = this.f4955a;
            i4 = this.f4961g;
            this.f4961g = i4 + 1;
            bArr[i4] = b3;
            bArr = this.f4955a;
            i4 = this.f4961g;
            this.f4961g = i4 + 1;
            bArr[i4] = b4;
            bArr = this.f4955a;
            i4 = this.f4961g;
            this.f4961g = i4 + 1;
            bArr[i4] = b;
            if (this.f4961g >= AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH) {
                m7643c();
            }
        }
        this.f4956b += i2;
    }

    public void m7648a(GLState gLState) {
    }

    public final void m7651b(GLState gLState) {
        m7648a(gLState);
        if (this.f4959e != null) {
            this.f4959e.m4832c();
            this.f4959e = null;
        }
        this.f4955a = null;
    }

    public final void m7645a(int i) {
        if (i > this.f4960f) {
            int max = Math.max(i, this.f4960f * 2);
            int i2 = max * 4;
            if (this.f4959e != null) {
                this.f4959e.m4833c(i2);
            } else if (i2 < AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH || this.f4962h) {
                boolean z = this.f4962h;
                Object obj = new byte[i2];
                System.arraycopy(this.f4955a, 0, obj, 0, this.f4961g);
                this.f4955a = obj;
            } else {
                this.f4959e = new ByteChunkArray(i2);
                this.f4959e.m4829a(this.f4955a, this.f4961g);
                this.f4955a = (byte[]) this.f4959e.c;
                this.f4961g = this.f4959e.d;
            }
            this.f4960f = max;
        }
    }

    public void m7652c(GLState gLState) {
        if (this.f4957c == null) {
            m7653d(gLState);
        }
        this.f4958d = this.f4957c.limit();
        gLState.m7541x().glColorPointer(4, 5121, 0, this.f4957c);
    }

    protected void m7653d(GLState gLState) {
        int i = this.f4956b * 4;
        this.f4957c = gLState.m7529l().m7593a(i);
        m7647a(i, true);
    }

    private void m7642a(byte[] bArr, int i) {
        int i2 = 0;
        if (this.f4963i > 0) {
            while (i2 < i) {
                int i3 = bArr[i2] & MotionEventCompat.ACTION_MASK;
                this.f4957c.put((byte) (((i3 * (255 - this.f4963i)) / MotionEventCompat.ACTION_MASK) + this.f4963i));
                i2++;
            }
            return;
        }
        this.f4957c.put(bArr, 0, i);
    }

    protected final void m7647a(int i, boolean z) {
        if (this.f4959e == null) {
            m7642a(this.f4955a, i);
        } else {
            m7643c();
            this.f4959e.m4839a(new ColorBuffer(this));
        }
        this.f4957c.limit(i);
        this.f4957c.position(0);
        if (z) {
            if (this.f4959e != null) {
                this.f4959e.m4832c();
                this.f4959e = null;
            }
            this.f4955a = null;
        }
    }

    public final int m7644a() {
        return this.f4958d;
    }

    public int m7649b() {
        int i = 32;
        if (this.f4959e != null) {
            i = this.f4959e.m4830b() + 32;
        } else if (this.f4955a != null) {
            i = (this.f4955a.length + 16) + 32;
        }
        if (this.f4957c != null) {
            return i + this.f4957c.capacity();
        }
        return i;
    }
}
