package com.olacabs.customer.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.google.android.m4b.maps.model.LatLng;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.ui.ai.SearchFragment;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class RideEstimateActivity extends FragmentActivity implements SearchFragment, TraceFieldInterface {
    public static final String f10029a;
    private static final JoinPoint f10030b = null;
    private static final JoinPoint f10031c = null;

    private static void m13801b() {
        Factory factory = new Factory("RideEstimateActivity.java", RideEstimateActivity.class);
        f10030b = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.RideEstimateActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 21);
        f10031c = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.ui.RideEstimateActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 31);
    }

    static {
        m13801b();
        f10029a = RideEstimateActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("RideEstimateActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "RideEstimateActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "RideEstimateActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f10030b, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_ride_estimate);
        m13802b(getIntent().getDoubleExtra("lattitude", 0.0d), getIntent().getDoubleExtra("longitude", 0.0d), 1);
        TraceMachine.exitMethod();
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f10031c, (Object) this, (Object) this));
        super.onStart();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
    }

    public void m13805a(LatLng latLng, String str) {
        if (getSupportFragmentManager().findFragmentById(R.id.container) != null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            FragmentTransaction fragmentTransaction = beginTransaction;
            int i = R.id.container;
            fragmentTransaction.replace(i, ac.m14018a(getIntent().getDoubleExtra("lattitude", 0.0d), getIntent().getDoubleExtra("longitude", 0.0d), latLng.f7554a, latLng.f7555b, str, getIntent().getStringExtra("categoryId"), getIntent().getStringExtra("pickup_mode"), getIntent().getLongExtra("pickUpTime", 0), getIntent().getStringExtra("coupon"))).commit();
        }
    }

    public void m13803a() {
    }

    private void m13802b(double d, double d2, int i) {
        getSupportFragmentManager().beginTransaction().add((int) R.id.container, ai.m14062a(d, d2, i)).commit();
    }

    public void m13804a(double d, double d2, int i) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, ai.m14062a(d, d2, i)).commit();
    }
}
