package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

public class OkHttpInstrumentation {
    private static final AgentLog log;

    static {
        log = AgentLogManager.getAgentLog();
    }

    @WrapReturn(className = "com/squareup/okhttp/OkHttpClient", methodDesc = "(Ljava/net/URL;)Ljava/net/HttpURLConnection;", methodName = "open")
    public static HttpURLConnection open(HttpURLConnection httpURLConnection) {
        if (httpURLConnection instanceof HttpsURLConnection) {
            return new HttpsURLConnectionExtension((HttpsURLConnection) httpURLConnection);
        }
        if (httpURLConnection != null) {
            return new HttpURLConnectionExtension(httpURLConnection);
        }
        return null;
    }

    @WrapReturn(className = "com/squareup/okhttp/OkHttpClient", methodDesc = "(Ljava/net/URL;Ljava/net/Proxy)Ljava/net/HttpURLConnection;", methodName = "open")
    public static HttpURLConnection openWithProxy(HttpURLConnection httpURLConnection) {
        if (httpURLConnection instanceof HttpsURLConnection) {
            return new HttpsURLConnectionExtension((HttpsURLConnection) httpURLConnection);
        }
        if (httpURLConnection != null) {
            return new HttpURLConnectionExtension(httpURLConnection);
        }
        return null;
    }

    @WrapReturn(className = "com/squareup/okhttp/OkUrlFactory", methodDesc = "(Ljava/net/URL;)Ljava/net/HttpURLConnection;", methodName = "open")
    public static HttpURLConnection urlFactoryOpen(HttpURLConnection httpURLConnection) {
        log.debug("OkHttpInstrumentation - wrapping return of call to OkUrlFactory.open...");
        if (httpURLConnection instanceof HttpsURLConnection) {
            return new HttpsURLConnectionExtension((HttpsURLConnection) httpURLConnection);
        }
        if (httpURLConnection != null) {
            return new HttpURLConnectionExtension(httpURLConnection);
        }
        return null;
    }
}
