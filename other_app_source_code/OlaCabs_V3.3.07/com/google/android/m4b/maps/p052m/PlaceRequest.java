package com.google.android.m4b.maps.p052m;

import android.annotation.SuppressLint;
import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Objects;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.android.m4b.maps.m.g */
public final class PlaceRequest implements C0591c {
    public static final PlaceRequestCreator CREATOR;
    static final long f7506a;
    final int f7507b;
    private final PlaceFilter f7508c;
    private final long f7509d;
    private final int f7510e;

    static {
        CREATOR = new PlaceRequestCreator();
        f7506a = TimeUnit.HOURS.toMillis(1);
    }

    public PlaceRequest(int i, PlaceFilter placeFilter, long j, int i2) {
        this.f7507b = i;
        this.f7508c = placeFilter;
        this.f7509d = j;
        this.f7510e = i2;
    }

    public final PlaceFilter m10687a() {
        return this.f7508c;
    }

    public final long m10688b() {
        return this.f7509d;
    }

    public final int m10689c() {
        return this.f7510e;
    }

    @SuppressLint({"DefaultLocale"})
    public final String toString() {
        return Objects.m10456a(this).m10455a("filter", this.f7508c).m10455a("interval", Long.valueOf(this.f7509d)).m10455a("priority", Integer.valueOf(this.f7510e)).toString();
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f7508c, Long.valueOf(this.f7509d), Integer.valueOf(this.f7510e)});
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceRequest)) {
            return false;
        }
        PlaceRequest placeRequest = (PlaceRequest) obj;
        if (Objects.m10457a(this.f7508c, placeRequest.f7508c) && this.f7509d == placeRequest.f7509d && this.f7510e == placeRequest.f7510e) {
            return true;
        }
        return false;
    }

    public final int describeContents() {
        PlaceRequestCreator placeRequestCreator = CREATOR;
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        PlaceRequestCreator placeRequestCreator = CREATOR;
        PlaceRequestCreator.m10691a(this, parcel, i);
    }
}
