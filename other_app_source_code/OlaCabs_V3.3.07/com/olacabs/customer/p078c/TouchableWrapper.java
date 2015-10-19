package com.olacabs.customer.p078c;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.olacabs.customer.c.e */
public class TouchableWrapper extends FrameLayout {
    private MapDragListener f9438a;
    private boolean f9439b;

    public TouchableWrapper(Context context, MapDragListener mapDragListener) {
        super(context);
        this.f9439b = false;
        this.f9438a = mapDragListener;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                if (this.f9439b) {
                    this.f9439b = false;
                    this.f9438a.m13353b();
                    break;
                }
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                this.f9439b = true;
                this.f9438a.o_();
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
