package com.google.android.m4b.maps.bv;

import com.google.android.m4b.maps.p046d.ProtoBufType;
import com.google.android.m4b.maps.p054p.Primitives;

/* renamed from: com.google.android.m4b.maps.bv.c */
public final class EndUserCredentials {
    public static final ProtoBufType f6772a;
    public static final ProtoBufType f6773b;

    static {
        f6772a = new ProtoBufType();
        f6773b = new ProtoBufType();
        f6772a.m10219a(1051, 1, Authenticator.f6746b).m10219a(542, 3, Primitives.m11085a(0)).m10219a(539, 2, Principal.f6779c).m10219a(539, 4, IamRequestAttributes.f6774a);
        f6773b.m10219a(1051, 1, Authenticator.f6747c).m10219a(542, 3, Primitives.m11085a(0)).m10219a(539, 2, Principal.f6779c);
    }
}
