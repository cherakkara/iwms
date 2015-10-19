package com.olacabs.customer.p078c;

import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.google.android.gms.common.api.C0226d;
import com.google.android.gms.location.C0532g;
import com.google.android.m4b.maps.CameraUpdate;
import com.google.android.m4b.maps.CameraUpdateFactory;
import com.google.android.m4b.maps.GoogleMap.GoogleMap;
import com.google.android.m4b.maps.GoogleMapOptions;
import com.google.android.m4b.maps.OnMapReadyCallback;
import com.google.android.m4b.maps.SupportMapFragment;
import com.google.android.m4b.maps.UiSettings;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.CameraPosition.C0594a;
import com.google.android.m4b.maps.model.LatLng;
import com.google.p025a.p026a.Preconditions;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.p076d.bm;
import com.olacabs.customer.p078c.FetchAddress.FetchAddress;
import com.olacabs.customer.ui.widgets.LocationButton;
import com.olacabs.customer.utils.BackgroundLooper;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.Locale;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

/* renamed from: com.olacabs.customer.c.d */
public class OlaMapFragment extends SupportMapFragment implements GoogleMap, FetchAddress, MapDragListener {
    private static final JoinPoint f9421o = null;
    private static final JoinPoint f9422p = null;
    private static final JoinPoint f9423q = null;
    private com.google.android.m4b.maps.GoogleMap f9424a;
    private LocationButton f9425b;
    private OnClickListener f9426c;
    private C0226d f9427d;
    private OnMapReadyCallback f9428e;
    private GoogleMap f9429f;
    private boolean f9430g;
    private DataManager f9431h;
    private LatLng f9432i;
    private Handler f9433j;
    private Geocoder f9434k;
    private FetchAddress f9435l;
    private MapDragListener f9436m;
    private boolean f9437n;

    /* renamed from: com.olacabs.customer.c.d.1 */
    class OlaMapFragment implements OnMapReadyCallback {
        final /* synthetic */ OlaMapFragment f9408a;

        OlaMapFragment(OlaMapFragment olaMapFragment) {
            this.f9408a = olaMapFragment;
        }

        public void m13354a(com.google.android.m4b.maps.GoogleMap googleMap) {
            if (googleMap != null && this.f9408a.getActivity() != null) {
                this.f9408a.f9424a = googleMap;
                UiSettings d = this.f9408a.f9424a.m10077d();
                d.m10562a(false);
                d.m10563b(false);
                d.m10568g(false);
                d.m10567f(false);
                d.m10564c(false);
                this.f9408a.f9424a.m10074a(this.f9408a.f9437n);
                if (this.f9408a.f9428e != null) {
                    this.f9408a.f9428e.m10467a(googleMap);
                }
                googleMap.m10072a(this.f9408a);
                if (this.f9408a.f9435l == null) {
                    return;
                }
                if (googleMap.m10075b() == 6.0f || this.f9408a.f9432i == null) {
                    this.f9408a.f9435l.m13351a(this.f9408a.getString(R.string.booking_address_not_found));
                    return;
                }
                this.f9408a.m13382b(new LatLng(this.f9408a.f9432i.f7554a, this.f9408a.f9432i.f7555b));
            }
        }
    }

    /* renamed from: com.olacabs.customer.c.d.2 */
    class OlaMapFragment implements OnClickListener {
        final /* synthetic */ OlaMapFragment f9409a;

        OlaMapFragment(OlaMapFragment olaMapFragment) {
            this.f9409a = olaMapFragment;
        }

        public void onClick(View view) {
            this.f9409a.m13402h();
            if (this.f9409a.f9426c != null) {
                this.f9409a.f9426c.onClick(view);
            }
        }
    }

    /* renamed from: com.olacabs.customer.c.d.a */
    public static class OlaMapFragment {
        private LocationButton f9410a;
        private OnClickListener f9411b;
        private C0226d f9412c;
        private OnMapReadyCallback f9413d;
        private GoogleMap f9414e;
        private boolean f9415f;
        private LatLng f9416g;
        private float f9417h;
        private boolean f9418i;
        private FetchAddress f9419j;
        private MapDragListener f9420k;

        public OlaMapFragment() {
            this.f9417h = -1.0f;
            this.f9418i = true;
        }

        public OlaMapFragment m13360a(LatLng latLng) {
            this.f9416g = latLng;
            return this;
        }

        public OlaMapFragment m13355a(float f) {
            this.f9417h = f;
            return this;
        }

        public OlaMapFragment m13363a(LocationButton locationButton) {
            Preconditions.m1819a((Object) locationButton, "%s cannot be null", "location button");
            this.f9410a = locationButton;
            return this;
        }

        public OlaMapFragment m13364a(boolean z) {
            this.f9418i = z;
            return this;
        }

        public OlaMapFragment m13356a(OnClickListener onClickListener) {
            Preconditions.m1819a((Object) onClickListener, "%s cannot be null", "OnClickListener");
            this.f9411b = onClickListener;
            return this;
        }

        public OlaMapFragment m13357a(C0226d c0226d) {
            Preconditions.m1819a((Object) c0226d, "%s cannot be null", "GoogleApiClient");
            this.f9412c = c0226d;
            return this;
        }

        public OlaMapFragment m13366b(boolean z) {
            this.f9415f = z;
            return this;
        }

        public OlaMapFragment m13359a(OnMapReadyCallback onMapReadyCallback) {
            Preconditions.m1819a((Object) onMapReadyCallback, "%s cannot be null", "OnMapReadyCallback listener");
            this.f9413d = onMapReadyCallback;
            return this;
        }

        public OlaMapFragment m13358a(GoogleMap googleMap) {
            Preconditions.m1819a((Object) googleMap, "%s cannot be null", "OnCameraChangeListener");
            this.f9414e = googleMap;
            return this;
        }

        public OlaMapFragment m13362a(MapDragListener mapDragListener) {
            Preconditions.m1819a((Object) mapDragListener, "%s cannot be null", "MapDragListener");
            this.f9420k = mapDragListener;
            return this;
        }

        public OlaMapFragment m13361a(FetchAddress fetchAddress) {
            Preconditions.m1819a((Object) fetchAddress, "%s cannot be null", "AddressChangeListener");
            this.f9419j = fetchAddress;
            return this;
        }

        public OlaMapFragment m13365a() {
            if (this.f9416g == null) {
                this.f9416g = new LatLng(Constants.DEFAULT_MAP_LAT, Constants.DEFAULT_MAP_LONG);
            }
            if (this.f9417h == -1.0f) {
                this.f9417h = 6.0f;
            }
            OlaMapFragment a = OlaMapFragment.m13381b(this.f9416g, this.f9417h);
            a.f9432i = this.f9416g;
            a.f9431h = DataManager.m13137a(a.getActivity());
            a.f9425b = this.f9410a;
            a.f9437n = this.f9418i;
            a.f9427d = this.f9412c;
            a.f9430g = this.f9415f;
            a.f9428e = this.f9413d;
            a.f9429f = this.f9414e;
            a.f9435l = this.f9419j;
            a.f9436m = this.f9420k;
            if (this.f9411b != null) {
                a.m13390a(this.f9411b);
            }
            if (this.f9410a != null) {
                this.f9410a.m14816a(this.f9415f ? 0 : 1);
            }
            return a;
        }
    }

    static {
        OlaMapFragment.m13389i();
    }

    private static void m13389i() {
        Factory factory = new Factory("OlaMapFragment.java", OlaMapFragment.class);
        f9421o = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.maps.OlaMapFragment", "android.os.Bundle", "bundle", Trace.NULL, "void"), 95);
        f9422p = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.maps.OlaMapFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), (int) HttpStatus.SC_SWITCHING_PROTOCOLS);
        f9423q = factory.m15070a("method-execution", factory.m15071a("1", "onStart", "com.olacabs.customer.maps.OlaMapFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 110);
    }

    private static OlaMapFragment m13381b(LatLng latLng, float f) {
        CameraPosition a = new C0594a().m10704a(latLng).m10703a(f).m10705a();
        Parcelable googleMapOptions = new GoogleMapOptions();
        googleMapOptions.m4631a(a);
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googleMapOptions);
        OlaMapFragment olaMapFragment = new OlaMapFragment();
        olaMapFragment.setArguments(bundle);
        return olaMapFragment;
    }

    public void onCreate(Bundle bundle) {
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f9421o, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f9433j = new Handler(BackgroundLooper.m14896a());
        this.f9434k = new Geocoder(getActivity(), Locale.getDefault());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f9422p, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        View touchableWrapper = new TouchableWrapper(getActivity(), this);
        touchableWrapper.addView(onCreateView);
        m13397c();
        return touchableWrapper;
    }

    public void onStart() {
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f9423q, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onStop() {
        super.onStop();
        EventBus.m3a().m17b(this);
    }

    public void m13397c() {
        m10543a(new OlaMapFragment(this));
    }

    public void m13393a(LatLng latLng, boolean z) {
        this.f9430g = z;
        m13377a(latLng);
        if (this.f9425b != null) {
            this.f9425b.m14816a(this.f9430g ? 0 : 1);
        }
    }

    public void m13394a(LatLng latLng, boolean z, int i) {
        this.f9430g = z;
        this.f9432i = latLng;
        m13391a(CameraUpdateFactory.m7791a(new C0594a().m10704a(latLng).m10703a((float) i).m10705a()));
        if (this.f9425b != null) {
            this.f9425b.m14816a(this.f9430g ? 0 : 1);
        }
    }

    public void m13390a(OnClickListener onClickListener) {
        this.f9426c = onClickListener;
        this.f9425b.setOnClickListener(new OlaMapFragment(this));
    }

    public boolean m13398d() {
        return this.f9430g;
    }

    public boolean m13399e() {
        return (this.f9424a == null || getActivity() == null) ? false : true;
    }

    public Location m13400f() {
        if (this.f9427d == null || m13399e()) {
            return C0532g.f2651b.m3918a(this.f9427d);
        }
        return null;
    }

    public LatLng m13401g() {
        if (m13399e()) {
            return this.f9432i;
        }
        return null;
    }

    public void m13402h() {
        if (this.f9427d != null && this.f9427d.m3231d()) {
            Location a = C0532g.f2651b.m3918a(this.f9427d);
            if (a != null) {
                LatLng latLng = new LatLng(a.getLatitude(), a.getLongitude());
                m13382b(latLng);
                m13377a(latLng);
                this.f9430g = false;
                if (this.f9425b != null) {
                    this.f9425b.m14816a(1);
                }
            }
        }
    }

    private void m13377a(LatLng latLng) {
        if (m13399e()) {
            this.f9432i = latLng;
            m13391a(CameraUpdateFactory.m7791a(new C0594a().m10704a(latLng).m10703a(16.0f).m10705a()));
        }
    }

    public void m13391a(CameraUpdate cameraUpdate) {
        if (m13399e()) {
            this.f9424a.m10071a(cameraUpdate);
        }
    }

    public void m13392a(CameraPosition cameraPosition) {
        if (m13399e()) {
            this.f9432i = cameraPosition.f7529a;
            if (this.f9429f != null) {
                this.f9429f.m10061a(cameraPosition);
            }
        }
    }

    public void onEvent(bm bmVar) {
        if (m13399e() && isResumed() && bmVar.getLocation() != null && !this.f9430g) {
            LatLng latLng = new LatLng(bmVar.getLocation().getLatitude(), bmVar.getLocation().getLongitude());
            if (!latLng.equals(this.f9432i)) {
                m13377a(latLng);
                if (this.f9435l != null) {
                    m13382b(latLng);
                }
            }
        }
    }

    public void o_() {
        if (this.f9425b != null) {
            this.f9425b.m14816a(0);
            this.f9430g = true;
        }
        if (this.f9436m != null) {
            this.f9436m.o_();
        }
    }

    public void m13396b() {
        if (this.f9435l != null) {
            m13382b(this.f9432i);
        }
        if (this.f9436m != null) {
            this.f9436m.m13353b();
        }
    }

    private void m13382b(LatLng latLng) {
        this.f9433j.post(new FetchAddress(this.f9434k, latLng, getString(R.string.booking_address_not_found), new WeakReference(this)));
    }

    public void m13395a(String str) {
        if (m13399e() && isResumed() && this.f9435l != null) {
            this.f9435l.m13351a(str);
        }
    }
}
