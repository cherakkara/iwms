package com.olacabs.customer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class RechargePayUActivity extends FragmentActivity implements TraceFieldInterface {
    private static final JoinPoint f9906i = null;
    private LinearLayout f9907a;
    private float f9908b;
    private String f9909c;
    private String f9910d;
    private String f9911e;
    private boolean f9912f;
    private OlaApp f9913g;
    private DataManager f9914h;

    static {
        m13726b();
    }

    private static void m13726b() {
        Factory factory = new Factory("RechargePayUActivity.java", RechargePayUActivity.class);
        f9906i = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.RechargePayUActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 30);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public RechargePayUActivity() {
        this.f9910d = Trace.NULL;
        this.f9911e = Trace.NULL;
        this.f9912f = false;
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("RechargePayUActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "RechargePayUActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "RechargePayUActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9906i, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f9908b = extras.getFloat(Constants.ARG_AMOUNT);
            this.f9909c = extras.getString(Constants.ARG_USER_CREDENTIALS);
            this.f9910d = extras.getString(Constants.EXTRA_OLA_OFFER_TEXT);
            this.f9911e = extras.getString(Constants.EXTRA_OLA_DENOMINATION);
        }
        this.f9913g = (OlaApp) getApplication();
        this.f9914h = this.f9913g.m12878a();
        if (this.f9912f) {
            this.f9914h.m13218d().setOfferFlow(true);
        } else {
            this.f9914h.m13218d().setOfferFlow(false);
        }
        this.f9907a = (LinearLayout) findViewById(R.id.recharge_screen_payu);
        setContentView(R.layout.activity_recharge_pay_u);
        m13727a();
        TraceMachine.exitMethod();
    }

    public void m13727a() {
        getSupportFragmentManager().beginTransaction().replace(R.id.recharge_screen_payu, PayuRechargeFragment.m14711a(this.f9908b, this.f9909c, this.f9910d, this.f9911e)).commitAllowingStateLoss();
    }

    public void onBackPressed() {
        Localytics.tagEvent("Payment Abandoned");
        setResult(Constants.MILLIS_IN_A_SECOND);
        finish();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 100) {
            if (i2 == -1) {
                if (!(intent == null || intent.getExtras() == null)) {
                    setResult(i2, intent);
                }
                finish();
            }
            if (i2 == 0) {
                setResult(i2, intent);
                finish();
            }
        }
    }
}
