package com.leanplum;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.p076d.da;
import java.util.Random;

public class LeanplumPushService extends IntentService {
    public static final String LEANPLUM_SENDER_ID = "44059457771";
    private static LeanplumPushNotificationCustomizer f8587a;
    private static String f8588b;

    public LeanplumPushService() {
        super("LeanplumPushService");
    }

    public static void setCustomizer(LeanplumPushNotificationCustomizer leanplumPushNotificationCustomizer) {
        f8587a = leanplumPushNotificationCustomizer;
    }

    public static void setGcmSenderId(String str) {
        f8588b = str;
    }

    public static void setGcmSenderIds(String... strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : strArr) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append(',');
            }
            stringBuffer.append(str);
        }
        f8588b = stringBuffer.toString();
    }

    static Class<? extends Activity> m12489a() {
        return null;
    }

    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        Object stringExtra;
        if ("com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            stringExtra = intent.getStringExtra("message_type");
            if (stringExtra == null) {
                stringExtra = GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE;
            }
        } else {
            stringExtra = null;
        }
        if (!extras.isEmpty() && (r0 == null || GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(r0))) {
            if (extras.containsKey("lp_message") && (!(extras.containsKey("_lpu") || extras.containsKey("_lpv")) || LeanplumActivityHelper.f8577b == null || LeanplumActivityHelper.f8576a)) {
                String b = m12496b(extras);
                if (b == null) {
                    m12499c(extras);
                } else if (Leanplum.f8554a) {
                    VariablesChangedCallback c0614o = new C0614O(this, b, extras);
                    if (extras.containsKey("_lpx")) {
                        c0614o.variablesChanged();
                    } else {
                        m12492a(b, c0614o);
                    }
                } else {
                    m12499c(extras);
                }
            }
            Log.i("Leanplum", "Received: " + extras.toString());
        }
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }

    static boolean m12493a(Bundle bundle) {
        return bundle.containsKey("_lpx");
    }

    static void m12492a(String str, VariablesChangedCallback variablesChangedCallback) {
        Leanplum.addOnceVariablesChangedAndNoDownloadsPendingHandler(new C0615P(str, variablesChangedCallback));
    }

    private void m12499c(Bundle bundle) {
        int intValue;
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        Intent intent = new Intent(this, GcmBroadcastReceiver.class);
        intent.addCategory("lpAction");
        intent.putExtras(bundle);
        PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), new Random().nextInt(), intent, 0);
        Context applicationContext = getApplicationContext();
        int i = applicationContext.getApplicationInfo().labelRes;
        CharSequence charSequence = i == 0 ? applicationContext.getApplicationInfo().loadLabel(applicationContext.getPackageManager()).toString() : applicationContext.getString(i);
        if (bundle.getString("title") != null) {
            charSequence = bundle.getString("title");
        }
        Builder contentText = new Builder(this).setSmallIcon(getApplicationInfo().icon).setContentTitle(charSequence).setStyle(new BigTextStyle().bigText(bundle.getString("lp_message"))).setContentText(bundle.getString("lp_message"));
        contentText.setAutoCancel(true);
        contentText.setContentIntent(broadcast);
        if (f8587a != null) {
            f8587a.customize(contentText, bundle);
        }
        Object obj = bundle.get("lp_notificationId");
        if (obj instanceof Number) {
            intValue = ((Number) obj).intValue();
        } else if (obj instanceof String) {
            try {
                intValue = Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
            }
        } else {
            if (bundle.containsKey("lp_messageId")) {
                intValue = bundle.getString("lp_messageId").hashCode();
            }
            intValue = 1;
        }
        notificationManager.notify(intValue, contentText.build());
    }

    static String m12496b(Bundle bundle) {
        String string = bundle.getString("_lpm");
        if (string == null) {
            string = bundle.getString("_lpu");
            if (string == null) {
                string = bundle.getString("_lpn");
                if (string == null) {
                    string = bundle.getString("_lpv");
                }
            }
        }
        if (string != null) {
            bundle.putString("lp_messageId", string);
        }
        return string;
    }

    private static int m12488a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    private static SharedPreferences m12495b(Context context) {
        return context.getSharedPreferences("__leanplum_push__", 0);
    }

    public static void unregister() {
        Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        Context a = Leanplum.m12431a();
        intent.putExtra("app", PendingIntent.getBroadcast(a, 0, new Intent(), 0));
        intent.setPackage("com.google.android.gms");
        a.startService(intent);
    }

    static void m12491a(String str) {
        Leanplum.m12438a(str);
        Context applicationContext = Leanplum.m12431a().getApplicationContext();
        SharedPreferences b = m12495b(applicationContext);
        int a = m12488a(applicationContext);
        if (C0633g.f8806m) {
            Log.i("Leanplum", "Saving GCM registration on app version " + a);
        }
        Editor edit = b.edit();
        edit.putString(da.GCM_REG_ID_KEY, str);
        edit.putInt("appVersion", a);
        try {
            edit.apply();
        } catch (NoSuchMethodError e) {
            edit.commit();
        }
        Log.i("Leanplum", "Device registered for push notifications with registrationId " + str);
    }

    static void m12497b() {
        try {
            if (Util.m12578i()) {
                Context a = Leanplum.m12431a();
                if (f8588b != null && m12494a("com.google.android.c2dm.permission.RECEIVE", false, true)) {
                    if ((m12494a(a.getPackageName() + ".gcm.permission.C2D_MESSAGE", true, false) || m12494a(a.getPackageName() + ".permission.C2D_MESSAGE", true, true)) && m12498b("com.google.android.c2dm.intent.RECEIVE") && m12498b("com.google.android.c2dm.intent.REGISTRATION") && m12500c()) {
                        Context applicationContext = a.getApplicationContext();
                        SharedPreferences b = m12495b(applicationContext);
                        String string = b.getString(da.GCM_REG_ID_KEY, Trace.NULL);
                        String string2 = b.getString("sender_ids", Trace.NULL);
                        if (string.length() == 0) {
                            Log.i("Leanplum", "GCM registration not found.");
                            string = Trace.NULL;
                        } else if (string2.equals(f8588b)) {
                            Log.i("Leanplum", "GCM sender IDs have changed.");
                            string = Trace.NULL;
                        } else if (b.getInt("appVersion", ExploreByTouchHelper.INVALID_ID) != m12488a(applicationContext)) {
                            string = Trace.NULL;
                        }
                        if (string.length() == 0) {
                            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                            applicationContext = Leanplum.m12431a();
                            intent.putExtra("app", PendingIntent.getBroadcast(applicationContext, 0, new Intent(), 0));
                            intent.putExtra("sender", f8588b);
                            intent.setPackage("com.google.android.gms");
                            applicationContext.startService(intent);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            Log.i("Leanplum", "No valid Google Play Services APK found.");
        } catch (Throwable e) {
            Log.e("Leanplum", "There was an error registering for push notifications.", e);
        }
    }

    private static boolean m12494a(String str, boolean z, boolean z2) {
        if (Leanplum.m12431a().checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        String str2;
        if (z) {
            str2 = "<permission android:name=\"" + str + "\" android:protectionLevel=\"signature\" />\n";
        } else {
            str2 = Trace.NULL;
        }
        if (z2) {
            Log.e("Leanplum", "In order to use push notifications, you need to enable the " + str + " permission in your AndroidManifest.xml file. Add this within the <manifest> section:\n" + str2 + "<uses-permission android:name=\"" + str + "\" />");
        }
        return false;
    }

    private static boolean m12498b(String str) {
        Context a = Leanplum.m12431a();
        String name = GcmBroadcastReceiver.class.getName();
        for (ResolveInfo resolveInfo : a.getPackageManager().queryBroadcastReceivers(new Intent(str), 0)) {
            if (resolveInfo.activityInfo.name.equals(name) && resolveInfo.activityInfo.packageName.equals(a.getPackageName())) {
                return true;
            }
        }
        Log.e("Leanplum", "Push notifications requires you to add the receiver " + name + " to your AndroidManifest.xml file with the " + str + " intent filter. Add this code within the <application> section:\n<receiver android:name=\"" + name + "\"\n    android:permission=\"com.google.android.c2dm.permission.SEND\">\n" + "    <intent-filter>\n        <action android:name=\"" + "com.google.android.c2dm.intent.RECEIVE\" />\n" + "        <action android:name=\"com.google.android.c2dm.intent.REGISTRATION" + "\" />\n        <category android:name=\"" + a.getPackageName() + "\" />\n    </intent-filter>\n" + "</receiver>");
        return false;
    }

    private static boolean m12500c() {
        String name = LeanplumPushService.class.getName();
        Context a = Leanplum.m12431a();
        try {
            a.getPackageManager().getServiceInfo(new ComponentName(a.getPackageName(), name), 0);
            return true;
        } catch (NameNotFoundException e) {
            Log.e("Leanplum", "Push notifications requires you to add the following service to your AndroidManifest.xml file within the <application> section:\n<service android:name=\"" + name + "\" />");
            return false;
        }
    }
}
