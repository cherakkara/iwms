package com.facebook.p023a;

import android.os.Bundle;
import com.facebook.LoggingBehavior;
import com.facebook.p022b.Logger;
import com.olacabs.customer.utils.Constants;
import java.io.Serializable;
import java.util.Locale;

/* renamed from: com.facebook.a.b */
class FacebookTimeSpentData implements Serializable {
    private static final String f709a;
    private static final long[] f710b;
    private boolean f711c;
    private boolean f712d;
    private long f713e;
    private long f714f;
    private long f715g;
    private long f716h;
    private int f717i;
    private String f718j;

    static {
        f709a = AppEventsLogger.class.getCanonicalName();
        f710b = new long[]{300000, 900000, 1800000, Constants.MILLIS_IN_AN_HOUR, 21600000, 43200000, 86400000, 172800000, 259200000, 604800000, 1209600000, 1814400000, 2419200000L, 5184000000L, 7776000000L, 10368000000L, 12960000000L, 15552000000L, 31536000000L};
    }

    FacebookTimeSpentData() {
        m858a();
    }

    void m862a(AppEventsLogger appEventsLogger, long j, String str) {
        long j2 = 0;
        if (m861c() || j - this.f713e > 300000) {
            Bundle bundle = new Bundle();
            bundle.putString("fb_mobile_launch_source", str);
            appEventsLogger.m855a("fb_mobile_activate_app", bundle);
            this.f713e = j;
        }
        if (this.f712d) {
            Logger.m999a(LoggingBehavior.APP_EVENTS, f709a, "Resume for active app");
            return;
        }
        long j3 = m860b() ? j - this.f715g : 0;
        if (j3 < 0) {
            Logger.m999a(LoggingBehavior.APP_EVENTS, f709a, "Clock skew detected");
        } else {
            j2 = j3;
        }
        if (j2 > Constants.MILLIS_IN_A_MINUTE) {
            m859a(appEventsLogger, j2);
        } else if (j2 > 1000) {
            this.f717i++;
        }
        if (this.f717i == 0) {
            this.f718j = str;
        }
        this.f714f = j;
        this.f712d = true;
    }

    private void m859a(AppEventsLogger appEventsLogger, long j) {
        Bundle bundle = new Bundle();
        bundle.putInt("fb_mobile_app_interruptions", this.f717i);
        bundle.putString("fb_mobile_time_between_sessions", String.format(Locale.ROOT, "session_quanta_%d", new Object[]{Integer.valueOf(FacebookTimeSpentData.m857a(j))}));
        bundle.putString("fb_mobile_launch_source", this.f718j);
        appEventsLogger.m854a("fb_mobile_deactivate_app", (double) (this.f716h / 1000), bundle);
        m858a();
    }

    private static int m857a(long j) {
        int i = 0;
        while (i < f710b.length && f710b[i] < j) {
            i++;
        }
        return i;
    }

    private void m858a() {
        this.f712d = false;
        this.f714f = -1;
        this.f715g = -1;
        this.f717i = 0;
        this.f716h = 0;
    }

    private boolean m860b() {
        return this.f715g != -1;
    }

    private boolean m861c() {
        boolean z = !this.f711c;
        this.f711c = true;
        return z;
    }
}
