package p004b.p005a.p006a.p007a.p008a.p016g;

import com.crashlytics.android.core.CrashlyticsCore;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.olacabs.customer.p076d.da;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpMethod;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequest;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;

/* renamed from: b.a.a.a.a.g.l */
class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    public DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        this(kit, str, str2, httpRequestFactory, HttpMethod.GET);
    }

    DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    public JSONObject m458a(SettingsRequest settingsRequest) {
        HttpRequest httpRequest = null;
        try {
            Map b = m456b(settingsRequest);
            httpRequest = m454a(getHttpRequest(b), settingsRequest);
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Requesting settings from " + getUrl());
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Settings query params were: " + b);
            JSONObject a = m457a(httpRequest);
            return a;
        } finally {
            if (httpRequest != null) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Settings request ID: " + httpRequest.m380b(da.X_REQUEST_ID));
            }
        }
    }

    JSONObject m457a(HttpRequest httpRequest) {
        int b = httpRequest.m378b();
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Settings result was: " + b);
        if (m459a(b)) {
            return m455a(httpRequest.m389e());
        }
        Fabric.m512h().m481e(CrashlyticsCore.TAG, "Failed to retrieve settings from " + getUrl());
        return null;
    }

    boolean m459a(int i) {
        return i == HttpStatus.SC_OK || i == HttpStatus.SC_CREATED || i == HttpStatus.SC_ACCEPTED || i == HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION;
    }

    private JSONObject m455a(String str) {
        try {
            return JSONObjectInstrumentation.init(str);
        } catch (Throwable e) {
            Fabric.m512h().m475a(CrashlyticsCore.TAG, "Failed to parse settings JSON from " + getUrl(), e);
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Settings response " + str);
            return null;
        }
    }

    private Map<String, String> m456b(SettingsRequest settingsRequest) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("build_version", settingsRequest.f393e);
        hashMap.put("display_version", settingsRequest.f392d);
        hashMap.put("source", Integer.toString(settingsRequest.f394f));
        if (settingsRequest.f395g != null) {
            hashMap.put("icon_hash", settingsRequest.f395g);
        }
        String str = settingsRequest.f391c;
        if (!CommonUtils.m180c(str)) {
            hashMap.put("instance", str);
        }
        return hashMap;
    }

    private HttpRequest m454a(HttpRequest httpRequest, SettingsRequest settingsRequest) {
        return httpRequest.m368a(AbstractSpiCall.HEADER_API_KEY, settingsRequest.f389a).m368a(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).m368a(AbstractSpiCall.HEADER_D, settingsRequest.f390b).m368a(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion()).m368a(HttpHeaders.ACCEPT, AbstractSpiCall.ACCEPT_JSON_VALUE);
    }
}
