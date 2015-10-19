package com.leanplum.activities;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class LeanplumActionBarActivity extends ActionBarActivity implements TraceFieldInterface {
    private LeanplumActivityHelper f8727a;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    private LeanplumActivityHelper m12714a() {
        if (this.f8727a == null) {
            this.f8727a = new LeanplumActivityHelper(this);
        }
        return this.f8727a;
    }

    protected void onPause() {
        super.onPause();
        m12714a().onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        m12714a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12714a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12714a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12714a().setContentView(i);
    }
}
