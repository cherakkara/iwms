package com.google.android.m4b.maps.br;

import com.google.android.m4b.maps.bo.EntityCommonProto;
import com.google.android.m4b.maps.cl.Point;
import com.google.android.m4b.maps.cl.Rect;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufType;
import com.google.android.m4b.maps.p054p.Primitives;

/* renamed from: com.google.android.m4b.maps.br.a */
public final class Indoor {
    public static final ProtoBufType f6734a;
    public static final ProtoBufType f6735b;
    public static final ProtoBufType f6736c;
    private static ProtoBufType f6737d;
    private static ProtoBufType f6738e;
    private static ProtoBufType f6739f;

    static {
        f6734a = new ProtoBufType();
        f6737d = new ProtoBufType();
        f6735b = new ProtoBufType();
        f6738e = new ProtoBufType();
        f6736c = new ProtoBufType();
        f6739f = new ProtoBufType();
        f6734a.m10219a(548, 1, null).m10219a(1051, 2, f6737d).m10219a(533, 3, Primitives.m11085a(0)).m10219a(536, 4, ProtoBuf.f7297a).m10219a(539, 5, Point.f7287a).m10219a(533, 6, null).m10219a(539, 7, f6739f);
        f6737d.m10219a(548, 1, null).m10219a(1060, 2, null).m10219a(548, 3, null).m10219a(548, 4, null).m10219a(545, 5, null).m10219a(545, 8, null).m10219a(539, 7, Rect.f7289a);
        f6735b.m10219a(1051, 2, f6738e).m10219a(1060, 1, null);
        f6738e.m10219a(548, 1, null).m10219a(545, 2, null);
        f6736c.m10219a(539, 3, EntityCommonProto.f6717a).m10219a(548, 4, null).m10219a(548, 1, null).m10219a(548, 2, null);
        f6739f.m10219a(539, 1, f6736c);
    }
}
