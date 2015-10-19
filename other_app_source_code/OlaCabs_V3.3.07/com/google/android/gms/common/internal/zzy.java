package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzy implements SafeParcelable {
    public static final Creator<zzy> CREATOR;
    final int f2337a;
    private final Account f2338b;
    private final int f2339c;

    static {
        CREATOR = new C0454v();
    }

    zzy(int i, Account account, int i2) {
        this.f2337a = i;
        this.f2338b = account;
        this.f2339c = i2;
    }

    public zzy(Account account, int i) {
        this(1, account, i);
    }

    public Account m3874a() {
        return this.f2338b;
    }

    public int m3875b() {
        return this.f2339c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0454v.m3857a(this, parcel, i);
    }
}
