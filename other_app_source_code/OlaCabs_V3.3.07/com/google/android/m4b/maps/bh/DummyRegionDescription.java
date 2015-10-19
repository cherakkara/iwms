package com.google.android.m4b.maps.bh;

import com.google.android.m4b.maps.bp.DummyTileCoordinateProto;
import com.google.android.m4b.maps.bp.TileBitmapProto;
import com.google.android.m4b.maps.cl.Point;
import com.google.android.m4b.maps.p046d.ProtoBufType;
import com.google.android.m4b.maps.p054p.Primitives;

/* renamed from: com.google.android.m4b.maps.bh.i */
public final class DummyRegionDescription {
    public static final ProtoBufType f6242a;
    public static final ProtoBufType f6243b;
    public static final ProtoBufType f6244c;
    private static ProtoBufType f6245d;
    private static ProtoBufType f6246e;
    private static ProtoBufType f6247f;

    static {
        f6242a = new ProtoBufType();
        f6245d = new ProtoBufType();
        f6246e = new ProtoBufType();
        f6247f = new ProtoBufType();
        f6243b = new ProtoBufType();
        f6244c = new ProtoBufType();
        f6242a.m10219a(539, 1, DummyTileCoordinateProto.f6731a).m10219a(539, 6, f6246e).m10219a(539, 8, TileBitmapProto.f6732a).m10219a(539, 3, f6247f).m10219a(539, 4, f6243b).m10219a(539, 5, f6245d);
        f6245d.m10219a(548, 1, null).m10219a(548, 2, null).m10219a(548, 3, null);
        f6246e.m10219a(533, 1, null).m10219a(533, 2, null);
        f6247f.m10219a(539, 1, f6244c).m10219a(539, 2, f6244c).m10219a(533, 3, null);
        f6243b.m10219a(541, 2, null).m10219a(541, 7, null).m10219a(541, 8, Primitives.m11085a(17)).m10219a(539, 1, f6244c).m10219a(539, 3, Point.f7287a).m10219a(1051, 4, Point.f7287a).m10219a(539, 5, Point.f7287a).m10219a(539, 6, Point.f7287a);
        f6244c.m10219a(285, 1, null).m10219a(285, 2, null);
    }
}
