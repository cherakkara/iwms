package com.facebook.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import com.facebook.p022b.NativeProtocol;
import com.facebook.p022b.ServerProtocol;
import com.facebook.p022b.Utility;

class KatanaProxyLoginMethodHandler extends LoginMethodHandler {
    public static final Creator<KatanaProxyLoginMethodHandler> CREATOR;

    /* renamed from: com.facebook.login.KatanaProxyLoginMethodHandler.1 */
    static class C01691 implements Creator {
        C01691() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1239a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1240a(i);
        }

        public KatanaProxyLoginMethodHandler m1239a(Parcel parcel) {
            return new KatanaProxyLoginMethodHandler(parcel);
        }

        public KatanaProxyLoginMethodHandler[] m1240a(int i) {
            return new KatanaProxyLoginMethodHandler[i];
        }
    }

    KatanaProxyLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    String m1242a() {
        return "katana_proxy_auth";
    }

    boolean m1245a(Request request) {
        String l = LoginClient.m1273l();
        Intent a = NativeProtocol.m1044a(this.b.m1285b(), request.m1254d(), request.m1250a(), l, request.m1256f(), request.m1253c());
        m1228a("e2e", l);
        return m1244a(a, LoginClient.m1271d());
    }

    boolean m1243a(int i, int i2, Intent intent) {
        Result a;
        Request c = this.b.m1288c();
        if (intent == null) {
            a = Result.m1261a(c, "Operation canceled");
        } else if (i2 == 0) {
            a = Result.m1261a(c, intent.getStringExtra("error"));
        } else if (i2 != -1) {
            a = Result.m1262a(c, "Unexpected resultCode from authorization.", null);
        } else {
            a = m1241a(c, intent);
        }
        if (a != null) {
            this.b.m1281a(a);
        } else {
            this.b.m1293h();
        }
        return true;
    }

    private Result m1241a(Request request, Intent intent) {
        Bundle extras = intent.getExtras();
        String string = extras.getString("error");
        if (string == null) {
            string = extras.getString("error_type");
        }
        String string2 = extras.getString("error_code");
        String string3 = extras.getString("error_message");
        if (string3 == null) {
            string3 = extras.getString("error_description");
        }
        String string4 = extras.getString("e2e");
        if (!Utility.m1126a(string4)) {
            m1227a(string4);
        }
        if (string == null && string2 == null && string3 == null) {
            try {
                return Result.m1260a(request, LoginMethodHandler.m1223a(request.m1250a(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, request.m1254d()));
            } catch (FacebookException e) {
                return Result.m1262a(request, null, e.getMessage());
            }
        } else if (ServerProtocol.f836a.contains(string)) {
            return null;
        } else {
            if (ServerProtocol.f837b.contains(string)) {
                return Result.m1261a(request, null);
            }
            return Result.m1263a(request, string, string3, string2);
        }
    }

    protected boolean m1244a(Intent intent, int i) {
        if (intent == null) {
            return false;
        }
        try {
            this.b.m1278a().startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    KatanaProxyLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    static {
        CREATOR = new C01691();
    }
}
