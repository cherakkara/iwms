package com.newrelic.agent.android.instrumentation.okhttp2;

import com.newrelic.agent.android.instrumentation.HttpURLConnectionExtension;
import com.newrelic.agent.android.instrumentation.ReplaceCallSite;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.net.HttpURLConnection;
import java.net.URL;

public class OkHttp2Instrumentation {
    private static final AgentLog log;

    static {
        log = AgentLogManager.getAgentLog();
    }

    private OkHttp2Instrumentation() {
    }

    @ReplaceCallSite
    public static Request build(Builder builder) {
        return new RequestBuilderExtension(builder).build();
    }

    @ReplaceCallSite
    public static Call newCall(OkHttpClient okHttpClient, Request request) {
        return new CallExtension(okHttpClient, request, okHttpClient.newCall(request));
    }

    @ReplaceCallSite
    public static Response.Builder body(Response.Builder builder, ResponseBody responseBody) {
        return new ResponseBuilderExtension(builder).body(responseBody);
    }

    @ReplaceCallSite
    public static Response.Builder newBuilder(Response.Builder builder) {
        return new ResponseBuilderExtension(builder);
    }

    @ReplaceCallSite
    public static HttpURLConnection open(OkUrlFactory okUrlFactory, URL url) {
        return new HttpURLConnectionExtension(okUrlFactory.open(url));
    }
}
