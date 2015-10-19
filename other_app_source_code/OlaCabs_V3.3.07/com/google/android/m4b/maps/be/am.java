package com.google.android.m4b.maps.be;

import android.graphics.Point;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.VisibleRegion;
import com.google.android.m4b.maps.p042r.IProjectionDelegate.IProjectionDelegate;
import com.google.android.m4b.maps.p042r.an;

/* compiled from: ProjectionDelegate */
public final class am extends IProjectionDelegate {
    private final be f5648a;
    private final ProjectionDelegate f5649b;

    /* renamed from: com.google.android.m4b.maps.be.am.a */
    public interface ProjectionDelegate {
        Point m8550a(LatLng latLng);

        LatLng m8551a(Point point);

        VisibleRegion m8552a();
    }

    public am(be beVar, ProjectionDelegate projectionDelegate) {
        this.f5649b = projectionDelegate;
        this.f5648a = beVar;
    }

    public final LatLng m8560a(IObjectWrapper iObjectWrapper) {
        this.f5648a.m8835b(UsageLog.PROJECTION_FROM_SCREEN_LOCATION);
        return this.f5649b.m8551a((Point) ObjectWrapper.m10131a(iObjectWrapper));
    }

    public final LatLng m8561a(an anVar) {
        this.f5648a.m8835b(UsageLog.PROJECTION_FROM_SCREEN_LOCATION);
        return this.f5649b.m8551a(anVar.m11117b());
    }

    public final IObjectWrapper m8559a(LatLng latLng) {
        this.f5648a.m8835b(UsageLog.PROJECTION_TO_SCREEN_LOCATION);
        return ObjectWrapper.m10130a(this.f5649b.m8550a(latLng));
    }

    public final an m8563b(LatLng latLng) {
        this.f5648a.m8835b(UsageLog.PROJECTION_TO_SCREEN_LOCATION);
        return new an(this.f5649b.m8550a(latLng));
    }

    public final VisibleRegion m8562a() {
        this.f5648a.m8835b(UsageLog.PROJECTION_GET_FRUSTUM);
        return this.f5649b.m8552a();
    }

    public final String toString() {
        return this.f5649b.toString();
    }
}
