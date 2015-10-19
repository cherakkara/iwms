package com.leanplum.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.leanplum.utils.BitmapUtil;
import com.leanplum.utils.SizeUtil;
import com.olacabs.customer.p076d.dm;

public class BackgroundImageView extends ImageView {
    private Paint f8878a;
    private boolean f8879b;
    private Matrix f8880c;
    private boolean f8881d;

    public BackgroundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8878a = new Paint();
        this.f8880c = new Matrix();
        m12791a();
    }

    public BackgroundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8878a = new Paint();
        this.f8880c = new Matrix();
        m12791a();
    }

    public BackgroundImageView(Context context, boolean z) {
        super(context);
        this.f8878a = new Paint();
        this.f8880c = new Matrix();
        m12791a();
        this.f8879b = z;
    }

    private void m12791a() {
        this.f8878a.setColor(-16711936);
        this.f8878a.setStrokeWidth(dm.DEFAULT_BACKOFF_MULT);
        this.f8878a.setStyle(Style.FILL_AND_STROKE);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.f8879b) {
            if (this.f8881d) {
                this.f8881d = false;
                return;
            }
            Bitmap loadBitmapFromView = loadBitmapFromView(this);
            canvas.drawColor(0, Mode.CLEAR);
            canvas.drawBitmap(BitmapUtil.getRoundedCornerBitmap(loadBitmapFromView, SizeUtil.dp20), this.f8880c, this.f8878a);
        }
    }

    public Bitmap loadBitmapFromView(View view) {
        if (view.getMeasuredHeight() <= 0) {
            view.measure(-2, -2);
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        this.f8881d = true;
        view.draw(canvas);
        return createBitmap;
    }
}
