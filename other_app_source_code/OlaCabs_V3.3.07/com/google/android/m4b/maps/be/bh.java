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
import com.newrelic.agent.android.instrumentation.Trace;

/* compiled from: WidgetContainer */
public final class bh {
    private final RelativeLayout f5808a;
    private final Resources f5809b;
    private final Context f5810c;
    private ImageView f5811d;
    private TextView f5812e;
    private final boolean f5813f;

    public bh(Context context, Resources resources) {
        this(context, resources, false, Trace.NULL, az.m8761b(context));
    }

    private bh(Context context, Resources resources, boolean z, String str, boolean z2) {
        int i = 0;
        this.f5808a = new RelativeLayout(context);
        this.f5808a.setLayoutParams(new LayoutParams(-1, -1));
        this.f5810c = context;
        this.f5809b = resources;
        this.f5813f = z2;
        this.f5811d = new ImageView(this.f5810c);
        this.f5811d.setImageDrawable(this.f5809b.getDrawable(R.watermark_dark));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        int dimensionPixelSize = this.f5809b.getDimensionPixelSize(R.watermark_margin);
        layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        this.f5811d.setLayoutParams(layoutParams);
        this.f5811d.setTag("GoogleWatermark");
        this.f5808a.addView(this.f5811d);
        this.f5812e = new TextView(this.f5810c);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(11);
        layoutParams.addRule(12);
        this.f5812e.setLayoutParams(layoutParams);
        this.f5812e.setTextSize(0, (float) this.f5809b.getDimensionPixelSize(R.dav_hud_copyright_fontsize));
        this.f5812e.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f5812e.setSingleLine(true);
        this.f5812e.setTag("GoogleCopyrights");
        TextView textView = this.f5812e;
        if (!this.f5813f) {
            i = 8;
        }
        textView.setVisibility(i);
        this.f5808a.addView(this.f5812e);
    }

    public final void m8856a(int i, int i2, int i3, int i4) {
        this.f5808a.setPadding(i, i2, i3, i4);
    }

    public final View m8855a() {
        return this.f5808a;
    }

    public final TextView m8858b() {
        return this.f5812e;
    }

    public final void m8857a(boolean z) {
        this.f5811d.setImageDrawable(this.f5809b.getDrawable(z ? R.watermark_light : R.watermark_dark));
        this.f5812e.setTextColor(z ? -1 : ViewCompat.MEASURED_STATE_MASK);
    }

    public final void m8859b(boolean z) {
        TextView textView = this.f5812e;
        int i = (this.f5813f && z) ? 0 : 8;
        textView.setVisibility(i);
    }
}
