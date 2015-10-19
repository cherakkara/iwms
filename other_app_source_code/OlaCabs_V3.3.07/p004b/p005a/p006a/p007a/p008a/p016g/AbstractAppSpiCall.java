package p004b.p005a.p006a.p007a.p008a.p016g;

import com.crashlytics.android.core.CrashlyticsCore;
import com.olacabs.customer.p076d.da;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Locale;
import org.apache.http.protocol.HTTP;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.KitInfo;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.ResponseParser;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpMethod;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequest;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;

/* renamed from: b.a.a.a.a.g.a */
abstract class AbstractAppSpiCall extends AbstractSpiCall {
    public AbstractAppSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    public boolean m424a(AppRequestData appRequestData) {
        HttpRequest b = m422b(m421a(getHttpRequest(), appRequestData), appRequestData);
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Sending app info to " + getUrl());
        if (appRequestData.f331j != null) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "App icon hash is " + appRequestData.f331j.f353a);
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "App icon size is " + appRequestData.f331j.f355c + "x" + appRequestData.f331j.f356d);
        }
        int b2 = b.m378b();
        Fabric.m512h().m474a(CrashlyticsCore.TAG, ("POST".equals(b.m402p()) ? "Create" : "Update") + " app request ID: " + b.m380b(da.X_REQUEST_ID));
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Result was " + b2);
        if (ResponseParser.m254a(b2) == 0) {
            return true;
        }
        return false;
    }

    private HttpRequest m421a(HttpRequest httpRequest, AppRequestData appRequestData) {
        return httpRequest.m368a(AbstractSpiCall.HEADER_API_KEY, appRequestData.f322a).m368a(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).m368a(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
    }

    private HttpRequest m422b(HttpRequest httpRequest, AppRequestData appRequestData) {
        HttpRequest e = httpRequest.m388e("app[identifier]", appRequestData.f323b).m388e("app[name]", appRequestData.f327f).m388e("app[display_version]", appRequestData.f324c).m388e("app[build_version]", appRequestData.f325d).m367a("app[source]", Integer.valueOf(appRequestData.f328g)).m388e("app[minimum_sdk_version]", appRequestData.f329h).m388e("app[built_sdk_version]", appRequestData.f330i);
        if (!CommonUtils.m180c(appRequestData.f326e)) {
            e.m388e("app[instance_identifier]", appRequestData.f326e);
        }
        if (appRequestData.f331j != null) {
            Closeable closeable = null;
            try {
                closeable = this.kit.getContext().getResources().openRawResource(appRequestData.f331j.f354b);
                e.m388e("app[icon][hash]", appRequestData.f331j.f353a).m372a("app[icon][data]", "icon.png", HTTP.OCTET_STREAM_TYPE, (InputStream) closeable).m367a("app[icon][width]", Integer.valueOf(appRequestData.f331j.f355c)).m367a("app[icon][height]", Integer.valueOf(appRequestData.f331j.f356d));
            } catch (Throwable e2) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to find app icon with resource ID: " + appRequestData.f331j.f354b, e2);
            } finally {
                String str = "Failed to close app icon InputStream.";
                CommonUtils.m167a(closeable, str);
            }
        }
        if (appRequestData.f332k != null) {
            for (KitInfo kitInfo : appRequestData.f332k) {
                e.m388e(m423a(kitInfo), kitInfo.m538b());
                e.m388e(m425b(kitInfo), kitInfo.m539c());
            }
        }
        return e;
    }

    String m423a(KitInfo kitInfo) {
        return String.format(Locale.US, "app[build][libraries][%s][version]", new Object[]{kitInfo.m537a()});
    }

    String m425b(KitInfo kitInfo) {
        return String.format(Locale.US, "app[build][libraries][%s][type]", new Object[]{kitInfo.m537a()});
    }
}
