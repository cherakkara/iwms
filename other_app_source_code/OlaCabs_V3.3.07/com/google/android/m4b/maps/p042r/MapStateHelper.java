package com.google.android.m4b.maps.p042r;

import android.os.Bundle;
import android.os.Parcelable;

/* renamed from: com.google.android.m4b.maps.r.m */
public final class MapStateHelper {
    public static <T extends Parcelable> T m11180a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(MapStateHelper.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(MapStateHelper.class.getClassLoader());
        return bundle2.getParcelable(str);
    }

    public static void m11181a(Bundle bundle, String str, Parcelable parcelable) {
        bundle.setClassLoader(MapStateHelper.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(MapStateHelper.class.getClassLoader());
        bundle2.putParcelable(str, parcelable);
        bundle.putBundle("map_state", bundle2);
    }

    private MapStateHelper() {
    }
}
