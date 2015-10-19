package com.newrelic.agent.android.activity.config;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonDeserializationContext;
import com.newrelic.com.google.gson.JsonDeserializer;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonParseException;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;

public class ActivityTraceConfigurationDeserializer implements JsonDeserializer<ActivityTraceConfiguration> {
    private final AgentLog log;

    public ActivityTraceConfigurationDeserializer() {
        this.log = AgentLogManager.getAgentLog();
    }

    public ActivityTraceConfiguration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ActivityTraceConfiguration activityTraceConfiguration = new ActivityTraceConfiguration();
        if (jsonElement.isJsonArray()) {
            JsonArray asJsonArray = jsonElement.getAsJsonArray();
            if (asJsonArray.size() != 2) {
                error("Root array must contain 2 elements.");
                return null;
            }
            Integer integer = getInteger(asJsonArray.get(0));
            if (integer == null) {
                return null;
            }
            if (integer.intValue() < 0) {
                error("The first element of the root array must not be negative.");
                return null;
            }
            activityTraceConfiguration.setMaxTotalTraceCount(integer.intValue());
            return activityTraceConfiguration;
        }
        error("Expected root element to be an array.");
        return null;
    }

    private Integer getInteger(JsonElement jsonElement) {
        if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (asJsonPrimitive.isNumber()) {
                int asInt = asJsonPrimitive.getAsInt();
                if (asInt >= 0) {
                    return Integer.valueOf(asInt);
                }
                error("Integer value must not be negative");
                return null;
            }
            error("Expected an integer.");
            return null;
        }
        error("Expected an integer.");
        return null;
    }

    private void error(String str) {
        this.log.error("ActivityTraceConfigurationDeserializer: " + str);
    }
}
