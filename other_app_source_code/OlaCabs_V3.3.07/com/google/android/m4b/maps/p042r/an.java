package com.google.android.m4b.maps.p042r;

import android.graphics.Point;
import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;

/* compiled from: Point */
/* renamed from: com.google.android.m4b.maps.r.an */
public final class an implements C0591c {
    public static final PointCreator CREATOR;
    private final int f7710a;
    private final Point f7711b;

    static {
        CREATOR = new PointCreator();
    }

    public an(int i, Point point) {
        this.f7710a = i;
        this.f7711b = point;
    }

    public an(Point point) {
        this(1, point);
    }

    final int m11116a() {
        return this.f7710a;
    }

    public final Point m11117b() {
        return this.f7711b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof an)) {
            return false;
        }
        return this.f7711b.equals(((an) obj).f7711b);
    }

    public final int hashCode() {
        return this.f7711b.hashCode();
    }

    public final String toString() {
        return this.f7711b.toString();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        PointCreator.m11186a(this, parcel, i);
    }
}
