package com.newrelic.agent.android.analytics;

import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import com.olacabs.customer.utils.Constants;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AnalyticsEvent extends HarvestableObject {
    private Set<AnalyticAttribute> attributeSet;
    private AnalyticsEventCategory category;
    private String eventType;
    private final AgentLog log;
    private String name;
    private long timestamp;

    protected AnalyticsEvent(String str) {
        this(str, AnalyticsEventCategory.Custom, null, null);
    }

    protected AnalyticsEvent(String str, AnalyticsEventCategory analyticsEventCategory) {
        this(str, analyticsEventCategory, null, null);
    }

    protected AnalyticsEvent(String str, AnalyticsEventCategory analyticsEventCategory, String str2, Set<AnalyticAttribute> set) {
        this.log = AgentLogManager.getAgentLog();
        this.attributeSet = Collections.synchronizedSet(new HashSet());
        this.name = str;
        if (analyticsEventCategory == null) {
            this.category = AnalyticsEventCategory.Custom;
        } else {
            this.category = analyticsEventCategory;
        }
        this.timestamp = System.currentTimeMillis();
        if (set != null) {
            for (AnalyticAttribute analyticAttribute : set) {
                this.attributeSet.add(new AnalyticAttribute(analyticAttribute));
            }
        }
        if (str != null) {
            this.attributeSet.add(new AnalyticAttribute(Constants.BUNDLE_NAME, str));
        }
        this.attributeSet.add(new AnalyticAttribute(Constants.PUSH_ACK_TIME, String.valueOf(this.timestamp)));
        this.attributeSet.add(new AnalyticAttribute(AnalyticAttribute.EVENT_CATEGORY_ATTRIBUTE, analyticsEventCategory.name()));
        if (str2 != null) {
            this.attributeSet.add(new AnalyticAttribute(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE, str2));
        } else {
            this.attributeSet.add(new AnalyticAttribute(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE));
        }
    }

    public void addAttributes(Set<AnalyticAttribute> set) {
        if (set != null) {
            for (AnalyticAttribute analyticAttribute : set) {
                if (!this.attributeSet.add(analyticAttribute)) {
                    this.log.error("Failed to add attribute " + analyticAttribute.getName() + " to event " + getName() + ": the event already contains that attribute.");
                }
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public AnalyticsEventCategory getCategory() {
        return this.category;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getEventType() {
        return this.eventType;
    }

    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        synchronized (this) {
            for (AnalyticAttribute analyticAttribute : this.attributeSet) {
                jsonObject.add(analyticAttribute.getName(), analyticAttribute.isStringAttribute() ? new JsonPrimitive(analyticAttribute.getStringValue()) : new JsonPrimitive(Float.valueOf(analyticAttribute.getFloatValue())));
            }
        }
        return jsonObject;
    }

    public Collection<AnalyticAttribute> getAttributeSet() {
        return Collections.unmodifiableCollection(this.attributeSet);
    }
}
