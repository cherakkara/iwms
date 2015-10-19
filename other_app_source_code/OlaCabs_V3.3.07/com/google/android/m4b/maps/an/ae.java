package com.google.android.m4b.maps.an;

/* compiled from: TileGlobalData */
public final class ae {
    private final int f3390a;
    private final ac f3391b;
    private final StyleTable f3392c;
    private final String[] f3393d;
    private final StyleEntryTable f3394e;

    public ae(int i, ac acVar, StyleTable styleTable, String[] strArr, StyleEntryTable styleEntryTable) {
        this.f3390a = i;
        this.f3391b = acVar;
        this.f3392c = styleTable;
        this.f3393d = strArr;
        this.f3394e = styleEntryTable;
    }

    public final int m5449a() {
        return this.f3390a;
    }

    public final ac m5451b() {
        return this.f3391b;
    }

    public final Style m5450a(int i) {
        return this.f3392c.m6116a(i);
    }

    public final StyleEntry m5452b(int i) {
        return this.f3394e.m6110a(i);
    }

    public final String m5453c(int i) {
        String[] strArr = this.f3393d;
        Object obj = (strArr == null || i < 0 || i >= strArr.length) ? null : 1;
        return obj != null ? this.f3393d[i] : null;
    }
}
