package com.facebook.p022b;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.olacabs.customer.p076d.AppInfo;

/* renamed from: com.facebook.b.k */
public class FacebookWebFallbackDialog extends WebDialog {
    private static final String f780a;
    private boolean f781b;

    /* renamed from: com.facebook.b.k.1 */
    class FacebookWebFallbackDialog implements Runnable {
        final /* synthetic */ FacebookWebFallbackDialog f769a;

        FacebookWebFallbackDialog(FacebookWebFallbackDialog facebookWebFallbackDialog) {
            this.f769a = facebookWebFallbackDialog;
        }

        public void run() {
            super.cancel();
        }
    }

    static {
        f780a = FacebookWebFallbackDialog.class.getName();
    }

    public FacebookWebFallbackDialog(Context context, String str, String str2) {
        super(context, str);
        m967b(str2);
    }

    protected Bundle m972a(String str) {
        Bundle c = Utility.m1136c(Uri.parse(str).getQuery());
        String string = c.getString("bridge_args");
        c.remove("bridge_args");
        if (!Utility.m1126a(string)) {
            try {
                c.putBundle("com.facebook.platform.protocol.BRIDGE_ARGS", BundleJSONConverter.m906a(JSONObjectInstrumentation.init(string)));
            } catch (Throwable e) {
                Utility.m1120a(f780a, "Unable to parse bridge_args JSON", e);
            }
        }
        string = c.getString("method_results");
        c.remove("method_results");
        if (!Utility.m1126a(string)) {
            if (Utility.m1126a(string)) {
                string = "{}";
            }
            try {
                c.putBundle("com.facebook.platform.protocol.RESULT_ARGS", BundleJSONConverter.m906a(JSONObjectInstrumentation.init(string)));
            } catch (Throwable e2) {
                Utility.m1120a(f780a, "Unable to parse bridge_args JSON", e2);
            }
        }
        c.remove(AppInfo.APP_VERSION_KEY);
        c.putInt("com.facebook.platform.protocol.PROTOCOL_VERSION", NativeProtocol.m1034a());
        return c;
    }

    public void cancel() {
        WebView c = m969c();
        if (!m968b() || m966a() || c == null || !c.isShown()) {
            super.cancel();
        } else if (!this.f781b) {
            this.f781b = true;
            c.loadUrl("javascript:" + "(function() {  var event = document.createEvent('Event');  event.initEvent('fbPlatformDialogMustClose',true,true);  document.dispatchEvent(event);})();");
            new Handler(Looper.getMainLooper()).postDelayed(new FacebookWebFallbackDialog(this), 1500);
        }
    }
}
