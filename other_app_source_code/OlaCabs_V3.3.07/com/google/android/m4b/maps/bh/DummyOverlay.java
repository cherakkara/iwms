package com.google.android.m4b.maps.bh;

import com.google.android.m4b.maps.bt.MessageSetProtos;
import com.google.android.m4b.maps.cl.Featureid;
import com.google.android.m4b.maps.cl.Point;
import com.google.android.m4b.maps.cl.Polyline;
import com.google.android.m4b.maps.p046d.ProtoBufType;
import com.google.android.m4b.maps.p054p.Primitives;

/* renamed from: com.google.android.m4b.maps.bh.d */
public final class DummyOverlay {
    public static final ProtoBufType f6225a;
    private static ProtoBufType f6226b;
    private static ProtoBufType f6227c;
    private static ProtoBufType f6228d;

    static {
        f6225a = new ProtoBufType();
        f6226b = new ProtoBufType();
        f6227c = new ProtoBufType();
        f6228d = new ProtoBufType();
        f6225a.m10219a(1051, 1, f6226b).m10219a(1051, 2, f6227c).m10219a(1054, 3, null).m10219a(1051, 4, f6228d);
        f6226b.m10219a(283, 1, Point.f7287a).m10219a(533, 9, null).m10219a(533, 10, null).m10219a(542, 2, null).m10219a(542, 11, null).m10219a(541, 3, null).m10219a(548, 4, null).m10219a(541, 5, Primitives.m11085a(-16777216)).m10219a(539, 6, Featureid.f7283a).m10219a(536, 7, null).m10219a(539, 8, MessageSetProtos.f6741a);
        f6227c.m10219a(539, 1, Polyline.f7288a).m10219a(547, 5, null).m10219a(541, 2, null).m10219a(530, 3, Primitives.m11085a(1065353216)).m10219a(536, 4, null);
        f6228d.m10219a(539, 1, Point.f7287a).m10219a(530, 2, null).m10219a(541, 3, null).m10219a(530, 4, Primitives.m11085a(1065353216)).m10219a(541, 5, null);
    }
}
