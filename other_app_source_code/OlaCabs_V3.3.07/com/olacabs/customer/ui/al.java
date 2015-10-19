package com.olacabs.customer.ui;

import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* compiled from: SplashScreenSlideFragment */
public class al extends Fragment implements TraceFieldInterface {
    public static final SplashScreenSlideFragment[] f10486a;
    private static final String f10487b;
    private static final JoinPoint f10488g = null;
    private static final JoinPoint f10489h = null;
    private int f10490c;
    private TextView f10491d;
    private TextView f10492e;
    private ImageView f10493f;

    /* renamed from: com.olacabs.customer.ui.al.1 */
    class SplashScreenSlideFragment implements OnTouchListener {
        final /* synthetic */ ak f10473a;
        final /* synthetic */ al f10474b;

        SplashScreenSlideFragment(al alVar, ak akVar) {
            this.f10474b = alVar;
            this.f10473a = akVar;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.f10473a.m14124a();
            return false;
        }
    }

    /* renamed from: com.olacabs.customer.ui.al.a */
    private enum SplashScreenSlideFragment {
        INIT_SCREEN(R.drawable.ic_landing_logo, 0, 0),
        RIDE_NOW(R.drawable.splash_ride_now, R.string.ride_now_splash_text, R.string.ride_now_splash_subtext),
        RIDE_SMART(R.drawable.splash_fav, R.string.ride_smart_splash_text, R.string.ride_smart_splash_subtext),
        STAY_INFORMED(R.drawable.splash_track, R.string.stay_informed_splash_text, R.string.stay_informed_splash_subtext),
        RIDE_CASHLESS(R.drawable.splash_olamoney, R.string.ride_cashless_splash_text, R.string.ride_cashless_splash_subtext),
        OLA_CARES(R.drawable.splash_saftey, R.string.ola_cares_splash_text, R.string.ola_cares_splash_subtext),
        EARN_REWARD(R.drawable.splash_invite, R.string.earn_rewards_splash_text, R.string.earn_rewards_splash_subtext);
        
        private final int f10483h;
        private final int f10484i;
        private final int f10485j;

        private SplashScreenSlideFragment(int i, int i2, int i3) {
            this.f10483h = i;
            this.f10484i = i2;
            this.f10485j = i3;
        }
    }

    private static void m14130a() {
        Factory factory = new Factory("SplashScreenSlideFragment.java", al.class);
        f10488g = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.SplashScreenSlideFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 67);
        f10489h = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.SplashScreenSlideFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 75);
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
        m14130a();
        f10487b = al.class.getSimpleName();
        f10486a = SplashScreenSlideFragment.values();
    }

    public static al m14129a(int i) {
        al alVar = new al();
        Bundle bundle = new Bundle();
        bundle.putInt("page", i);
        alVar.setArguments(bundle);
        return alVar;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("al");
        try {
            TraceMachine.enterMethod(this._nr_trace, "al#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "al#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10488g, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10490c = getArguments().getInt("page");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "al#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "al#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10489h, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        OLog.m13311a("onCreateView+ - Page no. - " + this.f10490c, new Object[0]);
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.splash_sliding_layout, viewGroup, false);
        SplashScreenSlideFragment splashScreenSlideFragment = f10486a[this.f10490c];
        this.f10493f = (ImageView) viewGroup2.findViewById(R.id.splash_sliding_image);
        if (splashScreenSlideFragment.equals(SplashScreenSlideFragment.INIT_SCREEN)) {
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.f10493f.setLayoutParams(layoutParams);
        }
        if (this.f10490c != 0) {
            float f = getResources().getDisplayMetrics().density;
            if (f < dm.DEFAULT_BACKOFF_MULT) {
                this.f10493f.setAdjustViewBounds(true);
                this.f10493f.setMaxHeight(m14131a(f)[0]);
                this.f10493f.setMaxWidth(m14131a(f)[1]);
            }
        }
        if (splashScreenSlideFragment.f10483h != 0) {
            this.f10493f.setImageResource(splashScreenSlideFragment.f10483h);
        }
        this.f10491d = (TextView) viewGroup2.findViewById(R.id.splash_sliding_text);
        if (splashScreenSlideFragment.f10484i != 0) {
            this.f10491d.setText(splashScreenSlideFragment.f10484i);
        }
        this.f10492e = (TextView) viewGroup2.findViewById(R.id.splash_sliding_subtext);
        if (splashScreenSlideFragment.f10485j != 0) {
            this.f10492e.setText(Html.fromHtml(getActivity().getString(splashScreenSlideFragment.f10485j)));
        }
        TraceMachine.exitMethod();
        return viewGroup2;
    }

    private int[] m14131a(float f) {
        int[] iArr = new int[2];
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactoryInstrumentation.decodeResource(getResources(), R.drawable.splash_ride_now, options);
        if (f < br.DEFAULT_BACKOFF_MULT) {
            iArr[0] = (int) (((float) options.outHeight) * ((f * f) / dm.DEFAULT_BACKOFF_MULT));
            iArr[1] = (int) (((float) options.outWidth) * ((f * f) / dm.DEFAULT_BACKOFF_MULT));
        } else {
            iArr[0] = (int) (((float) options.outHeight) * (f / dm.DEFAULT_BACKOFF_MULT));
            iArr[1] = (int) (((float) options.outWidth) * (f / dm.DEFAULT_BACKOFF_MULT));
        }
        return iArr;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setOnTouchListener(new SplashScreenSlideFragment(this, (ak) getParentFragment()));
    }
}
