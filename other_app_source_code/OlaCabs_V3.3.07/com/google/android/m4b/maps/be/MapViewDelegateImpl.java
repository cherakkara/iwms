package com.google.android.m4b.maps.be;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import com.google.android.m4b.maps.GoogleMapOptions;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.IGoogleMapDelegate;
import com.google.android.m4b.maps.p042r.aa.IMapViewDelegate;
import com.google.android.m4b.maps.p042r.aj;
import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.android.m4b.maps.be.s */
public class MapViewDelegateImpl extends IMapViewDelegate {
    private cd f6073a;
    private final GoogleMapOptions f6074b;
    private final Context f6075c;

    static {
        MapViewDelegateImpl.class.getSimpleName();
    }

    public MapViewDelegateImpl(Context context, GoogleMapOptions googleMapOptions) {
        this.f6075c = (Context) Preconditions.m1817a((Object) context);
        if (googleMapOptions == null) {
            googleMapOptions = new GoogleMapOptions();
        }
        this.f6074b = googleMapOptions;
    }

    public final IGoogleMapDelegate m9524a() {
        return this.f6073a;
    }

    public final void m9525a(Bundle bundle) {
        boolean a;
        if (this.f6075c instanceof Activity) {
            a = az.m8758a((Activity) this.f6075c);
        } else {
            a = false;
        }
        this.f6073a = by.m9188a((LayoutInflater) this.f6075c.getSystemService("layout_inflater"), this.f6074b, a);
        this.f6073a.m9180a(bundle);
    }

    public final void m9527b() {
        this.f6073a.m9181b();
    }

    public final void m9529c() {
        this.f6073a.m9183c();
    }

    public final void m9530d() {
        this.f6073a.m9179a();
        this.f6073a = null;
    }

    public final void m9531e() {
        this.f6073a.m9184d();
    }

    public final void m9528b(Bundle bundle) {
        this.f6073a.m9182b(bundle);
    }

    public final IObjectWrapper m9532f() {
        return ObjectWrapper.m10130a(this.f6073a.m9177B());
    }

    public final void m9526a(aj ajVar) {
        if (this.f6073a != null) {
            try {
                this.f6073a.m9142a(ajVar);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }
}
