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

public class AutocompleteFilter implements SafeParcelable {
    public static final C0543c CREATOR;
    final int f2665a;
    final boolean f2666b;
    final List<Integer> f2667c;
    private final Set<Integer> f2668d;

    static {
        CREATOR = new C0543c();
    }

    AutocompleteFilter(int i, boolean z, Collection<Integer> collection) {
        this.f2665a = i;
        this.f2666b = z;
        this.f2667c = collection == null ? Collections.emptyList() : new ArrayList(collection);
        if (this.f2667c.isEmpty()) {
            this.f2668d = Collections.emptySet();
        } else {
            this.f2668d = Collections.unmodifiableSet(new HashSet(this.f2667c));
        }
    }

    public boolean m4391a() {
        return this.f2666b;
    }

    public int describeContents() {
        C0543c c0543c = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutocompleteFilter)) {
            return false;
        }
        AutocompleteFilter autocompleteFilter = (AutocompleteFilter) obj;
        return this.f2668d.equals(autocompleteFilter.f2668d) && this.f2666b == autocompleteFilter.f2666b;
    }

    public int hashCode() {
        return C0452t.m3843a(Boolean.valueOf(this.f2666b), this.f2668d);
    }

    public String toString() {
        C0451a a = C0452t.m3844a((Object) this);
        if (!this.f2666b) {
            a.m3842a("restrictedToPlaces", Boolean.valueOf(this.f2666b));
        }
        a.m3842a("placeTypes", this.f2668d);
        return a.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0543c c0543c = CREATOR;
        C0543c.m4403a(this, parcel, i);
    }
}
