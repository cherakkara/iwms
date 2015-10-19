package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* compiled from: TileOverlayOptionsCreatorCheddar */
public class ad {
    static void m10818a(TileOverlayOptions tileOverlayOptions, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, tileOverlayOptions.m10803a());
        SafeParcelWriter.m10495a(parcel, 2, tileOverlayOptions.m10804b(), false);
        SafeParcelWriter.m10501a(parcel, 3, tileOverlayOptions.m10807e());
        SafeParcelWriter.m10491a(parcel, 4, tileOverlayOptions.m10806d());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
