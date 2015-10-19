package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.ce;
import com.olacabs.customer.p076d.ci;
import com.olacabs.customer.p076d.dp;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.x */
public class ProfileDetailsFragment extends Fragment implements OnClickListener, OnFocusChangeListener, TraceFieldInterface {
    private static final String f11427b;
    private static final JoinPoint f11428r = null;
    private static final JoinPoint f11429s = null;
    private static final JoinPoint f11430t = null;
    private static final JoinPoint f11431u = null;
    protected ProgressDialog f11432a;
    private EditText f11433c;
    private EditText f11434d;
    private EditText f11435e;
    private TextView f11436f;
    private TextView f11437g;
    private TextView f11438h;
    private TextView f11439i;
    private LinearLayout f11440j;
    private ImageView f11441k;
    private ImageView f11442l;
    private ImageView f11443m;
    private DataManager f11444n;
    private InputMethodManager f11445o;
    private aj f11446p;
    private aj f11447q;

    /* renamed from: com.olacabs.customer.ui.x.1 */
    class ProfileDetailsFragment implements aj {
        final /* synthetic */ ProfileDetailsFragment f11423a;

        ProfileDetailsFragment(ProfileDetailsFragment profileDetailsFragment) {
            this.f11423a = profileDetailsFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to get profile details", th);
            if (this.f11423a.isAdded()) {
                this.f11423a.f11432a.cancel();
                this.f11423a.m14877a(this.f11423a.getString(R.string.generic_failure_desc), this.f11423a.getString(R.string.generic_failure_header));
            }
        }

        public void onSuccess(Object obj) {
            if (this.f11423a.isAdded()) {
                this.f11423a.f11432a.cancel();
                ci ciVar = (ci) obj;
                if (ciVar != null && ciVar.isForceLogout()) {
                    new ForceLogoutCommand(true).m13270a(this.f11423a.getActivity());
                }
                if (ciVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    OLog.m13311a("Success fetching profile data", new Object[0]);
                    ce personalDetails = ciVar.getPersonalDetails();
                    this.f11423a.f11433c.setText(personalDetails.getEmail());
                    this.f11423a.f11435e.setText(personalDetails.getName());
                    this.f11423a.f11434d.setText(personalDetails.getMobile());
                    this.f11423a.f11444n.m13209c().setVerified(personalDetails.isVerified());
                    if (personalDetails.isVerified()) {
                        this.f11423a.f11441k.setVisibility(8);
                        this.f11423a.f11442l.setVisibility(0);
                        return;
                    }
                    this.f11423a.f11441k.setVisibility(0);
                    this.f11423a.f11442l.setVisibility(8);
                } else if (ciVar.getStatus().equalsIgnoreCase("FAILURE")) {
                    OLog.m13311a("Failed fetching profile data", new Object[0]);
                    this.f11423a.m14877a(ciVar.getText(), this.f11423a.getString(R.string.generic_failure_header));
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.x.2 */
    class ProfileDetailsFragment implements aj {
        final /* synthetic */ ProfileDetailsFragment f11424a;

        ProfileDetailsFragment(ProfileDetailsFragment profileDetailsFragment) {
            this.f11424a = profileDetailsFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to update profile details", th);
            this.f11424a.f11432a.cancel();
            this.f11424a.m14877a(this.f11424a.getString(R.string.generic_failure_desc), this.f11424a.getString(R.string.generic_failure_header));
        }

        public void onSuccess(Object obj) {
            this.f11424a.f11432a.cancel();
            dp dpVar = (dp) obj;
            if (!this.f11424a.isAdded() && this.f11424a.getActivity() != null) {
                return;
            }
            String verificationId;
            if (dpVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13311a("Update profile data success", new Object[0]);
                verificationId = dpVar.getVerificationId();
                if (dpVar.isVerificationFlow()) {
                    Intent intent = new Intent(this.f11424a.getActivity(), MobileVerificationActivity.class);
                    intent.putExtra("verification_id", verificationId);
                    intent.putExtra(Constants.BUNDLE_TYPE, "update");
                    intent.putExtra(Constants.BUNDLE_NAME, this.f11424a.f11435e.getText().toString());
                    intent.putExtra(dt.USER_PHONE_KEY, this.f11424a.f11434d.getText().toString());
                    this.f11424a.startActivity(intent);
                    return;
                }
                Localytics.tagEvent("Name changed");
                dt c = this.f11424a.f11444n.m13209c();
                c.setName(this.f11424a.f11435e.getText().toString());
                c.setPhoneNumber(this.f11424a.f11434d.getText().toString());
                ((MainActivity) this.f11424a.getActivity()).m13609f();
                this.f11424a.m14877a(dpVar.getText() == null ? this.f11424a.getString(R.string.profile_update_successful_desc) : dpVar.getText(), this.f11424a.getString(R.string.success));
            } else if (dpVar.getStatus().equalsIgnoreCase("FAILURE")) {
                String string;
                OLog.m13311a("Update profile data failure", new Object[0]);
                if (dpVar.getHeader() == null || dpVar.getText() == null) {
                    string = this.f11424a.getString(R.string.generic_failure_desc);
                    verificationId = this.f11424a.getString(R.string.failure);
                } else {
                    string = dpVar.getText();
                    verificationId = dpVar.getHeader();
                }
                if (dpVar.getReason().equals("INVALID_PARAMETERS")) {
                    string = this.f11424a.getString(R.string.profile_update_failed_desc);
                }
                this.f11424a.m14877a(string, verificationId);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.x.3 */
    class ProfileDetailsFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f11425a;
        final /* synthetic */ ProfileDetailsFragment f11426b;

        ProfileDetailsFragment(ProfileDetailsFragment profileDetailsFragment, AlertDialog alertDialog) {
            this.f11426b = profileDetailsFragment;
            this.f11425a = alertDialog;
        }

        public void onClick(View view) {
            this.f11425a.dismiss();
        }
    }

    private static void m14885e() {
        Factory factory = new Factory("ProfileDetailsFragment.java", ProfileDetailsFragment.class);
        f11428r = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.ProfileDetailsFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 180);
        f11429s = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.ProfileDetailsFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 192);
        f11430t = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.ProfileDetailsFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 219);
        f11431u = factory.m15070a("method-execution", factory.m15071a("1", "onPause", "com.olacabs.customer.ui.ProfileDetailsFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 309);
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
        ProfileDetailsFragment.m14885e();
        f11427b = ProfileDetailsFragment.class.getSimpleName();
    }

    public static ProfileDetailsFragment m14873a() {
        return new ProfileDetailsFragment();
    }

    public ProfileDetailsFragment() {
        this.f11446p = new ProfileDetailsFragment(this);
        this.f11447q = new ProfileDetailsFragment(this);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("x");
        try {
            TraceMachine.enterMethod(this._nr_trace, "x#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "x#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f11428r, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f11444n = ((OlaApp) getActivity().getApplication()).m12878a();
        Localytics.tagScreen("User Profile");
        this.f11445o = (InputMethodManager) getActivity().getSystemService("input_method");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "x#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "x#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f11429s, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        OLog.m13311a("onCreateView+", new Object[0]);
        View inflate = layoutInflater.inflate(R.layout.fragment_profile_update, viewGroup, false);
        this.f11433c = (EditText) inflate.findViewById(R.id.profile_update_email_txt);
        this.f11433c.setEnabled(false);
        this.f11435e = (EditText) inflate.findViewById(R.id.profile_update_name_txt);
        this.f11434d = (EditText) inflate.findViewById(R.id.profile_update_phone_number_txt);
        this.f11437g = (TextView) inflate.findViewById(R.id.profile_update_pwd_txt);
        this.f11437g.setOnClickListener(this);
        this.f11443m = (ImageView) inflate.findViewById(R.id.profile_update_menu_button);
        this.f11443m.setOnClickListener(this);
        this.f11436f = (TextView) inflate.findViewById(R.id.errorText);
        this.f11441k = (ImageView) inflate.findViewById(R.id.verify_image);
        this.f11442l = (ImageView) inflate.findViewById(R.id.verify_edit_image);
        this.f11441k.setOnClickListener(this);
        this.f11440j = (LinearLayout) inflate.findViewById(R.id.profile_update_cp_layout);
        this.f11440j.setOnClickListener(this);
        this.f11439i = (TextView) inflate.findViewById(R.id.button_save);
        this.f11439i.setOnClickListener(this);
        this.f11438h = (TextView) inflate.findViewById(R.id.logout);
        this.f11438h.setOnClickListener(this);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f11430t, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            if (this.f11444n.m13209c().isVerified()) {
                this.f11442l.setVisibility(0);
            } else {
                this.f11441k.setVisibility(0);
                this.f11442l.setVisibility(8);
            }
            m14879b();
        } finally {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    private void m14879b() {
        this.f11432a = new ProgressDialog(getActivity(), R.style.TransparentProgressDialog);
        this.f11432a.setIndeterminateDrawable(getActivity().getResources().getDrawable(R.drawable.custom_progress_background));
        this.f11432a.setCancelable(false);
        this.f11432a.show();
        this.f11444n.m13220d(new WeakReference(this.f11446p), f11427b);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_update_menu_button:
                this.f11445o.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                ((MainActivity) getActivity()).m13607d();
            case R.id.button_save:
                ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.f11439i.getWindowToken(), 0);
                m14881c();
            case R.id.logout:
                Localytics.tagEvent("Sign out clicked");
                ((MainActivity) getActivity()).m13603b();
            case R.id.profile_update_cp_layout:
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
            case R.id.verify_image:
                m14881c();
            default:
                OLog.m13318e("Click on unknown view", new Object[0]);
        }
    }

    private void m14881c() {
        boolean isEmpty = TextUtils.isEmpty(this.f11434d.getText().toString());
        boolean isEmpty2 = TextUtils.isEmpty(this.f11435e.getText().toString());
        boolean a = Utils.m14910a(this.f11434d.getText().toString());
        this.f11432a = new ProgressDialog(getActivity(), R.style.TransparentProgressDialog);
        this.f11432a.setIndeterminateDrawable(getActivity().getResources().getDrawable(R.drawable.custom_progress_background));
        this.f11432a.setCancelable(false);
        this.f11432a.show();
        if (isEmpty2 && isEmpty) {
            this.f11432a.cancel();
            m14877a(getString(R.string.name_mobile_required), getString(R.string.title_input_missing));
        } else if (isEmpty2) {
            this.f11432a.cancel();
            m14877a(getString(R.string.name_required), getString(R.string.title_input_missing));
        } else if (isEmpty) {
            this.f11432a.cancel();
            m14877a(getString(R.string.mobile_number_required), getString(R.string.title_input_missing));
        } else if (a) {
            this.f11444n.m13204b(new WeakReference(this.f11447q), this.f11435e.getText().toString(), this.f11434d.getText().toString(), f11427b);
        } else {
            this.f11432a.cancel();
            m14877a(getString(R.string.invalid_mobile_number), getString(R.string.title_invalid_input));
        }
    }

    public void onFocusChange(View view, boolean z) {
        m14874a(view);
    }

    public void onPause() {
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f11431u, (Object) this, (Object) this));
        super.onPause();
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    private void m14874a(View view) {
        switch (view.getId()) {
            case R.id.profile_update_name_txt:
                if (Utils.m14916c(this.f11435e.getText().toString())) {
                    m14883d();
                } else {
                    m14876a(getActivity().getString(R.string.invalid_name_hint));
                }
            default:
        }
    }

    private void m14883d() {
        this.f11436f.setText(Trace.NULL);
        this.f11436f.setVisibility(8);
    }

    private void m14876a(String str) {
        this.f11436f.setVisibility(0);
        this.f11436f.setText(str);
    }

    private void m14877a(String str, String str2) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str2);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new ProfileDetailsFragment(this, create));
        create.show();
    }
}
