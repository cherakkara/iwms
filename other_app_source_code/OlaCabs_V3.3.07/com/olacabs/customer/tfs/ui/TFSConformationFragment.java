package com.olacabs.customer.tfs.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.apsalar.sdk.Apsalar;
import com.leanplum.Leanplum;
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
import com.olacabs.customer.p075a.AnalyticsHelper;
import com.olacabs.customer.p075a.AnalyticsManager;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.tfs.p081a.TFSBookingCreateResponse;
import com.olacabs.customer.tfs.p081a.TFSFareDetails;
import com.olacabs.customer.tfs.p081a.TFSFareResponse;
import com.olacabs.customer.ui.BookingFragment;
import com.olacabs.customer.ui.MainActivity;
import com.olacabs.customer.ui.widgets.ErrorView.ErrorView;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.tfs.ui.a */
public class TFSConformationFragment extends Fragment implements OnClickListener, TraceFieldInterface {
    public static final String f9527a;
    private static final JoinPoint f9528m = null;
    private static final JoinPoint f9529n = null;
    private AnalyticsHelper f9530b;
    private boolean f9531c;
    private String f9532d;
    private String f9533e;
    private int f9534f;
    private aj f9535g;
    private ProgressDialog f9536h;
    private MainActivity f9537i;
    private DataManager f9538j;
    private AnalyticsManager f9539k;
    private View f9540l;

    /* renamed from: com.olacabs.customer.tfs.ui.a.1 */
    class TFSConformationFragment implements aj {
        final /* synthetic */ TFSConformationFragment f9521a;

        /* renamed from: com.olacabs.customer.tfs.ui.a.1.1 */
        class TFSConformationFragment implements OnClickListener {
            final /* synthetic */ TFSBookingCreateResponse f9518a;
            final /* synthetic */ AlertDialog f9519b;
            final /* synthetic */ TFSConformationFragment f9520c;

            TFSConformationFragment(TFSConformationFragment tFSConformationFragment, TFSBookingCreateResponse tFSBookingCreateResponse, AlertDialog alertDialog) {
                this.f9520c = tFSConformationFragment;
                this.f9518a = tFSBookingCreateResponse;
                this.f9519b = alertDialog;
            }

            public void onClick(View view) {
                BookingFragment bookingFragment = (BookingFragment) this.f9520c.f9521a.getParentFragment();
                bookingFragment.m14357a(false, this.f9518a.getBooking_id());
                bookingFragment.m14376n();
                this.f9519b.dismiss();
            }
        }

        TFSConformationFragment(TFSConformationFragment tFSConformationFragment) {
            this.f9521a = tFSConformationFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13317d("Failed to Confirm", th);
            HashMap hashMap = new HashMap();
            hashMap.put("Booking city", Sherlock.m13349d(this.f9521a.f9533e));
            if (this.f9521a.isAdded()) {
                this.f9521a.f9536h.dismiss();
                Sherlock.m13338a("Ins TFS booked ride", "Failure", (VolleyError) th, this.f9521a.getActivity().getString(R.string.connection_timed_out), true, hashMap);
                this.f9521a.m13463a(this.f9521a.getActivity().getString(R.string.sos_ec_header), this.f9521a.getActivity().getString(R.string.connection_timed_out));
                Localytics.tagEvent("TIMED OUT");
                return;
            }
            Sherlock.m13338a("Ins TFS booked ride", "Failure", (VolleyError) th, this.f9521a.getActivity().getString(R.string.connection_timed_out), false, hashMap);
        }

        public void onSuccess(Object obj) {
            HashMap hashMap = new HashMap();
            hashMap.put("Booking city", Sherlock.m13349d(this.f9521a.f9533e));
            TFSBookingCreateResponse tFSBookingCreateResponse = (TFSBookingCreateResponse) obj;
            if (this.f9521a.isAdded()) {
                this.f9521a.f9536h.dismiss();
                if (tFSBookingCreateResponse.getStatus().equalsIgnoreCase("SUCCESS") && tFSBookingCreateResponse.isBookingConfirmed() && Utils.m14924g(tFSBookingCreateResponse.getBooking_id())) {
                    CharSequence text;
                    Sherlock.m13340a("Ins TFS booked ride", hashMap);
                    Map hashMap2 = new HashMap();
                    hashMap2.put("Booking type", "Ride now");
                    hashMap2.put("Cab category", this.f9521a.f9532d);
                    hashMap2.put("Discount State", "N/A");
                    hashMap2.put("City name", this.f9521a.f9533e);
                    Localytics.tagEvent("Booking Sheduled", hashMap2);
                    Apsalar.event("Booking Sheduled", new JSONObject(hashMap2));
                    Leanplum.track("booking scheduled", hashMap2);
                    View inflate = this.f9521a.getActivity().getLayoutInflater().inflate(R.layout.view_dialog_ok_button, null, false);
                    AlertDialog create = new Builder(this.f9521a.f9537i).setView(inflate).create();
                    ((TextView) inflate.findViewById(R.id.item_header)).setText(this.f9521a.getString(R.string.success));
                    TextView textView = (TextView) inflate.findViewById(R.id.item_message);
                    if (TextUtils.isEmpty(tFSBookingCreateResponse.getText())) {
                        text = tFSBookingCreateResponse.getText();
                    } else {
                        text = this.f9521a.getString(R.string.tfs_successful_booking_message);
                    }
                    textView.setText(text);
                    inflate.findViewById(R.id.button_ok).setOnClickListener(new TFSConformationFragment(this, tFSBookingCreateResponse, create));
                    create.setCancelable(false);
                    create.show();
                } else if (!Utils.m14924g(tFSBookingCreateResponse.getBooking_id())) {
                    Sherlock.m13338a("Ins TFS booked ride", "Failure", null, "booking id null", true, hashMap);
                    this.f9521a.m13463a(this.f9521a.getActivity().getString(R.string.sos_ec_header), this.f9521a.getActivity().getString(R.string.error_unable_to_connect_message));
                } else if (tFSBookingCreateResponse.isBookingConfirmed()) {
                    Sherlock.m13338a("Ins TFS booked ride", "Failure", null, this.f9521a.getActivity().getString(R.string.connection_timed_out), true, hashMap);
                    this.f9521a.m13463a(this.f9521a.getActivity().getString(R.string.sos_ec_header), this.f9521a.getActivity().getString(R.string.connection_timed_out));
                } else {
                    Sherlock.m13338a("Ins TFS booked ride", "Failure", null, "booking confirmed false", true, hashMap);
                    this.f9521a.m13463a(this.f9521a.getActivity().getString(R.string.sorry_header), this.f9521a.getActivity().getString(R.string.tfs_no_cab_available_message));
                }
            } else if (tFSBookingCreateResponse.getStatus().equalsIgnoreCase("SUCCESS") && tFSBookingCreateResponse.isBookingConfirmed() && Utils.m14924g(tFSBookingCreateResponse.getBooking_id())) {
                Sherlock.m13338a("Ins TFS booked ride", Constants.SOS_SUCCESS_HEADER_TEXT, null, null, false, hashMap);
            } else if (!Utils.m14924g(tFSBookingCreateResponse.getBooking_id())) {
                Sherlock.m13338a("Ins TFS booked ride", "Failure", null, "booking id null", false, hashMap);
            } else if (tFSBookingCreateResponse.isBookingConfirmed()) {
                Sherlock.m13338a("Ins TFS booked ride", "Failure", null, "Failed to add Fragment", false, hashMap);
            } else {
                Sherlock.m13338a("Ins TFS booked ride", "Failure", null, "booking confirmed false", false, hashMap);
            }
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.a.2 */
    class TFSConformationFragment implements OnClickListener {
        final /* synthetic */ TFSConformationFragment f9522a;

        TFSConformationFragment(TFSConformationFragment tFSConformationFragment) {
            this.f9522a = tFSConformationFragment;
        }

        public void onClick(View view) {
            this.f9522a.f9540l.setSelected(!this.f9522a.f9540l.isSelected());
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.a.3 */
    class TFSConformationFragment implements OnClickListener {
        final /* synthetic */ TFSConformationFragment f9523a;

        TFSConformationFragment(TFSConformationFragment tFSConformationFragment) {
            this.f9523a = tFSConformationFragment;
        }

        public void onClick(View view) {
            this.f9523a.f9537i.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f9523a.f9537i.getString(R.string.url_tfs_terms_and_conditions))));
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.a.4 */
    class TFSConformationFragment implements OnClickListener {
        final /* synthetic */ String f9524a;
        final /* synthetic */ AlertDialog f9525b;
        final /* synthetic */ TFSConformationFragment f9526c;

        TFSConformationFragment(TFSConformationFragment tFSConformationFragment, String str, AlertDialog alertDialog) {
            this.f9526c = tFSConformationFragment;
            this.f9524a = str;
            this.f9525b = alertDialog;
        }

        public void onClick(View view) {
            if (this.f9524a.equalsIgnoreCase(this.f9526c.getResources().getString(R.string.sos_ec_header)) || this.f9524a.equalsIgnoreCase(this.f9526c.getResources().getString(R.string.sorry_header))) {
                this.f9525b.dismiss();
                BookingFragment bookingFragment = (BookingFragment) this.f9526c.getParentFragment();
                bookingFragment.m14379q();
                bookingFragment.m14376n();
                return;
            }
            this.f9525b.dismiss();
        }
    }

    private static void m13467c() {
        Factory factory = new Factory("TFSConformationFragment.java", TFSConformationFragment.class);
        f9528m = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.tfs.ui.TFSConformationFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 186);
        f9529n = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.tfs.ui.TFSConformationFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 211);
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
        TFSConformationFragment.m13467c();
        f9527a = TFSConformationFragment.class.getSimpleName();
    }

    public TFSConformationFragment() {
        this.f9535g = new TFSConformationFragment(this);
    }

    public static TFSConformationFragment m13458a(String str, String str2, String str3, int i, boolean z) {
        TFSConformationFragment tFSConformationFragment = new TFSConformationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category_id", str);
        bundle.putString("category_name", str2);
        bundle.putInt("eta", i);
        bundle.putBoolean("is_ride_now", z);
        bundle.putString("current_city", str3);
        tFSConformationFragment.setArguments(bundle);
        return tFSConformationFragment;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("a");
        try {
            TraceMachine.enterMethod(this._nr_trace, "a#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "a#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f9528m, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            this.f9532d = arguments.getString("category_name");
            this.f9531c = arguments.getBoolean("is_ride_now");
            this.f9533e = arguments.getString("current_city");
            this.f9534f = arguments.getInt("eta");
        }
        this.f9537i = (MainActivity) getActivity();
        this.f9536h = new ProgressDialog(this.f9537i, R.style.TransparentProgressDialog);
        this.f9536h.setIndeterminateDrawable(this.f9537i.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f9536h.setCancelable(false);
        OlaApp olaApp = (OlaApp) this.f9537i.getApplication();
        this.f9538j = olaApp.m12878a();
        this.f9539k = olaApp.m12879b();
        this.f9530b = this.f9539k.m12867b(getActivity());
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "a#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "a#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f9529n, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_tfs_confirmation, viewGroup, false);
        inflate.findViewById(R.id.button_ride_cancel).setOnClickListener(this);
        inflate.findViewById(R.id.button_ride_conform).setOnClickListener(this);
        inflate.findViewById(R.id.tfs_rate_card).setOnClickListener(this);
        m13460a(inflate);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.measure(0, 0);
        ((BookingFragment) getParentFragment()).m14347a(view.getMeasuredHeight());
    }

    private void m13460a(View view) {
        TextView textView = (TextView) view.findViewById(R.id.eta);
        TextView textView2 = (TextView) view.findViewById(R.id.terms_conditions);
        this.f9540l = view.findViewById(R.id.layout_airport_drop);
        textView.setText(this.f9534f + " min \n from now");
        this.f9540l.setOnClickListener(new TFSConformationFragment(this));
        textView2.setOnClickListener(new TFSConformationFragment(this));
    }

    public void onClick(View view) {
        OLog.m13313b("Clicked", new Object[0]);
        switch (view.getId()) {
            case R.id.button_ride_cancel:
                BookingFragment bookingFragment = (BookingFragment) getParentFragment();
                bookingFragment.m14379q();
                bookingFragment.m14376n();
            case R.id.button_ride_conform:
                Sherlock.m13334a("Ins TFS booked ride");
                if (Utils.m14909a(this.f9537i.getApplicationContext())) {
                    m13470a();
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("Booking city", Sherlock.m13349d(this.f9533e));
                Sherlock.m13338a("Ins TFS booked ride", "Failure", null, Constants.NO_NETWORK_TEXT, true, hashMap);
                m13463a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            case R.id.tfs_rate_card:
                m13465b(view);
            default:
        }
    }

    public void m13470a() {
        Map hashMap = new HashMap();
        hashMap.put("Booking type", this.f9531c ? "Ride now" : "Ride later");
        hashMap.put("Cab category", this.f9532d);
        hashMap.put("Discount State", "N/A");
        hashMap.put("City name", this.f9533e);
        Localytics.tagEvent("Booking Conformed", hashMap);
        this.f9530b.m12859a("fb_mobile_add_to_cart", hashMap);
        Apsalar.event("Booking Conformed", new JSONObject(hashMap));
        this.f9536h.show();
        this.f9538j.m13194a(new WeakReference(this.f9535g), this.f9540l.isSelected(), ((BookingFragment) getParentFragment()).m14367e(), f9527a);
    }

    public void m13471b() {
        BookingFragment bookingFragment = (BookingFragment) getParentFragment();
        bookingFragment.m14379q();
        bookingFragment.m14376n();
    }

    private void m13463a(String str, String str2) {
        View inflate = ((LayoutInflater) this.f9537i.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f9537i).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new TFSConformationFragment(this, str, create));
        create.show();
    }

    private void m13465b(View view) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_cab_rate_card_dynamic, null, false);
        TFSFareResponse tfsFareModel = this.f9538j.m13218d().getTfsFareModel();
        if (tfsFareModel != null && tfsFareModel.getFares() != null && tfsFareModel.getFares().isValid()) {
            m13461a(inflate, tfsFareModel);
            new ErrorView(getActivity()).m14826b(getParentFragment().getView()).m14823a(inflate).m14825a().m14831a(view);
        }
    }

    private void m13461a(View view, TFSFareResponse tFSFareResponse) {
        TFSFareDetails fares = tFSFareResponse.getFares();
        String string = getResources().getString(R.string.rs_symbol);
        TextView textView = (TextView) view.findViewById(R.id.item_base_fair_text);
        TextView textView2 = (TextView) view.findViewById(R.id.item_base_fair);
        TextView textView3 = (TextView) view.findViewById(R.id.item_rate_per_km);
        TextView textView4 = (TextView) view.findViewById(R.id.item_rate_per_km_text);
        TextView textView5 = (TextView) view.findViewById(R.id.item_rate_per_wait_minute);
        TextView textView6 = (TextView) view.findViewById(R.id.item_rate_per_wait_minute_text);
        TextView textView7 = (TextView) view.findViewById(R.id.fare_header_txt);
        TextView textView8 = (TextView) view.findViewById(R.id.item_desclaimer_text);
        TextView textView9 = (TextView) view.findViewById(R.id.item_desclaimer_text1);
        ((TextView) view.findViewById(R.id.item_tfs_powered)).setVisibility(0);
        textView9.setVisibility(8);
        textView9 = (TextView) view.findViewById(R.id.item_category);
        TextView textView10 = (TextView) view.findViewById(R.id.item_options);
        textView9.setAllCaps(false);
        textView9.setText(this.f9537i.getString(R.string.taxi_for_sure));
        textView10.setText(this.f9537i.getString(R.string.tfs_hatchback));
        textView7.setText(this.f9537i.getString(R.string.ride_confirm_fare_breakup_header));
        textView8.setText(this.f9537i.getString(R.string.tfs_disclaimer));
        textView2.setText(string + " " + fares.getBaseFare());
        textView3.setText(string + " " + fares.getExtraKmFare() + "/km");
        textView5.setText(string + " " + fares.getTripTimePulseRate() + "/min");
        textView.setText("First " + fares.getBaseKm() + "Km");
        textView4.setText("After " + fares.getBaseKm() + "Km");
        textView6.setText(getString(R.string.tfs_ride_time_rate));
    }
}
