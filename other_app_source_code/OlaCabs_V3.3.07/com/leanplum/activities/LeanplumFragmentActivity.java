package com.leanplum.activities;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public abstract class LeanplumFragmentActivity extends FragmentActivity implements TraceFieldInterface {
    private LeanplumActivityHelper f8732a;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    private LeanplumActivityHelper m12719a() {
        if (this.f8732a == null) {
            this.f8732a = new LeanplumActivityHelper(this);
        }
        return this.f8732a;
    }

    protected void onPause() {
        super.onPause();
        m12719a().onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        m12719a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12719a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12719a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12719a().setContentView(i);
    }
}
