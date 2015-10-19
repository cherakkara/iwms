package com.google.android.m4b.maps.bm;

import com.google.android.m4b.maps.bo.EntityCommonProto;
import com.google.android.m4b.maps.bo.GeometryProto;
import com.google.android.m4b.maps.bo.SearchRestrictParamsProto;
import com.google.android.m4b.maps.cl.Featureid;
import com.google.android.m4b.maps.cl.Rect;
import com.google.android.m4b.maps.p046d.ProtoBufType;

/* renamed from: com.google.android.m4b.maps.bm.b */
public final class DummySearchParams {
    public static final ProtoBufType f6687a;
    private static ProtoBufType f6688b;
    private static ProtoBufType f6689c;
    private static ProtoBufType f6690d;

    static {
        f6687a = new ProtoBufType();
        f6688b = new ProtoBufType();
        f6689c = new ProtoBufType();
        f6690d = new ProtoBufType();
        f6687a.m10219a(548, 1, null).m10219a(548, 5, null).m10219a(548, 12, null).m10219a(536, 10, null).m10219a(536, 11, null).m10219a(539, 8, GeometryProto.f6719b).m10219a(539, 6, Rect.f7289a).m10219a(539, 13, EntityCommonProto.f6717a).m10219a(539, 2, f6689c).m10219a(1051, 3, f6688b).m10219a(539, 4, SearchRestrictParamsProto.f6728a).m10219a(548, 17, null).m10219a(548, 18, null).m10219a(539, 19, f6690d);
        f6688b.m10219a(539, 1, Featureid.f7283a).m10219a(548, 3, null).m10219a(530, 2, null).m10219a(533, 4, null);
        f6689c.m10219a(548, 1, null).m10219a(548, 2, null).m10219a(548, 3, null).m10219a(530, 4, null).m10219a(530, 5, null);
        f6690d.m10219a(536, 1, null).m10219a(548, 2, null);
    }
}
