package com.newrelic.agent.android.analytics;

import java.util.Collection;

public interface EventManager {
    boolean addEvent(AnalyticsEvent analyticsEvent);

    void empty();

    int getEventsEjected();

    int getEventsRecorded();

    int getMaxEventBufferTime();

    int getMaxEventPoolSize();

    Collection<AnalyticsEvent> getQueuedEvents();

    void initialize();

    boolean isMaxEventBufferTimeExceeded();

    boolean isMaxEventPoolSizeExceeded();

    boolean isTransmitRequired();

    void setMaxEventBufferTime(int i);

    void setMaxEventPoolSize(int i);

    void shutdown();

    int size();
}
