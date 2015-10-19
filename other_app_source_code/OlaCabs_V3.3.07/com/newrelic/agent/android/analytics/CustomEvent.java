package com.newrelic.agent.android.analytics;

import java.util.Set;

public class CustomEvent extends AnalyticsEvent {
    public CustomEvent(String str) {
        super(str, AnalyticsEventCategory.Custom);
    }

    public CustomEvent(String str, Set<AnalyticAttribute> set) {
        super(str, AnalyticsEventCategory.Custom, null, set);
    }

    public CustomEvent(String str, String str2, Set<AnalyticAttribute> set) {
        super(str, AnalyticsEventCategory.Custom, str2, set);
    }
}
