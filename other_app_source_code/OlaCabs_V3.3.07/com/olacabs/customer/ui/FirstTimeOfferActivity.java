package com.olacabs.customer.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
public class FirstTimeOfferActivity extends Activity implements OnClickListener, TraceFieldInterface {
    private static final JoinPoint f9581f = null;
    private static final JoinPoint f9582g = null;
    private Button f9583a;
    private TextView f9584b;
    private ImageView f9585c;
    private OlaApp f9586d;
    private DataManager f9587e;

    static {
        m13508a();
    }

    private static void m13508a() {
        Factory factory = new Factory("FirstTimeOfferActivity.java", FirstTimeOfferActivity.class);
        f9581f = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.FirstTimeOfferActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 30);
        f9582g = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.ui.FirstTimeOfferActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 51);
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("FirstTimeOfferActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "FirstTimeOfferActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "FirstTimeOfferActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9581f, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_offer);
        this.f9583a = (Button) findViewById(R.id.claim);
        this.f9583a.setOnClickListener(this);
        this.f9584b = (TextView) findViewById(R.id.skip_text);
        this.f9585c = (ImageView) findViewById(R.id.offer_cancel);
        this.f9586d = (OlaApp) getApplication();
        this.f9587e = this.f9586d.m12878a();
        ((TextView) findViewById(R.id.offer_header_text)).setText(this.f9587e.m13218d().getFirstTimeOfferHeader());
        ((TextView) findViewById(R.id.offer_desc)).setText(this.f9587e.m13218d().getFirstTimeOfferText());
        this.f9584b.setOnClickListener(this);
        this.f9585c.setOnClickListener(this);
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(Constants.PREF_SHOW_FIRST_TIME_OFFER, false).apply();
        TraceMachine.exitMethod();
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9582g, (Object) this, (Object) this));
        super.onStart();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
    }

    public void onBackPressed() {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.offer_cancel:
                setResult(-1, new Intent());
                finish();
            case R.id.skip_text:
                setResult(-1, new Intent());
                finish();
            case R.id.claim:
                setResult(1, new Intent());
                finish();
            default:
        }
    }
}
