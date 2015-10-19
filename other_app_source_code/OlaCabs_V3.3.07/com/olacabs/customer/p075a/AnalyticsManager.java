package com.olacabs.customer.p075a;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import com.android.volley.Request;
import com.android.volley.Response.Response;
import com.android.volley.VolleyError;
import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.newrelic.agent.android.NewRelic;
import com.olacabs.customer.R;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.p076d.ao;
import com.olacabs.customer.p076d.by;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.utils.BackgroundLooper;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.p083a.ApplicationMode;
import org.json.JSONObject;
import p004b.p005a.p006a.p007a.Fabric;

/* renamed from: com.olacabs.customer.a.b */
public class AnalyticsManager {
    private static final String f8960a;
    private static AnalyticsManager f8961c;
    private Context f8962b;
    private Handler f8963d;

    /* renamed from: com.olacabs.customer.a.b.1 */
    class AnalyticsManager implements Runnable {
        final /* synthetic */ AnalyticsManager f8956a;

        AnalyticsManager(AnalyticsManager analyticsManager) {
            this.f8956a = analyticsManager;
        }

        public void run() {
            if (AppInfo.sRunningMode != ApplicationMode.DEV) {
                Fabric.m503a(this.f8956a.f8962b, new Crashlytics());
                Crashlytics.setString("GIT SHA", "b2fd669");
                Crashlytics.setString("HW (Brand - Model - Manufacturer)", Build.BRAND + " - " + Build.MODEL + " - " + Build.MANUFACTURER);
                Crashlytics.setString("Android Version :", ao.getAndroidVersion());
                if (AppInfo.sRunningMode == ApplicationMode.QA) {
                    dt instance = dt.getInstance(this.f8956a.f8962b);
                    if (instance != null) {
                        Crashlytics.setUserEmail(instance.getUserLoginEmail());
                        Crashlytics.setUserIdentifier(instance.getPhoneNumber());
                        Crashlytics.setUserName(instance.getName());
                    }
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.a.b.2 */
    class AnalyticsManager implements Runnable {
        final /* synthetic */ AnalyticsManager f8957a;

        AnalyticsManager(AnalyticsManager analyticsManager) {
            this.f8957a = analyticsManager;
        }

        public void run() {
            NewRelic.withApplicationToken(this.f8957a.f8962b.getString(R.string.new_relic_key)).withCrashReportingEnabled(false).withLoggingEnabled(false).start(this.f8957a.f8962b);
            OLog.m13311a("Delayed - Initilization - AnalyticsManager", new Object[0]);
        }
    }

    /* renamed from: com.olacabs.customer.a.b.3 */
    class AnalyticsManager implements Response<JSONObject> {
        final /* synthetic */ AnalyticsManager f8958a;

        AnalyticsManager(AnalyticsManager analyticsManager) {
            this.f8958a = analyticsManager;
        }

        public void m12861a(JSONObject jSONObject) {
            OLog.m13311a("v3/analytics/deep_link_localytics returned a response", new Object[0]);
        }
    }

    /* renamed from: com.olacabs.customer.a.b.4 */
    class AnalyticsManager implements Response {
        final /* synthetic */ AnalyticsManager f8959a;

        AnalyticsManager(AnalyticsManager analyticsManager) {
            this.f8959a = analyticsManager;
        }

        public void m12862a(VolleyError volleyError) {
            OLog.m13311a("v3/analytics/deep_link_localytics returned an error", volleyError);
        }
    }

    static {
        f8960a = AnalyticsManager.class.getSimpleName();
    }

    private AnalyticsManager(Context context) {
        this.f8963d = new Handler(BackgroundLooper.m14896a());
        this.f8962b = context;
    }

    public static AnalyticsManager m12864a(Context context) {
        if (f8961c == null) {
            f8961c = new AnalyticsManager(context);
        }
        return f8961c;
    }

    public void m12865a() {
        Sherlock.m13333a(this.f8962b);
        this.f8963d.post(new AnalyticsManager(this));
    }

    public void m12868b() {
        FacebookSdk.m1197a(this.f8962b);
        this.f8963d.post(new AnalyticsManager(this));
    }

    public AnalyticsHelper m12867b(Context context) {
        return new FBAnalyticsHelper(context);
    }

    public void m12866a(String str) {
        OLog.m13311a("reportDeepLinkingData", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f8962b.getApplicationContext();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.USER_ID, olaApp.m12878a().m13209c().getUserId());
            jSONObject.put("source_name", str);
        } catch (Throwable e) {
            OLog.m13310a("Failed to create params - ", e);
        }
        Request byVar = new by(olaApp, 1, "https://apps.olacabs.com/v3/analytics/deep_link_localytics", jSONObject, Request.Request.LOW, new AnalyticsManager(this), new AnalyticsManager(this));
        byVar.setTag(f8960a);
        olaApp.m12878a().m13168a(byVar);
    }

    public void m12869c() {
    }
}
