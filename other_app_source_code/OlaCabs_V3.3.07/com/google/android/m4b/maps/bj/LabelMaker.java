package com.google.android.m4b.maps.bj;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.opengl.GLUtils;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.FloatMath;
import com.olacabs.customer.p076d.br;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.bj.v */
public final class LabelMaker {
    private int f6631a;
    private int f6632b;
    private final boolean f6633c;
    private Bitmap f6634d;
    private Canvas f6635e;
    private int f6636f;
    private float f6637g;
    private float f6638h;
    private int f6639i;
    private int f6640j;
    private int f6641k;
    private final ArrayList<LabelMaker> f6642l;
    private int f6643m;
    private final boolean f6644n;

    /* renamed from: com.google.android.m4b.maps.bj.v.a */
    static class LabelMaker {
        public final Grid f6630a;

        public LabelMaker(Grid grid, float f, float f2, float f3, int i, int i2, int i3, int i4) {
            this.f6630a = grid;
            int[] iArr = new int[]{i, i2, i3, i4};
        }
    }

    public LabelMaker(boolean z, int i, int i2, boolean z2) {
        this.f6642l = new ArrayList();
        this.f6631a = i;
        this.f6632b = i2;
        this.f6637g = br.DEFAULT_BACKOFF_MULT / ((float) this.f6631a);
        this.f6638h = br.DEFAULT_BACKOFF_MULT / ((float) this.f6632b);
        this.f6633c = z;
        this.f6644n = z2;
        this.f6643m = 0;
    }

    public LabelMaker(boolean z, int i, int i2) {
        this(true, AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS, true);
    }

    public final void m10016a(GL10 gl10) {
        this.f6643m = 1;
        int[] iArr = new int[1];
        gl10.glGenTextures(1, iArr, 0);
        this.f6636f = iArr[0];
        gl10.glBindTexture(3553, this.f6636f);
        gl10.glTexParameterf(3553, 10241, 9728.0f);
        gl10.glTexParameterf(3553, 10240, 9728.0f);
        gl10.glTexParameterf(3553, 10242, 33071.0f);
        gl10.glTexParameterf(3553, 10243, 33071.0f);
        gl10.glTexEnvf(8960, 8704, 7681.0f);
    }

    public final void m10019b(GL10 gl10) {
        if (gl10 != null && this.f6643m > 0) {
            gl10.glDeleteTextures(1, new int[]{this.f6636f}, 0);
        }
    }

    public final void m10015a() {
        m10013a(1, 2);
        this.f6642l.clear();
        this.f6639i = 0;
        this.f6640j = 0;
        this.f6641k = 0;
        this.f6634d = Bitmap.createBitmap(this.f6631a, this.f6632b, this.f6633c ? Config.ARGB_8888 : Config.ALPHA_8);
        this.f6635e = new Canvas(this.f6634d);
        this.f6634d.eraseColor(0);
    }

    public final int m10014a(GL10 gl10, String str, Paint paint, Paint paint2) {
        int max;
        int max2;
        int i;
        int strokeWidth;
        int ceil;
        int i2;
        int i3;
        Drawable drawable = null;
        m10013a(2, 2);
        Object obj = drawable != null ? 1 : null;
        Object obj2 = (str == null || paint == null) ? null : 1;
        Rect rect = new Rect();
        if (obj != null) {
            drawable.getPadding(rect);
            max = Math.max(0, drawable.getMinimumWidth());
            max2 = Math.max(0, drawable.getMinimumHeight());
            i = max;
        } else {
            max2 = 0;
            i = 0;
        }
        if (paint2 != null) {
            strokeWidth = (((int) paint2.getStrokeWidth()) + 1) / 2;
            rect.top += strokeWidth;
            rect.bottom += strokeWidth;
            rect.left += strokeWidth;
            rect.right = strokeWidth + rect.right;
        }
        max = 0;
        strokeWidth = 0;
        int i4 = 0;
        if (obj2 != null) {
            i4 = Math.min(str.length(), 20);
            max = (int) FloatMath.ceil(paint.descent());
            strokeWidth = (int) FloatMath.ceil(paint.measureText(str, 0, i4));
            ceil = (int) FloatMath.ceil(-paint.ascent());
        } else {
            ceil = 0;
        }
        max += ceil;
        int min = Math.min(this.f6631a, strokeWidth);
        int i5 = rect.bottom + rect.top;
        int i6 = rect.right + rect.left;
        int max3 = Math.max(max2, max + i5);
        strokeWidth = Math.max(i, min + i6);
        i5 = ((max3 - i5) - max) / 2;
        i = ((strokeWidth - i6) - min) / 2;
        max2 = this.f6639i;
        min = this.f6640j;
        max = this.f6641k;
        int i7 = strokeWidth > this.f6631a ? this.f6631a : strokeWidth;
        if (max2 + i7 > this.f6631a) {
            strokeWidth = 0;
            i2 = max + min;
            i3 = 0;
        } else {
            strokeWidth = max;
            i2 = min;
            i3 = max2;
        }
        int max4 = Math.max(strokeWidth, max3);
        if (i2 + max4 > this.f6632b) {
            throw new IllegalArgumentException("Out of texture space.");
        }
        i6 = i3 + i7;
        strokeWidth = i2 + ceil;
        int i8 = i2 + max3;
        if (obj != null) {
            drawable.setBounds(i3, i2, i3 + i7, i2 + max3);
            drawable.draw(this.f6635e);
        }
        if (obj2 != null) {
            float f = (float) ((rect.left + i3) + i);
            float f2 = (float) ((rect.top + strokeWidth) + i5);
            Path path = new Path();
            paint.getTextPath(str, 0, i4, f, f2, path);
            if (paint2 != null) {
                this.f6635e.drawPath(path, paint2);
            }
            this.f6635e.drawPath(path, paint);
        }
        Grid grid = new Grid(2, 2);
        float f3 = ((float) i3) * this.f6637g;
        float f4 = ((float) i6) * this.f6637g;
        float f5 = ((float) i2) * this.f6638h;
        float f6 = ((float) i8) * this.f6638h;
        grid.m9977a(0, 0, 0.0f, 0.0f, 0.0f, f3, f6);
        grid.m9977a(1, 0, (float) i7, 0.0f, 0.0f, f4, f6);
        grid.m9977a(0, 1, 0.0f, (float) max3, 0.0f, f3, f5);
        grid.m9977a(1, 1, (float) i7, (float) max3, 0.0f, f4, f5);
        this.f6639i = i3 + i7;
        this.f6640j = i2;
        this.f6641k = max4;
        this.f6642l.add(new LabelMaker(grid, (float) i7, (float) max3, (float) ceil, i3, i2 + max3, i7, -max3));
        return this.f6642l.size() - 1;
    }

    public final void m10020c(GL10 gl10) {
        m10013a(2, 1);
        gl10.glBindTexture(3553, this.f6636f);
        GLUtils.texImage2D(3553, 0, this.f6634d, 0);
        this.f6634d.recycle();
        this.f6634d = null;
        this.f6635e = null;
    }

    public final void m10017a(GL10 gl10, float f, float f2) {
        m10013a(1, 3);
        gl10.glBindTexture(3553, this.f6636f);
        gl10.glShadeModel(7424);
        gl10.glEnable(3042);
        gl10.glBlendFunc(1, 771);
        gl10.glColor4x(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT);
        if (this.f6644n) {
            gl10.glMatrixMode(5889);
            gl10.glPushMatrix();
            gl10.glLoadIdentity();
            gl10.glOrthof(0.0f, f, 0.0f, f2, 0.0f, br.DEFAULT_BACKOFF_MULT);
            gl10.glMatrixMode(5888);
            gl10.glPushMatrix();
            gl10.glLoadIdentity();
        }
    }

    public final void m10018a(GL10 gl10, int i) {
        m10013a(3, 3);
        float floor = FloatMath.floor(0.0f);
        float floor2 = FloatMath.floor(0.0f);
        if (this.f6644n) {
            gl10.glMatrixMode(5888);
            gl10.glPushMatrix();
            gl10.glTranslatef(floor, floor2, 0.0f);
        }
        LabelMaker labelMaker = (LabelMaker) this.f6642l.get(i);
        gl10.glEnable(3553);
        labelMaker.f6630a.m9978a(gl10, true);
        if (this.f6644n) {
            gl10.glMatrixMode(5888);
            gl10.glPopMatrix();
        }
    }

    public final void m10021d(GL10 gl10) {
        m10013a(3, 1);
        gl10.glDisable(3042);
        if (this.f6644n) {
            gl10.glMatrixMode(5889);
            gl10.glPopMatrix();
            gl10.glMatrixMode(5888);
            gl10.glPopMatrix();
        }
    }

    private void m10013a(int i, int i2) {
        if (this.f6643m != i) {
            throw new IllegalArgumentException("Can't call this method now.");
        }
        this.f6643m = i2;
    }
}
