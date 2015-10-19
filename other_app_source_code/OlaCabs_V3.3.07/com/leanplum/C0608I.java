package com.leanplum;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;

/* renamed from: com.leanplum.I */
final class C0608I implements Runnable {
    private final /* synthetic */ Context f8553a;

    C0608I(C0605F c0605f, Context context) {
        this.f8553a = context;
    }

    public final void run() {
        try {
            Builder contentText = new Builder(this.f8553a).setSmallIcon(17301620).setContentTitle("Leanplum").setContentText("Your device is registered.");
            contentText.setContentIntent(PendingIntent.getActivity(this.f8553a.getApplicationContext(), 0, new Intent(), 0));
            ((NotificationManager) this.f8553a.getSystemService("notification")).notify(0, contentText.build());
        } catch (Exception e) {
            Log.i("Leanplum", "Device is registered.");
        }
    }
}
