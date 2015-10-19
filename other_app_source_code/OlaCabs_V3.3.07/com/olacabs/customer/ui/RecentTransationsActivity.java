package com.olacabs.customer.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import com.sothree.slidinguppanel.p086a.R.R;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class RecentTransationsActivity extends FragmentActivity implements OnPageChangeListener, OnClickListener, OnTabChangeListener, TraceFieldInterface {
    public static final String f9895a;
    private static final JoinPoint f9896i = null;
    private static final JoinPoint f9897j = null;
    private static final JoinPoint f9898k = null;
    private String[] f9899b;
    private TabHost f9900c;
    private View f9901d;
    private View f9902e;
    private View f9903f;
    private ViewPager f9904g;
    private Typeface f9905h;

    /* renamed from: com.olacabs.customer.ui.RecentTransationsActivity.a */
    class C0866a implements TabContentFactory {
        final /* synthetic */ RecentTransationsActivity f9892a;
        private final Context f9893b;

        public C0866a(RecentTransationsActivity recentTransationsActivity, Context context) {
            this.f9892a = recentTransationsActivity;
            this.f9893b = context;
        }

        public View createTabContent(String str) {
            View view = new View(this.f9893b);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }

    /* renamed from: com.olacabs.customer.ui.RecentTransationsActivity.b */
    private class C0867b extends FragmentPagerAdapter {
        final /* synthetic */ RecentTransationsActivity f9894a;

        public C0867b(RecentTransationsActivity recentTransationsActivity, FragmentManager fragmentManager) {
            this.f9894a = recentTransationsActivity;
            super(fragmentManager);
        }

        public int getCount() {
            return this.f9894a.f9899b.length;
        }

        public Fragment getItem(int i) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    return aa.m13985a(0);
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    return aa.m13985a(1);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    return aa.m13985a(2);
                default:
                    return null;
            }
        }
    }

    private static void m13725b() {
        Factory factory = new Factory("RecentTransationsActivity.java", RecentTransationsActivity.class);
        f9896i = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.RecentTransationsActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 42);
        f9897j = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.ui.RecentTransationsActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 76);
        f9898k = factory.m15070a("method-execution", factory.m15071a("4", "onPause", "com.olacabs.customer.ui.RecentTransationsActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 87);
    }

    public RecentTransationsActivity() {
        this.f9899b = new String[]{"ALL", "CREDIT", "DEBIT"};
    }

    static {
        m13725b();
        f9895a = RecentTransationsActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("RecentTransationsActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "RecentTransationsActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "RecentTransationsActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9896i, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(com.olacabs.customer.R.layout.activity_recent_transations);
        OlaApp olaApp = (OlaApp) getApplication();
        Localytics.tagScreen("Transaction list viewed");
        this.f9901d = findViewById(com.olacabs.customer.R.id.tab1Select);
        this.f9902e = findViewById(com.olacabs.customer.R.id.tab2Select);
        this.f9903f = findViewById(com.olacabs.customer.R.id.tab3Select);
        this.f9905h = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        findViewById(com.olacabs.customer.R.id.button_back).setOnClickListener(this);
        this.f9900c = (TabHost) findViewById(com.olacabs.customer.R.id.tabhost);
        this.f9900c.setup();
        this.f9900c.addTab(this.f9900c.newTabSpec(this.f9899b[0]).setIndicator(this.f9899b[0]).setContent(new C0866a(this, this)));
        this.f9900c.addTab(this.f9900c.newTabSpec(this.f9899b[1]).setIndicator(this.f9899b[1]).setContent(new C0866a(this, this)));
        this.f9900c.addTab(this.f9900c.newTabSpec(this.f9899b[2]).setIndicator(this.f9899b[2]).setContent(new C0866a(this, this)));
        this.f9900c.setCurrentTab(0);
        this.f9900c.getTabWidget().setDividerDrawable(new ColorDrawable(getResources().getColor(com.olacabs.customer.R.color.ola_gray_light_searchtext)));
        m13723a();
        this.f9900c.setOnTabChangedListener(this);
        this.f9904g = (ViewPager) findViewById(com.olacabs.customer.R.id.viewPager);
        PagerAdapter c0867b = new C0867b(this, getSupportFragmentManager());
        this.f9904g.setOffscreenPageLimit(3);
        this.f9904g.setAdapter(c0867b);
        this.f9904g.setOnPageChangeListener(this);
        TraceMachine.exitMethod();
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9897j, (Object) this, (Object) this));
        super.onStart();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
    }

    protected void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9898k, (Object) this, (Object) this));
        super.onPause();
    }

    private void m13723a() {
        for (int i = 0; i < this.f9900c.getTabWidget().getChildCount(); i++) {
            this.f9901d.setEnabled(false);
            this.f9902e.setEnabled(false);
            this.f9903f.setEnabled(false);
            TextView textView = (TextView) this.f9900c.getTabWidget().getChildAt(i).findViewById(16908310);
            textView.setTextColor(getResources().getColor(com.olacabs.customer.R.color.ola_black_text));
            textView.setTypeface(this.f9905h);
        }
        switch (this.f9900c.getCurrentTab()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                this.f9901d.setEnabled(true);
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f9902e.setEnabled(true);
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                this.f9903f.setEnabled(true);
            default:
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.f9900c.setCurrentTab(i);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onTabChanged(String str) {
        this.f9904g.setCurrentItem(this.f9900c.getCurrentTab());
        m13723a();
        this.f9904g.getAdapter().notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case com.olacabs.customer.R.id.button_back:
                finish();
            default:
        }
    }
}
