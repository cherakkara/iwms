package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.ThreadInfo;
import com.newrelic.agent.android.measurement.http.HttpErrorMeasurement;
import com.newrelic.agent.android.util.Util;
import java.util.HashMap;
import java.util.Map;

public class HttpErrorMeasurementProducer extends BaseMeasurementProducer {
    public static final String HTTP_METHOD_PARAMS_KEY = "http_method";
    public static final String WAN_TYPE_PARAMS_KEY = "wan_type";

    public HttpErrorMeasurementProducer() {
        super(MeasurementType.HttpError);
    }

    public void produceMeasurement(String str, String str2, int i) {
        produceMeasurement(str, str2, i, Trace.NULL);
    }

    public void produceMeasurement(String str, String str2, int i, String str3) {
        produceMeasurement(str, str2, i, str3, null);
    }

    public void produceMeasurement(String str, String str2, int i, String str3, Map<String, String> map) {
        produceMeasurement(str, str2, i, str3, map, new ThreadInfo());
    }

    public void produceMeasurement(String str, String str2, int i, String str3, Map<String, String> map, ThreadInfo threadInfo) {
        String sanitizeUrl = Util.sanitizeUrl(str);
        if (sanitizeUrl != null) {
            Measurement httpErrorMeasurement = new HttpErrorMeasurement(sanitizeUrl, i);
            if (map == null) {
                map = new HashMap();
            }
            map.put(HTTP_METHOD_PARAMS_KEY, str2);
            map.put(WAN_TYPE_PARAMS_KEY, Agent.getActiveNetworkWanType());
            httpErrorMeasurement.setThreadInfo(threadInfo);
            httpErrorMeasurement.setStackTrace(getSanitizedStackTrace());
            httpErrorMeasurement.setResponseBody(str3);
            httpErrorMeasurement.setParams(map);
            produceMeasurement(httpErrorMeasurement);
        }
    }

    private String getSanitizedStackTrace() {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i2 = 0;
        while (i < stackTrace.length) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (!shouldFilterStackTraceElement(stackTraceElement)) {
                stringBuilder.append(stackTraceElement.toString());
                if (i <= stackTrace.length - 1) {
                    stringBuilder.append("\n");
                }
                i2++;
                if (i2 >= Agent.getStackTraceLimit()) {
                    break;
                }
            }
            i++;
        }
        return stringBuilder.toString();
    }

    private boolean shouldFilterStackTraceElement(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        if (className.startsWith("com.newrelic")) {
            return true;
        }
        if (className.startsWith("dalvik.system.VMStack") && methodName.startsWith("getThreadStackTrace")) {
            return true;
        }
        if (className.startsWith("java.lang.Thread") && methodName.startsWith("getStackTrace")) {
            return true;
        }
        return false;
    }
}
