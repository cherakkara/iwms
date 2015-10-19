package com.google.android.m4b.maps.bd;

import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.bd.l */
public final class Matrix3f {
    private float[] f5520a;

    public Matrix3f() {
        this.f5520a = new float[9];
        m8247a();
    }

    private Matrix3f m8247a() {
        for (int i = 1; i < this.f5520a.length - 1; i++) {
            this.f5520a[i] = 0.0f;
        }
        this.f5520a[0] = br.DEFAULT_BACKOFF_MULT;
        this.f5520a[4] = br.DEFAULT_BACKOFF_MULT;
        this.f5520a[8] = br.DEFAULT_BACKOFF_MULT;
        return this;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 9; i += 3) {
            stringBuilder.append(this.f5520a[i] + ", " + this.f5520a[i + 1] + ", " + this.f5520a[i + 2] + "\n");
        }
        return stringBuilder.toString();
    }
}
