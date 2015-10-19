package com.apsalar.sdk;

import android.content.Context;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ApEvent */
class ApsalarSession extends ApsalarEvent implements ApsalarAPI, ApsalarJSON {
    static final String TAG = "Apsalar SDK/Session";

    protected void init(Context context) {
        this.ctx = context;
        this.urlbase = "https://e.apsalar.com/api/v1/start";
        this.eventType = 1;
    }

    protected ApsalarSession(Context context, ApsalarSessionInfo apsalarSessionInfo) {
        super(context, apsalarSessionInfo);
    }

    protected ApsalarSession(Context context, ApsalarSessionInfo apsalarSessionInfo, long j) {
        super(context, apsalarSessionInfo, Trace.NULL, j, Trace.NULL);
    }

    protected boolean makeURL() {
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        instance.getClass();
        String str = Trace.NULL;
        try {
            String deviceId = Apsalar.getDeviceId(instance.canonicalKeyspace);
            String str2 = instance.canonicalKeyspace;
            instance.getClass();
            constructDK();
            instance.getClass();
            StringBuilder append = new StringBuilder().append("?a=").append(URLEncoder.encode(this.info.apiKey, HTTP.UTF_8)).append("&ab=").append(URLEncoder.encode(this.info.abi, HTTP.UTF_8)).append("&av=").append(URLEncoder.encode(this.info.appVersion, HTTP.UTF_8)).append("&br=").append(URLEncoder.encode(this.info.brand, HTTP.UTF_8)).append("&c=").append(URLEncoder.encode(this.info.connType, HTTP.UTF_8)).append("&de=").append(URLEncoder.encode(this.info.device, HTTP.UTF_8)).append("&i=").append(URLEncoder.encode(this.info.clsPackage, HTTP.UTF_8)).append("&ma=").append(URLEncoder.encode(this.info.manufacturer, HTTP.UTF_8)).append("&mo=").append(URLEncoder.encode(this.info.model, HTTP.UTF_8)).append("&n=").append(URLEncoder.encode(this.info.appName, HTTP.UTF_8)).append("&p=").append(URLEncoder.encode(this.info.platform, HTTP.UTF_8)).append("&pr=").append(URLEncoder.encode(this.info.product, HTTP.UTF_8)).append("&rt=").append(URLEncoder.encode(this.info.retType, HTTP.UTF_8)).append("&s=").append(URLEncoder.encode(this.info.sessionId, HTTP.UTF_8)).append("&sdk=").append(URLEncoder.encode("Apsalar/" + this.info.sdkVersion, HTTP.UTF_8)).append("&u=").append(URLEncoder.encode(deviceId, HTTP.UTF_8)).append("&k=").append(URLEncoder.encode(str2, HTTP.UTF_8)).append("&dk=").append(URLEncoder.encode(instance.dk, HTTP.UTF_8)).append("&aifa=").append(URLEncoder.encode(instance.AIFA, HTTP.UTF_8)).append("&dnt=");
            str = instance.playStoreAvailable ? instance.isLAT ? "1" : "0" : "-1";
            str = append.append(URLEncoder.encode(str, HTTP.UTF_8)).append("&v=").append(URLEncoder.encode(this.info.osVersion, HTTP.UTF_8)).toString();
            instance.dk = null;
            this.old_k = str2;
            this.old_u = deviceId;
            this.url = str;
            if (this.url.length() < ApSingleton.maxUrlSize) {
                return true;
            }
            instance.getClass();
            return false;
        } catch (UnsupportedEncodingException e) {
            instance.incrExceptionCount();
            instance.getClass();
            return false;
        }
    }

    public int REST() {
        ApSingleton.getInstance(this.ctx).getClass();
        return super.REST(Boolean.valueOf(true));
    }

    static int handleRedirect(JSONObject jSONObject) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        try {
            String string = jSONObject.getString("k");
            String string2 = jSONObject.getString("u");
            if (string.equals(instance.canonicalKeyspace) && string2.equals(instance.canonicalDeviceId)) {
                return 1;
            }
            instance.canonicalKeyspace = string;
            instance.canonicalDeviceId = string2;
            if (string.equals("ANDI")) {
                instance.ANDI = string2;
            } else if (string.equals("AIFA")) {
                instance.AIFA = string2;
            }
            instance.getClass();
            return -1;
        } catch (JSONException e) {
            instance.incrExceptionCount();
            instance.getClass();
            return 1;
        }
    }
}
