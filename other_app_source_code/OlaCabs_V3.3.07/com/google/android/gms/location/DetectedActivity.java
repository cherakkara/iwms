package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Comparator;

public class DetectedActivity implements SafeParcelable {
    public static final C0528b CREATOR;
    public static final Comparator<DetectedActivity> f2608a;
    int f2609b;
    int f2610c;
    private final int f2611d;

    /* renamed from: com.google.android.gms.location.DetectedActivity.1 */
    static class C05241 implements Comparator<DetectedActivity> {
        C05241() {
        }

        public int m4310a(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
            int compareTo = Integer.valueOf(detectedActivity2.m4313b()).compareTo(Integer.valueOf(detectedActivity.m4313b()));
            return compareTo == 0 ? Integer.valueOf(detectedActivity.m4312a()).compareTo(Integer.valueOf(detectedActivity2.m4312a())) : compareTo;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m4310a((DetectedActivity) obj, (DetectedActivity) obj2);
        }
    }

    static {
        f2608a = new C05241();
        CREATOR = new C0528b();
    }

    public DetectedActivity(int i, int i2, int i3) {
        this.f2611d = i;
        this.f2609b = i2;
        this.f2610c = i3;
    }

    private int m4311a(int i) {
        return i > 9 ? 4 : i;
    }

    public int m4312a() {
        return m4311a(this.f2609b);
    }

    public int m4313b() {
        return this.f2610c;
    }

    public int m4314c() {
        return this.f2611d;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "DetectedActivity [type=" + m4312a() + ", confidence=" + this.f2610c + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0528b.m4351a(this, parcel, i);
    }
}
