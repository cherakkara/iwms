package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AgentHealthExceptions extends HarvestableObject {
    private static final JsonArray keyArray;
    private final Map<String, AgentHealthException> agentHealthExceptions;

    static {
        keyArray = new JsonArray();
    }

    public AgentHealthExceptions() {
        this.agentHealthExceptions = new ConcurrentHashMap();
        keyArray.add(new JsonPrimitive("ExceptionClass"));
        keyArray.add(new JsonPrimitive("Message"));
        keyArray.add(new JsonPrimitive("ThreadName"));
        keyArray.add(new JsonPrimitive("CallStack"));
        keyArray.add(new JsonPrimitive("Count"));
        keyArray.add(new JsonPrimitive("Extras"));
    }

    public void add(AgentHealthException agentHealthException) {
        String key = getKey(agentHealthException);
        synchronized (this.agentHealthExceptions) {
            AgentHealthException agentHealthException2 = (AgentHealthException) this.agentHealthExceptions.get(key);
            if (agentHealthException2 == null) {
                this.agentHealthExceptions.put(key, agentHealthException);
            } else {
                agentHealthException2.increment();
            }
        }
    }

    public void clear() {
        synchronized (this.agentHealthExceptions) {
            this.agentHealthExceptions.clear();
        }
    }

    public boolean isEmpty() {
        return this.agentHealthExceptions.isEmpty();
    }

    public Map<String, AgentHealthException> getAgentHealthExceptions() {
        return this.agentHealthExceptions;
    }

    public final String getKey(AgentHealthException agentHealthException) {
        String name = getClass().getName();
        if (agentHealthException != null) {
            return agentHealthException.getExceptionClass() + agentHealthException.getStackTrace()[0].toString();
        }
        return name;
    }

    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        JsonElement jsonArray = new JsonArray();
        for (AgentHealthException asJsonArray : this.agentHealthExceptions.values()) {
            jsonArray.add(asJsonArray.asJsonArray());
        }
        jsonObject.add("Type", new JsonPrimitive("AgentErrors"));
        jsonObject.add("Keys", keyArray);
        jsonObject.add("Data", jsonArray);
        return jsonObject;
    }
}
