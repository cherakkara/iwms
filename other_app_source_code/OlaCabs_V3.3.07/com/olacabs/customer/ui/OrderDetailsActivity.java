package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
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
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.google.android.m4b.maps.GoogleMap;
import com.google.android.m4b.maps.OnMapReadyCallback;
import com.google.android.m4b.maps.model.BitmapDescriptorFactory;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.CallSupportCommand;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.ad;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.aq;
import com.olacabs.customer.p076d.bb;
import com.olacabs.customer.p076d.bc;
import com.olacabs.customer.p076d.bs;
import com.olacabs.customer.p076d.bt;
import com.olacabs.customer.p076d.bu;
import com.olacabs.customer.p076d.du;
import com.olacabs.customer.p078c.OlaMapFragment;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
public class OrderDetailsActivity extends FragmentActivity implements OnClickListener, OnMapReadyCallback, TraceFieldInterface {
    private static final JoinPoint f9815L = null;
    private static final JoinPoint f9816M = null;
    private static final JoinPoint f9817N = null;
    private static final JoinPoint f9818O = null;
    private static final String f9819a;
    private static int f9820s;
    private ListView f9821A;
    private View f9822B;
    private int f9823C;
    private OlaMapFragment f9824D;
    private GoogleMap f9825E;
    private TextView f9826F;
    private aj f9827G;
    private aj f9828H;
    private Runnable f9829I;
    private Runnable f9830J;
    private aj f9831K;
    private OlaApp f9832b;
    private DataManager f9833c;
    private long f9834d;
    private du f9835e;
    private ArrayList<bc> f9836f;
    private String f9837g;
    private HashMap<Integer, Integer> f9838h;
    private HashMap<Integer, Integer> f9839i;
    private TextView f9840j;
    private TextView f9841k;
    private TextView f9842l;
    private TextView f9843m;
    private View f9844n;
    private View f9845o;
    private LinearLayout f9846p;
    private Double f9847q;
    private View f9848r;
    private Handler f9849t;
    private bb f9850u;
    private ad f9851v;
    private String f9852w;
    private List<String> f9853x;
    private List<bs> f9854y;
    private String f9855z;

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.13 */
    class AnonymousClass13 implements OnItemClickListener {
        final /* synthetic */ C0863a f9791a;
        final /* synthetic */ OrderDetailsActivity f9792b;

        AnonymousClass13(OrderDetailsActivity orderDetailsActivity, C0863a c0863a) {
            this.f9792b = orderDetailsActivity;
            this.f9791a = c0863a;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ImageView imageView = (ImageView) view.findViewById(R.id.chk);
            this.f9792b.f9855z = ((TextView) view.findViewById(R.id.item_text)).getText().toString();
            imageView.setSelected(true);
            this.f9792b.f9823C = i;
            this.f9791a.notifyDataSetChanged();
            if (this.f9792b.f9855z == null || this.f9792b.f9855z.length() <= 0) {
                this.f9792b.f9822B.setEnabled(false);
            } else {
                this.f9792b.f9822B.setEnabled(true);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.14 */
    class AnonymousClass14 implements OnClickListener {
        final /* synthetic */ AlertDialog f9793a;
        final /* synthetic */ OrderDetailsActivity f9794b;

        AnonymousClass14(OrderDetailsActivity orderDetailsActivity, AlertDialog alertDialog) {
            this.f9794b = orderDetailsActivity;
            this.f9793a = alertDialog;
        }

        public void onClick(View view) {
            this.f9794b.m13673a(this.f9794b.f9855z);
            this.f9794b.f9833c.m13172a(new WeakReference(this.f9794b.f9828H), this.f9794b.f9834d, this.f9794b.f9855z, OrderDetailsActivity.f9819a);
            this.f9793a.dismiss();
            this.f9794b.f9823C = -1;
        }
    }

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.1 */
    class C08531 implements aj {
        final /* synthetic */ OrderDetailsActivity f9795a;

        C08531(OrderDetailsActivity orderDetailsActivity) {
            this.f9795a = orderDetailsActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to fetch food order detail", th);
            VolleyError volleyError = (VolleyError) th;
            this.f9795a.f9848r.setVisibility(8);
            this.f9795a.f9840j.setVisibility(8);
            this.f9795a.f9845o.setVisibility(8);
            if (volleyError == null || volleyError.f464a == null) {
                try {
                    if (Utils.m14909a(this.f9795a.getApplicationContext())) {
                        this.f9795a.m13679b(this.f9795a.getString(R.string.generic_failure_header), this.f9795a.getString(R.string.generic_failure_desc));
                        return;
                    } else {
                        this.f9795a.m13705a();
                        return;
                    }
                } catch (Exception e) {
                    return;
                }
            }
            byte[] bArr = volleyError.f464a.f498b;
            if (bArr != null) {
                try {
                    bt btVar = (bt) new Gson().m12343a(new String(bArr), bt.class);
                    this.f9795a.m13707a(btVar.getHeader(), btVar.getText());
                } catch (Exception e2) {
                }
            }
        }

        public void onSuccess(Object obj) {
            OLog.m13313b("Fetched food order details successfully", new Object[0]);
            this.f9795a.f9848r.setVisibility(0);
            this.f9795a.f9840j.setVisibility(0);
            this.f9795a.f9845o.setVisibility(0);
            try {
                du duVar = (du) obj;
                if (duVar != null) {
                    this.f9795a.f9835e = duVar;
                    this.f9795a.f9836f = this.f9795a.f9835e.getItems();
                    this.f9795a.m13668a(this.f9795a.f9835e.getDriverAttributes());
                    if (this.f9795a.f9835e.getStatus() != null) {
                        if (this.f9795a.f9835e.getStatus().equalsIgnoreCase("DELIVERED")) {
                            this.f9795a.finish();
                        } else if (this.f9795a.f9835e.getStatus().equalsIgnoreCase(Constants.RIDE_STATUS_CANCELLED)) {
                            this.f9795a.m13688g();
                        }
                    }
                    this.f9795a.m13674a(this.f9795a.f9836f);
                    return;
                }
                OLog.m13318e("Failed to fetch food order details", new Object[0]);
                this.f9795a.f9848r.setVisibility(8);
                this.f9795a.f9840j.setVisibility(8);
                this.f9795a.f9845o.setVisibility(8);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.2 */
    class C08542 implements OnClickListener {
        final /* synthetic */ AlertDialog f9796a;
        final /* synthetic */ OrderDetailsActivity f9797b;

        C08542(OrderDetailsActivity orderDetailsActivity, AlertDialog alertDialog) {
            this.f9797b = orderDetailsActivity;
            this.f9796a = alertDialog;
        }

        public void onClick(View view) {
            this.f9796a.dismiss();
            this.f9797b.f9823C = -1;
        }
    }

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.3 */
    class C08553 implements OnDismissListener {
        final /* synthetic */ OrderDetailsActivity f9798a;

        C08553(OrderDetailsActivity orderDetailsActivity) {
            this.f9798a = orderDetailsActivity;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            this.f9798a.f9840j.setEnabled(true);
        }
    }

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.4 */
    class C08564 implements OnClickListener {
        final /* synthetic */ AlertDialog f9799a;
        final /* synthetic */ OrderDetailsActivity f9800b;

        C08564(OrderDetailsActivity orderDetailsActivity, AlertDialog alertDialog) {
            this.f9800b = orderDetailsActivity;
            this.f9799a = alertDialog;
        }

        public void onClick(View view) {
            this.f9800b.m13673a("User Cancellation");
            this.f9799a.dismiss();
            this.f9800b.f9833c.m13172a(new WeakReference(this.f9800b.f9828H), this.f9800b.f9834d, "User Cancellation", OrderDetailsActivity.f9819a);
        }
    }

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.5 */
    class C08575 implements OnClickListener {
        final /* synthetic */ AlertDialog f9801a;
        final /* synthetic */ OrderDetailsActivity f9802b;

        C08575(OrderDetailsActivity orderDetailsActivity, AlertDialog alertDialog) {
            this.f9802b = orderDetailsActivity;
            this.f9801a = alertDialog;
        }

        public void onClick(View view) {
            this.f9801a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.6 */
    class C08586 implements OnDismissListener {
        final /* synthetic */ OrderDetailsActivity f9803a;

        C08586(OrderDetailsActivity orderDetailsActivity) {
            this.f9803a = orderDetailsActivity;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            this.f9803a.f9840j.setEnabled(true);
        }
    }

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.7 */
    class C08597 implements OnClickListener {
        final /* synthetic */ AlertDialog f9804a;
        final /* synthetic */ OrderDetailsActivity f9805b;

        C08597(OrderDetailsActivity orderDetailsActivity, AlertDialog alertDialog) {
            this.f9805b = orderDetailsActivity;
            this.f9804a = alertDialog;
        }

        public void onClick(View view) {
            this.f9804a.dismiss();
            this.f9805b.finish();
        }
    }

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.8 */
    class C08608 implements aj {
        final /* synthetic */ OrderDetailsActivity f9806a;

        C08608(OrderDetailsActivity orderDetailsActivity) {
            this.f9806a = orderDetailsActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to cancel", th);
            VolleyError volleyError = (VolleyError) th;
            if (volleyError != null && volleyError.f464a != null) {
                byte[] bArr = volleyError.f464a.f498b;
                if (bArr != null) {
                    try {
                        bt btVar = (bt) new Gson().m12343a(new String(bArr), bt.class);
                        this.f9806a.m13707a(btVar.getHeader(), btVar.getText());
                    } catch (Exception e) {
                    }
                }
            }
        }

        public void onSuccess(Object obj) {
            OLog.m13313b("Cancelled", new Object[0]);
            this.f9806a.finish();
        }
    }

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.9 */
    class C08619 implements OnClickListener {
        final /* synthetic */ AlertDialog f9807a;
        final /* synthetic */ OrderDetailsActivity f9808b;

        C08619(OrderDetailsActivity orderDetailsActivity, AlertDialog alertDialog) {
            this.f9808b = orderDetailsActivity;
            this.f9807a = alertDialog;
        }

        public void onClick(View view) {
            this.f9807a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.a */
    private class C0863a extends BaseAdapter {
        final /* synthetic */ OrderDetailsActivity f9812a;
        private Context f9813b;
        private List<String> f9814c;

        /* renamed from: com.olacabs.customer.ui.OrderDetailsActivity.a.a */
        public class C0862a {
            public TextView f9809a;
            public ImageView f9810b;
            final /* synthetic */ C0863a f9811c;

            public C0862a(C0863a c0863a) {
                this.f9811c = c0863a;
            }
        }

        public C0863a(OrderDetailsActivity orderDetailsActivity, Context context, List<String> list) {
            this.f9812a = orderDetailsActivity;
            this.f9813b = context;
            this.f9814c = list;
        }

        public int getCount() {
            return this.f9814c.size();
        }

        public Object getItem(int i) {
            return this.f9814c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEmpty() {
            return this.f9814c.isEmpty();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            boolean z;
            if (view == null) {
                C0862a c0862a = new C0862a(this);
                view = ((Activity) this.f9813b).getLayoutInflater().inflate(R.layout.cancel_list_item, viewGroup, false);
                c0862a.f9809a = (TextView) view.findViewById(R.id.item_text);
                c0862a.f9810b = (ImageView) view.findViewById(R.id.chk);
                view.setTag(c0862a);
            }
            C0862a c0862a2 = (C0862a) view.getTag();
            if (!isEmpty()) {
                c0862a2.f9809a.setText((CharSequence) this.f9814c.get(i));
            }
            ImageView imageView = c0862a2.f9810b;
            if (this.f9812a.f9823C == i) {
                z = true;
            } else {
                z = false;
            }
            imageView.setSelected(z);
            return view;
        }
    }

    private static void m13700m() {
        Factory factory = new Factory("OrderDetailsActivity.java", OrderDetailsActivity.class);
        f9815L = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.OrderDetailsActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 296);
        f9816M = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.ui.OrderDetailsActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 375);
        f9817N = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.OrderDetailsActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 381);
        f9818O = factory.m15070a("method-execution", factory.m15071a("1", "onPause", "com.olacabs.customer.ui.OrderDetailsActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 394);
    }

    public OrderDetailsActivity() {
        this.f9834d = -1;
        this.f9838h = new HashMap();
        this.f9839i = new HashMap();
        this.f9855z = Trace.NULL;
        this.f9823C = -1;
        this.f9827G = new C08531(this);
        this.f9828H = new C08608(this);
        this.f9829I = new Runnable() {
            final /* synthetic */ OrderDetailsActivity f9788a;

            {
                this.f9788a = r1;
            }

            public void run() {
                this.f9788a.f9833c.m13199b(new WeakReference(this.f9788a.f9827G), this.f9788a.f9834d, OrderDetailsActivity.f9819a);
            }
        };
        this.f9830J = new Runnable() {
            final /* synthetic */ OrderDetailsActivity f9789a;

            {
                this.f9789a = r1;
            }

            public void run() {
                this.f9789a.f9833c.m13211c(new WeakReference(this.f9789a.f9831K), this.f9789a.f9834d, OrderDetailsActivity.f9819a);
            }
        };
        this.f9831K = new aj() {
            final /* synthetic */ OrderDetailsActivity f9790a;

            {
                this.f9790a = r1;
            }

            public void onFailure(Throwable th) {
                this.f9790a.f9849t.postDelayed(this.f9790a.f9830J, (long) OrderDetailsActivity.f9820s);
            }

            public void onSuccess(Object obj) {
                bu buVar = (bu) obj;
                if (0.0d != buVar.getEta()) {
                    this.f9790a.f9847q = Double.valueOf(buVar.getEta());
                }
                this.f9790a.m13672a(Double.valueOf(buVar.getLat()), Double.valueOf(buVar.getLng()), this.f9790a.f9847q);
                if (buVar.getStatus() != null) {
                    this.f9790a.f9849t.postDelayed(this.f9790a.f9830J, (long) OrderDetailsActivity.f9820s);
                    if (buVar.getStatus().equalsIgnoreCase("idle")) {
                        this.f9790a.f9833c.m13199b(new WeakReference(this.f9790a.f9827G), this.f9790a.f9834d, OrderDetailsActivity.f9819a);
                    }
                }
            }
        };
    }

    static {
        m13700m();
        f9819a = OrderDetailsActivity.class.getSimpleName();
        f9820s = 30000;
    }

    private void m13668a(aq aqVar) {
        if (aqVar != null) {
            this.f9841k.setText(aqVar.getDeliveryAgentName());
            this.f9837g = aqVar.getDeliveryAgentPhone();
        }
    }

    private Bitmap m13662a(int i, String str) {
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

    private void m13679b(String str, String str2) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08597(this, create));
        create.setCancelable(false);
        create.show();
    }

    private void m13688g() {
        Intent intent = new Intent(this, OrderCancelledActivity.class);
        intent.putExtra(Constants.ORDER_ID, this.f9835e.getCustomerOrderId());
        startActivity(intent);
        finish();
    }

    public void m13707a(String str, String str2) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08619(this, create));
        create.setCancelable(false);
        create.show();
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("OrderDetailsActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "OrderDetailsActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "OrderDetailsActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9815L, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_food_track);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(Constants.ORDER_ID)) {
            this.f9834d = extras.getLong(Constants.ORDER_ID);
        }
        if (this.f9834d == -1) {
            finish();
        }
        this.f9832b = (OlaApp) getApplication();
        this.f9833c = this.f9832b.m12878a();
        this.f9849t = new Handler();
        m13692i();
        m13690h();
        m13694j();
        TraceMachine.exitMethod();
    }

    private void m13690h() {
        float f;
        LatLng latLng;
        Location userLocation = this.f9833c.m13209c().getUserLocation();
        if (userLocation != null) {
            f = 16.0f;
            latLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
        } else {
            f = 6.0f;
            latLng = new LatLng(Constants.DEFAULT_MAP_LAT, Constants.DEFAULT_MAP_LONG);
        }
        this.f9824D = new OlaMapFragment.OlaMapFragment().m13360a(latLng).m13355a(f).m13359a((OnMapReadyCallback) this).m13366b(true).m13364a(false).m13365a();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_map, this.f9824D).commit();
    }

    private void m13692i() {
        this.f9840j = (TextView) findViewById(R.id.cancelRideText);
        this.f9842l = (TextView) findViewById(R.id.food_order_id);
        this.f9843m = (TextView) findViewById(R.id.order_total_amount);
        this.f9826F = (TextView) findViewById(R.id.no_internet_errorText);
        this.f9844n = findViewById(R.id.order_confirmed_call_support);
        this.f9845o = findViewById(R.id.order_confirmed_call_driver);
        this.f9846p = (LinearLayout) findViewById(R.id.ordered_items_lay);
        this.f9848r = findViewById(R.id.button_seperator);
        this.f9841k = (TextView) findViewById(R.id.delivery_man_name);
        this.f9845o.setVisibility(8);
        this.f9848r.setVisibility(8);
        this.f9844n.setOnClickListener(this);
        this.f9845o.setOnClickListener(this);
        findViewById(R.id.food_track_back).setOnClickListener(this);
        this.f9840j.setOnClickListener(this);
    }

    private void m13694j() {
        if (this.f9833c.m13218d() != null) {
            this.f9850u = this.f9833c.m13218d().getFoodDeliveryConfigurations();
            if (this.f9850u != null) {
                f9820s = this.f9850u.getOrderPollingTime() * Constants.MILLIS_IN_A_SECOND;
                this.f9853x = this.f9850u.getCancelReasons();
                this.f9854y = this.f9850u.getContactNumbers();
            }
            if (this.f9851v != null) {
                this.f9851v = this.f9833c.m13218d().getConfigurationResponse();
                this.f9852w = this.f9851v.getCityTag();
            }
        }
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9816M, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f9817N, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            if (Utils.m14909a(getApplicationContext())) {
                Localytics.tagEvent("Viewed Track order screen");
                this.f9849t.post(this.f9830J);
                this.f9849t.post(this.f9829I);
            } else {
                m13705a();
            }
            ActivityTraceAspect.m12823a().m12828d(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    public void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9818O, (Object) this, (Object) this));
        super.onPause();
        m13709c();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
    }

    public void onEvent(ah ahVar) {
        if (ahVar.isConnected()) {
            m13708b();
        } else {
            m13705a();
        }
    }

    public void m13705a() {
        this.f9840j.setEnabled(false);
        this.f9826F.setVisibility(0);
        this.f9826F.setText(getString(R.string.no_internet));
    }

    public void m13708b() {
        this.f9840j.setEnabled(true);
        this.f9826F.setVisibility(8);
    }

    public void m13709c() {
        this.f9849t.removeCallbacks(this.f9830J);
        this.f9849t.removeCallbacks(this.f9829I);
        this.f9833c.m13169a(f9819a);
    }

    private void m13672a(Double d, Double d2, Double d3) {
        if (this.f9824D.m13399e()) {
            LatLng latLng;
            this.f9825E.m10076c();
            Location userLocation = this.f9833c.m13209c().getUserLocation();
            if (userLocation != null) {
                latLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
            } else {
                latLng = new LatLng(Constants.DEFAULT_MAP_LAT, Constants.DEFAULT_MAP_LONG);
            }
            if (!(d3 == null || this.f9825E == null)) {
                this.f9825E.m10068a(new MarkerOptions().m10759a(latLng).m10760a(BitmapDescriptorFactory.m10824a(m13662a((int) R.drawable.track_ride_eta, Trace.NULL + Integer.toString(d3.intValue())))));
            }
            MarkerOptions a = new MarkerOptions().m10759a(new LatLng(d.doubleValue(), d2.doubleValue()));
            a.m10760a(BitmapDescriptorFactory.m10824a(m13710d()));
            this.f9825E.m10068a(a);
        }
    }

    protected Bitmap m13710d() {
        return BitmapFactoryInstrumentation.decodeResource(getResources(), R.drawable.ic_cab_top_view);
    }

    private void m13675a(List<String> list) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_cancel_list_dialog, null, false);
        this.f9821A = (ListView) inflate.findViewById(R.id.item_list);
        this.f9822B = inflate.findViewById(R.id.button_yes);
        this.f9822B.setEnabled(false);
        C0863a c0863a = new C0863a(this, this, list);
        this.f9821A.setAdapter(c0863a);
        c0863a.notifyDataSetChanged();
        AlertDialog create = new Builder(this).setView(inflate).create();
        this.f9821A.setFocusable(false);
        this.f9821A.setOnItemClickListener(new AnonymousClass13(this, c0863a));
        this.f9822B.setOnClickListener(new AnonymousClass14(this, create));
        inflate.findViewById(R.id.button_no).setOnClickListener(new C08542(this, create));
        create.setCancelable(false);
        create.setOnDismissListener(new C08553(this));
        create.show();
    }

    private void m13673a(String str) {
        if (this.f9833c.m13209c() != null && this.f9833c.m13209c().getCity() != null) {
            Map hashMap = new HashMap();
            hashMap.put("City name", this.f9833c.m13209c().getCity());
            hashMap.put("Cancel Reason", str);
            Localytics.tagEvent("Ola Food cancel order", hashMap);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.food_track_back:
                supportFinishAfterTransition();
            case R.id.cancelRideText:
                this.f9840j.setEnabled(false);
                if (this.f9853x != null) {
                    m13675a(this.f9853x);
                } else {
                    m13681c("Cancel Order", "Are you sure you want to cancel the order ?");
                }
            case R.id.order_confirmed_call_driver:
                m13696k();
                if (this.f9837g != null) {
                    new CallSupportCommand(this.f9837g).m12889a(this);
                }
            case R.id.order_confirmed_call_support:
                new CallSupportCommand(this.f9833c.m13218d() != null ? this.f9833c.m13218d().getFoodCallCenterNumber() : Ola.f11486c).m12889a(this);
                m13698l();
            default:
        }
    }

    private void m13696k() {
        if (this.f9833c.m13209c() != null && this.f9833c.m13209c().getCity() != null) {
            Map hashMap = new HashMap();
            hashMap.put("City name", this.f9833c.m13209c().getCity());
            Localytics.tagEvent("Ola Food Call Delivery Boy", hashMap);
        }
    }

    private void m13698l() {
        if (this.f9833c.m13209c() != null && this.f9833c.m13209c().getCity() != null) {
            Map hashMap = new HashMap();
            hashMap.put("City name", this.f9833c.m13209c().getCity());
            Localytics.tagEvent("Ola Food Call Customer Care", hashMap);
        }
    }

    private void m13681c(String str, String str2) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_yes).setOnClickListener(new C08564(this, create));
        inflate.findViewById(R.id.button_no).setOnClickListener(new C08575(this, create));
        create.setCancelable(false);
        create.setOnDismissListener(new C08586(this));
        create.show();
    }

    private void m13674a(ArrayList<bc> arrayList) {
        if (this.f9846p != null) {
            this.f9846p.removeAllViews();
        }
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService("layout_inflater");
        Double valueOf = Double.valueOf(0.0d);
        Iterator it = arrayList.iterator();
        Double d = valueOf;
        while (it.hasNext()) {
            bc bcVar = (bc) it.next();
            View inflate = layoutInflater.inflate(R.layout.food_order_confirmed_item, null, false);
            TextView textView = (TextView) inflate.findViewById(R.id.restaurant_name);
            TextView textView2 = (TextView) inflate.findViewById(R.id.price_amount);
            ((TextView) inflate.findViewById(R.id.item_name)).setText(bcVar.getQuantity() + " x " + bcVar.getTitle());
            textView.setText(bcVar.getMerchant_name());
            double price = (double) (bcVar.getPrice() * bcVar.getQuantity());
            textView2.setText(getResources().getString(R.string.rs_symbol) + String.valueOf(Math.round(price)));
            valueOf = Double.valueOf(price + d.doubleValue());
            this.f9846p.addView(inflate);
            d = valueOf;
        }
        ((TextView) findViewById(R.id.order_total_amount)).setText(getResources().getString(R.string.rs_symbol) + String.valueOf(Math.round(d.doubleValue())));
        ((TextView) findViewById(R.id.food_order_id)).setText(String.valueOf(this.f9835e.getCustomerOrderId()));
    }

    public void m13706a(GoogleMap googleMap) {
        this.f9825E = googleMap;
    }
}
