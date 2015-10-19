package com.localytics.android;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class LocalyticsActivityWithMarketing extends FragmentActivity implements TraceFieldInterface {
    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public void onPause() {
        Localytics.dismissCurrentInAppMessage();
        Localytics.clearInAppMessageDisplayActivity();
        Localytics.closeSession();
        Localytics.upload();
        super.onPause();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public void onResume() {
        super.onResume();
        Localytics.openSession();
        Localytics.setInAppMessageDisplayActivity(this);
        Localytics.handlePushNotificationOpened(getIntent());
        Localytics.handleTestMode(getIntent());
        Localytics.upload();
    }
}
