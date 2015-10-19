package com.facebook;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.facebook.R.R;
import com.facebook.login.LoginFragment;
import com.facebook.p022b.FacebookDialogFragment;
import com.facebook.p022b.NativeProtocol;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class FacebookActivity extends FragmentActivity implements TraceFieldInterface {
    public static String f608a;
    private static String f609b;
    private Fragment f610c;

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    static {
        f608a = "PassThrough";
        f609b = "SingleFragment";
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("FacebookActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "FacebookActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "FacebookActivity#onCreate", null);
                break;
            }
        }
        super.onCreate(bundle);
        setContentView(R.com_facebook_activity_layout);
        Intent intent = getIntent();
        if (f608a.equals(intent.getAction())) {
            m706a();
            TraceMachine.exitMethod();
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(f609b);
        if (findFragmentByTag == null) {
            if ("FacebookDialogFragment".equals(intent.getAction())) {
                findFragmentByTag = new FacebookDialogFragment();
                findFragmentByTag.setRetainInstance(true);
                findFragmentByTag.show(supportFragmentManager, f609b);
            } else {
                findFragmentByTag = new LoginFragment();
                findFragmentByTag.setRetainInstance(true);
                supportFragmentManager.beginTransaction().add(R.com_facebook_fragment_container, findFragmentByTag, f609b).commit();
            }
        }
        this.f610c = findFragmentByTag;
        TraceMachine.exitMethod();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f610c != null) {
            this.f610c.onConfigurationChanged(configuration);
        }
    }

    private void m706a() {
        Intent intent = getIntent();
        setResult(0, NativeProtocol.m1045a(intent, null, NativeProtocol.m1047a(NativeProtocol.m1057c(intent))));
        finish();
    }
}
