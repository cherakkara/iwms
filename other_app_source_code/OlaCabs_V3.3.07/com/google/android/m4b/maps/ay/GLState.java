package com.google.android.m4b.maps.ay;

import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.ah;
import com.google.android.m4b.maps.az.InterleavedVBO;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.az.TexCoordVBO.TexCoordVBO;
import com.google.android.m4b.maps.az.VBONativeBuffer;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.google.android.m4b.maps.az.VertexVBO.VertexVBO;
import com.google.android.m4b.maps.bd.EntityRenderer;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import com.google.p025a.p028c.au;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.ay.e */
public final class GLState {
    private static final AtomicLong f4836H;
    private static final Map<Long, WeakReference<GLState>> f4837J;
    private boolean f4838A;
    private final int f4839B;
    private boolean f4840C;
    private final int f4841D;
    private final EntityRenderer f4842E;
    private final ColorPalette f4843F;
    private final VBONativeBuffer f4844G;
    private final long f4845I;
    private TexturePool f4846K;
    final GL10 f4847a;
    final boolean f4848b;
    public final TexCoordBuffer f4849c;
    public final InterleavedVBO f4850d;
    public final VertexBuffer f4851e;
    public final VertexBuffer f4852f;
    public final VertexBuffer f4853g;
    public final VertexBuffer f4854h;
    public final VertexBuffer f4855i;
    public final float[] f4856j;
    public final float[] f4857k;
    public final Point f4858l;
    public final Point f4859m;
    private ah f4860n;
    private final NativeAllocator f4861o;
    private final int[] f4862p;
    private int f4863q;
    private int f4864r;
    private int[] f4865s;
    private int f4866t;
    private Integer f4867u;
    private Integer f4868v;
    private boolean f4869w;
    private long f4870x;
    private long f4871y;
    private boolean f4872z;

    static {
        f4836H = new AtomicLong(0);
        f4837J = au.m2807a();
    }

    public GLState(GL10 gl10, NativeAllocator nativeAllocator, ah ahVar, EntityRenderer entityRenderer, Resources resources) {
        this.f4872z = true;
        this.f4838A = true;
        this.f4840C = false;
        this.f4849c = new TexCoordVBO(new int[]{0, 0, 0, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, 0, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT});
        this.f4850d = new InterleavedVBO.InterleavedVBO(new float[]{-1.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, -1.0f, -1.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, br.DEFAULT_BACKOFF_MULT, -1.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT}, 9);
        this.f4851e = new VertexVBO(new int[]{0, AccessibilityNodeInfoCompat.ACTION_CUT, 0, 0, 0, 0, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, 0, AccessibilityNodeInfoCompat.ACTION_CUT, 0, 0});
        this.f4852f = new VertexVBO(new int[]{0, 0, AccessibilityNodeInfoCompat.ACTION_CUT, 0, 0, 0, AccessibilityNodeInfoCompat.ACTION_CUT, 0, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, 0, 0});
        this.f4853g = new VertexVBO(new int[]{SupportMenu.CATEGORY_MASK, AccessibilityNodeInfoCompat.ACTION_CUT, 0, SupportMenu.CATEGORY_MASK, SupportMenu.CATEGORY_MASK, 0, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, 0, AccessibilityNodeInfoCompat.ACTION_CUT, SupportMenu.CATEGORY_MASK, 0});
        this.f4854h = new VertexVBO(new int[]{SupportMenu.CATEGORY_MASK, 0, AccessibilityNodeInfoCompat.ACTION_CUT, SupportMenu.CATEGORY_MASK, 0, SupportMenu.CATEGORY_MASK, AccessibilityNodeInfoCompat.ACTION_CUT, 0, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, 0, SupportMenu.CATEGORY_MASK});
        this.f4855i = new VertexVBO(new int[]{0, AccessibilityNodeInfoCompat.ACTION_CUT, 0, 0, 0, 0, AccessibilityNodeInfoCompat.ACTION_CUT, 0, 0, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, 0});
        this.f4856j = new float[8];
        this.f4857k = new float[4];
        this.f4858l = new Point();
        this.f4859m = new Point();
        this.f4843F = new ColorPalette();
        this.f4844G = new VBONativeBuffer();
        this.f4845I = f4836H.getAndIncrement();
        this.f4847a = gl10;
        this.f4860n = ahVar;
        this.f4861o = nativeAllocator;
        this.f4842E = entityRenderer;
        this.f4847a.glDisable(3024);
        this.f4847a.glEnable(2884);
        this.f4847a.glDisable(2929);
        this.f4847a.glDisable(2960);
        this.f4847a.glCullFace(1029);
        this.f4847a.glFrontFace(2305);
        this.f4847a.glShadeModel(7425);
        this.f4847a.glHint(3152, 4354);
        this.f4864r = 0;
        this.f4862p = new int[AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH];
        this.f4863q = 0;
        this.f4865s = new int[32];
        this.f4866t = 0;
        boolean z = ((gl10 instanceof GL11) && gl10.glGetString(7938).contains("1.1")) || (gl10 instanceof GL20);
        this.f4848b = z;
        int[] iArr = new int[1];
        gl10.glGetIntegerv(3415, iArr, 0);
        this.f4839B = iArr[0];
        gl10.glGetIntegerv(3410, iArr, 0);
        int i = iArr[0];
        gl10.glGetIntegerv(3411, iArr, 0);
        int i2 = iArr[0];
        gl10.glGetIntegerv(3412, iArr, 0);
        int i3 = iArr[0];
        gl10.glGetIntegerv(3413, iArr, 0);
        int i4 = iArr[0];
        gl10.glGetIntegerv(3414, iArr, 0);
        int i5 = iArr[0];
        String glGetString = gl10.glGetString(7936);
        String glGetString2 = gl10.glGetString(7938);
        String glGetString3 = gl10.glGetString(7937);
        String str = "gl";
        String[] strArr = new String[10];
        strArr[0] = "r=" + i;
        strArr[1] = "g=" + i2;
        strArr[2] = "b=" + i3;
        strArr[3] = "a=" + i4;
        strArr[4] = "d=" + i5;
        strArr[5] = "s=" + this.f4839B;
        strArr[6] = "v=" + glGetString;
        strArr[7] = "i=" + glGetString2;
        strArr[8] = "c=" + glGetString3;
        strArr[9] = "e=" + (DeviceSpecificInfo.m11565a() ? "t" : "f");
        UserEventReporter.m11502a(58, str, UserEventReporter.m11500a(strArr));
        gl10.glGetIntegerv(3379, iArr, 0);
        this.f4841D = iArr[0];
        synchronized (f4837J) {
            f4837J.put(Long.valueOf(this.f4845I), new WeakReference(this));
        }
        if (resources != null) {
            this.f4846K = new TexturePool(resources, this);
        }
    }

    public final TexturePool m7513a() {
        return this.f4846K;
    }

    public final void m7517b() {
        this.f4869w = true;
    }

    public final void m7514a(long j) {
        if (this.f4870x == 0) {
            this.f4870x = j;
        } else {
            this.f4870x = Math.min(this.f4870x, j);
        }
        this.f4860n.m6768a(this.f4870x);
    }

    public final long m7519c() {
        if (this.f4870x == 0) {
            return -1;
        }
        return Math.max(0, this.f4870x - SystemClock.uptimeMillis());
    }

    public final boolean m7521d() {
        return this.f4869w;
    }

    public final long m7522e() {
        return this.f4871y;
    }

    public final void m7523f() {
        this.f4860n.m6767a();
        this.f4871y = SystemClock.uptimeMillis();
        this.f4869w = false;
        if (this.f4870x != 0 && this.f4871y >= this.f4870x) {
            this.f4870x = 0;
        }
    }

    public final void m7524g() {
        this.f4860n.m6772b();
    }

    public final boolean m7516a(int i) {
        if (this.f4860n.m6771a(i)) {
            return true;
        }
        this.f4869w = true;
        return false;
    }

    public final void m7518b(int i) {
        this.f4860n.m6773b(20000);
    }

    public final boolean m7525h() {
        return this.f4872z;
    }

    public final void m7526i() {
        this.f4872z = false;
    }

    public final boolean m7527j() {
        return this.f4838A;
    }

    public final void m7528k() {
        this.f4838A = false;
    }

    public final NativeAllocator m7529l() {
        return this.f4861o;
    }

    public final void m7530m() {
        if ((this.f4864r & 1) == 0) {
            this.f4847a.glEnableClientState(32884);
            m7501a(1, true);
        }
    }

    public final void m7531n() {
        if ((this.f4864r & 8) == 0) {
            this.f4847a.glEnableClientState(32886);
            m7501a(8, true);
        }
    }

    public final void m7532o() {
        if ((this.f4864r & 8) != 0) {
            this.f4847a.glDisableClientState(32886);
            m7501a(8, false);
        }
    }

    public final void m7533p() {
        if ((this.f4864r & 2) == 0) {
            this.f4847a.glEnable(3553);
            this.f4847a.glEnableClientState(32888);
            m7501a(2, true);
        }
    }

    public final void m7534q() {
        if ((this.f4864r & 2) != 0) {
            this.f4847a.glDisable(3553);
            this.f4847a.glDisableClientState(32888);
            m7501a(2, false);
        }
    }

    public final void m7535r() {
        if ((this.f4864r & 4) == 0) {
            this.f4847a.glEnable(3042);
            m7501a(4, true);
        }
    }

    public final void m7536s() {
        if ((this.f4864r & 16) == 0) {
            this.f4847a.glEnable(3024);
            m7501a(16, true);
        }
    }

    public final void m7537t() {
        if ((this.f4864r & 32) == 0) {
            this.f4872z = true;
            this.f4847a.glEnable(2929);
            m7501a(32, true);
        }
    }

    public final void m7538u() {
        if ((this.f4864r & 64) == 0) {
            this.f4847a.glEnable(32823);
            m7501a(64, true);
        }
    }

    public final void m7539v() {
        if ((this.f4864r & AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) == 0) {
            this.f4838A = true;
            this.f4847a.glEnable(2960);
            m7501a(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS, true);
        }
    }

    public final void m7540w() {
        if ((this.f4864r & AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) != 0) {
            this.f4847a.glDisable(2960);
            m7501a(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS, false);
        }
    }

    public final GL10 m7541x() {
        return this.f4847a;
    }

    public final ah m7542y() {
        return this.f4860n;
    }

    public final void m7543z() {
        int[] iArr = this.f4862p;
        int i = this.f4863q;
        this.f4863q = i + 1;
        iArr[i] = Integer.MAX_VALUE;
    }

    public final void m7503A() {
        while (true) {
            int i = this.f4863q - 1;
            this.f4863q = i;
            if (i >= 0 && this.f4862p[this.f4863q] != Integer.MAX_VALUE) {
                Object obj = (this.f4862p[this.f4863q] & 1073741824) != 0 ? 1 : null;
                int i2 = this.f4862p[this.f4863q] & 1073741823;
                switch (i2) {
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        if (obj == null) {
                            this.f4847a.glDisableClientState(32884);
                            break;
                        } else {
                            this.f4847a.glEnableClientState(32884);
                            break;
                        }
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        if (obj == null) {
                            this.f4847a.glDisable(3553);
                            this.f4847a.glDisableClientState(32888);
                            break;
                        }
                        this.f4847a.glEnable(3553);
                        this.f4847a.glEnableClientState(32888);
                        break;
                    case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                        if (obj == null) {
                            this.f4847a.glDisable(3042);
                            break;
                        } else {
                            this.f4847a.glEnable(3042);
                            break;
                        }
                    case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                        if (obj == null) {
                            this.f4847a.glDisableClientState(32886);
                            break;
                        } else {
                            this.f4847a.glEnableClientState(32886);
                            break;
                        }
                    case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                        if (obj == null) {
                            this.f4847a.glDisable(3024);
                            break;
                        } else {
                            this.f4847a.glEnable(3024);
                            break;
                        }
                    case HTTP.SP /*32*/:
                        if (obj == null) {
                            this.f4847a.glDisable(2929);
                            break;
                        } else {
                            this.f4847a.glEnable(2929);
                            break;
                        }
                    case AccessibilityNodeInfoCompat.ACTION_ACCESSIBILITY_FOCUS /*64*/:
                        if (obj == null) {
                            this.f4847a.glDisable(32823);
                            break;
                        } else {
                            this.f4847a.glEnable(32823);
                            break;
                        }
                    case AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS /*128*/:
                        if (obj == null) {
                            this.f4847a.glDisable(2960);
                            break;
                        } else {
                            this.f4847a.glEnable(2960);
                            break;
                        }
                }
                if (obj != null) {
                    this.f4864r |= i2;
                } else {
                    this.f4864r &= i2 ^ -1;
                }
            } else {
                return;
            }
        }
    }

    private void m7501a(int i, boolean z) {
        if (z) {
            this.f4864r |= i;
            int[] iArr = this.f4862p;
            int i2 = this.f4863q;
            this.f4863q = i2 + 1;
            iArr[i2] = i;
            return;
        }
        this.f4864r &= i ^ -1;
        iArr = this.f4862p;
        i2 = this.f4863q;
        this.f4863q = i2 + 1;
        iArr[i2] = 1073741824 | i;
    }

    public final synchronized void m7520c(int i) {
        if (this.f4866t == this.f4865s.length) {
            Object obj = new int[(this.f4865s.length * 2)];
            System.arraycopy(this.f4865s, 0, obj, 0, this.f4865s.length);
            this.f4865s = obj;
        }
        int[] iArr = this.f4865s;
        int i2 = this.f4866t;
        this.f4866t = i2 + 1;
        iArr[i2] = i;
    }

    public final synchronized void m7504B() {
        if (this.f4866t > 0) {
            this.f4847a.glDeleteTextures(this.f4866t, this.f4865s, 0);
            this.f4866t = 0;
        }
    }

    public final int m7505C() {
        if (this.f4867u == null) {
            int[] iArr = new int[1];
            this.f4847a.glGetIntegerv(3379, iArr, 0);
            this.f4867u = Integer.valueOf(iArr[0]);
        }
        return this.f4867u.intValue();
    }

    public final int m7506D() {
        if (this.f4868v == null) {
            int[] iArr = new int[2];
            this.f4847a.glGetIntegerv(33902, iArr, 0);
            this.f4868v = Integer.valueOf(iArr[1]);
        }
        return this.f4868v.intValue();
    }

    public final ColorPalette m7507E() {
        return this.f4843F;
    }

    public final boolean m7508F() {
        return this.f4848b;
    }

    public final int m7509G() {
        return this.f4839B;
    }

    public final void m7515a(boolean z) {
        this.f4840C = z;
    }

    public final boolean m7510H() {
        return this.f4840C;
    }

    public final int m7511I() {
        return this.f4841D;
    }

    public final VBONativeBuffer m7512J() {
        return this.f4844G;
    }

    public static long m7500a(GLState gLState) {
        return gLState == null ? -1 : gLState.f4845I;
    }

    public static GLState m7502b(long j) {
        GLState gLState;
        synchronized (f4837J) {
            WeakReference weakReference = (WeakReference) f4837J.get(Long.valueOf(j));
            if (weakReference != null) {
                gLState = (GLState) weakReference.get();
                if (gLState == null) {
                    f4837J.remove(Long.valueOf(j));
                }
            } else {
                gLState = null;
            }
        }
        return gLState;
    }
}
