package com.google.android.m4b.maps.bh;

import com.google.android.m4b.maps.bp.DummyTileCoordinateProto;
import com.google.android.m4b.maps.p046d.ProtoBufType;

/* renamed from: com.google.android.m4b.maps.bh.h */
public final class DummyPerTile {
    public static final ProtoBufType f6237a;
    public static final ProtoBufType f6238b;
    public static final ProtoBufType f6239c;
    private static ProtoBufType f6240d;
    private static ProtoBufType f6241e;

    static {
        f6237a = new ProtoBufType();
        f6238b = new ProtoBufType();
        f6240d = new ProtoBufType();
        f6241e = new ProtoBufType();
        f6239c = new ProtoBufType();
        f6237a.m10219a(548, 1, null).m10219a(539, 2, DummyTileCoordinateProto.f6731a).m10219a(533, 3, null).m10219a(1045, 4, null);
        f6238b.m10219a(1051, 1, f6237a);
        f6240d.m10219a(548, 1, null).m10219a(533, 2, null).m10219a(533, 3, null);
        f6241e.m10219a(548, 1, null).m10219a(539, 2, DummyTileCoordinateProto.f6731a).m10219a(539, 4, f6240d).m10219a(533, 3, null);
        f6239c.m10219a(1051, 1, f6241e);
    }
}
