package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.olacabs.customer.p076d.bk;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.o */
public class KaaliPeeliConformationFragment extends Fragment implements OnClickListener, TraceFieldInterface {
    public static final String f10905a;
    private static final JoinPoint f10906y = null;
    private static final JoinPoint f10907z = null;
    private View f10908b;
    private LinearLayout f10909c;
    private TextView f10910d;
    private TextView f10911e;
    private TextView f10912f;
    private View f10913g;
    private TextView f10914h;
    private TextView f10915i;
    private String f10916j;
    private String f10917k;
    private String f10918l;
    private String f10919m;
    private boolean f10920n;
    private String f10921o;
    private LatLng f10922p;
    private String f10923q;
    private int f10924r;
    private MainActivity f10925s;
    private String f10926t;
    private ArrayList<bk> f10927u;
    private DataManager f10928v;
    private AnalyticsManager f10929w;
    private AnalyticsHelper f10930x;

    /* renamed from: com.olacabs.customer.ui.o.1 */
    class KaaliPeeliConformationFragment implements DialogInterface.OnClickListener {
        final /* synthetic */ ArrayList f10891a;
        final /* synthetic */ KaaliPeeliConformationFragment f10892b;

        KaaliPeeliConformationFragment(KaaliPeeliConformationFragment kaaliPeeliConformationFragment, ArrayList arrayList) {
            this.f10892b = kaaliPeeliConformationFragment;
            this.f10891a = arrayList;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            bk bkVar = (bk) this.f10891a.get(i);
            if (!this.f10892b.f10926t.equalsIgnoreCase(bkVar.getId())) {
                this.f10892b.f10926t = bkVar.getId();
                this.f10892b.f10910d.setText(bkVar.getDisplayName());
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.o.2 */
    class KaaliPeeliConformationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10893a;
        final /* synthetic */ KaaliPeeliConformationFragment f10894b;

        KaaliPeeliConformationFragment(KaaliPeeliConformationFragment kaaliPeeliConformationFragment, AlertDialog alertDialog) {
            this.f10894b = kaaliPeeliConformationFragment;
            this.f10893a = alertDialog;
        }

        public void onClick(View view) {
            this.f10893a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.o.3 */
    class KaaliPeeliConformationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10895a;
        final /* synthetic */ EditText f10896b;
        final /* synthetic */ EditText f10897c;
        final /* synthetic */ KaaliPeeliConformationFragment f10898d;

        KaaliPeeliConformationFragment(KaaliPeeliConformationFragment kaaliPeeliConformationFragment, AlertDialog alertDialog, EditText editText, EditText editText2) {
            this.f10898d = kaaliPeeliConformationFragment;
            this.f10895a = alertDialog;
            this.f10896b = editText;
            this.f10897c = editText2;
        }

        public void onClick(View view) {
            this.f10895a.dismiss();
            ((InputMethodManager) this.f10898d.f10925s.getSystemService("input_method")).hideSoftInputFromWindow(this.f10896b.getWindowToken(), 0);
            this.f10898d.f10917k = this.f10896b.getText().toString().trim();
            this.f10898d.f10916j = this.f10897c.getText().toString().trim();
            if (this.f10898d.f10917k.trim().isEmpty() && this.f10898d.f10916j.trim().isEmpty()) {
                this.f10898d.f10915i.setText("ADD LANDMARK");
                this.f10898d.f10915i.setSelected(false);
                return;
            }
            this.f10898d.f10915i.setText("Edit Landmark");
            this.f10898d.f10915i.setSelected(true);
        }
    }

    /* renamed from: com.olacabs.customer.ui.o.4 */
    class KaaliPeeliConformationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10899a;
        final /* synthetic */ EditText f10900b;
        final /* synthetic */ KaaliPeeliConformationFragment f10901c;

        KaaliPeeliConformationFragment(KaaliPeeliConformationFragment kaaliPeeliConformationFragment, AlertDialog alertDialog, EditText editText) {
            this.f10901c = kaaliPeeliConformationFragment;
            this.f10899a = alertDialog;
            this.f10900b = editText;
        }

        public void onClick(View view) {
            if (this.f10901c.f10917k.trim().isEmpty() && this.f10901c.f10916j.trim().isEmpty()) {
                this.f10901c.f10915i.setText("ADD LANDMARK");
                this.f10901c.f10915i.setSelected(false);
            } else {
                this.f10901c.f10915i.setText("Edit Landmark");
            }
            this.f10899a.dismiss();
            ((InputMethodManager) this.f10901c.f10925s.getSystemService("input_method")).hideSoftInputFromWindow(this.f10900b.getWindowToken(), 0);
        }
    }

    /* renamed from: com.olacabs.customer.ui.o.a */
    private class KaaliPeeliConformationFragment extends BaseAdapter {
        final /* synthetic */ KaaliPeeliConformationFragment f10902a;
        private ArrayList<bk> f10903b;
        private LayoutInflater f10904c;

        public KaaliPeeliConformationFragment(KaaliPeeliConformationFragment kaaliPeeliConformationFragment, Context context, ArrayList<bk> arrayList) {
            this.f10902a = kaaliPeeliConformationFragment;
            this.f10903b = arrayList;
            this.f10904c = LayoutInflater.from(kaaliPeeliConformationFragment.f10925s);
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public int getCount() {
            return this.f10903b.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.f10904c.inflate(17367043, viewGroup, false);
            }
            ((TextView) view.findViewById(16908308)).setText(((bk) this.f10903b.get(i)).getDisplayName().trim());
            return view;
        }
    }

    private static void m14480f() {
        Factory factory = new Factory("KaaliPeeliConformationFragment.java", KaaliPeeliConformationFragment.class);
        f10906y = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.KaaliPeeliConformationFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), (int) HttpStatus.SC_PROCESSING);
        f10907z = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.KaaliPeeliConformationFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 122);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    static {
        KaaliPeeliConformationFragment.m14480f();
        f10905a = KaaliPeeliConformationFragment.class.getSimpleName();
    }

    public static KaaliPeeliConformationFragment m14464a(double d, double d2, String str, String str2, String str3, boolean z) {
        KaaliPeeliConformationFragment kaaliPeeliConformationFragment = new KaaliPeeliConformationFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble("user_latitude", d);
        bundle.putDouble("user_longitude", d2);
        bundle.putString("kp_panel_text", str);
        bundle.putString("kp_panel_sub_text", str2);
        bundle.putBoolean("is_ride_now", z);
        bundle.putString("current_city", str3);
        kaaliPeeliConformationFragment.setArguments(bundle);
        return kaaliPeeliConformationFragment;
    }

    public KaaliPeeliConformationFragment() {
        this.f10916j = Trace.NULL;
        this.f10917k = Trace.NULL;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("o");
        try {
            TraceMachine.enterMethod(this._nr_trace, "o#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "o#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10906y, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.f10922p = new LatLng(getArguments().getDouble("user_latitude"), getArguments().getDouble("user_longitude"));
            this.f10918l = getArguments().getString("kp_panel_text");
            this.f10919m = getArguments().getString("kp_panel_sub_text");
            this.f10920n = getArguments().getBoolean("is_ride_now");
            this.f10921o = getArguments().getString("current_city");
        }
        this.f10925s = (MainActivity) getActivity();
        OlaApp olaApp = (OlaApp) this.f10925s.getApplication();
        this.f10928v = olaApp.m12878a();
        this.f10929w = olaApp.m12879b();
        Localytics.tagScreen("KP confirmation");
        this.f10930x = this.f10929w.m12867b(getActivity());
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "o#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "o#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10907z, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_kaali_peeli_conformation, viewGroup, false);
        this.f10909c = (LinearLayout) inflate.findViewById(R.id.kp_layout_category_name);
        this.f10910d = (TextView) inflate.findViewById(R.id.kp_item_category_name);
        this.f10911e = (TextView) inflate.findViewById(R.id.kp_panel_text);
        this.f10912f = (TextView) inflate.findViewById(R.id.kp_panel_sub_text);
        this.f10911e.setText(this.f10918l);
        this.f10912f.setText(this.f10919m);
        this.f10908b = inflate.findViewById(R.id.kali_peeli_drop_loc_layout);
        this.f10913g = inflate.findViewById(R.id.kali_peeli_landmark_layout);
        this.f10914h = (TextView) inflate.findViewById(R.id.textView_drop_location);
        this.f10915i = (TextView) inflate.findViewById(R.id.textView_landmark);
        inflate.findViewById(R.id.button_ride_conform).setOnClickListener(this);
        inflate.findViewById(R.id.button_ride_cancel).setOnClickListener(this);
        this.f10909c.setOnClickListener(this);
        this.f10908b.setOnClickListener(this);
        this.f10913g.setOnClickListener(this);
        this.f10914h.setSelected(false);
        m14471b();
        this.f10915i.setSelected(false);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.measure(0, 0);
        ((BookingFragment) getParentFragment()).m14347a(view.getMeasuredHeight());
    }

    private void m14471b() {
        BookingCabCategoryFragment c = ((BookingFragment) getParentFragment()).m14363c();
        this.f10927u = new ArrayList();
        this.f10927u = c.m14244k();
        if (this.f10927u != null && !this.f10927u.isEmpty()) {
            this.f10926t = ((bk) this.f10927u.get(0)).getId();
            this.f10910d.setText(((bk) this.f10927u.get(0)).getDisplayName());
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_ride_cancel:
                m14481a();
            case R.id.button_ride_conform:
                Sherlock.m13334a("Ins KP ride booked");
                if (this.f10923q == null || this.f10924r == 0) {
                    m14474c();
                    return;
                }
                m14467a(this.f10926t);
                BookingFragment bookingFragment = (BookingFragment) getParentFragment();
                bookingFragment.m14361b(false);
                bookingFragment.m14376n();
                ((MainActivity) getActivity()).m13600a(this.f10926t, this.f10922p, this.f10923q, this.f10924r + Trace.NULL, this.f10917k, this.f10916j, this.f10921o);
            case R.id.kp_layout_category_name:
                m14468a(this.f10927u);
            case R.id.kali_peeli_drop_loc_layout:
                m14476d();
            case R.id.kali_peeli_landmark_layout:
                m14478e();
            default:
        }
    }

    private void m14468a(ArrayList<bk> arrayList) {
        AlertDialog create = new Builder(this.f10925s, 16973935).setTitle("Select your category").setAdapter(new KaaliPeeliConformationFragment(this, this.f10925s, arrayList), new KaaliPeeliConformationFragment(this, arrayList)).create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
    }

    private void m14467a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("KP_Category", str);
        Localytics.tagEvent("KP_Category Selected", hashMap);
        Map hashMap2 = new HashMap();
        hashMap2.put("Booking type", this.f10920n ? "Ride now" : "Ride later");
        hashMap2.put("Cab category", str);
        hashMap2.put("Discount State", "No coupon");
        hashMap2.put("City name", this.f10921o);
        this.f10930x.m12859a("fb_mobile_add_to_cart", hashMap2);
    }

    private void m14474c() {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText("Drop point missing!");
        ((TextView) inflate.findViewById(R.id.item_message)).setText("Please select a drop point to continue");
        inflate.findViewById(R.id.button_ok).setOnClickListener(new KaaliPeeliConformationFragment(this, create));
        create.show();
    }

    private void m14476d() {
        ((MainActivity) getActivity()).m13604b(this.f10922p.f7554a, this.f10922p.f7555b);
    }

    public void m14481a() {
        BookingFragment bookingFragment = (BookingFragment) getParentFragment();
        bookingFragment.m14379q();
        bookingFragment.m14376n();
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        try {
            if (this.f10925s != null) {
                this.f10925s.f9699d = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m14482a(String str, int i) {
        if (str != null) {
            this.f10914h.setText(str.toUpperCase());
            this.f10914h.setSelected(true);
            this.f10923q = str;
            this.f10924r = i;
        }
    }

    private void m14478e() {
        View inflate = LayoutInflater.from(this.f10925s).inflate(R.layout.view_dialog_landmark_detail, null, false);
        AlertDialog create = new Builder(this.f10925s).setView(inflate).create();
        Button button = (Button) inflate.findViewById(R.id.button_action);
        ((TextView) inflate.findViewById(R.id.item_header)).setText("ADD LANDMARK");
        EditText editText = (EditText) inflate.findViewById(R.id.edit_text_drop);
        EditText editText2 = (EditText) inflate.findViewById(R.id.edit_text_pickup);
        editText.setText(this.f10917k);
        editText2.setText(this.f10916j);
        button.setOnClickListener(new KaaliPeeliConformationFragment(this, create, editText, editText2));
        inflate.findViewById(R.id.button_cancel).setOnClickListener(new KaaliPeeliConformationFragment(this, create, editText));
        create.getWindow().setSoftInputMode(4);
        create.show();
    }
}
