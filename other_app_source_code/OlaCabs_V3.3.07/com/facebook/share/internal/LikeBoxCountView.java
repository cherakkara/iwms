package com.facebook.share.internal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.olacabs.customer.p076d.dm;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.facebook.share.internal.c */
public class LikeBoxCountView extends FrameLayout {
    private TextView f1198a;
    private LikeBoxCountView f1199b;
    private float f1200c;
    private float f1201d;
    private float f1202e;
    private Paint f1203f;
    private int f1204g;
    private int f1205h;

    /* renamed from: com.facebook.share.internal.c.1 */
    static /* synthetic */ class LikeBoxCountView {
        static final /* synthetic */ int[] f1192a;

        static {
            f1192a = new int[LikeBoxCountView.values().length];
            try {
                f1192a[LikeBoxCountView.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1192a[LikeBoxCountView.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1192a[LikeBoxCountView.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1192a[LikeBoxCountView.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* renamed from: com.facebook.share.internal.c.a */
    public enum LikeBoxCountView {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    public void setText(String str) {
        this.f1198a.setText(str);
    }

    public void setCaretPosition(LikeBoxCountView likeBoxCountView) {
        this.f1199b = likeBoxCountView;
        switch (LikeBoxCountView.f1192a[likeBoxCountView.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                m1528a(this.f1205h, 0, 0, 0);
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                m1528a(0, this.f1205h, 0, 0);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                m1528a(0, 0, this.f1205h, 0);
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                m1528a(0, 0, 0, this.f1205h);
            default:
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        switch (LikeBoxCountView.f1192a[this.f1199b.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                paddingLeft = (int) (((float) paddingLeft) + this.f1200c);
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                paddingTop = (int) (((float) paddingTop) + this.f1200c);
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                width = (int) (((float) width) - this.f1200c);
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                height = (int) (((float) height) - this.f1200c);
                break;
        }
        Canvas canvas2 = canvas;
        m1529a(canvas2, (float) paddingLeft, (float) paddingTop, (float) width, (float) height);
    }

    private void m1528a(int i, int i2, int i3, int i4) {
        this.f1198a.setPadding(this.f1204g + i, this.f1204g + i2, this.f1204g + i3, this.f1204g + i4);
    }

    private void m1529a(Canvas canvas, float f, float f2, float f3, float f4) {
        Path path = new Path();
        float f5 = this.f1202e * dm.DEFAULT_BACKOFF_MULT;
        path.addArc(new RectF(f, f2, f + f5, f2 + f5), -180.0f, 90.0f);
        if (this.f1199b == LikeBoxCountView.TOP) {
            path.lineTo((((f3 - f) - this.f1201d) / dm.DEFAULT_BACKOFF_MULT) + f, f2);
            path.lineTo(((f3 - f) / dm.DEFAULT_BACKOFF_MULT) + f, f2 - this.f1200c);
            path.lineTo((((f3 - f) + this.f1201d) / dm.DEFAULT_BACKOFF_MULT) + f, f2);
        }
        path.lineTo(f3 - this.f1202e, f2);
        path.addArc(new RectF(f3 - f5, f2, f3, f2 + f5), -90.0f, 90.0f);
        if (this.f1199b == LikeBoxCountView.RIGHT) {
            path.lineTo(f3, (((f4 - f2) - this.f1201d) / dm.DEFAULT_BACKOFF_MULT) + f2);
            path.lineTo(this.f1200c + f3, ((f4 - f2) / dm.DEFAULT_BACKOFF_MULT) + f2);
            path.lineTo(f3, (((f4 - f2) + this.f1201d) / dm.DEFAULT_BACKOFF_MULT) + f2);
        }
        path.lineTo(f3, f4 - this.f1202e);
        path.addArc(new RectF(f3 - f5, f4 - f5, f3, f4), 0.0f, 90.0f);
        if (this.f1199b == LikeBoxCountView.BOTTOM) {
            path.lineTo((((f3 - f) + this.f1201d) / dm.DEFAULT_BACKOFF_MULT) + f, f4);
            path.lineTo(((f3 - f) / dm.DEFAULT_BACKOFF_MULT) + f, this.f1200c + f4);
            path.lineTo((((f3 - f) - this.f1201d) / dm.DEFAULT_BACKOFF_MULT) + f, f4);
        }
        path.lineTo(this.f1202e + f, f4);
        path.addArc(new RectF(f, f4 - f5, f5 + f, f4), 90.0f, 90.0f);
        if (this.f1199b == LikeBoxCountView.LEFT) {
            path.lineTo(f, (((f4 - f2) + this.f1201d) / dm.DEFAULT_BACKOFF_MULT) + f2);
            path.lineTo(f - this.f1200c, ((f4 - f2) / dm.DEFAULT_BACKOFF_MULT) + f2);
            path.lineTo(f, (((f4 - f2) - this.f1201d) / dm.DEFAULT_BACKOFF_MULT) + f2);
        }
        path.lineTo(f, this.f1202e + f2);
        canvas.drawPath(path, this.f1203f);
    }
}
