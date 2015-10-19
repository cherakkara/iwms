package com.leanplum.activities;

import android.app.Activity;
import android.content.res.Resources;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public abstract class LeanplumActivity extends Activity implements TraceFieldInterface {
    private LeanplumActivityHelper f8728a;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    private LeanplumActivityHelper m12715a() {
        if (this.f8728a == null) {
            this.f8728a = new LeanplumActivityHelper(this);
        }
        return this.f8728a;
    }

    protected void onPause() {
        super.onPause();
        m12715a().onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        m12715a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12715a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12715a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12715a().setContentView(i);
    }
}
