package com.google.android.m4b.maps.bj;

import android.opengl.Visibility;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.bj.o */
final class Grid {
    private final FloatBuffer f6569a;
    private final float[] f6570b;
    private final FloatBuffer f6571c;
    private final CharBuffer f6572d;
    private final char[] f6573e;
    private final int f6574f;
    private final int f6575g;
    private final int f6576h;

    public Grid(int i, int i2) {
        if (i < 2 || i >= AccessibilityNodeInfoCompat.ACTION_CUT) {
            throw new IllegalArgumentException("w");
        } else if (i2 < 2 || i2 >= AccessibilityNodeInfoCompat.ACTION_CUT) {
            throw new IllegalArgumentException("h");
        } else if (i * i2 >= AccessibilityNodeInfoCompat.ACTION_CUT) {
            throw new IllegalArgumentException("w * h >= 65536");
        } else {
            this.f6574f = i;
            this.f6575g = i2;
            int i3 = i * i2;
            this.f6570b = new float[(i3 * 3)];
            this.f6569a = ByteBuffer.allocateDirect((i3 * 4) * 3).order(ByteOrder.nativeOrder()).asFloatBuffer();
            this.f6571c = ByteBuffer.allocateDirect((i3 * 4) * 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
            int i4 = this.f6574f - 1;
            int i5 = this.f6575g - 1;
            i3 = (i4 * i5) * 6;
            this.f6576h = i3;
            this.f6573e = new char[i3];
            this.f6572d = ByteBuffer.allocateDirect(i3 * 2).order(ByteOrder.nativeOrder()).asCharBuffer();
            int i6 = 0;
            i3 = 0;
            while (i6 < i5) {
                int i7 = i3;
                for (i3 = 0; i3 < i4; i3++) {
                    int i8 = (this.f6574f * i6) + i3;
                    char c = (char) (i8 + 1);
                    char c2 = (char) (this.f6574f + i8);
                    char c3 = (char) ((this.f6574f + i8) + 1);
                    int i9 = i7 + 1;
                    this.f6573e[i7] = (char) i8;
                    i8 = i9 + 1;
                    this.f6573e[i9] = c;
                    int i10 = i8 + 1;
                    this.f6573e[i8] = c2;
                    i8 = i10 + 1;
                    this.f6573e[i10] = c;
                    int i11 = i8 + 1;
                    this.f6573e[i8] = c2;
                    i7 = i11 + 1;
                    this.f6573e[i11] = c3;
                }
                i6++;
                i3 = i7;
            }
            this.f6572d.position(0);
            this.f6572d.put(this.f6573e);
            this.f6572d.position(0);
        }
    }

    final void m9977a(int i, int i2, float f, float f2, float f3, float f4, float f5) {
        if (i < 0 || i >= this.f6574f) {
            throw new IllegalArgumentException("i");
        } else if (i2 < 0 || i2 >= this.f6575g) {
            throw new IllegalArgumentException("j");
        } else {
            int i3 = (this.f6574f * i2) + i;
            int i4 = i3 * 3;
            this.f6570b[i4] = f;
            this.f6570b[i4 + 1] = f2;
            this.f6570b[i4 + 2] = f3;
            this.f6569a.put(i4, f);
            this.f6569a.put(i4 + 1, f2);
            this.f6569a.put(i4 + 2, f3);
            i3 *= 2;
            this.f6571c.put(i3, f4);
            this.f6571c.put(i3 + 1, f5);
        }
    }

    public final void m9979a(float[] fArr, int i) {
        Visibility.computeBoundingSphere(this.f6570b, 0, this.f6574f * this.f6575g, fArr, i);
    }

    public final void m9978a(GL10 gl10, boolean z) {
        gl10.glEnableClientState(32884);
        gl10.glVertexPointer(3, 5126, 0, this.f6569a);
        if (z) {
            gl10.glEnableClientState(32888);
            gl10.glTexCoordPointer(2, 5126, 0, this.f6571c);
            gl10.glEnable(3553);
        } else {
            gl10.glDisableClientState(32888);
            gl10.glDisable(3553);
        }
        gl10.glDrawElements(4, this.f6576h, 5123, this.f6572d);
    }

    public final int m9976a(VisibilityTester visibilityTester) {
        return visibilityTester.m9946a(this.f6570b, 0, this.f6573e, 0, this.f6573e.length);
    }
}
