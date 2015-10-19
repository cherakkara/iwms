package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.y */
public class RateCardFragment extends Fragment implements TraceFieldInterface {
    private static final JoinPoint f11452c = null;
    private static final JoinPoint f11453d = null;
    private WebView f11454a;
    private ProgressBar f11455b;

    /* renamed from: com.olacabs.customer.ui.y.1 */
    class RateCardFragment implements OnClickListener {
        final /* synthetic */ RateCardFragment f11448a;

        RateCardFragment(RateCardFragment rateCardFragment) {
            this.f11448a = rateCardFragment;
        }

        public void onClick(View view) {
            ((MainActivity) this.f11448a.getActivity()).m13607d();
        }
    }

    /* renamed from: com.olacabs.customer.ui.y.2 */
    class RateCardFragment extends WebViewClient {
        final /* synthetic */ RateCardFragment f11449a;

        RateCardFragment(RateCardFragment rateCardFragment) {
            this.f11449a = rateCardFragment;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            if (this.f11449a.f11455b.getVisibility() == 0) {
                this.f11449a.f11455b.setVisibility(8);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.y.3 */
    class RateCardFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f11450a;
        final /* synthetic */ RateCardFragment f11451b;

        RateCardFragment(RateCardFragment rateCardFragment, AlertDialog alertDialog) {
            this.f11451b = rateCardFragment;
            this.f11450a = alertDialog;
        }

        public void onClick(View view) {
            this.f11450a.dismiss();
        }
    }

    static {
        RateCardFragment.m14890b();
    }

    private static void m14890b() {
        Factory factory = new Factory("RateCardFragment.java", RateCardFragment.class);
        f11452c = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.RateCardFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 43);
        f11453d = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.RateCardFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 51);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public static RateCardFragment m14888a() {
        RateCardFragment rateCardFragment = new RateCardFragment();
        rateCardFragment.setArguments(new Bundle());
        return rateCardFragment;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("y");
        try {
            TraceMachine.enterMethod(this._nr_trace, "y#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "y#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f11452c, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "y#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "y#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f11453d, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_rate_card, viewGroup, false);
        inflate.findViewById(R.id.menuDrawerImageView).setOnClickListener(new RateCardFragment(this));
        this.f11455b = (ProgressBar) inflate.findViewById(R.id.emptyView);
        if (Utils.m14909a(getActivity().getApplicationContext())) {
            this.f11454a = (WebView) inflate.findViewById(R.id.webViewRateCard);
            this.f11454a.getSettings().setJavaScriptEnabled(true);
            this.f11454a.loadUrl("https://apps.olacabs.com/v3/pricing/rate_card_details");
            this.f11454a.setWebViewClient(new RateCardFragment(this));
        } else {
            m14889a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
        }
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14889a(String str, String str2) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new RateCardFragment(this, create));
        create.show();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }
}
