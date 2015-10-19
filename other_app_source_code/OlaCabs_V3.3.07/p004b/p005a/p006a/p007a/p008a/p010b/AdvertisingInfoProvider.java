package p004b.p005a.p006a.p007a.p008a.p010b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.crashlytics.android.core.CrashlyticsCore;
import com.newrelic.agent.android.instrumentation.Trace;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.p008a.p015f.PreferenceStore;
import p004b.p005a.p006a.p007a.p008a.p015f.PreferenceStoreImpl;

/* renamed from: b.a.a.a.a.b.c */
class AdvertisingInfoProvider {
    private final Context f119a;
    private final PreferenceStore f120b;

    /* renamed from: b.a.a.a.a.b.c.1 */
    class AdvertisingInfoProvider extends BackgroundPriorityRunnable {
        final /* synthetic */ AdvertisingInfo f117a;
        final /* synthetic */ AdvertisingInfoProvider f118b;

        AdvertisingInfoProvider(AdvertisingInfoProvider advertisingInfoProvider, AdvertisingInfo advertisingInfo) {
            this.f118b = advertisingInfoProvider;
            this.f117a = advertisingInfo;
        }

        public void onRun() {
            AdvertisingInfo a = this.f118b.m125e();
            if (!this.f117a.equals(a)) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Asychronously getting Advertising Info and storing it to preferences");
                this.f118b.m123b(a);
            }
        }
    }

    public AdvertisingInfoProvider(Context context) {
        this.f119a = context.getApplicationContext();
        this.f120b = new PreferenceStoreImpl(context, "TwitterAdvertisingInfoPreferences");
    }

    public AdvertisingInfo m126a() {
        AdvertisingInfo b = m127b();
        if (m124c(b)) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Using AdvertisingInfo from Preference Store");
            m121a(b);
            return b;
        }
        b = m125e();
        m123b(b);
        return b;
    }

    private void m121a(AdvertisingInfo advertisingInfo) {
        new Thread(new AdvertisingInfoProvider(this, advertisingInfo)).start();
    }

    @SuppressLint({"CommitPrefEdits"})
    private void m123b(AdvertisingInfo advertisingInfo) {
        if (m124c(advertisingInfo)) {
            this.f120b.m416a(this.f120b.m417b().putString("advertising_id", advertisingInfo.f115a).putBoolean("limit_ad_tracking_enabled", advertisingInfo.f116b));
        } else {
            this.f120b.m416a(this.f120b.m417b().remove("advertising_id").remove("limit_ad_tracking_enabled"));
        }
    }

    protected AdvertisingInfo m127b() {
        return new AdvertisingInfo(this.f120b.m415a().getString("advertising_id", Trace.NULL), this.f120b.m415a().getBoolean("limit_ad_tracking_enabled", false));
    }

    public AdvertisingInfoStrategy m128c() {
        return new AdvertisingInfoReflectionStrategy(this.f119a);
    }

    public AdvertisingInfoStrategy m129d() {
        return new AdvertisingInfoServiceStrategy(this.f119a);
    }

    private boolean m124c(AdvertisingInfo advertisingInfo) {
        return (advertisingInfo == null || TextUtils.isEmpty(advertisingInfo.f115a)) ? false : true;
    }

    private AdvertisingInfo m125e() {
        AdvertisingInfo a = m128c().m130a();
        if (m124c(a)) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Using AdvertisingInfo from Reflection Provider");
        } else {
            a = m129d().m130a();
            if (m124c(a)) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Using AdvertisingInfo from Service Provider");
            } else {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "AdvertisingInfo not present");
            }
        }
        return a;
    }
}
