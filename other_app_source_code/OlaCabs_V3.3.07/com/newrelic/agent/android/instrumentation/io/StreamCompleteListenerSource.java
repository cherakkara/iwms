package com.newrelic.agent.android.instrumentation.io;

public interface StreamCompleteListenerSource {
    void addStreamCompleteListener(StreamCompleteListener streamCompleteListener);

    void removeStreamCompleteListener(StreamCompleteListener streamCompleteListener);
}
