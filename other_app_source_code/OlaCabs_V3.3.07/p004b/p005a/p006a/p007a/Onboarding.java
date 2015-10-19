package p004b.p005a.p006a.p007a;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.crashlytics.android.core.CrashlyticsCore;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.apache.http.entity.mime.MIME;
import p004b.p005a.p006a.p007a.p008a.p010b.ApiKey;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.DeliveryMechanism;
import p004b.p005a.p006a.p007a.p008a.p014e.DefaultHttpRequestFactory;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;
import p004b.p005a.p006a.p007a.p008a.p016g.AppRequestData;
import p004b.p005a.p006a.p007a.p008a.p016g.AppSettingsData;
import p004b.p005a.p006a.p007a.p008a.p016g.CreateAppSpiCall;
import p004b.p005a.p006a.p007a.p008a.p016g.IconRequest;
import p004b.p005a.p006a.p007a.p008a.p016g.Settings;
import p004b.p005a.p006a.p007a.p008a.p016g.SettingsData;
import p004b.p005a.p006a.p007a.p008a.p016g.UpdateAppSpiCall;

/* renamed from: b.a.a.a.m */
class Onboarding extends Kit<Boolean> {
    private final HttpRequestFactory f434a;
    private PackageManager f435b;
    private String f436c;
    private PackageInfo f437d;
    private String f438e;
    private String f439f;
    private String f440g;
    private String f441h;
    private String f442i;
    private final Future<Map<String, KitInfo>> f443j;
    private final Collection<Kit> f444k;

    protected /* synthetic */ Object doInBackground() {
        return m546a();
    }

    public Onboarding(Future<Map<String, KitInfo>> future, Collection<Kit> collection) {
        this.f434a = new DefaultHttpRequestFactory();
        this.f443j = future;
        this.f444k = collection;
    }

    public String getVersion() {
        return "1.3.4.60";
    }

    protected boolean onPreExecute() {
        try {
            this.f440g = getIdManager().m218h();
            this.f435b = getContext().getPackageManager();
            this.f436c = getContext().getPackageName();
            this.f437d = this.f435b.getPackageInfo(this.f436c, 0);
            this.f438e = Integer.toString(this.f437d.versionCode);
            this.f439f = this.f437d.versionName == null ? "0.0" : this.f437d.versionName;
            this.f441h = this.f435b.getApplicationLabel(getContext().getApplicationInfo()).toString();
            this.f442i = Integer.toString(getContext().getApplicationInfo().targetSdkVersion);
            return true;
        } catch (Throwable e) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed init", e);
            return false;
        }
    }

    protected Boolean m546a() {
        boolean a;
        String k = CommonUtils.m188k(getContext());
        SettingsData c = m544c();
        if (c != null) {
            try {
                Map map;
                if (this.f443j != null) {
                    map = (Map) this.f443j.get();
                } else {
                    map = new HashMap();
                }
                a = m542a(k, c.f379a, m547a(map, this.f444k).values());
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Error performing auto configuration.", e);
            }
            return Boolean.valueOf(a);
        }
        a = false;
        return Boolean.valueOf(a);
    }

    private SettingsData m544c() {
        try {
            Settings.m462a().m464a(this, this.idManager, this.f434a, this.f438e, this.f439f, m548b()).m467c();
            return Settings.m462a().m466b();
        } catch (Throwable e) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Error dealing with settings", e);
            return null;
        }
    }

    Map<String, KitInfo> m547a(Map<String, KitInfo> map, Collection<Kit> collection) {
        for (Kit kit : collection) {
            if (!map.containsKey(kit.getIdentifier())) {
                map.put(kit.getIdentifier(), new KitInfo(kit.getIdentifier(), kit.getVersion(), MIME.ENC_BINARY));
            }
        }
        return map;
    }

    public String getIdentifier() {
        return "io.fabric.sdk.android:fabric";
    }

    private boolean m542a(String str, AppSettingsData appSettingsData, Collection<KitInfo> collection) {
        if ("new".equals(appSettingsData.f334b)) {
            if (m543b(str, appSettingsData, collection)) {
                return Settings.m462a().m468d();
            }
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to create app with Crashlytics service.", null);
            return false;
        } else if ("configured".equals(appSettingsData.f334b)) {
            return Settings.m462a().m468d();
        } else {
            if (!appSettingsData.f337e) {
                return true;
            }
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Server says an update is required - forcing a full App update.");
            m545c(str, appSettingsData, collection);
            return true;
        }
    }

    private boolean m543b(String str, AppSettingsData appSettingsData, Collection<KitInfo> collection) {
        return new CreateAppSpiCall(this, m548b(), appSettingsData.f335c, this.f434a).m428a(m540a(IconRequest.m460a(getContext(), str), (Collection) collection));
    }

    private boolean m545c(String str, AppSettingsData appSettingsData, Collection<KitInfo> collection) {
        return m541a(appSettingsData, IconRequest.m460a(getContext(), str), (Collection) collection);
    }

    private boolean m541a(AppSettingsData appSettingsData, IconRequest iconRequest, Collection<KitInfo> collection) {
        return new UpdateAppSpiCall(this, m548b(), appSettingsData.f335c, this.f434a).m470a(m540a(iconRequest, (Collection) collection));
    }

    private AppRequestData m540a(IconRequest iconRequest, Collection<KitInfo> collection) {
        return new AppRequestData(new ApiKey().m141a(getContext()), getIdManager().m213c(), this.f439f, this.f438e, CommonUtils.m161a(CommonUtils.m190m(r0)), this.f441h, DeliveryMechanism.m194a(this.f440g).m195a(), this.f442i, "0", iconRequest, collection);
    }

    String m548b() {
        return CommonUtils.m175b(getContext(), "com.crashlytics.ApiEndpoint");
    }
}
