package com.google.android.m4b.maps.bx;

import com.google.android.m4b.maps.bg.GeomProto;
import com.google.android.m4b.maps.bo.GmeCommonProto;
import com.google.android.m4b.maps.ck.Id;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufType;
import com.google.android.m4b.maps.p054p.Primitives;

/* renamed from: com.google.android.m4b.maps.bx.z */
public final class MapsEngine {
    public static final ProtoBufType f7109a;
    public static final ProtoBufType f7110b;
    public static final ProtoBufType f7111c;
    private static ProtoBufType f7112d;
    private static ProtoBufType f7113e;
    private static ProtoBufType f7114f;
    private static ProtoBufType f7115g;
    private static ProtoBufType f7116h;
    private static ProtoBufType f7117i;
    private static ProtoBufType f7118j;
    private static ProtoBufType f7119k;
    private static ProtoBufType f7120l;

    static {
        f7112d = new ProtoBufType();
        f7113e = new ProtoBufType();
        f7114f = new ProtoBufType();
        f7115g = new ProtoBufType();
        f7116h = new ProtoBufType();
        f7117i = new ProtoBufType();
        f7109a = new ProtoBufType();
        f7110b = new ProtoBufType();
        f7111c = new ProtoBufType();
        f7118j = new ProtoBufType();
        f7119k = new ProtoBufType();
        f7120l = new ProtoBufType();
        f7112d.m10219a(539, 1, Id.f7279a).m10219a(539, 2, f7113e);
        f7113e.m10219a(533, 1, null).m10219a(536, 2, ProtoBuf.f7298b);
        f7114f.m10219a(542, 1, Primitives.m11085a(0)).m10219a(539, 2, f7115g);
        f7115g.m10219a(548, 1, null).m10219a(548, 2, null).m10219a(539, 3, GeomProto.f6203a).m10219a(1051, 4, f7116h).m10219a(539, 5, GmeCommonProto.f6722a).m10219a(542, 6, Primitives.m11085a(0));
        f7116h.m10219a(539, 1, Tile.f6893i).m10219a(531, 2, null).m10219a(536, 3, ProtoBuf.f7298b).m10219a(548, 4, null).m10219a(548, 5, null).m10219a(1051, 6, f7117i);
        f7117i.m10219a(548, 1, null).m10219a(539, 3, GeomProto.f6204b).m10219a(548, 2, null);
        f7109a.m10219a(539, 1, Tile.f6893i).m10219a(548, 2, null).m10219a(1051, 3, f7110b).m10219a(542, 4, Primitives.m11085a(0));
        f7110b.m10219a(539, 1, Tile.f6893i).m10219a(548, 2, null);
        f7111c.m10219a(542, 1, Primitives.m11085a(0)).m10219a(548, 2, null).m10219a(548, 3, null).m10219a(1051, 4, f7118j);
        f7118j.m10219a(542, 1, Primitives.m11085a(0)).m10219a(548, 2, null).m10219a(548, 3, null).m10219a(539, 4, f7120l);
        f7119k.m10219a(548, 1, null).m10219a(548, 2, null);
        f7120l.m10219a(548, 1, null).m10219a(548, 2, null).m10219a(1051, 3, f7119k);
    }
}
