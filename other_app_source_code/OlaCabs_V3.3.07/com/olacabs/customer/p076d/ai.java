package com.olacabs.customer.p076d;

import android.os.Build;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.utils.Constants;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;

/* compiled from: DataManagerUtils */
/* renamed from: com.olacabs.customer.d.ai */
public class ai {
    public static Map<String, String> getStandardHeader(OlaApp olaApp) {
        AppInfo b = olaApp.m12878a().m13197b();
        ao e = olaApp.m12878a().m13224e();
        da d = olaApp.m12878a().m13218d();
        Map<String, String> hashMap = new HashMap();
        hashMap.put(ao.CLIENT_ID_KEY, e.getOsType());
        hashMap.put("api-key", "@ndro1d");
        hashMap.put(AppInfo.APP_VERSION_KEY_HEADER, AppInfo.sVersionName);
        hashMap.put(ao.OS_TYPE_KEY_HEADER, "android_" + e.getOsVersion());
        hashMap.put(HTTP.USER_AGENT, b.getUserAgent());
        hashMap.put(Constants.X_DEVICE_MODEL, Build.MODEL);
        hashMap.put(Constants.X_SESSION_ID, d.getSessionId());
        return hashMap;
    }
}
