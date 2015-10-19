package com.olacabs.customer.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.google.android.m4b.maps.model.LatLng;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.ui.ai.SearchFragment;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class FavouritesActivity extends FragmentActivity implements SearchFragment, TraceFieldInterface {
    public static final String f9573a;
    public static long f9574d;
    public static String f9575e;
    public static String f9576f;
    private static final JoinPoint f9577h = null;
    FragmentManager f9578b;
    FragmentManager f9579c;
    private DataManager f9580g;

    private static void m13502b() {
        Factory factory = new Factory("FavouritesActivity.java", FavouritesActivity.class);
        f9577h = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.FavouritesActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 30);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    static {
        m13502b();
        f9573a = FavouritesActivity.class.getSimpleName();
        f9574d = 0;
        f9575e = Trace.NULL;
        f9576f = Trace.NULL;
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("FavouritesActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "FavouritesActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "FavouritesActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9577h, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_ride_estimate);
        this.f9580g = ((OlaApp) getApplication()).m12878a();
        this.f9578b = getSupportFragmentManager();
        this.f9579c = getSupportFragmentManager();
        m13507a(getIntent().getStringExtra(Constants.BUNDLE_ADDRESS), getIntent().getDoubleExtra("lattitude", 0.0d), getIntent().getDoubleExtra("longitude", 0.0d));
        TraceMachine.exitMethod();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        this.f9580g.m13169a(f9573a);
    }

    public void m13506a(LatLng latLng, String str) {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container);
        Localytics.tagEvent("Favorite Searched");
        if (findFragmentById != null) {
            getSupportFragmentManager().popBackStack();
            this.f9579c.beginTransaction().add((int) R.id.container, NewFavouritesFragment.m14588a(f9574d, str, f9575e, f9576f, latLng.f7554a, latLng.f7555b)).addToBackStack(null).commit();
        }
    }

    public void m13503a() {
    }

    public void m13507a(String str, double d, double d2) {
        this.f9578b.beginTransaction().add((int) R.id.container, FavouritesFragment.m14430a(str, d, d2)).commit();
    }

    public void m13504a(double d, double d2) {
        getSupportFragmentManager().beginTransaction().add((int) R.id.container, ai.m14062a(d, d2, 2)).addToBackStack(null).commit();
    }

    public void m13505a(long j, String str, String str2, String str3, double d, double d2) {
        f9574d = j;
        f9575e = str2;
        f9576f = str3;
        this.f9579c.beginTransaction().add((int) R.id.container, NewFavouritesFragment.m14588a(j, str, str2, str3, d, d2)).addToBackStack("null").commit();
    }
}
