package com.google.android.m4b.maps.aj;

/* renamed from: com.google.android.m4b.maps.aj.b */
public class AndroidRotateEvent extends RotateEvent {
    protected final GmmGestureDetector f3162a;

    public AndroidRotateEvent(int i, GmmGestureDetector gmmGestureDetector) {
        super(i);
        this.f3162a = gmmGestureDetector;
    }

    public void m5099a(float f, float f2) {
    }

    public float m5098a() {
        return this.f3162a.m5169a();
    }

    public float m5100b() {
        return this.f3162a.m5171b();
    }

    public float m5101c() {
        return this.f3162a.m5175f();
    }
}
