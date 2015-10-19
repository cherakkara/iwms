package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.dq;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.p076d.dv;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
public class MobileVerificationActivity extends FragmentActivity implements TextWatcher, OnClickListener, TraceFieldInterface {
    private static final JoinPoint f9724A = null;
    private static final JoinPoint f9725B = null;
    private static final JoinPoint f9726C = null;
    private static final String f9727b;
    private static final JoinPoint f9728z = null;
    BroadcastReceiver f9729a;
    private String f9730c;
    private String f9731d;
    private TextView f9732e;
    private TextView f9733f;
    private TextView f9734g;
    private EditText f9735h;
    private TextView f9736i;
    private Button f9737j;
    private TextView f9738k;
    private LinearLayout f9739l;
    private ImageView f9740m;
    private ProgressDialog f9741n;
    private LinearLayout f9742o;
    private LinearLayout f9743p;
    private TextView f9744q;
    private DataManager f9745r;
    private TextView f9746s;
    private String f9747t;
    private String f9748u;
    private String f9749v;
    private IntentFilter f9750w;
    private aj f9751x;
    private aj f9752y;

    /* renamed from: com.olacabs.customer.ui.MobileVerificationActivity.1 */
    class C08411 implements aj {
        final /* synthetic */ MobileVerificationActivity f9713a;

        C08411(MobileVerificationActivity mobileVerificationActivity) {
            this.f9713a = mobileVerificationActivity;
        }

        public void onFailure(Throwable th) {
            this.f9713a.f9741n.dismiss();
            this.f9713a.m13637a(this.f9713a.getString(R.string.generic_failure_desc), this.f9713a.getString(R.string.generic_failure_header), false);
        }

        public void onSuccess(Object obj) {
            this.f9713a.f9741n.dismiss();
            dq dqVar = (dq) obj;
            if (dqVar.getStatus().equals("SUCCESS")) {
                this.f9713a.f9737j.setEnabled(false);
                this.f9713a.f9731d = dqVar.getVerificationId();
                Toast.makeText(this.f9713a.getApplicationContext(), this.f9713a.getString(R.string.call_in_while), 0).show();
            } else if (dqVar.getStatus().equals("FAILURE")) {
                Toast.makeText(this.f9713a.getApplicationContext(), this.f9713a.getString(R.string.call_back_failure), 0).show();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.MobileVerificationActivity.2 */
    class C08422 implements aj {
        final /* synthetic */ MobileVerificationActivity f9714a;

        C08422(MobileVerificationActivity mobileVerificationActivity) {
            this.f9714a = mobileVerificationActivity;
        }

        public void onFailure(Throwable th) {
            this.f9714a.f9741n.dismiss();
            String string = this.f9714a.getString(R.string.generic_failure_header);
            this.f9714a.m13637a(this.f9714a.getString(R.string.generic_failure_desc), string, false);
        }

        public void onSuccess(Object obj) {
            dv dvVar = (dv) obj;
            this.f9714a.f9741n.dismiss();
            if (dvVar.getStatus().equals("SUCCESS")) {
                Localytics.tagEvent("Verified successfully");
                this.f9714a.f9745r.m13209c().setPhoneNumber(dvVar.getMobile());
                if (Ola.f11483G) {
                    this.f9714a.m13615a("Ola Money Screen");
                } else {
                    this.f9714a.m13615a("Profile Update");
                }
                Localytics.tagEvent("Mobile nbr changed");
                this.f9714a.m13637a(this.f9714a.getString(R.string.mobile_verification_successful_desc), this.f9714a.getString(R.string.mobile_verification_successful_title), true);
            } else if (dvVar.getStatus().equals("FAILURE")) {
                String string;
                Localytics.tagEvent("Verification failed");
                String reason = dvVar.getReason();
                if (reason.equalsIgnoreCase("INVALID_VERIFICATION_CODE")) {
                    string = this.f9714a.getString(R.string.invalid_entry);
                    reason = this.f9714a.getString(R.string.mobile_verification_fail_invalid_code_desc);
                } else if (reason.equalsIgnoreCase("INCORRECT_VERIFICATION_CODE")) {
                    string = dvVar.getHeader();
                    reason = dvVar.getText();
                } else if (reason.equalsIgnoreCase("MISSING_PARAMETERS")) {
                    string = this.f9714a.getString(R.string.failure);
                    reason = dvVar.getText();
                } else if (reason.equalsIgnoreCase("INVALID_PARAMETERS")) {
                    string = this.f9714a.getString(R.string.invalid_entry);
                    reason = dvVar.getText();
                } else if (reason.equalsIgnoreCase("USER_ALREADY_EXISTS")) {
                    string = this.f9714a.getString(R.string.warning);
                    reason = dvVar.getText();
                } else if (reason.equalsIgnoreCase("USER_BANNED")) {
                    string = this.f9714a.getString(R.string.failure);
                    reason = dvVar.getText();
                } else {
                    string = this.f9714a.getString(R.string.generic_failure_header);
                    reason = this.f9714a.getString(R.string.generic_failure_desc);
                }
                this.f9714a.m13637a(reason, string, false);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.MobileVerificationActivity.3 */
    class C08433 extends CountDownTimer {
        long f9715a;
        long f9716b;
        final /* synthetic */ MobileVerificationActivity f9717c;

        C08433(MobileVerificationActivity mobileVerificationActivity, long j, long j2) {
            this.f9717c = mobileVerificationActivity;
            super(j, j2);
        }

        public void onTick(long j) {
            this.f9715a = TimeUnit.MILLISECONDS.toMinutes(j);
            this.f9716b = TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j));
            this.f9717c.f9736i.setText("(" + String.format("%02d : %02d", new Object[]{Long.valueOf(this.f9715a), Long.valueOf(this.f9716b)}) + ")");
        }

        public void onFinish() {
            this.f9717c.f9736i.setText("(00 : 00)");
            this.f9717c.f9737j.setEnabled(true);
            this.f9717c.f9736i.setVisibility(8);
        }
    }

    /* renamed from: com.olacabs.customer.ui.MobileVerificationActivity.4 */
    class C08444 extends BroadcastReceiver {
        final /* synthetic */ MobileVerificationActivity f9718a;

        C08444(MobileVerificationActivity mobileVerificationActivity) {
            this.f9718a = mobileVerificationActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if (TextUtils.isEmpty(this.f9718a.f9735h.getText().toString())) {
                for (SmsMessage smsMessage : MobileVerificationActivity.m13616a(intent)) {
                    if (smsMessage != null) {
                        String a = this.f9718a.m13635a(smsMessage);
                        if (Utils.m14924g(a)) {
                            this.f9718a.f9747t = a;
                            this.f9718a.f9735h.setText(this.f9718a.f9747t);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.MobileVerificationActivity.5 */
    class C08455 implements OnClickListener {
        final /* synthetic */ AlertDialog f9719a;
        final /* synthetic */ boolean f9720b;
        final /* synthetic */ MobileVerificationActivity f9721c;

        C08455(MobileVerificationActivity mobileVerificationActivity, AlertDialog alertDialog, boolean z) {
            this.f9721c = mobileVerificationActivity;
            this.f9719a = alertDialog;
            this.f9720b = z;
        }

        public void onClick(View view) {
            this.f9719a.dismiss();
            if (this.f9720b && this.f9721c.f9730c.equals("update")) {
                this.f9721c.m13626e();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.MobileVerificationActivity.6 */
    class C08466 implements OnClickListener {
        final /* synthetic */ AlertDialog f9722a;
        final /* synthetic */ MobileVerificationActivity f9723b;

        C08466(MobileVerificationActivity mobileVerificationActivity, AlertDialog alertDialog) {
            this.f9723b = mobileVerificationActivity;
            this.f9722a = alertDialog;
        }

        public void onClick(View view) {
            this.f9723b.f9734g.setClickable(true);
            this.f9722a.dismiss();
        }
    }

    private static void m13634i() {
        Factory factory = new Factory("MobileVerificationActivity.java", MobileVerificationActivity.class);
        f9728z = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.MobileVerificationActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 185);
        f9724A = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.ui.MobileVerificationActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 263);
        f9725B = factory.m15070a("method-execution", factory.m15071a("1", "onPause", "com.olacabs.customer.ui.MobileVerificationActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 271);
        f9726C = factory.m15070a("method-execution", factory.m15071a("4", "onResume", "com.olacabs.customer.ui.MobileVerificationActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 351);
    }

    public MobileVerificationActivity() {
        this.f9751x = new C08411(this);
        this.f9752y = new C08422(this);
        this.f9729a = new C08444(this);
    }

    static {
        m13634i();
        f9727b = MobileVerificationActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("MobileVerificationActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "MobileVerificationActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "MobileVerificationActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9728z, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_mobile_verification);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f9730c = extras.getString(Constants.BUNDLE_TYPE);
            this.f9731d = extras.getString("verification_id");
            this.f9748u = extras.getString(Constants.BUNDLE_NAME);
            this.f9749v = extras.getString(dt.USER_PHONE_KEY);
        }
        this.f9745r = ((OlaApp) getApplication()).m12878a();
        Localytics.tagScreen("Mobile Verification");
        this.f9750w = new IntentFilter();
        this.f9732e = (TextView) findViewById(R.id.verification_txt);
        this.f9732e.setText(String.format(getString(R.string.user_mobile_num_verification_desc), new Object[]{this.f9749v}));
        this.f9741n = new ProgressDialog(this, R.style.TransparentProgressDialog);
        this.f9741n.setIndeterminateDrawable(getResources().getDrawable(R.drawable.custom_progress_background));
        this.f9741n.setCancelable(false);
        this.f9733f = (TextView) findViewById(R.id.enter_verification_code_txt);
        this.f9735h = (EditText) findViewById(R.id.verification_edit_txt);
        this.f9735h.addTextChangedListener(this);
        this.f9734g = (TextView) findViewById(R.id.why_verify_txt);
        this.f9734g.setOnClickListener(this);
        this.f9736i = (TextView) findViewById(R.id.verify_timer);
        this.f9737j = (Button) findViewById(R.id.call_me_button);
        this.f9737j.setOnClickListener(this);
        this.f9738k = (TextView) findViewById(R.id.verify);
        this.f9738k.setOnClickListener(this);
        this.f9739l = (LinearLayout) findViewById(R.id.view_no_network_state);
        this.f9740m = (ImageView) findViewById(R.id.verify_menu_button);
        this.f9740m.setOnClickListener(this);
        this.f9746s = (TextView) findViewById(R.id.vc_nt_recvd_txt);
        this.f9742o = (LinearLayout) findViewById(R.id.verification_background);
        this.f9743p = (LinearLayout) findViewById(R.id.action_bar_layout);
        this.f9744q = (TextView) findViewById(R.id.action_bar_view);
        m13618b();
        m13622c();
        TraceMachine.exitMethod();
    }

    private void m13618b() {
        this.f9742o.setBackgroundColor(getResources().getColor(R.color.ola_white));
        this.f9743p.setBackgroundColor(getResources().getColor(R.color.ola_white));
        this.f9732e.setTextColor(getResources().getColor(R.color.ola_black_text));
        this.f9733f.setTextColor(getResources().getColor(R.color.ola_vm_text_color));
        this.f9746s.setTextColor(getResources().getColor(R.color.ola_vm_text_color));
        this.f9744q.setBackgroundColor(getResources().getColor(R.color.ola_white));
        this.f9744q.setTextColor(getResources().getColor(R.color.ola_black_text));
    }

    public void onBackPressed() {
        Localytics.tagEvent("Profile verification update cancelled");
        setResult(0);
        finish();
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9724A, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9725B, (Object) this, (Object) this));
        try {
            unregisterReceiver(this.f9729a);
        } catch (IllegalArgumentException e) {
        }
        super.onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        this.f9745r.m13169a(f9727b);
        EventBus.m3a().m17b(this);
    }

    private void m13622c() {
        new C08433(this, 30000, 1000).start();
    }

    public static SmsMessage[] m13616a(Intent intent) {
        Object[] objArr = (Object[]) intent.getSerializableExtra("pdus");
        int length = objArr.length;
        SmsMessage[] smsMessageArr = new SmsMessage[length];
        for (int i = 0; i < length; i++) {
            smsMessageArr[i] = SmsMessage.createFromPdu((byte[]) objArr[i]);
        }
        return smsMessageArr;
    }

    public String m13635a(SmsMessage smsMessage) {
        CharSequence displayMessageBody = smsMessage.getDisplayMessageBody();
        if (!displayMessageBody.isEmpty() && displayMessageBody.toLowerCase().contains("olacabs") && displayMessageBody.toLowerCase().contains("unique verification code")) {
            Matcher matcher = Pattern.compile("\\b\\d{4}\\b").matcher(displayMessageBody);
            if (matcher.find()) {
                return matcher.group();
            }
        }
        return null;
    }

    protected void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f9726C, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            this.f9750w.addAction("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(this.f9729a, this.f9750w);
            if (Utils.m14909a((Context) this)) {
                this.f9739l.setVisibility(4);
            } else {
                this.f9739l.setVisibility(0);
            }
            ActivityTraceAspect.m12823a().m12828d(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.verify_menu_button:
                super.onBackPressed();
            case R.id.verify:
                m13624d();
            case R.id.why_verify_txt:
                this.f9734g.setClickable(false);
                Localytics.tagEvent("Why verify clicked");
                m13636a();
            case R.id.call_me_button:
                Localytics.tagEvent("Call message clicked");
                Localytics.tagEvent("Profile call me pressed");
                m13630g();
            default:
        }
    }

    private void m13624d() {
        if (Utils.m14920e(this.f9747t)) {
            m13631h();
        } else {
            m13637a(getString(R.string.mobile_verfification_incorrect_desc), getString(R.string.mobile_verfification_incorrect_title), false);
        }
    }

    protected void m13637a(String str, String str2, boolean z) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str2);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08455(this, create, z));
        create.show();
    }

    protected void m13636a() {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(getString(R.string.why_verify_title));
        ((TextView) inflate.findViewById(R.id.item_message)).setText(getString(R.string.why_verify_desc));
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08466(this, create));
        create.show();
    }

    private void m13626e() {
        super.onBackPressed();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.f9747t = editable.toString();
        if (editable.toString().length() == 4) {
            this.f9738k.setEnabled(true);
        } else {
            this.f9738k.setEnabled(false);
        }
    }

    public void onEventMainThread(ah ahVar) {
        if (ahVar.isConnected()) {
            this.f9739l.setVisibility(4);
            m13628f();
            return;
        }
        this.f9739l.setVisibility(0);
        this.f9738k.setEnabled(false);
    }

    private void m13628f() {
        if (this.f9735h.getText().length() == 4) {
            this.f9738k.setEnabled(true);
        }
    }

    private void m13630g() {
        this.f9741n.show();
        this.f9745r.m13185a(new WeakReference(this.f9751x), this.f9730c, this.f9731d, this.f9748u, this.f9749v, f9727b);
    }

    private void m13631h() {
        this.f9741n.show();
        this.f9745r.m13207b(new WeakReference(this.f9752y), this.f9730c, this.f9731d, this.f9747t, this.f9748u, this.f9749v, f9727b);
    }

    private void m13615a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("Verification Through", str);
        Localytics.tagEvent("Verification Completed", hashMap);
        Ola.f11483G = false;
    }
}
