package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.olacabs.customer.p076d.ao;

public final class Status implements C0206g, SafeParcelable {
    public static final C0230i CREATOR;
    public static final Status f1987a;
    public static final Status f1988b;
    public static final Status f1989c;
    public static final Status f1990d;
    public static final Status f1991e;
    private final int f1992f;
    private final int f1993g;
    private final String f1994h;
    private final PendingIntent f1995i;

    static {
        f1987a = new Status(0);
        f1988b = new Status(14);
        f1989c = new Status(8);
        f1990d = new Status(15);
        f1991e = new Status(16);
        CREATOR = new C0230i();
    }

    public Status(int i) {
        this(i, null);
    }

    Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.f1992f = i;
        this.f1993g = i2;
        this.f1994h = str;
        this.f1995i = pendingIntent;
    }

    public Status(int i, String str) {
        this(1, i, str, null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    private String m3168h() {
        return this.f1994h != null ? this.f1994h : C0219c.m3205a(this.f1993g);
    }

    public Status m3169a() {
        return this;
    }

    public void m3170a(Activity activity, int i) throws SendIntentException {
        if (m3174e()) {
            activity.startIntentSenderForResult(this.f1995i.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    PendingIntent m3171b() {
        return this.f1995i;
    }

    public String m3172c() {
        return this.f1994h;
    }

    int m3173d() {
        return this.f1992f;
    }

    public int describeContents() {
        return 0;
    }

    public boolean m3174e() {
        return this.f1995i != null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.f1992f == status.f1992f && this.f1993g == status.f1993g && C0452t.m3845a(this.f1994h, status.f1994h) && C0452t.m3845a(this.f1995i, status.f1995i);
    }

    public boolean m3175f() {
        return this.f1993g <= 0;
    }

    public int m3176g() {
        return this.f1993g;
    }

    public int hashCode() {
        return C0452t.m3843a(Integer.valueOf(this.f1992f), Integer.valueOf(this.f1993g), this.f1994h, this.f1995i);
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("statusCode", m3168h()).m3842a(ao.DEVICE_RESOLUTION_KEY, this.f1995i).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0230i.m3236a(this, parcel, i);
    }
}
