package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.C0448b;

/* renamed from: com.google.android.gms.maps.model.d */
public class C0561d {
    static void m4553a(CameraPosition cameraPosition, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, cameraPosition.m4477a());
        C0448b.m3824a(parcel, 2, cameraPosition.f2737a, i, false);
        C0448b.m3818a(parcel, 3, cameraPosition.f2738b);
        C0448b.m3818a(parcel, 4, cameraPosition.f2739c);
        C0448b.m3818a(parcel, 5, cameraPosition.f2740d);
        C0448b.m3815a(parcel, a);
    }
}
