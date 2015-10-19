package com.newrelic.agent.android.tracing;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.api.v2.TraceMachineInterface;
import com.newrelic.agent.android.harvest.ActivityHistory;
import com.newrelic.agent.android.harvest.ActivitySighting;
import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.harvest.HarvestLifecycleAware;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.olacabs.customer.utils.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class TraceMachine extends HarvestAdapter {
    public static final String ACTIVITY_BACKGROUND_METRIC_PREFIX = "Mobile/Activity/Background/Name/";
    public static final String ACTIVITY_METRIC_PREFIX = "Mobile/Activity/Name/";
    public static final String ACTIVTY_DISPLAY_NAME_PREFIX = "Display ";
    public static final int HEALTHY_TRACE_TIMEOUT = 500;
    public static final String NR_TRACE_FIELD = "_nr_trace";
    public static final String NR_TRACE_TYPE = "Lcom/newrelic/agent/android/tracing/Trace;";
    public static final int UNHEALTHY_TRACE_TIMEOUT = 60000;
    private static final List<ActivitySighting> activityHistory;
    public static final AtomicBoolean disabled;
    private static final AgentLog log;
    private static final ThreadLocal<Trace> threadLocalTrace;
    private static final ThreadLocal<TraceStack> threadLocalTraceStack;
    private static final Collection<TraceLifecycleAware> traceListeners;
    private static TraceMachine traceMachine;
    private static TraceMachineInterface traceMachineInterface;
    private ActivityTrace activityTrace;

    private static class TraceStack extends Stack<Trace> {
        private TraceStack() {
        }
    }

    static {
        disabled = new AtomicBoolean(false);
        log = AgentLogManager.getAgentLog();
        traceListeners = new CopyOnWriteArrayList();
        threadLocalTrace = new ThreadLocal();
        threadLocalTraceStack = new ThreadLocal();
        activityHistory = new CopyOnWriteArrayList();
        traceMachine = null;
    }

    protected TraceMachine(Trace trace) {
        this.activityTrace = new ActivityTrace(trace);
        Harvest.addHarvestListener(this);
    }

    public static TraceMachine getTraceMachine() {
        return traceMachine;
    }

    public static void addTraceListener(TraceLifecycleAware traceLifecycleAware) {
        traceListeners.add(traceLifecycleAware);
    }

    public static void removeTraceListener(TraceLifecycleAware traceLifecycleAware) {
        traceListeners.remove(traceLifecycleAware);
    }

    public static void setTraceMachineInterface(TraceMachineInterface traceMachineInterface) {
        traceMachineInterface = traceMachineInterface;
    }

    public static void startTracing(String str) {
        startTracing(str, false);
    }

    public static void startTracing(String str, boolean z) {
        try {
            if (!disabled.get() && Harvest.shouldCollectActivityTraces()) {
                if (isTracingActive()) {
                    traceMachine.completeActivityTrace();
                }
                threadLocalTrace.remove();
                threadLocalTraceStack.set(new TraceStack());
                Trace trace = new Trace();
                if (z) {
                    trace.displayName = str;
                } else {
                    trace.displayName = formatActivityDisplayName(str);
                }
                trace.metricName = formatActivityMetricName(trace.displayName);
                trace.metricBackgroundName = formatActivityBackgroundMetricName(trace.displayName);
                trace.entryTimestamp = System.currentTimeMillis();
                if (log.getLevel() == 5) {
                    log.debug("Started trace of " + str + ":" + trace.myUUID.toString());
                }
                traceMachine = new TraceMachine(trace);
                trace.traceMachine = traceMachine;
                pushTraceContext(trace);
                traceMachine.activityTrace.previousActivity = getLastActivitySighting();
                activityHistory.add(new ActivitySighting(trace.entryTimestamp, trace.displayName));
                for (TraceLifecycleAware onTraceStart : traceListeners) {
                    onTraceStart.onTraceStart(traceMachine.activityTrace);
                }
            }
        } catch (Exception e) {
            log.error("Caught error while initializing TraceMachine, shutting it down", e);
            AgentHealth.noticeException(e);
            traceMachine = null;
            threadLocalTrace.remove();
            threadLocalTraceStack.remove();
        }
    }

    public static void haltTracing() {
        if (!isTracingInactive()) {
            HarvestLifecycleAware harvestLifecycleAware = traceMachine;
            traceMachine = null;
            harvestLifecycleAware.activityTrace.discard();
            endLastActivitySighting();
            Harvest.removeHarvestListener(harvestLifecycleAware);
            threadLocalTrace.remove();
            threadLocalTraceStack.remove();
        }
    }

    public static void endTrace() {
        traceMachine.completeActivityTrace();
    }

    public static void endTrace(String str) {
        try {
            if (getActivityTrace().rootTrace.myUUID.toString().equals(str)) {
                traceMachine.completeActivityTrace();
            }
        } catch (TracingInactiveException e) {
        }
    }

    public static String formatActivityMetricName(String str) {
        return ACTIVITY_METRIC_PREFIX + str;
    }

    public static String formatActivityBackgroundMetricName(String str) {
        return ACTIVITY_BACKGROUND_METRIC_PREFIX + str;
    }

    public static String formatActivityDisplayName(String str) {
        return ACTIVTY_DISPLAY_NAME_PREFIX + str;
    }

    private static Trace registerNewTrace(String str) throws TracingInactiveException {
        if (isTracingInactive()) {
            log.debug("Tried to register a new trace but tracing is inactive!");
            throw new TracingInactiveException();
        }
        Trace currentTrace = getCurrentTrace();
        Trace trace = new Trace(str, currentTrace.myUUID, traceMachine);
        try {
            traceMachine.activityTrace.addTrace(trace);
            if (log.getLevel() == 5) {
                log.debug("Registering trace of " + str + " with parent " + currentTrace.displayName);
            }
            currentTrace.addChild(trace);
            return trace;
        } catch (Exception e) {
            throw new TracingInactiveException();
        }
    }

    public void completeActivityTrace() {
        if (!isTracingInactive()) {
            HarvestLifecycleAware harvestLifecycleAware = traceMachine;
            traceMachine = null;
            harvestLifecycleAware.activityTrace.complete();
            endLastActivitySighting();
            for (TraceLifecycleAware onTraceComplete : traceListeners) {
                onTraceComplete.onTraceComplete(harvestLifecycleAware.activityTrace);
            }
            Harvest.removeHarvestListener(harvestLifecycleAware);
        }
    }

    public static void enterNetworkSegment(String str) {
        try {
            if (!isTracingInactive()) {
                if (getCurrentTrace().getType() == TraceType.NETWORK) {
                    exitMethod();
                }
                enterMethod(null, str, null);
                getCurrentTrace().setType(TraceType.NETWORK);
            }
        } catch (TracingInactiveException e) {
        } catch (Exception e2) {
            log.error("Caught error while calling enterNetworkSegment()", e2);
            AgentHealth.noticeException(e2);
        }
    }

    public static void enterMethod(String str) {
        enterMethod(null, str, null);
    }

    public static void enterMethod(String str, ArrayList<String> arrayList) {
        enterMethod(null, str, arrayList);
    }

    public static void enterMethod(Trace trace, String str, ArrayList<String> arrayList) {
        try {
            if (!isTracingInactive()) {
                long currentTimeMillis = System.currentTimeMillis();
                long j = traceMachine.activityTrace.lastUpdatedAt;
                long j2 = traceMachine.activityTrace.startedAt;
                if (j + 500 < currentTimeMillis && !traceMachine.activityTrace.hasMissingChildren()) {
                    log.debug("Completing activity trace after hitting healthy timeout (500ms)");
                    traceMachine.completeActivityTrace();
                } else if (Constants.MILLIS_IN_A_MINUTE + j2 < currentTimeMillis) {
                    log.debug("Completing activity trace after hitting unhealthy timeout (60000ms)");
                    traceMachine.completeActivityTrace();
                } else {
                    loadTraceContext(trace);
                    Trace registerNewTrace = registerNewTrace(str);
                    pushTraceContext(registerNewTrace);
                    registerNewTrace.scope = getCurrentScope();
                    registerNewTrace.setAnnotationParams(arrayList);
                    for (TraceLifecycleAware onEnterMethod : traceListeners) {
                        onEnterMethod.onEnterMethod();
                    }
                    registerNewTrace.entryTimestamp = System.currentTimeMillis();
                }
            }
        } catch (TracingInactiveException e) {
        } catch (Exception e2) {
            log.error("Caught error while calling enterMethod()", e2);
            AgentHealth.noticeException(e2);
        }
    }

    public static void exitMethod() {
        try {
            if (!isTracingInactive()) {
                Trace trace = (Trace) threadLocalTrace.get();
                if (trace == null) {
                    log.debug("threadLocalTrace is null");
                    return;
                }
                trace.exitTimestamp = System.currentTimeMillis();
                if (trace.threadId == 0 && traceMachineInterface != null) {
                    trace.threadId = traceMachineInterface.getCurrentThreadId();
                    trace.threadName = traceMachineInterface.getCurrentThreadName();
                }
                for (TraceLifecycleAware onExitMethod : traceListeners) {
                    onExitMethod.onExitMethod();
                }
                try {
                    trace.complete();
                    ((TraceStack) threadLocalTraceStack.get()).pop();
                    if (((TraceStack) threadLocalTraceStack.get()).empty()) {
                        threadLocalTrace.set(null);
                    } else {
                        Trace trace2 = (Trace) ((TraceStack) threadLocalTraceStack.get()).peek();
                        threadLocalTrace.set(trace2);
                        trace2.childExclusiveTime += trace.getDurationAsMilliseconds();
                    }
                    if (trace.getType() == TraceType.TRACE) {
                        TaskQueue.queue(trace);
                    }
                } catch (TracingInactiveException e) {
                    threadLocalTrace.remove();
                    threadLocalTraceStack.remove();
                    if (trace.getType() == TraceType.TRACE) {
                        TaskQueue.queue(trace);
                    }
                }
            }
        } catch (Exception e2) {
            log.error("Caught error while calling exitMethod()", e2);
            AgentHealth.noticeException(e2);
        }
    }

    private static void pushTraceContext(Trace trace) {
        if (!isTracingInactive() && trace != null) {
            TraceStack traceStack = (TraceStack) threadLocalTraceStack.get();
            if (traceStack.empty()) {
                traceStack.push(trace);
            } else if (traceStack.peek() != trace) {
                traceStack.push(trace);
            }
            threadLocalTrace.set(trace);
        }
    }

    private static void loadTraceContext(Trace trace) {
        if (!isTracingInactive()) {
            if (threadLocalTrace.get() == null) {
                threadLocalTrace.set(trace);
                threadLocalTraceStack.set(new TraceStack());
                if (trace != null) {
                    ((TraceStack) threadLocalTraceStack.get()).push(trace);
                } else {
                    return;
                }
            } else if (trace == null) {
                if (((TraceStack) threadLocalTraceStack.get()).isEmpty()) {
                    if (log.getLevel() == 5) {
                        log.debug("No context to load!");
                    }
                    threadLocalTrace.set(null);
                    return;
                }
                Trace trace2 = (Trace) ((TraceStack) threadLocalTraceStack.get()).peek();
                threadLocalTrace.set(trace2);
                trace = trace2;
            }
            if (log.getLevel() == 5) {
                log.debug("Trace " + trace.myUUID.toString() + " is now active");
            }
        }
    }

    public static void unloadTraceContext(Object obj) {
        try {
            if (!isTracingInactive()) {
                if (traceMachineInterface == null || !traceMachineInterface.isUIThread()) {
                    if (threadLocalTrace.get() != null && log.getLevel() == 5) {
                        log.debug("Trace " + ((Trace) threadLocalTrace.get()).myUUID.toString() + " is now inactive");
                    }
                    threadLocalTrace.remove();
                    threadLocalTraceStack.remove();
                    ((TraceFieldInterface) obj)._nr_setTrace(null);
                }
            }
        } catch (Exception e) {
            log.error("Caught error while calling unloadTraceContext()", e);
            AgentHealth.noticeException(e);
        }
    }

    public static Trace getCurrentTrace() throws TracingInactiveException {
        if (isTracingInactive()) {
            throw new TracingInactiveException();
        }
        Trace trace = (Trace) threadLocalTrace.get();
        return trace != null ? trace : getRootTrace();
    }

    public static Map<String, Object> getCurrentTraceParams() throws TracingInactiveException {
        return getCurrentTrace().getParams();
    }

    public static void setCurrentTraceParam(String str, Object obj) {
        if (!isTracingInactive()) {
            try {
                getCurrentTrace().getParams().put(str, obj);
            } catch (TracingInactiveException e) {
            }
        }
    }

    public static void setCurrentDisplayName(String str) {
        if (!isTracingInactive()) {
            try {
                getCurrentTrace().displayName = str;
            } catch (TracingInactiveException e) {
            }
        }
    }

    public static void setRootDisplayName(String str) {
        if (!isTracingInactive()) {
            try {
                Trace rootTrace = getRootTrace();
                Measurements.renameActivity(rootTrace.displayName, str);
                renameActivityHistory(rootTrace.displayName, str);
                rootTrace.metricName = formatActivityMetricName(str);
                rootTrace.metricBackgroundName = formatActivityBackgroundMetricName(str);
                rootTrace.displayName = str;
                getCurrentTrace().scope = getCurrentScope();
            } catch (TracingInactiveException e) {
            }
        }
    }

    private static void renameActivityHistory(String str, String str2) {
        for (ActivitySighting activitySighting : activityHistory) {
            if (activitySighting.getName().equals(str)) {
                activitySighting.setName(str2);
            }
        }
    }

    public static String getCurrentScope() {
        try {
            if (isTracingInactive()) {
                return null;
            }
            if (traceMachineInterface == null || traceMachineInterface.isUIThread()) {
                return traceMachine.activityTrace.rootTrace.metricName;
            }
            return traceMachine.activityTrace.rootTrace.metricBackgroundName;
        } catch (Exception e) {
            log.error("Caught error while calling getCurrentScope()", e);
            AgentHealth.noticeException(e);
            return null;
        }
    }

    public static boolean isTracingActive() {
        return traceMachine != null;
    }

    public static boolean isTracingInactive() {
        return traceMachine == null;
    }

    public void storeCompletedTrace(Trace trace) {
        try {
            if (isTracingInactive()) {
                log.debug("Attempted to store a completed trace with no trace machine!");
            } else {
                this.activityTrace.addCompletedTrace(trace);
            }
        } catch (Exception e) {
            log.error("Caught error while calling storeCompletedTrace()", e);
            AgentHealth.noticeException(e);
        }
    }

    public static Trace getRootTrace() throws TracingInactiveException {
        try {
            return traceMachine.activityTrace.rootTrace;
        } catch (NullPointerException e) {
            throw new TracingInactiveException();
        }
    }

    public static ActivityTrace getActivityTrace() throws TracingInactiveException {
        try {
            return traceMachine.activityTrace;
        } catch (NullPointerException e) {
            throw new TracingInactiveException();
        }
    }

    public static ActivityHistory getActivityHistory() {
        return new ActivityHistory(activityHistory);
    }

    public static ActivitySighting getLastActivitySighting() {
        if (activityHistory.isEmpty()) {
            return null;
        }
        return (ActivitySighting) activityHistory.get(activityHistory.size() - 1);
    }

    public static void endLastActivitySighting() {
        ActivitySighting lastActivitySighting = getLastActivitySighting();
        if (lastActivitySighting != null) {
            lastActivitySighting.end(System.currentTimeMillis());
        }
    }

    public static void clearActivityHistory() {
        activityHistory.clear();
    }

    public void onHarvestBefore() {
        if (isTracingActive()) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = traceMachine.activityTrace.lastUpdatedAt;
            long j2 = traceMachine.activityTrace.startedAt;
            if (j + 500 < currentTimeMillis && !traceMachine.activityTrace.hasMissingChildren()) {
                log.debug("Completing activity trace after hitting healthy timeout (500ms)");
                completeActivityTrace();
                StatsEngine.get().inc("Supportability/AgentHealth/HealthyActivityTraces");
                return;
            } else if (Constants.MILLIS_IN_A_MINUTE + j2 < currentTimeMillis) {
                log.debug("Completing activity trace after hitting unhealthy timeout (60000ms)");
                completeActivityTrace();
                StatsEngine.get().inc("Supportability/AgentHealth/UnhealthyActivityTraces");
                return;
            } else {
                return;
            }
        }
        log.debug("TraceMachine is inactive");
    }

    public void onHarvestSendFailed() {
        try {
            traceMachine.activityTrace.incrementReportAttemptCount();
        } catch (NullPointerException e) {
        }
    }
}
