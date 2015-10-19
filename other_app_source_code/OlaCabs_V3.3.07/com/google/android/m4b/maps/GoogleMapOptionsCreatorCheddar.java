package com.google.android.m4b.maps;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* renamed from: com.google.android.m4b.maps.e */
public class GoogleMapOptionsCreatorCheddar {
    static void m10304a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, googleMapOptions.m4629a());
        SafeParcelWriter.m10489a(parcel, 2, googleMapOptions.m4633b());
        SafeParcelWriter.m10489a(parcel, 3, googleMapOptions.m4635c());
        SafeParcelWriter.m10492a(parcel, 4, googleMapOptions.m4654n());
        SafeParcelWriter.m10496a(parcel, 5, googleMapOptions.m4655o(), i, false);
        SafeParcelWriter.m10489a(parcel, 6, googleMapOptions.m4637d());
        SafeParcelWriter.m10489a(parcel, 7, googleMapOptions.m4639e());
        SafeParcelWriter.m10489a(parcel, 8, googleMapOptions.m4641f());
        SafeParcelWriter.m10489a(parcel, 9, googleMapOptions.m4643g());
        SafeParcelWriter.m10489a(parcel, 10, googleMapOptions.m4645h());
        SafeParcelWriter.m10489a(parcel, 11, googleMapOptions.m4647i());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
