package com.localytics.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

@TargetApi(14)
public class LocalyticsActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
    public LocalyticsActivityLifecycleCallbacks(Context context) {
        this(context, null);
    }

    public LocalyticsActivityLifecycleCallbacks(Context context, String str) {
        Localytics.integrate(context, str);
        Localytics.setIsAutoIntegrate(true);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        Localytics.incrementActivityCounter();
    }

    public void onActivityResumed(Activity activity) {
        Localytics.openSession();
        Localytics.upload();
        if (activity instanceof FragmentActivity) {
            Localytics.setInAppMessageDisplayActivity((FragmentActivity) activity);
        }
        Localytics.handleTestMode(activity.getIntent());
        Localytics.handlePushNotificationOpened(activity.getIntent());
    }

    public void onActivityPaused(Activity activity) {
        if (activity instanceof FragmentActivity) {
            Localytics.dismissCurrentInAppMessage();
            Localytics.clearInAppMessageDisplayActivity();
        }
        Localytics.closeSession();
        Localytics.upload();
    }

    public void onActivityStopped(Activity activity) {
        Localytics.decrementActivityCounter();
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
