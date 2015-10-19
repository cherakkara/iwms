package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Telephony.Sms;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.google.android.m4b.maps.CameraUpdateFactory;
import com.google.android.m4b.maps.GoogleMap;
import com.google.android.m4b.maps.OnMapReadyCallback;
import com.google.android.m4b.maps.model.BitmapDescriptorFactory;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.LatLngBounds.C0595a;
import com.google.android.m4b.maps.model.MarkerOptions;
import com.leanplum.Leanplum;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.AppState;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.DataUpdaterManager;
import com.olacabs.customer.app.ForceLogoutCommand;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.AllottedCabInfo;
import com.olacabs.customer.p076d.CabImages;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.cn;
import com.olacabs.customer.p076d.dg;
import com.olacabs.customer.p076d.dh;
import com.olacabs.customer.p076d.dn;
import com.olacabs.customer.p078c.OlaMapFragment;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.OlaApplicationUtils;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.protocol.HTTP;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
public class TrackRideActivity extends FragmentActivity implements OnClickListener, OnMapReadyCallback, TraceFieldInterface {
    public static final String f10168a;
    private static final JoinPoint af = null;
    private static final JoinPoint ag = null;
    private static final JoinPoint ah = null;
    public static boolean f10169b;
    private static int f10170f;
    private static int f10171g;
    private LinearLayout f10172A;
    private RelativeLayout f10173B;
    private RelativeLayout f10174C;
    private boolean f10175D;
    private boolean f10176E;
    private C0595a f10177F;
    private LatLng f10178G;
    private LatLngBounds f10179H;
    private int f10180I;
    private int f10181J;
    private LinearLayout f10182K;
    private int f10183L;
    private boolean f10184M;
    private View f10185N;
    private String f10186O;
    private int f10187P;
    private boolean f10188Q;
    private boolean f10189R;
    private boolean f10190S;
    private String f10191T;
    private boolean f10192U;
    private boolean f10193V;
    private DataManager f10194W;
    private ImageView f10195X;
    private TextView f10196Y;
    private boolean f10197Z;
    private aj aa;
    private aj ab;
    private aj ac;
    private Runnable ad;
    private aj ae;
    ListView f10198c;
    AlertDialog f10199d;
    ProgressDialog f10200e;
    private GoogleMap f10201h;
    private OlaMapFragment f10202i;
    private dg f10203j;
    private AllottedCabInfo f10204k;
    private int f10205l;
    private Handler f10206m;
    private LinearLayout f10207n;
    private TextView f10208o;
    private boolean f10209p;
    private ImageView f10210q;
    private TextView f10211r;
    private ImageView f10212s;
    private LinearLayout f10213t;
    private ImageView f10214u;
    private RelativeLayout f10215v;
    private RelativeLayout f10216w;
    private ImageView f10217x;
    private ImageView f10218y;
    private TextView f10219z;

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.10 */
    class AnonymousClass10 implements OnClickListener {
        final /* synthetic */ boolean f10139a;
        final /* synthetic */ TrackRideActivity f10140b;

        AnonymousClass10(TrackRideActivity trackRideActivity, boolean z) {
            this.f10140b = trackRideActivity;
            this.f10139a = z;
        }

        public void onClick(View view) {
            this.f10140b.f10199d.dismiss();
            if (this.f10139a) {
                this.f10140b.finish();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.11 */
    class AnonymousClass11 implements OnClickListener {
        final /* synthetic */ AlertDialog f10141a;
        final /* synthetic */ String f10142b;
        final /* synthetic */ TrackRideActivity f10143c;

        AnonymousClass11(TrackRideActivity trackRideActivity, AlertDialog alertDialog, String str) {
            this.f10143c = trackRideActivity;
            this.f10141a = alertDialog;
            this.f10142b = str;
        }

        public void onClick(View view) {
            this.f10143c.f10219z.setEnabled(true);
            this.f10141a.dismiss();
            this.f10143c.m13928b("User cancellation", this.f10142b);
            if ("local_taxi".equalsIgnoreCase(this.f10142b) || "local_auto".equalsIgnoreCase(this.f10142b) || "delivery".equalsIgnoreCase(this.f10142b)) {
                this.f10143c.f10194W.m13184a(new WeakReference(this.f10143c.ae), String.valueOf(this.f10143c.getIntent().getStringExtra(Constants.ARG_BOOKING_ID)), "User cancellation", this.f10142b, TrackRideActivity.f10168a);
            } else {
                this.f10143c.f10194W.m13228e(new WeakReference(this.f10143c.ae), String.valueOf(this.f10143c.getIntent().getStringExtra(Constants.ARG_BOOKING_ID)), "User cancellation", TrackRideActivity.f10168a);
            }
            this.f10143c.f10200e.show();
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.12 */
    class AnonymousClass12 implements OnClickListener {
        final /* synthetic */ AlertDialog f10144a;
        final /* synthetic */ TrackRideActivity f10145b;

        AnonymousClass12(TrackRideActivity trackRideActivity, AlertDialog alertDialog) {
            this.f10145b = trackRideActivity;
            this.f10144a = alertDialog;
        }

        public void onClick(View view) {
            this.f10145b.f10219z.setEnabled(true);
            this.f10144a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.13 */
    class AnonymousClass13 implements OnItemClickListener {
        final /* synthetic */ C0911a f10146a;
        final /* synthetic */ TrackRideActivity f10147b;

        AnonymousClass13(TrackRideActivity trackRideActivity, C0911a c0911a) {
            this.f10147b = trackRideActivity;
            this.f10146a = c0911a;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ImageView imageView = (ImageView) view.findViewById(R.id.chk);
            this.f10147b.f10186O = ((TextView) view.findViewById(R.id.item_text)).getText().toString();
            imageView.setSelected(true);
            this.f10147b.f10187P = i;
            this.f10146a.notifyDataSetChanged();
            if (this.f10147b.f10186O == null || this.f10147b.f10186O.length() <= 0) {
                this.f10147b.f10185N.setEnabled(false);
            } else {
                this.f10147b.f10185N.setEnabled(true);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.1 */
    class C09011 implements aj {
        final /* synthetic */ TrackRideActivity f10148a;

        C09011(TrackRideActivity trackRideActivity) {
            this.f10148a = trackRideActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Fetching Driver Image failed", th);
            this.f10148a.f10218y.setBackgroundResource(R.drawable.driver_full_placeholder);
        }

        public void onSuccess(Object obj) {
            this.f10148a.f10218y.setBackgroundDrawable(new BitmapDrawable(this.f10148a.getResources(), (Bitmap) obj));
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.2 */
    class C09022 implements OnClickListener {
        final /* synthetic */ AlertDialog f10149a;
        final /* synthetic */ String f10150b;
        final /* synthetic */ TrackRideActivity f10151c;

        C09022(TrackRideActivity trackRideActivity, AlertDialog alertDialog, String str) {
            this.f10151c = trackRideActivity;
            this.f10149a = alertDialog;
            this.f10150b = str;
        }

        public void onClick(View view) {
            this.f10151c.f10219z.setEnabled(true);
            this.f10149a.dismiss();
            this.f10151c.f10187P = -1;
            this.f10151c.m13928b(this.f10151c.f10186O, this.f10150b);
            if ("local_taxi".equalsIgnoreCase(this.f10150b) || "local_auto".equalsIgnoreCase(this.f10150b)) {
                this.f10151c.f10194W.m13184a(new WeakReference(this.f10151c.ae), String.valueOf(this.f10151c.getIntent().getStringExtra(Constants.ARG_BOOKING_ID)), this.f10151c.f10186O, this.f10150b, TrackRideActivity.f10168a);
            } else {
                this.f10151c.f10194W.m13228e(new WeakReference(this.f10151c.ae), String.valueOf(this.f10151c.getIntent().getStringExtra(Constants.ARG_BOOKING_ID)), this.f10151c.f10186O, TrackRideActivity.f10168a);
            }
            this.f10151c.f10200e.show();
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.3 */
    class C09033 implements OnClickListener {
        final /* synthetic */ AlertDialog f10152a;
        final /* synthetic */ TrackRideActivity f10153b;

        C09033(TrackRideActivity trackRideActivity, AlertDialog alertDialog) {
            this.f10153b = trackRideActivity;
            this.f10152a = alertDialog;
        }

        public void onClick(View view) {
            this.f10153b.f10219z.setEnabled(true);
            this.f10152a.dismiss();
            this.f10153b.f10187P = -1;
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.4 */
    class C09044 implements OnClickListener {
        final /* synthetic */ AlertDialog f10154a;
        final /* synthetic */ TrackRideActivity f10155b;

        C09044(TrackRideActivity trackRideActivity, AlertDialog alertDialog) {
            this.f10155b = trackRideActivity;
            this.f10154a = alertDialog;
        }

        public void onClick(View view) {
            this.f10154a.dismiss();
            this.f10155b.f10197Z = true;
            this.f10155b.m13956m();
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.5 */
    class C09055 implements OnClickListener {
        final /* synthetic */ AlertDialog f10156a;
        final /* synthetic */ TrackRideActivity f10157b;

        C09055(TrackRideActivity trackRideActivity, AlertDialog alertDialog) {
            this.f10157b = trackRideActivity;
            this.f10156a = alertDialog;
        }

        public void onClick(View view) {
            this.f10156a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.6 */
    class C09066 implements aj {
        final /* synthetic */ TrackRideActivity f10158a;

        C09066(TrackRideActivity trackRideActivity) {
            this.f10158a = trackRideActivity;
        }

        public void onFailure(Throwable th) {
            Sherlock.m13337a("Ins track ride shown", (VolleyError) th, false);
            this.f10158a.f10200e.dismiss();
            OLog.m13310a("TrackRideRequester failed", th);
        }

        public void onSuccess(Object obj) {
            if (!this.f10158a.isFinishing() && !this.f10158a.f10192U) {
                this.f10158a.f10200e.dismiss();
                dh dhVar = (dh) obj;
                if (dhVar != null && dhVar.isForceLogout()) {
                    new ForceLogoutCommand(true).m13270a(this.f10158a);
                }
                if (dhVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    if (dhVar.getNextCallAfter() != null) {
                        TrackRideActivity.f10170f = dhVar.getNextCallAfter().intValue();
                        TrackRideActivity.f10171g = TrackRideActivity.f10170f * Constants.MILLIS_IN_A_SECOND;
                    }
                    this.f10158a.f10203j = dhVar.getBooking();
                    if (this.f10158a.f10203j != null) {
                        this.f10158a.f10204k = this.f10158a.f10203j.getAllottedCabInfo();
                    }
                    this.f10158a.f10184M = dhVar.isRechargeScreen();
                    if (dhVar.getBookingStatus() == null) {
                        Sherlock.m13336a("Ins track ride shown", null, null, false);
                        this.f10158a.m13919a(this.f10158a.getString(R.string.generic_failure_header), this.f10158a.getString(R.string.generic_failure_desc));
                        return;
                    }
                    if (dhVar.getBookingStatus().equalsIgnoreCase(Constants.RIDE_STATUS_IN_PROGRESS)) {
                        if (dhVar.isEmergencyNumberVerified()) {
                            this.f10158a.f10195X.setImageResource(R.drawable.sos_button_bg);
                        } else {
                            this.f10158a.f10195X.setImageResource(R.drawable.sos_button_emergency_bg);
                        }
                        this.f10158a.m13921a(dhVar.isEmergencyNumberVerified());
                        Editor edit = PreferenceManager.getDefaultSharedPreferences(this.f10158a).edit();
                        if (PreferenceManager.getDefaultSharedPreferences(this.f10158a).getBoolean(Constants.PREF_SOS_EMERGENCY_CONTACT, false) != dhVar.isEmergencyNumberVerified()) {
                            edit.putBoolean(Constants.PREF_SOS_EMERGENCY_CONTACT, dhVar.isEmergencyNumberVerified()).apply();
                        }
                        this.f10158a.f10195X.setVisibility(0);
                    } else {
                        this.f10158a.f10195X.setVisibility(4);
                    }
                    this.f10158a.f10205l = dhVar.getStateId();
                    if (this.f10158a.f10205l == 2 || this.f10158a.f10205l == 3 || this.f10158a.f10205l == 4) {
                        if (this.f10158a.f10204k != null && "delivery".equalsIgnoreCase(this.f10158a.f10204k.getCategory_id())) {
                            this.f10158a.f10214u.setImageResource(R.drawable.ic_tab_rate_normal);
                            this.f10158a.f10213t.setAlpha(50.0f);
                            this.f10158a.f10172A.setAlpha(50.0f);
                            this.f10158a.f10213t.setClickable(false);
                            this.f10158a.f10172A.setClickable(false);
                            if (this.f10158a.f10205l == 2) {
                                this.f10158a.f10219z.setVisibility(0);
                            } else {
                                this.f10158a.f10219z.setVisibility(8);
                            }
                        } else if (this.f10158a.f10205l == 4) {
                            this.f10158a.f10219z.setVisibility(8);
                            this.f10158a.f10196Y.setText(R.string.new_track_ride_heading_text);
                            this.f10158a.f10214u.setImageResource(R.drawable.bg_rating_star);
                        } else {
                            this.f10158a.f10219z.setVisibility(0);
                            this.f10158a.f10214u.setImageResource(R.drawable.ic_tab_rate_normal);
                            this.f10158a.f10213t.setAlpha(30.0f);
                        }
                        if (!(this.f10158a.f10204k == null || this.f10158a.f10204k.getDriverImageUrl() == null || this.f10158a.f10203j == null || this.f10158a.f10203j.getBookingId() == null)) {
                            PreferenceManager.getDefaultSharedPreferences(this.f10158a).edit().putString(this.f10158a.f10203j.getBookingId() + Constants.DRIVER_IMAGE_URL_SHARED_PREF, this.f10158a.f10204k.getDriverImageUrl()).commit();
                        }
                        this.f10158a.m13954l();
                        this.f10158a.f10191T = dhVar.getRechargeText();
                        if (this.f10158a.f10204k == null) {
                            Sherlock.m13336a("Ins track ride shown", null, "allocated cab info null", false);
                            this.f10158a.m13920a(this.f10158a.getString(R.string.generic_failure_header), this.f10158a.getString(R.string.generic_failure_desc), true);
                            return;
                        }
                        this.f10158a.m13940e();
                        this.f10158a.m13949i();
                        this.f10158a.f10173B.setVisibility(0);
                        this.f10158a.f10174C.setVisibility(8);
                    } else if (this.f10158a.f10205l == AppState.TRIP_END.m12882a()) {
                        if (this.f10158a.f10175D) {
                            this.f10158a.f10175D = false;
                            Intent intent = new Intent(this.f10158a, RideSummaryActivity.class);
                            intent.putExtra(Constants.ARG_FLAG_MAIN_ACTIVITY, this.f10158a.f10189R);
                            intent.putExtra(Constants.ARG_BOOKING_ID, this.f10158a.getIntent().getStringExtra(Constants.ARG_BOOKING_ID));
                            this.f10158a.startActivity(intent);
                            this.f10158a.finish();
                        }
                    } else if (this.f10158a.f10205l == AppState.LOCAL_TAXI_CANCELLATION.m12882a() || this.f10158a.f10205l == AppState.CITY_TAXI_CANCELLATION.m12882a()) {
                        this.f10158a.m13958n();
                        this.f10158a.finish();
                    } else {
                        this.f10158a.finish();
                    }
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.7 */
    class C09077 implements aj {
        final /* synthetic */ TrackRideActivity f10159a;

        C09077(TrackRideActivity trackRideActivity) {
            this.f10159a = trackRideActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Untrack Ride Request failed", th);
        }

        public void onSuccess(Object obj) {
            if (((dn) obj).getStatus().equalsIgnoreCase("SUCCESS")) {
                if (this.f10159a.f10197Z) {
                    this.f10159a.m13964q();
                } else if (this.f10159a.f10189R || (this.f10159a.f10190S && TrackRideActivity.f10169b)) {
                    this.f10159a.m13958n();
                }
                this.f10159a.finish();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.8 */
    class C09088 implements Runnable {
        final /* synthetic */ TrackRideActivity f10160a;

        C09088(TrackRideActivity trackRideActivity) {
            this.f10160a = trackRideActivity;
        }

        public void run() {
            if (Utils.m14909a(this.f10160a.getApplicationContext())) {
                this.f10160a.m13953k();
                return;
            }
            this.f10160a.m13977a();
            this.f10160a.m13949i();
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.9 */
    class C09099 implements aj {
        final /* synthetic */ TrackRideActivity f10161a;

        C09099(TrackRideActivity trackRideActivity) {
            this.f10161a = trackRideActivity;
        }

        public void onFailure(Throwable th) {
            this.f10161a.f10200e.dismiss();
            OLog.m13310a("CancelRideRequester failed", th);
        }

        public void onSuccess(Object obj) {
            try {
                this.f10161a.f10200e.dismiss();
                if (((cn) obj).getStatus().equalsIgnoreCase("SUCCESS")) {
                    this.f10161a.f10206m.removeCallbacks(this.f10161a.ad);
                    if (this.f10161a.f10189R) {
                        this.f10161a.m13958n();
                    }
                    this.f10161a.finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.TrackRideActivity.a */
    private class C0911a extends BaseAdapter {
        final /* synthetic */ TrackRideActivity f10165a;
        private Context f10166b;
        private List<String> f10167c;

        /* renamed from: com.olacabs.customer.ui.TrackRideActivity.a.a */
        public class C0910a {
            public TextView f10162a;
            public ImageView f10163b;
            final /* synthetic */ C0911a f10164c;

            public C0910a(C0911a c0911a) {
                this.f10164c = c0911a;
            }
        }

        public C0911a(TrackRideActivity trackRideActivity, Context context, List<String> list) {
            this.f10165a = trackRideActivity;
            this.f10166b = context;
            this.f10167c = list;
        }

        public int getCount() {
            return this.f10167c.size();
        }

        public Object getItem(int i) {
            return this.f10167c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEmpty() {
            return this.f10167c.isEmpty();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            boolean z;
            if (view == null) {
                C0910a c0910a = new C0910a(this);
                view = ((Activity) this.f10166b).getLayoutInflater().inflate(R.layout.cancel_list_item, viewGroup, false);
                c0910a.f10162a = (TextView) view.findViewById(R.id.item_text);
                c0910a.f10163b = (ImageView) view.findViewById(R.id.chk);
                view.setTag(c0910a);
            }
            C0910a c0910a2 = (C0910a) view.getTag();
            if (!isEmpty()) {
                c0910a2.f10162a.setText((CharSequence) this.f10167c.get(i));
            }
            ImageView imageView = c0910a2.f10163b;
            if (this.f10165a.f10187P == i) {
                z = true;
            } else {
                z = false;
            }
            imageView.setSelected(z);
            return view;
        }
    }

    private static void m13966r() {
        Factory factory = new Factory("TrackRideActivity.java", TrackRideActivity.class);
        af = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.TrackRideActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 364);
        ag = factory.m15070a("method-execution", factory.m15071a("4", "onResume", "com.olacabs.customer.ui.TrackRideActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 442);
        ah = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.ui.TrackRideActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 470);
    }

    public TrackRideActivity() {
        this.f10209p = true;
        this.f10175D = true;
        this.f10176E = false;
        this.f10184M = false;
        this.f10186O = Trace.NULL;
        this.f10187P = -1;
        this.f10188Q = false;
        this.f10190S = false;
        this.f10192U = false;
        this.f10193V = false;
        this.f10197Z = false;
        this.aa = new C09011(this);
        this.ab = new C09066(this);
        this.ac = new C09077(this);
        this.ad = new C09088(this);
        this.ae = new C09099(this);
    }

    static {
        m13966r();
        f10168a = TrackRideActivity.class.getSimpleName();
        f10169b = false;
        f10170f = 11;
        f10171g = f10170f * Constants.MILLIS_IN_A_SECOND;
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("TrackRideActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "TrackRideActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TrackRideActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(af, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f10189R = extras.getBoolean(Constants.ARG_FLAG_MAIN_ACTIVITY, false);
            this.f10190S = extras.getBoolean(Constants.ARG_FLAG_RIDE_DETAILS_ACTIVITY, false);
        }
        setContentView(R.layout.activity_track_ride);
        this.f10207n = (LinearLayout) findViewById(R.id.callText);
        this.f10207n.setOnClickListener(this);
        this.f10213t = (LinearLayout) findViewById(R.id.rateStar);
        this.f10213t.setOnClickListener(this);
        this.f10214u = (ImageView) this.f10213t.findViewById(R.id.rateStar_image);
        this.f10215v = (RelativeLayout) findViewById(R.id.view_no_location_overlay);
        this.f10215v.setOnClickListener(this);
        this.f10208o = (TextView) findViewById(R.id.no_internet_errorText);
        this.f10210q = (ImageView) findViewById(R.id.currentPositionImage);
        this.f10210q.setOnClickListener(this);
        this.f10211r = (TextView) findViewById(R.id.cusomMessagesText);
        this.f10212s = (ImageView) findViewById(R.id.custom_message_arrow);
        this.f10216w = (RelativeLayout) findViewById(R.id.cusomMessageLayout);
        this.f10216w.setOnClickListener(this);
        this.f10217x = (ImageView) findViewById(R.id.backImageView);
        this.f10218y = (ImageView) findViewById(R.id.driver_image);
        this.f10196Y = (TextView) findViewById(R.id.rideTitle);
        this.f10219z = (TextView) findViewById(R.id.cancelRideText);
        this.f10172A = (LinearLayout) findViewById(R.id.shareText);
        this.f10172A.setOnClickListener(this);
        this.f10217x.setOnClickListener(this);
        this.f10219z.setOnClickListener(this);
        this.f10195X = (ImageView) findViewById(R.id.sos_image);
        this.f10195X.setOnClickListener(this);
        this.f10173B = (RelativeLayout) findViewById(R.id.mapViewLayout);
        this.f10174C = (RelativeLayout) findViewById(R.id.empty_view_layout);
        this.f10189R = getIntent().getBooleanExtra(Constants.ARG_FLAG_MAIN_ACTIVITY, false);
        if (this.f10189R || this.f10190S) {
            this.f10216w.setVisibility(8);
        } else {
            this.f10216w.setVisibility(0);
        }
        m13936d();
        this.f10194W = ((OlaApp) getApplication()).m12878a();
        Localytics.tagScreen("Track Ride");
        m13951j();
        this.f10182K = (LinearLayout) findViewById(R.id.driverLayout);
        this.f10182K.measure(0, 0);
        this.f10183L = this.f10182K.getMeasuredHeight();
        this.f10200e = new ProgressDialog(this, R.style.TransparentProgressDialog);
        this.f10200e.setIndeterminateDrawable(getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10200e.setCancelable(false);
        TraceMachine.exitMethod();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f10200e != null && this.f10200e.isShowing()) {
            this.f10200e.dismiss();
        }
    }

    private void m13936d() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f10180I = displayMetrics.heightPixels;
        this.f10181J = displayMetrics.widthPixels;
    }

    protected void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(ag, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            this.f10192U = false;
            f10169b = false;
            if (Utils.m14909a(getApplicationContext())) {
                m13979b();
                m13953k();
            } else {
                m13977a();
                m13949i();
            }
            if (this.f10194W.m13218d().getBookingCancelReasons() == null) {
                DataUpdaterManager.m13261a(getApplicationContext()).m13264a("app_config_without_auth");
            }
            ActivityTraceAspect.m12823a().m12828d(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    public void onEvent(ah ahVar) {
        if (ahVar.isConnected()) {
            m13979b();
        } else {
            m13977a();
        }
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(ah, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void m13977a() {
        this.f10219z.setEnabled(false);
        this.f10208o.setVisibility(0);
        this.f10208o.setText(getString(R.string.no_internet));
    }

    public void m13979b() {
        this.f10219z.setEnabled(true);
        this.f10208o.setVisibility(8);
    }

    private void m13920a(String str, String str2, boolean z) {
        if (this.f10199d == null || !this.f10199d.isShowing()) {
            View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
            this.f10199d = new Builder(this).setView(inflate).create();
            ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
            ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
            inflate.findViewById(R.id.button_ok).setOnClickListener(new AnonymousClass10(this, z));
            this.f10199d.show();
        }
    }

    private void m13919a(String str, String str2) {
        m13920a(str, str2, false);
    }

    private void m13940e() {
        if (this.f10202i.m13399e()) {
            if (this.f10203j != null) {
                CharSequence charSequence;
                this.f10201h.m10076c();
                String str = Trace.NULL;
                if (this.f10205l == 2) {
                    String str2;
                    double d;
                    if (this.f10204k.getDistance() == null || this.f10204k.getDuration() == null) {
                        str2 = str;
                        d = 0.0d;
                    } else {
                        if (this.f10204k.getDistance().getValue() != null) {
                            d = Double.parseDouble(this.f10204k.getDistance().getValue());
                        } else {
                            d = 0.0d;
                        }
                        if (this.f10204k.getDistance().getUnit().equalsIgnoreCase("METER")) {
                            str2 = " kms ";
                            d /= 1000.0d;
                        } else {
                            str2 = " kms ";
                        }
                    }
                    if (this.f10189R || this.f10190S) {
                        this.f10211r.setVisibility(8);
                    } else {
                        this.f10211r.setVisibility(0);
                    }
                    if (this.f10184M) {
                        this.f10212s.setVisibility(0);
                        this.f10216w.setVisibility(0);
                        this.f10211r.setVisibility(0);
                        str = this.f10191T != null ? this.f10191T : "Ride Cash-free!. Recharge your Ola Money here";
                    } else {
                        this.f10216w.setVisibility(8);
                        str = d > 0.0d ? getResources().getString(R.string.track_ride_cab_driver) + " " + String.format("%.1f", new Object[]{Double.valueOf(d)}) + str2 + " " + getResources().getString(R.string.track_ride_away) : "Your driver is on the way";
                    }
                    if (this.f10203j.getPickup_lat() >= 0.0d || this.f10203j.getPickup_lng() >= 0.0d) {
                        String str3 = Trace.NULL;
                        if (this.f10204k != null) {
                            str3 = this.f10204k.getDuration().getValue();
                        }
                        this.f10201h.m10068a(new MarkerOptions().m10759a(new LatLng(this.f10203j.getPickup_lat(), this.f10203j.getPickup_lng())).m10760a(BitmapDescriptorFactory.m10824a(m13911a((int) R.drawable.track_ride_eta, str3))));
                    }
                    this.f10216w.setEnabled(true);
                    charSequence = str;
                } else if (this.f10205l == 3) {
                    this.f10216w.setVisibility(0);
                    this.f10216w.setEnabled(false);
                    this.f10212s.setVisibility(8);
                    this.f10201h.m10068a(new MarkerOptions().m10759a(new LatLng(this.f10203j.getPickup_lat(), this.f10203j.getPickup_lng())).m10760a(BitmapDescriptorFactory.m10824a(m13911a((int) R.drawable.track_ride_eta, "0"))));
                    r8 = getResources().getString(R.string.track_ride_cab_reach_message);
                } else {
                    this.f10216w.setVisibility(0);
                    this.f10216w.setEnabled(false);
                    this.f10212s.setVisibility(8);
                    this.f10201h.m10068a(new MarkerOptions().m10759a(new LatLng(this.f10203j.getPickup_lat(), this.f10203j.getPickup_lng())).m10760a(BitmapDescriptorFactory.m10823a((int) R.drawable.ic_map_marker)));
                    r8 = "Enjoy your ola ride";
                }
                if (this.f10203j.getShareRideText() == null) {
                    this.f10172A.setEnabled(false);
                } else if (Trace.NULL.equalsIgnoreCase(this.f10203j.getShareRideText().trim())) {
                    this.f10172A.setEnabled(false);
                }
                if (this.f10203j.getPickup_lat() == 0.0d || this.f10203j.getPickup_lng() == 0.0d) {
                    if (this.f10209p) {
                        this.f10209p = false;
                        m13943f();
                    }
                } else if ((this.f10203j.getPickup_lat() >= 0.0d || this.f10203j.getPickup_lng() >= 0.0d) && this.f10204k != null) {
                    LatLng latLng = new LatLng(this.f10204k.getLat(), this.f10204k.getLng());
                    this.f10178G = OlaApplicationUtils.m14900a(this.f10203j.getPickup_lat(), this.f10203j.getPickup_lng(), this.f10204k.getLat(), this.f10204k.getLng());
                    if (!(this.f10178G == null || this.f10176E)) {
                        this.f10177F = new C0595a();
                        this.f10177F.m10738a(new LatLng(this.f10178G.f7554a, this.f10178G.f7555b));
                        this.f10177F.m10738a(latLng);
                        this.f10179H = this.f10177F.m10739a();
                        this.f10201h.m10071a(CameraUpdateFactory.m7793a(this.f10179H, this.f10181J, this.f10180I, 50));
                        this.f10176E = true;
                    }
                }
                this.f10211r.setText(charSequence);
                if (this.f10204k != null) {
                    this.f10201h.m10068a(new MarkerOptions().m10759a(new LatLng(this.f10204k.getLat(), this.f10204k.getLng())).m10760a(BitmapDescriptorFactory.m10824a(m13976a(this.f10204k.getId()))));
                }
            }
            this.f10201h.m10070a(0, this.f10183L, 0, this.f10183L);
        }
    }

    protected Bitmap m13976a(String str) {
        Bitmap bitmap;
        List<CabImages> list = Ola.f11488e;
        Bitmap bitmap2 = null;
        if (list == null || list.size() == 0 || str == null) {
            bitmap = null;
        } else {
            for (CabImages cabImages : list) {
                if (str.equalsIgnoreCase(cabImages.getId()) || (str.equalsIgnoreCase("ANY") && cabImages.getId().equalsIgnoreCase("economy_sedan"))) {
                    byte[] decode = Base64.decode(cabImages.getData().getBytes(), 0);
                    bitmap = BitmapFactoryInstrumentation.decodeByteArray(decode, 0, decode.length);
                } else {
                    bitmap = bitmap2;
                }
                bitmap2 = bitmap;
            }
            bitmap = bitmap2;
        }
        if (bitmap != null) {
            return bitmap;
        }
        if ("local_taxi".equalsIgnoreCase(this.f10204k.getCategory_id())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_kp_car_front)).getBitmap();
        }
        if ("local_auto".equalsIgnoreCase(this.f10204k.getCategory_id())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_booking_auto_map)).getBitmap();
        }
        if (!"delivery".equalsIgnoreCase(this.f10204k.getCategory_id())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_cab_top_view)).getBitmap();
        }
        if ("bg_style_myntra".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.style_myntra_map)).getBitmap();
        }
        if ("bg_delivery_electronic".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.electronic_map)).getBitmap();
        }
        if ("bg_xmas_1".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.xmas_1_map)).getBitmap();
        }
        if ("bg_xmas_2".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.xmas_2_map)).getBitmap();
        }
        if ("bg_movie".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.movie_map)).getBitmap();
        }
        if ("bg_marketing_default".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_cab_top_view)).getBitmap();
        }
        if ("bg_comicon".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.comic_map)).getBitmap();
        }
        if ("bg_ipl".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.ipl_map)).getBitmap();
        }
        if ("bg_fathersday".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.fathersday)).getBitmap();
        }
        if ("bg_olanight".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.olanight)).getBitmap();
        }
        if ("bg_envday".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.envday)).getBitmap();
        }
        if ("bg_carshare".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_cab_top_view)).getBitmap();
        }
        if ("bg_donorsday".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.donorsday)).getBitmap();
        }
        if ("bg_myntra".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.myntra_map)).getBitmap();
        }
        if ("bg_access".equalsIgnoreCase(this.f10204k.getImage_name())) {
            return ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_cab_top_view)).getBitmap();
        }
        return ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_cab_top_view)).getBitmap();
    }

    private void m13943f() {
        if (this.f10202i.m13399e()) {
            LatLng latLng;
            if (this.f10205l == 2 || this.f10205l == 3) {
                latLng = new LatLng(this.f10203j.getPickup_lat(), this.f10203j.getPickup_lng());
            } else if (this.f10204k != null) {
                latLng = new LatLng(this.f10204k.getLat(), this.f10204k.getLng());
            } else {
                latLng = new LatLng(this.f10203j.getPickup_lat(), this.f10203j.getPickup_lng());
            }
            this.f10202i.m13394a(latLng, true, 17);
        }
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.backImageView:
                onBackPressed();
            case R.id.cancelRideText:
                if (Utils.m14909a(getApplicationContext())) {
                    m13979b();
                    if (this.f10204k != null) {
                        m13937d(this.f10204k.getCategory_id());
                        this.f10219z.setEnabled(false);
                        m13980b(this.f10204k.getCategory_id());
                        return;
                    }
                    return;
                }
                m13977a();
                m13919a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            case R.id.callText:
                Localytics.tagEvent("Driver Called");
                if (this.f10204k != null) {
                    startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + this.f10204k.getDriverMobile())));
                }
            case R.id.shareText:
                String str = Trace.NULL;
                if (this.f10203j != null) {
                    str = this.f10203j.getShareRideText();
                }
                m13947h();
                try {
                    if (VERSION.SDK_INT >= 19) {
                        String defaultSmsPackage = Sms.getDefaultSmsPackage(this);
                        Intent intent2 = new Intent("android.intent.action.SEND");
                        intent2.setType(HTTP.PLAIN_TEXT_TYPE);
                        intent2.putExtra("android.intent.extra.TEXT", str);
                        if (defaultSmsPackage != null) {
                            intent2.setPackage(defaultSmsPackage);
                        }
                        startActivity(intent2);
                        return;
                    }
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse("sms:"));
                    intent.putExtra("sms_body", str);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    OLog.m13313b("SMS app not found", new Object[0]);
                }
            case R.id.currentPositionImage:
                if (this.f10202i.m13399e() && this.f10204k != null) {
                    this.f10202i.m13394a(new LatLng(this.f10204k.getLat(), this.f10204k.getLng()), true, 17);
                }
            case R.id.cusomMessageLayout:
                if ((!this.f10189R && !this.f10190S) || this.f10184M) {
                    m13945g();
                    f10169b = true;
                    m13956m();
                }
            case R.id.rateStar:
                if (this.f10205l == 2 || this.f10205l == 3) {
                    this.f10173B.setBackgroundColor(getResources().getColor(R.color.ola_black_semi_transparency));
                    this.f10215v.findViewById(R.id.no_location_pointer).setVisibility(8);
                    this.f10215v.findViewById(R.id.no_location_alert).setVisibility(8);
                    this.f10215v.findViewById(R.id.rating_star_inactive).setVisibility(0);
                    ((TextView) this.f10215v.findViewById(R.id.no_location_text)).setText(R.string.track_ride_overlay_message);
                    ((TextView) this.f10215v.findViewById(R.id.no_location_message)).setText(R.string.track_ride_overlay_tap_message);
                    this.f10215v.findViewById(R.id.rating_star_pointer).setVisibility(0);
                    this.f10215v.setVisibility(0);
                    m13932c("Before start trip");
                    return;
                }
                intent = new Intent(this, RateRideActivity.class);
                intent.putExtra(Constants.DRIVER_IMAGE_URL, Sherlock.m13349d(this.f10204k != null ? this.f10204k.getDriverImageUrl() : Trace.NULL));
                intent.putExtra(Constants.ARG_BOOKING_ID, Sherlock.m13349d(this.f10203j.getBookingId()));
                if ("local_taxi".equalsIgnoreCase(this.f10204k.getCategory_id()) || "local_auto".equalsIgnoreCase(this.f10204k.getCategory_id())) {
                    intent.putExtra(Constants.ARG_CAR_CATEGORY_ID, "auto");
                } else {
                    intent.putExtra(Constants.ARG_CAR_CATEGORY_ID, "cab");
                }
                startActivity(intent);
                overridePendingTransition(R.anim.slideup, R.anim.noanimation);
                m13932c("After start trip");
            case R.id.sos_image:
                m13961o();
            case R.id.view_no_location_overlay:
                this.f10215v.setVisibility(8);
                this.f10173B.setBackgroundColor(getResources().getColor(R.color.transparent));
            default:
        }
    }

    private void m13945g() {
        Map hashMap = new HashMap();
        hashMap.put("Recharge screen type", "Track Ride");
        Localytics.tagEvent("Recharge hint clicked", hashMap);
    }

    private void m13932c(String str) {
        Map hashMap = new HashMap();
        hashMap.put("Time of click", str);
        Localytics.tagEvent("Rate ride on Track ride", hashMap);
    }

    public void m13980b(String str) {
        m13916a((LayoutInflater) getSystemService("layout_inflater"), str);
    }

    private void m13916a(LayoutInflater layoutInflater, String str) {
        if (this.f10194W.m13218d().getBookingCancelReasons() == null || !this.f10194W.m13218d().getBookingCancelReasons().containsKey(str)) {
            View inflate = layoutInflater.inflate(R.layout.view_dialog_messsage_yes_no, null, false);
            AlertDialog create = new Builder(this).setView(inflate).create();
            ((TextView) inflate.findViewById(R.id.item_header)).setText("Cancel Ride");
            ((TextView) inflate.findViewById(R.id.item_message)).setText("Are you sure you want to cancel booking ?");
            inflate.findViewById(R.id.button_yes).setOnClickListener(new AnonymousClass11(this, create, str));
            inflate.findViewById(R.id.button_no).setOnClickListener(new AnonymousClass12(this, create));
            create.setCancelable(false);
            create.show();
            return;
        }
        inflate = layoutInflater.inflate(R.layout.view_cancel_list_dialog, null, false);
        this.f10198c = (ListView) inflate.findViewById(R.id.item_list);
        this.f10185N = inflate.findViewById(R.id.button_yes);
        this.f10185N.setEnabled(false);
        C0911a c0911a = new C0911a(this, this, (List) this.f10194W.m13218d().getBookingCancelReasons().get(str));
        this.f10198c.setAdapter(c0911a);
        c0911a.notifyDataSetChanged();
        AlertDialog create2 = new Builder(this).setView(inflate).create();
        this.f10198c.setFocusable(false);
        this.f10198c.setOnItemClickListener(new AnonymousClass13(this, c0911a));
        this.f10185N.setOnClickListener(new C09022(this, create2, str));
        inflate.findViewById(R.id.button_no).setOnClickListener(new C09033(this, create2));
        create2.setCancelable(false);
        create2.show();
    }

    public void onBackPressed() {
        if (Utils.m14909a(getApplicationContext())) {
            Localytics.tagEvent("Track Ride Closed");
            m13956m();
            return;
        }
        m13919a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
    }

    private void m13928b(String str, String str2) {
        Map hashMap = new HashMap();
        if ("local_taxi".equalsIgnoreCase(str2)) {
            hashMap.put("KP Cancel Reason ", str);
            Localytics.tagEvent("KP Cancel Reason Submitted", hashMap);
        } else if ("local_auto".equalsIgnoreCase(str2)) {
            hashMap.put("AUTO Cancel Reason ", str);
            Localytics.tagEvent("AUTO Cancel Reason Submitted", hashMap);
        } else if ("delivery".equalsIgnoreCase(str2)) {
            Localytics.tagEvent("Delivery Cancelled ");
        } else {
            hashMap.put("Cab category", str2);
            hashMap.put("City taxi Cancel Reason ", str);
            Localytics.tagEvent("City taxi Cancel Reason Submitted", hashMap);
        }
    }

    private void m13947h() {
        Map hashMap = new HashMap();
        hashMap.put("App name", "sms");
        Localytics.tagEvent("Ride shared", hashMap);
    }

    private void m13937d(String str) {
        Map hashMap = new HashMap();
        hashMap.put("Cab category", str);
        Localytics.tagEvent("Ride Cancelled", hashMap);
        Leanplum.track("Ride Cancelled", hashMap);
    }

    private void m13949i() {
        if (this.f10206m == null) {
            this.f10206m = new Handler();
        }
        this.f10206m.postDelayed(this.ad, (long) f10171g);
    }

    private void m13951j() {
        this.f10202i = new OlaMapFragment.OlaMapFragment().m13355a(17.0f).m13359a((OnMapReadyCallback) this).m13366b(true).m13364a(false).m13365a();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_map, this.f10202i).commit();
    }

    private void m13953k() {
        this.f10194W.m13169a(f10168a);
        this.f10194W.m13250k(new WeakReference(this.ab), f10168a);
    }

    private void m13921a(boolean z) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.putBoolean(Constants.PREF_EMERGENCY_IS_PHONE_VERIFIED, z);
        edit.apply();
    }

    private void m13954l() {
        Sherlock.m13345b("Ins track ride shown");
        if (this.f10204k != null) {
            ((TextView) findViewById(R.id.driverNameText)).setText(this.f10204k.getDriverName());
            ((TextView) findViewById(R.id.carSpecificationText)).setText((this.f10204k.getColor() + " " + this.f10204k.getCarModel()).trim() + "\n" + this.f10204k.getLicense_number());
            if (this.f10204k.getDriverImageUrl() != null) {
                m13941e(this.f10204k.getDriverImageUrl());
            } else {
                this.f10218y.setBackgroundResource(R.drawable.driver_full_placeholder);
            }
        }
    }

    private void m13941e(String str) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.driver_image_size);
        this.f10194W.m13176a(new WeakReference(this.aa), str, dimensionPixelSize, dimensionPixelSize, Config.RGB_565, f10168a);
    }

    private void m13956m() {
        try {
            this.f10206m.removeCallbacks(this.ad);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f10194W.m13243i(new WeakReference(this.ac), f10168a);
    }

    private void m13958n() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private Bitmap m13911a(int i, String str) {
        int i2 = 50;
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        int i3 = (int) getResources().getDisplayMetrics().scaledDensity;
        int i4 = i3 * 60;
        int i5 = i3 * 76;
        Config config = Config.ARGB_8888;
        if (i4 <= 0) {
            i4 = 50;
        }
        if (i5 > 0) {
            i2 = i5;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactoryInstrumentation.decodeResource(getResources(), i), i4, i2, true);
        Canvas canvas = new Canvas(createScaledBitmap);
        float f = (float) (i4 / 2);
        float f2 = (float) (i4 / 2);
        Paint paint = new Paint(1);
        paint.setColor(Color.parseColor("#d4db28"));
        paint.setStyle(Style.FILL);
        paint.setTextSize((float) (i3 * 18));
        paint.setTypeface(createFromAsset);
        if (str.length() == 3) {
            canvas.drawText(str, f - ((float) (i3 * 15)), f2, paint);
        } else if (str.length() == 2) {
            canvas.drawText(str, f - ((float) (i3 * 10)), f2, paint);
        } else if (str.length() == 1) {
            canvas.drawText(str, f - ((float) (i3 * 6)), f2, paint);
        }
        paint.setTextSize((float) (i3 * 16));
        canvas.drawText(" min ", f - ((float) (i3 * 18)), f2 + ((float) (i3 * 15)), paint);
        return createScaledBitmap;
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
        this.f10192U = true;
        this.f10194W.m13169a(f10168a);
        if (this.f10206m != null) {
            this.f10206m.removeCallbacks(this.ad);
        }
    }

    private void m13961o() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (defaultSharedPreferences.getBoolean(Constants.PREF_EMERGENCY_IS_PHONE_VERIFIED, false)) {
            m13963p();
        } else if (Trace.NULL.equalsIgnoreCase(defaultSharedPreferences.getString(Constants.PREF_EMERGENCY_PHONE, Trace.NULL))) {
            if (Ola.f11479C != null) {
                r0 = Ola.f11479C;
            } else {
                r0 = getString(R.string.sos_ec_header);
            }
            if (Ola.f11480D != null) {
                r1 = Ola.f11480D;
            } else {
                r1 = getString(R.string.sos_ec_add_contact);
            }
            m13933c(r0, r1);
        } else {
            if (Ola.f11477A != null) {
                r0 = Ola.f11477A;
            } else {
                r0 = getString(R.string.sos_ec_header);
            }
            if (Ola.f11478B != null) {
                r1 = Ola.f11478B;
            } else {
                r1 = getString(R.string.sos_ec_verify_contact);
            }
            m13919a(r0, r1);
        }
    }

    private void m13963p() {
        startActivity(new Intent(this, SosTimerActivity.class));
    }

    private void m13933c(String str, String str2) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.button_yes)).setText(getString(R.string.sos_add_now));
        ((TextView) inflate.findViewById(R.id.button_no)).setText(getString(R.string.sos_later));
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_yes).setOnClickListener(new C09044(this, create));
        inflate.findViewById(R.id.button_no).setOnClickListener(new C09055(this, create));
        create.setCancelable(false);
        create.show();
    }

    private void m13964q() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra(Constants.SHOW_EC, true);
        intent.setFlags(268435456);
        startActivity(intent);
    }

    public void m13978a(GoogleMap googleMap) {
        this.f10201h = googleMap;
    }
}
