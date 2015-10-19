package com.newrelic.agent.android.api.v1;

public interface ConnectionListener {
    void connected(ConnectionEvent connectionEvent);

    void disconnected(ConnectionEvent connectionEvent);
}
