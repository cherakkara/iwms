package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.R;
import com.olacabs.customer.utils.Constants;

public class KaaliPeeliProgressView extends FrameLayout {
    public static final String f11315a;
    public int f11316b;
    private View f11317c;
    private TextView f11318d;
    private Paint f11319e;
    private Paint f11320f;
    private Handler f11321g;
    private C0894a f11322h;
    private int f11323i;
    private int f11324j;
    private int f11325k;
    private int f11326l;
    private int f11327m;
    private Runnable f11328n;

    /* renamed from: com.olacabs.customer.ui.widgets.KaaliPeeliProgressView.a */
    public interface C0894a {
        void m13852a();
    }

    /* renamed from: com.olacabs.customer.ui.widgets.KaaliPeeliProgressView.1 */
    class C09171 implements Runnable {
        final /* synthetic */ KaaliPeeliProgressView f11314a;

        C09171(KaaliPeeliProgressView kaaliPeeliProgressView) {
            this.f11314a = kaaliPeeliProgressView;
        }

        public void run() {
            this.f11314a.f11327m = this.f11314a.f11327m + this.f11314a.f11325k;
            if (this.f11314a.f11327m <= this.f11314a.f11316b) {
                this.f11314a.f11318d.setText(((this.f11314a.f11316b - this.f11314a.f11327m) / Constants.MILLIS_IN_A_SECOND) + Trace.NULL);
                this.f11314a.invalidate();
                this.f11314a.f11321g.postDelayed(this.f11314a.f11328n, (long) this.f11314a.f11325k);
            } else if (this.f11314a.f11322h != null) {
                this.f11314a.f11322h.m13852a();
            }
        }
    }

    static {
        f11315a = KaaliPeeliProgressView.class.getSimpleName();
    }

    public KaaliPeeliProgressView(Context context) {
        super(context);
        this.f11323i = 4;
        this.f11324j = 1;
        this.f11325k = 100;
        this.f11326l = 0;
        this.f11327m = 0;
        this.f11328n = new C09171(this);
        m14804a(context, null, 0);
    }

    public KaaliPeeliProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11323i = 4;
        this.f11324j = 1;
        this.f11325k = 100;
        this.f11326l = 0;
        this.f11327m = 0;
        this.f11328n = new C09171(this);
        m14804a(context, attributeSet, 0);
    }

    public KaaliPeeliProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11323i = 4;
        this.f11324j = 1;
        this.f11325k = 100;
        this.f11326l = 0;
        this.f11327m = 0;
        this.f11328n = new C09171(this);
        m14804a(context, attributeSet, i);
    }

    public void m14811a(int i, int i2, int i3) {
        this.f11319e.setColor(getResources().getColor(i));
        this.f11320f.setColor(getResources().getColor(i2));
        this.f11318d.setTextColor(getResources().getColor(i3));
    }

    private void m14804a(Context context, AttributeSet attributeSet, int i) {
        this.f11323i = (int) (((float) this.f11323i) * context.getResources().getDisplayMetrics().density);
        this.f11324j = (int) (((float) this.f11324j) * context.getResources().getDisplayMetrics().density);
        this.f11319e = new Paint();
        this.f11319e.setAntiAlias(true);
        this.f11319e.setColor(getResources().getColor(R.color.ola_light_timer_strip));
        this.f11319e.setStrokeWidth((float) this.f11323i);
        this.f11319e.setStyle(Style.STROKE);
        this.f11320f = new Paint();
        this.f11320f.setAntiAlias(true);
        this.f11320f.setColor(getResources().getColor(R.color.ola_gray_light_line));
        this.f11320f.setStrokeWidth((float) this.f11324j);
        this.f11320f.setStyle(Style.STROKE);
        this.f11317c = inflate(context, R.layout.view_kaali_peeli_progress, this).findViewById(R.id.main_layout);
        this.f11318d = (TextView) this.f11317c.findViewById(R.id.item_duration);
        this.f11321g = new Handler();
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int min = Math.min((getHeight() - paddingTop) - paddingBottom, (getWidth() - paddingLeft) - paddingRight);
        canvas.drawCircle((float) ((min / 2) + paddingLeft), (float) ((min / 2) + paddingTop), (float) ((min / 2) - this.f11323i), this.f11320f);
        canvas.drawArc(new RectF((float) (paddingLeft + this.f11323i), (float) (paddingTop + this.f11323i), (float) (paddingRight + (min - this.f11323i)), (float) (paddingBottom + (min - this.f11323i))), -90.0f, 360.0f * (((float) this.f11327m) / ((float) this.f11316b)), false, this.f11319e);
    }

    protected void onMeasure(int i, int i2) {
        if (MeasureSpec.getSize(i) >= MeasureSpec.getSize(i2)) {
            i = i2;
        }
        super.onMeasure(i, i);
    }

    public void m14810a() {
        this.f11321g.postDelayed(this.f11328n, (long) this.f11325k);
    }

    public void m14812b() {
        this.f11321g.removeCallbacks(this.f11328n);
    }

    public void m14813c() {
        m14814d();
        m14810a();
    }

    public void m14814d() {
        m14812b();
        this.f11327m = 0;
        m14815e();
    }

    public void m14815e() {
        this.f11318d.setText(String.valueOf(this.f11316b / Constants.MILLIS_IN_A_SECOND));
    }

    public void setOnProgressFinishListner(C0894a c0894a) {
        this.f11322h = c0894a;
    }
}
