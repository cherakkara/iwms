package com.payu.p084a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.newrelic.agent.android.api.v1.Defaults;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.utils.Constants;
import com.payu.a.b.AnonymousClass12;
import com.payu.a.b.AnonymousClass13;
import com.payu.a.b.AnonymousClass16;
import com.payu.p084a.ProgressDialogTestValue;
import com.payu.p084a.R.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

@Instrumented
/* renamed from: com.payu.a.b */
public abstract class Bank extends Fragment implements TraceFieldInterface {
    private static final int APPROVE = 5;
    public static final boolean DEBUG = false;
    private static final int DEFAULT = 0;
    private static final int ENTER_MANUALLY = 4;
    private static final int MAXIMISED = 2;
    private static final int MINIMISED = 1;
    private static final int OTP = 6;
    private static final int PASSWORD = 1;
    private static final int PIN = 3;
    private static final String PRODUCTION_URL = "https://secure.payu.in/js/sdk_js/v3/";
    private static final int REGENERATE_OTP = 2;
    private static final int SMS_OTP = 7;
    private static final String TEST_URL = "https://mobiletest.payu.in/js/sdk_js/v3/";
    public static final String TXN_ID = "txnid";
    private String URL;
    private Boolean approve_flag;
    private String bankName;
    public String bank_logo;
    public HashMap<String, Drawable> bank_logo_hashmap;
    private Bank buttonClickListener;
    private int c_hdfc;
    private boolean checkLoading;
    private int chooseActionHeight;
    private int frameState;
    private View loadingLayout;
    private int loading_height;
    Analytics mAnalytics;
    private JSONObject mBankJS;
    private BroadcastReceiver mBroadcastReceiver;
    private boolean mCVClicked;
    protected JSONObject mJS;
    private boolean mLoadingJS;
    private boolean mPageReady;
    private String mPassword;
    private Timer mTimer;
    private String mTxnID;
    protected WebView mWebView;
    private int maxWebview;
    private int minWebview;
    private boolean nbhelpVisible;
    public boolean pin_selected_flag;
    private boolean saveUserIDCheck;
    View transView;
    private String url;
    private Set<String> urlSet;
    private View viewarrow;

    /* compiled from: Bank */
    /* renamed from: com.payu.a.b.12 */
    class AnonymousClass12 implements Runnable {
        final /* synthetic */ String val$fields;
        final /* synthetic */ String val$params;

        /* renamed from: com.payu.a.b.12.1 */
        class Bank implements OnClickListener {
            final /* synthetic */ CheckBox val$checkBox;

            Bank(CheckBox checkBox) {
                this.val$checkBox = checkBox;
            }

            public void onClick(View view) {
                if (this.val$checkBox.isChecked()) {
                    Bank.this.saveUserIDCheck = true;
                } else {
                    Bank.this.saveUserIDCheck = Bank.DEBUG;
                }
            }
        }

        /* renamed from: com.payu.a.b.12.2 */
        class Bank implements OnClickListener {
            Bank() {
            }

            public void onClick(View view) {
                try {
                    Bank.this.mWebView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.btn_action)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        AnonymousClass12(String str, String str2) {
            this.val$fields = str;
            this.val$params = str2;
        }

        public void run() {
            try {
                com.payu.p084a.Bank.this.onHelpAvailable();
                View inflate = com.payu.p084a.Bank.this.getActivity().getLayoutInflater().inflate(R.nb_layout, null);
                if (this.val$fields == null) {
                    com.payu.p084a.Bank.this.onHelpUnavailable();
                    ((ViewGroup) com.payu.p084a.Bank.this.getActivity().findViewById(R.help_view)).removeAllViews();
                } else if (this.val$fields.equals("Button")) {
                    JSONObject init = JSONObjectInstrumentation.init(this.val$params);
                    if (!(!init.has("btn_text") || init.getString("btn_text") == null || init.getString("btn_text") == Trace.NULL)) {
                        CheckBox checkBox = (CheckBox) inflate.findViewById(R.checkbox);
                        if (init != null && init.has("checkbox") && init.getBoolean("checkbox")) {
                            if (com.payu.p084a.Bank.this.saveUserIDCheck) {
                                checkBox.setChecked(true);
                            } else {
                                checkBox.setChecked(com.payu.p084a.Bank.DEBUG);
                            }
                            checkBox.setOnClickListener(new com.payu.p084a.Bank.12.Bank(checkBox));
                            checkBox.setVisibility(com.payu.p084a.Bank.DEFAULT);
                        } else {
                            checkBox.setVisibility(8);
                        }
                        ((Button) inflate.findViewById(R.native_button)).setText(init.getString("btn_text"));
                        ((Button) inflate.findViewById(R.native_button)).setTransformationMethod(null);
                        ((Button) inflate.findViewById(R.native_button)).setOnClickListener(new com.payu.p084a.Bank.12.Bank());
                    }
                    ((ViewGroup) com.payu.p084a.Bank.this.getActivity().findViewById(R.help_view)).removeAllViews();
                    ((ViewGroup) com.payu.p084a.Bank.this.getActivity().findViewById(R.help_view)).addView(inflate);
                    com.payu.p084a.Bank.this.nbhelpVisible = true;
                } else {
                    com.payu.p084a.Bank.this.onHelpUnavailable();
                    ((ViewGroup) com.payu.p084a.Bank.this.getActivity().findViewById(R.help_view)).removeAllViews();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: Bank */
    /* renamed from: com.payu.a.b.13 */
    class AnonymousClass13 implements OnTouchListener {
        final String TAG;
        int height;
        float initialX;
        float initialY;
        boolean isTouch;
        final /* synthetic */ View val$helpView;
        final /* synthetic */ View val$view;

        /* renamed from: com.payu.a.b.13.1 */
        class Bank implements Runnable {
            Bank() {
            }

            public void run() {
                Bank.this.viewarrow.setVisibility(8);
            }
        }

        /* renamed from: com.payu.a.b.13.2 */
        class Bank implements Runnable {
            Bank() {
            }

            public void run() {
                AnonymousClass13.this.isTouch = true;
                Bank.this.frameState = Bank.REGENERATE_OTP;
                if (Bank.this.transView != null) {
                    Bank.this.showLayout(Bank.this.transView, Bank.this.getActivity());
                }
            }
        }

        /* renamed from: com.payu.a.b.13.3 */
        class Bank implements Runnable {
            Bank() {
            }

            public void run() {
                Bank.this.frameState = Bank.PASSWORD;
                AnonymousClass13.this.val$helpView.setVisibility(8);
                Bank.this.viewarrow.setVisibility(Bank.DEFAULT);
            }
        }

        AnonymousClass13(View view, View view2) {
            this.val$helpView = view;
            this.val$view = view2;
            this.TAG = "data";
            this.isTouch = true;
            this.height = com.payu.p084a.Bank.DEFAULT;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (com.payu.p084a.Bank.this.nbhelpVisible) {
                return com.payu.p084a.Bank.DEBUG;
            }
            com.payu.p084a.Bank.this.maximiseWebviewHeight();
            if (!this.isTouch) {
                return com.payu.p084a.Bank.DEBUG;
            }
            int actionMasked = motionEvent.getActionMasked();
            if (com.payu.p084a.Bank.this.viewarrow.getVisibility() != 0) {
                switch (actionMasked) {
                    case com.payu.p084a.Bank.DEFAULT /*0*/:
                        this.initialX = motionEvent.getX();
                        this.initialY = motionEvent.getY();
                        break;
                    case com.payu.p084a.Bank.PASSWORD /*1*/:
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        if (this.initialX < x) {
                        }
                        if (this.initialX > x) {
                        }
                        if (this.initialY < y) {
                            View view2 = this.val$view;
                            if (this.val$helpView.getVisibility() == 0 && y - this.initialY > 100.0f) {
                                this.height = view.getHeight();
                                Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (view.getHeight() - 30));
                                translateAnimation.setDuration(500);
                                translateAnimation.setFillBefore(com.payu.p084a.Bank.DEBUG);
                                translateAnimation.setFillEnabled(true);
                                translateAnimation.setZAdjustment(com.payu.p084a.Bank.PASSWORD);
                                view.startAnimation(translateAnimation);
                                if (com.payu.p084a.Bank.this.transView != null) {
                                    com.payu.p084a.Bank.this.transView.setVisibility(8);
                                }
                                this.isTouch = com.payu.p084a.Bank.DEBUG;
                                this.isTouch = true;
                                new Handler().postDelayed(new com.payu.p084a.Bank.13.Bank(), 400);
                            }
                        }
                        if (this.initialY > y) {
                            break;
                        }
                        break;
                    case com.payu.p084a.Bank.REGENERATE_OTP /*2*/:
                    case com.payu.p084a.Bank.PIN /*3*/:
                        break;
                    default:
                        break;
                }
            }
            com.payu.p084a.Bank.this.viewarrow.setClickable(com.payu.p084a.Bank.DEBUG);
            com.payu.p084a.Bank.this.viewarrow.setOnTouchListener(null);
            translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) this.height, 0.0f);
            translateAnimation.setDuration(500);
            translateAnimation.setFillBefore(true);
            view.startAnimation(translateAnimation);
            this.val$helpView.setVisibility(com.payu.p084a.Bank.DEFAULT);
            this.isTouch = com.payu.p084a.Bank.DEBUG;
            new Handler().postDelayed(new com.payu.p084a.Bank.13.Bank(), 20);
            new Handler().postDelayed(new com.payu.p084a.Bank.13.Bank(), 500);
            return true;
        }
    }

    /* compiled from: Bank */
    /* renamed from: com.payu.a.b.16 */
    class AnonymousClass16 extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
        public com.newrelic.agent.android.tracing.Trace _nr_trace;
        final /* synthetic */ String val$bank;

        /* renamed from: com.payu.a.b.16.1 */
        class Bank implements Runnable {
            Bank() {
            }

            public void run() {
                Bank.this.onPageFinished();
            }
        }

        public void _nr_setTrace(com.newrelic.agent.android.tracing.Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        AnonymousClass16(String str) {
            this.val$bank = str;
        }

        protected Void doInBackground(Void... voidArr) {
            com.payu.p084a.Bank.this.mLoadingJS = true;
            String string;
            String string2;
            try {
                string = com.payu.p084a.Bank.this.mBankJS.getString(this.val$bank);
                if (!(com.payu.p084a.Bank.this.getActivity() == null || new File(com.payu.p084a.Bank.this.getActivity().getFilesDir(), string).exists())) {
                    HttpResponse execute;
                    HttpClient defaultHttpClient = new DefaultHttpClient();
                    HttpUriRequest httpGet = new HttpGet(com.payu.p084a.Bank.this.URL + string + ".js");
                    if (defaultHttpClient instanceof HttpClient) {
                        execute = HttpInstrumentation.execute(defaultHttpClient, httpGet);
                    } else {
                        execute = defaultHttpClient.execute(httpGet);
                    }
                    if (execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        OutputStream openFileOutput = com.payu.p084a.Bank.this.getActivity().openFileOutput(string, com.payu.p084a.Bank.DEFAULT);
                        execute.getEntity().writeTo(openFileOutput);
                        openFileOutput.close();
                        com.payu.p084a.Bank.this.mAnalytics.log(com.payu.p084a.Bank.this.getLogMessage("updated bank", this.val$bank));
                    }
                }
                try {
                    if (com.payu.p084a.Bank.this.getActivity() != null) {
                        string2 = com.payu.p084a.Bank.this.mBankJS.getString(this.val$bank);
                        com.payu.p084a.Bank.this.mJS = JSONObjectInstrumentation.init(com.payu.p084a.Bank.this.decodeContents(com.payu.p084a.Bank.this.getActivity().openFileInput(string2)));
                        if (com.payu.p084a.Bank.this.mPageReady) {
                            com.payu.p084a.Bank.this.getActivity().runOnUiThread(new com.payu.p084a.Bank.16.Bank());
                        }
                        com.payu.p084a.Bank.this.mAnalytics.log(com.payu.p084a.Bank.this.getLogMessage("loaded bank", this.val$bank));
                        com.payu.p084a.Bank.this.mLoadingJS = com.payu.p084a.Bank.DEBUG;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } catch (JSONException e4) {
                e4.printStackTrace();
                try {
                    if (com.payu.p084a.Bank.this.getActivity() != null) {
                        string2 = com.payu.p084a.Bank.this.mBankJS.getString(this.val$bank);
                        com.payu.p084a.Bank.this.mJS = JSONObjectInstrumentation.init(com.payu.p084a.Bank.this.decodeContents(com.payu.p084a.Bank.this.getActivity().openFileInput(string2)));
                        if (com.payu.p084a.Bank.this.mPageReady) {
                            com.payu.p084a.Bank.this.getActivity().runOnUiThread(new com.payu.p084a.Bank.16.Bank());
                        }
                        com.payu.p084a.Bank.this.mAnalytics.log(com.payu.p084a.Bank.this.getLogMessage("loaded bank", this.val$bank));
                        com.payu.p084a.Bank.this.mLoadingJS = com.payu.p084a.Bank.DEBUG;
                    }
                } catch (JSONException e42) {
                    e42.printStackTrace();
                } catch (FileNotFoundException e22) {
                    e22.printStackTrace();
                } catch (Exception e32) {
                    e32.printStackTrace();
                }
            } catch (IOException e5) {
                e5.printStackTrace();
                try {
                    if (com.payu.p084a.Bank.this.getActivity() != null) {
                        string2 = com.payu.p084a.Bank.this.mBankJS.getString(this.val$bank);
                        com.payu.p084a.Bank.this.mJS = JSONObjectInstrumentation.init(com.payu.p084a.Bank.this.decodeContents(com.payu.p084a.Bank.this.getActivity().openFileInput(string2)));
                        if (com.payu.p084a.Bank.this.mPageReady) {
                            com.payu.p084a.Bank.this.getActivity().runOnUiThread(new com.payu.p084a.Bank.16.Bank());
                        }
                        com.payu.p084a.Bank.this.mAnalytics.log(com.payu.p084a.Bank.this.getLogMessage("loaded bank", this.val$bank));
                        com.payu.p084a.Bank.this.mLoadingJS = com.payu.p084a.Bank.DEBUG;
                    }
                } catch (JSONException e422) {
                    e422.printStackTrace();
                } catch (FileNotFoundException e222) {
                    e222.printStackTrace();
                } catch (Exception e322) {
                    e322.printStackTrace();
                }
            } catch (Exception e3222) {
                e3222.printStackTrace();
                try {
                    if (com.payu.p084a.Bank.this.getActivity() != null) {
                        string2 = com.payu.p084a.Bank.this.mBankJS.getString(this.val$bank);
                        com.payu.p084a.Bank.this.mJS = JSONObjectInstrumentation.init(com.payu.p084a.Bank.this.decodeContents(com.payu.p084a.Bank.this.getActivity().openFileInput(string2)));
                        if (com.payu.p084a.Bank.this.mPageReady) {
                            com.payu.p084a.Bank.this.getActivity().runOnUiThread(new com.payu.p084a.Bank.16.Bank());
                        }
                        com.payu.p084a.Bank.this.mAnalytics.log(com.payu.p084a.Bank.this.getLogMessage("loaded bank", this.val$bank));
                        com.payu.p084a.Bank.this.mLoadingJS = com.payu.p084a.Bank.DEBUG;
                    }
                } catch (JSONException e4222) {
                    e4222.printStackTrace();
                } catch (FileNotFoundException e2222) {
                    e2222.printStackTrace();
                } catch (Exception e32222) {
                    e32222.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    if (com.payu.p084a.Bank.this.getActivity() != null) {
                        string = com.payu.p084a.Bank.this.mBankJS.getString(this.val$bank);
                        com.payu.p084a.Bank.this.mJS = JSONObjectInstrumentation.init(com.payu.p084a.Bank.this.decodeContents(com.payu.p084a.Bank.this.getActivity().openFileInput(string)));
                        if (com.payu.p084a.Bank.this.mPageReady) {
                            com.payu.p084a.Bank.this.getActivity().runOnUiThread(new com.payu.p084a.Bank.16.Bank());
                        }
                        com.payu.p084a.Bank.this.mAnalytics.log(com.payu.p084a.Bank.this.getLogMessage("loaded bank", this.val$bank));
                        com.payu.p084a.Bank.this.mLoadingJS = com.payu.p084a.Bank.DEBUG;
                    }
                } catch (JSONException e6) {
                    e6.printStackTrace();
                } catch (FileNotFoundException e7) {
                    e7.printStackTrace();
                } catch (Exception e8) {
                    e8.printStackTrace();
                }
            }
            return null;
        }
    }

    /* renamed from: com.payu.a.b.1 */
    class Bank implements OnTouchListener {
        Bank() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (Bank.this.transView != null) {
                Bank.this.transView.setVisibility(8);
            }
            if (Bank.this.frameState == Bank.REGENERATE_OTP) {
                Bank.this.minimizeWebviewHeight();
            }
            return Bank.DEBUG;
        }
    }

    /* renamed from: com.payu.a.b.2 */
    class Bank implements Runnable {
        final /* synthetic */ String val$fields;
        final /* synthetic */ String val$params;

        /* renamed from: com.payu.a.b.2.1 */
        class Bank implements OnClickListener {
            final /* synthetic */ JSONObject val$jsonObject;

            /* renamed from: com.payu.a.b.2.1.1 */
            class Bank implements OnClickListener {
                Bank() {
                }

                public void onClick(View view) {
                    try {
                        Bank.this.mCVClicked = true;
                        Bank.this.mWebView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.pin)));
                        Bank.this.mAnalytics.log(Bank.this.getLogMessage(Bank.this.getString(R.pin), Bank.this.bankName));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            Bank(JSONObject jSONObject) {
                this.val$jsonObject = jSONObject;
            }

            public void onClick(View view) {
                Bank.this.c_hdfc = Bank.PASSWORD;
                Bank.this.pin_selected_flag = true;
                Bank.this.approve_flag = Boolean.valueOf(true);
                Bank.this.maximiseWebviewHeight();
                Bank.this.frameState = Bank.PASSWORD;
                if (Bank.this.transView != null) {
                    Bank.this.transView.setVisibility(8);
                }
                try {
                    if (this.val$jsonObject.has(Bank.this.getString(R.register)) && this.val$jsonObject.getBoolean(Bank.this.getString(R.register))) {
                        Bank.this.mAnalytics.log(Bank.this.getLogMessage(Bank.this.getString(R.register), Bank.this.bankName));
                        view = Bank.this.getActivity().getLayoutInflater().inflate(R.register_pin, null);
                        ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).removeAllViews();
                        ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).addView(view);
                        if (((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).isShown()) {
                            Bank.this.frameState = Bank.REGENERATE_OTP;
                        }
                        view.findViewById(R.pin).setOnClickListener(new Bank());
                        if (this.val$jsonObject.has(Bank.this.getString(R.otp)) && !this.val$jsonObject.getBoolean(Bank.this.getString(R.otp))) {
                            view.findViewById(R.otp).setVisibility(8);
                        }
                        view.setOnClickListener(Bank.this.buttonClickListener);
                        Bank.this.updateHeight(view);
                    }
                    Bank.this.onHelpUnavailable();
                    Bank.this.mCVClicked = true;
                    Bank.this.mWebView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.pin)));
                    Bank.this.mAnalytics.log(Bank.this.getLogMessage(Bank.this.getString(R.pin), Bank.this.bankName));
                    Bank.this.updateHeight(view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        Bank(String str, String str2) {
            this.val$fields = str;
            this.val$params = str2;
        }

        public void run() {
            boolean z = true;
            if (this.val$fields.equals(Bank.this.getString(R.error))) {
                Bank.this.onBankError();
            } else if (this.val$fields.equals("parse error")) {
                Bank.this.onBankError();
            } else if (this.val$fields.contentEquals("loading") && !Bank.this.pin_selected_flag && Bank.this.checkLoading) {
                Bank.this.onHelpAvailable();
                if (Bank.this.transView != null) {
                    Bank.this.transView.setVisibility(Bank.DEFAULT);
                }
                Bank.this.loadingLayout = Bank.this.getActivity().getLayoutInflater().inflate(R.loading, null);
                ((ImageView) Bank.this.loadingLayout.findViewById(R.bank_logo)).setImageDrawable((Drawable) Bank.this.bank_logo_hashmap.get(Bank.this.bank_logo));
                WindowManager windowManager = Bank.this.getActivity().getWindowManager();
                if (VERSION.SDK_INT >= 13) {
                    Point point = new Point();
                    windowManager.getDefaultDisplay().getSize(point);
                    int i = point.y;
                } else {
                    windowManager.getDefaultDisplay().getHeight();
                }
                Bank.this.updateLoaderHeight();
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, Bank.this.chooseActionHeight);
                View findViewById = Bank.this.loadingLayout.findViewById(R.loading);
                findViewById.setLayoutParams(layoutParams);
                findViewById.requestLayout();
                findViewById.invalidate();
                ProgressDialogTestValue.showDialog(Bank.this.getActivity(), Bank.this.loadingLayout.findViewById(R.progress));
                ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).removeAllViews();
                ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).addView(Bank.this.loadingLayout);
                if (((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).isShown()) {
                    Bank.this.frameState = Bank.REGENERATE_OTP;
                } else {
                    Bank.this.maximiseWebviewHeight();
                }
                Bank.this.updateHeight(Bank.this.loadingLayout);
            } else if (this.val$fields.equals(Bank.this.getString(R.choose))) {
                Bank.this.frameState = Bank.REGENERATE_OTP;
                Bank.this.checkLoading = true;
                if (Bank.this.transView != null) {
                    Bank.this.transView.setVisibility(Bank.DEFAULT);
                }
                r4 = Bank.this.getActivity().getLayoutInflater().inflate(R.choose_action, null);
                ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).setVisibility(8);
                if (Bank.this.maxWebview == 0) {
                    Bank.this.calclateMaximizewebView();
                    Bank.this.maximiseWebviewHeight();
                }
                ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).setVisibility(Bank.DEFAULT);
                if (Bank.this.viewarrow != null) {
                    Bank.this.viewarrow.setVisibility(8);
                }
                Bank.this.calclateHeight(r4);
                Bank.this.onHelpAvailable();
                r4.measure(-2, -2);
                Bank.this.chooseActionHeight = r4.getMeasuredHeight();
                ((ImageView) r4.findViewById(R.bank_logo)).setImageDrawable((Drawable) Bank.this.bank_logo_hashmap.get(Bank.this.bank_logo));
                ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).removeAllViews();
                ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).addView(r4);
                if (((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).isShown()) {
                    Bank.this.frameState = Bank.REGENERATE_OTP;
                }
                CharSequence spannableStringBuilder = new SpannableStringBuilder("Select an option for Faster payment");
                spannableStringBuilder.setSpan(new StyleSpan(Bank.PASSWORD), 21, 27, 33);
                ((TextView) r4.findViewById(R.choose_text)).setText(spannableStringBuilder);
                try {
                    JSONObject init = JSONObjectInstrumentation.init(this.val$params);
                    if (init.has(Bank.this.getString(R.otp)) && !init.getBoolean(Bank.this.getString(R.otp))) {
                        r4.findViewById(R.otp).setVisibility(8);
                        r4.findViewById(R.view).setVisibility(8);
                    }
                    r4.findViewById(R.otp).setOnClickListener(Bank.this.buttonClickListener);
                    if (!init.has(Bank.this.getString(R.pin)) || init.getBoolean(Bank.this.getString(R.pin))) {
                        r4.findViewById(R.pin).setOnClickListener(new Bank(init));
                    } else {
                        r4.findViewById(R.pin).setVisibility(8);
                        r4.findViewById(R.view).setVisibility(8);
                    }
                    if (init.has(Bank.this.getString(R.error))) {
                        r4.findViewById(R.error_message).setVisibility(Bank.DEFAULT);
                        ((TextView) r4.findViewById(R.error_message)).setText(init.getString("error"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (this.val$fields.equals(Bank.this.getString(R.incorrect_OTP_2))) {
                Bank.this.checkLoading = true;
                Bank.this.onHelpAvailable();
                Bank.this.mAnalytics.log(Bank.this.getLogMessage(Bank.this.getString(R.incorrect_OTP_2), null));
                r4 = Bank.this.getActivity().getLayoutInflater().inflate(R.retry_otp, null);
                ((ImageView) r4.findViewById(R.bank_logo)).setImageDrawable((Drawable) Bank.this.bank_logo_hashmap.get(Bank.this.bank_logo));
                ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).removeAllViews();
                ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).addView(r4);
                if (((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).isShown()) {
                    Bank.this.frameState = Bank.REGENERATE_OTP;
                } else {
                    Bank.this.maximiseWebviewHeight();
                }
                if (Bank.this.mPassword == null) {
                    r4.findViewById(R.regenerate_layout).setVisibility(Bank.DEFAULT);
                    r4.findViewById(R.Regenerate_layout_gone).setVisibility(8);
                    try {
                        r0 = JSONObjectInstrumentation.init(this.val$params);
                        if (!(r0.has(Bank.this.getString(R.pin)) && r0.getBoolean(Bank.this.getString(R.pin)))) {
                            z = Bank.DEBUG;
                        }
                        r4.findViewById(R.enter_manually).setOnClickListener(Bank.this.buttonClickListener);
                        if (z) {
                            r4.findViewById(R.pin_layout_gone).setVisibility(Bank.DEFAULT);
                        } else {
                            r4.findViewById(R.pin_layout_gone).setVisibility(8);
                        }
                        r4.findViewById(R.pin).setOnClickListener(Bank.this.buttonClickListener);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                Bank.this.updateHeight(r4);
            } else if (this.val$fields.equals(Bank.this.getString(R.retry_otp))) {
                Bank.this.checkLoading = true;
                Bank.this.onHelpAvailable();
                Bank.this.hideSoftKeyboard();
                if (Bank.this.transView != null) {
                    Bank.this.transView.setVisibility(Bank.DEFAULT);
                }
                Bank.this.mAnalytics.log(Bank.this.getLogMessage(Bank.this.getString(R.incorrect_OTP_2), Bank.this.bankName));
                r4 = Bank.this.getActivity().getLayoutInflater().inflate(R.retry_otp, null);
                ((ImageView) r4.findViewById(R.bank_logo)).setImageDrawable((Drawable) Bank.this.bank_logo_hashmap.get(Bank.this.bank_logo));
                ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).removeAllViews();
                ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).addView(r4);
                if (((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).isShown()) {
                    Bank.this.frameState = Bank.REGENERATE_OTP;
                } else {
                    Bank.this.maximiseWebviewHeight();
                }
                try {
                    if (Bank.this.mPassword == null) {
                        boolean z2;
                        boolean z3;
                        r0 = JSONObjectInstrumentation.init(this.val$params);
                        if (r0.has(Bank.this.getString(R.regenerate)) && r0.getBoolean(Bank.this.getString(R.regenerate))) {
                            z2 = true;
                        } else {
                            z2 = Bank.DEFAULT;
                        }
                        if (r0.has(Bank.this.getString(R.pin)) && r0.getBoolean(Bank.this.getString(R.pin))) {
                            z3 = true;
                        } else {
                            z3 = Bank.DEFAULT;
                        }
                        r4.findViewById(R.regenerate_layout).setVisibility(Bank.DEFAULT);
                        if (z2) {
                            r4.findViewById(R.Regenerate_layout_gone).setVisibility(Bank.DEFAULT);
                            if (z3) {
                                r4.findViewById(R.Enter_manually_gone).setVisibility(8);
                                r4.findViewById(R.pin_layout_gone).setVisibility(Bank.DEFAULT);
                            } else {
                                r4.findViewById(R.Enter_manually_gone).setVisibility(Bank.DEFAULT);
                                r4.findViewById(R.pin_layout_gone).setVisibility(8);
                            }
                        } else {
                            if (z3) {
                                r4.findViewById(R.pin_layout_gone).setVisibility(Bank.DEFAULT);
                            } else {
                                r4.findViewById(R.pin_layout_gone).setVisibility(8);
                            }
                            r4.findViewById(R.Regenerate_layout_gone).setVisibility(8);
                            r4.findViewById(R.Enter_manually_gone).setVisibility(Bank.DEFAULT);
                        }
                    }
                    r4.findViewById(R.pin).setOnClickListener(Bank.this.buttonClickListener);
                    r4.findViewById(R.enter_manually).setOnClickListener(Bank.this.buttonClickListener);
                    r4.findViewById(R.retry).setOnClickListener(Bank.this.buttonClickListener);
                    Bank.this.buttonClickListener.setView(r4);
                    r4.findViewById(R.approve).setOnClickListener(Bank.this.buttonClickListener);
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
                Bank.this.updateHeight(r4);
            } else if (this.val$fields.equals(Bank.this.getString(R.enter_pin))) {
                if (Bank.this.viewarrow != null) {
                    Bank.this.viewarrow.setVisibility(8);
                }
                Bank.this.onHelpUnavailable();
                Bank.this.pin_selected_flag = true;
                Bank.this.approve_flag = Boolean.valueOf(true);
                Bank.this.maximiseWebviewHeight();
                Bank.this.frameState = Bank.PASSWORD;
                if (Bank.this.transView != null) {
                    Bank.this.transView.setVisibility(8);
                }
                Bank.this.maximiseWebviewHeight();
                ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).removeAllViews();
            } else if (this.val$fields.equals(Bank.this.getString(R.enter_otp))) {
                if (Bank.this.bank_logo.equalsIgnoreCase("hdfc") && Bank.this.c_hdfc == 0) {
                    Bank.this.c_hdfc = Bank.PASSWORD;
                } else if (!Bank.this.bank_logo.equalsIgnoreCase("hdfc") || Bank.this.c_hdfc <= 0) {
                    Bank.this.enter_otp(this.val$params);
                } else {
                    Bank.this.enter_otp(this.val$params);
                }
            } else if (this.val$fields.equals(Bank.this.getString(R.incorrect_pin))) {
                try {
                    r0 = JSONObjectInstrumentation.init(this.val$params);
                    if (r0.has(Bank.this.getString(R.otp)) && r0.getBoolean(Bank.this.getString(R.otp))) {
                        Bank.this.checkLoading = true;
                        Bank.this.onHelpAvailable();
                        View inflate = Bank.this.getActivity().getLayoutInflater().inflate(R.choose_action, null);
                        ((ImageView) inflate.findViewById(R.bank_logo)).setImageDrawable((Drawable) Bank.this.bank_logo_hashmap.get(Bank.this.bank_logo));
                        TextView textView = (TextView) inflate.findViewById(R.error_message);
                        textView.setVisibility(Bank.DEFAULT);
                        textView.setText(Bank.this.getActivity().getResources().getString(R.incorrect_password));
                        textView = (TextView) inflate.findViewById(R.choose_text);
                        textView.setVisibility(Bank.DEFAULT);
                        textView.setText(Bank.this.getActivity().getResources().getString(R.retry));
                        ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).removeAllViews();
                        ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).addView(inflate);
                        inflate.findViewById(R.otp).setOnClickListener(Bank.this.buttonClickListener);
                        inflate.findViewById(R.pin).setOnClickListener(Bank.this.buttonClickListener);
                        Bank.this.updateHeight(inflate);
                        if (((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).isShown()) {
                            Bank.this.frameState = Bank.REGENERATE_OTP;
                        } else {
                            Bank.this.maximiseWebviewHeight();
                        }
                    }
                } catch (Exception e222) {
                    e222.printStackTrace();
                }
            } else {
                Bank.this.maximiseWebviewHeight();
                Bank.this.frameState = Bank.PASSWORD;
                if (Bank.this.viewarrow != null) {
                    Bank.this.viewarrow.setVisibility(8);
                }
                Bank.this.onHelpUnavailable();
            }
        }
    }

    /* renamed from: com.payu.a.b.3 */
    class Bank implements Runnable {
        final /* synthetic */ String val$result;

        Bank(String str) {
            this.val$result = str;
        }

        public void run() {
            Intent intent = new Intent();
            intent.putExtra(Bank.this.getString(R.result), this.val$result);
            Bank.this.getActivity().setResult(-1, intent);
            Bank.this.getActivity().finish();
        }
    }

    /* renamed from: com.payu.a.b.4 */
    class Bank implements Runnable {
        final /* synthetic */ String val$result;

        Bank(String str) {
            this.val$result = str;
        }

        public void run() {
            Intent intent = new Intent();
            intent.putExtra(Bank.this.getString(R.result), this.val$result);
            Bank.this.getActivity().setResult(Bank.DEFAULT, intent);
            Bank.this.getActivity().finish();
        }
    }

    /* renamed from: com.payu.a.b.5 */
    class Bank implements Runnable {
        final /* synthetic */ String val$result;

        Bank(String str) {
            this.val$result = str;
        }

        public void run() {
            Intent intent = new Intent();
            intent.putExtra(Bank.this.getString(R.result), this.val$result);
            Bank.this.getActivity().setResult(Bank.DEFAULT, intent);
            Bank.this.getActivity().finish();
        }
    }

    /* renamed from: com.payu.a.b.6 */
    class Bank implements Runnable {
        final /* synthetic */ View val$view;

        Bank(View view) {
            this.val$view = view;
        }

        public void run() {
            this.val$view.setVisibility(8);
        }
    }

    /* renamed from: com.payu.a.b.7 */
    class Bank implements Runnable {
        final /* synthetic */ View val$view;

        Bank(View view) {
            this.val$view = view;
        }

        public void run() {
            this.val$view.setVisibility(Bank.DEFAULT);
        }
    }

    /* renamed from: com.payu.a.b.8 */
    class Bank implements Runnable {
        final /* synthetic */ boolean val$pin;
        final /* synthetic */ boolean val$regenerate;
        final /* synthetic */ View val$view;

        Bank(View view, boolean z, boolean z2) {
            this.val$view = view;
            this.val$regenerate = z;
            this.val$pin = z2;
        }

        public void run() {
            if (Bank.this.mPassword == null && this.val$view.isShown()) {
                this.val$view.findViewById(R.retry_text).setVisibility(Bank.DEFAULT);
                if (this.val$regenerate) {
                    this.val$view.findViewById(R.Regenerate_layout_gone).setVisibility(Bank.DEFAULT);
                    this.val$view.findViewById(R.pin_layout_gone).setVisibility(8);
                    this.val$view.findViewById(R.Enter_manually_gone).setVisibility(Bank.DEFAULT);
                } else {
                    if (this.val$pin) {
                        this.val$view.findViewById(R.pin_layout_gone).setVisibility(Bank.DEFAULT);
                    } else {
                        this.val$view.findViewById(R.pin_layout_gone).setVisibility(8);
                    }
                    this.val$view.findViewById(R.Regenerate_layout_gone).setVisibility(8);
                    this.val$view.findViewById(R.Enter_manually_gone).setVisibility(Bank.DEFAULT);
                }
                this.val$view.findViewById(R.retry_text).setVisibility(Bank.DEFAULT);
                this.val$view.findViewById(R.regenerate_layout).setVisibility(Bank.DEFAULT);
                this.val$view.findViewById(R.approve).setVisibility(8);
                this.val$view.findViewById(R.waiting).setVisibility(8);
                this.val$view.findViewById(R.pin).setOnClickListener(Bank.this.buttonClickListener);
                this.val$view.findViewById(R.retry).setOnClickListener(Bank.this.buttonClickListener);
                this.val$view.findViewById(R.enter_manually).setOnClickListener(Bank.this.buttonClickListener);
                Bank.this.updateHeight(this.val$view);
            }
        }
    }

    /* renamed from: com.payu.a.b.9 */
    class Bank extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
        public com.newrelic.agent.android.tracing.Trace _nr_trace;

        /* renamed from: com.payu.a.b.9.1 */
        class Bank implements Runnable {
            Bank() {
            }

            public void run() {
                Bank.this.onPageStarted();
            }
        }

        public void _nr_setTrace(com.newrelic.agent.android.tracing.Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        Bank() {
        }

        protected Void doInBackground(Void... voidArr) {
            HttpClient defaultHttpClient = new DefaultHttpClient();
            String str = "initialize";
            try {
                HttpUriRequest httpGet = new HttpGet(Bank.this.URL + str + ".js");
                HttpResponse execute = !(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpGet) : HttpInstrumentation.execute(defaultHttpClient, httpGet);
                if (execute.getStatusLine().getStatusCode() != HttpStatus.SC_OK || Bank.this.getActivity() == null) {
                    execute.getEntity().getContent().close();
                } else {
                    OutputStream openFileOutput = Bank.this.getActivity().openFileOutput(str, Bank.DEFAULT);
                    execute.getEntity().writeTo(openFileOutput);
                    openFileOutput.close();
                }
                try {
                    if (Bank.this.getActivity() != null) {
                        Bank.this.mBankJS = JSONObjectInstrumentation.init(Bank.this.decodeContents(Bank.this.getActivity().openFileInput(str)));
                        if (Bank.this.mBankJS != null) {
                            Bank.this.setUrlString();
                        } else {
                            Bank.this.communicationError();
                        }
                        if (Bank.this.mPageReady && Bank.this.getActivity() != null) {
                            Bank.this.getActivity().runOnUiThread(new Bank());
                        }
                    }
                } catch (FileNotFoundException e) {
                    Bank.this.communicationError();
                    e.printStackTrace();
                } catch (JSONException e2) {
                    Bank.this.communicationError();
                    e2.printStackTrace();
                } catch (Exception e3) {
                    Bank.this.communicationError();
                    e3.printStackTrace();
                }
            } catch (IOException e4) {
                e4.printStackTrace();
                try {
                    if (Bank.this.getActivity() != null) {
                        Bank.this.mBankJS = JSONObjectInstrumentation.init(Bank.this.decodeContents(Bank.this.getActivity().openFileInput(str)));
                        if (Bank.this.mBankJS != null) {
                            Bank.this.setUrlString();
                        } else {
                            Bank.this.communicationError();
                        }
                        if (Bank.this.mPageReady && Bank.this.getActivity() != null) {
                            Bank.this.getActivity().runOnUiThread(new Bank());
                        }
                    }
                } catch (FileNotFoundException e5) {
                    Bank.this.communicationError();
                    e5.printStackTrace();
                } catch (JSONException e22) {
                    Bank.this.communicationError();
                    e22.printStackTrace();
                } catch (Exception e32) {
                    Bank.this.communicationError();
                    e32.printStackTrace();
                }
            } catch (Exception e322) {
                e322.printStackTrace();
                try {
                    if (Bank.this.getActivity() != null) {
                        Bank.this.mBankJS = JSONObjectInstrumentation.init(Bank.this.decodeContents(Bank.this.getActivity().openFileInput(str)));
                        if (Bank.this.mBankJS != null) {
                            Bank.this.setUrlString();
                        } else {
                            Bank.this.communicationError();
                        }
                        if (Bank.this.mPageReady && Bank.this.getActivity() != null) {
                            Bank.this.getActivity().runOnUiThread(new Bank());
                        }
                    }
                } catch (FileNotFoundException e52) {
                    Bank.this.communicationError();
                    e52.printStackTrace();
                } catch (JSONException e222) {
                    Bank.this.communicationError();
                    e222.printStackTrace();
                } catch (Exception e3222) {
                    Bank.this.communicationError();
                    e3222.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    if (Bank.this.getActivity() != null) {
                        Bank.this.mBankJS = JSONObjectInstrumentation.init(Bank.this.decodeContents(Bank.this.getActivity().openFileInput(str)));
                        if (Bank.this.mBankJS != null) {
                            Bank.this.setUrlString();
                        } else {
                            Bank.this.communicationError();
                        }
                        if (Bank.this.mPageReady && Bank.this.getActivity() != null) {
                            Bank.this.getActivity().runOnUiThread(new Bank());
                        }
                    }
                } catch (FileNotFoundException e6) {
                    Bank.this.communicationError();
                    e6.printStackTrace();
                } catch (JSONException e7) {
                    Bank.this.communicationError();
                    e7.printStackTrace();
                } catch (Exception e8) {
                    Bank.this.communicationError();
                    e8.printStackTrace();
                }
            }
            return null;
        }
    }

    /* renamed from: com.payu.a.b.a */
    public class Bank implements OnClickListener {
        private View view_edit;

        /* renamed from: com.payu.a.b.a.1 */
        class Bank implements TextWatcher {
            final /* synthetic */ View val$view;

            Bank(View view) {
                this.val$view = view;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (((EditText) this.val$view.findViewById(R.otp_sms)).getText().toString().length() > Bank.APPROVE) {
                    this.val$view.findViewById(R.approve).setClickable(true);
                    this.val$view.findViewById(R.approve).setAlpha(br.DEFAULT_BACKOFF_MULT);
                    return;
                }
                this.val$view.findViewById(R.approve).setClickable(Bank.DEBUG);
                this.val$view.findViewById(R.approve).setAlpha(Defaults.ACTIVITY_TRACE_MIN_UTILIZATION);
            }

            public void afterTextChanged(Editable editable) {
            }
        }

        public void setView(View view) {
            this.view_edit = view;
        }

        public void onClick(View view) {
            String str = Trace.NULL;
            if (view instanceof Button) {
                str = ((Button) view).getText().toString();
            } else if (view instanceof TextView) {
                str = ((TextView) view).getText().toString();
            }
            switch (Bank.this.getCode(str.toLowerCase())) {
                case Bank.PASSWORD /*1*/:
                case Bank.PIN /*3*/:
                    Bank.this.pin_selected_flag = true;
                    Bank.this.approve_flag = Boolean.valueOf(true);
                    Bank.this.maximiseWebviewHeight();
                    Bank.this.frameState = Bank.PASSWORD;
                    Bank.this.onHelpUnavailable();
                    if (Bank.this.viewarrow != null) {
                        Bank.this.viewarrow.setVisibility(8);
                    }
                    if (Bank.this.transView != null) {
                        Bank.this.transView.setVisibility(8);
                    }
                    Bank.this.mCVClicked = true;
                    try {
                        Bank.this.mWebView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.pin)));
                        Bank.this.mAnalytics.log(Bank.this.getLogMessage(Bank.this.getString(R.pin), Bank.this.bankName));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                case Bank.REGENERATE_OTP /*2*/:
                    try {
                        Bank.this.mPassword = null;
                        Bank.this.mAnalytics.log(Bank.this.getLogMessage(Bank.this.getString(R.regenerate), Bank.this.bankName));
                        Bank.this.mWebView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.regen_otp)));
                        Bank.this.prepareSmsListener();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                case Bank.ENTER_MANUALLY /*4*/:
                    View inflate = Bank.this.getActivity().getLayoutInflater().inflate(R.wait_for_otp, null);
                    Bank.this.mAnalytics.log(Bank.this.getLogMessage(Bank.this.getString(R.enter_manually_log), Bank.this.bankName));
                    ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).removeAllViews();
                    ((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).addView(inflate);
                    if (((ViewGroup) Bank.this.getActivity().findViewById(R.help_view)).isShown()) {
                        Bank.this.frameState = Bank.REGENERATE_OTP;
                    } else {
                        Bank.this.maximiseWebviewHeight();
                    }
                    ((ImageView) inflate.findViewById(R.bank_logo)).setImageDrawable((Drawable) Bank.this.bank_logo_hashmap.get(Bank.this.bank_logo));
                    inflate.findViewById(R.waiting).setVisibility(8);
                    inflate.findViewById(R.approve).setClickable(Bank.DEBUG);
                    Bank.this.showSoftKeyboard(inflate.findViewById(R.otp_sms));
                    inflate.findViewById(R.approve).setOnClickListener(null);
                    inflate.findViewById(R.approve).setAlpha(Defaults.ACTIVITY_TRACE_MIN_UTILIZATION);
                    inflate.findViewById(R.approve).setVisibility(Bank.DEFAULT);
                    inflate.findViewById(R.otp_sms).setVisibility(Bank.DEFAULT);
                    inflate.findViewById(R.regenerate_layout).setVisibility(8);
                    inflate.findViewById(R.progress).setVisibility(Bank.ENTER_MANUALLY);
                    Bank.this.showSoftKeyboard(inflate.findViewById(R.otp_sms));
                    Bank.this.buttonClickListener.setView(inflate);
                    inflate.findViewById(R.approve).setOnClickListener(Bank.this.buttonClickListener);
                    ((EditText) inflate.findViewById(R.otp_sms)).addTextChangedListener(new Bank(inflate));
                    Bank.this.updateHeight(inflate);
                case Bank.APPROVE /*5*/:
                    try {
                        Bank.this.hideSoftKeyboard();
                        Bank.this.mPassword = null;
                        Bank.this.checkLoading = Bank.DEBUG;
                        Bank.this.approve_flag = Boolean.valueOf(true);
                        Bank.this.onHelpUnavailable();
                        Bank.this.maximiseWebviewHeight();
                        Bank.this.frameState = Bank.PASSWORD;
                        Bank.this.prepareSmsListener();
                        if (((EditText) this.view_edit.findViewById(R.otp_sms)).getText().toString().length() > Bank.APPROVE) {
                            Bank.this.mAnalytics.log(Bank.this.getLogMessage(Bank.this.getString(R.approved_otp), Bank.this.bankName));
                            Bank.this.mWebView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.process_otp)) + "(\"" + ((TextView) Bank.this.getActivity().findViewById(R.otp_sms)).getText().toString() + "\")");
                        }
                    } catch (JSONException e22) {
                        e22.printStackTrace();
                    }
                case Bank.OTP /*6*/:
                case Bank.SMS_OTP /*7*/:
                    try {
                        Bank.this.mCVClicked = true;
                        Bank.this.mWebView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.otp)));
                        Bank.this.mAnalytics.log(Bank.this.getLogMessage(Bank.this.getString(R.otp), Bank.this.bankName));
                    } catch (JSONException e222) {
                        e222.printStackTrace();
                    }
                default:
            }
        }
    }

    public abstract void communicationError();

    public abstract void onBankError();

    public abstract void onHelpAvailable();

    public abstract void onHelpUnavailable();

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public abstract void registerBroadcast(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);

    public abstract void unregisterBroadcast(BroadcastReceiver broadcastReceiver);

    public abstract void updateSet(Set<String> set, String str);

    public Bank() {
        this.URL = PRODUCTION_URL;
        this.mTimer = null;
        this.mLoadingJS = DEBUG;
        this.bank_logo_hashmap = new HashMap();
        this.mCVClicked = DEBUG;
        this.approve_flag = Boolean.valueOf(DEBUG);
        this.c_hdfc = DEFAULT;
        this.urlSet = new HashSet();
        this.url = "https://secure.payu.in/_payment_options";
        this.mPageReady = DEBUG;
        this.saveUserIDCheck = true;
        this.buttonClickListener = new Bank();
    }

    private String decodeContents(FileInputStream fileInputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(fileInputStream);
            int i = DEFAULT;
            while (true) {
                int read = gZIPInputStream.read();
                if (read == -1) {
                    break;
                }
                if (i % REGENERATE_OTP == 0) {
                    stringBuilder.append((char) (read - ((i % APPROVE) + PASSWORD)));
                } else {
                    stringBuilder.append((char) (read + ((i % APPROVE) + PASSWORD)));
                }
                i += PASSWORD;
            }
            gZIPInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("b");
        try {
            TraceMachine.enterMethod(this._nr_trace, "b#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "b#onCreate", null);
                break;
            }
        }
        super.onCreate(bundle);
        this.pin_selected_flag = DEBUG;
        initialiseBanklogo();
        if (!getArguments().containsKey(TXN_ID) || getArguments().getString(TXN_ID) == null || getArguments().getString(TXN_ID).equals(Trace.NULL)) {
            this.mTxnID = "123";
        } else {
            this.mTxnID = getArguments().getString(TXN_ID);
        }
        this.mAnalytics = Analytics.getInstance(getActivity());
        this.mWebView = (WebView) getActivity().findViewById(getArguments().getInt("webView"));
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.addJavascriptInterface(this, "PayU");
        this.mWebView.getSettings().setSupportMultipleWindows(true);
        this.mWebView.getSettings().setAppCacheEnabled(DEBUG);
        this.mWebView.setOnTouchListener(new Bank());
        if (!getArguments().containsKey("showCustom") || getArguments().getBoolean("showCustom")) {
            this.mAnalytics.log(getLogMessage("init", this.bankName));
            prepareSmsListener();
            Bank bank = new Bank();
            Void[] voidArr = new Void[PIN];
            voidArr[DEFAULT] = null;
            voidArr[PASSWORD] = null;
            voidArr[REGENERATE_OTP] = null;
            if (bank instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(bank, voidArr);
            } else {
                bank.execute(voidArr);
            }
        }
        TraceMachine.exitMethod();
    }

    public void initialiseBanklogo() {
        this.bank_logo_hashmap.put("sbi", getResources().getDrawable(R.sbi));
        this.bank_logo_hashmap.put("sbinet", getResources().getDrawable(R.sbi));
        this.bank_logo_hashmap.put("icici", getResources().getDrawable(R.icici));
        this.bank_logo_hashmap.put("icicinet", getResources().getDrawable(R.icici));
        this.bank_logo_hashmap.put("icicicc", getResources().getDrawable(R.icici));
        this.bank_logo_hashmap.put("citi", getResources().getDrawable(R.citi));
        this.bank_logo_hashmap.put("kotak", getResources().getDrawable(R.kotak));
        this.bank_logo_hashmap.put("kotaknet", getResources().getDrawable(R.kotak));
        this.bank_logo_hashmap.put("hsbc", getResources().getDrawable(R.hsbc));
        this.bank_logo_hashmap.put("idbi", getResources().getDrawable(R.idbi));
        this.bank_logo_hashmap.put("indus", getResources().getDrawable(R.induslogo));
        this.bank_logo_hashmap.put("hdfc", getResources().getDrawable(R.hdfc_bank));
        this.bank_logo_hashmap.put("yesnet", getResources().getDrawable(R.yesbank_logo));
        this.bank_logo_hashmap.put("sc", getResources().getDrawable(R.scblogo));
        this.bank_logo_hashmap.put("axisnet", getResources().getDrawable(R.axis_logo));
        this.bank_logo_hashmap.put("amex", getResources().getDrawable(R.amex_logo));
        this.bank_logo_hashmap.put("hdfcnet", getResources().getDrawable(R.hdfc_bank));
        this.bank_logo_hashmap.put("ing", getResources().getDrawable(R.ing_logo));
    }

    private void prepareSmsListener() {
        if (this.mBroadcastReceiver == null) {
            this.mBroadcastReceiver = new BroadcastReceiver() {

                /* renamed from: com.payu.a.b.10.1 */
                class Bank implements OnClickListener {
                    Bank() {
                    }

                    public void onClick(View view) {
                        try {
                            Bank.this.mPassword = null;
                            Bank.this.prepareSmsListener();
                            Bank.this.checkLoading = Bank.DEBUG;
                            Bank.this.approve_flag = Boolean.valueOf(true);
                            Bank.this.onHelpUnavailable();
                            Bank.this.maximiseWebviewHeight();
                            Bank.this.frameState = Bank.PASSWORD;
                            Bank.this.mWebView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.process_otp)) + "(\"" + ((TextView) Bank.this.getActivity().findViewById(R.otp_sms)).getText().toString() + "\")");
                            Bank.this.mAnalytics.log(Bank.this.getLogMessage(Bank.this.getString(R.approved_otp), Bank.this.bankName));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onReceive(Context context, Intent intent) {
                    try {
                        if (com.payu.p084a.Bank.this.mBankJS != null) {
                            Bundle extras = intent.getExtras();
                            if (extras != null) {
                                SmsMessage createFromPdu = SmsMessage.createFromPdu((byte[]) ((Object[]) extras.get("pdus"))[com.payu.p084a.Bank.DEFAULT]);
                                if (Pattern.compile(com.payu.p084a.Bank.this.mBankJS.getString(com.payu.p084a.Bank.this.getString(R.detect_otp)), com.payu.p084a.Bank.REGENERATE_OTP).matcher(createFromPdu.getMessageBody()).find()) {
                                    Matcher matcher = Pattern.compile(com.payu.p084a.Bank.this.mBankJS.getString(com.payu.p084a.Bank.this.getString(R.find_otp)), com.payu.p084a.Bank.REGENERATE_OTP).matcher(createFromPdu.getMessageBody());
                                    if (matcher.find()) {
                                        com.payu.p084a.Bank.this.mPassword = matcher.group(com.payu.p084a.Bank.PASSWORD).replaceAll("[^0-9]", Trace.NULL);
                                    }
                                }
                                if (com.payu.p084a.Bank.this.mPassword != null && com.payu.p084a.Bank.this.getActivity().findViewById(R.otp_sms) != null) {
                                    com.payu.p084a.Bank.this.mAnalytics.log(com.payu.p084a.Bank.this.getLogMessage("OTP received", com.payu.p084a.Bank.this.bankName));
                                    ((TextView) com.payu.p084a.Bank.this.getActivity().findViewById(R.otp_sms)).setText(com.payu.p084a.Bank.this.mPassword);
                                    ProgressDialogTestValue.removeDialog(com.payu.p084a.Bank.this.getActivity().findViewById(R.progress));
                                    com.payu.p084a.Bank.this.getActivity().findViewById(R.retry_text).setVisibility(8);
                                    com.payu.p084a.Bank.this.getActivity().findViewById(R.regenerate_layout).setVisibility(8);
                                    if (com.payu.p084a.Bank.this.mTimer != null) {
                                        com.payu.p084a.Bank.this.mTimer.cancel();
                                        com.payu.p084a.Bank.this.mTimer = null;
                                    }
                                    com.payu.p084a.Bank.this.getActivity().findViewById(R.approve).setVisibility(com.payu.p084a.Bank.DEFAULT);
                                    com.payu.p084a.Bank.this.getActivity().findViewById(R.waiting).setVisibility(8);
                                    com.payu.p084a.Bank.this.getActivity().findViewById(R.approve).setClickable(true);
                                    com.payu.p084a.Bank.this.getActivity().findViewById(R.approve).setAlpha(br.DEFAULT_BACKOFF_MULT);
                                    com.payu.p084a.Bank.this.getActivity().findViewById(R.otp_recieved).setVisibility(com.payu.p084a.Bank.DEFAULT);
                                    com.payu.p084a.Bank.this.getActivity().findViewById(R.otp_sms).setVisibility(com.payu.p084a.Bank.DEFAULT);
                                    com.payu.p084a.Bank.this.getActivity().findViewById(R.approve).setOnClickListener(new com.payu.p084a.Bank.10.Bank());
                                    abortBroadcast();
                                    if (com.payu.p084a.Bank.this.mBroadcastReceiver != null) {
                                        com.payu.p084a.Bank.this.unregisterBroadcast(com.payu.p084a.Bank.this.mBroadcastReceiver);
                                        com.payu.p084a.Bank.this.mBroadcastReceiver = null;
                                    }
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.setPriority(9999999);
            intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
            registerBroadcast(this.mBroadcastReceiver, intentFilter);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mAnalytics.log(getLogMessage("terminate", this.bankName));
        if (this.mBroadcastReceiver != null) {
            unregisterBroadcast(this.mBroadcastReceiver);
            this.mBroadcastReceiver = null;
        }
        if (this.mTimer != null) {
            this.mTimer.cancel();
        }
    }

    @JavascriptInterface
    public void getUserId() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        SharedPreferences sharedPreferences = com.payu.p084a.Bank.this.getActivity().getSharedPreferences("com.payu.custombrowser", com.payu.p084a.Bank.DEFAULT);
                        Log.d("page----", "page" + sharedPreferences.getString(com.payu.p084a.Bank.this.bankName, Trace.NULL));
                        com.payu.p084a.Bank.this.mWebView.loadUrl("javascript:" + com.payu.p084a.Bank.this.mJS.getString(com.payu.p084a.Bank.this.getString(R.populate_user_id)) + "(\"" + sharedPreferences.getString(com.payu.p084a.Bank.this.bankName, Trace.NULL) + "\")");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void setUserId(String str) {
        if (this.saveUserIDCheck && getActivity() != null) {
            Editor edit = getActivity().getSharedPreferences("com.payu.custombrowser", DEFAULT).edit();
            edit.putString(this.bankName, str);
            edit.commit();
        }
    }

    @JavascriptInterface
    public void nativeHelperForNB(String str, String str2) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new AnonymousClass12(str, str2));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "b#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "b#onCreateView", null);
                break;
            }
        }
        try {
            this.transView = getActivity().findViewById(getArguments().getInt("tranLayout"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        View inflate = layoutInflater.inflate(R.bank, null);
        inflate.bringToFront();
        View findViewById = inflate.findViewById(R.help_view);
        this.viewarrow = inflate.findViewById(R.view);
        inflate.setOnTouchListener(new AnonymousClass13(findViewById, inflate));
        TraceMachine.exitMethod();
        return inflate;
    }

    @JavascriptInterface
    public void analyticsLog(String str) {
        if (!this.mCVClicked) {
            if (str.equals(getString(R.otp_wv))) {
                try {
                    this.mAnalytics.log(getLogMessage(getString(R.otp_wv), this.bankName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (str.equals(getString(R.pin_wv))) {
                try {
                    this.mAnalytics.log(getLogMessage(getString(R.pin_wv), this.bankName));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        reInit();
    }

    @JavascriptInterface
    public void reInit() {
        this.mCVClicked = DEBUG;
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                com.payu.p084a.Bank.this.onPageFinished();
            }
        });
    }

    @JavascriptInterface
    public void bankFound(String str) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                com.payu.p084a.Bank.this.calclateMaximizewebView();
            }
        });
        this.bankName = str;
        this.bank_logo = str;
        if (!this.mPageReady) {
            convertToNative("loading", "{}");
        }
        if (!this.mLoadingJS && this.mJS == null) {
            this.mAnalytics.log(getLogMessage("loading bank", str));
            AnonymousClass16 anonymousClass16 = new AnonymousClass16(str);
            Void[] voidArr = new Void[DEFAULT];
            if (anonymousClass16 instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(anonymousClass16, voidArr);
            } else {
                anonymousClass16.execute(voidArr);
            }
        }
    }

    @JavascriptInterface
    public void convertToNative(String str, String str2) {
        getActivity().runOnUiThread(new Bank(str, str2));
    }

    public void onPageFinished() {
        if (isAdded() && !isRemoving()) {
            this.mPageReady = true;
            if (this.approve_flag.booleanValue()) {
                onHelpUnavailable();
                this.approve_flag = Boolean.valueOf(DEBUG);
            }
            if (this.loadingLayout != null && this.loadingLayout.isShown()) {
                this.frameState = PASSWORD;
                maximiseWebviewHeight();
                onHelpUnavailable();
            }
            getActivity().getWindow().setSoftInputMode(PIN);
            if (this.mJS != null) {
                try {
                    this.mWebView.loadUrl("javascript:" + this.mJS.getString(getString(R.init)));
                    this.mAnalytics.log(getLogMessage(getString(R.detecting_page), this.bankName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (this.mBankJS != null && this.mJS == null) {
                this.transView.setVisibility(8);
            }
        }
    }

    public void onPageStarted() {
        if (this.nbhelpVisible) {
            onHelpUnavailable();
            this.nbhelpVisible = DEBUG;
        }
        if (isAdded() && !isRemoving()) {
            this.mPageReady = DEBUG;
            if (this.mBankJS != null) {
                try {
                    this.mWebView.loadUrl("javascript:" + this.mBankJS.getString(getString(R.detect_bank)));
                    this.mAnalytics.log(getLogMessage(getString(R.detecting_bank), null));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @JavascriptInterface
    public void onSuccess() {
        onSuccess(Trace.NULL);
    }

    @JavascriptInterface
    public void onSuccess(String str) {
        this.mAnalytics.log(getLogMessage(getString(R.success), null));
        Log.d("payu", "inside on success");
        getActivity().runOnUiThread(new Bank(str));
    }

    @JavascriptInterface
    public void onFailure() {
        onFailure(Trace.NULL);
    }

    @JavascriptInterface
    public void onFailure(String str) {
        this.mAnalytics.log(getLogMessage(getString(R.failure), null));
        Log.d("payu", "inside on failure");
        getActivity().runOnUiThread(new Bank(str));
    }

    @JavascriptInterface
    public void onCancel() {
        onCancel(Trace.NULL);
    }

    @JavascriptInterface
    public void onCancel(String str) {
        this.mAnalytics.log(getLogMessage(getString(R.cancel), this.bankName));
        getActivity().runOnUiThread(new Bank(str));
    }

    String getLogMessage(String str, String str2) {
        try {
            StringBuilder append = new StringBuilder().append(getActivity().getPackageName()).append(",");
            if (str2 == null) {
                str2 = Trace.NULL;
            }
            String stringBuilder = append.append(str2).append(",").append(System.currentTimeMillis()).append(",").append(this.mTxnID).append(",").append(str).toString();
            Log.d("Pakage Name", getActivity().getPackageName());
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(stringBuilder.getBytes());
            return stringBuilder + "," + new BigInteger(PASSWORD, instance.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void hideLayout(View view, Context context) {
        if (view != null) {
            view.startAnimation(AnimationUtils.loadAnimation(context, R.face_out));
            new Handler().postDelayed(new Bank(view), 500);
        }
    }

    public void showLayout(View view, Context context) {
        if (view != null) {
            view.startAnimation(AnimationUtils.loadAnimation(context, R.cb_fade_in));
            new Handler().postDelayed(new Bank(view), 500);
        }
    }

    private void showSoftKeyboard(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        ((InputMethodManager) getActivity().getSystemService("input_method")).showSoftInput((EditText) view, REGENERATE_OTP);
    }

    private void hideSoftKeyboard() {
        getActivity().getWindow().setSoftInputMode(PIN);
    }

    public void calclateHeight(View view) {
        view.measure(-2, -2);
        this.loading_height = view.getMeasuredHeight();
        this.minWebview = this.maxWebview - this.loading_height;
    }

    public void calclateMaximizewebView() {
        try {
            if (this.maxWebview == 0) {
                this.mWebView.measure(-1, -1);
                this.mWebView.requestLayout();
                this.maxWebview = this.mWebView.getMeasuredHeight();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void maximiseWebviewHeight() {
        if (this.maxWebview == 0) {
            calclateMaximizewebView();
        }
        this.mWebView.getLayoutParams().height = this.maxWebview;
        this.mWebView.requestLayout();
    }

    public void minimizeWebviewHeight() {
        this.mWebView.getLayoutParams().height = this.minWebview;
        this.mWebView.requestLayout();
    }

    public void setFixHeightFrame(View view) {
        this.mWebView.measure(-1, -1);
        int measuredHeight = this.mWebView.getMeasuredHeight();
        view.measure(-2, -2);
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, (int) (((double) measuredHeight) * 0.4d)));
        view.requestLayout();
    }

    public void swipeLeft(View view) {
        Animation translateAnimation = new TranslateAnimation(0.0f, (float) view.getWidth(), 0.0f, 0.0f);
        translateAnimation.setDuration(500);
        translateAnimation.setFillBefore(DEBUG);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setZAdjustment(PASSWORD);
        view.startAnimation(translateAnimation);
    }

    public void updateHeight(View view) {
        if (this.maxWebview == 0) {
            calclateMaximizewebView();
            maximiseWebviewHeight();
        }
        calclateHeight(view);
    }

    public void updateLoaderHeight() {
        if (this.chooseActionHeight == 0) {
            this.mWebView.measure(-1, -1);
            this.chooseActionHeight = (int) (((double) this.mWebView.getMeasuredHeight()) * 0.35d);
        }
    }

    public void setUrlString() {
        if (this.mBankJS != null) {
            try {
                if (this.mBankJS.has("pgUrlList")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(this.mBankJS.getString("pgUrlList").replace(" ", Trace.NULL), "||");
                    while (stringTokenizer.hasMoreTokens()) {
                        this.urlSet.add(stringTokenizer.nextToken());
                    }
                    updateSet(this.urlSet, this.url);
                    return;
                }
                communicationError();
            } catch (Exception e) {
                communicationError();
                e.printStackTrace();
            }
        }
    }

    public void enter_otp(String str) {
        this.mPassword = null;
        prepareSmsListener();
        if (this.chooseActionHeight == 0) {
            this.frameState = REGENERATE_OTP;
        }
        this.checkLoading = true;
        onHelpAvailable();
        if (this.transView != null) {
            this.transView.setVisibility(DEFAULT);
        }
        View inflate = getActivity().getLayoutInflater().inflate(R.wait_for_otp, null);
        ((ImageView) inflate.findViewById(R.bank_logo)).setImageDrawable((Drawable) this.bank_logo_hashmap.get(this.bank_logo));
        View findViewById = inflate.findViewById(R.progress);
        ProgressDialogTestValue.showDialog(getActivity(), findViewById);
        ((ViewGroup) getActivity().findViewById(R.help_view)).removeAllViews();
        ((ViewGroup) getActivity().findViewById(R.help_view)).addView(inflate);
        if (((ViewGroup) getActivity().findViewById(R.help_view)).isShown()) {
            this.frameState = REGENERATE_OTP;
        } else {
            maximiseWebviewHeight();
        }
        try {
            boolean z;
            boolean z2;
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.has(getString(R.regenerate)) && init.getBoolean(getString(R.regenerate))) {
                z = true;
            } else {
                z = DEBUG;
            }
            if (init.has(getString(R.pin)) && init.getBoolean(getString(R.pin))) {
                z2 = true;
            } else {
                z2 = DEBUG;
            }
            new Handler().postDelayed(new Bank(inflate, z, z2), Constants.MILLIS_IN_A_MINUTE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.mPassword != null) {
            ((TextView) inflate.findViewById(R.otp_sms)).setText(this.mPassword);
            Button button = (Button) inflate.findViewById(R.approve);
            button.setText(getString(R.approve_otp));
            button.setClickable(true);
            button.setAlpha(br.DEFAULT_BACKOFF_MULT);
            getActivity().findViewById(R.otp_recieved).setVisibility(DEFAULT);
            ProgressDialogTestValue.removeDialog(findViewById);
            inflate.findViewById(R.retry_text).setVisibility(8);
            inflate.findViewById(R.regenerate_layout).setVisibility(8);
            inflate.findViewById(R.approve).setVisibility(DEFAULT);
            inflate.findViewById(R.waiting).setVisibility(8);
            inflate.findViewById(R.otp_sms).setVisibility(DEFAULT);
            this.buttonClickListener.setView(inflate);
            ((Button) inflate.findViewById(R.approve)).setOnClickListener(this.buttonClickListener);
            if (this.mTimer != null) {
                this.mTimer.cancel();
                this.mTimer = null;
            }
        }
        updateHeight(inflate);
        if (((ViewGroup) getActivity().findViewById(R.help_view)).isShown()) {
            this.frameState = REGENERATE_OTP;
        } else {
            maximiseWebviewHeight();
        }
    }

    public int getCode(String str) {
        if (str.equalsIgnoreCase("pin")) {
            return PIN;
        }
        if (str.equalsIgnoreCase(dt.USER_PASSWORD_KEY)) {
            return PASSWORD;
        }
        if (str.equalsIgnoreCase("enter manually")) {
            return ENTER_MANUALLY;
        }
        if (str.equalsIgnoreCase("approve")) {
            return APPROVE;
        }
        if (str.equalsIgnoreCase("otp")) {
            return OTP;
        }
        if (str.equalsIgnoreCase("sms otp")) {
            return SMS_OTP;
        }
        if (str.equalsIgnoreCase("regenerate otp")) {
            return REGENERATE_OTP;
        }
        return DEFAULT;
    }

    public void update() {
        maximiseWebviewHeight();
        this.frameState = PASSWORD;
        onHelpUnavailable();
    }
}
