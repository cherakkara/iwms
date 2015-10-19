package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import com.facebook.login.LoginClient.Result.C0173a;
import com.facebook.p023a.AppEventsLogger;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.facebook.login.e */
class LoginLogger {
    private final AppEventsLogger f1008a;
    private String f1009b;

    LoginLogger(Context context, String str) {
        this.f1009b = str;
        this.f1008a = AppEventsLogger.m831a(context, str);
    }

    public String m1320a() {
        return this.f1009b;
    }

    static Bundle m1319a(String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        bundle.putString("0_auth_logger_id", str);
        bundle.putString("3_method", Trace.NULL);
        bundle.putString("2_result", Trace.NULL);
        bundle.putString("5_error_message", Trace.NULL);
        bundle.putString("4_error_code", Trace.NULL);
        bundle.putString("6_extras", Trace.NULL);
        return bundle;
    }

    public void m1321a(String str, String str2) {
        Bundle a = LoginLogger.m1319a(str);
        a.putString("3_method", str2);
        this.f1008a.m856a("fb_mobile_login_method_start", null, a);
    }

    public void m1323a(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        Bundle a = LoginLogger.m1319a(str);
        if (str3 != null) {
            a.putString("2_result", str3);
        }
        if (str4 != null) {
            a.putString("5_error_message", str4);
        }
        if (str5 != null) {
            a.putString("4_error_code", str5);
        }
        if (!(map == null || map.isEmpty())) {
            JSONObject jSONObject = new JSONObject(map);
            a.putString("6_extras", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
        }
        a.putString("3_method", str2);
        this.f1008a.m856a("fb_mobile_login_method_complete", null, a);
    }

    public void m1322a(String str, String str2, String str3) {
        Bundle a = LoginLogger.m1319a(Trace.NULL);
        a.putString("2_result", C0173a.ERROR.m1259a());
        a.putString("5_error_message", str2);
        a.putString("3_method", str3);
        this.f1008a.m856a(str, null, a);
    }
}
