package com.leanplum.activities;

import android.app.ActivityGroup;
import android.content.res.Resources;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class LeanplumActivityGroup extends ActivityGroup implements TraceFieldInterface {
    private LeanplumActivityHelper f8729a;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    private LeanplumActivityHelper m12716a() {
        if (this.f8729a == null) {
            this.f8729a = new LeanplumActivityHelper(this);
        }
        return this.f8729a;
    }

    protected void onPause() {
        super.onPause();
        m12716a().onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        m12716a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12716a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12716a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12716a().setContentView(i);
    }
}
