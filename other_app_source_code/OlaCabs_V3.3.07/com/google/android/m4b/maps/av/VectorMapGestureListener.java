package com.google.android.m4b.maps.av;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import com.google.android.m4b.maps.aj.GestureMotionEvent;
import com.google.android.m4b.maps.aj.GmmGestureDetector.GmmGestureDetector;
import com.google.android.m4b.maps.aj.RotateEvent;
import com.google.android.m4b.maps.aj.ScaleEvent;
import com.google.android.m4b.maps.aj.TiltEvent;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.av.r */
public final class VectorMapGestureListener extends SimpleOnGestureListener implements GmmGestureDetector {
    private static final float f4707c;
    private static final float f4708d;
    private static final double f4709e;
    private final float f4710a;
    private final float f4711b;
    private final VectorMapGestureListener f4712f;
    private VectorMapGestureListener f4713g;
    private MotionEvent f4714h;
    private float f4715i;
    private float f4716j;
    private VectorMapGestureListener f4717k;

    /* renamed from: com.google.android.m4b.maps.av.r.b */
    public interface VectorMapGestureListener {
        void m6894a(float f, float f2);

        void m6895a(float f, float f2, float f3);

        boolean m6896a(MotionEvent motionEvent);

        void m6897b(float f, float f2);

        boolean m6898c(float f, float f2);

        void m6899d(float f, float f2);

        boolean m6900e(float f, float f2);

        void m6901f(float f, float f2);

        int getHeight();

        int getWidth();

        void m6902u();

        VectorMapController m6903v();
    }

    /* renamed from: com.google.android.m4b.maps.av.r.a */
    enum VectorMapGestureListener {
        NONE,
        IN_PROGRESS,
        ZOOM,
        ROTATE
    }

    /* renamed from: com.google.android.m4b.maps.av.r.c */
    static class VectorMapGestureListener {
        boolean f4700a;
        boolean f4701b;
        boolean f4702c;
        boolean f4703d;
        boolean f4704e;
        boolean f4705f;
        boolean f4706g;

        VectorMapGestureListener() {
            this.f4700a = true;
            this.f4701b = true;
            this.f4702c = false;
            this.f4703d = true;
            this.f4704e = true;
            this.f4705f = true;
            this.f4706g = true;
        }
    }

    static {
        f4707c = DeviceSpecificInfo.f8009d ? 0.997f : 0.999f;
        f4708d = br.DEFAULT_BACKOFF_MULT / f4707c;
        f4709e = Math.log(2.0d);
    }

    public VectorMapGestureListener(VectorMapGestureListener vectorMapGestureListener) {
        this.f4717k = VectorMapGestureListener.NONE;
        this.f4712f = vectorMapGestureListener;
        this.f4713g = new VectorMapGestureListener();
        this.f4711b = (float) Config.m11320a().m11329a(20);
        this.f4710a = (float) Config.m11320a().m11329a(20);
    }

    public final boolean onDown(MotionEvent motionEvent) {
        if (this.f4717k == VectorMapGestureListener.NONE) {
            this.f4712f.m6897b(motionEvent.getX(), motionEvent.getY());
        }
        return true;
    }

    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        return this.f4712f.m6898c(motionEvent.getX(), motionEvent.getY());
    }

    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.f4712f.m6896a(motionEvent2) && this.f4713g.f4700a) {
            this.f4712f.m6903v().m7320a(f, f2);
            this.f4712f.m6894a(f, f2);
            this.f4712f.m6902u();
        }
        return true;
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (this.f4713g.f4700a && !onSingleTapUp(motionEvent2)) {
            this.f4712f.m6903v().m7331b(f, f2);
            this.f4712f.m6902u();
        }
        return true;
    }

    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if (this.f4713g.f4706g) {
            this.f4712f.m6902u();
            this.f4712f.m6899d(motionEvent.getX(), motionEvent.getY());
        }
        return true;
    }

    public final boolean onDoubleTap(MotionEvent motionEvent) {
        this.f4712f.m6902u();
        if (this.f4712f.m6900e(motionEvent.getX(), motionEvent.getY())) {
            return true;
        }
        this.f4717k = VectorMapGestureListener.IN_PROGRESS;
        this.f4714h = motionEvent;
        this.f4715i = motionEvent.getX();
        this.f4716j = motionEvent.getY();
        return false;
    }

    public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
        this.f4712f.m6902u();
        if (this.f4714h != null && motionEvent.getAction() == 1) {
            if (this.f4713g.f4701b && this.f4717k == VectorMapGestureListener.IN_PROGRESS) {
                VectorMapGestureListener vectorMapGestureListener = this.f4713g;
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                VectorMapGestureListener vectorMapGestureListener2 = this.f4713g;
                this.f4712f.m6895a(this.f4712f.m6903v().m7316a((float) br.DEFAULT_BACKOFF_MULT, x, y, 330), x, y);
                this.f4714h = null;
                this.f4717k = VectorMapGestureListener.NONE;
                return true;
            }
            this.f4714h = null;
            this.f4717k = VectorMapGestureListener.NONE;
        }
        if (this.f4714h == null || motionEvent.getAction() != 2) {
            return false;
        }
        y = motionEvent.getY() - this.f4716j;
        float x2 = motionEvent.getX() - this.f4715i;
        if (this.f4717k == VectorMapGestureListener.IN_PROGRESS && Math.abs(y) < this.f4711b && Math.abs(x2) < this.f4711b) {
            Math.round(Math.abs(this.f4714h.getX() - motionEvent.getX()));
            if (((float) Math.round(Math.abs(this.f4714h.getY() - motionEvent.getY()))) <= this.f4710a || !this.f4713g.f4701b) {
                return true;
            }
            this.f4717k = VectorMapGestureListener.ZOOM;
            UserEventReporter.m11501a(99, "d");
        }
        if (this.f4717k == VectorMapGestureListener.ZOOM && this.f4713g.f4701b) {
            this.f4712f.m6895a(this.f4712f.m6903v().m7317a((y / ((float) this.f4712f.getHeight())) * 6.0f, 0), this.f4714h.getX(), this.f4714h.getY());
        } else if (this.f4717k == VectorMapGestureListener.ROTATE && this.f4713g.f4704e) {
            x = ((float) this.f4712f.getWidth()) * 0.5f;
            y = ((float) this.f4712f.getHeight()) * 0.5f;
            x2 = GestureMotionEvent.m5077a(x, y, this.f4715i, this.f4716j);
            this.f4712f.m6903v().m7328b(x, y, (float) (((double) ((GestureMotionEvent.m5077a(x, y, motionEvent.getX(), motionEvent.getY()) - x2) * 180.0f)) / 3.141592653589793d));
        }
        this.f4715i = motionEvent.getX();
        this.f4716j = motionEvent.getY();
        return true;
    }

    public final void onLongPress(MotionEvent motionEvent) {
        if (this.f4714h == null && this.f4713g.f4705f) {
            this.f4712f.m6901f(motionEvent.getX(), motionEvent.getY());
        }
    }

    public final boolean m7339a(TiltEvent tiltEvent) {
        if (!this.f4713g.f4703d) {
            return false;
        }
        this.f4712f.m6903v().m7332b(tiltEvent.m5110a(), 0);
        return true;
    }

    public final boolean m7338a(ScaleEvent scaleEvent) {
        if (this.f4713g.f4701b) {
            if (scaleEvent.m5106e()) {
                this.f4712f.m6895a(this.f4712f.m6903v().m7317a(-1.0f, 330), ((float) this.f4712f.getWidth()) / dm.DEFAULT_BACKOFF_MULT, ((float) this.f4712f.getHeight()) / dm.DEFAULT_BACKOFF_MULT);
            } else {
                float log = (float) (Math.log((double) scaleEvent.m5104c()) / f4709e);
                float a = scaleEvent.m5102a();
                float b = scaleEvent.m5103b();
                boolean z = scaleEvent.m5105d() && scaleEvent.m5104c() > f4707c && scaleEvent.m5104c() < f4708d;
                if (z) {
                    log = 0.0f;
                }
                this.f4712f.m6895a(this.f4712f.m6903v().m7315a(log, a, b), a, b);
            }
        }
        return true;
    }

    public final boolean m7337a(RotateEvent rotateEvent) {
        if (!this.f4713g.f4704e) {
            return false;
        }
        rotateEvent.m5095a((float) this.f4712f.getWidth(), (float) this.f4712f.getHeight());
        float c = rotateEvent.m5097c() * 57.295776f;
        this.f4712f.m6903v().m7328b(rotateEvent.m5094a(), rotateEvent.m5096b(), c);
        return true;
    }

    public final void m7335a(boolean z) {
        this.f4713g.f4700a = z;
    }

    public final boolean m7336a() {
        return this.f4713g.f4700a;
    }

    public final void m7340b(boolean z) {
        this.f4713g.f4701b = z;
    }

    public final boolean m7341b() {
        return this.f4713g.f4701b;
    }

    public final void m7342c(boolean z) {
        this.f4713g.f4703d = z;
    }

    public final boolean m7343c() {
        return this.f4713g.f4703d;
    }

    public final void m7344d(boolean z) {
        this.f4713g.f4704e = z;
    }

    public final boolean m7345d() {
        return this.f4713g.f4704e;
    }
}
