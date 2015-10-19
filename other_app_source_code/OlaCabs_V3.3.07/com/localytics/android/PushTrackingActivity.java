package com.localytics.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class PushTrackingActivity extends Activity implements TraceFieldInterface {
    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("PushTrackingActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "PushTrackingActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "PushTrackingActivity#onCreate", null);
                break;
            }
        }
        super.onCreate(bundle);
        Intent intent = getIntent();
        Object localyticsAppKeyOrNull = DatapointHelper.getLocalyticsAppKeyOrNull(this);
        if (!TextUtils.isEmpty(localyticsAppKeyOrNull)) {
            Localytics.integrate(getApplicationContext(), localyticsAppKeyOrNull);
        }
        Localytics.openSession();
        Localytics.handlePushNotificationOpened(intent);
        finish();
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        launchIntentForPackage.putExtras(intent);
        launchIntentForPackage.addFlags(603979776);
        startActivity(launchIntentForPackage);
        TraceMachine.exitMethod();
    }
}
