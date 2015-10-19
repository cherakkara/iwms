package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Scope implements SafeParcelable {
    public static final Creator<Scope> CREATOR;
    final int f1985a;
    private final String f1986b;

    static {
        CREATOR = new C0256m();
    }

    Scope(int i, String str) {
        C0453u.m3849a(str, (Object) "scopeUri must not be null or empty");
        this.f1985a = i;
        this.f1986b = str;
    }

    public Scope(String str) {
        this(1, str);
    }

    public String m3166a() {
        return this.f1986b;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj ? true : !(obj instanceof Scope) ? false : this.f1986b.equals(((Scope) obj).f1986b);
    }

    public int hashCode() {
        return this.f1986b.hashCode();
    }

    public String toString() {
        return this.f1986b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0256m.m3344a(this, parcel, i);
    }
}
