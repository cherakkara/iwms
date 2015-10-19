package com.apsalar.sdk;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;

public class ApsalarJSInterface {
    final String TAG;
    Context mContext;
    int webViewId;

    public ApsalarJSInterface(Context context) {
        this.TAG = "ApsalarJSInterface";
        ApSingleton.getInstance(context).getClass();
        this.mContext = context;
    }

    @JavascriptInterface
    public void setWebViewId(int i) {
        ApSingleton.getInstance(ApSingleton.getContext()).getClass();
        this.webViewId = i;
    }

    @JavascriptInterface
    public void event(String str) {
        ApSingleton.getInstance(ApSingleton.getContext()).getClass();
        Apsalar.event(str);
    }

    @JavascriptInterface
    public void event(String str, String str2) throws JSONException {
        ApSingleton.getInstance(ApSingleton.getContext()).getClass();
        Apsalar.event(str, JSONObjectInstrumentation.init(str2));
    }

    @JavascriptInterface
    public void endSession() {
        ApSingleton.getInstance(ApSingleton.getContext()).getClass();
        Apsalar.endSession();
    }
}
