package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* renamed from: com.google.android.m4b.maps.model.u */
public class PolylineOptionsCreatorCheddar {
    static void m11032a(PolylineOptions polylineOptions, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, polylineOptions.m10785a());
        SafeParcelWriter.m10508b(parcel, 2, polylineOptions.m10786b(), false);
        SafeParcelWriter.m10491a(parcel, 3, polylineOptions.m10787c());
        SafeParcelWriter.m10492a(parcel, 4, polylineOptions.m10788d());
        SafeParcelWriter.m10491a(parcel, 5, polylineOptions.m10789e());
        SafeParcelWriter.m10501a(parcel, 6, polylineOptions.m10790f());
        SafeParcelWriter.m10501a(parcel, 7, polylineOptions.m10791g());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
