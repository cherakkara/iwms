package com.payu.sdk;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.payu.p084a.Bank;
import com.payu.sdk.R.R;
import java.util.Set;
import java.util.Timer;
import org.apache.http.util.EncodingUtils;

@Instrumented
public class ProcessPaymentActivity extends FragmentActivity implements TraceFieldInterface {
    boolean f11511a;
    private WebView f11512b;
    private ProgressDialog f11513c;
    private BroadcastReceiver f11514d;
    private ProgressDialog f11515e;
    private String f11516f;
    private boolean f11517g;
    private Set<String> f11518h;
    private String f11519i;

    public ProcessPaymentActivity() {
        this.f11514d = null;
        this.f11511a = false;
    }

    public ProgressDialog m14935a(Context context) {
        if (context == null || isFinishing()) {
            return null;
        }
        Drawable[] drawableArr = new Drawable[]{getResources().getDrawable(R.l_icon1), getResources().getDrawable(R.l_icon2), getResources().getDrawable(R.l_icon3), getResources().getDrawable(R.l_icon4)};
        View inflate = LayoutInflater.from(context).inflate(R.prog_dialog, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.imageView);
        ProgressDialog progressDialog = new ProgressDialog(context, R.ProgressDialog);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new C0940r(this, drawableArr, imageView), 0, 500);
        progressDialog.show();
        progressDialog.setContentView(inflate);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnDismissListener(new C0942t(this, timer));
        return progressDialog;
    }

    public void m14936a(int i) {
        if (i == 8 || i == 4) {
            if (this.f11515e != null && this.f11515e.isShowing()) {
                this.f11515e.dismiss();
            }
        } else if (this.f11515e == null || !this.f11515e.isShowing()) {
            this.f11515e = m14935a((Context) this);
        }
    }

    public void m14937b(int i) {
        if (i == 8 || i == 4) {
            if (this.f11515e != null && this.f11515e.isShowing()) {
                this.f11515e.dismiss();
            }
        } else if (this.f11515e == null) {
            this.f11515e = m14935a((Context) this);
        }
    }

    public void onBackPressed() {
        if (this.f11511a) {
            this.f11511a = false;
            Intent intent = new Intent();
            intent.putExtra("result", "Transaction canceled due to back pressed!");
            setResult(0, intent);
            super.onBackPressed();
            return;
        }
        boolean z;
        try {
            Bundle bundle = getPackageManager().getApplicationInfo(getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).metaData;
            z = bundle.containsKey("payu_disable_back") && bundle.getBoolean("payu_disable_back");
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            z = false;
        }
        if (!z) {
            Builder builder = new Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Do you really want to cancel the transaction ?");
            builder.setPositiveButton("Ok", new C0938p(this));
            builder.setNegativeButton("Cancel", new C0939q(this));
            builder.show();
        }
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("ProcessPaymentActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "ProcessPaymentActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ProcessPaymentActivity#onCreate", null);
                break;
            }
        }
        if (bundle != null) {
            super.onCreate(null);
            finish();
        } else {
            super.onCreate(bundle);
        }
        if (!(getIntent().getExtras() == null || !getIntent().getExtras().containsKey("postData") || getIntent().getExtras().getString("postData").contains("pg=NB"))) {
            ((InputMethodManager) getSystemService("input_method")).toggleSoftInput(3, 0);
        }
        getWindow().setSoftInputMode(3);
        this.f11513c = new ProgressDialog(this);
        setContentView(R.activity_process_payment);
        this.f11512b = (WebView) findViewById(R.webview);
        try {
            String str;
            Class.forName("com.payu.a.b");
            Fragment c0931i = new C0931i(this);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("webView", R.webview);
            bundle2.putInt("tranLayout", R.trans_overlay);
            for (String str2 : getIntent().getExtras().getString("postData").split("&")) {
                if (str2.contains(Bank.TXN_ID)) {
                    str = str2.split("=")[1];
                    break;
                }
            }
            str = null;
            if (str == null) {
                str = String.valueOf(System.currentTimeMillis());
            }
            bundle2.putString(Bank.TXN_ID, str);
            if (getIntent().getExtras().containsKey("showCustom")) {
                bundle2.putBoolean("showCustom", getIntent().getBooleanExtra("showCustom", false));
            }
            bundle2.putBoolean("showCustom", true);
            c0931i.setArguments(bundle2);
            findViewById(R.parent).bringToFront();
            try {
                getSupportFragmentManager().beginTransaction().add(R.parent, c0931i).commit();
            } catch (Exception e2) {
                e2.printStackTrace();
                finish();
            }
            this.f11512b.setWebChromeClient(new C0932j(this, c0931i));
            this.f11512b.setWebViewClient(new C0933k(this, c0931i));
        } catch (ClassNotFoundException e3) {
            this.f11512b.getSettings().setSupportMultipleWindows(true);
            this.f11512b.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            this.f11512b.addJavascriptInterface(new C0934l(this), "PayU");
            this.f11512b.setWebChromeClient(new C0937o(this));
            this.f11512b.setWebViewClient(new WebViewClient());
        }
        this.f11512b.getSettings().setJavaScriptEnabled(true);
        this.f11512b.getSettings().setDomStorageEnabled(true);
        this.f11512b.postUrl("https://secure.payu.in/_payment", EncodingUtils.getBytes(getIntent().getExtras().getString("postData"), "base64"));
        TraceMachine.exitMethod();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f11515e != null) {
            this.f11515e.dismiss();
        }
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }
}
