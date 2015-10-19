package com.newrelic.agent.android.analytics;

import java.util.Set;

public class CrashEvent extends AnalyticsEvent {
    public CrashEvent(String str) {
        super(str, AnalyticsEventCategory.Crash);
    }

    public CrashEvent(String str, Set<AnalyticAttribute> set) {
        super(str, AnalyticsEventCategory.Crash, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE, set);
    }
}
