package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.google.android.m4b.maps.R.R;

/* renamed from: com.google.android.m4b.maps.be.x */
public class MapsEngineInfocardLayout extends FrameLayout {
    protected final Context f6085a;
    protected final Resources f6086b;
    protected final MapsEngineInfocardManager f6087c;
    private View f6088d;

    /* renamed from: com.google.android.m4b.maps.be.x.1 */
    class MapsEngineInfocardLayout implements OnClickListener {
        private /* synthetic */ MapsEngineInfocardLayout f6093a;

        MapsEngineInfocardLayout(MapsEngineInfocardLayout mapsEngineInfocardLayout) {
            this.f6093a = mapsEngineInfocardLayout;
        }

        public final void onClick(View view) {
            this.f6093a.f6087c.m9566b();
        }
    }

    public MapsEngineInfocardLayout(Context context, Resources resources, MapsEngineInfocardManager mapsEngineInfocardManager) {
        super(context);
        this.f6085a = context;
        this.f6086b = resources;
        this.f6087c = mapsEngineInfocardManager;
    }

    protected final void m9559a(View view, boolean z) {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        int a = m9557a(36.0f);
        int a2 = m9557a(11.0f);
        layoutParams.setMargins(a2, a2, a2, a);
        setLayoutParams(layoutParams);
        setBackgroundDrawable(this.f6086b.getDrawable(R.card_bg));
        setClickable(true);
        if (z) {
            View linearLayout = new LinearLayout(this.f6085a);
            linearLayout.setOrientation(0);
            linearLayout.addView(view);
            linearLayout.addView(m9558a());
            addView(linearLayout);
            return;
        }
        addView(view);
    }

    protected final View m9558a() {
        View imageButton = new ImageButton(this.f6085a, null, 16974188);
        imageButton.setImageDrawable(this.f6086b.getDrawable(R.btn_close));
        int a = m9557a(11.0f);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        imageButton.setPadding(a, a, a, a);
        imageButton.setLayoutParams(layoutParams);
        imageButton.setContentDescription(this.f6086b.getString(R.CLOSE_SOFTKEY));
        imageButton.setOnClickListener(new MapsEngineInfocardLayout(this));
        this.f6088d = imageButton;
        this.f6088d.setTag("GoogleMapMapsEngineInfocardLayoutCloseButton");
        return imageButton;
    }

    protected void onMeasure(int i, int i2) {
        Object obj;
        Object obj2 = 1;
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        MeasureSpec.getMode(i);
        MeasureSpec.getMode(i2);
        int min = size - (m9557a(11.0f) * 2) >= m9557a(500.0f) ? (int) Math.min((double) size, Math.max(0.4d * ((double) size), (double) m9557a(400.0f))) : size;
        int a = (size2 - m9557a(11.0f)) - m9557a(36.0f);
        if (a > m9557a(480.0f)) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            obj2 = obj;
        } else if ((size - (m9557a(11.0f) * 2)) - min >= m9557a(100.0f)) {
            obj2 = null;
        }
        int i3 = obj2 != null ? (int) (0.75d * ((double) a)) : size2;
        min = Math.min(min, size);
        size = Math.min(i3, size2);
        super.onMeasure(MeasureSpec.makeMeasureSpec(min, 1073741824), MeasureSpec.makeMeasureSpec(size, ExploreByTouchHelper.INVALID_ID));
        setMeasuredDimension(min, Math.min(super.getMeasuredHeight(), size));
    }

    protected final int m9557a(float f) {
        return (int) Math.ceil((double) TypedValue.applyDimension(1, f, this.f6086b.getDisplayMetrics()));
    }
}
