package com.leanplum;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.leanplum.callbacks.ActionCallback;
import java.io.Serializable;
import java.util.Map;

/* renamed from: com.leanplum.d */
final class C0630d extends ActionCallback {
    C0630d(C0629c c0629c) {
    }

    public final boolean onResponse(ActionContext actionContext) {
        Object valueOf;
        String c = actionContext.m12407c();
        if (actionContext.m12408d()) {
            valueOf = Double.valueOf(5.0d);
        } else {
            Map map = (Map) aT.m12673b().get(c);
            if (map == null) {
                Log.e("Leanplum", "Could not find message options for ID " + c);
                return false;
            }
            valueOf = map.get("countdown");
        }
        if (valueOf instanceof Number) {
            long currentTimeMillis = System.currentTimeMillis() + (((Number) valueOf).longValue() * 1000);
            Context a = Leanplum.m12431a();
            Intent intent = new Intent(a, LeanplumPushService.class);
            AlarmManager alarmManager = (AlarmManager) a.getSystemService(NotificationCompatApi21.CATEGORY_ALARM);
            SharedPreferences sharedPreferences = a.getSharedPreferences("__leanplum_messaging__", 0);
            long j = sharedPreferences.getLong(String.format("__leanplum_local_message_%s", new Object[]{c}), 0);
            if (j > 0 && j > System.currentTimeMillis()) {
                if (j < currentTimeMillis) {
                    return false;
                }
                if (j >= currentTimeMillis) {
                    alarmManager.cancel(PendingIntent.getService(a, c.hashCode(), intent, 134217728));
                }
            }
            Map map2 = (Map) actionContext.objectNamed("Advanced options.Data");
            if (map2 != null) {
                for (String str : map2.keySet()) {
                    intent.putExtra(str, (Serializable) map2.get(str));
                }
            }
            String stringNamed = actionContext.stringNamed("Open action");
            boolean equals = Boolean.TRUE.equals(actionContext.objectNamed("Advanced options.Mute inside app"));
            if (stringNamed != null) {
                if (equals) {
                    intent.putExtra("_lpu", c);
                } else {
                    intent.putExtra("_lpm", c);
                }
            } else if (equals) {
                intent.putExtra("_lpv", c);
            } else {
                intent.putExtra("_lpn", c);
            }
            stringNamed = actionContext.stringNamed("Message");
            String str2 = "lp_message";
            if (stringNamed == null) {
                stringNamed = "Push message goes here.";
            }
            intent.putExtra(str2, stringNamed);
            stringNamed = actionContext.stringNamed("Android options.Collapse key");
            if (stringNamed != null) {
                intent.putExtra("collapseKey", stringNamed);
            }
            boolean equals2 = Boolean.TRUE.equals(actionContext.objectNamed("Android options.Delay while idle"));
            if (equals2) {
                intent.putExtra("delayWhileIdle", equals2);
            }
            alarmManager.set(0, currentTimeMillis, PendingIntent.getService(a, c.hashCode(), intent, 134217728));
            Editor edit = sharedPreferences.edit();
            edit.putLong(String.format("__leanplum_local_message_%s", new Object[]{c}), currentTimeMillis);
            try {
                edit.apply();
            } catch (NoSuchMethodError e) {
                edit.commit();
            }
            if (C0633g.f8804k) {
                Log.i("Leanplum", "Scheduled notification");
            }
            return true;
        }
        Log.e("Leanplum", "Invalid notification countdown: " + valueOf);
        return false;
    }
}
