package com.apsalar.sdk;

import android.os.Build.VERSION;
import com.newrelic.agent.android.api.v1.Defaults;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpRequestExecutor;

class ApsalarHttpClient {
    static final String TAG = "Apsalar SDK/HttpClient";
    static int responseSize;

    ApsalarHttpClient() {
    }

    static {
        responseSize = Defaults.RESPONSE_BODY_LIMIT;
    }

    private static void disableConnectionReuseIfNecessary() {
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private static void setHttpURLConnectionDefaults(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setConnectTimeout(HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE);
        httpURLConnection.setReadTimeout(HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        disableConnectionReuseIfNecessary();
    }

    private static String readAsString(InputStream inputStream) throws IOException, UnsupportedEncodingException {
        char[] cArr = new char[responseSize];
        new InputStreamReader(inputStream, HTTP.UTF_8).read(cArr);
        int i = 0;
        while (i < responseSize && cArr[i] != '\u0000') {
            i++;
        }
        return new String(cArr, 0, i);
    }

    static String get(String str) throws IOException, SocketTimeoutException {
        Throwable th;
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        instance.getClass();
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) HttpInstrumentation.openConnection(new URL(str).openConnection());
            try {
                setHttpURLConnectionDefaults(httpURLConnection2);
                httpURLConnection2.connect();
                String readAsString = readAsString(httpURLConnection2.getInputStream());
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                instance.getClass();
                return readAsString;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                httpURLConnection = httpURLConnection2;
                th = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
