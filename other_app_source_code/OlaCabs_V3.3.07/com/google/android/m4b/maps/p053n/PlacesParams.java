package com.google.android.m4b.maps.p053n;

import android.annotation.SuppressLint;
import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Objects;
import java.util.Arrays;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.n.c */
public final class PlacesParams implements C0591c {
    public static final PlacesParamsCreator CREATOR;
    public final int f7668a;
    public final String f7669b;
    public final String f7670c;

    static {
        CREATOR = new PlacesParamsCreator();
    }

    public PlacesParams(int i, String str, String str2) {
        this.f7668a = i;
        this.f7669b = str;
        this.f7670c = str2;
    }

    public PlacesParams(String str, Locale locale) {
        this.f7668a = 0;
        this.f7669b = str;
        this.f7670c = locale.toString();
    }

    @SuppressLint({"DefaultLocale"})
    public final String toString() {
        return Objects.m10456a(this).m10455a("clientPackageName", this.f7669b).m10455a("locale", this.f7670c).toString();
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f7669b, this.f7670c});
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PlacesParams)) {
            return false;
        }
        PlacesParams placesParams = (PlacesParams) obj;
        if (this.f7670c.equals(placesParams.f7670c) && this.f7669b.equals(placesParams.f7669b)) {
            return true;
        }
        return false;
    }

    public final int describeContents() {
        PlacesParamsCreator placesParamsCreator = CREATOR;
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        PlacesParamsCreator placesParamsCreator = CREATOR;
        PlacesParamsCreator.m11053a(this, parcel);
    }
}
