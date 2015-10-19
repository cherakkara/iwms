package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.C0448b;

/* renamed from: com.google.android.gms.maps.model.l */
public class C0576l {
    static void m4595a(LatLng latLng, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, latLng.m4498a());
        C0448b.m3817a(parcel, 2, latLng.f2762a);
        C0448b.m3817a(parcel, 3, latLng.f2763b);
        C0448b.m3815a(parcel, a);
    }
}
