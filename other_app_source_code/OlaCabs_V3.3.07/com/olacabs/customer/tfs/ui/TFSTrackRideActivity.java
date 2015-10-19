package com.olacabs.customer.tfs.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Telephony.Sms;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.m4b.maps.CameraUpdateFactory;
import com.google.android.m4b.maps.GoogleMap;
import com.google.android.m4b.maps.OnMapReadyCallback;
import com.google.android.m4b.maps.model.BitmapDescriptorFactory;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.LatLngBounds.C0595a;
import com.google.android.m4b.maps.model.MarkerOptions;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.BasicResponse;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p078c.OlaMapFragment;
import com.olacabs.customer.tfs.p081a.TFSCallDriverResponse;
import com.olacabs.customer.tfs.p081a.TFSShareRideResponse;
import com.olacabs.customer.tfs.p081a.TFSTrackTaxiResponse;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.OlaApplicationUtils;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
public class TFSTrackRideActivity extends FragmentActivity implements OnClickListener, OnMapReadyCallback, TraceFieldInterface {
    private static final JoinPoint f9472Q = null;
    private static final JoinPoint f9473R = null;
    private static final JoinPoint f9474S = null;
    private static final JoinPoint f9475T = null;
    public static final String f9476a;
    private static int f9477e;
    private View f9478A;
    private int f9479B;
    private View f9480C;
    private String f9481D;
    private int f9482E;
    private boolean f9483F;
    private DataManager f9484G;
    private TextView f9485H;
    private TextView f9486I;
    private String f9487J;
    private String f9488K;
    private String f9489L;
    private aj f9490M;
    private aj f9491N;
    private Runnable f9492O;
    private aj f9493P;
    ListView f9494b;
    AlertDialog f9495c;
    ProgressDialog f9496d;
    private GoogleMap f9497f;
    private OlaMapFragment f9498g;
    private TFSTrackTaxiResponse f9499h;
    private aj f9500i;
    private Handler f9501j;
    private TextView f9502k;
    private TextView f9503l;
    private TextView f9504m;
    private boolean f9505n;
    private ImageView f9506o;
    private ImageView f9507p;
    private TextView f9508q;
    private ImageView f9509r;
    private RelativeLayout f9510s;
    private RelativeLayout f9511t;
    private boolean f9512u;
    private C0595a f9513v;
    private LatLng f9514w;
    private LatLngBounds f9515x;
    private int f9516y;
    private int f9517z;

    /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.10 */
    class AnonymousClass10 implements OnClickListener {
        final /* synthetic */ AlertDialog f9453a;
        final /* synthetic */ TFSTrackRideActivity f9454b;

        AnonymousClass10(TFSTrackRideActivity tFSTrackRideActivity, AlertDialog alertDialog) {
            this.f9454b = tFSTrackRideActivity;
            this.f9453a = alertDialog;
        }

        public void onClick(View view) {
            this.f9454b.f9508q.setEnabled(true);
            this.f9453a.dismiss();
            this.f9454b.f9482E = -1;
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.1 */
    class C08011 implements aj {
        final /* synthetic */ TFSTrackRideActivity f9455a;

        C08011(TFSTrackRideActivity tFSTrackRideActivity) {
            this.f9455a = tFSTrackRideActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("TrackRideRequester failed", th);
        }

        public void onSuccess(Object obj) {
            if (!this.f9455a.isFinishing()) {
                this.f9455a.f9499h = (TFSTrackTaxiResponse) obj;
                if (!this.f9455a.f9499h.getStatus().equalsIgnoreCase("SUCCESS")) {
                    this.f9455a.finish();
                } else if (this.f9455a.f9499h.getServiceStatus() == null || !this.f9455a.f9499h.getServiceStatus().equalsIgnoreCase("completed")) {
                    if (this.f9455a.f9499h.getServiceStatus() != null && this.f9455a.f9499h.getServiceStatus().equalsIgnoreCase("Cancelled")) {
                        this.f9455a.m13446k();
                    }
                    if (this.f9455a.f9499h.getState() == null || !this.f9455a.f9499h.getState().equalsIgnoreCase("meteron")) {
                        this.f9455a.f9508q.setVisibility(0);
                    } else {
                        this.f9455a.f9508q.setVisibility(8);
                        this.f9455a.f9504m.setVisibility(0);
                    }
                    this.f9455a.m13433e();
                    this.f9455a.m13438g();
                    this.f9455a.f9510s.setVisibility(0);
                    this.f9455a.f9511t.setVisibility(8);
                } else {
                    this.f9455a.finish();
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.2 */
    class C08022 implements OnClickListener {
        final /* synthetic */ TFSTrackRideActivity f9456a;

        C08022(TFSTrackRideActivity tFSTrackRideActivity) {
            this.f9456a = tFSTrackRideActivity;
        }

        public void onClick(View view) {
            this.f9456a.f9495c.dismiss();
            this.f9456a.finish();
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.3 */
    class C08033 implements aj {
        final /* synthetic */ TFSTrackRideActivity f9457a;

        C08033(TFSTrackRideActivity tFSTrackRideActivity) {
            this.f9457a = tFSTrackRideActivity;
        }

        public void onFailure(Throwable th) {
            this.f9457a.f9496d.dismiss();
            OLog.m13310a("TFSDriver Details Requester failed", th);
        }

        public void onSuccess(Object obj) {
            this.f9457a.m13419a((TFSCallDriverResponse) obj);
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.4 */
    class C08044 implements aj {
        final /* synthetic */ TFSTrackRideActivity f9458a;

        C08044(TFSTrackRideActivity tFSTrackRideActivity) {
            this.f9458a = tFSTrackRideActivity;
        }

        public void onFailure(Throwable th) {
            this.f9458a.f9496d.dismiss();
            OLog.m13310a("TmRideShareDataRequester failed", th);
        }

        public void onSuccess(Object obj) {
            this.f9458a.f9496d.dismiss();
            TFSShareRideResponse tFSShareRideResponse = (TFSShareRideResponse) obj;
            if (tFSShareRideResponse.getStatus().equalsIgnoreCase("SUCCESS") && tFSShareRideResponse.getResponse_data() != null && tFSShareRideResponse.getResponse_data().getShorten_url() != null) {
                this.f9458a.m13426b(tFSShareRideResponse.getResponse_data().getShorten_url());
            }
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.5 */
    class C08055 implements Runnable {
        final /* synthetic */ TFSTrackRideActivity f9459a;

        C08055(TFSTrackRideActivity tFSTrackRideActivity) {
            this.f9459a = tFSTrackRideActivity;
        }

        public void run() {
            if (Utils.m14909a(this.f9459a.getApplicationContext())) {
                this.f9459a.m13441i();
                return;
            }
            this.f9459a.m13454a();
            this.f9459a.m13438g();
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.6 */
    class C08066 implements aj {
        final /* synthetic */ TFSTrackRideActivity f9460a;

        C08066(TFSTrackRideActivity tFSTrackRideActivity) {
            this.f9460a = tFSTrackRideActivity;
        }

        public void onFailure(Throwable th) {
            this.f9460a.f9496d.dismiss();
            OLog.m13310a("CancelRideRequester failed", th);
            this.f9460a.m13423a(this.f9460a.getString(R.string.connection_time_out_error_title), this.f9460a.getString(R.string.connection_time_out_error_desc));
        }

        public void onSuccess(Object obj) {
            this.f9460a.f9496d.dismiss();
            if (((BasicResponse) obj).getStatus().equalsIgnoreCase("SUCCESS")) {
                this.f9460a.f9501j.removeCallbacks(this.f9460a.f9492O);
                this.f9460a.finish();
            }
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.7 */
    class C08077 implements OnClickListener {
        final /* synthetic */ TFSTrackRideActivity f9461a;

        C08077(TFSTrackRideActivity tFSTrackRideActivity) {
            this.f9461a = tFSTrackRideActivity;
        }

        public void onClick(View view) {
            this.f9461a.f9495c.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.8 */
    class C08088 implements OnItemClickListener {
        final /* synthetic */ C0811a f9462a;
        final /* synthetic */ TFSTrackRideActivity f9463b;

        C08088(TFSTrackRideActivity tFSTrackRideActivity, C0811a c0811a) {
            this.f9463b = tFSTrackRideActivity;
            this.f9462a = c0811a;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ImageView imageView = (ImageView) view.findViewById(R.id.chk);
            this.f9463b.f9481D = ((TextView) view.findViewById(R.id.item_text)).getText().toString();
            imageView.setSelected(true);
            this.f9463b.f9482E = i;
            this.f9462a.notifyDataSetChanged();
            if (this.f9463b.f9481D == null || this.f9463b.f9481D.length() <= 0) {
                this.f9463b.f9480C.setEnabled(false);
            } else {
                this.f9463b.f9480C.setEnabled(true);
            }
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.9 */
    class C08099 implements OnClickListener {
        final /* synthetic */ AlertDialog f9464a;
        final /* synthetic */ TFSTrackRideActivity f9465b;

        C08099(TFSTrackRideActivity tFSTrackRideActivity, AlertDialog alertDialog) {
            this.f9465b = tFSTrackRideActivity;
            this.f9464a = alertDialog;
        }

        public void onClick(View view) {
            this.f9465b.f9508q.setEnabled(true);
            this.f9464a.dismiss();
            this.f9465b.f9482E = -1;
            this.f9465b.m13430c(this.f9465b.f9481D);
            this.f9465b.f9484G.m13244i(new WeakReference(this.f9465b.f9493P), this.f9465b.f9488K, this.f9465b.f9481D);
            this.f9465b.f9496d.show();
        }
    }

    /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.a */
    private class C0811a extends BaseAdapter {
        final /* synthetic */ TFSTrackRideActivity f9469a;
        private Context f9470b;
        private String[] f9471c;

        /* renamed from: com.olacabs.customer.tfs.ui.TFSTrackRideActivity.a.a */
        public class C0810a {
            public TextView f9466a;
            public ImageView f9467b;
            final /* synthetic */ C0811a f9468c;

            public C0810a(C0811a c0811a) {
                this.f9468c = c0811a;
            }
        }

        public C0811a(TFSTrackRideActivity tFSTrackRideActivity, Context context, String[] strArr) {
            this.f9469a = tFSTrackRideActivity;
            this.f9470b = context;
            this.f9471c = strArr;
        }

        public int getCount() {
            return this.f9471c.length;
        }

        public Object getItem(int i) {
            return this.f9471c[i];
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            boolean z;
            if (view == null) {
                C0810a c0810a = new C0810a(this);
                view = ((Activity) this.f9470b).getLayoutInflater().inflate(R.layout.cancel_list_item, viewGroup, false);
                c0810a.f9466a = (TextView) view.findViewById(R.id.item_text);
                c0810a.f9467b = (ImageView) view.findViewById(R.id.chk);
                view.setTag(c0810a);
            }
            C0810a c0810a2 = (C0810a) view.getTag();
            if (!isEmpty()) {
                c0810a2.f9466a.setText(this.f9471c[i]);
            }
            ImageView imageView = c0810a2.f9467b;
            if (this.f9469a.f9482E == i) {
                z = true;
            } else {
                z = false;
            }
            imageView.setSelected(z);
            return view;
        }
    }

    private static void m13448l() {
        Factory factory = new Factory("TFSTrackRideActivity.java", TFSTrackRideActivity.class);
        f9472Q = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.tfs.ui.TFSTrackRideActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 231);
        f9473R = factory.m15070a("method-execution", factory.m15071a("4", "onResume", "com.olacabs.customer.tfs.ui.TFSTrackRideActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 292);
        f9474S = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.tfs.ui.TFSTrackRideActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 319);
        f9475T = factory.m15070a("method-execution", factory.m15071a("4", "onPause", "com.olacabs.customer.tfs.ui.TFSTrackRideActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 627);
    }

    public TFSTrackRideActivity() {
        this.f9500i = new C08011(this);
        this.f9505n = true;
        this.f9512u = false;
        this.f9481D = Trace.NULL;
        this.f9482E = -1;
        this.f9483F = false;
        this.f9490M = new C08033(this);
        this.f9491N = new C08044(this);
        this.f9492O = new C08055(this);
        this.f9493P = new C08066(this);
    }

    static {
        m13448l();
        f9476a = TFSTrackRideActivity.class.getSimpleName();
        f9477e = TraceMachine.UNHEALTHY_TRACE_TIMEOUT;
    }

    private void m13419a(TFSCallDriverResponse tFSCallDriverResponse) {
        this.f9485H.setText(tFSCallDriverResponse.getDriver_name());
        this.f9486I.setText(tFSCallDriverResponse.getVehicle_number());
        this.f9487J = tFSCallDriverResponse.getDriver_number();
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("TFSTrackRideActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "TFSTrackRideActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TFSTrackRideActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9472Q, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f9488K = extras.getString(Constants.ARG_BOOKING_ID, Trace.NULL);
            this.f9489L = extras.getString(Constants.CITY, Trace.NULL);
        }
        if (TextUtils.isEmpty(this.f9488K)) {
            finish();
        }
        setContentView(R.layout.activity_tfs_track_ride);
        this.f9502k = (TextView) findViewById(R.id.callText);
        this.f9502k.setOnClickListener(this);
        this.f9503l = (TextView) findViewById(R.id.no_internet_errorText);
        this.f9504m = (TextView) findViewById(R.id.custom_message);
        this.f9506o = (ImageView) findViewById(R.id.currentPositionImage);
        this.f9506o.setOnClickListener(this);
        this.f9507p = (ImageView) findViewById(R.id.backImageView);
        this.f9508q = (TextView) findViewById(R.id.cancelRideText);
        this.f9509r = (ImageView) findViewById(R.id.shareText);
        this.f9509r.setOnClickListener(this);
        this.f9507p.setOnClickListener(this);
        this.f9508q.setOnClickListener(this);
        this.f9510s = (RelativeLayout) findViewById(R.id.mapViewLayout);
        this.f9511t = (RelativeLayout) findViewById(R.id.empty_view_layout);
        this.f9485H = (TextView) findViewById(R.id.tfs_driver_name);
        this.f9486I = (TextView) findViewById(R.id.tfs_vehicle_number);
        m13428c();
        this.f9484G = ((OlaApp) getApplication()).m12878a();
        Localytics.tagScreen("Track Ride");
        m13440h();
        this.f9478A = findViewById(R.id.layout_driver_details);
        this.f9478A.measure(0, 0);
        this.f9479B = this.f9478A.getMeasuredHeight();
        this.f9496d = new ProgressDialog(this, R.style.TransparentProgressDialog);
        this.f9496d.setIndeterminateDrawable(getResources().getDrawable(R.drawable.custom_progress_background));
        this.f9496d.setCancelable(false);
        TraceMachine.exitMethod();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f9496d != null && this.f9496d.isShowing()) {
            this.f9496d.dismiss();
        }
    }

    private void m13428c() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f9516y = displayMetrics.heightPixels;
        this.f9517z = displayMetrics.widthPixels;
    }

    protected void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f9473R, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            if (Utils.m14909a(getApplicationContext())) {
                m13457b();
                m13441i();
                m13432d();
            } else {
                m13454a();
                m13438g();
            }
            ActivityTraceAspect.m12823a().m12828d(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    private void m13432d() {
        this.f9484G.m13251k(new WeakReference(this.f9490M), this.f9488K, f9476a);
    }

    public void onEvent(ah ahVar) {
        if (ahVar.isConnected()) {
            m13457b();
        } else {
            m13454a();
        }
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9474S, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void m13454a() {
        this.f9508q.setEnabled(false);
        this.f9503l.setVisibility(0);
        this.f9503l.setText(getString(R.string.no_internet));
    }

    public void m13457b() {
        this.f9508q.setEnabled(true);
        this.f9503l.setVisibility(8);
    }

    private void m13423a(String str, String str2) {
        if (this.f9495c == null || !this.f9495c.isShowing()) {
            View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
            this.f9495c = new Builder(this).setView(inflate).create();
            ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
            ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
            inflate.findViewById(R.id.button_ok).setOnClickListener(new C08077(this));
            this.f9495c.show();
        }
    }

    private void m13433e() {
        if (this.f9498g.m13399e()) {
            if (this.f9499h != null) {
                this.f9497f.m10076c();
                double doubleValue = Double.valueOf(this.f9499h.getLatitude()).doubleValue();
                double doubleValue2 = Double.valueOf(this.f9499h.getLongitude()).doubleValue();
                double doubleValue3 = Double.valueOf(this.f9499h.getPickup_area_latitude()).doubleValue();
                double doubleValue4 = Double.valueOf(this.f9499h.getPickup_area_longitude()).doubleValue();
                if (this.f9499h.getState() != null && this.f9499h.getState().equalsIgnoreCase("meteron")) {
                    this.f9497f.m10068a(new MarkerOptions().m10759a(new LatLng(doubleValue3, doubleValue4)).m10760a(BitmapDescriptorFactory.m10823a((int) R.drawable.ic_map_marker)));
                } else if (this.f9499h.getState() == null || !this.f9499h.getState().equalsIgnoreCase("@Pickup")) {
                    this.f9497f.m10068a(new MarkerOptions().m10759a(new LatLng(doubleValue3, doubleValue4)).m10760a(BitmapDescriptorFactory.m10824a(m13416a((int) R.drawable.track_ride_eta, Trace.NULL + this.f9499h.getEta()))));
                } else {
                    this.f9497f.m10068a(new MarkerOptions().m10759a(new LatLng(doubleValue3, doubleValue4)).m10760a(BitmapDescriptorFactory.m10824a(m13416a((int) R.drawable.track_ride_eta, "0"))));
                }
                LatLng latLng = new LatLng(Double.valueOf(this.f9499h.getLatitude()).doubleValue(), Double.valueOf(this.f9499h.getLongitude()).doubleValue());
                this.f9514w = OlaApplicationUtils.m14900a(doubleValue3, doubleValue4, doubleValue, doubleValue2);
                if (!(this.f9514w == null || this.f9512u)) {
                    this.f9513v = new C0595a();
                    this.f9513v.m10738a(new LatLng(this.f9514w.f7554a, this.f9514w.f7555b));
                    this.f9513v.m10738a(latLng);
                    this.f9515x = this.f9513v.m10739a();
                    this.f9497f.m10071a(CameraUpdateFactory.m7793a(this.f9515x, this.f9517z, this.f9516y, 50));
                    this.f9512u = true;
                }
                this.f9497f.m10068a(new MarkerOptions().m10759a(latLng).m10760a(BitmapDescriptorFactory.m10823a((int) R.drawable.tfs_map_icon)));
            }
            this.f9497f.m10070a(0, this.f9479B, 0, this.f9479B);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backImageView:
                onBackPressed();
            case R.id.cancelRideText:
                if (Utils.m14909a(getApplicationContext())) {
                    m13457b();
                    this.f9508q.setEnabled(false);
                    m13456a("TFS");
                    return;
                }
                m13454a();
                m13423a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            case R.id.callText:
                if (Utils.m14924g(this.f9487J)) {
                    Localytics.tagEvent("TFS Driver Called");
                    startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + this.f9487J)));
                }
            case R.id.shareText:
                m13435f();
                this.f9496d.show();
                this.f9484G.m13254l(new WeakReference(this.f9491N), this.f9488K, f9476a);
            case R.id.currentPositionImage:
                if (this.f9498g.m13399e() && this.f9499h != null && this.f9499h.getLatitude() != null && this.f9499h.getLongitude() != null) {
                    this.f9498g.m13394a(new LatLng(Double.valueOf(this.f9499h.getLatitude()).doubleValue(), Double.valueOf(this.f9499h.getLongitude()).doubleValue()), true, 17);
                }
            default:
        }
    }

    private void m13426b(String str) {
        try {
            if (VERSION.SDK_INT >= 19) {
                String defaultSmsPackage = Sms.getDefaultSmsPackage(this);
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType(HTTP.PLAIN_TEXT_TYPE);
                intent.putExtra("android.intent.extra.TEXT", str);
                if (defaultSmsPackage != null) {
                    intent.setPackage(defaultSmsPackage);
                }
                startActivity(intent);
                return;
            }
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(Uri.parse("sms:"));
            intent2.putExtra("sms_body", str);
            startActivity(intent2);
        } catch (ActivityNotFoundException e) {
            OLog.m13313b("SMS app not found", new Object[0]);
        }
    }

    public void m13456a(String str) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_cancel_list_dialog, null, false);
        this.f9494b = (ListView) inflate.findViewById(R.id.item_list);
        this.f9480C = inflate.findViewById(R.id.button_yes);
        this.f9480C.setEnabled(false);
        C0811a c0811a = new C0811a(this, this, getResources().getStringArray(R.array.tfs_cancel_reasons));
        this.f9494b.setAdapter(c0811a);
        c0811a.notifyDataSetChanged();
        AlertDialog create = new Builder(this).setView(inflate).create();
        this.f9494b.setFocusable(false);
        this.f9494b.setOnItemClickListener(new C08088(this, c0811a));
        this.f9480C.setOnClickListener(new C08099(this, create));
        inflate.findViewById(R.id.button_no).setOnClickListener(new AnonymousClass10(this, create));
        create.setCancelable(false);
        create.show();
    }

    public void onBackPressed() {
        m13444j();
    }

    private void m13430c(String str) {
        Map hashMap = new HashMap();
        hashMap.put("Cancel Reason", str);
        Localytics.tagEvent("TFS Ride Cancelled", hashMap);
    }

    private void m13435f() {
        Map hashMap = new HashMap();
        hashMap.put("App name", "sms");
        Localytics.tagEvent("TFS Ride Shared", hashMap);
    }

    private void m13438g() {
        if (this.f9501j == null) {
            this.f9501j = new Handler();
        }
        this.f9501j.postDelayed(this.f9492O, (long) f9477e);
    }

    private void m13440h() {
        this.f9498g = new OlaMapFragment.OlaMapFragment().m13355a(17.0f).m13359a((OnMapReadyCallback) this).m13366b(true).m13364a(false).m13365a();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_map, this.f9498g).commit();
    }

    private void m13441i() {
        this.f9484G.m13169a(f9476a);
        this.f9484G.m13248j(new WeakReference(this.f9500i), this.f9488K, f9476a);
    }

    private void m13444j() {
        try {
            if (this.f9501j != null) {
                this.f9501j.removeCallbacks(this.f9492O);
            }
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            if (!defaultSharedPreferences.contains(Constants.PREF_COACHMARK_TFS)) {
                defaultSharedPreferences.edit().putBoolean(Constants.PREF_COACHMARK_TFS, true).apply();
            }
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap m13416a(int i, String str) {
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

    protected void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9475T, (Object) this, (Object) this));
        super.onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
        this.f9484G.m13169a(f9476a);
        if (this.f9501j != null) {
            this.f9501j.removeCallbacks(this.f9492O);
        }
    }

    private void m13446k() {
        if (this.f9495c == null || !this.f9495c.isShowing()) {
            View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
            this.f9495c = new Builder(this).setView(inflate).create();
            ((TextView) inflate.findViewById(R.id.item_header)).setText(getString(R.string.tfs_ride_cancelled));
            if (this.f9499h.getBooking_id() != null) {
                ((TextView) inflate.findViewById(R.id.item_message)).setText(String.format(getString(R.string.tfs_ride_cancelled_data), new Object[]{this.f9499h.getBooking_id()}));
            } else {
                ((TextView) inflate.findViewById(R.id.item_message)).setText(getString(R.string.tfs_ride_cancelled_data_flat));
            }
            inflate.findViewById(R.id.button_ok).setOnClickListener(new C08022(this));
            this.f9495c.show();
        }
    }

    public void m13455a(GoogleMap googleMap) {
        this.f9497f = googleMap;
    }
}
