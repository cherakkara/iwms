package com.google.android.gms.maps.internal;

import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.internal.a */
public final class C0553a {
    public static byte m4469a(Boolean bool) {
        return bool != null ? bool.booleanValue() ? (byte) 1 : (byte) 0 : (byte) -1;
    }

    public static Boolean m4470a(byte b) {
        switch (b) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return Boolean.FALSE;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return Boolean.TRUE;
            default:
                return null;
        }
    }
}
