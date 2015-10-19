package android.support.v7.widget;

import android.content.Context;
import android.view.View;
import com.olacabs.customer.p076d.dm;

/* renamed from: android.support.v7.widget.a */
class CardViewApi21 implements CardViewImpl {
    CardViewApi21() {
    }

    public void m57a(CardViewDelegate cardViewDelegate, Context context, int i, float f, float f2, float f3) {
        cardViewDelegate.setBackgroundDrawable(new RoundRectDrawable(i, f));
        View view = (View) cardViewDelegate;
        view.setClipToOutline(true);
        view.setElevation(f2);
        m59b(cardViewDelegate, f3);
    }

    public void m55a(CardViewDelegate cardViewDelegate, float f) {
        ((RoundRectDrawable) cardViewDelegate.getBackground()).m89a(f);
    }

    public void m54a() {
    }

    public void m59b(CardViewDelegate cardViewDelegate, float f) {
        ((RoundRectDrawable) cardViewDelegate.getBackground()).m90a(f, cardViewDelegate.getUseCompatPadding(), cardViewDelegate.getPreventCornerOverlap());
        m64f(cardViewDelegate);
    }

    public float m53a(CardViewDelegate cardViewDelegate) {
        return ((RoundRectDrawable) cardViewDelegate.getBackground()).m88a();
    }

    public float m58b(CardViewDelegate cardViewDelegate) {
        return m62d(cardViewDelegate) * dm.DEFAULT_BACKOFF_MULT;
    }

    public float m60c(CardViewDelegate cardViewDelegate) {
        return m62d(cardViewDelegate) * dm.DEFAULT_BACKOFF_MULT;
    }

    public float m62d(CardViewDelegate cardViewDelegate) {
        return ((RoundRectDrawable) cardViewDelegate.getBackground()).m92b();
    }

    public void m61c(CardViewDelegate cardViewDelegate, float f) {
        ((View) cardViewDelegate).setElevation(f);
    }

    public float m63e(CardViewDelegate cardViewDelegate) {
        return ((View) cardViewDelegate).getElevation();
    }

    public void m64f(CardViewDelegate cardViewDelegate) {
        if (cardViewDelegate.getUseCompatPadding()) {
            float a = m53a(cardViewDelegate);
            float d = m62d(cardViewDelegate);
            int ceil = (int) Math.ceil((double) RoundRectDrawableWithShadow.m95b(a, d, cardViewDelegate.getPreventCornerOverlap()));
            int ceil2 = (int) Math.ceil((double) RoundRectDrawableWithShadow.m93a(a, d, cardViewDelegate.getPreventCornerOverlap()));
            cardViewDelegate.m37a(ceil, ceil2, ceil, ceil2);
            return;
        }
        cardViewDelegate.m37a(0, 0, 0, 0);
    }

    public void m65g(CardViewDelegate cardViewDelegate) {
        m59b(cardViewDelegate, m53a(cardViewDelegate));
    }

    public void m66h(CardViewDelegate cardViewDelegate) {
        m59b(cardViewDelegate, m53a(cardViewDelegate));
    }

    public void m56a(CardViewDelegate cardViewDelegate, int i) {
        ((RoundRectDrawable) cardViewDelegate.getBackground()).m91a(i);
    }
}
