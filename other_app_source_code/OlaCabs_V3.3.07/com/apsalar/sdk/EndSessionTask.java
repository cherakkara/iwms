package com.apsalar.sdk;

import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;

/* compiled from: ApsalarSessionInfo */
class EndSessionTask extends AsyncTask<ApsalarEvent, Void, Integer> implements TraceFieldInterface {
    static final String TAG = "Apsalar SDK/EndSessionTask";
    public Trace _nr_trace;
    Integer status;

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    EndSessionTask() {
        this.status = Integer.valueOf(-1);
    }

    protected Integer doInBackground(ApsalarEvent... apsalarEventArr) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        this.status = Integer.valueOf(apsalarEventArr[0].REST());
        instance.getClass();
        return this.status;
    }

    protected void onPostExcute(Integer num) {
        ApSingleton.getInstance(ApSingleton.getContext()).getClass();
    }
}
