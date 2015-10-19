package com.google.android.m4b.maps.bf;

import android.content.res.Resources;
import android.os.Handler;
import android.os.RemoteException;
import com.google.android.m4b.maps.be.az;
import com.google.android.m4b.maps.be.be;
import com.google.android.m4b.maps.be.bp;
import com.google.android.m4b.maps.be.bp.CameraManager;
import com.google.android.m4b.maps.bf.ProjectionLite.ProjectionLite;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.ICancelableCallback;
import com.google.android.m4b.maps.p042r.ac;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import com.google.p025a.p031f.DoubleMath;
import java.util.Collection;

/* renamed from: com.google.android.m4b.maps.bf.c */
public final class CameraManagerLite implements bp {
    private final MapRendererViewLite f6133b;
    private final double f6134c;
    private CameraPosition f6135d;
    private ac f6136e;
    private Collection<ac> f6137f;
    private int f6138g;
    private int f6139h;
    private int f6140i;
    private int f6141j;

    public CameraManagerLite(MapRendererViewLite mapRendererViewLite, Resources resources, Handler handler) {
        this.f6135d = CameraManagerLite.m9593b(new CameraPosition(new LatLng(0.0d, 0.0d), 3.0f, 0.0f, 0.0f));
        this.f6133b = mapRendererViewLite;
        this.f6134c = Math.max(1.0d, Math.floor((double) resources.getDisplayMetrics().density));
    }

    public final void m9607a(ac acVar) {
        this.f6136e = acVar;
    }

    public final void m9610b(ac acVar) {
        if (this.f6137f == null) {
            this.f6137f = ar.m2515a();
        }
        this.f6137f.add(acVar);
    }

    public final void m9613c(ac acVar) {
        this.f6137f.remove(acVar);
        if (this.f6137f.isEmpty()) {
            this.f6137f = null;
        }
    }

    public final void m9601a(CameraManager cameraManager, int i, ICancelableCallback iCancelableCallback, be beVar) {
        boolean z = i != 0 || iCancelableCallback == null;
        Preconditions.m1823a(z, (Object) "Callback supplied with instantaneous camera movement");
        Preconditions.m1829b(true, (Object) "Camera moved during a cancellation");
        cameraManager.m8928a(this, i, beVar);
    }

    public final void m9596a() {
    }

    private void m9592a(CameraPosition cameraPosition) {
        this.f6135d = CameraManagerLite.m9593b(cameraPosition);
        this.f6133b.m9633d();
        if (this.f6137f != null) {
            for (ac a : this.f6137f) {
                try {
                    a.m9031a(this.f6135d);
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
        }
        if (this.f6136e != null) {
            try {
                this.f6136e.m9031a(this.f6135d);
            } catch (RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        }
    }

    public final CameraPosition m9611c() {
        return this.f6135d;
    }

    public final CameraPosition m9595a(LatLngBounds latLngBounds) {
        ProjectionLite projectionLite;
        int width = this.f6133b.getWidth();
        int height = this.f6133b.getHeight();
        double d = this.f6134c;
        latLngBounds.m10746c();
        double d2 = (double) 22.0f;
        ProjectionLite a = ProjectionLite.m9696a(latLngBounds.f7561a, d2, d);
        ProjectionLite a2 = ProjectionLite.m9696a(latLngBounds.f7562b, d2, d);
        if (latLngBounds.f7561a.f7555b > latLngBounds.f7562b.f7555b) {
            projectionLite = new ProjectionLite(((long) ((int) ProjectionLite.m9695a(d2, d))) + a2.f6187a, a2.f6188b);
        } else {
            projectionLite = a2;
        }
        long j = projectionLite.f6187a - a.f6187a;
        long j2 = a.f6188b - projectionLite.f6188b;
        while (true) {
            if (j <= ((long) width) && j2 <= ((long) height)) {
                return new CameraPosition(latLngBounds.m10746c(), (float) d2, 0.0f, 0.0f);
            }
            d2 -= 1.0d;
            j >>= 1;
            j2 >>= 1;
        }
    }

    public final void m9598a(float f, int i) {
        m9592a(new CameraPosition(this.f6135d.f7529a, this.f6135d.f7530b + f, this.f6135d.f7531c, this.f6135d.f7532d));
    }

    public final void m9609b(float f, int i) {
        az.m8757a(5, "zoomByCumulative is not supported in Lite Mode");
    }

    public final void m9599a(float f, int i, int i2, int i3) {
        az.m8757a(5, "zoomBy with focus is not supported in Lite Mode");
    }

    public final void m9612c(float f, int i) {
        m9592a(new CameraPosition(this.f6135d.f7529a, f, this.f6135d.f7531c, this.f6135d.f7532d));
    }

    public final void m9597a(float f, float f2, int i) {
        az.m8757a(5, "scrollBy is not supported in Lite Mode");
    }

    public final void m9602a(CameraPosition cameraPosition, int i) {
        m9592a(cameraPosition);
    }

    public final void m9604a(LatLng latLng, int i) {
        m9592a(new CameraPosition(latLng, this.f6135d.f7530b, this.f6135d.f7531c, this.f6135d.f7532d));
    }

    public final void m9603a(LatLng latLng, float f, int i) {
        m9592a(new CameraPosition(latLng, f, this.f6135d.f7531c, this.f6135d.f7532d));
    }

    public final void m9605a(LatLngBounds latLngBounds, int i, int i2) {
        m9592a(m9595a(latLngBounds));
    }

    public final void m9606a(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        az.m8757a(5, "newLatLngBounds with size is not supported in Lite Mode");
    }

    public final float m9594a(LatLng latLng) {
        return 22.0f;
    }

    public final float m9614d() {
        return 0.0f;
    }

    public final void m9600a(int i, int i2, int i3, int i4) {
        this.f6138g = i;
        this.f6139h = i2;
        this.f6140i = i3;
        this.f6141j = i4;
    }

    public final ProjectionLite m9608b() {
        return new ProjectionLite(this.f6135d, this.f6133b.getWidth(), this.f6133b.getHeight(), this.f6134c, this.f6138g, this.f6139h, this.f6140i, this.f6141j);
    }

    private static CameraPosition m9593b(CameraPosition cameraPosition) {
        if (!(cameraPosition.f7531c == 0.0f && cameraPosition.f7532d == 0.0f)) {
            az.m8757a(5, "Non zero bearing and tilt are not supported in Lite Mode");
        }
        if (!DoubleMath.m3000a((double) cameraPosition.f7530b)) {
            az.m8757a(5, "Non integer zooms are not supported in Lite Mode");
        }
        return new CameraPosition(cameraPosition.f7529a, (float) Math.round(Math.max(0.0f, Math.min(22.0f, cameraPosition.f7530b))), 0.0f, 0.0f);
    }
}
