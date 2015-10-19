package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* renamed from: com.google.android.m4b.maps.model.d */
public class CameraPositionCreatorCheddar {
    static void m10830a(CameraPosition cameraPosition, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, cameraPosition.m10712a());
        SafeParcelWriter.m10496a(parcel, 2, cameraPosition.f7529a, i, false);
        SafeParcelWriter.m10491a(parcel, 3, cameraPosition.f7530b);
        SafeParcelWriter.m10491a(parcel, 4, cameraPosition.f7531c);
        SafeParcelWriter.m10491a(parcel, 5, cameraPosition.f7532d);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
