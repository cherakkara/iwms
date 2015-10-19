package com.crashlytics.android.answers;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import p004b.p005a.p006a.p007a.p008a.p010b.ExecutorUtils;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;
import p004b.p005a.p006a.p007a.p008a.p016g.AnalyticsSettingsData;

class SessionAnalyticsManager {
    private static final String EXECUTOR_SERVICE = "Crashlytics SAM";
    static final String ON_CRASH_ERROR_MSG = "onCrash called from main thread!!!";
    boolean customEventsEnabled;
    final SessionEventsHandler eventsHandler;
    final SessionEventMetadata metadata;

    public static SessionAnalyticsManager build(Context context, SessionEventMetadata sessionEventMetadata, SessionAnalyticsFilesManager sessionAnalyticsFilesManager, HttpRequestFactory httpRequestFactory) {
        ScheduledExecutorService b = ExecutorUtils.m199b(EXECUTOR_SERVICE);
        return new SessionAnalyticsManager(sessionEventMetadata, new SessionEventsHandler(context, new EnabledSessionAnalyticsManagerStrategy(context, b, sessionAnalyticsFilesManager, httpRequestFactory), sessionAnalyticsFilesManager, b));
    }

    SessionAnalyticsManager(SessionEventMetadata sessionEventMetadata, SessionEventsHandler sessionEventsHandler) {
        this.customEventsEnabled = true;
        this.metadata = sessionEventMetadata;
        this.eventsHandler = sessionEventsHandler;
    }

    public void onCustom(String str, Map<String, Object> map) {
        if (this.customEventsEnabled) {
            this.eventsHandler.recordEventAsync(SessionEvent.buildCustomEvent(this.metadata, str, map), false);
        }
    }

    public void onCrash(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException(ON_CRASH_ERROR_MSG);
        }
        this.eventsHandler.recordEventSync(SessionEvent.buildCrashEvent(this.metadata, str));
    }

    public void onError(String str) {
        this.eventsHandler.recordEventAsync(SessionEvent.buildErrorEvent(this.metadata, str), false);
    }

    public void onInstall() {
        this.eventsHandler.recordEventAsync(SessionEvent.buildInstallEvent(this.metadata), true);
    }

    public void onCreate(Activity activity) {
        this.eventsHandler.recordEventAsync(SessionEvent.buildActivityLifecycleEvent(this.metadata, Type.CREATE, activity), false);
    }

    public void onDestroy(Activity activity) {
        this.eventsHandler.recordEventAsync(SessionEvent.buildActivityLifecycleEvent(this.metadata, Type.DESTROY, activity), false);
    }

    public void onPause(Activity activity) {
        this.eventsHandler.recordEventAsync(SessionEvent.buildActivityLifecycleEvent(this.metadata, Type.PAUSE, activity), false);
    }

    public void onResume(Activity activity) {
        this.eventsHandler.recordEventAsync(SessionEvent.buildActivityLifecycleEvent(this.metadata, Type.RESUME, activity), false);
    }

    public void onSaveInstanceState(Activity activity) {
        this.eventsHandler.recordEventAsync(SessionEvent.buildActivityLifecycleEvent(this.metadata, Type.SAVE_INSTANCE_STATE, activity), false);
    }

    public void onStart(Activity activity) {
        this.eventsHandler.recordEventAsync(SessionEvent.buildActivityLifecycleEvent(this.metadata, Type.START, activity), false);
    }

    public void onStop(Activity activity) {
        this.eventsHandler.recordEventAsync(SessionEvent.buildActivityLifecycleEvent(this.metadata, Type.STOP, activity), false);
    }

    public void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData, String str) {
        this.customEventsEnabled = analyticsSettingsData.f317f;
        this.eventsHandler.setAnalyticsSettingsData(analyticsSettingsData, str);
    }

    public void disable() {
        this.eventsHandler.disable();
    }
}
