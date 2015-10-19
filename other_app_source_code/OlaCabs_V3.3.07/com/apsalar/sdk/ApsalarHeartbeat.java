package com.apsalar.sdk;

import android.content.Context;
import com.newrelic.agent.android.instrumentation.Trace;

/* compiled from: ApEvent */
class ApsalarHeartbeat extends ApsalarEvent implements ApsalarAPI {
    protected void init(Context context) {
        this.ctx = context;
        this.eventType = 2;
        this.eventTime = System.currentTimeMillis();
    }

    protected ApsalarHeartbeat(Context context, ApsalarSessionInfo apsalarSessionInfo, long j) {
        super(context, apsalarSessionInfo, "heartbeat", j, Trace.NULL);
    }
}
