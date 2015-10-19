package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.p003a.R.R;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

public class CardView extends FrameLayout implements CardViewDelegate {
    private static final CardViewImpl f76a;
    private boolean f77b;
    private boolean f78c;
    private final Rect f79d;
    private final Rect f80e;

    static {
        if (VERSION.SDK_INT >= 21) {
            f76a = new CardViewApi21();
        } else if (VERSION.SDK_INT >= 17) {
            f76a = new CardViewJellybeanMr1();
        } else {
            f76a = new CardViewEclairMr1();
        }
        f76a.m41a();
    }

    public CardView(Context context) {
        super(context);
        this.f79d = new Rect();
        this.f80e = new Rect();
        m38a(context, null, 0);
    }

    public CardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f79d = new Rect();
        this.f80e = new Rect();
        m38a(context, attributeSet, 0);
    }

    public CardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f79d = new Rect();
        this.f80e = new Rect();
        m38a(context, attributeSet, i);
    }

    public void setPadding(int i, int i2, int i3, int i4) {
    }

    public void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    public boolean getUseCompatPadding() {
        return this.f77b;
    }

    public void setUseCompatPadding(boolean z) {
        if (this.f77b != z) {
            this.f77b = z;
            f76a.m51g(this);
        }
    }

    protected void onMeasure(int i, int i2) {
        if (f76a instanceof CardViewApi21) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = MeasureSpec.getMode(i);
        switch (mode) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
            case 1073741824:
                i = MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) f76a.m45b(this)), MeasureSpec.getSize(i)), mode);
                break;
        }
        mode = MeasureSpec.getMode(i2);
        switch (mode) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
            case 1073741824:
                i2 = MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) f76a.m47c(this)), MeasureSpec.getSize(i2)), mode);
                break;
        }
        super.onMeasure(i, i2);
    }

    private void m38a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.CardView, i, R.CardView_Light);
        int color = obtainStyledAttributes.getColor(R.CardView_cardBackgroundColor, 0);
        float dimension = obtainStyledAttributes.getDimension(R.CardView_cardCornerRadius, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(R.CardView_cardElevation, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(R.CardView_cardMaxElevation, 0.0f);
        this.f77b = obtainStyledAttributes.getBoolean(R.CardView_cardUseCompatPadding, false);
        this.f78c = obtainStyledAttributes.getBoolean(R.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.CardView_contentPadding, 0);
        this.f79d.left = obtainStyledAttributes.getDimensionPixelSize(R.CardView_contentPaddingLeft, dimensionPixelSize);
        this.f79d.top = obtainStyledAttributes.getDimensionPixelSize(R.CardView_contentPaddingTop, dimensionPixelSize);
        this.f79d.right = obtainStyledAttributes.getDimensionPixelSize(R.CardView_contentPaddingRight, dimensionPixelSize);
        this.f79d.bottom = obtainStyledAttributes.getDimensionPixelSize(R.CardView_contentPaddingBottom, dimensionPixelSize);
        if (dimension2 > dimension3) {
            dimension3 = dimension2;
        }
        obtainStyledAttributes.recycle();
        f76a.m44a(this, context, color, dimension, dimension2, dimension3);
    }

    public void setCardBackgroundColor(int i) {
        f76a.m43a((CardViewDelegate) this, i);
    }

    public int getContentPaddingLeft() {
        return this.f79d.left;
    }

    public int getContentPaddingRight() {
        return this.f79d.right;
    }

    public int getContentPaddingTop() {
        return this.f79d.top;
    }

    public int getContentPaddingBottom() {
        return this.f79d.bottom;
    }

    public void setRadius(float f) {
        f76a.m42a((CardViewDelegate) this, f);
    }

    public float getRadius() {
        return f76a.m49d(this);
    }

    public void m39a(int i, int i2, int i3, int i4) {
        this.f80e.set(i, i2, i3, i4);
        super.setPadding(this.f79d.left + i, this.f79d.top + i2, this.f79d.right + i3, this.f79d.bottom + i4);
    }

    public void setCardElevation(float f) {
        f76a.m48c(this, f);
    }

    public float getCardElevation() {
        return f76a.m50e(this);
    }

    public void setMaxCardElevation(float f) {
        f76a.m46b(this, f);
    }

    public float getMaxCardElevation() {
        return f76a.m40a(this);
    }

    public boolean getPreventCornerOverlap() {
        return this.f78c;
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.f78c) {
            this.f78c = z;
            f76a.m52h(this);
        }
    }
}
