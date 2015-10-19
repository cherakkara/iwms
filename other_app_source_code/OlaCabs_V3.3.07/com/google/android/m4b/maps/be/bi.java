package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.google.android.m4b.maps.R.R;

/* compiled from: ZoomButtons */
public final class bi implements OnClickListener {
    private final View f5814a;
    private final View f5815b;
    private final View f5816c;
    private ZoomButtons f5817d;

    /* renamed from: com.google.android.m4b.maps.be.bi.a */
    public interface ZoomButtons {
        void m8860a();

        void m8861b();
    }

    private bi(View view, View view2, View view3) {
        this.f5816c = view;
        this.f5814a = view2;
        this.f5815b = view3;
    }

    public static bi m8862a(Context context, Resources resources) {
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setTag("GoogleMapZoomButtons");
        View view = new View(context);
        view.setLayoutParams(new LayoutParams(resources.getDimensionPixelSize(R.btn_width), resources.getDimensionPixelSize(R.btn_zoom_height)));
        view.setBackgroundDrawable(resources.getDrawable(R.btn_zoom_up));
        view.setContentDescription(resources.getString(R.ZOOM_IN_ALT_TEXT));
        view.setTag("GoogleMapZoomInButton");
        linearLayout.addView(view);
        View view2 = new View(context);
        view2.setLayoutParams(new LayoutParams(resources.getDimensionPixelSize(R.btn_width), resources.getDimensionPixelSize(R.btn_zoom_height)));
        view2.setBackgroundDrawable(resources.getDrawable(R.btn_zoom_down));
        view2.setContentDescription(resources.getString(R.ZOOM_OUT_ALT_TEXT));
        view2.setTag("GoogleMapZoomOutButton");
        linearLayout.addView(view2);
        Object biVar = new bi(linearLayout, view, view2);
        view.setOnClickListener(biVar);
        view2.setOnClickListener(biVar);
        return biVar;
    }

    public final void m8865a(boolean z) {
        this.f5816c.setVisibility(z ? 0 : 8);
    }

    public final void m8864a(ZoomButtons zoomButtons) {
        this.f5817d = zoomButtons;
    }

    public final void onClick(View view) {
        if (this.f5817d != null) {
            if (view == this.f5814a) {
                this.f5817d.m8860a();
            } else if (view == this.f5815b) {
                this.f5817d.m8861b();
            }
        }
    }

    public final void m8866b(boolean z) {
        this.f5814a.setEnabled(z);
    }

    public final void m8867c(boolean z) {
        this.f5815b.setEnabled(z);
    }

    public final View m8863a() {
        return this.f5816c;
    }
}
