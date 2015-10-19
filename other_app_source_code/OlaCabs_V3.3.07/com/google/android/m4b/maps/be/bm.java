package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.m4b.maps.R.R;

/* compiled from: ButtonContainer */
public final class bm {
    private final RelativeLayout f5831a;
    private final Resources f5832b;
    private final Context f5833c;
    private bi f5834d;
    private af f5835e;
    private LevelPickerAdapter f5836f;
    private MapToolbar f5837g;
    private bs f5838h;

    public bm(Context context, Resources resources, MapToolbar mapToolbar) {
        this.f5831a = new RelativeLayout(context);
        this.f5831a.setLayoutParams(new LayoutParams(-1, -1));
        this.f5833c = context;
        this.f5832b = resources;
        this.f5837g = mapToolbar;
        LayoutParams layoutParams = new LayoutParams(this.f5832b.getDimensionPixelSize(R.btn_width), this.f5832b.getDimensionPixelSize(R.btn_myl_height));
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        layoutParams.setMargins(0, this.f5832b.getDimensionPixelSize(R.btn_margin), this.f5832b.getDimensionPixelSize(R.btn_margin), 0);
        Context context2 = this.f5833c;
        Resources resources2 = this.f5832b;
        View view = new View(context2);
        view.setBackgroundDrawable(resources2.getDrawable(R.btn_myl));
        view.setVisibility(8);
        view.setContentDescription(resources2.getString(R.MY_LOCATION_ALT_TEXT));
        view.setTag("GoogleMapMyLocationButton");
        this.f5835e = new af(view);
        m8909a(this.f5835e.m8409a(), 2, layoutParams);
        layoutParams = new LayoutParams(-2, -1);
        layoutParams.addRule(11);
        layoutParams.addRule(2, 1);
        layoutParams.addRule(3, 2);
        layoutParams.setMargins(0, this.f5832b.getDimensionPixelSize(R.btn_margin), this.f5832b.getDimensionPixelSize(R.btn_margin), this.f5832b.getDimensionPixelSize(R.btn_margin));
        this.f5836f = LevelPickerAdapter.m9401a(this.f5833c, this.f5832b);
        m8909a(this.f5836f.m9403a(), 3, layoutParams);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(11);
        layoutParams.addRule(12);
        layoutParams.setMargins(0, 0, this.f5832b.getDimensionPixelSize(R.btn_zoom_x_margin), this.f5832b.getDimensionPixelSize(R.btn_zoom_y_margin));
        this.f5834d = bi.m8862a(this.f5833c, this.f5832b);
        m8909a(this.f5834d.m8863a(), 1, layoutParams);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(0, 1);
        layoutParams.alignWithParent = true;
        layoutParams.addRule(12);
        layoutParams.setMargins(0, 0, this.f5832b.getDimensionPixelSize(R.btn_zoom_x_margin), this.f5832b.getDimensionPixelSize(R.btn_zoom_y_margin));
        m8909a(this.f5837g.m9514c(), 4, layoutParams);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(10);
        layoutParams.setMargins(this.f5832b.getDimensionPixelSize(R.btn_margin), this.f5832b.getDimensionPixelSize(R.btn_margin), 0, 0);
        this.f5838h = new bs(this.f5833c);
        m8909a(this.f5838h, 5, layoutParams);
    }

    private void m8909a(View view, int i, LayoutParams layoutParams) {
        view.setVisibility(8);
        view.setId(i);
        this.f5831a.addView(view, layoutParams);
    }

    public final void m8911a(int i, int i2, int i3, int i4) {
        this.f5831a.setPadding(i, i2, i3, i4);
    }

    public final View m8910a() {
        return this.f5831a;
    }

    public final bi m8912b() {
        return this.f5834d;
    }

    public final af m8913c() {
        return this.f5835e;
    }

    public final LevelPickerAdapter m8914d() {
        return this.f5836f;
    }

    public final MapToolbar m8915e() {
        return this.f5837g;
    }

    public final bs m8916f() {
        return this.f5838h;
    }
}
