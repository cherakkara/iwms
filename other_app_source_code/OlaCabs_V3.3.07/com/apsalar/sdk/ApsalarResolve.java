package com.apsalar.sdk;

import android.content.Context;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: ApEvent */
class ApsalarResolve extends ApsalarEvent {
    static final String TAG = "Apsalar SDK/Resolve";
    String clsPackage;
    String hash;
    boolean playStoreAvailable;

    protected void init(Context context) {
        this.ctx = context;
        this.urlbase = "https://e.apsalar.com/api/v1/resolve";
    }

    ApsalarResolve(Context context, ApsalarSessionInfo apsalarSessionInfo, boolean z) {
        super(context, apsalarSessionInfo);
        this.clsPackage = null;
        this.hash = null;
        this.playStoreAvailable = false;
        this.clsPackage = context.getPackageName();
        this.playStoreAvailable = z;
    }

    protected boolean makeURL(boolean z) {
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        instance.getClass();
        String str = Trace.NULL;
        try {
            String deviceId = Apsalar.getDeviceId(instance.canonicalKeyspace);
            str = instance.canonicalKeyspace;
            if (z && resolveHelper()) {
                deviceId = this.info.deviceId;
                str = instance.canonicalKeyspace;
            }
            if (alreadyResolved(str)) {
                instance.getClass();
                return false;
            }
            this.info.retType = "json";
            String str2 = "?a=" + URLEncoder.encode(this.info.apiKey, HTTP.UTF_8) + "&k=" + URLEncoder.encode(str, HTTP.UTF_8) + "&u=" + URLEncoder.encode(deviceId, HTTP.UTF_8) + "&p=" + URLEncoder.encode(this.info.platform, HTTP.UTF_8) + "&v=" + URLEncoder.encode(this.info.osVersion, HTTP.UTF_8) + "&rt=" + URLEncoder.encode(this.info.retType, HTTP.UTF_8);
            instance.dk = null;
            this.old_k = str;
            this.old_u = deviceId;
            this.url = str2;
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
        return REST(true);
    }

    public int doGet(boolean z) {
        int i;
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        Apsalar.getDeviceId(instance.canonicalKeyspace);
        instance.getClass();
        if (makeURL(z)) {
            i = 1;
        } else if (alreadyResolved(instance.canonicalKeyspace)) {
            instance.getClass();
            return 1;
        } else {
            instance.getClass();
            i = -1;
        }
        if (i != 1) {
            return i;
        }
        int i2;
        String str = this.url;
        String str2 = Trace.NULL;
        try {
            MessageDigest instance2 = MessageDigest.getInstance("SHA-1");
            instance2.reset();
            instance2.update(this.info.secret.getBytes(HTTP.UTF_8));
            instance2.update(str.getBytes(HTTP.UTF_8));
            str2 = Apsalar.hexDigest(instance2.digest());
            i2 = i;
        } catch (NoSuchAlgorithmException e) {
            instance.incrExceptionCount();
            instance.getClass();
            i2 = -1;
        } catch (UnsupportedEncodingException e2) {
            instance.incrExceptionCount();
            instance.getClass();
            i2 = -1;
        } catch (IndexOutOfBoundsException e3) {
            instance.incrExceptionCount();
            instance.getClass();
            i2 = -1;
        }
        str2 = this.urlbase + str + "&h=" + str2;
        instance.getClass();
        if (i2 != 1) {
            return i2;
        }
        this.returnData = null;
        if (i2 != 1) {
            return i2;
        }
        try {
            this.returnData = ApsalarHttpClient.get(str2);
            try {
                if (this.returnData != null) {
                    instance.incrSentEventsCount();
                }
                this.returnDataJSON = JSONObjectInstrumentation.init(this.returnData);
                String string = this.returnDataJSON.getString("k");
                String string2 = this.returnDataJSON.getString("u");
                instance.getClass();
                if (!this.old_k.equals(instance.canonicalKeyspace) || !this.old_u.equals(instance.canonicalDeviceId)) {
                    return i2;
                }
                changeDeviceKeys(string, string2, this.old_k, this.old_u, false);
                return i2;
            } catch (JSONException e4) {
                instance.incrExceptionCount();
                instance.getClass();
                return i2;
            }
        } catch (ProtocolException e5) {
            instance.incrExceptionCount();
            instance.getClass();
            return -1;
        } catch (SocketTimeoutException e6) {
            instance.incrExceptionCount();
            instance.incrNetworkErrorCount();
            instance.getClass();
            return 0;
        } catch (IOException e7) {
            instance.incrExceptionCount();
            instance.incrNetworkErrorCount();
            instance.getClass();
            return 0;
        }
    }

    private void trackResolve(String str) {
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        instance.getClass();
        if (str.equals("ANDI")) {
            instance.resolved_ANDI = true;
        } else if (str.equals("AIFA")) {
            instance.resolved_AIFA = true;
        }
    }

    private boolean alreadyResolved(String str) {
        boolean z = true;
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        if (!((instance.resolved_ANDI && str.equals("ANDI")) || (instance.resolved_AIFA && str.equals("AIFA")))) {
            z = false;
        }
        instance.getClass();
        return z;
    }

    boolean do_resolve() {
        boolean z = false;
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        instance.getClass();
        boolean z2 = false;
        for (int i = 0; i < instance.desired.length(); i++) {
            try {
                String string = instance.desired.getString(i);
                if (alreadyResolved(string)) {
                    instance.getClass();
                } else {
                    String deviceId = Apsalar.getDeviceId(string);
                    String str = instance.canonicalKeyspace;
                    if (!string.equals(instance.canonicalKeyspace) || !deviceId.equals(instance.canonicalDeviceId)) {
                        if (string.equals(str) && !string.equals(instance.canonicalKeyspace)) {
                            if (!alreadyResolved(string)) {
                                Log.d(TAG, "ApsalarResolve: found - RESOLVING... keySpace=" + string + ", deviceId=" + deviceId);
                                Apsalar.getDeviceId(string);
                                this.info.deviceId = instance.info.deviceId;
                                switch (doGet(false)) {
                                    case ContentLengthStrategy.IDENTITY /*-1*/:
                                    case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                                        instance.getClass();
                                        break;
                                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                                        instance.getClass();
                                        trackResolve(string);
                                        z2 = true;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            z2 = true;
                        } else {
                            instance.getClass();
                        }
                    } else {
                        instance.getClass();
                        trackResolve(string);
                        z2 = true;
                    }
                }
            } catch (JSONException e) {
                instance.incrExceptionCount();
                instance.getClass();
            }
        }
        if (z2 || instance.resolved_ANDI || instance.resolved_AIFA) {
            z = true;
        }
        if (z) {
            instance.devicesAlreadyResolved = true;
        }
        return z;
    }

    public int REST(boolean z) {
        int i = 1;
        ApSingleton instance = ApSingleton.getInstance(this.ctx);
        int doGet = doGet(true);
        if (doGet != 1) {
            instance.getClass();
            return doGet;
        }
        if (this.returnData == null) {
            instance.getClass();
        } else if (z) {
            try {
                this.returnDataJSON = JSONObjectInstrumentation.init(this.returnData);
                try {
                    if (this.returnDataJSON.getString(NotificationCompatApi21.CATEGORY_STATUS).toLowerCase().equals("ok")) {
                        instance.getClass();
                    }
                } catch (JSONException e) {
                }
                JSONArray jSONArray = instance.desired;
                JSONArray jSONArray2 = this.returnDataJSON.getJSONArray("desired");
                instance.desired = jSONArray2;
                if (jSONArray2 == null) {
                    instance.getClass();
                    i = 0;
                } else {
                    trackResolve(instance.canonicalKeyspace);
                    if (!(jSONArray == null || instance.desired.equals(jSONArray))) {
                        instance.getClass();
                        ApsalarSQLiteHelper.clearConfigTables();
                        Apsalar.loadConfig(this.ctx);
                        instance.devicesAlreadyResolved = false;
                        instance.already_did_SQL = false;
                    }
                    instance.getClass();
                }
            } catch (Throwable th) {
                instance.incrExceptionCount();
                instance.getClass();
                i = 0;
            }
            if (!(instance.devicesAlreadyResolved || do_resolve())) {
                i = 0;
            }
        } else {
            instance.getClass();
            i = 0;
        }
        instance.getClass();
        return i;
    }

    public String toString() {
        return this.returnData;
    }
}
