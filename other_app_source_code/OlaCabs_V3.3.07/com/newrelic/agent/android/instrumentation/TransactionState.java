package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.producer.HttpErrorMeasurementProducer;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.Util;
import java.net.MalformedURLException;
import java.net.URL;

public final class TransactionState {
    private static final AgentLog log;
    private String appData;
    private long bytesReceived;
    private long bytesSent;
    private String carrier;
    private String contentType;
    private long endTime;
    private int errorCode;
    private String httpMethod;
    private long startTime;
    private State state;
    private int statusCode;
    private TransactionData transactionData;
    private String url;
    private String wanType;

    private enum State {
        READY,
        SENT,
        COMPLETE
    }

    static {
        log = AgentLogManager.getAgentLog();
    }

    public TransactionState() {
        this.startTime = System.currentTimeMillis();
        this.carrier = WanType.UNKNOWN;
        this.wanType = WanType.UNKNOWN;
        this.state = State.READY;
        TraceMachine.enterNetworkSegment("External/unknownhost");
    }

    public void setCarrier(String str) {
        if (isSent()) {
            log.warning("setCarrier(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.carrier = str;
        TraceMachine.setCurrentTraceParam(AnalyticAttribute.CARRIER_ATTRIBUTE, str);
    }

    public void setWanType(String str) {
        if (isSent()) {
            log.warning("setWanType(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.wanType = str;
        TraceMachine.setCurrentTraceParam(HttpErrorMeasurementProducer.WAN_TYPE_PARAMS_KEY, str);
    }

    public void setAppData(String str) {
        if (isComplete()) {
            log.warning("setAppData(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.appData = str;
        TraceMachine.setCurrentTraceParam("encoded_app_data", str);
    }

    public void setUrl(String str) {
        String sanitizeUrl = Util.sanitizeUrl(str);
        if (sanitizeUrl != null) {
            if (isSent()) {
                log.warning("setUrl(...) called on TransactionState in " + this.state.toString() + " state");
                return;
            }
            this.url = sanitizeUrl;
            try {
                TraceMachine.setCurrentDisplayName("External/" + new URL(sanitizeUrl).getHost());
            } catch (MalformedURLException e) {
                log.error("unable to parse host name from " + sanitizeUrl);
            }
            TraceMachine.setCurrentTraceParam("uri", sanitizeUrl);
        }
    }

    public void setHttpMethod(String str) {
        if (isSent()) {
            log.warning("setHttpMethod(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.httpMethod = str;
        TraceMachine.setCurrentTraceParam(HttpErrorMeasurementProducer.HTTP_METHOD_PARAMS_KEY, str);
    }

    public String getUrl() {
        return this.url;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public boolean isSent() {
        return this.state.ordinal() >= State.SENT.ordinal();
    }

    public boolean isComplete() {
        return this.state.ordinal() >= State.COMPLETE.ordinal();
    }

    public void setStatusCode(int i) {
        if (isComplete()) {
            log.warning("setStatusCode(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.statusCode = i;
        TraceMachine.setCurrentTraceParam("status_code", Integer.valueOf(i));
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setErrorCode(int i) {
        if (isComplete()) {
            if (this.transactionData != null) {
                this.transactionData.setErrorCode(i);
            }
            log.warning("setErrorCode(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.errorCode = i;
        TraceMachine.setCurrentTraceParam("error_code", Integer.valueOf(i));
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setBytesSent(long j) {
        if (isComplete()) {
            log.warning("setBytesSent(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.bytesSent = j;
        TraceMachine.setCurrentTraceParam("bytes_sent", Long.valueOf(j));
        this.state = State.SENT;
    }

    public void setBytesReceived(long j) {
        if (isComplete()) {
            log.warning("setBytesReceived(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.bytesReceived = j;
        TraceMachine.setCurrentTraceParam("bytes_received", Long.valueOf(j));
    }

    public long getBytesReceived() {
        return this.bytesReceived;
    }

    public TransactionData end() {
        if (!isComplete()) {
            this.state = State.COMPLETE;
            this.endTime = System.currentTimeMillis();
            TraceMachine.exitMethod();
        }
        return toTransactionData();
    }

    private TransactionData toTransactionData() {
        if (!isComplete()) {
            log.warning("toTransactionData() called on incomplete TransactionState");
        }
        if (this.url == null) {
            log.error("Attempted to convert a TransactionState instance with no URL into a TransactionData");
            return null;
        }
        if (this.transactionData == null) {
            this.transactionData = new TransactionData(this.url, this.httpMethod, this.carrier, ((float) (this.endTime - this.startTime)) / 1000.0f, this.statusCode, this.errorCode, this.bytesSent, this.bytesReceived, this.appData, this.wanType);
        }
        return this.transactionData;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public String toString() {
        return "TransactionState{url='" + this.url + '\'' + ", httpMethod='" + this.httpMethod + '\'' + ", statusCode=" + this.statusCode + ", errorCode=" + this.errorCode + ", bytesSent=" + this.bytesSent + ", bytesReceived=" + this.bytesReceived + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", appData='" + this.appData + '\'' + ", carrier='" + this.carrier + '\'' + ", wanType='" + this.wanType + '\'' + ", state=" + this.state + ", contentType='" + this.contentType + '\'' + ", transactionData=" + this.transactionData + '}';
    }
}
