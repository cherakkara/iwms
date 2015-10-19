package com.olacabs.customer.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import com.crashlytics.android.Crashlytics;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumResources;
import com.leanplum.annotations.Parser;
import com.localytics.android.LocalyticsActivityLifecycleCallbacks;
import com.olacabs.customer.p075a.AnalyticsManager;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.p076d.ao;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.utils.p083a.ApplicationMode;
import com.olacabs.p067a.p068a.p069a.p074d.MegatronCore;
import p004b.p005a.p006a.p007a.Fabric;

public class OlaApp extends Application {
    public static Context f8967a;
    private DataManager f8968b;
    private AnalyticsManager f8969c;

    public void onCreate() {
        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);
        super.onCreate();
        m12877d();
        OLog.m13311a("App started", new Object[0]);
        f8967a = this;
        m12880c();
    }

    public synchronized DataManager m12878a() {
        return this.f8968b;
    }

    public synchronized AnalyticsManager m12879b() {
        return this.f8969c;
    }

    public void onTerminate() {
        if (this.f8968b != null) {
            this.f8968b.m13230f();
        }
        if (this.f8969c != null) {
            this.f8969c.m12869c();
        }
    }

    public void m12880c() {
        MegatronCore.m12842e().m12845a(getApplicationContext(), "https://rtevents.olacabs.com/megatron/v1/document/consumer_apps/bulk");
        this.f8968b = DataManager.m13137a((Context) this);
        this.f8968b.m13163a();
        this.f8969c = AnalyticsManager.m12864a((Context) this);
        this.f8969c.m12865a();
        registerActivityLifecycleCallbacks(new LocalyticsActivityLifecycleCallbacks(this));
    }

    private void m12877d() {
        if (AppInfo.sRunningMode != ApplicationMode.DEV) {
            Fabric.m503a((Context) this, new Crashlytics());
            Crashlytics.setString("GIT SHA", "b2fd669");
            Crashlytics.setString("HW (Brand - Model - Manufacturer)", Build.BRAND + " - " + Build.MODEL + " - " + Build.MANUFACTURER);
            Crashlytics.setString("Android Version :", ao.getAndroidVersion());
            if (AppInfo.sRunningMode == ApplicationMode.QA) {
                dt instance = dt.getInstance(this);
                Crashlytics.setUserEmail(instance.getUserLoginEmail());
                Crashlytics.setUserIdentifier(instance.getPhoneNumber());
                Crashlytics.setUserName(instance.getName());
            }
        }
    }

    public Resources getResources() {
        return new LeanplumResources(super.getResources());
    }
}
