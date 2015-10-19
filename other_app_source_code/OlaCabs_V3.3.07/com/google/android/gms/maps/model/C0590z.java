package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.C0448b;

/* renamed from: com.google.android.gms.maps.model.z */
public class C0590z {
    static void m4627a(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, tileOverlayOptions.m4539a());
        C0448b.m3822a(parcel, 2, tileOverlayOptions.m4540b(), false);
        C0448b.m3829a(parcel, 3, tileOverlayOptions.m4542d());
        C0448b.m3818a(parcel, 4, tileOverlayOptions.m4541c());
        C0448b.m3815a(parcel, a);
    }
}
