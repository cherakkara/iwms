package com.olacabs.customer.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {
    private static final String f8965a;

    static {
        f8965a = GCMBroadcastReceiver.class.getSimpleName();
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            OLog.m13311a("Broadcast received at " + System.currentTimeMillis() + ". Action - " + intent.getAction(), new Object[0]);
            WakefulBroadcastReceiver.startWakefulService(context, intent.setComponent(new ComponentName(context.getPackageName(), GCMIntentService.class.getName())));
            setResultCode(-1);
        }
    }
}
