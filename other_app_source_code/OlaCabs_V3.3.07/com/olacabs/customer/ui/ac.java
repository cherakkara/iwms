package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.TransportMediator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.m4b.maps.model.LatLng;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.CityBaseCarModelDetailsResponse;
import com.olacabs.customer.p076d.aa;
import com.olacabs.customer.p076d.ab;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.cr;
import com.olacabs.customer.p076d.cs;
import com.olacabs.customer.p078c.FallBacks;
import com.olacabs.customer.ui.widgets.ErrorView.ErrorView;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* compiled from: RideEstimateFragment */
public class ac extends Fragment implements OnClickListener, ErrorView, TraceFieldInterface {
    private static final JoinPoint f10287I = null;
    private static final JoinPoint f10288J = null;
    public static final String f10289a;
    private CityBaseCarModelDetailsResponse f10290A;
    private DataManager f10291B;
    private boolean f10292C;
    private cr f10293D;
    private boolean f10294E;
    private String f10295F;
    private aj f10296G;
    private aj f10297H;
    private LinearLayout f10298b;
    private ScrollView f10299c;
    private double f10300d;
    private double f10301e;
    private String f10302f;
    private double f10303g;
    private double f10304h;
    private String f10305i;
    private String f10306j;
    private String f10307k;
    private long f10308l;
    private String f10309m;
    private RideEstimateFragment f10310n;
    private LatLng f10311o;
    private RelativeLayout f10312p;
    private TextView f10313q;
    private TextView f10314r;
    private TextView f10315s;
    private TextView f10316t;
    private TextView f10317u;
    private TextView f10318v;
    private ImageView f10319w;
    private RideEstimateActivity f10320x;
    private TextView f10321y;
    private ImageView f10322z;

    /* renamed from: com.olacabs.customer.ui.ac.1 */
    class RideEstimateFragment implements aj {
        final /* synthetic */ ac f10279a;

        RideEstimateFragment(ac acVar) {
            this.f10279a = acVar;
        }

        public void onFailure(Throwable th) {
            if (this.f10279a.isAdded()) {
                this.f10279a.m14035a(this.f10279a.getActivity().getString(R.string.generic_failure_desc), this.f10279a.getActivity().getString(R.string.generic_failure_header));
            }
        }

        public void onSuccess(Object obj) {
            cs csVar = (cs) obj;
            if (csVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                if (csVar.getSurchargeText() != null) {
                    this.f10279a.f10294E = csVar.isSurchargeApplicable();
                    this.f10279a.f10295F = csVar.getSurchargeText();
                }
                this.f10279a.f10293D = csVar.getRideEstimate();
                if (this.f10279a.f10293D != null) {
                    this.f10279a.m14027c();
                    this.f10279a.f10312p.setVisibility(8);
                }
            } else if (csVar.getHeader() != null && csVar.getText() != null) {
                this.f10279a.m14035a(csVar.getText(), csVar.getHeader());
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.ac.2 */
    class RideEstimateFragment implements aj {
        final /* synthetic */ ac f10280a;

        RideEstimateFragment(ac acVar) {
            this.f10280a = acVar;
        }

        public void onFailure(Throwable th) {
            if (this.f10280a.isAdded()) {
                this.f10280a.m14035a(this.f10280a.getActivity().getString(R.string.generic_failure_desc), this.f10280a.getActivity().getString(R.string.generic_failure_header));
            }
        }

        public void onSuccess(Object obj) {
            if (this.f10280a.isAdded()) {
                ab abVar = (ab) obj;
                if (abVar != null && abVar.getStatus().equalsIgnoreCase("SUCCESS") && abVar.getCarModels() != null) {
                    this.f10280a.f10290A = abVar.getCarModels().getCategoryDetails(this.f10280a.f10306j);
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.ac.3 */
    class RideEstimateFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10281a;
        final /* synthetic */ ac f10282b;

        RideEstimateFragment(ac acVar, AlertDialog alertDialog) {
            this.f10282b = acVar;
            this.f10281a = alertDialog;
        }

        public void onClick(View view) {
            this.f10281a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.ac.4 */
    class RideEstimateFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10283a;
        final /* synthetic */ ac f10284b;

        RideEstimateFragment(ac acVar, AlertDialog alertDialog) {
            this.f10284b = acVar;
            this.f10283a = alertDialog;
        }

        public void onClick(View view) {
            this.f10284b.f10320x.m13804a(this.f10284b.f10300d, this.f10284b.f10301e, 1);
            this.f10283a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.ac.a */
    private class RideEstimateFragment extends AsyncTask<LatLng, Void, String> implements TraceFieldInterface {
        public Trace _nr_trace;
        Context f10285a;
        final /* synthetic */ ac f10286b;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            try {
                TraceMachine.enterMethod(this._nr_trace, "ac$a#doInBackground", null);
            } catch (NoSuchFieldError e) {
                while (true) {
                    TraceMachine.enterMethod(null, "ac$a#doInBackground", null);
                    break;
                }
            }
            String a = m14012a((LatLng[]) objArr);
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return a;
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            try {
                TraceMachine.enterMethod(this._nr_trace, "ac$a#onPostExecute", null);
            } catch (NoSuchFieldError e) {
                while (true) {
                    TraceMachine.enterMethod(null, "ac$a#onPostExecute", null);
                    break;
                }
            }
            m14013a((String) obj);
            TraceMachine.exitMethod();
        }

        public RideEstimateFragment(ac acVar, Context context) {
            this.f10286b = acVar;
            this.f10285a = context;
        }

        protected void m14013a(String str) {
            super.onPostExecute(str);
            this.f10286b.f10302f = str;
        }

        protected String m14012a(LatLng... latLngArr) {
            List fromLocation;
            Geocoder geocoder = new Geocoder(this.f10285a, Locale.getDefault());
            LatLng latLng = latLngArr[0];
            try {
                fromLocation = geocoder.getFromLocation(latLng.f7554a, latLng.f7555b, 1);
            } catch (IOException e) {
                e.printStackTrace();
                fromLocation = null;
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                fromLocation = null;
            }
            if (fromLocation == null || fromLocation.size() <= 0) {
                String a;
                String str = com.newrelic.agent.android.instrumentation.Trace.NULL;
                try {
                    a = new FallBacks().m13350a(latLng.f7554a + "," + latLng.f7555b);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    a = str;
                }
                if (a == null || a.isEmpty()) {
                    return "No address found";
                }
                return a;
            }
            Address address = (Address) fromLocation.get(0);
            String str2 = "%s, %s, %s";
            Object[] objArr = new Object[3];
            objArr[0] = address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : com.newrelic.agent.android.instrumentation.Trace.NULL;
            objArr[1] = address.getLocality();
            objArr[2] = address.getCountryName();
            return String.format(str2, objArr);
        }
    }

    private static void m14033f() {
        Factory factory = new Factory("RideEstimateFragment.java", ac.class);
        f10287I = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.RideEstimateFragment", "android.os.Bundle", "savedInstanceState", com.newrelic.agent.android.instrumentation.Trace.NULL, "void"), 161);
        f10288J = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.RideEstimateFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", com.newrelic.agent.android.instrumentation.Trace.NULL, "android.view.View"), 192);
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
        m14033f();
        f10289a = ac.class.getSimpleName();
    }

    public ac() {
        this.f10292C = false;
        this.f10296G = new RideEstimateFragment(this);
        this.f10297H = new RideEstimateFragment(this);
    }

    public static ac m14018a(double d, double d2, double d3, double d4, String str, String str2, String str3, long j, String str4) {
        ac acVar = new ac();
        Bundle bundle = new Bundle();
        bundle.putDouble("pickUpLat", d);
        bundle.putDouble("pickUpLng", d2);
        bundle.putDouble("dropLat", d3);
        bundle.putDouble("dropLng", d4);
        bundle.putString("dropAddress", str);
        bundle.putString("carCategory", str2);
        bundle.putString("pickUpMode", str3);
        bundle.putLong("pickUpTime", j);
        bundle.putString("couponCode", str4);
        acVar.setArguments(bundle);
        return acVar;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("ac");
        try {
            TraceMachine.enterMethod(this._nr_trace, "ac#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ac#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10287I, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f10300d = arguments.getDouble("pickUpLat");
            this.f10301e = arguments.getDouble("pickUpLng");
            this.f10303g = arguments.getDouble("dropLat");
            this.f10304h = arguments.getDouble("dropLng");
            this.f10305i = arguments.getString("dropAddress");
            this.f10306j = arguments.getString("carCategory");
            this.f10307k = arguments.getString("pickUpMode");
            this.f10308l = arguments.getLong("pickUpTime");
            this.f10309m = arguments.getString("couponCode");
            this.f10311o = new LatLng(this.f10300d, this.f10301e);
            if (this.f10310n == null || this.f10310n.getStatus() == Status.FINISHED || this.f10310n.getStatus() == Status.RUNNING) {
                this.f10310n = new RideEstimateFragment(this, getActivity());
            }
            RideEstimateFragment rideEstimateFragment = this.f10310n;
            LatLng[] latLngArr = new LatLng[]{this.f10311o};
            if (rideEstimateFragment instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(rideEstimateFragment, latLngArr);
            } else {
                rideEstimateFragment.execute(latLngArr);
            }
        }
        this.f10291B = ((OlaApp) getActivity().getApplication()).m12878a();
        Localytics.tagScreen("ride estimate");
        this.f10320x = (RideEstimateActivity) getActivity();
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "ac#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ac#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10288J, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_ride_estimate, viewGroup, false);
        this.f10321y = (TextView) inflate.findViewById(R.id.fare_break_up_text);
        this.f10321y.setOnClickListener(this);
        inflate.findViewById(R.id.fare_break_up_text_1).setOnClickListener(this);
        this.f10298b = (LinearLayout) inflate.findViewById(R.id.layout_ride_card);
        this.f10299c = (ScrollView) inflate.findViewById(R.id.scroll_ride_estimate);
        this.f10312p = (RelativeLayout) inflate.findViewById(R.id.empty_view_layout);
        this.f10312p.setVisibility(0);
        this.f10313q = (TextView) inflate.findViewById(R.id.pick_up_address_text);
        this.f10314r = (TextView) inflate.findViewById(R.id.drop_address_text);
        this.f10315s = (TextView) inflate.findViewById(R.id.total_fare_text);
        this.f10316t = (TextView) inflate.findViewById(R.id.approx_time_text);
        this.f10317u = (TextView) inflate.findViewById(R.id.item_ok_button);
        this.f10317u.setOnClickListener(this);
        this.f10319w = (ImageView) inflate.findViewById(R.id.ride_cab_image);
        this.f10318v = (TextView) inflate.findViewById(R.id.surcharge_note);
        inflate.findViewById(R.id.drop_address_text).setOnClickListener(this);
        this.f10322z = (ImageView) inflate.findViewById(R.id.rate_card_image);
        this.f10322z.setOnClickListener(this);
        if (Utils.m14909a(this.f10320x.getApplicationContext())) {
            m14029d();
            m14031e();
        } else {
            m14020a();
        }
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14020a() {
        View inflate = ((LayoutInflater) this.f10320x.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10320x).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(Constants.CONNECTION_TIME_OUT_HEADER);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(Constants.NO_NETWORK_TEXT);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new RideEstimateFragment(this, create));
        create.show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drop_address_text:
                this.f10320x.m13804a(this.f10300d, this.f10301e, 1);
            case R.id.item_ok_button:
                getActivity().finish();
            case R.id.fare_break_up_text:
                this.f10298b.setVisibility(0);
                this.f10321y.setVisibility(4);
                this.f10299c.setSmoothScrollingEnabled(true);
                this.f10299c.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
            case R.id.rate_card_image:
                if (this.f10290A != null && this.f10290A.isValid()) {
                    m14021a(view);
                }
            case R.id.fare_break_up_text_1:
                this.f10298b.setVisibility(4);
                this.f10321y.setVisibility(0);
                this.f10299c.setSmoothScrollingEnabled(true);
                this.f10299c.fullScroll(33);
            default:
        }
    }

    private void m14021a(View view) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_cab_rate_card_dynamic, null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.item_category);
        TextView textView2 = (TextView) inflate.findViewById(R.id.item_options);
        TextView textView3 = (TextView) inflate.findViewById(R.id.item_desclaimer_text1);
        inflate.findViewById(R.id.item_category_line).setVisibility(8);
        textView.setVisibility(8);
        textView2.setVisibility(8);
        textView3.setVisibility(8);
        m14024b(inflate);
        com.olacabs.customer.ui.widgets.ErrorView a = new ErrorView(getActivity()).m14826b(getView()).m14823a(inflate).m14825a();
        a.m14833a((ErrorView) this);
        if (!this.f10292C) {
            this.f10292C = true;
            a.m14831a(view);
        }
    }

    private void m14024b(View view) {
        String string = this.f10320x.getResources().getString(R.string.rs_symbol);
        TextView textView = (TextView) view.findViewById(R.id.item_base_fair_text);
        TextView textView2 = (TextView) view.findViewById(R.id.item_base_fair);
        TextView textView3 = (TextView) view.findViewById(R.id.item_rate_per_km);
        TextView textView4 = (TextView) view.findViewById(R.id.item_rate_per_km_text);
        TextView textView5 = (TextView) view.findViewById(R.id.item_rate_per_wait_minute);
        TextView textView6 = (TextView) view.findViewById(R.id.item_rate_per_wait_minute_text);
        TextView textView7 = (TextView) view.findViewById(R.id.fare_header_txt);
        TextView textView8 = (TextView) view.findViewById(R.id.item_desclaimer_text);
        CharSequence header = this.f10290A != null ? !com.newrelic.agent.android.instrumentation.Trace.NULL.equalsIgnoreCase(this.f10290A.getHeader()) ? this.f10290A.getHeader() : getActivity().getString(R.string.fare_breakup_header) : getActivity().getString(R.string.fare_breakup_header);
        textView7.setText(header);
        CharSequence note = this.f10290A != null ? !com.newrelic.agent.android.instrumentation.Trace.NULL.equalsIgnoreCase(this.f10290A.getNote()) ? this.f10290A.getNote() : getActivity().getString(R.string.airport_rates_text) : getActivity().getString(R.string.airport_rates_text);
        textView8.setText(note);
        textView.setText(((aa) this.f10290A.getCityBaseFareBreakUp().get(0)).getDisplayText());
        textView2.setText(string + ((aa) this.f10290A.getCityBaseFareBreakUp().get(0)).getValue());
        textView4.setText(((aa) this.f10290A.getCityBaseFareBreakUp().get(1)).getDisplayText());
        textView3.setText(string + ((aa) this.f10290A.getCityBaseFareBreakUp().get(1)).getValue() + "/km");
        textView6.setText(((aa) this.f10290A.getCityBaseFareBreakUp().get(2)).getDisplayText());
        textView5.setText(string + ((aa) this.f10290A.getCityBaseFareBreakUp().get(2)).getValue() + "/min");
    }

    private void m14027c() {
        String str;
        if (this.f10306j.equalsIgnoreCase("economy_sedan")) {
            this.f10319w.setImageResource(R.drawable.ride_estimate_sedan_cab_icon);
        } else if (this.f10306j.equalsIgnoreCase("compact")) {
            this.f10319w.setImageResource(R.drawable.ride_estimate_mini_cab_icon);
        } else if (this.f10306j.equalsIgnoreCase("luxury_sedan")) {
            this.f10319w.setImageResource(R.drawable.ride_estimate_luxury_cab_icon);
        } else if (this.f10306j.equalsIgnoreCase("ola_nano")) {
            this.f10319w.setImageResource(R.drawable.ride_estimate_nano_cab_icon);
        } else {
            this.f10319w.setImageResource(R.drawable.ride_estimate_sedan_cab_icon);
        }
        this.f10313q.setText(this.f10302f);
        this.f10314r.setText(this.f10305i);
        this.f10315s.setText(this.f10320x.getResources().getString(R.string.rs_symbol) + this.f10293D.getFare() + "-" + this.f10320x.getResources().getString(R.string.rs_symbol) + (Integer.parseInt(this.f10293D.getFare()) + ((Integer.parseInt(this.f10293D.getFare()) * 15) / 100)));
        if (this.f10293D.getApprox_travel_time() < 60.0d) {
            str = Math.round(this.f10293D.getApprox_travel_time()) + " mins";
        } else {
            int approx_travel_time = ((int) this.f10293D.getApprox_travel_time()) % 60;
            str = (((int) this.f10293D.getApprox_travel_time()) / 60) + " hrs " + approx_travel_time + " mins";
        }
        this.f10316t.setText(this.f10320x.getResources().getString(R.string.ride_estimate_approx_time) + " " + str);
        if (this.f10294E && this.f10291B.m13218d().isB() && this.f10308l < System.currentTimeMillis() / 1000) {
            this.f10318v.setVisibility(0);
            this.f10318v.setText(this.f10295F);
            return;
        }
        this.f10318v.setVisibility(8);
    }

    private void m14029d() {
        this.f10291B.m13187a(new WeakReference(this.f10296G), String.valueOf(this.f10300d), String.valueOf(this.f10301e), String.valueOf(this.f10303g), String.valueOf(this.f10304h), this.f10306j, this.f10309m, this.f10307k, String.valueOf(this.f10308l), f10289a);
    }

    protected void m14035a(String str, String str2) {
        View inflate = ((LayoutInflater) this.f10320x.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10320x).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str2);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new RideEstimateFragment(this, create));
        create.show();
    }

    private void m14031e() {
        if (!this.f10291B.m13218d().isB() || this.f10308l >= System.currentTimeMillis() / 1000) {
            this.f10291B.m13212c(new WeakReference(this.f10297H), Double.valueOf(this.f10300d), Double.valueOf(this.f10301e), "cityBasedRequestFareTag");
        } else {
            this.f10291B.m13174a(new WeakReference(this.f10297H), Double.valueOf(this.f10300d), Double.valueOf(this.f10301e), "cityBasedRequestFareTag");
        }
    }

    public void m14036b() {
        this.f10292C = false;
    }
}
