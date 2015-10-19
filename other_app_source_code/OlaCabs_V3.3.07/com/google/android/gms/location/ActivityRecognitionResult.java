package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class ActivityRecognitionResult implements SafeParcelable {
    public static final C0527a CREATOR;
    List<DetectedActivity> f2604a;
    long f2605b;
    long f2606c;
    private final int f2607d;

    static {
        CREATOR = new C0527a();
    }

    public ActivityRecognitionResult(int i, List<DetectedActivity> list, long j, long j2) {
        this.f2607d = 1;
        this.f2604a = list;
        this.f2605b = j;
        this.f2606c = j2;
    }

    public int m4309a() {
        return this.f2607d;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "ActivityRecognitionResult [probableActivities=" + this.f2604a + ", timeMillis=" + this.f2605b + ", elapsedRealtimeMillis=" + this.f2606c + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0527a.m4348a(this, parcel, i);
    }
}
