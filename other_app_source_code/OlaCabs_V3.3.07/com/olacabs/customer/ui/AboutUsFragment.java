package com.olacabs.customer.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.a */
public class AboutUsFragment extends Fragment implements OnClickListener, TraceFieldInterface {
    private static final String f10220a;
    private static final JoinPoint f10221d = null;
    private static final JoinPoint f10222e = null;
    private MainActivity f10223b;
    private DataManager f10224c;

    private static void m13982b() {
        Factory factory = new Factory("AboutUsFragment.java", AboutUsFragment.class);
        f10221d = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.AboutUsFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 36);
        f10222e = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.AboutUsFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 46);
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
        AboutUsFragment.m13982b();
        f10220a = AboutUsFragment.class.getSimpleName();
    }

    public static AboutUsFragment m13981a() {
        return new AboutUsFragment();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("a");
        try {
            TraceMachine.enterMethod(this._nr_trace, "a#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "a#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10221d, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10223b = (MainActivity) getActivity();
        this.f10224c = ((OlaApp) getActivity().getApplication()).m12878a();
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "a#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "a#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10222e, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_about_us, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.tnc);
        textView.setText(Html.fromHtml(getString(R.string.terms_and_conditions)));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView) inflate.findViewById(R.id.version_header)).setText(this.f10223b.getString(R.string.version_text) + " " + AppInfo.sVersionName);
        textView = (TextView) inflate.findViewById(R.id.praveen);
        textView.setText(Html.fromHtml(getString(R.string.praveen)));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        ((ImageButton) inflate.findViewById(R.id.button_navigation_drawer)).setOnClickListener(this);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_navigation_drawer:
                ((MainActivity) getActivity()).m13607d();
            default:
        }
    }
}
