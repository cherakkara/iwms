package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationSettingsRequest implements SafeParcelable {
    public static final Creator<LocationSettingsRequest> CREATOR;
    private final int f2627a;
    private final List<LocationRequest> f2628b;
    private final boolean f2629c;
    private final boolean f2630d;

    /* renamed from: com.google.android.gms.location.LocationSettingsRequest.a */
    public static final class C0526a {
        private final ArrayList<LocationRequest> f2624a;
        private boolean f2625b;
        private boolean f2626c;

        public C0526a() {
            this.f2624a = new ArrayList();
            this.f2625b = false;
            this.f2626c = false;
        }

        public C0526a m4328a(LocationRequest locationRequest) {
            this.f2624a.add(locationRequest);
            return this;
        }

        public C0526a m4329a(boolean z) {
            this.f2625b = z;
            return this;
        }

        public LocationSettingsRequest m4330a() {
            return new LocationSettingsRequest(this.f2625b, this.f2626c, null);
        }
    }

    static {
        CREATOR = new C0540p();
    }

    LocationSettingsRequest(int i, List<LocationRequest> list, boolean z, boolean z2) {
        this.f2627a = i;
        this.f2628b = list;
        this.f2629c = z;
        this.f2630d = z2;
    }

    private LocationSettingsRequest(List<LocationRequest> list, boolean z, boolean z2) {
        this(1, (List) list, z, z2);
    }

    public int m4331a() {
        return this.f2627a;
    }

    public List<LocationRequest> m4332b() {
        return Collections.unmodifiableList(this.f2628b);
    }

    public boolean m4333c() {
        return this.f2629c;
    }

    public boolean m4334d() {
        return this.f2630d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0540p.m4382a(this, parcel, i);
    }
}
