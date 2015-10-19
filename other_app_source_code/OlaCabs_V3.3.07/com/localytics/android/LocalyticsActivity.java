package com.localytics.android;

import android.app.Activity;
import android.content.Intent;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class LocalyticsActivity extends Activity implements TraceFieldInterface {
    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public void onResume() {
        super.onResume();
        Localytics.openSession();
        Localytics.handlePushNotificationOpened(getIntent());
        Localytics.upload();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public void onPause() {
        Localytics.closeSession();
        Localytics.upload();
        super.onPause();
    }
}
