package com.google.android.m4b.maps.be;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.m4b.maps.StreetViewPanoramaOptions;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaReadyCallback;
import com.google.android.m4b.maps.p042r.IStreetViewPanoramaDelegate;
import com.google.android.m4b.maps.p042r.IStreetViewPanoramaFragmentDelegate.IStreetViewPanoramaFragmentDelegate;
import com.google.android.m4b.maps.p042r.MapStateHelper;
import com.google.p025a.p026a.Preconditions;

/* compiled from: StreetViewPanoramaFragmentDelegateImpl */
public final class as extends IStreetViewPanoramaFragmentDelegate {
    private final StreetViewPanoramaFragmentDelegateImpl f5675a;
    private at f5676b;
    private StreetViewPanoramaOptions f5677c;
    private Handler f5678d;

    /* renamed from: com.google.android.m4b.maps.be.as.a */
    public interface StreetViewPanoramaFragmentDelegateImpl {
        at m8616a(LayoutInflater layoutInflater, StreetViewPanoramaOptions streetViewPanoramaOptions);
    }

    /* renamed from: com.google.android.m4b.maps.be.as.1 */
    static class StreetViewPanoramaFragmentDelegateImpl implements StreetViewPanoramaFragmentDelegateImpl {
        private /* synthetic */ boolean f5672a;

        StreetViewPanoramaFragmentDelegateImpl(boolean z) {
            this.f5672a = z;
        }

        public final at m8617a(LayoutInflater layoutInflater, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            return at.m8667a(layoutInflater, streetViewPanoramaOptions, this.f5672a);
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.as.2 */
    class StreetViewPanoramaFragmentDelegateImpl implements Runnable {
        private /* synthetic */ IOnStreetViewPanoramaReadyCallback f5673a;
        private /* synthetic */ as f5674b;

        StreetViewPanoramaFragmentDelegateImpl(as asVar, IOnStreetViewPanoramaReadyCallback iOnStreetViewPanoramaReadyCallback) {
            this.f5674b = asVar;
            this.f5673a = iOnStreetViewPanoramaReadyCallback;
        }

        public final void run() {
            try {
                if (this.f5674b.f5676b != null) {
                    this.f5673a.m11130a(this.f5674b.f5676b);
                }
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    private as(StreetViewPanoramaFragmentDelegateImpl streetViewPanoramaFragmentDelegateImpl) {
        this.f5675a = (StreetViewPanoramaFragmentDelegateImpl) Preconditions.m1817a((Object) streetViewPanoramaFragmentDelegateImpl);
        this.f5678d = new Handler(Looper.getMainLooper());
    }

    public static as m8630a(Activity activity) {
        return new as(new StreetViewPanoramaFragmentDelegateImpl(az.m8758a(activity)));
    }

    @Deprecated
    public final IStreetViewPanoramaDelegate m8633a() {
        return this.f5676b;
    }

    public final void m8635a(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions, Bundle bundle) {
        this.f5677c = streetViewPanoramaOptions;
    }

    public final void m8634a(Bundle bundle) {
        if (this.f5677c == null) {
            this.f5677c = (StreetViewPanoramaOptions) MapStateHelper.m11180a(bundle, "StreetViewPanoramaOptions");
        }
        if (this.f5677c == null) {
            this.f5677c = new StreetViewPanoramaOptions();
        }
    }

    public final IObjectWrapper m8632a(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle) {
        Object d;
        if (this.f5676b == null) {
            this.f5676b = this.f5675a.m8616a((LayoutInflater) ObjectWrapper.m10131a(iObjectWrapper), this.f5677c);
            this.f5676b.m8674a(bundle);
            d = this.f5676b.m8688d();
        } else {
            View d2 = this.f5676b.m8688d();
            ViewGroup viewGroup = (ViewGroup) d2.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(d2);
            }
            View view = d2;
        }
        return ObjectWrapper.m10130a(d);
    }

    public final void m8637b() {
        this.f5676b.m8673a();
    }

    public final void m8639c() {
        this.f5676b.m8683b();
    }

    public final void m8640d() {
        if (this.f5676b.m8690e()) {
            this.f5676b.m8686c();
            this.f5676b = null;
        }
    }

    public final void m8641e() {
        if (this.f5676b != null) {
            this.f5676b.m8686c();
            this.f5676b = null;
        }
        this.f5677c = null;
    }

    public final void m8642f() {
    }

    public final void m8638b(Bundle bundle) {
        if (this.f5676b != null) {
            this.f5676b.m8684b(bundle);
        } else if (this.f5677c != null) {
            MapStateHelper.m11181a(bundle, "StreetViewPanoramaOptions", this.f5677c);
        }
    }

    public final boolean m8643g() {
        return this.f5676b != null;
    }

    public final void m8636a(IOnStreetViewPanoramaReadyCallback iOnStreetViewPanoramaReadyCallback) {
        if (this.f5676b != null) {
            this.f5678d.post(new StreetViewPanoramaFragmentDelegateImpl(this, iOnStreetViewPanoramaReadyCallback));
        }
    }
}
