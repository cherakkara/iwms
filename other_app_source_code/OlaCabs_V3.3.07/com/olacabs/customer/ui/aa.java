package com.olacabs.customer.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p075a.AnalyticsManager;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.dj;
import com.olacabs.customer.p076d.dj.TransactionResult;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* compiled from: RecentTransactionItemFragment */
public class aa extends Fragment implements TraceFieldInterface {
    public static final String f10232a;
    private static final JoinPoint f10233n = null;
    private static final JoinPoint f10234o = null;
    private final int f10235b;
    private boolean f10236c;
    private aj f10237d;
    private OnScrollListener f10238e;
    private Activity f10239f;
    private int f10240g;
    private RecentTransactionItemFragment f10241h;
    private ProgressBar f10242i;
    private ListView f10243j;
    private TextView f10244k;
    private DataManager f10245l;
    private AnalyticsManager f10246m;

    /* renamed from: com.olacabs.customer.ui.aa.1 */
    class RecentTransactionItemFragment implements aj {
        final /* synthetic */ aa f10225a;

        RecentTransactionItemFragment(aa aaVar) {
            this.f10225a = aaVar;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to ", th);
            Sherlock.m13335a("Ins ola money tx shown", (VolleyError) th);
            th.printStackTrace();
            this.f10225a.f10236c = false;
        }

        public void onSuccess(Object obj) {
            dj djVar = (dj) obj;
            if (djVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13311a("Get transactions data succeeded", new Object[0]);
                Sherlock.m13345b("Ins ola money tx shown");
                this.f10225a.f10242i.setVisibility(8);
                ArrayList results = djVar.getResults();
                this.f10225a.f10241h.m13983a(results);
                if (results.size() > 0 || this.f10225a.f10243j.getChildCount() > 0) {
                    this.f10225a.f10244k.setVisibility(8);
                    this.f10225a.f10243j.setVisibility(0);
                } else {
                    this.f10225a.f10244k.setVisibility(0);
                    this.f10225a.f10243j.setVisibility(8);
                }
                if (results.size() < 10) {
                    this.f10225a.f10243j.setOnScrollListener(null);
                }
                this.f10225a.f10236c = false;
            } else if (djVar.getStatus().equalsIgnoreCase("FAILURE")) {
                OLog.m13318e("Get account balance failed - " + djVar.getStatus(), new Object[0]);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.aa.2 */
    class RecentTransactionItemFragment implements OnScrollListener {
        final /* synthetic */ aa f10226a;

        RecentTransactionItemFragment(aa aaVar) {
            this.f10226a = aaVar;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (!this.f10226a.f10236c && (i + i2) + 1 >= i3) {
                this.f10226a.m13986a();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.aa.a */
    private class RecentTransactionItemFragment extends BaseAdapter {
        final /* synthetic */ aa f10227a;
        private LayoutInflater f10228b;
        private ArrayList<TransactionResult> f10229c;
        private String f10230d;
        private SimpleDateFormat f10231e;

        public RecentTransactionItemFragment(aa aaVar, Context context) {
            this.f10227a = aaVar;
            this.f10228b = LayoutInflater.from(context);
            this.f10229c = new ArrayList();
            this.f10230d = context.getResources().getString(R.string.rs_symbol);
            this.f10231e = new SimpleDateFormat("EEE, dd MMM,yyyy");
        }

        public int getCount() {
            return this.f10229c.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.f10228b.inflate(R.layout.item_olamoney_transaction, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(R.id.item_transaction_type_text);
            ImageView imageView = (ImageView) view.findViewById(R.id.item_transaction_type_icon);
            TextView textView2 = (TextView) view.findViewById(R.id.item_extra_text);
            TextView textView3 = (TextView) view.findViewById(R.id.item_balance);
            TextView textView4 = (TextView) view.findViewById(R.id.item_date);
            TransactionResult transactionResult = (TransactionResult) this.f10229c.get(i);
            ((TextView) view.findViewById(R.id.item_amount)).setText(this.f10230d + transactionResult.getAmount());
            textView2.setText(transactionResult.getTransactionNote());
            textView3.setText("Balance:" + this.f10230d + transactionResult.getBalance());
            textView4.setText(this.f10231e.format(transactionResult.getDate()));
            if (transactionResult.isCredited()) {
                textView.setText("CR");
                textView.setTextColor(this.f10227a.getResources().getColor(R.color.ola_green_dark));
                imageView.setImageResource(R.drawable.ic_ola_money_debit);
            } else {
                textView.setText("DR");
                textView.setTextColor(this.f10227a.getResources().getColor(R.color.ola_red_dark));
                imageView.setImageResource(R.drawable.ic_ola_money_credit);
            }
            return view;
        }

        public void m13983a(ArrayList<TransactionResult> arrayList) {
            this.f10229c.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    private static void m13989b() {
        Factory factory = new Factory("RecentTransactionItemFragment.java", aa.class);
        f10233n = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.RecentTransactionItemFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 116);
        f10234o = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.RecentTransactionItemFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 129);
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
        m13989b();
        f10232a = aa.class.getSimpleName();
    }

    public aa() {
        this.f10235b = 10;
        this.f10236c = false;
        this.f10237d = new RecentTransactionItemFragment(this);
        this.f10238e = new RecentTransactionItemFragment(this);
    }

    public static aa m13985a(int i) {
        aa aaVar = new aa();
        Bundle bundle = new Bundle();
        bundle.putInt("transaction_type", i);
        aaVar.setArguments(bundle);
        return aaVar;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("aa");
        try {
            TraceMachine.enterMethod(this._nr_trace, "aa#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "aa#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10233n, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.f10240g = getArguments().getInt("transaction_type");
        }
        this.f10239f = getActivity();
        OlaApp olaApp = (OlaApp) this.f10239f.getApplication();
        this.f10245l = olaApp.m12878a();
        this.f10246m = olaApp.m12879b();
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "aa#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "aa#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10234o, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_recent_transaction_item, viewGroup, false);
        this.f10243j = (ListView) inflate.findViewById(R.id.listView);
        this.f10242i = (ProgressBar) inflate.findViewById(R.id.progressBar_loading);
        this.f10241h = new RecentTransactionItemFragment(this, this.f10239f);
        this.f10243j.setAdapter(this.f10241h);
        this.f10244k = (TextView) inflate.findViewById(R.id.no_transactions);
        this.f10243j.setOnScrollListener(this.f10238e);
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m13986a() {
        String userLoginEmail = ((OlaApp) this.f10239f.getApplication()).m12878a().m13209c().getUserLoginEmail();
        String str = Trace.NULL;
        switch (this.f10240g) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                str = "CREDIT";
                break;
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                str = "DEBIT";
                break;
        }
        this.f10236c = true;
        this.f10245l.m13205b(new WeakReference(this.f10237d), userLoginEmail, String.valueOf((this.f10243j.getAdapter().getCount() / 10) + 1), str, f10232a);
    }
}
