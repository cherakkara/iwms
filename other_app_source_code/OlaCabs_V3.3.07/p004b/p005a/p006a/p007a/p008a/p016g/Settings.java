package p004b.p005a.p006a.p007a.p008a.p016g;

import android.content.Context;
import com.crashlytics.android.core.CrashlyticsCore;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.p008a.p010b.ApiKey;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.DeliveryMechanism;
import p004b.p005a.p006a.p007a.p008a.p010b.IdManager;
import p004b.p005a.p006a.p007a.p008a.p010b.SystemCurrentTimeProvider;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;

/* renamed from: b.a.a.a.a.g.q */
public class Settings {
    private final AtomicReference<SettingsData> f371a;
    private final CountDownLatch f372b;
    private SettingsController f373c;
    private boolean f374d;

    /* renamed from: b.a.a.a.a.g.q.a */
    static class Settings {
        private static final Settings f370a;

        static {
            f370a = new Settings();
        }
    }

    /* renamed from: b.a.a.a.a.g.q.b */
    public interface Settings<T> {
        T usingSettings(SettingsData settingsData);
    }

    public static Settings m462a() {
        return Settings.f370a;
    }

    private Settings() {
        this.f371a = new AtomicReference();
        this.f372b = new CountDownLatch(1);
        this.f374d = false;
    }

    public synchronized Settings m464a(Kit kit, IdManager idManager, HttpRequestFactory httpRequestFactory, String str, String str2, String str3) {
        Settings settings;
        if (this.f374d) {
            settings = this;
        } else {
            if (this.f373c == null) {
                Context context = kit.getContext();
                String c = idManager.m213c();
                String a = new ApiKey().m141a(context);
                String h = idManager.m218h();
                SystemCurrentTimeProvider systemCurrentTimeProvider = new SystemCurrentTimeProvider();
                DefaultSettingsJsonTransform defaultSettingsJsonTransform = new DefaultSettingsJsonTransform();
                DefaultCachedSettingsIo defaultCachedSettingsIo = new DefaultCachedSettingsIo(kit);
                String k = CommonUtils.m188k(context);
                String str4 = str3;
                DefaultSettingsSpiCall defaultSettingsSpiCall = new DefaultSettingsSpiCall(kit, str4, String.format(Locale.US, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", new Object[]{c}), httpRequestFactory);
                h = str2;
                String str5 = str;
                this.f373c = new DefaultSettingsController(kit, new SettingsRequest(a, idManager.m210a(a, c), CommonUtils.m161a(CommonUtils.m190m(context)), h, str5, DeliveryMechanism.m194a(h).m195a(), k), systemCurrentTimeProvider, defaultSettingsJsonTransform, defaultCachedSettingsIo, defaultSettingsSpiCall);
            }
            this.f374d = true;
            settings = this;
        }
        return settings;
    }

    public <T> T m465a(Settings<T> settings, T t) {
        SettingsData settingsData = (SettingsData) this.f371a.get();
        return settingsData == null ? t : settings.usingSettings(settingsData);
    }

    public SettingsData m466b() {
        try {
            this.f372b.await();
            return (SettingsData) this.f371a.get();
        } catch (InterruptedException e) {
            Fabric.m512h().m481e(CrashlyticsCore.TAG, "Interrupted while waiting for settings data.");
            return null;
        }
    }

    public synchronized boolean m467c() {
        SettingsData a;
        a = this.f373c.m431a();
        m463a(a);
        return a != null;
    }

    public synchronized boolean m468d() {
        SettingsData a;
        a = this.f373c.m432a(SettingsCacheBehavior.SKIP_CACHE_LOOKUP);
        m463a(a);
        if (a == null) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to force reload of settings from Crashlytics.", null);
        }
        return a != null;
    }

    private void m463a(SettingsData settingsData) {
        this.f371a.set(settingsData);
        this.f372b.countDown();
    }
}
