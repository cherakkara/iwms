package com.leanplum.messagetemplates;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.leanplum.utils.BitmapUtil;
import com.leanplum.utils.SizeUtil;
import com.leanplum.views.BackgroundImageView;
import com.leanplum.views.CloseButton;
import com.newrelic.agent.android.api.v1.Defaults;
import com.olacabs.customer.p076d.br;

public class BaseMessageDialog extends Dialog {
    private RelativeLayout f8818a;
    private boolean f8819b;
    private boolean f8820c;
    protected C0640j options;
    protected WebInterstitialOptions webOptions;

    protected BaseMessageDialog(Activity activity, boolean z, C0640j c0640j, WebInterstitialOptions webInterstitialOptions) {
        int i;
        View webView;
        if (((activity.getWindow().getAttributes().flags & Defaults.RESPONSE_BODY_LIMIT) == Defaults.RESPONSE_BODY_LIMIT ? 1 : null) != null) {
            i = 16973841;
        } else {
            i = 16973840;
        }
        super(activity, i);
        this.f8819b = false;
        this.f8820c = false;
        SizeUtil.init(activity);
        this.options = c0640j;
        this.webOptions = webInterstitialOptions;
        if (webInterstitialOptions != null) {
            this.f8819b = true;
        }
        this.f8818a = new RelativeLayout(activity);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.f8818a.setBackgroundColor(0);
        this.f8818a.setLayoutParams(layoutParams);
        View relativeLayout = new RelativeLayout(activity);
        if (z) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        } else {
            int dpToPx = SizeUtil.dpToPx(activity, ((CenterPopupOptions) this.options).getWidth());
            int dpToPx2 = SizeUtil.dpToPx(activity, ((CenterPopupOptions) this.options).getHeight());
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            Point point = new Point();
            if (VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point = new Point(defaultDisplay.getHeight(), defaultDisplay.getHeight());
            }
            int i2 = point.x - SizeUtil.dp20;
            int i3 = point.y - SizeUtil.dp20;
            double d = ((double) dpToPx) / ((double) dpToPx2);
            if (dpToPx <= i2 || ((int) (((double) dpToPx) / d)) >= i3) {
                i = dpToPx;
                dpToPx = dpToPx2;
            } else {
                dpToPx = (int) (((double) i2) / d);
                i = i2;
            }
            if (dpToPx <= i3 || ((int) (((double) dpToPx) * d)) >= i2) {
                i3 = dpToPx;
            } else {
                i = (int) (((double) i3) * d);
            }
            layoutParams = new RelativeLayout.LayoutParams(i, i3);
        }
        layoutParams.addRule(13, -1);
        relativeLayout.setLayoutParams(layoutParams);
        Drawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(m12779a(z ? 0 : SizeUtil.dp20));
        shapeDrawable.getPaint().setColor(0);
        if (VERSION.SDK_INT >= 16) {
            relativeLayout.setBackground(shapeDrawable);
        } else {
            relativeLayout.setBackgroundDrawable(shapeDrawable);
        }
        if (this.f8819b) {
            webView = new WebView(activity);
            webView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            webView.setWebViewClient(new C0648h(this));
            webView.loadUrl(this.webOptions.getUrl());
            relativeLayout.addView(webView, webView.getLayoutParams());
        } else {
            View backgroundImageView = new BackgroundImageView((Context) activity, z);
            backgroundImageView.setScaleType(ScaleType.CENTER_CROP);
            i = !z ? SizeUtil.dp20 : 0;
            backgroundImageView.setImageBitmap(this.options.getBackgroundImage());
            Drawable shapeDrawable2 = new ShapeDrawable();
            shapeDrawable2.setShape(m12779a(i));
            shapeDrawable2.getPaint().setColor(this.options.getBackgroundColor());
            if (VERSION.SDK_INT >= 16) {
                backgroundImageView.setBackground(shapeDrawable2);
            } else {
                backgroundImageView.setBackgroundDrawable(shapeDrawable2);
            }
            backgroundImageView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            relativeLayout.addView(backgroundImageView, backgroundImageView.getLayoutParams());
            backgroundImageView = new RelativeLayout(activity);
            backgroundImageView.setLayoutParams(new LayoutParams(-1, -2));
            webView = new TextView(activity);
            webView.setPadding(0, SizeUtil.dp5, 0, SizeUtil.dp5);
            webView.setGravity(17);
            webView.setText(this.options.getTitle());
            webView.setTextColor(this.options.getTitleColor());
            webView.setTextSize(2, 20.0f);
            webView.setTypeface(null, 1);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(14, -1);
            layoutParams2.addRule(15, -1);
            webView.setLayoutParams(layoutParams2);
            backgroundImageView.addView(webView, webView.getLayoutParams());
            backgroundImageView.setId(104);
            relativeLayout.addView(backgroundImageView, backgroundImageView.getLayoutParams());
            View textView = new TextView(activity);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12, -1);
            layoutParams.addRule(14, -1);
            layoutParams.setMargins(0, 0, 0, SizeUtil.dp5);
            textView.setPadding(SizeUtil.dp20, SizeUtil.dp5, SizeUtil.dp20, SizeUtil.dp5);
            textView.setLayoutParams(layoutParams);
            textView.setText(this.options.getAcceptButtonText());
            textView.setTextColor(this.options.getAcceptButtonTextColor());
            textView.setTypeface(null, 1);
            BitmapUtil.stateBackgroundDarkerByPercentage(textView, this.options.getAcceptButtonBackgroundColor(), 30);
            textView.setTextSize(2, 18.0f);
            textView.setOnClickListener(new C0649i(this));
            textView.setId(105);
            relativeLayout.addView(textView, textView.getLayoutParams());
            View textView2 = new TextView(activity);
            textView2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            textView2.setGravity(17);
            textView2.setText(this.options.getMessageText());
            textView2.setTextColor(this.options.getMessageColor());
            textView2.setTextSize(2, 18.0f);
            ((RelativeLayout.LayoutParams) textView2.getLayoutParams()).addRule(3, backgroundImageView.getId());
            ((RelativeLayout.LayoutParams) textView2.getLayoutParams()).addRule(2, textView.getId());
            relativeLayout.addView(textView2, textView2.getLayoutParams());
        }
        relativeLayout.setId(108);
        this.f8818a.addView(relativeLayout, relativeLayout.getLayoutParams());
        if (!this.f8819b || webInterstitialOptions.hasDismissButton()) {
            webView = new CloseButton(activity);
            webView.setId(103);
            LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            if (z) {
                layoutParams3.addRule(10, this.f8818a.getId());
                layoutParams3.addRule(11, this.f8818a.getId());
                layoutParams3.setMargins(0, SizeUtil.dp5, SizeUtil.dp5, 0);
            } else {
                layoutParams3.addRule(6, relativeLayout.getId());
                layoutParams3.addRule(7, relativeLayout.getId());
                layoutParams3.setMargins(0, -SizeUtil.dp7, -SizeUtil.dp7, 0);
            }
            webView.setLayoutParams(layoutParams3);
            webView.setOnClickListener(new C0647g(this));
            this.f8818a.addView(webView, webView.getLayoutParams());
        }
        setContentView(this.f8818a, this.f8818a.getLayoutParams());
        RelativeLayout relativeLayout2 = this.f8818a;
        Animation alphaAnimation = new AlphaAnimation(0.0f, br.DEFAULT_BACKOFF_MULT);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        alphaAnimation.setDuration(350);
        relativeLayout2.setAnimation(alphaAnimation);
        if (!z) {
            getWindow().addFlags(2);
            if (VERSION.SDK_INT >= 14) {
                getWindow().setDimAmount(0.7f);
            }
        }
    }

    public void cancel() {
        if (!this.f8820c) {
            this.f8820c = true;
            Animation alphaAnimation = new AlphaAnimation(br.DEFAULT_BACKOFF_MULT, 0.0f);
            alphaAnimation.setInterpolator(new AccelerateInterpolator());
            alphaAnimation.setDuration(350);
            alphaAnimation.setAnimationListener(new C0645e(this));
            this.f8818a.startAnimation(alphaAnimation);
        }
    }

    private static Shape m12779a(int i) {
        return new RoundRectShape(new float[]{(float) i, (float) i, (float) i, (float) i, (float) i, (float) i, (float) i, (float) i}, null, null);
    }
}
