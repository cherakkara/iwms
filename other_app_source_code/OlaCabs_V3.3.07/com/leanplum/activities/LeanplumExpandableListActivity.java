package com.leanplum.activities;

import android.app.ExpandableListActivity;
import android.content.res.Resources;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class LeanplumExpandableListActivity extends ExpandableListActivity implements TraceFieldInterface {
    private LeanplumActivityHelper f8731a;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    private LeanplumActivityHelper m12718a() {
        if (this.f8731a == null) {
            this.f8731a = new LeanplumActivityHelper(this);
        }
        return this.f8731a;
    }

    protected void onPause() {
        super.onPause();
        m12718a().onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        m12718a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12718a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12718a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12718a().setContentView(i);
    }
}
