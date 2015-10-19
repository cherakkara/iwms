package com.olacabs.p067a.p068a.p069a.p070a;

import com.google.gson.p063a.SerializedName;

/* renamed from: com.olacabs.a.a.a.a.d */
public class BatteryInfo {
    @SerializedName(a = "eventType")
    private String mEventType;
    @SerializedName(a = "level")
    private double mPercentage;
    @SerializedName(a = "sessionId")
    private String mSessionId;
    @SerializedName(a = "timestamp")
    private long mTimestamp;

    public BatteryInfo() {
        this.mEventType = "batteryInfo";
    }

    public double getPercentage() {
        return this.mPercentage;
    }

    public void setSessionId(String str) {
        this.mSessionId = str;
    }

    public void setPercentage(double d) {
        this.mPercentage = d;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }
}
