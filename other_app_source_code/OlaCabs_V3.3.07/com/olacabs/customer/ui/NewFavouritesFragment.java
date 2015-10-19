package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.m4b.maps.GoogleMap.GoogleMap;
import com.google.android.m4b.maps.OnMapReadyCallback;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.LatLng;
import com.google.gson.Gson;
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
import com.olacabs.customer.p076d.AddEditFavouriteResponse;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.aw;
import com.olacabs.customer.p078c.FetchAddress.FetchAddress;
import com.olacabs.customer.p078c.OlaMapFragment;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.List;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.t */
public class NewFavouritesFragment extends Fragment implements OnClickListener, GoogleMap, OnMapReadyCallback, FetchAddress, BackKeyHandler, TraceFieldInterface {
    private static final JoinPoint f11065A = null;
    public static String f11066a;
    public static String f11067b;
    public static String f11068c;
    private static final String f11069e;
    private static final JoinPoint f11070z = null;
    protected ProgressDialog f11071d;
    private FavouritesActivity f11072f;
    private ImageView f11073g;
    private ImageView f11074h;
    private TextView f11075i;
    private TextView f11076j;
    private LatLng f11077k;
    private EditText f11078l;
    private EditText f11079m;
    private long f11080n;
    private String f11081o;
    private String f11082p;
    private double f11083q;
    private double f11084r;
    private String f11085s;
    private aw f11086t;
    private com.google.android.m4b.maps.GoogleMap f11087u;
    private OlaMapFragment f11088v;
    private boolean f11089w;
    private DataManager f11090x;
    private aj f11091y;

    /* renamed from: com.olacabs.customer.ui.t.1 */
    class NewFavouritesFragment implements aj {
        final /* synthetic */ NewFavouritesFragment f11060a;

        NewFavouritesFragment(NewFavouritesFragment newFavouritesFragment) {
            this.f11060a = newFavouritesFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to add/edit favourite ", th);
            this.f11060a.f11071d.dismiss();
        }

        public void onSuccess(Object obj) {
            this.f11060a.m14590a((AddEditFavouriteResponse) obj);
            this.f11060a.f11071d.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.t.2 */
    class NewFavouritesFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f11061a;
        final /* synthetic */ NewFavouritesFragment f11062b;

        NewFavouritesFragment(NewFavouritesFragment newFavouritesFragment, AlertDialog alertDialog) {
            this.f11062b = newFavouritesFragment;
            this.f11061a = alertDialog;
        }

        public void onClick(View view) {
            this.f11061a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.t.3 */
    class NewFavouritesFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f11063a;
        final /* synthetic */ NewFavouritesFragment f11064b;

        NewFavouritesFragment(NewFavouritesFragment newFavouritesFragment, AlertDialog alertDialog) {
            this.f11064b = newFavouritesFragment;
            this.f11063a = alertDialog;
        }

        public void onClick(View view) {
            this.f11063a.dismiss();
            for (int i = 0; i < this.f11064b.getChildFragmentManager().getBackStackEntryCount(); i++) {
                this.f11064b.f11072f.onBackPressed();
            }
            this.f11064b.f11072f.m13507a(this.f11064b.f11072f.getIntent().getStringExtra(Constants.BUNDLE_ADDRESS), this.f11064b.f11072f.getIntent().getDoubleExtra("lattitude", 0.0d), this.f11064b.f11072f.getIntent().getDoubleExtra("longitude", 0.0d));
        }
    }

    private static void m14600i() {
        Factory factory = new Factory("NewFavouritesFragment.java", NewFavouritesFragment.class);
        f11070z = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.NewFavouritesFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 111);
        f11065A = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.NewFavouritesFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 135);
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
        NewFavouritesFragment.m14600i();
        f11069e = NewFavouritesFragment.class.getSimpleName();
        f11066a = "Edit non-existing favourite";
        f11067b = "Edit or Delete other user's favourite";
        f11068c = "Unknown error";
    }

    public static NewFavouritesFragment m14588a(long j, String str, String str2, String str3, double d, double d2) {
        NewFavouritesFragment newFavouritesFragment = new NewFavouritesFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.BUNDLE_FAV_ID, j);
        bundle.putString(Constants.BUNDLE_ADDRESS, str);
        bundle.putString(Constants.BUNDLE_NAME, str2);
        bundle.putString(Constants.BUNDLE_TYPE, str3);
        bundle.putDouble(Constants.LAT, d);
        bundle.putDouble(Constants.LNG, d2);
        newFavouritesFragment.setArguments(bundle);
        return newFavouritesFragment;
    }

    public NewFavouritesFragment() {
        this.f11080n = 0;
        this.f11089w = true;
        this.f11091y = new NewFavouritesFragment(this);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("t");
        try {
            TraceMachine.enterMethod(this._nr_trace, "t#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "t#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f11070z, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f11072f = (FavouritesActivity) getActivity();
        this.f11090x = ((OlaApp) this.f11072f.getApplication()).m12878a();
        Localytics.tagScreen("Favourites");
        if (getArguments() != null) {
            this.f11080n = getArguments().getLong(Constants.BUNDLE_FAV_ID);
            this.f11081o = getArguments().getString(Constants.BUNDLE_ADDRESS);
            this.f11082p = getArguments().getString(Constants.BUNDLE_NAME);
            this.f11085s = getArguments().getString(Constants.BUNDLE_TYPE);
            this.f11083q = getArguments().getDouble(Constants.LAT);
            this.f11084r = getArguments().getDouble(Constants.LNG);
            this.f11089w = false;
            if (this.f11081o != null) {
                this.f11089w = true;
            }
        }
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "t#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "t#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f11065A, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_new_favourites, viewGroup, false);
        m14589a(inflate);
        m14593b();
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14593b() {
        this.f11088v = new OlaMapFragment.OlaMapFragment().m13360a(new LatLng(this.f11083q, this.f11084r)).m13355a(16.0f).m13358a((GoogleMap) this).m13359a((OnMapReadyCallback) this).m13361a((FetchAddress) this).m13366b(true).m13364a(false).m13365a();
        getChildFragmentManager().beginTransaction().replace(R.id.container_map, this.f11088v).commit();
    }

    public void m14603a(LatLng latLng) {
        this.f11077k = latLng;
        this.f11088v.m13394a(this.f11077k, true, 16);
    }

    private void m14589a(View view) {
        this.f11073g = (ImageView) view.findViewById(R.id.new_fav_menu_button);
        this.f11073g.setOnClickListener(this);
        this.f11075i = (TextView) view.findViewById(R.id.new_fav_done_txt);
        this.f11075i.setOnClickListener(this);
        this.f11076j = (TextView) view.findViewById(R.id.new_fav_error_txt);
        this.f11078l = (EditText) view.findViewById(R.id.new_fav_title_txt);
        this.f11078l.requestFocus();
        this.f11074h = (ImageView) view.findViewById(R.id.currentposition);
        this.f11074h.setOnClickListener(this);
        m14595d();
        if (this.f11080n != 0) {
            this.f11074h.setVisibility(8);
        }
        this.f11079m = (EditText) view.findViewById(R.id.new_fav_sub_title_txt);
        this.f11079m.setOnClickListener(this);
        m14594c();
    }

    private void m14594c() {
        if (!TextUtils.isEmpty(this.f11081o)) {
            this.f11079m.setText(this.f11081o);
        }
        if (!TextUtils.isEmpty(this.f11082p)) {
            this.f11078l.setText(this.f11082p);
        }
    }

    public void onClick(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.new_fav_menu_button:
                ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.f11073g.getWindowToken(), 0);
                this.f11072f.onBackPressed();
            case R.id.new_fav_done_txt:
                ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.f11075i.getWindowToken(), 0);
                String trim = this.f11078l.getText().toString().trim();
                Boolean valueOf = Boolean.valueOf(false);
                try {
                    List list = Ola.f11489f;
                    while (i < list.size()) {
                        Boolean valueOf2;
                        if (((aw) list.get(i)).getLat() == ((double) Math.round(this.f11077k.f7554a * 10000.0d)) / 10000.0d && ((aw) list.get(i)).getLng() == ((double) Math.round(this.f11077k.f7555b * 10000.0d)) / 10000.0d) {
                            valueOf2 = Boolean.valueOf(true);
                        } else {
                            valueOf2 = valueOf;
                        }
                        i++;
                        valueOf = valueOf2;
                    }
                } catch (Exception e) {
                }
                if (trim == null || trim.equalsIgnoreCase(Trace.NULL)) {
                    m14592a("Invalid name", "Please enter a valid name and try again");
                } else if (valueOf.booleanValue() && this.f11080n == 0) {
                    m14592a(getResources().getString(R.string.sos_ec_header), getResources().getString(R.string.address_already_added));
                } else if (!Utils.m14909a(this.f11072f.getApplicationContext())) {
                    m14592a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
                } else if (this.f11080n == 0) {
                    m14597f();
                } else {
                    m14598g();
                }
            case R.id.currentposition:
                m14603a(new LatLng(this.f11083q, this.f11084r));
            default:
        }
    }

    private void m14592a(String str, String str2) {
        View inflate = ((LayoutInflater) this.f11072f.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f11072f).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new NewFavouritesFragment(this, create));
        create.show();
    }

    private void m14595d() {
        this.f11074h.setVisibility(4);
    }

    private void m14596e() {
        if (this.f11080n == 0) {
            this.f11074h.setVisibility(0);
        }
    }

    public boolean m14606a() {
        if (this.f11080n == 0) {
            Localytics.tagEvent("Favorite Add Cancelled");
        } else {
            Localytics.tagEvent("Favorite Edit Cancelled");
        }
        return false;
    }

    private void m14597f() {
        String h = m14599h();
        this.f11071d = new ProgressDialog(this.f11072f, R.style.TransparentProgressDialog);
        this.f11071d.setIndeterminateDrawable(this.f11072f.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f11071d.setCancelable(false);
        this.f11071d.show();
        this.f11090x.m13221d(new WeakReference(this.f11091y), h, f11069e);
    }

    private void m14598g() {
        String h = m14599h();
        this.f11071d = new ProgressDialog(this.f11072f, R.style.TransparentProgressDialog);
        this.f11071d.setIndeterminateDrawable(this.f11072f.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f11071d.setCancelable(false);
        this.f11071d.show();
        this.f11090x.m13177a(new WeakReference(this.f11091y), h, this.f11080n, f11069e);
    }

    private void m14590a(AddEditFavouriteResponse addEditFavouriteResponse) {
        if (!addEditFavouriteResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
            String reason = addEditFavouriteResponse.getReason();
            if (reason.equalsIgnoreCase("FAVOURITE_NOT_FOUND")) {
                m14605a(f11066a, false);
            } else if (reason.equalsIgnoreCase("USER_FAVOURITE_MISMATCH")) {
                m14605a(f11067b, false);
            } else if (reason.equalsIgnoreCase("INVALID_JSON")) {
                m14605a(getString(R.string.generic_failure_desc), false);
            } else if (reason.equalsIgnoreCase("UNKNOWN")) {
                m14605a(f11068c, false);
            }
        } else if (addEditFavouriteResponse.getRequestType().contains("EDIT")) {
            m14605a(this.f11078l.getText().toString() + " has been updated successfully.", true);
            Localytics.tagEvent("Favorite Edited");
        } else {
            m14605a(this.f11078l.getText().toString() + " has been added successfully to your favourite.", true);
            Localytics.tagEvent("Favorite added");
        }
    }

    protected void m14605a(String str, boolean z) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        if (z) {
            ((TextView) inflate.findViewById(R.id.item_header)).setText(Constants.SOS_SUCCESS_HEADER_TEXT);
        } else {
            ((TextView) inflate.findViewById(R.id.item_header)).setText("Failure");
        }
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new NewFavouritesFragment(this, create));
        create.show();
    }

    private String m14599h() {
        Gson gson = new Gson();
        this.f11086t = new aw();
        this.f11086t.setName(this.f11078l.getText().toString());
        this.f11086t.setLat(this.f11077k.f7554a);
        this.f11086t.setLng(this.f11077k.f7555b);
        this.f11086t.setAddress(this.f11079m.getText().toString());
        if (this.f11080n != 0) {
            this.f11086t.setId((int) this.f11080n);
            this.f11086t.setType(this.f11085s);
        }
        return gson.m12346a(this.f11086t);
    }

    public void m14601a(com.google.android.m4b.maps.GoogleMap googleMap) {
        this.f11087u = googleMap;
    }

    public void m14602a(CameraPosition cameraPosition) {
        this.f11077k = cameraPosition.f7529a;
        m14596e();
    }

    public void m14604a(String str) {
        this.f11079m.setText(str);
    }
}
