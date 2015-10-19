package p004b.p005a.p006a.p007a.p008a.p010b;

import android.content.Context;
import com.crashlytics.android.core.CrashlyticsCore;
import p004b.p005a.p006a.p007a.Fabric;

/* renamed from: b.a.a.a.a.b.d */
class AdvertisingInfoReflectionStrategy implements AdvertisingInfoStrategy {
    private final Context f121a;

    public AdvertisingInfoReflectionStrategy(Context context) {
        this.f121a = context.getApplicationContext();
    }

    boolean m135a(Context context) {
        try {
            if (((Integer) Class.forName("com.google.android.gms.common.e").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{context})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public AdvertisingInfo m134a() {
        if (m135a(this.f121a)) {
            return new AdvertisingInfo(m131b(), m132c());
        }
        return null;
    }

    private String m131b() {
        try {
            return (String) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId", new Class[0]).invoke(m133d(), new Object[0]);
        } catch (Exception e) {
            Fabric.m512h().m479d(CrashlyticsCore.TAG, "Could not call getId on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return null;
        }
    }

    private boolean m132c() {
        try {
            return ((Boolean) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(m133d(), new Object[0])).booleanValue();
        } catch (Exception e) {
            Fabric.m512h().m479d(CrashlyticsCore.TAG, "Could not call isLimitAdTrackingEnabled on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return false;
        }
    }

    private Object m133d() {
        Object obj = null;
        try {
            obj = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.f121a});
        } catch (Exception e) {
            Fabric.m512h().m479d(CrashlyticsCore.TAG, "Could not call getAdvertisingIdInfo on com.google.android.gms.ads.identifier.AdvertisingIdClient");
        }
        return obj;
    }
}
