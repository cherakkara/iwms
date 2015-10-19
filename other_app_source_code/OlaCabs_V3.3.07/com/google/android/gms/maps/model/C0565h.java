package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.C0448b;

/* renamed from: com.google.android.gms.maps.model.h */
public class C0565h {
    static void m4561a(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, groundOverlayOptions.m4487b());
        C0448b.m3822a(parcel, 2, groundOverlayOptions.m4486a(), false);
        C0448b.m3824a(parcel, 3, groundOverlayOptions.m4488c(), i, false);
        C0448b.m3818a(parcel, 4, groundOverlayOptions.m4489d());
        C0448b.m3818a(parcel, 5, groundOverlayOptions.m4490e());
        C0448b.m3824a(parcel, 6, groundOverlayOptions.m4491f(), i, false);
        C0448b.m3818a(parcel, 7, groundOverlayOptions.m4492g());
        C0448b.m3818a(parcel, 8, groundOverlayOptions.m4493h());
        C0448b.m3829a(parcel, 9, groundOverlayOptions.m4497l());
        C0448b.m3818a(parcel, 10, groundOverlayOptions.m4494i());
        C0448b.m3818a(parcel, 11, groundOverlayOptions.m4495j());
        C0448b.m3818a(parcel, 12, groundOverlayOptions.m4496k());
        C0448b.m3815a(parcel, a);
    }
}
