package com.olacabs.customer.p076d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p077b.BuildConfiguration;
import com.olacabs.customer.utils.Utils;
import com.olacabs.customer.utils.p083a.ApplicationMode;

/* renamed from: com.olacabs.customer.d.f */
public class AppInfo {
    public static final String APP_VERSION_KEY = "version";
    public static final String APP_VERSION_KEY_HEADER = "app_version";
    private static final String LOGTAG;
    private static final String USER_AGENT_DELIMITER = "/";
    public static final String USER_AGENT_KEY = "User-Agent";
    public static String sAppSignature;
    private static AppInfo sInstance;
    public static ApplicationMode sRunningMode;
    public static String sUserAgent;
    public static int sVersionCode;
    public static String sVersionName;
    private Context mContext;

    static {
        LOGTAG = AppInfo.class.getSimpleName();
        sRunningMode = BuildConfiguration.f9400a;
    }

    public static AppInfo getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AppInfo(context);
        }
        return sInstance;
    }

    private AppInfo(Context context) {
        this.mContext = context;
    }

    public void init() {
        Utils.m14907a();
        OLog.m13311a("Loading up app info", new Object[0]);
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
            sVersionCode = packageInfo.versionCode;
            sVersionName = packageInfo.versionName;
            Signature[] signatureArr = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 64).signatures;
            if (signatureArr == null || signatureArr.length <= 0) {
                OLog.m13318e("Failed to detect app signature!", new Object[0]);
            } else {
                sAppSignature = signatureArr[0].toCharsString();
            }
        } catch (NameNotFoundException e) {
            OLog.m13319f("PackMan can't find our own package!", new Object[0]);
        }
    }

    public String getUserAgent() {
        if (sUserAgent == null) {
            ao e = ((OlaApp) this.mContext).m12878a().m13224e();
            sUserAgent = new StringBuffer("OlaConsumerApp").append(USER_AGENT_DELIMITER).append(sVersionName).append(" (").append(e.getOsType()).append(USER_AGENT_DELIMITER).append(e.getOsVersion()).append(")").toString();
        }
        return sUserAgent;
    }
}
