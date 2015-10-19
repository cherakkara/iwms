package com.newrelic.agent.android.instrumentation.io;

import java.util.EventObject;

public final class StreamCompleteEvent extends EventObject {
    private static final long serialVersionUID = 1;
    private final long bytes;
    private final Exception exception;

    public StreamCompleteEvent(Object obj, long j, Exception exception) {
        super(obj);
        this.bytes = j;
        this.exception = exception;
    }

    public StreamCompleteEvent(Object obj, long j) {
        this(obj, j, null);
    }

    public long getBytes() {
        return this.bytes;
    }

    public Exception getException() {
        return this.exception;
    }

    public boolean isError() {
        return this.exception != null;
    }
}
