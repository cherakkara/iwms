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
import android.widget.TextView;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.co;
import com.olacabs.customer.p076d.cu;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* compiled from: RidesCategoryItemUpcomingFragment */
public class af extends Fragment implements TraceFieldInterface {
    private static final JoinPoint f10344h = null;
    private static final JoinPoint f10345i = null;
    private ag f10346a;
    private RelativeLayout f10347b;
    private MainActivity f10348c;
    private ProgressBar f10349d;
    private TextView f10350e;
    private DataManager f10351f;
    private ab f10352g;

    /* renamed from: com.olacabs.customer.ui.af.1 */
    class RidesCategoryItemUpcomingFragment implements OnClickListener {
        final /* synthetic */ af f10342a;

        RidesCategoryItemUpcomingFragment(af afVar) {
            this.f10342a = afVar;
        }

        public void onClick(View view) {
            this.f10342a.f10348c.m13608e().m13650a(1);
        }
    }

    /* renamed from: com.olacabs.customer.ui.af.2 */
    class RidesCategoryItemUpcomingFragment implements OnItemClickListener {
        final /* synthetic */ af f10343a;

        RidesCategoryItemUpcomingFragment(af afVar) {
            this.f10343a = afVar;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Localytics.tagEvent("Rides_Ride Selected");
            Intent intent = new Intent(this.f10343a.f10348c, RideDetailsActivity.class);
            String car_category = ((co) this.f10343a.f10352g.f10261b.getUpcoming().get(i)).getCar_category();
            if (car_category.equalsIgnoreCase("kp") || car_category.equalsIgnoreCase("cool_cab") || car_category.equalsIgnoreCase("auto") || car_category.equalsIgnoreCase("delivery")) {
                intent.putExtra(Constants.ARG_KRN, ((co) this.f10343a.f10352g.f10261b.getUpcoming().get(i)).getKrn());
            } else {
                intent.putExtra(Constants.ARG_BOOKING_ID, ((co) this.f10343a.f10352g.f10261b.getUpcoming().get(i)).getId());
            }
            intent.putExtra(Constants.ARG_CAR_CATEGORY_ID, car_category);
            this.f10343a.startActivity(intent);
        }
    }

    static {
        m14047b();
    }

    private static void m14047b() {
        Factory factory = new Factory("RidesCategoryItemUpcomingFragment.java", af.class);
        f10344h = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.RidesCategoryItemUpcomingFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 47);
        f10345i = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.RidesCategoryItemUpcomingFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 60);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public static af m14045a() {
        af afVar = new af();
        afVar.setArguments(new Bundle());
        return afVar;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("af");
        try {
            TraceMachine.enterMethod(this._nr_trace, "af#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "af#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10344h, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10348c = (MainActivity) getActivity();
        this.f10351f = ((OlaApp) this.f10348c.getApplication()).m12878a();
        Localytics.tagScreen("Rides");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "af#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "af#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10345i, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_rides_category_item, null);
        this.f10352g = (ab) getParentFragment();
        this.f10350e = (TextView) inflate.findViewById(R.id.rideTextMessage);
        this.f10350e.setText("There are no upcoming rides for you.");
        ListView listView = (ListView) inflate.findViewById(R.id.list);
        this.f10349d = (ProgressBar) inflate.findViewById(R.id.emptyView);
        listView.setAdapter(this.f10346a);
        this.f10347b = (RelativeLayout) inflate.findViewById(R.id.defaultDisplay);
        this.f10347b = (RelativeLayout) inflate.findViewById(R.id.defaultDisplay);
        inflate.findViewById(R.id.defaultSubText).setOnClickListener(new RidesCategoryItemUpcomingFragment(this));
        if (this.f10352g.f10261b == null) {
            this.f10352g.f10261b = new cu();
        }
        this.f10346a = new ag(this.f10348c, this.f10352g.f10261b.getUpcoming());
        listView.setAdapter(this.f10346a);
        listView.setOnItemClickListener(new RidesCategoryItemUpcomingFragment(this));
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.f10352g.f10261b.getUpcoming().size() > 0) {
            this.f10347b.setVisibility(8);
            this.f10349d.setVisibility(8);
        } else if (ab.f10255c) {
            this.f10347b.setVisibility(0);
            this.f10349d.setVisibility(8);
        } else {
            this.f10347b.setVisibility(8);
        }
    }
}
