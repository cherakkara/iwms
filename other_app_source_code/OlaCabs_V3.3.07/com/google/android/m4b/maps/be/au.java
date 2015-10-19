package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.m4b.maps.R.R;

/* compiled from: StreetViewPanoramaOverlayContainer */
public final class au {
    private final RelativeLayout f5691a;
    private final Context f5692b;
    private ImageView f5693c;
    private TextView f5694d;
    private TextView f5695e;
    private final Resources f5696f;

    public au(Context context, Resources resources) {
        this.f5691a = new RelativeLayout(context);
        this.f5691a.setLayoutParams(new LayoutParams(-1, -1));
        this.f5692b = context;
        this.f5696f = resources;
        this.f5691a.setVisibility(8);
        this.f5694d = m8697c();
        this.f5694d.setText("Report a problem");
        this.f5694d.setId(1);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(11);
        layoutParams.addRule(12);
        layoutParams.setMargins(10, 0, 0, 0);
        this.f5691a.addView(this.f5694d, layoutParams);
        this.f5695e = m8697c();
        this.f5695e.setId(2);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(0, this.f5694d.getId());
        layoutParams.addRule(12);
        this.f5691a.addView(this.f5695e, layoutParams);
        this.f5693c = new ImageView(this.f5692b);
        this.f5693c.setImageDrawable(this.f5696f.getDrawable(R.watermark_light));
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        int dimensionPixelSize = this.f5696f.getDimensionPixelSize(R.watermark_margin);
        layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        this.f5693c.setLayoutParams(layoutParams);
        this.f5693c.setTag("GoogleWatermark");
        this.f5691a.addView(this.f5693c);
    }

    private TextView m8697c() {
        TextView textView = new TextView(this.f5692b);
        textView.setClickable(true);
        textView.setBackgroundColor(-1862270977);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setTextSize(10.0f);
        textView.setPadding(5, 0, 5, 0);
        return textView;
    }

    public final void m8699a(String str) {
        this.f5695e.setText(str);
    }

    public final View m8698a() {
        return this.f5691a;
    }

    public final TextView m8701b() {
        return this.f5694d;
    }

    public final void m8700a(boolean z) {
        this.f5691a.setVisibility(z ? 0 : 8);
    }
}
