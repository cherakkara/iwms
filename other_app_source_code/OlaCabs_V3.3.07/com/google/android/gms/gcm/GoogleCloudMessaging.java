package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GoogleCloudMessaging {
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
    public static final String MESSAGE_TYPE_MESSAGE = "gcm";
    public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
    public static final String REGISTRATION_ID = "registration_id";
    private static String zzakT;
    public static int zzakU;
    public static int zzakV;
    public static int zzakW;
    static GoogleCloudMessaging zzakX;
    private static final AtomicInteger zzala;
    private PendingIntent zzakY;
    private Map<String, Handler> zzakZ;
    private final BlockingQueue<Intent> zzalb;
    final Messenger zzalc;
    private Context zzlN;

    /* renamed from: com.google.android.gms.gcm.GoogleCloudMessaging.1 */
    class C04551 extends Handler {
        final /* synthetic */ GoogleCloudMessaging zzald;

        C04551(GoogleCloudMessaging googleCloudMessaging, Looper looper) {
            this.zzald = googleCloudMessaging;
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message == null || !(message.obj instanceof Intent)) {
                Log.w("GCM", "Dropping invalid message");
            }
            Intent intent = (Intent) message.obj;
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
                this.zzald.zzalb.add(intent);
            } else if (!this.zzald.zzj(intent)) {
                intent.setPackage(this.zzald.zzlN.getPackageName());
                this.zzald.zzlN.sendBroadcast(intent);
            }
        }
    }

    static {
        zzakT = null;
        zzakU = 5000000;
        zzakV = 6500000;
        zzakW = 7000000;
        zzala = new AtomicInteger(1);
    }

    public GoogleCloudMessaging() {
        this.zzalb = new LinkedBlockingQueue();
        this.zzakZ = Collections.synchronizedMap(new HashMap());
        this.zzalc = new Messenger(new C04551(this, Looper.getMainLooper()));
    }

    public static synchronized GoogleCloudMessaging getInstance(Context context) {
        GoogleCloudMessaging googleCloudMessaging;
        synchronized (GoogleCloudMessaging.class) {
            if (zzakX == null) {
                zzakX = new GoogleCloudMessaging();
                zzakX.zzlN = context.getApplicationContext();
            }
            googleCloudMessaging = zzakX;
        }
        return googleCloudMessaging;
    }

    public static String zzZ(Context context) {
        if (zzakT == null) {
            zzakT = "com.google.android.gms";
        }
        return zzakT;
    }

    private String zza(Intent intent, String str) throws IOException {
        if (intent == null) {
            throw new IOException(ERROR_SERVICE_NOT_AVAILABLE);
        }
        String stringExtra = intent.getStringExtra(str);
        if (stringExtra != null) {
            return stringExtra;
        }
        stringExtra = intent.getStringExtra("error");
        if (stringExtra != null) {
            throw new IOException(stringExtra);
        }
        throw new IOException(ERROR_SERVICE_NOT_AVAILABLE);
    }

    private void zza(String str, String str2, long j, int i, Bundle bundle) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        zzk(intent);
        intent.setPackage(zzZ(this.zzlN));
        intent.putExtra("google.to", str);
        intent.putExtra("google.message_id", str2);
        intent.putExtra("google.ttl", Long.toString(j));
        intent.putExtra("google.delay", Integer.toString(i));
        this.zzlN.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    public static int zzaa(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(zzZ(context), 0).versionCode;
        } catch (NameNotFoundException e) {
            return -1;
        }
    }

    private boolean zzj(Intent intent) {
        Object stringExtra = intent.getStringExtra("In-Reply-To");
        if (stringExtra == null && intent.hasExtra("error")) {
            stringExtra = intent.getStringExtra("google.message_id");
        }
        if (stringExtra != null) {
            Handler handler = (Handler) this.zzakZ.remove(stringExtra);
            if (handler != null) {
                Message obtain = Message.obtain();
                obtain.obj = intent;
                return handler.sendMessage(obtain);
            }
        }
        return false;
    }

    private String zzpd() {
        return "google.rpc" + String.valueOf(zzala.getAndIncrement());
    }

    private Intent zzt(Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException(ERROR_MAIN_THREAD);
        } else if (zzaa(this.zzlN) < 0) {
            throw new IOException("Google Play Services missing");
        } else {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage(zzZ(this.zzlN));
            zzk(intent);
            intent.putExtra("google.message_id", zzpd());
            intent.putExtras(bundle);
            intent.putExtra("google.messenger", this.zzalc);
            this.zzlN.startService(intent);
            try {
                return (Intent) this.zzalb.poll(30000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public void close() {
        zzpe();
    }

    public String getMessageType(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            return null;
        }
        String stringExtra = intent.getStringExtra("message_type");
        return stringExtra == null ? MESSAGE_TYPE_MESSAGE : stringExtra;
    }

    public synchronized String register(String... strArr) throws IOException {
        Bundle bundle;
        String zzd = zzd(strArr);
        bundle = new Bundle();
        bundle.putString("sender", zzd);
        return zza(zzt(bundle), REGISTRATION_ID);
    }

    public void send(String str, String str2, long j, Bundle bundle) throws IOException {
        zza(str, str2, j, -1, bundle);
    }

    public void send(String str, String str2, Bundle bundle) throws IOException {
        send(str, str2, -1, bundle);
    }

    public synchronized void unregister() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException(ERROR_MAIN_THREAD);
        }
        Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        intent.setPackage(zzZ(this.zzlN));
        intent.putExtra("google.messenger", this.zzalc);
        zzk(intent);
        this.zzlN.startService(intent);
        try {
            zza((Intent) this.zzalb.poll(30000, TimeUnit.MILLISECONDS), "unregistered");
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        }
    }

    String zzd(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        StringBuilder stringBuilder = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            stringBuilder.append(',').append(strArr[i]);
        }
        return stringBuilder.toString();
    }

    synchronized void zzk(Intent intent) {
        if (this.zzakY == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.zzakY = PendingIntent.getBroadcast(this.zzlN, 0, intent2, 0);
        }
        intent.putExtra("app", this.zzakY);
    }

    synchronized void zzpe() {
        if (this.zzakY != null) {
            this.zzakY.cancel();
            this.zzakY = null;
        }
    }
}
