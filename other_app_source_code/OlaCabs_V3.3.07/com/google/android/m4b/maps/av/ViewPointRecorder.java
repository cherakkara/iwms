package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.as;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.google.android.m4b.maps.p058v.Util;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;

/* renamed from: com.google.android.m4b.maps.av.s */
public final class ViewPointRecorder {
    private final DataRequestDispatcherInterface f4718a;
    private Point f4719b;
    private int f4720c;
    private final Point f4721d;
    private ViewPointRecorder f4722e;
    private ViewPointRecorder f4723f;

    /* renamed from: com.google.android.m4b.maps.av.s.a */
    public interface ViewPointRecorder {
        void m7346a(int i);
    }

    /* renamed from: com.google.android.m4b.maps.av.s.b */
    public interface ViewPointRecorder {
    }

    public ViewPointRecorder(DataRequestDispatcherInterface dataRequestDispatcherInterface) {
        this.f4719b = new Point(0, 0);
        this.f4720c = -1;
        this.f4721d = new Point();
        this.f4722e = null;
        this.f4723f = null;
        this.f4718a = dataRequestDispatcherInterface;
    }

    private void m7347a(Point point, Point point2, int i, as asVar) {
        boolean z;
        this.f4719b = point;
        this.f4720c = i;
        int a = point.m5941a();
        int c = point.m5952c();
        int e = (int) ((((double) asVar.m5684e()) * Math.cos((((double) a) * 1.0E-6d) * 0.017453292519943295d)) * 0.33527612686157227d);
        int d = (int) (((double) asVar.m5683d()) * 0.33527612686157227d);
        if (this.f4723f != null) {
            ViewPointRecorder viewPointRecorder = this.f4723f;
            z = false;
        } else {
            z = true;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeInt(a);
            dataOutputStream.writeInt(c);
            dataOutputStream.writeInt(point2.m5941a());
            dataOutputStream.writeInt(point2.m5952c());
            dataOutputStream.writeShort(i);
            dataOutputStream.writeInt(e);
            dataOutputStream.writeInt(d);
            dataOutputStream.writeBoolean(z);
            dataOutputStream.writeBoolean(true);
            this.f4718a.m11372a(7, byteArrayOutputStream.toByteArray(), false, false, true);
            if (this.f4722e != null) {
                this.f4722e.m7346a(i);
            }
        } catch (Throwable e2) {
            Util.m11552a("view point", e2);
        }
    }

    public final void m7350a(Camera camera) {
        Point b = camera.m7430b();
        int max = Math.max(0, Math.min(30, Math.round(camera.m7444l())));
        if (max != this.f4720c) {
            m7347a(b, b, max, camera.m7453u().m5662a());
        } else if (!this.f4719b.equals(b)) {
            Point point = this.f4719b;
            Point point2 = null;
            Point b2 = camera.m7430b();
            int f = (int) (((float) (b2.m5958f() - point.m5958f())) / camera.m7450r());
            int g = (int) (((float) (b2.m5960g() - point.m5960g())) / camera.m7450r());
            int e = camera.m7437e();
            int f2 = camera.m7438f();
            int i = e / 2;
            int i2 = f2 / 2;
            if (Math.abs(f) > e || Math.abs(g) > f2) {
                point2 = b2;
            } else {
                if (f < (-i)) {
                    e = -e;
                } else if (f <= i) {
                    e = 0;
                }
                int i3 = g < (-i2) ? -f2 : g > i2 ? f2 : 0;
                if (!(i3 == 0 && e == 0)) {
                    this.f4721d.m5955d((int) (((float) e) * camera.m7450r()), (int) (((float) i3) * camera.m7450r()));
                    point2 = point.m5957e(this.f4721d);
                }
            }
            if (point2 == null) {
                return;
            }
            if (this.f4719b == null || b.m5954d(point2) < b.m5954d(this.f4719b)) {
                m7347a(point2, b, max, camera.m7453u().m5662a());
            }
        }
    }

    public final void m7348a(ViewPointRecorder viewPointRecorder) {
        this.f4722e = viewPointRecorder;
    }

    public final void m7349a(ViewPointRecorder viewPointRecorder) {
        this.f4723f = viewPointRecorder;
    }
}
