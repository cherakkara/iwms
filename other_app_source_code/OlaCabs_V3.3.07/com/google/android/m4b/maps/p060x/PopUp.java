package com.google.android.m4b.maps.p060x;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.animation.LinearInterpolator;
import com.google.android.m4b.maps.ay.GLState;

/* renamed from: com.google.android.m4b.maps.x.k */
public final class PopUp {
    private static final Integer[] f8052b;
    private static final int f8053c;
    private final ArrayAnimation<Integer> f8054a;

    static {
        f8052b = new Integer[]{Integer.valueOf(0), Integer.valueOf(164), Integer.valueOf(655), Integer.valueOf(1469), Integer.valueOf(2598), Integer.valueOf(4030), Integer.valueOf(5752), Integer.valueOf(7747), Integer.valueOf(9997), Integer.valueOf(12479), Integer.valueOf(15169), Integer.valueOf(18042), Integer.valueOf(21071), Integer.valueOf(24224), Integer.valueOf(27474), Integer.valueOf(30787), Integer.valueOf(34133), Integer.valueOf(37478), Integer.valueOf(40792), Integer.valueOf(44041), Integer.valueOf(47195), Integer.valueOf(50223), Integer.valueOf(53096), Integer.valueOf(55787), Integer.valueOf(58269), Integer.valueOf(60518), Integer.valueOf(62514), Integer.valueOf(64236), Integer.valueOf(65668), Integer.valueOf(66796), Integer.valueOf(67610), Integer.valueOf(68102), Integer.valueOf(68266), Integer.valueOf(68102), Integer.valueOf(67610), Integer.valueOf(66796), Integer.valueOf(65668), Integer.valueOf(AccessibilityNodeInfoCompat.ACTION_CUT)};
        f8053c = 296;
    }

    public PopUp() {
        this.f8054a = new ArrayAnimation(new LinearInterpolator(), f8052b);
        this.f8054a.setDuration((long) f8053c);
    }

    public final int m11599a(GLState gLState) {
        long e = gLState.m7522e();
        if (!this.f8054a.hasStarted()) {
            this.f8054a.start();
        }
        this.f8054a.m11576b(e);
        int intValue = ((Integer) this.f8054a.m11575b()).intValue();
        if (!this.f8054a.hasEnded()) {
            gLState.m7517b();
        }
        return intValue;
    }
}
