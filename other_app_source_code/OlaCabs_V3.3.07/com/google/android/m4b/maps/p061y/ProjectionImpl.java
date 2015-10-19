package com.google.android.m4b.maps.p061y;

import android.graphics.Point;
import com.google.android.m4b.maps.an.ar;
import com.google.android.m4b.maps.an.as;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.be.am.ProjectionDelegate;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.VisibleRegion;
import com.google.p025a.p026a.Objects;

/* renamed from: com.google.android.m4b.maps.y.q */
public final class ProjectionImpl implements ProjectionDelegate {
    private final Camera f8157a;
    private final int f8158b;
    private final int f8159c;
    private final int f8160d;
    private final int f8161e;

    public ProjectionImpl(Camera camera, int i, int i2, int i3, int i4) {
        this.f8157a = camera;
        this.f8158b = i;
        this.f8159c = i2;
        this.f8160d = i3;
        this.f8161e = i4;
    }

    public final LatLng m11839a(Point point) {
        com.google.android.m4b.maps.an.Point d = this.f8157a.m7435d((float) point.x, (float) point.y);
        return d == null ? null : ConversionUtils.m11635a(d);
    }

    public final Point m11838a(LatLng latLng) {
        int[] b = this.f8157a.m7431b(ConversionUtils.m11640b(latLng));
        return new Point(b[0], b[1]);
    }

    public final VisibleRegion m11840a() {
        ar a = this.f8157a.m7422a(this.f8158b, this.f8159c, this.f8160d, this.f8161e);
        LatLng a2 = ConversionUtils.m11635a(a.m5669d());
        LatLng a3 = ConversionUtils.m11635a(a.m5670e());
        LatLng a4 = ConversionUtils.m11635a(a.m5672g());
        LatLng a5 = ConversionUtils.m11635a(a.m5671f());
        as a6 = a.m5662a();
        return new VisibleRegion(a2, a3, a4, a5, new LatLngBounds(ConversionUtils.m11635a(a6.m5685f()), ConversionUtils.m11635a(a6.m5686g())));
    }

    public final String toString() {
        return Objects.m1809a((Object) this).m1806a("camera", this.f8157a).toString();
    }
}
