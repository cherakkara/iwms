package com.google.android.m4b.maps.be;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.StreetViewPanoramaOptions;
import com.google.android.m4b.maps.be.ap.ReverseGeocodeDataRequest;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.bj.StreetViewSurfaceView;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.StreetViewPanoramaCamera;
import com.google.android.m4b.maps.model.StreetViewPanoramaLocation;
import com.google.android.m4b.maps.model.StreetViewPanoramaOrientation;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaCameraChangeListener;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaChangeListener;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaClickListener;
import com.google.android.m4b.maps.p042r.IStreetViewPanoramaDelegate.IStreetViewPanoramaDelegate;
import com.google.android.m4b.maps.p042r.MapStateHelper;
import com.google.android.m4b.maps.p061y.ConversionUtils;
import com.google.p025a.p026a.Preconditions;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;

/* compiled from: StreetViewPanoramaImpl */
public final class at extends IStreetViewPanoramaDelegate implements OnClickListener {
    private static StreetViewPanoramaCamera f5681j;
    private final av f5682a;
    private final ba f5683b;
    private final StreetViewPanoramaOptions f5684c;
    private final FrameLayout f5685d;
    private final au f5686e;
    private final Context f5687f;
    private final be f5688g;
    private StreetViewPanoramaImpl f5689h;
    private final ax f5690i;

    /* renamed from: com.google.android.m4b.maps.be.at.a */
    public interface StreetViewPanoramaImpl {
        void m8644a(String str, StreetViewPanoramaLocation streetViewPanoramaLocation);
    }

    /* renamed from: com.google.android.m4b.maps.be.at.1 */
    class StreetViewPanoramaImpl implements StreetViewPanoramaImpl {
        private /* synthetic */ at f5679a;

        StreetViewPanoramaImpl(at atVar) {
            this.f5679a = atVar;
        }

        public final void m8645a(String str, StreetViewPanoramaLocation streetViewPanoramaLocation) {
            if (streetViewPanoramaLocation != null) {
                this.f5679a.f5686e.m8699a(str);
                this.f5679a.f5686e.m8700a(true);
            } else {
                this.f5679a.f5686e.m8700a(false);
            }
            if (streetViewPanoramaLocation != null && ab.m8297a(this.f5679a.f5687f)) {
                at atVar = this.f5679a;
                DataRequest apVar = new ap(streetViewPanoramaLocation.f7614b, 21.0f);
                apVar.m8584a(new StreetViewPanoramaImpl(atVar));
                DataRequestDispatcher.m11393a().m11451c(apVar);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.at.2 */
    class StreetViewPanoramaImpl implements ReverseGeocodeDataRequest {
        private /* synthetic */ at f5680a;

        StreetViewPanoramaImpl(at atVar) {
            this.f5680a = atVar;
        }

        public final void m8646a(ap apVar) {
            if (apVar.m8589j() > 0) {
                ab.m8296a(this.f5680a.f5682a.m8726k(), this.f5680a.f5687f.getResources().getString(R.YOUR_LOCATION) + ": " + apVar.m8583a(0).m8579a());
            }
        }
    }

    static {
        f5681j = new StreetViewPanoramaCamera(0.0f, 0.0f, 0.0f);
    }

    public static at m8667a(LayoutInflater layoutInflater, StreetViewPanoramaOptions streetViewPanoramaOptions, boolean z) {
        Context applicationContext = layoutInflater.getContext().getApplicationContext();
        bt a = bt.m9042a(applicationContext);
        Context a2 = Initializer.m9389a(applicationContext, a, false);
        FrameLayout frameLayout = new FrameLayout(a2);
        av a3 = StreetViewSurfaceView.m9861a(a2, a);
        au auVar = new au(a2, InternalResourceLoader.m9392a());
        ba b = bb.m8775b();
        be b2 = bf.m8843b();
        Preconditions.m1817a((Object) streetViewPanoramaOptions);
        StreetViewPanoramaCamera g = streetViewPanoramaOptions.m4670g();
        if (g == null) {
            g = f5681j;
        }
        a3.m8713a(streetViewPanoramaOptions.m4673j(), streetViewPanoramaOptions.m4671h(), streetViewPanoramaOptions.m4672i(), ConversionUtils.m11633a(g));
        an.m8572a(a2).m8577a(1);
        ax axVar = new ax(a2);
        frameLayout.addView(a3.m8726k());
        frameLayout.addView(auVar.m8698a());
        b2.m8833a(UsageLog.PANORAMA_CREATED);
        return new at(a2, a3, streetViewPanoramaOptions, b, auVar, frameLayout, b2, axVar);
    }

    private at(Context context, av avVar, StreetViewPanoramaOptions streetViewPanoramaOptions, ba baVar, au auVar, FrameLayout frameLayout, be beVar, ax axVar) {
        this.f5687f = context;
        this.f5682a = avVar;
        this.f5684c = streetViewPanoramaOptions;
        this.f5683b = baVar;
        this.f5686e = auVar;
        this.f5685d = frameLayout;
        this.f5688g = beVar;
        this.f5690i = axVar;
        this.f5689h = new StreetViewPanoramaImpl(this);
        this.f5682a.m8704a(this.f5689h);
        this.f5686e.m8701b().setOnClickListener(this);
        if (this.f5684c.m4674k() != null) {
            m8687c(this.f5684c.m4674k().booleanValue());
        }
        if (this.f5684c.m4675l() != null) {
            m8682a(this.f5684c.m4675l().booleanValue());
        }
        if (this.f5684c.m4676m() != null) {
            m8685b(this.f5684c.m4676m().booleanValue());
        }
        if (this.f5684c.m4677n() != null) {
            m8689d(this.f5684c.m4677n().booleanValue());
        }
    }

    public final void m8674a(Bundle bundle) {
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) MapStateHelper.m11180a(bundle, "camera");
        if (streetViewPanoramaCamera == null) {
            streetViewPanoramaCamera = this.f5684c.m4670g() != null ? this.f5684c.m4670g() : f5681j;
        }
        av avVar = this.f5682a;
        String string = (bundle == null || !bundle.containsKey(Constants.EXTRA_POSITION)) ? Trace.NULL : bundle.getString(Constants.EXTRA_POSITION);
        avVar.m8708a(streetViewPanoramaCamera, string);
    }

    public final void m8673a() {
        this.f5682a.m8717c();
    }

    public final void m8683b() {
        this.f5682a.m8715b();
    }

    public final void m8686c() {
        this.f5688g.m8832a();
    }

    public final void m8684b(Bundle bundle) {
        MapStateHelper.m11181a(bundle, "StreetViewPanoramaOptions", this.f5684c);
        MapStateHelper.m11181a(bundle, "camera", m8695j());
        if (this.f5682a.m8719d() != null) {
            bundle.putString(Constants.EXTRA_POSITION, this.f5682a.m8719d().f7615c);
        }
    }

    public final View m8688d() {
        return this.f5685d;
    }

    public final boolean m8690e() {
        return this.f5684c.m4678o() != null && this.f5684c.m4678o().booleanValue();
    }

    public final boolean m8691f() {
        this.f5683b.m8774a();
        return this.f5682a.m8722g();
    }

    public final void m8682a(boolean z) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_ENABLE_ZOOM);
        this.f5682a.m8714a(z);
    }

    public final boolean m8692g() {
        this.f5683b.m8774a();
        return this.f5682a.m8723h();
    }

    public final void m8685b(boolean z) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_ENABLE_PANNING);
        this.f5682a.m8716b(z);
    }

    public final boolean m8693h() {
        this.f5683b.m8774a();
        return this.f5682a.m8724i();
    }

    public final void m8687c(boolean z) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_ENABLE_NAVIGATION);
        this.f5682a.m8718c(z);
    }

    public final boolean m8694i() {
        this.f5683b.m8774a();
        return this.f5682a.m8725j();
    }

    public final void m8689d(boolean z) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_ENABLE_STREET_NAMES);
        this.f5682a.m8720d(z);
    }

    public final void m8677a(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_ANIMATE_TO);
        this.f5682a.m8707a(streetViewPanoramaCamera, j);
    }

    public final StreetViewPanoramaCamera m8695j() {
        this.f5683b.m8774a();
        return this.f5682a.m8721e();
    }

    public final void m8681a(String str) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_SET_POSITION_WITH_ID);
        this.f5682a.m8712a(str);
    }

    public final void m8675a(LatLng latLng) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_SET_POSITION);
        this.f5682a.m8705a(latLng);
    }

    public final void m8676a(LatLng latLng, int i) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_SET_POSITION_WITH_RADIUS);
        this.f5682a.m8706a(latLng, i);
    }

    public final StreetViewPanoramaLocation m8696k() {
        this.f5683b.m8774a();
        return this.f5682a.m8719d();
    }

    public final void m8679a(IOnStreetViewPanoramaChangeListener iOnStreetViewPanoramaChangeListener) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_SET_CHANGE_LISTENER);
        this.f5682a.m8710a(iOnStreetViewPanoramaChangeListener);
    }

    public final void m8678a(IOnStreetViewPanoramaCameraChangeListener iOnStreetViewPanoramaCameraChangeListener) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_SET_CAMERA_CHANGE_LISTENER);
        this.f5682a.m8709a(iOnStreetViewPanoramaCameraChangeListener);
    }

    public final void m8680a(IOnStreetViewPanoramaClickListener iOnStreetViewPanoramaClickListener) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_SET_CLICK_LISTENER);
        this.f5682a.m8711a(iOnStreetViewPanoramaClickListener);
    }

    public final StreetViewPanoramaOrientation m8672a(IObjectWrapper iObjectWrapper) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_PROJECT_TO_ORIENTATION);
        Point point = (Point) ObjectWrapper.m10131a(iObjectWrapper);
        return this.f5682a.m8702a(point.x, point.y);
    }

    public final IObjectWrapper m8671a(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.f5683b.m8774a();
        this.f5688g.m8835b(UsageLog.PANORAMA_PROJECT_TO_POINT);
        return ObjectWrapper.m10130a(this.f5682a.m8703a(streetViewPanoramaOrientation));
    }

    public final void onClick(View view) {
        if (view == this.f5686e.m8701b()) {
            this.f5690i.m8746a(this.f5682a.m8719d(), this.f5682a.m8721e());
        }
    }
}
