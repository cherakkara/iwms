package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.apsalar.sdk.Apsalar;
import com.google.android.m4b.maps.model.LatLng;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.AppState;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.ForceLogoutCommand;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p075a.AnalyticsHelper;
import com.olacabs.customer.p075a.AnalyticsManager;
import com.olacabs.customer.p076d.BasicResponse;
import com.olacabs.customer.p076d.BookingCabInfoResponse;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.cn;
import com.olacabs.customer.ui.widgets.KaaliPeeliProgressView;
import com.olacabs.customer.ui.widgets.KaaliPeeliProgressView.C0894a;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
/* renamed from: com.olacabs.customer.ui.q */
public class LocalTaxiBookingRequestFragment extends Fragment implements OnClickListener, BackKeyHandler, TraceFieldInterface {
    private static final JoinPoint f10961F = null;
    private static final JoinPoint f10962G = null;
    private static final JoinPoint f10963H = null;
    private static final JoinPoint f10964I = null;
    public static final String f10965a;
    private boolean f10966A;
    private aj f10967B;
    private LocalTaxiBookingRequestFragment f10968C;
    private DataManager f10969D;
    private AnalyticsManager f10970E;
    Runnable f10971b;
    private aj f10972c;
    private String f10973d;
    private aj f10974e;
    private KaaliPeeliProgressView f10975f;
    private ImageView f10976g;
    private Button f10977h;
    private Handler f10978i;
    private MainActivity f10979j;
    private String f10980k;
    private LatLng f10981l;
    private String f10982m;
    private String f10983n;
    private String f10984o;
    private String f10985p;
    private String f10986q;
    private String f10987r;
    private String f10988s;
    private boolean f10989t;
    private AnalyticsHelper f10990u;
    private String f10991v;
    private aj f10992w;
    private boolean f10993x;
    private boolean f10994y;
    private boolean f10995z;

    /* renamed from: com.olacabs.customer.ui.q.a */
    public interface LocalTaxiBookingRequestFragment {
        void m13564a(String str);
    }

    /* renamed from: com.olacabs.customer.ui.q.1 */
    class LocalTaxiBookingRequestFragment implements aj {
        final /* synthetic */ LocalTaxiBookingRequestFragment f10953a;

        LocalTaxiBookingRequestFragment(LocalTaxiBookingRequestFragment localTaxiBookingRequestFragment) {
            this.f10953a = localTaxiBookingRequestFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("CancelRideRequester failed", th);
            th.printStackTrace();
        }

        public void onSuccess(Object obj) {
            if (((cn) obj).getStatus().equalsIgnoreCase("SUCCESS")) {
                this.f10953a.m14527n();
                this.f10953a.m14520k();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.q.2 */
    class LocalTaxiBookingRequestFragment implements Runnable {
        final /* synthetic */ LocalTaxiBookingRequestFragment f10954a;

        LocalTaxiBookingRequestFragment(LocalTaxiBookingRequestFragment localTaxiBookingRequestFragment) {
            this.f10954a = localTaxiBookingRequestFragment;
        }

        public void run() {
            this.f10954a.m14528o();
            this.f10954a.f10978i.postDelayed(this, 7000);
        }
    }

    /* renamed from: com.olacabs.customer.ui.q.3 */
    class LocalTaxiBookingRequestFragment implements aj {
        final /* synthetic */ LocalTaxiBookingRequestFragment f10955a;

        LocalTaxiBookingRequestFragment(LocalTaxiBookingRequestFragment localTaxiBookingRequestFragment) {
            this.f10955a = localTaxiBookingRequestFragment;
        }

        public void onFailure(Throwable th) {
            if (this.f10955a.isAdded()) {
                OLog.m13312a(th, "mLocalTaxiSubmitResponse failed", new Object[0]);
                this.f10955a.m14510f();
            }
        }

        public void onSuccess(Object obj) {
            if (!this.f10955a.isAdded()) {
                return;
            }
            if (((BasicResponse) obj).getStatus().equalsIgnoreCase("SUCCESS")) {
                this.f10955a.m14517i();
                if ("cancel".equalsIgnoreCase(this.f10955a.f10973d)) {
                    this.f10955a.m14537b();
                    ((MainActivity) this.f10955a.getActivity()).m13595a(1);
                    return;
                } else if ("retry".equalsIgnoreCase(this.f10955a.f10973d)) {
                    this.f10955a.m14513g();
                    return;
                } else {
                    return;
                }
            }
            this.f10955a.m14510f();
        }
    }

    /* renamed from: com.olacabs.customer.ui.q.4 */
    class LocalTaxiBookingRequestFragment implements aj {
        final /* synthetic */ LocalTaxiBookingRequestFragment f10956a;

        LocalTaxiBookingRequestFragment(LocalTaxiBookingRequestFragment localTaxiBookingRequestFragment) {
            this.f10956a = localTaxiBookingRequestFragment;
        }

        public void onFailure(Throwable th) {
            if (this.f10956a.isAdded()) {
                HashMap hashMap = new HashMap();
                hashMap.put("Local taxi category", this.f10956a.f10980k);
                if (Utils.m14924g(this.f10956a.f10991v)) {
                    hashMap.put("Booking city", this.f10956a.f10991v);
                } else {
                    hashMap.put("Booking city", "NA");
                }
                Sherlock.m13342a("Ins KP ride booked", hashMap, (VolleyError) th);
                OLog.m13310a("Fetch Configuration Request failed", th);
            }
        }

        public void onSuccess(Object obj) {
            if (this.f10956a.isAdded()) {
                BookingCabInfoResponse bookingCabInfoResponse = (BookingCabInfoResponse) obj;
                if (bookingCabInfoResponse != null && bookingCabInfoResponse.isForceLogout()) {
                    new ForceLogoutCommand(true).m13270a(this.f10956a.getActivity());
                }
                if (bookingCabInfoResponse.getStatus().equalsIgnoreCase("SUCCESS") && bookingCabInfoResponse.getStateId() != AppState.HOME_PAGE.ordinal()) {
                    this.f10956a.m14530p();
                    if (bookingCabInfoResponse.getStateId() != 5) {
                        this.f10956a.f10988s = bookingCabInfoResponse.getBooking().getBookingId();
                    } else if (bookingCabInfoResponse.getBookingId() != null) {
                        this.f10956a.f10988s = bookingCabInfoResponse.getBookingId();
                    } else {
                        bookingCabInfoResponse.getBooking().getBookingId();
                    }
                    this.f10956a.f10969D.m13218d().setAppState(AppState.m12881a(bookingCabInfoResponse.getStateId()));
                    this.f10956a.m14527n();
                    Ola.f11494k = false;
                    HashMap hashMap = new HashMap();
                    hashMap.put("Local taxi category", this.f10956a.f10980k);
                    if (Utils.m14924g(this.f10956a.f10991v)) {
                        hashMap.put("Booking city", this.f10956a.f10991v);
                    } else {
                        hashMap.put("Booking city", "NA");
                    }
                    Sherlock.m13340a("Ins KP ride booked", hashMap);
                    this.f10956a.f10968C.m13564a(this.f10956a.f10988s);
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.q.5 */
    class LocalTaxiBookingRequestFragment implements aj {
        final /* synthetic */ LocalTaxiBookingRequestFragment f10957a;

        LocalTaxiBookingRequestFragment(LocalTaxiBookingRequestFragment localTaxiBookingRequestFragment) {
            this.f10957a = localTaxiBookingRequestFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("mLocalTaxiBookingIdRequester failed", th);
            th.printStackTrace();
            this.f10957a.m14510f();
        }

        public void onSuccess(Object obj) {
            BasicResponse basicResponse = (BasicResponse) obj;
            if (this.f10957a.isAdded() && this.f10957a.getActivity() != null) {
                if (basicResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                    if (this.f10957a.f10993x) {
                        Ola.f11494k = false;
                    }
                    if (this.f10957a.f10995z) {
                        this.f10957a.f10975f.m14810a();
                        this.f10957a.f10995z = false;
                    } else {
                        this.f10957a.f10975f.m14813c();
                    }
                    this.f10957a.f10977h.setEnabled(true);
                    this.f10957a.f10986q = basicResponse.getBookingId();
                    this.f10957a.m14525m();
                    return;
                }
                this.f10957a.m14510f();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.q.6 */
    class LocalTaxiBookingRequestFragment implements C0894a {
        final /* synthetic */ LocalTaxiBookingRequestFragment f10958a;

        LocalTaxiBookingRequestFragment(LocalTaxiBookingRequestFragment localTaxiBookingRequestFragment) {
            this.f10958a = localTaxiBookingRequestFragment;
        }

        public void m14495a() {
            this.f10958a.m14510f();
            this.f10958a.m14527n();
        }
    }

    /* renamed from: com.olacabs.customer.ui.q.7 */
    class LocalTaxiBookingRequestFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10959a;
        final /* synthetic */ LocalTaxiBookingRequestFragment f10960b;

        LocalTaxiBookingRequestFragment(LocalTaxiBookingRequestFragment localTaxiBookingRequestFragment, AlertDialog alertDialog) {
            this.f10960b = localTaxiBookingRequestFragment;
            this.f10959a = alertDialog;
        }

        public void onClick(View view) {
            this.f10959a.dismiss();
            this.f10960b.f10966A = false;
            this.f10960b.m14510f();
        }
    }

    private static void m14533q() {
        Factory factory = new Factory("LocalTaxiBookingRequestFragment.java", LocalTaxiBookingRequestFragment.class);
        f10961F = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.LocalTaxiBookingRequestFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), (int) HttpStatus.SC_MULTIPLE_CHOICES);
        f10962G = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.LocalTaxiBookingRequestFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 330);
        f10963H = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.LocalTaxiBookingRequestFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 360);
        f10964I = factory.m15070a("method-execution", factory.m15071a("1", "onStart", "com.olacabs.customer.ui.LocalTaxiBookingRequestFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 368);
    }

    static {
        LocalTaxiBookingRequestFragment.m14533q();
        f10965a = LocalTaxiBookingRequestFragment.class.getSimpleName();
    }

    public LocalTaxiBookingRequestFragment() {
        this.f10972c = new LocalTaxiBookingRequestFragment(this);
        this.f10971b = new LocalTaxiBookingRequestFragment(this);
        this.f10974e = new LocalTaxiBookingRequestFragment(this);
        this.f10988s = null;
        this.f10989t = false;
        this.f10992w = new LocalTaxiBookingRequestFragment(this);
        this.f10993x = false;
        this.f10995z = true;
        this.f10966A = false;
        this.f10967B = new LocalTaxiBookingRequestFragment(this);
    }

    public static LocalTaxiBookingRequestFragment m14496a(String str, LatLng latLng, String str2, String str3, String str4, String str5, String str6) {
        LocalTaxiBookingRequestFragment localTaxiBookingRequestFragment = new LocalTaxiBookingRequestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CITY, str6);
        bundle.putString("category_id", str);
        bundle.putDouble("user_latitude", latLng.f7554a);
        bundle.putDouble("user_longitude", latLng.f7555b);
        bundle.putString("drop_locality", str2);
        bundle.putString("drop_locality_id", str3);
        bundle.putString("drop_landmark", str4);
        bundle.putString("pickup_landmark", str5);
        localTaxiBookingRequestFragment.setArguments(bundle);
        return localTaxiBookingRequestFragment;
    }

    public static LocalTaxiBookingRequestFragment m14497a(String str, LatLng latLng, String str2, String str3, String str4, String str5, boolean z, String str6, boolean z2, String str7) {
        LocalTaxiBookingRequestFragment localTaxiBookingRequestFragment = new LocalTaxiBookingRequestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CITY, str7);
        bundle.putString("category_id", str);
        bundle.putDouble("user_latitude", latLng.f7554a);
        bundle.putDouble("user_longitude", latLng.f7555b);
        bundle.putString("drop_locality", str2);
        bundle.putString("drop_locality_id", str3);
        bundle.putString("drop_landmark", str4);
        bundle.putString("pickup_landmark", str5);
        bundle.putBoolean("kp_show_cancel", z);
        bundle.putString(Constants.BUNDLE_KP_KRN, str6);
        bundle.putBoolean("kp_enable_retry", z2);
        localTaxiBookingRequestFragment.setArguments(bundle);
        return localTaxiBookingRequestFragment;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("q");
        try {
            TraceMachine.enterMethod(this._nr_trace, "q#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "q#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10961F, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            this.f10991v = arguments.getString(Constants.CITY);
            this.f10980k = arguments.getString("category_id");
            this.f10981l = new LatLng(arguments.getDouble("user_latitude"), arguments.getDouble("user_longitude"));
            this.f10982m = arguments.getString("drop_locality");
            this.f10983n = arguments.getString("drop_locality_id");
            this.f10984o = arguments.getString("drop_landmark");
            this.f10985p = arguments.getString("pickup_landmark");
            this.f10993x = arguments.getBoolean("kp_show_cancel");
            Ola.f11494k = this.f10993x;
            if (arguments.containsKey(Constants.BUNDLE_KP_KRN)) {
                this.f10987r = arguments.getString(Constants.BUNDLE_KP_KRN);
            }
            if (arguments.containsKey("kp_enable_retry")) {
                this.f10994y = arguments.getBoolean("kp_enable_retry");
            }
        }
        this.f10978i = new Handler();
        this.f10979j = (MainActivity) getActivity();
        this.f10979j.f9699d = false;
        OlaApp olaApp = (OlaApp) this.f10979j.getApplication();
        this.f10969D = olaApp.m12878a();
        this.f10970E = olaApp.m12879b();
        this.f10990u = this.f10970E.m12867b(getActivity());
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "q#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "q#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10962G, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_local_booking_request, viewGroup, false);
        this.f10975f = (KaaliPeeliProgressView) inflate.findViewById(R.id.progress_view);
        this.f10975f.f11316b = TraceMachine.UNHEALTHY_TRACE_TIMEOUT;
        inflate.findViewById(R.id.button_retry).setOnClickListener(this);
        inflate.findViewById(R.id.button_retry_cancel).setOnClickListener(this);
        this.f10976g = (ImageView) inflate.findViewById(R.id.kp_image_view);
        TextView textView = (TextView) inflate.findViewById(R.id.label_progess_text);
        if (m14538c()) {
            this.f10976g.setImageResource(R.drawable.ic_auto_selected);
            textView.setText(R.string.text_auto_waiting);
        } else {
            this.f10976g.setImageResource(R.drawable.ic_kp_car_selected);
            textView.setText(R.string.text_kp_waiting);
        }
        this.f10977h = (Button) inflate.findViewById(R.id.kp_button_cancel);
        this.f10977h.setEnabled(false);
        this.f10977h.setOnClickListener(this);
        this.f10975f.setOnProgressFinishListner(new LocalTaxiBookingRequestFragment(this));
        if (!this.f10993x) {
            m14523l();
        }
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f10963H, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            if (this.f10993x) {
                m14510f();
            }
            ActivityTraceAspect.m12823a().m12827c(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f10964I, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f10968C = (LocalTaxiBookingRequestFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.f10968C = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        m14508e();
    }

    public boolean m14536a() {
        ((MainActivity) getActivity()).m13602a(true);
        return false;
    }

    private void m14508e() {
        if (getView() != null) {
            getView().findViewById(R.id.layout_progress).setVisibility(0);
            getView().findViewById(R.id.layout_retry).setVisibility(8);
            TextView textView;
            if (m14538c()) {
                if (!TextUtils.isEmpty(Ola.f11502s)) {
                    textView = (TextView) getView().findViewById(R.id.ola_money_kp);
                    textView.setText(Ola.f11502s);
                    textView.setVisibility(0);
                }
            } else if (!TextUtils.isEmpty(Ola.f11503t)) {
                textView = (TextView) getView().findViewById(R.id.ola_money_kp);
                textView.setText(Ola.f11503t);
                textView.setVisibility(0);
            }
        }
    }

    private void m14510f() {
        HashMap hashMap = new HashMap();
        hashMap.put("Local taxi category", this.f10980k);
        if (Utils.m14924g(this.f10991v)) {
            hashMap.put("Booking city", this.f10991v);
        } else {
            hashMap.put("Booking city", "NA");
        }
        Sherlock.m13342a("Ins KP ride booked", hashMap, null);
        if (getView() != null) {
            if (m14538c()) {
                Localytics.tagEvent("AUTO stock out");
            } else {
                Localytics.tagEvent("KP stock out");
            }
            getView().findViewById(R.id.layout_progress).setVisibility(8);
            View findViewById = getView().findViewById(R.id.layout_retry);
            findViewById.setVisibility(0);
            if (Ola.f11494k) {
                ((Button) getView().findViewById(R.id.button_retry_cancel)).setText("CANCEL");
                if (!this.f10994y) {
                    ((Button) getView().findViewById(R.id.button_retry)).setClickable(false);
                }
            } else {
                ((Button) getView().findViewById(R.id.button_retry_cancel)).setText("OK");
                ((Button) getView().findViewById(R.id.button_retry)).setClickable(true);
            }
            if (Utils.m14909a(this.f10979j.getApplicationContext())) {
                TextView textView = (TextView) findViewById.findViewById(R.id.no_kp_text);
                TextView textView2 = (TextView) findViewById.findViewById(R.id.no_kp_sub_text);
                if (Ola.f11494k) {
                    textView.setText("Booking Cancelled!");
                    if (m14538c()) {
                        textView2.setText(Ola.f11492i != null ? Ola.f11492i : "Regret to inform that driver cancelled the booking");
                        return;
                    } else {
                        textView2.setText(Ola.f11492i != null ? Ola.f11492i : "Regret to inform that driver cancelled the booking");
                        return;
                    }
                } else if (m14538c()) {
                    textView.setText("No Autos available\nnear you!");
                    textView2.setText("How about choosing\nanother category?");
                    return;
                } else {
                    textView.setText("No Kaali Peelis\nnear you!");
                    textView2.setText("How about choosing\nanother category?");
                    return;
                }
            }
            ((TextView) findViewById.findViewById(R.id.no_kp_text)).setText("No internet connection available!");
            ((TextView) findViewById.findViewById(R.id.no_kp_sub_text)).setText("Please check your settings or try again later.");
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.kp_button_cancel:
                this.f10989t = true;
                m14514h();
            case R.id.button_retry_cancel:
                if (this.f10993x) {
                    Localytics.tagEvent("Cancelled booking_Cancel clicked");
                    m14500a("cancel");
                    return;
                }
                this.f10989t = true;
                this.f10979j.onBackPressed();
            case R.id.button_retry:
                if (Utils.m14909a(this.f10979j.getApplicationContext())) {
                    Sherlock.m13334a("Ins KP ride booked");
                    if (this.f10993x) {
                        Localytics.tagEvent("Cancelled Booking_Retry clicked");
                        m14500a("retry");
                        return;
                    }
                    m14513g();
                    return;
                }
                m14501a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            default:
        }
    }

    private void m14513g() {
        m14508e();
        this.f10975f.m14814d();
        this.f10977h.setEnabled(false);
        m14523l();
    }

    private void m14500a(String str) {
        CharSequence userId = ((OlaApp) this.f10979j.getApplication()).m12878a().m13209c().getUserId();
        if (TextUtils.isEmpty(userId)) {
            PreferenceManager.getDefaultSharedPreferences(this.f10979j).getString(Constants.PREF_USER_ID, null);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.USER_ID, userId);
            if (this.f10987r != null) {
                jSONObject.put(Constants.ARG_KRN, this.f10987r);
            }
            jSONObject.put("text", str);
            this.f10973d = str;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            StringEntity stringEntity = new StringEntity(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        this.f10969D.m13245i(new WeakReference(this.f10974e), this.f10987r, str, f10965a);
    }

    private void m14514h() {
        String str;
        if (m14538c()) {
            str = "Auto Rickshaw User Cancellation";
        } else {
            str = "KP User Cancellation";
        }
        m14519j();
        this.f10969D.m13184a(new WeakReference(this.f10972c), this.f10986q, str, this.f10980k, f10965a);
    }

    private void m14517i() {
        Map hashMap = new HashMap();
        hashMap.put("Cancelled Reason", Ola.f11493j != null ? Ola.f11493j : Trace.NULL);
        Localytics.tagEvent("Driver Cancelled Booking", hashMap);
    }

    private void m14519j() {
        if (m14538c()) {
            Localytics.tagEvent("AUTO request cancelled");
        } else {
            Localytics.tagEvent("KP request cancelled");
        }
    }

    private void m14520k() {
        if (this.f10979j != null) {
            try {
                this.f10979j.onBackPressed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void m14523l() {
        this.f10969D.m13208b(new WeakReference(this.f10967B), this.f10980k, String.valueOf(this.f10981l.f7554a), String.valueOf(this.f10981l.f7555b), this.f10983n, this.f10982m, this.f10984o, this.f10985p, ((OlaApp) this.f10979j.getApplication()).m12878a().m13209c().getUserAddress(), f10965a);
    }

    public void onEventMainThread(ah ahVar) {
        boolean isConnected = ahVar.isConnected();
        OLog.m13311a("Received DataConnectivityEvent. Connected? - " + isConnected, new Object[0]);
        if (!isConnected && !this.f10966A) {
            this.f10966A = true;
            this.f10975f.m14812b();
            m14501a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
        }
    }

    private void m14525m() {
        m14527n();
        this.f10978i.post(this.f10971b);
    }

    private void m14527n() {
        this.f10978i.removeCallbacks(this.f10971b);
        this.f10969D.m13169a(f10965a);
    }

    private void m14528o() {
        this.f10969D.m13173a(new WeakReference(this.f10992w), this.f10981l, f10965a);
    }

    private void m14530p() {
        Object hashMap = new HashMap();
        if (m14538c()) {
            hashMap.put("Cab category", "Auto");
            Localytics.tagEvent("AUTO confirmed");
            Apsalar.event("AUTO confirmed");
        } else {
            hashMap.put("Cab category", "KP");
            Localytics.tagEvent("KP confirmed");
            Apsalar.event("KP confirmed");
        }
        hashMap.put("Discount State", "No coupon");
        this.f10990u.m12859a("fb_mobile_purchase", hashMap);
    }

    private void m14501a(String str, String str2) {
        View inflate = ((LayoutInflater) this.f10979j.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10979j).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new LocalTaxiBookingRequestFragment(this, create));
        create.show();
    }

    protected void m14537b() {
        Fragment findFragmentById = this.f10979j.getSupportFragmentManager().findFragmentById(R.id.container);
        if (!isDetached() && (findFragmentById instanceof LocalTaxiBookingRequestFragment)) {
            this.f10979j.getSupportFragmentManager().popBackStack(null, 1);
        }
    }

    public boolean m14538c() {
        return "local_auto".equalsIgnoreCase(this.f10980k) || "auto".equalsIgnoreCase(this.f10980k);
    }

    public boolean m14539d() {
        return this.f10989t;
    }
}
