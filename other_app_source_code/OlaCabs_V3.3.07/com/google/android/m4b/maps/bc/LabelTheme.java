package com.google.android.m4b.maps.bc;

import com.google.android.m4b.maps.av.TextGenerator.TextGenerator;
import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.bc.c */
public final class LabelTheme {
    public static final LabelTheme f5409s;
    public static final LabelTheme f5410t;
    public static final LabelTheme f5411u;
    public final TextGenerator f5412a;
    public final int f5413b;
    public final int f5414c;
    public final TextGenerator f5415d;
    public final float f5416e;
    public final int f5417f;
    public final int f5418g;
    public final TextGenerator f5419h;
    public final float f5420i;
    public final int f5421j;
    public final int f5422k;
    public final float f5423l;
    public final float f5424m;
    public final float f5425n;
    public final boolean f5426o;
    public final boolean f5427p;
    public final boolean f5428q;
    public final boolean f5429r;

    static {
        f5409s = new LabelTheme(com.google.android.m4b.maps.av.TextGenerator.f4632c, 18, 12, com.google.android.m4b.maps.av.TextGenerator.f4631b, 1.6f, 14, 32, com.google.android.m4b.maps.av.TextGenerator.f4630a, 1.2f, 8, 16, 1.85f, 1.5f, br.DEFAULT_BACKOFF_MULT, true, false, false, false);
        f5410t = new LabelTheme(com.google.android.m4b.maps.av.TextGenerator.f4630a, 11, 11, com.google.android.m4b.maps.av.TextGenerator.f4630a, br.DEFAULT_BACKOFF_MULT, 8, 24, com.google.android.m4b.maps.av.TextGenerator.f4630a, 1.2f, 8, 16, 1.2f, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, false, true, true, true);
        f5411u = new LabelTheme(com.google.android.m4b.maps.av.TextGenerator.f4630a, 13, 13, com.google.android.m4b.maps.av.TextGenerator.f4630a, 1.2f, 10, 28, com.google.android.m4b.maps.av.TextGenerator.f4630a, 1.2f, 10, 20, 1.2f, 1.25f, 1.5f, false, true, true, true);
        LabelTheme labelTheme = new LabelTheme(com.google.android.m4b.maps.av.TextGenerator.f4630a, 13, 13, com.google.android.m4b.maps.av.TextGenerator.f4630a, 1.2f, 10, 28, com.google.android.m4b.maps.av.TextGenerator.f4630a, 1.2f, 10, 20, 1.2f, br.DEFAULT_BACKOFF_MULT, 1.3f, false, true, true, true);
    }

    private LabelTheme(TextGenerator textGenerator, int i, int i2, TextGenerator textGenerator2, float f, int i3, int i4, TextGenerator textGenerator3, float f2, int i5, int i6, float f3, float f4, float f5, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f5412a = textGenerator;
        this.f5413b = i;
        this.f5414c = i2;
        this.f5415d = textGenerator2;
        this.f5416e = f;
        this.f5417f = i3;
        this.f5418g = i4;
        this.f5419h = textGenerator3;
        this.f5420i = 1.2f;
        this.f5421j = i5;
        this.f5422k = i6;
        this.f5423l = f3;
        this.f5424m = f4;
        this.f5425n = f5;
        this.f5426o = z;
        this.f5427p = z2;
        this.f5428q = z3;
        this.f5429r = z4;
    }
}
