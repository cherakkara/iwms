package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;

/* compiled from: TileCreatorCheddar */
public class ab {
    static void m10814a(Tile tile, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, tile.m10799a());
        SafeParcelWriter.m10492a(parcel, 2, tile.f7622a);
        SafeParcelWriter.m10492a(parcel, 3, tile.f7623b);
        SafeParcelWriter.m10502a(parcel, 4, tile.f7624c, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
