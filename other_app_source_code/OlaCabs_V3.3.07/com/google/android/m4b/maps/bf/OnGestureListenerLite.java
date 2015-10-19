package com.google.android.m4b.maps.bf;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

/* renamed from: com.google.android.m4b.maps.bf.h */
public final class OnGestureListenerLite extends SimpleOnGestureListener {
    private MapRendererViewLite f6171a;
    private OverlayRendererManagerLite f6172b;

    OnGestureListenerLite(MapRendererViewLite mapRendererViewLite, OverlayRendererManagerLite overlayRendererManagerLite) {
        this.f6171a = mapRendererViewLite;
        this.f6172b = overlayRendererManagerLite;
    }

    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        boolean a = this.f6172b.m9678a(motionEvent);
        if (a) {
            return a;
        }
        return this.f6171a.m9627a(motionEvent);
    }

    public final void onLongPress(MotionEvent motionEvent) {
        this.f6171a.m9629b(motionEvent);
    }
}
