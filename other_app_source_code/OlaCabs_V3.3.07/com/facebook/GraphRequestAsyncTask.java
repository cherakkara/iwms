package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: com.facebook.m */
public class GraphRequestAsyncTask extends AsyncTask<Void, Void, List<GraphResponse>> implements TraceFieldInterface {
    private static final String f1010a;
    private static Method f1011b;
    public Trace _nr_trace;
    private final HttpURLConnection f1012c;
    private final GraphRequestBatch f1013d;
    private Exception f1014e;

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "m#doInBackground", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "m#doInBackground", null);
                break;
            }
        }
        List a = m1325a((Void[]) objArr);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return a;
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "m#onPostExecute", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "m#onPostExecute", null);
                break;
            }
        }
        m1326a((List) obj);
        TraceMachine.exitMethod();
    }

    static {
        f1010a = GraphRequestAsyncTask.class.getCanonicalName();
        for (Method method : AsyncTask.class.getMethods()) {
            if ("executeOnExecutor".equals(method.getName())) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 2 && parameterTypes[0] == Executor.class && parameterTypes[1].isArray()) {
                    f1011b = method;
                    return;
                }
            }
        }
    }

    public GraphRequestAsyncTask(GraphRequestBatch graphRequestBatch) {
        this(null, graphRequestBatch);
    }

    public GraphRequestAsyncTask(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        this.f1013d = graphRequestBatch;
        this.f1012c = httpURLConnection;
    }

    public String toString() {
        return "{RequestAsyncTask: " + " connection: " + this.f1012c + ", requests: " + this.f1013d + "}";
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (this.f1013d.m1338c() == null) {
            this.f1013d.m1332a(new Handler());
        }
    }

    protected void m1326a(List<GraphResponse> list) {
        super.onPostExecute(list);
        if (this.f1014e != null) {
            Log.d(f1010a, String.format("onPostExecute: exception encountered during request: %s", new Object[]{this.f1014e.getMessage()}));
        }
    }

    protected List<GraphResponse> m1325a(Void... voidArr) {
        try {
            if (this.f1012c == null) {
                return this.f1013d.m1342g();
            }
            return GraphRequest.m739a(this.f1012c, this.f1013d);
        } catch (Exception e) {
            this.f1014e = e;
            return null;
        }
    }

    GraphRequestAsyncTask m1324a() {
        if (f1011b != null) {
            try {
                f1011b.invoke(this, new Object[]{FacebookSdk.m1206d(), null});
            } catch (InvocationTargetException e) {
            } catch (IllegalAccessException e2) {
            }
        } else {
            Void[] voidArr = new Void[0];
            if (this instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(this, voidArr);
            } else {
                execute(voidArr);
            }
        }
        return this;
    }
}
