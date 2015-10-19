package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.ForceLogoutCommand;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.ChangePasswordResponse;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.p079e.p080a.PasswordStrength;
import com.olacabs.customer.ui.widgets.PasswordStrengthIndicator;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class ChangePasswordActivity extends FragmentActivity implements TextWatcher, OnClickListener, OnFocusChangeListener, TraceFieldInterface {
    public static final String f9546a;
    private static final JoinPoint f9547p = null;
    private static final JoinPoint f9548q = null;
    private static final JoinPoint f9549r = null;
    private static final JoinPoint f9550s = null;
    private EditText f9551b;
    private EditText f9552c;
    private EditText f9553d;
    private TextView f9554e;
    private TextView f9555f;
    private ImageView f9556g;
    private Button f9557h;
    private LinearLayout f9558i;
    private PasswordStrengthIndicator f9559j;
    private PasswordStrength f9560k;
    private TextView f9561l;
    private DataManager f9562m;
    private ProgressDialog f9563n;
    private aj f9564o;

    /* renamed from: com.olacabs.customer.ui.ChangePasswordActivity.1 */
    class C08121 implements aj {
        final /* synthetic */ ChangePasswordActivity f9541a;

        C08121(ChangePasswordActivity changePasswordActivity) {
            this.f9541a = changePasswordActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to fetch profile details", th);
            this.f9541a.f9563n.dismiss();
            this.f9541a.m13492a(this.f9541a.getString(R.string.generic_failure_desc), false);
        }

        public void onSuccess(Object obj) {
            this.f9541a.f9563n.dismiss();
            ChangePasswordResponse changePasswordResponse = (ChangePasswordResponse) obj;
            if (changePasswordResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13311a("Fetched  data", new Object[0]);
                Editor edit = PreferenceManager.getDefaultSharedPreferences(this.f9541a).edit();
                edit.clear();
                edit.putBoolean(Constants.PREF_SAFETY_CAROUSEL_SHOWN_ALREADY, true);
                edit.putBoolean(da.NEW_INSTALL_IDENTIFIER_KEY, false);
                edit.apply();
                Localytics.tagEvent("Password changed");
                this.f9541a.m13492a(this.f9541a.getString(R.string.password_change_success), true);
            } else if (changePasswordResponse.getStatus().equalsIgnoreCase("FAILURE")) {
                OLog.m13311a("Failed to fetch data", new Object[0]);
                if (changePasswordResponse.getReason().equalsIgnoreCase("INCORRECT_PASSWORD")) {
                    this.f9541a.m13492a(this.f9541a.getString(R.string.password_change_failed), false);
                } else {
                    this.f9541a.m13492a(changePasswordResponse.getText(), false);
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.ChangePasswordActivity.2 */
    class C08132 implements OnClickListener {
        final /* synthetic */ AlertDialog f9542a;
        final /* synthetic */ ChangePasswordActivity f9543b;

        C08132(ChangePasswordActivity changePasswordActivity, AlertDialog alertDialog) {
            this.f9543b = changePasswordActivity;
            this.f9542a = alertDialog;
        }

        public void onClick(View view) {
            this.f9542a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.ChangePasswordActivity.3 */
    class C08143 implements OnDismissListener {
        final /* synthetic */ boolean f9544a;
        final /* synthetic */ ChangePasswordActivity f9545b;

        C08143(ChangePasswordActivity changePasswordActivity, boolean z) {
            this.f9545b = changePasswordActivity;
            this.f9544a = z;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (this.f9544a) {
                this.f9545b.m13487k();
            }
        }
    }

    private static void m13491o() {
        Factory factory = new Factory("ChangePasswordActivity.java", ChangePasswordActivity.class);
        f9547p = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.ChangePasswordActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), (int) HttpStatus.SC_PROCESSING);
        f9548q = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.ui.ChangePasswordActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 114);
        f9549r = factory.m15070a("method-execution", factory.m15071a("4", "onResume", "com.olacabs.customer.ui.ChangePasswordActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 187);
        f9550s = factory.m15070a("method-execution", factory.m15071a("1", "onPause", "com.olacabs.customer.ui.ChangePasswordActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 195);
    }

    public ChangePasswordActivity() {
        this.f9564o = new C08121(this);
    }

    static {
        m13491o();
        f9546a = ChangePasswordActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("ChangePasswordActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "ChangePasswordActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ChangePasswordActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9547p, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_change_password);
        m13473a();
        this.f9562m = ((OlaApp) getApplication()).m12878a();
        Localytics.tagScreen("Password Change");
        TraceMachine.exitMethod();
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9548q, (Object) this, (Object) this));
        super.onStart();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
    }

    private void m13473a() {
        this.f9551b = (EditText) findViewById(R.id.cp_old_password_txt);
        this.f9551b.setOnFocusChangeListener(this);
        this.f9551b.addTextChangedListener(this);
        this.f9552c = (EditText) findViewById(R.id.cp_new_password_txt);
        this.f9552c.setOnFocusChangeListener(this);
        this.f9552c.addTextChangedListener(this);
        this.f9553d = (EditText) findViewById(R.id.cp_re_enter_password_txt);
        this.f9556g = (ImageView) findViewById(R.id.pwd_back_button);
        this.f9556g.setOnClickListener(this);
        this.f9553d.setOnFocusChangeListener(this);
        this.f9553d.addTextChangedListener(this);
        this.f9554e = (TextView) findViewById(R.id.error_text);
        this.f9558i = (LinearLayout) findViewById(R.id.button_fragment_container);
        this.f9559j = (PasswordStrengthIndicator) findViewById(R.id.password_indicator);
        this.f9559j.setVisibility(4);
        this.f9560k = new PasswordStrength(this);
        this.f9561l = (TextView) findViewById(R.id.password_indicator_text);
        this.f9561l.setVisibility(8);
        m13476b();
    }

    private void m13476b() {
        m13480d();
        View inflate = getLayoutInflater().inflate(R.layout.view_ola_button, null);
        this.f9558i.addView(inflate);
        this.f9557h = (Button) inflate.findViewById(R.id.sign_up_submit_btn);
        this.f9557h.setText(getResources().getString(R.string.save));
        m13479c();
        this.f9557h.setOnClickListener(this);
    }

    private void m13479c() {
        boolean z = false;
        PasswordStrength.PasswordStrength a = this.f9560k.m13410a(this.f9552c.getText().toString());
        boolean z2 = (TextUtils.isEmpty(this.f9551b.getText().toString()) || TextUtils.isEmpty(this.f9552c.getText().toString()) || TextUtils.isEmpty(this.f9553d.getText().toString())) ? false : true;
        if (!this.f9551b.getText().toString().equals(this.f9552c.getText().toString()) && this.f9552c.getText().toString().equals(this.f9553d.getText().toString())) {
            if (a != PasswordStrength.PasswordStrength.WEAK) {
                z = z2;
            }
            this.f9557h.setEnabled(z);
        }
    }

    private void m13480d() {
        this.f9558i.removeAllViews();
    }

    private void m13481e() {
        m13480d();
        View inflate = getLayoutInflater().inflate(R.layout.view_no_network_state, null);
        this.f9558i.addView(inflate);
        this.f9555f = (TextView) inflate.findViewById(R.id.no_internet_txt);
    }

    protected void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f9549r, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            if (Utils.m14909a(getApplicationContext())) {
                m13476b();
            } else {
                m13481e();
            }
            ActivityTraceAspect.m12823a().m12828d(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    public void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9550s, (Object) this, (Object) this));
        this.f9562m.m13169a(f9546a);
        super.onPause();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pwd_back_button:
                Localytics.tagEvent("Pwd change cancelled");
                super.onBackPressed();
            case R.id.sign_up_submit_btn:
                m13488l();
                if (m13483g()) {
                    m13482f();
                }
            default:
        }
    }

    private void m13482f() {
        this.f9563n = new ProgressDialog(this, R.style.TransparentProgressDialog);
        this.f9563n.setIndeterminateDrawable(getResources().getDrawable(R.drawable.custom_progress_background));
        this.f9563n.setCancelable(false);
        this.f9563n.show();
        this.f9562m.m13233f(new WeakReference(this.f9564o), this.f9551b.getText().toString(), this.f9552c.getText().toString(), f9546a);
    }

    protected void m13492a(String str, boolean z) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        if (z) {
            ((TextView) inflate.findViewById(R.id.item_header)).setText("Password Changed");
        } else {
            ((TextView) inflate.findViewById(R.id.item_header)).setText("Failure");
        }
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08132(this, create));
        create.setOnDismissListener(new C08143(this, z));
        create.show();
    }

    private boolean m13483g() {
        String obj = this.f9552c.getText().toString();
        Object obj2 = this.f9551b.getText().toString();
        CharSequence obj3 = this.f9553d.getText().toString();
        PasswordStrength.PasswordStrength a = this.f9560k.m13410a(this.f9552c.getText().toString());
        if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2) || TextUtils.isEmpty(obj3)) {
            m13475a(Constants.PASSWORD_SHOULD_NOT_BLANK);
            return false;
        } else if (obj2.equals(obj)) {
            m13475a(Constants.PASSWORD_SHOULD_NOT_SAME);
            return false;
        } else if (!Utils.m14918d(obj)) {
            m13475a(Constants.PASSWORD_SHOULD_NOT_BLANK);
            return false;
        } else if (a == PasswordStrength.PasswordStrength.WEAK) {
            m13475a(getString(R.string.password_letters_numbers));
            return false;
        } else if (obj.equals(obj3)) {
            return true;
        } else {
            m13475a(getString(R.string.password_match_failed_hint));
            return false;
        }
    }

    public void onFocusChange(View view, boolean z) {
        m13474a(view);
    }

    private void m13474a(View view) {
        String obj;
        switch (view.getId()) {
            case R.id.cp_old_password_txt:
                m13479c();
            case R.id.cp_new_password_txt:
                m13479c();
                if (this.f9551b.getText().toString() == null || this.f9551b.getText().toString().length() == 0) {
                    m13475a(Constants.PASSWORD_SHOULD_NOT_BLANK);
                    this.f9557h.setEnabled(false);
                } else {
                    m13484h();
                }
                if (this.f9552c.hasFocus()) {
                    m13478b(getString(R.string.password_letters_numbers));
                    return;
                }
                m13488l();
                obj = this.f9552c.getText().toString();
                if (Utils.m14924g(obj)) {
                    PasswordStrength.PasswordStrength a = this.f9560k.m13410a(obj);
                    if (a != PasswordStrength.PasswordStrength.WEAK) {
                        return;
                    }
                    if (a.f9450d) {
                        m13488l();
                        m13475a(getString(R.string.password_too_simple));
                        m13489m();
                        return;
                    }
                    m13488l();
                    m13475a(getString(R.string.password_letters_numbers));
                    m13490n();
                }
            case R.id.cp_re_enter_password_txt:
                m13479c();
                obj = this.f9551b.getText().toString();
                String obj2 = this.f9552c.getText().toString();
                if (!Utils.m14918d(obj) || !Utils.m14918d(obj2)) {
                    m13475a(Constants.PASSWORD_SHOULD_NOT_BLANK);
                    this.f9557h.setEnabled(false);
                } else if (!Utils.m14914b(obj, obj2)) {
                    m13475a(Constants.PASSWORD_SHOULD_NOT_SAME);
                    this.f9557h.setEnabled(false);
                }
            default:
        }
    }

    private void m13475a(String str) {
        this.f9554e.setVisibility(0);
        this.f9554e.setText(str);
    }

    private void m13484h() {
        if (this.f9554e.getVisibility() != 8) {
            this.f9554e.setVisibility(8);
            this.f9554e.setText(Trace.NULL);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        if (this.f9553d.hasFocus()) {
            if (TextUtils.isEmpty(editable.toString())) {
                this.f9557h.setEnabled(false);
                return;
            }
            m13479c();
            String obj = this.f9551b.getText().toString();
            String obj2 = this.f9552c.getText().toString();
            if (!Utils.m14918d(obj) || !Utils.m14918d(obj2)) {
                m13475a(Constants.PASSWORD_SHOULD_NOT_BLANK);
                this.f9557h.setEnabled(false);
            } else if (Utils.m14914b(obj, obj2)) {
                m13479c();
                m13484h();
            } else {
                m13475a(Constants.PASSWORD_SHOULD_NOT_SAME);
                this.f9557h.setEnabled(false);
            }
        } else if (this.f9551b.hasFocus()) {
            if (TextUtils.isEmpty(editable.toString())) {
                this.f9557h.setEnabled(false);
            }
        } else if (!this.f9552c.hasFocus()) {
        } else {
            if (Utils.m14924g(editable.toString())) {
                this.f9557h.setEnabled(false);
                PasswordStrength.PasswordStrength a = this.f9560k.m13410a(editable.toString());
                this.f9559j.m14820a(a);
                if (this.f9561l.getVisibility() != 0) {
                    m13486j();
                }
                if (this.f9559j.getVisibility() != 0) {
                    this.f9559j.setVisibility(0);
                }
                if (a != PasswordStrength.PasswordStrength.WEAK || a.f9450d) {
                }
                if (a == PasswordStrength.PasswordStrength.WEAK) {
                    this.f9561l.setText(getString(R.string.strength_weak));
                    this.f9561l.setTextColor(getResources().getColor(R.color.password_weak));
                    return;
                } else if (a == PasswordStrength.PasswordStrength.GOOD) {
                    this.f9561l.setText(getString(R.string.strength_good));
                    this.f9561l.setTextColor(getResources().getColor(R.color.password_good));
                    return;
                } else if (a == PasswordStrength.PasswordStrength.STRONG) {
                    this.f9561l.setText(getString(R.string.strength_strong));
                    this.f9561l.setTextColor(getResources().getColor(R.color.password_strong));
                    return;
                } else {
                    return;
                }
            }
            this.f9559j.m14819a();
            this.f9559j.setVisibility(4);
            m13485i();
        }
    }

    private void m13485i() {
        this.f9561l.setVisibility(8);
    }

    private void m13486j() {
        this.f9561l.setVisibility(0);
    }

    private void m13487k() {
        Localytics.tagEvent("Signed out");
        new ForceLogoutCommand().m13270a((Context) this);
    }

    private void m13488l() {
        this.f9554e.setBackgroundColor(getResources().getColor(R.color.ola_red_dark));
        this.f9554e.setTextColor(getResources().getColor(R.color.ola_white));
        this.f9554e.setText(Trace.NULL);
        this.f9554e.setVisibility(8);
    }

    private void m13478b(String str) {
        this.f9554e.setText(str);
        this.f9554e.setBackgroundColor(getResources().getColor(R.color.ola_green_flurescent));
        this.f9554e.setTextColor(getResources().getColor(R.color.ola_black));
        this.f9554e.setVisibility(0);
    }

    private void m13489m() {
        Map hashMap = new HashMap();
        hashMap.put("Password error", "Simple password error");
        Localytics.tagEvent("Error on password entry", hashMap);
    }

    private void m13490n() {
        Map hashMap = new HashMap();
        hashMap.put("Password error", "Incorrect password policy error");
        Localytics.tagEvent("Error on password entry", hashMap);
    }
}
