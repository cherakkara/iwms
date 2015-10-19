package com.newrelic.agent.android.sample;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Sample;
import com.newrelic.agent.android.tracing.Sample.SampleType;
import com.newrelic.agent.android.tracing.TraceLifecycleAware;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.NamedThreadFactory;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Sampler implements TraceLifecycleAware, Runnable {
    private static final int KB_IN_MB = 1024;
    private static final int[] PID;
    private static final long SAMPLE_FREQ_MS = 100;
    private static boolean cpuSamplingDisabled;
    private static final AgentLog log;
    private static Sampler sampler;
    private static final ReentrantLock samplerLock;
    private final ActivityManager activityManager;
    private RandomAccessFile appStatFile;
    private final AtomicBoolean isRunning;
    private Long lastAppCpuTime;
    private Long lastCpuTime;
    private RandomAccessFile procStatFile;
    private ScheduledFuture sampleFuture;
    private final EnumMap<SampleType, Collection<Sample>> samples;
    private final ScheduledExecutorService scheduler;

    static {
        PID = new int[]{Process.myPid()};
        log = AgentLogManager.getAgentLog();
        samplerLock = new ReentrantLock();
        cpuSamplingDisabled = false;
    }

    private Sampler(Context context) {
        this.samples = new EnumMap(SampleType.class);
        this.scheduler = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("Sampler"));
        this.isRunning = new AtomicBoolean(false);
        this.activityManager = (ActivityManager) context.getSystemService("activity");
        this.samples.put(SampleType.MEMORY, new ArrayList());
        this.samples.put(SampleType.CPU, new ArrayList());
    }

    public static void init(Context context) {
        samplerLock.lock();
        sampler = new Sampler(context);
        samplerLock.unlock();
        TraceMachine.addTraceListener(sampler);
        log.debug("Sampler Initialized");
    }

    public static void start() {
        samplerLock.lock();
        if (sampler == null) {
            samplerLock.unlock();
            return;
        }
        sampler.schedule();
        samplerLock.unlock();
        log.debug("Sampler started");
    }

    public static void stop() {
        samplerLock.lock();
        if (sampler == null) {
            samplerLock.unlock();
            return;
        }
        sampler.stop(false);
        samplerLock.unlock();
    }

    public static void stopNow() {
        samplerLock.lock();
        if (sampler == null) {
            samplerLock.unlock();
            return;
        }
        sampler.stop(true);
        samplerLock.unlock();
    }

    public static void shutdown() {
        samplerLock.lock();
        if (sampler == null) {
            samplerLock.unlock();
            return;
        }
        TraceMachine.removeTraceListener(sampler);
        stop();
        sampler = null;
        samplerLock.unlock();
    }

    public void run() {
        try {
            if (this.isRunning.get()) {
                sample();
            }
        } catch (Exception e) {
            log.error("Caught exception while running the sampler", e);
            AgentHealth.noticeException(e);
        }
    }

    private void schedule() {
        if (!this.isRunning.get()) {
            clear();
            this.isRunning.set(true);
            this.sampleFuture = this.scheduler.scheduleAtFixedRate(this, 0, SAMPLE_FREQ_MS, TimeUnit.MILLISECONDS);
        }
    }

    private void stop(boolean z) {
        samplerLock.lock();
        if (this.isRunning.get()) {
            this.isRunning.set(false);
            this.sampleFuture.cancel(z);
            resetCpuSampler();
            samplerLock.unlock();
            log.debug("Sampler stopped");
            return;
        }
        samplerLock.unlock();
    }

    public static boolean isRunning() {
        if (sampler == null || sampler.sampleFuture.isDone()) {
            return false;
        }
        return true;
    }

    private void sample() {
        samplerLock.lock();
        try {
            Sample sampleMemory = sampleMemory();
            if (sampleMemory != null) {
                getSampleCollection(SampleType.MEMORY).add(sampleMemory);
            }
            sampleMemory = sampleCpu();
            if (sampleMemory != null) {
                getSampleCollection(SampleType.CPU).add(sampleMemory);
            }
            samplerLock.unlock();
        } catch (Throwable th) {
            samplerLock.unlock();
        }
    }

    private void clear() {
        for (Collection clear : this.samples.values()) {
            clear.clear();
        }
    }

    public static Sample sampleMemory() {
        if (sampler == null) {
            return null;
        }
        return sampleMemory(sampler.activityManager);
    }

    public static Sample sampleMemory(ActivityManager activityManager) {
        int totalPss = activityManager.getProcessMemoryInfo(PID)[0].getTotalPss();
        if (totalPss < 0) {
            return null;
        }
        Sample sample = new Sample(SampleType.MEMORY);
        sample.setSampleValue(((double) totalPss) / 1024.0d);
        return sample;
    }

    public Sample sampleCpu() {
        if (cpuSamplingDisabled) {
            return null;
        }
        try {
            if (this.procStatFile == null || this.appStatFile == null) {
                this.procStatFile = new RandomAccessFile("/proc/stat", "r");
                this.appStatFile = new RandomAccessFile("/proc/" + PID[0] + "/stat", "r");
            } else {
                this.procStatFile.seek(0);
                this.appStatFile.seek(0);
            }
            String readLine = this.procStatFile.readLine();
            String readLine2 = this.appStatFile.readLine();
            String[] split = readLine.split(" ");
            String[] split2 = readLine2.split(" ");
            long parseLong = (((((Long.parseLong(split[2]) + Long.parseLong(split[3])) + Long.parseLong(split[4])) + Long.parseLong(split[5])) + Long.parseLong(split[6])) + Long.parseLong(split[7])) + Long.parseLong(split[8]);
            long parseLong2 = Long.parseLong(split2[14]) + Long.parseLong(split2[13]);
            if (this.lastCpuTime == null && this.lastAppCpuTime == null) {
                this.lastCpuTime = Long.valueOf(parseLong);
                this.lastAppCpuTime = Long.valueOf(parseLong2);
                return null;
            }
            Sample sample = new Sample(SampleType.CPU);
            sample.setSampleValue((((double) (parseLong2 - this.lastAppCpuTime.longValue())) / ((double) (parseLong - this.lastCpuTime.longValue()))) * 100.0d);
            this.lastCpuTime = Long.valueOf(parseLong);
            this.lastAppCpuTime = Long.valueOf(parseLong2);
            return sample;
        } catch (Exception e) {
            cpuSamplingDisabled = true;
            log.debug("Exception hit while CPU sampling: " + e.getMessage());
            AgentHealth.noticeException(e);
            return null;
        }
    }

    private void resetCpuSampler() {
        this.lastCpuTime = null;
        this.lastAppCpuTime = null;
        if (this.appStatFile != null && this.procStatFile != null) {
            try {
                this.appStatFile.close();
                this.procStatFile.close();
                this.appStatFile = null;
                this.procStatFile = null;
            } catch (Exception e) {
                log.debug("Exception hit while resetting CPU sampler: " + e.getMessage());
                AgentHealth.noticeException(e);
            }
        }
    }

    public static Map<SampleType, Collection<Sample>> copySamples() {
        samplerLock.lock();
        if (sampler == null) {
            samplerLock.unlock();
            return new HashMap();
        }
        Map enumMap = new EnumMap(sampler.samples);
        for (SampleType sampleType : sampler.samples.keySet()) {
            enumMap.put(sampleType, new ArrayList((Collection) sampler.samples.get(sampleType)));
        }
        samplerLock.unlock();
        return Collections.unmodifiableMap(enumMap);
    }

    private Collection<Sample> getSampleCollection(SampleType sampleType) {
        return (Collection) this.samples.get(sampleType);
    }

    public void onEnterMethod() {
        if (!this.isRunning.get()) {
            start();
        }
    }

    public void onExitMethod() {
    }

    public void onTraceStart(ActivityTrace activityTrace) {
        start();
    }

    public void onTraceComplete(ActivityTrace activityTrace) {
        stop();
        activityTrace.setVitals(copySamples());
        clear();
    }
}
