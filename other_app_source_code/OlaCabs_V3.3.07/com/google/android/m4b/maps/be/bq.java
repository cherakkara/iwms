package com.google.android.m4b.maps.be;

import android.os.Bundle;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.be.bp.CameraManager;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.internal.CameraUpdateParcelable;
import com.google.android.m4b.maps.p042r.ICameraUpdateFactoryDelegate.ICameraUpdateFactoryDelegate;
import com.google.android.m4b.maps.p047g.Preconditions;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* compiled from: CameraUpdateFactoryDelegate */
public class bq extends ICameraUpdateFactoryDelegate {
    private static final Bundle f5865a;
    private static final CameraManager f5866b;
    private static final CameraManager f5867c;

    /* compiled from: CameraUpdateFactoryDelegate */
    /* renamed from: com.google.android.m4b.maps.be.bq.10 */
    class AnonymousClass10 implements CameraManager {
        private /* synthetic */ float f5848a;
        private /* synthetic */ float f5849b;

        AnonymousClass10(bq bqVar, float f, float f2) {
            this.f5848a = f;
            this.f5849b = f2;
        }

        public final void m8949a(bp bpVar, int i, be beVar) {
            beVar.m8835b(UsageLog.CAMERA_UPDATE_SCROLL_BY);
            bpVar.m8932a(this.f5848a, this.f5849b, i);
        }

        public final String toString() {
            return UsageLog.CAMERA_UPDATE_SCROLL_BY;
        }
    }

    /* compiled from: CameraUpdateFactoryDelegate */
    /* renamed from: com.google.android.m4b.maps.be.bq.11 */
    class AnonymousClass11 implements CameraManager {
        private /* synthetic */ CameraPosition f5850a;

        AnonymousClass11(bq bqVar, CameraPosition cameraPosition) {
            this.f5850a = cameraPosition;
        }

        public final void m8950a(bp bpVar, int i, be beVar) {
            beVar.m8835b(UsageLog.CAMERA_UPDATE_NEW_CAMERA_POSITION);
            bpVar.m8937a(this.f5850a, i);
        }

        public final String toString() {
            return UsageLog.CAMERA_UPDATE_NEW_CAMERA_POSITION;
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bq.1 */
    static class CameraUpdateFactoryDelegate implements CameraManager {
        CameraUpdateFactoryDelegate() {
        }

        public final void m8951a(bp bpVar, int i, be beVar) {
            beVar.m8835b(UsageLog.CAMERA_UPDATE_ZOOM_IN);
            bpVar.m8933a((float) br.DEFAULT_BACKOFF_MULT, i);
        }

        public final String toString() {
            return UsageLog.CAMERA_UPDATE_ZOOM_IN;
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bq.2 */
    class CameraUpdateFactoryDelegate implements CameraManager {
        private /* synthetic */ LatLng f5851a;

        CameraUpdateFactoryDelegate(bq bqVar, LatLng latLng) {
            this.f5851a = latLng;
        }

        public final void m8952a(bp bpVar, int i, be beVar) {
            beVar.m8835b(UsageLog.CAMERA_UPDATE_NEW_LATLNG);
            bpVar.m8939a(this.f5851a, i);
        }

        public final String toString() {
            return UsageLog.CAMERA_UPDATE_NEW_LATLNG;
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bq.3 */
    class CameraUpdateFactoryDelegate implements CameraManager {
        private /* synthetic */ LatLng f5852a;
        private /* synthetic */ float f5853b;

        CameraUpdateFactoryDelegate(bq bqVar, LatLng latLng, float f) {
            this.f5852a = latLng;
            this.f5853b = f;
        }

        public final void m8953a(bp bpVar, int i, be beVar) {
            beVar.m8835b(UsageLog.CAMERA_UPDATE_NEW_LATLNG_ZOOM);
            bpVar.m8938a(this.f5852a, this.f5853b, i);
        }

        public final String toString() {
            return UsageLog.CAMERA_UPDATE_NEW_LATLNG_ZOOM;
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bq.4 */
    class CameraUpdateFactoryDelegate implements CameraManager {
        private /* synthetic */ LatLngBounds f5854a;
        private /* synthetic */ int f5855b;

        CameraUpdateFactoryDelegate(bq bqVar, LatLngBounds latLngBounds, int i) {
            this.f5854a = latLngBounds;
            this.f5855b = i;
        }

        public final void m8954a(bp bpVar, int i, be beVar) {
            beVar.m8835b(UsageLog.CAMERA_UPDATE_NEW_LATLNG_BOUNDS);
            bpVar.m8940a(this.f5854a, this.f5855b, i);
        }

        public final String toString() {
            return UsageLog.CAMERA_UPDATE_NEW_LATLNG_BOUNDS;
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bq.5 */
    class CameraUpdateFactoryDelegate implements CameraManager {
        private /* synthetic */ LatLngBounds f5856a;
        private /* synthetic */ int f5857b;
        private /* synthetic */ int f5858c;
        private /* synthetic */ int f5859d;

        CameraUpdateFactoryDelegate(bq bqVar, LatLngBounds latLngBounds, int i, int i2, int i3) {
            this.f5856a = latLngBounds;
            this.f5857b = i;
            this.f5858c = i2;
            this.f5859d = i3;
        }

        public final void m8955a(bp bpVar, int i, be beVar) {
            beVar.m8835b(UsageLog.CAMERA_UPDATE_NEW_LATLNG_BOUNDS_WITH_DIMENSIONS);
            bpVar.m8941a(this.f5856a, this.f5857b, this.f5858c, this.f5859d, i);
        }

        public final String toString() {
            return UsageLog.CAMERA_UPDATE_NEW_LATLNG_BOUNDS_WITH_DIMENSIONS;
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bq.6 */
    static class CameraUpdateFactoryDelegate implements CameraManager {
        CameraUpdateFactoryDelegate() {
        }

        public final void m8956a(bp bpVar, int i, be beVar) {
            beVar.m8835b(UsageLog.CAMERA_UPDATE_ZOOM_OUT);
            bpVar.m8933a(-1.0f, i);
        }

        public final String toString() {
            return UsageLog.CAMERA_UPDATE_ZOOM_OUT;
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bq.7 */
    class CameraUpdateFactoryDelegate implements CameraManager {
        private /* synthetic */ float f5860a;

        CameraUpdateFactoryDelegate(bq bqVar, float f) {
            this.f5860a = f;
        }

        public final void m8957a(bp bpVar, int i, be beVar) {
            beVar.m8835b(UsageLog.CAMERA_UPDATE_ZOOM_TO);
            bpVar.m8946c(this.f5860a, i);
        }

        public final String toString() {
            return UsageLog.CAMERA_UPDATE_ZOOM_TO;
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bq.8 */
    class CameraUpdateFactoryDelegate implements CameraManager {
        private /* synthetic */ float f5861a;

        CameraUpdateFactoryDelegate(bq bqVar, float f) {
            this.f5861a = f;
        }

        public final void m8958a(bp bpVar, int i, be beVar) {
            beVar.m8835b(UsageLog.CAMERA_UPDATE_ZOOM_BY);
            bpVar.m8933a(this.f5861a, i);
        }

        public final String toString() {
            return UsageLog.CAMERA_UPDATE_ZOOM_BY;
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bq.9 */
    class CameraUpdateFactoryDelegate implements CameraManager {
        private /* synthetic */ float f5862a;
        private /* synthetic */ int f5863b;
        private /* synthetic */ int f5864c;

        CameraUpdateFactoryDelegate(bq bqVar, float f, int i, int i2) {
            this.f5862a = f;
            this.f5863b = i;
            this.f5864c = i2;
        }

        public final void m8959a(bp bpVar, int i, be beVar) {
            beVar.m8835b(UsageLog.CAMERA_UPDATE_ZOOM_BY_FIXING);
            bpVar.m8934a(this.f5862a, this.f5863b, this.f5864c, i);
        }

        public final String toString() {
            return UsageLog.CAMERA_UPDATE_ZOOM_BY_FIXING;
        }
    }

    static {
        bq.class.getSimpleName();
        f5865a = new Bundle(0);
        f5866b = new CameraUpdateFactoryDelegate();
        f5867c = new CameraUpdateFactoryDelegate();
    }

    public final IObjectWrapper m8972a() {
        return ObjectWrapper.m10130a(f5866b);
    }

    public final IObjectWrapper m8982b() {
        return ObjectWrapper.m10130a(f5867c);
    }

    public final IObjectWrapper m8973a(float f) {
        return ObjectWrapper.m10130a(new CameraUpdateFactoryDelegate(this, f));
    }

    public final IObjectWrapper m8983b(float f) {
        return ObjectWrapper.m10130a(new CameraUpdateFactoryDelegate(this, f));
    }

    public final IObjectWrapper m8975a(float f, int i, int i2) {
        return ObjectWrapper.m10130a(new CameraUpdateFactoryDelegate(this, f, i, i2));
    }

    public final IObjectWrapper m8974a(float f, float f2) {
        return ObjectWrapper.m10130a(new AnonymousClass10(this, f, f2));
    }

    public final IObjectWrapper m8976a(CameraPosition cameraPosition) {
        return ObjectWrapper.m10130a(new AnonymousClass11(this, cameraPosition));
    }

    public final IObjectWrapper m8977a(LatLng latLng) {
        return ObjectWrapper.m10130a(new CameraUpdateFactoryDelegate(this, latLng));
    }

    public final IObjectWrapper m8978a(LatLng latLng, float f) {
        return ObjectWrapper.m10130a(new CameraUpdateFactoryDelegate(this, latLng, f));
    }

    public final IObjectWrapper m8979a(LatLngBounds latLngBounds, int i) {
        return ObjectWrapper.m10130a(new CameraUpdateFactoryDelegate(this, latLngBounds, i));
    }

    public final IObjectWrapper m8980a(LatLngBounds latLngBounds, int i, int i2, int i3) {
        return ObjectWrapper.m10130a(new CameraUpdateFactoryDelegate(this, latLngBounds, i, i2, i3));
    }

    public final IObjectWrapper m8981a(CameraUpdateParcelable cameraUpdateParcelable) {
        int b = cameraUpdateParcelable.m10847b();
        Bundle c = cameraUpdateParcelable.m10848c();
        c.setClassLoader(LatLng.class.getClassLoader());
        switch (b) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                m8971a(c, 0);
                return m8972a();
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                m8971a(c, 0);
                return m8982b();
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                m8971a(c, 1);
                return m8973a(c.getFloat("zoomLevel"));
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                m8971a(c, 1);
                return m8983b(c.getFloat(Constants.ARG_AMOUNT));
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                m8971a(c, 3);
                return m8975a(c.getFloat(Constants.ARG_AMOUNT), c.getInt("screenFocusX"), c.getInt("screenFocusY"));
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                m8971a(c, 2);
                return m8974a(c.getFloat("xPixel"), c.getFloat("yPixel"));
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                m8971a(c, 1);
                return m8976a((CameraPosition) c.getParcelable("cameraPosition"));
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                m8971a(c, 1);
                return m8977a((LatLng) c.getParcelable("latLng"));
            case HTTP.HT /*9*/:
                m8971a(c, 2);
                return m8978a((LatLng) c.getParcelable("latLng"), c.getFloat("zoomLevel"));
            case HTTP.LF /*10*/:
                m8971a(c, 2);
                return m8979a((LatLngBounds) c.getParcelable("latLngBounds"), c.getInt("padding"));
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                m8971a(c, 4);
                return m8980a((LatLngBounds) c.getParcelable("latLngBounds"), c.getInt("width"), c.getInt("height"), c.getInt("padding"));
            default:
                return null;
        }
    }

    private static void m8971a(Bundle bundle, int i) {
        int size = bundle.size();
        Preconditions.m10466b(size == i, "Wrong number of parameters: expected " + i + " but found " + size);
    }
}
