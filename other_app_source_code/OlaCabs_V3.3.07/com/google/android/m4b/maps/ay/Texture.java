package com.google.android.m4b.maps.ay;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.opengl.GLUtils;
import android.util.DisplayMetrics;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.android.m4b.maps.bd.ImageData;
import com.google.android.m4b.maps.bd.TextureState;
import com.google.android.m4b.maps.p058v.Util;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ay.l */
public final class Texture extends TextureState {
    private boolean f4939d;
    private int f4940e;
    private final int[] f4941f;
    private boolean f4942g;
    private boolean f4943h;
    private boolean f4944i;
    private boolean f4945j;
    private float f4946k;
    private float f4947l;
    private final long f4948m;
    private int f4949n;
    private int f4950o;
    private boolean f4951p;

    /* renamed from: com.google.android.m4b.maps.ay.l.a */
    public static class Texture<K> extends LRUCache<K, Texture> {
        public final /* synthetic */ void m7598b(Object obj, Object obj2) {
            ((Texture) obj2).m7626f();
        }

        public Texture(int i) {
            super(i);
        }
    }

    public Texture(GLState gLState) {
        this.f4941f = new int[1];
        this.f4942g = false;
        this.f4943h = false;
        this.f4944i = false;
        this.f4945j = true;
        this.f4950o = 0;
        this.f4951p = false;
        this.f4948m = GLState.m7500a(gLState);
        this.f4941f[0] = 0;
        this.f4949n = 1;
    }

    public Texture(GLState gLState, boolean z) {
        this(gLState);
        this.f4951p = z;
    }

    private GLState m7608k() {
        GLState b = GLState.m7502b(this.f4948m);
        if (b != null) {
            return b;
        }
        throw new IllegalStateException("Texture is out of date.");
    }

    public final void m7613a(GL10 gl10) {
        if (gl10 != m7608k().f4847a) {
            throw new IllegalStateException("Attempted to bind texture into an OpenGL context other than the one it was created from.");
        } else if (this.f4941f[0] != 0) {
            gl10.glBindTexture(3553, this.f4941f[0]);
        }
    }

    public final GL10 m7609a() {
        return m7608k().f4847a;
    }

    public final void m7610a(Resources resources, int i) {
        Bitmap e = Texture.m7607e(resources, i);
        m7612a(e, e.getWidth(), e.getHeight());
        if (!this.f4939d && !this.f4951p) {
            e.recycle();
        }
    }

    public final void m7616b(Resources resources, int i) {
        Bitmap e = Texture.m7607e(resources, i);
        m7605a(e, e.getWidth(), e.getHeight(), false, true, false);
        if (!this.f4939d && !this.f4951p) {
            e.recycle();
        }
    }

    public final void m7611a(Bitmap bitmap) {
        boolean z;
        Bitmap bitmap2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (Texture.m7606c(bitmap)) {
            z = false;
            bitmap2 = bitmap;
        } else {
            bitmap2 = Texture.m7604a(bitmap, Config.ARGB_8888, m7608k().m7529l());
            z = true;
        }
        m7605a(bitmap2, width, height, false, false, true);
        if (z && !this.f4939d && !this.f4951p) {
            bitmap2.recycle();
        }
    }

    public final void m7617b(Bitmap bitmap) {
        m7612a(bitmap, bitmap.getWidth(), bitmap.getHeight());
    }

    public final void m7612a(Bitmap bitmap, int i, int i2) {
        boolean z;
        Bitmap bitmap2;
        if (Texture.m7606c(bitmap)) {
            z = false;
            bitmap2 = bitmap;
        } else {
            bitmap2 = Texture.m7604a(bitmap, Config.ARGB_8888, m7608k().m7529l());
            z = true;
        }
        m7605a(bitmap2, i, i2, false, false, false);
        if (z && !this.f4939d && !this.f4951p) {
            bitmap2.recycle();
        }
    }

    public final void m7620c(Resources resources, int i) {
        boolean z;
        Bitmap bitmap;
        Bitmap e = Texture.m7607e(resources, i);
        int width = e.getWidth();
        int height = e.getHeight();
        if (Texture.m7606c(e)) {
            z = false;
            bitmap = e;
        } else {
            z = true;
            bitmap = Texture.m7604a(e, Config.ALPHA_8, m7608k().m7529l());
        }
        m7605a(bitmap.extractAlpha(), width, height, true, false, false);
        if (!(!z || this.f4939d || this.f4951p)) {
            bitmap.recycle();
        }
        if (!this.f4939d && !this.f4951p) {
            e.recycle();
        }
    }

    public final void m7623d(Resources resources, int i) {
        Bitmap e = Texture.m7607e(resources, i);
        m7605a(e, e.getWidth(), e.getHeight(), true, true, false);
        if (!this.f4939d && !this.f4951p) {
            e.recycle();
        }
    }

    public final void m7614a(boolean z) {
        this.f4942g = z;
    }

    public final void m7618b(boolean z) {
        this.f4943h = z;
    }

    public final void m7621c(boolean z) {
        this.f4944i = z;
    }

    public final void m7624d(boolean z) {
        this.f4945j = z;
    }

    public final float m7615b() {
        return this.f4946k;
    }

    public final float m7619c() {
        return this.f4947l;
    }

    public final int m7622d() {
        return this.f4940e;
    }

    public final synchronized void m7625e() {
        this.f4949n++;
    }

    public final synchronized void m7626f() {
        if (this.f4949n <= 0) {
            Util.m11555b("Texture", "releaseRef called on Texture with " + this.f4949n + " references!");
        } else {
            GLState b = GLState.m7502b(this.f4948m);
            int i = this.f4949n - 1;
            this.f4949n = i;
            if (i == 0 && this.f4941f[0] != 0) {
                if (b != null) {
                    b.m7520c(this.f4941f[0]);
                }
                this.f4950o = 0;
            }
        }
    }

    public final int m7627g() {
        return this.f4949n;
    }

    private static Bitmap m7607e(Resources resources, int i) {
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Options options = new Options();
        options.inDensity = displayMetrics.densityDpi;
        options.inTargetDensity = displayMetrics.densityDpi;
        return BitmapFactoryInstrumentation.decodeResource(resources, i, options);
    }

    public static int m7603a(int i, int i2) {
        while (i2 < i) {
            i2 <<= 1;
        }
        return i2;
    }

    private static boolean m7606c(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        return (width & (width + -1)) == 0 && ((height - 1) & height) == 0;
    }

    private static Bitmap m7604a(Bitmap bitmap, Config config, NativeAllocator nativeAllocator) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int a = Texture.m7603a(width, 1);
        int a2 = Texture.m7603a(height, 1);
        Bitmap a3 = nativeAllocator.m7591a(a, a2, config);
        a3.eraseColor(0);
        Canvas canvas = new Canvas(a3);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        if (a > width) {
            canvas.drawBitmap(bitmap, new Rect(width - 1, 0, width, height), new Rect(width, 0, width + 1, height), paint);
        }
        if (a2 > height) {
            canvas.drawBitmap(bitmap, new Rect(0, height - 1, width, height), new Rect(0, height, width, height + 1), paint);
        }
        if (a > width && a2 > height) {
            canvas.drawBitmap(bitmap, new Rect(width - 1, height - 1, width, height), new Rect(width, height, width + 1, height + 1), paint);
        }
        return a3;
    }

    private synchronized void m7605a(Bitmap bitmap, int i, int i2, boolean z, boolean z2, boolean z3) {
        if (z2 && z3) {
            throw new IllegalArgumentException("Cannot have both isMipMap and autoGenerateMipMap be true.");
        }
        int a;
        int a2;
        int i3;
        GLState k = m7608k();
        GL10 gl10 = k.f4847a;
        Object obj = (z3 && k.f4848b) ? 1 : null;
        if (bitmap == null) {
            this.f4940e = i2;
            a = Texture.m7603a(i, 1);
            a2 = Texture.m7603a(i2, 1);
        } else {
            if (z2) {
                i3 = i2 / 2;
            } else {
                i3 = i2;
            }
            this.f4940e = i3;
            a = bitmap.getWidth();
            a2 = bitmap.getHeight();
        }
        i3 = k.m7505C();
        if (a > i3 || a2 > i3) {
            throw new IllegalArgumentException("Textures with dimensions" + a + "x" + a2 + " are larger than  the maximum supported size " + i3 + "x" + i3);
        }
        int i4;
        this.f4946k = ((float) i) / ((float) a);
        this.f4947l = ((float) i2) / ((float) a2);
        if (this.f4951p) {
            m7601b(this.f4942g ? 10497 : 33071, this.f4943h ? 10497 : 33071);
            if (!this.f4944i) {
                m7602c(9728, 9728);
            } else if (!z2 && obj == null) {
                m7602c(9729, 9729);
            } else if (this.f4945j) {
                m7602c(9987, 9729);
            } else {
                m7602c(9985, 9729);
            }
        } else {
            if (this.f4941f[0] == 0) {
                gl10.glGenTextures(1, this.f4941f, 0);
            }
            gl10.glBindTexture(3553, this.f4941f[0]);
            if (this.f4942g) {
                gl10.glTexParameterf(3553, 10242, 10497.0f);
            } else {
                gl10.glTexParameterf(3553, 10242, 33071.0f);
            }
            if (this.f4943h) {
                gl10.glTexParameterf(3553, 10243, 10497.0f);
            } else {
                gl10.glTexParameterf(3553, 10243, 33071.0f);
            }
            if (this.f4944i) {
                if (!z2 && obj == null) {
                    gl10.glTexParameterf(3553, 10241, 9729.0f);
                } else if (this.f4945j) {
                    gl10.glTexParameterf(3553, 10241, 9987.0f);
                } else {
                    gl10.glTexParameterf(3553, 10241, 9985.0f);
                }
                gl10.glTexParameterf(3553, 10240, 9729.0f);
            } else {
                gl10.glTexParameterf(3553, 10241, 9728.0f);
                gl10.glTexParameterf(3553, 10240, 9728.0f);
            }
        }
        if (bitmap == null) {
            gl10.glFinish();
            gl10.glTexParameterf(3553, 33169, 0.0f);
            gl10.glCopyTexImage2D(3553, 0, 6407, 0, 0, a, a2, 0);
        } else if (z2) {
            int i5 = 0;
            int i6 = 0;
            i3 = bitmap.getWidth();
            while (i3 > 0) {
                Bitmap a3;
                Canvas canvas = new Canvas();
                Rect rect = new Rect(0, i6, i3, i3 + i6);
                Rect rect2 = new Rect(0, 0, i3, i3);
                if (z) {
                    a3 = k.m7529l().m7591a(i3, i3, Config.ALPHA_8);
                } else {
                    a3 = k.m7529l().m7591a(i3, i3, Config.ARGB_8888);
                }
                canvas.setBitmap(a3);
                canvas.drawBitmap(bitmap, rect, rect2, null);
                if (this.f4951p) {
                    m7599a(new ImageData(a3), true);
                    i3 = 0;
                } else {
                    gl10.glTexParameterf(3553, 33169, 0.0f);
                    int i7 = i5 + 1;
                    GLUtils.texImage2D(3553, i5, a3, 0);
                    a3.recycle();
                    i5 = i7;
                }
                i6 += i3;
                i3 /= 2;
            }
        } else if (obj != null) {
            if (this.f4951p) {
                m7599a(new ImageData(bitmap), true);
            } else {
                gl10.glTexParameterx(3553, 33169, 1);
                GLUtils.texImage2D(3553, 0, bitmap, 0);
            }
        } else if (this.f4951p) {
            m7599a(new ImageData(bitmap), false);
        } else {
            gl10.glTexParameterf(3553, 33169, 0.0f);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
        }
        if (bitmap == null) {
            i4 = (a * a2) * 3;
        } else {
            i4 = bitmap.getRowBytes() * bitmap.getHeight();
        }
        this.f4950o = i4;
        boolean z4 = this.f4939d;
    }

    public final int m7628h() {
        return this.f4950o;
    }
}
