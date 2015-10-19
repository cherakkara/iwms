package com.newrelic.agent.android.instrumentation.okhttp2;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.protocol.HTTP;

public class OkHttp2TransactionStateUtil extends TransactionStateUtil {
    private static final String NO_BODY_TEXT = "Response BODY not found.";
    private static final AgentLog log;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public static void inspectAndInstrument(TransactionState transactionState, Request request) {
        TransactionStateUtil.inspectAndInstrument(transactionState, request.urlString(), request.method());
    }

    public static void inspectAndInstrumentResponse(TransactionState transactionState, Response response) {
        TransactionStateUtil.inspectAndInstrumentResponse(transactionState, response.header(TransactionStateUtil.APP_DATA_HEADER), (int) response.body().contentLength(), response.code());
        addTransactionAndErrorData(transactionState, response);
    }

    private static void addTransactionAndErrorData(TransactionState transactionState, Response response) {
        TransactionData end = transactionState.end();
        if (end != null) {
            TaskQueue.queue(new HttpTransactionMeasurement(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), end.getErrorCode(), end.getTimestamp(), (double) end.getTime(), end.getBytesSent(), end.getBytesReceived(), end.getAppData()));
            if (((long) transactionState.getStatusCode()) >= 400) {
                String header = response.header(HTTP.CONTENT_TYPE);
                Map treeMap = new TreeMap();
                if (!(header == null || header.length() <= 0 || Trace.NULL.equals(header))) {
                    treeMap.put("content_type", null);
                }
                treeMap.put("content_length", transactionState.getBytesReceived() + Trace.NULL);
                Measurements.addHttpError(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), response.message(), treeMap);
            }
        }
    }
}
