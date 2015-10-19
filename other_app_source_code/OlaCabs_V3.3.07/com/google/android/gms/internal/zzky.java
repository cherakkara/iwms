package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzld.C0522a;

public class zzky implements SafeParcelable {
    public static final C0500f CREATOR;
    private final int f2445a;
    private final zzla f2446b;

    static {
        CREATOR = new C0500f();
    }

    zzky(int i, zzla com_google_android_gms_internal_zzla) {
        this.f2445a = i;
        this.f2446b = com_google_android_gms_internal_zzla;
    }

    private zzky(zzla com_google_android_gms_internal_zzla) {
        this.f2445a = 1;
        this.f2446b = com_google_android_gms_internal_zzla;
    }

    public static zzky m4182a(C0522a<?, ?> c0522a) {
        if (c0522a instanceof zzla) {
            return new zzky((zzla) c0522a);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    int m4183a() {
        return this.f2445a;
    }

    zzla m4184b() {
        return this.f2446b;
    }

    public C0522a<?, ?> m4185c() {
        if (this.f2446b != null) {
            return this.f2446b;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }

    public int describeContents() {
        C0500f c0500f = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0500f c0500f = CREATOR;
        C0500f.m4116a(this, parcel, i);
    }
}
