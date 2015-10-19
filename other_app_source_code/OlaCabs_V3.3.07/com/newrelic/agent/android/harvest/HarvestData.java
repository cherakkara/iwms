package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticsEvent;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class HarvestData extends HarvestableArray {
    private static final AgentLog log;
    private ActivityTraces activityTraces;
    private AgentHealth agentHealth;
    private boolean analyticsEnabled;
    private Collection<AnalyticsEvent> analyticsEvents;
    private DataToken dataToken;
    private DeviceInformation deviceInformation;
    private double harvestTimeDelta;
    private HttpErrors httpErrors;
    private HttpTransactions httpTransactions;
    private MachineMeasurements machineMeasurements;
    private Set<AnalyticAttribute> sessionAttributes;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public HarvestData() {
        this.dataToken = new DataToken();
        this.httpTransactions = new HttpTransactions();
        this.httpErrors = new HttpErrors();
        this.activityTraces = new ActivityTraces();
        this.machineMeasurements = new MachineMeasurements();
        this.deviceInformation = Agent.getDeviceInformation();
        this.agentHealth = new AgentHealth();
        this.sessionAttributes = new HashSet();
        this.analyticsEvents = new HashSet();
        this.analyticsEnabled = false;
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(this.dataToken.asJson());
        jsonArray.add(this.deviceInformation.asJson());
        jsonArray.add(new JsonPrimitive(Double.valueOf(this.harvestTimeDelta)));
        jsonArray.add(this.httpTransactions.asJson());
        jsonArray.add(this.machineMeasurements.asJson());
        jsonArray.add(this.httpErrors.asJson());
        JsonElement asJson = this.activityTraces.asJson();
        String jsonElement = asJson.toString();
        if (jsonElement.length() < Harvest.getHarvestConfiguration().getActivity_trace_max_size()) {
            jsonArray.add(asJson);
        } else {
            StatsEngine.get().sample("Supportability/AgentHealth/BigActivityTracesDropped", (float) jsonElement.length());
        }
        jsonArray.add(this.agentHealth.asJson());
        if (this.analyticsEnabled) {
            JsonElement jsonObject = new JsonObject();
            for (AnalyticAttribute analyticAttribute : this.sessionAttributes) {
                if (analyticAttribute.isStringAttribute()) {
                    jsonObject.addProperty(analyticAttribute.getName(), analyticAttribute.getStringValue());
                } else {
                    jsonObject.addProperty(analyticAttribute.getName(), Float.valueOf(analyticAttribute.getFloatValue()));
                }
            }
            jsonArray.add(jsonObject);
            jsonObject = new JsonArray();
            for (AnalyticsEvent asJsonObject : this.analyticsEvents) {
                jsonObject.add(asJsonObject.asJsonObject());
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public boolean isValid() {
        return this.dataToken.isValid();
    }

    public void reset() {
        this.httpErrors.clear();
        this.httpTransactions.clear();
        this.activityTraces.clear();
        this.machineMeasurements.clear();
        this.agentHealth.clear();
        this.sessionAttributes.clear();
        this.analyticsEvents.clear();
    }

    public void setDataToken(DataToken dataToken) {
        if (dataToken != null) {
            this.dataToken = dataToken;
        }
    }

    public void setDeviceInformation(DeviceInformation deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    public void setHarvestTimeDelta(double d) {
        this.harvestTimeDelta = d;
    }

    public void setHttpTransactions(HttpTransactions httpTransactions) {
        this.httpTransactions = httpTransactions;
    }

    public void setMachineMeasurements(MachineMeasurements machineMeasurements) {
        this.machineMeasurements = machineMeasurements;
    }

    public void setActivityTraces(ActivityTraces activityTraces) {
        this.activityTraces = activityTraces;
    }

    public void setHttpErrors(HttpErrors httpErrors) {
        this.httpErrors = httpErrors;
    }

    public Set<AnalyticAttribute> getSessionAttributes() {
        return this.sessionAttributes;
    }

    public void setSessionAttributes(Set<AnalyticAttribute> set) {
        log.debug("HarvestData.setSessionAttributes invoked with attribute set " + set);
        this.sessionAttributes = new HashSet(set);
    }

    public Collection<AnalyticsEvent> getAnalyticsEvents() {
        return this.analyticsEvents;
    }

    public void setAnalyticsEvents(Collection<AnalyticsEvent> collection) {
        this.analyticsEvents = new HashSet(collection);
    }

    public DeviceInformation getDeviceInformation() {
        return this.deviceInformation;
    }

    public HttpErrors getHttpErrors() {
        return this.httpErrors;
    }

    public HttpTransactions getHttpTransactions() {
        return this.httpTransactions;
    }

    public MachineMeasurements getMetrics() {
        return this.machineMeasurements;
    }

    public ActivityTraces getActivityTraces() {
        return this.activityTraces;
    }

    public AgentHealth getAgentHealth() {
        return this.agentHealth;
    }

    public DataToken getDataToken() {
        return this.dataToken;
    }

    public boolean isAnalyticsEnabled() {
        return this.analyticsEnabled;
    }

    public void setAnalyticsEnabled(boolean z) {
        this.analyticsEnabled = z;
    }

    public String toString() {
        return "HarvestData{\n\tdataToken=" + this.dataToken + ", \n\tdeviceInformation=" + this.deviceInformation + ", \n\tharvestTimeDelta=" + this.harvestTimeDelta + ", \n\thttpTransactions=" + this.httpTransactions + ", \n\tmachineMeasurements=" + this.machineMeasurements + ", \n\thttpErrors=" + this.httpErrors + ", \n\tactivityTraces=" + this.activityTraces + ", \n\tsessionAttributes=" + this.sessionAttributes + ", \n\tanalyticAttributes=" + this.analyticsEvents + '}';
    }
}
