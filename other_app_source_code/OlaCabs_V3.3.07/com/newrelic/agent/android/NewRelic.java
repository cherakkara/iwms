package com.newrelic.agent.android;

import android.content.Context;
import android.text.TextUtils;
import com.newrelic.agent.android.analytics.AnalyticsControllerImpl;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.logging.AndroidAgentLog;
import com.newrelic.agent.android.logging.NullAgentLog;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.MetricUnit;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.tracing.TracingInactiveException;
import com.newrelic.agent.android.util.NetworkFailure;
import com.olacabs.customer.p076d.br;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class NewRelic {
    private static final String DEFAULT_COLLECTOR_ADDR = "mobile-collector.newrelic.com";
    private static final String UNKNOWN_HTTP_REQUEST_TYPE = "unknown";
    private static final AgentConfiguration agentConfiguration;
    private static final AgentLog log;
    private static boolean started;
    private int logLevel;
    private boolean loggingEnabled;

    private boolean isInstrumented() {
        return true;
    }

    static {
        log = AgentLogManager.getAgentLog();
        agentConfiguration = new AgentConfiguration();
        started = false;
    }

    private NewRelic(String str) {
        this.loggingEnabled = true;
        this.logLevel = 3;
        agentConfiguration.setApplicationToken(str);
    }

    public static NewRelic withApplicationToken(String str) {
        return new NewRelic(str);
    }

    public NewRelic usingSsl(boolean z) {
        agentConfiguration.setUseSsl(z);
        return this;
    }

    public NewRelic usingCollectorAddress(String str) {
        agentConfiguration.setCollectorHost(str);
        return this;
    }

    public NewRelic usingCrashCollectorAddress(String str) {
        agentConfiguration.setCrashCollectorHost(str);
        return this;
    }

    public NewRelic withLocationServiceEnabled(boolean z) {
        agentConfiguration.setUseLocationService(z);
        return this;
    }

    public NewRelic withLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
        return this;
    }

    public NewRelic withLogLevel(int i) {
        this.logLevel = i;
        return this;
    }

    public NewRelic withCrashReportingEnabled(boolean z) {
        agentConfiguration.setReportCrashes(z);
        if (z) {
            enableFeature(FeatureFlag.CrashReporting);
        } else {
            disableFeature(FeatureFlag.CrashReporting);
        }
        return this;
    }

    public NewRelic withHttpResponseBodyCaptureEnabled(boolean z) {
        if (z) {
            enableFeature(FeatureFlag.HttpResponseBodyCapture);
        } else {
            disableFeature(FeatureFlag.HttpResponseBodyCapture);
        }
        return this;
    }

    public NewRelic withApplicationVersion(String str) {
        if (str != null) {
            agentConfiguration.setCustomApplicationVersion(str);
        }
        return this;
    }

    public NewRelic withBuildIdentifier(String str) {
        if (!TextUtils.isEmpty(str)) {
            agentConfiguration.setCustomBuildIdentifier(str);
        }
        return this;
    }

    public NewRelic withAnalyticsEvents(boolean z) {
        if (z) {
            enableFeature(FeatureFlag.AnalyticsEvents);
        } else {
            disableFeature(FeatureFlag.AnalyticsEvents);
        }
        return this;
    }

    public static void enableFeature(FeatureFlag featureFlag) {
        FeatureFlag.enableFeature(featureFlag);
    }

    public static void disableFeature(FeatureFlag featureFlag) {
        FeatureFlag.disableFeature(featureFlag);
    }

    public void start(Context context) {
        if (started) {
            log.debug("NewRelic is already running.");
            return;
        }
        try {
            AgentLogManager.setAgentLog(this.loggingEnabled ? new AndroidAgentLog() : new NullAgentLog());
            log.setLevel(this.logLevel);
            if (isInstrumented()) {
                AndroidAgentImpl.init(context, agentConfiguration);
                started = true;
                return;
            }
            log.error("Failed to detect New Relic instrumentation.  Something likely went wrong during your build process and you should contact support@newrelic.com.");
        } catch (Throwable th) {
            log.error("Error occurred while starting the New Relic agent!", th);
        }
    }

    public static boolean isStarted() {
        return started;
    }

    public static void shutdown() {
        if (started) {
            try {
                Agent.getImpl().stop();
            } finally {
                Agent.setImpl(NullAgentImpl.instance);
                started = false;
            }
        }
    }

    public static String startInteraction(String str) {
        checkNull(str, "startInteraction: actionName must be an action/method name.");
        TraceMachine.startTracing(str.replace("/", "."), true);
        try {
            return TraceMachine.getActivityTrace().getId();
        } catch (TracingInactiveException e) {
            return null;
        }
    }

    @Deprecated
    public static String startInteraction(Context context, String str) {
        checkNull(context, "startInteraction: context must be an Activity instance.");
        checkNull(str, "startInteraction: actionName must be an action/method name.");
        TraceMachine.startTracing(context.getClass().getSimpleName() + "#" + str.replace("/", "."));
        try {
            return TraceMachine.getActivityTrace().getId();
        } catch (TracingInactiveException e) {
            return null;
        }
    }

    @Deprecated
    public static String startInteraction(Context context, String str, boolean z) {
        if (!TraceMachine.isTracingActive() || z) {
            return startInteraction(context, str);
        }
        log.warning("startInteraction: An interaction is already being traced, and invalidateActiveTrace is false. This interaction will not be traced.");
        return null;
    }

    public static void endInteraction(String str) {
        TraceMachine.endTrace(str);
    }

    public static void setInteractionName(String str) {
        TraceMachine.setRootDisplayName(str);
    }

    public static void recordMetric(String str, String str2, int i, double d, double d2) {
        recordMetric(str, str2, i, d, d2, null, null);
    }

    public static void recordMetric(String str, String str2, int i, double d, double d2, MetricUnit metricUnit, MetricUnit metricUnit2) {
        checkNull(str2, "recordMetric: category must not be null. If no MetricCategory is applicable, use MetricCategory.NONE.");
        checkEmpty(str, "recordMetric: name must not be empty.");
        if (!checkNegative(i, "recordMetric: count must not be negative.")) {
            Measurements.addCustomMetric(str, str2, i, d, d2, metricUnit, metricUnit2);
        }
    }

    public static void recordMetric(String str, String str2, double d) {
        recordMetric(str, str2, 1, d, d, null, null);
    }

    public static void recordMetric(String str, String str2) {
        recordMetric(str, str2, 1.0d);
    }

    public static void noticeHttpTransaction(String str, String str2, int i, long j, long j2, long j3, long j4) {
        _noticeHttpTransaction(str, str2, i, j, j2, j3, j4, null, null, null);
    }

    public static void noticeHttpTransaction(String str, String str2, int i, long j, long j2, long j3, long j4, String str3) {
        _noticeHttpTransaction(str, str2, i, j, j2, j3, j4, str3, null, null);
    }

    public static void noticeHttpTransaction(String str, String str2, int i, long j, long j2, long j3, long j4, String str3, Map<String, String> map) {
        _noticeHttpTransaction(str, str2, i, j, j2, j3, j4, str3, map, null);
    }

    public static void noticeHttpTransaction(String str, String str2, int i, long j, long j2, long j3, long j4, String str3, Map<String, String> map, String str4) {
        _noticeHttpTransaction(str, str2, i, j, j2, j3, j4, str3, map, str4);
    }

    public static void noticeHttpTransaction(String str, String str2, int i, long j, long j2, long j3, long j4, String str3, Map<String, String> map, HttpResponse httpResponse) {
        if (httpResponse != null) {
            Header firstHeader = httpResponse.getFirstHeader(TransactionStateUtil.CROSS_PROCESS_ID_HEADER);
            if (!(firstHeader == null || firstHeader.getValue() == null || firstHeader.getValue().length() <= 0)) {
                _noticeHttpTransaction(str, str2, i, j, j2, j3, j4, str3, map, firstHeader.getValue());
                return;
            }
        }
        _noticeHttpTransaction(str, str2, i, j, j2, j3, j4, str3, map, null);
    }

    public static void noticeHttpTransaction(String str, String str2, int i, long j, long j2, long j3, long j4, String str3, Map<String, String> map, URLConnection uRLConnection) {
        if (uRLConnection != null) {
            String headerField = uRLConnection.getHeaderField(TransactionStateUtil.CROSS_PROCESS_ID_HEADER);
            if (headerField != null && headerField.length() > 0) {
                _noticeHttpTransaction(str, str2, i, j, j2, j3, j4, str3, map, headerField);
                return;
            }
        }
        _noticeHttpTransaction(str, str2, i, j, j2, j3, j4, str3, map, null);
    }

    @Deprecated
    public static void noticeHttpTransaction(String str, int i, long j, long j2, long j3, long j4, String str2, Map<String, String> map, HttpResponse httpResponse) {
        noticeHttpTransaction(str, UNKNOWN_HTTP_REQUEST_TYPE, i, j, j2, j3, j4, str2, (Map) map, httpResponse);
    }

    @Deprecated
    public static void noticeHttpTransaction(String str, int i, long j, long j2, long j3, long j4, String str2, Map<String, String> map, URLConnection uRLConnection) {
        noticeHttpTransaction(str, UNKNOWN_HTTP_REQUEST_TYPE, i, j, j2, j3, j4, str2, (Map) map, uRLConnection);
    }

    @Deprecated
    public static void noticeHttpTransaction(String str, int i, long j, long j2, long j3, long j4) {
        _noticeHttpTransaction(str, UNKNOWN_HTTP_REQUEST_TYPE, i, j, j2, j3, j4, null, null, null);
    }

    @Deprecated
    public static void noticeHttpTransaction(String str, int i, long j, long j2, long j3, long j4, String str2) {
        _noticeHttpTransaction(str, UNKNOWN_HTTP_REQUEST_TYPE, i, j, j2, j3, j4, str2, null, null);
    }

    @Deprecated
    public static void noticeHttpTransaction(String str, int i, long j, long j2, long j3, long j4, String str2, Map<String, String> map) {
        _noticeHttpTransaction(str, UNKNOWN_HTTP_REQUEST_TYPE, i, j, j2, j3, j4, str2, map, null);
    }

    @Deprecated
    public static void noticeHttpTransaction(String str, int i, long j, long j2, long j3, long j4, String str2, Map<String, String> map, String str3) {
        _noticeHttpTransaction(str, UNKNOWN_HTTP_REQUEST_TYPE, i, j, j2, j3, j4, str2, map, str3);
    }

    private static void _noticeHttpTransaction(String str, String str2, int i, long j, long j2, long j3, long j4, String str3, Map<String, String> map, String str4) {
        checkEmpty(str, "noticeHttpTransaction: url must not be empty.");
        checkEmpty(str2, "noticeHttpTransaction: httpMethod must not be empty.");
        try {
            URL url = new URL(str);
            double d = (double) (j2 - j);
            if (!checkNegative((int) d, "noticeHttpTransaction: the startTimeMs is later than the endTimeMs, resulting in a negative total time.")) {
                TaskQueue.queue(new HttpTransactionMeasurement(str, str2, i, 0, j, d / 1000.0d, j3, j4, str4));
                if (((long) i) >= 400) {
                    Measurements.addHttpError(str, str2, i, str3, map);
                }
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("noticeHttpTransaction: URL is malformed: " + str);
        }
    }

    public static void noticeNetworkFailure(String str, String str2, long j, long j2, NetworkFailure networkFailure) {
        log.debug("NewRelic.noticeNetworkFailure invoke - url: " + str + ", httpMethod: " + str2 + ", startTime: " + j + ", endTime: " + j2 + ", failure: " + networkFailure);
        TaskQueue.queue(new HttpTransactionMeasurement(str, str2, 0, networkFailure.getErrorCode(), j, (double) j2, 0, 0, null));
    }

    public static void noticeNetworkFailure(String str, String str2, long j, long j2, Exception exception) {
        checkEmpty(str, "noticeHttpException: url must not be empty.");
        noticeNetworkFailure(str, str2, j, j2, NetworkFailure.exceptionToNetworkFailure(exception));
    }

    @Deprecated
    public static void noticeNetworkFailure(String str, long j, long j2, NetworkFailure networkFailure) {
        noticeNetworkFailure(str, UNKNOWN_HTTP_REQUEST_TYPE, j, j2, networkFailure);
    }

    @Deprecated
    public static void noticeNetworkFailure(String str, long j, long j2, Exception exception) {
        noticeNetworkFailure(str, UNKNOWN_HTTP_REQUEST_TYPE, j, j2, exception);
    }

    private static void checkNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    private static void checkEmpty(String str, String str2) {
        checkNull(str, str2);
        if (str.length() == 0) {
            throw new IllegalArgumentException(str2);
        }
    }

    private static boolean checkNegative(int i, String str) {
        if (i >= 0) {
            return false;
        }
        log.error(str);
        return true;
    }

    public static void crashNow() {
        crashNow("This is a demonstration crash courtesy of New Relic");
    }

    public static void crashNow(String str) {
        throw new RuntimeException(str);
    }

    public static boolean setAttribute(String str, String str2) {
        return AnalyticsControllerImpl.getInstance().setAttribute(str, str2);
    }

    public static boolean setAttribute(String str, float f) {
        return AnalyticsControllerImpl.getInstance().setAttribute(str, f);
    }

    public static boolean incrementAttribute(String str) {
        return AnalyticsControllerImpl.getInstance().incrementAttribute(str, br.DEFAULT_BACKOFF_MULT);
    }

    public static boolean incrementAttribute(String str, float f) {
        return AnalyticsControllerImpl.getInstance().incrementAttribute(str, f);
    }

    public static boolean removeAttribute(String str) {
        return AnalyticsControllerImpl.getInstance().removeAttribute(str);
    }

    public static boolean removeAllAttributes() {
        return AnalyticsControllerImpl.getInstance().removeAllAttributes();
    }

    public static boolean recordEvent(String str, Map<String, Object> map) {
        return AnalyticsControllerImpl.getInstance().recordEvent(str, map);
    }

    public static void setMaxEventPoolSize(int i) {
        AnalyticsControllerImpl.getInstance().setMaxEventPoolSize(i);
    }

    public static void setMaxEventBufferTime(int i) {
        AnalyticsControllerImpl.getInstance().setMaxEventBufferTime(i);
    }

    public static String currentSessionId() {
        return agentConfiguration.getSessionID();
    }
}
