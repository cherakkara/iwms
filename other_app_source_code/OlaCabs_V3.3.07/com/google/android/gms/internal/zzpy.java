package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.olacabs.customer.utils.Constants;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class zzpy implements SafeParcelable {
    public static final ar CREATOR;
    final int f2528a;
    final boolean f2529b;
    private final String f2530c;
    private final Bundle f2531d;
    @Deprecated
    private final zzqd f2532e;
    private final LatLng f2533f;
    private final float f2534g;
    private final LatLngBounds f2535h;
    private final String f2536i;
    private final Uri f2537j;
    private final boolean f2538k;
    private final float f2539l;
    private final int f2540m;
    private final long f2541n;
    private final List<Integer> f2542o;
    private final List<Integer> f2543p;
    private final String f2544q;
    private final String f2545r;
    private final String f2546s;
    private final String f2547t;
    private final List<String> f2548u;
    private final Map<Integer, String> f2549v;
    private final TimeZone f2550w;
    private Locale f2551x;
    private au f2552y;

    static {
        CREATOR = new ar();
    }

    zzpy(int i, String str, List<Integer> list, List<Integer> list2, Bundle bundle, String str2, String str3, String str4, String str5, List<String> list3, LatLng latLng, float f, LatLngBounds latLngBounds, String str6, Uri uri, boolean z, float f2, int i2, long j, boolean z2, zzqd com_google_android_gms_internal_zzqd) {
        List emptyList;
        this.f2528a = i;
        this.f2530c = str;
        this.f2543p = Collections.unmodifiableList(list);
        this.f2542o = list2;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.f2531d = bundle;
        this.f2544q = str2;
        this.f2545r = str3;
        this.f2546s = str4;
        this.f2547t = str5;
        if (list3 == null) {
            emptyList = Collections.emptyList();
        }
        this.f2548u = emptyList;
        this.f2533f = latLng;
        this.f2534g = f;
        this.f2535h = latLngBounds;
        if (str6 == null) {
            str6 = "UTC";
        }
        this.f2536i = str6;
        this.f2537j = uri;
        this.f2538k = z;
        this.f2539l = f2;
        this.f2540m = i2;
        this.f2541n = j;
        this.f2549v = Collections.unmodifiableMap(new HashMap());
        this.f2550w = null;
        this.f2551x = null;
        this.f2529b = z2;
        this.f2532e = com_google_android_gms_internal_zzqd;
    }

    private void m4277a(String str) {
        if (this.f2529b && this.f2552y != null) {
            this.f2552y.m4033a(this.f2530c, str);
        }
    }

    public String m4278a() {
        m4277a("getId");
        return this.f2530c;
    }

    public List<Integer> m4279b() {
        m4277a("getPlaceTypes");
        return this.f2543p;
    }

    public List<Integer> m4280c() {
        m4277a("getTypesDeprecated");
        return this.f2542o;
    }

    public String m4281d() {
        m4277a("getName");
        return this.f2544q;
    }

    public int describeContents() {
        ar arVar = CREATOR;
        return 0;
    }

    public String m4282e() {
        m4277a("getAddress");
        return this.f2545r;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzpy)) {
            return false;
        }
        zzpy com_google_android_gms_internal_zzpy = (zzpy) obj;
        return this.f2530c.equals(com_google_android_gms_internal_zzpy.f2530c) && C0452t.m3845a(this.f2551x, com_google_android_gms_internal_zzpy.f2551x) && this.f2541n == com_google_android_gms_internal_zzpy.f2541n;
    }

    public LatLng m4283f() {
        m4277a("getLatLng");
        return this.f2533f;
    }

    public float m4284g() {
        m4277a("getLevelNumber");
        return this.f2534g;
    }

    public LatLngBounds m4285h() {
        m4277a("getViewport");
        return this.f2535h;
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2530c, this.f2551x, Long.valueOf(this.f2541n));
    }

    public Uri m4286i() {
        m4277a("getWebsiteUri");
        return this.f2537j;
    }

    public String m4287j() {
        m4277a("getPhoneNumber");
        return this.f2546s;
    }

    public String m4288k() {
        m4277a("getRegularOpenHours");
        return this.f2547t;
    }

    public List<String> m4289l() {
        m4277a("getAttributions");
        return this.f2548u;
    }

    public boolean m4290m() {
        m4277a("isPermanentlyClosed");
        return this.f2538k;
    }

    public float m4291n() {
        m4277a("getRating");
        return this.f2539l;
    }

    public int m4292o() {
        m4277a("getPriceLevel");
        return this.f2540m;
    }

    public long m4293p() {
        return this.f2541n;
    }

    public Bundle m4294q() {
        return this.f2531d;
    }

    public String m4295r() {
        return this.f2536i;
    }

    @Deprecated
    public zzqd m4296s() {
        m4277a("getLocalization");
        return this.f2532e;
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("id", this.f2530c).m3842a("placeTypes", this.f2543p).m3842a("locale", this.f2551x).m3842a(Constants.BUNDLE_NAME, this.f2544q).m3842a(Constants.BUNDLE_ADDRESS, this.f2545r).m3842a("phoneNumber", this.f2546s).m3842a("latlng", this.f2533f).m3842a("viewport", this.f2535h).m3842a("websiteUri", this.f2537j).m3842a("isPermanentlyClosed", Boolean.valueOf(this.f2538k)).m3842a("priceLevel", Integer.valueOf(this.f2540m)).m3842a("timestampSecs", Long.valueOf(this.f2541n)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        ar arVar = CREATOR;
        ar.m4024a(this, parcel, i);
    }
}
