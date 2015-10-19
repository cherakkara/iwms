package com.google.android.m4b.maps;

import android.os.Parcel;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.StreetViewPanoramaCamera;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.ConversionUtil;

public final class StreetViewPanoramaOptions implements C0591c {
    public static final StreetViewPanoramaOptionsCreator CREATOR;
    private final int f2860a;
    private StreetViewPanoramaCamera f2861b;
    private String f2862c;
    private LatLng f2863d;
    private Integer f2864e;
    private Boolean f2865f;
    private Boolean f2866g;
    private Boolean f2867h;
    private Boolean f2868i;
    private Boolean f2869j;

    static {
        CREATOR = new StreetViewPanoramaOptionsCreator();
    }

    StreetViewPanoramaOptions(int i, StreetViewPanoramaCamera streetViewPanoramaCamera, String str, LatLng latLng, Integer num, byte b, byte b2, byte b3, byte b4, byte b5) {
        this.f2865f = Boolean.valueOf(true);
        this.f2866g = Boolean.valueOf(true);
        this.f2867h = Boolean.valueOf(true);
        this.f2868i = Boolean.valueOf(true);
        this.f2860a = i;
        this.f2861b = streetViewPanoramaCamera;
        this.f2863d = latLng;
        this.f2864e = num;
        this.f2862c = str;
        this.f2865f = ConversionUtil.m11087a(b);
        this.f2866g = ConversionUtil.m11087a(b2);
        this.f2867h = ConversionUtil.m11087a(b3);
        this.f2868i = ConversionUtil.m11087a(b4);
        this.f2869j = ConversionUtil.m11087a(b5);
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        StreetViewPanoramaOptionsCreator.m10513a(this, parcel, i);
    }

    final int m4664a() {
        return this.f2860a;
    }

    final byte m4665b() {
        return ConversionUtil.m11086a(this.f2865f);
    }

    final byte m4666c() {
        return ConversionUtil.m11086a(this.f2866g);
    }

    final byte m4667d() {
        return ConversionUtil.m11086a(this.f2867h);
    }

    final byte m4668e() {
        return ConversionUtil.m11086a(this.f2868i);
    }

    final byte m4669f() {
        return ConversionUtil.m11086a(this.f2869j);
    }

    public StreetViewPanoramaOptions() {
        this.f2865f = Boolean.valueOf(true);
        this.f2866g = Boolean.valueOf(true);
        this.f2867h = Boolean.valueOf(true);
        this.f2868i = Boolean.valueOf(true);
        this.f2860a = 1;
    }

    public final StreetViewPanoramaCamera m4670g() {
        return this.f2861b;
    }

    public final LatLng m4671h() {
        return this.f2863d;
    }

    public final Integer m4672i() {
        return this.f2864e;
    }

    public final String m4673j() {
        return this.f2862c;
    }

    public final Boolean m4674k() {
        return this.f2865f;
    }

    public final Boolean m4675l() {
        return this.f2866g;
    }

    public final Boolean m4676m() {
        return this.f2867h;
    }

    public final Boolean m4677n() {
        return this.f2868i;
    }

    public final Boolean m4678o() {
        return this.f2869j;
    }
}
