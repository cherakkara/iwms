package com.google.android.m4b.maps.aj;

/* renamed from: com.google.android.m4b.maps.aj.c */
public final class AndroidScaleEvent extends ScaleEvent {
    private final GmmGestureDetector f3164a;

    public AndroidScaleEvent(int i, GmmGestureDetector gmmGestureDetector) {
        super(i);
        this.f3164a = gmmGestureDetector;
    }

    public final float m5107a() {
        return this.f3164a.m5169a();
    }

    public final float m5108b() {
        return this.f3164a.m5171b();
    }

    public final float m5109c() {
        return this.f3164a.m5173d();
    }
}
