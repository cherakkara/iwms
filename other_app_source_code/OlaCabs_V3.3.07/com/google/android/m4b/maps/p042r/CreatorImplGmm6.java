package com.google.android.m4b.maps.p042r;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import com.google.android.m4b.maps.GoogleMapOptions;
import com.google.android.m4b.maps.MapsInitializer;
import com.google.android.m4b.maps.StreetViewPanoramaOptions;
import com.google.android.m4b.maps.be.InternalResourceLoader;
import com.google.android.m4b.maps.be.MapFragmentDelegateImpl;
import com.google.android.m4b.maps.be.MapViewDelegateImpl;
import com.google.android.m4b.maps.be.as;
import com.google.android.m4b.maps.be.aw;
import com.google.android.m4b.maps.be.az;
import com.google.android.m4b.maps.be.bg;
import com.google.android.m4b.maps.be.bj;
import com.google.android.m4b.maps.be.bq;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.internal.IBitmapDescriptorFactoryDelegate;
import com.google.android.m4b.maps.p042r.ICreator.ICreator;

/* renamed from: com.google.android.m4b.maps.r.r */
public class CreatorImplGmm6 extends ICreator {
    public final void m11210a(IObjectWrapper iObjectWrapper) {
        m11211a(iObjectWrapper, 0);
    }

    public final void m11211a(IObjectWrapper iObjectWrapper, int i) {
        az.m8757a(4, "Google Play services client version: " + i);
        InternalResourceLoader.m9393a((Resources) ObjectWrapper.m10131a(iObjectWrapper));
        bg.m8849a(i, true);
        SafeParcelableVersionManager.m11187a(i);
        MapsInitializer.m10316a((ICreator) this);
    }

    public final IMapFragmentDelegate m11213b(IObjectWrapper iObjectWrapper) {
        Activity activity = (Activity) ObjectWrapper.m10131a(iObjectWrapper);
        CreatorImplGmm6.m11206a((Context) activity);
        return MapFragmentDelegateImpl.m9459a(activity);
    }

    public final aa m11207a(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions) {
        Context context = (Context) ObjectWrapper.m10131a(iObjectWrapper);
        CreatorImplGmm6.m11206a(context);
        return new MapViewDelegateImpl(context, googleMapOptions);
    }

    public final IStreetViewPanoramaFragmentDelegate m11214c(IObjectWrapper iObjectWrapper) {
        Activity activity = (Activity) ObjectWrapper.m10131a(iObjectWrapper);
        CreatorImplGmm6.m11206a((Context) activity);
        return as.m8630a(activity);
    }

    public final IStreetViewPanoramaViewDelegate m11208a(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions) {
        Context context = (Context) ObjectWrapper.m10131a(iObjectWrapper);
        CreatorImplGmm6.m11206a(context);
        return new aw(context, streetViewPanoramaOptions);
    }

    public final ICameraUpdateFactoryDelegate m11209a() {
        return new bq();
    }

    public final IBitmapDescriptorFactoryDelegate m11212b() {
        return new bj();
    }

    private static void m11206a(Context context) {
        try {
            az.m8757a(4, "Google Play services package version: " + context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode);
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }
}
