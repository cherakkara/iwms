package com.google.android.m4b.maps.p044k;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.m4b.maps.p037h.C0591c;
import com.olacabs.customer.utils.Constants;
import java.util.Arrays;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.k.e */
public final class LocationRequest implements C0591c {
    public static final LocationRequestCreator CREATOR;
    int f7448a;
    long f7449b;
    long f7450c;
    boolean f7451d;
    long f7452e;
    int f7453f;
    float f7454g;
    private final int f7455h;

    public static LocationRequest m10548a() {
        return new LocationRequest();
    }

    public LocationRequest() {
        this.f7455h = 1;
        this.f7448a = HttpStatus.SC_PROCESSING;
        this.f7449b = Constants.MILLIS_IN_AN_HOUR;
        this.f7450c = 600000;
        this.f7451d = false;
        this.f7452e = Long.MAX_VALUE;
        this.f7453f = Integer.MAX_VALUE;
        this.f7454g = 0.0f;
    }

    public final LocationRequest m10550a(int i) {
        switch (100) {
            case HttpStatus.SC_CONTINUE /*100*/:
            case HttpStatus.SC_PROCESSING /*102*/:
            case 104:
            case 105:
                this.f7448a = 100;
                return this;
            default:
                throw new IllegalArgumentException("invalid quality: " + 100);
        }
    }

    public final LocationRequest m10551a(long j) {
        LocationRequest.m10549c(5000);
        this.f7449b = 5000;
        if (!this.f7451d) {
            this.f7450c = (long) (((double) this.f7449b) / 6.0d);
        }
        return this;
    }

    public final LocationRequest m10553b(long j) {
        LocationRequest.m10549c(16);
        this.f7451d = true;
        this.f7450c = 16;
        return this;
    }

    private static void m10549c(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("invalid interval: " + j);
        }
    }

    static {
        CREATOR = new LocationRequestCreator();
    }

    LocationRequest(int i, int i2, long j, long j2, boolean z, long j3, int i3, float f) {
        this.f7455h = i;
        this.f7448a = i2;
        this.f7449b = j;
        this.f7450c = j2;
        this.f7451d = z;
        this.f7452e = j3;
        this.f7453f = i3;
        this.f7454g = f;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        LocationRequestCreator.m10555a(this, parcel);
    }

    public final String toString() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder append = stringBuilder.append("Request[");
        switch (this.f7448a) {
            case HttpStatus.SC_CONTINUE /*100*/:
                str = "PRIORITY_HIGH_ACCURACY";
                break;
            case HttpStatus.SC_PROCESSING /*102*/:
                str = "PRIORITY_BALANCED_POWER_ACCURACY";
                break;
            case 104:
                str = "PRIORITY_LOW_POWER";
                break;
            case 105:
                str = "PRIORITY_NO_POWER";
                break;
            default:
                str = "???";
                break;
        }
        append.append(str);
        if (this.f7448a != 105) {
            stringBuilder.append(" requested=");
            stringBuilder.append(this.f7449b + "ms");
        }
        stringBuilder.append(" fastest=");
        stringBuilder.append(this.f7450c + "ms");
        if (this.f7452e != Long.MAX_VALUE) {
            long elapsedRealtime = this.f7452e - SystemClock.elapsedRealtime();
            stringBuilder.append(" expireIn=");
            stringBuilder.append(elapsedRealtime + "ms");
        }
        if (this.f7453f != Integer.MAX_VALUE) {
            stringBuilder.append(" num=").append(this.f7453f);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f7448a), Long.valueOf(this.f7449b), Long.valueOf(this.f7450c), Boolean.valueOf(this.f7451d), Long.valueOf(this.f7452e), Integer.valueOf(this.f7453f), Float.valueOf(this.f7454g)});
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) obj;
        if (this.f7448a == locationRequest.f7448a && this.f7449b == locationRequest.f7449b && this.f7450c == locationRequest.f7450c && this.f7451d == locationRequest.f7451d && this.f7452e == locationRequest.f7452e && this.f7453f == locationRequest.f7453f && this.f7454g == locationRequest.f7454g) {
            return true;
        }
        return false;
    }

    final int m10552b() {
        return this.f7455h;
    }
}
