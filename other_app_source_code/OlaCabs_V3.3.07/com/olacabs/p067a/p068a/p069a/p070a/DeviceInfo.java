package com.olacabs.p067a.p068a.p069a.p070a;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Base64;
import com.google.gson.p063a.SerializedName;
import com.newrelic.agent.android.api.common.WanType;
import java.util.UUID;

/* renamed from: com.olacabs.a.a.a.a.f */
public class DeviceInfo {
    private transient Context mContext;
    @SerializedName(a = "osVersion")
    private String sAndroidVersion;
    @SerializedName(a = "deviceId")
    private String sDeviceIdentifier;
    @SerializedName(a = "installId")
    private UUID sInstallID;
    @SerializedName(a = "bundleId")
    private String sPackageName;
    @SerializedName(a = "model")
    private String sPhoneModel;

    public DeviceInfo(Context context) {
        this.sPhoneModel = Build.MODEL;
        this.sInstallID = UUID.randomUUID();
        this.mContext = context;
        init();
    }

    public void init() {
        this.sDeviceIdentifier = computeDeviceIdentifier();
        this.sAndroidVersion = VERSION.RELEASE;
        this.sPhoneModel = Build.MODEL;
        this.sPackageName = this.mContext.getPackageName();
    }

    private String computeDeviceIdentifier() {
        String fullDeviceId = getFullDeviceId(getInternalDeviceId());
        try {
            fullDeviceId = Base64.encodeToString(fullDeviceId.getBytes(), 9).toUpperCase();
        } catch (Exception e) {
        }
        return fullDeviceId;
    }

    private String getInternalDeviceId() {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.serialno"});
        } catch (Exception e) {
            return null;
        }
    }

    private String getDeviceIdFromWifiManager() {
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService(WanType.WIFI);
        if (wifiManager != null) {
            return wifiManager.getConnectionInfo().getMacAddress();
        }
        return null;
    }

    private String getFullDeviceId(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str != null) {
            stringBuilder.append(str);
            stringBuilder.append("$$");
        }
        String deviceIdFromWifiManager = getDeviceIdFromWifiManager();
        if (deviceIdFromWifiManager != null) {
            stringBuilder.append(deviceIdFromWifiManager);
        }
        return stringBuilder.toString();
    }
}
