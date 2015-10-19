package com.olacabs.customer.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.apsalar.sdk.Constants;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.apache.http.HttpHost;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class FaqActivity extends Activity implements TraceFieldInterface {
    private static final String f9567a;
    private static final String[] f9568e;
    private static final JoinPoint f9569f = null;
    private ImageView f9570b;
    private WebView f9571c;
    private ProgressBar f9572d;

    /* renamed from: com.olacabs.customer.ui.FaqActivity.1 */
    class C08151 extends WebViewClient {
        final /* synthetic */ FaqActivity f9565a;

        C08151(FaqActivity faqActivity) {
            this.f9565a = faqActivity;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            try {
                Uri parse = Uri.parse(str);
                if (parse.getScheme().equals("mailto:")) {
                    this.f9565a.startActivity(new Intent("android.intent.action.VIEW", parse));
                    return true;
                } else if (!this.f9565a.m13498a(parse)) {
                    this.f9565a.m13496a(parse, null);
                    return true;
                } else if (!str.toLowerCase().startsWith(HttpHost.DEFAULT_SCHEME_NAME) && !str.toLowerCase().startsWith(Constants.API_PROTOCOL)) {
                    return true;
                } else {
                    webView.loadUrl(str);
                    return false;
                }
            } catch (Exception e) {
                OLog.m13318e("Error loading FAQ URLs", new Object[0]);
                return false;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (this.f9565a.f9572d.getVisibility() == 0) {
                this.f9565a.f9572d.setVisibility(8);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.FaqActivity.2 */
    class C08162 implements OnClickListener {
        final /* synthetic */ FaqActivity f9566a;

        C08162(FaqActivity faqActivity) {
            this.f9566a = faqActivity;
        }

        public void onClick(View view) {
            this.f9566a.finish();
        }
    }

    private static void m13495a() {
        Factory factory = new Factory("FaqActivity.java", FaqActivity.class);
        f9569f = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.FaqActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 42);
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
        m13495a();
        f9567a = FaqActivity.class.getSimpleName();
        f9568e = new String[]{"www.olacabs.com", "www.olacabs-dev.in", "apps.olacabs.com", "mobile.api.olacabs-dev.in"};
    }

    public static Intent m13493a(Context context, String str) {
        Intent intent = new Intent(context, FaqActivity.class);
        intent.putExtra("URL_PARAM", str);
        return intent;
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("FaqActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "FaqActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "FaqActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9569f, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        setContentView(R.layout.activity_faq);
        Object stringExtra = getIntent().getStringExtra("URL_PARAM");
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
            TraceMachine.exitMethod();
            return;
        }
        this.f9572d = (ProgressBar) findViewById(R.id.emptyView);
        this.f9571c = (WebView) findViewById(R.id.webViewRateCard);
        this.f9571c.getSettings().setJavaScriptEnabled(true);
        this.f9571c.setWebViewClient(new C08151(this));
        this.f9571c.loadUrl(stringExtra);
        this.f9570b = (ImageView) findViewById(R.id.backImageView);
        this.f9570b.setOnClickListener(new C08162(this));
        TraceMachine.exitMethod();
    }

    private boolean m13498a(Uri uri) {
        String host = uri.getHost();
        for (String str : f9568e) {
            if (host != null && str.compareToIgnoreCase(host) == 0) {
                return true;
            }
        }
        return false;
    }

    private void m13496a(Uri uri, String str) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        if (str != null) {
            intent.setDataAndType(uri, str);
        }
        Utils.m14908a((Context) this, intent);
    }
}
