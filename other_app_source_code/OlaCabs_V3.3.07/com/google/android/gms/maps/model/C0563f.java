package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.C0448b;

/* renamed from: com.google.android.gms.maps.model.f */
public class C0563f {
    static void m4557a(CircleOptions circleOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, circleOptions.m4478a());
        C0448b.m3824a(parcel, 2, circleOptions.m4479b(), i, false);
        C0448b.m3817a(parcel, 3, circleOptions.m4480c());
        C0448b.m3818a(parcel, 4, circleOptions.m4481d());
        C0448b.m3819a(parcel, 5, circleOptions.m4482e());
        C0448b.m3819a(parcel, 6, circleOptions.m4483f());
        C0448b.m3818a(parcel, 7, circleOptions.m4484g());
        C0448b.m3829a(parcel, 8, circleOptions.m4485h());
        C0448b.m3815a(parcel, a);
    }
}
