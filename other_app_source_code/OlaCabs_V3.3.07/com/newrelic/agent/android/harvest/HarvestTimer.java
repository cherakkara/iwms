package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.TicToc;
import com.newrelic.agent.android.util.NamedThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HarvestTimer implements Runnable {
    private static final long DEFAULT_HARVEST_PERIOD = 60000;
    private static final long HARVEST_PERIOD_LEEWAY = 1000;
    private static final long NEVER_TICKED = -1;
    protected final Harvester harvester;
    protected long lastTickTime;
    private final AgentLog log;
    private long period;
    private final ScheduledExecutorService scheduler;
    private long startTimeMs;
    private ScheduledFuture tickFuture;

    /* renamed from: com.newrelic.agent.android.harvest.HarvestTimer.1 */
    class C07331 implements Runnable {
        final /* synthetic */ HarvestTimer val$timer;

        C07331(HarvestTimer harvestTimer) {
            this.val$timer = harvestTimer;
        }

        public void run() {
            this.val$timer.tick();
        }
    }

    public HarvestTimer(Harvester harvester) {
        this.scheduler = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("Harvester"));
        this.log = AgentLogManager.getAgentLog();
        this.period = DEFAULT_HARVEST_PERIOD;
        this.harvester = harvester;
    }

    public void run() {
        synchronized (this) {
            try {
                tickIfReady();
            } catch (Exception e) {
                this.log.error("HarvestTimer: Exception in timer tick: " + e.getMessage());
                e.printStackTrace();
                AgentHealth.noticeException(e);
            }
        }
    }

    private void tickIfReady() {
        long timeSinceLastTick = timeSinceLastTick();
        if (HARVEST_PERIOD_LEEWAY + timeSinceLastTick >= this.period || timeSinceLastTick == NEVER_TICKED) {
            this.log.debug("HarvestTimer: time since last tick: " + timeSinceLastTick);
            timeSinceLastTick = now();
            try {
                tick();
            } catch (Exception e) {
                this.log.error("HarvestTimer: Exception in timer tick: " + e.getMessage());
                e.printStackTrace();
                AgentHealth.noticeException(e);
            }
            this.lastTickTime = timeSinceLastTick;
            this.log.debug("Set last tick time to: " + this.lastTickTime);
            return;
        }
        this.log.debug("HarvestTimer: Tick is too soon (" + timeSinceLastTick + " delta) Last tick time: " + this.lastTickTime + " . Skipping.");
    }

    protected void tick() {
        this.log.debug("Harvest: tick");
        TicToc ticToc = new TicToc();
        ticToc.tic();
        try {
            this.harvester.execute();
        } catch (Exception e) {
            this.log.error("HarvestTimer: Exception in harvest execute: " + e.getMessage());
            e.printStackTrace();
            AgentHealth.noticeException(e);
        }
        this.log.debug("Harvest: executed");
        if (this.harvester.isDisabled()) {
            stop();
        }
        this.log.debug("HarvestTimer tick took " + ticToc.toc() + "ms");
    }

    public void start() {
        if (isRunning()) {
            this.log.warning("HarvestTimer: Attempting to start while already running");
        } else if (this.period <= 0) {
            this.log.error("HarvestTimer: Refusing to start with a period of 0 ms");
        } else {
            this.log.debug("HarvestTimer: Starting with a period of " + this.period + "ms");
            this.startTimeMs = System.currentTimeMillis();
            this.tickFuture = this.scheduler.scheduleAtFixedRate(this, 0, this.period, TimeUnit.MILLISECONDS);
            this.harvester.start();
        }
    }

    public void stop() {
        if (isRunning()) {
            this.log.debug("HarvestTimer: Stopped.");
            this.startTimeMs = 0;
            this.harvester.stop();
            this.tickFuture.cancel(true);
            this.tickFuture = null;
            return;
        }
        this.log.warning("HarvestTimer: Attempting to stop when not running");
    }

    public void shutdown() {
        this.scheduler.shutdownNow();
    }

    public void tickNow() {
        try {
            this.scheduler.schedule(new C07331(this), 0, TimeUnit.SECONDS).get();
        } catch (Exception e) {
            this.log.error("Exception waiting for tickNow to finish: " + e.getMessage());
            e.printStackTrace();
            AgentHealth.noticeException(e);
        }
    }

    public boolean isRunning() {
        return this.tickFuture != null;
    }

    public void setPeriod(long j) {
        this.period = j;
    }

    public long timeSinceLastTick() {
        if (this.lastTickTime == 0) {
            return NEVER_TICKED;
        }
        return now() - this.lastTickTime;
    }

    public long timeSinceStart() {
        if (this.startTimeMs == 0) {
            return 0;
        }
        return now() - this.startTimeMs;
    }

    private long now() {
        return System.currentTimeMillis();
    }
}
