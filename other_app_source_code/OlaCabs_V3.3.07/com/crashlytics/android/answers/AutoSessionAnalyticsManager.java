package com.crashlytics.android.answers;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.util.concurrent.ScheduledExecutorService;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.ExecutorUtils;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;

@TargetApi(14)
class AutoSessionAnalyticsManager extends SessionAnalyticsManager {
    private static final String EXECUTOR_SERVICE = "Crashlytics Trace Manager";
    private final ActivityLifecycleCallbacks activityLifecycleCallbacks;
    private final Application application;

    /* renamed from: com.crashlytics.android.answers.AutoSessionAnalyticsManager.1 */
    class C01141 implements ActivityLifecycleCallbacks {
        C01141() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            AutoSessionAnalyticsManager.this.onCreate(activity);
        }

        public void onActivityDestroyed(Activity activity) {
            AutoSessionAnalyticsManager.this.onDestroy(activity);
        }

        public void onActivityPaused(Activity activity) {
            AutoSessionAnalyticsManager.this.onPause(activity);
        }

        public void onActivityResumed(Activity activity) {
            AutoSessionAnalyticsManager.this.onResume(activity);
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            AutoSessionAnalyticsManager.this.onSaveInstanceState(activity);
        }

        public void onActivityStarted(Activity activity) {
            AutoSessionAnalyticsManager.this.onStart(activity);
        }

        public void onActivityStopped(Activity activity) {
            AutoSessionAnalyticsManager.this.onStop(activity);
        }
    }

    public static AutoSessionAnalyticsManager build(Application application, SessionEventMetadata sessionEventMetadata, SessionAnalyticsFilesManager sessionAnalyticsFilesManager, HttpRequestFactory httpRequestFactory) {
        ScheduledExecutorService b = ExecutorUtils.m199b(EXECUTOR_SERVICE);
        return new AutoSessionAnalyticsManager(sessionEventMetadata, new SessionEventsHandler(application, new EnabledSessionAnalyticsManagerStrategy(application, b, sessionAnalyticsFilesManager, httpRequestFactory), sessionAnalyticsFilesManager, b), application);
    }

    AutoSessionAnalyticsManager(SessionEventMetadata sessionEventMetadata, SessionEventsHandler sessionEventsHandler, Application application) {
        super(sessionEventMetadata, sessionEventsHandler);
        this.activityLifecycleCallbacks = new C01141();
        this.application = application;
        CommonUtils.m164a(Answers.getInstance().getContext(), "Registering activity lifecycle callbacks for session analytics.");
        application.registerActivityLifecycleCallbacks(this.activityLifecycleCallbacks);
    }

    public void disable() {
        CommonUtils.m164a(Answers.getInstance().getContext(), "Unregistering activity lifecycle callbacks for session analytics");
        this.application.unregisterActivityLifecycleCallbacks(this.activityLifecycleCallbacks);
        super.disable();
    }
}
