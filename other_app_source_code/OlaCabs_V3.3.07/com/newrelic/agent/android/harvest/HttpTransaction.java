package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

public class HttpTransaction extends HarvestableArray {
    private String appData;
    private long bytesReceived;
    private long bytesSent;
    private String carrier;
    private int errorCode;
    private String httpMethod;
    private int statusCode;
    private Long timestamp;
    private double totalTime;
    private String url;
    private String wanType;

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(this.url));
        jsonArray.add(new JsonPrimitive(this.carrier));
        jsonArray.add(new JsonPrimitive(Double.valueOf(this.totalTime)));
        jsonArray.add(new JsonPrimitive(Integer.valueOf(this.statusCode)));
        jsonArray.add(new JsonPrimitive(Integer.valueOf(this.errorCode)));
        jsonArray.add(new JsonPrimitive(Long.valueOf(this.bytesSent)));
        jsonArray.add(new JsonPrimitive(Long.valueOf(this.bytesReceived)));
        jsonArray.add(this.appData == null ? null : new JsonPrimitive(this.appData));
        jsonArray.add(new JsonPrimitive(this.wanType));
        jsonArray.add(new JsonPrimitive(this.httpMethod));
        return jsonArray;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setHttpMethod(String str) {
        this.httpMethod = str;
    }

    public void setCarrier(String str) {
        this.carrier = str;
    }

    public void setWanType(String str) {
        this.wanType = str;
    }

    public void setTotalTime(double d) {
        this.totalTime = d;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public void setBytesSent(long j) {
        this.bytesSent = j;
    }

    public void setBytesReceived(long j) {
        this.bytesReceived = j;
    }

    public void setAppData(String str) {
        this.appData = str;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public String getUrl() {
        return this.url;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public String getCarrier() {
        return this.carrier;
    }

    public double getTotalTime() {
        return this.totalTime;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public long getBytesSent() {
        return this.bytesSent;
    }

    public long getBytesReceived() {
        return this.bytesReceived;
    }

    public String getAppData() {
        return this.appData;
    }

    public String getWanType() {
        return this.wanType;
    }

    public String toString() {
        return "HttpTransaction{url='" + this.url + '\'' + ", carrier='" + this.carrier + '\'' + ", wanType='" + this.wanType + '\'' + ", httpMethod='" + this.httpMethod + '\'' + ", totalTime=" + this.totalTime + ", statusCode=" + this.statusCode + ", errorCode=" + this.errorCode + ", bytesSent=" + this.bytesSent + ", bytesReceived=" + this.bytesReceived + ", appData='" + this.appData + '\'' + ", timestamp=" + this.timestamp + '}';
    }
}
