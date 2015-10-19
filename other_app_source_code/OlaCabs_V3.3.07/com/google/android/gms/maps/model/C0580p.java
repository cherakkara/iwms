package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.C0448b;

/* renamed from: com.google.android.gms.maps.model.p */
public class C0580p {
    static void m4603a(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, polygonOptions.m4514a());
        C0448b.m3838c(parcel, 2, polygonOptions.m4516c(), false);
        C0448b.m3839d(parcel, 3, polygonOptions.m4515b(), false);
        C0448b.m3818a(parcel, 4, polygonOptions.m4517d());
        C0448b.m3819a(parcel, 5, polygonOptions.m4518e());
        C0448b.m3819a(parcel, 6, polygonOptions.m4519f());
        C0448b.m3818a(parcel, 7, polygonOptions.m4520g());
        C0448b.m3829a(parcel, 8, polygonOptions.m4521h());
        C0448b.m3829a(parcel, 9, polygonOptions.m4522i());
        C0448b.m3815a(parcel, a);
    }
}
