package com.newrelic.agent.android.measurement.http;

import com.newrelic.agent.android.measurement.BaseMeasurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.Util;

public class HttpTransactionMeasurement extends BaseMeasurement {
    private String appData;
    private long bytesReceived;
    private long bytesSent;
    private int errorCode;
    private String httpMethod;
    private int statusCode;
    private double totalTime;
    private String url;

    public HttpTransactionMeasurement(String str, String str2, int i, long j, double d, long j2, long j3, String str3) {
        super(MeasurementType.Network);
        String sanitizeUrl = Util.sanitizeUrl(str);
        setName(sanitizeUrl);
        setScope(TraceMachine.getCurrentScope());
        setStartTime(j);
        setEndTime(((long) ((int) d)) + j);
        setExclusiveTime((long) ((int) (1000.0d * d)));
        this.url = sanitizeUrl;
        this.httpMethod = str2;
        this.statusCode = i;
        this.bytesSent = j2;
        this.bytesReceived = j3;
        this.totalTime = d;
        this.appData = str3;
    }

    public HttpTransactionMeasurement(String str, String str2, int i, int i2, long j, double d, long j2, long j3, String str3) {
        this(str, str2, i, j, d, j2, j3, str3);
        this.errorCode = i2;
    }

    public double asDouble() {
        return this.totalTime;
    }

    public String getUrl() {
        return this.url;
    }

    public String getHttpMethod() {
        return this.httpMethod;
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

    public void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        return "HttpTransactionMeasurement{url='" + this.url + '\'' + ", httpMethod='" + this.httpMethod + '\'' + ", totalTime=" + this.totalTime + ", statusCode=" + this.statusCode + ", errorCode=" + this.errorCode + ", bytesSent=" + this.bytesSent + ", bytesReceived=" + this.bytesReceived + ", appData='" + this.appData + '\'' + '}';
    }
}
