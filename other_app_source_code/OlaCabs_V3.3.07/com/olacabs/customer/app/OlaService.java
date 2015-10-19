package com.olacabs.customer.app;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class OlaService extends IntentService {
    private static final String f8971a;

    static {
        f8971a = OlaService.class.getSimpleName();
    }

    public OlaService() {
        super(f8971a);
    }

    protected void onHandleIntent(Intent intent) {
        OLog.m13311a("Service started", new Object[0]);
        if (intent != null && intent.getAction() != null) {
            if (intent.getAction().equals("android.intent.action.MY_PACKAGE_REPLACED")) {
                new HandleAppUpdateCommand().m13273a(getApplicationContext());
            }
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }
}
