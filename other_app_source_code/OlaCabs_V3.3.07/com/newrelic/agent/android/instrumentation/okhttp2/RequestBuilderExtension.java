package com.newrelic.agent.android.instrumentation.okhttp2;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import java.net.URL;
import java.util.Locale;

public class RequestBuilderExtension extends Builder {
    private static final AgentLog log;
    private Builder impl;
    private TransactionState transactionState;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public RequestBuilderExtension(Builder builder) {
        this.impl = builder;
        setCrossProcessHeader();
    }

    public Builder url(String str) {
        return this.impl.url(str);
    }

    public Builder url(URL url) {
        return this.impl.url(url);
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

    public Builder cacheControl(CacheControl cacheControl) {
        return this.impl.cacheControl(cacheControl);
    }

    public Builder get() {
        return this.impl.get();
    }

    public Builder head() {
        return this.impl.head();
    }

    public Builder post(RequestBody requestBody) {
        return this.impl.post(requestBody);
    }

    public Builder delete() {
        return this.impl.delete();
    }

    public Builder put(RequestBody requestBody) {
        return this.impl.put(requestBody);
    }

    public Builder patch(RequestBody requestBody) {
        return this.impl.patch(requestBody);
    }

    public Builder method(String str, RequestBody requestBody) {
        return this.impl.method(str, requestBody);
    }

    public Builder tag(Object obj) {
        return this.impl.tag(obj);
    }

    public Request build() {
        return this.impl.build();
    }

    private void setCrossProcessHeader() {
        String crossProcessId = Agent.getCrossProcessId();
        if (crossProcessId != null) {
            this.impl.addHeader(TransactionStateUtil.CROSS_PROCESS_ID_HEADER.toLowerCase(Locale.ENGLISH), crossProcessId);
        }
    }
}
