package com.newrelic.agent.android.tracing;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.activity.NamedActivity;
import com.newrelic.agent.android.harvest.ActivitySighting;
import com.newrelic.agent.android.harvest.ConnectInformation;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.tracing.Sample.SampleType;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import com.olacabs.customer.utils.Constants;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ActivityTrace extends HarvestableArray {
    private static final HashMap<String, String> ACTIVITY_HISTORY_TYPE;
    private static final HashMap<String, String> ENVIRONMENT_TYPE;
    public static final int MAX_TRACES = 2000;
    private static final String SIZE_NORMAL = "NORMAL";
    public static final String TRACE_VERSION = "1.0";
    private static final HashMap<String, String> VITALS_TYPE;
    private boolean complete;
    public long lastUpdatedAt;
    private final AgentLog log;
    private NamedActivity measuredActivity;
    private final Set<UUID> missingChildren;
    private final HashMap<String, String> params;
    public ActivitySighting previousActivity;
    private long reportAttemptCount;
    public Trace rootTrace;
    public long startedAt;
    private int traceCount;
    private final ConcurrentHashMap<UUID, Trace> traces;
    private Map<SampleType, Collection<Sample>> vitals;

    /* renamed from: com.newrelic.agent.android.tracing.ActivityTrace.1 */
    static class C07441 extends HashMap<String, String> {
        C07441() {
            put(Constants.BUNDLE_TYPE, "ENVIRONMENT");
        }
    }

    /* renamed from: com.newrelic.agent.android.tracing.ActivityTrace.2 */
    static class C07452 extends HashMap<String, String> {
        C07452() {
            put(Constants.BUNDLE_TYPE, "VITALS");
        }
    }

    /* renamed from: com.newrelic.agent.android.tracing.ActivityTrace.3 */
    static class C07463 extends HashMap<String, String> {
        C07463() {
            put(Constants.BUNDLE_TYPE, "ACTIVITY_HISTORY");
        }
    }

    static {
        ENVIRONMENT_TYPE = new C07441();
        VITALS_TYPE = new C07452();
        ACTIVITY_HISTORY_TYPE = new C07463();
    }

    public ActivityTrace() {
        this.traces = new ConcurrentHashMap();
        this.traceCount = 0;
        this.missingChildren = Collections.synchronizedSet(new HashSet());
        this.reportAttemptCount = 0;
        this.complete = false;
        this.params = new HashMap();
        this.log = AgentLogManager.getAgentLog();
    }

    public ActivityTrace(Trace trace) {
        this.traces = new ConcurrentHashMap();
        this.traceCount = 0;
        this.missingChildren = Collections.synchronizedSet(new HashSet());
        this.reportAttemptCount = 0;
        this.complete = false;
        this.params = new HashMap();
        this.log = AgentLogManager.getAgentLog();
        this.rootTrace = trace;
        this.lastUpdatedAt = trace.entryTimestamp;
        this.startedAt = this.lastUpdatedAt;
        this.params.put("traceVersion", TRACE_VERSION);
        this.params.put(Constants.BUNDLE_TYPE, "ACTIVITY");
        this.measuredActivity = (NamedActivity) Measurements.startActivity(trace.displayName);
        this.measuredActivity.setStartTime(trace.entryTimestamp);
    }

    public String getId() {
        if (this.rootTrace == null) {
            return null;
        }
        return this.rootTrace.myUUID.toString();
    }

    public void addTrace(Trace trace) {
        this.missingChildren.add(trace.myUUID);
        this.lastUpdatedAt = System.currentTimeMillis();
    }

    public void addCompletedTrace(Trace trace) {
        trace.traceMachine = null;
        this.missingChildren.remove(trace.myUUID);
        if (this.traceCount > MAX_TRACES) {
            this.log.debug("Maximum trace limit reached, discarding trace " + trace.myUUID);
            return;
        }
        this.traces.put(trace.myUUID, trace);
        this.traceCount++;
        if (trace.exitTimestamp > this.rootTrace.exitTimestamp) {
            this.rootTrace.exitTimestamp = trace.exitTimestamp;
        }
        if (this.log.getLevel() == 5) {
            this.log.debug("Added trace " + trace.myUUID.toString() + " missing children: " + this.missingChildren.size());
        }
        this.lastUpdatedAt = System.currentTimeMillis();
    }

    public boolean hasMissingChildren() {
        return !this.missingChildren.isEmpty();
    }

    public boolean isComplete() {
        return this.complete;
    }

    public void discard() {
        if (this.log.getLevel() == 5) {
            this.log.debug("Discarding trace of " + this.rootTrace.displayName + ":" + this.rootTrace.myUUID.toString() + "(" + this.traces.size() + " traces)");
        }
        this.rootTrace.traceMachine = null;
        this.complete = true;
        Measurements.endActivityWithoutMeasurement(this.measuredActivity);
    }

    public void complete() {
        if (this.log.getLevel() == 5) {
            this.log.debug("Completing trace of " + this.rootTrace.displayName + ":" + this.rootTrace.myUUID.toString() + "(" + this.traces.size() + " traces)");
        }
        if (this.rootTrace.exitTimestamp == 0) {
            this.rootTrace.exitTimestamp = System.currentTimeMillis();
        }
        if (this.traces.isEmpty()) {
            this.rootTrace.traceMachine = null;
            this.complete = true;
            Measurements.endActivityWithoutMeasurement(this.measuredActivity);
            return;
        }
        this.measuredActivity.setEndTime(this.rootTrace.exitTimestamp);
        Measurements.endActivity(this.measuredActivity);
        this.rootTrace.traceMachine = null;
        this.complete = true;
        TaskQueue.queue(this);
    }

    public Map<UUID, Trace> getTraces() {
        return this.traces;
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        if (this.complete) {
            jsonArray.add(new Gson().toJsonTree(this.params, GSON_STRING_MAP_TYPE));
            jsonArray.add(new JsonPrimitive(Long.valueOf(this.rootTrace.entryTimestamp)));
            jsonArray.add(new JsonPrimitive(Long.valueOf(this.rootTrace.exitTimestamp)));
            jsonArray.add(new JsonPrimitive(this.rootTrace.displayName));
            JsonElement jsonArray2 = new JsonArray();
            jsonArray2.add(getEnvironment());
            jsonArray2.add(traceToTree(this.rootTrace));
            jsonArray2.add(getVitalsAsJson());
            if (this.previousActivity != null) {
                jsonArray2.add(getPreviousActivityAsJson());
            }
            jsonArray.add(jsonArray2);
            return jsonArray;
        }
        this.log.debug("Attempted to serialize trace " + this.rootTrace.myUUID.toString() + " but it has yet to be finalized");
        return null;
    }

    private JsonArray traceToTree(Trace trace) {
        JsonArray jsonArray = new JsonArray();
        trace.prepareForSerialization();
        jsonArray.add(new Gson().toJsonTree(trace.getParams(), GSON_STRING_MAP_TYPE));
        jsonArray.add(new JsonPrimitive(Long.valueOf(trace.entryTimestamp)));
        jsonArray.add(new JsonPrimitive(Long.valueOf(trace.exitTimestamp)));
        jsonArray.add(new JsonPrimitive(trace.displayName));
        JsonElement jsonArray2 = new JsonArray();
        jsonArray2.add(new JsonPrimitive(Long.valueOf(trace.threadId)));
        jsonArray2.add(new JsonPrimitive(trace.threadName));
        jsonArray.add(jsonArray2);
        if (trace.getChildren().isEmpty()) {
            jsonArray.add(new JsonArray());
        } else {
            JsonElement jsonArray3 = new JsonArray();
            for (UUID uuid : trace.getChildren()) {
                Trace trace2 = (Trace) this.traces.get(uuid);
                if (trace2 != null) {
                    jsonArray3.add(traceToTree(trace2));
                }
            }
            jsonArray.add(jsonArray3);
        }
        return jsonArray;
    }

    private JsonArray getEnvironment() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new Gson().toJsonTree(ENVIRONMENT_TYPE, GSON_STRING_MAP_TYPE));
        jsonArray.addAll(new ConnectInformation(Agent.getApplicationInformation(), Agent.getDeviceInformation()).asJsonArray());
        HashMap hashMap = new HashMap();
        hashMap.put("size", SIZE_NORMAL);
        jsonArray.add(new Gson().toJsonTree(hashMap, GSON_STRING_MAP_TYPE));
        return jsonArray;
    }

    public void setVitals(Map<SampleType, Collection<Sample>> map) {
        this.vitals = map;
    }

    private JsonArray getVitalsAsJson() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new Gson().toJsonTree(VITALS_TYPE, GSON_STRING_MAP_TYPE));
        JsonElement jsonObject = new JsonObject();
        if (this.vitals != null) {
            for (Entry entry : this.vitals.entrySet()) {
                JsonElement jsonArray2 = new JsonArray();
                for (Sample sample : (Collection) entry.getValue()) {
                    if (sample.getTimestamp() <= this.lastUpdatedAt) {
                        jsonArray2.add(sample.asJsonArray());
                    }
                }
                jsonObject.add(((SampleType) entry.getKey()).toString(), jsonArray2);
            }
        }
        jsonArray.add(jsonObject);
        return jsonArray;
    }

    private JsonArray getPreviousActivityAsJson() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new Gson().toJsonTree(ACTIVITY_HISTORY_TYPE, GSON_STRING_MAP_TYPE));
        jsonArray.addAll(this.previousActivity.asJsonArray());
        return jsonArray;
    }

    public void setLastUpdatedAt(long j) {
        this.lastUpdatedAt = j;
    }

    public long getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public long getReportAttemptCount() {
        return this.reportAttemptCount;
    }

    public void incrementReportAttemptCount() {
        this.reportAttemptCount++;
    }
}
