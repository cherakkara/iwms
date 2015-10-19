package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.C0448b;

public class ab {
    static void m4549a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, visibleRegion.m4544a());
        C0448b.m3824a(parcel, 2, visibleRegion.f2828a, i, false);
        C0448b.m3824a(parcel, 3, visibleRegion.f2829b, i, false);
        C0448b.m3824a(parcel, 4, visibleRegion.f2830c, i, false);
        C0448b.m3824a(parcel, 5, visibleRegion.f2831d, i, false);
        C0448b.m3824a(parcel, 6, visibleRegion.f2832e, i, false);
        C0448b.m3815a(parcel, a);
    }
}
