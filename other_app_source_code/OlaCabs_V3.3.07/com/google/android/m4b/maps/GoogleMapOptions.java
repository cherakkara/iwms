package com.google.android.m4b.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.ConversionUtil;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;

public final class GoogleMapOptions implements C0591c {
    public static final GoogleMapOptionsCreator CREATOR;
    private final int f2847a;
    private Boolean f2848b;
    private Boolean f2849c;
    private int f2850d;
    private CameraPosition f2851e;
    private Boolean f2852f;
    private Boolean f2853g;
    private Boolean f2854h;
    private Boolean f2855i;
    private Boolean f2856j;
    private Boolean f2857k;
    private Boolean f2858l;
    private Boolean f2859m;

    static {
        CREATOR = new GoogleMapOptionsCreator();
    }

    GoogleMapOptions(int i, byte b, byte b2, int i2, CameraPosition cameraPosition, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10) {
        this.f2850d = -1;
        this.f2847a = i;
        this.f2848b = ConversionUtil.m11087a(b);
        this.f2849c = ConversionUtil.m11087a(b2);
        this.f2850d = i2;
        this.f2851e = cameraPosition;
        this.f2852f = ConversionUtil.m11087a(b3);
        this.f2853g = ConversionUtil.m11087a(b4);
        this.f2854h = ConversionUtil.m11087a(b5);
        this.f2855i = ConversionUtil.m11087a(b6);
        this.f2856j = ConversionUtil.m11087a(b7);
        this.f2857k = ConversionUtil.m11087a(b8);
        this.f2858l = ConversionUtil.m11087a(b9);
        this.f2859m = ConversionUtil.m11087a(b10);
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            GoogleMapOptionsCreatorCheddar.m10304a(this, parcel, i);
        } else {
            GoogleMapOptionsCreator.m10232a(this, parcel, i);
        }
    }

    final int m4629a() {
        return this.f2847a;
    }

    final byte m4633b() {
        return ConversionUtil.m11086a(this.f2848b);
    }

    final byte m4635c() {
        return ConversionUtil.m11086a(this.f2849c);
    }

    final byte m4637d() {
        return ConversionUtil.m11086a(this.f2852f);
    }

    final byte m4639e() {
        return ConversionUtil.m11086a(this.f2853g);
    }

    final byte m4641f() {
        return ConversionUtil.m11086a(this.f2854h);
    }

    final byte m4643g() {
        return ConversionUtil.m11086a(this.f2855i);
    }

    final byte m4645h() {
        return ConversionUtil.m11086a(this.f2856j);
    }

    final byte m4647i() {
        return ConversionUtil.m11086a(this.f2857k);
    }

    final byte m4649j() {
        return ConversionUtil.m11086a(this.f2858l);
    }

    final byte m4651k() {
        return ConversionUtil.m11086a(this.f2859m);
    }

    public GoogleMapOptions() {
        this.f2850d = -1;
        this.f2847a = 1;
    }

    public final GoogleMapOptions m4632a(boolean z) {
        this.f2848b = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions m4634b(boolean z) {
        this.f2849c = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions m4630a(int i) {
        this.f2850d = i;
        return this;
    }

    public final GoogleMapOptions m4631a(CameraPosition cameraPosition) {
        this.f2851e = cameraPosition;
        return this;
    }

    public final GoogleMapOptions m4636c(boolean z) {
        this.f2852f = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions m4638d(boolean z) {
        this.f2853g = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions m4640e(boolean z) {
        this.f2854h = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions m4642f(boolean z) {
        this.f2855i = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions m4644g(boolean z) {
        this.f2856j = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions m4646h(boolean z) {
        this.f2857k = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions m4648i(boolean z) {
        this.f2858l = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions m4650j(boolean z) {
        this.f2859m = Boolean.valueOf(z);
        return this;
    }

    public final Boolean m4652l() {
        return this.f2848b;
    }

    public final Boolean m4653m() {
        return this.f2849c;
    }

    public final int m4654n() {
        return this.f2850d;
    }

    public final CameraPosition m4655o() {
        return this.f2851e;
    }

    public final Boolean m4656p() {
        return this.f2852f;
    }

    public final Boolean m4657q() {
        return this.f2853g;
    }

    public final Boolean m4658r() {
        return this.f2854h;
    }

    public final Boolean m4659s() {
        return this.f2855i;
    }

    public final Boolean m4660t() {
        return this.f2856j;
    }

    public final Boolean m4661u() {
        return this.f2857k;
    }

    public final Boolean m4662v() {
        return this.f2858l;
    }

    public final Boolean m4663w() {
        return this.f2859m;
    }

    public static GoogleMapOptions m4628a(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.MapM4bAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(0)) {
            googleMapOptions.m4630a(obtainAttributes.getInt(0, -1));
        }
        if (obtainAttributes.hasValue(14)) {
            googleMapOptions.m4632a(obtainAttributes.getBoolean(14, false));
        }
        if (obtainAttributes.hasValue(13)) {
            googleMapOptions.m4634b(obtainAttributes.getBoolean(13, false));
        }
        if (obtainAttributes.hasValue(7)) {
            googleMapOptions.m4638d(obtainAttributes.getBoolean(7, true));
        }
        if (obtainAttributes.hasValue(8)) {
            googleMapOptions.m4646h(obtainAttributes.getBoolean(8, true));
        }
        if (obtainAttributes.hasValue(9)) {
            googleMapOptions.m4640e(obtainAttributes.getBoolean(9, true));
        }
        if (obtainAttributes.hasValue(10)) {
            googleMapOptions.m4644g(obtainAttributes.getBoolean(10, true));
        }
        if (obtainAttributes.hasValue(12)) {
            googleMapOptions.m4642f(obtainAttributes.getBoolean(12, true));
        }
        if (obtainAttributes.hasValue(11)) {
            googleMapOptions.m4636c(obtainAttributes.getBoolean(11, true));
        }
        if (obtainAttributes.hasValue(6)) {
            googleMapOptions.m4648i(obtainAttributes.getBoolean(6, false));
        }
        if (obtainAttributes.hasValue(15)) {
            googleMapOptions.m4650j(obtainAttributes.getBoolean(15, true));
        }
        googleMapOptions.m4631a(CameraPosition.m10709a(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }
}
