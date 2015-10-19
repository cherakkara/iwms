package com.olacabs.customer.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* compiled from: SOSCarouselSlidingFragment */
public class ah extends Fragment implements TraceFieldInterface {
    public static final SOSCarouselSlidingFragment[] f10367a;
    private static final JoinPoint f10368h = null;
    private static final JoinPoint f10369i = null;
    RelativeLayout f10370b;
    LinearLayout f10371c;
    private TextView f10372d;
    private TextView f10373e;
    private int f10374f;
    private ImageView f10375g;

    /* renamed from: com.olacabs.customer.ui.ah.a */
    private enum SOSCarouselSlidingFragment {
        INIT_SCREEN(0, 0, 0),
        ADD_EC(R.drawable.saftey_asset_02, R.string.safety_header_1, R.string.safety_text_1),
        FEELING_UNSAFE(R.drawable.saftey_asset_03, R.string.safety_header_2, R.string.safety_text_2),
        LOVED_ONES_WORRIED(R.drawable.saftey_asset_04, R.string.safety_header_3, R.string.safety_text_3);
        
        private final int f10364e;
        private final int f10365f;
        private final int f10366g;

        private SOSCarouselSlidingFragment(int i, int i2, int i3) {
            this.f10364e = i;
            this.f10365f = i2;
            this.f10366g = i3;
        }
    }

    private static void m14054a() {
        Factory factory = new Factory("SOSCarouselSlidingFragment.java", ah.class);
        f10368h = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.SOSCarouselSlidingFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 68);
        f10369i = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.SOSCarouselSlidingFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 75);
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
        m14054a();
        f10367a = SOSCarouselSlidingFragment.values();
    }

    public static ah m14053a(int i) {
        ah ahVar = new ah();
        Bundle bundle = new Bundle();
        bundle.putInt("page", i);
        ahVar.setArguments(bundle);
        return ahVar;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("ah");
        try {
            TraceMachine.enterMethod(this._nr_trace, "ah#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ah#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10368h, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10374f = getArguments().getInt("page");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "ah#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ah#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10369i, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.sos_fragment_screen_slide, viewGroup, false);
        SOSCarouselSlidingFragment sOSCarouselSlidingFragment = f10367a[this.f10374f];
        this.f10370b = (RelativeLayout) viewGroup2.findViewById(R.id.carousel_teaching);
        this.f10371c = (LinearLayout) viewGroup2.findViewById(R.id.sliding_content);
        if (sOSCarouselSlidingFragment.equals(SOSCarouselSlidingFragment.INIT_SCREEN)) {
            this.f10370b.setVisibility(0);
            this.f10371c.setVisibility(8);
        } else {
            this.f10370b.setVisibility(8);
            this.f10371c.setVisibility(0);
        }
        this.f10375g = (ImageView) viewGroup2.findViewById(R.id.image);
        if (sOSCarouselSlidingFragment.f10364e != 0) {
            this.f10375g.setBackgroundResource(sOSCarouselSlidingFragment.f10364e);
        }
        this.f10372d = (TextView) viewGroup2.findViewById(R.id.splash_sliding_text);
        if (sOSCarouselSlidingFragment.f10365f != 0) {
            this.f10372d.setText(sOSCarouselSlidingFragment.f10365f);
        }
        this.f10373e = (TextView) viewGroup2.findViewById(R.id.splash_sliding_subtext);
        if (sOSCarouselSlidingFragment.f10366g != 0) {
            this.f10373e.setText(Html.fromHtml(getActivity().getString(sOSCarouselSlidingFragment.f10366g)));
        }
        TraceMachine.exitMethod();
        return viewGroup2;
    }
}
