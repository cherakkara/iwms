package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;
import com.google.android.m4b.maps.p047g.Objects;
import com.google.android.m4b.maps.p047g.Preconditions;
import java.util.Arrays;

public final class LatLngBounds implements C0591c {
    public static final LatLngBoundsCreator CREATOR;
    public final LatLng f7561a;
    public final LatLng f7562b;
    private final int f7563c;

    /* renamed from: com.google.android.m4b.maps.model.LatLngBounds.a */
    public static final class C0595a {
        private double f7557a;
        private double f7558b;
        private double f7559c;
        private double f7560d;

        public C0595a() {
            this.f7557a = Double.POSITIVE_INFINITY;
            this.f7558b = Double.NEGATIVE_INFINITY;
            this.f7559c = Double.NaN;
            this.f7560d = Double.NaN;
        }

        public final C0595a m10738a(LatLng latLng) {
            Object obj = 1;
            this.f7557a = Math.min(this.f7557a, latLng.f7554a);
            this.f7558b = Math.max(this.f7558b, latLng.f7554a);
            double d = latLng.f7555b;
            if (Double.isNaN(this.f7559c)) {
                this.f7559c = d;
            } else {
                if (this.f7559c <= this.f7560d) {
                    if (this.f7559c > d || d > this.f7560d) {
                        obj = null;
                    }
                } else if (this.f7559c > d && d > this.f7560d) {
                    obj = null;
                }
                if (obj == null) {
                    if (LatLngBounds.m10743c(this.f7559c, d) < LatLngBounds.m10744d(this.f7560d, d)) {
                        this.f7559c = d;
                    }
                }
                return this;
            }
            this.f7560d = d;
            return this;
        }

        public final LatLngBounds m10739a() {
            Preconditions.m10463a(!Double.isNaN(this.f7559c), (Object) "no included points");
            return new LatLngBounds(new LatLng(this.f7557a, this.f7559c), new LatLng(this.f7558b, this.f7560d));
        }
    }

    static {
        CREATOR = new LatLngBoundsCreator();
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        Preconditions.m10460a((Object) latLng, (Object) "null southwest");
        Preconditions.m10460a((Object) latLng2, (Object) "null northeast");
        Preconditions.m10464a(latLng2.f7554a >= latLng.f7554a, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.f7554a), Double.valueOf(latLng2.f7554a));
        this.f7563c = i;
        this.f7561a = latLng;
        this.f7562b = latLng2;
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this(1, latLng, latLng2);
    }

    final int m10745a() {
        return this.f7563c;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            LatLngBoundsCreatorCheddar.m11010a(this, parcel, i);
        } else {
            LatLngBoundsCreator.m10839a(this, parcel, i);
        }
    }

    public final int describeContents() {
        return 0;
    }

    public static C0595a m10742b() {
        return new C0595a();
    }

    public final LatLng m10746c() {
        double d = (this.f7561a.f7554a + this.f7562b.f7554a) / 2.0d;
        double d2 = this.f7562b.f7555b;
        double d3 = this.f7561a.f7555b;
        if (d3 <= d2) {
            d2 = (d2 + d3) / 2.0d;
        } else {
            d2 = ((d2 + 360.0d) + d3) / 2.0d;
        }
        return new LatLng(d, d2);
    }

    private static double m10743c(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    private static double m10744d(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f7561a, this.f7562b});
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        if (this.f7561a.equals(latLngBounds.f7561a) && this.f7562b.equals(latLngBounds.f7562b)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return Objects.m10456a(this).m10455a("southwest", this.f7561a).m10455a("northeast", this.f7562b).toString();
    }
}
