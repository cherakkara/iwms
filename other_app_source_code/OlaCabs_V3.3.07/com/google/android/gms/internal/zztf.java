package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zztf implements SafeParcelable {
    public static final bd CREATOR;
    public final int f2587a;
    public final long f2588b;
    public final String f2589c;
    public final byte[] f2590d;
    public final Bundle f2591e;

    static {
        CREATOR = new bd();
    }

    zztf(int i, long j, String str, byte[] bArr, Bundle bundle) {
        this.f2587a = i;
        this.f2588b = j;
        this.f2589c = str;
        this.f2590d = bArr;
        this.f2591e = bundle;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("tag=").append(this.f2589c).append(",");
        stringBuilder.append("eventTime=").append(this.f2588b).append(",");
        if (!(this.f2591e == null || this.f2591e.isEmpty())) {
            stringBuilder.append("keyValues=");
            for (String str : this.f2591e.keySet()) {
                stringBuilder.append("(").append(str).append(",");
                stringBuilder.append(this.f2591e.getString(str)).append(")");
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        bd.m4058a(this, parcel, i);
    }
}
