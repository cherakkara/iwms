package com.leanplum.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;

public class BitmapUtil {
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float f = (float) i;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static void stateBackgroundDarkerByPercentage(View view, int i, int i2) {
        stateBackground(view, i, getDarker(i, i2));
    }

    public static int getDarker(int i, int i2) {
        if (i2 < 0 || i2 > 100) {
            i2 = 0;
        }
        double d = ((double) (100 - i2)) / 100.0d;
        return (((int) (d * ((double) (i & MotionEventCompat.ACTION_MASK)))) & MotionEventCompat.ACTION_MASK) | ((((i >>> 24) << 24) | ((((int) (((double) ((i >> 16) & MotionEventCompat.ACTION_MASK)) * d)) & MotionEventCompat.ACTION_MASK) << 16)) | ((((int) (((double) ((i >> 8) & MotionEventCompat.ACTION_MASK)) * d)) & MotionEventCompat.ACTION_MASK) << 8));
    }

    public static void stateBackground(View view, int i, int i2) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(getBackground(i, i2));
        } else {
            view.setBackgroundColor(i);
        }
    }

    private static Drawable getBackground(int i, int i2) {
        Drawable stateListDrawable = new StateListDrawable();
        int i3 = SizeUtil.dp10;
        Shape roundRectShape = new RoundRectShape(new float[]{(float) i3, (float) i3, (float) i3, (float) i3, (float) i3, (float) i3, (float) i3, (float) i3}, null, null);
        Drawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(roundRectShape);
        shapeDrawable.getPaint().setColor(i2);
        stateListDrawable.addState(new int[]{16842919, 16842908}, shapeDrawable);
        stateListDrawable.addState(new int[]{-16842919, 16842908}, shapeDrawable);
        stateListDrawable.addState(new int[]{16842919, -16842908}, shapeDrawable);
        shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(roundRectShape);
        shapeDrawable.getPaint().setColor(i);
        stateListDrawable.addState(new int[]{-16842919, -16842908}, shapeDrawable);
        return stateListDrawable;
    }
}
