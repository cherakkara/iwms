package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class zzuw implements SafeParcelable {
    public static final Creator<zzuw> CREATOR;
    final int f2601a;
    final boolean f2602b;
    final List<Scope> f2603c;

    static {
        CREATOR = new bl();
    }

    zzuw(int i, boolean z, List<Scope> list) {
        this.f2601a = i;
        this.f2602b = z;
        this.f2603c = list;
    }

    public zzuw(boolean z, Set<Scope> set) {
        this(1, z, m4308a(set));
    }

    private static List<Scope> m4308a(Set<Scope> set) {
        return set == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(set));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        bl.m4078a(this, parcel, i);
    }
}
