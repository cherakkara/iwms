package com.google.android.m4b.maps.bm;

import com.google.android.m4b.maps.bo.AdsSpotlightProto;
import com.google.android.m4b.maps.p046d.ProtoBufType;

/* renamed from: com.google.android.m4b.maps.bm.a */
public final class DummyAdsLayerParams {
    public static final ProtoBufType f6684a;
    private static ProtoBufType f6685b;
    private static ProtoBufType f6686c;

    static {
        f6684a = new ProtoBufType();
        f6685b = new ProtoBufType();
        f6686c = new ProtoBufType();
        f6684a.m10219a(539, 2, f6685b).m10219a(539, 3, f6686c);
        f6685b.m10219a(1051, 4, AdsSpotlightProto.f6706a);
        f6686c.m10219a(1060, 1, null).m10219a(533, 2, null);
    }
}
