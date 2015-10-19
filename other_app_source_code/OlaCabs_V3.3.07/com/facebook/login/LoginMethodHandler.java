package com.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.login.LoginClient.Request;
import com.facebook.p022b.Utility;
import com.facebook.p023a.AppEventsLogger;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.olacabs.customer.utils.Constants;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;

abstract class LoginMethodHandler implements Parcelable {
    Map<String, String> f955a;
    protected LoginClient f956b;

    abstract String m1225a();

    abstract boolean m1230a(Request request);

    LoginMethodHandler(LoginClient loginClient) {
        this.f956b = loginClient;
    }

    LoginMethodHandler(Parcel parcel) {
        this.f955a = Utility.m1110a(parcel);
    }

    void m1226a(LoginClient loginClient) {
        if (this.f956b != null) {
            throw new FacebookException("Can't set LoginClient if it is already set.");
        }
        this.f956b = loginClient;
    }

    boolean m1229a(int i, int i2, Intent intent) {
        return false;
    }

    boolean m1232c() {
        return false;
    }

    void m1231b() {
    }

    protected void m1228a(String str, Object obj) {
        if (this.f955a == null) {
            this.f955a = new HashMap();
        }
        this.f955a.put(str, obj == null ? null : obj.toString());
    }

    protected void m1227a(String str) {
        String d = this.f956b.m1288c().m1254d();
        AppEventsLogger a = AppEventsLogger.m831a(this.f956b.m1285b(), d);
        Bundle bundle = new Bundle();
        bundle.putString("fb_web_login_e2e", str);
        bundle.putLong("fb_web_login_switchback_time", System.currentTimeMillis());
        bundle.putString("app_id", d);
        a.m856a("fb_dialogs_web_login_dialog_complete", null, bundle);
    }

    static AccessToken m1222a(Bundle bundle, AccessTokenSource accessTokenSource, String str) {
        Date a = Utility.m1107a(bundle, "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH", new Date(0));
        Collection stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
        String string = bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN");
        if (Utility.m1126a(string)) {
            return null;
        }
        return new AccessToken(string, str, bundle.getString("com.facebook.platform.extra.USER_ID"), stringArrayList, null, accessTokenSource, a, new Date());
    }

    public static AccessToken m1223a(Collection<String> collection, Bundle bundle, AccessTokenSource accessTokenSource, String str) throws FacebookException {
        Collection collection2;
        Date a = Utility.m1107a(bundle, "expires_in", new Date());
        String string = bundle.getString("access_token");
        String string2 = bundle.getString("granted_scopes");
        if (Utility.m1126a(string2)) {
            Collection<String> collection3 = collection;
        } else {
            Collection arrayList = new ArrayList(Arrays.asList(string2.split(",")));
        }
        string2 = bundle.getString("denied_scopes");
        if (Utility.m1126a(string2)) {
            collection2 = null;
        } else {
            collection2 = new ArrayList(Arrays.asList(string2.split(",")));
        }
        if (Utility.m1126a(string)) {
            return null;
        }
        return new AccessToken(string, str, m1224b(bundle.getString("signed_request")), arrayList, collection2, accessTokenSource, a, new Date());
    }

    private static String m1224b(String str) throws FacebookException {
        if (str == null || str.isEmpty()) {
            throw new FacebookException("Authorization response does not contain the signed_request");
        }
        try {
            String[] split = str.split("\\.");
            if (split.length == 2) {
                return JSONObjectInstrumentation.init(new String(Base64.decode(split[1], 0), HTTP.UTF_8)).getString(Constants.USER_ID);
            }
        } catch (UnsupportedEncodingException e) {
        } catch (JSONException e2) {
        }
        throw new FacebookException("Failed to retrieve user_id from signed_request");
    }

    public void writeToParcel(Parcel parcel, int i) {
        Utility.m1115a(parcel, this.f955a);
    }
}
