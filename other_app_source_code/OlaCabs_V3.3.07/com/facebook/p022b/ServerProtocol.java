package com.facebook.p022b;

import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.olacabs.customer.p076d.AppInfo;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.b.r */
public final class ServerProtocol {
    public static final Collection<String> f836a;
    public static final Collection<String> f837b;
    private static final String f838c;

    static {
        f838c = ServerProtocol.class.getName();
        f836a = Utility.m1106a("service_disabled", "AndroidAuthKillSwitchException");
        f837b = Utility.m1106a("access_denied", "OAuthAccessDeniedException");
    }

    public static final String m1070a() {
        return String.format("m.%s", new Object[]{FacebookSdk.m1207e()});
    }

    public static final String m1071b() {
        return String.format("https://graph.%s", new Object[]{FacebookSdk.m1207e()});
    }

    public static final String m1072c() {
        return String.format("https://graph-video.%s", new Object[]{FacebookSdk.m1207e()});
    }

    public static final String m1073d() {
        return "v2.3";
    }

    public static Bundle m1069a(String str, int i, Bundle bundle) {
        String d = FacebookSdk.m1205d(FacebookSdk.m1208f());
        if (Utility.m1126a(d)) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("android_key_hash", d);
        bundle2.putString("app_id", FacebookSdk.m1210h());
        bundle2.putInt(AppInfo.APP_VERSION_KEY, i);
        bundle2.putString("display", "touch");
        Bundle bundle3 = new Bundle();
        bundle3.putString("action_id", str);
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            JSONObject a = BundleJSONConverter.m907a(bundle3);
            JSONObject a2 = BundleJSONConverter.m907a(bundle);
            if (a == null || a2 == null) {
                return null;
            }
            bundle2.putString("bridge_args", !(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a));
            bundle2.putString("method_args", !(a2 instanceof JSONObject) ? a2.toString() : JSONObjectInstrumentation.toString(a2));
            bundle3 = bundle2;
            return bundle3;
        } catch (JSONException e) {
            Logger.m998a(LoggingBehavior.DEVELOPER_ERRORS, 6, f838c, "Error creating Url -- " + e);
            bundle3 = null;
        }
    }
}
