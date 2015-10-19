package com.google.android.m4b.maps.p052m;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Objects;
import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.m.a */
public final class NearbyAlertRequest implements C0591c {
    public static final NearbyAlertRequestCreator CREATOR;
    private final int f7489a;
    private final int f7490b;
    private final int f7491c;
    private final PlaceFilter f7492d;

    static {
        CREATOR = new NearbyAlertRequestCreator();
    }

    NearbyAlertRequest(int i, int i2, int i3, PlaceFilter placeFilter) {
        this.f7489a = i;
        this.f7490b = i2;
        this.f7491c = i3;
        this.f7492d = placeFilter;
    }

    public final int m10671a() {
        return this.f7489a;
    }

    public final int m10672b() {
        return this.f7490b;
    }

    public final int m10673c() {
        return this.f7491c;
    }

    public final PlaceFilter m10674d() {
        return this.f7492d;
    }

    public final String toString() {
        return Objects.m10456a(this).m10455a("transitionTypes", Integer.valueOf(this.f7490b)).m10455a("loiteringTimeMillis", Integer.valueOf(this.f7491c)).m10455a("placeFilter", this.f7492d).toString();
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f7490b), Integer.valueOf(this.f7491c)});
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NearbyAlertRequest)) {
            return false;
        }
        NearbyAlertRequest nearbyAlertRequest = (NearbyAlertRequest) obj;
        if (this.f7490b == nearbyAlertRequest.f7490b && this.f7491c == nearbyAlertRequest.f7491c && this.f7492d.equals(nearbyAlertRequest.f7492d)) {
            return true;
        }
        return false;
    }

    public final int describeContents() {
        NearbyAlertRequestCreator nearbyAlertRequestCreator = CREATOR;
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        NearbyAlertRequestCreator nearbyAlertRequestCreator = CREATOR;
        NearbyAlertRequestCreator.m10676a(this, parcel, i);
    }
}
