package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.sothree.slidinguppanel.p086a.R.R;

public class LocationButton extends ImageView {
    public LocationButton(Context context) {
        super(context);
    }

    public LocationButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LocationButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void m14816a(int i) {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                setImageLevel(i);
            default:
        }
    }
}
