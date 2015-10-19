package com.crashlytics.android.answers;

import android.content.Context;
import java.io.IOException;
import java.util.UUID;
import p004b.p005a.p006a.p007a.p008a.p010b.CurrentTimeProvider;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsFilesManager;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsStorage;
import p004b.p005a.p006a.p007a.p008a.p016g.AnalyticsSettingsData;

class SessionAnalyticsFilesManager extends EventsFilesManager<SessionEvent> {
    private static final String SESSION_ANALYTICS_TO_SEND_FILE_PREFIX = "sa";
    private AnalyticsSettingsData analyticsSettingsData;

    SessionAnalyticsFilesManager(Context context, SessionEventTransform sessionEventTransform, CurrentTimeProvider currentTimeProvider, EventsStorage eventsStorage) throws IOException {
        super(context, sessionEventTransform, currentTimeProvider, eventsStorage, 100);
    }

    protected String generateUniqueRollOverFileName() {
        return SESSION_ANALYTICS_TO_SEND_FILE_PREFIX + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + UUID.randomUUID().toString() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.currentTimeProvider.m193a() + ".tap";
    }

    protected int getMaxFilesToKeep() {
        return this.analyticsSettingsData == null ? super.getMaxFilesToKeep() : this.analyticsSettingsData.f316e;
    }

    protected int getMaxByteSizePerFile() {
        return this.analyticsSettingsData == null ? super.getMaxByteSizePerFile() : this.analyticsSettingsData.f314c;
    }

    void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData) {
        this.analyticsSettingsData = analyticsSettingsData;
    }
}
