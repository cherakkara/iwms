package com.leanplum.activities;

import android.app.LauncherActivity;
import android.content.res.Resources;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class LeanplumLauncherActivity extends LauncherActivity implements TraceFieldInterface {
    private LeanplumActivityHelper f8733a;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    private LeanplumActivityHelper m12720a() {
        if (this.f8733a == null) {
            this.f8733a = new LeanplumActivityHelper(this);
        }
        return this.f8733a;
    }

    protected void onPause() {
        super.onPause();
        m12720a().onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        m12720a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12720a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12720a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12720a().setContentView(i);
    }
}
