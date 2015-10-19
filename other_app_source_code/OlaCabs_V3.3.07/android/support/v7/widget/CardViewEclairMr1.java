package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RoundRectDrawableWithShadow.RoundRectDrawableWithShadow;
import android.view.View;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;

/* renamed from: android.support.v7.widget.c */
class CardViewEclairMr1 implements CardViewImpl {
    final RectF f82a;

    /* renamed from: android.support.v7.widget.c.1 */
    class CardViewEclairMr1 implements RoundRectDrawableWithShadow {
        final /* synthetic */ CardViewEclairMr1 f81a;

        CardViewEclairMr1(CardViewEclairMr1 cardViewEclairMr1) {
            this.f81a = cardViewEclairMr1;
        }

        public void m68a(Canvas canvas, RectF rectF, float f, Paint paint) {
            float f2 = dm.DEFAULT_BACKOFF_MULT * f;
            float width = (rectF.width() - f2) - br.DEFAULT_BACKOFF_MULT;
            float height = (rectF.height() - f2) - br.DEFAULT_BACKOFF_MULT;
            if (f >= br.DEFAULT_BACKOFF_MULT) {
                f += 0.5f;
                this.f81a.f82a.set(-f, -f, f, f);
                int save = canvas.save();
                canvas.translate(rectF.left + f, rectF.top + f);
                canvas.drawArc(this.f81a.f82a, 180.0f, 90.0f, true, paint);
                canvas.translate(width, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(this.f81a.f82a, 180.0f, 90.0f, true, paint);
                canvas.translate(height, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(this.f81a.f82a, 180.0f, 90.0f, true, paint);
                canvas.translate(width, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(this.f81a.f82a, 180.0f, 90.0f, true, paint);
                canvas.restoreToCount(save);
                canvas.drawRect((rectF.left + f) - br.DEFAULT_BACKOFF_MULT, rectF.top, (rectF.right - f) + br.DEFAULT_BACKOFF_MULT, rectF.top + f, paint);
                canvas.drawRect((rectF.left + f) - br.DEFAULT_BACKOFF_MULT, (rectF.bottom - f) + br.DEFAULT_BACKOFF_MULT, (rectF.right - f) + br.DEFAULT_BACKOFF_MULT, rectF.bottom, paint);
            }
            canvas.drawRect(rectF.left, Math.max(0.0f, f - br.DEFAULT_BACKOFF_MULT) + rectF.top, rectF.right, (rectF.bottom - f) + br.DEFAULT_BACKOFF_MULT, paint);
        }
    }

    CardViewEclairMr1() {
        this.f82a = new RectF();
    }

    public void m72a() {
        RoundRectDrawableWithShadow.f92c = new CardViewEclairMr1(this);
    }

    public void m75a(CardViewDelegate cardViewDelegate, Context context, int i, float f, float f2, float f3) {
        Drawable a = m71a(context, i, f, f2, f3);
        a.m104a(cardViewDelegate.getPreventCornerOverlap());
        cardViewDelegate.setBackgroundDrawable(a);
        m82f(cardViewDelegate);
    }

    RoundRectDrawableWithShadow m71a(Context context, int i, float f, float f2, float f3) {
        return new RoundRectDrawableWithShadow(context.getResources(), i, f, f2, f3);
    }

    public void m82f(CardViewDelegate cardViewDelegate) {
        Rect rect = new Rect();
        m69i(cardViewDelegate).m103a(rect);
        ((View) cardViewDelegate).setMinimumHeight((int) Math.ceil((double) m78c(cardViewDelegate)));
        ((View) cardViewDelegate).setMinimumWidth((int) Math.ceil((double) m76b(cardViewDelegate)));
        cardViewDelegate.m37a(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void m83g(CardViewDelegate cardViewDelegate) {
    }

    public void m84h(CardViewDelegate cardViewDelegate) {
        m69i(cardViewDelegate).m104a(cardViewDelegate.getPreventCornerOverlap());
        m82f(cardViewDelegate);
    }

    public void m74a(CardViewDelegate cardViewDelegate, int i) {
        m69i(cardViewDelegate).m102a(i);
    }

    public void m73a(CardViewDelegate cardViewDelegate, float f) {
        m69i(cardViewDelegate).m100a(f);
        m82f(cardViewDelegate);
    }

    public float m80d(CardViewDelegate cardViewDelegate) {
        return m69i(cardViewDelegate).m99a();
    }

    public void m79c(CardViewDelegate cardViewDelegate, float f) {
        m69i(cardViewDelegate).m106b(f);
    }

    public float m81e(CardViewDelegate cardViewDelegate) {
        return m69i(cardViewDelegate).m105b();
    }

    public void m77b(CardViewDelegate cardViewDelegate, float f) {
        m69i(cardViewDelegate).m108c(f);
        m82f(cardViewDelegate);
    }

    public float m70a(CardViewDelegate cardViewDelegate) {
        return m69i(cardViewDelegate).m107c();
    }

    public float m76b(CardViewDelegate cardViewDelegate) {
        return m69i(cardViewDelegate).m109d();
    }

    public float m78c(CardViewDelegate cardViewDelegate) {
        return m69i(cardViewDelegate).m110e();
    }

    private RoundRectDrawableWithShadow m69i(CardViewDelegate cardViewDelegate) {
        return (RoundRectDrawableWithShadow) cardViewDelegate.getBackground();
    }
}
