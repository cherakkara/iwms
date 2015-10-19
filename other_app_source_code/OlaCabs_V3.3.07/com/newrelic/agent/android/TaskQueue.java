package com.newrelic.agent.android;

import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.harvest.AgentHealthException;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.util.NamedThreadFactory;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskQueue extends HarvestAdapter {
    private static final long DEQUEUE_PERIOD_MS = 1000;
    private static Future dequeueFuture;
    private static final Runnable dequeueTask;
    private static final ConcurrentLinkedQueue<Object> queue;
    private static final ScheduledExecutorService queueExecutor;

    /* renamed from: com.newrelic.agent.android.TaskQueue.1 */
    static class C07301 implements Runnable {
        C07301() {
        }

        public void run() {
            TaskQueue.dequeue();
        }
    }

    static {
        queueExecutor = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("TaskQueue"));
        queue = new ConcurrentLinkedQueue();
        dequeueTask = new C07301();
    }

    public static void queue(Object obj) {
        queue.add(obj);
    }

    public static void backgroundDequeue() {
        queueExecutor.execute(dequeueTask);
    }

    public static void synchronousDequeue() {
        try {
            queueExecutor.submit(dequeueTask).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
    }

    public static void start() {
        if (dequeueFuture == null) {
            dequeueFuture = queueExecutor.scheduleAtFixedRate(dequeueTask, 0, DEQUEUE_PERIOD_MS, TimeUnit.MILLISECONDS);
        }
    }

    public static void stop() {
        if (dequeueFuture != null) {
            dequeueFuture.cancel(true);
            dequeueFuture = null;
        }
    }

    private static void dequeue() {
        if (queue.size() != 0) {
            Measurements.setBroadcastNewMeasurements(false);
            while (!queue.isEmpty()) {
                try {
                    Object remove = queue.remove();
                    if (remove instanceof ActivityTrace) {
                        Harvest.addActivityTrace((ActivityTrace) remove);
                    } else if (remove instanceof Metric) {
                        Harvest.addMetric((Metric) remove);
                    } else if (remove instanceof AgentHealthException) {
                        Harvest.addAgentHealthException((AgentHealthException) remove);
                    } else if (remove instanceof Trace) {
                        Measurements.addTracedMethod((Trace) remove);
                    } else if (remove instanceof HttpTransactionMeasurement) {
                        Measurements.addHttpTransaction((HttpTransactionMeasurement) remove);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    AgentHealth.noticeException(e);
                }
            }
            Measurements.broadcast();
            Measurements.setBroadcastNewMeasurements(true);
        }
    }

    public static int size() {
        return queue.size();
    }

    public static void clear() {
        queue.clear();
    }
}
