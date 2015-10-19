package com.google.android.m4b.maps.bj;

import android.content.Context;
import android.os.SystemClock;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import com.google.android.m4b.maps.bj.ScaleGestureDetector.ScaleGestureDetector;

/* renamed from: com.google.android.m4b.maps.bj.d */
public final class StreetViewGestureController implements OnDoubleTapListener, OnGestureListener, ScaleGestureDetector {
    private final ScaleGestureDetector f6418a;
    private float f6419b;
    private long f6420c;
    private final float f6421d;
    private final ai f6422e;
    private final OnGestureListener f6423f;
    private final OnDoubleTapListener f6424g;
    private final GestureDetector f6425h;

    public StreetViewGestureController(Context context, OnGestureListener onGestureListener, OnDoubleTapListener onDoubleTapListener, ai aiVar) {
        this.f6419b = 0.0f;
        this.f6423f = onGestureListener;
        this.f6424g = onDoubleTapListener;
        this.f6425h = new GestureDetector(context, this);
        this.f6425h.m9973a((OnDoubleTapListener) this);
        this.f6425h.m9974a(true);
        this.f6422e = aiVar;
        this.f6418a = new ScaleGestureDetector(context, this);
        this.f6421d = context.getResources().getDisplayMetrics().density;
    }

    public final boolean m9855a(MotionEvent motionEvent) {
        return this.f6425h.m9975a(motionEvent) | this.f6418a.m9847a(motionEvent);
    }

    public final void m9854a(boolean z) {
        this.f6425h.m9974a(false);
    }

    public final boolean m9856a(ScaleGestureDetector scaleGestureDetector) {
        this.f6419b += Math.abs(scaleGestureDetector.m9849c() - scaleGestureDetector.m9850d());
        return this.f6422e.m9841a(new ah(0, scaleGestureDetector));
    }

    public final boolean m9857b(ScaleGestureDetector scaleGestureDetector) {
        this.f6419b = 0.0f;
        this.f6420c = SystemClock.elapsedRealtime();
        return this.f6422e.m9841a(new ah(1, scaleGestureDetector));
    }

    public final void m9858c(ScaleGestureDetector scaleGestureDetector) {
        Object obj = (SystemClock.elapsedRealtime() - this.f6420c >= 300 || this.f6419b > 22.0f * this.f6421d) ? null : 1;
        if (obj != null) {
            this.f6422e.m9842f();
        } else {
            this.f6422e.m9841a(new ah(2, scaleGestureDetector));
        }
    }

    public final boolean onDown(MotionEvent motionEvent) {
        return this.f6423f.onDown(motionEvent);
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.f6423f.onFling(motionEvent, motionEvent2, f, f2);
    }

    public final void onLongPress(MotionEvent motionEvent) {
        this.f6423f.onLongPress(motionEvent);
    }

    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.f6423f.onScroll(motionEvent, motionEvent2, f, f2);
    }

    public final void onShowPress(MotionEvent motionEvent) {
        this.f6423f.onShowPress(motionEvent);
    }

    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        return this.f6424g.onSingleTapConfirmed(motionEvent);
    }

    public final boolean onDoubleTap(MotionEvent motionEvent) {
        return this.f6424g.onDoubleTap(motionEvent);
    }

    public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return this.f6424g.onDoubleTapEvent(motionEvent);
    }

    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return this.f6424g.onSingleTapConfirmed(motionEvent);
    }
}
