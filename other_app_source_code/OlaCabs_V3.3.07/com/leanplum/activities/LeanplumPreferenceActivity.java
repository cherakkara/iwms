package com.leanplum.activities;

import android.content.res.Resources;
import android.preference.PreferenceActivity;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class LeanplumPreferenceActivity extends PreferenceActivity implements TraceFieldInterface {
    private LeanplumActivityHelper f8736a;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    private LeanplumActivityHelper m12723a() {
        if (this.f8736a == null) {
            this.f8736a = new LeanplumActivityHelper(this);
        }
        return this.f8736a;
    }

    protected void onPause() {
        super.onPause();
        m12723a().onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        m12723a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12723a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12723a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12723a().setContentView(i);
    }
}
