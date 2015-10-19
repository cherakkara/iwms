package com.newrelic.agent.android.instrumentation;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.tracing.TracingInactiveException;
import com.newrelic.agent.android.util.ExceptionHelper;
import java.util.concurrent.Executor;

public class AsyncTaskInstrumentation {
    private static final AgentLog log;

    static {
        log = AgentLogManager.getAgentLog();
    }

    protected AsyncTaskInstrumentation() {
    }

    @ReplaceCallSite
    public static final <Params, Progress, Result> AsyncTask execute(AsyncTask<Params, Progress, Result> asyncTask, Params... paramsArr) {
        try {
            ((TraceFieldInterface) asyncTask)._nr_setTrace(TraceMachine.getCurrentTrace());
        } catch (Exception e) {
            ExceptionHelper.recordSupportabilityMetric(e, "AsyncTaskException");
            log.error("Not a TraceFieldInterface: " + e.getMessage());
        } catch (TracingInactiveException e2) {
        } catch (NoSuchFieldError e3) {
        }
        return asyncTask.execute(paramsArr);
    }

    @ReplaceCallSite
    @TargetApi(11)
    public static final <Params, Progress, Result> AsyncTask executeOnExecutor(AsyncTask<Params, Progress, Result> asyncTask, Executor executor, Params... paramsArr) {
        try {
            ((TraceFieldInterface) asyncTask)._nr_setTrace(TraceMachine.getCurrentTrace());
        } catch (Exception e) {
            ExceptionHelper.recordSupportabilityMetric(e, "AsyncTaskException");
            log.error("Not a TraceFieldInterface: " + e.getMessage());
        } catch (TracingInactiveException e2) {
        } catch (NoSuchFieldError e3) {
        }
        return asyncTask.executeOnExecutor(executor, paramsArr);
    }
}
