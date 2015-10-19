package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* renamed from: com.google.android.m4b.maps.model.j */
public class LatLngBoundsCreatorCheddar {
    static void m11010a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, latLngBounds.m10745a());
        SafeParcelWriter.m10496a(parcel, 2, latLngBounds.f7561a, i, false);
        SafeParcelWriter.m10496a(parcel, 3, latLngBounds.f7562b, i, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
