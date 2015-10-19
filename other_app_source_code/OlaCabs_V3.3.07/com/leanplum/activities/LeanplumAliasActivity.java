package com.leanplum.activities;

import android.app.AliasActivity;
import android.content.res.Resources;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class LeanplumAliasActivity extends AliasActivity implements TraceFieldInterface {
    private LeanplumActivityHelper f8730a;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    private LeanplumActivityHelper m12717a() {
        if (this.f8730a == null) {
            this.f8730a = new LeanplumActivityHelper(this);
        }
        return this.f8730a;
    }

    protected void onPause() {
        super.onPause();
        m12717a().onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        m12717a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12717a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12717a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12717a().setContentView(i);
    }
}
