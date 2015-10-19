package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;

public class JSONArrayInstrumentation {
    private static final ArrayList<String> categoryParams;

    static {
        categoryParams = new ArrayList(Arrays.asList(new String[]{AnalyticAttribute.EVENT_CATEGORY_ATTRIBUTE, MetricCategory.class.getName(), "JSON"}));
    }

    JSONArrayInstrumentation() {
    }

    @TraceConstructor
    public static JSONArray init(String str) throws JSONException {
        try {
            TraceMachine.enterMethod("JSONArray#<init>", categoryParams);
            JSONArray jSONArray = new JSONArray(str);
            TraceMachine.exitMethod();
            return jSONArray;
        } catch (JSONException e) {
            TraceMachine.exitMethod();
            throw e;
        }
    }

    @ReplaceCallSite(scope = "org.json.JSONArray")
    public static String toString(JSONArray jSONArray) {
        TraceMachine.enterMethod("JSONArray#toString", categoryParams);
        String jSONArray2 = jSONArray.toString();
        TraceMachine.exitMethod();
        return jSONArray2;
    }

    @ReplaceCallSite(scope = "org.json.JSONArray")
    public static String toString(JSONArray jSONArray, int i) throws JSONException {
        try {
            TraceMachine.enterMethod("JSONArray#toString", categoryParams);
            String jSONArray2 = jSONArray.toString(i);
            TraceMachine.exitMethod();
            return jSONArray2;
        } catch (JSONException e) {
            TraceMachine.exitMethod();
            throw e;
        }
    }
}
