package com.apsalar.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.common.WanType;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

class ApsalarSessionInfo implements ApsalarJSON {
    static final String TAG = "Apsalar SDK/SessionInfo";
    protected String abi;
    protected String apiKey;
    protected String appName;
    protected String appVersion;
    protected String brand;
    protected String clsPackage;
    protected String connType;
    protected String device;
    protected String deviceId;
    protected JSONObject known_items;
    protected String manufacturer;
    protected String model;
    protected String osVersion;
    protected String platform;
    protected String product;
    protected String retType;
    protected String sdkVersion;
    protected String secret;
    protected String sessionId;
    protected long sessionStart;

    protected ApsalarSessionInfo(JSONObject jSONObject, String str, String str2) {
        this.sessionStart = 0;
        this.sdkVersion = Constants.SDK_VER;
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        try {
            this.apiKey = str;
            this.secret = str2;
            this.abi = jSONObject.has("abi") ? jSONObject.getString("abi") : WanType.UNKNOWN;
            this.platform = jSONObject.has("platform") ? jSONObject.getString("platform") : "Android";
            this.clsPackage = jSONObject.has("clsPackage") ? jSONObject.getString("clsPackage") : WanType.UNKNOWN;
            this.appVersion = jSONObject.has("appVersion") ? jSONObject.getString("appVersion") : WanType.UNKNOWN;
            this.deviceId = jSONObject.has("deviceId") ? jSONObject.getString("deviceId") : "unspecified";
            this.sessionId = jSONObject.has(AnalyticAttribute.SESSION_ID_ATTRIBUTE) ? jSONObject.getString(AnalyticAttribute.SESSION_ID_ATTRIBUTE) : "unspecified";
            this.retType = jSONObject.has("retType") ? jSONObject.getString("retType") : "json";
            this.connType = jSONObject.has("connType") ? jSONObject.getString("connType") : WanType.WIFI;
            this.appName = jSONObject.has(AnalyticAttribute.APP_NAME_ATTRIBUTE) ? jSONObject.getString(AnalyticAttribute.APP_NAME_ATTRIBUTE) : WanType.UNKNOWN;
            this.osVersion = jSONObject.has(AnalyticAttribute.OS_VERSION_ATTRIBUTE) ? jSONObject.getString(AnalyticAttribute.OS_VERSION_ATTRIBUTE) : WanType.UNKNOWN;
            this.brand = jSONObject.has("brand") ? jSONObject.getString("brand") : WanType.UNKNOWN;
            this.device = jSONObject.has("device") ? jSONObject.getString("device") : WanType.UNKNOWN;
            this.manufacturer = jSONObject.has("manufacturer") ? jSONObject.getString("manufacturer") : WanType.UNKNOWN;
            this.model = jSONObject.has("model") ? jSONObject.getString("model") : WanType.UNKNOWN;
            this.product = jSONObject.has("product") ? jSONObject.getString("product") : WanType.UNKNOWN;
            this.sdkVersion = jSONObject.has("sdkVersion") ? jSONObject.getString("sdkVersion") : "unspecified";
            this.sessionStart = jSONObject.getLong("sessionStart");
        } catch (JSONException e) {
            instance.getClass();
        }
        instance.getClass();
    }

    protected ApsalarSessionInfo(Context context, String str, String str2) {
        this.sessionStart = 0;
        this.sdkVersion = Constants.SDK_VER;
        ApSingleton instance = ApSingleton.getInstance(context);
        this.sessionId = UUID.randomUUID().toString();
        instance.getClass();
        this.apiKey = str;
        this.secret = str2;
        PackageManager packageManager = context.getPackageManager();
        this.platform = "Android";
        this.clsPackage = context.getPackageName();
        try {
            this.appVersion = packageManager.getPackageInfo(this.clsPackage, 0).versionName;
        } catch (NameNotFoundException e) {
            this.appVersion = WanType.UNKNOWN;
        }
        if (this.deviceId == null) {
            this.deviceId = "None";
        }
        this.retType = "json";
        this.connType = WanType.WIFI;
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0);
            if (networkInfo != null && networkInfo.isConnected()) {
                this.connType = "wwan";
            }
        } catch (SecurityException e2) {
            instance.getClass();
        } catch (Exception e3) {
            instance.getClass();
        }
        this.appName = WanType.UNKNOWN;
        try {
            this.appName = packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.clsPackage, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS)).toString();
        } catch (NameNotFoundException e4) {
            instance.getClass();
        } catch (NullPointerException e5) {
            instance.getClass();
        }
        this.osVersion = VERSION.RELEASE;
        this.brand = Build.BRAND;
        this.device = Build.DEVICE;
        this.abi = WanType.UNKNOWN;
        this.manufacturer = WanType.UNKNOWN;
        try {
            this.abi = (String) Build.class.getDeclaredField("CPU_ABI").get(Build.class);
        } catch (Throwable th) {
            instance.getClass();
        }
        try {
            this.manufacturer = (String) Build.class.getDeclaredField("MANUFACTURER").get(Build.class);
        } catch (Throwable th2) {
            instance.getClass();
        }
        this.model = Build.MODEL;
        this.product = Build.PRODUCT;
        this.abi = this.abi != null ? this.abi : WanType.UNKNOWN;
        this.platform = this.platform != null ? this.platform : "Android";
        this.clsPackage = this.clsPackage != null ? this.clsPackage : WanType.UNKNOWN;
        this.appVersion = this.appVersion != null ? this.appVersion : WanType.UNKNOWN;
        this.deviceId = this.deviceId != null ? this.deviceId : "unspecified";
        this.sessionId = this.sessionId != null ? this.sessionId : "unspecified";
        this.retType = this.retType != null ? this.retType : "json";
        this.connType = this.connType != null ? this.connType : WanType.WIFI;
        this.appName = this.appName != null ? this.appName : WanType.UNKNOWN;
        this.osVersion = this.osVersion != null ? this.osVersion : WanType.UNKNOWN;
        this.brand = this.brand != null ? this.brand : WanType.UNKNOWN;
        this.device = this.device != null ? this.device : WanType.UNKNOWN;
        this.manufacturer = this.manufacturer != null ? this.manufacturer : WanType.UNKNOWN;
        this.model = this.model != null ? this.model : WanType.UNKNOWN;
        this.product = this.product != null ? this.product : WanType.UNKNOWN;
        this.sdkVersion = this.sdkVersion != null ? this.sdkVersion : "unspecified";
    }

    public JSONObject toJSON() {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sessionStart", instance.info.sessionStart);
            jSONObject.put("apiKey", instance.info.apiKey);
            jSONObject.put("secret", instance.info.secret);
            jSONObject.put("abi", this.abi);
            jSONObject.put("platform", this.platform);
            jSONObject.put("clsPackage", this.clsPackage);
            jSONObject.put("appVersion", this.appVersion);
            jSONObject.put("deviceId", this.deviceId);
            jSONObject.put(AnalyticAttribute.SESSION_ID_ATTRIBUTE, this.sessionId);
            jSONObject.put("retType", this.retType);
            jSONObject.put("connType", this.connType);
            jSONObject.put(AnalyticAttribute.APP_NAME_ATTRIBUTE, this.appName);
            jSONObject.put(AnalyticAttribute.OS_VERSION_ATTRIBUTE, this.osVersion);
            jSONObject.put("brand", this.brand);
            jSONObject.put("device", this.device);
            jSONObject.put("manufacturer", this.manufacturer);
            jSONObject.put("model", this.model);
            jSONObject.put("product", this.product);
            jSONObject.put("sdkVersion", this.sdkVersion);
        } catch (JSONException e) {
            instance.getClass();
        } catch (Exception e2) {
            instance.getClass();
        }
        return jSONObject;
    }
}
