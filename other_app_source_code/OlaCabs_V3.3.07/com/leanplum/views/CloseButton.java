package com.leanplum.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.leanplum.utils.SizeUtil;
import com.olacabs.customer.p076d.dm;

public class CloseButton extends View {
    private Paint f8882a;
    private Paint f8883b;
    private Paint f8884c;
    private float f8885d;
    private float f8886e;
    private float f8887f;
    private float f8888g;
    private float f8889h;
    private boolean f8890i;

    public CloseButton(Context context) {
        super(context);
        this.f8882a = new Paint();
        this.f8883b = new Paint();
        this.f8884c = new Paint();
        this.f8890i = false;
        m12792a();
    }

    public CloseButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8882a = new Paint();
        this.f8883b = new Paint();
        this.f8884c = new Paint();
        this.f8890i = false;
        m12792a();
    }

    private final void m12792a() {
        this.f8882a.setAntiAlias(true);
        this.f8882a.setColor(-2236963);
        this.f8882a.setStrokeWidth(dm.DEFAULT_BACKOFF_MULT);
        this.f8882a.setStyle(Style.FILL_AND_STROKE);
        this.f8883b.setAntiAlias(true);
        this.f8883b.setColor(-6710887);
        this.f8883b.setStrokeWidth(dm.DEFAULT_BACKOFF_MULT);
        this.f8883b.setStyle(Style.FILL_AND_STROKE);
        this.f8884c.setAntiAlias(true);
        this.f8884c.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f8884c.setStrokeWidth(3.0f);
        this.f8884c.setStyle(Style.FILL_AND_STROKE);
        this.f8885d = (float) SizeUtil.dp30;
        this.f8886e = this.f8885d * 0.33333334f;
        this.f8888g = this.f8885d * 0.6666667f;
        this.f8887f = this.f8885d * 0.33333334f;
        this.f8889h = this.f8885d * 0.6666667f;
    }

    public boolean performClick() {
        return super.performClick();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f8890i = true;
            invalidate();
            return true;
        } else if (motionEvent.getAction() != 1) {
            return super.onTouchEvent(motionEvent);
        } else {
            this.f8890i = false;
            invalidate();
            performClick();
            return true;
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
        setMeasuredDimension((int) this.f8885d, (int) this.f8885d);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) ((getWidth() / 2) - 1), this.f8890i ? this.f8883b : this.f8882a);
        canvas.drawLine(this.f8886e, this.f8887f, this.f8888g, this.f8889h, this.f8884c);
        canvas.drawLine(this.f8888g, this.f8887f, this.f8886e, this.f8889h, this.f8884c);
    }
}
