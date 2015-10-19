package com.apsalar.sdk;

import com.newrelic.agent.android.instrumentation.Trace;

class ApsalarHttpResponse {
    public String body;
    public int code;
    public String message;
    public int status;

    ApsalarHttpResponse(int i, String str, String str2, int i2) {
        this.code = i;
        if (str == null) {
            str = Trace.NULL;
        }
        this.message = str;
        if (str2 == null) {
            str2 = Trace.NULL;
        }
        this.body = str2;
        this.status = i2;
    }
}
