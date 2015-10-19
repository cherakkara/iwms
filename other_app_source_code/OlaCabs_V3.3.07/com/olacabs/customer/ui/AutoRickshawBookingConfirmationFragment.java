package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.m4b.maps.model.LatLng;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p075a.AnalyticsHelper;
import com.olacabs.customer.p075a.AnalyticsManager;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.b */
public class AutoRickshawBookingConfirmationFragment extends Fragment implements OnClickListener, TraceFieldInterface {
    private static final JoinPoint f10503u = null;
    private static final JoinPoint f10504v = null;
    private View f10505a;
    private TextView f10506b;
    private TextView f10507c;
    private TextView f10508d;
    private LatLng f10509e;
    private String f10510f;
    private String f10511g;
    private String f10512h;
    private int f10513i;
    private View f10514j;
    private TextView f10515k;
    private MainActivity f10516l;
    private String f10517m;
    private String f10518n;
    private boolean f10519o;
    private String f10520p;
    private DataManager f10521q;
    private AnalyticsManager f10522r;
    private AnalyticsHelper f10523s;
    private Button f10524t;

    /* renamed from: com.olacabs.customer.ui.b.1 */
    class AutoRickshawBookingConfirmationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10494a;
        final /* synthetic */ AutoRickshawBookingConfirmationFragment f10495b;

        AutoRickshawBookingConfirmationFragment(AutoRickshawBookingConfirmationFragment autoRickshawBookingConfirmationFragment, AlertDialog alertDialog) {
            this.f10495b = autoRickshawBookingConfirmationFragment;
            this.f10494a = alertDialog;
        }

        public void onClick(View view) {
            this.f10494a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.b.2 */
    class AutoRickshawBookingConfirmationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10496a;
        final /* synthetic */ EditText f10497b;
        final /* synthetic */ EditText f10498c;
        final /* synthetic */ AutoRickshawBookingConfirmationFragment f10499d;

        AutoRickshawBookingConfirmationFragment(AutoRickshawBookingConfirmationFragment autoRickshawBookingConfirmationFragment, AlertDialog alertDialog, EditText editText, EditText editText2) {
            this.f10499d = autoRickshawBookingConfirmationFragment;
            this.f10496a = alertDialog;
            this.f10497b = editText;
            this.f10498c = editText2;
        }

        public void onClick(View view) {
            this.f10496a.dismiss();
            ((InputMethodManager) this.f10499d.f10516l.getSystemService("input_method")).hideSoftInputFromWindow(this.f10497b.getWindowToken(), 0);
            this.f10499d.f10512h = this.f10497b.getText().toString().trim();
            this.f10499d.f10511g = this.f10498c.getText().toString().trim();
            if (this.f10499d.f10512h.trim().isEmpty() && this.f10499d.f10511g.trim().isEmpty()) {
                this.f10499d.f10515k.setText("ADD LANDMARK");
                this.f10499d.f10515k.setSelected(false);
                return;
            }
            this.f10499d.f10515k.setText("Edit Landmark");
            this.f10499d.f10515k.setSelected(true);
        }
    }

    /* renamed from: com.olacabs.customer.ui.b.3 */
    class AutoRickshawBookingConfirmationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10500a;
        final /* synthetic */ EditText f10501b;
        final /* synthetic */ AutoRickshawBookingConfirmationFragment f10502c;

        AutoRickshawBookingConfirmationFragment(AutoRickshawBookingConfirmationFragment autoRickshawBookingConfirmationFragment, AlertDialog alertDialog, EditText editText) {
            this.f10502c = autoRickshawBookingConfirmationFragment;
            this.f10500a = alertDialog;
            this.f10501b = editText;
        }

        public void onClick(View view) {
            if (this.f10502c.f10512h.trim().isEmpty() && this.f10502c.f10511g.trim().isEmpty()) {
                this.f10502c.f10515k.setText("ADD LANDMARK");
                this.f10502c.f10515k.setSelected(false);
            } else {
                this.f10502c.f10515k.setText("Edit Landmark");
            }
            this.f10500a.dismiss();
            ((InputMethodManager) this.f10502c.f10516l.getSystemService("input_method")).hideSoftInputFromWindow(this.f10501b.getWindowToken(), 0);
        }
    }

    static {
        AutoRickshawBookingConfirmationFragment.m14143f();
    }

    private static void m14143f() {
        Factory factory = new Factory("AutoRickshawBookingConfirmationFragment.java", AutoRickshawBookingConfirmationFragment.class);
        f10503u = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.AutoRickshawBookingConfirmationFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), (int) HttpStatus.SC_SWITCHING_PROTOCOLS);
        f10504v = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.AutoRickshawBookingConfirmationFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 123);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    public AutoRickshawBookingConfirmationFragment() {
        this.f10511g = Trace.NULL;
        this.f10512h = Trace.NULL;
    }

    public static AutoRickshawBookingConfirmationFragment m14133a(double d, double d2, String str, String str2, String str3, boolean z) {
        AutoRickshawBookingConfirmationFragment autoRickshawBookingConfirmationFragment = new AutoRickshawBookingConfirmationFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble("user_latitude", d);
        bundle.putDouble("user_longitude", d2);
        bundle.putString("kp_panel_text", str);
        bundle.putString("kp_panel_sub_text", str2);
        bundle.putBoolean("is_ride_now", z);
        bundle.putString("current_city", str3);
        autoRickshawBookingConfirmationFragment.setArguments(bundle);
        return autoRickshawBookingConfirmationFragment;
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        try {
            if (this.f10516l != null) {
                this.f10516l.f9699d = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("b");
        try {
            TraceMachine.enterMethod(this._nr_trace, "b#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "b#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10503u, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.f10509e = new LatLng(getArguments().getDouble("user_latitude"), getArguments().getDouble("user_longitude"));
            this.f10517m = getArguments().getString("kp_panel_text");
            this.f10518n = getArguments().getString("kp_panel_sub_text");
            this.f10519o = getArguments().getBoolean("is_ride_now");
            this.f10520p = getArguments().getString("current_city");
        }
        OlaApp olaApp = (OlaApp) getActivity().getApplication();
        this.f10521q = olaApp.m12878a();
        this.f10522r = olaApp.m12879b();
        Localytics.tagScreen("AUTO confirmation");
        this.f10516l = (MainActivity) getActivity();
        this.f10523s = this.f10522r.m12867b(getActivity());
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "b#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "b#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10504v, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_auto_rickshaw_booking_confirmation, viewGroup, false);
        this.f10505a = inflate.findViewById(R.id.auto_rickshaw_drop_loc_layout);
        this.f10506b = (TextView) inflate.findViewById(R.id.textView_drop_location);
        this.f10514j = inflate.findViewById(R.id.auto_rickshaw_landmark_layout);
        this.f10515k = (TextView) inflate.findViewById(R.id.textView_landmark);
        this.f10507c = (TextView) inflate.findViewById(R.id.auto_panel_txt);
        this.f10508d = (TextView) inflate.findViewById(R.id.auto_panel_sub_txt);
        this.f10507c.setText(this.f10517m);
        this.f10508d.setText(this.f10518n);
        this.f10524t = (Button) inflate.findViewById(R.id.button_ride_conform);
        inflate.findViewById(R.id.button_ride_conform).setOnClickListener(this);
        inflate.findViewById(R.id.button_ride_cancel).setOnClickListener(this);
        this.f10505a.setOnClickListener(this);
        this.f10506b.setSelected(false);
        this.f10514j.setOnClickListener(this);
        this.f10515k.setSelected(false);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.measure(0, 0);
        ((BookingFragment) getParentFragment()).m14347a(view.getMeasuredHeight());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.auto_rickshaw_drop_loc_layout:
                m14139c();
            case R.id.auto_rickshaw_landmark_layout:
                m14142e();
            case R.id.button_ride_cancel:
                m14144a();
            case R.id.button_ride_conform:
                Sherlock.m13334a("Ins KP ride booked");
                if (this.f10510f == null || this.f10513i == 0) {
                    this.f10524t.setEnabled(false);
                    m14141d();
                    return;
                }
                m14137b();
                BookingFragment bookingFragment = (BookingFragment) getParentFragment();
                bookingFragment.m14361b(false);
                bookingFragment.m14376n();
                ((MainActivity) getActivity()).m13600a("auto", this.f10509e, this.f10510f, this.f10513i + Trace.NULL, this.f10512h, this.f10511g, this.f10520p);
            default:
        }
    }

    private void m14137b() {
        Map hashMap = new HashMap();
        hashMap.put("Booking type", this.f10519o ? "Ride now" : "Ride later");
        hashMap.put("Cab category", "auto");
        hashMap.put("Discount State", "No coupon");
        hashMap.put("City name", this.f10520p);
        this.f10523s.m12859a("fb_mobile_add_to_cart", hashMap);
        Localytics.tagEvent("AUTO selected");
    }

    private void m14139c() {
        ((MainActivity) getActivity()).m13604b(this.f10509e.f7554a, this.f10509e.f7555b);
    }

    public void m14144a() {
        BookingFragment bookingFragment = (BookingFragment) getParentFragment();
        bookingFragment.m14379q();
        bookingFragment.m14376n();
    }

    private void m14141d() {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText("Drop point missing!");
        ((TextView) inflate.findViewById(R.id.item_message)).setText("Please select a drop point to continue");
        inflate.findViewById(R.id.button_ok).setOnClickListener(new AutoRickshawBookingConfirmationFragment(this, create));
        create.setCancelable(false);
        create.show();
        this.f10524t.setEnabled(true);
    }

    public void m14145a(String str, int i) {
        if (str != null) {
            this.f10506b.setText(str.toUpperCase());
            this.f10506b.setSelected(true);
            this.f10510f = str;
            this.f10513i = i;
        }
    }

    private void m14142e() {
        View inflate = LayoutInflater.from(this.f10516l).inflate(R.layout.view_dialog_landmark_detail, null, false);
        AlertDialog create = new Builder(this.f10516l).setView(inflate).create();
        Button button = (Button) inflate.findViewById(R.id.button_action);
        ((TextView) inflate.findViewById(R.id.item_header)).setText("ADD LANDMARK");
        EditText editText = (EditText) inflate.findViewById(R.id.edit_text_drop);
        EditText editText2 = (EditText) inflate.findViewById(R.id.edit_text_pickup);
        editText.setText(this.f10512h);
        editText2.setText(this.f10511g);
        button.setOnClickListener(new AutoRickshawBookingConfirmationFragment(this, create, editText, editText2));
        inflate.findViewById(R.id.button_cancel).setOnClickListener(new AutoRickshawBookingConfirmationFragment(this, create, editText));
        create.getWindow().setSoftInputMode(4);
        create.show();
    }
}
