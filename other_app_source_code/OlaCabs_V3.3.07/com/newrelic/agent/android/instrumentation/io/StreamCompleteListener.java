package com.newrelic.agent.android.instrumentation.io;

public interface StreamCompleteListener {
    void streamComplete(StreamCompleteEvent streamCompleteEvent);

    void streamError(StreamCompleteEvent streamCompleteEvent);
}
