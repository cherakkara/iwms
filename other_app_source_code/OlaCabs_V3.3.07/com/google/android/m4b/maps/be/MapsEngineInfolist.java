package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.olacabs.customer.p076d.br;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.be.z */
public final class MapsEngineInfolist extends MapsEngineInfocardLayout {
    private static final int f6110d;
    private final List<MapsEngineFeatureImpl> f6111e;

    /* renamed from: com.google.android.m4b.maps.be.z.a */
    class MapsEngineInfolist extends BaseAdapter {
        final /* synthetic */ MapsEngineInfolist f6109a;

        /* renamed from: com.google.android.m4b.maps.be.z.a.1 */
        class MapsEngineInfolist implements OnClickListener {
            private /* synthetic */ int f6107a;
            private /* synthetic */ MapsEngineInfolist f6108b;

            MapsEngineInfolist(MapsEngineInfolist mapsEngineInfolist, int i) {
                this.f6108b = mapsEngineInfolist;
                this.f6107a = i;
            }

            public final void onClick(View view) {
                this.f6108b.f6109a.m9570a(this.f6107a);
            }
        }

        private MapsEngineInfolist(MapsEngineInfolist mapsEngineInfolist) {
            this.f6109a = mapsEngineInfolist;
        }

        public final int getCount() {
            return this.f6109a.f6111e.size();
        }

        public final Object getItem(int i) {
            return this.f6109a.f6111e.get(i);
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            WebView webView;
            View view2;
            View relativeLayout;
            WebView webView2;
            if (view == null || !(view instanceof FrameLayout)) {
                webView = null;
                Object obj = null;
            } else {
                view = (FrameLayout) view;
                if (view.getChildCount() == 2 && (view.getChildAt(0) instanceof WebView)) {
                    webView = (WebView) view.getChildAt(0);
                    view2 = view;
                } else {
                    webView = null;
                    view2 = view;
                }
            }
            if (view2 == null || webView == null) {
                view2 = new FrameLayout(this.f6109a.a);
                view2.setLayoutParams(new LayoutParams(-2, this.f6109a.m9557a(72.0f)));
                ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, this.f6109a.m9557a(72.0f));
                View webView3 = new WebView(this.f6109a.a);
                webView3.setVerticalScrollBarEnabled(false);
                webView3.setHorizontalScrollBarEnabled(false);
                webView3.setLayoutParams(layoutParams);
                view2.addView(webView3);
                relativeLayout = new RelativeLayout(this.f6109a.a);
                relativeLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, this.f6109a.m9557a(72.0f)));
                relativeLayout.setClickable(true);
                relativeLayout.setOnClickListener(new MapsEngineInfolist(this, i));
                View view3 = new View(this.f6109a.a);
                ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, this.f6109a.m9557a(12.0f));
                layoutParams2.addRule(12);
                view3.setLayoutParams(layoutParams2);
                view3.setBackgroundDrawable(new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -1}));
                relativeLayout.addView(view3);
                view2.addView(relativeLayout);
                relativeLayout = view2;
                view2 = webView3;
            } else {
                relativeLayout = view2;
                webView2 = webView;
            }
            webView2.loadData(((MapsEngineFeatureImpl) this.f6109a.f6111e.get(i)).m9554e(), "text/html; charset=UTF-8", null);
            return relativeLayout;
        }
    }

    static {
        f6110d = Color.parseColor("#E2E2E2");
    }

    public MapsEngineInfolist(Context context, Resources resources, MapsEngineInfocardManager mapsEngineInfocardManager, List<MapsEngineFeatureImpl> list) {
        super(context, resources, mapsEngineInfocardManager);
        this.f6111e = list;
        View listView = new ListView(context);
        listView.setAdapter(new MapsEngineInfolist());
        listView.setDivider(new ColorDrawable(f6110d));
        listView.setDividerHeight(m9557a(0.5f));
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = br.DEFAULT_BACKOFF_MULT;
        int a = m9557a(11.0f);
        layoutParams.setMargins(a, a, 0, a);
        listView.setLayoutParams(layoutParams);
        listView.setTag("GoogleMapMapsEngineInfolistListView");
        m9559a(listView, true);
    }

    public final void m9570a(int i) {
        this.c.m9563a(i);
    }
}
