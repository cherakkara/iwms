package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* renamed from: com.google.android.m4b.maps.model.l */
public class LatLngCreatorCheddar {
    static void m11014a(LatLng latLng, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, latLng.m10737a());
        SafeParcelWriter.m10490a(parcel, 2, latLng.f7554a);
        SafeParcelWriter.m10490a(parcel, 3, latLng.f7555b);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
