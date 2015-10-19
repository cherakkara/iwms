package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.cl;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class RechargeWithCodeActivity extends Activity implements OnClickListener, TraceFieldInterface {
    private static final String f9919b;
    private static final JoinPoint f9920g = null;
    private static final JoinPoint f9921h = null;
    private static final JoinPoint f9922i = null;
    protected ProgressDialog f9923a;
    private EditText f9924c;
    private TextView f9925d;
    private DataManager f9926e;
    private aj f9927f;

    /* renamed from: com.olacabs.customer.ui.RechargeWithCodeActivity.1 */
    class C08681 implements aj {
        final /* synthetic */ RechargeWithCodeActivity f9915a;

        C08681(RechargeWithCodeActivity rechargeWithCodeActivity) {
            this.f9915a = rechargeWithCodeActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed verify recharge code", th);
            this.f9915a.f9923a.cancel();
            this.f9915a.m13732a(this.f9915a.getString(R.string.connection_time_out_error_desc), this.f9915a.getString(R.string.connection_time_out_error_title), false);
        }

        public void onSuccess(Object obj) {
            this.f9915a.f9923a.cancel();
            cl clVar = (cl) obj;
            String header;
            if (clVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                if (clVar.getBalanceAdded() != null) {
                    OLog.m13311a("Successfully verified recharge code", new Object[0]);
                    header = clVar.getHeader();
                    this.f9915a.m13732a(clVar.getText(), header, true);
                    Localytics.tagEvent("Recharge Voucher Successful");
                }
            } else if (clVar.getStatus().equalsIgnoreCase("FAILURE")) {
                OLog.m13318e("Failed verify recharge code", new Object[0]);
                header = clVar.getHeader();
                this.f9915a.m13732a(clVar.getText(), header, false);
                Localytics.tagEvent("Recharge Voucher Failed");
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.RechargeWithCodeActivity.2 */
    class C08692 implements OnClickListener {
        final /* synthetic */ boolean f9916a;
        final /* synthetic */ AlertDialog f9917b;
        final /* synthetic */ RechargeWithCodeActivity f9918c;

        C08692(RechargeWithCodeActivity rechargeWithCodeActivity, boolean z, AlertDialog alertDialog) {
            this.f9918c = rechargeWithCodeActivity;
            this.f9916a = z;
            this.f9917b = alertDialog;
        }

        public void onClick(View view) {
            if (this.f9916a) {
                this.f9918c.setResult(-1);
                this.f9918c.finish();
            } else {
                this.f9918c.f9924c.setText(Trace.NULL);
            }
            this.f9917b.dismiss();
        }
    }

    private static void m13729a() {
        Factory factory = new Factory("RechargeWithCodeActivity.java", RechargeWithCodeActivity.class);
        f9920g = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.RechargeWithCodeActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 71);
        f9921h = factory.m15070a("method-execution", factory.m15071a("4", "onResume", "com.olacabs.customer.ui.RechargeWithCodeActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 87);
        f9922i = factory.m15070a("method-execution", factory.m15071a("4", "onPause", "com.olacabs.customer.ui.RechargeWithCodeActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 93);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    public RechargeWithCodeActivity() {
        this.f9927f = new C08681(this);
    }

    static {
        m13729a();
        f9919b = RechargeWithCodeActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("RechargeWithCodeActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "RechargeWithCodeActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "RechargeWithCodeActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9920g, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_recharge_with_code);
        this.f9926e = ((OlaApp) getApplication()).m12878a();
        Localytics.tagScreen("Recharge With Coupon Viewed");
        this.f9924c = (EditText) findViewById(R.id.recharge_code_edittext);
        this.f9925d = (TextView) findViewById(R.id.payment_errorText);
        findViewById(R.id.button_pay).setOnClickListener(this);
        findViewById(R.id.button_back).setOnClickListener(this);
        TraceMachine.exitMethod();
    }

    protected void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f9921h, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            m13730a(this.f9924c);
        } finally {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    protected void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9922i, (Object) this, (Object) this));
        super.onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        this.f9926e.m13169a(f9919b);
    }

    public void onBackPressed() {
        setResult(0);
        finish();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                Localytics.tagEvent("Recharge Voucher Abandoned");
                setResult(0);
                finish();
            case R.id.button_pay:
                if (this.f9924c.getText().toString().equalsIgnoreCase(Trace.NULL)) {
                    this.f9925d.setText(getString(R.string.invalid_recharge_code_error));
                    this.f9925d.setVisibility(0);
                    return;
                }
                this.f9925d.setVisibility(8);
                this.f9923a = new ProgressDialog(this, R.style.TransparentProgressDialog);
                this.f9923a.setIndeterminateDrawable(getResources().getDrawable(R.drawable.custom_progress_background));
                this.f9923a.setCancelable(false);
                this.f9923a.show();
                this.f9926e.m13227e(new WeakReference(this.f9927f), this.f9924c.getText().toString(), f9919b);
            default:
                throw new IllegalArgumentException("Click on unknown view");
        }
    }

    private void m13732a(String str, String str2, boolean z) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str2);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08692(this, z, create));
        create.show();
    }

    private void m13730a(View view) {
        if (view != null) {
            view.requestFocus();
            ((InputMethodManager) getSystemService("input_method")).showSoftInput(view, 2);
        }
    }
}
