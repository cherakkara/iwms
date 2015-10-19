package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RoundRectDrawableWithShadow.RoundRectDrawableWithShadow;

/* renamed from: android.support.v7.widget.e */
class CardViewJellybeanMr1 extends CardViewEclairMr1 {

    /* renamed from: android.support.v7.widget.e.1 */
    class CardViewJellybeanMr1 implements RoundRectDrawableWithShadow {
        final /* synthetic */ CardViewJellybeanMr1 f83a;

        CardViewJellybeanMr1(CardViewJellybeanMr1 cardViewJellybeanMr1) {
            this.f83a = cardViewJellybeanMr1;
        }

        public void m85a(Canvas canvas, RectF rectF, float f, Paint paint) {
            canvas.drawRoundRect(rectF, f, f, paint);
        }
    }

    CardViewJellybeanMr1() {
    }

    public void m86a() {
        RoundRectDrawableWithShadow.f92c = new CardViewJellybeanMr1(this);
    }
}
