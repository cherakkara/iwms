package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class zzb implements SafeParcelable {
    public static final C0535k CREATOR;
    private static final List<Integer> f2698a;
    private static final List<Integer> f2699b;
    private static final List<Integer> f2700c;
    private static final List<Integer> f2701d;
    private final int f2702e;
    private final List<Integer> f2703f;

    static {
        f2698a = Collections.unmodifiableList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18)}));
        f2699b = Collections.unmodifiableList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(18)}));
        f2700c = Collections.unmodifiableList(Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(6), Integer.valueOf(8), Integer.valueOf(10), Integer.valueOf(12), Integer.valueOf(14), Integer.valueOf(16)}));
        f2701d = Collections.unmodifiableList(Arrays.asList(new Integer[]{Integer.valueOf(3), Integer.valueOf(5), Integer.valueOf(7), Integer.valueOf(9), Integer.valueOf(11), Integer.valueOf(13), Integer.valueOf(15), Integer.valueOf(17)}));
        CREATOR = new C0535k();
    }

    zzb(int i, List<Integer> list) {
        this.f2702e = i;
        this.f2703f = list;
    }

    public int m4433a() {
        return this.f2702e;
    }

    public List<Integer> m4434b() {
        return this.f2703f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0535k.m4371a(this, parcel, i);
    }
}
