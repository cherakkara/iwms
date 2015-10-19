package com.google.android.m4b.maps.p042r;

import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.a */
public final class ConversionUtil {
    public static Boolean m11087a(byte b) {
        switch (b) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return Boolean.FALSE;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return Boolean.TRUE;
            default:
                return null;
        }
    }

    public static byte m11086a(Boolean bool) {
        if (bool != null) {
            return bool.booleanValue() ? (byte) 1 : (byte) 0;
        } else {
            return (byte) -1;
        }
    }
}
