package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.cu;
import com.olacabs.customer.p076d.cv;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import com.sothree.slidinguppanel.p086a.R.R;
import java.lang.ref.WeakReference;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
/* compiled from: RideCategoryFragment */
public class ab extends Fragment implements OnPageChangeListener, OnTabChangeListener, TraceFieldInterface {
    public static final String f10254a;
    public static boolean f10255c;
    private static final String[] f10256d;
    private static final JoinPoint f10257v = null;
    private static final JoinPoint f10258w = null;
    private static final JoinPoint f10259x = null;
    private static final JoinPoint f10260y = null;
    public cu f10261b;
    private TabHost f10262e;
    private RideCategoryFragment f10263f;
    private Typeface f10264g;
    private ImageView f10265h;
    private TextView f10266i;
    private TextView f10267j;
    private TextView f10268k;
    private View f10269l;
    private ProgressBar f10270m;
    private LinearLayout f10271n;
    private Activity f10272o;
    private boolean f10273p;
    private DataManager f10274q;
    private ViewPager f10275r;
    private cv f10276s;
    private ViewStub f10277t;
    private aj f10278u;

    /* renamed from: com.olacabs.customer.ui.ab.1 */
    class RideCategoryFragment implements OnClickListener {
        final /* synthetic */ ab f10247a;

        RideCategoryFragment(ab abVar) {
            this.f10247a = abVar;
        }

        public void onClick(View view) {
            ((MainActivity) this.f10247a.f10272o).m13607d();
        }
    }

    /* renamed from: com.olacabs.customer.ui.ab.2 */
    class RideCategoryFragment implements aj {
        final /* synthetic */ ab f10248a;

        RideCategoryFragment(ab abVar) {
            this.f10248a = abVar;
        }

        public void onFailure(Throwable th) {
            OLog.m13313b("RidesRequest failed", th);
            ab.f10255c = true;
            this.f10248a.f10270m.setVisibility(8);
            this.f10248a.f10271n.setVisibility(0);
        }

        public void onSuccess(Object obj) {
            ab.f10255c = true;
            this.f10248a.f10270m.setVisibility(8);
            this.f10248a.f10271n.setVisibility(0);
            if (this.f10248a.f10272o != null && !this.f10248a.f10273p) {
                this.f10248a.f10276s = (cv) obj;
                if (this.f10248a.f10276s.getStatus().equalsIgnoreCase("SUCCESS")) {
                    this.f10248a.f10261b = this.f10248a.f10276s.getRidesCategoryList();
                    this.f10248a.f10275r.setAdapter(this.f10248a.f10263f);
                    this.f10248a.onTabChanged("All rides");
                } else if (this.f10248a.f10276s.getHeader() != null && this.f10248a.f10276s.getText() != null) {
                    this.f10248a.m13998a(this.f10248a.f10276s.getHeader(), this.f10248a.f10276s.getText());
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.ab.3 */
    class RideCategoryFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10249a;
        final /* synthetic */ ab f10250b;

        RideCategoryFragment(ab abVar, AlertDialog alertDialog) {
            this.f10250b = abVar;
            this.f10249a = alertDialog;
        }

        public void onClick(View view) {
            this.f10249a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.ab.a */
    public class RideCategoryFragment extends FragmentPagerAdapter {
        final /* synthetic */ ab f10251a;

        public RideCategoryFragment(ab abVar, FragmentManager fragmentManager) {
            this.f10251a = abVar;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    return ad.m14038a();
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    return af.m14045a();
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    return ae.m14041a();
                default:
                    return ad.m14038a();
            }
        }

        public int getCount() {
            return ab.f10256d.length;
        }

        public CharSequence getPageTitle(int i) {
            return ab.f10256d[i];
        }
    }

    /* renamed from: com.olacabs.customer.ui.ab.b */
    private class RideCategoryFragment implements TabContentFactory {
        final /* synthetic */ ab f10252a;
        private final Context f10253b;

        public RideCategoryFragment(ab abVar, Context context) {
            this.f10252a = abVar;
            this.f10253b = context;
        }

        public View createTabContent(String str) {
            View view = new View(this.f10253b);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }

    private static void m14008h() {
        Factory factory = new Factory("RideCategoryFragment.java", ab.class);
        f10257v = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.RideCategoryFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 80);
        f10258w = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.RideCategoryFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 88);
        f10259x = factory.m15070a("method-execution", factory.m15071a("1", "onStart", "com.olacabs.customer.ui.RideCategoryFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 154);
        f10260y = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.RideCategoryFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 161);
    }

    static {
        m14008h();
        f10256d = new String[]{"All rides", "Upcoming", "Completed"};
        f10254a = ab.class.getSimpleName();
        f10255c = false;
    }

    public static ab m13996a() {
        ab abVar = new ab();
        abVar.setArguments(new Bundle());
        return abVar;
    }

    public ab() {
        this.f10273p = false;
        this.f10278u = new RideCategoryFragment(this);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("ab");
        try {
            TraceMachine.enterMethod(this._nr_trace, "ab#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ab#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10257v, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10274q = ((OlaApp) this.f10272o.getApplication()).m12878a();
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "ab#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ab#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10258w, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        this.f10269l = layoutInflater.inflate(com.olacabs.customer.R.layout.fragment_ride_category, viewGroup, false);
        m14005f();
        View view = this.f10269l;
        TraceMachine.exitMethod();
        return view;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f10272o = activity;
    }

    private void m14005f() {
        this.f10266i = (TextView) this.f10269l.findViewById(com.olacabs.customer.R.id.tab1Select);
        this.f10267j = (TextView) this.f10269l.findViewById(com.olacabs.customer.R.id.tab2Select);
        this.f10268k = (TextView) this.f10269l.findViewById(com.olacabs.customer.R.id.tab3Select);
        this.f10270m = (ProgressBar) this.f10269l.findViewById(com.olacabs.customer.R.id.emptyView);
        this.f10271n = (LinearLayout) this.f10269l.findViewById(com.olacabs.customer.R.id.tabLayout);
        this.f10277t = (ViewStub) this.f10269l.findViewById(com.olacabs.customer.R.id.stub_sad_error);
        this.f10264g = Typeface.createFromAsset(this.f10272o.getAssets(), "OpenSans-Regular.ttf");
        f10255c = false;
        this.f10265h = (ImageView) this.f10269l.findViewById(com.olacabs.customer.R.id.menuDrawerImageView);
        this.f10265h.setOnClickListener(new RideCategoryFragment(this));
        this.f10262e = (TabHost) this.f10269l.findViewById(com.olacabs.customer.R.id.tabhost);
        this.f10262e.setup();
        this.f10262e.addTab(this.f10262e.newTabSpec("All rides").setIndicator("All rides").setContent(new RideCategoryFragment(this, this.f10272o)));
        this.f10262e.addTab(this.f10262e.newTabSpec("Upcoming").setIndicator("Upcoming").setContent(new RideCategoryFragment(this, this.f10272o)));
        this.f10262e.addTab(this.f10262e.newTabSpec("Completed").setIndicator("Completed").setContent(new RideCategoryFragment(this, this.f10272o)));
        this.f10262e.setCurrentTab(0);
        this.f10262e.getTabWidget().setDividerDrawable(new ColorDrawable(getResources().getColor(com.olacabs.customer.R.color.ola_gray_light_searchtext)));
        m14007g();
        this.f10262e.setOnTabChangedListener(this);
        this.f10275r = (ViewPager) this.f10269l.findViewById(com.olacabs.customer.R.id.pager);
        this.f10263f = new RideCategoryFragment(this, getChildFragmentManager());
        this.f10275r.setOnPageChangeListener(this);
        this.f10275r.setAdapter(this.f10263f);
        if (Utils.m14909a(getActivity().getApplicationContext())) {
            m14009b();
        } else {
            m14010c();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f10273p = true;
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f10259x, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f10260y, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            if (Utils.m14909a(getActivity().getApplicationContext())) {
                m14011d();
            } else {
                m14010c();
            }
            ActivityTraceAspect.m12823a().m12827c(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public void m14009b() {
        this.f10274q.m13239h(new WeakReference(this.f10278u), f10254a);
    }

    public void onEvent(ah ahVar) {
        if (ahVar.isConnected()) {
            m14011d();
        } else {
            m14010c();
        }
    }

    public void m14010c() {
        this.f10271n.setVisibility(8);
        this.f10277t.setVisibility(0);
        this.f10270m.setVisibility(8);
    }

    public void m14011d() {
        this.f10270m.setVisibility(0);
        m14009b();
        this.f10277t.setVisibility(8);
        this.f10271n.setVisibility(0);
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.f10262e.setCurrentTab(i);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onTabChanged(String str) {
        this.f10275r.setCurrentItem(this.f10262e.getCurrentTab());
        m14007g();
    }

    private void m14007g() {
        for (int i = 0; i < this.f10262e.getTabWidget().getChildCount(); i++) {
            this.f10266i.setEnabled(false);
            this.f10267j.setEnabled(false);
            this.f10268k.setEnabled(false);
            TextView textView = (TextView) this.f10262e.getTabWidget().getChildAt(i).findViewById(16908310);
            textView.setTextColor(getResources().getColor(com.olacabs.customer.R.color.ola_black_text));
            textView.setTypeface(this.f10264g);
        }
        switch (this.f10262e.getCurrentTab()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                this.f10266i.setEnabled(true);
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f10267j.setEnabled(true);
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                this.f10268k.setEnabled(true);
            default:
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == com.olacabs.customer.R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void m13998a(String str, String str2) {
        View inflate = ((LayoutInflater) this.f10272o.getSystemService("layout_inflater")).inflate(com.olacabs.customer.R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10272o).setView(inflate).create();
        ((TextView) inflate.findViewById(com.olacabs.customer.R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(com.olacabs.customer.R.id.item_message)).setText(str2);
        inflate.findViewById(com.olacabs.customer.R.id.button_ok).setOnClickListener(new RideCategoryFragment(this, create));
        create.show();
    }
}
