package com.google.android.m4b.maps.bx;

import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufType;
import com.google.android.m4b.maps.p054p.Primitives;

/* compiled from: Reversegeocode */
public final class af {
    public static final ProtoBufType f6820a;
    public static final ProtoBufType f6821b;
    private static ProtoBufType f6822c;
    private static ProtoBufType f6823d;

    static {
        f6822c = new ProtoBufType();
        f6823d = new ProtoBufType();
        f6820a = new ProtoBufType();
        f6821b = new ProtoBufType();
        f6822c.m10219a(286, 1, null);
        f6823d.m10219a(283, 1, f6822c).m10219a(1060, 2, null).m10219a(539, 3, Address.f6807a);
        f6820a.m10219a(283, 1, Geometry.f7067d).m10219a(533, 2, Primitives.m11085a(1)).m10219a(539, 3, Geometry.f7068e).m10219a(536, 4, ProtoBuf.f7298b);
        f6821b.m10219a(286, 1, null).m10219a(1051, 2, f6823d).m10219a(548, 3, null);
    }
}
