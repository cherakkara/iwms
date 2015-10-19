package com.facebook.p022b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.p023a.AppEventsLogger;
import com.newrelic.agent.android.instrumentation.Trace;

/* renamed from: com.facebook.b.c */
public class BoltsMeasurementEventListener extends BroadcastReceiver {
    private static BoltsMeasurementEventListener f738a;
    private Context f739b;

    private BoltsMeasurementEventListener(Context context) {
        this.f739b = context.getApplicationContext();
    }

    private void m888a() {
        LocalBroadcastManager.getInstance(this.f739b).registerReceiver(this, new IntentFilter("com.parse.bolts.measurement_event"));
    }

    private void m889b() {
        LocalBroadcastManager.getInstance(this.f739b).unregisterReceiver(this);
    }

    public static BoltsMeasurementEventListener m887a(Context context) {
        if (f738a != null) {
            return f738a;
        }
        f738a = new BoltsMeasurementEventListener(context);
        f738a.m888a();
        return f738a;
    }

    protected void finalize() throws Throwable {
        try {
            m889b();
        } finally {
            super.finalize();
        }
    }

    public void onReceive(Context context, Intent intent) {
        AppEventsLogger a = AppEventsLogger.m830a(context);
        String str = "bf_" + intent.getStringExtra("event_name");
        Bundle bundleExtra = intent.getBundleExtra("event_args");
        Bundle bundle = new Bundle();
        for (String str2 : bundleExtra.keySet()) {
            bundle.putString(str2.replaceAll("[^0-9a-zA-Z _-]", "-").replaceAll("^[ -]*", Trace.NULL).replaceAll("[ -]*$", Trace.NULL), (String) bundleExtra.get(str2));
        }
        a.m855a(str, bundle);
    }
}
