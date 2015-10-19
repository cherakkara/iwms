package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
import com.olacabs.customer.R.R;

public class FontAutoCompleteTextView extends AutoCompleteTextView {
    public FontAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.FontEditText, 0, 0);
        try {
            if (obtainStyledAttributes.hasValue(0)) {
                m14798a(context, obtainStyledAttributes.getString(0));
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    public FontAutoCompleteTextView(Context context) {
        super(context);
    }

    private void m14798a(Context context, String str) {
        try {
            setTypeface(Typeface.createFromAsset(context.getAssets(), str));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
