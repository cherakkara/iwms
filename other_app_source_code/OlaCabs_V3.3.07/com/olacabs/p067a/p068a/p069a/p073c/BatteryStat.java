package com.olacabs.p067a.p068a.p069a.p073c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p070a.BatteryInfo;
import com.olacabs.p067a.p068a.p069a.p074d.MegatronCore;
import java.util.ArrayList;

/* renamed from: com.olacabs.a.a.a.c.b */
public class BatteryStat extends CountDownTimer {
    private Context f8914a;
    private String f8915b;
    private ArrayList<BatteryInfo> f8916c;

    /* renamed from: com.olacabs.a.a.a.c.b.1 */
    class BatteryStat extends BroadcastReceiver {
        final /* synthetic */ BatteryStat f8913a;

        BatteryStat(BatteryStat batteryStat) {
            this.f8913a = batteryStat;
        }

        public void onReceive(Context context, Intent intent) {
            context.unregisterReceiver(this);
            int intExtra = intent.getIntExtra("level", -1);
            int intExtra2 = intent.getIntExtra("scale", -1);
            if (intExtra >= 0 && intExtra2 > 0) {
                double d = (double) ((intExtra * 100) / intExtra2);
                BatteryInfo batteryInfo = new BatteryInfo();
                batteryInfo.setPercentage(d);
                batteryInfo.setTimestamp(System.currentTimeMillis());
                MegatronCore.m12842e().m12846a(batteryInfo);
                this.f8913a.f8916c.add(batteryInfo);
            }
        }
    }

    public BatteryStat(Context context, String str) {
        super(3000000, Constants.MILLIS_IN_A_MINUTE);
        this.f8916c = new ArrayList();
        this.f8914a = context;
        this.f8915b = str;
        start();
    }

    public void onTick(long j) {
        m12830a(j);
    }

    public void onFinish() {
    }

    private void m12830a(long j) {
        this.f8914a.registerReceiver(new BatteryStat(this), new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }
}
