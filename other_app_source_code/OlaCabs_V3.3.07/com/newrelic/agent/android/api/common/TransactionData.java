package com.newrelic.agent.android.api.common;

import java.util.ArrayList;
import java.util.List;

public class TransactionData {
    private final String appData;
    private final long bytesReceived;
    private final long bytesSent;
    private final String carrier;
    private int errorCode;
    private final Object errorCodeLock;
    private final String httpMethod;
    private final int statusCode;
    private final float time;
    private final long timestamp;
    private final String url;
    private final String wanType;

    public TransactionData(String str, String str2, String str3, float f, int i, int i2, long j, long j2, String str4, String str5) {
        this.errorCodeLock = new Object();
        int indexOf = str.indexOf(63);
        if (indexOf < 0) {
            indexOf = str.indexOf(59);
            if (indexOf < 0) {
                indexOf = str.length();
            }
        }
        this.url = str.substring(0, indexOf);
        this.httpMethod = str2;
        this.carrier = str3;
        this.time = f;
        this.statusCode = i;
        this.errorCode = i2;
        this.bytesSent = j;
        this.bytesReceived = j2;
        this.appData = str4;
        this.wanType = str5;
        this.timestamp = System.currentTimeMillis();
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

    public int getStatusCode() {
        return this.statusCode;
    }

    public int getErrorCode() {
        int i;
        synchronized (this.errorCodeLock) {
            i = this.errorCode;
        }
        return i;
    }

    public void setErrorCode(int i) {
        synchronized (this.errorCodeLock) {
            this.errorCode = i;
        }
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

    public List<Object> asList() {
        List arrayList = new ArrayList();
        arrayList.add(this.url);
        arrayList.add(this.carrier);
        arrayList.add(Float.valueOf(this.time));
        arrayList.add(Integer.valueOf(this.statusCode));
        arrayList.add(Integer.valueOf(this.errorCode));
        arrayList.add(Long.valueOf(this.bytesSent));
        arrayList.add(Long.valueOf(this.bytesReceived));
        arrayList.add(this.appData);
        return arrayList;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public float getTime() {
        return this.time;
    }

    public TransactionData clone() {
        return new TransactionData(this.url, this.httpMethod, this.carrier, this.time, this.statusCode, this.errorCode, this.bytesSent, this.bytesReceived, this.appData, this.wanType);
    }

    public String toString() {
        return "TransactionData{timestamp=" + this.timestamp + ", url='" + this.url + '\'' + ", httpMethod='" + this.httpMethod + '\'' + ", carrier='" + this.carrier + '\'' + ", time=" + this.time + ", statusCode=" + this.statusCode + ", errorCode=" + this.errorCode + ", errorCodeLock=" + this.errorCodeLock + ", bytesSent=" + this.bytesSent + ", bytesReceived=" + this.bytesReceived + ", appData='" + this.appData + '\'' + ", wanType='" + this.wanType + '\'' + '}';
    }
}
