package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.olacabs.customer.R.R;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;

public class FloatLabelLayout extends LinearLayout {
    private EditText f11310a;
    private TextView f11311b;
    private CharSequence f11312c;
    private Interpolator f11313d;

    /* renamed from: com.olacabs.customer.ui.widgets.FloatLabelLayout.1 */
    class C09141 implements TextWatcher {
        final /* synthetic */ FloatLabelLayout f11307a;

        C09141(FloatLabelLayout floatLabelLayout) {
            this.f11307a = floatLabelLayout;
        }

        public void afterTextChanged(Editable editable) {
            this.f11307a.m14797a(true);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.FloatLabelLayout.2 */
    class C09152 implements OnFocusChangeListener {
        final /* synthetic */ FloatLabelLayout f11308a;

        C09152(FloatLabelLayout floatLabelLayout) {
            this.f11308a = floatLabelLayout;
        }

        public void onFocusChange(View view, boolean z) {
            this.f11308a.m14797a(true);
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.FloatLabelLayout.3 */
    class C09163 extends ViewPropertyAnimatorListenerAdapter {
        final /* synthetic */ FloatLabelLayout f11309a;

        C09163(FloatLabelLayout floatLabelLayout) {
            this.f11309a = floatLabelLayout;
        }

        public void onAnimationEnd(View view) {
            this.f11309a.f11311b.setVisibility(4);
            this.f11309a.f11310a.setHint(this.f11309a.f11312c);
        }
    }

    public FloatLabelLayout(Context context) {
        this(context, null);
    }

    public FloatLabelLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatLabelLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(1);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.FloatLabelLayout);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, m14791a(3.0f));
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(3, m14791a(4.0f));
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(2, m14791a(3.0f));
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(4, m14791a((float) dm.DEFAULT_BACKOFF_MULT));
        this.f11312c = obtainStyledAttributes.getText(5);
        this.f11311b = new TextView(context);
        this.f11311b.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize4);
        this.f11311b.setVisibility(4);
        this.f11311b.setText(this.f11312c);
        ViewCompat.setPivotX(this.f11311b, 0.0f);
        ViewCompat.setPivotY(this.f11311b, 0.0f);
        this.f11311b.setTextAppearance(context, obtainStyledAttributes.getResourceId(0, 16973894));
        obtainStyledAttributes.recycle();
        addView(this.f11311b, -2, -2);
        this.f11313d = AnimationUtils.loadInterpolator(context, VERSION.SDK_INT >= 21 ? 17563661 : 17432582);
    }

    public final void addView(View view, int i, LayoutParams layoutParams) {
        if (view instanceof EditText) {
            setEditText((EditText) view);
        }
        super.addView(view, i, layoutParams);
    }

    private void setEditText(EditText editText) {
        if (this.f11310a != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        this.f11310a = editText;
        m14797a(false);
        this.f11310a.addTextChangedListener(new C09141(this));
        this.f11310a.setOnFocusChangeListener(new C09152(this));
        if (TextUtils.isEmpty(this.f11312c)) {
            setHint(this.f11310a.getHint());
        }
    }

    public void m14797a(boolean z) {
        Object obj = !TextUtils.isEmpty(this.f11310a.getText()) ? 1 : null;
        boolean isFocused = this.f11310a.isFocused();
        this.f11311b.setActivated(isFocused);
        if (obj != null || isFocused) {
            if (this.f11311b.getVisibility() != 0) {
                m14794b(z);
            }
        } else if (this.f11311b.getVisibility() == 0) {
            m14796c(z);
        }
    }

    public EditText getEditText() {
        return this.f11310a;
    }

    public TextView getLabel() {
        return this.f11311b;
    }

    public void setHint(CharSequence charSequence) {
        this.f11312c = charSequence;
        this.f11311b.setText(charSequence);
    }

    private void m14794b(boolean z) {
        if (z) {
            this.f11311b.setVisibility(0);
            ViewCompat.setTranslationY(this.f11311b, (float) this.f11311b.getHeight());
            float textSize = this.f11310a.getTextSize() / this.f11311b.getTextSize();
            ViewCompat.setScaleX(this.f11311b, textSize);
            ViewCompat.setScaleY(this.f11311b, textSize);
            ViewCompat.animate(this.f11311b).translationY(0.0f).scaleY(br.DEFAULT_BACKOFF_MULT).scaleX(br.DEFAULT_BACKOFF_MULT).setDuration(150).setListener(null).setInterpolator(this.f11313d).start();
        } else {
            this.f11311b.setVisibility(0);
        }
        this.f11310a.setHint(null);
    }

    private void m14796c(boolean z) {
        if (z) {
            float textSize = this.f11310a.getTextSize() / this.f11311b.getTextSize();
            ViewCompat.setScaleX(this.f11311b, br.DEFAULT_BACKOFF_MULT);
            ViewCompat.setScaleY(this.f11311b, br.DEFAULT_BACKOFF_MULT);
            ViewCompat.setTranslationY(this.f11311b, 0.0f);
            ViewCompat.animate(this.f11311b).translationY((float) this.f11311b.getHeight()).setDuration(150).scaleX(textSize).scaleY(textSize).setListener(new C09163(this)).setInterpolator(this.f11313d).start();
            return;
        }
        this.f11311b.setVisibility(4);
        this.f11310a.setHint(this.f11312c);
    }

    private int m14791a(float f) {
        return (int) TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }
}
