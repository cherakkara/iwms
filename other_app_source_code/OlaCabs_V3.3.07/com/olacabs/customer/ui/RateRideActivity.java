package com.olacabs.customer.ui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
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
import android.widget.TextView;
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
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class RateRideActivity extends FragmentActivity implements OnClickListener, OnRatingBarChangeListener, TraceFieldInterface {
    private static final JoinPoint f9858F = null;
    private static final JoinPoint f9859G = null;
    private static final JoinPoint f9860H = null;
    public static final String f9861a;
    private TextView f9862A;
    private boolean f9863B;
    private View f9864C;
    private int f9865D;
    private aj f9866E;
    private ListView f9867b;
    private TextView f9868c;
    private TextView f9869d;
    private EditText f9870e;
    private ImageView f9871f;
    private ImageView f9872g;
    private ImageView f9873h;
    private ArrayList<String> f9874i;
    private ArrayList<Integer> f9875j;
    private AnimatorSet f9876k;
    private AnimatorSet f9877l;
    private LinearLayout f9878m;
    private SharedPreferences f9879n;
    private ViewGroup f9880o;
    private ViewGroup f9881p;
    private ViewGroup f9882q;
    private RatingBar f9883r;
    private String f9884s;
    private String f9885t;
    private String f9886u;
    private int f9887v;
    private RatingReasonsListAdapter f9888w;
    private String f9889x;
    private DataManager f9890y;
    private View f9891z;

    /* renamed from: com.olacabs.customer.ui.RateRideActivity.1 */
    class C08641 implements aj {
        final /* synthetic */ RateRideActivity f9856a;

        C08641(RateRideActivity rateRideActivity) {
            this.f9856a = rateRideActivity;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Fetching Driver Image failed", th);
            this.f9856a.f9871f.setImageResource(R.drawable.driver_image_without_border);
        }

        public void onSuccess(Object obj) {
            this.f9856a.f9871f.setImageDrawable(this.f9856a.m13711a((Bitmap) obj));
        }
    }

    /* renamed from: com.olacabs.customer.ui.RateRideActivity.2 */
    class C08652 implements OnScrollListener {
        final /* synthetic */ RateRideActivity f9857a;

        C08652(RateRideActivity rateRideActivity) {
            this.f9857a = rateRideActivity;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i2 != 0) {
                int top = this.f9857a.f9880o.getTop();
                this.f9857a.f9878m.setTranslationY((float) Math.max(0, this.f9857a.f9891z.getTop() + top));
                if (top + this.f9857a.f9891z.getTop() <= 0) {
                    this.f9857a.f9864C.setVisibility(0);
                } else {
                    this.f9857a.f9864C.setVisibility(8);
                }
            }
        }
    }

    private static void m13717b() {
        Factory factory = new Factory("RateRideActivity.java", RateRideActivity.class);
        f9858F = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.RateRideActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 90);
        f9859G = factory.m15070a("method-execution", factory.m15071a("4", "onPause", "com.olacabs.customer.ui.RateRideActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 177);
        f9860H = factory.m15070a("method-execution", factory.m15071a("4", "onResume", "com.olacabs.customer.ui.RateRideActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 190);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public RateRideActivity() {
        this.f9889x = Trace.NULL;
        this.f9863B = false;
        this.f9865D = 0;
        this.f9866E = new C08641(this);
    }

    static {
        m13717b();
        f9861a = RateRideActivity.class.getSimpleName();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("RateRideActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "RateRideActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "RateRideActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9858F, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_rate_ride);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f9884s = extras.getString(Constants.ARG_BOOKING_ID);
            this.f9885t = extras.getString(Constants.DRIVER_IMAGE_URL);
            this.f9886u = extras.getString(Constants.ARG_CAR_CATEGORY_ID);
        }
        this.f9867b = (ListView) findViewById(R.id.rate_ride_list);
        this.f9867b.setChoiceMode(2);
        m13714a();
        this.f9867b.addHeaderView(this.f9880o, null, false);
        this.f9867b.addFooterView(this.f9882q, null, false);
        this.f9867b.addFooterView(this.f9881p, null, false);
        this.f9875j = new ArrayList();
        this.f9888w = new RatingReasonsListAdapter(this, new ArrayList(), new ArrayList());
        this.f9867b.setAdapter(this.f9888w);
        this.f9867b.setOnScrollListener(new C08652(this));
        this.f9890y = ((OlaApp) getApplication()).m12878a();
        if (this.f9885t.equalsIgnoreCase("na")) {
            this.f9871f.setImageResource(R.drawable.driver_image_without_border);
        } else {
            m13715a(this.f9885t);
        }
        this.f9879n = PreferenceManager.getDefaultSharedPreferences(this);
        Localytics.tagScreen("Track Ride rate Ride screen");
        TraceMachine.exitMethod();
    }

    private void m13714a() {
        LayoutInflater layoutInflater = getLayoutInflater();
        this.f9880o = (ViewGroup) layoutInflater.inflate(R.layout.view_rate_ride_list_header, this.f9867b, false);
        this.f9881p = (ViewGroup) layoutInflater.inflate(R.layout.view_rate_ride_list_footer, this.f9867b, false);
        this.f9882q = (ViewGroup) layoutInflater.inflate(R.layout.view_rate_ride_image_footer, this.f9867b, false);
        this.f9881p.setVisibility(8);
        this.f9878m = (LinearLayout) findViewById(R.id.ratingbar_container);
        this.f9883r = (RatingBar) findViewById(R.id.ratingBar);
        this.f9883r.setOnRatingBarChangeListener(this);
        this.f9864C = findViewById(R.id.rating_bar_shadow);
        this.f9870e = (EditText) this.f9881p.findViewById(R.id.leaveCommentText);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(this.f9870e.getLayoutParams());
        layoutParams.setMargins(0, 0, 0, 0);
        this.f9870e.setLayoutParams(layoutParams);
        this.f9870e.setOnClickListener(this);
        this.f9873h = (ImageView) findViewById(R.id.backImageView);
        this.f9873h.setOnClickListener(this);
        this.f9869d = (TextView) findViewById(R.id.summary_rating_submit);
        this.f9869d.setOnClickListener(this);
        this.f9891z = this.f9880o.findViewById(R.id.rating_place_holder_view);
        this.f9862A = (TextView) this.f9880o.findViewById(R.id.ride_rate_text_what_went_wrong);
        this.f9876k = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flip_up);
        this.f9877l = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flip_down);
        this.f9871f = (ImageView) this.f9880o.findViewById(R.id.driver_image);
        this.f9872g = (ImageView) this.f9880o.findViewById(R.id.rating_image);
        this.f9868c = (TextView) this.f9880o.findViewById(R.id.ride_rate_text);
    }

    protected void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9859G, (Object) this, (Object) this));
        super.onPause();
        overridePendingTransition(R.anim.noanimation, R.anim.slidedown);
    }

    private RoundedBitmapDrawable m13711a(Bitmap bitmap) {
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        create.setCornerRadius((float) bitmap.getWidth());
        return create;
    }

    protected void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f9860H, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            this.f9863B = this.f9879n.getBoolean(this.f9884s + Constants.USER_RATED, false);
            if (this.f9863B) {
                int i = this.f9879n.getInt(this.f9884s + Constants.USER_RATING, -1);
                String string = this.f9879n.getString(this.f9884s + Constants.USER_OPTIONS, "no");
                CharSequence string2 = this.f9879n.getString(this.f9884s + Constants.USER_FEEDBACK, "no");
                if (!string2.equalsIgnoreCase("no")) {
                    this.f9870e.setText(string2);
                }
                if (string.equalsIgnoreCase("no") || string.length() <= 0) {
                    this.f9875j.clear();
                } else {
                    for (int i2 = 0; i2 < string.length(); i2++) {
                        this.f9875j.add(Integer.valueOf(Integer.parseInt(String.valueOf(string.charAt(i2)))));
                    }
                }
                if (i > 0) {
                    this.f9883r.setRating((float) i);
                }
                this.f9863B = false;
            }
            ActivityTraceAspect.m12823a().m12828d(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    public void m13722a(String str, String str2, int i) {
        if (this.f9879n.getBoolean(Constants.TRACK_RIDE_DRIVER_RATINGS, false)) {
            this.f9879n.edit().putBoolean(Constants.TRACK_RIDE_DRIVER_RATINGS, false).apply();
        }
        if (!this.f9884s.equalsIgnoreCase("na") && !this.f9884s.equalsIgnoreCase("null") && this.f9884s.length() > 0) {
            this.f9879n.edit().putBoolean(this.f9884s + Constants.USER_RATED, true).commit();
            this.f9879n.edit().putInt(this.f9884s + Constants.USER_RATING, i).commit();
            this.f9879n.edit().putString(this.f9884s + Constants.USER_OPTIONS, str2).commit();
            this.f9879n.edit().putString(this.f9884s + Constants.USER_FEEDBACK, str).commit();
        }
    }

    private void m13715a(String str) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.driver_image_size);
        this.f9890y.m13176a(new WeakReference(this.f9866E), str, dimensionPixelSize, dimensionPixelSize, Config.RGB_565, f9861a);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backImageView:
                finish();
            case R.id.summary_rating_submit:
                String valueOf = String.valueOf(this.f9870e.getText());
                this.f9889x = Trace.NULL;
                this.f9889x = this.f9888w.m14892a();
                m13722a(valueOf, this.f9889x, this.f9887v);
                finish();
            case R.id.leaveCommentText:
                this.f9869d.setVisibility(0);
            default:
        }
    }

    public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
        if (f == 0.0f) {
            ratingBar.setRating(br.DEFAULT_BACKOFF_MULT);
            return;
        }
        if (!this.f9863B) {
            this.f9875j = this.f9888w.m14894b();
        }
        if (this.f9888w.getCount() < 1) {
            if (this.f9886u.equalsIgnoreCase("auto")) {
                this.f9874i = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.auto_driver_rating_reasons)));
            } else {
                this.f9874i = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.driver_rating_reasons)));
            }
        }
        this.f9867b.removeFooterView(this.f9882q);
        this.f9881p.setVisibility(0);
        this.f9869d.setVisibility(0);
        this.f9887v = Math.round(f);
        if (this.f9872g.getVisibility() == 8) {
            this.f9876k.setTarget(this.f9871f);
            this.f9877l.setTarget(this.f9872g);
            this.f9876k.start();
            this.f9877l.start();
        }
        int measuredWidth = this.f9868c.getMeasuredWidth();
        switch (this.f9887v) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f9872g.setImageResource(R.drawable.rating_horrible);
                this.f9872g.setVisibility(0);
                this.f9868c.setText(getResources().getString(R.string.rating_one_star_text));
                break;
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                this.f9872g.setImageResource(R.drawable.rating_bad);
                this.f9872g.setVisibility(0);
                this.f9868c.setText(getResources().getString(R.string.rating_two_star_text));
                break;
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                this.f9872g.setImageResource(R.drawable.rating_okok);
                this.f9872g.setVisibility(0);
                this.f9868c.setText(getResources().getString(R.string.rating_three_star_text));
                break;
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                this.f9872g.setImageResource(R.drawable.rating_good);
                this.f9872g.setVisibility(0);
                this.f9868c.setText(getResources().getString(R.string.rating_four_star_text));
                break;
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoDragView /*5*/:
                this.f9872g.setImageResource(R.drawable.rating_awesome);
                this.f9872g.setVisibility(0);
                this.f9868c.setText(getResources().getString(R.string.rating_five_star_text));
                break;
        }
        this.f9868c.measure(0, 0);
        m13721a(this.f9868c, measuredWidth, this.f9868c.getMeasuredWidth());
        if (ratingBar.getRating() < 4.0f) {
            if (this.f9865D > 3 && this.f9870e.getText().toString().length() > 0) {
                this.f9870e.setText(Trace.NULL);
            }
            if (this.f9862A.getVisibility() == 8) {
                Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rating_list_down);
                loadAnimation.setDuration(300);
                this.f9862A.startAnimation(loadAnimation);
                this.f9862A.setVisibility(0);
            }
            this.f9874i.clear();
            if (this.f9886u.equalsIgnoreCase("auto")) {
                this.f9874i = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.auto_driver_rating_reasons)));
            } else {
                this.f9874i = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.driver_rating_reasons)));
            }
            this.f9888w.m14893a(this.f9874i, this.f9875j, true);
            this.f9888w.notifyDataSetChanged();
        } else {
            if (this.f9865D < 4 && this.f9865D != 0 && this.f9870e.getText().toString().length() > 0) {
                this.f9870e.setText(Trace.NULL);
            }
            this.f9862A.setVisibility(8);
            this.f9874i.clear();
            this.f9888w.m14893a(this.f9874i, this.f9875j, true);
            this.f9888w.notifyDataSetChanged();
        }
        this.f9865D = (int) ratingBar.getRating();
    }

    public void m13721a(View view, int i, int i2) {
        Animation scaleAnimation = new ScaleAnimation(((float) i) / ((float) i2), br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, 1, 0.5f, 1, 0.0f);
        scaleAnimation.setInterpolator(this, 17432586);
        scaleAnimation.setDuration(120);
        view.startAnimation(scaleAnimation);
    }
}
