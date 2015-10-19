package com.google.android.m4b.maps.av;

/* compiled from: DrawMode */
public enum ac {
    NORMAL(1),
    HYBRID(2),
    NIGHT(3),
    TERRAIN(4),
    RASTER_ONLY(5),
    NONE(6);
    
    public static final int f4132g;
    private final int f4134h;

    static {
        f4132g = values().length;
    }

    private ac(int i) {
        this.f4134h = i;
    }

    public final int m6700a() {
        return this.f4134h;
    }
}
