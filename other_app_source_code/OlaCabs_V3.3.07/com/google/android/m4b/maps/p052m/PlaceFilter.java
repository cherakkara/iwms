package com.google.android.m4b.maps.p052m;

import android.annotation.SuppressLint;
import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Objects;
import com.newrelic.agent.android.instrumentation.Trace;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.m.c */
public final class PlaceFilter implements C0591c {
    public static final PlaceFilterCreator CREATOR;
    final int f7493a;
    final List<PlaceType> f7494b;
    final List<UserDataType> f7495c;
    final List<String> f7496d;
    private final String f7497e;
    private final boolean f7498f;
    private final String f7499g;
    private final Set<PlaceType> f7500h;
    private final Set<UserDataType> f7501i;
    private final Set<String> f7502j;

    static {
        CREATOR = new PlaceFilterCreator();
    }

    PlaceFilter(int i, List<PlaceType> list, String str, boolean z, List<UserDataType> list2, String str2, List<String> list3) {
        List emptyList;
        this.f7493a = i;
        if (list == null) {
            emptyList = Collections.emptyList();
        } else {
            emptyList = Collections.unmodifiableList(list);
        }
        this.f7494b = emptyList;
        if (str == null) {
            str = Trace.NULL;
        }
        this.f7497e = str;
        this.f7498f = z;
        if (list2 == null) {
            emptyList = Collections.emptyList();
        } else {
            emptyList = Collections.unmodifiableList(list2);
        }
        this.f7495c = emptyList;
        if (str2 == null) {
            str2 = Trace.NULL;
        }
        this.f7499g = str2;
        if (list3 == null) {
            emptyList = Collections.emptyList();
        } else {
            emptyList = Collections.unmodifiableList(list3);
        }
        this.f7496d = emptyList;
        this.f7500h = PlaceFilter.m10677a(this.f7494b);
        this.f7501i = PlaceFilter.m10677a(this.f7495c);
        this.f7502j = PlaceFilter.m10677a(this.f7496d);
    }

    private static <E> Set<E> m10677a(List<E> list) {
        if (list.isEmpty()) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet(list));
    }

    @Deprecated
    public final String m10678a() {
        return this.f7497e;
    }

    public final boolean m10679b() {
        return this.f7498f;
    }

    public final String m10680c() {
        return this.f7499g;
    }

    @SuppressLint({"DefaultLocale"})
    public final String toString() {
        return Objects.m10456a(this).m10455a("types", this.f7500h).m10455a("placeIds", this.f7502j).m10455a("requireOpenNow", Boolean.valueOf(this.f7498f)).m10455a("userAccountName", this.f7499g).m10455a("requestedUserDataTypes", this.f7501i).toString();
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f7500h, Boolean.valueOf(this.f7498f), this.f7501i, this.f7499g, this.f7502j});
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceFilter)) {
            return false;
        }
        PlaceFilter placeFilter = (PlaceFilter) obj;
        if (this.f7500h.equals(placeFilter.f7500h) && this.f7498f == placeFilter.f7498f && this.f7499g.equals(placeFilter.f7499g) && this.f7501i.equals(placeFilter.f7501i) && this.f7502j.equals(placeFilter.f7502j)) {
            return true;
        }
        return false;
    }

    public final int describeContents() {
        PlaceFilterCreator placeFilterCreator = CREATOR;
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        PlaceFilterCreator placeFilterCreator = CREATOR;
        PlaceFilterCreator.m10682a(this, parcel);
    }
}
