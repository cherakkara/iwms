package com.newrelic.agent.android.analytics;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.olacabs.customer.utils.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class EventManagerImpl implements EventManager {
    public static int DEFAULT_MAX_EVENT_BUFFER_SIZE;
    public static int DEFAULT_MAX_EVENT_BUFFER_TIME;
    private static final AgentLog log;
    private List<AnalyticsEvent> events;
    private AtomicInteger eventsEjected;
    private AtomicInteger eventsRecorded;
    private long firstEventTimestamp;
    private AtomicBoolean initialized;
    private int maxBufferTimeInSec;
    private int maxEventPoolSize;

    static {
        log = AgentLogManager.getAgentLog();
        DEFAULT_MAX_EVENT_BUFFER_TIME = 600;
        DEFAULT_MAX_EVENT_BUFFER_SIZE = Constants.MILLIS_IN_A_SECOND;
    }

    public EventManagerImpl() {
        this(DEFAULT_MAX_EVENT_BUFFER_SIZE, DEFAULT_MAX_EVENT_BUFFER_TIME);
    }

    public EventManagerImpl(int i, int i2) {
        this.initialized = new AtomicBoolean(false);
        this.eventsRecorded = new AtomicInteger(0);
        this.eventsEjected = new AtomicInteger(0);
        this.events = Collections.synchronizedList(new ArrayList(i));
        this.maxBufferTimeInSec = i2;
        this.maxEventPoolSize = i;
        this.firstEventTimestamp = 0;
        this.eventsRecorded.set(0);
        this.eventsEjected.set(0);
    }

    public void initialize() {
        if (this.initialized.compareAndSet(false, true)) {
            this.firstEventTimestamp = 0;
            this.eventsRecorded.set(0);
            this.eventsEjected.set(0);
            empty();
            return;
        }
        log.debug("EventManagerImpl has already been initialized.  Bypassing...");
    }

    public void shutdown() {
        this.initialized.set(false);
    }

    public int size() {
        return this.events.size();
    }

    public void empty() {
        this.events.clear();
    }

    public boolean isTransmitRequired() {
        log.debug("EventManagerImpl.isTransmitRequired: initialized: " + this.initialized.get());
        log.debug("EventManagerImpl.isTransmitRequired: buffer size: " + this.events.size());
        return (!this.initialized.get() && this.events.size() > 0) || isMaxEventBufferTimeExceeded();
    }

    public boolean addEvent(AnalyticsEvent analyticsEvent) {
        int incrementAndGet = this.eventsRecorded.incrementAndGet();
        if (this.events.size() == 0) {
            log.debug("EventManagerImpl.addEvent - Queue is currently empty, setting to first event timestamp to " + System.currentTimeMillis());
            this.firstEventTimestamp = System.currentTimeMillis();
        }
        if (this.events.size() >= this.maxEventPoolSize) {
            this.eventsEjected.incrementAndGet();
            incrementAndGet = (int) (((double) incrementAndGet) * Math.random());
            if (incrementAndGet >= this.maxEventPoolSize) {
                return true;
            }
            this.events.remove(incrementAndGet);
        }
        return this.events.add(analyticsEvent);
    }

    public int getEventsRecorded() {
        return this.eventsRecorded.get();
    }

    public int getEventsEjected() {
        return this.eventsEjected.get();
    }

    public boolean isMaxEventBufferTimeExceeded() {
        log.debug("EventManagerImpl.isMaxEventBufferTimeExceeded invoked.  maxBufferTimeInSec: " + this.maxBufferTimeInSec + ", firstEventTimestamp: " + this.firstEventTimestamp + ", current timestamp: " + System.currentTimeMillis() + ", delta: " + (System.currentTimeMillis() - this.firstEventTimestamp));
        if (this.firstEventTimestamp <= 0 || System.currentTimeMillis() - this.firstEventTimestamp <= ((long) (this.maxBufferTimeInSec * Constants.MILLIS_IN_A_SECOND))) {
            return false;
        }
        return true;
    }

    public boolean isMaxEventPoolSizeExceeded() {
        log.debug("EventManagerImpl.isMaxEventBufferSizeExceeded invoked.  maxEventPoolSize: " + this.maxEventPoolSize + ", current pool size: " + this.events.size());
        return this.events.size() > this.maxEventPoolSize;
    }

    public int getMaxEventPoolSize() {
        return this.maxEventPoolSize;
    }

    public void setMaxEventPoolSize(int i) {
        this.maxEventPoolSize = i;
    }

    public void setMaxEventBufferTime(int i) {
        this.maxBufferTimeInSec = i;
    }

    public int getMaxEventBufferTime() {
        return this.maxBufferTimeInSec;
    }

    public Collection<AnalyticsEvent> getQueuedEvents() {
        return Collections.unmodifiableCollection(this.events);
    }
}
