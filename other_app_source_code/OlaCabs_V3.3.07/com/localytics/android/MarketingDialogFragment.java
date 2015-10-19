package com.localytics.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.localytics.android.Localytics.InAppMessageDismissButtonLocation;
import com.newrelic.agent.android.api.v1.Defaults;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.olacabs.customer.utils.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.HttpHost;
import org.apache.http.protocol.HTTP;

@Instrumented
public final class MarketingDialogFragment extends DialogFragment implements TraceFieldInterface {
    private static final String AMP_DESCRIPTION = "amp_view";
    private static final String CLOSE_BUTTON_DESCRIPTION = "close_button";
    private static final int CLOSE_BUTTON_ID = 1;
    static final String DIALOG_TAG = "marketing_dialog";
    private static final int OPENING_EXTERNAL = 2;
    private static final int OPENING_INTERNAL = 1;
    private static final int PROTOCOL_UNMATCHED = -1;
    private static final int PROTOCOL_UNRECOGNIZED = -2;
    private static final int WEB_VIEW_ID = 2;
    private static InAppMessageDismissButtonLocation dismissButtonLocation;
    private static Bitmap sDismissButtonImage;
    private SparseArray<MarketingCallable> mCallbacks;
    private final AtomicBoolean mEnterAnimatable;
    private final AtomicBoolean mExitAnimatable;
    private JavaScriptClient mJavaScriptClient;
    private MarketingDialog mMarketingDialog;
    private MarketingMessage mMarketingMessage;
    private final AtomicBoolean mUploadedViewEvent;

    /* renamed from: com.localytics.android.MarketingDialogFragment.1 */
    class C06941 extends MarketingCallable {

        /* renamed from: com.localytics.android.MarketingDialogFragment.1.1 */
        class C06931 implements Runnable {
            final /* synthetic */ MarketingWebView val$mWebView;
            final /* synthetic */ String val$url;

            C06931(MarketingWebView marketingWebView, String str) {
                this.val$mWebView = marketingWebView;
                this.val$url = str;
            }

            public void run() {
                this.val$mWebView.loadUrl(this.val$url);
            }
        }

        C06941() {
        }

        Object call(Object[] objArr) {
            String str = (String) objArr[0];
            MarketingWebView access$100 = MarketingDialogFragment.this.mMarketingDialog.mWebView;
            if (MarketingDialogFragment.this.handleUrl(str, MarketingDialogFragment.this.getActivity())) {
                new Handler(Looper.getMainLooper()).post(new C06931(access$100, str));
            }
            return null;
        }
    }

    /* renamed from: com.localytics.android.MarketingDialogFragment.2 */
    class C06952 extends MarketingCallable {
        C06952() {
        }

        Object call(Object[] objArr) {
            MarketingDialogFragment.this.mMarketingDialog.dismissWithAnimation();
            return null;
        }
    }

    final class MarketingDialog extends Dialog {
        private static final String LOCATION_BOTTOM = "bottom";
        private static final String LOCATION_CENTER = "center";
        private static final String LOCATION_FULL = "full";
        private static final String LOCATION_TOP = "top";
        private static final int MARGIN = 10;
        private static final int MAX_BANNER_WIDTH_DIP = 360;
        private TranslateAnimation mAnimBottomIn;
        private TranslateAnimation mAnimBottomOut;
        private TranslateAnimation mAnimCenterIn;
        private TranslateAnimation mAnimCenterOut;
        private TranslateAnimation mAnimFullIn;
        private TranslateAnimation mAnimFullOut;
        private TranslateAnimation mAnimTopIn;
        private TranslateAnimation mAnimTopOut;
        private CloseButton mBtnClose;
        private RelativeLayout mDialogLayout;
        private float mHeight;
        private String mLocation;
        private DisplayMetrics mMetrics;
        private RelativeLayout mRootLayout;
        private MarketingWebView mWebView;
        private float mWidth;

        /* renamed from: com.localytics.android.MarketingDialogFragment.MarketingDialog.1 */
        class C06961 implements Runnable {
            final /* synthetic */ MarketingDialogFragment val$this$0;

            C06961(MarketingDialogFragment marketingDialogFragment) {
                this.val$this$0 = marketingDialogFragment;
            }

            public void run() {
                MarketingDialog.this.dismiss();
            }
        }

        /* renamed from: com.localytics.android.MarketingDialogFragment.MarketingDialog.2 */
        class C06972 implements OnClickListener {
            C06972() {
            }

            public void onClick(View view) {
                if (MarketingDialogFragment.this.mExitAnimatable.getAndSet(false)) {
                    MarketingDialog.this.dismissWithAnimation();
                }
            }
        }

        /* renamed from: com.localytics.android.MarketingDialogFragment.MarketingDialog.3 */
        class C06983 implements AnimationListener {
            C06983() {
            }

            public void onAnimationStart(Animation animation) {
                if (MarketingDialogFragment.this.mCallbacks != null) {
                    MarketingCallable marketingCallable = (MarketingCallable) MarketingDialogFragment.this.mCallbacks.get(17);
                    if (!Constants.isTestModeEnabled() && marketingCallable != null) {
                        marketingCallable.call(null);
                    }
                }
            }

            public void onAnimationEnd(Animation animation) {
                try {
                    MarketingDialogFragment.this.dismiss();
                } catch (Exception e) {
                    LocalyticsManager.throwOrLogError(RuntimeException.class, "Localytics library threw an uncaught exception", e);
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        }

        /* renamed from: com.localytics.android.MarketingDialogFragment.MarketingDialog.4 */
        class C06994 implements AnimationListener {
            C06994() {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (MarketingDialogFragment.this.mCallbacks != null) {
                    MarketingCallable marketingCallable = (MarketingCallable) MarketingDialogFragment.this.mCallbacks.get(16);
                    if (!Constants.isTestModeEnabled() && marketingCallable != null) {
                        Object[] objArr = new Object[MarketingDialogFragment.OPENING_INTERNAL];
                        objArr[0] = MarketingDialogFragment.this.mMarketingMessage;
                        marketingCallable.call(objArr);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        }

        /* renamed from: com.localytics.android.MarketingDialogFragment.MarketingDialog.5 */
        class C07005 implements Runnable {
            C07005() {
            }

            public void run() {
                String str = (String) MarketingDialogFragment.this.mMarketingMessage.get("location");
                if (str.equals(MarketingDialog.LOCATION_CENTER)) {
                    MarketingDialog.this.mRootLayout.startAnimation(MarketingDialog.this.mAnimCenterOut);
                } else if (str.equals(MarketingDialog.LOCATION_FULL)) {
                    MarketingDialog.this.mRootLayout.startAnimation(MarketingDialog.this.mAnimFullOut);
                } else if (str.equals(MarketingDialog.LOCATION_TOP)) {
                    MarketingDialog.this.mRootLayout.startAnimation(MarketingDialog.this.mAnimTopOut);
                } else if (str.equals(MarketingDialog.LOCATION_BOTTOM)) {
                    MarketingDialog.this.mRootLayout.startAnimation(MarketingDialog.this.mAnimBottomOut);
                }
            }
        }

        final class CloseButton extends View {
            private Bitmap mBitmap;
            private final float mCenterX;
            private final float mCenterY;
            private final float mInnerRadius;
            private final float mOffset;
            private final Paint mPaint;
            private final float mRadius;
            private final Paint mShadowInnerPaint;
            private final Paint mShadowOuterPaint;
            private final float mStrokeWidth;

            @TargetApi(11)
            CloseButton(Context context, AttributeSet attributeSet) {
                float f;
                super(context, attributeSet);
                setId(MarketingDialogFragment.OPENING_INTERNAL);
                setContentDescription(MarketingDialogFragment.CLOSE_BUTTON_DESCRIPTION);
                if (DatapointHelper.getApiLevel() >= 14) {
                    setLayerType(MarketingDialogFragment.OPENING_INTERNAL, null);
                }
                float f2 = getResources().getDisplayMetrics().density;
                this.mCenterX = 13.0f * f2;
                this.mCenterY = 13.0f * f2;
                this.mRadius = 13.0f * f2;
                this.mOffset = 5.0f * f2;
                this.mStrokeWidth = 2.5f * f2;
                this.mInnerRadius = this.mRadius - (this.mStrokeWidth * 0.5f);
                this.mPaint = new Paint(MarketingDialogFragment.OPENING_INTERNAL);
                this.mShadowInnerPaint = new Paint(MarketingDialogFragment.OPENING_INTERNAL);
                this.mShadowInnerPaint.setMaskFilter(new BlurMaskFilter(this.mRadius - f2, Blur.INNER));
                this.mShadowOuterPaint = new Paint(MarketingDialogFragment.OPENING_INTERNAL);
                this.mShadowOuterPaint.setMaskFilter(new BlurMaskFilter(dm.DEFAULT_BACKOFF_MULT * f2, Blur.OUTER));
                if (MarketingDialogFragment.sDismissButtonImage == null) {
                    f = 30.0f;
                } else {
                    f = 40.0f;
                }
                f = (f * f2) + 0.5f;
                setLayoutParams(new LayoutParams((int) f, (int) f));
                this.mBitmap = Bitmap.createBitmap((int) ((26.0f * f2) + 0.5f), (int) ((26.0f * f2) + 0.5f), Config.ARGB_8888);
                Canvas canvas = new Canvas(this.mBitmap);
                this.mPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.mPaint.setStyle(Style.FILL);
                canvas.drawCircle(this.mCenterX, this.mCenterY, this.mRadius, this.mPaint);
                this.mPaint.setColor(MarketingDialogFragment.PROTOCOL_UNMATCHED);
                this.mPaint.setStyle(Style.STROKE);
                this.mPaint.setStrokeWidth(this.mStrokeWidth);
                canvas.drawCircle(this.mCenterX, this.mCenterY, this.mInnerRadius, this.mPaint);
                this.mPaint.setStrokeWidth(f2 * 4.5f);
                canvas.drawLine(this.mCenterX - this.mOffset, this.mCenterY - this.mOffset, this.mCenterX + this.mOffset, this.mCenterY + this.mOffset, this.mPaint);
                canvas.drawLine(this.mCenterX - this.mOffset, this.mCenterY + this.mOffset, this.mCenterX + this.mOffset, this.mCenterY - this.mOffset, this.mPaint);
            }

            protected void onDraw(Canvas canvas) {
                Bitmap access$1600;
                float height;
                super.onDraw(canvas);
                float f = getResources().getDisplayMetrics().density;
                if (MarketingDialogFragment.sDismissButtonImage != null) {
                    access$1600 = MarketingDialogFragment.sDismissButtonImage;
                    height = ((float) (access$1600.getHeight() - access$1600.getWidth())) / dm.DEFAULT_BACKOFF_MULT;
                } else if (this.mBitmap != null) {
                    float f2;
                    if (MarketingDialogFragment.dismissButtonLocation == InAppMessageDismissButtonLocation.LEFT) {
                        f2 = f;
                        height = 0.0f;
                    } else {
                        height = 4.0f * f;
                        f2 = 3.0f * f;
                    }
                    canvas.drawCircle(this.mCenterX + f2, this.mCenterY + f, this.mRadius - f, this.mShadowInnerPaint);
                    canvas.drawCircle(f2 + this.mCenterX, this.mCenterY + f, this.mRadius - f, this.mShadowOuterPaint);
                    access$1600 = this.mBitmap;
                } else {
                    return;
                }
                canvas.drawBitmap(access$1600, height, 0.0f, this.mPaint);
            }

            public void release() {
                if (this.mBitmap != null) {
                    this.mBitmap.recycle();
                    this.mBitmap = null;
                }
            }
        }

        final class MarketingWebView extends WebView {

            final class MarketingWebViewClient extends WebViewClient {
                private final Activity mActivity;

                MarketingWebViewClient(Activity activity) {
                    this.mActivity = activity;
                }

                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    return MarketingDialogFragment.this.handleUrl(str, this.mActivity);
                }

                public void onPageFinished(WebView webView, String str) {
                    int i = ((String) MarketingDialogFragment.this.mMarketingMessage.get("location")).equals(MarketingDialog.LOCATION_CENTER) ? ((int) ((10.0f * MarketingDialog.this.mMetrics.density) + 0.5f)) << MarketingDialogFragment.OPENING_INTERNAL : 0;
                    int min = Math.min(MarketingDialog.this.mMetrics.widthPixels, MarketingDialog.this.mMetrics.heightPixels) - i;
                    float min2 = ((float) Math.min(Math.max(MarketingDialog.this.mMetrics.widthPixels, MarketingDialog.this.mMetrics.heightPixels) - i, (int) ((MarketingDialog.this.mHeight * MarketingDialog.this.mMetrics.density) + 0.5f))) / MarketingDialog.this.mMetrics.density;
                    Object[] objArr = new Object[MarketingDialogFragment.WEB_VIEW_ID];
                    objArr[0] = Float.valueOf(((float) Math.min(min, (int) ((MarketingDialog.this.mWidth * MarketingDialog.this.mMetrics.density) + 0.5f))) / MarketingDialog.this.mMetrics.density);
                    objArr[MarketingDialogFragment.OPENING_INTERNAL] = Float.valueOf(min2);
                    webView.loadUrl(String.format("javascript:(function() {  var viewportNode = document.createElement('meta');  viewportNode.name    = 'viewport';  viewportNode.content = 'width=%f, height=%f, user-scalable=no, minimum-scale=.25, maximum-scale=1';  viewportNode.id      = 'metatag';  document.getElementsByTagName('head')[0].appendChild(viewportNode);})()", objArr));
                    webView.loadUrl(MarketingDialogFragment.this.mJavaScriptClient.getJsGlueCode());
                    MarketingDialog.this.mRootLayout.setVisibility(0);
                    if (MarketingDialogFragment.this.mEnterAnimatable.getAndSet(false)) {
                        MarketingDialog.this.enterWithAnimation();
                    }
                }
            }

            @SuppressLint({"SetJavaScriptEnabled"})
            @TargetApi(16)
            MarketingWebView(Context context, AttributeSet attributeSet) {
                super(context, attributeSet);
                if (DatapointHelper.getApiLevel() >= 19) {
                    setLayerType(MarketingDialogFragment.OPENING_INTERNAL, null);
                }
                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(MarketingDialogFragment.PROTOCOL_UNMATCHED, MarketingDialogFragment.PROTOCOL_UNMATCHED);
                layoutParams.gravity = 17;
                setLayoutParams(layoutParams);
                setBackgroundColor(0);
                setInitialScale(MarketingDialogFragment.OPENING_INTERNAL);
                setHorizontalScrollBarEnabled(false);
                setVerticalScrollBarEnabled(false);
                setWebViewClient(new MarketingWebViewClient(MarketingDialogFragment.this.getActivity()));
                WebSettings settings = getSettings();
                settings.setJavaScriptEnabled(true);
                addJavascriptInterface(MarketingDialogFragment.this.mJavaScriptClient, "localytics");
                settings.setUseWideViewPort(true);
                if (DatapointHelper.getApiLevel() >= 16) {
                    settings.setAllowUniversalAccessFromFileURLs(true);
                }
            }
        }

        MarketingDialog(Context context, int i) {
            super(context, i);
            if (MarketingDialogFragment.this.mMarketingMessage == null) {
                MarketingDialogFragment.this.dismiss();
                return;
            }
            this.mLocation = (String) MarketingDialogFragment.this.mMarketingMessage.get("location");
            setupViews();
            createAnimations();
            adjustLayout();
            String str = (String) MarketingDialogFragment.this.mMarketingMessage.get("html_url");
            if (str != null) {
                this.mWebView.loadUrl(str);
            } else {
                new Handler(Looper.getMainLooper()).post(new C06961(MarketingDialogFragment.this));
            }
        }

        private void setupViews() {
            this.mRootLayout = new RelativeLayout(getContext());
            this.mRootLayout.setVisibility(4);
            this.mRootLayout.setContentDescription(MarketingDialogFragment.AMP_DESCRIPTION);
            this.mRootLayout.setLayoutParams(new LayoutParams(MarketingDialogFragment.PROTOCOL_UNRECOGNIZED, MarketingDialogFragment.PROTOCOL_UNRECOGNIZED));
            this.mDialogLayout = new RelativeLayout(getContext());
            ViewGroup.LayoutParams layoutParams = new LayoutParams(MarketingDialogFragment.PROTOCOL_UNRECOGNIZED, MarketingDialogFragment.PROTOCOL_UNRECOGNIZED);
            layoutParams.addRule(13);
            this.mDialogLayout.setLayoutParams(layoutParams);
            this.mRootLayout.addView(this.mDialogLayout);
            this.mWebView = new MarketingWebView(getContext(), null);
            this.mWebView.setId(MarketingDialogFragment.WEB_VIEW_ID);
            this.mDialogLayout.addView(this.mWebView);
            this.mBtnClose = new CloseButton(getContext(), null);
            this.mBtnClose.setOnClickListener(new C06972());
            if (MarketingDialogFragment.dismissButtonLocation == InAppMessageDismissButtonLocation.RIGHT) {
                LayoutParams layoutParams2 = (LayoutParams) this.mBtnClose.getLayoutParams();
                layoutParams2.addRule(7, this.mWebView.getId());
                this.mBtnClose.setLayoutParams(layoutParams2);
            }
            this.mDialogLayout.addView(this.mBtnClose);
            requestWindowFeature(MarketingDialogFragment.OPENING_INTERNAL);
            setContentView(this.mRootLayout);
        }

        private void createAnimations() {
            this.mAnimCenterIn = new TranslateAnimation(MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, br.DEFAULT_BACKOFF_MULT, MarketingDialogFragment.WEB_VIEW_ID, 0.0f);
            this.mAnimCenterIn.setDuration(500);
            this.mAnimCenterOut = new TranslateAnimation(MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, br.DEFAULT_BACKOFF_MULT);
            this.mAnimCenterOut.setDuration(500);
            this.mAnimTopIn = new TranslateAnimation(MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, -1.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f);
            this.mAnimTopIn.setDuration(500);
            this.mAnimTopOut = new TranslateAnimation(MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, -1.0f);
            this.mAnimTopOut.setDuration(500);
            this.mAnimBottomIn = new TranslateAnimation(MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, br.DEFAULT_BACKOFF_MULT, MarketingDialogFragment.WEB_VIEW_ID, 0.0f);
            this.mAnimBottomIn.setDuration(500);
            this.mAnimBottomOut = new TranslateAnimation(MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, br.DEFAULT_BACKOFF_MULT);
            this.mAnimBottomOut.setDuration(500);
            this.mAnimFullIn = new TranslateAnimation(MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, br.DEFAULT_BACKOFF_MULT, MarketingDialogFragment.WEB_VIEW_ID, 0.0f);
            this.mAnimFullIn.setDuration(500);
            this.mAnimFullOut = new TranslateAnimation(MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, 0.0f, MarketingDialogFragment.WEB_VIEW_ID, br.DEFAULT_BACKOFF_MULT);
            this.mAnimFullOut.setDuration(500);
            AnimationListener c06983 = new C06983();
            this.mAnimCenterOut.setAnimationListener(c06983);
            this.mAnimTopOut.setAnimationListener(c06983);
            this.mAnimBottomOut.setAnimationListener(c06983);
            this.mAnimFullOut.setAnimationListener(c06983);
            c06983 = new C06994();
            this.mAnimCenterIn.setAnimationListener(c06983);
            this.mAnimTopIn.setAnimationListener(c06983);
            this.mAnimBottomIn.setAnimationListener(c06983);
            this.mAnimFullIn.setAnimationListener(c06983);
        }

        @SuppressLint({"NewApi"})
        private void adjustLayout() {
            this.mMetrics = new DisplayMetrics();
            ((WindowManager) MarketingDialogFragment.this.getActivity().getSystemService("window")).getDefaultDisplay().getMetrics(this.mMetrics);
            this.mWidth = ((Float) MarketingDialogFragment.this.mMarketingMessage.get("display_width")).floatValue();
            this.mHeight = ((Float) MarketingDialogFragment.this.mMarketingMessage.get("display_height")).floatValue();
            float f = this.mHeight / this.mWidth;
            float min = Math.min(360.0f * this.mMetrics.density, (float) Math.min(this.mMetrics.widthPixels, this.mMetrics.heightPixels));
            Window window = getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setFlags(32, 32);
            if (this.mLocation.equals(LOCATION_CENTER)) {
                window.setLayout(this.mMetrics.widthPixels, this.mMetrics.heightPixels);
                int i = (int) ((10.0f * this.mMetrics.density) + 0.5f);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mWebView.getLayoutParams();
                marginLayoutParams.width = ((int) Math.min(min - ((float) (i << MarketingDialogFragment.OPENING_INTERNAL)), (float) ((int) ((this.mWidth * this.mMetrics.density) + 0.5f)))) + (i << MarketingDialogFragment.OPENING_INTERNAL);
                marginLayoutParams.height = ((int) (f * Math.min(min - ((float) (i << MarketingDialogFragment.OPENING_INTERNAL)), (float) ((int) ((this.mWidth * this.mMetrics.density) + 0.5f))))) + (i << MarketingDialogFragment.OPENING_INTERNAL);
                marginLayoutParams.setMargins(i, i, i, i);
                this.mWebView.setLayoutParams(marginLayoutParams);
                this.mWebView.requestLayout();
                LayoutParams layoutParams = (LayoutParams) this.mBtnClose.getLayoutParams();
                layoutParams.setMargins(0, 0, -i, 0);
                this.mBtnClose.setLayoutParams(layoutParams);
                this.mBtnClose.requestLayout();
            } else if (this.mLocation.equals(LOCATION_FULL)) {
                window.setLayout(this.mMetrics.widthPixels, this.mMetrics.heightPixels);
            } else if (this.mLocation.equals(LOCATION_TOP)) {
                attributes.y = -268435455;
                attributes.dimAmount = 0.0f;
                window.setLayout((int) min, (int) ((f * min) + 0.5f));
            } else if (this.mLocation.equals(LOCATION_BOTTOM)) {
                attributes.y = 268435455;
                attributes.dimAmount = 0.0f;
                window.setLayout((int) min, (int) ((f * min) + 0.5f));
            }
            window.setFlags(Defaults.RESPONSE_BODY_LIMIT, Defaults.RESPONSE_BODY_LIMIT);
        }

        void dismissWithAnimation() {
            new Handler(Looper.getMainLooper()).post(new C07005());
        }

        void enterWithAnimation() {
            String str = (String) MarketingDialogFragment.this.mMarketingMessage.get("location");
            if (str.equals(LOCATION_CENTER)) {
                this.mRootLayout.startAnimation(this.mAnimCenterIn);
            } else if (str.equals(LOCATION_FULL)) {
                this.mRootLayout.startAnimation(this.mAnimFullIn);
            } else if (str.equals(LOCATION_TOP)) {
                this.mRootLayout.startAnimation(this.mAnimTopIn);
            } else if (str.equals(LOCATION_BOTTOM)) {
                this.mRootLayout.startAnimation(this.mAnimBottomIn);
            }
        }

        protected void onStop() {
            if (this.mBtnClose != null) {
                this.mBtnClose.release();
            }
            super.onStop();
        }

        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            if (i != 4) {
                return super.onKeyDown(i, keyEvent);
            }
            if (MarketingDialogFragment.this.mExitAnimatable.getAndSet(false)) {
                dismissWithAnimation();
            }
            return true;
        }
    }

    static {
        sDismissButtonImage = null;
        dismissButtonLocation = InAppMessageDismissButtonLocation.LEFT;
    }

    public MarketingDialogFragment() {
        this.mEnterAnimatable = new AtomicBoolean(true);
        this.mExitAnimatable = new AtomicBoolean(true);
        this.mUploadedViewEvent = new AtomicBoolean(false);
    }

    static MarketingDialogFragment newInstance() {
        MarketingDialogFragment marketingDialogFragment = new MarketingDialogFragment();
        marketingDialogFragment.setRetainInstance(true);
        return marketingDialogFragment;
    }

    static void setDismissButtonImage(Bitmap bitmap) {
        if (sDismissButtonImage != null) {
            sDismissButtonImage.recycle();
        }
        sDismissButtonImage = bitmap;
    }

    static InAppMessageDismissButtonLocation getInAppDismissButtonLocation() {
        return dismissButtonLocation;
    }

    static void setInAppDismissButtonLocation(InAppMessageDismissButtonLocation inAppMessageDismissButtonLocation) {
        dismissButtonLocation = inAppMessageDismissButtonLocation;
    }

    void dismissCampaign() {
        if (this.mMarketingDialog != null) {
            if (this.mMarketingMessage != null) {
                tagMarketingActionEventWithAction("X");
            }
            this.mMarketingDialog.dismiss();
        }
    }

    public void onAttach(Activity activity) {
        Log.m12799v("[InAppDialogFragment]: onAttach");
        super.onAttach(activity);
    }

    public void onDetach() {
        Log.m12799v("[InAppDialogFragment]: onDetach");
        super.onDetach();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("MarketingDialogFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "MarketingDialogFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "MarketingDialogFragment#onCreate", null);
                break;
            }
        }
        Log.m12799v("[InAppDialogFragment]: onCreate");
        super.onCreate(bundle);
        TraceMachine.exitMethod();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Log.m12799v("[InAppDialogFragment]: onCreateDialog");
        Dialog marketingDialog = new MarketingDialog(getActivity(), 16973835);
        this.mMarketingDialog = marketingDialog;
        return marketingDialog;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        Log.m12799v("[InAppDialogFragment]: onDismiss");
        try {
            if (this.mMarketingMessage != null) {
                tagMarketingActionEventWithAction("X");
            }
        } catch (Throwable e) {
            Log.m12796e("MarketingDialogFragment onDismiss", e);
        }
        super.onDismiss(dialogInterface);
    }

    public void onActivityCreated(Bundle bundle) {
        Log.m12799v("[InAppDialogFragment]: onActivityCreated");
        super.onActivityCreated(bundle);
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        Log.m12799v("[InAppDialogFragment]: onStart");
        super.onStart();
    }

    public void onSaveInstanceState(Bundle bundle) {
        Log.m12799v("[InAppDialogFragment]: onSaveInstanceState");
        super.onSaveInstanceState(bundle);
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        Log.m12799v("[InAppDialogFragment]: onStop");
        super.onStop();
    }

    public void onDestroyView() {
        Log.m12799v("[InAppDialogFragment]: onDestroyView");
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setOnDismissListener(null);
        }
        super.onDestroyView();
    }

    private void tagMarketingActionEventWithAction(String str) {
        if (!Constants.isTestModeEnabled()) {
            Object[] objArr;
            if (this.mUploadedViewEvent.getAndSet(true)) {
                objArr = new Object[OPENING_INTERNAL];
                objArr[0] = str;
                Log.m12801w(String.format("The in-app action for this message has already been set. Ignoring in-app Action: [%s]", objArr));
                return;
            }
            TreeMap treeMap = new TreeMap();
            treeMap.put("ampAction", str);
            treeMap.put(Constants.BUNDLE_TYPE, "In-App");
            treeMap.put("ampCampaignId", this.mMarketingMessage.get("campaign_id").toString());
            treeMap.put("ampCampaign", this.mMarketingMessage.get("rule_name_non_unique").toString());
            treeMap.put("Schema Version - Client", String.valueOf(3));
            Object obj = this.mMarketingMessage.get("schema_version");
            if (obj != null) {
                treeMap.put("Schema Version - Server", obj.toString());
            }
            String str2 = (String) this.mMarketingMessage.get("ab_test");
            if (!TextUtils.isEmpty(str2)) {
                treeMap.put("ampAB", str2);
            }
            if (this.mCallbacks != null) {
                MarketingCallable marketingCallable = (MarketingCallable) this.mCallbacks.get(WEB_VIEW_ID);
                if (marketingCallable != null) {
                    Object[] objArr2 = new Object[WEB_VIEW_ID];
                    objArr2[0] = "ampView";
                    objArr2[OPENING_INTERNAL] = treeMap;
                    marketingCallable.call(objArr2);
                }
                if (Constants.IS_LOGGING_ENABLED) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Entry entry : treeMap.entrySet()) {
                        stringBuilder.append("Key = ").append((String) entry.getKey()).append(", Value = ").append((String) entry.getValue());
                    }
                    objArr = new Object[OPENING_INTERNAL];
                    objArr[0] = stringBuilder.toString();
                    Log.m12799v(String.format("In-app event tagged successfully.\n   Attributes Dictionary = \n%s", objArr));
                }
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "MarketingDialogFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "MarketingDialogFragment#onCreateView", null);
                break;
            }
        }
        Log.m12799v("[InAppDialogFragment]: onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        TraceMachine.exitMethod();
        return onCreateView;
    }

    public void onViewStateRestored(Bundle bundle) {
        Log.m12799v("[InAppDialogFragment]: onViewStateRestored");
        super.onViewStateRestored(bundle);
    }

    public void onResume() {
        Log.m12799v("[InAppDialogFragment]: onResume");
        super.onResume();
    }

    public void onPause() {
        Log.m12799v("[InAppDialogFragment]: onPause");
        super.onPause();
    }

    public void onDestroy() {
        Log.m12799v("[InAppDialogFragment]: onDestroy");
        if (this.mCallbacks != null) {
            MarketingCallable marketingCallable = (MarketingCallable) this.mCallbacks.get(OPENING_INTERNAL);
            if (!(Constants.isTestModeEnabled() || marketingCallable == null)) {
                Object[] objArr = new Object[OPENING_INTERNAL];
                objArr[0] = this.mMarketingMessage;
                marketingCallable.call(objArr);
            }
        }
        super.onDestroy();
    }

    MarketingDialogFragment setData(MarketingMessage marketingMessage) {
        this.mMarketingMessage = marketingMessage;
        return this;
    }

    MarketingDialogFragment setJavaScriptClient(JavaScriptClient javaScriptClient) {
        this.mJavaScriptClient = javaScriptClient;
        SparseArray callbacks = this.mJavaScriptClient.getCallbacks();
        callbacks.put(15, new C06941());
        callbacks.put(4, new C06952());
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean handleUrl(java.lang.String r10, android.app.Activity r11) {
        /*
        r9 = this;
        r0 = 1;
        r1 = 0;
        r8 = 2;
        r2 = "[In-app Nav Handler]: Evaluating in-app URL:\n\tURL:%s";
        r3 = new java.lang.Object[r0];
        r3[r1] = r10;
        r2 = java.lang.String.format(r2, r3);
        com.localytics.android.Localytics.Log.m12801w(r2);
        r2 = "://";
        r2 = r10.endsWith(r2);	 Catch:{ Exception -> 0x0082, all -> 0x00b3 }
        if (r2 == 0) goto L_0x00c4;
    L_0x0018:
        r2 = 0;
        r3 = r10.length();	 Catch:{ Exception -> 0x0082, all -> 0x00b3 }
        r3 = r3 + -1;
        r3 = r10.substring(r2, r3);	 Catch:{ Exception -> 0x0082, all -> 0x00b3 }
    L_0x0023:
        r2 = new java.net.URI;	 Catch:{ Exception -> 0x00bf, all -> 0x00b3 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x00bf, all -> 0x00b3 }
        r9.tagMarketingActionForURL(r2);	 Catch:{ Exception -> 0x00bf, all -> 0x00b3 }
        r4 = new java.net.URL;	 Catch:{ Exception -> 0x00bf, all -> 0x00b3 }
        r4.<init>(r3);	 Catch:{ Exception -> 0x00bf, all -> 0x00b3 }
        r2 = r9.handleFileProtocolRequest(r4);	 Catch:{ Exception -> 0x00bf, all -> 0x00b3 }
        if (r2 <= 0) goto L_0x0042;
    L_0x0036:
        if (r2 != r8) goto L_0x0040;
    L_0x0038:
        if (r2 != r8) goto L_0x003f;
    L_0x003a:
        r1 = r9.mMarketingDialog;
        r1.dismissWithAnimation();
    L_0x003f:
        return r0;
    L_0x0040:
        r0 = r1;
        goto L_0x0038;
    L_0x0042:
        r2 = r9.handleHttpProtocolRequest(r4, r11);	 Catch:{ Exception -> 0x00c2 }
        if (r2 <= 0) goto L_0x0054;
    L_0x0048:
        if (r2 != r8) goto L_0x0052;
    L_0x004a:
        if (r2 != r8) goto L_0x003f;
    L_0x004c:
        r1 = r9.mMarketingDialog;
        r1.dismissWithAnimation();
        goto L_0x003f;
    L_0x0052:
        r0 = r1;
        goto L_0x004a;
    L_0x0054:
        r2 = r9.handleCustomProtocolRequest(r4, r11);	 Catch:{ Exception -> 0x00c2 }
        if (r2 <= 0) goto L_0x0066;
    L_0x005a:
        if (r2 != r8) goto L_0x0064;
    L_0x005c:
        if (r2 != r8) goto L_0x003f;
    L_0x005e:
        r1 = r9.mMarketingDialog;
        r1.dismissWithAnimation();
        goto L_0x003f;
    L_0x0064:
        r0 = r1;
        goto L_0x005c;
    L_0x0066:
        r5 = "[In-app Nav Handler]: Protocol handler scheme not recognized. Attempting to load the URL... [Scheme: %s]";
        r6 = 1;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x00c2 }
        r7 = 0;
        r4 = r4.getProtocol();	 Catch:{ Exception -> 0x00c2 }
        r6[r7] = r4;	 Catch:{ Exception -> 0x00c2 }
        r4 = java.lang.String.format(r5, r6);	 Catch:{ Exception -> 0x00c2 }
        com.localytics.android.Localytics.Log.m12801w(r4);	 Catch:{ Exception -> 0x00c2 }
        if (r2 != r8) goto L_0x0080;
    L_0x007b:
        r0 = r9.mMarketingDialog;
        r0.dismissWithAnimation();
    L_0x0080:
        r0 = r1;
        goto L_0x003f;
    L_0x0082:
        r2 = move-exception;
        r2 = r1;
        r3 = r10;
    L_0x0085:
        r2 = r9.handleCustomProtocolRequest(r3, r11);	 Catch:{ all -> 0x00bc }
        if (r2 <= 0) goto L_0x0097;
    L_0x008b:
        if (r2 != r8) goto L_0x0095;
    L_0x008d:
        if (r2 != r8) goto L_0x003f;
    L_0x008f:
        r1 = r9.mMarketingDialog;
        r1.dismissWithAnimation();
        goto L_0x003f;
    L_0x0095:
        r0 = r1;
        goto L_0x008d;
    L_0x0097:
        r0 = "[In-app Nav Handler]: Invalid url %s";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00bc }
        r4 = 0;
        r3[r4] = r10;	 Catch:{ all -> 0x00bc }
        r0 = java.lang.String.format(r0, r3);	 Catch:{ all -> 0x00bc }
        com.localytics.android.Localytics.Log.m12801w(r0);	 Catch:{ all -> 0x00bc }
        r0 = r9.mMarketingDialog;	 Catch:{ all -> 0x00bc }
        r0.dismissWithAnimation();	 Catch:{ all -> 0x00bc }
        if (r2 != r8) goto L_0x0080;
    L_0x00ad:
        r0 = r9.mMarketingDialog;
        r0.dismissWithAnimation();
        goto L_0x0080;
    L_0x00b3:
        r0 = move-exception;
    L_0x00b4:
        if (r1 != r8) goto L_0x00bb;
    L_0x00b6:
        r1 = r9.mMarketingDialog;
        r1.dismissWithAnimation();
    L_0x00bb:
        throw r0;
    L_0x00bc:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00b4;
    L_0x00bf:
        r2 = move-exception;
        r2 = r1;
        goto L_0x0085;
    L_0x00c2:
        r4 = move-exception;
        goto L_0x0085;
    L_0x00c4:
        r3 = r10;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.localytics.android.MarketingDialogFragment.handleUrl(java.lang.String, android.app.Activity):boolean");
    }

    private void tagMarketingActionForURL(URI uri) {
        Object valueByQueryKey = getValueByQueryKey("ampAction", uri);
        if (TextUtils.isEmpty(valueByQueryKey)) {
            String scheme = uri.getScheme();
            if (!scheme.equals("file") && !scheme.equals(HttpHost.DEFAULT_SCHEME_NAME) && !scheme.equals(com.apsalar.sdk.Constants.API_PROTOCOL)) {
                tagMarketingActionEventWithAction("click");
                return;
            }
            return;
        }
        Object[] objArr = new Object[OPENING_INTERNAL];
        objArr[0] = valueByQueryKey;
        Log.m12801w(String.format("Attempting to tag event with custom in-app action. [Action: %s]", objArr));
        tagMarketingActionEventWithAction(valueByQueryKey);
    }

    private int handleFileProtocolRequest(URL url) {
        if (!url.getProtocol().equals("file")) {
            return PROTOCOL_UNMATCHED;
        }
        Log.m12801w("[In-app Nav Handler]: Displaying content from your local creatives.");
        return OPENING_INTERNAL;
    }

    private int handleHttpProtocolRequest(URL url, Activity activity) {
        String protocol = url.getProtocol();
        if (!protocol.equals(HttpHost.DEFAULT_SCHEME_NAME) && !protocol.equals(com.apsalar.sdk.Constants.API_PROTOCOL)) {
            return PROTOCOL_UNMATCHED;
        }
        Log.m12801w("[In-app Nav Handler]: Handling a request for an external HTTP address.");
        Object valueByQueryKey = getValueByQueryKey("ampExternalOpen", url);
        if (!TextUtils.isEmpty(valueByQueryKey) && valueByQueryKey.toLowerCase(Locale.US).equals("true")) {
            Object[] objArr = new Object[OPENING_INTERNAL];
            objArr[0] = "ampExternalOpen";
            Log.m12801w(String.format("[In-app Nav Handler]: Query string hook [%s] set to true. Opening the URL in chrome", objArr));
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url.toString()));
            if (this.mMarketingDialog.getContext().getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                activity.startActivity(intent);
                return WEB_VIEW_ID;
            }
        }
        Log.m12801w("[In-app Nav Handler]: Loading HTTP request inside the current in-app view");
        return OPENING_INTERNAL;
    }

    private int handleCustomProtocolRequest(URL url, Activity activity) {
        return handleCustomProtocolRequest(url.toString(), activity);
    }

    private int handleCustomProtocolRequest(String str, Activity activity) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        if (this.mMarketingDialog.getContext().getPackageManager().queryIntentActivities(intent, 0).size() <= 0) {
            return PROTOCOL_UNRECOGNIZED;
        }
        Log.m12801w("[In-app Nav Handler]: An app on this device is registered to handle this protocol scheme. Opening...");
        intent.setFlags(AccessibilityNodeInfoCompat.ACTION_SET_SELECTION);
        activity.startActivity(intent);
        return WEB_VIEW_ID;
    }

    private String getValueByQueryKey(String str, URI uri) {
        String str2 = null;
        CharSequence query = uri.getQuery();
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(query))) {
            String[] split = uri.getQuery().split("[&]");
            int length = split.length;
            int i = 0;
            while (i < length) {
                String[] split2 = split[i].split("[=]");
                if (split2[0].compareTo(str) == 0 && WEB_VIEW_ID == split2.length) {
                    try {
                        str2 = URLDecoder.decode(split2[OPENING_INTERNAL], HTTP.UTF_8);
                        break;
                    } catch (UnsupportedEncodingException e) {
                    }
                } else {
                    i += OPENING_INTERNAL;
                }
            }
        }
        return str2;
    }

    private String getValueByQueryKey(String str, URL url) {
        try {
            return getValueByQueryKey(str, url.toURI());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public MarketingDialogFragment setCallbacks(SparseArray<MarketingCallable> sparseArray) {
        this.mCallbacks = sparseArray;
        return this;
    }
}
