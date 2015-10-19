package com.google.android.m4b.maps.bk;

import com.google.android.m4b.maps.bm.DummyAdsLayerParams;
import com.google.android.m4b.maps.bm.DummySearchParams;
import com.google.android.m4b.maps.bo.AdsSpotlightProto;
import com.google.android.m4b.maps.bo.DirectionsRequestProto;
import com.google.android.m4b.maps.bo.EntityCommonProto;
import com.google.android.m4b.maps.p046d.ProtoBufType;
import com.google.android.m4b.maps.p054p.Primitives;

/* renamed from: com.google.android.m4b.maps.bk.a */
public final class DummySpotlightDescription {
    public static final ProtoBufType f6673a;
    private static ProtoBufType f6674b;
    private static ProtoBufType f6675c;

    static {
        f6674b = new ProtoBufType();
        f6673a = new ProtoBufType();
        f6675c = new ProtoBufType();
        f6674b.m10219a(548, 1, null);
        f6673a.m10219a(539, 2, EntityCommonProto.f6717a).m10219a(536, 14, null).m10219a(548, 10, null).m10219a(539, 12, DummySearchParams.f6687a).m10219a(539, 15, f6674b).m10219a(539, 8, DirectionsRequestProto.f6715a).m10219a(539, 5, DummyAdsLayerParams.f6684a).m10219a(539, 6, AdsSpotlightProto.f6706a).m10219a(542, 11, Primitives.m11085a(1)).m10219a(1053, 19, null).m10219a(539, 13, DummySpotlightFlags.f6676a).m10219a(1051, 16, SelectedItemProto.f6681a).m10219a(548, 17, null).m10219a(1051, 18, f6675c).m10219a(539, 1, f6673a);
        f6675c.m10219a(542, 1, null).m10219a(548, 2, null);
    }
}
