package com.leanplum;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.olacabs.customer.p076d.da;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Object obj = null;
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
            if (intent.getStringExtra("error") != null) {
                Log.e("Leanplum", "Error when registering for push notifications: " + intent.getStringExtra("error"));
                return;
            }
            String stringExtra = intent.getStringExtra(da.GCM_REG_ID_KEY);
            if (stringExtra != null) {
                LeanplumPushService.m12491a(stringExtra);
            }
        } else if (intent.hasCategory("lpAction")) {
            Bundle extras = intent.getExtras();
            Log.i("Leanplum", "Opening notification");
            Class a = LeanplumPushService.m12489a();
            if (LeanplumActivityHelper.f8577b == null || LeanplumActivityHelper.f8576a || (a != null && (a == null || !a.isInstance(LeanplumActivityHelper.f8577b)))) {
                int i = 1;
            }
            if (obj != null) {
                Class a2 = LeanplumPushService.m12489a();
                Intent intent2 = a2 != null ? new Intent(context, a2) : context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                intent2.putExtras(extras);
                intent2.addFlags(335544320);
                context.startActivity(intent2);
            }
            LeanplumActivityHelper.queueActionUponActive(new C0639m(this, extras));
        } else {
            WakefulBroadcastReceiver.startWakefulService(context, intent.setComponent(new ComponentName(context.getPackageName(), LeanplumPushService.class.getName())));
            setResultCode(-1);
        }
    }
}
