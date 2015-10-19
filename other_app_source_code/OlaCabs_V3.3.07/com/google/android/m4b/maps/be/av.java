package com.google.android.m4b.maps.be;

import android.view.View;
import com.google.android.m4b.maps.be.at.StreetViewPanoramaImpl;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.StreetViewPanoramaCamera;
import com.google.android.m4b.maps.model.StreetViewPanoramaLocation;
import com.google.android.m4b.maps.model.StreetViewPanoramaOrientation;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaCameraChangeListener;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaChangeListener;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaClickListener;

/* compiled from: StreetViewPanoramaRenderer */
public interface av {
    StreetViewPanoramaOrientation m8702a(int i, int i2);

    Object m8703a(StreetViewPanoramaOrientation streetViewPanoramaOrientation);

    void m8704a(StreetViewPanoramaImpl streetViewPanoramaImpl);

    void m8705a(LatLng latLng);

    void m8706a(LatLng latLng, int i);

    void m8707a(StreetViewPanoramaCamera streetViewPanoramaCamera, long j);

    void m8708a(StreetViewPanoramaCamera streetViewPanoramaCamera, String str);

    void m8709a(IOnStreetViewPanoramaCameraChangeListener iOnStreetViewPanoramaCameraChangeListener);

    void m8710a(IOnStreetViewPanoramaChangeListener iOnStreetViewPanoramaChangeListener);

    void m8711a(IOnStreetViewPanoramaClickListener iOnStreetViewPanoramaClickListener);

    void m8712a(String str);

    void m8713a(String str, LatLng latLng, Integer num, ay ayVar);

    void m8714a(boolean z);

    void m8715b();

    void m8716b(boolean z);

    void m8717c();

    void m8718c(boolean z);

    StreetViewPanoramaLocation m8719d();

    void m8720d(boolean z);

    StreetViewPanoramaCamera m8721e();

    boolean m8722g();

    boolean m8723h();

    boolean m8724i();

    boolean m8725j();

    View m8726k();
}
