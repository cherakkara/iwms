package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputFilter.AllCaps;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.google.android.m4b.maps.model.LatLng;
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
import com.olacabs.customer.p075a.AnalyticsHelper;
import com.olacabs.customer.p075a.AnalyticsManager;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.dh;
import com.olacabs.customer.ui.j.AnonymousClass10;
import com.olacabs.customer.ui.j.AnonymousClass11;
import com.olacabs.customer.ui.j.AnonymousClass12;
import com.olacabs.customer.ui.j.AnonymousClass13;
import com.olacabs.customer.ui.j.AnonymousClass14;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.j */
public class DeliveryConformationFragment extends Fragment implements OnClickListener, TraceFieldInterface {
    public static final String f10781a;
    private static final JoinPoint f10782w = null;
    private static final JoinPoint f10783x = null;
    private AnalyticsHelper f10784b;
    private boolean f10785c;
    private AlertDialog f10786d;
    private aj f10787e;
    private String f10788f;
    private String f10789g;
    private String f10790h;
    private long f10791i;
    private String f10792j;
    private String f10793k;
    private String f10794l;
    private String f10795m;
    private String f10796n;
    private TextView f10797o;
    private TextView f10798p;
    private TextView f10799q;
    private String f10800r;
    private ProgressDialog f10801s;
    private MainActivity f10802t;
    private DataManager f10803u;
    private AnalyticsManager f10804v;

    /* compiled from: DeliveryConformationFragment */
    /* renamed from: com.olacabs.customer.ui.j.10 */
    class AnonymousClass10 implements OnClickListener {
        final /* synthetic */ AlertDialog f10751a;
        final /* synthetic */ EditText f10752b;
        final /* synthetic */ DeliveryConformationFragment f10753c;

        AnonymousClass10(DeliveryConformationFragment deliveryConformationFragment, AlertDialog alertDialog, EditText editText) {
            this.f10753c = deliveryConformationFragment;
            this.f10751a = alertDialog;
            this.f10752b = editText;
        }

        public void onClick(View view) {
            this.f10751a.dismiss();
            ((InputMethodManager) this.f10753c.f10802t.getSystemService("input_method")).hideSoftInputFromWindow(this.f10752b.getWindowToken(), 0);
            this.f10753c.f10798p.setText(this.f10752b.getText());
            this.f10753c.f10798p.setSelected(true);
        }
    }

    /* compiled from: DeliveryConformationFragment */
    /* renamed from: com.olacabs.customer.ui.j.11 */
    class AnonymousClass11 implements OnClickListener {
        final /* synthetic */ AlertDialog f10754a;
        final /* synthetic */ EditText f10755b;
        final /* synthetic */ DeliveryConformationFragment f10756c;

        AnonymousClass11(DeliveryConformationFragment deliveryConformationFragment, AlertDialog alertDialog, EditText editText) {
            this.f10756c = deliveryConformationFragment;
            this.f10754a = alertDialog;
            this.f10755b = editText;
        }

        public void onClick(View view) {
            this.f10754a.dismiss();
            ((InputMethodManager) this.f10756c.f10802t.getSystemService("input_method")).hideSoftInputFromWindow(this.f10755b.getWindowToken(), 0);
        }
    }

    /* compiled from: DeliveryConformationFragment */
    /* renamed from: com.olacabs.customer.ui.j.12 */
    class AnonymousClass12 implements TextWatcher {
        final /* synthetic */ Button f10757a;
        final /* synthetic */ DeliveryConformationFragment f10758b;

        AnonymousClass12(DeliveryConformationFragment deliveryConformationFragment, Button button) {
            this.f10758b = deliveryConformationFragment;
            this.f10757a = button;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence.toString().length() < 10) {
                this.f10757a.setEnabled(false);
            } else {
                this.f10757a.setEnabled(true);
            }
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* compiled from: DeliveryConformationFragment */
    /* renamed from: com.olacabs.customer.ui.j.13 */
    class AnonymousClass13 implements OnClickListener {
        final /* synthetic */ AlertDialog f10759a;
        final /* synthetic */ EditText f10760b;
        final /* synthetic */ DeliveryConformationFragment f10761c;

        AnonymousClass13(DeliveryConformationFragment deliveryConformationFragment, AlertDialog alertDialog, EditText editText) {
            this.f10761c = deliveryConformationFragment;
            this.f10759a = alertDialog;
            this.f10760b = editText;
        }

        public void onClick(View view) {
            this.f10759a.dismiss();
            ((InputMethodManager) this.f10761c.f10802t.getSystemService("input_method")).hideSoftInputFromWindow(this.f10760b.getWindowToken(), 0);
            this.f10761c.f10797o.setText(this.f10760b.getText());
            this.f10761c.f10797o.setSelected(true);
        }
    }

    /* compiled from: DeliveryConformationFragment */
    /* renamed from: com.olacabs.customer.ui.j.14 */
    class AnonymousClass14 implements OnClickListener {
        final /* synthetic */ AlertDialog f10762a;
        final /* synthetic */ EditText f10763b;
        final /* synthetic */ DeliveryConformationFragment f10764c;

        AnonymousClass14(DeliveryConformationFragment deliveryConformationFragment, AlertDialog alertDialog, EditText editText) {
            this.f10764c = deliveryConformationFragment;
            this.f10762a = alertDialog;
            this.f10763b = editText;
        }

        public void onClick(View view) {
            this.f10762a.dismiss();
            ((InputMethodManager) this.f10764c.f10802t.getSystemService("input_method")).hideSoftInputFromWindow(this.f10763b.getWindowToken(), 0);
        }
    }

    /* renamed from: com.olacabs.customer.ui.j.1 */
    class DeliveryConformationFragment implements aj {
        final /* synthetic */ DeliveryConformationFragment f10765a;

        /* renamed from: com.olacabs.customer.ui.j.1.1 */
        class DeliveryConformationFragment implements OnClickListener {
            final /* synthetic */ String f10749a;
            final /* synthetic */ DeliveryConformationFragment f10750b;

            /* renamed from: com.olacabs.customer.ui.j.1.1.1 */
            class DeliveryConformationFragment implements Runnable {
                final /* synthetic */ Context f10747a;
                final /* synthetic */ DeliveryConformationFragment f10748b;

                DeliveryConformationFragment(DeliveryConformationFragment deliveryConformationFragment, Context context) {
                    this.f10748b = deliveryConformationFragment;
                    this.f10747a = context;
                }

                public void run() {
                    Intent intent = new Intent(this.f10747a, TrackRideActivity.class);
                    intent.putExtra(Constants.ARG_BOOKING_ID, this.f10748b.f10749a);
                    this.f10747a.startActivity(intent);
                }
            }

            DeliveryConformationFragment(DeliveryConformationFragment deliveryConformationFragment, String str) {
                this.f10750b = deliveryConformationFragment;
                this.f10749a = str;
            }

            public void onClick(View view) {
                new Handler().postDelayed(new DeliveryConformationFragment(this, this.f10750b.f10765a.getActivity()), 600);
                BookingFragment bookingFragment = (BookingFragment) this.f10750b.f10765a.getParentFragment();
                bookingFragment.m14361b(false);
                bookingFragment.m14376n();
                this.f10750b.f10765a.f10786d.dismiss();
            }
        }

        DeliveryConformationFragment(DeliveryConformationFragment deliveryConformationFragment) {
            this.f10765a = deliveryConformationFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to fetch surcharge", th);
            if (this.f10765a.isAdded()) {
                this.f10765a.f10801s.dismiss();
                this.f10765a.m14404c(this.f10765a.getActivity().getString(R.string.sos_ec_header), this.f10765a.getActivity().getString(R.string.connection_timed_out));
            }
        }

        public void onSuccess(Object obj) {
            if (this.f10765a.isAdded()) {
                this.f10765a.f10801s.dismiss();
                dh dhVar = (dh) obj;
                if (dhVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    String bookingId = dhVar.getBooking().getBookingId();
                    Map hashMap = new HashMap();
                    hashMap.put("Booking type", this.f10765a.f10785c ? "Ride now" : "Ride later");
                    hashMap.put("Cab category", this.f10765a.f10789g);
                    hashMap.put("Discount State", "No coupon");
                    hashMap.put("City name", this.f10765a.f10800r);
                    Localytics.tagEvent("Booking Sheduled", hashMap);
                    Leanplum.track("booking scheduled", hashMap);
                    this.f10765a.f10784b.m12859a("fb_mobile_purchase", hashMap);
                    if (!this.f10765a.f10785c) {
                        this.f10765a.m14399b(dhVar.getTitle(), dhVar.getText());
                    } else if (this.f10765a.f10786d == null || !this.f10765a.f10786d.isShowing()) {
                        View inflate = this.f10765a.getActivity().getLayoutInflater().inflate(R.layout.view_dialog_ok_button, null, false);
                        this.f10765a.f10786d = new Builder(this.f10765a.getActivity()).setView(inflate).create();
                        ((TextView) inflate.findViewById(R.id.item_header)).setText(dhVar.getTitle());
                        ((TextView) inflate.findViewById(R.id.item_message)).setText(dhVar.getText());
                        inflate.findViewById(R.id.button_ok).setOnClickListener(new DeliveryConformationFragment(this, bookingId));
                        this.f10765a.f10786d.setCancelable(false);
                        this.f10765a.f10786d.show();
                    }
                } else if (dhVar.getStatus().equalsIgnoreCase("FAILURE") && dhVar.getReason() != null) {
                    if (dhVar.getReason().equalsIgnoreCase("INSUFFICIENT_AMOUNT")) {
                        this.f10765a.m14397a(dhVar.getHeader() != null ? dhVar.getHeader() : "Insufficient Balance", dhVar.getText() != null ? dhVar.getText() : "Recharge Ola money");
                        return;
                    }
                    String header;
                    DeliveryConformationFragment deliveryConformationFragment = this.f10765a;
                    if (dhVar.getHeader() != null) {
                        header = dhVar.getHeader();
                    } else {
                        header = this.f10765a.getActivity().getString(R.string.sos_ec_header);
                    }
                    deliveryConformationFragment.m14404c(header, dhVar.getText() != null ? dhVar.getText() : this.f10765a.getActivity().getString(R.string.generic_failure_desc));
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.j.2 */
    class DeliveryConformationFragment implements TextWatcher {
        final /* synthetic */ Button f10766a;
        final /* synthetic */ DeliveryConformationFragment f10767b;

        DeliveryConformationFragment(DeliveryConformationFragment deliveryConformationFragment, Button button) {
            this.f10767b = deliveryConformationFragment;
            this.f10766a = button;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence.toString().trim().length() < 1) {
                this.f10766a.setEnabled(false);
            } else {
                this.f10766a.setEnabled(true);
            }
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.j.3 */
    class DeliveryConformationFragment implements DialogInterface.OnClickListener {
        final /* synthetic */ DeliveryConformationFragment f10768a;

        DeliveryConformationFragment(DeliveryConformationFragment deliveryConformationFragment) {
            this.f10768a = deliveryConformationFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.j.4 */
    class DeliveryConformationFragment implements DialogInterface.OnClickListener {
        final /* synthetic */ DatePicker f10769a;
        final /* synthetic */ DeliveryConformationFragment f10770b;

        DeliveryConformationFragment(DeliveryConformationFragment deliveryConformationFragment, DatePicker datePicker) {
            this.f10770b = deliveryConformationFragment;
            this.f10769a = datePicker;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Calendar instance = Calendar.getInstance();
            instance.set(1, this.f10769a.getYear());
            instance.set(2, this.f10769a.getMonth());
            instance.set(5, this.f10769a.getDayOfMonth());
            if (instance.getTimeInMillis() < System.currentTimeMillis() + Constants.MILLIS_IN_AN_HOUR) {
                this.f10770b.m14412g();
                return;
            }
            dialogInterface.dismiss();
            if (instance.getTimeInMillis() != this.f10770b.f10791i) {
                this.f10770b.f10791i = instance.getTimeInMillis();
                this.f10770b.m14402c();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.j.5 */
    class DeliveryConformationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10771a;
        final /* synthetic */ DeliveryConformationFragment f10772b;

        DeliveryConformationFragment(DeliveryConformationFragment deliveryConformationFragment, AlertDialog alertDialog) {
            this.f10772b = deliveryConformationFragment;
            this.f10771a = alertDialog;
        }

        public void onClick(View view) {
            this.f10771a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.j.6 */
    class DeliveryConformationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10773a;
        final /* synthetic */ DeliveryConformationFragment f10774b;

        DeliveryConformationFragment(DeliveryConformationFragment deliveryConformationFragment, AlertDialog alertDialog) {
            this.f10774b = deliveryConformationFragment;
            this.f10773a = alertDialog;
        }

        public void onClick(View view) {
            this.f10773a.dismiss();
            BookingFragment bookingFragment = (BookingFragment) this.f10774b.getParentFragment();
            bookingFragment.m14379q();
            bookingFragment.m14376n();
        }
    }

    /* renamed from: com.olacabs.customer.ui.j.7 */
    class DeliveryConformationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10775a;
        final /* synthetic */ DeliveryConformationFragment f10776b;

        DeliveryConformationFragment(DeliveryConformationFragment deliveryConformationFragment, AlertDialog alertDialog) {
            this.f10776b = deliveryConformationFragment;
            this.f10775a = alertDialog;
        }

        public void onClick(View view) {
            this.f10775a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.j.8 */
    class DeliveryConformationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10777a;
        final /* synthetic */ DeliveryConformationFragment f10778b;

        DeliveryConformationFragment(DeliveryConformationFragment deliveryConformationFragment, AlertDialog alertDialog) {
            this.f10778b = deliveryConformationFragment;
            this.f10777a = alertDialog;
        }

        public void onClick(View view) {
            this.f10777a.dismiss();
            Localytics.tagEvent("Recharge pressed");
            this.f10778b.f10802t.m13595a(3);
        }
    }

    /* renamed from: com.olacabs.customer.ui.j.9 */
    class DeliveryConformationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10779a;
        final /* synthetic */ DeliveryConformationFragment f10780b;

        DeliveryConformationFragment(DeliveryConformationFragment deliveryConformationFragment, AlertDialog alertDialog) {
            this.f10780b = deliveryConformationFragment;
            this.f10779a = alertDialog;
        }

        public void onClick(View view) {
            this.f10779a.dismiss();
            Localytics.tagEvent("Cancel pressed");
            BookingFragment bookingFragment = (BookingFragment) this.f10780b.getParentFragment();
            bookingFragment.m14379q();
            bookingFragment.m14376n();
        }
    }

    private static void m14416i() {
        Factory factory = new Factory("DeliveryConformationFragment.java", DeliveryConformationFragment.class);
        f10782w = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.DeliveryConformationFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 231);
        f10783x = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.DeliveryConformationFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 277);
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
        DeliveryConformationFragment.m14416i();
        f10781a = DeliveryConformationFragment.class.getSimpleName();
    }

    public DeliveryConformationFragment() {
        this.f10787e = new DeliveryConformationFragment(this);
    }

    public static DeliveryConformationFragment m14394a(String str, String str2, String str3, String str4, String str5, String str6, long j, boolean z, String str7, String str8, String str9, String str10) {
        DeliveryConformationFragment deliveryConformationFragment = new DeliveryConformationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category_id", str);
        bundle.putString("cateegory_name", str2);
        bundle.putString("category_base_rate", str3);
        bundle.putString("category_rate_per_km", str4);
        bundle.putLong("pick_up_time", j);
        bundle.putBoolean("is_ride_now", z);
        bundle.putString("location_tag", str6);
        bundle.putString("current_city", str5);
        bundle.putString("market_campaign", str7);
        bundle.putString("delivery_text", str8);
        bundle.putString("delivery_sub_text", str9);
        bundle.putString("delivery_date_hint", str10);
        deliveryConformationFragment.setArguments(bundle);
        return deliveryConformationFragment;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("j");
        try {
            TraceMachine.enterMethod(this._nr_trace, "j#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "j#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10782w, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            this.f10788f = arguments.getString("category_id");
            this.f10789g = arguments.getString("cateegory_name");
            this.f10785c = arguments.getBoolean("is_ride_now");
            this.f10792j = arguments.getString("delivery_location");
            this.f10791i = arguments.getLong("pick_up_time");
            this.f10790h = arguments.getString("location_tag");
            this.f10800r = arguments.getString("current_city");
            this.f10793k = arguments.getString("market_campaign");
            this.f10794l = arguments.getString("delivery_text");
            this.f10795m = arguments.getString("delivery_sub_text");
            this.f10796n = arguments.getString("delivery_date_hint");
        }
        this.f10802t = (MainActivity) getActivity();
        this.f10801s = new ProgressDialog(this.f10802t, R.style.TransparentProgressDialog);
        this.f10801s.setIndeterminateDrawable(this.f10802t.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10801s.setCancelable(false);
        OlaApp olaApp = (OlaApp) this.f10802t.getApplication();
        this.f10803u = olaApp.m12878a();
        this.f10804v = olaApp.m12879b();
        this.f10784b = this.f10804v.m12867b(getActivity());
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "j#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "j#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10783x, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_delivery_conformation, viewGroup, false);
        inflate.findViewById(R.id.button_ride_cancel).setOnClickListener(this);
        inflate.findViewById(R.id.button_ride_conform).setOnClickListener(this);
        inflate.findViewById(R.id.receiver_name_layout).setOnClickListener(this);
        inflate.findViewById(R.id.receiver_number_layout).setOnClickListener(this);
        this.f10797o = (TextView) inflate.findViewById(R.id.receiver_name);
        this.f10798p = (TextView) inflate.findViewById(R.id.receiver_number);
        this.f10799q = (TextView) inflate.findViewById(R.id.delivery_date);
        if (this.f10785c) {
            inflate.findViewById(R.id.item_arrow_delivery_date).setVisibility(8);
        } else {
            inflate.findViewById(R.id.item_arrow_delivery_date).setVisibility(0);
            inflate.findViewById(R.id.delivery_date_layout).setOnClickListener(this);
        }
        m14395a(inflate);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.measure(0, 0);
        ((BookingFragment) getParentFragment()).m14347a(view.getMeasuredHeight());
        if (m14414h()) {
            view.findViewById(R.id.recipient_layout).setVisibility(0);
        }
    }

    private void m14402c() {
        m14395a(getView());
    }

    private void m14395a(View view) {
        TextView textView = (TextView) view.findViewById(R.id.confirmation_text_1);
        TextView textView2 = (TextView) view.findViewById(R.id.confirmation_text_2);
        TextView textView3 = (TextView) view.findViewById(R.id.confirmation_text_3);
        TextView textView4 = (TextView) view.findViewById(R.id.delivery_date_header);
        textView3.setText(Html.fromHtml("<a href=\"http://www.olacabs.com/offers/marketing\">KNOW MORE</a>"));
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(this.f10794l.isEmpty() ? this.f10794l : Ola.f11499p);
        textView2.setText(this.f10795m.isEmpty() ? this.f10795m : Ola.f11500q);
        textView4.setText(this.f10796n.isEmpty() ? this.f10796n : Ola.f11501r);
        if (!this.f10785c) {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(this.f10791i);
            this.f10799q.setText(new SimpleDateFormat("dd MMM").format(instance.getTime()));
        } else if (this.f10791i > 0) {
            this.f10799q.setText(String.format(getResources().getQuantityString(R.plurals.minutes, (int) this.f10791i), new Object[]{Long.valueOf(this.f10791i)}));
        } else {
            this.f10799q.setText("Cab will be there in a few seconds...");
        }
    }

    public void onClick(View view) {
        OLog.m13313b("Clicked", new Object[0]);
        switch (view.getId()) {
            case R.id.button_ride_cancel:
                m14420a();
            case R.id.button_ride_conform:
                if (!Utils.m14909a(this.f10802t.getApplicationContext())) {
                    m14404c(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
                } else if (m14414h() && this.f10797o.getText().toString().isEmpty()) {
                    m14404c(getActivity().getString(R.string.recepient_name_missing), "Please enter the recipient's name to continue");
                } else if (m14414h() && this.f10798p.getText().toString().isEmpty()) {
                    m14404c(getActivity().getString(R.string.recepient_number_missing), "Please enter the recipient's phone number to continue");
                } else {
                    m14421b();
                }
            case R.id.receiver_name_layout:
                m14408e();
            case R.id.receiver_number_layout:
                m14406d();
            case R.id.delivery_date_layout:
                m14410f();
            default:
        }
    }

    private void m14397a(String str, String str2) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        ((TextView) inflate.findViewById(R.id.button_yes)).setText("RECHARGE");
        ((TextView) inflate.findViewById(R.id.button_no)).setText("CANCEL");
        inflate.findViewById(R.id.button_yes).setOnClickListener(new DeliveryConformationFragment(this, create));
        inflate.findViewById(R.id.button_no).setOnClickListener(new DeliveryConformationFragment(this, create));
        create.show();
    }

    private void m14406d() {
        View inflate = LayoutInflater.from(this.f10802t).inflate(R.layout.view_dialog_receiver_detail, null, false);
        AlertDialog create = new Builder(this.f10802t).setView(inflate).create();
        Button button = (Button) inflate.findViewById(R.id.button_action);
        button.setEnabled(false);
        ((TextView) inflate.findViewById(R.id.item_header)).setText("ENTER 10 DIGIT MOBILE NO.");
        EditText editText = (EditText) inflate.findViewById(R.id.edit_text);
        editText.setFilters(new InputFilter[]{new LengthFilter(10)});
        editText.setInputType(3);
        if (!this.f10798p.getText().toString().trim().equalsIgnoreCase(getActivity().getString(R.string.text_add_recepient))) {
            editText.setText(this.f10798p.getText());
        }
        button.setOnClickListener(new AnonymousClass10(this, create, editText));
        inflate.findViewById(R.id.button_cancel).setOnClickListener(new AnonymousClass11(this, create, editText));
        editText.addTextChangedListener(new AnonymousClass12(this, button));
        create.getWindow().setSoftInputMode(4);
        create.show();
    }

    private void m14408e() {
        View inflate = LayoutInflater.from(this.f10802t).inflate(R.layout.view_dialog_receiver_detail, null, false);
        AlertDialog create = new Builder(this.f10802t).setView(inflate).create();
        Button button = (Button) inflate.findViewById(R.id.button_action);
        button.setEnabled(false);
        ((TextView) inflate.findViewById(R.id.item_header)).setText("ENTER NAME HERE");
        EditText editText = (EditText) inflate.findViewById(R.id.edit_text);
        editText.setFilters(new InputFilter[]{new AllCaps()});
        if (!this.f10797o.getText().toString().trim().equalsIgnoreCase(getActivity().getString(R.string.hint_add_recepient))) {
            editText.setText(this.f10797o.getText());
        }
        button.setOnClickListener(new AnonymousClass13(this, create, editText));
        inflate.findViewById(R.id.button_cancel).setOnClickListener(new AnonymousClass14(this, create, editText));
        editText.addTextChangedListener(new DeliveryConformationFragment(this, button));
        create.getWindow().setSoftInputMode(4);
        create.show();
    }

    private void m14410f() {
        View inflate = ((LayoutInflater) this.f10802t.getSystemService("layout_inflater")).inflate(R.layout.view_date_time_picker, null, false);
        DatePicker datePicker = (DatePicker) inflate.findViewById(R.id.date_picker);
        ((TimePicker) inflate.findViewById(R.id.time_picker)).setVisibility(8);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.f10791i);
        datePicker.updateDate(instance.get(1), instance.get(2), instance.get(5));
        AlertDialog create = new Builder(this.f10802t, 16973935).setView(inflate).setPositiveButton("Done", new DeliveryConformationFragment(this, datePicker)).setNegativeButton("Cancel", new DeliveryConformationFragment(this)).create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
    }

    private void m14412g() {
        View inflate = ((LayoutInflater) this.f10802t.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10802t).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText("Invalid pickup time");
        ((TextView) inflate.findViewById(R.id.item_message)).setText("Please make sure that pickup time is after atleast an hour from now");
        inflate.findViewById(R.id.button_ok).setOnClickListener(new DeliveryConformationFragment(this, create));
        create.show();
    }

    public void m14420a() {
        BookingFragment bookingFragment = (BookingFragment) getParentFragment();
        bookingFragment.m14379q();
        bookingFragment.m14376n();
    }

    public void m14421b() {
        Map hashMap = new HashMap();
        hashMap.put("Booking type", this.f10785c ? "Ride now" : "Ride later");
        hashMap.put("Cab category", this.f10789g);
        hashMap.put("City name", this.f10800r);
        Localytics.tagEvent("Booking Conformed", hashMap);
        this.f10784b.m12859a("fb_mobile_add_to_cart", hashMap);
        this.f10801s.show();
        BookingFragment bookingFragment = (BookingFragment) getParentFragment();
        String d = bookingFragment.m14365d();
        if (TextUtils.isEmpty(d)) {
            d = this.f10803u.m13209c().getUserAddress();
        }
        LatLng e = bookingFragment.m14367e();
        if (e != null) {
            this.f10803u.m13189a(new WeakReference(this.f10787e), this.f10788f, String.valueOf(e.f7554a), String.valueOf(e.f7555b), this.f10785c, String.valueOf(this.f10791i / 1000), d, this.f10797o.getText().toString(), this.f10798p.getText().toString(), f10781a);
        }
    }

    private void m14399b(String str, String str2) {
        View inflate = ((LayoutInflater) this.f10802t.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10802t).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new DeliveryConformationFragment(this, create));
        create.show();
    }

    private boolean m14414h() {
        return "delivery".equalsIgnoreCase(this.f10793k);
    }

    private void m14404c(String str, String str2) {
        View inflate = ((LayoutInflater) this.f10802t.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10802t).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new DeliveryConformationFragment(this, create));
        create.show();
    }
}
