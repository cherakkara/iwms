package com.olacabs.customer.app;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.text.TextUtils;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.ui.SplashActivity;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.sothree.slidinguppanel.p086a.R.R;

public class GCMIntentService extends IntentService {
    private static final String f8966a;

    static {
        f8966a = GCMIntentService.class.getSimpleName();
    }

    public GCMIntentService() {
        super(f8966a);
    }

    protected void onHandleIntent(Intent intent) {
        OLog.m13311a("GCMIntentService started", new Object[0]);
        if (intent == null) {
            OLog.m13311a("Started with empty intent", new Object[0]);
            return;
        }
        if (!intent.getAction().equals("com.google.android.c2dm.intent.REGISTRATION")) {
            Bundle extras = intent.getExtras();
            String messageType = GoogleCloudMessaging.getInstance(this).getMessageType(intent);
            if (!extras.isEmpty()) {
                int i = -1;
                switch (messageType.hashCode()) {
                    case -2062414158:
                        if (messageType.equals(GoogleCloudMessaging.MESSAGE_TYPE_DELETED)) {
                            i = 1;
                            break;
                        }
                        break;
                    case 102161:
                        if (messageType.equals(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE)) {
                            i = 2;
                            break;
                        }
                        break;
                    case 814694033:
                        if (messageType.equals(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR)) {
                            i = 0;
                            break;
                        }
                        break;
                }
                switch (i) {
                    case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        OLog.m13313b("Ignoring message type" + messageType, new Object[0]);
                        break;
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        if (!getString(com.olacabs.customer.R.string.gcm_sender_id).equals(extras.getString("from"))) {
                            m12872a(intent);
                            break;
                        } else {
                            m12873a(extras);
                            break;
                        }
                    default:
                        break;
                }
            }
        }
        String stringExtra = intent.getStringExtra(da.GCM_REG_ID_KEY);
        OLog.m13313b("ACTION_REGISTRATION received \n\n" + stringExtra, new Object[0]);
        if (Utils.m14924g(stringExtra)) {
            m12874a(stringExtra);
        }
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void m12874a(String str) {
        if (!TextUtils.isEmpty(str)) {
        }
    }

    private void m12872a(Intent intent) {
        if (intent.getExtras() != null && !intent.getExtras().containsKey("ll")) {
        }
    }

    private void m12873a(Bundle bundle) {
        String string = bundle.getString("ver");
        OLog.m13313b("Version " + string, new Object[0]);
        if (Utils.m14924g(string) && TextUtils.isDigitsOnly(string)) {
            switch (Integer.parseInt(string)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    m12876b(bundle);
                    break;
                default:
                    m12876b(bundle);
                    break;
            }
        }
        OLog.m13315c("Received: " + bundle.toString(), new Object[0]);
    }

    private void m12876b(Bundle bundle) {
        String string = bundle.getString("tl");
        String string2 = bundle.getString(NotificationCompatApi21.CATEGORY_MESSAGE);
        String string3 = bundle.getString("rId");
        String string4 = bundle.getString("tgt");
        String string5 = bundle.getString("bId");
        if (Utils.m14924g(string) && Utils.m14924g(string2)) {
            Intent intent = new Intent(this, SplashActivity.class);
            intent.putExtra(Constants.PUSH_MESSAGE, true);
            intent.putExtra(Constants.PUSH_LANDING, string4);
            intent.putExtra(Constants.PUSH_BOOKING_ID, string5);
            intent.putExtra(Constants.PUSH_REQUEST_ID, string3);
            intent.setFlags(536870912);
            m12875a(string, string2, intent);
            DataManager.m13137a(getApplicationContext()).m13229e(null, string3, Constants.PUSH_ACK_TYPE_DELI, String.valueOf(System.currentTimeMillis()), f8966a);
        }
    }

    private void m12875a(String str, String str2, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 134217728);
        Builder contentText = new Builder(this).setSmallIcon(com.olacabs.customer.R.drawable.ola_push).setLargeIcon(BitmapFactoryInstrumentation.decodeResource(getResources(), com.olacabs.customer.R.drawable.ola_icon)).setContentTitle(str).setTicker(str).setStyle(new BigTextStyle().bigText(str2)).setCategory(NotificationCompatApi21.CATEGORY_MESSAGE).setVisibility(1).setPriority(2).setContentText(str2);
        contentText.setContentIntent(activity);
        Notification build = contentText.build();
        build.defaults = 5;
        build.ledARGB = -256;
        build.flags = 17;
        notificationManager.notify(1, build);
    }
}
