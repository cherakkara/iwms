package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WebImage implements SafeParcelable {
    public static final Creator<WebImage> CREATOR;
    private final int f2220a;
    private final Uri f2221b;
    private final int f2222c;
    private final int f2223d;

    static {
        CREATOR = new C0408b();
    }

    WebImage(int i, Uri uri, int i2, int i3) {
        this.f2220a = i;
        this.f2221b = uri;
        this.f2222c = i2;
        this.f2223d = i3;
    }

    int m3540a() {
        return this.f2220a;
    }

    public Uri m3541b() {
        return this.f2221b;
    }

    public int m3542c() {
        return this.f2222c;
    }

    public int m3543d() {
        return this.f2223d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof WebImage)) {
            return false;
        }
        WebImage webImage = (WebImage) obj;
        return C0452t.m3845a(this.f2221b, webImage.f2221b) && this.f2222c == webImage.f2222c && this.f2223d == webImage.f2223d;
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2221b, Integer.valueOf(this.f2222c), Integer.valueOf(this.f2223d));
    }

    public String toString() {
        return String.format("Image %dx%d %s", new Object[]{Integer.valueOf(this.f2222c), Integer.valueOf(this.f2223d), this.f2221b.toString()});
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0408b.m3550a(this, parcel, i);
    }
}
