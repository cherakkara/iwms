package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* renamed from: com.google.android.m4b.maps.model.s */
public class PolygonOptionsCreatorCheddar {
    static void m11028a(PolygonOptions polygonOptions, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, polygonOptions.m10775a());
        SafeParcelWriter.m10508b(parcel, 2, polygonOptions.m10777c(), false);
        SafeParcelWriter.m10510c(parcel, 3, polygonOptions.m10776b(), false);
        SafeParcelWriter.m10491a(parcel, 4, polygonOptions.m10779e());
        SafeParcelWriter.m10492a(parcel, 5, polygonOptions.m10780f());
        SafeParcelWriter.m10492a(parcel, 6, polygonOptions.m10781g());
        SafeParcelWriter.m10491a(parcel, 7, polygonOptions.m10782h());
        SafeParcelWriter.m10501a(parcel, 8, polygonOptions.m10783i());
        SafeParcelWriter.m10501a(parcel, 9, polygonOptions.m10784j());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
