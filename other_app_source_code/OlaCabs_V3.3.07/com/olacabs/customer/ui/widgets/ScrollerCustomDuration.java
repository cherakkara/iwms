package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* renamed from: com.olacabs.customer.ui.widgets.d */
public class ScrollerCustomDuration extends Scroller {
    private double f11400a;

    public ScrollerCustomDuration(Context context, Interpolator interpolator) {
        super(context, interpolator);
        this.f11400a = 1.0d;
    }

    public void m14854a(double d) {
        this.f11400a = d;
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, (int) (((double) i5) * this.f11400a));
    }
}
