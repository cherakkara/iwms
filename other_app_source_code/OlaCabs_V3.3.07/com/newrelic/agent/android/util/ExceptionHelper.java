package com.newrelic.agent.android.util;

import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.harvest.AgentHealthException;
import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;

public class ExceptionHelper implements HarvestErrorCodes {
    private static final AgentLog log;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public static int exceptionToErrorCode(Exception exception) {
        log.debug("ExceptionHelper: exception " + exception.getClass().getName() + " to error code.");
        if (exception instanceof ClientProtocolException) {
            return HarvestErrorCodes.NSURLErrorBadServerResponse;
        }
        if (exception instanceof UnknownHostException) {
            return HarvestErrorCodes.NSURLErrorDNSLookupFailed;
        }
        if ((exception instanceof SocketTimeoutException) || (exception instanceof ConnectTimeoutException)) {
            return HarvestErrorCodes.NSURLErrorTimedOut;
        }
        if (exception instanceof ConnectException) {
            return HarvestErrorCodes.NSURLErrorCannotConnectToHost;
        }
        if (exception instanceof MalformedURLException) {
            return HarvestErrorCodes.NSURLErrorBadURL;
        }
        if (exception instanceof SSLException) {
            return HarvestErrorCodes.NSURLErrorSecureConnectionFailed;
        }
        if (exception instanceof FileNotFoundException) {
            return HarvestErrorCodes.NSURLErrorFileIOException;
        }
        if (exception instanceof EOFException) {
            return HarvestErrorCodes.NSURLErrorFileIOException;
        }
        if (exception instanceof IOException) {
            recordSupportabilityMetric(exception, "IOException");
            return HarvestErrorCodes.NSURLErrorIOException;
        } else if (!(exception instanceof RuntimeException)) {
            return -1;
        } else {
            recordSupportabilityMetric(exception, "RuntimeException");
            return HarvestErrorCodes.NSURLErrorRuntimeException;
        }
    }

    public static void recordSupportabilityMetric(Exception exception, String str) {
        AgentHealthException agentHealthException = new AgentHealthException(exception);
        StackTraceElement stackTraceElement = agentHealthException.getStackTrace()[0];
        log.error(String.format("ExceptionHelper: %s:%s(%s:%s) %s[%s] %s", new Object[]{agentHealthException.getSourceClass(), agentHealthException.getSourceMethod(), stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber()), str, agentHealthException.getExceptionClass(), agentHealthException.getMessage()}));
        AgentHealth.noticeException(agentHealthException, str);
    }
}
