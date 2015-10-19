package com.localytics.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

public class ReferralReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                extras.containsKey(null);
            }
            if (intent.getAction().equals("com.android.vending.INSTALL_REFERRER")) {
                Object localyticsAppKeyOrNull = DatapointHelper.getLocalyticsAppKeyOrNull(context);
                if (!TextUtils.isEmpty(localyticsAppKeyOrNull)) {
                    Localytics.integrate(context.getApplicationContext(), localyticsAppKeyOrNull);
                    String stringExtra = intent.getStringExtra("referrer");
                    if (stringExtra != null && stringExtra.length() != 0) {
                        LocalyticsManager.getInstance().setReferrerId(stringExtra);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
