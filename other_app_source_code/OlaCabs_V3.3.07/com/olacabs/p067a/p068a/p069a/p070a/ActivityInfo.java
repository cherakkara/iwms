package com.olacabs.p067a.p068a.p069a.p070a;

import com.google.gson.p063a.SerializedName;
import com.olacabs.p067a.p068a.p069a.p071b.GenericUtilities;

/* renamed from: com.olacabs.a.a.a.a.a */
public class ActivityInfo {
    @SerializedName(a = "viewName")
    private String mClassName;
    @SerializedName(a = "eventType")
    private String mEventType;
    @SerializedName(a = "elapsedTime")
    private long mLoadTime;
    @SerializedName(a = "loadType")
    private String mLoadType;
    private transient long mObjectID;
    @SerializedName(a = "currentMemoryUsage")
    private double mRAMUsage;
    @SerializedName(a = "sessionId")
    private String mSessionId;
    private transient long mStartTime;
    @SerializedName(a = "timestamp")
    private long mTimeStamp;

    public ActivityInfo() {
        this.mEventType = "viewInfo";
    }

    public void setSessionId(String str) {
        this.mSessionId = str;
    }

    public void setRAMUsage() {
        this.mRAMUsage = GenericUtilities.m12820a();
    }

    public long getObjectID() {
        return this.mObjectID;
    }

    public void setObjectID(long j) {
        this.mObjectID = j;
    }

    public void setClassName(String str) {
        this.mClassName = str;
    }

    public void setLoadTime(long j) {
        this.mLoadTime = j - this.mStartTime;
        this.mTimeStamp = this.mStartTime;
    }

    public long getLoadTime() {
        return this.mLoadTime;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public String getClassName() {
        return this.mClassName;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public void setLoadType(String str) {
        this.mLoadType = str;
    }
}
