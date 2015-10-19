package com.newrelic.agent.android.instrumentation.retrofit;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;

public class RetrofitTransactionStateUtil extends TransactionStateUtil {
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String NO_BODY_TEXT = "Response BODY not found.";
    private static final AgentLog log;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public static void inspectAndInstrument(TransactionState transactionState, Request request) {
        transactionState.setUrl(request.getUrl());
        transactionState.setHttpMethod(request.getMethod());
        transactionState.setCarrier(Agent.getActiveNetworkCarrier());
        transactionState.setWanType(Agent.getActiveNetworkWanType());
    }

    public static void inspectAndInstrumentResponse(TransactionState transactionState, Response response) {
        String appDataHeader = getAppDataHeader(response.getHeaders(), TransactionStateUtil.APP_DATA_HEADER);
        if (!(appDataHeader == null || Trace.NULL.equals(appDataHeader))) {
            transactionState.setAppData(appDataHeader);
        }
        transactionState.setStatusCode(response.getStatus());
        long length = response.getBody().length();
        if (length >= 0) {
            transactionState.setBytesReceived(length);
        }
        addTransactionAndErrorData(transactionState, response);
    }

    private static String getAppDataHeader(List<Header> list, String str) {
        if (list != null) {
            for (Header header : list) {
                if (header.getName() != null && header.getName().equalsIgnoreCase(str)) {
                    return header.getValue();
                }
            }
        }
        return null;
    }

    private static void addTransactionAndErrorData(TransactionState transactionState, Response response) {
        TransactionData end = transactionState.end();
        if (end != null) {
            TaskQueue.queue(new HttpTransactionMeasurement(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), end.getErrorCode(), end.getTimestamp(), (double) end.getTime(), end.getBytesSent(), end.getBytesReceived(), end.getAppData()));
            if (((long) transactionState.getStatusCode()) >= 400) {
                String appDataHeader = getAppDataHeader(response.getHeaders(), CONTENT_TYPE_HEADER);
                Map treeMap = new TreeMap();
                if (!(appDataHeader == null || appDataHeader.length() <= 0 || Trace.NULL.equals(appDataHeader))) {
                    treeMap.put("content_type", null);
                }
                treeMap.put("content_length", transactionState.getBytesReceived() + Trace.NULL);
                Measurements.addHttpError(end.getUrl(), end.getHttpMethod(), end.getStatusCode(), response.getReason(), treeMap);
            }
        }
    }
}
