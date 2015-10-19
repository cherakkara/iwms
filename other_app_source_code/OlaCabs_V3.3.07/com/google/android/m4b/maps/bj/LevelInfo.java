package com.google.android.m4b.maps.bj;

import android.os.SystemClock;
import android.util.FloatMath;
import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.bj.w */
final class LevelInfo {
    final int f6645a;
    final int f6646b;
    private final int f6647c;
    private final LevelInfo f6648d;
    private int f6649e;
    private final int f6650f;
    private final int f6651g;
    private final int f6652h;
    private final int f6653i;
    private final int f6654j;
    private final int f6655k;
    private final int f6656l;
    private final int f6657m;
    private Grid[] f6658n;
    private final float f6659o;
    private final float f6660p;
    private final float f6661q;
    private final float f6662r;
    private float[] f6663s;
    private boolean f6664t;

    /* renamed from: com.google.android.m4b.maps.bj.w.a */
    interface LevelInfo {
        int m9707a(int i);

        Grid m9708a(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5, float f6, boolean z);
    }

    public LevelInfo(int i, int i2, LevelInfo levelInfo, int i3, int i4, int i5, int i6, float f, int i7) {
        int i8 = 1;
        this.f6664t = false;
        if (i2 < 0) {
            throw new IllegalArgumentException("zoomShift");
        }
        this.f6648d = levelInfo;
        this.f6647c = i;
        this.f6650f = i3 >> i2;
        this.f6651g = i4 >> i2;
        this.f6652h = i5;
        this.f6653i = i6;
        this.f6654j = this.f6650f / this.f6652h;
        this.f6656l = this.f6650f - (this.f6654j * this.f6652h);
        this.f6655k = this.f6651g / this.f6653i;
        this.f6657m = this.f6651g - (this.f6655k * this.f6653i);
        this.f6649e = i7;
        this.f6645a = (this.f6656l > 0 ? 1 : 0) + this.f6654j;
        int i9 = this.f6655k;
        if (this.f6657m <= 0) {
            i8 = 0;
        }
        this.f6646b = i9 + i8;
        this.f6659o = ((float) this.f6652h) / ((float) this.f6650f);
        this.f6660p = ((float) this.f6656l) / ((float) this.f6650f);
        this.f6661q = (((float) this.f6653i) / ((float) this.f6651g)) * f;
        this.f6662r = (((float) this.f6657m) / ((float) this.f6651g)) * f;
    }

    public final int m10024a() {
        return (this.f6645a * this.f6646b) * this.f6649e;
    }

    private static int m10022a(float f, boolean z, int i) {
        float f2;
        if (z) {
            f2 = f / 0.015625f;
        } else {
            f2 = f / 0.03125f;
        }
        return Math.max((int) FloatMath.ceil(f2 / ((float) i)), 1);
    }

    private void m10023b() {
        int a = m10024a();
        this.f6663s = new float[(a * 4)];
        for (int i = 0; i < a; i++) {
            this.f6658n[i].m9979a(this.f6663s, i * 4);
        }
    }

    public final Grid m10026a(int i, boolean z) {
        m10027a(z);
        return this.f6658n[i];
    }

    public final float[] m10027a(boolean z) {
        if (this.f6663s == null || z != this.f6664t) {
            long uptimeMillis = SystemClock.uptimeMillis();
            int a = LevelInfo.m10022a(this.f6659o, z, this.f6649e);
            int a2 = LevelInfo.m10022a(this.f6660p, z, this.f6649e);
            int a3 = LevelInfo.m10022a(this.f6661q, z, this.f6649e);
            int a4 = LevelInfo.m10022a(this.f6662r, z, this.f6649e);
            float f = ((float) this.f6656l) / ((float) this.f6652h);
            float f2 = ((float) this.f6657m) / ((float) this.f6653i);
            this.f6658n = new Grid[m10024a()];
            int i = 0;
            while (i < this.f6649e) {
                float f3 = 0.0f;
                int i2 = 0;
                while (i2 < this.f6646b) {
                    float f4 = 0.0f;
                    int i3 = 0;
                    while (i3 < this.f6645a) {
                        Grid a5;
                        if (i2 < this.f6655k) {
                            if (i3 < this.f6654j) {
                                a5 = this.f6648d.m9708a(a, a3, f4, f3, i, this.f6659o, this.f6661q, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, z);
                            } else {
                                a5 = this.f6648d.m9708a(a2, a3, f4, f3, i, this.f6660p, this.f6661q, f, br.DEFAULT_BACKOFF_MULT, z);
                            }
                        } else if (i3 < this.f6654j) {
                            r18 = a;
                            r20 = f4;
                            r21 = f3;
                            r22 = i;
                            a5 = this.f6648d.m9708a(r18, a4, r20, r21, r22, this.f6659o, this.f6662r, br.DEFAULT_BACKOFF_MULT, f2, z);
                        } else {
                            r18 = a2;
                            r20 = f4;
                            r21 = f3;
                            r22 = i;
                            a5 = this.f6648d.m9708a(r18, a4, r20, r21, r22, this.f6660p, this.f6662r, f, f2, z);
                        }
                        Grid[] gridArr = this.f6658n;
                        if (i3 < 0 || i3 >= this.f6645a) {
                            throw new IllegalArgumentException("tileX");
                        } else if (i2 < 0 || i2 >= this.f6646b) {
                            throw new IllegalArgumentException("tileY");
                        } else if (i < 0 || i >= this.f6649e) {
                            throw new IllegalArgumentException("tileFace");
                        } else {
                            gridArr[((this.f6645a * i2) + i3) + ((this.f6645a * i) * this.f6646b)] = a5;
                            f4 += this.f6659o;
                            i3++;
                        }
                    }
                    f3 += this.f6661q;
                    i2++;
                }
                i++;
            }
            m10023b();
            new StringBuilder("LevelInfo.genGridsAndBoundingSpheres ").append(this.f6645a).append("*").append(this.f6646b).append("@").append(this.f6647c).append(": ").append(SystemClock.uptimeMillis() - uptimeMillis).append(" ms");
            this.f6664t = z;
        }
        return this.f6663s;
    }

    public final int m10025a(int i) {
        return this.f6648d.m9707a(i);
    }
}
