package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.android.volley.VolleyError;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.AppState;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.p076d.dd;
import com.olacabs.customer.p076d.de;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.p079e.p080a.PasswordStrength;
import com.olacabs.customer.ui.widgets.FloatLabelLayout;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.EncryptionScheme;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
/* compiled from: SignUpFragment */
public class aj extends Fragment implements TextWatcher, OnClickListener, OnFocusChangeListener, OnEditorActionListener, TraceFieldInterface {
    protected static final String f10435a;
    private static final JoinPoint f10436w = null;
    private static final JoinPoint f10437x = null;
    private static final JoinPoint f10438y = null;
    private static final JoinPoint f10439z = null;
    protected ProgressDialog f10440b;
    private String f10441c;
    private String f10442d;
    private String f10443e;
    private String f10444f;
    private TextView f10445g;
    private ScrollView f10446h;
    private EditText f10447i;
    private EditText f10448j;
    private EditText f10449k;
    private EditText f10450l;
    private EditText f10451m;
    private AutoCompleteTextView f10452n;
    private ImageView f10453o;
    private TextView f10454p;
    private TextView f10455q;
    private LinearLayout f10456r;
    private DataManager f10457s;
    private boolean f10458t;
    private PasswordStrength f10459u;
    private com.olacabs.customer.p076d.aj f10460v;

    /* renamed from: com.olacabs.customer.ui.aj.1 */
    class SignUpFragment implements com.olacabs.customer.p076d.aj {
        final /* synthetic */ aj f10426a;

        SignUpFragment(aj ajVar) {
            this.f10426a = ajVar;
        }

        public void onFailure(Throwable th) {
            Sherlock.m13337a("Ins signup clicked", (VolleyError) th, true);
            this.f10426a.f10458t = false;
            OLog.m13310a("Signup failed", th);
            this.f10426a.f10440b.dismiss();
            this.f10426a.m14097a(this.f10426a.getString(R.string.signup_failure_error_desc), this.f10426a.getString(R.string.signup_failure_error_title));
        }

        public void onSuccess(Object obj) {
            this.f10426a.f10440b.dismiss();
            de deVar = (de) obj;
            this.f10426a.f10458t = false;
            if (deVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13311a("Sign-up call successful", new Object[0]);
                if (deVar.verificationFlowNeeded()) {
                    Bundle bundle = new Bundle();
                    if (deVar.getVerificationId() != null) {
                        bundle.putString("verification_id", deVar.getVerificationId());
                    }
                    bundle.putString(Constants.BUNDLE_TYPE, "signup");
                    ((SplashActivity) this.f10426a.getActivity()).m13900b(bundle);
                    return;
                }
                dt c = this.f10426a.f10457s.m13209c();
                da d = this.f10426a.f10457s.m13218d();
                c.updateUserInfoOnSignUp(deVar.getUserId(), deVar.getReferralCode(), this.f10426a.f10450l.getText().toString(), this.f10426a.f10447i.getText().toString(), this.f10426a.f10452n.getText().toString(), deVar.getCity(), true);
                d.setIsFirstSession(true);
                d.setAppState(AppState.BEFORE_BOOKING);
                d.clearSignUpAttemptDetail();
                ((SplashActivity) this.f10426a.getActivity()).m13902c();
                ((SplashActivity) this.f10426a.getActivity()).m13899b();
            } else if (deVar.getStatus().equalsIgnoreCase("FAILURE")) {
                String reason = deVar.getReason();
                OLog.m13311a("Sign-up call failed - " + reason, new Object[0]);
                if (!TextUtils.isEmpty(deVar.getText())) {
                    reason = deVar.getHeader();
                    if (TextUtils.isEmpty(reason)) {
                        reason = this.f10426a.getString(R.string.generic_failure_header);
                    }
                    this.f10426a.m14097a(deVar.getText(), reason);
                } else if (reason.equalsIgnoreCase("USER_ALREADY_EXISTS")) {
                    this.f10426a.m14112k();
                } else {
                    if (reason.equals("INVALID_EMAIL_ID")) {
                        reason = this.f10426a.getString(R.string.invalid_email_id_hint);
                    } else if (reason.equals("MISSING_PARAMETERS")) {
                        reason = this.f10426a.getString(R.string.empty_fields_hint);
                    } else if (reason.equals("INVALID_REFERRAL_CODE")) {
                        reason = this.f10426a.getString(R.string.invalid_ref_code_desc);
                    } else {
                        reason = this.f10426a.getString(R.string.signup_generic_failure_desc);
                    }
                    this.f10426a.m14097a(reason, this.f10426a.getString(R.string.generic_failure_header));
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.aj.2 */
    class SignUpFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10427a;
        final /* synthetic */ aj f10428b;

        SignUpFragment(aj ajVar, AlertDialog alertDialog) {
            this.f10428b = ajVar;
            this.f10427a = alertDialog;
        }

        public void onClick(View view) {
            this.f10427a.dismiss();
            this.f10428b.f10458t = false;
        }
    }

    /* renamed from: com.olacabs.customer.ui.aj.3 */
    class SignUpFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10429a;
        final /* synthetic */ aj f10430b;

        SignUpFragment(aj ajVar, AlertDialog alertDialog) {
            this.f10430b = ajVar;
            this.f10429a = alertDialog;
        }

        public void onClick(View view) {
            this.f10429a.dismiss();
            this.f10430b.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_sign_up, LoginFragment.m14541a()).commit();
        }
    }

    /* renamed from: com.olacabs.customer.ui.aj.4 */
    class SignUpFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10431a;
        final /* synthetic */ aj f10432b;

        SignUpFragment(aj ajVar, AlertDialog alertDialog) {
            this.f10432b = ajVar;
            this.f10431a = alertDialog;
        }

        public void onClick(View view) {
            this.f10431a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.aj.5 */
    class SignUpFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10433a;
        final /* synthetic */ aj f10434b;

        SignUpFragment(aj ajVar, AlertDialog alertDialog) {
            this.f10434b = ajVar;
            this.f10433a = alertDialog;
        }

        public void onClick(View view) {
            this.f10433a.dismiss();
        }
    }

    private static void m14116o() {
        Factory factory = new Factory("SignUpFragment.java", aj.class);
        f10436w = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.SignUpFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 178);
        f10437x = factory.m15070a("method-execution", factory.m15071a("1", "onStart", "com.olacabs.customer.ui.SignUpFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 195);
        f10438y = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.SignUpFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), (int) HttpStatus.SC_ACCEPTED);
        f10439z = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.SignUpFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 212);
    }

    static {
        m14116o();
        f10435a = aj.class.getSimpleName();
    }

    public static aj m14093a() {
        return new aj();
    }

    public aj() {
        this.f10458t = false;
        this.f10460v = new SignUpFragment(this);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("aj");
        try {
            TraceMachine.enterMethod(this._nr_trace, "aj#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "aj#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10436w, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            this.f10441c = extras.getString(Constants.PREF_REFERRAL_CODE);
            this.f10443e = extras.getString(Constants.PREF_NAME);
            this.f10444f = extras.getString(Constants.PREF_MOBILE);
            this.f10442d = extras.getString(Constants.PREF_EMAIL);
        }
        this.f10457s = ((OlaApp) getActivity().getApplication()).m12878a();
        Localytics.tagScreen("Sign up");
        TraceMachine.exitMethod();
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f10437x, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
        m14102c();
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f10438y, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            if (!Utils.m14909a(getActivity())) {
                this.f10456r.setVisibility(0);
            }
            ActivityTraceAspect.m12823a().m12827c(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "aj#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "aj#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10439z, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        OLog.m13311a("onCreateView +", new Object[0]);
        View inflate = layoutInflater.inflate(R.layout.fragment_sign_up, viewGroup, false);
        this.f10446h = (ScrollView) inflate.findViewById(R.id.sign_up_scroll_view);
        this.f10454p = (TextView) inflate.findViewById(R.id.error_text);
        this.f10455q = (TextView) inflate.findViewById(R.id.agreement_txt2);
        this.f10455q.setText(Html.fromHtml(getString(R.string.user_terms_link_text)));
        this.f10455q.setMovementMethod(LinkMovementMethod.getInstance());
        this.f10447i = (EditText) inflate.findViewById(R.id.phone_number_txt);
        this.f10448j = (EditText) inflate.findViewById(R.id.password_txt);
        this.f10449k = (EditText) inflate.findViewById(R.id.re_enter_password_txt);
        this.f10450l = (EditText) inflate.findViewById(R.id.name_txt);
        this.f10452n = (AutoCompleteTextView) inflate.findViewById(R.id.email_txt);
        this.f10452n.requestFocus();
        this.f10452n.setAdapter(new ArrayAdapter(getActivity(), R.layout.item_simple_dropdown, Utils.m14919e(getActivity())));
        this.f10451m = (EditText) inflate.findViewById(R.id.referral_code_txt);
        this.f10451m.setOnEditorActionListener(this);
        this.f10453o = (ImageView) inflate.findViewById(R.id.question_mark);
        this.f10453o.setOnClickListener(this);
        this.f10445g = (TextView) inflate.findViewById(R.id.sign_up);
        this.f10445g.setOnClickListener(this);
        m14108g();
        m14104d();
        this.f10456r = (LinearLayout) inflate.findViewById(R.id.view_no_network_state);
        this.f10459u = new PasswordStrength(getActivity());
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14102c() {
        if (!TextUtils.isEmpty(this.f10442d)) {
            this.f10452n.setText(this.f10442d);
        }
        if (!TextUtils.isEmpty(this.f10443e)) {
            this.f10450l.setText(this.f10443e);
        }
        if (!TextUtils.isEmpty(this.f10444f)) {
            this.f10447i.setText(this.f10444f);
        }
        if (!TextUtils.isEmpty(this.f10441c)) {
            this.f10451m.setText(this.f10441c);
        }
    }

    private void m14104d() {
        this.f10447i.setOnFocusChangeListener(this);
        this.f10447i.addTextChangedListener(this);
        this.f10448j.setOnFocusChangeListener(this);
        this.f10449k.setOnFocusChangeListener(this);
        this.f10450l.setOnFocusChangeListener(this);
        this.f10452n.setOnFocusChangeListener(this);
        this.f10451m.setOnFocusChangeListener(this);
        this.f10446h.setOnFocusChangeListener(this);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        EventBus.m3a().m17b(this);
    }

    private void m14094a(View view) {
        ((FloatLabelLayout) view.getParent()).m14797a(true);
    }

    public void onFocusChange(View view, boolean z) {
        m14094a(view);
        if (view.hasFocus()) {
            if (view.getId() == R.id.password_txt) {
                m14100b(getActivity().getString(R.string.password_letters_numbers));
            }
            m14108g();
            return;
        }
        String obj;
        switch (view.getId()) {
            case R.id.email_txt:
                if (Utils.m14913b(this.f10452n.getText().toString().trim())) {
                    m14107f();
                } else {
                    m14096a(getActivity().getString(R.string.invalid_email_id_hint));
                }
                m14108g();
            case R.id.password_txt:
                m14105e();
                obj = this.f10448j.getText().toString();
                if (Utils.m14924g(obj)) {
                    PasswordStrength.PasswordStrength a = this.f10459u.m13410a(obj);
                    if (a != PasswordStrength.PasswordStrength.WEAK) {
                        m14108g();
                        return;
                    } else if (a.f9450d) {
                        m14096a(getActivity().getString(R.string.password_too_simple));
                        m14114m();
                        return;
                    } else {
                        m14096a(getActivity().getString(R.string.password_letters_numbers));
                        m14115n();
                        return;
                    }
                }
                m14096a(getActivity().getString(R.string.invalid_password_hint));
            case R.id.re_enter_password_txt:
                obj = this.f10448j.getText().toString();
                String obj2 = this.f10449k.getText().toString();
                if (!Utils.m14918d(obj2)) {
                    m14096a(getActivity().getString(R.string.invalid_password_hint));
                } else if (Utils.m14911a(obj, obj2)) {
                    m14107f();
                    m14108g();
                } else {
                    m14096a(getActivity().getString(R.string.password_match_failed_hint));
                }
            case R.id.name_txt:
                if (Utils.m14916c(this.f10450l.getText().toString())) {
                    m14107f();
                } else {
                    m14096a(getActivity().getString(R.string.invalid_name_hint));
                }
                m14108g();
            case R.id.phone_number_txt:
                if (Utils.m14910a(this.f10447i.getText().toString())) {
                    m14107f();
                    m14108g();
                    return;
                }
                m14096a(getActivity().getString(R.string.invalid_number_hint));
            case R.id.referral_code_txt:
                m14108g();
            default:
                OLog.m13318e("onFocus changed on unknown field", new Object[0]);
        }
    }

    private void m14105e() {
        this.f10454p.setBackgroundColor(getResources().getColor(R.color.ola_red_dark));
        this.f10454p.setTextColor(getResources().getColor(R.color.ola_white));
        this.f10454p.setText(Trace.NULL);
        this.f10454p.setVisibility(8);
    }

    private void m14107f() {
        this.f10454p.setText(Trace.NULL);
        this.f10454p.setVisibility(8);
    }

    private void m14108g() {
        if (TextUtils.isEmpty(this.f10452n.getText().toString()) || TextUtils.isEmpty(this.f10447i.getText().toString()) || TextUtils.isEmpty(this.f10448j.getText().toString()) || TextUtils.isEmpty(this.f10449k.getText().toString()) || TextUtils.isEmpty(this.f10450l.getText().toString())) {
            this.f10445g.setEnabled(false);
        } else {
            this.f10445g.setEnabled(true);
        }
    }

    private void m14096a(String str) {
        this.f10454p.setVisibility(0);
        this.f10454p.setText(str);
    }

    private void m14100b(String str) {
        this.f10454p.setText(str);
        this.f10454p.setBackgroundColor(getResources().getColor(R.color.ola_green_flurescent));
        this.f10454p.setTextColor(getResources().getColor(R.color.ola_black));
        this.f10454p.setVisibility(0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up:
                if (!this.f10458t) {
                    Sherlock.m13334a("Ins signup clicked");
                    this.f10458t = true;
                    m14109h();
                }
            case R.id.question_mark:
                m14117b();
            default:
        }
    }

    private void m14109h() {
        m14110i();
        m14111j();
        if (m14113l()) {
            this.f10454p.setVisibility(8);
            da d = ((OlaApp) getActivity().getApplication()).m12878a().m13218d();
            if (d.isLocationEnabled()) {
                dd signUpAttemptDetails = d.getSignUpAttemptDetails();
                signUpAttemptDetails.mEnteredEmailId = this.f10452n.getText().toString().trim();
                signUpAttemptDetails.mPassword = EncryptionScheme.m14898a(this.f10448j.getText().toString());
                signUpAttemptDetails.mName = this.f10450l.getText().toString();
                signUpAttemptDetails.mPhoneNumber = this.f10447i.getText().toString();
                signUpAttemptDetails.mReferrerCode = this.f10451m.getText().toString();
                this.f10440b = new ProgressDialog(getActivity(), R.style.TransparentnobgProgressDialog);
                this.f10440b.setIndeterminateDrawable(getActivity().getResources().getDrawable(R.drawable.custom_progress_background));
                this.f10440b.setCancelable(false);
                this.f10440b.show();
                this.f10457s.m13186a(new WeakReference(this.f10460v), this.f10452n.getText().toString().trim(), EncryptionScheme.m14898a(this.f10448j.getText().toString()), this.f10450l.getText().toString(), this.f10447i.getText().toString(), this.f10451m.getText().toString(), f10435a);
                return;
            }
            return;
        }
        this.f10458t = false;
        this.f10452n.clearFocus();
    }

    private void m14110i() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        inputMethodManager.hideSoftInputFromWindow(this.f10452n.getWindowToken(), 0);
        inputMethodManager.hideSoftInputFromWindow(this.f10448j.getWindowToken(), 0);
        inputMethodManager.hideSoftInputFromWindow(this.f10449k.getWindowToken(), 0);
        inputMethodManager.hideSoftInputFromWindow(this.f10447i.getWindowToken(), 0);
        inputMethodManager.hideSoftInputFromWindow(this.f10450l.getWindowToken(), 0);
        inputMethodManager.hideSoftInputFromWindow(this.f10451m.getWindowToken(), 0);
    }

    private void m14111j() {
        Map hashMap = new HashMap();
        hashMap.put("Email status", !TextUtils.isEmpty(this.f10452n.getText().toString()) ? "email provided" : "email not provided");
        hashMap.put("Name status", !TextUtils.isEmpty(this.f10450l.getText().toString()) ? "name provided" : "name not provided");
        hashMap.put("Referral status", !TextUtils.isEmpty(this.f10451m.getText().toString()) ? "referral provided" : "referral not provided");
        Localytics.tagEvent("Sign up", hashMap);
    }

    private void m14097a(String str, String str2) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        create.setCancelable(false);
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str2);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new SignUpFragment(this, create));
        create.show();
    }

    private void m14112k() {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(getString(R.string.error_signup_existing_user_title));
        ((TextView) inflate.findViewById(R.id.item_message)).setText(getString(R.string.error_signup_existing_user_desc));
        inflate.findViewById(R.id.button_yes).setOnClickListener(new SignUpFragment(this, create));
        inflate.findViewById(R.id.button_no).setOnClickListener(new SignUpFragment(this, create));
        create.show();
    }

    protected void m14117b() {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(getString(R.string.signup_referral_code_hint_title));
        ((TextView) inflate.findViewById(R.id.item_message)).setText(getString(R.string.signup_referral_code_hint_desc));
        inflate.findViewById(R.id.button_ok).setOnClickListener(new SignUpFragment(this, create));
        create.show();
    }

    private boolean m14113l() {
        Activity activity = getActivity();
        m14105e();
        if (this.f10459u.m13410a(this.f10448j.getText().toString()) == PasswordStrength.PasswordStrength.WEAK) {
            m14107f();
            m14096a(activity.getString(R.string.password_letters_numbers));
            return false;
        } else if (!Utils.m14913b(this.f10452n.getText().toString().trim())) {
            this.f10454p.setVisibility(0);
            this.f10454p.setText(activity.getString(R.string.invalid_email_id_hint));
            return false;
        } else if (!Utils.m14911a(this.f10448j.getText().toString(), this.f10449k.getText().toString())) {
            this.f10454p.setVisibility(0);
            this.f10454p.setText(activity.getString(R.string.password_match_failed_hint));
            return false;
        } else if (!Utils.m14916c(this.f10450l.getText().toString())) {
            this.f10454p.setVisibility(0);
            this.f10454p.setText(activity.getString(R.string.invalid_name_hint));
            return false;
        } else if (Utils.m14910a(this.f10447i.getText().toString())) {
            return true;
        } else {
            this.f10454p.setVisibility(0);
            this.f10454p.setText(activity.getString(R.string.invalid_number_hint));
            return false;
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        if (Utils.m14910a(editable.toString())) {
            m14108g();
        } else {
            this.f10445g.setEnabled(false);
        }
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 2) {
            return false;
        }
        if (this.f10458t) {
            return true;
        }
        this.f10458t = true;
        m14109h();
        return true;
    }

    public void onEventMainThread(ah ahVar) {
        OLog.m13311a("Received data connectivity event. Connected? - " + ahVar.isConnected(), new Object[0]);
        if (ahVar.isConnected()) {
            this.f10456r.setVisibility(4);
            m14108g();
            return;
        }
        this.f10456r.setVisibility(0);
        this.f10445g.setEnabled(false);
    }

    private void m14114m() {
        Map hashMap = new HashMap();
        hashMap.put("Password error", "Simple password error");
        Localytics.tagEvent("Error on password entry", hashMap);
    }

    private void m14115n() {
        Map hashMap = new HashMap();
        hashMap.put("Password error", "Incorrect password policy error");
        Localytics.tagEvent("Error on password entry", hashMap);
    }
}
