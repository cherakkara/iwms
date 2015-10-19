package com.newrelic.agent.android.stats;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.metric.Metric;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class StatsEngine extends HarvestAdapter {
    public static final StatsEngine INSTANCE;
    public boolean enabled;
    private ConcurrentHashMap<String, Metric> statsMap;

    static {
        INSTANCE = new StatsEngine();
    }

    private StatsEngine() {
        this.enabled = true;
        this.statsMap = new ConcurrentHashMap();
    }

    public static StatsEngine get() {
        return INSTANCE;
    }

    public void inc(String str) {
        Metric lazyGet = lazyGet(str);
        synchronized (lazyGet) {
            lazyGet.increment();
        }
    }

    public void inc(String str, long j) {
        Metric lazyGet = lazyGet(str);
        synchronized (lazyGet) {
            lazyGet.increment(j);
        }
    }

    public void sample(String str, float f) {
        Metric lazyGet = lazyGet(str);
        synchronized (lazyGet) {
            lazyGet.sample((double) f);
        }
    }

    public void sampleTimeMs(String str, long j) {
        sample(str, ((float) j) / 1000.0f);
    }

    public static void populateMetrics() {
        for (Entry value : INSTANCE.getStatsMap().entrySet()) {
            TaskQueue.queue((Metric) value.getValue());
        }
    }

    public void onHarvest() {
        populateMetrics();
        reset();
    }

    public static void reset() {
        INSTANCE.getStatsMap().clear();
    }

    public static synchronized void disable() {
        synchronized (StatsEngine.class) {
            INSTANCE.enabled = false;
        }
    }

    public static synchronized void enable() {
        synchronized (StatsEngine.class) {
            INSTANCE.enabled = true;
        }
    }

    public ConcurrentHashMap<String, Metric> getStatsMap() {
        return this.statsMap;
    }

    private Metric lazyGet(String str) {
        Metric metric = (Metric) this.statsMap.get(str);
        if (metric == null) {
            metric = new Metric(str);
            if (this.enabled) {
                this.statsMap.put(str, metric);
            }
        }
        return metric;
    }
}
