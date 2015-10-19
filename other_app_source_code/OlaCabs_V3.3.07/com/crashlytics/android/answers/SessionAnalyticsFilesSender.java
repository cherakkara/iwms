package com.crashlytics.android.answers;

import java.io.File;
import java.util.List;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.ResponseParser;
import p004b.p005a.p006a.p007a.p008a.p013d.FilesSender;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpMethod;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequest;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;

class SessionAnalyticsFilesSender extends AbstractSpiCall implements FilesSender {
    static final String FILE_CONTENT_TYPE = "application/vnd.crashlytics.android.events";
    static final String FILE_PARAM_NAME = "session_analytics_file_";
    private final String apiKey;

    public SessionAnalyticsFilesSender(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, String str3) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.POST);
        this.apiKey = str3;
    }

    public boolean send(List<File> list) {
        HttpRequest applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), this.apiKey), list);
        CommonUtils.m164a(Answers.getInstance().getContext(), "Sending " + list.size() + " analytics files to " + getUrl());
        int b = applyMultipartDataTo.m378b();
        CommonUtils.m164a(Answers.getInstance().getContext(), "Response code for analytics file send is " + b);
        return ResponseParser.m254a(b) == 0;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, String str) {
        return httpRequest.m368a(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).m368a(AbstractSpiCall.HEADER_CLIENT_VERSION, Answers.getInstance().getVersion()).m368a(AbstractSpiCall.HEADER_API_KEY, str);
    }

    private HttpRequest applyMultipartDataTo(HttpRequest httpRequest, List<File> list) {
        int i = 0;
        for (File file : list) {
            CommonUtils.m164a(Answers.getInstance().getContext(), "Adding analytics session file " + file.getName() + " to multipart POST");
            httpRequest.m371a(FILE_PARAM_NAME + i, file.getName(), FILE_CONTENT_TYPE, file);
            i++;
        }
        return httpRequest;
    }
}
