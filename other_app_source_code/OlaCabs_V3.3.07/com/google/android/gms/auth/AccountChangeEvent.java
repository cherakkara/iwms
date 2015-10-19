package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.sothree.slidinguppanel.p086a.R.R;

public class AccountChangeEvent implements SafeParcelable {
    public static final C0202a CREATOR;
    final int f1969a;
    final long f1970b;
    final String f1971c;
    final int f1972d;
    final int f1973e;
    final String f1974f;

    static {
        CREATOR = new C0202a();
    }

    AccountChangeEvent(int i, long j, String str, int i2, int i3, String str2) {
        this.f1969a = i;
        this.f1970b = j;
        this.f1971c = (String) C0453u.m3846a((Object) str);
        this.f1972d = i2;
        this.f1973e = i3;
        this.f1974f = str2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccountChangeEvent)) {
            return false;
        }
        AccountChangeEvent accountChangeEvent = (AccountChangeEvent) obj;
        return this.f1969a == accountChangeEvent.f1969a && this.f1970b == accountChangeEvent.f1970b && C0452t.m3845a(this.f1971c, accountChangeEvent.f1971c) && this.f1972d == accountChangeEvent.f1972d && this.f1973e == accountChangeEvent.f1973e && C0452t.m3845a(this.f1974f, accountChangeEvent.f1974f);
    }

    public int hashCode() {
        return C0452t.m3843a(Integer.valueOf(this.f1969a), Long.valueOf(this.f1970b), this.f1971c, Integer.valueOf(this.f1972d), Integer.valueOf(this.f1973e), this.f1974f);
    }

    public String toString() {
        String str = "UNKNOWN";
        switch (this.f1972d) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                str = "ADDED";
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                str = "REMOVED";
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                str = "RENAMED_FROM";
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                str = "RENAMED_TO";
                break;
        }
        return "AccountChangeEvent {accountName = " + this.f1971c + ", changeType = " + str + ", changeData = " + this.f1974f + ", eventIndex = " + this.f1973e + "}";
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0202a.m3148a(this, parcel, i);
    }
}
