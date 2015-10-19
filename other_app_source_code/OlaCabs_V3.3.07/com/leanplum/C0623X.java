package com.leanplum;

import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Map;

/* renamed from: com.leanplum.X */
final class C0623X extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
    public Trace _nr_trace;
    private /* synthetic */ C0618S f8660a;
    private final /* synthetic */ String f8661b;
    private final /* synthetic */ Map f8662c;

    C0623X(C0618S c0618s, String str, Map map) {
        this.f8660a = c0618s;
        this.f8661b = str;
        this.f8662c = map;
    }

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected final /* synthetic */ Object doInBackground(Object... objArr) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "X#doInBackground", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "X#doInBackground", null);
                break;
            }
        }
        this.f8660a.m12527a(C0633g.f8794a, C0633g.f8810q, this.f8661b, this.f8662c);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return null;
    }
}
