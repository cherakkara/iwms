package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import com.olacabs.customer.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class AddPlaceRequest implements SafeParcelable {
    public static final Creator<AddPlaceRequest> CREATOR;
    final int f2658a;
    private final String f2659b;
    private final LatLng f2660c;
    private final String f2661d;
    private final List<Integer> f2662e;
    private final String f2663f;
    private final Uri f2664g;

    static {
        CREATOR = new C0542b();
    }

    AddPlaceRequest(int i, String str, LatLng latLng, String str2, List<Integer> list, String str3, Uri uri) {
        this.f2658a = i;
        this.f2659b = C0453u.m3848a(str);
        this.f2660c = (LatLng) C0453u.m3846a((Object) latLng);
        this.f2661d = str2;
        this.f2662e = new ArrayList(list);
        boolean z = (TextUtils.isEmpty(str3) && uri == null) ? false : true;
        C0453u.m3855b(z, "One of phone number or URI should be provided.");
        this.f2663f = str3;
        this.f2664g = uri;
    }

    public String m4385a() {
        return this.f2659b;
    }

    public LatLng m4386b() {
        return this.f2660c;
    }

    public String m4387c() {
        return this.f2661d;
    }

    public List<Integer> m4388d() {
        return this.f2662e;
    }

    public int describeContents() {
        return 0;
    }

    public String m4389e() {
        return this.f2663f;
    }

    public Uri m4390f() {
        return this.f2664g;
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a(Constants.BUNDLE_NAME, this.f2659b).m3842a("latLng", this.f2660c).m3842a(Constants.BUNDLE_ADDRESS, this.f2661d).m3842a("placeTypes", this.f2662e).m3842a("phoneNumer", this.f2663f).m3842a("websiteUri", this.f2664g).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0542b.m4400a(this, parcel, i);
    }
}
