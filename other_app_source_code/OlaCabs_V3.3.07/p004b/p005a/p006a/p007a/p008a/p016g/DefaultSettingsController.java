package p004b.p005a.p006a.p007a.p008a.p016g;

import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;
import com.crashlytics.android.core.CrashlyticsCore;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import org.json.JSONException;
import org.json.JSONObject;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.CurrentTimeProvider;
import p004b.p005a.p006a.p007a.p008a.p015f.PreferenceStore;
import p004b.p005a.p006a.p007a.p008a.p015f.PreferenceStoreImpl;

/* renamed from: b.a.a.a.a.g.j */
class DefaultSettingsController implements SettingsController {
    private final SettingsRequest f342a;
    private final SettingsJsonTransform f343b;
    private final CurrentTimeProvider f344c;
    private final CachedSettingsIo f345d;
    private final SettingsSpiCall f346e;
    private final Kit f347f;
    private final PreferenceStore f348g;

    public DefaultSettingsController(Kit kit, SettingsRequest settingsRequest, CurrentTimeProvider currentTimeProvider, SettingsJsonTransform settingsJsonTransform, CachedSettingsIo cachedSettingsIo, SettingsSpiCall settingsSpiCall) {
        this.f347f = kit;
        this.f342a = settingsRequest;
        this.f344c = currentTimeProvider;
        this.f343b = settingsJsonTransform;
        this.f345d = cachedSettingsIo;
        this.f346e = settingsSpiCall;
        this.f348g = new PreferenceStoreImpl(this.f347f);
    }

    public SettingsData m435a() {
        return m436a(SettingsCacheBehavior.USE_CACHE);
    }

    public SettingsData m436a(SettingsCacheBehavior settingsCacheBehavior) {
        Throwable th;
        SettingsData settingsData;
        Throwable th2;
        SettingsData settingsData2 = null;
        try {
            if (!(Fabric.m513i() || m440d())) {
                settingsData2 = m434b(settingsCacheBehavior);
            }
            if (settingsData2 == null) {
                try {
                    JSONObject a = this.f346e.m453a(this.f342a);
                    if (a != null) {
                        settingsData2 = this.f343b.m441a(this.f344c, a);
                        this.f345d.m427a(settingsData2.f385g, a);
                        m433a(a, "Loaded settings: ");
                        m437a(m438b());
                    }
                } catch (Throwable e) {
                    th = e;
                    settingsData = settingsData2;
                    th2 = th;
                    Fabric.m512h().m482e(CrashlyticsCore.TAG, "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
                    return settingsData;
                }
            }
            settingsData = settingsData2;
            if (settingsData == null) {
                try {
                    settingsData = m434b(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
                } catch (Exception e2) {
                    th2 = e2;
                    Fabric.m512h().m482e(CrashlyticsCore.TAG, "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
                    return settingsData;
                }
            }
        } catch (Throwable e3) {
            th = e3;
            settingsData = null;
            th2 = th;
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
            return settingsData;
        }
        return settingsData;
    }

    private SettingsData m434b(SettingsCacheBehavior settingsCacheBehavior) {
        Throwable th;
        SettingsData settingsData = null;
        try {
            if (SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(settingsCacheBehavior)) {
                return null;
            }
            JSONObject a = this.f345d.m426a();
            if (a != null) {
                SettingsData a2 = this.f343b.m441a(this.f344c, a);
                if (a2 != null) {
                    m433a(a, "Loaded cached settings: ");
                    long a3 = this.f344c.m193a();
                    if (SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(settingsCacheBehavior) || !a2.m469a(a3)) {
                        try {
                            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Returning cached settings.");
                            return a2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            settingsData = a2;
                            th = th2;
                            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to get cached settings", th);
                            return settingsData;
                        }
                    }
                    Fabric.m512h().m474a(CrashlyticsCore.TAG, "Cached settings have expired.");
                    return null;
                }
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to transform cached settings data.", null);
                return null;
            }
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "No cached settings data found.");
            return null;
        } catch (Exception e2) {
            th = e2;
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to get cached settings", th);
            return settingsData;
        }
    }

    private void m433a(JSONObject jSONObject, String str) throws JSONException {
        JSONObject jSONObject2;
        if (CommonUtils.m182e(this.f347f.getContext())) {
            jSONObject2 = jSONObject;
        } else {
            jSONObject2 = this.f343b.m442a(jSONObject);
        }
        Fabric.m512h().m474a(CrashlyticsCore.TAG, str + (!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : JSONObjectInstrumentation.toString(jSONObject2)));
    }

    String m438b() {
        return CommonUtils.m161a(CommonUtils.m190m(this.f347f.getContext()));
    }

    String m439c() {
        return this.f348g.m415a().getString("existing_instance_identifier", Trace.NULL);
    }

    @SuppressLint({"CommitPrefEdits"})
    boolean m437a(String str) {
        Editor b = this.f348g.m417b();
        b.putString("existing_instance_identifier", str);
        return this.f348g.m416a(b);
    }

    boolean m440d() {
        return !m439c().equals(m438b());
    }
}
