package com.olacabs.customer.p076d;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Display;
import android.view.WindowManager;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import java.lang.reflect.Field;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

/* compiled from: DeviceInfo */
/* renamed from: com.olacabs.customer.d.ao */
public class ao {
    public static final String CLIENT_ID_KEY = "client";
    public static final String DEVICE_IDENTIFIER_KEY = "device_identifier";
    public static final String DEVICE_ID_KEY = "device_id";
    public static String DEVICE_MODEL = null;
    public static final String DEVICE_RESOLUTION_KEY = "resolution";
    private static final String LOGTAG;
    public static final String NETWORK_TYPE_KEY = "network_status";
    public static final String OS_TYPE_KEY = "os_type";
    public static final String OS_TYPE_KEY_HEADER = "os";
    public static final String OS_VERSION_KEY = "os_version";
    public static final String PHONE_MODEL_KEY = "phone_model";
    private static String sAndroidVersion;
    private static String sDeviceId;
    private static String sDeviceIdentifier;
    private static ao sInstance;
    private static String sPhoneModel;
    private Context mContext;
    private long mDeviceMemClass;
    private String mScreenSize;
    private int mScreenWidth;

    static {
        LOGTAG = ao.class.getSimpleName();
        DEVICE_MODEL = Build.MODEL;
    }

    public static ao getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ao(context);
        }
        return sInstance;
    }

    private ao(Context context) {
        this.mContext = context;
    }

    public void init() {
        Utils.m14907a();
        OLog.m13311a("Loading up device info", new Object[0]);
        sDeviceId = ((TelephonyManager) this.mContext.getSystemService(Constants.PHONE)).getDeviceId();
        sDeviceIdentifier = computeDeviceIdentifier();
        sAndroidVersion = VERSION.RELEASE;
        sPhoneModel = Build.MODEL;
        this.mDeviceMemClass = (long) (((ActivityManager) this.mContext.getSystemService("activity")).getMemoryClass() * AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START);
        this.mScreenSize = getScreenSizeFromDensity(this.mContext.getResources().getDisplayMetrics().densityDpi);
        Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.mScreenWidth = point.x;
    }

    public String getDeviceId() {
        return sDeviceId;
    }

    public String getDeviceIdentifier() {
        return sDeviceIdentifier;
    }

    public String getOsType() {
        return AbstractSpiCall.ANDROID_CLIENT_TYPE;
    }

    public String getOsVersion() {
        return sAndroidVersion;
    }

    public String getPhoneModel() {
        return sPhoneModel;
    }

    public long getDeviceMemSize() {
        return this.mDeviceMemClass;
    }

    public String getScreenSize() {
        return this.mScreenSize;
    }

    private String getScreenSizeFromDensity(int i) {
        if (i == 120) {
            return "ldpi";
        }
        if (i == 160) {
            return "mdpi";
        }
        if (i == 240) {
            return "hdpi";
        }
        if (i == 320) {
            return "xhdpi";
        }
        if (i == 480) {
            return "xxhdpi";
        }
        return "xhdpi";
    }

    private String computeDeviceIdentifier() {
        String fullDeviceId = getFullDeviceId(getInternalDeviceId());
        try {
            fullDeviceId = Base64.encodeToString(fullDeviceId.getBytes(), 9).toUpperCase();
        } catch (Throwable e) {
            OLog.m13314b(e, "Error generating ID", new Object[0]);
        }
        return fullDeviceId;
    }

    private String getInternalDeviceId() {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.serialno"});
        } catch (Throwable e) {
            OLog.m13314b(e, "Error getting id", new Object[0]);
            return null;
        }
    }

    private String getDeviceIdFromWifiManager() {
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService(WanType.WIFI);
        if (wifiManager != null) {
            return wifiManager.getConnectionInfo().getMacAddress();
        }
        return null;
    }

    private String getFullDeviceId(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str != null) {
            stringBuilder.append(str);
            stringBuilder.append("$$");
        }
        String deviceIdFromWifiManager = getDeviceIdFromWifiManager();
        if (deviceIdFromWifiManager != null) {
            stringBuilder.append(deviceIdFromWifiManager);
        }
        return stringBuilder.toString();
    }

    public static String getAndroidVersion() {
        String str = Trace.NULL;
        for (Field field : VERSION_CODES.class.getFields()) {
            String name = field.getName();
            try {
                if (field.getInt(new Object()) != VERSION.SDK_INT) {
                    name = str;
                }
                str = name;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (NullPointerException e3) {
                e3.printStackTrace();
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(" ");
            stringBuilder.append(str);
            stringBuilder.append(" ");
        }
        stringBuilder.append(VERSION.RELEASE);
        stringBuilder.append(" - ");
        stringBuilder.append(" ");
        stringBuilder.append(VERSION.SDK_INT);
        return stringBuilder.toString();
    }

    public int getScreenWidth() {
        return this.mScreenWidth;
    }
}
