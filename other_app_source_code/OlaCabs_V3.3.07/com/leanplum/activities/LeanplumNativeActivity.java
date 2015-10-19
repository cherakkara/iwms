package com.leanplum.activities;

import android.app.NativeActivity;
import android.content.res.Resources;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class LeanplumNativeActivity extends NativeActivity implements TraceFieldInterface {
    private LeanplumActivityHelper f8735a;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    private LeanplumActivityHelper m12722a() {
        if (this.f8735a == null) {
            this.f8735a = new LeanplumActivityHelper(this);
        }
        return this.f8735a;
    }

    protected void onPause() {
        super.onPause();
        m12722a().onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        m12722a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12722a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12722a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12722a().setContentView(i);
    }
}
