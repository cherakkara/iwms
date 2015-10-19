package com.olacabs.p067a.p068a.p069a.p070a;

import com.google.gson.p063a.SerializedName;
import java.util.UUID;

/* renamed from: com.olacabs.a.a.a.a.e */
public class BatteryListInfo {
    @SerializedName(a = "data")
    private BatteryInfo batteryInfo;
    @SerializedName(a = "id")
    private UUID id;
    @SerializedName(a = "timestamp")
    private long timestamp;

    public BatteryListInfo(BatteryInfo batteryInfo) {
        this.id = UUID.randomUUID();
        this.timestamp = System.currentTimeMillis();
        setBatteryInfo(batteryInfo);
    }

    public BatteryInfo getBatteryInfo() {
        return this.batteryInfo;
    }

    public void setBatteryInfo(BatteryInfo batteryInfo) {
        this.batteryInfo = batteryInfo;
        this.timestamp = batteryInfo.getTimestamp();
    }
}
