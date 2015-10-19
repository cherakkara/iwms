package com.olacabs.p067a.p068a.p069a.p070a;

import com.google.gson.p063a.SerializedName;
import java.util.UUID;

/* renamed from: com.olacabs.a.a.a.a.b */
public class ActivityListInfo {
    @SerializedName(a = "data")
    private ActivityInfo activityInfo;
    @SerializedName(a = "id")
    private UUID id;
    @SerializedName(a = "timestamp")
    private long timestamp;

    public ActivityInfo getActivityInfo() {
        return this.activityInfo;
    }

    public ActivityListInfo(ActivityInfo activityInfo) {
        this.id = UUID.randomUUID();
        setActivityInfo(activityInfo);
    }

    public void setActivityInfo(ActivityInfo activityInfo) {
        this.activityInfo = activityInfo;
        this.timestamp = activityInfo.getTimeStamp();
    }
}
