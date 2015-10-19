package com.google.android.m4b.maps.p051l;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.l.f */
public final class ParcelableGeofence implements C0591c {
    public static final ParcelableGeofenceCreator CREATOR;
    private final int f7479a;
    private final String f7480b;
    private final long f7481c;
    private final short f7482d;
    private final double f7483e;
    private final double f7484f;
    private final float f7485g;
    private final int f7486h;
    private final int f7487i;
    private final int f7488j;

    static {
        CREATOR = new ParcelableGeofenceCreator();
    }

    public ParcelableGeofence(int i, String str, int i2, short s, double d, double d2, float f, long j, int i3, int i4) {
        if (str == null || str.length() > 100) {
            throw new IllegalArgumentException("requestId is null or too long: " + str);
        } else if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        } else if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        } else {
            int i5 = i2 & 7;
            if (i5 == 0) {
                throw new IllegalArgumentException("No supported transition specified: " + i2);
            }
            this.f7479a = i;
            this.f7482d = s;
            this.f7480b = str;
            this.f7483e = d;
            this.f7484f = d2;
            this.f7485g = f;
            this.f7481c = j;
            this.f7486h = i5;
            this.f7487i = i3;
            this.f7488j = i4;
        }
    }

    public final int m10660a() {
        return this.f7479a;
    }

    public final short m10661b() {
        return this.f7482d;
    }

    public final double m10662c() {
        return this.f7483e;
    }

    public final double m10663d() {
        return this.f7484f;
    }

    public final float m10664e() {
        return this.f7485g;
    }

    public final String m10665f() {
        return this.f7480b;
    }

    public final long m10666g() {
        return this.f7481c;
    }

    public final int m10667h() {
        return this.f7486h;
    }

    public final int m10668i() {
        return this.f7487i;
    }

    public final int m10669j() {
        return this.f7488j;
    }

    public final int describeContents() {
        ParcelableGeofenceCreator parcelableGeofenceCreator = CREATOR;
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ParcelableGeofenceCreator parcelableGeofenceCreator = CREATOR;
        ParcelableGeofenceCreator.m10670a(this, parcel);
    }

    public final String toString() {
        String str;
        Locale locale = Locale.US;
        String str2 = "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]";
        Object[] objArr = new Object[9];
        switch (this.f7482d) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                str = "CIRCLE";
                break;
            default:
                str = null;
                break;
        }
        objArr[0] = str;
        objArr[1] = this.f7480b;
        objArr[2] = Integer.valueOf(this.f7486h);
        objArr[3] = Double.valueOf(this.f7483e);
        objArr[4] = Double.valueOf(this.f7484f);
        objArr[5] = Float.valueOf(this.f7485g);
        objArr[6] = Integer.valueOf(this.f7487i / Constants.MILLIS_IN_A_SECOND);
        objArr[7] = Integer.valueOf(this.f7488j);
        objArr[8] = Long.valueOf(this.f7481c);
        return String.format(locale, str2, objArr);
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f7483e);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.f7484f);
        return (((((((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.f7485g)) * 31) + this.f7482d) * 31) + this.f7486h;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ParcelableGeofence)) {
            return false;
        }
        ParcelableGeofence parcelableGeofence = (ParcelableGeofence) obj;
        if (this.f7485g != parcelableGeofence.f7485g) {
            return false;
        }
        if (this.f7483e != parcelableGeofence.f7483e) {
            return false;
        }
        if (this.f7484f != parcelableGeofence.f7484f) {
            return false;
        }
        if (this.f7482d != parcelableGeofence.f7482d) {
            return false;
        }
        return true;
    }
}
