package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.C0448b;

/* renamed from: com.google.android.gms.maps.model.r */
public class C0582r {
    static void m4607a(PolylineOptions polylineOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, polylineOptions.m4523a());
        C0448b.m3838c(parcel, 2, polylineOptions.m4524b(), false);
        C0448b.m3818a(parcel, 3, polylineOptions.m4525c());
        C0448b.m3819a(parcel, 4, polylineOptions.m4526d());
        C0448b.m3818a(parcel, 5, polylineOptions.m4527e());
        C0448b.m3829a(parcel, 6, polylineOptions.m4528f());
        C0448b.m3829a(parcel, 7, polylineOptions.m4529g());
        C0448b.m3815a(parcel, a);
    }
}
