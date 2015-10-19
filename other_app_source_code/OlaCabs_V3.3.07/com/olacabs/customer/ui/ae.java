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
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.co;
import com.olacabs.customer.p076d.cu;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* compiled from: RidesCategoryItemCompletedFragment */
public class ae extends Fragment implements TraceFieldInterface {
    private static final JoinPoint f10334g = null;
    private static final JoinPoint f10335h = null;
    private MainActivity f10336a;
    private ProgressBar f10337b;
    private ag f10338c;
    private RelativeLayout f10339d;
    private DataManager f10340e;
    private ab f10341f;

    /* renamed from: com.olacabs.customer.ui.ae.1 */
    class RidesCategoryItemCompletedFragment implements OnClickListener {
        final /* synthetic */ ae f10332a;

        RidesCategoryItemCompletedFragment(ae aeVar) {
            this.f10332a = aeVar;
        }

        public void onClick(View view) {
            this.f10332a.f10336a.m13608e().m13650a(1);
        }
    }

    /* renamed from: com.olacabs.customer.ui.ae.2 */
    class RidesCategoryItemCompletedFragment implements OnItemClickListener {
        final /* synthetic */ ae f10333a;

        RidesCategoryItemCompletedFragment(ae aeVar) {
            this.f10333a = aeVar;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Localytics.tagEvent("Rides_Ride Selected");
            Intent intent = new Intent(this.f10333a.f10336a, RideDetailsActivity.class);
            String car_category = ((co) this.f10333a.f10341f.f10261b.getCompleted().get(i)).getCar_category();
            if (car_category.equalsIgnoreCase("kp") || car_category.equalsIgnoreCase("cool_cab") || car_category.equalsIgnoreCase("auto") || car_category.equalsIgnoreCase("delivery")) {
                intent.putExtra(Constants.ARG_KRN, ((co) this.f10333a.f10341f.f10261b.getCompleted().get(i)).getKrn());
            } else {
                intent.putExtra(Constants.ARG_BOOKING_ID, ((co) this.f10333a.f10341f.f10261b.getCompleted().get(i)).getId());
            }
            intent.putExtra(Constants.ARG_CAR_CATEGORY_ID, car_category);
            this.f10333a.startActivity(intent);
        }
    }

    static {
        m14043b();
    }

    private static void m14043b() {
        Factory factory = new Factory("RidesCategoryItemCompletedFragment.java", ae.class);
        f10334g = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.RidesCategoryItemCompletedFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 41);
        f10335h = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.RidesCategoryItemCompletedFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 54);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public static ae m14041a() {
        return new ae();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("ae");
        try {
            TraceMachine.enterMethod(this._nr_trace, "ae#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ae#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10334g, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10336a = (MainActivity) getActivity();
        this.f10340e = ((OlaApp) this.f10336a.getApplication()).m12878a();
        Localytics.tagScreen("Rides");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "ae#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ae#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10335h, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_rides_category_item, null);
        this.f10341f = (ab) getParentFragment();
        ListView listView = (ListView) inflate.findViewById(R.id.list);
        this.f10337b = (ProgressBar) inflate.findViewById(R.id.emptyView);
        listView.setAdapter(this.f10338c);
        this.f10339d = (RelativeLayout) inflate.findViewById(R.id.defaultDisplay);
        this.f10339d = (RelativeLayout) inflate.findViewById(R.id.defaultDisplay);
        inflate.findViewById(R.id.defaultSubText).setOnClickListener(new RidesCategoryItemCompletedFragment(this));
        if (this.f10341f.f10261b == null) {
            this.f10341f.f10261b = new cu();
        }
        this.f10338c = new ag(this.f10336a, this.f10341f.f10261b.getCompleted());
        listView.setAdapter(this.f10338c);
        listView.setOnItemClickListener(new RidesCategoryItemCompletedFragment(this));
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.f10341f.f10261b.getCompleted().size() > 0) {
            this.f10339d.setVisibility(8);
            this.f10337b.setVisibility(8);
        } else if (ab.f10255c) {
            this.f10339d.setVisibility(0);
            this.f10337b.setVisibility(8);
        } else {
            this.f10339d.setVisibility(8);
        }
    }
}
