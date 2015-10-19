package com.google.android.m4b.maps.p061y;

import android.content.res.Resources;
import android.location.Location;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.an.MyLocationMarker;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.MyLocationOverlay;
import com.google.android.m4b.maps.be.ag.MyLocationLayerImpl;
import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.android.m4b.maps.y.l */
public final class MyLocationRendererImpl implements MyLocationLayerImpl {
    private final VectorMapView f8127a;
    private final Resources f8128b;
    private MyLocationOverlay f8129c;

    public MyLocationRendererImpl(VectorMapView vectorMapView) {
        this.f8127a = (VectorMapView) Preconditions.m1817a((Object) vectorMapView);
        this.f8128b = vectorMapView.getResources();
    }

    public final void m11776a() {
        if (this.f8129c == null) {
            this.f8129c = this.f8127a.m11868d(true);
            this.f8129c.m7175a(this.f8128b.getDimension(R.vm_mylocation_dot_size), this.f8128b.getInteger(R.vm_mylocation_dot_opaque_percent), this.f8128b.getInteger(R.vm_mylocation_chevron_opaque_percent));
        }
        this.f8127a.m11862a(this.f8129c);
    }

    public final void m11778b() {
        this.f8127a.m11867b(this.f8129c);
    }

    public final void m11777a(Location location) {
        Point a = Point.m5923a(location.getLatitude(), location.getLongitude());
        MyLocationMarker myLocationMarker = new MyLocationMarker(a, location.getBearing(), (int) location.getAccuracy());
        myLocationMarker.m5907a(a);
        myLocationMarker.m5909a(location.hasBearing());
        this.f8129c.m7176a(myLocationMarker);
        this.f8127a.m11866a(true, true);
    }
}
