package com.crashlytics.android.answers;

import p004b.p005a.p006a.p007a.p008a.p013d.EventsStrategy;
import p004b.p005a.p006a.p007a.p008a.p016g.AnalyticsSettingsData;

interface SessionAnalyticsManagerStrategy<T> extends EventsStrategy<T> {
    void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData, String str);
}
