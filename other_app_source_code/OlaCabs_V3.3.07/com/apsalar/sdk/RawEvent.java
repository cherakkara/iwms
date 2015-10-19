package com.apsalar.sdk;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ApEvent */
class RawEvent {
    String eventData;
    String eventName;
    long eventTime;
    int eventType;
    String sessionJson;
    JSONObject sessionJsonObj;

    RawEvent(int i, String str, String str2) {
        this.eventType = 0;
        this.eventName = Trace.NULL;
        this.eventData = Trace.NULL;
        this.eventTime = 0;
        this.sessionJson = Trace.NULL;
        this.sessionJsonObj = null;
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        if (str == null) {
            str = Trace.NULL;
        }
        if (str2 == null) {
            str2 = Trace.NULL;
        }
        this.eventType = i;
        this.eventName = str.replace("\\n", Trace.NULL);
        this.eventData = str2.replace("\\n", Trace.NULL);
        this.eventTime = System.currentTimeMillis();
        JSONObject toJSON = instance.info.toJSON();
        this.sessionJson = !(toJSON instanceof JSONObject) ? toJSON.toString() : JSONObjectInstrumentation.toString(toJSON);
        this.sessionJsonObj = instance.info.toJSON();
    }

    RawEvent(int i) {
        this.eventType = 0;
        this.eventName = Trace.NULL;
        this.eventData = Trace.NULL;
        this.eventTime = 0;
        this.sessionJson = Trace.NULL;
        this.sessionJsonObj = null;
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        this.eventType = i;
        this.eventName = null;
        if (i == 2) {
            this.eventName = "heartbeat";
        }
        this.eventData = null;
        this.eventTime = System.currentTimeMillis();
        JSONObject toJSON = instance.info.toJSON();
        this.sessionJson = !(toJSON instanceof JSONObject) ? toJSON.toString() : JSONObjectInstrumentation.toString(toJSON);
        this.sessionJsonObj = instance.info.toJSON();
    }

    public String toString() {
        String str = Trace.NULL;
        try {
            str = this.sessionJsonObj.getString(AnalyticAttribute.SESSION_ID_ATTRIBUTE);
        } catch (JSONException e) {
            str = e.toString();
        }
        return "eventType=" + String.valueOf(this.eventType) + ", eventName=" + this.eventName + ", eventTime=" + this.eventTime + ", eventData=" + this.eventData + ", sessionId=" + str;
    }
}
