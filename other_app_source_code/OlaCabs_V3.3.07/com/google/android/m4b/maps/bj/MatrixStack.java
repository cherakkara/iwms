package com.google.android.m4b.maps.bj;

import android.opengl.Matrix;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* renamed from: com.google.android.m4b.maps.bj.z */
public final class MatrixStack {
    private final float[] f6670a;
    private int f6671b;
    private final float[] f6672c;

    public MatrixStack() {
        this(32);
    }

    private MatrixStack(int i) {
        this.f6670a = new float[AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY];
        this.f6672c = new float[32];
        m10035a();
    }

    public final void m10038a(float f, float f2, float f3, float f4, float f5, float f6) {
        Matrix.frustumM(this.f6670a, this.f6671b, f, f2, f3, f4, f5, f6);
    }

    public final void m10041a(int i, int i2, int i3, int i4, int i5, int i6) {
        m10038a(((float) i) * 1.5258789E-5f, ((float) i2) * 1.5258789E-5f, ((float) i3) * 1.5258789E-5f, ((float) i4) * 1.5258789E-5f, ((float) i5) * 1.5258789E-5f, 1.5258789E-5f * ((float) i6));
    }

    public final void m10035a() {
        Matrix.setIdentityM(this.f6670a, this.f6671b);
    }

    public final void m10044a(float[] fArr, int i) {
        System.arraycopy(fArr, i, this.f6670a, this.f6671b, 16);
    }

    public final void m10042a(FloatBuffer floatBuffer) {
        floatBuffer.get(this.f6670a, this.f6671b, 16);
    }

    public final void m10045a(int[] iArr, int i) {
        for (int i2 = 0; i2 < 16; i2++) {
            this.f6670a[this.f6671b + i2] = ((float) iArr[i + i2]) * 1.5258789E-5f;
        }
    }

    public final void m10043a(IntBuffer intBuffer) {
        for (int i = 0; i < 16; i++) {
            this.f6670a[this.f6671b + i] = ((float) intBuffer.get()) * 1.5258789E-5f;
        }
    }

    public final void m10053b(float[] fArr, int i) {
        System.arraycopy(this.f6670a, this.f6671b, this.f6672c, 0, 16);
        Matrix.multiplyMM(this.f6670a, this.f6671b, this.f6672c, 0, fArr, i);
    }

    public final void m10051b(FloatBuffer floatBuffer) {
        floatBuffer.get(this.f6672c, 16, 16);
        m10053b(this.f6672c, 16);
    }

    public final void m10054b(int[] iArr, int i) {
        for (int i2 = 0; i2 < 16; i2++) {
            this.f6672c[i2 + 16] = ((float) iArr[i + i2]) * 1.5258789E-5f;
        }
        m10053b(this.f6672c, 16);
    }

    public final void m10052b(IntBuffer intBuffer) {
        for (int i = 0; i < 16; i++) {
            this.f6672c[i + 16] = ((float) intBuffer.get()) * 1.5258789E-5f;
        }
        m10053b(this.f6672c, 16);
    }

    public final void m10048b(float f, float f2, float f3, float f4, float f5, float f6) {
        Matrix.orthoM(this.f6670a, this.f6671b, f, f2, f3, f4, f5, f6);
    }

    public final void m10050b(int i, int i2, int i3, int i4, int i5, int i6) {
        m10048b(((float) i) * 1.5258789E-5f, ((float) i2) * 1.5258789E-5f, ((float) i3) * 1.5258789E-5f, ((float) i4) * 1.5258789E-5f, ((float) i5) * 1.5258789E-5f, 1.5258789E-5f * ((float) i6));
    }

    public final void m10046b() {
        m10033a(-1);
        m10034b(-1);
    }

    public final void m10055c() {
        m10033a(1);
        System.arraycopy(this.f6670a, this.f6671b, this.f6670a, this.f6671b + 16, 16);
        m10034b(1);
    }

    public final void m10037a(float f, float f2, float f3, float f4) {
        Matrix.setRotateM(this.f6672c, 0, f, f2, f3, f4);
        System.arraycopy(this.f6670a, this.f6671b, this.f6672c, 16, 16);
        Matrix.multiplyMM(this.f6670a, this.f6671b, this.f6672c, 16, this.f6672c, 0);
    }

    public final void m10040a(int i, int i2, int i3, int i4) {
        m10037a((float) i, ((float) i2) * 1.5258789E-5f, ((float) i3) * 1.5258789E-5f, ((float) i4) * 1.5258789E-5f);
    }

    public final void m10036a(float f, float f2, float f3) {
        Matrix.scaleM(this.f6670a, this.f6671b, f, f2, f3);
    }

    public final void m10039a(int i, int i2, int i3) {
        m10036a(((float) i) * 1.5258789E-5f, ((float) i2) * 1.5258789E-5f, ((float) i3) * 1.5258789E-5f);
    }

    public final void m10047b(float f, float f2, float f3) {
        Matrix.translateM(this.f6670a, this.f6671b, f, f2, f3);
    }

    public final void m10049b(int i, int i2, int i3) {
        m10047b(((float) i) * 1.5258789E-5f, ((float) i2) * 1.5258789E-5f, ((float) i3) * 1.5258789E-5f);
    }

    public final void m10056c(float[] fArr, int i) {
        System.arraycopy(this.f6670a, this.f6671b, fArr, i, 16);
    }

    private void m10033a(int i) {
        int i2 = this.f6671b + (i * 16);
        if (i2 < 0) {
            throw new IllegalArgumentException("stack underflow");
        } else if (i2 + 16 > this.f6670a.length) {
            throw new IllegalArgumentException("stack overflow");
        }
    }

    private void m10034b(int i) {
        this.f6671b += i * 16;
    }
}
