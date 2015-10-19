package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import com.olacabs.customer.app.OLog;
import java.lang.reflect.Field;

public class SlowPagerView extends ViewPager {
    private static final String f11342a;
    private ScrollerCustomDuration f11343b;

    static {
        f11342a = SlowPagerView.class.getSimpleName();
    }

    public SlowPagerView(Context context) {
        super(context);
        this.f11343b = null;
        m14821a();
    }

    public SlowPagerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11343b = null;
        m14821a();
    }

    private void m14821a() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            Field declaredField2 = ViewPager.class.getDeclaredField("sInterpolator");
            declaredField2.setAccessible(true);
            this.f11343b = new ScrollerCustomDuration(getContext(), (Interpolator) declaredField2.get(null));
            declaredField.set(this, this.f11343b);
        } catch (Throwable e) {
            OLog.m13310a("Reflection failed", e);
        } catch (Throwable e2) {
            OLog.m13310a("Reflection failed", e2);
        }
    }

    public void setScrollDurationFactor(double d) {
        this.f11343b.m14854a(d);
    }
}
