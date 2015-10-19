package com.newrelic.agent.android.analytics;

import java.util.Set;

public class InteractionEvent extends AnalyticsEvent {
    public InteractionEvent(String str) {
        super(str, AnalyticsEventCategory.Interaction);
    }

    public InteractionEvent(String str, Set<AnalyticAttribute> set) {
        super(str, AnalyticsEventCategory.Interaction, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE, set);
    }
}
