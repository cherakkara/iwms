package com.crashlytics.android.answers;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import p004b.p005a.p006a.p007a.p008a.p010b.ApiKey;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p013d.EnabledEventsStrategy;
import p004b.p005a.p006a.p007a.p008a.p013d.FilesSender;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;
import p004b.p005a.p006a.p007a.p008a.p016g.AnalyticsSettingsData;

class EnabledSessionAnalyticsManagerStrategy extends EnabledEventsStrategy<SessionEvent> implements SessionAnalyticsManagerStrategy<SessionEvent> {
    EventFilter eventFilter;
    FilesSender filesSender;
    private final HttpRequestFactory httpRequestFactory;

    public EnabledSessionAnalyticsManagerStrategy(Context context, ScheduledExecutorService scheduledExecutorService, SessionAnalyticsFilesManager sessionAnalyticsFilesManager, HttpRequestFactory httpRequestFactory) {
        super(context, scheduledExecutorService, sessionAnalyticsFilesManager);
        this.eventFilter = new KeepAllEventFilter();
        this.httpRequestFactory = httpRequestFactory;
    }

    public FilesSender getFilesSender() {
        return this.filesSender;
    }

    public void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData, String str) {
        this.filesSender = AnswersRetryFilesSender.build(new SessionAnalyticsFilesSender(Answers.getInstance(), str, analyticsSettingsData.f312a, this.httpRequestFactory, new ApiKey().m141a(this.context)));
        ((SessionAnalyticsFilesManager) this.filesManager).setAnalyticsSettingsData(analyticsSettingsData);
        configureRollover(analyticsSettingsData.f313b);
        if (analyticsSettingsData.f318g > 1) {
            this.eventFilter = new SamplingEventFilter(analyticsSettingsData.f318g);
        }
    }

    public void recordEvent(SessionEvent sessionEvent) {
        if (this.eventFilter.skipEvent(sessionEvent)) {
            CommonUtils.m164a(Answers.getInstance().getContext(), "skipping filtered event " + sessionEvent);
        } else {
            super.recordEvent(sessionEvent);
        }
    }
}
