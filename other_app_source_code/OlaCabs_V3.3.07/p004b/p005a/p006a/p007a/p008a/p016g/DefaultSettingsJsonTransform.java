package p004b.p005a.p006a.p007a.p008a.p016g;

import android.support.v4.view.MotionEventCompat;
import com.crashlytics.android.beta.BuildConfig;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;
import p004b.p005a.p006a.p007a.p008a.p010b.CurrentTimeProvider;

/* renamed from: b.a.a.a.a.g.k */
class DefaultSettingsJsonTransform implements SettingsJsonTransform {
    DefaultSettingsJsonTransform() {
    }

    public SettingsData m451a(CurrentTimeProvider currentTimeProvider, JSONObject jSONObject) throws JSONException {
        int optInt = jSONObject.optInt("settings_version", 0);
        int optInt2 = jSONObject.optInt("cache_duration", 3600);
        return new SettingsData(m443a(currentTimeProvider, (long) optInt2, jSONObject), m444b(jSONObject.getJSONObject("app")), m448f(jSONObject.getJSONObject("session")), m449g(jSONObject.getJSONObject("prompt")), m446d(jSONObject.getJSONObject("features")), m447e(jSONObject.getJSONObject("analytics")), m450h(jSONObject.getJSONObject(BuildConfig.ARTIFACT_ID)), optInt, optInt2);
    }

    public JSONObject m452a(JSONObject jSONObject) throws JSONException {
        JSONObject init = JSONObjectInstrumentation.init(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
        init.getJSONObject("features").remove("collect_analytics");
        init.remove("analytics");
        return init;
    }

    private AppSettingsData m444b(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("identifier");
        String string2 = jSONObject.getString(NotificationCompatApi21.CATEGORY_STATUS);
        String string3 = jSONObject.getString("url");
        String string4 = jSONObject.getString("reports_url");
        boolean optBoolean = jSONObject.optBoolean("update_required", false);
        AppIconSettingsData appIconSettingsData = null;
        if (jSONObject.has("icon") && jSONObject.getJSONObject("icon").has("hash")) {
            appIconSettingsData = m445c(jSONObject.getJSONObject("icon"));
        }
        return new AppSettingsData(string, string2, string3, string4, optBoolean, appIconSettingsData);
    }

    private AppIconSettingsData m445c(JSONObject jSONObject) throws JSONException {
        return new AppIconSettingsData(jSONObject.getString("hash"), jSONObject.getInt("width"), jSONObject.getInt("height"));
    }

    private FeaturesSettingsData m446d(JSONObject jSONObject) {
        return new FeaturesSettingsData(jSONObject.optBoolean("prompt_enabled", false), jSONObject.optBoolean("collect_logged_exceptions", true), jSONObject.optBoolean("collect_reports", true), jSONObject.optBoolean("collect_analytics", false));
    }

    private AnalyticsSettingsData m447e(JSONObject jSONObject) {
        return new AnalyticsSettingsData(jSONObject.optString("url", "https://e.crashlytics.com/spi/v2/events"), jSONObject.optInt("flush_interval_secs", 600), jSONObject.optInt("max_byte_size_per_file", 8000), jSONObject.optInt("max_file_count_per_send", 1), jSONObject.optInt("max_pending_send_file_count", 100), jSONObject.optBoolean("track_custom_events", true), jSONObject.optInt("sampling_rate", 1));
    }

    private SessionSettingsData m448f(JSONObject jSONObject) throws JSONException {
        return new SessionSettingsData(jSONObject.optInt("log_buffer_size", 64000), jSONObject.optInt("max_chained_exception_depth", 8), jSONObject.optInt("max_custom_exception_events", 64), jSONObject.optInt("max_custom_key_value_pairs", 64), jSONObject.optInt("identifier_mask", MotionEventCompat.ACTION_MASK), jSONObject.optBoolean("send_session_without_crash", false));
    }

    private PromptSettingsData m449g(JSONObject jSONObject) throws JSONException {
        return new PromptSettingsData(jSONObject.optString("title", "Send Crash Report?"), jSONObject.optString("message", "Looks like we crashed! Please help us fix the problem by sending a crash report."), jSONObject.optString("send_button_title", "Send"), jSONObject.optBoolean("show_cancel_button", true), jSONObject.optString("cancel_button_title", "Don't Send"), jSONObject.optBoolean("show_always_send_button", true), jSONObject.optString("always_send_button_title", "Always Send"));
    }

    private BetaSettingsData m450h(JSONObject jSONObject) throws JSONException {
        return new BetaSettingsData(jSONObject.optString("update_endpoint", SettingsJsonConstants.f388a), jSONObject.optInt("update_suspend_duration", 3600));
    }

    private long m443a(CurrentTimeProvider currentTimeProvider, long j, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("expires_at")) {
            return jSONObject.getLong("expires_at");
        }
        return currentTimeProvider.m193a() + (1000 * j);
    }
}
