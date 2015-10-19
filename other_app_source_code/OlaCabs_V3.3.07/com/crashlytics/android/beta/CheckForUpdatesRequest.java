package com.crashlytics.android.beta;

import com.crashlytics.android.core.CrashlyticsCore;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.olacabs.customer.p076d.da;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.apache.http.protocol.HTTP;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpMethod;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequest;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;

class CheckForUpdatesRequest extends AbstractSpiCall {
    static final String BETA_SOURCE = "3";
    static final String BUILD_VERSION = "build_version";
    static final String DISPLAY_VERSION = "display_version";
    static final String INSTANCE = "instance";
    static final String SOURCE = "source";
    private final CheckForUpdatesResponseTransform responseTransform;

    public CheckForUpdatesRequest(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, CheckForUpdatesResponseTransform checkForUpdatesResponseTransform) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.GET);
        this.responseTransform = checkForUpdatesResponseTransform;
    }

    public CheckForUpdatesResponse invoke(String str, String str2, BuildProperties buildProperties) {
        HttpRequest applyHeadersTo;
        Throwable e;
        Throwable th;
        CheckForUpdatesResponse checkForUpdatesResponse = null;
        try {
            Map queryParamsFor = getQueryParamsFor(buildProperties);
            try {
                applyHeadersTo = applyHeadersTo(getHttpRequest(queryParamsFor), str, str2);
                Fabric.m512h().m474a(Beta.TAG, "Checking for updates from " + getUrl());
                Fabric.m512h().m474a(Beta.TAG, "Checking for updates query params are: " + queryParamsFor);
                if (applyHeadersTo.m384c()) {
                    Fabric.m512h().m474a(Beta.TAG, "Checking for updates was successful");
                    checkForUpdatesResponse = this.responseTransform.fromJson(JSONObjectInstrumentation.init(applyHeadersTo.m389e()));
                    if (applyHeadersTo != null) {
                        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Checking for updates request ID: " + applyHeadersTo.m380b(da.X_REQUEST_ID));
                    }
                } else {
                    Fabric.m512h().m481e(Beta.TAG, "Checking for updates failed. Response code: " + applyHeadersTo.m378b());
                    if (applyHeadersTo != null) {
                        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Checking for updates request ID: " + applyHeadersTo.m380b(da.X_REQUEST_ID));
                    }
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    Fabric.m512h().m482e(Beta.TAG, "Error while checking for updates from " + getUrl(), e);
                    if (applyHeadersTo != null) {
                        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Checking for updates request ID: " + applyHeadersTo.m380b(da.X_REQUEST_ID));
                    }
                    return checkForUpdatesResponse;
                } catch (Throwable th2) {
                    th = th2;
                    if (applyHeadersTo != null) {
                        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Checking for updates request ID: " + applyHeadersTo.m380b(da.X_REQUEST_ID));
                    }
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            applyHeadersTo = null;
            Fabric.m512h().m482e(Beta.TAG, "Error while checking for updates from " + getUrl(), e);
            if (applyHeadersTo != null) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Checking for updates request ID: " + applyHeadersTo.m380b(da.X_REQUEST_ID));
            }
            return checkForUpdatesResponse;
        } catch (Throwable e4) {
            applyHeadersTo = null;
            th = e4;
            if (applyHeadersTo != null) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Checking for updates request ID: " + applyHeadersTo.m380b(da.X_REQUEST_ID));
            }
            throw th;
        }
        return checkForUpdatesResponse;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, String str, String str2) {
        return httpRequest.m368a(HttpHeaders.ACCEPT, AbstractSpiCall.ACCEPT_JSON_VALUE).m368a(HTTP.USER_AGENT, AbstractSpiCall.CRASHLYTICS_USER_AGENT + this.kit.getVersion()).m368a(AbstractSpiCall.HEADER_DEVELOPER_TOKEN, AbstractSpiCall.CLS_ANDROID_SDK_DEVELOPER_TOKEN).m368a(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).m368a(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion()).m368a(AbstractSpiCall.HEADER_API_KEY, str).m368a(AbstractSpiCall.HEADER_D, str2);
    }

    private Map<String, String> getQueryParamsFor(BuildProperties buildProperties) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(BUILD_VERSION, buildProperties.versionCode);
        hashMap.put(DISPLAY_VERSION, buildProperties.versionName);
        hashMap.put(INSTANCE, buildProperties.buildId);
        hashMap.put(SOURCE, BETA_SOURCE);
        return hashMap;
    }
}
