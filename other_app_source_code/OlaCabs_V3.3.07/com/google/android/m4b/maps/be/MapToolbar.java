package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.p042r.ac.IOnCameraChangeListener;
import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.android.m4b.maps.be.r */
public final class MapToolbar extends IOnCameraChangeListener implements OnClickListener {
    private final ImageView f6061a;
    private final ImageView f6062b;
    private final LinearLayout f6063c;
    private boolean f6064d;
    private boolean f6065e;
    private bx f6066f;
    private ad f6067g;
    private boolean f6068h;
    private CameraPosition f6069i;
    private int f6070j;
    private int f6071k;
    private int f6072l;

    private MapToolbar(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, Resources resources, bx bxVar, boolean z) {
        this.f6063c = linearLayout;
        this.f6061a = imageView;
        this.f6062b = imageView2;
        this.f6066f = (bx) Preconditions.m1817a((Object) bxVar);
        this.f6065e = z;
        this.f6070j = resources.getDimensionPixelSize(R.btn_map_toolbar_margin);
        this.f6071k = resources.getDimensionPixelSize(R.btn_map_toolbar_divider);
        this.f6072l = resources.getDimensionPixelSize(R.btn_map_toolbar_bottom_shadow);
    }

    public static MapToolbar m9505a(Context context, Resources resources, bx bxVar, boolean z) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setTag("GoogleMapToolbar");
        View imageView = new ImageView(context);
        imageView.setImageResource(R.icon_gmm);
        imageView.setContentDescription(resources.getString(R.OPEN_GMM_ALT_TEXT));
        imageView.setTag("GoogleMapOpenGmmButton");
        View imageView2 = new ImageView(context);
        imageView2.setImageResource(R.icon_direction);
        imageView2.setContentDescription(resources.getString(R.DIRECTIONS_ALT_TEXT));
        imageView2.setTag("GoogleMapDirectionsButton");
        imageView2.setColorFilter(context.getResources().getColor(R.qu_google_blue_500));
        Object mapToolbar = new MapToolbar(linearLayout, imageView, imageView2, resources, bxVar, z);
        imageView.setOnClickListener(mapToolbar);
        imageView2.setOnClickListener(mapToolbar);
        linearLayout.addView(imageView2);
        linearLayout.addView(imageView);
        return mapToolbar;
    }

    public final void m9511a(boolean z) {
        this.f6064d = z;
        if (!z) {
            this.f6063c.setVisibility(8);
        }
    }

    public final Boolean m9508a() {
        return Boolean.valueOf(this.f6064d);
    }

    private void m9506a(ImageView imageView, int i) {
        imageView.setBackgroundResource(i);
        Drawable background = imageView.getBackground();
        imageView.setLayoutParams(new LayoutParams(background.getIntrinsicWidth(), background.getIntrinsicHeight()));
        imageView.getDrawable();
        if (i == R.btn_right) {
            imageView.setPadding(this.f6071k, 0, this.f6070j, this.f6072l);
        } else if (i == R.btn_left) {
            imageView.setPadding(this.f6070j, 0, this.f6071k, this.f6072l);
        } else {
            imageView.setPadding(0, 0, 0, this.f6072l);
        }
        imageView.setScaleType(ScaleType.CENTER);
    }

    private void m9507b(boolean z) {
        if (z != (this.f6063c.getVisibility() == 0)) {
            Animation translateAnimation;
            int width = ((View) this.f6063c.getParent()).getWidth() - this.f6063c.getLeft();
            if (z) {
                translateAnimation = new TranslateAnimation((float) width, 0.0f, 0.0f, 0.0f);
                this.f6063c.setVisibility(0);
                translateAnimation.setInterpolator(new DecelerateInterpolator());
            } else {
                translateAnimation = new TranslateAnimation(0.0f, (float) width, 0.0f, 0.0f);
                translateAnimation.setInterpolator(new AccelerateInterpolator());
            }
            translateAnimation.setDuration(300);
            this.f6063c.startAnimation(translateAnimation);
        }
    }

    public final void m9513b() {
        this.f6067g = null;
        if (!this.f6065e) {
            m9507b(false);
        }
        this.f6063c.setVisibility(8);
    }

    public final void m9512a(boolean z, boolean z2, ad adVar, boolean z3) {
        if (this.f6064d) {
            this.f6062b.setVisibility(z2 ? 0 : 8);
            this.f6061a.setVisibility(0);
            this.f6067g = adVar;
            this.f6068h = z3;
            if (z2) {
                m9506a(this.f6061a, R.btn_right);
                m9506a(this.f6062b, R.btn_left);
            } else {
                m9506a(this.f6061a, R.btn_standalone);
            }
            if (!this.f6065e) {
                m9507b(true);
            }
            this.f6063c.setVisibility(0);
        }
    }

    public final void onClick(View view) {
        if (view == this.f6061a) {
            this.f6066f.m9069a(this.f6069i, this.f6067g, this.f6068h);
        } else if (view == this.f6062b) {
            bx bxVar = this.f6066f;
            CameraPosition cameraPosition = this.f6069i;
            bxVar.m9068a(this.f6067g);
        }
    }

    public final View m9514c() {
        return this.f6063c;
    }

    public final void m9509a(ad adVar) {
        if (adVar == this.f6067g && !this.f6065e) {
            m9513b();
        }
    }

    public final void m9510a(CameraPosition cameraPosition) {
        this.f6069i = cameraPosition;
        if (!this.f6065e && this.f6067g != null && !this.f6067g.m8348G().m8408i(this.f6067g)) {
            m9513b();
        }
    }
}
