package com.google.android.m4b.maps.aj;

import com.google.android.m4b.maps.aj.GmmGestureDetector.GmmGestureDetector;
import com.google.android.m4b.maps.p040u.UserEventReporter;

/* renamed from: com.google.android.m4b.maps.aj.q */
public final class TiltGesture extends TwoFingerSwipeGesture {
    public TiltGesture(GmmGestureDetector gmmGestureDetector) {
        super(gmmGestureDetector);
    }

    protected final float m5201a(GestureMotionEvent gestureMotionEvent, int i) {
        return gestureMotionEvent.m5078a(i);
    }

    protected final float m5202b(GestureMotionEvent gestureMotionEvent, int i) {
        return gestureMotionEvent.m5080b(i);
    }

    protected final float m5200a(float f) {
        return (float) Math.abs(((double) Math.abs(f)) - 1.5707963267948966d);
    }

    protected final boolean m5203b(GmmGestureDetector gmmGestureDetector) {
        UserEventReporter.m11501a(99, "t");
        return this.a.m5127b(gmmGestureDetector);
    }

    protected final void m5204d(GmmGestureDetector gmmGestureDetector) {
        this.a.m5129c(gmmGestureDetector);
    }

    protected final boolean m5205f(GmmGestureDetector gmmGestureDetector) {
        return this.a.m5125a(gmmGestureDetector);
    }
}
