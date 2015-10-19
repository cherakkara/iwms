package com.olacabs.customer.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.android.volley.VolleyError;
import com.newrelic.agent.android.NewRelic;
import com.olacabs.customer.R;
import com.olacabs.customer.p076d.ao;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import java.util.HashMap;
import org.apache.http.HttpHeaders;
import org.apache.http.protocol.HTTP;

/* renamed from: com.olacabs.customer.app.p */
public class Sherlock {
    private static HashMap<String, Long> f9395a;
    private static HashMap<String, String> f9396b;
    private static ConnectivityManager f9397c;
    private static TelephonyManager f9398d;
    private static Context f9399e;

    static {
        f9395a = new HashMap();
        f9396b = new HashMap();
    }

    public static void m13333a(Context context) {
        f9397c = (ConnectivityManager) context.getSystemService("connectivity");
        f9398d = (TelephonyManager) context.getSystemService(Constants.PHONE);
        f9399e = context;
        Sherlock.m13343b();
        Sherlock.m13344b(context);
        Sherlock.m13332a();
    }

    private static void m13332a() {
        f9396b.put("Play Services Version", String.valueOf(f9399e.getResources().getInteger(R.integer.google_play_services_version)));
    }

    public static void m13344b(Context context) {
        dt instance = dt.getInstance(context);
        f9396b.put(HttpHeaders.LOCATION, Sherlock.m13349d(instance.getCity()));
        f9396b.put("User ID", Sherlock.m13349d(instance.getUserId()));
        f9396b.put("Session ID", da.getInstance(context).getSessionId());
    }

    private static void m13343b() {
        f9396b.put("Brand", Build.BRAND);
        f9396b.put("Model", Build.MODEL);
        f9396b.put("Manufacturer", Build.MANUFACTURER);
        f9396b.put("Android Version", ao.getAndroidVersion());
    }

    public static void m13334a(String str) {
        f9395a.put(str, Long.valueOf(System.currentTimeMillis()));
    }

    public static void m13345b(String str) {
        Sherlock.m13338a(str, Constants.SOS_SUCCESS_HEADER_TEXT, null, null, false, null);
    }

    public static void m13347c(String str) {
        f9395a.remove(str);
    }

    public static void m13340a(String str, HashMap hashMap) {
        Sherlock.m13338a(str, Constants.SOS_SUCCESS_HEADER_TEXT, null, null, false, hashMap);
    }

    public static void m13342a(String str, HashMap hashMap, VolleyError volleyError) {
        Sherlock.m13338a(str, "Failure", volleyError, "NA", false, hashMap);
    }

    public static void m13337a(String str, VolleyError volleyError, boolean z) {
        Sherlock.m13338a(str, "Failure", volleyError, "NA", z, null);
    }

    public static void m13335a(String str, VolleyError volleyError) {
        Sherlock.m13338a(str, "Failure", volleyError, "NA", false, null);
    }

    public static void m13336a(String str, VolleyError volleyError, String str2, boolean z) {
        Sherlock.m13338a(str, "Failure", volleyError, str2, z, null);
    }

    public static void m13338a(String str, String str2, VolleyError volleyError, String str3, boolean z, HashMap hashMap) {
        Sherlock.m13339a(str, str2, volleyError, str3, z, hashMap, -2147483648L);
    }

    public static void m13339a(String str, String str2, VolleyError volleyError, String str3, boolean z, HashMap hashMap, long j) {
        Long l = (Long) f9395a.get(str);
        if (l != null) {
            float a;
            f9395a.remove(str);
            if (j == -2147483648L) {
                a = Sherlock.m13331a(l.longValue(), System.currentTimeMillis());
            } else {
                a = Sherlock.m13331a(l.longValue(), j);
            }
            f9396b.put("Result", Sherlock.m13349d(str2));
            if (volleyError == null || volleyError.f464a == null) {
                f9396b.put("Error Code", "NA");
            } else {
                f9396b.put("Error Code", Sherlock.m13349d(String.valueOf(volleyError.f464a.f497a)));
            }
            f9396b.put("Error Reason", Sherlock.m13349d(str3));
            f9396b.put("Popup Displayed", String.valueOf(z));
            if (hashMap != null) {
                hashMap.putAll(f9396b);
                Sherlock.m13341a(str, hashMap, a);
                return;
            }
            Sherlock.m13341a(str, new HashMap(f9396b), a);
            return;
        }
        OLog.m13317d("Start call is missing for %s", str);
    }

    private static String m13346c() {
        if (f9397c == null || f9397c.getActiveNetworkInfo() == null) {
            return "NA";
        }
        if (f9397c.getActiveNetworkInfo().getType() == 0) {
            return Sherlock.m13348d();
        }
        return f9397c.getActiveNetworkInfo().getTypeName();
    }

    private static String m13348d() {
        if (f9398d == null) {
            return "NA";
        }
        int networkType = f9398d.getNetworkType();
        switch (networkType) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
            case R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                return "2G";
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoDragView /*5*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoOverlay /*6*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
            case HTTP.HT /*9*/:
            case HTTP.LF /*10*/:
            case R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
            case R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
            case R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                return "3G";
            case HTTP.CR /*13*/:
                return "4G";
            default:
                return "Mobile Nw - " + networkType;
        }
    }

    private static float m13331a(long j, long j2) {
        return ((float) (j2 - j)) / 1000.0f;
    }

    public static String m13349d(String str) {
        return Utils.m14924g(str) ? str : "NA";
    }

    private static void m13341a(String str, HashMap<String, Object> hashMap, float f) {
        OLog.m13313b("for " + str + " event duration is : " + f + " seconds", new Object[0]);
        hashMap.put("Network Type", Sherlock.m13346c());
        hashMap.put("Duration", Float.valueOf(f));
        NewRelic.recordEvent(str, hashMap);
    }
}
