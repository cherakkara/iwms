package com.leanplum.activities;

import android.app.TabActivity;
import android.content.res.Resources;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class LeanplumTabActivity extends TabActivity implements TraceFieldInterface {
    private LeanplumActivityHelper f8741a;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    private LeanplumActivityHelper m12728a() {
        if (this.f8741a == null) {
            this.f8741a = new LeanplumActivityHelper(this);
        }
        return this.f8741a;
    }

    protected void onPause() {
        super.onPause();
        m12728a().onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        m12728a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12728a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12728a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12728a().setContentView(i);
    }
}
