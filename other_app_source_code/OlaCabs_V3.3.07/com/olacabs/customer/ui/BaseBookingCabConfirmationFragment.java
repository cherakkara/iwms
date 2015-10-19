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
import android.text.InputFilter;
import android.text.InputFilter.AllCaps;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import com.android.volley.VolleyError;
import com.apsalar.sdk.Apsalar;
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
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p075a.AnalyticsHelper;
import com.olacabs.customer.p075a.AnalyticsManager;
import com.olacabs.customer.p076d.ac;
import com.olacabs.customer.p076d.ae;
import com.olacabs.customer.p076d.af;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.dh;
import com.olacabs.customer.ui.d.AnonymousClass10;
import com.olacabs.customer.ui.d.AnonymousClass11;
import com.olacabs.customer.ui.d.AnonymousClass13;
import com.olacabs.customer.ui.d.AnonymousClass14;
import com.olacabs.customer.ui.d.AnonymousClass15;
import com.olacabs.customer.ui.widgets.ErrorView.ErrorView;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.d */
public class BaseBookingCabConfirmationFragment extends Fragment implements OnClickListener, ErrorView, TraceFieldInterface {
    private static final JoinPoint f10564C = null;
    private static final JoinPoint f10565D = null;
    public static final String f10566a;
    private AnalyticsHelper f10567A;
    private aj f10568B;
    protected boolean f10569b;
    protected String f10570c;
    protected String f10571d;
    protected long f10572e;
    protected String f10573f;
    protected String f10574g;
    protected String f10575h;
    protected String f10576i;
    protected String f10577j;
    protected boolean f10578k;
    protected ProgressDialog f10579l;
    protected DataManager f10580m;
    protected boolean f10581n;
    TextView f10582o;
    RelativeLayout f10583p;
    Button f10584q;
    private String f10585r;
    private ProgressDialog f10586s;
    private String f10587t;
    private AlertDialog f10588u;
    private aj f10589v;
    private aj f10590w;
    private TextView f10591x;
    private MainActivity f10592y;
    private AnalyticsManager f10593z;

    /* compiled from: BaseBookingCabConfirmationFragment */
    /* renamed from: com.olacabs.customer.ui.d.10 */
    class AnonymousClass10 implements OnClickListener {
        final /* synthetic */ View f10529a;
        final /* synthetic */ AlertDialog f10530b;
        final /* synthetic */ BaseBookingCabConfirmationFragment f10531c;

        AnonymousClass10(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, View view, AlertDialog alertDialog) {
            this.f10531c = baseBookingCabConfirmationFragment;
            this.f10529a = view;
            this.f10530b = alertDialog;
        }

        public void onClick(View view) {
            this.f10529a.setEnabled(true);
            this.f10530b.dismiss();
        }
    }

    /* compiled from: BaseBookingCabConfirmationFragment */
    /* renamed from: com.olacabs.customer.ui.d.11 */
    class AnonymousClass11 implements DialogInterface.OnClickListener {
        final /* synthetic */ ArrayList f10532a;
        final /* synthetic */ BaseBookingCabConfirmationFragment f10533b;

        AnonymousClass11(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, ArrayList arrayList) {
            this.f10533b = baseBookingCabConfirmationFragment;
            this.f10532a = arrayList;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            ac acVar = (ac) this.f10532a.get(i);
            if (!this.f10533b.f10570c.equalsIgnoreCase(acVar.getId())) {
                this.f10533b.f10570c = acVar.getId();
                this.f10533b.f10571d = acVar.getDisplayName();
                this.f10533b.f10587t = null;
                this.f10533b.m14163d();
            }
        }
    }

    /* compiled from: BaseBookingCabConfirmationFragment */
    /* renamed from: com.olacabs.customer.ui.d.13 */
    class AnonymousClass13 implements DialogInterface.OnClickListener {
        final /* synthetic */ DatePicker f10535a;
        final /* synthetic */ TimePicker f10536b;
        final /* synthetic */ BaseBookingCabConfirmationFragment f10537c;

        AnonymousClass13(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, DatePicker datePicker, TimePicker timePicker) {
            this.f10537c = baseBookingCabConfirmationFragment;
            this.f10535a = datePicker;
            this.f10536b = timePicker;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Calendar instance = Calendar.getInstance();
            instance.set(1, this.f10535a.getYear());
            instance.set(2, this.f10535a.getMonth());
            instance.set(5, this.f10535a.getDayOfMonth());
            instance.set(11, this.f10536b.getCurrentHour().intValue());
            instance.set(12, this.f10536b.getCurrentMinute().intValue());
            if (instance.getTimeInMillis() < System.currentTimeMillis() + Constants.MILLIS_IN_AN_HOUR) {
                this.f10537c.m14168f();
                return;
            }
            dialogInterface.dismiss();
            if (instance.getTimeInMillis() != this.f10537c.f10572e) {
                this.f10537c.f10572e = instance.getTimeInMillis();
                this.f10537c.f10587t = null;
                this.f10537c.m14163d();
            }
        }
    }

    /* compiled from: BaseBookingCabConfirmationFragment */
    /* renamed from: com.olacabs.customer.ui.d.14 */
    class AnonymousClass14 implements OnClickListener {
        final /* synthetic */ AlertDialog f10538a;
        final /* synthetic */ BaseBookingCabConfirmationFragment f10539b;

        AnonymousClass14(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, AlertDialog alertDialog) {
            this.f10539b = baseBookingCabConfirmationFragment;
            this.f10538a = alertDialog;
        }

        public void onClick(View view) {
            this.f10538a.dismiss();
        }
    }

    /* compiled from: BaseBookingCabConfirmationFragment */
    /* renamed from: com.olacabs.customer.ui.d.15 */
    class AnonymousClass15 implements OnClickListener {
        final /* synthetic */ AlertDialog f10540a;
        final /* synthetic */ BaseBookingCabConfirmationFragment f10541b;

        AnonymousClass15(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, AlertDialog alertDialog) {
            this.f10541b = baseBookingCabConfirmationFragment;
            this.f10540a = alertDialog;
        }

        public void onClick(View view) {
            this.f10540a.dismiss();
            BookingFragment bookingFragment = (BookingFragment) this.f10541b.getParentFragment();
            bookingFragment.m14379q();
            bookingFragment.m14376n();
        }
    }

    /* renamed from: com.olacabs.customer.ui.d.1 */
    class BaseBookingCabConfirmationFragment implements aj {
        final /* synthetic */ BaseBookingCabConfirmationFragment f10542a;

        /* renamed from: com.olacabs.customer.ui.d.1.1 */
        class BaseBookingCabConfirmationFragment implements OnClickListener {
            final /* synthetic */ String f10527a;
            final /* synthetic */ BaseBookingCabConfirmationFragment f10528b;

            /* renamed from: com.olacabs.customer.ui.d.1.1.1 */
            class BaseBookingCabConfirmationFragment implements Runnable {
                final /* synthetic */ Context f10525a;
                final /* synthetic */ BaseBookingCabConfirmationFragment f10526b;

                BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, Context context) {
                    this.f10526b = baseBookingCabConfirmationFragment;
                    this.f10525a = context;
                }

                public void run() {
                    if (this.f10525a != null) {
                        Intent intent = new Intent(this.f10525a, TrackRideActivity.class);
                        intent.putExtra(Constants.ARG_BOOKING_ID, this.f10526b.f10527a);
                        this.f10525a.startActivity(intent);
                    }
                }
            }

            BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, String str) {
                this.f10528b = baseBookingCabConfirmationFragment;
                this.f10527a = str;
            }

            public void onClick(View view) {
                Sherlock.m13334a("Ins track ride shown");
                new Handler().postDelayed(new BaseBookingCabConfirmationFragment(this, this.f10528b.f10542a.getActivity()), 600);
                BookingFragment bookingFragment = (BookingFragment) this.f10528b.f10542a.getParentFragment();
                bookingFragment.m14361b(false);
                bookingFragment.m14376n();
                this.f10528b.f10542a.f10588u.dismiss();
            }
        }

        BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment) {
            this.f10542a = baseBookingCabConfirmationFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to Confirm", th);
            if (this.f10542a.isAdded()) {
                this.f10542a.f10579l.dismiss();
                this.f10542a.f10584q.setClickable(true);
                HashMap hashMap = new HashMap();
                if (Utils.m14924g(this.f10542a.f10576i)) {
                    hashMap.put("Booking city", this.f10542a.f10576i);
                } else {
                    hashMap.put("Booking city", "NA");
                }
                Sherlock.m13338a("Ins booked ride", "Failure", (VolleyError) th, Constants.NO_NETWORK_TEXT, true, hashMap);
                this.f10542a.m14181b(this.f10542a.getActivity().getString(R.string.sos_ec_header), this.f10542a.getActivity().getString(R.string.connection_timed_out));
                Localytics.tagEvent("TIMED OUT");
            }
        }

        public void onSuccess(Object obj) {
            if (this.f10542a.isAdded()) {
                this.f10542a.f10579l.dismiss();
                this.f10542a.f10584q.setClickable(true);
                dh dhVar = (dh) obj;
                if (dhVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    String bookingId = dhVar.getBooking().getBookingId();
                    Map hashMap = new HashMap();
                    hashMap.put("Booking type", this.f10542a.f10569b ? "Ride now" : "Ride later");
                    hashMap.put("Cab category", this.f10542a.f10571d);
                    hashMap.put("Discount State", this.f10542a.f10587t == null ? "No coupon" : "Coupon applied");
                    hashMap.put("City name", this.f10542a.f10576i);
                    Localytics.tagEvent("Booking Sheduled", hashMap);
                    Apsalar.event("Booking Sheduled", new JSONObject(hashMap));
                    Leanplum.track("booking scheduled", hashMap);
                    this.f10542a.f10567A.m12859a("fb_mobile_purchase", hashMap);
                    if (this.f10542a.f10569b) {
                        if (this.f10542a.f10588u == null || !this.f10542a.f10588u.isShowing()) {
                            HashMap hashMap2 = new HashMap();
                            if (Utils.m14924g(this.f10542a.f10576i)) {
                                hashMap2.put("Booking city", this.f10542a.f10576i);
                            } else {
                                hashMap2.put("Booking city", "NA");
                            }
                            Sherlock.m13340a("Ins booked ride", hashMap2);
                            View inflate = this.f10542a.getActivity().getLayoutInflater().inflate(R.layout.view_dialog_ok_button, null, false);
                            this.f10542a.f10588u = new Builder(this.f10542a.getActivity()).setView(inflate).create();
                            ((TextView) inflate.findViewById(R.id.item_header)).setText(dhVar.getHeader());
                            ((TextView) inflate.findViewById(R.id.item_message)).setText(dhVar.getText());
                            inflate.findViewById(R.id.button_ok).setOnClickListener(new BaseBookingCabConfirmationFragment(this, bookingId));
                            this.f10542a.f10588u.setCancelable(false);
                            this.f10542a.f10588u.show();
                        }
                    } else if (!dhVar.isRechargeScreen()) {
                        this.f10542a.m14161c(dhVar.getHeader(), dhVar.getText());
                    } else if (dhVar.getHeader() != null && dhVar.getText() != null) {
                        this.f10542a.m14164d(dhVar.getHeader(), dhVar.getText());
                    }
                } else if (dhVar.getStatus().equalsIgnoreCase("FAILURE") && dhVar.getReason() != null) {
                    String header;
                    HashMap hashMap3 = new HashMap();
                    if (Utils.m14924g(this.f10542a.f10576i)) {
                        hashMap3.put("Booking city", this.f10542a.f10576i);
                    } else {
                        hashMap3.put("Booking city", "NA");
                    }
                    Sherlock.m13338a("Ins booked ride", "Failure", null, this.f10542a.getActivity().getString(R.string.generic_failure_desc), true, hashMap3);
                    BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment = this.f10542a;
                    if (dhVar.getHeader() != null) {
                        header = dhVar.getHeader();
                    } else {
                        header = this.f10542a.getActivity().getString(R.string.sos_ec_header);
                    }
                    baseBookingCabConfirmationFragment.m14181b(header, dhVar.getText() != null ? dhVar.getText() : this.f10542a.getActivity().getString(R.string.generic_failure_desc));
                    if (dhVar.getReason().toLowerCase().contains("capping")) {
                        Map hashMap4 = new HashMap();
                        hashMap4.put("Cab category", this.f10542a.f10571d);
                        hashMap4.put("City name", this.f10542a.f10576i);
                        hashMap4.put("Screen", "Confirmation");
                        Localytics.tagEvent("Booking request rejected", hashMap4);
                    }
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.d.2 */
    class BaseBookingCabConfirmationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10543a;
        final /* synthetic */ BaseBookingCabConfirmationFragment f10544b;

        BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, AlertDialog alertDialog) {
            this.f10544b = baseBookingCabConfirmationFragment;
            this.f10543a = alertDialog;
        }

        public void onClick(View view) {
            this.f10543a.dismiss();
            BookingFragment bookingFragment = (BookingFragment) this.f10544b.getParentFragment();
            bookingFragment.m14379q();
            bookingFragment.m14376n();
        }
    }

    /* renamed from: com.olacabs.customer.ui.d.3 */
    class BaseBookingCabConfirmationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10545a;
        final /* synthetic */ BaseBookingCabConfirmationFragment f10546b;

        BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, AlertDialog alertDialog) {
            this.f10546b = baseBookingCabConfirmationFragment;
            this.f10545a = alertDialog;
        }

        public void onClick(View view) {
            this.f10546b.m14171h();
            this.f10545a.dismiss();
            if (this.f10546b.f10592y != null) {
                this.f10546b.f10592y.m13605b(3);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.d.4 */
    class BaseBookingCabConfirmationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10547a;
        final /* synthetic */ BaseBookingCabConfirmationFragment f10548b;

        BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, AlertDialog alertDialog) {
            this.f10548b = baseBookingCabConfirmationFragment;
            this.f10547a = alertDialog;
        }

        public void onClick(View view) {
            this.f10547a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.d.5 */
    class BaseBookingCabConfirmationFragment implements aj {
        final /* synthetic */ BaseBookingCabConfirmationFragment f10549a;

        BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment) {
            this.f10549a = baseBookingCabConfirmationFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to fetch surcharge", th);
            this.f10549a.f10579l.dismiss();
            this.f10549a.f10584q.setClickable(true);
            HashMap hashMap = new HashMap();
            if (Utils.m14924g(this.f10549a.f10576i)) {
                hashMap.put("Booking city", this.f10549a.f10576i);
            } else {
                hashMap.put("Booking city", "NA");
            }
            Sherlock.m13338a("Ins booked ride", "Failure", null, this.f10549a.getActivity().getString(R.string.generic_failure_desc), true, hashMap);
            this.f10549a.m14181b(this.f10549a.getActivity().getString(R.string.generic_failure_header), this.f10549a.getActivity().getString(R.string.generic_failure_desc));
        }

        public void onSuccess(Object obj) {
            this.f10549a.f10579l.dismiss();
            this.f10549a.f10584q.setClickable(true);
            this.f10549a.m14157b((af) obj);
        }
    }

    /* renamed from: com.olacabs.customer.ui.d.6 */
    class BaseBookingCabConfirmationFragment implements OnClickListener {
        final /* synthetic */ af f10550a;
        final /* synthetic */ AlertDialog f10551b;
        final /* synthetic */ BaseBookingCabConfirmationFragment f10552c;

        BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, af afVar, AlertDialog alertDialog) {
            this.f10552c = baseBookingCabConfirmationFragment;
            this.f10550a = afVar;
            this.f10551b = alertDialog;
        }

        public void onClick(View view) {
            this.f10552c.m14153a("Accepted", this.f10552c.f10585r, this.f10550a.getSurchargeAmount());
            this.f10551b.dismiss();
            this.f10552c.m14179a(this.f10552c.f10585r, this.f10550a.getSurchargeAmount());
            Leanplum.advanceTo(null);
        }
    }

    /* renamed from: com.olacabs.customer.ui.d.7 */
    class BaseBookingCabConfirmationFragment implements OnClickListener {
        final /* synthetic */ af f10553a;
        final /* synthetic */ AlertDialog f10554b;
        final /* synthetic */ BaseBookingCabConfirmationFragment f10555c;

        BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, af afVar, AlertDialog alertDialog) {
            this.f10555c = baseBookingCabConfirmationFragment;
            this.f10553a = afVar;
            this.f10554b = alertDialog;
        }

        public void onClick(View view) {
            this.f10555c.m14153a("Rejected", this.f10555c.f10585r, this.f10553a.getSurchargeAmount());
            Leanplum.advanceTo(null);
            this.f10554b.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.d.8 */
    class BaseBookingCabConfirmationFragment implements aj {
        final /* synthetic */ BaseBookingCabConfirmationFragment f10556a;

        BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment) {
            this.f10556a = baseBookingCabConfirmationFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to apply coupon", th);
            this.f10556a.f10586s.dismiss();
            this.f10556a.m14181b(this.f10556a.getActivity().getString(R.string.generic_failure_header), this.f10556a.getActivity().getString(R.string.generic_failure_desc));
        }

        public void onSuccess(Object obj) {
            this.f10556a.f10586s.dismiss();
            ae aeVar = (ae) obj;
            if (aeVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                this.f10556a.f10591x.setText(aeVar.getCode().toUpperCase());
                this.f10556a.f10587t = aeVar.getCode();
            } else if (aeVar.getStatus().equalsIgnoreCase("FAILURE") && this.f10556a.isAdded() && this.f10556a.getActivity() != null) {
                this.f10556a.m14181b(aeVar.getHeader(), aeVar.getText());
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.d.9 */
    class BaseBookingCabConfirmationFragment implements OnClickListener {
        final /* synthetic */ EditText f10557a;
        final /* synthetic */ View f10558b;
        final /* synthetic */ AlertDialog f10559c;
        final /* synthetic */ BaseBookingCabConfirmationFragment f10560d;

        BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, EditText editText, View view, AlertDialog alertDialog) {
            this.f10560d = baseBookingCabConfirmationFragment;
            this.f10557a = editText;
            this.f10558b = view;
            this.f10559c = alertDialog;
        }

        public void onClick(View view) {
            ((InputMethodManager) this.f10560d.f10592y.getSystemService("input_method")).hideSoftInputFromWindow(this.f10557a.getWindowToken(), 0);
            this.f10558b.setEnabled(true);
            this.f10559c.dismiss();
            if (Utils.m14909a(this.f10560d.f10592y.getApplicationContext())) {
                this.f10560d.m14152a(this.f10557a.getText().toString());
            } else {
                this.f10560d.m14181b(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.d.a */
    private class BaseBookingCabConfirmationFragment extends BaseAdapter {
        final /* synthetic */ BaseBookingCabConfirmationFragment f10561a;
        private ArrayList<ac> f10562b;
        private LayoutInflater f10563c;

        public BaseBookingCabConfirmationFragment(BaseBookingCabConfirmationFragment baseBookingCabConfirmationFragment, Context context, ArrayList<ac> arrayList) {
            this.f10561a = baseBookingCabConfirmationFragment;
            this.f10562b = arrayList;
            this.f10563c = LayoutInflater.from(baseBookingCabConfirmationFragment.f10592y);
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public int getCount() {
            return this.f10562b.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.f10563c.inflate(17367043, viewGroup, false);
            }
            ((TextView) view.findViewById(16908308)).setText(((ac) this.f10562b.get(i)).getDisplayName().trim());
            return view;
        }
    }

    private static void m14173i() {
        Factory factory = new Factory("BaseBookingCabConfirmationFragment.java", BaseBookingCabConfirmationFragment.class);
        f10564C = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.BaseBookingCabConfirmationFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 285);
        f10565D = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.BaseBookingCabConfirmationFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 313);
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
        BaseBookingCabConfirmationFragment.m14173i();
        f10566a = BaseBookingCabConfirmationFragment.class.getSimpleName();
    }

    public BaseBookingCabConfirmationFragment() {
        this.f10578k = false;
        this.f10589v = new BaseBookingCabConfirmationFragment(this);
        this.f10590w = new BaseBookingCabConfirmationFragment(this);
        this.f10581n = false;
        this.f10568B = new BaseBookingCabConfirmationFragment(this);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("d");
        try {
            TraceMachine.enterMethod(this._nr_trace, "d#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "d#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10564C, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            this.f10570c = arguments.getString("category_id");
            this.f10571d = arguments.getString("category_name");
            this.f10573f = arguments.getString("category_base_rate");
            this.f10574g = arguments.getString("category_rate_per_km");
            this.f10572e = arguments.getLong("pick_up_time");
            this.f10569b = arguments.getBoolean("is_ride_now");
            this.f10575h = arguments.getString("location_tag");
            this.f10576i = arguments.getString("current_city");
        }
        this.f10592y = (MainActivity) getActivity();
        this.f10579l = new ProgressDialog(this.f10592y, R.style.TransparentProgressDialog);
        this.f10579l.setIndeterminateDrawable(this.f10592y.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10579l.setCancelable(false);
        OlaApp olaApp = (OlaApp) getActivity().getApplication();
        this.f10580m = olaApp.m12878a();
        this.f10593z = olaApp.m12879b();
        this.f10567A = this.f10593z.m12867b(getActivity());
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "d#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "d#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10565D, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_booking_cab_conformation, viewGroup, false);
        this.f10591x = (TextView) inflate.findViewById(R.id.textView_coupon_code);
        this.f10582o = (TextView) inflate.findViewById(R.id.surcharge_amount);
        this.f10583p = (RelativeLayout) inflate.findViewById(R.id.surcharge_layout);
        this.f10584q = (Button) inflate.findViewById(R.id.button_ride_conform);
        inflate.findViewById(R.id.button_ride_cancel).setOnClickListener(this);
        inflate.findViewById(R.id.button_ride_conform).setOnClickListener(this);
        inflate.findViewById(R.id.item_get_Ride_estimate).setOnClickListener(this);
        inflate.findViewById(R.id.item_ride_card).setOnClickListener(this);
        inflate.findViewById(R.id.item_apply_coupon).setOnClickListener(this);
        inflate.findViewById(R.id.item_apply_coupon).setOnClickListener(this);
        if (this.f10569b) {
            inflate.findViewById(R.id.item_arrow_category_name).setVisibility(8);
            inflate.findViewById(R.id.item_arrow_pickuptime).setVisibility(8);
        } else {
            inflate.findViewById(R.id.item_arrow_category_name).setVisibility(0);
            inflate.findViewById(R.id.item_arrow_pickuptime).setVisibility(0);
            inflate.findViewById(R.id.layout_pickup_time).setOnClickListener(this);
            inflate.findViewById(R.id.layout_category_name).setOnClickListener(this);
        }
        m14177a(inflate);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.measure(0, 0);
        ((BookingFragment) getParentFragment()).m14347a(view.getMeasuredHeight());
    }

    private void m14163d() {
        m14177a(getView());
    }

    protected void m14177a(View view) {
        TextView textView = (TextView) view.findViewById(R.id.item_pick_up_time);
        ((TextView) view.findViewById(R.id.item_category_name)).setText(this.f10571d);
        this.f10591x.setText(this.f10587t == null ? "Apply Coupon" : this.f10587t);
        if (!this.f10569b) {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(this.f10572e);
            textView.setText(new SimpleDateFormat("HH:mm, dd MMM", Locale.getDefault()).format(instance.getTime()));
        } else if (this.f10572e > 0) {
            textView.setText(String.format(getResources().getQuantityString(R.plurals.minutes, (int) this.f10572e), new Object[]{Long.valueOf(this.f10572e)}));
        } else {
            textView.setText("Cab will be there in a few seconds...");
        }
    }

    private void m14156b(View view) {
        View inflate = LayoutInflater.from(this.f10592y).inflate(R.layout.view_dialog_apply_coupon, null, false);
        AlertDialog create = new Builder(this.f10592y).setView(inflate).create();
        create.setCancelable(false);
        ((EditText) inflate.findViewById(R.id.edit_text)).setFilters(new InputFilter[]{new LengthFilter(15), new AllCaps()});
        EditText editText = (EditText) inflate.findViewById(R.id.edit_text);
        ((TextView) inflate.findViewById(R.id.item_message)).setText("ENTER COUPON CODE TO APPLY");
        ((TextView) inflate.findViewById(R.id.button_action)).setText("Apply");
        inflate.findViewById(R.id.button_action).setOnClickListener(new BaseBookingCabConfirmationFragment(this, editText, view, create));
        inflate.findViewById(R.id.button_cancel).setOnClickListener(new AnonymousClass10(this, view, create));
        create.show();
    }

    private void m14154a(ArrayList<ac> arrayList) {
        AlertDialog create = new Builder(this.f10592y, 16973935).setTitle("Select your category").setAdapter(new BaseBookingCabConfirmationFragment(this, this.f10592y, arrayList), new AnonymousClass11(this, arrayList)).create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
    }

    private void m14166e() {
        View inflate = ((LayoutInflater) this.f10592y.getSystemService("layout_inflater")).inflate(R.layout.view_date_time_picker, null, false);
        DatePicker datePicker = (DatePicker) inflate.findViewById(R.id.date_picker);
        TimePicker timePicker = (TimePicker) inflate.findViewById(R.id.time_picker);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.f10572e);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(new Date());
        datePicker.setMinDate(instance2.getTime().getTime() - 1000);
        instance2.add(2, 1);
        datePicker.setMaxDate(instance2.getTime().getTime());
        datePicker.updateDate(instance.get(1), instance.get(2), instance.get(5));
        timePicker.setCurrentHour(Integer.valueOf(instance.get(11)));
        timePicker.setCurrentMinute(Integer.valueOf(instance.get(12)));
        AlertDialog create = new Builder(this.f10592y, 16973935).setView(inflate).setPositiveButton("Done", new AnonymousClass13(this, datePicker, timePicker)).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            final /* synthetic */ BaseBookingCabConfirmationFragment f10534a;

            {
                this.f10534a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
    }

    private void m14168f() {
        View inflate = ((LayoutInflater) this.f10592y.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10592y).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText("Invalid pickup time");
        ((TextView) inflate.findViewById(R.id.item_message)).setText("Please make sure that pickup time is after atleast an hour from now");
        inflate.findViewById(R.id.button_ok).setOnClickListener(new AnonymousClass14(this, create));
        create.show();
    }

    private void m14161c(String str, String str2) {
        View inflate = ((LayoutInflater) this.f10592y.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10592y).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new AnonymousClass15(this, create));
        create.show();
    }

    private void m14169g() {
        LatLng e = ((BookingFragment) getParentFragment()).m14367e();
        if (e != null) {
            Localytics.tagEvent("Ride estimate selected");
            Intent intent = new Intent(getActivity(), RideEstimateActivity.class);
            intent.putExtra("lattitude", e.f7554a);
            intent.putExtra("longitude", e.f7555b);
            intent.putExtra("categoryId", this.f10570c);
            if (this.f10569b) {
                intent.putExtra("pickup_mode", "NOW");
            } else {
                intent.putExtra("pickup_mode", "LATER");
            }
            intent.putExtra("pickUpTime", this.f10572e / 1000);
            intent.putExtra("coupon", Trace.NULL);
            startActivity(intent);
        }
    }

    public void m14176a() {
        Map hashMap = new HashMap();
        hashMap.put("Booking type", this.f10569b ? "Ride now" : "Ride later");
        hashMap.put("Cab category", this.f10571d);
        Localytics.tagEvent("Booking Cancelled", hashMap);
        BookingFragment bookingFragment = (BookingFragment) getParentFragment();
        bookingFragment.m14379q();
        bookingFragment.m14376n();
    }

    public void m14179a(String str, String str2) {
        String str3;
        this.f10579l.show();
        this.f10584q.setClickable(true);
        Map hashMap = new HashMap();
        hashMap.put("Booking type", this.f10569b ? "Ride now" : "Ride later");
        hashMap.put("Cab category", this.f10571d);
        hashMap.put("Discount State", this.f10587t == null ? "No coupon" : "Coupon applied");
        hashMap.put("City name", this.f10576i);
        Localytics.tagEvent("Booking Conformed", hashMap);
        Apsalar.event("Booking Conformed", new JSONObject(hashMap));
        this.f10567A.m12859a("fb_mobile_add_to_cart", hashMap);
        BookingFragment bookingFragment = (BookingFragment) getParentFragment();
        String str4 = null;
        if (!this.f10569b) {
            str4 = String.valueOf(this.f10572e / 1000);
        }
        if (bookingFragment.m14368f()) {
            str3 = "CUSTOM";
        } else {
            str3 = "CURRENT";
        }
        String d = bookingFragment.m14365d();
        if (TextUtils.isEmpty(d)) {
            d = this.f10580m.m13209c().getUserAddress();
        }
        this.f10580m.m13179a(new WeakReference(this.f10589v), this.f10570c, bookingFragment.m14367e(), this.f10569b, str4, str3, d, this.f10587t, this.f10580m.m13259o().isDeeplinked(), str, str2, f10566a);
    }

    public void onClick(View view) {
        OLog.m13313b("Clicked", new Object[0]);
        switch (view.getId()) {
            case R.id.button_ride_cancel:
                m14176a();
                Leanplum.advanceTo(null);
            case R.id.layout_category_name:
                m14154a(((BookingFragment) getParentFragment()).m14363c().m14245l());
            case R.id.layout_pickup_time:
                m14166e();
            case R.id.item_get_Ride_estimate:
                m14169g();
            case R.id.item_apply_coupon:
                view.setEnabled(false);
                m14156b(view);
            default:
        }
    }

    private void m14164d(String str, String str2) {
        View inflate = ((LayoutInflater) this.f10592y.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
        AlertDialog create = new Builder(this.f10592y).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        ((Button) inflate.findViewById(R.id.button_yes)).setText("Ok");
        ((Button) inflate.findViewById(R.id.button_no)).setText("Recharge");
        inflate.findViewById(R.id.button_yes).setOnClickListener(new BaseBookingCabConfirmationFragment(this, create));
        inflate.findViewById(R.id.button_no).setOnClickListener(new BaseBookingCabConfirmationFragment(this, create));
        create.show();
    }

    private void m14171h() {
        Map hashMap = new HashMap();
        hashMap.put("Recharge screen type", "Ride Confirmation");
        Localytics.tagEvent("Recharge hint clicked", hashMap);
    }

    protected void m14181b(String str, String str2) {
        View inflate = ((LayoutInflater) this.f10592y.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10592y).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new BaseBookingCabConfirmationFragment(this, create));
        create.show();
    }

    private void m14152a(String str) {
        BookingFragment bookingFragment = (BookingFragment) getParentFragment();
        this.f10586s = new ProgressDialog(this.f10592y, R.style.TransparentProgressDialog);
        this.f10586s.setIndeterminateDrawable(this.f10592y.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10586s.setCancelable(false);
        this.f10586s.show();
        this.f10580m.m13182a(new WeakReference(this.f10590w), str, this.f10570c, String.valueOf(this.f10572e / 1000), bookingFragment.m14367e(), f10566a);
    }

    public void m14180b() {
        this.f10581n = false;
    }

    private void m14157b(af afVar) {
        if (afVar.getStatus().equalsIgnoreCase("SUCCESS")) {
            if (afVar.isPeakSurchargeApplicable()) {
                this.f10578k = afVar.isPeakSurchargeApplicable();
                this.f10577j = afVar.getSurchargeAmount();
                Leanplum.advanceTo("surcharge popup shown");
                m14178a(afVar);
                return;
            }
            this.f10578k = false;
            this.f10577j = Trace.NULL;
            m14179a("None", "None");
        } else if (afVar.getStatus().equalsIgnoreCase("FAILURE")) {
            HashMap hashMap = new HashMap();
            if (Utils.m14924g(this.f10576i)) {
                hashMap.put("Booking city", this.f10576i);
            } else {
                hashMap.put("Booking city", "NA");
            }
            Sherlock.m13338a("Ins booked ride", "Failure", null, getActivity().getString(R.string.generic_failure_desc), true, hashMap);
            m14181b(getActivity().getString(R.string.generic_failure_header), getActivity().getString(R.string.generic_failure_desc));
        }
    }

    protected void m14182c() {
        this.f10579l.show();
        this.f10580m.m13183a(new WeakReference(this.f10568B), this.f10576i, this.f10570c, String.valueOf(this.f10572e / 1000), ((BookingFragment) getParentFragment()).m14367e(), this.f10569b, f10566a);
    }

    protected void m14178a(af afVar) {
        Sherlock.m13347c("Ins booked ride");
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.dynamic_surcharge_view, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(afVar.getHeader());
        ImageView imageView = (ImageView) inflate.findViewById(R.id.divider_image);
        if (this.f10570c.equalsIgnoreCase("compact")) {
            imageView.setImageResource(R.drawable.surcharge_popup_mini);
        } else if (this.f10570c.equalsIgnoreCase("luxury_sedan")) {
            imageView.setImageResource(R.drawable.surcharge_popup_prime);
        } else {
            imageView.setImageResource(R.drawable.surcharge_popup_sedan);
        }
        if (afVar.isSurchargeMultiplier()) {
            this.f10585r = "Multiplier";
            ((TextView) inflate.findViewById(R.id.main_figure)).setText(afVar.getSurchargeAmount() + "x");
        } else {
            this.f10585r = "Flat";
            ((TextView) inflate.findViewById(R.id.main_figure)).setText(getActivity().getString(R.string.rs_symbol) + afVar.getSurchargeAmount());
        }
        ((TextView) inflate.findViewById(R.id.text_below_figure)).setText(afVar.getText());
        ((TextView) inflate.findViewById(R.id.agree_text)).setText(afVar.getSurchargeAgreeText());
        TextView textView = (TextView) inflate.findViewById(R.id.disagree_text);
        textView.setPaintFlags(8);
        if (afVar.getSurchargeDisagreeText() != null) {
            textView.setText(afVar.getSurchargeDisagreeText());
        } else {
            textView.setText("TRY LATER");
        }
        inflate.findViewById(R.id.agree_text).setOnClickListener(new BaseBookingCabConfirmationFragment(this, afVar, create));
        inflate.findViewById(R.id.disagree_text).setOnClickListener(new BaseBookingCabConfirmationFragment(this, afVar, create));
        create.setCancelable(false);
        create.show();
    }

    private void m14153a(String str, String str2, String str3) {
        Map hashMap = new HashMap();
        hashMap.put("Type", str2);
        hashMap.put("Action Taken", str);
        hashMap.put("Value", str3);
        hashMap.put("Cab category", this.f10571d);
        Localytics.tagEvent("Surcharge Pop Up Shown", hashMap);
        hashMap.put("ab_type", this.f10580m.m13218d().isA() ? "A" : "B");
        if ("Accepted".equalsIgnoreCase("Accepted")) {
            Leanplum.track("booking confirm surcharge", hashMap);
        }
        Leanplum.track("Surcharge Pop Up Shown", hashMap);
    }
}
