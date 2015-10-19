package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* renamed from: com.google.android.m4b.maps.model.f */
public class CircleOptionsCreatorCheddar {
    static void m10834a(CircleOptions circleOptions, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, circleOptions.m10713a());
        SafeParcelWriter.m10496a(parcel, 2, circleOptions.m10714b(), i, false);
        SafeParcelWriter.m10490a(parcel, 3, circleOptions.m10715c());
        SafeParcelWriter.m10491a(parcel, 4, circleOptions.m10716d());
        SafeParcelWriter.m10492a(parcel, 5, circleOptions.m10717e());
        SafeParcelWriter.m10492a(parcel, 6, circleOptions.m10718f());
        SafeParcelWriter.m10491a(parcel, 7, circleOptions.m10719g());
        SafeParcelWriter.m10501a(parcel, 8, circleOptions.m10720h());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
