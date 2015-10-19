package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class AccountChangeEventsResponse implements SafeParcelable {
    public static final C0204c CREATOR;
    final int f1979a;
    final List<AccountChangeEvent> f1980b;

    static {
        CREATOR = new C0204c();
    }

    AccountChangeEventsResponse(int i, List<AccountChangeEvent> list) {
        this.f1979a = i;
        this.f1980b = (List) C0453u.m3846a((Object) list);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0204c.m3154a(this, parcel, i);
    }
}
