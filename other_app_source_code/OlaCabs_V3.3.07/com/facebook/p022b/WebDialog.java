package com.facebook.p022b;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.AccessToken;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookServiceException;
import com.facebook.R.R;

/* renamed from: com.facebook.b.u */
public class WebDialog extends Dialog {
    private String f770a;
    private String f771b;
    private WebDialog f772c;
    private WebView f773d;
    private ProgressDialog f774e;
    private ImageView f775f;
    private FrameLayout f776g;
    private boolean f777h;
    private boolean f778i;
    private boolean f779j;

    /* renamed from: com.facebook.b.u.c */
    public interface WebDialog {
        void m938a(Bundle bundle, FacebookException facebookException);
    }

    /* renamed from: com.facebook.b.u.1 */
    class WebDialog implements OnCancelListener {
        final /* synthetic */ WebDialog f856a;

        WebDialog(WebDialog webDialog) {
            this.f856a = webDialog;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.f856a.cancel();
        }
    }

    /* renamed from: com.facebook.b.u.2 */
    class WebDialog implements OnClickListener {
        final /* synthetic */ WebDialog f857a;

        WebDialog(WebDialog webDialog) {
            this.f857a = webDialog;
        }

        public void onClick(View view) {
            this.f857a.cancel();
        }
    }

    /* renamed from: com.facebook.b.u.3 */
    class WebDialog extends WebView {
        final /* synthetic */ WebDialog f858a;

        WebDialog(WebDialog webDialog, Context context) {
            this.f858a = webDialog;
            super(context);
        }

        public void onWindowFocusChanged(boolean z) {
            try {
                super.onWindowFocusChanged(z);
            } catch (NullPointerException e) {
            }
        }
    }

    /* renamed from: com.facebook.b.u.4 */
    class WebDialog implements OnTouchListener {
        final /* synthetic */ WebDialog f859a;

        WebDialog(WebDialog webDialog) {
            this.f859a = webDialog;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!view.hasFocus()) {
                view.requestFocus();
            }
            return false;
        }
    }

    /* renamed from: com.facebook.b.u.a */
    public static class WebDialog {
        private Context f860a;
        private String f861b;
        private String f862c;
        private int f863d;
        private WebDialog f864e;
        private Bundle f865f;
        private AccessToken f866g;

        public WebDialog(Context context, String str, Bundle bundle) {
            this.f863d = 16973840;
            this.f866g = AccessToken.m690a();
            if (this.f866g == null) {
                String a = Utility.m1096a(context);
                if (a != null) {
                    this.f861b = a;
                } else {
                    throw new FacebookException("Attempted to create a builder without a valid access token or a valid default Application ID.");
                }
            }
            m1151a(context, str, bundle);
        }

        public WebDialog(Context context, String str, String str2, Bundle bundle) {
            this.f863d = 16973840;
            if (str == null) {
                str = Utility.m1096a(context);
            }
            Validate.m1147a(str, "applicationId");
            this.f861b = str;
            m1151a(context, str2, bundle);
        }

        public WebDialog m1152a(WebDialog webDialog) {
            this.f864e = webDialog;
            return this;
        }

        public WebDialog m1153a() {
            if (this.f866g != null) {
                this.f865f.putString("app_id", this.f866g.m703g());
                this.f865f.putString("access_token", this.f866g.m698b());
            } else {
                this.f865f.putString("app_id", this.f861b);
            }
            return new WebDialog(this.f860a, this.f862c, this.f865f, this.f863d, this.f864e);
        }

        public String m1154b() {
            return this.f861b;
        }

        public Context m1155c() {
            return this.f860a;
        }

        public int m1156d() {
            return this.f863d;
        }

        public Bundle m1157e() {
            return this.f865f;
        }

        public WebDialog m1158f() {
            return this.f864e;
        }

        private void m1151a(Context context, String str, Bundle bundle) {
            this.f860a = context;
            this.f862c = str;
            if (bundle != null) {
                this.f865f = bundle;
            } else {
                this.f865f = new Bundle();
            }
        }
    }

    /* renamed from: com.facebook.b.u.b */
    private class WebDialog extends WebViewClient {
        final /* synthetic */ WebDialog f867a;

        private WebDialog(WebDialog webDialog) {
            this.f867a = webDialog;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Utility.m1134b("FacebookSDK.WebDialog", "Redirect URL: " + str);
            if (str.startsWith(this.f867a.f771b)) {
                int i;
                Bundle a = this.f867a.m962a(str);
                String string = a.getString("error");
                if (string == null) {
                    string = a.getString("error_type");
                }
                String string2 = a.getString("error_msg");
                if (string2 == null) {
                    string2 = a.getString("error_message");
                }
                if (string2 == null) {
                    string2 = a.getString("error_description");
                }
                String string3 = a.getString("error_code");
                if (Utility.m1126a(string3)) {
                    i = -1;
                } else {
                    try {
                        i = Integer.parseInt(string3);
                    } catch (NumberFormatException e) {
                        i = -1;
                    }
                }
                if (Utility.m1126a(string) && Utility.m1126a(string2) && i == -1) {
                    this.f867a.m963a(a);
                } else if (string != null && (string.equals("access_denied") || string.equals("OAuthAccessDeniedException"))) {
                    this.f867a.cancel();
                } else if (i == 4201) {
                    this.f867a.cancel();
                } else {
                    this.f867a.m965a(new FacebookServiceException(new FacebookRequestError(i, string, string2), string2));
                }
                return true;
            } else if (str.startsWith("fbconnect://cancel")) {
                this.f867a.cancel();
                return true;
            } else if (str.contains("touch")) {
                return false;
            } else {
                this.f867a.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.f867a.m965a(new FacebookDialogException(str, i, str2));
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            sslErrorHandler.cancel();
            this.f867a.m965a(new FacebookDialogException(null, -11, null));
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Utility.m1134b("FacebookSDK.WebDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
            if (!this.f867a.f778i) {
                this.f867a.f774e.show();
            }
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!this.f867a.f778i) {
                this.f867a.f774e.dismiss();
            }
            this.f867a.f776g.setBackgroundColor(0);
            this.f867a.f773d.setVisibility(0);
            this.f867a.f775f.setVisibility(0);
            this.f867a.f779j = true;
        }
    }

    public WebDialog(Context context, String str) {
        this(context, str, 16973840);
    }

    public WebDialog(Context context, String str, int i) {
        super(context, i);
        this.f771b = "fbconnect://success";
        this.f777h = false;
        this.f778i = false;
        this.f779j = false;
        this.f770a = str;
    }

    public WebDialog(Context context, String str, Bundle bundle, int i, WebDialog webDialog) {
        super(context, i);
        this.f771b = "fbconnect://success";
        this.f777h = false;
        this.f778i = false;
        this.f779j = false;
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("redirect_uri", "fbconnect://success");
        bundle.putString("display", "touch");
        this.f770a = Utility.m1089a(ServerProtocol.m1070a(), ServerProtocol.m1073d() + "/" + "dialog/" + str, bundle).toString();
        this.f772c = webDialog;
    }

    public void m964a(WebDialog webDialog) {
        this.f772c = webDialog;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            cancel();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void dismiss() {
        if (this.f773d != null) {
            this.f773d.stopLoading();
        }
        if (!this.f778i && this.f774e.isShowing()) {
            this.f774e.dismiss();
        }
        super.dismiss();
    }

    protected void onStart() {
        super.onStart();
        m970d();
    }

    public void onDetachedFromWindow() {
        this.f778i = true;
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        this.f778i = false;
        super.onAttachedToWindow();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f774e = new ProgressDialog(getContext());
        this.f774e.requestWindowFeature(1);
        this.f774e.setMessage(getContext().getString(R.com_facebook_loading));
        this.f774e.setOnCancelListener(new WebDialog(this));
        requestWindowFeature(1);
        this.f776g = new FrameLayout(getContext());
        m970d();
        getWindow().setGravity(17);
        getWindow().setSoftInputMode(16);
        m960e();
        m954a((this.f775f.getDrawable().getIntrinsicWidth() / 2) + 1);
        this.f776g.addView(this.f775f, new LayoutParams(-2, -2));
        setContentView(this.f776g);
    }

    protected void m967b(String str) {
        this.f771b = str;
    }

    protected Bundle m962a(String str) {
        Uri parse = Uri.parse(str);
        Bundle c = Utility.m1136c(parse.getQuery());
        c.putAll(Utility.m1136c(parse.getFragment()));
        return c;
    }

    protected boolean m966a() {
        return this.f777h;
    }

    protected boolean m968b() {
        return this.f779j;
    }

    protected WebView m969c() {
        return this.f773d;
    }

    public void m970d() {
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        getWindow().setLayout(Math.min(m952a(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels, displayMetrics.density, 480, 800), displayMetrics.widthPixels), Math.min(m952a(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.heightPixels : displayMetrics.widthPixels, displayMetrics.density, 800, 1280), displayMetrics.heightPixels));
    }

    private int m952a(int i, float f, int i2, int i3) {
        double d = 0.5d;
        int i4 = (int) (((float) i) / f);
        if (i4 <= i2) {
            d = 1.0d;
        } else if (i4 < i3) {
            d = 0.5d + ((((double) (i3 - i4)) / ((double) (i3 - i2))) * 0.5d);
        }
        return (int) (d * ((double) i));
    }

    protected void m963a(Bundle bundle) {
        if (this.f772c != null && !this.f777h) {
            this.f777h = true;
            this.f772c.m938a(bundle, null);
            dismiss();
        }
    }

    protected void m965a(Throwable th) {
        if (this.f772c != null && !this.f777h) {
            this.f777h = true;
            if (th instanceof FacebookException) {
                th = (FacebookException) th;
            } else {
                th = new FacebookException(th);
            }
            this.f772c.m938a(null, th);
            dismiss();
        }
    }

    public void cancel() {
        if (this.f772c != null && !this.f777h) {
            m965a(new FacebookOperationCanceledException());
        }
    }

    private void m960e() {
        this.f775f = new ImageView(getContext());
        this.f775f.setOnClickListener(new WebDialog(this));
        this.f775f.setImageDrawable(getContext().getResources().getDrawable(R.com_facebook_close));
        this.f775f.setVisibility(4);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void m954a(int i) {
        View linearLayout = new LinearLayout(getContext());
        this.f773d = new WebDialog(this, getContext());
        this.f773d.setVerticalScrollBarEnabled(false);
        this.f773d.setHorizontalScrollBarEnabled(false);
        this.f773d.setWebViewClient(new WebDialog());
        this.f773d.getSettings().setJavaScriptEnabled(true);
        this.f773d.loadUrl(this.f770a);
        this.f773d.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f773d.setVisibility(4);
        this.f773d.getSettings().setSavePassword(false);
        this.f773d.getSettings().setSaveFormData(false);
        this.f773d.setFocusable(true);
        this.f773d.setFocusableInTouchMode(true);
        this.f773d.setOnTouchListener(new WebDialog(this));
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(this.f773d);
        linearLayout.setBackgroundColor(-872415232);
        this.f776g.addView(linearLayout);
    }
}
