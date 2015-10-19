package com.payu.sdk;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.payu.p084a.Bank;
import com.payu.sdk.R.R;
import java.util.Set;

/* renamed from: com.payu.sdk.i */
class C0931i extends Bank {
    final /* synthetic */ ProcessPaymentActivity f11568a;

    C0931i(ProcessPaymentActivity processPaymentActivity) {
        this.f11568a = processPaymentActivity;
    }

    public void communicationError() {
        this.f11568a.m14936a(8);
    }

    public void onBankError() {
        this.f11568a.m14936a(8);
        this.f11568a.findViewById(R.parent).setVisibility(8);
        this.f11568a.findViewById(R.trans_overlay).setVisibility(8);
    }

    public void onHelpAvailable() {
        this.f11568a.findViewById(R.parent).setVisibility(0);
    }

    public void onHelpUnavailable() {
        this.f11568a.findViewById(R.parent).setVisibility(8);
        this.f11568a.findViewById(R.trans_overlay).setVisibility(8);
    }

    public void registerBroadcast(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        this.f11568a.f11514d = broadcastReceiver;
        this.f11568a.registerReceiver(broadcastReceiver, intentFilter);
    }

    public void unregisterBroadcast(BroadcastReceiver broadcastReceiver) {
        if (this.f11568a.f11514d != null) {
            this.f11568a.unregisterReceiver(this.f11568a.f11514d);
            this.f11568a.f11514d = null;
        }
    }

    public void updateSet(Set<String> set, String str) {
        if (!(set == null || set.size() <= 0 || this.f11568a.f11519i == null || set.contains(this.f11568a.f11519i))) {
            this.f11568a.m14936a(8);
        }
        this.f11568a.f11518h = set;
        this.f11568a.f11516f = str;
    }
}
