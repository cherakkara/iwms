package com.olacabs.customer.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.ui.widgets.SlowPagerView;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* compiled from: SlidingFragment */
public class ak extends Fragment implements TraceFieldInterface {
    private static final String f10463a;
    private static int[] f10464b;
    private static final JoinPoint f10465h = null;
    private static final JoinPoint f10466i = null;
    private static final JoinPoint f10467j = null;
    private ImageView f10468c;
    private SlowPagerView f10469d;
    private PagerAdapter f10470e;
    private Handler f10471f;
    private Runnable f10472g;

    /* renamed from: com.olacabs.customer.ui.ak.1 */
    class SlidingFragment implements Runnable {
        final /* synthetic */ ak f10461a;

        SlidingFragment(ak akVar) {
            this.f10461a = akVar;
        }

        public void run() {
            if (this.f10461a.f10469d.getCurrentItem() > 5) {
                this.f10461a.f10469d.setCurrentItem(0, false);
            } else {
                this.f10461a.f10469d.setCurrentItem(this.f10461a.f10469d.getCurrentItem() + 1, true);
            }
            this.f10461a.f10471f.postDelayed(this.f10461a.f10472g, 4000);
        }
    }

    /* renamed from: com.olacabs.customer.ui.ak.2 */
    class SlidingFragment extends SimpleOnPageChangeListener {
        final /* synthetic */ ak f10462a;

        SlidingFragment(ak akVar) {
            this.f10462a = akVar;
        }

        public void onPageSelected(int i) {
            this.f10462a.f10468c.setImageResource(ak.f10464b[i]);
        }
    }

    /* renamed from: com.olacabs.customer.ui.ak.a */
    private static class SlidingFragment extends FragmentStatePagerAdapter {
        public SlidingFragment(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return al.m14129a(i);
        }

        public int getCount() {
            return 7;
        }

        public int getItemPosition(Object obj) {
            return -2;
        }
    }

    private static void m14123d() {
        Factory factory = new Factory("SlidingFragment.java", ak.class);
        f10465h = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.SlidingFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 62);
        f10466i = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.SlidingFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 85);
        f10467j = factory.m15070a("method-execution", factory.m15071a("1", "onPause", "com.olacabs.customer.ui.SlidingFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 91);
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
        m14123d();
        f10463a = ak.class.getSimpleName();
        f10464b = new int[]{R.drawable.loader_1, R.drawable.loader_2, R.drawable.loader_3, R.drawable.loader_4, R.drawable.loader_5, R.drawable.loader_6, R.drawable.loader_7};
    }

    public ak() {
        this.f10471f = new Handler();
        this.f10472g = new SlidingFragment(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "ak#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ak#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10465h, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        OLog.m13311a("onCreateView+", new Object[0]);
        View inflate = layoutInflater.inflate(R.layout.sliding_fragment_layout, viewGroup, false);
        this.f10468c = (ImageView) inflate.findViewById(R.id.page_indicator_image);
        this.f10469d = (SlowPagerView) inflate.findViewById(R.id.pager);
        this.f10470e = new SlidingFragment(getChildFragmentManager());
        this.f10469d.setAdapter(this.f10470e);
        this.f10469d.setScrollDurationFactor(5.0d);
        this.f10469d.setOnPageChangeListener(new SlidingFragment(this));
        this.f10471f.postDelayed(this.f10472g, 2000);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f10466i, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            this.f10469d.setCurrentItem(0, false);
        } finally {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public void onPause() {
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f10467j, (Object) this, (Object) this));
        super.onPause();
        m14124a();
    }

    public void onDetach() {
        super.onDetach();
        m14125b();
    }

    public void m14124a() {
        if (this.f10471f != null) {
            this.f10469d.setScrollDurationFactor(1.0d);
            this.f10471f.removeCallbacks(this.f10472g);
        }
    }

    protected void m14125b() {
        if (!isDetached()) {
            FragmentManager childFragmentManager = getChildFragmentManager();
            if (childFragmentManager.getBackStackEntryCount() > 0) {
                childFragmentManager.popBackStack(childFragmentManager.getBackStackEntryAt(0).getId(), 1);
            }
        }
    }
}
