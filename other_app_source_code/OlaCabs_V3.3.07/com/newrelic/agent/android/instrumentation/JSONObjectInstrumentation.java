package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectInstrumentation {
    private static final ArrayList<String> categoryParams;

    static {
        categoryParams = new ArrayList(Arrays.asList(new String[]{AnalyticAttribute.EVENT_CATEGORY_ATTRIBUTE, MetricCategory.class.getName(), "JSON"}));
    }

    JSONObjectInstrumentation() {
    }

    @TraceConstructor
    public static JSONObject init(String str) throws JSONException {
        try {
            TraceMachine.enterMethod(null, "JSONObject#<init>", categoryParams);
            JSONObject jSONObject = new JSONObject(str);
            TraceMachine.exitMethod();
            return jSONObject;
        } catch (JSONException e) {
            TraceMachine.exitMethod();
            throw e;
        }
    }

    @ReplaceCallSite(scope = "org.json.JSONObject")
    public static String toString(JSONObject jSONObject) {
        TraceMachine.enterMethod(null, "JSONObject#toString", categoryParams);
        String jSONObject2 = jSONObject.toString();
        TraceMachine.exitMethod();
        return jSONObject2;
    }

    @ReplaceCallSite(scope = "org.json.JSONObject")
    public static String toString(JSONObject jSONObject, int i) throws JSONException {
        TraceMachine.enterMethod(null, "JSONObject#toString", categoryParams);
        try {
            String jSONObject2 = jSONObject.toString(i);
            TraceMachine.exitMethod();
            return jSONObject2;
        } catch (JSONException e) {
            TraceMachine.exitMethod();
            throw e;
        }
    }
}
