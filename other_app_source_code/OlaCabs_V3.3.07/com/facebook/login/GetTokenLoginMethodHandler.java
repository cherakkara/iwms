package com.facebook.login;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import com.facebook.p022b.PlatformServiceClient.PlatformServiceClient;
import com.facebook.p022b.Utility.Utility;
import java.util.ArrayList;
import java.util.HashSet;
import org.json.JSONException;
import org.json.JSONObject;

class GetTokenLoginMethodHandler extends LoginMethodHandler {
    public static final Creator<GetTokenLoginMethodHandler> CREATOR;
    private GetTokenClient f957c;

    /* renamed from: com.facebook.login.GetTokenLoginMethodHandler.1 */
    class C01661 implements PlatformServiceClient {
        final /* synthetic */ Request f950a;
        final /* synthetic */ GetTokenLoginMethodHandler f951b;

        C01661(GetTokenLoginMethodHandler getTokenLoginMethodHandler, Request request) {
            this.f951b = getTokenLoginMethodHandler;
            this.f950a = request;
        }

        public void m1217a(Bundle bundle) {
            this.f951b.m1234a(this.f950a, bundle);
        }
    }

    /* renamed from: com.facebook.login.GetTokenLoginMethodHandler.2 */
    class C01672 implements Utility {
        final /* synthetic */ Bundle f952a;
        final /* synthetic */ Request f953b;
        final /* synthetic */ GetTokenLoginMethodHandler f954c;

        C01672(GetTokenLoginMethodHandler getTokenLoginMethodHandler, Bundle bundle, Request request) {
            this.f954c = getTokenLoginMethodHandler;
            this.f952a = bundle;
            this.f953b = request;
        }

        public void m1219a(JSONObject jSONObject) {
            try {
                this.f952a.putString("com.facebook.platform.extra.USER_ID", jSONObject.getString("id"));
                this.f954c.m1237b(this.f953b, this.f952a);
            } catch (JSONException e) {
                this.f954c.b.m1287b(Result.m1262a(this.f954c.b.m1288c(), "Caught exception", e.getMessage()));
            }
        }

        public void m1218a(FacebookException facebookException) {
            this.f954c.b.m1287b(Result.m1262a(this.f954c.b.m1288c(), "Caught exception", facebookException.getMessage()));
        }
    }

    /* renamed from: com.facebook.login.GetTokenLoginMethodHandler.3 */
    static class C01683 implements Creator {
        C01683() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1220a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1221a(i);
        }

        public GetTokenLoginMethodHandler m1220a(Parcel parcel) {
            return new GetTokenLoginMethodHandler(parcel);
        }

        public GetTokenLoginMethodHandler[] m1221a(int i) {
            return new GetTokenLoginMethodHandler[i];
        }
    }

    GetTokenLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    String m1233a() {
        return "get_token";
    }

    void m1236b() {
        if (this.f957c != null) {
            this.f957c.m1066b();
            this.f957c = null;
        }
    }

    boolean m1235a(Request request) {
        this.f957c = new GetTokenClient(this.b.m1285b(), request.m1254d());
        if (!this.f957c.m1065a()) {
            return false;
        }
        this.b.m1295j();
        this.f957c.m1064a(new C01661(this, request));
        return true;
    }

    void m1234a(Request request, Bundle bundle) {
        this.f957c = null;
        this.b.m1296k();
        if (bundle != null) {
            ArrayList stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
            Object<String> a = request.m1250a();
            if (stringArrayList == null || !(a == null || stringArrayList.containsAll(a))) {
                Object hashSet = new HashSet();
                for (String str : a) {
                    if (!stringArrayList.contains(str)) {
                        hashSet.add(str);
                    }
                }
                if (!hashSet.isEmpty()) {
                    m1228a("new_permissions", TextUtils.join(",", hashSet));
                }
                request.m1251a(hashSet);
            } else {
                m1238c(request, bundle);
                return;
            }
        }
        this.b.m1293h();
    }

    void m1237b(Request request, Bundle bundle) {
        this.b.m1281a(Result.m1260a(this.b.m1288c(), LoginMethodHandler.m1222a(bundle, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE, request.m1254d())));
    }

    void m1238c(Request request, Bundle bundle) {
        String string = bundle.getString("com.facebook.platform.extra.USER_ID");
        if (string == null || string.isEmpty()) {
            this.b.m1295j();
            com.facebook.p022b.Utility.m1118a(bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN"), new C01672(this, bundle, request));
            return;
        }
        m1237b(request, bundle);
    }

    GetTokenLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    static {
        CREATOR = new C01683();
    }
}
