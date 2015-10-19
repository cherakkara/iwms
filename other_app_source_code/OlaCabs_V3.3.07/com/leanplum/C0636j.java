package com.leanplum;

import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.List;

/* renamed from: com.leanplum.j */
final class C0636j extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
    public Trace _nr_trace;
    private final /* synthetic */ List f8813a;
    private final /* synthetic */ List f8814b;

    C0636j(List list, List list2) {
        this.f8813a = list;
        this.f8814b = list2;
    }

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected final /* synthetic */ Object doInBackground(Object... objArr) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "j#doInBackground", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "j#doInBackground", null);
                break;
            }
        }
        FileManager.m12426b(this.f8813a, this.f8814b);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return null;
    }
}
