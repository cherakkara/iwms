package com.google.android.m4b.maps.p056s;

import com.google.android.m4b.maps.p046d.ProtoBufType;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.s.l */
public final class Gnmea {
    public static final ProtoBufType f7769a;
    private static ProtoBufType f7770b;
    private static ProtoBufType f7771c;

    static {
        f7770b = new ProtoBufType();
        f7771c = new ProtoBufType();
        f7769a = new ProtoBufType();
        f7770b.m10219a(277, 1, null).m10219a(533, 2, null).m10219a(529, 3, null).m10219a(529, 4, null);
        f7771c.m10219a(275, 1, null).m10219a(292, 2, null).m10219a(542, 3, null).m10219a(539, HttpStatus.SC_SWITCHING_PROTOCOLS, f7770b);
        f7769a.m10219a(1051, 1, f7771c);
    }
}
