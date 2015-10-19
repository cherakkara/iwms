package com.crashlytics.android.answers;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsHandler;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsStrategy;
import p004b.p005a.p006a.p007a.p008a.p016g.AnalyticsSettingsData;

class SessionEventsHandler extends EventsHandler<SessionEvent> {

    /* renamed from: com.crashlytics.android.answers.SessionEventsHandler.1 */
    class C01161 implements Runnable {
        final /* synthetic */ AnalyticsSettingsData val$analyticsSettingsData;
        final /* synthetic */ String val$protocolAndHostOverride;

        C01161(AnalyticsSettingsData analyticsSettingsData, String str) {
            this.val$analyticsSettingsData = analyticsSettingsData;
            this.val$protocolAndHostOverride = str;
        }

        public void run() {
            try {
                ((SessionAnalyticsManagerStrategy) SessionEventsHandler.this.strategy).setAnalyticsSettingsData(this.val$analyticsSettingsData, this.val$protocolAndHostOverride);
            } catch (Throwable e) {
                CommonUtils.m165a(Answers.getInstance().getContext(), "Crashlytics failed to set analytics settings data.", e);
            }
        }
    }

    SessionEventsHandler(Context context, EventsStrategy<SessionEvent> eventsStrategy, SessionAnalyticsFilesManager sessionAnalyticsFilesManager, ScheduledExecutorService scheduledExecutorService) {
        super(context, eventsStrategy, sessionAnalyticsFilesManager, scheduledExecutorService);
    }

    protected EventsStrategy<SessionEvent> getDisabledEventsStrategy() {
        return new DisabledSessionAnalyticsManagerStrategy();
    }

    protected void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData, String str) {
        super.executeAsync(new C01161(analyticsSettingsData, str));
    }
}
