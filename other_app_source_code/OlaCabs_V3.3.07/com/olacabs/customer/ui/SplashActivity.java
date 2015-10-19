package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.apsalar.sdk.Apsalar;
import com.google.android.gms.common.C0270e;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.C0226d;
import com.google.android.gms.common.api.C0226d.C0220a;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.common.api.C0229h;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.location.C0532g;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest.C0526a;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.m4b.maps.model.LatLng;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.DataUpdaterManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p075a.AnalyticsHelper;
import com.olacabs.customer.p075a.AnalyticsManager;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.p076d.db;
import com.olacabs.customer.utils.BackgroundLooper;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import com.olacabs.p067a.p068a.p069a.p074d.MegatronCore;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class SplashActivity extends FragmentActivity implements OnClickListener, C0221b, C0222c, C0229h<LocationSettingsResult>, TraceFieldInterface {
    private static final JoinPoint f10117s = null;
    private static final JoinPoint f10118t = null;
    private static final JoinPoint f10119u = null;
    private static final JoinPoint f10120v = null;
    private ProgressBar f10121a;
    private Button f10122b;
    private Button f10123c;
    private ImageView f10124d;
    private LinearLayout f10125e;
    private LinearLayout f10126f;
    private OlaApp f10127g;
    private DataManager f10128h;
    private AnalyticsManager f10129i;
    private AnalyticsHelper f10130j;
    private Handler f10131k;
    private C0226d f10132l;
    private boolean f10133m;
    private Map<String, Map<String, String>> f10134n;
    private Map<String, Map<String, String>> f10135o;
    private List<db> f10136p;
    private da f10137q;
    private Handler f10138r;

    /* renamed from: com.olacabs.customer.ui.SplashActivity.1 */
    class C08971 extends Handler {
        final /* synthetic */ SplashActivity f10114a;

        C08971(SplashActivity splashActivity) {
            this.f10114a = splashActivity;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    OLog.m13311a("Received SLIDEUP_ANIMATION_DONE", new Object[0]);
                    this.f10114a.m13893a();
                    this.f10114a.f10124d.setVisibility(8);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    OLog.m13311a("Received SHOW_NO_NW_NEW_USER", new Object[0]);
                    this.f10114a.f10126f.setVisibility(0);
                    this.f10114a.f10125e.setVisibility(8);
                    this.f10114a.f10121a.setVisibility(4);
                    if (this.f10114a.f10124d.isShown()) {
                        this.f10114a.m13876d();
                    } else {
                        this.f10114a.m13893a();
                    }
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    OLog.m13311a("Received SHOW_NO_NW_EXISTING_USER", new Object[0]);
                    this.f10114a.f10126f.setVisibility(0);
                    this.f10114a.f10125e.setVisibility(8);
                    this.f10114a.f10121a.setVisibility(4);
                default:
                    OLog.m13317d("Unknown message on UI mUIHandler. Ignoring!", new Object[0]);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.SplashActivity.2 */
    class C08982 implements AnimationListener {
        final /* synthetic */ SplashActivity f10115a;

        C08982(SplashActivity splashActivity) {
            this.f10115a = splashActivity;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f10115a.f10138r.sendEmptyMessage(1);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.SplashActivity.3 */
    class C08993 implements Runnable {
        final /* synthetic */ SplashActivity f10116a;

        C08993(SplashActivity splashActivity) {
            this.f10116a = splashActivity;
        }

        public void run() {
            try {
                String register = GoogleCloudMessaging.getInstance(this.f10116a.f10127g).register(this.f10116a.getString(com.olacabs.customer.R.string.gcm_sender_id));
                OLog.m13311a("GCM reg id " + register, new Object[0]);
                this.f10116a.f10128h.m13218d().setGcmRegId(register);
                this.f10116a.f10128h.m13249k();
            } catch (Throwable e) {
                OLog.m13310a("Failed to register with GCM", e);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.SplashActivity.a */
    public static class C0900a extends DialogFragment {
        public Dialog onCreateDialog(Bundle bundle) {
            return C0270e.m3380a(getArguments().getInt("dialog_error"), getActivity(), 1001);
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (isAdded()) {
                ((SplashActivity) getActivity()).m13889k();
            }
        }
    }

    static {
        m13892n();
    }

    private static void m13892n() {
        Factory factory = new Factory("SplashActivity.java", SplashActivity.class);
        f10117s = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.SplashActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 167);
        f10118t = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.ui.SplashActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), (int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        f10119u = factory.m15070a("method-execution", factory.m15071a("4", "onResume", "com.olacabs.customer.ui.SplashActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 264);
        f10120v = factory.m15070a("method-execution", factory.m15071a("4", "onPause", "com.olacabs.customer.ui.SplashActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 286);
    }

    public SplashActivity() {
        this.f10133m = false;
        this.f10138r = new C08971(this);
    }

    private void m13866a(LatLng latLng) {
        this.f10128h.m13193a(null, false, latLng, C0532g.f2651b.m3918a(this.f10132l), false, null, "cabinfoRequestTag_splash", Boolean.valueOf(true));
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("SplashActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "SplashActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "SplashActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f10117s, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        OLog.m13311a("onCreate+", new Object[0]);
        setContentView(com.olacabs.customer.R.layout.activity_splash);
        this.f10132l = new C0220a(this).m3207a(C0532g.f2650a).m3208a((C0221b) this).m3209a((C0222c) this).m3211b();
        this.f10124d = (ImageView) findViewById(com.olacabs.customer.R.id.ola_imageView);
        this.f10121a = (ProgressBar) findViewById(com.olacabs.customer.R.id.progressBar_splash);
        this.f10125e = (LinearLayout) findViewById(com.olacabs.customer.R.id.view_signup_login);
        this.f10122b = (Button) findViewById(com.olacabs.customer.R.id.login_btn);
        this.f10123c = (Button) findViewById(com.olacabs.customer.R.id.sign_up_btn);
        this.f10123c.setOnClickListener(this);
        this.f10122b.setOnClickListener(this);
        this.f10126f = (LinearLayout) findViewById(com.olacabs.customer.R.id.view_no_network_state);
        this.f10131k = new Handler(BackgroundLooper.m14896a());
        this.f10127g = (OlaApp) getApplication();
        this.f10128h = this.f10127g.m12878a();
        this.f10129i = this.f10127g.m12879b();
        this.f10130j = this.f10129i.m12867b(this);
        this.f10137q = this.f10128h.m13218d();
        this.f10137q.setSessionId(UUID.randomUUID().toString());
        MegatronCore.m12842e().m12852b(this.f10137q.getSessionId());
        Sherlock.m13344b((Context) this);
        this.f10134n = new HashMap();
        this.f10135o = new HashMap();
        this.f10136p = new ArrayList();
        if (this.f10137q.isPreviouslyLoggedIn()) {
            Map hashMap = new HashMap();
            hashMap.put("App loader", null);
            this.f10134n.put(Constants.SCREEN_TAG, hashMap);
        } else {
            this.f10121a.setVisibility(0);
            this.f10129i.m12868b();
            Localytics.tagScreen("App loader");
            Apsalar.startSession(this, getString(com.olacabs.customer.R.string.apsalar_app_id), getString(com.olacabs.customer.R.string.apsalar_secrect));
        }
        if (!(getIntent() == null || getIntent().getData() == null)) {
            Uri data = getIntent().getData();
            Map hashMap2;
            if (Utils.m14924g(data.getPath()) && data.getPath().contains("/city")) {
                String queryParameter = data.getQueryParameter(Constants.UTM_SOURCE);
                if (Utils.m14924g(queryParameter)) {
                    this.f10129i.m12866a(queryParameter);
                }
                hashMap2 = new HashMap();
                hashMap2.put(Constants.CITY, data.getPath());
                m13868a("FROM_APP_INDEXING", hashMap2);
            } else {
                this.f10128h.m13166a(data);
                hashMap2 = new HashMap();
                hashMap2.put(Constants.DEEP_LINKING_SOURCE_NAME, data.getQueryParameter(Constants.UTM_SOURCE) + Trace.NULL);
                m13868a(Constants.LEAD_SOURCE, hashMap2);
            }
        }
        if (this.f10137q.isNewInstall()) {
            m13877d(true);
        }
        TraceMachine.exitMethod();
    }

    private void m13877d(boolean z) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.putBoolean(Constants.PREF_COACHMARK_BOOKING_SCREEN, z);
        edit.putBoolean(Constants.PREF_COACHMARK_FAVOURITE_SCREEN, z);
        edit.putBoolean(Constants.PREF_COACHMARK_TRACK_SCREEN, z);
        edit.putBoolean(Constants.PREF_COACHMARK_CONFIRMATION_SCREEN, z);
        edit.putBoolean(Constants.PREF_COACHMARK_DELIVERY_SCREEN, z);
        edit.apply();
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f10118t, (Object) this, (Object) this));
        super.onStart();
        if (!this.f10133m) {
            this.f10132l.m3227b();
        }
    }

    protected void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f10119u, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            OLog.m13311a("onResume+", new Object[0]);
            m13867a("App launched");
            m13872b("fb_mobile_activate_app");
            da d = this.f10128h.m13218d();
            d.markIsNewInstall(false);
            d.updateLastShownVersion(AppInfo.sVersionCode);
            if (d.isPreviouslyLoggedIn()) {
                Sherlock.m13334a("Ins app launch time");
            }
            m13903c(Utils.m14917d((Context) this));
        } finally {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    protected void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f10120v, (Object) this, (Object) this));
        super.onPause();
        Apsalar.unregisterApsalarReceiver();
        this.f10138r.removeCallbacksAndMessages(null);
        this.f10128h.m13218d().setConfigurationLoaded(false);
        if (this.f10137q.isPreviouslyLoggedIn()) {
            this.f10137q.setFBAnalyticsEvents(this.f10135o);
            this.f10137q.setAnalyticsEvents(this.f10134n);
            this.f10137q.setSherlockEvent(this.f10136p);
        }
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        this.f10132l.m3230c();
        this.f10137q.setConfigurationLoaded(false);
    }

    private void m13876d() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, com.olacabs.customer.R.anim.slideup_logo);
        loadAnimation.setFillAfter(false);
        this.f10124d.setAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new C08982(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case com.olacabs.customer.R.id.sign_up_btn:
                m13881f();
            case com.olacabs.customer.R.id.login_btn:
                m13878e();
            default:
                OLog.m13318e("Click on unknown view", new Object[0]);
        }
    }

    private void m13878e() {
        if (!isFinishing()) {
            this.f10124d.setVisibility(8);
            getSupportFragmentManager().beginTransaction().addToBackStack(LoginFragment.f11004a).replace(com.olacabs.customer.R.id.container_sign_up, LoginFragment.m14541a(), LoginFragment.f11004a).commit();
        }
    }

    private void m13881f() {
        if (!isFinishing()) {
            this.f10124d.setVisibility(8);
            getSupportFragmentManager().beginTransaction().addToBackStack(aj.f10435a).replace(com.olacabs.customer.R.id.container_sign_up, aj.m14093a(), aj.f10435a).commit();
        }
    }

    public void m13900b(Bundle bundle) {
        if (!isFinishing()) {
            this.f10124d.setVisibility(8);
            getSupportFragmentManager().beginTransaction().addToBackStack(MobileVerificationFragment.f11034a).replace(com.olacabs.customer.R.id.container_sign_up, MobileVerificationFragment.m14561a(bundle), MobileVerificationFragment.f11034a).commitAllowingStateLoss();
        }
    }

    protected void m13893a() {
        if (!isFinishing()) {
            getSupportFragmentManager().beginTransaction().replace(com.olacabs.customer.R.id.container_sign_up, new ak()).commitAllowingStateLoss();
        }
    }

    public void m13899b() {
        m13898a(false);
    }

    public void m13898a(boolean z) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("isNormal", true);
        intent.putExtra("trigger_apsalar", z);
        Intent intent2 = getIntent();
        if (intent2.getBooleanExtra(Constants.PUSH_MESSAGE, false)) {
            OLog.m13313b("Landing page " + getIntent().getStringExtra(Constants.PUSH_LANDING), new Object[0]);
            String stringExtra = intent2.getStringExtra(Constants.PUSH_REQUEST_ID);
            String valueOf = String.valueOf(System.currentTimeMillis());
            intent.fillIn(getIntent(), 2);
            DataManager.m13137a((Context) this).m13229e(null, stringExtra, Constants.PUSH_ACK_TYPE_OPEN, valueOf, Constants.PUSH_MESSAGE);
        }
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(com.olacabs.customer.R.id.container_sign_up) instanceof MobileVerificationFragment) {
            m13867a("Profile verification signup cancelled");
        }
        super.onBackPressed();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        OLog.m13311a("onActivityResult+ - " + i, new Object[0]);
        switch (i) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                if (i2 == 0) {
                    Toast.makeText(this, getString(com.olacabs.customer.R.string.toast_play_services_missing), 0).show();
                    if (this.f10137q.isPreviouslyLoggedIn()) {
                        this.f10136p.add(new db("Ins app launch time", null, "No Play Services", true));
                    } else {
                        Sherlock.m13336a("Ins app launch time", null, "No Play Services", true);
                    }
                    finish();
                    break;
                }
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (intent == null) {
                    m13889k();
                    m13901b(false);
                    break;
                }
                OLog.m13315c("isGpsPresent - %s\nisGpsUsable - %s\nisLocationPresent - %s\nisLocationUsable - %s\nisNetworkLocationPresent - %s\nisNetworkLocationUsable - %s", Boolean.valueOf(r0.m4341c()), Boolean.valueOf(r0.m4340b()), Boolean.valueOf(r0.m4345g()), Boolean.valueOf(r0.m4344f()), Boolean.valueOf(r0.m4343e()), Boolean.valueOf(LocationSettingsStates.m4338a(intent).m4342d()));
                if (!m13869a(LocationSettingsStates.m4338a(intent))) {
                    m13889k();
                    m13901b(false);
                    break;
                }
                m13890l();
                m13901b(true);
                break;
            default:
                OLog.m13318e("Unknown request code : " + i, new Object[0]);
                break;
        }
        super.onActivityResult(i, i2, intent);
    }

    public void m13901b(boolean z) {
        Object obj = z ? "SettingsClickTrue" : "SettingsClickFalse";
        Map hashMap = new HashMap();
        hashMap.put("location_status", obj);
        m13868a("location_access_popup", hashMap);
    }

    private boolean m13869a(LocationSettingsStates locationSettingsStates) {
        return locationSettingsStates.m4340b();
    }

    public void m13903c(boolean z) {
        Map hashMap = new HashMap();
        hashMap.put("Location service status", z ? "Location service On" : "Location service Off");
        m13868a("App started", hashMap);
    }

    private boolean m13883g() {
        int a = C0270e.m3379a((Context) this);
        if (a == 0) {
            return true;
        }
        if (C0270e.m3394b(a)) {
            C0270e.m3380a(a, (Activity) this, 1).show();
            return false;
        }
        OLog.m13315c("Google Play Services not supported", new Object[0]);
        Toast.makeText(this, getString(com.olacabs.customer.R.string.toast_play_services_missing), 0).show();
        if (this.f10137q.isPreviouslyLoggedIn()) {
            this.f10136p.add(new db("Ins app launch time", null, "No Play Services", true));
        } else {
            Sherlock.m13336a("Ins app launch time", null, "No Play Services", true);
        }
        finish();
        return false;
    }

    private void m13885h() {
        this.f10131k.post(new C08993(this));
    }

    private void m13886i() {
        da d = this.f10128h.m13218d();
        boolean isNewInstall = d.isNewInstall();
        boolean isAnUpdate = d.isAnUpdate();
        OLog.m13313b("Is new install? " + isNewInstall, new Object[0]);
        OLog.m13313b("Is update? " + isAnUpdate, new Object[0]);
        boolean isEmpty = TextUtils.isEmpty(d.getGcmRegId());
        boolean isGcmRegWithBackend = d.isGcmRegWithBackend();
        if (!m13883g()) {
            return;
        }
        if (isNewInstall || isAnUpdate || isEmpty) {
            m13885h();
        } else if (!isGcmRegWithBackend) {
            this.f10128h.m13249k();
        }
    }

    public void m13895a(Bundle bundle) {
        C0526a a = new C0526a().m4328a(LocationRequest.m4318a().m4324a(100));
        a.m4329a(true);
        C0532g.f2653d.m4018a(this.f10132l, a.m4330a()).m3180a(this);
        if (this.f10128h.m13218d().isPreviouslyLoggedIn()) {
            Location a2 = C0532g.f2651b.m3918a(this.f10132l);
            if (a2 != null) {
                m13866a(new LatLng(a2.getLatitude(), a2.getLongitude()));
                this.f10137q.cabInfoTriggered(true);
                return;
            }
            this.f10137q.cabInfoTriggered(false);
        }
    }

    public void m13894a(int i) {
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (!this.f10133m) {
            if (connectionResult.m3159a()) {
                try {
                    this.f10133m = true;
                    connectionResult.m3158a(this, 1001);
                    return;
                } catch (SendIntentException e) {
                    this.f10132l.m3227b();
                    return;
                }
            }
            m13871b(connectionResult.m3161c());
            this.f10133m = true;
        }
    }

    private void m13871b(int i) {
        C0900a c0900a = new C0900a();
        Bundle bundle = new Bundle();
        bundle.putInt("dialog_error", i);
        c0900a.setArguments(bundle);
        c0900a.show(getFragmentManager(), "errordialog");
    }

    public void m13897a(LocationSettingsResult locationSettingsResult) {
        Status a = locationSettingsResult.m4335a();
        OLog.m13315c("isGpsUsable - %s\n isLocationUsable - %s\n isNetworkLocationUsable - %s", Boolean.valueOf(r1.m4340b()), Boolean.valueOf(r1.m4344f()), Boolean.valueOf(locationSettingsResult.m4337c().m4342d()));
        switch (a.m3176g()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                this.f10133m = false;
                m13890l();
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                if (m13869a(r1)) {
                    this.f10133m = false;
                    m13890l();
                    return;
                }
                this.f10133m = true;
                try {
                    if (this.f10128h.m13224e() != null) {
                        Map hashMap = new HashMap();
                        hashMap.put("android_version", this.f10128h.m13224e().getOsVersion());
                        m13868a("location settings popup shown", hashMap);
                    }
                    a.m3170a(this, 2);
                } catch (SendIntentException e) {
                    m13888j();
                }
            case 8502:
                m13888j();
            default:
        }
    }

    private void m13868a(String str, Map<String, String> map) {
        if (this.f10137q.isPreviouslyLoggedIn()) {
            this.f10134n.put(str, map);
        } else {
            Localytics.tagEvent(str, map);
        }
    }

    private void m13867a(String str) {
        if (this.f10137q.isPreviouslyLoggedIn()) {
            this.f10134n.put(str, null);
        } else {
            Localytics.tagEvent(str);
        }
    }

    private void m13872b(String str) {
        if (this.f10137q.isPreviouslyLoggedIn()) {
            this.f10135o.put(str, null);
        } else {
            this.f10130j.m12858a(str);
        }
    }

    private void m13888j() {
        Toast.makeText(this, com.olacabs.customer.R.string.location_setting_dialog_desc, 1).show();
    }

    private void m13889k() {
        this.f10128h.m13218d().setLocationStatus(false);
        this.f10133m = false;
        HashMap hashMap = new HashMap();
        hashMap.put("Location status", "OFF");
        if (this.f10137q.isPreviouslyLoggedIn()) {
            this.f10136p.add(new db("Ins app launch time", hashMap, null));
        } else {
            Sherlock.m13342a("Ins app launch time", hashMap, null);
        }
        finish();
    }

    private void m13890l() {
        da d = this.f10128h.m13218d();
        d.setLocationStatus(true);
        if (Utils.m14909a((Context) this)) {
            OLog.m13311a("Previously LoggedIn " + d.isPreviouslyLoggedIn(), new Object[0]);
            if (d.isPreviouslyLoggedIn()) {
                d.setConfigurationLoaded(false);
                m13898a(true);
            } else if (!d.isPreviouslyLoggedIn()) {
                DataUpdaterManager.m13261a(getApplicationContext()).m13264a("app_config");
                this.f10125e.setVisibility(0);
                this.f10126f.setVisibility(8);
                this.f10121a.setVisibility(4);
                if (!m13891m()) {
                    if (this.f10124d.isShown()) {
                        m13876d();
                    } else {
                        m13893a();
                    }
                }
            }
            m13886i();
            return;
        }
        if (d.isPreviouslyLoggedIn()) {
            this.f10138r.sendEmptyMessage(3);
        } else {
            this.f10138r.sendEmptyMessage(2);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Failure type", "No Network");
        hashMap.put("Location status", "ON");
        if (this.f10137q.isPreviouslyLoggedIn()) {
            this.f10136p.add(new db("Ins app launch time", hashMap, null));
        } else {
            Sherlock.m13342a("Ins app launch time", hashMap, null);
        }
    }

    public void m13902c() {
        DataUpdaterManager.m13261a(getApplicationContext()).m13264a("profile_data");
    }

    private boolean m13891m() {
        return m13874c(LoginFragment.f11004a) || m13874c(aj.f10435a) || m13874c(MobileVerificationFragment.f11034a);
    }

    private boolean m13874c(String str) {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(str);
        return findFragmentByTag != null ? findFragmentByTag.isVisible() : false;
    }
}
