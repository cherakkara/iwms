package com.google.android.m4b.maps.au;

import com.google.android.m4b.maps.am.MapPoint;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.p046d.ProtoBuf;

/* renamed from: com.google.android.m4b.maps.au.d */
public final class GeometryConverter {
    public static Point m6619a(ProtoBuf protoBuf) {
        int d = protoBuf.m10204d(3);
        d = 1 << ((30 - d) - 7);
        return new Point((protoBuf.m10204d(1) * d) - 536870912, 536870912 - (d * protoBuf.m10204d(2)));
    }

    public static Point m6618a(MapPoint mapPoint) {
        return Point.m5934b(mapPoint.m5393a(), mapPoint.m5394b());
    }
}
