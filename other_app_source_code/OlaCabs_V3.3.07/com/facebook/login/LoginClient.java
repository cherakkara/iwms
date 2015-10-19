package com.facebook.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.R.R;
import com.facebook.p022b.CallbackManagerImpl.CallbackManagerImpl;
import com.facebook.p022b.Utility;
import com.facebook.p022b.Validate;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class LoginClient implements Parcelable {
    public static final Creator<LoginClient> CREATOR;
    LoginMethodHandler[] f975a;
    int f976b;
    Fragment f977c;
    C0175b f978d;
    C0174a f979e;
    boolean f980f;
    Request f981g;
    Map<String, String> f982h;
    private LoginLogger f983i;

    /* renamed from: com.facebook.login.LoginClient.1 */
    static class C01701 implements Creator {
        C01701() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1246a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1247a(i);
        }

        public LoginClient m1246a(Parcel parcel) {
            return new LoginClient(parcel);
        }

        public LoginClient[] m1247a(int i) {
            return new LoginClient[i];
        }
    }

    public static class Request implements Parcelable {
        public static final Creator<Request> CREATOR;
        private final LoginBehavior f958a;
        private Set<String> f959b;
        private final DefaultAudience f960c;
        private final String f961d;
        private final String f962e;
        private boolean f963f;

        /* renamed from: com.facebook.login.LoginClient.Request.1 */
        static class C01711 implements Creator {
            C01711() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m1248a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m1249a(i);
            }

            public Request m1248a(Parcel parcel) {
                return new Request(null);
            }

            public Request[] m1249a(int i) {
                return new Request[i];
            }
        }

        Set<String> m1250a() {
            return this.f959b;
        }

        void m1251a(Set<String> set) {
            Validate.m1146a((Object) set, "permissions");
            this.f959b = set;
        }

        LoginBehavior m1252b() {
            return this.f958a;
        }

        DefaultAudience m1253c() {
            return this.f960c;
        }

        String m1254d() {
            return this.f961d;
        }

        String m1255e() {
            return this.f962e;
        }

        boolean m1256f() {
            return this.f963f;
        }

        private Request(Parcel parcel) {
            boolean z;
            DefaultAudience defaultAudience = null;
            this.f963f = false;
            String readString = parcel.readString();
            this.f958a = readString != null ? LoginBehavior.valueOf(readString) : null;
            Collection arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            this.f959b = new HashSet(arrayList);
            readString = parcel.readString();
            if (readString != null) {
                defaultAudience = DefaultAudience.valueOf(readString);
            }
            this.f960c = defaultAudience;
            this.f961d = parcel.readString();
            this.f962e = parcel.readString();
            if (parcel.readByte() != null) {
                z = true;
            } else {
                z = false;
            }
            this.f963f = z;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            String str = null;
            parcel.writeString(this.f958a != null ? this.f958a.name() : null);
            parcel.writeStringList(new ArrayList(this.f959b));
            if (this.f960c != null) {
                str = this.f960c.name();
            }
            parcel.writeString(str);
            parcel.writeString(this.f961d);
            parcel.writeString(this.f962e);
            parcel.writeByte((byte) (this.f963f ? 1 : 0));
        }

        static {
            CREATOR = new C01711();
        }
    }

    public static class Result implements Parcelable {
        public static final Creator<Result> CREATOR;
        final C0173a f969a;
        final AccessToken f970b;
        final String f971c;
        final String f972d;
        final Request f973e;
        public Map<String, String> f974f;

        /* renamed from: com.facebook.login.LoginClient.Result.1 */
        static class C01721 implements Creator {
            C01721() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m1257a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m1258a(i);
            }

            public Result m1257a(Parcel parcel) {
                return new Result(null);
            }

            public Result[] m1258a(int i) {
                return new Result[i];
            }
        }

        /* renamed from: com.facebook.login.LoginClient.Result.a */
        enum C0173a {
            SUCCESS("success"),
            CANCEL("cancel"),
            ERROR("error");
            
            private final String f968d;

            private C0173a(String str) {
                this.f968d = str;
            }

            String m1259a() {
                return this.f968d;
            }
        }

        Result(Request request, C0173a c0173a, AccessToken accessToken, String str, String str2) {
            Validate.m1146a((Object) c0173a, "code");
            this.f973e = request;
            this.f970b = accessToken;
            this.f971c = str;
            this.f969a = c0173a;
            this.f972d = str2;
        }

        static Result m1260a(Request request, AccessToken accessToken) {
            return new Result(request, C0173a.SUCCESS, accessToken, null, null);
        }

        static Result m1261a(Request request, String str) {
            return new Result(request, C0173a.CANCEL, null, str, null);
        }

        static Result m1262a(Request request, String str, String str2) {
            return m1263a(request, str, str2, null);
        }

        static Result m1263a(Request request, String str, String str2, String str3) {
            return new Result(request, C0173a.ERROR, null, TextUtils.join(": ", Utility.m1130b(str, str2)), str3);
        }

        private Result(Parcel parcel) {
            this.f969a = C0173a.valueOf(parcel.readString());
            this.f970b = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.f971c = parcel.readString();
            this.f972d = parcel.readString();
            this.f973e = (Request) parcel.readParcelable(Request.class.getClassLoader());
            this.f974f = Utility.m1110a(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f969a.name());
            parcel.writeParcelable(this.f970b, i);
            parcel.writeString(this.f971c);
            parcel.writeString(this.f972d);
            parcel.writeParcelable(this.f973e, i);
            Utility.m1115a(parcel, this.f974f);
        }

        static {
            CREATOR = new C01721();
        }
    }

    /* renamed from: com.facebook.login.LoginClient.a */
    interface C0174a {
        void m1264a();

        void m1265b();
    }

    /* renamed from: com.facebook.login.LoginClient.b */
    public interface C0175b {
        void m1266a(Result result);
    }

    public LoginClient(Fragment fragment) {
        this.f976b = -1;
        this.f977c = fragment;
    }

    public Fragment m1278a() {
        return this.f977c;
    }

    void m1279a(Fragment fragment) {
        if (this.f977c != null) {
            throw new FacebookException("Can't set fragment once it is already set.");
        }
        this.f977c = fragment;
    }

    FragmentActivity m1285b() {
        return this.f977c.getActivity();
    }

    public Request m1288c() {
        return this.f981g;
    }

    public static int m1271d() {
        return CallbackManagerImpl.Login.m908a();
    }

    void m1280a(Request request) {
        if (!m1290e()) {
            m1286b(request);
        }
    }

    void m1286b(Request request) {
        if (request != null) {
            if (this.f981g != null) {
                throw new FacebookException("Attempted to authorize while a request is pending.");
            } else if (AccessToken.m690a() == null || m1292g()) {
                this.f981g = request;
                this.f975a = m1270c(request);
                m1293h();
            }
        }
    }

    boolean m1290e() {
        return this.f981g != null && this.f976b >= 0;
    }

    void m1291f() {
        if (this.f976b >= 0) {
            m1274m().m1231b();
        }
    }

    private LoginMethodHandler m1274m() {
        if (this.f976b >= 0) {
            return this.f975a[this.f976b];
        }
        return null;
    }

    public boolean m1284a(int i, int i2, Intent intent) {
        if (this.f981g != null) {
            return m1274m().m1229a(i, i2, intent);
        }
        return false;
    }

    private LoginMethodHandler[] m1270c(Request request) {
        ArrayList arrayList = new ArrayList();
        LoginBehavior b = request.m1252b();
        if (b.m1312a()) {
            arrayList.add(new GetTokenLoginMethodHandler(this));
            arrayList.add(new KatanaProxyLoginMethodHandler(this));
        }
        if (b.m1313b()) {
            arrayList.add(new WebViewLoginMethodHandler(this));
        }
        LoginMethodHandler[] loginMethodHandlerArr = new LoginMethodHandler[arrayList.size()];
        arrayList.toArray(loginMethodHandlerArr);
        return loginMethodHandlerArr;
    }

    boolean m1292g() {
        if (this.f980f) {
            return true;
        }
        if (m1277a("android.permission.INTERNET") != 0) {
            Activity b = m1285b();
            m1287b(Result.m1262a(this.f981g, b.getString(R.com_facebook_internet_permission_error_title), b.getString(R.com_facebook_internet_permission_error_message)));
            return false;
        }
        this.f980f = true;
        return true;
    }

    void m1293h() {
        if (this.f976b >= 0) {
            m1268a(m1274m().m1225a(), "skipped", null, null, m1274m().f955a);
        }
        while (this.f975a != null && this.f976b < this.f975a.length - 1) {
            this.f976b++;
            if (m1294i()) {
                return;
            }
        }
        if (this.f981g != null) {
            m1275n();
        }
    }

    private void m1275n() {
        m1287b(Result.m1262a(this.f981g, "Login attempt failed.", null));
    }

    private void m1269a(String str, String str2, boolean z) {
        if (this.f982h == null) {
            this.f982h = new HashMap();
        }
        if (this.f982h.containsKey(str) && z) {
            str2 = ((String) this.f982h.get(str)) + "," + str2;
        }
        this.f982h.put(str, str2);
    }

    boolean m1294i() {
        boolean z = false;
        LoginMethodHandler m = m1274m();
        if (!m.m1232c() || m1292g()) {
            z = m.m1230a(this.f981g);
            if (z) {
                m1276o().m1321a(this.f981g.m1255e(), m.m1225a());
            } else {
                m1269a("not_tried", m.m1225a(), true);
            }
        } else {
            m1269a("no_internet_permission", "1", false);
        }
        return z;
    }

    void m1281a(Result result) {
        if (result.f970b == null || AccessToken.m690a() == null) {
            m1287b(result);
        } else {
            m1289c(result);
        }
    }

    void m1287b(Result result) {
        LoginMethodHandler m = m1274m();
        if (m != null) {
            m1267a(m.m1225a(), result, m.f955a);
        }
        if (this.f982h != null) {
            result.f974f = this.f982h;
        }
        this.f975a = null;
        this.f976b = -1;
        this.f981g = null;
        this.f982h = null;
        m1272d(result);
    }

    void m1283a(C0175b c0175b) {
        this.f978d = c0175b;
    }

    void m1282a(C0174a c0174a) {
        this.f979e = c0174a;
    }

    int m1277a(String str) {
        return m1285b().checkCallingOrSelfPermission(str);
    }

    void m1289c(Result result) {
        if (result.f970b == null) {
            throw new FacebookException("Can't validate without a token");
        }
        Result a;
        AccessToken a2 = AccessToken.m690a();
        AccessToken accessToken = result.f970b;
        if (!(a2 == null || accessToken == null)) {
            try {
                if (a2.m704h().equals(accessToken.m704h())) {
                    a = Result.m1260a(this.f981g, result.f970b);
                    m1287b(a);
                }
            } catch (Exception e) {
                m1287b(Result.m1262a(this.f981g, "Caught exception", e.getMessage()));
                return;
            }
        }
        a = Result.m1262a(this.f981g, "User logged in as different Facebook user.", null);
        m1287b(a);
    }

    private LoginLogger m1276o() {
        if (this.f983i == null || !this.f983i.m1320a().equals(this.f981g.m1254d())) {
            this.f983i = new LoginLogger(m1285b(), this.f981g.m1254d());
        }
        return this.f983i;
    }

    private void m1272d(Result result) {
        if (this.f978d != null) {
            this.f978d.m1266a(result);
        }
    }

    void m1295j() {
        if (this.f979e != null) {
            this.f979e.m1264a();
        }
    }

    void m1296k() {
        if (this.f979e != null) {
            this.f979e.m1265b();
        }
    }

    private void m1267a(String str, Result result, Map<String, String> map) {
        m1268a(str, result.f969a.m1259a(), result.f971c, result.f972d, map);
    }

    private void m1268a(String str, String str2, String str3, String str4, Map<String, String> map) {
        if (this.f981g == null) {
            m1276o().m1322a("fb_mobile_login_method_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.", str);
        } else {
            m1276o().m1323a(this.f981g.m1255e(), str, str2, str3, str4, map);
        }
    }

    static String m1273l() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("init", System.currentTimeMillis());
        } catch (JSONException e) {
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }

    public LoginClient(Parcel parcel) {
        this.f976b = -1;
        Parcelable[] readParcelableArray = parcel.readParcelableArray(LoginMethodHandler.class.getClassLoader());
        this.f975a = new LoginMethodHandler[readParcelableArray.length];
        for (int i = 0; i < readParcelableArray.length; i++) {
            this.f975a[i] = (LoginMethodHandler) readParcelableArray[i];
            this.f975a[i].m1226a(this);
        }
        this.f976b = parcel.readInt();
        this.f981g = (Request) parcel.readParcelable(Request.class.getClassLoader());
        this.f982h = Utility.m1110a(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelableArray(this.f975a, i);
        parcel.writeInt(this.f976b);
        parcel.writeParcelable(this.f981g, i);
        Utility.m1115a(parcel, this.f982h);
    }

    static {
        CREATOR = new C01701();
    }
}
