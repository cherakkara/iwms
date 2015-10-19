package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountChangeEventsRequest implements SafeParcelable {
    public static final C0203b CREATOR;
    final int f1975a;
    int f1976b;
    @Deprecated
    String f1977c;
    Account f1978d;

    static {
        CREATOR = new C0203b();
    }

    public AccountChangeEventsRequest() {
        this.f1975a = 1;
    }

    AccountChangeEventsRequest(int i, int i2, String str, Account account) {
        this.f1975a = i;
        this.f1976b = i2;
        this.f1977c = str;
        if (account != null || TextUtils.isEmpty(str)) {
            this.f1978d = account;
        } else {
            this.f1978d = new Account(str, "com.google");
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0203b.m3151a(this, parcel, i);
    }
}
