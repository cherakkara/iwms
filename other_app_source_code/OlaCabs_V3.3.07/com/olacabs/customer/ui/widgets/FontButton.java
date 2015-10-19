package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import com.olacabs.customer.R.R;

public class FontButton extends Button {
    public FontButton(Context context) {
        super(context);
    }

    public FontButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.FontButton, 0, 0);
        try {
            if (obtainStyledAttributes.hasValue(0)) {
                m14799a(context, obtainStyledAttributes.getString(0));
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    private void m14799a(Context context, String str) {
        try {
            setTypeface(Typeface.createFromAsset(context.getAssets(), str));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
