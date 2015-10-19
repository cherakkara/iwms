package com.olacabs.customer.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.AllCaps;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.VolleyError;
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
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.p076d.ae;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.aw;
import com.olacabs.customer.p076d.ax;
import com.olacabs.customer.p076d.cn;
import com.olacabs.customer.p076d.cp;
import com.olacabs.customer.p076d.cq;
import com.olacabs.customer.p076d.ct;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
public class RideDetailsActivity extends Activity implements OnClickListener, TraceFieldInterface {
    public static final String f9977a;
    private static final JoinPoint av = null;
    private static final JoinPoint aw = null;
    private static final JoinPoint ax = null;
    private static final JoinPoint ay = null;
    public static String f9978b;
    public static String f9979c;
    public static String f9980d;
    public static String f9981e;
    private static String f9982m;
    private TextView f9983A;
    private TextView f9984B;
    private TextView f9985C;
    private TextView f9986D;
    private TextView f9987E;
    private TextView f9988F;
    private LinearLayout f9989G;
    private LinearLayout f9990H;
    private ImageView f9991I;
    private ImageView f9992J;
    private ImageView f9993K;
    private ImageView f9994L;
    private ImageView f9995M;
    private ImageView f9996N;
    private ImageView f9997O;
    private LinearLayout f9998P;
    private ImageView f9999Q;
    private LinearLayout f10000R;
    private LinearLayout f10001S;
    private LinearLayout f10002T;
    private LinearLayout f10003U;
    private LinearLayout f10004V;
    private LinearLayout f10005W;
    private LinearLayout f10006X;
    private TextView f10007Y;
    private TextView f10008Z;
    private TextView aa;
    private TextView ab;
    private TextView ac;
    private TextView ad;
    private TextView ae;
    private TextView af;
    private TextView ag;
    private LinearLayout ah;
    private LinearLayout ai;
    private ImageView aj;
    private FrameLayout ak;
    private String al;
    private View am;
    private int an;
    private DataManager ao;
    private String ap;
    private AlertDialog aq;
    private aj ar;
    private aj as;
    private String at;
    private aj au;
    protected ProgressDialog f10009f;
    ListView f10010g;
    private Handler f10011h;
    private Runnable f10012i;
    private aj f10013j;
    private aj f10014k;
    private aj f10015l;
    private ProgressBar f10016n;
    private ViewStub f10017o;
    private cp f10018p;
    private aj f10019q;
    private TextView f10020r;
    private TextView f10021s;
    private TextView f10022t;
    private TextView f10023u;
    private TextView f10024v;
    private TextView f10025w;
    private TextView f10026x;
    private TextView f10027y;
    private TextView f10028z;

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.11 */
    class AnonymousClass11 implements OnItemClickListener {
        final /* synthetic */ C0883a f9929a;
        final /* synthetic */ RideDetailsActivity f9930b;

        AnonymousClass11(RideDetailsActivity rideDetailsActivity, C0883a c0883a) {
            this.f9930b = rideDetailsActivity;
            this.f9929a = c0883a;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ImageView imageView = (ImageView) view.findViewById(R.id.chk);
            this.f9930b.al = ((TextView) view.findViewById(R.id.item_text)).getText().toString();
            imageView.setSelected(true);
            this.f9930b.an = i;
            this.f9929a.notifyDataSetChanged();
            if (this.f9930b.al == null || this.f9930b.al.length() <= 0) {
                this.f9930b.am.setEnabled(false);
            } else {
                this.f9930b.am.setEnabled(true);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.13 */
    class AnonymousClass13 implements OnClickListener {
        final /* synthetic */ String f9932a;
        final /* synthetic */ RideDetailsActivity f9933b;

        AnonymousClass13(RideDetailsActivity rideDetailsActivity, String str) {
            this.f9933b = rideDetailsActivity;
            this.f9932a = str;
        }

        public void onClick(View view) {
            this.f9933b.aq.dismiss();
            this.f9933b.an = -1;
            if ("local_taxi".equalsIgnoreCase(this.f9932a) || "local_auto".equalsIgnoreCase(this.f9932a) || "delivery".equalsIgnoreCase(this.f9932a)) {
                this.f9933b.m13770g();
                this.f9933b.m13758b(this.f9933b.f10018p.getCategory_booked(), this.f9933b.al);
                this.f9933b.ao.m13184a(new WeakReference(this.f9933b.f10015l), String.valueOf(this.f9933b.getIntent().getStringExtra(Constants.ARG_KRN)), this.f9933b.al, this.f9932a, RideDetailsActivity.f9977a);
                return;
            }
            this.f9933b.m13770g();
            this.f9933b.m13758b(this.f9933b.f10018p.getCategory_booked(), "User cancellation");
            this.f9933b.ao.m13228e(new WeakReference(this.f9933b.f10015l), String.valueOf(this.f9933b.getIntent().getStringExtra(Constants.ARG_BOOKING_ID)), this.f9933b.al, RideDetailsActivity.f9977a);
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.1 */
    class C08701 implements Runnable {
        final /* synthetic */ RideDetailsActivity f9940a;

        C08701(RideDetailsActivity rideDetailsActivity) {
            this.f9940a = rideDetailsActivity;
        }

        public void run() {
            if (this.f9940a.getIntent() != null) {
                RideDetailsActivity.f9982m = this.f9940a.getIntent().getStringExtra(Constants.ARG_CAR_CATEGORY_ID);
                this.f9940a.ao.m13196a(new WeakReference(this.f9940a.f10019q), this.f9940a.m13781l(), String.valueOf(this.f9940a.getIntent().getStringExtra(Constants.ARG_KRN)), String.valueOf(this.f9940a.getIntent().getStringExtra(Constants.ARG_BOOKING_ID)), RideDetailsActivity.f9982m, RideDetailsActivity.f9977a);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.21 */
    class AnonymousClass21 implements OnClickListener {
        final /* synthetic */ EditText f9944a;
        final /* synthetic */ AlertDialog f9945b;
        final /* synthetic */ RideDetailsActivity f9946c;

        /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.21.1 */
        class C08711 implements OnClickListener {
            final /* synthetic */ AlertDialog f9942a;
            final /* synthetic */ AnonymousClass21 f9943b;

            C08711(AnonymousClass21 anonymousClass21, AlertDialog alertDialog) {
                this.f9943b = anonymousClass21;
                this.f9942a = alertDialog;
            }

            public void onClick(View view) {
                this.f9942a.dismiss();
            }
        }

        AnonymousClass21(RideDetailsActivity rideDetailsActivity, EditText editText, AlertDialog alertDialog) {
            this.f9946c = rideDetailsActivity;
            this.f9944a = editText;
            this.f9945b = alertDialog;
        }

        public void onClick(View view) {
            ((InputMethodManager) this.f9946c.getSystemService("input_method")).hideSoftInputFromWindow(this.f9944a.getWindowToken(), 0);
            this.f9945b.dismiss();
            this.f9946c.ap = this.f9944a.getText().toString();
            if (!Utils.m14909a(this.f9946c.getApplicationContext())) {
                this.f9946c.m13754a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            } else if (this.f9946c.ap.trim().equalsIgnoreCase(Trace.NULL)) {
                View inflate = ((LayoutInflater) this.f9946c.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
                AlertDialog create = new Builder(this.f9946c).setView(inflate).create();
                ((TextView) inflate.findViewById(R.id.item_header)).setText("Invalid Name!");
                ((TextView) inflate.findViewById(R.id.item_message)).setText("Please enter a valid name and try again!");
                inflate.findViewById(R.id.button_ok).setOnClickListener(new C08711(this, create));
                create.show();
            } else {
                this.f9946c.m13770g();
                this.f9946c.ao.m13214c(new WeakReference(this.f9946c.ar), this.f9946c.m13762c(this.f9946c.ap), RideDetailsActivity.f9977a);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.2 */
    class C08722 implements OnClickListener {
        final /* synthetic */ AlertDialog f9947a;
        final /* synthetic */ RideDetailsActivity f9948b;

        C08722(RideDetailsActivity rideDetailsActivity, AlertDialog alertDialog) {
            this.f9948b = rideDetailsActivity;
            this.f9947a = alertDialog;
        }

        public void onClick(View view) {
            this.f9947a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.3 */
    class C08743 implements OnClickListener {
        final /* synthetic */ EditText f9951a;
        final /* synthetic */ AlertDialog f9952b;
        final /* synthetic */ RideDetailsActivity f9953c;

        /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.3.1 */
        class C08731 implements OnClickListener {
            final /* synthetic */ AlertDialog f9949a;
            final /* synthetic */ C08743 f9950b;

            C08731(C08743 c08743, AlertDialog alertDialog) {
                this.f9950b = c08743;
                this.f9949a = alertDialog;
            }

            public void onClick(View view) {
                this.f9949a.dismiss();
            }
        }

        C08743(RideDetailsActivity rideDetailsActivity, EditText editText, AlertDialog alertDialog) {
            this.f9953c = rideDetailsActivity;
            this.f9951a = editText;
            this.f9952b = alertDialog;
        }

        public void onClick(View view) {
            ((InputMethodManager) this.f9953c.getSystemService("input_method")).hideSoftInputFromWindow(this.f9951a.getWindowToken(), 0);
            this.f9952b.dismiss();
            if (!Utils.m14909a(this.f9953c.getApplicationContext())) {
                this.f9953c.m13754a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            } else if (this.f9951a.getText().toString().trim().equalsIgnoreCase(Trace.NULL)) {
                View inflate = ((LayoutInflater) this.f9953c.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
                AlertDialog create = new Builder(this.f9953c).setView(inflate).create();
                ((TextView) inflate.findViewById(R.id.item_header)).setText("Invalid Coupon!");
                ((TextView) inflate.findViewById(R.id.item_message)).setText("Please enter a valid coupon and try again!");
                inflate.findViewById(R.id.button_ok).setOnClickListener(new C08731(this, create));
                create.show();
            } else {
                this.f9953c.m13770g();
                this.f9953c.ao.m13215c(new WeakReference(this.f9953c.f10013j), String.valueOf(this.f9953c.getIntent().getStringExtra(Constants.ARG_BOOKING_ID)), this.f9951a.getText().toString(), RideDetailsActivity.f9977a);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.4 */
    class C08754 implements OnClickListener {
        final /* synthetic */ AlertDialog f9954a;
        final /* synthetic */ RideDetailsActivity f9955b;

        C08754(RideDetailsActivity rideDetailsActivity, AlertDialog alertDialog) {
            this.f9955b = rideDetailsActivity;
            this.f9954a = alertDialog;
        }

        public void onClick(View view) {
            this.f9954a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.5 */
    class C08765 implements TextWatcher {
        final /* synthetic */ TextView f9956a;
        final /* synthetic */ EditText f9957b;
        final /* synthetic */ RideDetailsActivity f9958c;

        C08765(RideDetailsActivity rideDetailsActivity, TextView textView, EditText editText) {
            this.f9958c = rideDetailsActivity;
            this.f9956a = textView;
            this.f9957b = editText;
        }

        public void afterTextChanged(Editable editable) {
            if (Utils.m14913b(editable.toString())) {
                this.f9956a.setEnabled(true);
                return;
            }
            this.f9956a.setEnabled(false);
            this.f9957b.setError(this.f9958c.getString(R.string.invalid_email_id_hint));
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.6 */
    class C08786 implements OnClickListener {
        final /* synthetic */ EditText f9961a;
        final /* synthetic */ AlertDialog f9962b;
        final /* synthetic */ RideDetailsActivity f9963c;

        /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.6.1 */
        class C08771 implements OnClickListener {
            final /* synthetic */ AlertDialog f9959a;
            final /* synthetic */ C08786 f9960b;

            C08771(C08786 c08786, AlertDialog alertDialog) {
                this.f9960b = c08786;
                this.f9959a = alertDialog;
            }

            public void onClick(View view) {
                this.f9959a.dismiss();
            }
        }

        C08786(RideDetailsActivity rideDetailsActivity, EditText editText, AlertDialog alertDialog) {
            this.f9963c = rideDetailsActivity;
            this.f9961a = editText;
            this.f9962b = alertDialog;
        }

        public void onClick(View view) {
            ((InputMethodManager) this.f9963c.getSystemService("input_method")).hideSoftInputFromWindow(this.f9961a.getWindowToken(), 0);
            this.f9962b.dismiss();
            if (!Utils.m14909a(this.f9963c.getApplicationContext())) {
                this.f9963c.m13754a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            } else if (this.f9961a.getText().toString().trim().equalsIgnoreCase(Trace.NULL)) {
                View inflate = ((LayoutInflater) this.f9963c.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
                AlertDialog create = new Builder(this.f9963c).setView(inflate).create();
                ((TextView) inflate.findViewById(R.id.item_header)).setText("Invalid Email!");
                ((TextView) inflate.findViewById(R.id.item_message)).setText("Please enter a valid email address and try again!");
                inflate.findViewById(R.id.button_ok).setOnClickListener(new C08771(this, create));
                create.show();
            } else {
                this.f9963c.at = this.f9961a.getText().toString();
                this.f9963c.m13770g();
                this.f9963c.ao.m13222d(new WeakReference(this.f9963c.au), String.valueOf(this.f9963c.getIntent().getStringExtra(Constants.ARG_BOOKING_ID)), this.f9963c.at, RideDetailsActivity.f9977a);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.7 */
    class C08797 implements OnClickListener {
        final /* synthetic */ AlertDialog f9964a;
        final /* synthetic */ RideDetailsActivity f9965b;

        C08797(RideDetailsActivity rideDetailsActivity, AlertDialog alertDialog) {
            this.f9965b = rideDetailsActivity;
            this.f9964a = alertDialog;
        }

        public void onClick(View view) {
            this.f9964a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.8 */
    class C08808 implements OnClickListener {
        final /* synthetic */ String f9966a;
        final /* synthetic */ AlertDialog f9967b;
        final /* synthetic */ RideDetailsActivity f9968c;

        C08808(RideDetailsActivity rideDetailsActivity, String str, AlertDialog alertDialog) {
            this.f9968c = rideDetailsActivity;
            this.f9966a = str;
            this.f9967b = alertDialog;
        }

        public void onClick(View view) {
            if (this.f9966a.equalsIgnoreCase("Apply Coupon")) {
                this.f9968c.f10001S.setEnabled(false);
                this.f9968c.f9994L.setEnabled(false);
                this.f9968c.f10008Z.setEnabled(false);
            } else if (this.f9966a.equalsIgnoreCase("Booking Cancel!")) {
                this.f9968c.f10002T.setVisibility(8);
                this.f9968c.f10005W.setVisibility(0);
                this.f9968c.f10002T.setEnabled(false);
                this.f9968c.aa.setEnabled(false);
                this.f9968c.f9995M.setEnabled(false);
                this.f9968c.f10005W.setEnabled(false);
                this.f9968c.f9997O.setEnabled(false);
                this.f9968c.ad.setEnabled(false);
                this.f9968c.f10001S.setEnabled(false);
                this.f9968c.f10008Z.setEnabled(false);
                this.f9968c.f9994L.setEnabled(false);
                this.f9968c.f10003U.setVisibility(0);
                this.f9968c.f10004V.setVisibility(8);
                this.f9968c.f10003U.setEnabled(false);
                this.f9968c.ab.setEnabled(false);
                this.f9968c.f9993K.setEnabled(false);
                this.f9968c.f10004V.setEnabled(false);
                this.f9968c.f9996N.setEnabled(false);
                this.f9968c.ac.setEnabled(false);
                this.f9968c.f10022t.setText(Constants.RIDE_STATUS_CANCELLED);
                this.f9968c.f10022t.setBackgroundColor(this.f9968c.getResources().getColor(R.color.ola_gray_text));
            }
            this.f9967b.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.9 */
    class C08819 implements OnClickListener {
        final /* synthetic */ String f9969a;
        final /* synthetic */ RideDetailsActivity f9970b;

        C08819(RideDetailsActivity rideDetailsActivity, String str) {
            this.f9970b = rideDetailsActivity;
            this.f9969a = str;
        }

        public void onClick(View view) {
            this.f9970b.aq.dismiss();
            if ("local_taxi".equalsIgnoreCase(this.f9969a) || "local_auto".equalsIgnoreCase(this.f9969a) || "delivery".equalsIgnoreCase(this.f9969a)) {
                this.f9970b.m13770g();
                this.f9970b.m13758b(this.f9970b.f10018p.getCategory_booked(), "User cancellation");
                this.f9970b.ao.m13184a(new WeakReference(this.f9970b.f10015l), String.valueOf(this.f9970b.getIntent().getStringExtra(Constants.ARG_KRN)), "User cancellation", this.f9969a, RideDetailsActivity.f9977a);
                return;
            }
            this.f9970b.m13770g();
            this.f9970b.m13758b(this.f9970b.f10018p.getCategory_booked(), "User cancellation");
            this.f9970b.ao.m13228e(new WeakReference(this.f9970b.f10015l), String.valueOf(this.f9970b.getIntent().getStringExtra(Constants.ARG_BOOKING_ID)), "User cancellation", RideDetailsActivity.f9977a);
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.a */
    private class C0883a extends BaseAdapter {
        final /* synthetic */ RideDetailsActivity f9974a;
        private Context f9975b;
        private List<String> f9976c;

        /* renamed from: com.olacabs.customer.ui.RideDetailsActivity.a.a */
        public class C0882a {
            public TextView f9971a;
            public ImageView f9972b;
            final /* synthetic */ C0883a f9973c;

            public C0882a(C0883a c0883a) {
                this.f9973c = c0883a;
            }
        }

        public C0883a(RideDetailsActivity rideDetailsActivity, Context context, List<String> list) {
            this.f9974a = rideDetailsActivity;
            this.f9975b = context;
            this.f9976c = list;
        }

        public int getCount() {
            return this.f9976c.size();
        }

        public Object getItem(int i) {
            return this.f9976c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEmpty() {
            return this.f9976c.isEmpty();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            boolean z;
            if (view == null) {
                C0882a c0882a = new C0882a(this);
                view = ((Activity) this.f9975b).getLayoutInflater().inflate(R.layout.cancel_list_item, viewGroup, false);
                c0882a.f9971a = (TextView) view.findViewById(R.id.item_text);
                c0882a.f9972b = (ImageView) view.findViewById(R.id.chk);
                view.setTag(c0882a);
            }
            C0882a c0882a2 = (C0882a) view.getTag();
            if (!isEmpty()) {
                c0882a2.f9971a.setText((CharSequence) this.f9976c.get(i));
            }
            ImageView imageView = c0882a2.f9972b;
            if (this.f9974a.an == i) {
                z = true;
            } else {
                z = false;
            }
            imageView.setSelected(z);
            return view;
        }
    }

    private static void m13783m() {
        Factory factory = new Factory("RideDetailsActivity.java", RideDetailsActivity.class);
        av = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.RideDetailsActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 331);
        aw = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.ui.RideDetailsActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), (int) HttpStatus.SC_METHOD_NOT_ALLOWED);
        ax = factory.m15070a("method-execution", factory.m15071a("4", "onResume", "com.olacabs.customer.ui.RideDetailsActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), (int) HttpStatus.SC_LENGTH_REQUIRED);
        ay = factory.m15070a("method-execution", factory.m15071a("4", "onPause", "com.olacabs.customer.ui.RideDetailsActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 1320);
    }

    public RideDetailsActivity() {
        this.f10011h = new Handler();
        this.f10012i = new C08701(this);
        this.f10013j = new aj() {
            final /* synthetic */ RideDetailsActivity f9931a;

            {
                this.f9931a = r1;
            }

            public void onFailure(Throwable th) {
                OLog.m13310a("CodeApplyRequest failed", th);
                this.f9931a.f10009f.cancel();
            }

            public void onSuccess(Object obj) {
                this.f9931a.f10009f.cancel();
                ae aeVar = (ae) obj;
                if (aeVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    Localytics.tagEvent("Rides Coupon Applied");
                    this.f9931a.m13754a("Apply Coupon", "Coupon Applied Succesfully \n");
                } else if (aeVar.getReason().equalsIgnoreCase("INVALID_COUPON_CODE")) {
                    String str = "Invalid coupon!";
                    if (aeVar.getHeader() != null) {
                        str = aeVar.getHeader();
                    }
                    this.f9931a.m13754a(str, this.f9931a.getString(R.string.invalide_coupon_code));
                }
            }
        };
        this.f10014k = new aj() {
            final /* synthetic */ RideDetailsActivity f9935a;

            {
                this.f9935a = r1;
            }

            public void onFailure(Throwable th) {
                Sherlock.m13335a("Ins track ride shown", (VolleyError) th);
                OLog.m13317d("TrackRideRequest failed", th);
            }

            public void onSuccess(Object obj) {
                ct ctVar = (ct) obj;
                if (ctVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    Intent intent = new Intent(this.f9935a, TrackRideActivity.class);
                    if (this.f9935a.m13781l()) {
                        intent.putExtra(Constants.ARG_BOOKING_ID, this.f9935a.getIntent().getStringExtra(Constants.ARG_KRN));
                    } else {
                        intent.putExtra(Constants.ARG_BOOKING_ID, this.f9935a.getIntent().getStringExtra(Constants.ARG_BOOKING_ID));
                    }
                    intent.putExtra(Constants.ARG_FLAG_RIDE_DETAILS_ACTIVITY, true);
                    this.f9935a.startActivity(intent);
                } else if (Utils.m14924g(ctVar.getHeader()) && Utils.m14924g(ctVar.getText())) {
                    this.f9935a.m13754a(ctVar.getHeader(), ctVar.getText());
                }
            }
        };
        this.f10015l = new aj() {
            final /* synthetic */ RideDetailsActivity f9936a;

            {
                this.f9936a = r1;
            }

            public void onFailure(Throwable th) {
                this.f9936a.f10009f.cancel();
                OLog.m13310a("CancelRideRequester failed", th);
            }

            public void onSuccess(Object obj) {
                this.f9936a.f10009f.cancel();
                cn cnVar = (cn) obj;
                if (cnVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    this.f9936a.m13754a("Booking Cancel!", "Your ride is cancelled");
                } else {
                    this.f9936a.m13754a("Failure", cnVar.getReason());
                }
            }
        };
        this.f10019q = new aj() {
            final /* synthetic */ RideDetailsActivity f9937a;

            {
                this.f9937a = r1;
            }

            public void onFailure(Throwable th) {
                this.f9937a.f10011h.postDelayed(this.f9937a.f10012i, 11000);
                OLog.m13310a("RideRequest failed", th);
            }

            public void onSuccess(Object obj) {
                cq cqVar = (cq) obj;
                if (cqVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    this.f9937a.f10018p = cqVar.getRideDetails();
                    String status = this.f9937a.f10018p.getStatus();
                    if (!(status.equalsIgnoreCase(Constants.RIDE_STATUS_CANCELLED) || status.equalsIgnoreCase(Constants.RIDE_STATUS_COMPLETED))) {
                        this.f9937a.f10011h.postDelayed(this.f9937a.f10012i, 11000);
                    }
                    this.f9937a.m13767e();
                    this.f9937a.m13779k();
                    this.f9937a.f9998P.setVisibility(0);
                    this.f9937a.ah.setVisibility(0);
                    this.f9937a.ak.setVisibility(0);
                    return;
                }
                this.f9937a.finish();
            }
        };
        this.al = Trace.NULL;
        this.an = -1;
        this.ar = new aj() {
            final /* synthetic */ RideDetailsActivity f9938a;

            {
                this.f9938a = r1;
            }

            public void onFailure(Throwable th) {
                OLog.m13310a("Add Favourite Request failed", th);
                this.f9938a.f10009f.cancel();
            }

            public void onSuccess(Object obj) {
                this.f9938a.f10009f.cancel();
                this.f9938a.m13751a((ax) obj, this.f9938a.ap);
            }
        };
        this.as = new aj() {
            final /* synthetic */ RideDetailsActivity f9939a;

            {
                this.f9939a = r1;
            }

            public void onFailure(Throwable th) {
                OLog.m13310a("Fetching Driver Image failed", th);
            }

            public void onSuccess(Object obj) {
                this.f9939a.f9991I.setImageBitmap((Bitmap) obj);
            }
        };
        this.au = new aj() {
            final /* synthetic */ RideDetailsActivity f9941a;

            {
                this.f9941a = r1;
            }

            public void onFailure(Throwable th) {
                OLog.m13310a("mMailInvoiceRequester failed", th);
                this.f9941a.f10009f.cancel();
            }

            public void onSuccess(Object obj) {
                this.f9941a.f10009f.cancel();
                ae aeVar = (ae) obj;
                if (aeVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    Localytics.tagEvent("Rides Invoice Emailed");
                    this.f9941a.m13754a("Invoice Sent!", "Invoice was successfully emailed to the address " + this.f9941a.at);
                    return;
                }
                this.f9941a.m13754a(aeVar.getHeader(), aeVar.getReason());
            }
        };
    }

    static {
        m13783m();
        f9977a = RideDetailsActivity.class.getSimpleName();
        f9978b = "Edit non-existing favourite";
        f9979c = "Edit or Delete other user's favourite";
        f9980d = "Incorrect JSON sent";
        f9981e = "Unknown error";
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("RideDetailsActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "RideDetailsActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "RideDetailsActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(av, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_ride_details);
        this.f10020r = (TextView) findViewById(R.id.ridecrn);
        this.f10021s = (TextView) findViewById(R.id.rideDesc);
        this.f10022t = (TextView) findViewById(R.id.ride_status);
        this.f10023u = (TextView) findViewById(R.id.totalBillValueText);
        this.f10024v = (TextView) findViewById(R.id.totalPaidValueText);
        this.f10025w = (TextView) findViewById(R.id.totalPayDetail);
        this.f10026x = (TextView) findViewById(R.id.ridedistance);
        this.f10027y = (TextView) findViewById(R.id.timetaken);
        this.f10028z = (TextView) findViewById(R.id.waittime);
        this.f9983A = (TextView) findViewById(R.id.addName);
        this.f9985C = (TextView) findViewById(R.id.addDetail);
        this.f9984B = (TextView) findViewById(R.id.drop_addName);
        this.f9986D = (TextView) findViewById(R.id.drop_addDetail);
        this.f9987E = (TextView) findViewById(R.id.pickTimeText);
        this.f9988F = (TextView) findViewById(R.id.youSave);
        this.af = (TextView) findViewById(R.id.ola_money_local_balance);
        this.f9991I = (ImageView) findViewById(R.id.staticmap);
        this.aj = (ImageView) findViewById(R.id.backImageView);
        this.aj.setOnClickListener(this);
        this.f9992J = (ImageView) findViewById(R.id.option1);
        this.f9994L = (ImageView) findViewById(R.id.option3);
        this.f9995M = (ImageView) findViewById(R.id.option2);
        this.f9993K = (ImageView) findViewById(R.id.option4);
        this.f9996N = (ImageView) findViewById(R.id.option5);
        this.f9997O = (ImageView) findViewById(R.id.option6);
        this.f9989G = (LinearLayout) findViewById(R.id.billingInfo);
        this.ai = (LinearLayout) findViewById(R.id.localTaxibillingInfo);
        this.f9990H = (LinearLayout) findViewById(R.id.tripInfo);
        this.f9998P = (LinearLayout) findViewById(R.id.rideDetails);
        this.f10000R = (LinearLayout) findViewById(R.id.favPickupLayout);
        this.f10001S = (LinearLayout) findViewById(R.id.couponLayout);
        this.f10002T = (LinearLayout) findViewById(R.id.invoiceLayout);
        this.f10003U = (LinearLayout) findViewById(R.id.issueReport);
        this.f10004V = (LinearLayout) findViewById(R.id.trackRide);
        this.f10005W = (LinearLayout) findViewById(R.id.cancelLayout);
        this.ah = (LinearLayout) findViewById(R.id.bottomTab);
        this.f10006X = (LinearLayout) findViewById(R.id.drop_layout);
        this.ak = (FrameLayout) findViewById(R.id.topBarLayout);
        this.f10017o = (ViewStub) findViewById(R.id.stub_sad_error);
        this.f10016n = (ProgressBar) findViewById(R.id.emptyView);
        this.f10000R.setOnClickListener(this);
        this.f10001S.setOnClickListener(this);
        this.f10002T.setOnClickListener(this);
        this.f10003U.setOnClickListener(this);
        this.f10004V.setOnClickListener(this);
        this.f10005W.setOnClickListener(this);
        this.f10007Y = (TextView) findViewById(R.id.favPickupText);
        this.f10008Z = (TextView) findViewById(R.id.couponCodeText);
        this.aa = (TextView) findViewById(R.id.mailInvoiceText);
        this.ab = (TextView) findViewById(R.id.reportIssueText);
        this.ac = (TextView) findViewById(R.id.trackRideText);
        this.ad = (TextView) findViewById(R.id.cancelText);
        this.ag = (TextView) findViewById(R.id.addDropLabel);
        this.ae = (TextView) findViewById(R.id.addLabel);
        this.f9999Q = (ImageView) findViewById(R.id.categoryBookedImageView);
        this.ao = ((OlaApp) getApplication()).m12878a();
        Localytics.tagScreen("Rides");
        TraceMachine.exitMethod();
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(aw, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    protected void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(ax, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            m13797a();
        } finally {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    public void m13797a() {
        if (Utils.m14909a(getApplicationContext())) {
            m13800c();
        } else {
            m13799b();
        }
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
        this.ao.m13169a(f9977a);
    }

    private void m13767e() {
        if (this.f10018p != null) {
            String str;
            TextView textView;
            if (m13781l()) {
                this.f10021s.setText((this.f10018p.getService_type().getDisplay_name() + " ride").toUpperCase());
            } else {
                this.f10021s.setText((this.f10018p.getService_type().getDisplay_name() + " " + this.f10018p.getCategory_booked() + " ride").toUpperCase());
            }
            CharSequence toUpperCase = this.f10018p.getStatus().toUpperCase();
            if ("Mini".equalsIgnoreCase(this.f10018p.getCategory_booked()) && "compact".equalsIgnoreCase(this.f10018p.getCategory_booked())) {
                this.f9999Q.setImageResource(R.drawable.ic_ride_details_mini);
            } else if ("Sedan".equalsIgnoreCase(this.f10018p.getCategory_booked()) && "economy_sedan".equalsIgnoreCase(this.f10018p.getCategory_booked())) {
                this.f9999Q.setImageResource(R.drawable.ic_ride_details_sedan);
            } else if ("luxury_sedan".equalsIgnoreCase(this.f10018p.getCategory_booked()) && "prime".equalsIgnoreCase(this.f10018p.getCategory_booked())) {
                this.f9999Q.setImageResource(R.drawable.ic_ride_details_luxury);
            } else if ("kp".equalsIgnoreCase(this.f10018p.getCategory_booked()) || "cool_cab".equals(this.f10018p.getCategory_booked())) {
                this.f9999Q.setImageResource(R.drawable.ic_ride_detail_kp);
            } else if ("auto".equalsIgnoreCase(this.f10018p.getCategory_booked())) {
                this.f9999Q.setImageResource(R.drawable.ic_ride_detail_auto);
            }
            if (Constants.RIDE_STATUS_IN_PROGRESS.equals(toUpperCase) || Constants.RIDE_STATUS_SCHEDULED.equals(toUpperCase)) {
                toUpperCase = Constants.RIDE_STATUS_CONFIRMED;
            }
            this.f10022t.setText(toUpperCase);
            if (!Constants.RIDE_STATUS_COMPLETED.equalsIgnoreCase(this.f10018p.getStatus()) || m13781l()) {
                this.f9990H.setVisibility(8);
                this.f9989G.setVisibility(8);
                if (this.f10018p.getOlaMoneyPaid() != null && this.f10018p.getOlaMoneyPaid().intValue() > 0) {
                    this.ai.setVisibility(0);
                    this.af.setText(getResources().getString(R.string.rs_symbol) + this.f10018p.getOlaMoneyPaid());
                }
            } else {
                this.f10023u.setText("Rs " + this.f10018p.getBill().getTotal());
                double ola_money = (double) (this.f10018p.getPayment().getOla_money() + this.f10018p.getBill().getDiscount());
                this.f10024v.setText("Rs " + this.f10018p.getPayment().getTotal());
                this.f9989G.setVisibility(8);
                str = Trace.NULL;
                if (this.f10018p.getPayment().getOla_money() != 0) {
                    str = "Rs " + this.f10018p.getPayment().getOla_money() + " Ola Money";
                    this.f9989G.setVisibility(0);
                    this.f10025w.setVisibility(0);
                }
                if (this.f10018p.getBill().getDiscount() != 0) {
                    this.f9988F.setText(" Rs " + this.f10018p.getBill().getDiscount() + " You Save");
                    this.f9988F.setVisibility(0);
                }
                if (this.f10018p.getPayment().getCash() != 0) {
                    toUpperCase = str + " Rs " + this.f10018p.getPayment().getCash() + " Cash";
                    this.f10025w.setVisibility(0);
                    this.f9989G.setVisibility(0);
                } else {
                    toUpperCase = str + " Rs " + 0 + " Cash";
                }
                this.f10025w.setText(toUpperCase);
                if (this.f10018p.getRoute() != null) {
                    ola_money = Double.parseDouble(this.f10018p.getRoute().getDistance().getValue());
                    if (this.f10018p.getRoute().getDistance().getUnit().equalsIgnoreCase("METER")) {
                        ola_money /= 1000.0d;
                    }
                    this.f10026x.setText(this.f10018p.getRoute().getDistance().getValue() == null ? "0 km" : ola_money + " kms");
                    textView = this.f10027y;
                    if (this.f10018p.getRoute().getDuration().getValue() == null) {
                        toUpperCase = "0 min";
                    } else {
                        toUpperCase = this.f10018p.getRoute().getDuration().getValue() + " mins";
                    }
                    textView.setText(toUpperCase);
                    textView = this.f10028z;
                    if (this.f10018p.getRoute().getWait_time().getValue() == null) {
                        toUpperCase = "0 min";
                    } else {
                        toUpperCase = this.f10018p.getRoute().getWait_time().getValue() + " mins";
                    }
                    textView.setText(toUpperCase);
                    this.f9990H.setVisibility(0);
                } else {
                    this.f9990H.setVisibility(8);
                }
            }
            if (this.f10018p.getPickup().getFav_name() == null || this.f10018p.getPickup_address() == null) {
                str = this.f10018p.getPickup_address();
                if (str != null) {
                    toUpperCase = str.replaceFirst(", ", "!<#>!");
                    if (toUpperCase.contains("!<#>!")) {
                        String[] split = toUpperCase.split("!<#>!");
                        this.f9983A.setText(split[0]);
                        if (split.length > 1) {
                            this.f9985C.setText(split[1]);
                        }
                    } else {
                        this.f9985C.setText(toUpperCase);
                    }
                }
            } else {
                this.f9983A.setText(this.f10018p.getPickup().getFav_name());
                textView = this.f9985C;
                if (this.f10018p.getPickup_landmark() != null) {
                    toUpperCase = this.f10018p.getPickup_landmark() + "\n" + this.f10018p.getPickup_address();
                } else {
                    toUpperCase = this.f10018p.getPickup_address();
                }
                textView.setText(toUpperCase);
            }
            if (this.f10018p.getPickup().getLat() == 0.0d || this.f10018p.getPickup().getLng() == 0.0d) {
                this.f9991I.setVisibility(8);
            } else {
                this.ao.m13178a(new WeakReference(this.as), "http://maps.google.com/maps/api/staticmap", Config.ARGB_8888, HttpStatus.SC_MULTIPLE_CHOICES, HttpStatus.SC_MULTIPLE_CHOICES, Double.toString(this.f10018p.getPickup().getLat()), Double.toString(this.f10018p.getPickup().getLng()), "15", "300x300", "false", f9977a);
            }
            this.f9987E.setText(new SimpleDateFormat("hh:mm a 'on' dd MMM, yyyy").format(new Date(this.f10018p.getPickup().getTime() * 1000)));
            if (m13781l()) {
                m13769f();
            } else {
                this.f10020r.setText("CRN " + this.f10018p.getCrn());
            }
        }
    }

    private void m13769f() {
        this.f10020r.setText("KRN " + this.f10018p.getKrn());
        this.ae.setVisibility(0);
        CharSequence drop_address = this.f10018p.getDrop_address();
        if (drop_address != null && !drop_address.isEmpty()) {
            this.ag.setVisibility(0);
            this.f10006X.setVisibility(0);
            this.f9984B.setText(drop_address);
        }
    }

    public void onClick(View view) {
        View inflate;
        AlertDialog create;
        EditText editText;
        switch (view.getId()) {
            case R.id.backImageView:
                finish();
            case R.id.favPickupLayout:
                inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_apply_coupon, null, false);
                create = new Builder(this).setView(inflate).create();
                ((EditText) inflate.findViewById(R.id.edit_text)).setFilters(new InputFilter[]{new LengthFilter(15)});
                editText = (EditText) inflate.findViewById(R.id.edit_text);
                ((TextView) inflate.findViewById(R.id.item_message)).setText("ENTER NAME TO ADD FAVOURITE");
                ((TextView) inflate.findViewById(R.id.button_action)).setText("ADD");
                inflate.findViewById(R.id.button_action).setOnClickListener(new AnonymousClass21(this, editText, create));
                inflate.findViewById(R.id.button_cancel).setOnClickListener(new C08722(this, create));
                create.show();
            case R.id.couponLayout:
                inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_apply_coupon, null, false);
                create = new Builder(this).setView(inflate).create();
                ((EditText) inflate.findViewById(R.id.edit_text)).setFilters(new InputFilter[]{new LengthFilter(15), new AllCaps()});
                editText = (EditText) inflate.findViewById(R.id.edit_text);
                ((TextView) inflate.findViewById(R.id.item_message)).setText("ENTER COUPON CODE TO APPLY");
                ((TextView) inflate.findViewById(R.id.button_action)).setText("Apply");
                inflate.findViewById(R.id.button_action).setOnClickListener(new C08743(this, editText, create));
                inflate.findViewById(R.id.button_cancel).setOnClickListener(new C08754(this, create));
                create.show();
            case R.id.issueReport:
                if (Utils.m14909a(getApplicationContext())) {
                    Localytics.tagEvent("Rides Issue Reported");
                    Account[] accountsByType = AccountManager.get(getApplicationContext()).getAccountsByType("com.google");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("\n\n\n\n\n--------------------\nOla Android ").append(AppInfo.sVersionName).append(" on ").append(Build.MODEL).append(" running Android ").append(VERSION.RELEASE).append(" user ");
                    if (accountsByType.length > 0) {
                        stringBuilder.append(accountsByType[0].name);
                    }
                    String[] strArr = new String[]{getString(R.string.support_email_addr_main)};
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setData(Uri.parse("mailto:"));
                    intent.setType("message/rfc822");
                    intent.putExtra("android.intent.extra.EMAIL", strArr);
                    if (m13781l()) {
                        intent.putExtra("android.intent.extra.SUBJECT", "Issue related to my ride <KRN " + this.f10018p.getKrn() + ">");
                    } else {
                        intent.putExtra("android.intent.extra.SUBJECT", "Issue related to my ride <CRN " + this.f10018p.getCrn() + ">");
                    }
                    intent.putExtra("android.intent.extra.TEXT", stringBuilder.toString());
                    startActivity(Intent.createChooser(intent, "Send mail ..."));
                    return;
                }
                m13754a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            case R.id.trackRide:
                Sherlock.m13334a("Ins track ride shown");
                if (Utils.m14909a(getApplicationContext())) {
                    m13772h();
                    this.ao.m13195a(new WeakReference(this.f10014k), m13781l(), String.valueOf(getIntent().getStringExtra(Constants.ARG_KRN)), String.valueOf(getIntent().getStringExtra(Constants.ARG_BOOKING_ID)), f9977a);
                    return;
                }
                m13754a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            case R.id.invoiceLayout:
                View inflate2 = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_apply_coupon, null, false);
                AlertDialog create2 = new Builder(this).setView(inflate2).create();
                editText = (EditText) inflate2.findViewById(R.id.edit_text);
                TextView textView = (TextView) inflate2.findViewById(R.id.button_action);
                editText.setAllCaps(false);
                editText.setText(this.ao.m13209c().getUserLoginEmail());
                editText.setSelection(editText.getText().toString().length());
                ((TextView) inflate2.findViewById(R.id.item_message)).setText(R.string.enter_email_address);
                textView.setText(R.string.send);
                editText.addTextChangedListener(new C08765(this, textView, editText));
                textView.setOnClickListener(new C08786(this, editText, create2));
                inflate2.findViewById(R.id.button_cancel).setOnClickListener(new C08797(this, create2));
                create2.show();
            case R.id.cancelLayout:
                if (!Utils.m14909a(getApplicationContext())) {
                    m13754a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
                } else if (this.aq == null || !this.aq.isShowing()) {
                    m13798a(this.f10018p.getCategory_id());
                }
            default:
        }
    }

    private void m13770g() {
        this.f10009f = new ProgressDialog(this, R.style.TransparentnobgProgressDialog);
        this.f10009f.setIndeterminateDrawable(getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10009f.setCancelable(false);
        this.f10009f.show();
    }

    private void m13751a(ax axVar, String str) {
        if (axVar.getStatus().equalsIgnoreCase("SUCCESS")) {
            this.f10007Y.setEnabled(false);
            this.f10000R.setEnabled(false);
            this.f9992J.setEnabled(false);
            m13754a(Constants.SOS_SUCCESS_HEADER_TEXT, str + " has been added successfully to your favourite.");
            Localytics.tagEvent("Favorite Edited");
        } else if (!axVar.getStatus().equalsIgnoreCase("FAILURE")) {
        } else {
            if (axVar.getText() != null) {
                m13754a(axVar.getHeader(), axVar.getText());
                return;
            }
            String reason = axVar.getReason();
            if (reason.equalsIgnoreCase("FAVOURITE_NOT_FOUND")) {
                m13754a("Failure", f9978b);
            } else if (reason.equalsIgnoreCase("USER_FAVOURITE_MISMATCH")) {
                m13754a("Failure", f9979c);
            } else if (reason.equalsIgnoreCase("INVALID_JSON")) {
                m13754a("Failure", f9980d);
            } else if (reason.equalsIgnoreCase("UNKNOWN")) {
                m13754a("Failure", f9981e);
            }
        }
    }

    private String m13762c(String str) {
        Gson gson = new Gson();
        Object awVar = new aw();
        awVar.setAddress(URLEncoder.encode(this.f10018p.getPickup_address()));
        awVar.setLat(this.f10018p.getPickup().getLat());
        awVar.setLng(this.f10018p.getPickup().getLng());
        awVar.setName(str);
        return gson.m12346a(awVar);
    }

    public void m13799b() {
        this.f10017o.setVisibility(0);
        this.f10016n.setVisibility(8);
    }

    public void m13800c() {
        this.f10011h.post(this.f10012i);
        this.f10016n.setVisibility(8);
        this.f10017o.setVisibility(8);
    }

    public void onEvent(ah ahVar) {
        if (ahVar.isConnected()) {
            m13800c();
        } else {
            m13799b();
        }
    }

    private void m13754a(String str, String str2) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        create.setCancelable(false);
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08808(this, str, create));
        create.show();
    }

    private void m13772h() {
        Map hashMap = new HashMap();
        hashMap.put("Cab category", this.f10018p.getCategory_booked());
        Localytics.tagEvent("Rides Ride Track", hashMap);
    }

    private void m13775i() {
        this.f10000R.setEnabled(false);
        this.f10007Y.setEnabled(false);
        this.f9992J.setEnabled(false);
    }

    private void m13777j() {
        this.f10000R.setEnabled(true);
        this.f10007Y.setEnabled(true);
        this.f9992J.setEnabled(true);
    }

    private void m13779k() {
        String status = this.f10018p.getStatus();
        if (status.equalsIgnoreCase(Constants.RIDE_STATUS_CONFIRMED)) {
            if (this.f10018p.getFav_name() != null) {
                m13775i();
            } else {
                m13777j();
            }
            this.f10002T.setVisibility(8);
            this.f10005W.setVisibility(0);
            this.f10002T.setEnabled(false);
            this.aa.setEnabled(false);
            this.f9995M.setEnabled(false);
            this.f10005W.setEnabled(true);
            this.f9997O.setEnabled(true);
            this.ad.setEnabled(true);
            this.f10001S.setEnabled(true);
            this.f10008Z.setEnabled(true);
            this.f9994L.setEnabled(true);
            this.f10003U.setVisibility(8);
            this.f10004V.setVisibility(0);
            this.f10003U.setEnabled(false);
            this.ab.setEnabled(false);
            this.f9993K.setEnabled(false);
            this.f10004V.setEnabled(false);
            this.f9996N.setEnabled(false);
            this.ac.setEnabled(false);
            if (m13781l()) {
                this.f10004V.setEnabled(true);
                this.f9996N.setEnabled(true);
                this.ac.setEnabled(true);
            }
            this.f10022t.setBackgroundColor(getResources().getColor(R.color.ola_orange_light_upcoming));
        } else if (status.equalsIgnoreCase(Constants.RIDE_STATUS_SCHEDULED)) {
            if (this.f10018p.getFav_name() != null) {
                m13775i();
            } else {
                m13777j();
            }
            this.f10002T.setVisibility(8);
            this.f10005W.setVisibility(0);
            this.f10002T.setEnabled(false);
            this.aa.setEnabled(false);
            this.f9995M.setEnabled(false);
            this.f10005W.setEnabled(true);
            this.f9997O.setEnabled(true);
            this.ad.setEnabled(true);
            this.f10001S.setEnabled(true);
            this.f10008Z.setEnabled(true);
            this.f9994L.setEnabled(true);
            this.f10003U.setVisibility(8);
            this.f10004V.setVisibility(0);
            this.f10003U.setEnabled(false);
            this.ab.setEnabled(false);
            this.f9993K.setEnabled(false);
            this.f10004V.setEnabled(true);
            this.f9996N.setEnabled(true);
            this.ac.setEnabled(true);
            this.f10022t.setBackgroundColor(getResources().getColor(R.color.ola_light_tab_strip));
        } else if (status.equalsIgnoreCase(Constants.RIDE_STATUS_IN_PROGRESS)) {
            if (this.f10018p.getFav_name() != null) {
                m13775i();
            } else {
                m13777j();
            }
            this.f10002T.setVisibility(0);
            this.f10005W.setVisibility(8);
            this.f10002T.setEnabled(false);
            this.aa.setEnabled(false);
            this.f9995M.setEnabled(false);
            this.f10005W.setEnabled(false);
            this.f9997O.setEnabled(false);
            this.ad.setEnabled(false);
            this.f10001S.setEnabled(false);
            this.f10008Z.setEnabled(false);
            this.f9994L.setEnabled(false);
            this.f10003U.setVisibility(8);
            this.f10004V.setVisibility(0);
            this.f10003U.setEnabled(false);
            this.ab.setEnabled(false);
            this.f9993K.setEnabled(false);
            this.f10004V.setEnabled(true);
            this.f9996N.setEnabled(true);
            this.ac.setEnabled(true);
            this.f10022t.setBackgroundColor(getResources().getColor(R.color.ola_light_tab_strip));
        } else if (status.equalsIgnoreCase(Constants.RIDE_STATUS_COMPLETED)) {
            if (this.f10018p.getFav_name() != null) {
                m13775i();
            } else {
                m13777j();
            }
            this.f10002T.setVisibility(0);
            this.f10005W.setVisibility(8);
            this.f10002T.setEnabled(true);
            this.aa.setEnabled(true);
            this.f9995M.setEnabled(true);
            this.f10005W.setEnabled(false);
            this.f9997O.setEnabled(false);
            this.ad.setEnabled(false);
            this.f10001S.setEnabled(false);
            this.f10008Z.setEnabled(false);
            this.f9994L.setEnabled(false);
            this.f10003U.setVisibility(0);
            this.f10004V.setVisibility(8);
            this.f10003U.setEnabled(true);
            this.ab.setEnabled(true);
            this.f9993K.setEnabled(true);
            this.f10004V.setEnabled(false);
            this.f9996N.setEnabled(false);
            this.ac.setEnabled(false);
            this.f10022t.setBackgroundColor(getResources().getColor(R.color.ola_gray_text));
        } else if (status.equalsIgnoreCase(Constants.RIDE_STATUS_CANCELLED)) {
            if (this.f10018p.getFav_name() != null) {
                m13775i();
            } else {
                m13777j();
            }
            this.f10002T.setVisibility(8);
            this.f10005W.setVisibility(8);
            this.f10002T.setEnabled(false);
            this.aa.setEnabled(false);
            this.f9995M.setEnabled(false);
            this.f10005W.setEnabled(false);
            this.f9997O.setEnabled(false);
            this.ad.setEnabled(false);
            this.f10001S.setEnabled(false);
            this.f10008Z.setEnabled(false);
            this.f9994L.setEnabled(false);
            this.f10003U.setVisibility(0);
            this.f10004V.setVisibility(0);
            this.f10003U.setEnabled(true);
            this.ab.setEnabled(true);
            this.f9993K.setEnabled(true);
            this.f10004V.setEnabled(false);
            this.f9996N.setEnabled(false);
            this.ac.setEnabled(false);
            this.f10022t.setBackgroundColor(getResources().getColor(R.color.ola_gray_text));
        }
        if (this.f10018p.getPickup().getLat() == 0.0d || this.f10018p.getPickup().getLng() == 0.0d) {
            this.f9992J.setEnabled(false);
            this.f10007Y.setEnabled(false);
            this.f10000R.setEnabled(false);
        }
        if (this.f10018p.getPickup().getFav_name() != null) {
            this.f9992J.setEnabled(false);
            this.f10007Y.setEnabled(false);
        }
        if (!this.f10018p.isCan_add_coupon() || status.equalsIgnoreCase(Constants.RIDE_STATUS_CANCELLED) || status.equalsIgnoreCase(Constants.RIDE_STATUS_IN_PROGRESS)) {
            this.f9994L.setEnabled(false);
            this.f10008Z.setEnabled(false);
            this.f10001S.setEnabled(false);
        }
        if (m13781l()) {
            this.f10001S.setVisibility(8);
            this.f10002T.setVisibility(8);
            this.f10005W.setVisibility(0);
            this.f10004V.setVisibility(0);
            this.f10003U.setVisibility(0);
        }
    }

    private boolean m13781l() {
        return "kp".equalsIgnoreCase(f9982m) || "cool_cab".equalsIgnoreCase(f9982m) || "auto".equalsIgnoreCase(f9982m) || "delivery".equalsIgnoreCase(f9982m);
    }

    public void m13798a(String str) {
        m13750a((LayoutInflater) getSystemService("layout_inflater"), str);
    }

    private void m13750a(LayoutInflater layoutInflater, String str) {
        this.aq = new Builder(this).create();
        if (this.ao.m13218d().getBookingCancelReasons() == null || !this.ao.m13218d().getBookingCancelReasons().containsKey(str)) {
            View inflate = layoutInflater.inflate(R.layout.view_dialog_messsage_yes_no, null, false);
            this.aq.setView(inflate);
            ((TextView) inflate.findViewById(R.id.item_header)).setText("Cancel Ride");
            ((TextView) inflate.findViewById(R.id.item_message)).setText("Are you sure you want to cancel booking ?");
            inflate.findViewById(R.id.button_yes).setOnClickListener(new C08819(this, str));
            inflate.findViewById(R.id.button_no).setOnClickListener(new OnClickListener() {
                final /* synthetic */ RideDetailsActivity f9928a;

                {
                    this.f9928a = r1;
                }

                public void onClick(View view) {
                    this.f9928a.aq.dismiss();
                }
            });
            this.aq.setCancelable(false);
            this.aq.show();
            return;
        }
        inflate = layoutInflater.inflate(R.layout.view_cancel_list_dialog, null, false);
        this.f10010g = (ListView) inflate.findViewById(R.id.item_list);
        this.am = inflate.findViewById(R.id.button_yes);
        this.am.setEnabled(false);
        C0883a c0883a = new C0883a(this, this, (List) this.ao.m13218d().getBookingCancelReasons().get(str));
        this.f10010g.setAdapter(c0883a);
        c0883a.notifyDataSetChanged();
        this.aq.setView(inflate);
        this.f10010g.setFocusable(false);
        this.f10010g.setOnItemClickListener(new AnonymousClass11(this, c0883a));
        this.am.setOnClickListener(new AnonymousClass13(this, str));
        inflate.findViewById(R.id.button_no).setOnClickListener(new OnClickListener() {
            final /* synthetic */ RideDetailsActivity f9934a;

            {
                this.f9934a = r1;
            }

            public void onClick(View view) {
                this.f9934a.aq.dismiss();
                this.f9934a.an = -1;
            }
        });
        this.aq.setCancelable(false);
        this.aq.show();
    }

    protected void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(ay, (Object) this, (Object) this));
        this.f10011h.removeCallbacks(this.f10012i);
        super.onPause();
        this.ao.m13169a(f9977a);
    }

    private void m13758b(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("Cab category", str);
        hashMap.put("Cancel Reason", str2);
        Localytics.tagEvent("Rides Ride Cancelled", hashMap);
    }
}
