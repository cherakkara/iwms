package com.facebook.p022b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.facebook.FacebookActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.p022b.Utility.Utility;

/* renamed from: com.facebook.b.g */
public class DialogPresenter {

    /* renamed from: com.facebook.b.g.a */
    public interface DialogPresenter {
        Bundle m912a();

        Bundle m913b();
    }

    public static void m914a(AppCall appCall) {
        DialogPresenter.m919a(appCall, new FacebookException("Unable to show the provided content. This typically means that the Facebook app is not installed or up to date. If showing via the Web, this could mean that the content has properties that are not supported via this channel"));
    }

    public static void m919a(AppCall appCall, FacebookException facebookException) {
        DialogPresenter.m923b(appCall, facebookException);
    }

    public static void m915a(AppCall appCall, Activity activity) {
        activity.startActivityForResult(appCall.m878b(), appCall.m880d());
        appCall.m881e();
    }

    public static void m917a(AppCall appCall, Fragment fragment) {
        fragment.startActivityForResult(appCall.m878b(), appCall.m880d());
        appCall.m881e();
    }

    public static boolean m921a(DialogFeature dialogFeature) {
        return DialogPresenter.m925c(dialogFeature) != -1;
    }

    public static boolean m924b(DialogFeature dialogFeature) {
        return DialogPresenter.m926d(dialogFeature) != null;
    }

    public static void m923b(AppCall appCall, FacebookException facebookException) {
        if (facebookException != null) {
            Intent intent = new Intent();
            intent.setClass(FacebookSdk.m1208f(), FacebookActivity.class);
            intent.setAction(FacebookActivity.f608a);
            NativeProtocol.m1049a(intent, appCall.m879c().toString(), null, NativeProtocol.m1034a(), NativeProtocol.m1046a(facebookException));
            appCall.m877a(intent);
        }
    }

    public static void m920a(AppCall appCall, String str, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("action", str);
        bundle2.putBundle("params", bundle);
        Intent intent = new Intent();
        NativeProtocol.m1049a(intent, appCall.m879c().toString(), str, NativeProtocol.m1034a(), bundle2);
        intent.setClass(FacebookSdk.m1208f(), FacebookActivity.class);
        intent.setAction("FacebookDialogFragment");
        appCall.m877a(intent);
    }

    public static void m916a(AppCall appCall, Bundle bundle, DialogFeature dialogFeature) {
        String name = dialogFeature.name();
        Uri d = DialogPresenter.m926d(dialogFeature);
        if (d == null) {
            throw new FacebookException("Unable to fetch the Url for the DialogFeature : '" + name + "'");
        }
        Bundle a = ServerProtocol.m1069a(appCall.m879c().toString(), NativeProtocol.m1034a(), bundle);
        if (a == null) {
            throw new FacebookException("Unable to fetch the app's key-hash");
        }
        Uri a2;
        if (d.isRelative()) {
            a2 = Utility.m1089a(ServerProtocol.m1070a(), d.toString(), a);
        } else {
            a2 = Utility.m1089a(d.getAuthority(), d.getPath(), a);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("url", a2.toString());
        bundle2.putBoolean("is_fallback", true);
        Intent intent = new Intent();
        NativeProtocol.m1049a(intent, appCall.m879c().toString(), dialogFeature.m910a(), NativeProtocol.m1034a(), bundle2);
        intent.setClass(FacebookSdk.m1208f(), FacebookActivity.class);
        intent.setAction("FacebookDialogFragment");
        appCall.m877a(intent);
    }

    public static void m918a(AppCall appCall, DialogPresenter dialogPresenter, DialogFeature dialogFeature) {
        Context f = FacebookSdk.m1208f();
        String a = dialogFeature.m910a();
        int c = DialogPresenter.m925c(dialogFeature);
        if (c == -1) {
            throw new FacebookException("Cannot present this dialog. This likely means that the Facebook app is not installed.");
        }
        Bundle a2;
        if (NativeProtocol.m1050a(c)) {
            a2 = dialogPresenter.m912a();
        } else {
            a2 = dialogPresenter.m913b();
        }
        if (a2 == null) {
            a2 = new Bundle();
        }
        Intent a3 = NativeProtocol.m1043a(f, appCall.m879c().toString(), a, c, a2);
        if (a3 == null) {
            throw new FacebookException("Unable to create Intent; this likely means theFacebook app is not installed.");
        }
        appCall.m877a(a3);
    }

    private static Uri m926d(DialogFeature dialogFeature) {
        String name = dialogFeature.name();
        Utility a = Utility.m1091a(FacebookSdk.m1210h(), dialogFeature.m910a(), name);
        if (a != null) {
            return a.m1082c();
        }
        return null;
    }

    public static int m925c(DialogFeature dialogFeature) {
        String h = FacebookSdk.m1210h();
        String a = dialogFeature.m910a();
        return NativeProtocol.m1037a(a, DialogPresenter.m922a(h, a, dialogFeature));
    }

    private static int[] m922a(String str, String str2, DialogFeature dialogFeature) {
        Utility a = Utility.m1091a(str, str2, dialogFeature.name());
        if (a != null) {
            return a.m1083d();
        }
        return new int[]{dialogFeature.m911b()};
    }
}
