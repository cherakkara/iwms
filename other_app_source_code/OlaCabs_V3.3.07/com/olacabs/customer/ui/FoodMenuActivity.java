package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.CheckoutResponse;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.ba;
import com.olacabs.customer.p076d.bd;
import com.olacabs.customer.p076d.be;
import com.olacabs.customer.p076d.bt;
import com.olacabs.customer.p076d.bz;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.ui.widgets.FontTextView;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
public class FoodMenuActivity extends FragmentActivity implements OnClickListener, TraceFieldInterface {
    private static final JoinPoint f9611C = null;
    private static final JoinPoint f9612D = null;
    private static final JoinPoint f9613E = null;
    private static final JoinPoint f9614F = null;
    private static final String f9615b;
    private ViewStub f9616A;
    private aj f9617B;
    public double f9618a;
    private aj f9619c;
    private View f9620d;
    private TextView f9621e;
    private RelativeLayout f9622f;
    private OlaApp f9623g;
    private DataManager f9624h;
    private C0826a f9625i;
    private ViewPager f9626j;
    private ArrayList<bd> f9627k;
    private HashMap<Long, Integer> f9628l;
    private HashMap<Long, Integer> f9629m;
    private HashMap<Long, Integer> f9630n;
    private HashMap<Long, Integer> f9631o;
    private be f9632p;
    private TextView f9633q;
    private ImageView f9634r;
    private ProgressBar f9635s;
    private ProgressDialog f9636t;
    private TextView f9637u;
    private TextView f9638v;
    private RelativeLayout f9639w;
    private RelativeLayout f9640x;
    private ListView f9641y;
    private AlertDialog f9642z;

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.1 */
    class C08171 implements aj {
        final /* synthetic */ FoodMenuActivity f9590a;

        C08171(FoodMenuActivity foodMenuActivity) {
            this.f9590a = foodMenuActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to place food order", th);
            VolleyError volleyError = (VolleyError) th;
            this.f9590a.m13555f();
            Object obj = "Network Error";
            if (!(volleyError == null || volleyError.f464a == null)) {
                byte[] bArr = volleyError.f464a.f498b;
                if (bArr != null) {
                    String str = "Other";
                    String str2 = new String(bArr);
                    try {
                        bt btVar = (bt) new Gson().m12343a(str2, bt.class);
                        bz bzVar = (bz) new Gson().m12343a(str2, bz.class);
                        if (volleyError.f464a.f497a != HttpStatus.SC_UNPROCESSABLE_ENTITY || bzVar.getPartialItems() == null || bzVar.getPartialItems().isEmpty()) {
                            HashMap hashMap = new HashMap();
                            if (Utils.m14924g(this.f9590a.f9624h.m13209c().getCity())) {
                                hashMap.put("Booking city", this.f9590a.f9624h.m13209c().getCity());
                            } else {
                                hashMap.put("Booking city", "NA");
                            }
                            Sherlock.m13338a("Ins OFD order placed", "Failure", volleyError, btVar.getText(), true, hashMap);
                            this.f9590a.m13518a(btVar.getHeader(), btVar.getText());
                        } else {
                            this.f9590a.m13521b(bzVar);
                        }
                        obj = str;
                    } catch (Exception e) {
                        String str3 = str;
                    }
                }
            }
            if (this.f9590a.f9624h.m13209c() != null && this.f9590a.f9624h.m13209c().getCity() != null) {
                Map hashMap2 = new HashMap();
                hashMap2.put("City name", this.f9590a.f9624h.m13209c().getCity());
                hashMap2.put(Constants.BUNDLE_TYPE, obj);
                Localytics.tagEvent("OlaFood error message", hashMap2);
            }
        }

        public void onSuccess(Object obj) {
            HashMap hashMap = new HashMap();
            if (Utils.m14924g(this.f9590a.f9624h.m13209c().getCity())) {
                hashMap.put("Booking city", this.f9590a.f9624h.m13209c().getCity());
            } else {
                hashMap.put("Booking city", "NA");
            }
            Sherlock.m13340a("Ins OFD order placed", hashMap);
            OLog.m13313b("Order successful", new Object[0]);
            this.f9590a.m13555f();
            this.f9590a.m13525d(((CheckoutResponse) obj).getId());
        }
    }

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.2 */
    class C08182 implements AnimationListener {
        final /* synthetic */ FoodMenuActivity f9591a;

        C08182(FoodMenuActivity foodMenuActivity) {
            this.f9591a = foodMenuActivity;
        }

        public void onAnimationStart(Animation animation) {
            this.f9591a.m13547b(0);
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.3 */
    class C08193 implements AnimationListener {
        final /* synthetic */ FoodMenuActivity f9592a;

        C08193(FoodMenuActivity foodMenuActivity) {
            this.f9592a = foodMenuActivity;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f9592a.m13547b(4);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.4 */
    class C08204 implements aj {
        final /* synthetic */ FoodMenuActivity f9593a;

        C08204(FoodMenuActivity foodMenuActivity) {
            this.f9593a = foodMenuActivity;
        }

        public void onFailure(Throwable th) {
            this.f9593a.m13555f();
            OLog.m13310a("Failed fetch to food items", th);
            this.f9593a.m13543a((int) R.string.failed_to_fetch_menu);
        }

        public void onSuccess(Object obj) {
            OLog.m13313b("Got food items", new Object[0]);
            this.f9593a.m13555f();
            this.f9593a.f9639w.setVisibility(8);
            this.f9593a.f9621e.setVisibility(8);
            this.f9593a.f9635s.setVisibility(8);
            this.f9593a.f9620d.setVisibility(0);
            this.f9593a.f9632p = (be) obj;
            if (this.f9593a.f9632p.getFoodListItems() == null || this.f9593a.f9632p.getFoodListItems().size() <= 0) {
                OLog.m13313b("Did not find any food items in response", new Object[0]);
                this.f9593a.m13543a((int) R.string.failed_to_fetch_menu);
            } else {
                this.f9593a.f9627k = this.f9593a.f9632p.getFoodListItems();
                for (int i = 0; i < this.f9593a.f9627k.size(); i++) {
                    this.f9593a.f9629m.put(Long.valueOf(((bd) this.f9593a.f9627k.get(i)).getId()), Integer.valueOf(i));
                }
                this.f9593a.f9625i.notifyDataSetChanged();
            }
            this.f9593a.m13531j();
        }
    }

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.5 */
    class C08215 implements OnClickListener {
        final /* synthetic */ AlertDialog f9594a;
        final /* synthetic */ FoodMenuActivity f9595b;

        C08215(FoodMenuActivity foodMenuActivity, AlertDialog alertDialog) {
            this.f9595b = foodMenuActivity;
            this.f9594a = alertDialog;
        }

        public void onClick(View view) {
            try {
                this.f9594a.dismiss();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.6 */
    class C08226 implements OnClickListener {
        final /* synthetic */ AlertDialog f9596a;
        final /* synthetic */ long f9597b;
        final /* synthetic */ FoodMenuActivity f9598c;

        C08226(FoodMenuActivity foodMenuActivity, AlertDialog alertDialog, long j) {
            this.f9598c = foodMenuActivity;
            this.f9596a = alertDialog;
            this.f9597b = j;
        }

        public void onClick(View view) {
            this.f9596a.dismiss();
            Intent intent = new Intent(this.f9598c, OrderDetailsActivity.class);
            intent.putExtra(Constants.ORDER_ID, this.f9597b);
            this.f9598c.startActivity(intent);
            this.f9598c.finish();
        }
    }

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.7 */
    class C08237 implements OnClickListener {
        final /* synthetic */ bz f9599a;
        final /* synthetic */ FoodMenuActivity f9600b;

        C08237(FoodMenuActivity foodMenuActivity, bz bzVar) {
            this.f9600b = foodMenuActivity;
            this.f9599a = bzVar;
        }

        public void onClick(View view) {
            Sherlock.m13347c("Ins OFD order placed");
            Sherlock.m13334a("Ins OFD order placed");
            this.f9600b.m13545a(this.f9599a);
            this.f9600b.m13522b("Continue");
            this.f9600b.f9642z.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.8 */
    class C08248 implements OnClickListener {
        final /* synthetic */ FoodMenuActivity f9601a;

        C08248(FoodMenuActivity foodMenuActivity) {
            this.f9601a = foodMenuActivity;
        }

        public void onClick(View view) {
            this.f9601a.f9642z.dismiss();
            Sherlock.m13347c("Ins OFD order placed");
            this.f9601a.m13522b("Cancel");
            this.f9601a.m13550c();
        }
    }

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.9 */
    class C08259 implements AnimationListener {
        final /* synthetic */ View f9602a;
        final /* synthetic */ FoodMenuActivity f9603b;

        C08259(FoodMenuActivity foodMenuActivity, View view) {
            this.f9603b = foodMenuActivity;
            this.f9602a = view;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f9602a.setVisibility(8);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.a */
    private class C0826a extends FragmentPagerAdapter {
        final /* synthetic */ FoodMenuActivity f9604a;
        private ArrayList<bd> f9605b;

        private C0826a(FoodMenuActivity foodMenuActivity, FragmentManager fragmentManager, ArrayList<bd> arrayList) {
            this.f9604a = foodMenuActivity;
            super(fragmentManager);
            this.f9605b = arrayList;
        }

        public Fragment getItem(int i) {
            return FoodMenuItemFragment.m14445a(i);
        }

        public int getCount() {
            if (this.f9604a.f9627k != null) {
                return this.f9604a.f9627k.size();
            }
            return 0;
        }

        public int getItemPosition(Object obj) {
            return -2;
        }
    }

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.b */
    static class C0827b {
        public TextView f9606a;
        public TextView f9607b;

        C0827b() {
        }
    }

    /* renamed from: com.olacabs.customer.ui.FoodMenuActivity.c */
    private class C0828c extends BaseAdapter {
        final /* synthetic */ FoodMenuActivity f9608a;
        private Context f9609b;
        private ArrayList<ba> f9610c;

        public C0828c(FoodMenuActivity foodMenuActivity, Context context, ArrayList<ba> arrayList) {
            this.f9608a = foodMenuActivity;
            this.f9609b = context;
            this.f9610c = arrayList;
        }

        public int getCount() {
            return this.f9610c.size();
        }

        public Object getItem(int i) {
            return this.f9610c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEmpty() {
            return this.f9610c.isEmpty();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                C0827b c0827b = new C0827b();
                view = ((Activity) this.f9609b).getLayoutInflater().inflate(R.layout.partialstock_list_item, viewGroup, false);
                c0827b.f9606a = (TextView) view.findViewById(R.id.food_item_name_text);
                c0827b.f9607b = (TextView) view.findViewById(R.id.food_item_quantity_text);
                view.setTag(c0827b);
            }
            C0827b c0827b2 = (C0827b) view.getTag();
            c0827b2.f9606a.setText(((ba) this.f9610c.get(i)).getTitle());
            c0827b2.f9607b.setText(String.valueOf(((ba) this.f9610c.get(i)).getQuantity()));
            return view;
        }
    }

    private static void m13540p() {
        Factory factory = new Factory("FoodMenuActivity.java", FoodMenuActivity.class);
        f9611C = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.FoodMenuActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 263);
        f9612D = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.ui.FoodMenuActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 335);
        f9613E = factory.m15070a("method-execution", factory.m15071a("4", "onPause", "com.olacabs.customer.ui.FoodMenuActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 341);
        f9614F = factory.m15070a("method-execution", factory.m15071a("4", "onResume", "com.olacabs.customer.ui.FoodMenuActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 397);
    }

    public FoodMenuActivity() {
        this.f9619c = new C08171(this);
        this.f9628l = new HashMap();
        this.f9629m = new HashMap();
        this.f9630n = new HashMap();
        this.f9631o = new HashMap();
        this.f9618a = 0.0d;
        this.f9617B = new C08204(this);
    }

    static {
        m13540p();
        f9615b = FoodMenuActivity.class.getSimpleName();
    }

    private void m13531j() {
        this.f9638v.setText(String.format(getResources().getString(R.string.title_food_category), new Object[]{this.f9632p.getFoodCategory().getName()}));
        if (!(this.f9624h.m13209c() == null || this.f9624h.m13209c().getCity() == null)) {
            Object obj;
            Map hashMap = new HashMap();
            hashMap.put("City name", this.f9624h.m13209c().getCity());
            hashMap.put("Category", this.f9632p.getFoodCategory().getName());
            hashMap.put("Is Service Available", String.valueOf(m13554e()));
            String str = "Partial Stock Out";
            Iterator it = this.f9627k.iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2;
                if (((bd) it.next()).getQuantity() > 0) {
                    i2 = 1;
                } else {
                    i2 = i;
                }
                i = i2;
            }
            if (i == 0) {
                obj = "Full Stock";
            } else if (i == this.f9627k.size()) {
                obj = "No Stock Out";
            } else {
                String str2 = str;
            }
            hashMap.put("Stockout", obj);
            Localytics.tagEvent("User Viewed Menu Screen", hashMap);
        }
        if (m13554e()) {
            m13551c(4);
            return;
        }
        try {
            Date parse = new SimpleDateFormat("HH:mm:ss").parse(this.f9632p.getStart_time());
            Date parse2 = new SimpleDateFormat("HH:mm:ss").parse(this.f9632p.getEnd_time());
            DateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
            this.f9637u.setText(String.format(getResources().getString(R.string.no_service_text), new Object[]{this.f9632p.getFoodCategory().getName(), simpleDateFormat.format(parse), simpleDateFormat.format(parse2)}));
            m13551c(0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("FoodMenuActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "FoodMenuActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "FoodMenuActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9611C, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.food_menu_activity);
        this.f9620d = findViewById(R.id.food_menu);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (defaultSharedPreferences.getBoolean(Constants.PREF_FOOD_INTRO, true)) {
            View inflate = ((ViewStub) findViewById(R.id.stub_food_intro)).inflate();
            if (Utils.m14924g(getIntent().getStringExtra("CURRENT_SESSION_CATEGORY"))) {
                ((FontTextView) inflate.findViewById(R.id.food_intro_button)).setText(String.format(getString(R.string.food_intro_button_text_dynamic), new Object[]{r0.toUpperCase()}));
            }
            inflate.setVisibility(0);
            inflate.findViewById(R.id.food_intro_button_layout).setOnClickListener(this);
            inflate.findViewById(R.id.food_intro_back).setOnClickListener(this);
            defaultSharedPreferences.edit().putBoolean(Constants.PREF_FOOD_INTRO, false).apply();
        }
        this.f9616A = (ViewStub) findViewById(R.id.stub_sad_error);
        this.f9623g = (OlaApp) getApplication();
        this.f9624h = this.f9623g.m12878a();
        this.f9621e = (TextView) findViewById(R.id.error_text);
        this.f9626j = (ViewPager) findViewById(R.id.food_menu_pager);
        this.f9626j.setOffscreenPageLimit(3);
        this.f9640x = (RelativeLayout) findViewById(R.id.food_menu_order_review);
        int applyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        this.f9626j.setClipToPadding(false);
        this.f9626j.setPadding(applyDimension, 0, applyDimension, 0);
        this.f9626j.setPageMargin(-applyDimension);
        this.f9625i = new C0826a(getSupportFragmentManager(), this.f9627k, null);
        this.f9626j.setAdapter(this.f9625i);
        this.f9622f = (RelativeLayout) findViewById(R.id.place_order_button);
        this.f9622f.setOnClickListener(this);
        this.f9633q = (TextView) findViewById(R.id.amount_text_view);
        this.f9638v = (TextView) findViewById(R.id.title_food_category);
        this.f9637u = (TextView) findViewById(R.id.no_service_text);
        this.f9635s = (ProgressBar) findViewById(R.id.progressBar_loading);
        this.f9634r = (ImageView) findViewById(R.id.failed_sad_face);
        this.f9639w = (RelativeLayout) findViewById(R.id.progress_bar_lay);
        findViewById(R.id.food_menu_back).setOnClickListener(this);
        findViewById(R.id.food_order_faq).setOnClickListener(this);
        findViewById(R.id.food_activity_review_confirm).setOnClickListener(this);
        findViewById(R.id.food_activity_review_cancel).setOnClickListener(this);
        findViewById(R.id.food_order_faq).setOnClickListener(this);
        this.f9640x.setOnClickListener(this);
        this.f9639w.setVisibility(0);
        this.f9621e.setText(getResources().getString(R.string.trying_to_fetch_menu));
        this.f9621e.setVisibility(0);
        this.f9635s.setVisibility(0);
        this.f9620d.setVisibility(8);
        this.f9636t = new ProgressDialog(this, R.style.TransparentProgressDialog);
        this.f9636t.setIndeterminateDrawable(getResources().getDrawable(R.drawable.custom_progress_background));
        this.f9636t.setCancelable(false);
        m13550c();
        TraceMachine.exitMethod();
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9612D, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    protected void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9613E, (Object) this, (Object) this));
        super.onPause();
        m13555f();
    }

    public void onEvent(ah ahVar) {
        if (ahVar.isConnected()) {
            m13550c();
            m13541a();
            return;
        }
        m13543a((int) R.string.no_internet_sadface);
    }

    public void m13543a(int i) {
        m13558i();
        if (i == R.string.no_internet_sadface) {
            m13546b();
        }
        if (this.f9640x.getVisibility() == 0) {
            m13538n();
        }
        if (this.f9642z != null && this.f9642z.isShowing()) {
            this.f9642z.dismiss();
            this.f9642z = null;
        }
        this.f9639w.setVisibility(0);
        this.f9634r.setVisibility(0);
        this.f9621e.setVisibility(0);
        this.f9621e.setText(i);
        this.f9635s.setVisibility(8);
        this.f9620d.setVisibility(8);
    }

    public void m13541a() {
        if (this.f9618a > 0.0d) {
            m13557h();
        } else {
            m13558i();
        }
        findViewById(R.id.dim_overlay).setVisibility(8);
        this.f9640x.setVisibility(8);
        this.f9639w.setVisibility(8);
        this.f9621e.setVisibility(8);
        this.f9635s.setVisibility(8);
        this.f9620d.setVisibility(0);
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
        this.f9624h.m13169a(f9615b);
    }

    protected void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f9614F, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            if (Utils.m14909a(getApplicationContext())) {
                m13541a();
            } else {
                m13543a((int) R.string.no_internet_sadface);
            }
            ActivityTraceAspect.m12823a().m12828d(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    public void m13546b() {
        this.f9628l.clear();
        this.f9629m.clear();
        this.f9618a = 0.0d;
    }

    public void m13550c() {
        this.f9640x.setVisibility(8);
        this.f9624h.m13253l(new WeakReference(this.f9617B), f9615b);
        this.f9626j.setCurrentItem(0);
    }

    private void m13518a(String str, String str2) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08215(this, create));
        create.show();
    }

    private void m13525d(long j) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        create.setCancelable(false);
        ((TextView) inflate.findViewById(R.id.item_header)).setText(getString(R.string.confirm_order_header));
        ((TextView) inflate.findViewById(R.id.item_message)).setText(getString(R.string.confirm_order_text));
        ((TextView) inflate.findViewById(R.id.button_ok)).setText(getString(R.string.confirm_order_button_text));
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08226(this, create, j));
        create.show();
    }

    private void m13521b(bz bzVar) {
        m13546b();
        m13538n();
        m13558i();
        for (int i = 0; i < bzVar.getPartialItems().size(); i++) {
            this.f9630n.put(Long.valueOf(((ba) bzVar.getPartialItems().get(i)).getListing_id()), Integer.valueOf(((ba) bzVar.getPartialItems().get(i)).getQuantity()));
            this.f9631o.put(Long.valueOf(((ba) bzVar.getPartialItems().get(i)).getListing_id()), Integer.valueOf(i));
        }
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_partial_stock_dialog, null, false);
        ((TextView) inflate.findViewById(R.id.partial_stock_header)).setText(bzVar.getHeader());
        ((TextView) inflate.findViewById(R.id.partial_stock_message)).setText(bzVar.getMessage());
        this.f9641y = (ListView) inflate.findViewById(R.id.partial_stock_item_list);
        this.f9641y.setAdapter(new C0828c(this, this, bzVar.getPartialItems()));
        Builder builder = new Builder(this);
        builder.setView(inflate);
        this.f9642z = builder.create();
        inflate.findViewById(R.id.button_partialstock_confirm).setOnClickListener(new C08237(this, bzVar));
        inflate.findViewById(R.id.button_partialstock_cancel).setOnClickListener(new C08248(this));
        this.f9642z.setCancelable(false);
        this.f9642z.show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.food_intro_button_layout:
                m13536l();
            case R.id.food_intro_back:
            case R.id.food_menu_back:
                supportFinishAfterTransition();
            case R.id.food_order_faq:
                startActivity(FaqActivity.m13493a((Context) this, getString(R.string.url_ofd_faq)));
            case R.id.place_order_button:
                dt c = this.f9624h.m13209c();
                if (Utils.m14924g(c.getPhoneNumber()) && Utils.m14924g(c.getName()) && c.isVerified()) {
                    m13534k();
                    m13537m();
                    return;
                }
                m13518a(getString(R.string.missing_phone_number_header), getString(R.string.missing_phone_number_desc));
            case R.id.food_activity_review_cancel:
                m13517a("Cancelled");
                m13538n();
            case R.id.food_activity_review_confirm:
                Sherlock.m13334a("Ins OFD order placed");
                m13517a("Confirmed");
                m13539o();
            default:
        }
    }

    private void m13517a(String str) {
        if (this.f9624h.m13209c() != null && this.f9624h.m13209c().getCity() != null) {
            Map hashMap = new HashMap();
            hashMap.put("City name", this.f9624h.m13209c().getCity());
            hashMap.put("Action Taken", str);
            Localytics.tagEvent("Order Review Screen Viewed", hashMap);
        }
    }

    private void m13522b(String str) {
        if (this.f9624h.m13209c() != null && this.f9624h.m13209c().getCity() != null) {
            Map hashMap = new HashMap();
            hashMap.put("City name", this.f9624h.m13209c().getCity());
            hashMap.put("Action Taken", str);
            Localytics.tagEvent("Partial stock out popup", hashMap);
        }
    }

    private void m13534k() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ordered_items_lay);
        if (linearLayout.getChildCount() != 0) {
            linearLayout.removeAllViews();
        }
        Set<Long> keySet = this.f9628l.keySet();
        ArrayList arrayList = new ArrayList();
        for (Long l : keySet) {
            bd bdVar = (bd) this.f9627k.get(((Integer) this.f9629m.get(l)).intValue());
            arrayList.add(new ba(bdVar.getId(), bdVar.getMerchant().getId(), bdVar.getMerchant().getName(), bdVar.getName(), bdVar.getDescription(), ((Integer) this.f9628l.get(l)).intValue(), bdVar.getSale_price(), bdVar.getAttributes()));
        }
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService("layout_inflater");
        Double valueOf = Double.valueOf(0.0d);
        Iterator it = arrayList.iterator();
        Double d = valueOf;
        while (it.hasNext()) {
            ba baVar = (ba) it.next();
            View inflate = layoutInflater.inflate(R.layout.food_order_confirmed_item, null, false);
            TextView textView = (TextView) inflate.findViewById(R.id.restaurant_name);
            TextView textView2 = (TextView) inflate.findViewById(R.id.price_amount);
            ((TextView) inflate.findViewById(R.id.item_name)).setText(baVar.getQuantity() + " x " + baVar.getTitle());
            textView.setText(baVar.getMerchant_name());
            double price = baVar.getPrice() * ((double) baVar.getQuantity());
            textView2.setText(getResources().getString(R.string.rs_symbol) + String.valueOf(Math.round(price)));
            valueOf = Double.valueOf(price + d.doubleValue());
            linearLayout.addView(inflate);
            d = valueOf;
        }
        ((TextView) findViewById(R.id.food_activity_review_total)).setText(getResources().getString(R.string.rs_symbol) + String.valueOf(Math.round(d.doubleValue())));
    }

    private void m13536l() {
        View findViewById = findViewById(R.id.food_intro);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_out_right_to_left);
        loadAnimation.setAnimationListener(new C08259(this, findViewById));
        findViewById.startAnimation(loadAnimation);
    }

    private void m13537m() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.slideup);
        loadAnimation.setAnimationListener(new AnimationListener() {
            final /* synthetic */ FoodMenuActivity f9588a;

            {
                this.f9588a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.f9588a.f9640x.setVisibility(0);
                this.f9588a.findViewById(R.id.dim_overlay).setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.f9640x.startAnimation(loadAnimation);
    }

    private void m13538n() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.slidedown);
        loadAnimation.setAnimationListener(new AnimationListener() {
            final /* synthetic */ FoodMenuActivity f9589a;

            {
                this.f9589a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.f9589a.findViewById(R.id.dim_overlay).setVisibility(8);
            }

            public void onAnimationEnd(Animation animation) {
                this.f9589a.f9640x.setVisibility(4);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.f9640x.startAnimation(loadAnimation);
    }

    private void m13539o() {
        m13556g();
        String str = Trace.NULL;
        Set<Long> keySet = this.f9628l.keySet();
        ArrayList arrayList = new ArrayList();
        for (Long l : keySet) {
            bd bdVar = (bd) this.f9627k.get(((Integer) this.f9629m.get(l)).intValue());
            arrayList.add(new ba(bdVar.getId(), bdVar.getMerchant().getId(), bdVar.getMerchant().getName(), bdVar.getName(), bdVar.getDescription(), ((Integer) this.f9628l.get(l)).intValue(), bdVar.getSale_price(), bdVar.getAttributes()));
        }
        int i = 0;
        for (Integer intValue : this.f9628l.values()) {
            i = intValue.intValue() + i;
        }
        m13519a(str, arrayList, i);
    }

    public void m13545a(bz bzVar) {
        m13556g();
        String str = Trace.NULL;
        Set<Long> keySet = this.f9630n.keySet();
        ArrayList arrayList = new ArrayList();
        for (Long l : keySet) {
            ba baVar = (ba) bzVar.getPartialItems().get(((Integer) this.f9631o.get(l)).intValue());
            arrayList.add(new ba(baVar.getListing_id(), baVar.getMerchant_id(), baVar.getMerchant_name(), baVar.getTitle(), baVar.getDescription(), ((Integer) this.f9630n.get(l)).intValue(), baVar.getPrice(), baVar.getAttributes()));
        }
        int i = 0;
        for (Integer intValue : this.f9630n.values()) {
            i = intValue.intValue() + i;
        }
        m13519a(str, arrayList, i);
    }

    private void m13519a(String str, ArrayList<ba> arrayList, int i) {
        m13512a(i, this.f9632p.getFoodCategory().getName());
        if (!(this.f9624h == null || this.f9624h.m13218d() == null || this.f9624h.m13218d().getConfigurationResponse() == null)) {
            str = this.f9624h.m13218d().getConfigurationResponse().getCityTag();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (TextUtils.isEmpty(str)) {
                jSONObject.put("city_name", Ola.f11504u != null ? Ola.f11504u : "Bangalore");
            } else {
                jSONObject.put("city_name", str);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(Constants.USER_ID, this.f9624h.m13209c().getUserId());
            jSONObject2.put("items", JSONArrayInstrumentation.init(new Gson().m12346a((Object) arrayList)));
            jSONObject2.put("order_date", System.currentTimeMillis());
            jSONObject2.put("attributes", jSONObject);
            if (!(this.f9624h == null || this.f9624h.m13209c().getUserLocation() == null)) {
                jSONObject2.put("latitude", this.f9624h.m13209c().getUserLocation().getLatitude());
                jSONObject2.put("longitude", this.f9624h.m13209c().getUserLocation().getLongitude());
            }
        } catch (Throwable e2) {
            OLog.m13310a("Failed to form order JSON", e2);
        }
        this.f9624h.m13191a(new WeakReference(this.f9619c), jSONObject2, f9615b);
    }

    private void m13512a(int i, String str) {
        if (this.f9624h.m13209c() != null && this.f9624h.m13209c().getCity() != null) {
            Map hashMap = new HashMap();
            hashMap.put("City name", this.f9624h.m13209c().getCity());
            hashMap.put("Units of Menu Item", String.valueOf(i));
            hashMap.put("Category", str);
            Localytics.tagEvent("Place Order clicked", hashMap);
        }
    }

    public void m13544a(long j) {
        if (this.f9628l.containsKey(Long.valueOf(j))) {
            this.f9628l.put(Long.valueOf(j), Integer.valueOf(((Integer) this.f9628l.get(Long.valueOf(j))).intValue() + 1));
        } else {
            this.f9628l.put(Long.valueOf(j), Integer.valueOf(1));
        }
    }

    public void m13548b(long j) {
        if (!this.f9628l.containsKey(Long.valueOf(j))) {
            return;
        }
        if (((Integer) this.f9628l.get(Long.valueOf(j))).intValue() > 1) {
            this.f9628l.put(Long.valueOf(j), Integer.valueOf(((Integer) this.f9628l.get(Long.valueOf(j))).intValue() - 1));
        } else {
            this.f9628l.remove(Long.valueOf(j));
        }
    }

    public int m13549c(long j) {
        if (this.f9628l.containsKey(Long.valueOf(j))) {
            return ((Integer) this.f9628l.get(Long.valueOf(j))).intValue();
        }
        return 0;
    }

    public void m13547b(int i) {
        this.f9637u.setVisibility(4);
        this.f9622f.setVisibility(i);
    }

    public void m13551c(int i) {
        this.f9622f.setVisibility(4);
        this.f9637u.setVisibility(i);
    }

    public String m13553d() {
        return this.f9632p.getFoodCategory().getName();
    }

    public boolean m13554e() {
        return this.f9632p.isServiceAvailable();
    }

    public void m13542a(double d) {
        this.f9618a += d;
        this.f9633q.setText(String.valueOf(Math.round(this.f9618a)));
        if (this.f9618a > 0.0d) {
            m13557h();
        } else {
            m13558i();
        }
    }

    public bd m13552d(int i) {
        if (this.f9627k != null) {
            return (bd) this.f9627k.get(i);
        }
        return null;
    }

    public void m13555f() {
        if (this.f9636t != null && this.f9636t.isShowing()) {
            try {
                this.f9636t.dismiss();
            } catch (Exception e) {
            }
        }
    }

    public void m13556g() {
        if (!this.f9636t.isShowing()) {
            this.f9636t.show();
        }
    }

    public void m13557h() {
        if (this.f9622f.getVisibility() == 4) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.slideup);
            loadAnimation.setAnimationListener(new C08182(this));
            this.f9622f.startAnimation(loadAnimation);
            return;
        }
        m13547b(0);
    }

    public void m13558i() {
        if (this.f9622f.getVisibility() == 0) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.slidedown);
            loadAnimation.setAnimationListener(new C08193(this));
            this.f9622f.startAnimation(loadAnimation);
            return;
        }
        m13547b(4);
    }
}
