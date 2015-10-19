package com.olacabs.customer.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.AccountBalanceResponse;
import com.olacabs.customer.p076d.aj;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class InvitedListActivity extends Activity implements OnClickListener, TraceFieldInterface {
    public static final String f9648a;
    private static final JoinPoint f9649f = null;
    private ListView f9650b;
    private TextView f9651c;
    private DataManager f9652d;
    private aj f9653e;

    /* renamed from: com.olacabs.customer.ui.InvitedListActivity.1 */
    class C08291 implements aj {
        final /* synthetic */ InvitedListActivity f9643a;

        C08291(InvitedListActivity invitedListActivity) {
            this.f9643a = invitedListActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to obtain account balance", th);
        }

        public void onSuccess(Object obj) {
            AccountBalanceResponse accountBalanceResponse = (AccountBalanceResponse) obj;
            if (accountBalanceResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13311a("Get account balance succeeded", new Object[0]);
                String string = this.f9643a.getResources().getString(R.string.rs_symbol);
                this.f9643a.findViewById(R.id.progressBar_loading).setVisibility(8);
                if (accountBalanceResponse.getInvitees() == null || accountBalanceResponse.getInvitees().size() == 0) {
                    this.f9643a.findViewById(R.id.layout_no_invites).setVisibility(0);
                    return;
                }
                this.f9643a.f9650b.setAdapter(new C0830a(this.f9643a, this.f9643a, accountBalanceResponse.getInvitees()));
                this.f9643a.f9651c.setText(string + accountBalanceResponse.getTotalBalance());
                this.f9643a.findViewById(R.id.main_layout).setVisibility(0);
            } else if (accountBalanceResponse.getStatus().equalsIgnoreCase("FAILURE")) {
                OLog.m13318e("Get account balance failed - " + accountBalanceResponse.getReason(), new Object[0]);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.InvitedListActivity.a */
    private class C0830a extends BaseAdapter {
        final /* synthetic */ InvitedListActivity f9644a;
        private LayoutInflater f9645b;
        private ArrayList<AccountBalanceResponse.AccountBalanceResponse> f9646c;
        private String f9647d;

        public C0830a(InvitedListActivity invitedListActivity, Context context, ArrayList<AccountBalanceResponse.AccountBalanceResponse> arrayList) {
            this.f9644a = invitedListActivity;
            this.f9646c = arrayList;
            this.f9645b = LayoutInflater.from(context);
            this.f9647d = context.getString(R.string.rs_symbol);
        }

        public int getCount() {
            return this.f9646c == null ? 0 : this.f9646c.size();
        }

        public Object getItem(int i) {
            return this.f9646c.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.f9645b.inflate(R.layout.item_invite_invitees, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(R.id.item_amount);
            TextView textView2 = (TextView) view.findViewById(R.id.item_joined);
            AccountBalanceResponse.AccountBalanceResponse accountBalanceResponse = (AccountBalanceResponse.AccountBalanceResponse) this.f9646c.get(i);
            ((TextView) view.findViewById(R.id.item_name)).setText(accountBalanceResponse.getName());
            if (accountBalanceResponse.isTravelled()) {
                textView2.setVisibility(8);
                textView.setVisibility(0);
                textView.setText(this.f9647d + accountBalanceResponse.getCreditGain());
            } else {
                textView2.setText(accountBalanceResponse.getStatus());
                textView2.setVisibility(0);
                textView.setVisibility(8);
            }
            return view;
        }
    }

    private static void m13562b() {
        Factory factory = new Factory("InvitedListActivity.java", InvitedListActivity.class);
        f9649f = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.InvitedListActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 69);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    public InvitedListActivity() {
        this.f9653e = new C08291(this);
    }

    static {
        m13562b();
        f9648a = InvitedListActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("InvitedListActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "InvitedListActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "InvitedListActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9649f, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_invited_list);
        this.f9650b = (ListView) findViewById(R.id.listView);
        this.f9651c = (TextView) findViewById(R.id.item_money_earned);
        findViewById(R.id.button_back).setOnClickListener(this);
        this.f9652d = ((OlaApp) getApplication()).m12878a();
        Localytics.tagScreen("earnings");
        m13560a();
        TraceMachine.exitMethod();
    }

    private void m13560a() {
        this.f9652d.m13203b(new WeakReference(this.f9653e), "detail", f9648a);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                finish();
            default:
        }
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        this.f9652d.m13169a(f9648a);
    }
}
