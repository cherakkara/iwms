package com.olacabs.customer.ui;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class SOSCarouselActivity extends FragmentActivity implements OnClickListener, TraceFieldInterface {
    private static int[] f10096c;
    private static final JoinPoint f10097f = null;
    private ImageView f10098a;
    private TextView f10099b;
    private ViewPager f10100d;
    private PagerAdapter f10101e;

    /* renamed from: com.olacabs.customer.ui.SOSCarouselActivity.1 */
    class C08911 extends SimpleOnPageChangeListener {
        final /* synthetic */ SOSCarouselActivity f10094a;

        C08911(SOSCarouselActivity sOSCarouselActivity) {
            this.f10094a = sOSCarouselActivity;
        }

        public void onPageSelected(int i) {
            this.f10094a.f10098a.setImageResource(SOSCarouselActivity.f10096c[i]);
            if (i == 3) {
                this.f10094a.f10099b.setVisibility(0);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.SOSCarouselActivity.a */
    private class C0892a extends FragmentStatePagerAdapter {
        final /* synthetic */ SOSCarouselActivity f10095a;

        public C0892a(SOSCarouselActivity sOSCarouselActivity, FragmentManager fragmentManager) {
            this.f10095a = sOSCarouselActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return ah.m14053a(i);
        }

        public int getCount() {
            return 4;
        }
    }

    private static void m13851b() {
        Factory factory = new Factory("SOSCarouselActivity.java", SOSCarouselActivity.class);
        f10097f = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.SOSCarouselActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 49);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    static {
        m13851b();
        f10096c = new int[]{R.drawable.saftey_asset_dot_0, R.drawable.saftey_asset_dot_1, R.drawable.saftey_asset_dot_2, R.drawable.saftey_asset_dot_3};
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("SOSCarouselActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "SOSCarouselActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "SOSCarouselActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f10097f, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.sos_activity_view);
        this.f10100d = (ViewPager) findViewById(R.id.pager);
        this.f10098a = (ImageView) findViewById(R.id.page_indicator_image);
        this.f10099b = (TextView) findViewById(R.id.done_text);
        this.f10099b.setOnClickListener(this);
        this.f10101e = new C0892a(this, getSupportFragmentManager());
        this.f10100d.setAdapter(this.f10101e);
        this.f10100d.setOnPageChangeListener(new C08911(this));
        TraceMachine.exitMethod();
    }

    public void onBackPressed() {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.done_text:
                Editor edit = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                edit.putBoolean(Constants.PREF_SAFETY_CAROUSEL_SHOWN_ALREADY, true);
                edit.apply();
                setResult(-1, new Intent());
                finish();
            default:
        }
    }
}
