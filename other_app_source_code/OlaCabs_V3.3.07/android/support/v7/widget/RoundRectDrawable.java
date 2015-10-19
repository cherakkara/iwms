package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* renamed from: android.support.v7.widget.f */
class RoundRectDrawable extends Drawable {
    private float f84a;
    private final Paint f85b;
    private final RectF f86c;
    private final Rect f87d;
    private float f88e;
    private boolean f89f;
    private boolean f90g;

    public RoundRectDrawable(int i, float f) {
        this.f89f = false;
        this.f90g = true;
        this.f84a = f;
        this.f85b = new Paint(5);
        this.f85b.setColor(i);
        this.f86c = new RectF();
        this.f87d = new Rect();
    }

    void m90a(float f, boolean z, boolean z2) {
        if (f != this.f88e || this.f89f != z || this.f90g != z2) {
            this.f88e = f;
            this.f89f = z;
            this.f90g = z2;
            m87a(null);
            invalidateSelf();
        }
    }

    float m88a() {
        return this.f88e;
    }

    public void draw(Canvas canvas) {
        canvas.drawRoundRect(this.f86c, this.f84a, this.f84a, this.f85b);
    }

    private void m87a(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.f86c.set((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom);
        this.f87d.set(rect);
        if (this.f89f) {
            float a = RoundRectDrawableWithShadow.m93a(this.f88e, this.f84a, this.f90g);
            this.f87d.inset((int) Math.ceil((double) RoundRectDrawableWithShadow.m95b(this.f88e, this.f84a, this.f90g)), (int) Math.ceil((double) a));
            this.f86c.set(this.f87d);
        }
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        m87a(rect);
    }

    public void getOutline(Outline outline) {
        outline.setRoundRect(this.f87d, this.f84a);
    }

    void m89a(float f) {
        if (f != this.f84a) {
            this.f84a = f;
            m87a(null);
            invalidateSelf();
        }
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return -3;
    }

    public float m92b() {
        return this.f84a;
    }

    public void m91a(int i) {
        this.f85b.setColor(i);
        invalidateSelf();
    }
}
