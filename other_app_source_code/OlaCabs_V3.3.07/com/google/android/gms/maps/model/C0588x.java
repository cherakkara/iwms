package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.C0448b;

/* renamed from: com.google.android.gms.maps.model.x */
public class C0588x {
    static void m4623a(Tile tile, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, tile.m4537a());
        C0448b.m3819a(parcel, 2, tile.f2815a);
        C0448b.m3819a(parcel, 3, tile.f2816b);
        C0448b.m3830a(parcel, 4, tile.f2817c, false);
        C0448b.m3815a(parcel, a);
    }
}
