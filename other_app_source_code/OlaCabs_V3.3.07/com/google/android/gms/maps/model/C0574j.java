package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.C0448b;

/* renamed from: com.google.android.gms.maps.model.j */
public class C0574j {
    static void m4591a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, latLngBounds.m4499a());
        C0448b.m3824a(parcel, 2, latLngBounds.f2765a, i, false);
        C0448b.m3824a(parcel, 3, latLngBounds.f2766b, i, false);
        C0448b.m3815a(parcel, a);
    }
}
