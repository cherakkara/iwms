package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.C0477l;
import com.google.android.gms.location.C0477l.C0478a;
import com.google.android.gms.location.C0480m;
import com.google.android.gms.location.C0480m.C0481a;

public class zzpi implements SafeParcelable {
    public static final am CREATOR;
    int f2503a;
    zzpg f2504b;
    C0477l f2505c;
    PendingIntent f2506d;
    C0480m f2507e;
    private final int f2508f;

    static {
        CREATOR = new am();
    }

    zzpi(int i, int i2, zzpg com_google_android_gms_internal_zzpg, IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2) {
        C0480m c0480m = null;
        this.f2508f = i;
        this.f2503a = i2;
        this.f2504b = com_google_android_gms_internal_zzpg;
        this.f2505c = iBinder == null ? null : C0478a.m3986a(iBinder);
        this.f2506d = pendingIntent;
        if (iBinder2 != null) {
            c0480m = C0481a.m3990a(iBinder2);
        }
        this.f2507e = c0480m;
    }

    public static zzpi m4255a(zzpg com_google_android_gms_internal_zzpg, C0477l c0477l) {
        return new zzpi(1, 1, com_google_android_gms_internal_zzpg, c0477l.asBinder(), null, null);
    }

    public static zzpi m4256a(C0477l c0477l) {
        return new zzpi(1, 2, null, c0477l.asBinder(), null, null);
    }

    public static zzpi m4257a(C0480m c0480m) {
        return new zzpi(1, 2, null, null, null, c0480m.asBinder());
    }

    int m4258a() {
        return this.f2508f;
    }

    IBinder m4259b() {
        return this.f2505c == null ? null : this.f2505c.asBinder();
    }

    IBinder m4260c() {
        return this.f2507e == null ? null : this.f2507e.asBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        am.m4008a(this, parcel, i);
    }
}
