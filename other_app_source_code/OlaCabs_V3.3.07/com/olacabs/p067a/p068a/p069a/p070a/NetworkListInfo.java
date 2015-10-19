package com.olacabs.p067a.p068a.p069a.p070a;

import com.google.gson.p063a.SerializedName;
import java.util.UUID;

/* renamed from: com.olacabs.a.a.a.a.m */
public class NetworkListInfo {
    @SerializedName(a = "id")
    private UUID id;
    @SerializedName(a = "data")
    private NetworkInfo networkInfo;
    @SerializedName(a = "timestamp")
    private long timestamp;

    public NetworkInfo getNetworkInfo() {
        return this.networkInfo;
    }

    public void setNetworkInfo(NetworkInfo networkInfo) {
        this.networkInfo = networkInfo;
    }

    public NetworkListInfo(NetworkInfo networkInfo) {
        this.id = UUID.randomUUID();
        setNetworkInfo(networkInfo);
        this.timestamp = networkInfo.getRequestStartTime();
    }
}
