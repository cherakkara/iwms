package com.google.android.m4b.maps.be;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.m4b.maps.GoogleMapOptions;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.IGoogleMapDelegate;
import com.google.android.m4b.maps.p042r.IMapFragmentDelegate.IMapFragmentDelegate;
import com.google.android.m4b.maps.p042r.MapStateHelper;
import com.google.android.m4b.maps.p042r.aj;
import com.google.p025a.p026a.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.be.o */
public class MapFragmentDelegateImpl extends IMapFragmentDelegate {
    private final MapFragmentDelegateImpl f6057a;
    private cd f6058b;
    private GoogleMapOptions f6059c;
    private final List<aj> f6060d;

    /* renamed from: com.google.android.m4b.maps.be.o.a */
    public interface MapFragmentDelegateImpl {
        cd m9445a(LayoutInflater layoutInflater, GoogleMapOptions googleMapOptions);
    }

    /* renamed from: com.google.android.m4b.maps.be.o.1 */
    static class MapFragmentDelegateImpl implements MapFragmentDelegateImpl {
        private /* synthetic */ boolean f6056a;

        MapFragmentDelegateImpl(boolean z) {
            this.f6056a = z;
        }

        public final cd m9446a(LayoutInflater layoutInflater, GoogleMapOptions googleMapOptions) {
            return by.m9188a(layoutInflater, googleMapOptions, this.f6056a);
        }
    }

    static {
        MapFragmentDelegateImpl.class.getSimpleName();
    }

    private MapFragmentDelegateImpl(MapFragmentDelegateImpl mapFragmentDelegateImpl) {
        this.f6057a = (MapFragmentDelegateImpl) Preconditions.m1817a((Object) mapFragmentDelegateImpl);
        this.f6060d = new ArrayList();
    }

    public static MapFragmentDelegateImpl m9459a(Activity activity) {
        return new MapFragmentDelegateImpl(new MapFragmentDelegateImpl(az.m8758a(activity)));
    }

    @Deprecated
    public final IGoogleMapDelegate m9461a() {
        return this.f6058b;
    }

    public final void m9463a(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions, Bundle bundle) {
        this.f6059c = googleMapOptions;
    }

    public final void m9462a(Bundle bundle) {
        if (this.f6059c == null) {
            this.f6059c = (GoogleMapOptions) MapStateHelper.m11180a(bundle, "MapOptions");
        }
        if (this.f6059c == null) {
            this.f6059c = new GoogleMapOptions();
        }
    }

    public final IObjectWrapper m9460a(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle) {
        Object obj;
        View B;
        if (this.f6058b == null) {
            this.f6058b = this.f6057a.m9445a((LayoutInflater) ObjectWrapper.m10131a(iObjectWrapper), this.f6059c);
            this.f6058b.m9180a(bundle);
            B = this.f6058b.m9177B();
            for (aj a : this.f6060d) {
                try {
                    this.f6058b.m9142a(a);
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
            this.f6060d.clear();
            obj = B;
        } else {
            B = this.f6058b.m9177B();
            ViewGroup viewGroup = (ViewGroup) B.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(B);
            }
            View view = B;
        }
        return ObjectWrapper.m10130a(obj);
    }

    public final void m9465b() {
        this.f6058b.m9181b();
    }

    public final void m9467c() {
        this.f6058b.m9183c();
    }

    public final void m9468d() {
        if (this.f6058b.m9178C()) {
            this.f6058b.m9179a();
            this.f6058b = null;
        }
    }

    public final void m9469e() {
        if (this.f6058b != null) {
            this.f6058b.m9179a();
            this.f6058b = null;
        }
        this.f6059c = null;
    }

    public final void m9470f() {
        this.f6058b.m9184d();
    }

    public final void m9466b(Bundle bundle) {
        if (this.f6058b != null) {
            this.f6058b.m9182b(bundle);
        } else if (this.f6059c != null) {
            MapStateHelper.m11181a(bundle, "MapOptions", this.f6059c);
        }
    }

    public final boolean m9471g() {
        return this.f6058b != null;
    }

    public final void m9464a(aj ajVar) {
        if (this.f6058b != null) {
            try {
                this.f6058b.m9142a(ajVar);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.f6060d.add(ajVar);
    }
}
