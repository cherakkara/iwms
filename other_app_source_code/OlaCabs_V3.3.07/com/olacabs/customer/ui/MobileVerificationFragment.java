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
import android.support.v4.app.Fragment;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.apsalar.sdk.Apsalar;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.AppState;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p075a.AnalyticsHelper;
import com.olacabs.customer.p075a.AnalyticsManager;
import com.olacabs.customer.p076d.AuthErrorCodesResponse;
import com.olacabs.customer.p076d.AuthTokenSession;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.p076d.dd;
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
import org.json.JSONObject;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
/* renamed from: com.olacabs.customer.ui.s */
public class MobileVerificationFragment extends Fragment implements TextWatcher, OnClickListener, TraceFieldInterface {
    private static final JoinPoint f11030A = null;
    private static final JoinPoint f11031B = null;
    private static final JoinPoint f11032C = null;
    private static final JoinPoint f11033D = null;
    public static final String f11034a;
    private static final JoinPoint f11035z = null;
    private String f11036b;
    private String f11037c;
    private TextView f11038d;
    private TextView f11039e;
    private TextView f11040f;
    private EditText f11041g;
    private TextView f11042h;
    private Button f11043i;
    private TextView f11044j;
    private LinearLayout f11045k;
    private ImageView f11046l;
    private ProgressDialog f11047m;
    private LinearLayout f11048n;
    private LinearLayout f11049o;
    private TextView f11050p;
    private DataManager f11051q;
    private AnalyticsManager f11052r;
    private AnalyticsHelper f11053s;
    private TextView f11054t;
    private String f11055u;
    private IntentFilter f11056v;
    private BroadcastReceiver f11057w;
    private aj f11058x;
    private aj f11059y;

    /* renamed from: com.olacabs.customer.ui.s.1 */
    class MobileVerificationFragment implements aj {
        final /* synthetic */ MobileVerificationFragment f11020a;

        MobileVerificationFragment(MobileVerificationFragment mobileVerificationFragment) {
            this.f11020a = mobileVerificationFragment;
        }

        public void onFailure(Throwable th) {
            if (this.f11020a.isAdded()) {
                this.f11020a.f11047m.dismiss();
                this.f11020a.m14586a(this.f11020a.getString(R.string.generic_failure_desc), this.f11020a.getString(R.string.generic_failure_header));
            }
        }

        public void onSuccess(Object obj) {
            if (this.f11020a.isAdded()) {
                this.f11020a.f11047m.dismiss();
                dq dqVar = (dq) obj;
                if (dqVar.getStatus().equals("SUCCESS")) {
                    this.f11020a.f11043i.setEnabled(false);
                    this.f11020a.f11037c = dqVar.getVerificationId();
                    Toast.makeText(this.f11020a.getActivity().getApplicationContext(), this.f11020a.getString(R.string.call_in_while), 0).show();
                } else if (dqVar.getStatus().equals("FAILURE")) {
                    Toast.makeText(this.f11020a.getActivity().getApplicationContext(), this.f11020a.getString(R.string.call_back_failure), 0).show();
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.s.2 */
    class MobileVerificationFragment implements aj {
        final /* synthetic */ MobileVerificationFragment f11021a;

        MobileVerificationFragment(MobileVerificationFragment mobileVerificationFragment) {
            this.f11021a = mobileVerificationFragment;
        }

        public void onFailure(Throwable th) {
            if (this.f11021a.isAdded()) {
                Sherlock.m13336a("Ins OTP verify clicked", (VolleyError) th, th.getMessage(), true);
                this.f11021a.f11047m.dismiss();
                VolleyError volleyError = (VolleyError) th;
                String str = Trace.NULL;
                str = Trace.NULL;
                if (volleyError == null || volleyError.f464a == null) {
                    str = this.f11021a.getString(R.string.generic_failure_header);
                    this.f11021a.m14586a(this.f11021a.getString(R.string.generic_failure_desc), str);
                    return;
                }
                byte[] bArr = volleyError.f464a.f498b;
                if (bArr != null) {
                    try {
                        AuthErrorCodesResponse authErrorCodesResponse = (AuthErrorCodesResponse) new Gson().m12343a(new String(bArr), AuthErrorCodesResponse.class);
                        if (authErrorCodesResponse != null) {
                            this.f11021a.m14586a(authErrorCodesResponse.getText(), authErrorCodesResponse.getHeader());
                            return;
                        }
                        this.f11021a.m14586a(this.f11021a.getString(R.string.generic_failure_desc), this.f11021a.getString(R.string.generic_failure_header));
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        this.f11021a.m14586a(this.f11021a.getString(R.string.generic_failure_desc), this.f11021a.getString(R.string.generic_failure_header));
                    }
                }
            }
        }

        public void onSuccess(Object obj) {
            dv dvVar = (dv) obj;
            if (this.f11021a.isAdded()) {
                this.f11021a.f11047m.dismiss();
                if (dvVar.getStatus().equals("SUCCESS")) {
                    Localytics.tagEvent("Verified successfully");
                    dt c = this.f11021a.f11051q.m13209c();
                    da d = this.f11021a.f11051q.m13218d();
                    c.setName(dvVar.getName());
                    c.setCity(dvVar.getCity());
                    c.setUserId(dvVar.getUserId());
                    Sherlock.m13344b(this.f11021a.getActivity());
                    c.setReferralCode(dvVar.getReferralCode());
                    c.setOlaBalance(dvVar.getOlaMoneyBalance());
                    AuthTokenSession authTokenExpireSessions = dvVar.getAuthTokenExpireSessions();
                    if (authTokenExpireSessions != null && authTokenExpireSessions.isValid()) {
                        d.setAuthSessionExpiry(authTokenExpireSessions.getExpiryFromNow() + System.currentTimeMillis());
                        d.setAuthSessionId(authTokenExpireSessions.getAuthToken());
                        d.setAuthRefreshToken(authTokenExpireSessions.getRefreshToken());
                    }
                    d.setAppState(AppState.BEFORE_BOOKING);
                    this.f11021a.m14564a(dvVar.getCity());
                    this.f11021a.m14569b("SignUp");
                    c.updateUserInfoOnSignUp(dvVar.getUserId(), dvVar.getReferralCode(), dvVar.getName(), dvVar.getMobile(), d.getSignUpAttemptDetails().mEnteredEmailId, dvVar.getCity(), dvVar.isVerified());
                    d.clearSignUpAttemptDetail();
                    this.f11021a.m14574d();
                } else if (dvVar.getStatus().equals("FAILURE")) {
                    String string;
                    Localytics.tagEvent("Verification failed");
                    String reason = dvVar.getReason();
                    if (reason.equalsIgnoreCase("INVALID_VERIFICATION_CODE")) {
                        string = this.f11021a.getString(R.string.invalid_entry);
                        reason = this.f11021a.getString(R.string.mobile_verification_fail_invalid_code_desc);
                    } else if (reason.equalsIgnoreCase("INCORRECT_VERIFICATION_CODE")) {
                        string = dvVar.getHeader();
                        reason = dvVar.getText();
                    } else if (reason.equalsIgnoreCase("MISSING_PARAMETERS")) {
                        string = this.f11021a.getString(R.string.failure);
                        reason = dvVar.getText();
                    } else if (reason.equalsIgnoreCase("INVALID_PARAMETERS")) {
                        string = this.f11021a.getString(R.string.invalid_entry);
                        reason = dvVar.getText();
                    } else if (reason.equalsIgnoreCase("USER_ALREADY_EXISTS")) {
                        string = this.f11021a.getString(R.string.warning);
                        reason = dvVar.getText();
                    } else if (reason.equalsIgnoreCase("USER_BANNED")) {
                        string = this.f11021a.getString(R.string.failure);
                        reason = dvVar.getText();
                    } else if (dvVar.getText() == null || dvVar.getHeader() == null) {
                        string = this.f11021a.getString(R.string.generic_failure_header);
                        reason = this.f11021a.getString(R.string.generic_failure_desc);
                    } else {
                        string = dvVar.getHeader();
                        reason = dvVar.getText();
                    }
                    Sherlock.m13336a("Ins OTP verify clicked", null, reason, true);
                    this.f11021a.m14586a(reason, string);
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.s.3 */
    class MobileVerificationFragment extends BroadcastReceiver {
        final /* synthetic */ MobileVerificationFragment f11022a;

        MobileVerificationFragment(MobileVerificationFragment mobileVerificationFragment) {
            this.f11022a = mobileVerificationFragment;
        }

        public void onReceive(Context context, Intent intent) {
            if (TextUtils.isEmpty(this.f11022a.f11041g.getText().toString())) {
                for (SmsMessage smsMessage : MobileVerificationFragment.m14565a(intent)) {
                    if (smsMessage != null) {
                        String a = this.f11022a.m14584a(smsMessage);
                        if (Utils.m14924g(a)) {
                            this.f11022a.f11055u = a;
                            this.f11022a.f11041g.setText(this.f11022a.f11055u);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.s.4 */
    class MobileVerificationFragment extends CountDownTimer {
        long f11023a;
        long f11024b;
        final /* synthetic */ MobileVerificationFragment f11025c;

        MobileVerificationFragment(MobileVerificationFragment mobileVerificationFragment, long j, long j2) {
            this.f11025c = mobileVerificationFragment;
            super(j, j2);
        }

        public void onTick(long j) {
            if (this.f11025c.isAdded()) {
                this.f11023a = TimeUnit.MILLISECONDS.toMinutes(j);
                this.f11024b = TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j));
                this.f11025c.f11042h.setText("(" + String.format("%02d : %02d", new Object[]{Long.valueOf(this.f11023a), Long.valueOf(this.f11024b)}) + ")");
            }
        }

        public void onFinish() {
            if (this.f11025c.isAdded()) {
                this.f11025c.f11042h.setText("(00 : 00)");
                this.f11025c.f11043i.setEnabled(true);
                this.f11025c.f11042h.setVisibility(8);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.s.5 */
    class MobileVerificationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f11026a;
        final /* synthetic */ MobileVerificationFragment f11027b;

        MobileVerificationFragment(MobileVerificationFragment mobileVerificationFragment, AlertDialog alertDialog) {
            this.f11027b = mobileVerificationFragment;
            this.f11026a = alertDialog;
        }

        public void onClick(View view) {
            this.f11026a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.s.6 */
    class MobileVerificationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f11028a;
        final /* synthetic */ MobileVerificationFragment f11029b;

        MobileVerificationFragment(MobileVerificationFragment mobileVerificationFragment, AlertDialog alertDialog) {
            this.f11029b = mobileVerificationFragment;
            this.f11028a = alertDialog;
        }

        public void onClick(View view) {
            this.f11029b.f11040f.setClickable(true);
            this.f11028a.dismiss();
        }
    }

    private static void m14583h() {
        Factory factory = new Factory("MobileVerificationFragment.java", MobileVerificationFragment.class);
        f11035z = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.MobileVerificationFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 266);
        f11030A = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.MobileVerificationFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 342);
        f11031B = factory.m15070a("method-execution", factory.m15071a("1", "onStart", "com.olacabs.customer.ui.MobileVerificationFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 522);
        f11032C = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.MobileVerificationFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 528);
        f11033D = factory.m15070a("method-execution", factory.m15071a("1", "onPause", "com.olacabs.customer.ui.MobileVerificationFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 540);
    }

    static {
        MobileVerificationFragment.m14583h();
        f11034a = MobileVerificationFragment.class.getSimpleName();
    }

    public static MobileVerificationFragment m14561a(Bundle bundle) {
        MobileVerificationFragment mobileVerificationFragment = new MobileVerificationFragment();
        mobileVerificationFragment.setArguments(bundle);
        return mobileVerificationFragment;
    }

    public MobileVerificationFragment() {
        this.f11058x = new MobileVerificationFragment(this);
        this.f11059y = new MobileVerificationFragment(this);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("s");
        try {
            TraceMachine.enterMethod(this._nr_trace, "s#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "s#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f11035z, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f11036b = arguments.getString(Constants.BUNDLE_TYPE);
            this.f11037c = arguments.getString("verification_id");
        }
        OlaApp olaApp = (OlaApp) getActivity().getApplication();
        this.f11051q = olaApp.m12878a();
        this.f11052r = olaApp.m12879b();
        this.f11053s = this.f11052r.m12867b(getActivity());
        Localytics.tagScreen("Mobile Verification");
        this.f11047m = new ProgressDialog(getActivity(), R.style.TransparentProgressDialog);
        this.f11047m.setIndeterminateDrawable(getResources().getDrawable(R.drawable.custom_progress_background));
        this.f11047m.setCancelable(false);
        this.f11057w = new MobileVerificationFragment(this);
        this.f11056v = new IntentFilter();
        this.f11056v.addAction("android.provider.Telephony.SMS_RECEIVED");
        TraceMachine.exitMethod();
    }

    public String m14584a(SmsMessage smsMessage) {
        CharSequence displayMessageBody = smsMessage.getDisplayMessageBody();
        if (!displayMessageBody.isEmpty() && displayMessageBody.toLowerCase().contains("olacabs") && displayMessageBody.toLowerCase().contains("unique verification code")) {
            Matcher matcher = Pattern.compile("\\b\\d{4}\\b").matcher(displayMessageBody);
            if (matcher.find()) {
                return matcher.group();
            }
        }
        return null;
    }

    private void m14567b() {
        if (Utils.m14920e(this.f11055u)) {
            m14579f();
        } else {
            m14586a(getString(R.string.mobile_verfification_incorrect_desc), getString(R.string.mobile_verfification_incorrect_title));
        }
    }

    public static SmsMessage[] m14565a(Intent intent) {
        Object[] objArr = (Object[]) intent.getSerializableExtra("pdus");
        int length = objArr.length;
        SmsMessage[] smsMessageArr = new SmsMessage[length];
        for (int i = 0; i < length; i++) {
            smsMessageArr[i] = SmsMessage.createFromPdu((byte[]) objArr[i]);
        }
        return smsMessageArr;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "s#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "s#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f11030A, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.activity_mobile_verification, viewGroup, false);
        m14563a(inflate);
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14563a(View view) {
        dd signUpAttemptDetails = this.f11051q.m13218d().getSignUpAttemptDetails();
        this.f11038d = (TextView) view.findViewById(R.id.verification_txt);
        String string = getString(R.string.user_mobile_num_verification_desc);
        Object[] objArr = new Object[1];
        objArr[0] = signUpAttemptDetails.mPhoneNumber == null ? this.f11051q.m13209c().getPhoneNumber() : signUpAttemptDetails.mPhoneNumber;
        this.f11038d.setText(String.format(string, objArr));
        this.f11039e = (TextView) view.findViewById(R.id.enter_verification_code_txt);
        this.f11041g = (EditText) view.findViewById(R.id.verification_edit_txt);
        this.f11041g.addTextChangedListener(this);
        this.f11040f = (TextView) view.findViewById(R.id.why_verify_txt);
        this.f11040f.setOnClickListener(this);
        this.f11042h = (TextView) view.findViewById(R.id.verify_timer);
        this.f11043i = (Button) view.findViewById(R.id.call_me_button);
        this.f11043i.setOnClickListener(this);
        this.f11044j = (TextView) view.findViewById(R.id.verify);
        this.f11044j.setOnClickListener(this);
        this.f11045k = (LinearLayout) view.findViewById(R.id.view_no_network_state);
        this.f11046l = (ImageView) view.findViewById(R.id.verify_menu_button);
        this.f11046l.setOnClickListener(this);
        this.f11054t = (TextView) view.findViewById(R.id.vc_nt_recvd_txt);
        this.f11048n = (LinearLayout) view.findViewById(R.id.verification_background);
        this.f11049o = (LinearLayout) view.findViewById(R.id.action_bar_layout);
        this.f11050p = (TextView) view.findViewById(R.id.action_bar_view);
        m14571c();
    }

    private void m14571c() {
        new MobileVerificationFragment(this, 30000, 1000).start();
    }

    protected void m14586a(String str, String str2) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str2);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new MobileVerificationFragment(this, create));
        create.show();
    }

    private void m14564a(String str) {
        Map hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("City name", str);
        }
        Localytics.tagEvent("Signed up successfully", hashMap);
        this.f11053s.m12859a("fb_mobile_complete_registration", hashMap);
        Apsalar.event("Signed up successfully", new JSONObject(hashMap));
    }

    private void m14569b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("Verification Through", str);
        Localytics.tagEvent("Verification Completed", hashMap);
        Ola.f11483G = false;
    }

    private void m14574d() {
        Localytics.tagEvent("clicked and signed up successfully");
        ((SplashActivity) getActivity()).m13902c();
        ((SplashActivity) getActivity()).m13899b();
    }

    private void m14577e() {
        this.f11047m.show();
        this.f11051q.m13185a(new WeakReference(this.f11058x), this.f11036b, this.f11037c, this.f11051q.m13218d().getSignUpAttemptDetails().mName, this.f11051q.m13218d().getSignUpAttemptDetails().mPhoneNumber, f11034a);
    }

    protected void m14585a() {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(getString(R.string.why_verify_title));
        ((TextView) inflate.findViewById(R.id.item_message)).setText(getString(R.string.why_verify_desc));
        inflate.findViewById(R.id.button_ok).setOnClickListener(new MobileVerificationFragment(this, create));
        create.show();
    }

    private void m14579f() {
        this.f11047m.show();
        this.f11051q.m13207b(new WeakReference(this.f11059y), this.f11036b, this.f11037c, this.f11055u, Trace.NULL, Trace.NULL, f11034a);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.verify_menu_button:
                getActivity().onBackPressed();
            case R.id.verify:
                Sherlock.m13334a("Ins OTP verify clicked");
                m14567b();
            case R.id.why_verify_txt:
                this.f11040f.setClickable(false);
                Localytics.tagEvent("Why verify clicked");
                m14585a();
            case R.id.call_me_button:
                Localytics.tagEvent("Call message clicked");
                m14577e();
            default:
        }
    }

    public void onEventMainThread(ah ahVar) {
        if (ahVar.isConnected()) {
            this.f11045k.setVisibility(4);
            m14581g();
            return;
        }
        this.f11045k.setVisibility(0);
        this.f11044j.setEnabled(false);
    }

    private void m14581g() {
        if (this.f11041g.getText().length() == 4) {
            this.f11044j.setEnabled(true);
        }
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f11031B, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f11032C, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            Sherlock.m13345b("Ins signup clicked");
            getActivity().registerReceiver(this.f11057w, this.f11056v);
            if (Utils.m14909a(getActivity())) {
                this.f11045k.setVisibility(4);
            } else {
                this.f11045k.setVisibility(0);
            }
            ActivityTraceAspect.m12823a().m12827c(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public void onPause() {
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f11033D, (Object) this, (Object) this));
        super.onPause();
        getActivity().unregisterReceiver(this.f11057w);
        super.onPause();
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        this.f11051q.m13169a(f11034a);
        EventBus.m3a().m17b(this);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.f11055u = editable.toString();
        if (editable.toString().length() == 4) {
            this.f11044j.setEnabled(true);
        } else {
            this.f11044j.setEnabled(false);
        }
    }
}
