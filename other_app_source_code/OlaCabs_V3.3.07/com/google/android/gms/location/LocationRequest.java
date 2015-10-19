package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.olacabs.customer.utils.Constants;
import org.apache.http.HttpStatus;

public final class LocationRequest implements SafeParcelable {
    public static final C0538n CREATOR;
    int f2615a;
    long f2616b;
    long f2617c;
    boolean f2618d;
    long f2619e;
    int f2620f;
    float f2621g;
    long f2622h;
    private final int f2623i;

    static {
        CREATOR = new C0538n();
    }

    public LocationRequest() {
        this.f2623i = 1;
        this.f2615a = HttpStatus.SC_PROCESSING;
        this.f2616b = Constants.MILLIS_IN_AN_HOUR;
        this.f2617c = 600000;
        this.f2618d = false;
        this.f2619e = Long.MAX_VALUE;
        this.f2620f = Integer.MAX_VALUE;
        this.f2621g = 0.0f;
        this.f2622h = 0;
    }

    LocationRequest(int i, int i2, long j, long j2, boolean z, long j3, int i3, float f, long j4) {
        this.f2623i = i;
        this.f2615a = i2;
        this.f2616b = j;
        this.f2617c = j2;
        this.f2618d = z;
        this.f2619e = j3;
        this.f2620f = i3;
        this.f2621g = f;
        this.f2622h = j4;
    }

    public static LocationRequest m4318a() {
        return new LocationRequest();
    }

    public static String m4319b(int i) {
        switch (i) {
            case HttpStatus.SC_CONTINUE /*100*/:
                return "PRIORITY_HIGH_ACCURACY";
            case HttpStatus.SC_PROCESSING /*102*/:
                return "PRIORITY_BALANCED_POWER_ACCURACY";
            case 104:
                return "PRIORITY_LOW_POWER";
            case 105:
                return "PRIORITY_NO_POWER";
            default:
                return "???";
        }
    }

    private static void m4320b(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("invalid displacement: " + f);
        }
    }

    private static void m4321c(int i) {
        switch (i) {
            case HttpStatus.SC_CONTINUE /*100*/:
            case HttpStatus.SC_PROCESSING /*102*/:
            case 104:
            case 105:
            default:
                throw new IllegalArgumentException("invalid quality: " + i);
        }
    }

    private static void m4322c(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("invalid interval: " + j);
        }
    }

    public LocationRequest m4323a(float f) {
        m4320b(f);
        this.f2621g = f;
        return this;
    }

    public LocationRequest m4324a(int i) {
        m4321c(i);
        this.f2615a = i;
        return this;
    }

    public LocationRequest m4325a(long j) {
        m4322c(j);
        this.f2616b = j;
        if (!this.f2618d) {
            this.f2617c = (long) (((double) this.f2616b) / 6.0d);
        }
        return this;
    }

    int m4326b() {
        return this.f2623i;
    }

    public LocationRequest m4327b(long j) {
        m4322c(j);
        this.f2618d = true;
        this.f2617c = j;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) obj;
        return this.f2615a == locationRequest.f2615a && this.f2616b == locationRequest.f2616b && this.f2617c == locationRequest.f2617c && this.f2618d == locationRequest.f2618d && this.f2619e == locationRequest.f2619e && this.f2620f == locationRequest.f2620f && this.f2621g == locationRequest.f2621g;
    }

    public int hashCode() {
        return C0452t.m3843a(Integer.valueOf(this.f2615a), Long.valueOf(this.f2616b), Long.valueOf(this.f2617c), Boolean.valueOf(this.f2618d), Long.valueOf(this.f2619e), Integer.valueOf(this.f2620f), Float.valueOf(this.f2621g));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Request[").append(m4319b(this.f2615a));
        if (this.f2615a != 105) {
            stringBuilder.append(" requested=");
            stringBuilder.append(this.f2616b + "ms");
        }
        stringBuilder.append(" fastest=");
        stringBuilder.append(this.f2617c + "ms");
        if (this.f2622h > this.f2616b) {
            stringBuilder.append(" maxWait=");
            stringBuilder.append(this.f2622h + "ms");
        }
        if (this.f2619e != Long.MAX_VALUE) {
            long elapsedRealtime = this.f2619e - SystemClock.elapsedRealtime();
            stringBuilder.append(" expireIn=");
            stringBuilder.append(elapsedRealtime + "ms");
        }
        if (this.f2620f != Integer.MAX_VALUE) {
            stringBuilder.append(" num=").append(this.f2620f);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0538n.m4376a(this, parcel, i);
    }
}
