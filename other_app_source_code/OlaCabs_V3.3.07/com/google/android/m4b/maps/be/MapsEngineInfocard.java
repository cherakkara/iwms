package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.m4b.maps.R.R;
import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.be.w */
public final class MapsEngineInfocard extends MapsEngineInfocardLayout {
    private static final int f6089d;
    private static final int f6090e;
    private final MapsEngineFeatureImpl f6091f;
    private View f6092g;

    /* renamed from: com.google.android.m4b.maps.be.w.1 */
    class MapsEngineInfocard implements OnClickListener {
        private /* synthetic */ MapsEngineInfocard f6084a;

        MapsEngineInfocard(MapsEngineInfocard mapsEngineInfocard) {
            this.f6084a = mapsEngineInfocard;
        }

        public final void onClick(View view) {
            this.f6084a.c.m9562a();
        }
    }

    static {
        f6089d = Color.parseColor("#4D4D4D");
        f6090e = Color.parseColor("#E2E2E2");
    }

    public MapsEngineInfocard(Context context, Resources resources, MapsEngineInfocardManager mapsEngineInfocardManager, boolean z, MapsEngineFeatureImpl mapsEngineFeatureImpl) {
        super(context, resources, mapsEngineInfocardManager);
        this.f6091f = mapsEngineFeatureImpl;
        if (z) {
            View linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new LayoutParams(-1, -2));
            linearLayout.setOrientation(1);
            View relativeLayout = new RelativeLayout(this.a);
            relativeLayout.setLayoutParams(new LayoutParams(-1, -2));
            View textView = new TextView(this.a);
            ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(9);
            layoutParams.addRule(15);
            int a = m9557a(11.0f);
            textView.setPadding(a, a, a, a);
            textView.setLayoutParams(layoutParams);
            textView.setText(this.b.getString(R.BACK_TO_LIST));
            textView.setTextColor(f6089d);
            textView.setTextSize(16.0f);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setCompoundDrawablesWithIntrinsicBounds(this.b.getDrawable(R.btn_back), null, null, null);
            textView.setOnClickListener(new MapsEngineInfocard(this));
            this.f6092g = textView;
            this.f6092g.setTag("GoogleMapMapsEngineInfocardBackButton");
            relativeLayout.addView(textView);
            ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(11);
            layoutParams2.addRule(15);
            relativeLayout.addView(m9558a(), layoutParams2);
            linearLayout.addView(relativeLayout);
            relativeLayout = new View(this.a);
            relativeLayout.setLayoutParams(new LayoutParams(-1, m9557a(0.5f)));
            relativeLayout.setBackgroundColor(f6090e);
            linearLayout.addView(relativeLayout);
            linearLayout.addView(m9560a(context, false));
            m9559a(linearLayout, false);
            return;
        }
        m9559a(m9560a(context, true), true);
    }

    private WebView m9560a(Context context, boolean z) {
        ViewGroup.LayoutParams layoutParams;
        int i;
        WebView webView = new WebView(context);
        if (z) {
            layoutParams = new LayoutParams(0, -2);
        } else {
            layoutParams = new LayoutParams(-2, 0);
        }
        layoutParams.weight = br.DEFAULT_BACKOFF_MULT;
        int a = m9557a(11.0f);
        if (z) {
            i = a;
        } else {
            i = 0;
        }
        layoutParams.setMargins(a, a, i, a);
        webView.setLayoutParams(layoutParams);
        webView.setFocusable(false);
        webView.loadData(this.f6091f.m9554e(), "text/html; charset=UTF-8", null);
        return webView;
    }
}
