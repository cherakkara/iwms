package com.newrelic.agent.android.harvest.crash;

import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import com.olacabs.customer.utils.Constants;

public class ExceptionInfo extends HarvestableObject {
    private String className;
    private String message;

    public ExceptionInfo(Throwable th) {
        this.className = th.getClass().getName();
        if (th.getMessage() != null) {
            this.message = th.getMessage();
        } else {
            this.message = Trace.NULL;
        }
    }

    public String getClassName() {
        return this.className;
    }

    public String getMessage() {
        return this.message;
    }

    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(Constants.BUNDLE_NAME, new JsonPrimitive(this.className != null ? this.className : Trace.NULL));
        jsonObject.add("cause", new JsonPrimitive(this.message != null ? this.message : Trace.NULL));
        return jsonObject;
    }

    public static ExceptionInfo newFromJson(JsonObject jsonObject) {
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.className = jsonObject.has(Constants.BUNDLE_NAME) ? jsonObject.get(Constants.BUNDLE_NAME).getAsString() : Trace.NULL;
        exceptionInfo.message = jsonObject.has("cause") ? jsonObject.get("cause").getAsString() : Trace.NULL;
        return exceptionInfo;
    }
}
