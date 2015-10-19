package com.olacabs.p067a.p068a.p069a.p070a;

import android.content.Context;
import com.google.gson.p063a.SerializedName;

/* renamed from: com.olacabs.a.a.a.a.g */
public class EnvironmentData {
    @SerializedName(a = "appInfo")
    private AppInfo mAppInfo;
    @SerializedName(a = "deviceInfo")
    private DeviceInfo mDeviceInfo;
    @SerializedName(a = "pin")
    private PinInfo mPinInfo;
    @SerializedName(a = "timestamp")
    private long mTimestamp;
    @SerializedName(a = "eventType")
    private String sEventType;
    @SerializedName(a = "sessionId")
    private String sSessionId;

    public void setSessionId(String str) {
        this.sSessionId = str;
    }

    public EnvironmentData(Context context, String str) {
        this.sEventType = "environmentInfo";
        this.mDeviceInfo = new DeviceInfo(context);
        this.mAppInfo = new AppInfo(context);
        this.mPinInfo = new PinInfo(context);
        setSessionId(str);
        this.mTimestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }
}
