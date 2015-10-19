package com.olacabs.customer.ui.widgets;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.olacabs.customer.R.R;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.olacabs.customer.p079e.p080a.PasswordStrength.PasswordStrength;

public class PasswordStrengthIndicator extends View implements AnimatorUpdateListener {
    private Paint f11331a;
    private int f11332b;
    private int f11333c;
    private int f11334d;
    private float f11335e;
    private float f11336f;
    private ValueAnimator f11337g;
    private float f11338h;
    private float f11339i;
    private float f11340j;

    /* renamed from: com.olacabs.customer.ui.widgets.PasswordStrengthIndicator.1 */
    class C09181 implements OnGlobalLayoutListener {
        final /* synthetic */ PasswordStrengthIndicator f11329a;

        C09181(PasswordStrengthIndicator passwordStrengthIndicator) {
            this.f11329a = passwordStrengthIndicator;
        }

        public void onGlobalLayout() {
            this.f11329a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            this.f11329a.f11336f = (float) (this.f11329a.getWidth() / 3);
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.PasswordStrengthIndicator.2 */
    static /* synthetic */ class C09192 {
        static final /* synthetic */ int[] f11330a;

        static {
            f11330a = new int[PasswordStrength.values().length];
            try {
                f11330a[PasswordStrength.WEAK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f11330a[PasswordStrength.GOOD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f11330a[PasswordStrength.STRONG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public PasswordStrengthIndicator(Context context) {
        this(context, null);
        m14818b();
    }

    public PasswordStrengthIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        m14818b();
    }

    public PasswordStrengthIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11338h = 0.0f;
        this.f11339i = 0.0f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.PasswordStrengthIndicator);
        this.f11332b = obtainStyledAttributes.getColor(1, com.olacabs.customer.R.color.password_weak);
        this.f11333c = obtainStyledAttributes.getColor(2, com.olacabs.customer.R.color.password_good);
        this.f11334d = obtainStyledAttributes.getColor(0, com.olacabs.customer.R.color.password_strong);
        this.f11335e = obtainStyledAttributes.getFloat(3, 25.0f);
        obtainStyledAttributes.recycle();
        m14818b();
    }

    public void setColor(int i) {
        this.f11331a.setColor(i);
    }

    private void m14818b() {
        this.f11337g = ValueAnimator.ofFloat(new float[]{0.0f, br.DEFAULT_BACKOFF_MULT});
        this.f11337g.setDuration(200);
        this.f11337g.addUpdateListener(this);
        this.f11331a = new Paint();
        this.f11331a.setColor(this.f11332b);
        this.f11331a.setStyle(Style.STROKE);
        this.f11331a.setStrokeWidth(this.f11335e);
        this.f11331a.setAntiAlias(true);
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new C09181(this));
        }
    }

    public void m14820a(PasswordStrength passwordStrength) {
        switch (C09192.f11330a[passwordStrength.ordinal()]) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f11331a.setColor(getResources().getColor(this.f11332b));
                this.f11339i = this.f11336f;
                break;
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                this.f11331a.setColor(getResources().getColor(this.f11333c));
                this.f11339i = dm.DEFAULT_BACKOFF_MULT * this.f11336f;
                break;
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                this.f11331a.setColor(getResources().getColor(this.f11334d));
                this.f11339i = 3.0f * this.f11336f;
                break;
        }
        this.f11337g.setFloatValues(new float[]{this.f11338h, this.f11339i});
        this.f11338h = this.f11339i;
        this.f11337g.start();
    }

    public void m14819a() {
        this.f11340j = 0.0f;
        this.f11338h = 0.0f;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawLine(0.0f, 0.0f, this.f11340j, 0.0f, this.f11331a);
        canvas.restore();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i);
        if (mode == ExploreByTouchHelper.INVALID_ID) {
            throw new IllegalStateException("AnimatedPathView cannot have a WRAP_CONTENT property");
        } else if (mode2 == ExploreByTouchHelper.INVALID_ID) {
            throw new IllegalStateException("AnimatedPathView cannot have a WRAP_CONTENT property");
        } else {
            setMeasuredDimension(size, size2);
        }
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f11340j = Float.parseFloat(valueAnimator.getAnimatedValue().toString());
        invalidate();
    }
}
