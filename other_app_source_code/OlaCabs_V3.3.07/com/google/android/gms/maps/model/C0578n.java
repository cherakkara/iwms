package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.C0448b;

/* renamed from: com.google.android.gms.maps.model.n */
public class C0578n {
    static void m4599a(MarkerOptions markerOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, markerOptions.m4500a());
        C0448b.m3824a(parcel, 2, markerOptions.m4502c(), i, false);
        C0448b.m3826a(parcel, 3, markerOptions.m4503d(), false);
        C0448b.m3826a(parcel, 4, markerOptions.m4504e(), false);
        C0448b.m3822a(parcel, 5, markerOptions.m4501b(), false);
        C0448b.m3818a(parcel, 6, markerOptions.m4505f());
        C0448b.m3818a(parcel, 7, markerOptions.m4506g());
        C0448b.m3829a(parcel, 8, markerOptions.m4507h());
        C0448b.m3829a(parcel, 9, markerOptions.m4508i());
        C0448b.m3815a(parcel, a);
    }
}
