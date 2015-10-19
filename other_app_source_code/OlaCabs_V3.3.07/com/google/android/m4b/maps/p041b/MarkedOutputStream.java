package com.google.android.m4b.maps.p041b;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.OutputStream;

/* renamed from: com.google.android.m4b.maps.b.h */
public final class MarkedOutputStream extends OutputStream {
    private byte[] f5022a;
    private int[] f5023b;
    private int f5024c;
    private int f5025d;
    private int f5026e;

    public MarkedOutputStream() {
        this.f5025d = 0;
        this.f5026e = 0;
        this.f5025d = 0;
        this.f5026e = 0;
        this.f5024c = 0;
        this.f5022a = new byte[16];
        this.f5023b = new int[1];
    }

    private static int m7774a(int i, int i2, int i3, int i4) {
        int i5 = i2 - i3;
        while (i5 < i) {
            i5 = i2 >> i4;
            if (i5 <= 0) {
                throw new IllegalArgumentException("Incorrect size [" + i2 + "] and rightShiftAmount [" + i4 + "]");
            }
            i2 += i5;
            i5 = i2 - i3;
        }
        return i2;
    }

    public final int m7777a(int i) {
        return this.f5023b[i];
    }

    public final int m7776a() {
        return this.f5026e;
    }

    public final void m7781b(int i) {
        int a = MarkedOutputStream.m7774a(1, this.f5023b.length, this.f5026e, 0);
        if (a > this.f5023b.length) {
            Object obj = new int[a];
            System.arraycopy(this.f5023b, 0, obj, 0, this.f5026e);
            this.f5023b = obj;
        }
        int[] iArr = this.f5023b;
        int i2 = this.f5026e;
        this.f5026e = i2 + 1;
        iArr[i2] = i;
    }

    public final void m7778a(int i, int i2) {
        this.f5023b[i] = i2;
    }

    private void m7775c(int i) {
        if (this.f5022a.length - i >= this.f5025d) {
            this.f5024c++;
            return;
        }
        int i2;
        if (this.f5024c >= 64) {
            i2 = 1;
            if (this.f5024c >= AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                i2 = 2;
            }
        } else {
            i2 = 0;
        }
        i2 = MarkedOutputStream.m7774a(i, this.f5022a.length, this.f5025d, i2);
        if (i2 > this.f5022a.length) {
            Object obj = new byte[i2];
            System.arraycopy(this.f5022a, 0, obj, 0, this.f5025d);
            this.f5022a = obj;
            this.f5024c = 0;
        }
    }

    public final int m7780b() {
        return this.f5025d;
    }

    public final void m7779a(OutputStream outputStream, int i, int i2) {
        outputStream.write(this.f5022a, i, i2);
    }

    public final void write(int i) {
        m7775c(1);
        byte[] bArr = this.f5022a;
        int i2 = this.f5025d;
        this.f5025d = i2 + 1;
        bArr[i2] = (byte) (i & MotionEventCompat.ACTION_MASK);
    }

    public final void write(byte[] bArr) {
        m7775c(bArr.length);
        System.arraycopy(bArr, 0, this.f5022a, this.f5025d, bArr.length);
        this.f5025d += bArr.length;
    }

    public final void write(byte[] bArr, int i, int i2) {
        m7775c(i2);
        System.arraycopy(bArr, i, this.f5022a, this.f5025d, i2);
        this.f5025d += i2;
    }
}
