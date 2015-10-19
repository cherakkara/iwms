package com.google.android.m4b.maps.bj;

import android.content.Context;
import android.util.FloatMath;
import android.view.MotionEvent;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.bj.b */
public final class ScaleGestureDetector {
    private final ScaleGestureDetector f6408a;
    private float f6409b;
    private float f6410c;
    private float f6411d;
    private float f6412e;
    private float f6413f;
    private float f6414g;
    private float f6415h;
    private boolean f6416i;

    /* renamed from: com.google.android.m4b.maps.bj.b.a */
    public interface ScaleGestureDetector {
        boolean m9843a(ScaleGestureDetector scaleGestureDetector);

        boolean m9844b(ScaleGestureDetector scaleGestureDetector);

        void m9845c(ScaleGestureDetector scaleGestureDetector);
    }

    public ScaleGestureDetector(Context context, ScaleGestureDetector scaleGestureDetector) {
        this.f6408a = scaleGestureDetector;
    }

    public final boolean m9847a(MotionEvent motionEvent) {
        int i;
        int actionMasked = motionEvent.getActionMasked();
        Object obj = (actionMasked == 1 || actionMasked == 3) ? 1 : null;
        if (actionMasked == 0 || obj != null) {
            if (this.f6416i) {
                this.f6408a.m9845c(this);
                this.f6416i = false;
                this.f6413f = 0.0f;
            }
            if (obj != null) {
                return true;
            }
        }
        Object obj2 = (actionMasked == 6 || actionMasked == 5) ? 1 : null;
        Object obj3 = actionMasked == 6 ? 1 : null;
        int actionIndex = obj3 != null ? motionEvent.getActionIndex() : -1;
        int pointerCount = motionEvent.getPointerCount();
        float f = 0.0f;
        float f2 = 0.0f;
        for (i = 0; i < pointerCount; i++) {
            if (actionIndex != i) {
                f2 += motionEvent.getX(i);
                f += motionEvent.getY(i);
            }
        }
        i = obj3 != null ? pointerCount - 1 : pointerCount;
        float f3 = f2 / ((float) i);
        float f4 = f / ((float) i);
        f = 0.0f;
        f2 = 0.0f;
        for (int i2 = 0; i2 < pointerCount; i2++) {
            if (actionIndex != i2) {
                f2 += Math.abs(motionEvent.getX(i2) - f3);
                f += Math.abs(motionEvent.getY(i2) - f4);
            }
        }
        float f5 = (f2 / ((float) i)) * dm.DEFAULT_BACKOFF_MULT;
        f = (f / ((float) i)) * dm.DEFAULT_BACKOFF_MULT;
        f2 = FloatMath.sqrt((f5 * f5) + (f * f));
        boolean z = this.f6416i;
        this.f6409b = f3;
        this.f6410c = f4;
        if (this.f6416i && (f2 == 0.0f || obj2 != null)) {
            this.f6408a.m9845c(this);
            this.f6416i = false;
            this.f6413f = f2;
        }
        if (obj2 != null) {
            this.f6414g = f5;
            this.f6415h = f;
            this.f6411d = f2;
            this.f6412e = f2;
            this.f6413f = f2;
        }
        if (!(this.f6416i || f2 == 0.0f || (!z && Math.abs(f2 - this.f6413f) <= -1.0f))) {
            this.f6414g = f5;
            this.f6415h = f;
            this.f6411d = f2;
            this.f6412e = f2;
            this.f6416i = this.f6408a.m9844b(this);
        }
        if (actionMasked == 2) {
            this.f6414g = f5;
            this.f6415h = f;
            this.f6411d = f2;
            boolean z2 = true;
            if (this.f6416i) {
                z2 = this.f6408a.m9843a(this);
            }
            if (z2) {
                f5 = this.f6414g;
                f5 = this.f6415h;
                this.f6412e = this.f6411d;
            }
        }
        return true;
    }

    public final float m9846a() {
        return this.f6409b;
    }

    public final float m9848b() {
        return this.f6410c;
    }

    public final float m9849c() {
        return this.f6411d;
    }

    public final float m9850d() {
        return this.f6412e;
    }
}
