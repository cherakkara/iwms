package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.CallEmergencyContactCommand;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.by;
import com.olacabs.customer.p076d.df;
import com.olacabs.customer.ui.widgets.KaaliPeeliProgressView;
import com.olacabs.customer.ui.widgets.KaaliPeeliProgressView.C0894a;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class SosTimerActivity extends Activity implements OnClickListener, TraceFieldInterface {
    private static final String f10106b;
    private static final JoinPoint f10107g = null;
    private static final JoinPoint f10108h = null;
    protected ProgressDialog f10109a;
    private KaaliPeeliProgressView f10110c;
    private TextView f10111d;
    private DataManager f10112e;
    private aj f10113f;

    /* renamed from: com.olacabs.customer.ui.SosTimerActivity.1 */
    class C08931 implements aj {
        final /* synthetic */ SosTimerActivity f10102a;

        C08931(SosTimerActivity sosTimerActivity) {
            this.f10102a = sosTimerActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("SOS failed", th);
            this.f10102a.f10109a.cancel();
            this.f10102a.m13863d();
        }

        public void onSuccess(Object obj) {
            this.f10102a.f10109a.cancel();
            df dfVar = (df) obj;
            if (dfVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                this.f10102a.m13857a(dfVar.getHeader() != null ? dfVar.getHeader().toString() : Constants.SOS_SUCCESS_HEADER_TEXT, dfVar.getText() != null ? dfVar.getText().toString() : Constants.SOS_SUCCESS_BODY_TEXT);
            } else if (dfVar.getStatus().equalsIgnoreCase("FAILURE")) {
                this.f10102a.m13857a(dfVar.getHeader() != null ? dfVar.getHeader().toString() : Constants.CONNECTION_TIME_OUT_HEADER, dfVar.getText() != null ? dfVar.getText().toString() : Constants.CONNECTION_TIME_OUT_TEXT);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.SosTimerActivity.2 */
    class C08952 implements C0894a {
        final /* synthetic */ SosTimerActivity f10103a;

        C08952(SosTimerActivity sosTimerActivity) {
            this.f10103a = sosTimerActivity;
        }

        public void m13853a() {
            OLog.m13315c("SOS EVENT TRIGGERED", new Object[0]);
            this.f10103a.findViewById(R.id.cancel_button).setEnabled(false);
            this.f10103a.m13861c();
            this.f10103a.f10112e.m13226e(new WeakReference(this.f10103a.f10113f), SosTimerActivity.f10106b);
        }
    }

    /* renamed from: com.olacabs.customer.ui.SosTimerActivity.3 */
    class C08963 implements OnClickListener {
        final /* synthetic */ AlertDialog f10104a;
        final /* synthetic */ SosTimerActivity f10105b;

        C08963(SosTimerActivity sosTimerActivity, AlertDialog alertDialog) {
            this.f10105b = sosTimerActivity;
            this.f10104a = alertDialog;
        }

        public void onClick(View view) {
            this.f10104a.dismiss();
            this.f10105b.finish();
        }
    }

    private static void m13864e() {
        Factory factory = new Factory("SosTimerActivity.java", SosTimerActivity.class);
        f10107g = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.SosTimerActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 68);
        f10108h = factory.m15070a("method-execution", factory.m15071a("1", "onPause", "com.olacabs.customer.ui.SosTimerActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 109);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    public SosTimerActivity() {
        this.f10113f = new C08931(this);
    }

    static {
        m13864e();
        f10106b = SosTimerActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("SosTimerActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "SosTimerActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "SosTimerActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f10107g, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10112e = ((OlaApp) getApplication()).m12878a();
        setContentView(R.layout.activity_sos_timer);
        this.f10111d = (TextView) findViewById(R.id.sos_screen_text);
        m13858b();
        this.f10110c = (KaaliPeeliProgressView) findViewById(R.id.progress_view);
        findViewById(R.id.cancel_button).setOnClickListener(this);
        this.f10110c.f11316b = by.DEFAULT_TIMEOUT_MS;
        this.f10110c.m14815e();
        this.f10110c.setOnProgressFinishListner(new C08952(this));
        this.f10110c.m14811a((int) R.color.ola_sos_arc, (int) R.color.ola_gray_light_line, (int) R.color.ola_sos_arc);
        this.f10110c.m14810a();
        TraceMachine.exitMethod();
    }

    private void m13858b() {
        if (Ola.f11482F != null && !Ola.f11482F.isEmpty()) {
            this.f10111d.setText(Ola.f11482F);
        }
    }

    private void m13861c() {
        this.f10109a = new ProgressDialog(this, R.style.TransparentnobgProgressDialog);
        this.f10109a.setIndeterminateDrawable(getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10109a.setCancelable(false);
        this.f10109a.show();
    }

    public void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f10108h, (Object) this, (Object) this));
        super.onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        this.f10112e.m13169a(f10106b);
    }

    private void m13857a(String str, String str2) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        create.setCancelable(false);
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08963(this, create));
        create.show();
    }

    private void m13863d() {
        String str = Ola.f11481E;
        if (Utils.m14910a(str)) {
            new CallEmergencyContactCommand(str).m12888a(this);
        }
        finish();
    }

    public void onBackPressed() {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_button:
                Localytics.tagEvent("App SOS Cancelled");
                this.f10110c.m14812b();
                finish();
            default:
        }
    }
}
