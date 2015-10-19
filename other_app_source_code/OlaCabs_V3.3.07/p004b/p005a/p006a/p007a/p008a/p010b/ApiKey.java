package p004b.p005a.p006a.p007a.p008a.p010b;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.crashlytics.android.core.CrashlyticsCore;
import p004b.p005a.p006a.p007a.Fabric;

/* renamed from: b.a.a.a.a.b.g */
public class ApiKey {
    public String m141a(Context context) {
        Object b = m142b(context);
        if (TextUtils.isEmpty(b)) {
            b = m143c(context);
        }
        if (TextUtils.isEmpty(b)) {
            m144d(context);
        }
        return b;
    }

    protected String m142b(Context context) {
        String str = null;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).metaData;
            if (bundle != null) {
                str = bundle.getString("io.fabric.ApiKey");
                if (str == null) {
                    Fabric.m512h().m474a(CrashlyticsCore.TAG, "Falling back to Crashlytics key lookup from Manifest");
                    str = bundle.getString("com.crashlytics.ApiKey");
                }
            }
        } catch (Exception e) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Caught non-fatal exception while retrieving apiKey: " + e);
        }
        return str;
    }

    protected String m143c(Context context) {
        int a = CommonUtils.m148a(context, "io.fabric.ApiKey", "string");
        if (a == 0) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Falling back to Crashlytics key lookup from Strings");
            a = CommonUtils.m148a(context, "com.crashlytics.ApiKey", "string");
        }
        if (a != 0) {
            return context.getResources().getString(a);
        }
        return null;
    }

    protected void m144d(Context context) {
        if (Fabric.m513i() || CommonUtils.m186i(context)) {
            throw new IllegalArgumentException(m140a());
        }
        Fabric.m512h().m481e(CrashlyticsCore.TAG, m140a());
    }

    protected String m140a() {
        return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
    }
}
