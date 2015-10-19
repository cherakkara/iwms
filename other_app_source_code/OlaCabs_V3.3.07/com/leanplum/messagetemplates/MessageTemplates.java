package com.leanplum.messagetemplates;

import android.content.Context;
import com.leanplum.ActionArgs;
import com.leanplum.Leanplum;

public class MessageTemplates {
    private static Boolean f8833a;

    static {
        f8833a = Boolean.valueOf(false);
    }

    static String m12782a(Context context) {
        int i = context.getApplicationInfo().labelRes;
        if (i == 0) {
            return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
        }
        return context.getString(i);
    }

    public static void register(Context context) {
        synchronized (f8833a) {
            if (f8833a.booleanValue()) {
                return;
            }
            f8833a = Boolean.valueOf(true);
            Leanplum.defineAction("Open URL", Leanplum.ACTION_KIND_ACTION, new ActionArgs().with("URL", "http://www.example.com"), new C0661v());
            Leanplum.defineAction("Alert", Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION, new ActionArgs().with("Title", m12782a(context)).with("Message", "Alert message goes here.").with("Dismiss text", "OK").withAction("Dismiss action", null), new C0642b());
            Leanplum.defineAction("Confirm", Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION, new ActionArgs().with("Title", m12782a(context)).with("Message", "Confirmation message goes here.").with("Accept text", "Yes").with("Cancel text", "No").withAction("Accept action", null).withAction("Cancel action", null), new C0654o());
            CenterPopup.register(context);
            Interstitial.register(context);
            WebInterstitial.register(context);
        }
    }
}
