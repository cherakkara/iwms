package com.google.android.m4b.maps.p061y;

import android.os.Handler;
import android.os.RemoteException;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.PositionChangeFinishedCallback;
import com.google.android.m4b.maps.av.VectorMapController.VectorMapController;
import com.google.android.m4b.maps.av.aa;
import com.google.android.m4b.maps.ax.CameraPosition;
import com.google.android.m4b.maps.ax.CameraPositionProvider;
import com.google.android.m4b.maps.be.be;
import com.google.android.m4b.maps.be.bp;
import com.google.android.m4b.maps.be.bp.CameraManager;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.ICancelableCallback;
import com.google.android.m4b.maps.p042r.ac;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import java.util.Collection;

/* renamed from: com.google.android.m4b.maps.y.a */
public class CameraManagerImpl implements aa, PositionChangeFinishedCallback, VectorMapController, bp {
    private static final double f8059b;
    private static /* synthetic */ boolean f8060j;
    private final VectorMapControllerAdapter f8061c;
    private final VectorMapView f8062d;
    private final Handler f8063e;
    private ICancelableCallback f8064f;
    private ac f8065g;
    private Collection<ac> f8066h;
    private int f8067i;

    /* renamed from: com.google.android.m4b.maps.y.a.1 */
    class CameraManagerImpl implements Runnable {
        private /* synthetic */ CameraPosition f8055a;
        private /* synthetic */ CameraManagerImpl f8056b;

        CameraManagerImpl(CameraManagerImpl cameraManagerImpl, CameraPosition cameraPosition) {
            this.f8056b = cameraManagerImpl;
            this.f8055a = cameraPosition;
        }

        public final void run() {
            CameraManagerImpl.m11603a(this.f8056b, ConversionUtils.m11634a(this.f8056b.f8061c.m11954a(this.f8055a, -1.0f)));
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.a.2 */
    class CameraManagerImpl implements Runnable {
        private /* synthetic */ CameraPosition f8057a;
        private /* synthetic */ CameraManagerImpl f8058b;

        CameraManagerImpl(CameraManagerImpl cameraManagerImpl, CameraPosition cameraPosition) {
            this.f8058b = cameraManagerImpl;
            this.f8057a = cameraPosition;
        }

        public final void run() {
            CameraManagerImpl.m11605b(this.f8058b, ConversionUtils.m11634a(this.f8058b.f8061c.m11954a(this.f8057a, -1.0f)));
        }
    }

    static {
        boolean z;
        if (CameraManagerImpl.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        f8060j = z;
        f8059b = 1.0d / Math.log(2.0d);
    }

    static /* synthetic */ void m11603a(CameraManagerImpl cameraManagerImpl, com.google.android.m4b.maps.model.CameraPosition cameraPosition) {
        if (cameraManagerImpl.f8064f != null) {
            try {
                cameraManagerImpl.m11606e().m11215a();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        if (cameraManagerImpl.f8065g != null) {
            try {
                cameraManagerImpl.f8065g.m9031a(cameraPosition);
            } catch (RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        }
    }

    static /* synthetic */ void m11605b(CameraManagerImpl cameraManagerImpl, com.google.android.m4b.maps.model.CameraPosition cameraPosition) {
        if (cameraManagerImpl.f8066h != null) {
            for (ac a : cameraManagerImpl.f8066h) {
                try {
                    a.m9031a(cameraPosition);
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
        }
    }

    private static int m11600a(int i) {
        if (i == -1) {
            return 330;
        }
        return Math.max(0, i);
    }

    private static int m11604b(int i) {
        if (i == -1) {
            return -1;
        }
        return Math.max(0, i);
    }

    public CameraManagerImpl(VectorMapView vectorMapView, VectorMapControllerAdapter vectorMapControllerAdapter, Handler handler) {
        this.f8062d = vectorMapView;
        this.f8061c = vectorMapControllerAdapter;
        this.f8063e = handler;
        this.f8061c.m11958a((aa) this);
        this.f8061c.m11959a((PositionChangeFinishedCallback) this);
        this.f8061c.m11960a((VectorMapController) this);
    }

    private ICancelableCallback m11606e() {
        ICancelableCallback iCancelableCallback = this.f8064f;
        this.f8064f = null;
        return iCancelableCallback;
    }

    public final void m11621a(ac acVar) {
        this.f8065g = acVar;
    }

    public final void m11625b(ac acVar) {
        if (this.f8066h == null) {
            this.f8066h = ar.m2515a();
        }
        this.f8066h.add(acVar);
    }

    public final void m11628c(ac acVar) {
        this.f8066h.remove(acVar);
        if (this.f8066h.isEmpty()) {
            this.f8066h = null;
        }
    }

    public final void m11615a(CameraManager cameraManager, int i, ICancelableCallback iCancelableCallback, be beVar) {
        boolean z = true;
        boolean z2 = i != 0 || iCancelableCallback == null;
        Preconditions.m1823a(z2, (Object) "Callback supplied with instantaneous camera movement");
        if (this.f8067i != 0) {
            z = false;
        }
        Preconditions.m1829b(z, (Object) "Camera moved during a cancellation");
        cameraManager.m8928a(this, i, beVar);
        if (f8060j || this.f8067i == 0) {
            this.f8064f = iCancelableCallback;
            return;
        }
        throw new AssertionError();
    }

    public final void m11609a() {
        Preconditions.m1829b(this.f8067i == 0, (Object) "Camera stopped during a cancellation");
        this.f8061c.m11955a(0.0f, 0.0f);
    }

    public final void m11614a(CameraPosition cameraPosition) {
        this.f8063e.post(new CameraManagerImpl(this, cameraPosition));
    }

    public final void m11624b(CameraPosition cameraPosition) {
        this.f8063e.post(new CameraManagerImpl(this, cameraPosition));
    }

    public final void m11622b() {
        if (this.f8064f != null) {
            this.f8067i++;
            try {
                m11606e().m11216b();
                this.f8067i--;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            } catch (Throwable th) {
                this.f8067i--;
            }
        }
    }

    public final com.google.android.m4b.maps.model.CameraPosition m11626c() {
        return ConversionUtils.m11634a(this.f8061c.m11964c());
    }

    public final com.google.android.m4b.maps.model.CameraPosition m11608a(LatLngBounds latLngBounds) {
        return ConversionUtils.m11634a(CameraManagerImpl.m11601a(latLngBounds, (double) (this.f8062d.getWidth() - this.f8061c.m11953a()), (double) (this.f8062d.getHeight() - this.f8061c.m11962b()), (double) this.f8062d.getResources().getDisplayMetrics().density));
    }

    public final void m11611a(float f, int i) {
        this.f8061c.m11951a(f, CameraManagerImpl.m11600a(i));
    }

    public final void m11623b(float f, int i) {
        this.f8061c.m11963b(Math.min(m11607a(m11626c().f7529a), Math.max(this.f8061c.m11965d(), this.f8061c.m11966e())) + f, CameraManagerImpl.m11600a(-1));
    }

    public final void m11612a(float f, int i, int i2, int i3) {
        this.f8061c.m11950a(f, (float) i, (float) i2, CameraManagerImpl.m11600a(i3));
    }

    public final void m11627c(float f, int i) {
        this.f8061c.m11963b(f, CameraManagerImpl.m11600a(i));
    }

    public final void m11610a(float f, float f2, int i) {
        VectorMapControllerAdapter vectorMapControllerAdapter = this.f8061c;
        CameraPositionProvider a = com.google.android.m4b.maps.av.VectorMapController.m7307a(this.f8061c.m11964c(), this.f8062d.m11872t(), f, f2);
        int b = CameraManagerImpl.m11604b(i);
        this.f8061c.m11961a(a, b, b);
    }

    public final void m11616a(com.google.android.m4b.maps.model.CameraPosition cameraPosition, int i) {
        int b = CameraManagerImpl.m11604b(i);
        this.f8061c.m11961a(new CameraPosition(ConversionUtils.m11637a(cameraPosition.f7529a), cameraPosition.f7530b, cameraPosition.f7531c, cameraPosition.f7532d, 0.0f), b, b);
    }

    public final void m11618a(LatLng latLng, int i) {
        CameraPosition c = this.f8061c.m11964c();
        CameraPositionProvider cameraPosition = new CameraPosition(ConversionUtils.m11637a(latLng), c.m7457a(), c.m7461d(), c.m7462e(), c.m7463f());
        int b = CameraManagerImpl.m11604b(i);
        this.f8061c.m11961a(cameraPosition, b, b);
    }

    public final void m11617a(LatLng latLng, float f, int i) {
        CameraPosition c = this.f8061c.m11964c();
        CameraPositionProvider cameraPosition = new CameraPosition(ConversionUtils.m11637a(latLng), f, c.m7461d(), c.m7462e(), c.m7463f());
        int b = CameraManagerImpl.m11604b(i);
        this.f8061c.m11961a(cameraPosition, b, b);
    }

    public final void m11619a(LatLngBounds latLngBounds, int i, int i2) {
        int width = this.f8062d.getWidth();
        int height = this.f8062d.getHeight();
        boolean z = (width == 0 || height == 0) ? false : true;
        Preconditions.m1829b(z, (Object) "Error using newLatLngBounds(LatLngBounds, int): Map size can't be 0. Most likely, layout has not yet occured for the map view.  Either wait until layout has occurred or use newLatLngBounds(LatLngBounds, int, int, int) which allows you to specify the map's dimensions.");
        m11620a(latLngBounds, width, height, i, i2);
    }

    public final void m11620a(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        double d = ((double) i) - ((double) (i3 * 2));
        double d2 = ((double) i2) - ((double) (i3 * 2));
        boolean z = d > 0.0d && d2 > 0.0d;
        Preconditions.m1829b(z, (Object) "View size is too small after padding");
        CameraPositionProvider a = CameraManagerImpl.m11601a(latLngBounds, d - ((double) this.f8061c.m11953a()), d2 - ((double) this.f8061c.m11962b()), (double) this.f8062d.getResources().getDisplayMetrics().density);
        int b = CameraManagerImpl.m11604b(i4);
        this.f8061c.m11961a(a, b, b);
    }

    private static CameraPosition m11601a(LatLngBounds latLngBounds, double d, double d2, double d3) {
        int f;
        double d4 = 256.0d * d3;
        Point b = ConversionUtils.m11640b(latLngBounds.f7562b);
        Point b2 = ConversionUtils.m11640b(latLngBounds.f7561a);
        if (b.m5958f() < b2.m5958f()) {
            f = (1073741824 - b2.m5958f()) + b.m5958f();
        } else {
            f = b.m5958f() - b2.m5958f();
        }
        int g = b.m5960g() - b2.m5960g();
        d4 = 30.0d - (Math.log(Math.max((((double) f) * d4) / d, (d4 * ((double) g)) / d2)) * f8059b);
        return new CameraPosition(new Point(((f / 2) + b2.m5958f()) % 1073741824, b2.m5960g() + (g / 2)), (float) d4, 0.0f, 0.0f, 0.0f);
    }

    public final float m11607a(LatLng latLng) {
        return this.f8061c.m11952a(ConversionUtils.m11640b(latLng));
    }

    public final float m11629d() {
        return this.f8061c.m11965d();
    }

    public final void m11613a(int i, int i2, int i3, int i4) {
        this.f8061c.m11956a(i, i2, i3, i4);
    }
}
