package com.olacabs.customer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.p076d.co;
import com.olacabs.customer.p076d.cu;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* compiled from: RidesCategoryItemAllFragment */
public class ad extends Fragment implements TraceFieldInterface {
    private static final JoinPoint f10325f = null;
    private static final JoinPoint f10326g = null;
    MainActivity f10327a;
    ProgressBar f10328b;
    private ag f10329c;
    private RelativeLayout f10330d;
    private ab f10331e;

    /* renamed from: com.olacabs.customer.ui.ad.1 */
    class RidesCategoryItemAllFragment implements OnClickListener {
        final /* synthetic */ ad f10323a;

        RidesCategoryItemAllFragment(ad adVar) {
            this.f10323a = adVar;
        }

        public void onClick(View view) {
            this.f10323a.f10327a.m13608e().m13650a(1);
        }
    }

    /* renamed from: com.olacabs.customer.ui.ad.2 */
    class RidesCategoryItemAllFragment implements OnItemClickListener {
        final /* synthetic */ ad f10324a;

        RidesCategoryItemAllFragment(ad adVar) {
            this.f10324a = adVar;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Localytics.tagEvent("Rides_Ride Selected");
            Intent intent = new Intent(this.f10324a.f10327a, RideDetailsActivity.class);
            String car_category = ((co) this.f10324a.f10331e.f10261b.getAll().get(i)).getCar_category();
            if (car_category.equalsIgnoreCase("kp") || car_category.equalsIgnoreCase("cool_cab") || car_category.equalsIgnoreCase("auto") || car_category.equalsIgnoreCase("delivery")) {
                intent.putExtra(Constants.ARG_KRN, ((co) this.f10324a.f10331e.f10261b.getAll().get(i)).getKrn());
            } else {
                intent.putExtra(Constants.ARG_BOOKING_ID, ((co) this.f10324a.f10331e.f10261b.getAll().get(i)).getId());
            }
            intent.putExtra(Constants.ARG_CAR_CATEGORY_ID, car_category);
            this.f10324a.startActivity(intent);
        }
    }

    static {
        m14039b();
    }

    private static void m14039b() {
        Factory factory = new Factory("RidesCategoryItemAllFragment.java", ad.class);
        f10325f = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.RidesCategoryItemAllFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 45);
        f10326g = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.RidesCategoryItemAllFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 56);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public static ad m14038a() {
        ad adVar = new ad();
        adVar.setArguments(new Bundle());
        return adVar;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("ad");
        try {
            TraceMachine.enterMethod(this._nr_trace, "ad#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ad#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10325f, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10327a = (MainActivity) getActivity();
        this.f10331e = (ab) getParentFragment();
        Localytics.tagScreen("Rides");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "ad#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ad#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10326g, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_rides_category_item, null);
        ListView listView = (ListView) inflate.findViewById(R.id.list);
        this.f10328b = (ProgressBar) inflate.findViewById(R.id.emptyView);
        listView.setAdapter(this.f10329c);
        this.f10330d = (RelativeLayout) inflate.findViewById(R.id.defaultDisplay);
        inflate.findViewById(R.id.defaultSubText).setOnClickListener(new RidesCategoryItemAllFragment(this));
        if (this.f10331e.f10261b == null && this.f10331e.f10261b == null) {
            this.f10331e.f10261b = new cu();
        }
        this.f10329c = new ag(this.f10327a, this.f10331e.f10261b.getAll());
        listView.setAdapter(this.f10329c);
        listView.setOnItemClickListener(new RidesCategoryItemAllFragment(this));
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.f10331e.f10261b.getAll().size() > 0) {
            this.f10330d.setVisibility(8);
            this.f10328b.setVisibility(8);
        } else if (ab.f10255c) {
            this.f10330d.setVisibility(0);
            this.f10328b.setVisibility(8);
        } else {
            this.f10330d.setVisibility(8);
        }
    }
}
