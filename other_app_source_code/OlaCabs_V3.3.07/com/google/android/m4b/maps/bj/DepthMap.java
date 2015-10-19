package com.google.android.m4b.maps.bj;

import android.support.v4.view.MotionEventCompat;
import com.google.android.m4b.maps.be.ar;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

/* renamed from: com.google.android.m4b.maps.bj.k */
public final class DepthMap {
    private final byte[] f6526a;
    private final byte[] f6527b;
    private byte[] f6528c;
    private DepthMap[] f6529d;
    private byte[] f6530e;
    private String[] f6531f;
    private DepthMap[] f6532g;
    private int f6533h;
    private int f6534i;
    private boolean f6535j;

    /* renamed from: com.google.android.m4b.maps.bj.k.a */
    public static class DepthMap {
        public final float f6520a;
        public final float f6521b;
        public final float f6522c;
        private float f6523d;

        public DepthMap(float f, float f2, float f3, float f4) {
            this.f6520a = f;
            this.f6521b = f2;
            this.f6522c = f3;
            this.f6523d = f4;
        }

        public final float m9948a(float f, float f2, float f3) {
            return this.f6523d / (((this.f6520a * f) + (this.f6521b * f2)) + (this.f6522c * f3));
        }

        public final boolean m9949a() {
            return Math.abs(this.f6522c) >= 0.9f;
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.k.b */
    public static class DepthMap {
        public final float f6524a;
        public final float f6525b;

        public DepthMap(float f, float f2) {
            this.f6524a = f;
            this.f6525b = f2;
        }
    }

    public DepthMap(byte[] bArr, byte[] bArr2) {
        this.f6526a = bArr;
        if (m9955f()) {
            this.f6527b = bArr2;
            if (m9956g()) {
                this.f6535j = true;
                return;
            }
            throw new IOException();
        }
        throw new IOException();
    }

    protected DepthMap() {
        this.f6526a = null;
        this.f6527b = null;
    }

    public final boolean m9960a() {
        if (this.f6535j) {
            return false;
        }
        m9955f();
        m9956g();
        this.f6535j = true;
        return true;
    }

    public final boolean m9963b() {
        if (!this.f6535j) {
            return false;
        }
        this.f6528c = null;
        this.f6529d = null;
        this.f6530e = null;
        this.f6531f = null;
        this.f6532g = null;
        this.f6535j = false;
        return true;
    }

    private void m9954e() {
        if (!this.f6535j) {
            throw new IllegalArgumentException("Depth map must be decompressed");
        }
    }

    public final DepthMap m9958a(float f, float f2) {
        m9954e();
        m9954e();
        int i = this.f6528c[m9950a(f) + (m9953b(f2) * this.f6533h)] & MotionEventCompat.ACTION_MASK;
        if (i != 0) {
            return this.f6529d[i];
        }
        return null;
    }

    public final float m9957a(float f, float f2, float[] fArr) {
        m9954e();
        DepthMap a = m9958a(f, f2);
        if (a == null) {
            return 0.0f;
        }
        if (fArr != null && fArr.length >= 3) {
            fArr[0] = a.f6520a;
            fArr[1] = -a.f6522c;
            fArr[2] = a.f6521b;
        }
        float[] fArr2 = new float[3];
        ar.m8597a(f, f2, fArr2, 0);
        return a.m9948a(fArr2[0], -fArr2[2], fArr2[1]);
    }

    public final String m9962b(float f, float f2, float[] fArr) {
        m9954e();
        int i = this.f6530e[m9950a(f) + (m9953b(f2) * this.f6533h)] & MotionEventCompat.ACTION_MASK;
        if (i > 0) {
            DepthMap a = m9958a(f, f2);
            float[] fArr2 = new float[3];
            ar.m8597a(f, f2, fArr2, 0);
            float a2 = a.m9948a(fArr2[0], -fArr2[2], fArr2[1]);
            for (int i2 = 0; i2 < 3; i2++) {
                fArr2[i2] = fArr2[i2] * a2;
            }
            ar.m8596a(fArr2[0] - this.f6532g[i].f6524a, fArr2[1], fArr2[2] + this.f6532g[i].f6525b, fArr);
        }
        return this.f6531f[i];
    }

    public final DepthMap m9961b(float f, float f2) {
        m9954e();
        int i = this.f6530e[m9950a(f) + (m9953b(f2) * this.f6533h)] & MotionEventCompat.ACTION_MASK;
        m9954e();
        return this.f6532g[i];
    }

    public final boolean m9964c(float f, float f2) {
        m9954e();
        DepthMap a = m9958a(f, f2);
        return a == null ? false : a.m9949a();
    }

    private int m9950a(float f) {
        return DepthMap.m9951a(((float) this.f6533h) * f, this.f6533h);
    }

    private int m9953b(float f) {
        return DepthMap.m9951a((br.DEFAULT_BACKOFF_MULT - (dm.DEFAULT_BACKOFF_MULT * f)) * ((float) this.f6534i), this.f6534i);
    }

    private static int m9951a(float f, int i) {
        int i2 = (int) f;
        if (i2 >= i) {
            return i2 - i;
        }
        if (i2 < 0) {
            return i2 + i;
        }
        return i2;
    }

    private boolean m9955f() {
        if (this.f6526a == null) {
            return false;
        }
        LEDataInputStream a = DepthMap.m9952a(this.f6526a);
        try {
            if (a.readUnsignedByte() != 8) {
                return false;
            }
            int readUnsignedShort = a.readUnsignedShort();
            this.f6533h = a.readUnsignedShort();
            this.f6534i = a.readUnsignedShort();
            if (a.readUnsignedByte() != 8) {
                return false;
            }
            this.f6528c = new byte[(this.f6533h * this.f6534i)];
            a.readFully(this.f6528c);
            this.f6529d = new DepthMap[readUnsignedShort];
            for (int i = 0; i < readUnsignedShort; i++) {
                this.f6529d[i] = new DepthMap(a.readFloat(), a.readFloat(), a.readFloat(), a.readFloat());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean m9956g() {
        if (this.f6527b == null) {
            return false;
        }
        LEDataInputStream a = DepthMap.m9952a(this.f6527b);
        try {
            if (a.readUnsignedByte() != 8) {
                return false;
            }
            int readUnsignedShort = a.readUnsignedShort();
            int readUnsignedShort2 = a.readUnsignedShort();
            int readUnsignedShort3 = a.readUnsignedShort();
            if (this.f6533h != readUnsignedShort2 || this.f6534i != readUnsignedShort3 || a.readUnsignedByte() != 8) {
                return false;
            }
            this.f6530e = new byte[(this.f6533h * this.f6534i)];
            a.readFully(this.f6530e);
            this.f6531f = new String[readUnsignedShort];
            this.f6531f[0] = null;
            byte[] bArr = new byte[22];
            for (readUnsignedShort2 = 1; readUnsignedShort2 < readUnsignedShort; readUnsignedShort2++) {
                a.readFully(bArr);
                this.f6531f[readUnsignedShort2] = new String(bArr);
            }
            this.f6532g = new DepthMap[readUnsignedShort];
            this.f6532g[0] = null;
            for (readUnsignedShort2 = 1; readUnsignedShort2 < readUnsignedShort; readUnsignedShort2++) {
                this.f6532g[readUnsignedShort2] = new DepthMap(a.readFloat(), a.readFloat());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static LEDataInputStream m9952a(byte[] bArr) {
        return new LEDataInputStream(new InflaterInputStream(new ByteArrayInputStream(bArr)));
    }

    public final byte[] m9965c() {
        return this.f6526a;
    }

    public final byte[] m9966d() {
        return this.f6527b;
    }

    public final DepthMap m9959a(String str) {
        m9954e();
        for (int i = 1; i < this.f6531f.length; i++) {
            if (str.equals(this.f6531f[i])) {
                return this.f6532g[i];
            }
        }
        return null;
    }
}
