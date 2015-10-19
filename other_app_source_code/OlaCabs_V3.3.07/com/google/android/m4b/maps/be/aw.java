package com.google.android.m4b.maps.be;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.view.LayoutInflater;
import com.google.android.m4b.maps.StreetViewPanoramaOptions;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaReadyCallback;
import com.google.android.m4b.maps.p042r.IStreetViewPanoramaDelegate;
import com.google.android.m4b.maps.p042r.IStreetViewPanoramaViewDelegate.IStreetViewPanoramaViewDelegate;

/* compiled from: StreetViewPanoramaViewDelegateImpl */
public final class aw extends IStreetViewPanoramaViewDelegate {
    private at f5699a;
    private final Context f5700b;
    private final StreetViewPanoramaOptions f5701c;

    /* renamed from: com.google.android.m4b.maps.be.aw.1 */
    class StreetViewPanoramaViewDelegateImpl implements Runnable {
        private /* synthetic */ IOnStreetViewPanoramaReadyCallback f5697a;
        private /* synthetic */ aw f5698b;

        StreetViewPanoramaViewDelegateImpl(aw awVar, IOnStreetViewPanoramaReadyCallback iOnStreetViewPanoramaReadyCallback) {
            this.f5698b = awVar;
            this.f5697a = iOnStreetViewPanoramaReadyCallback;
        }

        public final void run() {
            try {
                if (this.f5698b.f5699a != null) {
                    this.f5697a.m11130a(this.f5698b.f5699a);
                }
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public aw(Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
        this.f5700b = context;
        if (streetViewPanoramaOptions == null) {
            streetViewPanoramaOptions = new StreetViewPanoramaOptions();
        }
        this.f5701c = streetViewPanoramaOptions;
    }

    @Deprecated
    public final IStreetViewPanoramaDelegate m8737a() {
        return this.f5699a;
    }

    public final void m8738a(Bundle bundle) {
        boolean a;
        if (this.f5700b instanceof Activity) {
            a = az.m8758a((Activity) this.f5700b);
        } else {
            a = false;
        }
        this.f5699a = at.m8667a((LayoutInflater) this.f5700b.getSystemService("layout_inflater"), this.f5701c, a);
        this.f5699a.m8674a(bundle);
    }

    public final void m8740b() {
        this.f5699a.m8686c();
    }

    public final void m8742c() {
        this.f5699a.m8673a();
    }

    public final void m8743d() {
        this.f5699a.m8683b();
    }

    public final void m8744e() {
    }

    public final void m8741b(Bundle bundle) {
        this.f5699a.m8684b(bundle);
    }

    public final IObjectWrapper m8745f() {
        return ObjectWrapper.m10130a(this.f5699a.m8688d());
    }

    public final void m8739a(IOnStreetViewPanoramaReadyCallback iOnStreetViewPanoramaReadyCallback) {
        new Handler(Looper.getMainLooper()).post(new StreetViewPanoramaViewDelegateImpl(this, iOnStreetViewPanoramaReadyCallback));
    }
}
