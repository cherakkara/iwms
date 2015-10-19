package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import com.olacabs.customer.R.R;

public class FontEditText extends EditText {
    public FontEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.FontEditText, 0, 0);
        try {
            if (obtainStyledAttributes.hasValue(0)) {
                m14800a(context, obtainStyledAttributes.getString(0));
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    public FontEditText(Context context) {
        super(context);
    }

    private void m14800a(Context context, String str) {
        try {
            setTypeface(Typeface.createFromAsset(context.getAssets(), str));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
