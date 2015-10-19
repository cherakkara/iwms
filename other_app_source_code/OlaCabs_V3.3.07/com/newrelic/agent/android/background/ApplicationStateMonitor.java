package com.newrelic.agent.android.background;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.util.NamedThreadFactory;
import com.olacabs.customer.p076d.by;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ApplicationStateMonitor implements Runnable {
    private static ApplicationStateMonitor instance;
    private static final AgentLog log;
    private final int activitySnoozeTimeInMilliseconds;
    private final ArrayList<ApplicationStateListener> applicationStateListeners;
    private long count;
    private final Object foregroundLock;
    private boolean foregrounded;
    private final Object snoozeLock;
    private long snoozeStartTime;

    static {
        log = AgentLogManager.getAgentLog();
    }

    private ApplicationStateMonitor() {
        this(5, 5, TimeUnit.SECONDS, by.DEFAULT_TIMEOUT_MS);
    }

    ApplicationStateMonitor(int i, int i2, TimeUnit timeUnit, int i3) {
        this.count = 0;
        this.snoozeStartTime = 0;
        this.snoozeLock = new Object();
        this.applicationStateListeners = new ArrayList();
        this.foregrounded = true;
        this.foregroundLock = new Object();
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("AppStateMon"));
        this.activitySnoozeTimeInMilliseconds = i3;
        scheduledThreadPoolExecutor.scheduleAtFixedRate(this, (long) i, (long) i2, timeUnit);
        log.info("Application state monitor has started");
    }

    public static ApplicationStateMonitor getInstance() {
        if (instance == null) {
            instance = new ApplicationStateMonitor();
        }
        return instance;
    }

    public void addApplicationStateListener(ApplicationStateListener applicationStateListener) {
        synchronized (this.applicationStateListeners) {
            this.applicationStateListeners.add(applicationStateListener);
        }
    }

    public void removeApplicationStateListener(ApplicationStateListener applicationStateListener) {
        synchronized (this.applicationStateListeners) {
            this.applicationStateListeners.remove(applicationStateListener);
        }
    }

    public void run() {
        synchronized (this.foregroundLock) {
            if (this.foregrounded && getSnoozeTime() >= ((long) this.activitySnoozeTimeInMilliseconds)) {
                notifyApplicationInBackground();
                this.foregrounded = false;
            }
        }
    }

    public void uiHidden() {
        synchronized (this.foregroundLock) {
            if (this.foregrounded) {
                log.info("UI has become hidden (app backgrounded)");
                notifyApplicationInBackground();
                this.foregrounded = false;
            }
        }
    }

    public void activityStopped() {
        synchronized (this.foregroundLock) {
            synchronized (this.snoozeLock) {
                this.count--;
                if (this.count == 0) {
                    this.snoozeStartTime = System.currentTimeMillis();
                }
            }
        }
    }

    public void activityStarted() {
        synchronized (this.foregroundLock) {
            synchronized (this.snoozeLock) {
                this.count++;
                if (this.count == 1) {
                    this.snoozeStartTime = 0;
                }
            }
            if (!this.foregrounded) {
                log.verbose("Application appears to be in the foreground");
                notifyApplicationInForeground();
                this.foregrounded = true;
            }
        }
    }

    private void notifyApplicationInBackground() {
        ArrayList arrayList;
        log.verbose("Application appears to have gone to the background");
        synchronized (this.applicationStateListeners) {
            arrayList = new ArrayList(this.applicationStateListeners);
        }
        ApplicationStateEvent applicationStateEvent = new ApplicationStateEvent(this);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ApplicationStateListener) it.next()).applicationBackgrounded(applicationStateEvent);
        }
    }

    private void notifyApplicationInForeground() {
        ArrayList arrayList;
        synchronized (this.applicationStateListeners) {
            arrayList = new ArrayList(this.applicationStateListeners);
        }
        ApplicationStateEvent applicationStateEvent = new ApplicationStateEvent(this);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ApplicationStateListener) it.next()).applicationForegrounded(applicationStateEvent);
        }
    }

    private long getSnoozeTime() {
        long j = 0;
        synchronized (this.foregroundLock) {
            synchronized (this.snoozeLock) {
                if (this.snoozeStartTime == 0) {
                } else {
                    j = System.currentTimeMillis() - this.snoozeStartTime;
                }
            }
        }
        return j;
    }
}
