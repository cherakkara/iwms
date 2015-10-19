package com.google.android.m4b.maps.aj;

import android.view.MotionEvent;
import com.google.android.m4b.maps.p040u.Config;

/* renamed from: com.google.android.m4b.maps.aj.a */
public final class AndroidGestureMotionEvent extends GestureMotionEvent {
    private MotionEvent f3160a;

    public AndroidGestureMotionEvent(MotionEvent motionEvent) {
        this.f3160a = motionEvent;
    }

    public final long m5088a() {
        return this.f3160a.getEventTime();
    }

    public final int m5090b() {
        return this.f3160a.getPointerCount();
    }

    public final float m5087a(int i) {
        return this.f3160a.getX(i);
    }

    public final float m5089b(int i) {
        return this.f3160a.getY(i);
    }

    public final float m5091c() {
        return Config.m11320a().m11332f();
    }

    public final float m5092d() {
        return Config.m11320a().m11333g();
    }

    public final void m5093e() {
        this.f3160a.recycle();
        this.f3160a = null;
    }
}
