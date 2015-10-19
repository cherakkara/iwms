package com.newrelic.agent.android.instrumentation.okhttp2;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.squareup.okhttp.Handshake;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Response.Builder;
import com.squareup.okhttp.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class ResponseBuilderExtension extends Builder {
    private static final AgentLog log;
    private Builder impl;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public ResponseBuilderExtension(Builder builder) {
        this.impl = builder;
    }

    public Builder request(Request request) {
        return this.impl.request(request);
    }

    public Builder protocol(Protocol protocol) {
        return this.impl.protocol(protocol);
    }

    public Builder code(int i) {
        return this.impl.code(i);
    }

    public Builder message(String str) {
        return this.impl.message(str);
    }

    public Builder handshake(Handshake handshake) {
        return this.impl.handshake(handshake);
    }

    public Builder header(String str, String str2) {
        return this.impl.header(str, str2);
    }

    public Builder addHeader(String str, String str2) {
        return this.impl.addHeader(str, str2);
    }

    public Builder removeHeader(String str) {
        return this.impl.removeHeader(str);
    }

    public Builder headers(Headers headers) {
        return this.impl.headers(headers);
    }

    public Builder body(ResponseBody responseBody) {
        BufferedSource source = responseBody.source();
        Buffer buffer = new Buffer();
        try {
            source.readAll(buffer);
        } catch (Throwable e) {
            log.error("IOException reading from source: ", e);
        }
        return this.impl.body(new PrebufferedResponseBody(responseBody, buffer));
    }

    public Builder networkResponse(Response response) {
        return this.impl.networkResponse(response);
    }

    public Builder cacheResponse(Response response) {
        return this.impl.cacheResponse(response);
    }

    public Builder priorResponse(Response response) {
        return this.impl.priorResponse(response);
    }

    public Response build() {
        return this.impl.build();
    }
}
