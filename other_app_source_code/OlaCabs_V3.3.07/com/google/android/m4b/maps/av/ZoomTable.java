package com.google.android.m4b.maps.av;

import com.olacabs.customer.p076d.br;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/* renamed from: com.google.android.m4b.maps.av.t */
public final class ZoomTable {
    public static final ZoomTable f4724a;
    private final int f4725b;
    private final int[] f4726c;
    private final int f4727d;
    private final int f4728e;
    private final int f4729f;
    private final int[] f4730g;
    private final int[] f4731h;
    private final float[] f4732i;
    private TreeSet<Integer> f4733j;
    private final float[] f4734k;

    static {
        f4724a = new ZoomTable(21);
    }

    public ZoomTable(int[] iArr, int i, int i2, int i3) {
        int i4;
        this.f4726c = iArr;
        this.f4725b = i2;
        float f = br.DEFAULT_BACKOFF_MULT / ((float) i2);
        this.f4729f = i3;
        int length = this.f4726c.length;
        int[] iArr2 = this.f4726c;
        this.f4728e = this.f4726c[length - 1];
        this.f4727d = i;
        this.f4732i = new float[(this.f4728e + 1)];
        this.f4734k = new float[(this.f4728e + 1)];
        Arrays.fill(this.f4732i, -1.0f);
        Arrays.fill(this.f4734k, -1.0f);
        this.f4733j = new TreeSet();
        float f2 = (float) this.f4727d;
        int i5 = 0;
        int i6 = this.f4727d;
        while (i5 < length) {
            float f3;
            this.f4733j.add(Integer.valueOf(this.f4726c[i5]));
            if (this.f4732i[this.f4726c[i5]] < 0.0f) {
                float f4 = (((float) i5) * f) + ((float) this.f4727d);
                while (i6 < this.f4726c[i5]) {
                    this.f4732i[i6] = f2;
                    this.f4734k[i6] = f4;
                    i6++;
                }
                this.f4732i[i6] = f4;
                float f5 = f4;
                i4 = i6;
                f3 = f5;
            } else {
                i4 = i6;
                f3 = f2;
            }
            i5++;
            f2 = f3;
            i6 = i4;
        }
        this.f4730g = new int[(this.f4728e + 1)];
        this.f4731h = new int[(this.f4728e + 1)];
        Arrays.fill(this.f4730g, -1);
        Arrays.fill(this.f4731h, -1);
        Iterator it = this.f4733j.iterator();
        i4 = -1;
        while (it.hasNext()) {
            i6 = ((Integer) it.next()).intValue();
            this.f4730g[i6] = i4;
            if (i4 >= 0) {
                this.f4731h[i4] = i6;
            }
            i4 = i6;
        }
    }

    private ZoomTable(int i) {
        this.f4726c = new int[0];
        this.f4725b = 1;
        this.f4729f = 21;
        this.f4728e = 21;
        this.f4727d = 22;
        this.f4732i = new float[0];
        this.f4734k = new float[0];
        this.f4733j = new TreeSet();
        this.f4730g = new int[0];
        this.f4731h = new int[0];
    }

    public final int m7351a() {
        return this.f4729f;
    }

    public final int m7352a(float f) {
        int i = (int) ((f - ((float) this.f4727d)) * ((float) this.f4725b));
        if (i >= this.f4726c.length) {
            return this.f4728e;
        }
        if (i < 0) {
            return -1;
        }
        return this.f4726c[i];
    }

    public final int m7353a(int i) {
        if (i < 0 || i >= this.f4730g.length) {
            return -1;
        }
        return this.f4730g[i];
    }

    public final int m7355b(int i) {
        if (i < 0 || i >= this.f4731h.length) {
            return -1;
        }
        return this.f4731h[i];
    }

    public final int m7354b() {
        return this.f4728e;
    }

    public final boolean m7356c(int i) {
        return this.f4733j.contains(Integer.valueOf(i));
    }

    public final float m7357d(int i) {
        if (i < 0 || i >= this.f4732i.length) {
            return -1.0f;
        }
        return this.f4732i[i];
    }

    public final float m7358e(int i) {
        if (i < 0 || i >= this.f4732i.length) {
            return -1.0f;
        }
        return this.f4734k[i];
    }
}
