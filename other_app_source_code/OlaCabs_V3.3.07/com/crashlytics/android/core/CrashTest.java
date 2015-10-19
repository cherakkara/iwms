package com.crashlytics.android.core;

import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import p004b.p005a.p006a.p007a.Fabric;

public class CrashTest {

    /* renamed from: com.crashlytics.android.core.CrashTest.1 */
    class C01211 extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
        public Trace _nr_trace;
        final /* synthetic */ long val$delayMs;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        C01211(long j) {
            this.val$delayMs = j;
        }

        protected Void doInBackground(Void... voidArr) {
            try {
                Thread.sleep(this.val$delayMs);
            } catch (InterruptedException e) {
            }
            CrashTest.this.throwRuntimeException("Background thread crash");
            return null;
        }
    }

    public void throwRuntimeException(String str) {
        throw new RuntimeException(str);
    }

    public int stackOverflow() {
        return stackOverflow() + ((int) Math.random());
    }

    public void indexOutOfBounds() {
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Out of bounds value: " + new int[2][10]);
    }

    public void crashAsyncTask(long j) {
        AsyncTask c01211 = new C01211(j);
        Void[] voidArr = new Void[]{(Void) null};
        if (c01211 instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(c01211, voidArr);
        } else {
            c01211.execute(voidArr);
        }
    }

    public void throwFiveChainedExceptions() {
        try {
            privateMethodThatThrowsException("1");
        } catch (Throwable e) {
            throw new RuntimeException("2", e);
        } catch (Throwable e2) {
            try {
                throw new RuntimeException("3", e2);
            } catch (Throwable e22) {
                try {
                    throw new RuntimeException("4", e22);
                } catch (Throwable e222) {
                    throw new RuntimeException("5", e222);
                }
            }
        }
    }

    private void privateMethodThatThrowsException(String str) {
        throw new RuntimeException(str);
    }
}
