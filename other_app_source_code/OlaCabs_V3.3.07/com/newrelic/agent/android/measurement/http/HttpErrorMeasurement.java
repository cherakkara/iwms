package com.newrelic.agent.android.measurement.http;

import com.newrelic.agent.android.measurement.BaseMeasurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import java.util.Map;

public class HttpErrorMeasurement extends BaseMeasurement {
    private int httpStatusCode;
    private Map<String, String> params;
    private String responseBody;
    private String stackTrace;
    private String url;

    public HttpErrorMeasurement(String str, int i) {
        super(MeasurementType.HttpError);
        setUrl(str);
        setName(str);
        setHttpStatusCode(i);
        setStartTime(System.currentTimeMillis());
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setHttpStatusCode(int i) {
        this.httpStatusCode = i;
    }

    public void setResponseBody(String str) {
        this.responseBody = str;
    }

    public void setStackTrace(String str) {
        this.stackTrace = str;
    }

    public void setParams(Map<String, String> map) {
        this.params = map;
    }

    public String getUrl() {
        return this.url;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public String getStackTrace() {
        return this.stackTrace;
    }

    public Map<String, String> getParams() {
        return this.params;
    }
}
