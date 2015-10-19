package com.google.android.m4b.maps.be;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.os.IBinder;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.p042r.ac;

/* compiled from: CompassButtonImageView */
public final class bs extends ImageView implements AnimationListener, ac {
    private Matrix f5884a;
    private Matrix f5885b;
    private float f5886c;
    private float f5887d;
    private Animation f5888e;
    private Animation f5889f;
    private boolean f5890g;

    public bs(Context context) {
        super(context);
        setScaleType(ScaleType.MATRIX);
        setImageResource(R.ic_compass_needle);
        setBackgroundResource(R.button_compass_selector);
        this.f5888e = AnimationUtils.loadAnimation(context, R.compass_button_fade_in);
        this.f5888e.setAnimationListener(this);
        this.f5889f = AnimationUtils.loadAnimation(context, R.compass_button_fade_out);
        this.f5889f.setAnimationListener(this);
    }

    public final void m9034a(boolean z, CameraPosition cameraPosition) {
        this.f5890g = z;
        if (z) {
            setVisibility(4);
            m9033a(cameraPosition);
            return;
        }
        clearAnimation();
        setVisibility(8);
    }

    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f5884a = new Matrix();
        this.f5885b = new Matrix();
        RectF rectF = new RectF(0.0f, 0.0f, (float) i, (float) i2);
        this.f5884a.setRectToRect(new RectF(0.0f, 0.0f, (float) getDrawable().getIntrinsicWidth(), (float) getDrawable().getIntrinsicHeight()), rectF, ScaleToFit.CENTER);
        m9032a();
    }

    public final void m9033a(CameraPosition cameraPosition) {
        if (this.f5890g) {
            this.f5886c = cameraPosition.f7532d;
            this.f5887d = cameraPosition.f7531c;
            m9032a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m9032a() {
        /*
        r9 = this;
        r1 = 1;
        r0 = 0;
        r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r7 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r9.f5884a;
        if (r2 == 0) goto L_0x004d;
    L_0x000c:
        r2 = r9.f5885b;
        if (r2 == 0) goto L_0x004d;
    L_0x0010:
        r2 = r9.f5885b;
        r3 = r9.f5884a;
        r2.set(r3);
        r2 = r9.f5885b;
        r3 = r9.f5886c;
        r3 = -r3;
        r4 = r9.getWidth();
        r4 = (float) r4;
        r4 = r4 / r6;
        r5 = r9.getHeight();
        r5 = (float) r5;
        r5 = r5 / r6;
        r2.postRotate(r3, r4, r5);
        r2 = r9.f5887d;
        r3 = 1119092736; // 0x42b40000 float:90.0 double:5.529052754E-315;
        r2 = r2 / r3;
        r3 = 1060320051; // 0x3f333333 float:0.7 double:5.23867711E-315;
        r2 = r2 * r3;
        r3 = r9.f5885b;
        r4 = r8 - r2;
        r3.postScale(r8, r4);
        r3 = r9.f5885b;
        r4 = 0;
        r2 = r2 / r6;
        r5 = r9.getHeight();
        r5 = (float) r5;
        r2 = r2 * r5;
        r3.postTranslate(r4, r2);
        r2 = r9.f5885b;
        r9.setImageMatrix(r2);
    L_0x004d:
        r2 = r9.f5887d;
        r3 = r9.f5886c;
        r2 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1));
        if (r2 > 0) goto L_0x0063;
    L_0x0055:
        r2 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r2 < 0) goto L_0x0060;
    L_0x0059:
        r2 = 1135853568; // 0x43b3c000 float:359.5 double:5.611862267E-315;
        r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x007a;
    L_0x0060:
        r2 = r1;
    L_0x0061:
        if (r2 != 0) goto L_0x0064;
    L_0x0063:
        r0 = r1;
    L_0x0064:
        if (r0 != 0) goto L_0x007c;
    L_0x0066:
        r0 = r9.getVisibility();
        if (r0 != 0) goto L_0x0079;
    L_0x006c:
        r0 = r9.getAnimation();
        r1 = r9.f5889f;
        if (r0 == r1) goto L_0x0079;
    L_0x0074:
        r0 = r9.f5889f;
        r9.startAnimation(r0);
    L_0x0079:
        return;
    L_0x007a:
        r2 = r0;
        goto L_0x0061;
    L_0x007c:
        r0 = r9.getVisibility();
        if (r0 != 0) goto L_0x008e;
    L_0x0082:
        r0 = r9.getAnimation();
        r1 = r9.f5889f;
        if (r0 != r1) goto L_0x008e;
    L_0x008a:
        r9.clearAnimation();
        goto L_0x0079;
    L_0x008e:
        r0 = r9.getVisibility();
        r1 = 4;
        if (r0 != r1) goto L_0x0079;
    L_0x0095:
        r0 = r9.getAnimation();
        r1 = r9.f5888e;
        if (r0 == r1) goto L_0x0079;
    L_0x009d:
        r0 = r9.f5888e;
        r9.startAnimation(r0);
        goto L_0x0079;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.be.bs.a():void");
    }

    public final void onAnimationEnd(Animation animation) {
        if (animation == this.f5889f && this.f5890g) {
            setVisibility(4);
        }
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationStart(Animation animation) {
        if (animation == this.f5888e && this.f5890g) {
            setVisibility(0);
        }
    }

    public final IBinder asBinder() {
        return null;
    }
}
