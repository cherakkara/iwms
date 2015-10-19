package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* compiled from: VisibleRegionCreatorCheddar */
public class ag {
    static void m10822a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, visibleRegion.m10809a());
        SafeParcelWriter.m10496a(parcel, 2, visibleRegion.f7635a, i, false);
        SafeParcelWriter.m10496a(parcel, 3, visibleRegion.f7636b, i, false);
        SafeParcelWriter.m10496a(parcel, 4, visibleRegion.f7637c, i, false);
        SafeParcelWriter.m10496a(parcel, 5, visibleRegion.f7638d, i, false);
        SafeParcelWriter.m10496a(parcel, 6, visibleRegion.f7639e, i, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
