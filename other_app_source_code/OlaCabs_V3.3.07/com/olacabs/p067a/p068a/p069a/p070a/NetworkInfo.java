package com.olacabs.p067a.p068a.p069a.p070a;

import com.google.gson.p063a.SerializedName;

/* renamed from: com.olacabs.a.a.a.a.l */
public class NetworkInfo {
    @SerializedName(a = "carrierName")
    private String mCarrierName;
    @SerializedName(a = "elapsedTime")
    private long mElapsedTime;
    @SerializedName(a = "eventType")
    private String mEventType;
    @SerializedName(a = "networkType")
    private String mNetworkType;
    @SerializedName(a = "requestInfo")
    private RequestInfo mRequestInfo;
    @SerializedName(a = "timestamp")
    private long mRequestStartTime;
    private transient long mResponseEndTime;
    @SerializedName(a = "responseInfo")
    private ResponseInfo mResponseInfo;
    @SerializedName(a = "sessionId")
    private String mSessionId;

    public NetworkInfo() {
        this.mEventType = "networkInfo";
    }

    public void setElapsedTime() {
        this.mElapsedTime = this.mResponseEndTime - this.mRequestStartTime;
    }

    public void setSessionId(String str) {
        this.mSessionId = str;
    }

    public void setRequestStartTime(long j) {
        this.mRequestStartTime = j;
    }

    public void setResponseEndTime(long j) {
        this.mResponseEndTime = j;
    }

    public void setNetworkType(String str) {
        this.mNetworkType = str;
    }

    public long getRequestStartTime() {
        return this.mRequestStartTime;
    }

    public long getResponseEndTime() {
        return this.mResponseEndTime;
    }

    public String getNetworkType() {
        return this.mNetworkType;
    }

    public String getCarrierName() {
        return this.mCarrierName;
    }

    public void setCarrierName(String str) {
        this.mCarrierName = str;
    }

    public RequestInfo getRequestInfo() {
        return this.mRequestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.mRequestInfo = requestInfo;
    }

    public ResponseInfo getResponseInfo() {
        return this.mResponseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.mResponseInfo = responseInfo;
    }
}
