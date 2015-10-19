package com.olacabs.customer.p076d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.olacabs.customer.utils.Utils;
import p000a.p001a.p002a.EventBus;

/* compiled from: DataConnectivityChangesReceiver */
/* renamed from: com.olacabs.customer.d.ag */
public class ag extends BroadcastReceiver {
    private boolean mLastState;

    public ag(boolean z) {
        this.mLastState = z;
    }

    public void onReceive(Context context, Intent intent) {
        boolean a = Utils.m14909a(context);
        if (this.mLastState != a) {
            this.mLastState = a;
            EventBus.m3a().m18c(new ah(a));
        }
    }
}
