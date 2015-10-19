package com.google.android.m4b.maps.be;

import android.opengl.Matrix;
import com.google.android.m4b.maps.p047g.Objects;
import com.olacabs.customer.p076d.br;

/* compiled from: StreetViewUserOrientation */
public final class ay {
    private float f5703a;
    private float f5704b;
    private float f5705c;
    private final float[] f5706d;
    private boolean f5707e;

    public ay() {
        this.f5704b = 0.5f;
        this.f5706d = new float[]{br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT};
    }

    public ay(float f, float f2, float f3) {
        this.f5704b = 0.5f;
        this.f5706d = new float[]{br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT};
        this.f5703a = f;
        this.f5704b = f2;
        this.f5705c = f3;
        this.f5707e = false;
    }

    public ay(ay ayVar) {
        this.f5704b = 0.5f;
        this.f5706d = new float[]{br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT};
        this.f5703a = ayVar.f5703a;
        this.f5704b = ayVar.f5704b;
        this.f5705c = ayVar.f5705c;
        Object obj = ayVar.f5706d;
        Object obj2 = this.f5706d;
        float[] fArr = this.f5706d;
        System.arraycopy(obj, 0, obj2, 0, 16);
        this.f5707e = ayVar.f5707e;
    }

    public final float[] m8749a() {
        if (!this.f5707e) {
            Matrix.setIdentityM(this.f5706d, 0);
            Matrix.rotateM(this.f5706d, 0, 90.0f - (this.f5704b * 180.0f), br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f);
            Matrix.rotateM(this.f5706d, 0, this.f5703a, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f);
            this.f5707e = true;
        }
        return this.f5706d;
    }

    public final float m8750b() {
        return this.f5703a;
    }

    public final void m8747a(float f) {
        this.f5703a = f;
        this.f5707e = false;
    }

    public final float m8752c() {
        return this.f5704b;
    }

    public final void m8751b(float f) {
        this.f5704b = f;
        this.f5707e = false;
    }

    public final float m8754d() {
        return (this.f5704b - 0.5f) * 180.0f;
    }

    public final float m8755e() {
        return this.f5705c;
    }

    public final void m8753c(float f) {
        this.f5705c = f;
    }

    public final void m8748a(float f, int i) {
        float f2 = 0.0f;
        float a = ar.m8592a(this.f5705c + f, 0.0f, (float) i);
        if (a >= 0.05f) {
            f2 = a;
        }
        this.f5705c = f2;
    }

    public final float m8756f() {
        return ar.m8604e(-this.f5705c);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ay)) {
            return false;
        }
        ay ayVar = (ay) obj;
        if (Float.floatToIntBits(this.f5703a) == Float.floatToIntBits(ayVar.f5703a) && Float.floatToIntBits(this.f5704b) == Float.floatToIntBits(ayVar.f5704b) && Float.floatToIntBits(this.f5705c) == Float.floatToIntBits(ayVar.f5705c)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return Objects.m10456a(this).m10455a("pitch", Float.valueOf(m8754d())).m10455a("yaw", Float.valueOf(this.f5703a)).m10455a("zoom", Float.valueOf(this.f5705c)).toString();
    }
}
