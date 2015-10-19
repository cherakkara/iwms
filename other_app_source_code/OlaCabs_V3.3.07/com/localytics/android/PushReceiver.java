package com.localytics.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

public class PushReceiver extends BroadcastReceiver {
    private static final int MAX_RETRIES = 3;
    private static final long RETRY_DELAY = 5000;
    private static int numRetries;
    static String retrySenderId;

    static {
        retrySenderId = null;
        numRetries = 0;
    }

    public void onReceive(Context context, Intent intent) {
        Object localyticsAppKeyOrNull = DatapointHelper.getLocalyticsAppKeyOrNull(context);
        if (!TextUtils.isEmpty(localyticsAppKeyOrNull)) {
            Localytics.integrate(context.getApplicationContext(), localyticsAppKeyOrNull);
        }
        if (intent.getAction().equals("com.google.android.c2dm.intent.REGISTRATION")) {
            Localytics.handleRegistration(intent);
            return;
        }
        Bundle extras = intent.getExtras();
        try {
            localyticsAppKeyOrNull = ReflectionUtils.tryInvokeStatic("com.google.android.gms.gcm.GoogleCloudMessaging", "getInstance", new Class[]{Context.class}, new Object[]{context});
            if (localyticsAppKeyOrNull != null) {
                String str = (String) ReflectionUtils.tryInvokeInstance(localyticsAppKeyOrNull, "getMessageType", new Class[]{Intent.class}, new Object[]{intent});
                if (!extras.isEmpty()) {
                    if (ReflectionUtils.tryGetStaticField("com.google.android.gms.gcm.GoogleCloudMessaging", "ERROR_SERVICE_NOT_AVAILABLE").equals(str)) {
                        if (!TextUtils.isEmpty(retrySenderId)) {
                            numRetries++;
                            if (numRetries <= MAX_RETRIES) {
                                Log.m12801w("GCM registration ERROR_SERVICE_NOT_AVAILABLE. Retrying in 5000 milliseconds.");
                                Localytics.registerPush(retrySenderId, RETRY_DELAY);
                            } else {
                                numRetries = 0;
                            }
                            retrySenderId = null;
                        }
                    } else if (ReflectionUtils.tryGetStaticField("com.google.android.gms.gcm.GoogleCloudMessaging", "MESSAGE_TYPE_MESSAGE").equals(str)) {
                        Localytics.handleNotificationReceived(intent);
                    }
                }
            }
        } catch (Throwable e) {
            Log.m12802w("Something went wrong with GCM", e);
        }
    }
}
