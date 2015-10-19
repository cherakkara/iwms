package com.newrelic.agent.android.instrumentation.retrofit;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.concurrent.Executor;
import retrofit.Endpoint;
import retrofit.ErrorHandler;
import retrofit.Profiler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.RestAdapter.Log;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.Client;
import retrofit.client.Client.Provider;
import retrofit.converter.Converter;

public class RestAdapterBuilderExtension extends Builder {
    private static final AgentLog log;
    private Builder impl;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public RestAdapterBuilderExtension(Builder builder) {
        this.impl = builder;
    }

    public Builder setEndpoint(String str) {
        return this.impl.setEndpoint(str);
    }

    public Builder setEndpoint(Endpoint endpoint) {
        return this.impl.setEndpoint(endpoint);
    }

    public Builder setClient(Client client) {
        log.debug("RestAdapterBuilderExtension.setClient() wrapping client " + client + " with new ClientExtension.");
        return this.impl.setClient(new ClientExtension(client));
    }

    public Builder setClient(Provider provider) {
        return this.impl.setClient(provider);
    }

    public Builder setExecutors(Executor executor, Executor executor2) {
        return this.impl.setExecutors(executor, executor2);
    }

    public Builder setRequestInterceptor(RequestInterceptor requestInterceptor) {
        return this.impl.setRequestInterceptor(requestInterceptor);
    }

    public Builder setConverter(Converter converter) {
        return this.impl.setConverter(converter);
    }

    public Builder setProfiler(Profiler profiler) {
        return this.impl.setProfiler(profiler);
    }

    public Builder setErrorHandler(ErrorHandler errorHandler) {
        return this.impl.setErrorHandler(errorHandler);
    }

    public Builder setLog(Log log) {
        return this.impl.setLog(log);
    }

    public Builder setLogLevel(LogLevel logLevel) {
        return this.impl.setLogLevel(logLevel);
    }

    public RestAdapter build() {
        return this.impl.build();
    }
}
