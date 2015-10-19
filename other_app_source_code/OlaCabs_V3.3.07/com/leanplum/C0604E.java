package com.leanplum;

import android.content.Context;
import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Map;

/* renamed from: com.leanplum.E */
final class C0604E extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
    public Trace _nr_trace;
    private final /* synthetic */ Context f8540a;
    private final /* synthetic */ String f8541b;
    private final /* synthetic */ Map f8542c;
    private final /* synthetic */ boolean f8543d;

    C0604E(Context context, String str, Map map, boolean z) {
        this.f8540a = context;
        this.f8541b = str;
        this.f8542c = map;
        this.f8543d = z;
    }

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected final /* synthetic */ Object doInBackground(Object... objArr) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "E#doInBackground", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "E#doInBackground", null);
                break;
            }
        }
        Leanplum.m12450b(this.f8540a, this.f8541b, this.f8542c, this.f8543d);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return null;
    }
}
