package com.google.android.m4b.maps.p052m;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.p037h.C0591c;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.m.k */
public final class UserAddedPlace implements C0591c {
    public static final Creator<UserAddedPlace> CREATOR;
    final int f7513a;
    private final String f7514b;
    private final LatLng f7515c;
    private final String f7516d;
    private final List<PlaceType> f7517e;
    private final String f7518f;
    private final String f7519g;

    UserAddedPlace(int i, String str, LatLng latLng, String str2, List<PlaceType> list, String str3, String str4) {
        this.f7513a = i;
        this.f7514b = str;
        this.f7515c = latLng;
        this.f7516d = str2;
        this.f7517e = new ArrayList(list);
        this.f7518f = str3;
        this.f7519g = str4;
    }

    static {
        CREATOR = new UserAddedPlaceCreator();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        UserAddedPlaceCreator.m10700a(this, parcel, i);
    }

    public final String m10694a() {
        return this.f7514b;
    }

    public final LatLng m10695b() {
        return this.f7515c;
    }

    public final String m10696c() {
        return this.f7516d;
    }

    public final List<PlaceType> m10697d() {
        return this.f7517e;
    }

    public final String m10698e() {
        return this.f7518f;
    }

    public final String m10699f() {
        return this.f7519g;
    }
}
