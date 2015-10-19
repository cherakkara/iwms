package com.apsalar.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;

public class ApsalarReceiver extends BroadcastReceiver {
    static final String TAG = "Apsalar SDK/Receiver";

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            intent.getStringExtra("extraInfo");
            AlarmClock.interrupt();
        }
        intent.setComponent(null);
        for (ResolveInfo resolveInfo : context.getPackageManager().queryBroadcastReceivers(intent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo.enabled && activityInfo.exported && activityInfo.packageName.equals(context.getPackageName())) {
                String str = activityInfo.name;
                if (!activityInfo.name.equals(getClass().getName())) {
                    try {
                        BroadcastReceiver broadcastReceiver = (BroadcastReceiver) Class.forName(str).newInstance();
                        intent.setClassName(context, str);
                        broadcastReceiver.onReceive(context, intent);
                    } catch (Throwable th) {
                    }
                } else if (action.equals("com.android.vending.INSTALL_REFERRER")) {
                    String stringExtra = intent.getStringExtra("referrer");
                    if (stringExtra != null && stringExtra.length() != 0) {
                        try {
                            saveCSIReferrer(context, URLDecoder.decode(stringExtra, HTTP.UTF_8));
                        } catch (UnsupportedEncodingException e) {
                            return;
                        }
                    }
                    return;
                } else {
                    continue;
                }
            }
        }
    }

    private boolean invokeStartSession(Context context) {
        Context applicationContext = context.getApplicationContext();
        ApSingleton instance = ApSingleton.getInstance(applicationContext);
        instance.ctx = applicationContext;
        instance.apsalar_receiver = this;
        return false;
    }

    public static Map<String, String> retrieveCSIReferrer(Context context) {
        Map hashMap = new HashMap();
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("apsalar_csi_" + context.getPackageName(), 0);
            for (String str : sharedPreferences.getAll().keySet()) {
                String string = sharedPreferences.getString(str, null);
                if (string != null) {
                    hashMap.put(str, string);
                }
            }
        }
        return hashMap;
    }

    public static void saveCSIReferrer(Context context, String str) {
        if (context != null) {
            Editor edit = context.getSharedPreferences("apsalar_csi_" + context.getPackageName(), 0).edit();
            edit.putString("referrer", str);
            edit.commit();
        }
    }
}
