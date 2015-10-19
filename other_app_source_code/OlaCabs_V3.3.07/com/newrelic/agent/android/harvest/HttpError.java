package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.FeatureFlag;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpErrorMeasurement;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Map;

public class HttpError extends HarvestableArray {
    private static final AgentLog log;
    private String appData;
    private long count;
    private String digest;
    private int httpStatusCode;
    private Map<String, String> params;
    private String responseBody;
    private String stackTrace;
    private Long timestamp;
    private String url;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public HttpError(String str, int i, String str2, String str3, Map<String, String> map) {
        this.url = str;
        this.httpStatusCode = i;
        this.responseBody = str2;
        this.stackTrace = str3;
        this.params = map;
        this.count = 1;
        this.digest = computeHash();
    }

    public HttpError(HttpErrorMeasurement httpErrorMeasurement) {
        this(httpErrorMeasurement.getUrl(), httpErrorMeasurement.getHttpStatusCode(), httpErrorMeasurement.getResponseBody(), httpErrorMeasurement.getStackTrace(), httpErrorMeasurement.getParams());
        setTimestamp(Long.valueOf(httpErrorMeasurement.getStartTime()));
    }

    public JsonArray asJsonArray() {
        int response_body_limit = Harvest.getHarvestConfiguration().getResponse_body_limit();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(this.url));
        jsonArray.add(new JsonPrimitive(Integer.valueOf(this.httpStatusCode)));
        jsonArray.add(new JsonPrimitive(Long.valueOf(this.count)));
        String str = Trace.NULL;
        if (FeatureFlag.featureEnabled(FeatureFlag.HttpResponseBodyCapture)) {
            str = optional(this.responseBody);
            if (str.length() > response_body_limit) {
                log.warning("HTTP Error response BODY is too large. Truncating to " + response_body_limit + " bytes.");
                str = str.substring(0, response_body_limit);
            }
        } else {
            log.warning("not enabled");
        }
        jsonArray.add(new JsonPrimitive(Agent.getEncoder().encode(str.getBytes())));
        jsonArray.add(new JsonPrimitive(optional(this.stackTrace)));
        JsonElement jsonObject = new JsonObject();
        if (this.params == null) {
            this.params = Collections.emptyMap();
        }
        jsonObject.add("custom_params", HarvestableObject.fromMap(this.params).asJson());
        jsonArray.add(jsonObject);
        jsonArray.add(new JsonPrimitive(optional(this.appData)));
        return jsonArray;
    }

    public void incrementCount() {
        this.count++;
    }

    public String getHash() {
        return this.digest;
    }

    public void digest() {
        this.digest = computeHash();
    }

    private String computeHash() {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(this.url.getBytes());
            instance.update(ByteBuffer.allocate(8).putInt(this.httpStatusCode).array());
            if (this.stackTrace != null && this.stackTrace.length() > 0) {
                instance.update(this.stackTrace.getBytes());
            }
            return new String(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            log.error("Unable to initialize SHA-1 hash algorithm");
            return null;
        }
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setHttpStatusCode(int i) {
        this.httpStatusCode = i;
    }

    public void setCount(long j) {
        this.count = j;
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

    public void setAppData(String str) {
        this.appData = str;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }
}
