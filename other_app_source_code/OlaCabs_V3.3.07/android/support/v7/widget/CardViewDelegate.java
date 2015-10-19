package android.support.v7.widget;

import android.graphics.drawable.Drawable;

/* renamed from: android.support.v7.widget.b */
interface CardViewDelegate {
    void m37a(int i, int i2, int i3, int i4);

    Drawable getBackground();

    boolean getPreventCornerOverlap();

    boolean getUseCompatPadding();

    void setBackgroundDrawable(Drawable drawable);
}
