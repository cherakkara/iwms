package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;
import com.google.android.m4b.maps.p047g.Objects;
import java.util.Arrays;

public final class VisibleRegion implements C0591c {
    public static final af CREATOR;
    public final LatLng f7635a;
    public final LatLng f7636b;
    public final LatLng f7637c;
    public final LatLng f7638d;
    public final LatLngBounds f7639e;
    private final int f7640f;

    static {
        CREATOR = new af();
    }

    VisibleRegion(int i, LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4, LatLngBounds latLngBounds) {
        this.f7640f = i;
        this.f7635a = latLng;
        this.f7636b = latLng2;
        this.f7637c = latLng3;
        this.f7638d = latLng4;
        this.f7639e = latLngBounds;
    }

    public VisibleRegion(LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4, LatLngBounds latLngBounds) {
        this(1, latLng, latLng2, latLng3, latLng4, latLngBounds);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            ag.m10822a(this, parcel, i);
        } else {
            af.m10819a(this, parcel, i);
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f7635a, this.f7636b, this.f7637c, this.f7638d, this.f7639e});
    }

    final int m10809a() {
        return this.f7640f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VisibleRegion)) {
            return false;
        }
        VisibleRegion visibleRegion = (VisibleRegion) obj;
        if (this.f7635a.equals(visibleRegion.f7635a) && this.f7636b.equals(visibleRegion.f7636b) && this.f7637c.equals(visibleRegion.f7637c) && this.f7638d.equals(visibleRegion.f7638d) && this.f7639e.equals(visibleRegion.f7639e)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return Objects.m10456a(this).m10455a("nearLeft", this.f7635a).m10455a("nearRight", this.f7636b).m10455a("farLeft", this.f7637c).m10455a("farRight", this.f7638d).m10455a("latLngBounds", this.f7639e).toString();
    }
}
