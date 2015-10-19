package com.newrelic.agent.android.harvest;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import com.newrelic.agent.android.harvest.type.Harvestable;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.stats.TicToc;
import com.newrelic.agent.android.util.ExceptionHelper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.zip.Deflater;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

public class HarvestConnection implements HarvestErrorCodes {
    private static final String APPLICATION_TOKEN_HEADER = "X-App-License-Key";
    private static final String COLLECTOR_CONNECT_URI = "/mobile/v3/connect";
    private static final String COLLECTOR_DATA_URI = "/mobile/v3/data";
    private static final String CONNECT_TIME_HEADER = "X-NewRelic-Connect-Time";
    private static final Boolean DISABLE_COMPRESSION_FOR_DEBUGGING;
    private String applicationToken;
    private final HttpClient collectorClient;
    private String collectorHost;
    private ConnectInformation connectInformation;
    private final AgentLog log;
    private long serverTimestamp;
    private boolean useSsl;

    static {
        DISABLE_COMPRESSION_FOR_DEBUGGING = Boolean.valueOf(false);
    }

    public HarvestConnection() {
        this.log = AgentLogManager.getAgentLog();
        int convert = (int) TimeUnit.MILLISECONDS.convert(20, TimeUnit.SECONDS);
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, convert);
        HttpConnectionParams.setSoTimeout(basicHttpParams, convert);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD);
        this.collectorClient = new DefaultHttpClient(basicHttpParams);
    }

    public HttpPost createPost(String str, String str2) {
        String str3 = (str2.length() <= AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY || DISABLE_COMPRESSION_FOR_DEBUGGING.booleanValue()) ? HTTP.IDENTITY_CODING : "deflate";
        HttpPost httpPost = new HttpPost(str);
        httpPost.addHeader(HTTP.CONTENT_TYPE, AbstractSpiCall.ACCEPT_JSON_VALUE);
        httpPost.addHeader(HTTP.CONTENT_ENCODING, str3);
        httpPost.addHeader(HTTP.USER_AGENT, System.getProperty("http.agent"));
        if (this.applicationToken == null) {
            this.log.error("Cannot create POST without an Application Token.");
            return null;
        }
        httpPost.addHeader(APPLICATION_TOKEN_HEADER, this.applicationToken);
        if (this.serverTimestamp != 0) {
            httpPost.addHeader(CONNECT_TIME_HEADER, Long.valueOf(this.serverTimestamp).toString());
        }
        if ("deflate".equals(str3)) {
            httpPost.setEntity(new ByteArrayEntity(deflate(str2)));
        } else {
            try {
                httpPost.setEntity(new StringEntity(str2, "utf-8"));
            } catch (Throwable e) {
                this.log.error("UTF-8 is unsupported");
                throw new IllegalArgumentException(e);
            }
        }
        return httpPost;
    }

    public HarvestResponse send(HttpPost httpPost) {
        HarvestResponse harvestResponse = new HarvestResponse();
        try {
            TicToc ticToc = new TicToc();
            ticToc.tic();
            HttpResponse execute = this.collectorClient.execute(httpPost);
            harvestResponse.setResponseTime(ticToc.toc());
            harvestResponse.setStatusCode(execute.getStatusLine().getStatusCode());
            try {
                harvestResponse.setResponseBody(readResponse(execute));
                return harvestResponse;
            } catch (IOException e) {
                e.printStackTrace();
                this.log.error("Failed to retrieve collector response: " + e.getMessage());
                return harvestResponse;
            }
        } catch (Exception e2) {
            this.log.error("Failed to send POST to collector: " + e2.getMessage());
            recordCollectorError(e2);
            return null;
        }
    }

    public HarvestResponse sendConnect() {
        if (this.connectInformation == null) {
            throw new IllegalArgumentException();
        }
        HttpPost createConnectPost = createConnectPost(this.connectInformation.toJsonString());
        if (createConnectPost == null) {
            this.log.error("Failed to create connect POST");
            return null;
        }
        TicToc ticToc = new TicToc();
        ticToc.tic();
        HarvestResponse send = send(createConnectPost);
        StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Connect", ticToc.toc());
        return send;
    }

    public HarvestResponse sendData(Harvestable harvestable) {
        if (harvestable == null) {
            throw new IllegalArgumentException();
        }
        HttpPost createDataPost = createDataPost(harvestable.toJsonString());
        if (createDataPost != null) {
            return send(createDataPost);
        }
        this.log.error("Failed to create data POST");
        return null;
    }

    public HttpPost createConnectPost(String str) {
        return createPost(getCollectorConnectUri(), str);
    }

    public HttpPost createDataPost(String str) {
        return createPost(getCollectorDataUri(), str);
    }

    private byte[] deflate(String str) {
        Deflater deflater = new Deflater();
        deflater.setInput(str.getBytes());
        deflater.finish();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
        while (!deflater.finished()) {
            int deflate = deflater.deflate(bArr);
            if (deflate <= 0) {
                this.log.error("HTTP request contains an incomplete payload");
            }
            byteArrayOutputStream.write(bArr, 0, deflate);
        }
        deflater.end();
        return byteArrayOutputStream.toByteArray();
    }

    public static String readResponse(HttpResponse httpResponse) throws IOException {
        char[] cArr = new char[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
        StringBuilder stringBuilder = new StringBuilder();
        InputStream content = httpResponse.getEntity().getContent();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read < 0) {
                    break;
                }
                stringBuilder.append(cArr, 0, read);
            }
            return stringBuilder.toString();
        } finally {
            content.close();
        }
    }

    private void recordCollectorError(Exception exception) {
        this.log.error("HarvestConnection: Attempting to convert network exception " + exception.getClass().getName() + " to error code.");
        StatsEngine.get().inc("Supportability/AgentHealth/Collector/ResponseErrorCodes/" + ExceptionHelper.exceptionToErrorCode(exception));
    }

    private String getCollectorUri(String str) {
        return (this.useSsl ? "https://" : "http://") + this.collectorHost + str;
    }

    private String getCollectorConnectUri() {
        return getCollectorUri(COLLECTOR_CONNECT_URI);
    }

    private String getCollectorDataUri() {
        return getCollectorUri(COLLECTOR_DATA_URI);
    }

    public void setServerTimestamp(long j) {
        this.log.debug("Setting server timestamp: " + j);
        this.serverTimestamp = j;
    }

    public void useSsl(boolean z) {
        this.useSsl = z;
    }

    public void setApplicationToken(String str) {
        this.applicationToken = str;
    }

    public void setCollectorHost(String str) {
        this.collectorHost = str;
    }

    public void setConnectInformation(ConnectInformation connectInformation) {
        this.connectInformation = connectInformation;
    }

    public ConnectInformation getConnectInformation() {
        return this.connectInformation;
    }
}
