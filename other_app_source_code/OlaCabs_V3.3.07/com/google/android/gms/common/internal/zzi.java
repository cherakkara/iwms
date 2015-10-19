package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.C0411p.C0412a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzi implements SafeParcelable {
    public static final Creator<zzi> CREATOR;
    final int f2329a;
    final int f2330b;
    int f2331c;
    String f2332d;
    IBinder f2333e;
    Scope[] f2334f;
    Bundle f2335g;
    Account f2336h;

    static {
        CREATOR = new C0421j();
    }

    public zzi(int i) {
        this.f2329a = 2;
        this.f2331c = 7095000;
        this.f2330b = i;
    }

    zzi(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account) {
        this.f2329a = i;
        this.f2330b = i2;
        this.f2331c = i3;
        this.f2332d = str;
        if (i < 2) {
            this.f2336h = m3868a(iBinder);
        } else {
            this.f2333e = iBinder;
            this.f2336h = account;
        }
        this.f2334f = scopeArr;
        this.f2335g = bundle;
    }

    private Account m3868a(IBinder iBinder) {
        return iBinder != null ? C0413b.m3558a(C0412a.m3557a(iBinder)) : null;
    }

    public zzi m3869a(Account account) {
        this.f2336h = account;
        return this;
    }

    public zzi m3870a(Bundle bundle) {
        this.f2335g = bundle;
        return this;
    }

    public zzi m3871a(C0411p c0411p) {
        if (c0411p != null) {
            this.f2333e = c0411p.asBinder();
        }
        return this;
    }

    public zzi m3872a(String str) {
        this.f2332d = str;
        return this;
    }

    public zzi m3873a(Scope[] scopeArr) {
        this.f2334f = scopeArr;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0421j.m3582a(this, parcel, i);
    }
}
