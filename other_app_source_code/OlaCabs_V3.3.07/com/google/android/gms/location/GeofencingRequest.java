package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzpk;
import java.util.List;

public class GeofencingRequest implements SafeParcelable {
    public static final Creator<GeofencingRequest> CREATOR;
    private final int f2612a;
    private final List<zzpk> f2613b;
    private final int f2614c;

    static {
        CREATOR = new C0534j();
    }

    GeofencingRequest(int i, List<zzpk> list, int i2) {
        this.f2612a = i;
        this.f2613b = list;
        this.f2614c = i2;
    }

    public int m4315a() {
        return this.f2612a;
    }

    public List<zzpk> m4316b() {
        return this.f2613b;
    }

    public int m4317c() {
        return this.f2614c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0534j.m4368a(this, parcel, i);
    }
}
