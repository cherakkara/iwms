package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.olacabs.customer.p076d.UpdateECResponse;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.am;
import com.olacabs.customer.p076d.at;
import com.olacabs.customer.p076d.ci;
import com.olacabs.customer.p076d.cm;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
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
/* renamed from: com.olacabs.customer.ui.v */
public class OlaSafetyFragment extends Fragment implements OnClickListener, TraceFieldInterface {
    private static final JoinPoint f11163D = null;
    private static final JoinPoint f11164E = null;
    private static final JoinPoint f11165F = null;
    private static final JoinPoint f11166G = null;
    private static final String f11167u;
    private aj f11168A;
    private aj f11169B;
    private aj f11170C;
    AlertDialog f11171a;
    ProgressDialog f11172b;
    EditText f11173c;
    EditText f11174d;
    EditText f11175e;
    TextView f11176f;
    TextView f11177g;
    TextView f11178h;
    TextView f11179i;
    TextView f11180j;
    TextView f11181k;
    ImageView f11182l;
    ImageView f11183m;
    ImageView f11184n;
    boolean f11185o;
    boolean f11186p;
    boolean f11187q;
    boolean f11188r;
    CheckBox f11189s;
    SharedPreferences f11190t;
    private MainActivity f11191v;
    private DataManager f11192w;
    private ViewStub f11193x;
    private RelativeLayout f11194y;
    private aj f11195z;

    /* renamed from: com.olacabs.customer.ui.v.1 */
    class OlaSafetyFragment implements aj {
        final /* synthetic */ OlaSafetyFragment f11154a;

        OlaSafetyFragment(OlaSafetyFragment olaSafetyFragment) {
            this.f11154a = olaSafetyFragment;
        }

        public void onFailure(Throwable th) {
            if (this.f11154a.isAdded()) {
                this.f11154a.m14691d();
                if (Utils.m14909a(this.f11154a.getActivity().getApplicationContext())) {
                    this.f11154a.m14670a(this.f11154a.getActivity().getString(R.string.generic_failure_header), this.f11154a.getActivity().getString(R.string.generic_failure_desc));
                } else {
                    this.f11154a.m14689b();
                }
            }
        }

        public void onSuccess(Object obj) {
            if (this.f11154a.isAdded()) {
                this.f11154a.m14691d();
                cm cmVar = (cm) obj;
                this.f11154a.m14670a(cmVar.getHeader(), cmVar.getText());
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.v.2 */
    class OlaSafetyFragment implements OnClickListener {
        final /* synthetic */ OlaSafetyFragment f11155a;

        OlaSafetyFragment(OlaSafetyFragment olaSafetyFragment) {
            this.f11155a = olaSafetyFragment;
        }

        public void onClick(View view) {
            this.f11155a.f11171a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.v.3 */
    class OlaSafetyFragment implements aj {
        final /* synthetic */ OlaSafetyFragment f11156a;

        OlaSafetyFragment(OlaSafetyFragment olaSafetyFragment) {
            this.f11156a = olaSafetyFragment;
        }

        public void onFailure(Throwable th) {
            if (this.f11156a.isAdded()) {
                this.f11156a.m14691d();
                if (Utils.m14909a(this.f11156a.getActivity().getApplicationContext())) {
                    this.f11156a.m14670a(this.f11156a.getActivity().getString(R.string.generic_failure_header), this.f11156a.getActivity().getString(R.string.generic_failure_desc));
                } else {
                    this.f11156a.m14689b();
                }
            }
        }

        public void onSuccess(Object obj) {
            if (this.f11156a.isAdded()) {
                this.f11156a.m14691d();
                UpdateECResponse updateECResponse = (UpdateECResponse) obj;
                if (updateECResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                    this.f11156a.m14670a(updateECResponse.getHeader(), updateECResponse.getText());
                    this.f11156a.m14662a(((UpdateECResponse) obj).getEmergencyContact());
                    return;
                }
                this.f11156a.m14670a(updateECResponse.getHeader(), updateECResponse.getText());
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.v.4 */
    class OlaSafetyFragment implements aj {
        final /* synthetic */ OlaSafetyFragment f11157a;

        OlaSafetyFragment(OlaSafetyFragment olaSafetyFragment) {
            this.f11157a = olaSafetyFragment;
        }

        public void onFailure(Throwable th) {
            if (this.f11157a.isAdded()) {
                this.f11157a.m14691d();
                this.f11157a.m14671a(false);
                if (Utils.m14909a(this.f11157a.getActivity().getApplicationContext())) {
                    this.f11157a.m14670a(this.f11157a.getActivity().getString(R.string.generic_failure_header), this.f11157a.getActivity().getString(R.string.generic_failure_desc));
                } else {
                    this.f11157a.m14689b();
                }
            }
        }

        public void onSuccess(Object obj) {
            if (this.f11157a.isAdded()) {
                this.f11157a.m14691d();
                this.f11157a.m14674b(obj);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.v.5 */
    class OlaSafetyFragment implements aj {
        final /* synthetic */ OlaSafetyFragment f11158a;

        OlaSafetyFragment(OlaSafetyFragment olaSafetyFragment) {
            this.f11158a = olaSafetyFragment;
        }

        public void onFailure(Throwable th) {
            if (this.f11158a.isAdded()) {
                OLog.m13318e("Failed to fetch profile details", new Object[0]);
                this.f11158a.m14691d();
                if (Utils.m14909a(this.f11158a.getActivity().getApplicationContext())) {
                    this.f11158a.m14670a(this.f11158a.getActivity().getString(R.string.generic_failure_header), this.f11158a.getActivity().getString(R.string.generic_failure_desc));
                } else {
                    this.f11158a.m14689b();
                }
            }
        }

        public void onSuccess(Object obj) {
            if (this.f11158a.isAdded()) {
                this.f11158a.m14691d();
                this.f11158a.m14668a(obj);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.v.6 */
    class OlaSafetyFragment implements TextWatcher {
        final /* synthetic */ OlaSafetyFragment f11159a;

        OlaSafetyFragment(OlaSafetyFragment olaSafetyFragment) {
            this.f11159a = olaSafetyFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f11159a.m14686q();
            this.f11159a.f11182l.setVisibility(8);
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.v.7 */
    class OlaSafetyFragment implements TextWatcher {
        final /* synthetic */ OlaSafetyFragment f11160a;

        OlaSafetyFragment(OlaSafetyFragment olaSafetyFragment) {
            this.f11160a = olaSafetyFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f11160a.m14686q();
            this.f11160a.f11183m.setVisibility(8);
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.v.8 */
    class OlaSafetyFragment implements TextWatcher {
        final /* synthetic */ OlaSafetyFragment f11161a;

        OlaSafetyFragment(OlaSafetyFragment olaSafetyFragment) {
            this.f11161a = olaSafetyFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f11161a.m14686q();
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.v.9 */
    class OlaSafetyFragment implements OnClickListener {
        final /* synthetic */ OlaSafetyFragment f11162a;

        OlaSafetyFragment(OlaSafetyFragment olaSafetyFragment) {
            this.f11162a = olaSafetyFragment;
        }

        public void onClick(View view) {
            this.f11162a.m14687r();
            this.f11162a.f11171a.dismiss();
        }
    }

    private static void m14688s() {
        Factory factory = new Factory("OlaSafetyFragment.java", OlaSafetyFragment.class);
        f11163D = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.OlaSafetyFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 220);
        f11164E = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.OlaSafetyFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 232);
        f11165F = factory.m15070a("method-execution", factory.m15071a("1", "onStart", "com.olacabs.customer.ui.OlaSafetyFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), (int) HttpStatus.SC_UNAUTHORIZED);
        f11166G = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.OlaSafetyFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), (int) HttpStatus.SC_REQUEST_TOO_LONG);
    }

    static {
        OlaSafetyFragment.m14688s();
        f11167u = OlaSafetyFragment.class.getSimpleName();
    }

    public OlaSafetyFragment() {
        this.f11185o = false;
        this.f11186p = false;
        this.f11187q = false;
        this.f11188r = false;
        this.f11195z = new OlaSafetyFragment(this);
        this.f11168A = new OlaSafetyFragment(this);
        this.f11169B = new OlaSafetyFragment(this);
        this.f11170C = new OlaSafetyFragment(this);
    }

    public static OlaSafetyFragment m14660a() {
        return new OlaSafetyFragment();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("v");
        try {
            TraceMachine.enterMethod(this._nr_trace, "v#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "v#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f11163D, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f11191v = (MainActivity) getActivity();
        this.f11190t = PreferenceManager.getDefaultSharedPreferences(this.f11191v);
        this.f11192w = ((OlaApp) getActivity().getApplication()).m12878a();
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "v#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "v#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f11164E, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_ola_saftey, viewGroup, false);
        m14661a(inflate);
        m14677h();
        m14676g();
        m14675f();
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14661a(View view) {
        this.f11194y = (RelativeLayout) view.findViewById(R.id.emergency_contact_layout);
        this.f11184n = (ImageView) view.findViewById(R.id.button_navigation_drawer);
        this.f11184n.setOnClickListener(this);
        this.f11175e = (EditText) view.findViewById(R.id.ec_email_txt);
        this.f11173c = (EditText) view.findViewById(R.id.ec_name_txt);
        this.f11174d = (EditText) view.findViewById(R.id.ec_mobile_txt);
        this.f11176f = (TextView) view.findViewById(R.id.ec_save);
        this.f11176f.setOnClickListener(this);
        this.f11176f.setEnabled(false);
        this.f11177g = (TextView) view.findViewById(R.id.errorText);
        this.f11178h = (TextView) view.findViewById(R.id.resend_verification);
        this.f11179i = (TextView) view.findViewById(R.id.footer_text);
        this.f11180j = (TextView) view.findViewById(R.id.remove_details);
        this.f11180j.setPaintFlags(this.f11180j.getPaintFlags() | 8);
        this.f11180j.setOnClickListener(this);
        this.f11181k = (TextView) view.findViewById(R.id.help_text);
        this.f11183m = (ImageView) view.findViewById(R.id.ec_email_txt_alert);
        this.f11182l = (ImageView) view.findViewById(R.id.ec_mobile_txt_alert);
        this.f11178h.setOnClickListener(this);
        this.f11193x = (ViewStub) view.findViewById(R.id.stub_sad_error);
        this.f11189s = (CheckBox) view.findViewById(R.id.ec_checkbox);
        this.f11189s.setOnClickListener(this);
        this.f11174d.setFilters(new InputFilter[]{new LengthFilter(10)});
        this.f11174d.setInputType(3);
    }

    private void m14675f() {
        if (Utils.m14909a(getActivity().getApplicationContext())) {
            m14692e();
            this.f11192w.m13220d(new WeakReference(this.f11170C), f11167u);
            return;
        }
        m14689b();
    }

    private void m14668a(Object obj) {
        ci ciVar = (ci) obj;
        if (ciVar != null && ciVar.isForceLogout()) {
            new ForceLogoutCommand(true).m13270a(getActivity());
        }
        if (ciVar.getStatus().equalsIgnoreCase("SUCCESS")) {
            OLog.m13311a("Fetched profile data", new Object[0]);
            m14662a(ciVar.getPersonalDetails().getEmergencyContact());
        }
    }

    private void m14662a(at atVar) {
        Editor edit = this.f11190t.edit();
        if (atVar != null) {
            edit.putString(Constants.PREF_EMERGENCY_NAME, atVar.getEmergencyContactName());
            edit.putString(Constants.PREF_EMERGENCY_EMAIL, atVar.getEmergencyEmail());
            edit.putString(Constants.PREF_EMERGENCY_PHONE, atVar.getEmergencyMobile());
            edit.putBoolean(Constants.PREF_EMERGENCY_IS_EMAIL_VERIFIED, atVar.isEmailVerified());
            edit.putBoolean(Constants.PREF_EMERGENCY_IS_PHONE_VERIFIED, atVar.isMobileVerified());
            edit.putBoolean(Constants.PREF_IS_AUTO_SHARE, atVar.isEnableAutoShare());
            this.f11179i.setText(atVar.getFooterText() != null ? atVar.getFooterText() : Trace.NULL);
            this.f11181k.setText(atVar.getStatusText() != null ? atVar.getStatusText() : Trace.NULL);
        }
        edit.apply();
        m14676g();
    }

    private void m14676g() {
        this.f11173c.setText(this.f11190t.getString(Constants.PREF_EMERGENCY_NAME, Trace.NULL));
        this.f11174d.setText(this.f11190t.getString(Constants.PREF_EMERGENCY_PHONE, Trace.NULL));
        this.f11175e.setText(this.f11190t.getString(Constants.PREF_EMERGENCY_EMAIL, Trace.NULL));
        this.f11185o = this.f11190t.getBoolean(Constants.PREF_EMERGENCY_IS_EMAIL_VERIFIED, false);
        this.f11186p = this.f11190t.getBoolean(Constants.PREF_EMERGENCY_IS_PHONE_VERIFIED, false);
        this.f11188r = this.f11190t.getBoolean(Constants.PREF_IS_AUTO_SHARE, false);
        if (!(this.f11185o || TextUtils.isEmpty(this.f11175e.getText().toString()))) {
            this.f11183m.setVisibility(0);
        }
        if (!(this.f11186p || TextUtils.isEmpty(this.f11174d.getText().toString()))) {
            this.f11182l.setVisibility(0);
        }
        if ((this.f11186p || TextUtils.isEmpty(this.f11174d.getText().toString())) && (this.f11185o || TextUtils.isEmpty(this.f11175e.getText().toString()))) {
            this.f11178h.setVisibility(8);
            this.f11179i.setVisibility(8);
        } else {
            this.f11178h.setVisibility(0);
            this.f11179i.setVisibility(0);
        }
        this.f11189s.setChecked(this.f11188r);
        if (this.f11174d.getText().toString().isEmpty() || this.f11173c.getText().toString().isEmpty()) {
            this.f11180j.setVisibility(8);
        } else {
            this.f11180j.setVisibility(0);
        }
        if (!(this.f11191v == null || this.f11191v.m13608e() == null)) {
            this.f11191v.m13608e().m13649a();
        }
        this.f11177g.setVisibility(8);
    }

    private void m14677h() {
        this.f11174d.addTextChangedListener(new OlaSafetyFragment(this));
        this.f11175e.addTextChangedListener(new OlaSafetyFragment(this));
        this.f11173c.addTextChangedListener(new OlaSafetyFragment(this));
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f11165F, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f11166G, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            if (Utils.m14909a(getActivity().getApplicationContext())) {
                m14690c();
            } else {
                m14689b();
            }
            ActivityTraceAspect.m12823a().m12827c(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public void onEvent(ah ahVar) {
        if (ahVar.isConnected()) {
            m14690c();
        } else {
            m14689b();
        }
    }

    public void m14689b() {
        this.f11194y.setVisibility(8);
        this.f11178h.setVisibility(8);
        this.f11179i.setVisibility(8);
        if (this.f11171a != null && this.f11171a.isShowing()) {
            this.f11171a.dismiss();
        }
        m14691d();
        this.f11193x.setVisibility(0);
    }

    public void m14690c() {
        this.f11193x.setVisibility(8);
        this.f11194y.setVisibility(0);
        if ((!this.f11186p && !Trace.NULL.equalsIgnoreCase(this.f11174d.getText().toString())) || (!this.f11185o && !Trace.NULL.equalsIgnoreCase(this.f11175e.getText().toString()))) {
            this.f11178h.setVisibility(0);
            this.f11179i.setVisibility(0);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_navigation_drawer:
                ((InputMethodManager) this.f11191v.getSystemService("input_method")).hideSoftInputFromWindow(this.f11184n.getWindowToken(), 0);
                if (this.f11187q) {
                    this.f11187q = false;
                    m14679j();
                    m14685p();
                    return;
                }
                this.f11191v.m13607d();
            case R.id.ec_save:
                m14681l();
            case R.id.remove_details:
                m14678i();
            case R.id.ec_checkbox:
                if (this.f11189s.isChecked()) {
                    Localytics.tagEvent("Always send ride details checked");
                }
                m14684o();
            case R.id.resend_verification:
                Localytics.tagEvent("Resend Verification Link clicked");
                m14680k();
            default:
        }
    }

    private void m14678i() {
        View inflate = ((LayoutInflater) this.f11191v.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
        this.f11171a = new Builder(this.f11191v).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(Ola.f11508y);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(Ola.f11509z);
        ((TextView) inflate.findViewById(R.id.button_yes)).setText("OK");
        ((TextView) inflate.findViewById(R.id.button_no)).setText("CANCEL");
        inflate.findViewById(R.id.button_yes).setOnClickListener(new OlaSafetyFragment(this));
        inflate.findViewById(R.id.button_no).setOnClickListener(new OnClickListener() {
            final /* synthetic */ OlaSafetyFragment f11153a;

            {
                this.f11153a = r1;
            }

            public void onClick(View view) {
                this.f11153a.f11171a.dismiss();
            }
        });
        this.f11171a.show();
    }

    private void m14679j() {
        this.f11184n.setImageResource(R.drawable.bg_button_nav_menu);
        m14676g();
    }

    private void m14680k() {
        m14692e();
        this.f11192w.m13202b(new WeakReference(this.f11195z), f11167u);
    }

    private void m14681l() {
        if (!this.f11174d.getText().toString().matches("[6-9][0-9]+") || this.f11174d.getText().toString().length() < 10) {
            m14669a(getActivity().getString(R.string.invalid_number_hint));
        } else if (this.f11173c.getText().toString().length() < 1) {
            m14669a(getActivity().getString(R.string.invalid_name_hint));
        } else {
            Localytics.tagEvent("Emergency Mobile added");
            m14682m();
            if (this.f11175e.getText().toString().length() == 0 || Utils.m14913b(this.f11175e.getText().toString())) {
                Localytics.tagEvent("Emergency Email added");
                m14682m();
                m14683n();
            } else {
                m14669a(getActivity().getString(R.string.invalid_email_id_hint));
            }
            ((InputMethodManager) this.f11191v.getSystemService("input_method")).hideSoftInputFromWindow(this.f11176f.getWindowToken(), 0);
        }
    }

    private void m14669a(String str) {
        this.f11177g.setVisibility(0);
        this.f11177g.setText(str);
    }

    private void m14682m() {
        this.f11177g.setText(Trace.NULL);
        this.f11177g.setVisibility(8);
    }

    private void m14683n() {
        m14692e();
        this.f11192w.m13188a(new WeakReference(this.f11168A), this.f11173c.getText().toString(), this.f11174d.getText().toString(), this.f11175e.getText().toString(), this.f11189s.isChecked(), f11167u);
    }

    private void m14670a(String str, String str2) {
        if (this.f11171a == null || !this.f11171a.isShowing()) {
            View inflate = ((LayoutInflater) this.f11191v.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
            this.f11171a = new Builder(this.f11191v).setView(inflate).create();
            ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
            ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
            inflate.findViewById(R.id.button_ok).setOnClickListener(new OlaSafetyFragment(this));
            this.f11171a.show();
        }
    }

    private void m14684o() {
        this.f11187q = true;
        this.f11184n.setImageResource(R.drawable.ic_ride_details_cancel);
        if (this.f11174d.getText().toString().length() < 10 || this.f11173c.getText().toString().length() < 1) {
            this.f11176f.setEnabled(false);
        } else {
            this.f11176f.setEnabled(true);
        }
        this.f11178h.setVisibility(8);
        this.f11179i.setVisibility(8);
    }

    private void m14685p() {
        this.f11187q = false;
        this.f11184n.setImageResource(R.drawable.bg_button_nav_menu);
        this.f11176f.setEnabled(false);
        if ((this.f11186p || Trace.NULL.equalsIgnoreCase(this.f11174d.getText().toString())) && (this.f11185o || Trace.NULL.equalsIgnoreCase(this.f11175e.getText().toString()))) {
            this.f11178h.setVisibility(8);
            this.f11179i.setVisibility(8);
            return;
        }
        this.f11178h.setVisibility(0);
        this.f11179i.setVisibility(0);
    }

    private void m14686q() {
        if (this.f11173c.getText().toString().equals(this.f11190t.getString(Constants.PREF_EMERGENCY_NAME, Trace.NULL)) && this.f11174d.getText().toString().equals(this.f11190t.getString(Constants.PREF_EMERGENCY_PHONE, Trace.NULL)) && this.f11175e.getText().toString().equals(this.f11190t.getString(Constants.PREF_EMERGENCY_EMAIL, Trace.NULL))) {
            m14685p();
        } else {
            m14684o();
        }
    }

    public void m14691d() {
        if (this.f11172b != null && this.f11172b.isShowing()) {
            this.f11172b.dismiss();
        }
    }

    public void m14692e() {
        this.f11172b = new ProgressDialog(this.f11191v, R.style.TransparentProgressDialog);
        this.f11172b.setIndeterminateDrawable(this.f11191v.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f11172b.setCancelable(false);
        if (!this.f11172b.isShowing()) {
            this.f11172b.show();
        }
    }

    private void m14687r() {
        m14692e();
        this.f11192w.m13213c(new WeakReference(this.f11169B), f11167u);
    }

    private void m14674b(Object obj) {
        am amVar = (am) obj;
        if (amVar.getStatus().equalsIgnoreCase("SUCCESS")) {
            Editor edit = this.f11190t.edit();
            edit.putString(Constants.PREF_EMERGENCY_NAME, Trace.NULL);
            edit.putString(Constants.PREF_EMERGENCY_EMAIL, Trace.NULL);
            edit.putString(Constants.PREF_EMERGENCY_PHONE, Trace.NULL);
            edit.putBoolean(Constants.PREF_EMERGENCY_IS_EMAIL_VERIFIED, false);
            edit.putBoolean(Constants.PREF_EMERGENCY_IS_PHONE_VERIFIED, false);
            edit.putBoolean(Constants.PREF_IS_AUTO_SHARE, false);
            edit.apply();
            m14676g();
            m14671a(true);
            return;
        }
        m14670a(amVar.getHeader(), amVar.getText());
        m14671a(false);
    }

    private void m14671a(boolean z) {
        Map hashMap = new HashMap();
        hashMap.put("Status", z ? "SUCCESS" : "FAILURE");
        Localytics.tagEvent("Emergency contact clicked", hashMap);
    }
}
