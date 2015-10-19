package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Parcelable;
import android.os.Process;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.google.android.gms.common.internal.C0418g;
import com.google.android.gms.internal.C0511p.C0510a;
import com.newrelic.agent.android.api.v1.Defaults;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.google.android.gms.internal.o */
public class C0509o {
    private static final Object f2416a;
    private static C0509o f2417b;
    private static final ComponentName f2418g;
    private final List<String> f2419c;
    private final List<String> f2420d;
    private final List<String> f2421e;
    private final List<String> f2422f;
    private C0513r f2423h;

    static {
        f2416a = new Object();
        f2418g = new ComponentName("com.google.android.gms", "com.google.android.gms.common.stats.GmsCoreStatsService");
    }

    private C0509o() {
        if (m4148b() == C0512q.f2431a) {
            this.f2419c = Collections.EMPTY_LIST;
            this.f2420d = Collections.EMPTY_LIST;
            this.f2421e = Collections.EMPTY_LIST;
            this.f2422f = Collections.EMPTY_LIST;
            return;
        }
        String str = (String) C0510a.f2426c.m3884c();
        this.f2419c = str == null ? Collections.EMPTY_LIST : Arrays.asList(str.split(","));
        str = (String) C0510a.f2427d.m3884c();
        this.f2420d = str == null ? Collections.EMPTY_LIST : Arrays.asList(str.split(","));
        str = (String) C0510a.f2428e.m3884c();
        this.f2421e = str == null ? Collections.EMPTY_LIST : Arrays.asList(str.split(","));
        str = (String) C0510a.f2429f.m3884c();
        this.f2422f = str == null ? Collections.EMPTY_LIST : Arrays.asList(str.split(","));
        this.f2423h = new C0513r(Defaults.RESPONSE_BODY_LIMIT, ((Long) C0510a.f2430g.m3884c()).longValue());
    }

    private long m4143a(ServiceConnection serviceConnection) {
        return (((long) Process.myPid()) << 32) | ((long) System.identityHashCode(serviceConnection));
    }

    private static ServiceInfo m4144a(Context context, Intent intent) {
        List queryIntentServices = context.getPackageManager().queryIntentServices(intent, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            Log.w("ConnectionTracker", "There are no handler of this intent: " + intent.toUri(0));
            return null;
        }
        if (queryIntentServices.size() > 1) {
            Log.w("ConnectionTracker", "There are multiple handlers for this intent: " + intent.toUri(0));
            Iterator it = queryIntentServices.iterator();
            if (it.hasNext()) {
                Log.w("ConnectionTracker", ((ResolveInfo) it.next()).serviceInfo.name);
                return null;
            }
        }
        return ((ResolveInfo) queryIntentServices.get(0)).serviceInfo;
    }

    public static C0509o m4145a() {
        synchronized (f2416a) {
            if (f2417b == null) {
                f2417b = new C0509o();
            }
        }
        return f2417b;
    }

    private void m4146a(Context context, ServiceConnection serviceConnection, String str, Intent intent, String str2) {
        if (C0418g.f2234a) {
            long a = m4143a(serviceConnection);
            if (m4147a(context, str, intent, a, str2)) {
                Parcelable com_google_android_gms_internal_zzlm;
                long currentTimeMillis = System.currentTimeMillis();
                String a2 = C0520y.m4177a(3, 5);
                if (!str2.equals("UNBIND")) {
                    if (!str2.equals("DISCONNECT")) {
                        ServiceInfo a3 = C0509o.m4144a(context, intent);
                        String str3 = str2;
                        String str4 = str;
                        com_google_android_gms_internal_zzlm = new zzlm(currentTimeMillis, str3, C0520y.m4178a(context), str4, a3.processName, a3.name, a2, a);
                        context.startService(new Intent().setComponent(f2418g).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", com_google_android_gms_internal_zzlm));
                    }
                }
                com_google_android_gms_internal_zzlm = new zzlm(currentTimeMillis, str2, null, null, null, null, a2, a);
                context.startService(new Intent().setComponent(f2418g).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", com_google_android_gms_internal_zzlm));
            }
        }
    }

    private boolean m4147a(Context context, String str, Intent intent, long j, String str2) {
        int b = m4148b();
        if (b == C0512q.f2431a || this.f2423h == null) {
            return false;
        }
        if (str2 == "DISCONNECT" || str2 == "UNBIND") {
            return this.f2423h.m4155a(j);
        } else {
            ServiceInfo a = C0509o.m4144a(context, intent);
            if (a == null) {
                return false;
            }
            String a2 = C0520y.m4178a(context);
            String str3 = a.processName;
            String str4 = a.name;
            if (this.f2419c.contains(a2) || this.f2420d.contains(str) || this.f2421e.contains(str3) || this.f2422f.contains(str4)) {
                return false;
            }
            if (str3.equals(a2) && (b & C0512q.f2435e) != 0) {
                return false;
            }
            this.f2423h.m4154a(Long.valueOf(j));
            return true;
        }
    }

    private int m4148b() {
        try {
            return (C0418g.f2234a && C0457a.m3881b() && C0457a.m3876a() == Process.myUid()) ? ((Integer) C0510a.f2425b.m3884c()).intValue() : C0512q.f2431a;
        } catch (SecurityException e) {
            return C0512q.f2431a;
        }
    }

    public void m4149a(Context context, ServiceConnection serviceConnection) {
        m4146a(context, serviceConnection, null, null, "UNBIND");
        context.unbindService(serviceConnection);
    }

    public void m4150a(Context context, ServiceConnection serviceConnection, String str, Intent intent) {
        m4146a(context, serviceConnection, str, intent, "CONNECT");
    }

    public boolean m4151a(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i) {
        m4146a(context, serviceConnection, str, intent, "BIND");
        return context.bindService(intent, serviceConnection, i);
    }

    public void m4152b(Context context, ServiceConnection serviceConnection) {
        m4146a(context, serviceConnection, null, null, "DISCONNECT");
    }
}
