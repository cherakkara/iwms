package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.C0411p.C0412a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzaa implements SafeParcelable {
    public static final Creator<zzaa> CREATOR;
    final int f2311a;
    IBinder f2312b;
    private ConnectionResult f2313c;
    private boolean f2314d;
    private boolean f2315e;

    static {
        CREATOR = new C0414c();
    }

    public zzaa(int i) {
        this(new ConnectionResult(i, null));
    }

    zzaa(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.f2311a = i;
        this.f2312b = iBinder;
        this.f2313c = connectionResult;
        this.f2314d = z;
        this.f2315e = z2;
    }

    public zzaa(ConnectionResult connectionResult) {
        this(1, null, connectionResult, false, false);
    }

    public C0411p m3860a() {
        return C0412a.m3557a(this.f2312b);
    }

    public ConnectionResult m3861b() {
        return this.f2313c;
    }

    public boolean m3862c() {
        return this.f2314d;
    }

    public boolean m3863d() {
        return this.f2315e;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaa)) {
            return false;
        }
        zzaa com_google_android_gms_common_internal_zzaa = (zzaa) obj;
        return this.f2313c.equals(com_google_android_gms_common_internal_zzaa.f2313c) && m3860a().equals(com_google_android_gms_common_internal_zzaa.m3860a());
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0414c.m3560a(this, parcel, i);
    }
}
