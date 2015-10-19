package com.google.android.m4b.maps.aj;

import com.google.android.m4b.maps.aj.GmmGestureDetector.GmmGestureDetector;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.aj.e */
public abstract class Gesture {
    protected final GmmGestureDetector f3171a;
    private boolean f3172b;

    /* renamed from: com.google.android.m4b.maps.aj.e.a */
    public enum Gesture {
        NO,
        MAYBE,
        YES
    }

    protected abstract Gesture m5113a(long j, LinkedList<GestureMotionEvent> linkedList, List<Gesture> list);

    protected abstract boolean m5118b(GmmGestureDetector gmmGestureDetector);

    protected abstract void m5121d(GmmGestureDetector gmmGestureDetector);

    protected abstract boolean m5124f(GmmGestureDetector gmmGestureDetector);

    public Gesture(GmmGestureDetector gmmGestureDetector) {
        this.f3171a = gmmGestureDetector;
    }

    public final boolean m5115a() {
        return this.f3172b;
    }

    public boolean m5117b() {
        return false;
    }

    public boolean m5120c() {
        return false;
    }

    public boolean m5122d() {
        return false;
    }

    public final Gesture m5114a(long j, LinkedList<GestureMotionEvent> linkedList, boolean z, List<Gesture> list, StringBuilder stringBuilder) {
        if (m5117b() && !list.isEmpty()) {
            return Gesture.NO;
        }
        for (Gesture b : list) {
            if (b.m5117b()) {
                return Gesture.NO;
            }
        }
        if (z != m5122d()) {
            return Gesture.NO;
        }
        return m5113a(j, linkedList, list);
    }

    public final boolean m5116a(GmmGestureDetector gmmGestureDetector) {
        if (this.f3172b) {
            throw new IllegalStateException("Gesture already active: " + getClass().getName());
        }
        this.f3172b = m5118b(gmmGestureDetector);
        return this.f3172b;
    }

    public final void m5119c(GmmGestureDetector gmmGestureDetector) {
        if (this.f3172b) {
            this.f3172b = false;
            m5121d(gmmGestureDetector);
            return;
        }
        throw new IllegalStateException("Gesture already inactive: " + getClass().getName());
    }

    public final boolean m5123e(GmmGestureDetector gmmGestureDetector) {
        if (this.f3172b) {
            return m5124f(gmmGestureDetector);
        }
        throw new IllegalStateException("Gesture is not active: " + getClass().getName());
    }

    protected static float m5112a(float f, float f2) {
        if (f2 >= f) {
            return Math.min(f2 - f, (float) ((6.283185307179586d + ((double) f)) - ((double) f2)));
        }
        return -Gesture.m5112a(f2, f);
    }
}
