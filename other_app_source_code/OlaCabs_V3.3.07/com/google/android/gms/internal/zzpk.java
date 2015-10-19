package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.C0523d;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Locale;

public class zzpk implements SafeParcelable, C0523d {
    public static final an CREATOR;
    private final int f2509a;
    private final String f2510b;
    private final long f2511c;
    private final short f2512d;
    private final double f2513e;
    private final double f2514f;
    private final float f2515g;
    private final int f2516h;
    private final int f2517i;
    private final int f2518j;

    static {
        CREATOR = new an();
    }

    public zzpk(int i, String str, int i2, short s, double d, double d2, float f, long j, int i3, int i4) {
        m4265a(str);
        m4264a(f);
        m4263a(d, d2);
        int a = m4262a(i2);
        this.f2509a = i;
        this.f2512d = s;
        this.f2510b = str;
        this.f2513e = d;
        this.f2514f = d2;
        this.f2515g = f;
        this.f2511c = j;
        this.f2516h = a;
        this.f2517i = i3;
        this.f2518j = i4;
    }

    public zzpk(String str, int i, short s, double d, double d2, float f, long j, int i2, int i3) {
        this(1, str, i, s, d, d2, f, j, i2, i3);
    }

    private static int m4262a(int i) {
        int i2 = i & 7;
        if (i2 != 0) {
            return i2;
        }
        throw new IllegalArgumentException("No supported transition specified: " + i);
    }

    private static void m4263a(double d, double d2) {
        if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        }
    }

    private static void m4264a(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        }
    }

    private static void m4265a(String str) {
        if (str == null || str.length() > 100) {
            throw new IllegalArgumentException("requestId is null or too long: " + str);
        }
    }

    private static String m4266b(int i) {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return "CIRCLE";
            default:
                return null;
        }
    }

    public int m4267a() {
        return this.f2509a;
    }

    public short m4268b() {
        return this.f2512d;
    }

    public double m4269c() {
        return this.f2513e;
    }

    public double m4270d() {
        return this.f2514f;
    }

    public int describeContents() {
        an anVar = CREATOR;
        return 0;
    }

    public float m4271e() {
        return this.f2515g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof zzpk)) {
            return false;
        }
        zzpk com_google_android_gms_internal_zzpk = (zzpk) obj;
        return this.f2515g != com_google_android_gms_internal_zzpk.f2515g ? false : this.f2513e != com_google_android_gms_internal_zzpk.f2513e ? false : this.f2514f != com_google_android_gms_internal_zzpk.f2514f ? false : this.f2512d == com_google_android_gms_internal_zzpk.f2512d;
    }

    public String m4272f() {
        return this.f2510b;
    }

    public long m4273g() {
        return this.f2511c;
    }

    public int m4274h() {
        return this.f2516h;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f2513e);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.f2514f);
        return (((((((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.f2515g)) * 31) + this.f2512d) * 31) + this.f2516h;
    }

    public int m4275i() {
        return this.f2517i;
    }

    public int m4276j() {
        return this.f2518j;
    }

    public String toString() {
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[]{m4266b(this.f2512d), this.f2510b, Integer.valueOf(this.f2516h), Double.valueOf(this.f2513e), Double.valueOf(this.f2514f), Float.valueOf(this.f2515g), Integer.valueOf(this.f2517i / Constants.MILLIS_IN_A_SECOND), Integer.valueOf(this.f2518j), Long.valueOf(this.f2511c)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        an anVar = CREATOR;
        an.m4011a(this, parcel, i);
    }
}
