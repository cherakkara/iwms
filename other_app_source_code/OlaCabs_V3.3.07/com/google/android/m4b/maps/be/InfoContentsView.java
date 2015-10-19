package com.google.android.m4b.maps.be;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

/* renamed from: com.google.android.m4b.maps.be.f */
public final class InfoContentsView extends LinearLayout {
    private final TextView f6009a;
    private final TextView f6010b;

    private InfoContentsView(Context context, TextView textView, TextView textView2) {
        super(context);
        this.f6009a = textView;
        this.f6010b = textView2;
    }

    public static InfoContentsView m9380a(Context context) {
        View textView = new TextView(context);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        textView.setLayoutParams(layoutParams);
        textView.setEllipsize(TruncateAt.END);
        textView.setSingleLine(true);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setTextSize(14.0f);
        textView.setTypeface(null, 1);
        View textView2 = new TextView(context);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView2.setEllipsize(TruncateAt.END);
        textView2.setSingleLine(true);
        textView2.setTextColor(-7829368);
        textView2.setTextSize(14.0f);
        InfoContentsView infoContentsView = new InfoContentsView(context, textView, textView2);
        infoContentsView.addView(textView);
        infoContentsView.addView(textView2);
        infoContentsView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        infoContentsView.setOrientation(1);
        return infoContentsView;
    }

    public final void m9381a(String str) {
        this.f6009a.setText(str);
        this.f6009a.setVisibility(str != null ? 0 : 8);
    }

    public final void m9382b(String str) {
        this.f6010b.setText(str);
        this.f6010b.setVisibility(str != null ? 0 : 8);
    }
}
