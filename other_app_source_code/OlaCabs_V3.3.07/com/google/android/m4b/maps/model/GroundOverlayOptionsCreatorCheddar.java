package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* renamed from: com.google.android.m4b.maps.model.h */
public class GroundOverlayOptionsCreatorCheddar {
    static void m10838a(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, groundOverlayOptions.m10725b());
        SafeParcelWriter.m10495a(parcel, 2, groundOverlayOptions.m10721a(), false);
        SafeParcelWriter.m10496a(parcel, 3, groundOverlayOptions.m10727d(), i, false);
        SafeParcelWriter.m10491a(parcel, 4, groundOverlayOptions.m10728e());
        SafeParcelWriter.m10491a(parcel, 5, groundOverlayOptions.m10729f());
        SafeParcelWriter.m10496a(parcel, 6, groundOverlayOptions.m10730g(), i, false);
        SafeParcelWriter.m10491a(parcel, 7, groundOverlayOptions.m10731h());
        SafeParcelWriter.m10491a(parcel, 8, groundOverlayOptions.m10732i());
        SafeParcelWriter.m10501a(parcel, 9, groundOverlayOptions.m10736m());
        SafeParcelWriter.m10491a(parcel, 10, groundOverlayOptions.m10733j());
        SafeParcelWriter.m10491a(parcel, 11, groundOverlayOptions.m10734k());
        SafeParcelWriter.m10491a(parcel, 12, groundOverlayOptions.m10735l());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
