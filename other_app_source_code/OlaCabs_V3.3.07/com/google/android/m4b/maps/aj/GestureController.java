package com.google.android.m4b.maps.aj;

import android.content.Context;
import android.view.MotionEvent;
import com.google.android.m4b.maps.aj.GmmGestureDetector.GmmGestureDetector;

/* renamed from: com.google.android.m4b.maps.aj.f */
public final class GestureController implements GmmGestureDetector {
    private GmmGestureDetector f3173a;
    private GmmGestureDetector f3174b;
    private boolean f3175c;

    public final void m5137a(Context context, GmmGestureDetector gmmGestureDetector) {
        this.f3173a = gmmGestureDetector;
        this.f3174b = new GmmGestureDetector(context, this);
    }

    public final boolean m5138a(MotionEvent motionEvent) {
        return this.f3174b.m5170a(motionEvent);
    }

    public final boolean m5140a(GmmGestureDetector gmmGestureDetector, boolean z) {
        if (z) {
            return true;
        }
        return this.f3173a.m5160a(new AndroidScaleEvent(0, gmmGestureDetector));
    }

    public final boolean m5142b(GmmGestureDetector gmmGestureDetector, boolean z) {
        if (z) {
            return true;
        }
        return this.f3173a.m5160a(new AndroidScaleEvent(1, gmmGestureDetector));
    }

    public final void m5144c(GmmGestureDetector gmmGestureDetector, boolean z) {
        if (z) {
            this.f3173a.m5160a(new AndroidScaleEvent(3, gmmGestureDetector));
        } else {
            this.f3173a.m5160a(new AndroidScaleEvent(2, gmmGestureDetector));
        }
    }

    public final boolean m5139a(GmmGestureDetector gmmGestureDetector) {
        return this.f3173a.m5161a(new AndroidTiltEvent(0, gmmGestureDetector));
    }

    public final boolean m5141b(GmmGestureDetector gmmGestureDetector) {
        boolean a = this.f3173a.m5161a(new AndroidTiltEvent(1, gmmGestureDetector));
        if (a) {
            this.f3175c = true;
        }
        return a;
    }

    public final void m5143c(GmmGestureDetector gmmGestureDetector) {
        this.f3175c = false;
        this.f3173a.m5161a(new AndroidTiltEvent(2, gmmGestureDetector));
    }

    public final boolean m5145d(GmmGestureDetector gmmGestureDetector) {
        return this.f3173a.m5159a(new AndroidRotateEvent(0, gmmGestureDetector));
    }

    public final boolean m5146e(GmmGestureDetector gmmGestureDetector) {
        return this.f3173a.m5159a(new AndroidRotateEvent(1, gmmGestureDetector));
    }

    public final void m5147f(GmmGestureDetector gmmGestureDetector) {
        this.f3173a.m5159a(new AndroidRotateEvent(2, gmmGestureDetector));
    }

    public final boolean m5148g(GmmGestureDetector gmmGestureDetector) {
        return this.f3173a.m5159a(new SideSwipeRotateEvent(0, gmmGestureDetector));
    }

    public final boolean m5149h(GmmGestureDetector gmmGestureDetector) {
        return this.f3173a.m5159a(new SideSwipeRotateEvent(1, gmmGestureDetector));
    }

    public final void m5150i(GmmGestureDetector gmmGestureDetector) {
        this.f3173a.m5159a(new SideSwipeRotateEvent(2, gmmGestureDetector));
    }

    public final boolean onDown(MotionEvent motionEvent) {
        return this.f3173a.onDown(motionEvent);
    }

    public final void onShowPress(MotionEvent motionEvent) {
        this.f3173a.onShowPress(motionEvent);
    }

    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        return this.f3173a.onSingleTapUp(motionEvent);
    }

    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return !this.f3175c && this.f3173a.onScroll(motionEvent, motionEvent2, f, f2);
    }

    public final void onLongPress(MotionEvent motionEvent) {
        this.f3173a.onLongPress(motionEvent);
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.f3173a.onFling(motionEvent, motionEvent2, f, f2);
    }

    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return this.f3173a.onSingleTapConfirmed(motionEvent);
    }

    public final boolean onDoubleTap(MotionEvent motionEvent) {
        return this.f3173a.onDoubleTap(motionEvent);
    }

    public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return this.f3173a.onDoubleTapEvent(motionEvent);
    }
}
