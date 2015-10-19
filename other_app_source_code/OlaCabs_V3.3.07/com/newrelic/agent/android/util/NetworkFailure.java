package com.newrelic.agent.android.util;

import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;

public enum NetworkFailure {
    Unknown(-1),
    BadURL(HarvestErrorCodes.NSURLErrorBadURL),
    TimedOut(HarvestErrorCodes.NSURLErrorTimedOut),
    CannotConnectToHost(HarvestErrorCodes.NSURLErrorCannotConnectToHost),
    DNSLookupFailed(HarvestErrorCodes.NSURLErrorDNSLookupFailed),
    BadServerResponse(HarvestErrorCodes.NSURLErrorBadServerResponse),
    SecureConnectionFailed(HarvestErrorCodes.NSURLErrorSecureConnectionFailed);
    
    private static final AgentLog log;
    private int errorCode;

    static {
        log = AgentLogManager.getAgentLog();
    }

    private NetworkFailure(int i) {
        this.errorCode = i;
    }

    public static NetworkFailure exceptionToNetworkFailure(Exception exception) {
        log.error("NetworkFailure.exceptionToNetworkFailure: Attempting to convert network exception " + exception.getClass().getName() + " to error code.");
        NetworkFailure networkFailure = Unknown;
        if (exception instanceof UnknownHostException) {
            return DNSLookupFailed;
        }
        if ((exception instanceof SocketTimeoutException) || (exception instanceof ConnectTimeoutException)) {
            return TimedOut;
        }
        if (exception instanceof ConnectException) {
            return CannotConnectToHost;
        }
        if (exception instanceof MalformedURLException) {
            return BadURL;
        }
        if (exception instanceof SSLException) {
            return SecureConnectionFailed;
        }
        if ((exception instanceof HttpResponseException) || (exception instanceof ClientProtocolException)) {
            return BadServerResponse;
        }
        return networkFailure;
    }

    public static int exceptionToErrorCode(Exception exception) {
        return exceptionToNetworkFailure(exception).getErrorCode();
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
