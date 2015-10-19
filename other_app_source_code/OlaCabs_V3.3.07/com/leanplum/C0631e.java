package com.leanplum;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.leanplum.callbacks.ActionCallback;

/* renamed from: com.leanplum.e */
final class C0631e extends ActionCallback {
    C0631e(C0629c c0629c) {
    }

    public final boolean onResponse(ActionContext actionContext) {
        boolean z;
        String c = actionContext.m12407c();
        Context a = Leanplum.m12431a();
        SharedPreferences sharedPreferences = a.getSharedPreferences("__leanplum_messaging__", 0);
        String format = String.format("__leanplum_local_message_%s", new Object[]{c});
        long j = sharedPreferences.getLong(format, 0);
        Editor edit = sharedPreferences.edit();
        edit.remove(format);
        try {
            edit.apply();
        } catch (NoSuchMethodError e) {
            edit.commit();
        }
        ((AlarmManager) a.getSystemService(NotificationCompatApi21.CATEGORY_ALARM)).cancel(PendingIntent.getService(a, c.hashCode(), new Intent(a, LeanplumPushService.class), 134217728));
        if (j > System.currentTimeMillis()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            Log.i("Leanplum", "Cancelled notification");
        }
        return z;
    }
}
