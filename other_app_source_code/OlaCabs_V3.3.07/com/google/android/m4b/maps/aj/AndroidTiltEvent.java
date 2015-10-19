package com.google.android.m4b.maps.aj;

/* renamed from: com.google.android.m4b.maps.aj.d */
public final class AndroidTiltEvent extends TiltEvent {
    private final GmmGestureDetector f3166a;

    public AndroidTiltEvent(int i, GmmGestureDetector gmmGestureDetector) {
        super(i);
        this.f3166a = gmmGestureDetector;
    }

    public final float m5111a() {
        return this.f3166a.m5174e();
    }
}
