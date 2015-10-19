package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.AuthErrorCodesResponse;
import com.olacabs.customer.p076d.AuthTokenSession;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.bp;
import com.olacabs.customer.p076d.ca;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.ui.widgets.FloatLabelLayout;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
/* renamed from: com.olacabs.customer.ui.r */
public class LoginFragment extends Fragment implements OnClickListener, OnFocusChangeListener, OnEditorActionListener, TraceFieldInterface {
    public static final String f11004a;
    private static final JoinPoint f11005m = null;
    private static final JoinPoint f11006n = null;
    private static final JoinPoint f11007o = null;
    private static final JoinPoint f11008p = null;
    protected ProgressDialog f11009b;
    private TextView f11010c;
    private TextView f11011d;
    private EditText f11012e;
    private AutoCompleteTextView f11013f;
    private Button f11014g;
    private LinearLayout f11015h;
    private DataManager f11016i;
    private boolean f11017j;
    private aj f11018k;
    private aj f11019l;

    /* renamed from: com.olacabs.customer.ui.r.1 */
    class LoginFragment implements aj {
        final /* synthetic */ LoginFragment f10996a;

        LoginFragment(LoginFragment loginFragment) {
            this.f10996a = loginFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Login failed", th);
            this.f10996a.f11009b.cancel();
            String str = Trace.NULL;
            str = Trace.NULL;
            Sherlock.m13336a("Ins Login", (VolleyError) th, th.getMessage(), true);
            VolleyError volleyError = (VolleyError) th;
            String string = this.f10996a.getString(R.string.generic_failure_desc);
            String string2 = this.f10996a.getString(R.string.generic_failure_header);
            if (volleyError == null || volleyError.f464a == null) {
                this.f10996a.m14545a(string, string2);
                return;
            }
            byte[] bArr = volleyError.f464a.f498b;
            if (bArr != null) {
                try {
                    AuthErrorCodesResponse authErrorCodesResponse = (AuthErrorCodesResponse) new Gson().m12343a(new String(bArr), AuthErrorCodesResponse.class);
                    if (authErrorCodesResponse != null && Utils.m14924g(authErrorCodesResponse.getText()) && Utils.m14924g(authErrorCodesResponse.getHeader())) {
                        this.f10996a.m14545a(authErrorCodesResponse.getText(), authErrorCodesResponse.getHeader());
                    } else {
                        this.f10996a.m14545a(string, string2);
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    this.f10996a.m14545a(string, string2);
                }
            }
        }

        public void onSuccess(Object obj) {
            this.f10996a.f11009b.cancel();
            Localytics.tagEvent("Login successful");
            bp bpVar = (bp) obj;
            if (bpVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13311a("Login successful", new Object[0]);
                this.f10996a.f11016i.m13209c().updateUserInfoOnLogin(bpVar.getUserId(), bpVar.getReferralCode(), bpVar.getPhone(), bpVar.getOlaMoneyBalance(), this.f10996a.f11013f.getText().toString().trim(), true, bpVar.getName());
                AuthTokenSession authTokenExpireSession = bpVar.getAuthTokenExpireSession();
                if (authTokenExpireSession != null && authTokenExpireSession.isValid()) {
                    da d = this.f10996a.f11016i.m13218d();
                    OLog.m13313b("auth token is :" + authTokenExpireSession.getAuthToken(), new Object[0]);
                    d.setAuthSessionId(authTokenExpireSession.getAuthToken());
                    OLog.m13313b("refresh auth token is :" + authTokenExpireSession.getRefreshToken(), new Object[0]);
                    d.setAuthRefreshToken(authTokenExpireSession.getRefreshToken());
                    OLog.m13313b("refresh auth token expiry :" + authTokenExpireSession.getExpiryFromNow(), new Object[0]);
                    d.setAuthSessionExpiry(authTokenExpireSession.getExpiryFromNow() + System.currentTimeMillis());
                }
                ((SplashActivity) this.f10996a.getActivity()).m13902c();
                ((SplashActivity) this.f10996a.getActivity()).m13899b();
                Localytics.tagEvent("Logged in successfully");
            } else if (bpVar.getStatus().equalsIgnoreCase("FAILURE")) {
                String reason;
                String text = bpVar.getText();
                OLog.m13311a("Login failed - " + bpVar.getReason(), new Object[0]);
                if (TextUtils.isEmpty(bpVar.getText()) && TextUtils.isEmpty(bpVar.getReason())) {
                    reason = bpVar.getReason();
                    if (reason.equals("INVALID_EMAIL_ID")) {
                        text = this.f10996a.getString(R.string.invalid_email_id_hint);
                    } else if (reason.equals("INVALID_USER_PASSWORD")) {
                        text = this.f10996a.getString(R.string.invalid_email_or_password_hint);
                    } else if (reason.equals("UNKNOWN")) {
                        text = this.f10996a.getString(R.string.generic_failure_desc);
                    }
                }
                Sherlock.m13336a("Ins Login", null, text, true);
                reason = bpVar.getHeader();
                if (TextUtils.isEmpty(reason)) {
                    reason = this.f10996a.getString(R.string.generic_failure_header);
                }
                this.f10996a.m14545a(text, reason);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.r.2 */
    class LoginFragment implements aj {
        final /* synthetic */ LoginFragment f10997a;

        LoginFragment(LoginFragment loginFragment) {
            this.f10997a = loginFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Pwd reset failed", th);
            this.f10997a.f11009b.cancel();
            this.f10997a.m14545a(this.f10997a.getString(R.string.connection_time_out_error_desc), this.f10997a.getString(R.string.connection_time_out_error_title));
        }

        public void onSuccess(Object obj) {
            this.f10997a.f11009b.cancel();
            ca caVar = (ca) obj;
            if (caVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13311a("Pwd reset succeeded", new Object[0]);
                this.f10997a.m14545a(this.f10997a.getString(R.string.password_reset_success_diag_desc) + this.f10997a.f11013f.getText().toString(), this.f10997a.getString(R.string.password_reset_success_diag_title));
            } else if (caVar.getStatus().equalsIgnoreCase("FAILURE")) {
                OLog.m13318e("Pwd reset failed", new Object[0]);
                if (caVar.getReason().equalsIgnoreCase("USER_NOT_FOUND")) {
                    this.f10997a.m14554f();
                } else {
                    this.f10997a.m14545a(this.f10997a.getString(R.string.connection_time_out_error_desc), this.f10997a.getString(R.string.connection_time_out_error_title));
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.r.3 */
    class LoginFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10998a;
        final /* synthetic */ LoginFragment f10999b;

        LoginFragment(LoginFragment loginFragment, AlertDialog alertDialog) {
            this.f10999b = loginFragment;
            this.f10998a = alertDialog;
        }

        public void onClick(View view) {
            this.f10998a.dismiss();
            this.f10999b.f11015h.setVisibility(4);
            this.f10999b.getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(aj.f10435a).replace(R.id.container_sign_up, aj.m14093a()).commit();
        }
    }

    /* renamed from: com.olacabs.customer.ui.r.4 */
    class LoginFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f11000a;
        final /* synthetic */ LoginFragment f11001b;

        LoginFragment(LoginFragment loginFragment, AlertDialog alertDialog) {
            this.f11001b = loginFragment;
            this.f11000a = alertDialog;
        }

        public void onClick(View view) {
            this.f11000a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.r.5 */
    class LoginFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f11002a;
        final /* synthetic */ LoginFragment f11003b;

        LoginFragment(LoginFragment loginFragment, AlertDialog alertDialog) {
            this.f11003b = loginFragment;
            this.f11002a = alertDialog;
        }

        public void onClick(View view) {
            this.f11002a.dismiss();
            this.f11003b.f11017j = false;
        }
    }

    private static void m14558j() {
        Factory factory = new Factory("LoginFragment.java", LoginFragment.class);
        f11005m = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.LoginFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), (int) HttpStatus.SC_PARTIAL_CONTENT);
        f11006n = factory.m15070a("method-execution", factory.m15071a("1", "onStart", "com.olacabs.customer.ui.LoginFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 215);
        f11007o = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.LoginFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 221);
        f11008p = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.LoginFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 231);
    }

    static {
        LoginFragment.m14558j();
        f11004a = LoginFragment.class.getSimpleName();
    }

    public static LoginFragment m14541a() {
        return new LoginFragment();
    }

    public LoginFragment() {
        this.f11017j = false;
        this.f11018k = new LoginFragment(this);
        this.f11019l = new LoginFragment(this);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("r");
        try {
            TraceMachine.enterMethod(this._nr_trace, "r#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "r#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f11005m, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f11016i = ((OlaApp) getActivity().getApplication()).m12878a();
        Localytics.tagScreen("Login");
        TraceMachine.exitMethod();
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f11006n, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f11007o, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            if (!Utils.m14909a(getActivity())) {
                this.f11015h.setVisibility(0);
            }
            ActivityTraceAspect.m12823a().m12827c(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "r#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "r#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f11008p, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        OLog.m13311a("onCreateView +", new Object[0]);
        View inflate = layoutInflater.inflate(R.layout.fragment_login, viewGroup, false);
        this.f11010c = (TextView) inflate.findViewById(R.id.login);
        this.f11010c.setOnClickListener(this);
        this.f11011d = (TextView) inflate.findViewById(R.id.login_errorText);
        this.f11013f = (AutoCompleteTextView) inflate.findViewById(R.id.login_email_txt);
        this.f11013f.requestFocus();
        this.f11013f.setOnFocusChangeListener(this);
        this.f11013f.setAdapter(new ArrayAdapter(getActivity(), R.layout.item_simple_dropdown, Utils.m14919e(getActivity())));
        this.f11012e = (EditText) inflate.findViewById(R.id.login_password_txt);
        this.f11012e.setOnEditorActionListener(this);
        this.f11012e.setOnFocusChangeListener(this);
        this.f11014g = (Button) inflate.findViewById(R.id.forgot_pwd_button);
        this.f11014g.setOnClickListener(this);
        this.f11015h = (LinearLayout) inflate.findViewById(R.id.view_no_network_state);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
    }

    public void onDetach() {
        super.onDetach();
        m14559b();
    }

    public void onEventMainThread(ah ahVar) {
        OLog.m13311a("Received data connectivity event. Connected? - " + ahVar.isConnected(), new Object[0]);
        if (ahVar.isConnected()) {
            this.f11015h.setVisibility(4);
            m14552d();
            return;
        }
        this.f11015h.setVisibility(0);
        this.f11010c.setEnabled(false);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                if (!this.f11017j) {
                    Sherlock.m13334a("Ins Login");
                    m14549c();
                }
            case R.id.forgot_pwd_button:
                m14553e();
            default:
        }
    }

    private void m14549c() {
        Localytics.tagEvent("Login clicked");
        if (m14555g()) {
            this.f11017j = true;
            m14557i();
            this.f11016i.m13181a(new WeakReference(this.f11018k), this.f11013f.getText().toString().trim(), this.f11012e.getText().toString(), f11004a);
            return;
        }
        this.f11013f.clearFocus();
        this.f11017j = false;
    }

    private void m14552d() {
        if (TextUtils.isEmpty(this.f11013f.getText().toString().trim())) {
            this.f11010c.setEnabled(false);
        } else {
            this.f11010c.setEnabled(true);
        }
    }

    private void m14553e() {
        OLog.m13311a("Forgot password", new Object[0]);
        Localytics.tagEvent("Forgot password clicked");
        String trim = this.f11013f.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            m14545a(getActivity().getString(R.string.empty_email_id_warning_desc), getActivity().getString(R.string.empty_email_id_warning_title));
        } else if (Utils.m14913b(trim)) {
            m14557i();
            this.f11016i.m13180a(new WeakReference(this.f11019l), this.f11013f.getText().toString().trim(), f11004a);
        } else {
            this.f11011d.setText(getActivity().getString(R.string.invalid_email_id_hint));
            this.f11011d.setVisibility(0);
        }
    }

    private void m14554f() {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(getString(R.string.offer_signup_dialog_title));
        ((TextView) inflate.findViewById(R.id.item_message)).setText(String.format(getString(R.string.offer_signup_dialog_desc), new Object[]{this.f11013f.getText().toString()}));
        inflate.findViewById(R.id.button_yes).setOnClickListener(new LoginFragment(this, create));
        inflate.findViewById(R.id.button_no).setOnClickListener(new LoginFragment(this, create));
        create.show();
    }

    private void m14545a(String str, String str2) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        create.setCancelable(false);
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str2);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new LoginFragment(this, create));
        create.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
        create.show();
    }

    private boolean m14555g() {
        boolean b = Utils.m14913b(this.f11013f.getText().toString().trim());
        boolean d = Utils.m14918d(this.f11012e.getText().toString());
        if (b && d) {
            this.f11011d.setVisibility(4);
            return true;
        }
        if (b) {
            m14544a(getActivity().getString(R.string.invalid_password_hint));
        } else {
            m14544a(getActivity().getString(R.string.invalid_email_id_hint));
        }
        return false;
    }

    private void m14542a(View view) {
        ((FloatLabelLayout) view.getParent()).m14797a(true);
    }

    public void onFocusChange(View view, boolean z) {
        m14542a(view);
        m14548b(view);
    }

    private void m14548b(View view) {
        switch (view.getId()) {
            case R.id.login_password_txt:
                m14556h();
            default:
        }
    }

    private void m14556h() {
        String trim = this.f11013f.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            this.f11010c.setEnabled(false);
            m14544a(getActivity().getString(R.string.empty_login_fields_hint));
        } else if (Utils.m14913b(trim)) {
            this.f11011d.setVisibility(8);
            m14552d();
        } else {
            this.f11010c.setEnabled(false);
            m14544a(getActivity().getString(R.string.invalid_email_id_hint));
        }
    }

    private void m14544a(String str) {
        this.f11011d.setVisibility(0);
        this.f11011d.setText(str);
    }

    protected void m14559b() {
        if (!isDetached()) {
            FragmentManager childFragmentManager = getChildFragmentManager();
            if (childFragmentManager.getBackStackEntryCount() > 0) {
                childFragmentManager.popBackStack(childFragmentManager.getBackStackEntryAt(0).getId(), 1);
            }
        }
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 2) {
            return false;
        }
        if (!this.f11017j) {
            m14549c();
        }
        return true;
    }

    private void m14557i() {
        this.f11009b = new ProgressDialog(getActivity(), R.style.TransparentnobgProgressDialog);
        this.f11009b.setIndeterminateDrawable(getActivity().getResources().getDrawable(R.drawable.custom_progress_background));
        this.f11009b.setCancelable(false);
        this.f11009b.show();
    }
}
