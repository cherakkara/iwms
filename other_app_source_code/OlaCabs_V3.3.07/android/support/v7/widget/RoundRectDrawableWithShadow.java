package android.support.v7.widget;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.v7.p003a.R.R;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;

/* renamed from: android.support.v7.widget.g */
class RoundRectDrawableWithShadow extends Drawable {
    static final double f91a;
    static RoundRectDrawableWithShadow f92c;
    final int f93b;
    Paint f94d;
    Paint f95e;
    Paint f96f;
    final RectF f97g;
    float f98h;
    Path f99i;
    float f100j;
    float f101k;
    float f102l;
    float f103m;
    private boolean f104n;
    private final int f105o;
    private final int f106p;
    private boolean f107q;
    private boolean f108r;

    /* renamed from: android.support.v7.widget.g.a */
    interface RoundRectDrawableWithShadow {
        void m67a(Canvas canvas, RectF rectF, float f, Paint paint);
    }

    static {
        f91a = Math.cos(Math.toRadians(45.0d));
    }

    RoundRectDrawableWithShadow(Resources resources, int i, float f, float f2, float f3) {
        this.f104n = true;
        this.f107q = true;
        this.f108r = false;
        this.f105o = resources.getColor(R.cardview_shadow_start_color);
        this.f106p = resources.getColor(R.cardview_shadow_end_color);
        this.f93b = resources.getDimensionPixelSize(R.cardview_compat_inset_shadow);
        this.f94d = new Paint(5);
        this.f94d.setColor(i);
        this.f95e = new Paint(5);
        this.f95e.setStyle(Style.FILL);
        this.f98h = (float) ((int) (0.5f + f));
        this.f97g = new RectF();
        this.f96f = new Paint(this.f95e);
        this.f96f.setAntiAlias(false);
        m101a(f2, f3);
    }

    private int m97d(float f) {
        int i = (int) (0.5f + f);
        if (i % 2 == 1) {
            return i - 1;
        }
        return i;
    }

    public void m104a(boolean z) {
        this.f107q = z;
        invalidateSelf();
    }

    public void setAlpha(int i) {
        this.f94d.setAlpha(i);
        this.f95e.setAlpha(i);
        this.f96f.setAlpha(i);
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f104n = true;
    }

    void m101a(float f, float f2) {
        if (f < 0.0f || f2 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float d = (float) m97d(f);
        float d2 = (float) m97d(f2);
        if (d > d2) {
            if (!this.f108r) {
                this.f108r = true;
            }
            d = d2;
        }
        if (this.f103m != d || this.f101k != d2) {
            this.f103m = d;
            this.f101k = d2;
            this.f102l = (float) ((int) (((d * 1.5f) + ((float) this.f93b)) + 0.5f));
            this.f100j = ((float) this.f93b) + d2;
            this.f104n = true;
            invalidateSelf();
        }
    }

    public boolean getPadding(Rect rect) {
        int ceil = (int) Math.ceil((double) RoundRectDrawableWithShadow.m93a(this.f101k, this.f98h, this.f107q));
        int ceil2 = (int) Math.ceil((double) RoundRectDrawableWithShadow.m95b(this.f101k, this.f98h, this.f107q));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    static float m93a(float f, float f2, boolean z) {
        if (z) {
            return (float) (((double) (1.5f * f)) + ((1.0d - f91a) * ((double) f2)));
        }
        return 1.5f * f;
    }

    static float m95b(float f, float f2, boolean z) {
        if (z) {
            return (float) (((double) f) + ((1.0d - f91a) * ((double) f2)));
        }
        return f;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f94d.setColorFilter(colorFilter);
        this.f95e.setColorFilter(colorFilter);
        this.f96f.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -3;
    }

    void m100a(float f) {
        float f2 = (float) ((int) (0.5f + f));
        if (this.f98h != f2) {
            this.f98h = f2;
            this.f104n = true;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        if (this.f104n) {
            m96b(getBounds());
            this.f104n = false;
        }
        canvas.translate(0.0f, this.f103m / dm.DEFAULT_BACKOFF_MULT);
        m94a(canvas);
        canvas.translate(0.0f, (-this.f103m) / dm.DEFAULT_BACKOFF_MULT);
        f92c.m67a(canvas, this.f97g, this.f98h, this.f94d);
    }

    private void m94a(Canvas canvas) {
        Object obj;
        float f = (-this.f98h) - this.f102l;
        float f2 = (this.f98h + ((float) this.f93b)) + (this.f103m / dm.DEFAULT_BACKOFF_MULT);
        Object obj2 = this.f97g.width() - (dm.DEFAULT_BACKOFF_MULT * f2) > 0.0f ? 1 : null;
        if (this.f97g.height() - (dm.DEFAULT_BACKOFF_MULT * f2) > 0.0f) {
            obj = 1;
        } else {
            obj = null;
        }
        int save = canvas.save();
        canvas.translate(this.f97g.left + f2, this.f97g.top + f2);
        canvas.drawPath(this.f99i, this.f95e);
        if (obj2 != null) {
            canvas.drawRect(0.0f, f, this.f97g.width() - (dm.DEFAULT_BACKOFF_MULT * f2), -this.f98h, this.f96f);
        }
        canvas.restoreToCount(save);
        save = canvas.save();
        canvas.translate(this.f97g.right - f2, this.f97g.bottom - f2);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f99i, this.f95e);
        if (obj2 != null) {
            canvas.drawRect(0.0f, f, this.f97g.width() - (dm.DEFAULT_BACKOFF_MULT * f2), this.f102l + (-this.f98h), this.f96f);
        }
        canvas.restoreToCount(save);
        int save2 = canvas.save();
        canvas.translate(this.f97g.left + f2, this.f97g.bottom - f2);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f99i, this.f95e);
        if (obj != null) {
            canvas.drawRect(0.0f, f, this.f97g.height() - (dm.DEFAULT_BACKOFF_MULT * f2), -this.f98h, this.f96f);
        }
        canvas.restoreToCount(save2);
        save2 = canvas.save();
        canvas.translate(this.f97g.right - f2, this.f97g.top + f2);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f99i, this.f95e);
        if (obj != null) {
            canvas.drawRect(0.0f, f, this.f97g.height() - (dm.DEFAULT_BACKOFF_MULT * f2), -this.f98h, this.f96f);
        }
        canvas.restoreToCount(save2);
    }

    private void m98f() {
        RectF rectF = new RectF(-this.f98h, -this.f98h, this.f98h, this.f98h);
        RectF rectF2 = new RectF(rectF);
        rectF2.inset(-this.f102l, -this.f102l);
        if (this.f99i == null) {
            this.f99i = new Path();
        } else {
            this.f99i.reset();
        }
        this.f99i.setFillType(FillType.EVEN_ODD);
        this.f99i.moveTo(-this.f98h, 0.0f);
        this.f99i.rLineTo(-this.f102l, 0.0f);
        this.f99i.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f99i.arcTo(rectF, 270.0f, -90.0f, false);
        this.f99i.close();
        float f = this.f98h / (this.f98h + this.f102l);
        float[] fArr = new float[]{0.0f, f, br.DEFAULT_BACKOFF_MULT};
        f = 0.0f;
        this.f95e.setShader(new RadialGradient(0.0f, f, this.f98h + this.f102l, new int[]{this.f105o, this.f105o, this.f106p}, fArr, TileMode.CLAMP));
        float f2 = 0.0f;
        this.f96f.setShader(new LinearGradient(0.0f, (-this.f98h) + this.f102l, f2, (-this.f98h) - this.f102l, new int[]{this.f105o, this.f105o, this.f106p}, new float[]{0.0f, 0.5f, br.DEFAULT_BACKOFF_MULT}, TileMode.CLAMP));
        this.f96f.setAntiAlias(false);
    }

    private void m96b(Rect rect) {
        float f = this.f101k * 1.5f;
        this.f97g.set(((float) rect.left) + this.f101k, ((float) rect.top) + f, ((float) rect.right) - this.f101k, ((float) rect.bottom) - f);
        m98f();
    }

    float m99a() {
        return this.f98h;
    }

    void m103a(Rect rect) {
        getPadding(rect);
    }

    void m106b(float f) {
        m101a(f, this.f101k);
    }

    void m108c(float f) {
        m101a(this.f103m, f);
    }

    float m105b() {
        return this.f103m;
    }

    float m107c() {
        return this.f101k;
    }

    float m109d() {
        return (Math.max(this.f101k, (this.f98h + ((float) this.f93b)) + (this.f101k / dm.DEFAULT_BACKOFF_MULT)) * dm.DEFAULT_BACKOFF_MULT) + ((this.f101k + ((float) this.f93b)) * dm.DEFAULT_BACKOFF_MULT);
    }

    float m110e() {
        return (Math.max(this.f101k, (this.f98h + ((float) this.f93b)) + ((this.f101k * 1.5f) / dm.DEFAULT_BACKOFF_MULT)) * dm.DEFAULT_BACKOFF_MULT) + (((this.f101k * 1.5f) + ((float) this.f93b)) * dm.DEFAULT_BACKOFF_MULT);
    }

    public void m102a(int i) {
        this.f94d.setColor(i);
        invalidateSelf();
    }
}
