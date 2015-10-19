package com.olacabs.customer.app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class OlaReceiver extends WakefulBroadcastReceiver {
    private static final String f8970a;

    static {
        f8970a = OlaReceiver.class.getSimpleName();
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            OLog.m13311a("Broadcast received at " + System.currentTimeMillis() + ". Action - " + intent.getAction(), new Object[0]);
            intent.setClass(context, OlaService.class);
            WakefulBroadcastReceiver.startWakefulService(context, intent);
        }
    }
}
