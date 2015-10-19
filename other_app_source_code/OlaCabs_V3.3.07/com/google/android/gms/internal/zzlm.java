package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class zzlm implements SafeParcelable {
    public static final C0508n CREATOR;
    final int f2482a;
    private final long f2483b;
    private String f2484c;
    private final String f2485d;
    private final String f2486e;
    private final String f2487f;
    private final String f2488g;
    private final String f2489h;
    private final long f2490i;
    private long f2491j;

    static {
        CREATOR = new C0508n();
    }

    zzlm(int i, long j, String str, String str2, String str3, String str4, String str5, String str6, long j2) {
        this.f2482a = i;
        this.f2483b = j;
        this.f2484c = str;
        this.f2485d = str2;
        this.f2486e = str3;
        this.f2487f = str4;
        this.f2488g = str5;
        this.f2491j = -1;
        this.f2489h = str6;
        this.f2490i = j2;
    }

    public zzlm(long j, String str, String str2, String str3, String str4, String str5, String str6, long j2) {
        this(1, j, str, str2, str3, str4, str5, str6, j2);
    }

    public long m4243a() {
        return this.f2483b;
    }

    public String m4244b() {
        return this.f2484c;
    }

    public String m4245c() {
        return this.f2485d;
    }

    public String m4246d() {
        return this.f2486e;
    }

    public int describeContents() {
        return 0;
    }

    public String m4247e() {
        return this.f2487f;
    }

    public String m4248f() {
        return this.f2488g;
    }

    public String m4249g() {
        return this.f2489h;
    }

    public long m4250h() {
        return this.f2490i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0508n.m4140a(this, parcel, i);
    }
}
