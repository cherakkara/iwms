package com.leanplum.activities;

import android.app.ListActivity;
import android.content.res.Resources;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class LeanplumListActivity extends ListActivity implements TraceFieldInterface {
    private LeanplumActivityHelper f8734a;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    private LeanplumActivityHelper m12721a() {
        if (this.f8734a == null) {
            this.f8734a = new LeanplumActivityHelper(this);
        }
        return this.f8734a;
    }

    protected void onPause() {
        super.onPause();
        m12721a().onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        m12721a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12721a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12721a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12721a().setContentView(i);
    }
}
