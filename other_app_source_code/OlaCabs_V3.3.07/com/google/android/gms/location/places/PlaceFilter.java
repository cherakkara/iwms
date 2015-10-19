package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.C0452t.C0451a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class PlaceFilter implements SafeParcelable {
    public static final C0545e CREATOR;
    final int f2669a;
    final List<Integer> f2670b;
    final boolean f2671c;
    final List<zzj> f2672d;
    final List<String> f2673e;
    private final Set<Integer> f2674f;
    private final Set<zzj> f2675g;
    private final Set<String> f2676h;

    static {
        CREATOR = new C0545e();
    }

    public PlaceFilter() {
        this(false, null);
    }

    PlaceFilter(int i, List<Integer> list, boolean z, List<String> list2, List<zzj> list3) {
        this.f2669a = i;
        this.f2670b = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.f2671c = z;
        this.f2672d = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.f2673e = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.f2674f = m4393a(this.f2670b);
        this.f2675g = m4393a(this.f2672d);
        this.f2676h = m4393a(this.f2673e);
    }

    public PlaceFilter(Collection<Integer> collection, boolean z, Collection<String> collection2, Collection<zzj> collection3) {
        this(0, m4392a((Collection) collection), z, m4392a((Collection) collection2), m4392a((Collection) collection3));
    }

    public PlaceFilter(boolean z, Collection<String> collection) {
        this(null, z, collection, null);
    }

    private static <E> List<E> m4392a(Collection<E> collection) {
        return (collection == null || collection.isEmpty()) ? Collections.emptyList() : new ArrayList(collection);
    }

    private static <E> Set<E> m4393a(List<E> list) {
        return (list == null || list.isEmpty()) ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet(list));
    }

    public int describeContents() {
        C0545e c0545e = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceFilter)) {
            return false;
        }
        PlaceFilter placeFilter = (PlaceFilter) obj;
        return this.f2674f.equals(placeFilter.f2674f) && this.f2671c == placeFilter.f2671c && this.f2675g.equals(placeFilter.f2675g) && this.f2676h.equals(placeFilter.f2676h);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2674f, Boolean.valueOf(this.f2671c), this.f2675g, this.f2676h);
    }

    public String toString() {
        C0451a a = C0452t.m3844a((Object) this);
        if (!this.f2674f.isEmpty()) {
            a.m3842a("types", this.f2674f);
        }
        a.m3842a("requireOpenNow", Boolean.valueOf(this.f2671c));
        if (!this.f2676h.isEmpty()) {
            a.m3842a("placeIds", this.f2676h);
        }
        if (!this.f2675g.isEmpty()) {
            a.m3842a("requestedUserDataTypes", this.f2675g);
        }
        return a.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0545e c0545e = CREATOR;
        C0545e.m4409a(this, parcel, i);
    }
}
