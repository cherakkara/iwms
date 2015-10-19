package com.google.android.m4b.maps.aj;

import com.google.android.m4b.maps.aj.GmmGestureDetector.GmmGestureDetector;
import com.google.android.m4b.maps.p040u.UserEventReporter;

/* renamed from: com.google.android.m4b.maps.aj.o */
public final class SideSwipeRotateGesture extends TwoFingerSwipeGesture {
    public SideSwipeRotateGesture(GmmGestureDetector gmmGestureDetector) {
        super(gmmGestureDetector);
        this.b = 0.05f;
        this.c = (float) Math.tan(0.3490658503988659d);
    }

    protected final float m5195a(GestureMotionEvent gestureMotionEvent, int i) {
        return gestureMotionEvent.m5080b(i);
    }

    protected final float m5196b(GestureMotionEvent gestureMotionEvent, int i) {
        return gestureMotionEvent.m5078a(i);
    }

    protected final float m5194a(float f) {
        return Math.min(Math.abs(f), (float) Math.abs(((double) Math.abs(f)) - 3.141592653589793d));
    }

    protected final boolean m5197b(GmmGestureDetector gmmGestureDetector) {
        UserEventReporter.m11501a(99, "s");
        return this.a.m5135h(gmmGestureDetector);
    }

    protected final void m5198d(GmmGestureDetector gmmGestureDetector) {
        this.a.m5136i(gmmGestureDetector);
    }

    protected final boolean m5199f(GmmGestureDetector gmmGestureDetector) {
        return this.a.m5134g(gmmGestureDetector);
    }
}
