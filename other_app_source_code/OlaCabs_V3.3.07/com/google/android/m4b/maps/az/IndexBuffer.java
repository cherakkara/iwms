package com.google.android.m4b.maps.az;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.ab.ShortChunkArray;
import com.google.android.m4b.maps.ay.GLState;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* renamed from: com.google.android.m4b.maps.az.c */
public class IndexBuffer implements IndexBufferInterface {
    protected short[] f4966a;
    int f4967b;
    ShortBuffer f4968c;
    protected int f4969d;
    protected ShortChunkArray f4970e;
    private int f4971f;
    private int f4972g;
    private boolean f4973h;

    public IndexBuffer(int i) {
        this(i, false);
    }

    public IndexBuffer(int i, boolean z) {
        this.f4969d = 0;
        this.f4973h = z;
        this.f4971f = i;
        m7660f();
    }

    protected final void m7661a() {
        if (this.f4970e != null) {
            this.f4970e.m4831b(this.f4972g);
            this.f4966a = (short[]) this.f4970e.c;
            this.f4972g = this.f4970e.d;
        }
    }

    private void m7659e() {
        if (this.f4972g >= AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT) {
            m7661a();
        }
    }

    public final void m7675d(int i) {
        short[] sArr = this.f4966a;
        int i2 = this.f4972g;
        this.f4972g = i2 + 1;
        sArr[i2] = (short) i;
        this.f4967b++;
        if (this.f4972g >= AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT) {
            m7661a();
        }
    }

    public final void m7667a(short s, short s2) {
        short[] sArr = this.f4966a;
        int i = this.f4972g;
        this.f4972g = i + 1;
        sArr[i] = s;
        sArr = this.f4966a;
        i = this.f4972g;
        this.f4972g = i + 1;
        sArr[i] = s2;
        this.f4967b += 2;
        if (this.f4972g >= AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT) {
            m7661a();
        }
    }

    public final void m7668a(short s, short s2, short s3) {
        short[] sArr = this.f4966a;
        int i = this.f4972g;
        this.f4972g = i + 1;
        sArr[i] = s;
        sArr = this.f4966a;
        i = this.f4972g;
        this.f4972g = i + 1;
        sArr[i] = s2;
        sArr = this.f4966a;
        i = this.f4972g;
        this.f4972g = i + 1;
        sArr[i] = s3;
        this.f4967b += 3;
        if (this.f4972g >= AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT) {
            m7661a();
        }
    }

    public final void m7662a(int i, int i2, int i3) {
        short[] sArr = this.f4966a;
        int i4 = this.f4972g;
        this.f4972g = i4 + 1;
        sArr[i4] = (short) i;
        sArr = this.f4966a;
        i4 = this.f4972g;
        this.f4972g = i4 + 1;
        sArr[i4] = (short) i2;
        sArr = this.f4966a;
        i4 = this.f4972g;
        this.f4972g = i4 + 1;
        sArr[i4] = (short) i3;
        this.f4967b += 3;
        if (this.f4972g >= AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT) {
            m7661a();
        }
    }

    public final void m7663a(int i, int i2, int i3, int i4) {
        short[] sArr = this.f4966a;
        int i5 = this.f4972g;
        this.f4972g = i5 + 1;
        sArr[i5] = (short) i;
        sArr = this.f4966a;
        i5 = this.f4972g;
        this.f4972g = i5 + 1;
        sArr[i5] = (short) i2;
        sArr = this.f4966a;
        i5 = this.f4972g;
        this.f4972g = i5 + 1;
        sArr[i5] = (short) i3;
        sArr = this.f4966a;
        i5 = this.f4972g;
        this.f4972g = i5 + 1;
        sArr[i5] = (short) i3;
        sArr = this.f4966a;
        i5 = this.f4972g;
        this.f4972g = i5 + 1;
        sArr[i5] = (short) i2;
        sArr = this.f4966a;
        i5 = this.f4972g;
        this.f4972g = i5 + 1;
        sArr[i5] = (short) i4;
        this.f4967b += 6;
        if (this.f4972g >= AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT) {
            m7661a();
        }
    }

    public final void m7666a(IndexBuffer indexBuffer, int i, int i2) {
        Object obj = 1;
        Object obj2 = (indexBuffer.f4970e == null || indexBuffer.f4967b < AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT) ? 1 : null;
        if (this.f4970e != null && this.f4972g + i2 > AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT) {
            obj = null;
        }
        if (obj2 != null && r2 != null) {
            System.arraycopy(indexBuffer.f4966a, i, this.f4966a, this.f4972g, i2);
            this.f4972g += i2;
            this.f4967b += i2;
            if (this.f4972g >= AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT && this.f4970e != null) {
                m7661a();
            }
        } else if (indexBuffer.f4970e == null) {
            m7658a(indexBuffer.f4966a, i, i2);
        } else {
            indexBuffer.m7659e();
            int i3 = i & 2047;
            int i4 = i >> 11;
            while (i2 > 0) {
                int min = Math.min(2048 - i3, i2);
                m7658a((short[]) indexBuffer.f4970e.m4827a(i4), i3, min);
                i2 -= min;
                i4++;
                i3 = 0;
            }
        }
    }

    private void m7658a(short[] sArr, int i, int i2) {
        if (this.f4970e == null) {
            System.arraycopy(sArr, i, this.f4966a, this.f4972g, i2);
            this.f4972g += i2;
        } else {
            int i3 = i + i2;
            while (i < i3) {
                int min = Math.min(i3 - i, 2048 - this.f4972g);
                System.arraycopy(sArr, i, this.f4966a, this.f4972g, min);
                i += min;
                this.f4972g = min + this.f4972g;
                m7659e();
            }
        }
        this.f4967b += i2;
    }

    public final int m7669b() {
        return this.f4967b;
    }

    private void m7660f() {
        this.f4972g = 0;
        if (this.f4966a == null) {
            if (this.f4971f < AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT || this.f4973h) {
                this.f4966a = new short[this.f4971f];
            } else {
                this.f4970e = new ShortChunkArray(this.f4971f);
                m7661a();
            }
        } else if (this.f4970e != null) {
            this.f4970e.m4828a();
            m7661a();
        }
        this.f4967b = 0;
        this.f4968c = null;
    }

    public void m7664a(GLState gLState) {
        m7671b(gLState);
        m7660f();
    }

    public void m7671b(GLState gLState) {
    }

    public final void m7673c(GLState gLState) {
        m7671b(gLState);
        if (this.f4970e != null) {
            this.f4970e.m4832c();
            this.f4970e = null;
        }
        this.f4966a = null;
    }

    public final void m7670b(int i) {
        if (i > this.f4971f) {
            int max = Math.max(i, this.f4971f * 2);
            if (this.f4970e != null) {
                this.f4970e.m4833c(max);
            } else if (max < AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT || this.f4973h) {
                boolean z = this.f4973h;
                Object obj = new short[max];
                System.arraycopy(this.f4966a, 0, obj, 0, this.f4972g);
                this.f4966a = obj;
            } else {
                this.f4970e = new ShortChunkArray(max);
                this.f4970e.m4829a(this.f4966a, this.f4972g);
                this.f4966a = (short[]) this.f4970e.c;
                this.f4972g = this.f4970e.d;
            }
            this.f4971f = max;
        }
    }

    public void m7665a(GLState gLState, int i) {
        if (this.f4968c == null) {
            m7676d(gLState);
        }
        this.f4969d = this.f4968c.limit() * 2;
        gLState.m7541x().glDrawElements(i, this.f4967b, 5123, this.f4968c);
    }

    protected void m7676d(GLState gLState) {
        ByteBuffer a = gLState.m7529l().m7593a(this.f4967b * 2);
        a.order(ByteOrder.nativeOrder());
        this.f4968c = a.asShortBuffer();
        if (this.f4970e == null) {
            this.f4968c.put(this.f4966a, 0, this.f4967b);
        } else {
            m7661a();
            this.f4970e.m4845a(this.f4968c);
            this.f4970e.m4832c();
            this.f4970e = null;
        }
        this.f4968c.position(0);
        this.f4966a = null;
    }

    public final int m7672c() {
        return this.f4969d;
    }

    public int m7674d() {
        int i = 32;
        if (this.f4970e != null) {
            i = (this.f4970e.m4830b() * 2) + 32;
        } else if (this.f4966a != null) {
            i = ((this.f4966a.length * 2) + 16) + 32;
        }
        if (this.f4968c != null) {
            return i + (this.f4968c.capacity() * 2);
        }
        return i;
    }
}
