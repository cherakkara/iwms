package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;

public abstract class RoundedBitmapDrawable extends Drawable {
    private static final int DEFAULT_PAINT_FLAGS = 6;
    private boolean mApplyGravity;
    Bitmap mBitmap;
    private int mBitmapHeight;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private float mCornerRadius;
    final Rect mDstRect;
    final RectF mDstRectF;
    private int mGravity;
    private Paint mPaint;
    private int mTargetDensity;

    public final Paint getPaint() {
        return this.mPaint;
    }

    public final Bitmap getBitmap() {
        return this.mBitmap;
    }

    private void computeBitmapSize() {
        this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
        this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
    }

    public void setTargetDensity(Canvas canvas) {
        setTargetDensity(canvas.getDensity());
    }

    public void setTargetDensity(DisplayMetrics displayMetrics) {
        setTargetDensity(displayMetrics.densityDpi);
    }

    public void setTargetDensity(int i) {
        if (this.mTargetDensity != i) {
            if (i == 0) {
                i = 160;
            }
            this.mTargetDensity = i;
            if (this.mBitmap != null) {
                computeBitmapSize();
            }
            invalidateSelf();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            this.mGravity = i;
            this.mApplyGravity = true;
            invalidateSelf();
        }
    }

    public void setMipMap(boolean z) {
        throw new UnsupportedOperationException();
    }

    public boolean hasMipMap() {
        throw new UnsupportedOperationException();
    }

    public void setAntiAlias(boolean z) {
        this.mPaint.setAntiAlias(z);
        invalidateSelf();
    }

    public boolean hasAntiAlias() {
        return this.mPaint.isAntiAlias();
    }

    public void setFilterBitmap(boolean z) {
        this.mPaint.setFilterBitmap(z);
        invalidateSelf();
    }

    public void setDither(boolean z) {
        this.mPaint.setDither(z);
        invalidateSelf();
    }

    void gravityCompatApply(int i, int i2, int i3, Rect rect, Rect rect2) {
        throw new UnsupportedOperationException();
    }

    void updateDstRect() {
        if (this.mApplyGravity) {
            gravityCompatApply(this.mGravity, this.mBitmapWidth, this.mBitmapHeight, getBounds(), this.mDstRect);
            this.mDstRectF.set(this.mDstRect);
            this.mApplyGravity = false;
        }
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            updateDstRect();
            Paint paint = this.mPaint;
            if (paint.getShader() == null) {
                canvas.drawBitmap(bitmap, null, this.mDstRect, paint);
            } else {
                canvas.drawRoundRect(this.mDstRectF, this.mCornerRadius, this.mCornerRadius, paint);
            }
        }
    }

    public void setAlpha(int i) {
        if (i != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.mPaint.getAlpha();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }

    public void setCornerRadius(float f) {
        if (isGreaterThanZero(f)) {
            this.mPaint.setShader(this.mBitmapShader);
        } else {
            this.mPaint.setShader(null);
        }
        this.mCornerRadius = f;
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }

    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }

    public int getOpacity() {
        if (this.mGravity != 119) {
            return -3;
        }
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmap.hasAlpha() || this.mPaint.getAlpha() < MotionEventCompat.ACTION_MASK || isGreaterThanZero(this.mCornerRadius)) {
            return -3;
        }
        return -1;
    }

    RoundedBitmapDrawable(Resources resources, Bitmap bitmap) {
        this.mTargetDensity = 160;
        this.mGravity = 119;
        this.mPaint = new Paint(DEFAULT_PAINT_FLAGS);
        this.mDstRect = new Rect();
        this.mDstRectF = new RectF();
        this.mApplyGravity = true;
        if (resources != null) {
            this.mTargetDensity = resources.getDisplayMetrics().densityDpi;
        }
        this.mBitmap = bitmap;
        if (this.mBitmap != null) {
            computeBitmapSize();
            this.mBitmapShader = new BitmapShader(this.mBitmap, TileMode.CLAMP, TileMode.CLAMP);
            return;
        }
        this.mBitmapHeight = -1;
        this.mBitmapWidth = -1;
    }

    private static boolean isGreaterThanZero(float f) {
        return Float.compare(f, 0.0f) > 0;
    }
}
