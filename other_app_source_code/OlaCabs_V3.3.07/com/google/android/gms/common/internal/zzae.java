package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzae implements SafeParcelable {
    public static final Creator<zzae> CREATOR;
    final int f2316a;
    final IBinder f2317b;
    private final int f2318c;
    private final Scope[] f2319d;
    private final Bundle f2320e;
    private final String f2321f;

    static {
        CREATOR = new C0415d();
    }

    zzae(int i, int i2, IBinder iBinder, Scope[] scopeArr, Bundle bundle, String str) {
        this.f2316a = i;
        this.f2318c = i2;
        this.f2317b = iBinder;
        this.f2319d = scopeArr;
        this.f2320e = bundle;
        this.f2321f = str;
    }

    public zzae(C0411p c0411p, Scope[] scopeArr, String str, Bundle bundle) {
        this(1, 7095000, c0411p == null ? null : c0411p.asBinder(), scopeArr, bundle, str);
    }

    public int m3864a() {
        return this.f2318c;
    }

    public Scope[] m3865b() {
        return this.f2319d;
    }

    public String m3866c() {
        return this.f2321f;
    }

    public Bundle m3867d() {
        return this.f2320e;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0415d.m3563a(this, parcel, i);
    }
}
