package com.newrelic.agent.android.logging;

import android.util.Log;

public class AndroidAgentLog implements AgentLog {
    private static final String TAG = "com.newrelic.agent.android";
    private int level;

    public AndroidAgentLog() {
        this.level = 3;
    }

    public void debug(String str) {
        if (this.level == 5) {
            Log.d(TAG, str);
        }
    }

    public void verbose(String str) {
        if (this.level >= 4) {
            Log.v(TAG, str);
        }
    }

    public void info(String str) {
        if (this.level >= 3) {
            Log.i(TAG, str);
        }
    }

    public void warning(String str) {
        if (this.level >= 2) {
            Log.w(TAG, str);
        }
    }

    public void error(String str) {
        if (this.level >= 1) {
            Log.e(TAG, str);
        }
    }

    public void error(String str, Throwable th) {
        if (this.level >= 1) {
            Log.e(TAG, str, th);
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        if (i > 5 || i < 1) {
            throw new IllegalArgumentException("Log level is not between ERROR and DEBUG");
        }
        this.level = i;
    }
}
