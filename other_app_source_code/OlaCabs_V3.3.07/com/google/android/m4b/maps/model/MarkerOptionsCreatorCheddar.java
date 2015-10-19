package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* renamed from: com.google.android.m4b.maps.model.q */
public class MarkerOptionsCreatorCheddar {
    static void m11024a(MarkerOptions markerOptions, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, markerOptions.m10758a());
        SafeParcelWriter.m10496a(parcel, 2, markerOptions.m10762c(), i, false);
        SafeParcelWriter.m10498a(parcel, 3, markerOptions.m10763d(), false);
        SafeParcelWriter.m10498a(parcel, 4, markerOptions.m10764e(), false);
        SafeParcelWriter.m10495a(parcel, 5, markerOptions.m10761b(), false);
        SafeParcelWriter.m10491a(parcel, 6, markerOptions.m10766g());
        SafeParcelWriter.m10491a(parcel, 7, markerOptions.m10767h());
        SafeParcelWriter.m10501a(parcel, 8, markerOptions.m10768i());
        SafeParcelWriter.m10501a(parcel, 9, markerOptions.m10769j());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
