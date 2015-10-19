package com.crashlytics.android.core;

import com.olacabs.customer.p076d.da;
import java.util.Map.Entry;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;
import p004b.p005a.p006a.p007a.p008a.p010b.ResponseParser;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpMethod;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequest;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;

class DefaultCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    static final String FILE_CONTENT_TYPE = "application/octet-stream";
    static final String FILE_PARAM = "report[file]";
    static final String IDENTIFIER_PARAM = "report[identifier]";

    public DefaultCreateReportSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.POST);
    }

    DefaultCreateReportSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    public boolean invoke(CreateReportRequest createReportRequest) {
        HttpRequest applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), createReportRequest), createReportRequest);
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Sending report to: " + getUrl());
        int b = applyMultipartDataTo.m378b();
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Create report request ID: " + applyMultipartDataTo.m380b(da.X_REQUEST_ID));
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Result was: " + b);
        return ResponseParser.m254a(b) == 0;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, CreateReportRequest createReportRequest) {
        HttpRequest a = httpRequest.m368a(AbstractSpiCall.HEADER_API_KEY, createReportRequest.apiKey).m368a(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).m368a(AbstractSpiCall.HEADER_CLIENT_VERSION, CrashlyticsCore.getInstance().getVersion());
        HttpRequest httpRequest2 = a;
        for (Entry a2 : createReportRequest.report.getCustomHeaders().entrySet()) {
            httpRequest2 = httpRequest2.m374a(a2);
        }
        return httpRequest2;
    }

    private HttpRequest applyMultipartDataTo(HttpRequest httpRequest, CreateReportRequest createReportRequest) {
        Report report = createReportRequest.report;
        return httpRequest.m371a(FILE_PARAM, report.getFileName(), FILE_CONTENT_TYPE, report.getFile()).m388e(IDENTIFIER_PARAM, report.getIdentifier());
    }
}
