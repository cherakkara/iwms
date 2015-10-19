package com.olacabs.customer.ui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
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
import com.olacabs.customer.app.ForceLogoutCommand;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.CabInfoRideSummaryResponse;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.az;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dl;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class RideSummaryActivity extends FragmentActivity implements OnClickListener, OnRatingBarChangeListener, TraceFieldInterface {
    private static final JoinPoint f10042Y = null;
    private static final JoinPoint f10043Z = null;
    public static final String f10044a;
    private static final JoinPoint aa = null;
    private TextView f10045A;
    private TextView f10046B;
    private LinearLayout f10047C;
    private DataManager f10048D;
    private SharedPreferences f10049E;
    private ViewStub f10050F;
    private ViewGroup f10051G;
    private ViewGroup f10052H;
    private ViewGroup f10053I;
    private View f10054J;
    private View f10055K;
    private LinearLayout f10056L;
    private RatingReasonsListAdapter f10057M;
    private RelativeLayout f10058N;
    private EditText f10059O;
    private View f10060P;
    private TextView f10061Q;
    private TextView f10062R;
    private View f10063S;
    private ImageView f10064T;
    private ImageView f10065U;
    private View f10066V;
    private boolean f10067W;
    private aj f10068X;
    protected ProgressDialog f10069b;
    boolean f10070c;
    ArrayList<Integer> f10071d;
    private aj f10072e;
    private int f10073f;
    private LinearLayout f10074g;
    private RatingBar f10075h;
    private String f10076i;
    private TextView f10077j;
    private String f10078k;
    private TextView f10079l;
    private CabInfoRideSummaryResponse f10080m;
    private ArrayList<String> f10081n;
    private AnimatorSet f10082o;
    private AnimatorSet f10083p;
    private boolean f10084q;
    private aj f10085r;
    private ImageView f10086s;
    private RelativeLayout f10087t;
    private RelativeLayout f10088u;
    private ListView f10089v;
    private TextView f10090w;
    private TextView f10091x;
    private TextView f10092y;
    private TextView f10093z;

    /* renamed from: com.olacabs.customer.ui.RideSummaryActivity.1 */
    class C08841 implements aj {
        final /* synthetic */ RideSummaryActivity f10032a;

        C08841(RideSummaryActivity rideSummaryActivity) {
            this.f10032a = rideSummaryActivity;
        }

        public void onFailure(Throwable th) {
            this.f10032a.f10069b.dismiss();
            OLog.m13310a("FeedBack failed", th);
            Sherlock.m13336a("Ins rating submitted", (VolleyError) th, th.getMessage(), false);
        }

        public void onSuccess(Object obj) {
            this.f10032a.f10069b.dismiss();
            az azVar = (az) obj;
            if (azVar != null) {
                try {
                    if (azVar.getStatus() != null) {
                        if (azVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                            Sherlock.m13345b("Ins rating submitted");
                            if (this.f10032a.f10075h.getRating() > 3.0f) {
                                this.f10032a.m13827d(this.f10032a.getString(R.string.rating_success_title), this.f10032a.getString(R.string.high_rating_success_message));
                                return;
                            } else {
                                this.f10032a.m13827d(this.f10032a.getString(R.string.rating_success_title), this.f10032a.getString(R.string.low_rating_success_message));
                                return;
                            }
                        }
                        Sherlock.m13336a("Ins rating submitted", null, azVar.getReason(), true);
                        this.f10032a.m13827d("Failure", azVar.getReason());
                        return;
                    }
                } catch (Exception e) {
                    return;
                }
            }
            this.f10032a.m13827d("Failure", this.f10032a.getString(R.string.generic_failure_desc));
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideSummaryActivity.2 */
    class C08852 implements aj {
        final /* synthetic */ RideSummaryActivity f10033a;

        C08852(RideSummaryActivity rideSummaryActivity) {
            this.f10033a = rideSummaryActivity;
        }

        public void onFailure(Throwable th) {
            this.f10033a.m13835h();
            OLog.m13310a("CabInfo failed", th);
        }

        public void onSuccess(Object obj) {
            this.f10033a.f10080m = (CabInfoRideSummaryResponse) obj;
            if (this.f10033a.f10080m != null && this.f10033a.f10080m.isForceLogout()) {
                new ForceLogoutCommand(true).m13270a(this.f10033a);
            }
            if (this.f10033a.f10080m != null && this.f10033a.f10080m.getStatus().equalsIgnoreCase("SUCCESS")) {
                if (this.f10033a.f10080m.getStateId() == AppState.TRIP_END.m12882a() && Utils.m14924g(this.f10033a.f10080m.getBookingId())) {
                    this.f10033a.m13825d();
                    this.f10033a.f10074g.setVisibility(0);
                    return;
                }
                this.f10033a.m13833g();
                this.f10033a.finish();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideSummaryActivity.3 */
    class C08863 implements OnGlobalLayoutListener {
        final /* synthetic */ RideSummaryActivity f10034a;

        C08863(RideSummaryActivity rideSummaryActivity) {
            this.f10034a = rideSummaryActivity;
        }

        public void onGlobalLayout() {
            this.f10034a.m13818b(this.f10034a.f10059O);
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideSummaryActivity.4 */
    class C08874 implements OnScrollListener {
        final /* synthetic */ RideSummaryActivity f10035a;

        C08874(RideSummaryActivity rideSummaryActivity) {
            this.f10035a = rideSummaryActivity;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i2 != 0) {
                int top = this.f10035a.f10051G.getTop();
                if (this.f10035a.f10070c) {
                    this.f10035a.f10056L.setTranslationY((float) Math.max(0, this.f10035a.f10060P.getTop() + top));
                    if (top + this.f10035a.f10060P.getTop() <= 0) {
                        this.f10035a.f10066V.setVisibility(0);
                        return;
                    } else {
                        this.f10035a.f10066V.setVisibility(8);
                        return;
                    }
                }
                int measuredHeight = this.f10035a.f10088u.getMeasuredHeight();
                if (this.f10035a.f10067W) {
                    this.f10035a.f10056L.setTranslationY((float) Math.max(0, this.f10035a.f10060P.getTop() + top));
                } else {
                    this.f10035a.f10056L.setTranslationY((float) Math.max(measuredHeight, this.f10035a.f10060P.getTop() + top));
                }
                if (measuredHeight >= top + this.f10035a.f10060P.getTop()) {
                    this.f10035a.f10066V.setVisibility(0);
                } else {
                    this.f10035a.f10066V.setVisibility(8);
                }
                top = this.f10035a.f10052H.getTop();
                measuredHeight = this.f10035a.f10055K.getTop();
                if (this.f10035a.f10067W) {
                    this.f10035a.f10088u.setTranslationY((float) (measuredHeight + top));
                } else {
                    this.f10035a.f10088u.setTranslationY((float) Math.max(0, measuredHeight + top));
                }
                this.f10035a.f10086s.setTranslationY((float) (top + this.f10035a.f10077j.getBottom()));
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideSummaryActivity.5 */
    class C08885 implements aj {
        final /* synthetic */ RideSummaryActivity f10036a;

        C08885(RideSummaryActivity rideSummaryActivity) {
            this.f10036a = rideSummaryActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Fetching Driver Image failed", th);
            this.f10036a.f10064T.setImageResource(R.drawable.driver_image_without_border);
        }

        public void onSuccess(Object obj) {
            this.f10036a.f10064T.setImageDrawable(this.f10036a.m13806a((Bitmap) obj));
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideSummaryActivity.6 */
    class C08896 implements OnClickListener {
        final /* synthetic */ AlertDialog f10037a;
        final /* synthetic */ RideSummaryActivity f10038b;

        C08896(RideSummaryActivity rideSummaryActivity, AlertDialog alertDialog) {
            this.f10038b = rideSummaryActivity;
            this.f10037a = alertDialog;
        }

        public void onClick(View view) {
            this.f10037a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.RideSummaryActivity.7 */
    class C08907 implements OnClickListener {
        final /* synthetic */ AlertDialog f10039a;
        final /* synthetic */ String f10040b;
        final /* synthetic */ RideSummaryActivity f10041c;

        C08907(RideSummaryActivity rideSummaryActivity, AlertDialog alertDialog, String str) {
            this.f10041c = rideSummaryActivity;
            this.f10039a = alertDialog;
            this.f10040b = str;
        }

        public void onClick(View view) {
            this.f10039a.dismiss();
            if (!"Failure".equalsIgnoreCase(this.f10040b)) {
                if (this.f10041c.getIntent().getBooleanExtra(Constants.ARG_FLAG_MAIN_ACTIVITY, false)) {
                    this.f10041c.m13833g();
                }
                this.f10041c.finish();
            }
        }
    }

    private static void m13837i() {
        Factory factory = new Factory("RideSummaryActivity.java", RideSummaryActivity.class);
        f10042Y = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.RideSummaryActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 194);
        f10043Z = factory.m15070a("method-execution", factory.m15071a("4", "onPause", "com.olacabs.customer.ui.RideSummaryActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 809);
        aa = factory.m15070a("method-execution", factory.m15071a("4", "onResume", "com.olacabs.customer.ui.RideSummaryActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 820);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    public RideSummaryActivity() {
        this.f10072e = new C08841(this);
        this.f10073f = 0;
        this.f10084q = false;
        this.f10085r = new C08852(this);
        this.f10070c = false;
        this.f10067W = false;
        this.f10071d = new ArrayList();
        this.f10068X = new C08885(this);
    }

    static {
        m13837i();
        f10044a = RideSummaryActivity.class.getSimpleName();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("RideSummaryActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "RideSummaryActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "RideSummaryActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f10042Y, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_ride_summary);
        m13810a();
        this.f10059O.getViewTreeObserver().addOnGlobalLayoutListener(new C08863(this));
        this.f10086s.setOnClickListener(this);
        this.f10081n = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.driver_rating_reasons)));
        this.f10089v.setOnScrollListener(new C08874(this));
        this.f10048D = ((OlaApp) getApplication()).m12878a();
        Localytics.tagScreen("Ride Summary");
        this.f10049E = PreferenceManager.getDefaultSharedPreferences(this);
        TraceMachine.exitMethod();
    }

    private void m13810a() {
        this.f10074g = (LinearLayout) findViewById(R.id.main_layout);
        this.f10079l = (TextView) findViewById(R.id.summary_rating_submit);
        this.f10079l.setOnClickListener(this);
        this.f10090w = (TextView) findViewById(R.id.payable_amount);
        this.f10089v = (ListView) findViewById(R.id.ride_summary_list);
        this.f10088u = (RelativeLayout) findViewById(R.id.payable_bill_layout);
        this.f10050F = (ViewStub) findViewById(R.id.stub_ridesummary_error);
        this.f10054J = findViewById(R.id.payable_amount_edge);
        this.f10056L = (LinearLayout) findViewById(R.id.ratingbar_container);
        this.f10075h = (RatingBar) findViewById(R.id.ratingBar);
        this.f10066V = findViewById(R.id.rating_bar_shadow);
        Drawable bitmapDrawable = new BitmapDrawable(BitmapFactoryInstrumentation.decodeResource(getResources(), R.drawable.summary_edge_pattern));
        bitmapDrawable.setTileModeX(TileMode.REPEAT);
        bitmapDrawable.setDither(true);
        this.f10054J.setBackgroundDrawable(bitmapDrawable);
        LayoutInflater layoutInflater = getLayoutInflater();
        this.f10052H = (ViewGroup) layoutInflater.inflate(R.layout.view_ride_summary_list_header, this.f10089v, false);
        this.f10047C = (LinearLayout) this.f10052H.findViewById(R.id.kp_text_layout);
        this.f10093z = (TextView) this.f10052H.findViewById(R.id.kp_thanks_message_text);
        this.f10045A = (TextView) this.f10052H.findViewById(R.id.kp_thanks_message_text1);
        this.f10046B = (TextView) this.f10052H.findViewById(R.id.kp_thanks_message_text3);
        this.f10077j = (TextView) this.f10052H.findViewById(R.id.total_bill_money_text);
        this.f10092y = (TextView) this.f10052H.findViewById(R.id.discount);
        this.f10091x = (TextView) this.f10052H.findViewById(R.id.ola_money_deducted);
        this.f10087t = (RelativeLayout) this.f10052H.findViewById(R.id.bill_summary_layout);
        this.f10055K = this.f10052H.findViewById(R.id.header_reference_view);
        this.f10058N = (RelativeLayout) this.f10052H.findViewById(R.id.cabs_bill_layout);
        this.f10086s = (ImageView) findViewById(R.id.summary_show_more);
        this.f10063S = this.f10052H.findViewById(R.id.auto_kp_pattern_view);
        this.f10063S.setBackgroundDrawable(bitmapDrawable);
        this.f10051G = (ViewGroup) layoutInflater.inflate(R.layout.view_rate_ride_list_header, this.f10089v, false);
        this.f10060P = this.f10051G.findViewById(R.id.rating_place_holder_view);
        this.f10061Q = (TextView) this.f10051G.findViewById(R.id.ride_rate_text_what_went_wrong);
        this.f10062R = (TextView) this.f10051G.findViewById(R.id.ride_rate_text);
        this.f10064T = (ImageView) this.f10051G.findViewById(R.id.driver_image);
        this.f10065U = (ImageView) this.f10051G.findViewById(R.id.rating_image);
        this.f10089v.addHeaderView(this.f10052H, null, false);
        this.f10089v.addHeaderView(this.f10051G, null, false);
        this.f10053I = (ViewGroup) layoutInflater.inflate(R.layout.view_rate_ride_list_footer, this.f10089v, false);
        this.f10089v.addFooterView(this.f10053I);
        this.f10059O = (EditText) this.f10053I.findViewById(R.id.leaveCommentText);
        this.f10059O.setVisibility(8);
        this.f10082o = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flip_up);
        this.f10083p = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flip_down);
    }

    private void m13817b() {
        if (this.f10080m.getBookingId() != null && this.f10049E.getBoolean(this.f10080m.getBookingId() + Constants.USER_RATED, false)) {
            String bookingId = this.f10080m.getBookingId();
            if (this.f10049E.getInt(bookingId + Constants.USER_RATING, 0) > 0) {
                this.f10049E.edit().remove(bookingId + Constants.USER_RATING).commit();
            }
            if (this.f10049E.getString(bookingId + Constants.USER_FEEDBACK, null) != null) {
                this.f10049E.edit().remove(bookingId + Constants.USER_FEEDBACK).commit();
            }
            if (this.f10049E.getString(bookingId + Constants.USER_OPTIONS, null) != null) {
                this.f10049E.edit().remove(bookingId + Constants.USER_OPTIONS).commit();
            }
            if (this.f10049E.getString(bookingId + Constants.DRIVER_IMAGE_URL, null) != null) {
                this.f10049E.edit().remove(bookingId + Constants.DRIVER_IMAGE_URL).commit();
            }
            this.f10049E.edit().remove(bookingId + Constants.USER_RATED).commit();
        }
    }

    private void m13823c() {
        if (this.f10080m.getBookingId() == null || !this.f10049E.getBoolean(this.f10080m.getBookingId() + Constants.USER_RATED, false)) {
            this.f10062R.setText(R.string.summary_Help_us_serve);
        } else {
            this.f10084q = true;
            String bookingId = this.f10080m.getBookingId();
            if (this.f10049E.getString(bookingId + Constants.USER_FEEDBACK, null) != null) {
                this.f10059O.setText(this.f10049E.getString(bookingId + Constants.USER_FEEDBACK, Trace.NULL));
            }
            if (this.f10049E.getString(bookingId + Constants.USER_OPTIONS, null) == null || this.f10049E.getString(bookingId + Constants.USER_OPTIONS, null).length() <= 0) {
                this.f10071d.clear();
            } else {
                String string = this.f10049E.getString(bookingId + Constants.USER_OPTIONS, Trace.NULL);
                for (int i = 0; i < string.length(); i++) {
                    int parseInt = Integer.parseInt(String.valueOf(string.charAt(i)));
                    if (this.f10070c) {
                        this.f10081n = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.auto_driver_rating_reasons)));
                    } else {
                        this.f10081n = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.driver_rating_reasons)));
                    }
                    if (this.f10081n.size() > parseInt) {
                        this.f10071d.add(Integer.valueOf(parseInt));
                    }
                }
            }
            this.f10079l.setVisibility(0);
            this.f10079l.setText(R.string.rating_submit_ok);
            if (this.f10049E.getInt(bookingId + Constants.USER_RATING, 0) > 0) {
                this.f10062R.setText(R.string.summary_you_rated);
                this.f10075h.setRating((float) this.f10049E.getInt(bookingId + Constants.USER_RATING, 0));
                this.f10059O.setVisibility(0);
            }
            this.f10084q = false;
        }
        if (this.f10080m.getBookingId() == null || this.f10049E.getString(this.f10080m.getBookingId() + Constants.DRIVER_IMAGE_URL_SHARED_PREF, null) == null) {
            this.f10064T.setImageResource(R.drawable.driver_image_without_border);
        } else {
            m13814a(this.f10049E.getString(this.f10080m.getBookingId() + Constants.DRIVER_IMAGE_URL_SHARED_PREF, Trace.NULL));
        }
    }

    private void m13814a(String str) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.driver_image_size);
        this.f10048D.m13176a(new WeakReference(this.f10068X), str, dimensionPixelSize, dimensionPixelSize, Config.RGB_565, f10044a);
    }

    private RoundedBitmapDrawable m13806a(Bitmap bitmap) {
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        create.setCornerRadius((float) bitmap.getWidth());
        return create;
    }

    public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
        if (ratingBar.getRating() == 0.0f) {
            ratingBar.setRating(br.DEFAULT_BACKOFF_MULT);
            return;
        }
        if (!(this.f10057M.m14894b() == null || this.f10084q)) {
            this.f10071d.clear();
            this.f10071d = this.f10057M.m14894b();
        }
        if (ratingBar.getRating() >= 4.0f || "delivery".equalsIgnoreCase(this.f10080m.getServiceType())) {
            if (this.f10073f < 4 && this.f10073f != 0 && this.f10059O.getText().toString().length() > 0) {
                this.f10059O.setText(Trace.NULL);
            }
            this.f10081n.clear();
            this.f10057M.m14893a(this.f10081n, this.f10071d, false);
            this.f10057M.notifyDataSetChanged();
            this.f10061Q.setVisibility(8);
            this.f10089v.smoothScrollBy(this.f10089v.getBottom(), Constants.MILLIS_IN_A_SECOND);
        } else {
            if (this.f10073f > 3 && this.f10059O.getText().toString().length() > 0) {
                this.f10059O.setText(Trace.NULL);
            }
            if (this.f10061Q.getVisibility() == 8) {
                Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rating_list_down);
                loadAnimation.setDuration(300);
                this.f10061Q.startAnimation(loadAnimation);
                this.f10061Q.setVisibility(0);
            }
            this.f10081n.clear();
            if (this.f10070c) {
                this.f10081n = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.auto_driver_rating_reasons)));
            } else {
                this.f10081n = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.driver_rating_reasons)));
            }
            this.f10057M.m14893a(this.f10081n, this.f10071d, false);
            this.f10057M.notifyDataSetChanged();
            if (this.f10070c && this.f10059O.getVisibility() == 8) {
                this.f10089v.smoothScrollBy(this.f10051G.getTop(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
            } else if (this.f10059O.getVisibility() == 8) {
                this.f10089v.smoothScrollBy(this.f10051G.getTop() - this.f10088u.getMeasuredHeight(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
            }
        }
        this.f10059O.setVisibility(0);
        this.f10079l.setVisibility(0);
        this.f10079l.setText(R.string.rating_summary_submit);
        if (!this.f10084q) {
            if (this.f10065U.getVisibility() == 8) {
                this.f10082o.setTarget(this.f10064T);
                this.f10083p.setTarget(this.f10065U);
                this.f10082o.start();
                this.f10083p.start();
            }
            int measuredWidth = this.f10062R.getMeasuredWidth();
            switch ((int) ratingBar.getRating()) {
                case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    this.f10065U.setImageResource(R.drawable.rating_horrible);
                    this.f10065U.setVisibility(0);
                    this.f10062R.setText(getResources().getString(R.string.rating_one_star_text));
                    break;
                case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    this.f10065U.setImageResource(R.drawable.rating_bad);
                    this.f10065U.setVisibility(0);
                    this.f10062R.setText(getResources().getString(R.string.rating_two_star_text));
                    break;
                case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    this.f10065U.setImageResource(R.drawable.rating_okok);
                    this.f10065U.setVisibility(0);
                    this.f10062R.setText(getResources().getString(R.string.rating_three_star_text));
                    break;
                case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    this.f10065U.setImageResource(R.drawable.rating_good);
                    this.f10065U.setVisibility(0);
                    this.f10062R.setText(getResources().getString(R.string.rating_four_star_text));
                    break;
                case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    this.f10065U.setImageResource(R.drawable.rating_awesome);
                    this.f10065U.setVisibility(0);
                    this.f10062R.setText(getResources().getString(R.string.rating_five_star_text));
                    break;
            }
            this.f10062R.measure(0, 0);
            m13847a(this.f10062R, measuredWidth, this.f10062R.getMeasuredWidth());
        }
        this.f10073f = (int) ratingBar.getRating();
    }

    public void m13847a(View view, int i, int i2) {
        Animation scaleAnimation = new ScaleAnimation(((float) i) / ((float) i2), br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, 1, 0.5f, 1, 0.0f);
        scaleAnimation.setInterpolator(this, 17432586);
        scaleAnimation.setDuration(120);
        view.startAnimation(scaleAnimation);
    }

    private void m13815a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("Rating", str2);
        hashMap.put("Comment state", str);
        Localytics.tagEvent("Rating Submitted", hashMap);
    }

    private void m13820b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("Reason", str);
        Localytics.tagEvent("1/2 star selected", hashMap);
    }

    private void m13821b(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("Change in rating", str2);
        hashMap.put("Rating", str);
        Localytics.tagEvent("Driver Rating submitted clicked", hashMap);
    }

    private void m13825d() {
        if (this.f10057M == null) {
            this.f10057M = new RatingReasonsListAdapter(this, new ArrayList(), new ArrayList());
            this.f10089v.setAdapter(this.f10057M);
        } else {
            this.f10059O.setVisibility(0);
        }
        if ("kp".equalsIgnoreCase(this.f10080m.getServiceType()) || "auto".equalsIgnoreCase(this.f10080m.getServiceType())) {
            CharSequence subDisplayText;
            this.f10070c = true;
            this.f10047C.setVisibility(0);
            this.f10058N.setVisibility(8);
            this.f10055K.setVisibility(8);
            this.f10088u.setVisibility(8);
            this.f10086s.setVisibility(8);
            this.f10093z.setText(this.f10080m.getDisplayText() != null ? this.f10080m.getDisplayText() : getResources().getString(R.string.rating_thankyou));
            this.f10045A.setText(this.f10080m.getMiddleDisplayText() != null ? this.f10080m.getMiddleDisplayText() : getResources().getString(R.string.rating_we_hope_message));
            TextView textView = this.f10046B;
            if (this.f10080m.getSubDisplayText() != null) {
                subDisplayText = this.f10080m.getSubDisplayText();
            } else {
                subDisplayText = "Note: Ola Money, discounts and offers not applicable.";
            }
            textView.setText(subDisplayText);
        } else if ("delivery".equalsIgnoreCase(this.f10080m.getServiceType())) {
            this.f10047C.setVisibility(0);
            this.f10058N.setVisibility(8);
            this.f10055K.setVisibility(8);
            this.f10088u.setVisibility(8);
            this.f10086s.setVisibility(8);
            this.f10093z.setText(this.f10080m.getDisplayText() != null ? this.f10080m.getDisplayText() : getResources().getString(R.string.ride_summary_thanks_message));
            this.f10045A.setText(this.f10080m.getMiddleDisplayText() != null ? this.f10080m.getMiddleDisplayText() : "Please settle the fare in cash\nwith your driver.");
            this.f10046B.setText(this.f10080m.getSubDisplayText() != null ? this.f10080m.getSubDisplayText() : Trace.NULL);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(0, 100, 0, 100);
            this.f10047C.setLayoutParams(layoutParams);
        } else {
            dl tripInfo = this.f10080m.getTripInfo();
            m13811a(tripInfo);
            Editor edit = this.f10049E.edit();
            edit.putInt(Constants.PREF_OLA_MONEY_BALANCE, this.f10080m.getOlaMoneyBalance());
            edit.apply();
            this.f10047C.setVisibility(8);
            this.f10077j.setText(getResources().getString(R.string.rs_symbol) + ((int) tripInfo.getAmount()));
            this.f10090w.setText(getResources().getString(R.string.rs_symbol) + tripInfo.getPayableAmount());
            this.f10091x.setText(getResources().getString(R.string.rs_symbol) + tripInfo.getAdvance());
            this.f10092y.setText(getResources().getString(R.string.rs_symbol) + tripInfo.getDiscount());
        }
        this.f10075h.setOnRatingBarChangeListener(this);
        m13823c();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.summary_rating_submit:
                if (this.f10075h.getRating() == 0.0f) {
                    m13824c(getString(R.string.submit_ratig_header), getString(R.string.rate_driver_desc));
                } else {
                    int i;
                    Sherlock.m13334a("Ins rating submitted");
                    this.f10078k = Trace.NULL;
                    if (this.f10075h.getRating() < 4.0f) {
                        if (this.f10070c) {
                            this.f10081n = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.auto_driver_rating_reasons)));
                        } else {
                            this.f10081n = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.driver_rating_reasons)));
                        }
                        if (this.f10059O.getVisibility() == 0 && this.f10057M.m14894b().size() > 0) {
                            for (i = 0; i < this.f10057M.m14894b().size(); i++) {
                                if (this.f10078k.length() <= 0) {
                                    this.f10078k = (String) this.f10081n.get(((Integer) this.f10057M.m14894b().get(i)).intValue());
                                } else {
                                    this.f10078k += "," + ((String) this.f10081n.get(((Integer) this.f10057M.m14894b().get(i)).intValue()));
                                }
                            }
                        } else if (this.f10071d.size() > 0) {
                            for (i = 0; i < this.f10071d.size(); i++) {
                                if (this.f10078k.length() <= 0) {
                                    this.f10078k = (String) this.f10081n.get(((Integer) this.f10071d.get(i)).intValue());
                                } else {
                                    this.f10078k += "," + ((String) this.f10081n.get(((Integer) this.f10071d.get(i)).intValue()));
                                }
                            }
                        }
                    }
                    this.f10076i = this.f10059O.getText().toString().trim();
                    if (Utils.m14909a(getApplicationContext())) {
                        String str;
                        if (this.f10076i.equalsIgnoreCase(Trace.NULL)) {
                            str = "no comment";
                        } else {
                            str = "comment provided";
                        }
                        m13815a(str, ((int) this.f10075h.getRating()) + Trace.NULL);
                        if (this.f10075h.getRating() < 4.0f && this.f10059O.getVisibility() == 0 && this.f10057M.m14894b().size() > 0 && this.f10057M.m14894b().size() > 0) {
                            for (i = 0; i < this.f10057M.m14894b().size(); i++) {
                                m13820b((String) this.f10081n.get(((Integer) this.f10057M.m14894b().get(i)).intValue()));
                            }
                        } else if (this.f10075h.getRating() < 4.0f && this.f10071d.size() > 0) {
                            for (i = 0; i < this.f10071d.size(); i++) {
                                m13820b((String) this.f10081n.get(((Integer) this.f10071d.get(i)).intValue()));
                            }
                        }
                        if (this.f10080m.getBookingId() == null || !this.f10049E.getBoolean(this.f10080m.getBookingId() + Constants.USER_RATED, false) || this.f10049E.getInt(this.f10080m.getBookingId() + Constants.USER_RATING, 0) <= 0) {
                            str = "Rated in the End";
                        } else if (((int) this.f10075h.getRating()) == this.f10049E.getInt(this.f10080m.getBookingId() + Constants.USER_RATING, 0)) {
                            str = "Rated and didn\u2019t change";
                        } else {
                            str = "Rated and changed";
                        }
                        m13821b(((int) this.f10075h.getRating()) + Trace.NULL, str);
                        m13830f();
                        this.f10048D.m13217c(new WeakReference(this.f10072e), this.f10080m.getServiceType(), this.f10080m.getBookingId(), String.valueOf(this.f10075h.getRating()), this.f10076i, this.f10078k, f10044a);
                    } else {
                        Sherlock.m13336a("Ins rating submitted", null, Constants.NO_NETWORK_TEXT, true);
                        m13824c(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
                    }
                }
                m13817b();
            case R.id.summary_show_more:
                m13829e();
            default:
        }
    }

    private void m13829e() {
        if (this.f10087t.getVisibility() == 0) {
            this.f10087t.setVisibility(8);
            this.f10086s.setSelected(false);
            return;
        }
        this.f10087t.setVisibility(0);
        this.f10086s.setSelected(true);
    }

    private void m13824c(String str, String str2) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08896(this, create));
        create.show();
    }

    private void m13830f() {
        this.f10069b = new ProgressDialog(this, R.style.TransparentnobgProgressDialog);
        this.f10069b.setIndeterminateDrawable(getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10069b.setCancelable(false);
        this.f10069b.show();
    }

    private void m13827d(String str, String str2) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new C08907(this, create, str));
        create.show();
    }

    private void m13833g() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("isNormal", true);
        intent.putExtra("fromRideSummary", true);
        startActivity(intent);
    }

    public void onBackPressed() {
    }

    protected void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f10043Z, (Object) this, (Object) this));
        super.onPause();
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        this.f10048D.m13169a(f10044a);
    }

    protected void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(aa, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            if (Utils.m14909a(getApplicationContext())) {
                this.f10048D.m13235g(new WeakReference(this.f10085r), f10044a);
            } else {
                m13824c(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            }
            ActivityTraceAspect.m12823a().m12828d(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    private void m13811a(dl dlVar) {
        Object obj = "CASH";
        int payableAmount = dlVar.getPayableAmount();
        double advance = dlVar.getAdvance();
        if (payableAmount > 0 && advance > 0.0d) {
            obj = "BOTH";
        } else if (payableAmount > 0) {
            obj = "CASH";
        } else if (advance > 0.0d) {
            obj = "OLA_MONEY";
        }
        Map hashMap = new HashMap();
        hashMap.put("Payment mode", obj);
        Localytics.tagEvent("Ride Payment Mode", hashMap);
    }

    private void m13835h() {
        this.f10050F.setVisibility(0);
    }

    private boolean m13816a(View view) {
        WindowManager windowManager = (WindowManager) getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return this.f10089v.getRootView().getHeight() - this.f10089v.getHeight() > point.y / 3;
    }

    private void m13818b(View view) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(this.f10059O.getLayoutParams());
        if (m13816a(this.f10059O)) {
            if (!this.f10067W) {
                layoutParams.setMargins(0, 0, 0, 0);
                this.f10059O.setLayoutParams(layoutParams);
            }
            this.f10067W = true;
            return;
        }
        if (this.f10067W) {
            layoutParams.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.rate_ride_leave_comment_height));
            this.f10059O.setLayoutParams(layoutParams);
        }
        this.f10067W = false;
    }
}
