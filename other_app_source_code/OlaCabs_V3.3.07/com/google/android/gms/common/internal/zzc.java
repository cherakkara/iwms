package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Set;

public class zzc implements SafeParcelable {
    public static final Creator<zzc> CREATOR;
    final int f2322a;
    final IBinder f2323b;
    final Scope[] f2324c;

    static {
        CREATOR = new C0417f();
    }

    zzc(int i, IBinder iBinder, Scope[] scopeArr) {
        this.f2322a = i;
        this.f2323b = iBinder;
        this.f2324c = scopeArr;
    }

    public zzc(C0411p c0411p, Set<Scope> set) {
        this(1, c0411p.asBinder(), (Scope[]) set.toArray(new Scope[set.size()]));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0417f.m3569a(this, parcel, i);
    }
}
