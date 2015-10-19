package com.olacabs.p067a.p068a.p069a.p070a;

import android.content.Context;
import com.google.gson.p063a.SerializedName;
import java.util.UUID;

/* renamed from: com.olacabs.a.a.a.a.h */
public class EnvironmentInfo {
    @SerializedName(a = "data")
    private EnvironmentData mEnvironmentData;
    @SerializedName(a = "id")
    private UUID mId;
    @SerializedName(a = "timestamp")
    private long mTimeStamp;

    public EnvironmentInfo(Context context, String str) {
        this.mId = UUID.randomUUID();
        this.mEnvironmentData = new EnvironmentData(context, str);
        this.mTimeStamp = this.mEnvironmentData.getTimestamp();
    }
}
