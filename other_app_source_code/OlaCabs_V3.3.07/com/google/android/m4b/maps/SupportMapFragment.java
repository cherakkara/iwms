package com.google.android.m4b.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.m4b.maps.ca.GooglePlayServicesNotAvailableException;
import com.google.android.m4b.maps.cc.DeferredLifecycleHelper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.cc.OnDelegateCreatedListener;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.C0593l;
import com.google.android.m4b.maps.p042r.IGoogleMapDelegate;
import com.google.android.m4b.maps.p042r.IMapFragmentDelegate;
import com.google.android.m4b.maps.p042r.MapStateHelper;
import com.google.android.m4b.maps.p042r.MapsDynamicJar;
import com.google.android.m4b.maps.p042r.aj.IOnMapReadyCallback;
import com.google.android.m4b.maps.p047g.Preconditions;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.List;

@Instrumented
/* renamed from: com.google.android.m4b.maps.j */
public class SupportMapFragment extends Fragment implements TraceFieldInterface {
    private final SupportMapFragment f7445a;
    private GoogleMap f7446b;

    /* renamed from: com.google.android.m4b.maps.j.a */
    static class SupportMapFragment implements C0593l {
        private final Fragment f7434a;
        private final IMapFragmentDelegate f7435b;

        /* renamed from: com.google.android.m4b.maps.j.a.1 */
        class SupportMapFragment extends IOnMapReadyCallback {
            private /* synthetic */ OnMapReadyCallback f7433a;

            SupportMapFragment(SupportMapFragment supportMapFragment, OnMapReadyCallback onMapReadyCallback) {
                this.f7433a = onMapReadyCallback;
            }

            public final void m10518a(IGoogleMapDelegate iGoogleMapDelegate) {
                this.f7433a.m10467a(new GoogleMap(iGoogleMapDelegate));
            }
        }

        public SupportMapFragment(Fragment fragment, IMapFragmentDelegate iMapFragmentDelegate) {
            this.f7435b = (IMapFragmentDelegate) Preconditions.m10459a((Object) iMapFragmentDelegate);
            this.f7434a = (Fragment) Preconditions.m10459a((Object) fragment);
        }

        public final void m10521a(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.f7435b.m9450a(ObjectWrapper.m10130a((Object) activity), (GoogleMapOptions) bundle.getParcelable("MapOptions"), bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void m10522a(Bundle bundle) {
            if (bundle == null) {
                try {
                    bundle = new Bundle();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
            Bundle arguments = this.f7434a.getArguments();
            if (arguments != null && arguments.containsKey("MapOptions")) {
                MapStateHelper.m11181a(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
            }
            this.f7435b.m9449a(bundle);
        }

        public final View m10519a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) ObjectWrapper.m10131a(this.f7435b.m9447a(ObjectWrapper.m10130a((Object) layoutInflater), ObjectWrapper.m10130a((Object) viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void m10520a() {
            try {
                this.f7435b.m9452b();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void m10524b() {
            try {
                this.f7435b.m9454c();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void m10526c() {
            try {
                this.f7435b.m9455d();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void m10527d() {
            try {
                this.f7435b.m9456e();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void m10528e() {
            try {
                this.f7435b.m9457f();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void m10525b(Bundle bundle) {
            try {
                this.f7435b.m9453b(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final IMapFragmentDelegate m10529f() {
            return this.f7435b;
        }

        public final void m10523a(OnMapReadyCallback onMapReadyCallback) {
            try {
                this.f7435b.m9451a(new SupportMapFragment(this, onMapReadyCallback));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.j.b */
    static class SupportMapFragment extends DeferredLifecycleHelper<SupportMapFragment> {
        private final Fragment f7436a;
        private OnDelegateCreatedListener<SupportMapFragment> f7437b;
        private Activity f7438c;
        private final List<OnMapReadyCallback> f7439d;

        static /* synthetic */ void m10530a(SupportMapFragment supportMapFragment, Activity activity) {
            supportMapFragment.f7438c = activity;
            supportMapFragment.m10531a();
        }

        SupportMapFragment(Fragment fragment) {
            this.f7439d = new ArrayList();
            this.f7436a = fragment;
        }

        protected final void m10532a(OnDelegateCreatedListener<SupportMapFragment> onDelegateCreatedListener) {
            this.f7437b = onDelegateCreatedListener;
            m10531a();
        }

        public final void m10531a() {
            if (this.f7438c != null && this.f7437b != null && m10113b() == null) {
                try {
                    MapsInitializer.m10315a(this.f7438c);
                    this.f7437b.m10090a(new SupportMapFragment(this.f7436a, MapsDynamicJar.m11182a(this.f7438c).m11195b(ObjectWrapper.m10130a(this.f7438c))));
                    for (OnMapReadyCallback a : this.f7439d) {
                        ((SupportMapFragment) m10113b()).m10523a(a);
                    }
                    this.f7439d.clear();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }

        public final void m10533a(OnMapReadyCallback onMapReadyCallback) {
            if (m10113b() != null) {
                ((SupportMapFragment) m10113b()).m10523a(onMapReadyCallback);
            } else {
                this.f7439d.add(onMapReadyCallback);
            }
        }
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public SupportMapFragment() {
        this.f7445a = new SupportMapFragment(this);
    }

    @Deprecated
    public final GoogleMap m10542a() {
        this.f7445a.m10531a();
        IMapFragmentDelegate f = this.f7445a.m10113b() == null ? null : ((SupportMapFragment) this.f7445a.m10113b()).m10529f();
        if (f == null) {
            return null;
        }
        try {
            IGoogleMapDelegate a = f.m9448a();
            if (a == null) {
                return null;
            }
            if (this.f7446b == null || this.f7446b.m10069a().asBinder() != a.asBinder()) {
                this.f7446b = new GoogleMap(a);
            }
            return this.f7446b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SupportMapFragment.m10530a(this.f7445a, activity);
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        SupportMapFragment.m10530a(this.f7445a, activity);
        Parcelable a = GoogleMapOptions.m4628a(activity, attributeSet);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", a);
        this.f7445a.m10110a(activity, bundle2, bundle);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("j");
        try {
            TraceMachine.enterMethod(this._nr_trace, "j#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "j#onCreate", null);
                break;
            }
        }
        super.onCreate(bundle);
        this.f7445a.m10111a(bundle);
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "j#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "j#onCreateView", null);
                break;
            }
        }
        View a = this.f7445a.m10109a(layoutInflater, viewGroup, bundle);
        TraceMachine.exitMethod();
        return a;
    }

    public void onResume() {
        super.onResume();
        this.f7445a.m10115c();
    }

    public void onPause() {
        this.f7445a.m10116d();
        super.onPause();
    }

    public void onDestroyView() {
        this.f7445a.m10117e();
        super.onDestroyView();
    }

    public void onDestroy() {
        this.f7445a.m10118f();
        super.onDestroy();
    }

    public void onLowMemory() {
        this.f7445a.m10119g();
        super.onLowMemory();
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.f7445a.m10114b(bundle);
    }

    public void m10543a(OnMapReadyCallback onMapReadyCallback) {
        Preconditions.m10465b("getMapAsync must be called on the main thread.");
        this.f7445a.m10533a(onMapReadyCallback);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
