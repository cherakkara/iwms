package com.google.android.m4b.maps.bj;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.opengl.GLU;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.FloatMath;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.be.ar;
import com.google.android.m4b.maps.be.ay;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.v1.Defaults;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;
import org.apache.http.HttpStatus;

/* compiled from: Overlay */
final class aa extends ExploreByTouchHelper {
    private static final float[] f6284a;
    private static final float[] f6285b;
    private boolean f6286A;
    private int[][] f6287B;
    private final float f6288C;
    private boolean f6289D;
    private boolean f6290E;
    private PanoramaConfig f6291c;
    private PanoramaLink[] f6292d;
    private int f6293e;
    private int f6294f;
    private float f6295g;
    private final Overlay f6296h;
    private final Overlay f6297i;
    private boolean f6298j;
    private boolean f6299k;
    private final af f6300l;
    private final float[] f6301m;
    private LabelMaker f6302n;
    private final CharSequence[] f6303o;
    private final CharSequence[] f6304p;
    private final Paint f6305q;
    private final Paint f6306r;
    private Overlay f6307s;
    private Overlay f6308t;
    private final Object f6309u;
    private int f6310v;
    private int f6311w;
    private long f6312x;
    private long f6313y;
    private boolean f6314z;

    /* renamed from: com.google.android.m4b.maps.bj.aa.a */
    static class Overlay {
        private final int[] f6274a;
        private final int[] f6275b;
        private final int[] f6276c;
        private int f6277d;
        private int f6278e;
        private int f6279f;

        public Overlay() {
            this.f6274a = new int[HttpStatus.SC_BAD_REQUEST];
            this.f6275b = new int[100];
            this.f6276c = new int[100];
        }

        public final void m9717a(int i, int i2) {
            this.f6277d = 0;
            this.f6278e = i;
            this.f6279f = i2;
        }

        public final void m9718a(af afVar, GL10 gl10, float[] fArr, Overlay overlay, int i, int i2, int i3, int i4) {
            afVar.m9784b(gl10);
            int i5 = this.f6277d * 4;
            if (overlay.m9726a(afVar, this.f6274a, i5, fArr, this.f6278e, this.f6279f)) {
                int i6 = this.f6274a[i5];
                int i7 = this.f6274a[i5 + 1];
                int i8 = this.f6274a[i5 + 2];
                i5 = this.f6274a[i5 + 3];
                int i9 = this.f6277d * 4;
                int[] iArr = this.f6274a;
                int max = Math.max(0, i8 - i6);
                int max2 = Math.max(64, i5 - i7);
                i6 = ((i6 + i8) >> 1) - (max >> 1);
                i7 = ((i7 + i5) >> 1) - (max2 >> 1);
                iArr[i9] = i6;
                iArr[i9 + 1] = i7;
                iArr[i9 + 2] = i6 + max;
                iArr[i9 + 3] = i7 + max2;
                this.f6276c[this.f6277d] = i4;
                int[] iArr2 = this.f6275b;
                i7 = this.f6277d;
                this.f6277d = i7 + 1;
                iArr2[i7] = i3;
            }
        }

        public final int m9721b(int i, int i2) {
            int[] iArr = this.f6274a;
            for (int i3 = this.f6277d - 1; i3 >= 0; i3--) {
                int i4 = i3 * 4;
                if (iArr[i4] <= i && iArr[i4 + 1] <= i2 && i < iArr[i4 + 2] && i2 < iArr[i4 + 3]) {
                    return this.f6275b[i3];
                }
            }
            return -1;
        }

        public final int[] m9719a() {
            return this.f6275b;
        }

        public final int m9720b() {
            return this.f6277d;
        }

        public final int[] m9722c() {
            return this.f6274a;
        }

        public final int m9716a(int i) {
            return this.f6276c[i];
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.aa.b */
    static class Overlay {
        private final FloatBuffer f6280a;
        private final int f6281b;
        private final int f6282c;
        private final ByteBuffer f6283d;

        public Overlay(float[] fArr) {
            this(fArr, Overlay.m9724a(fArr.length / 3));
        }

        private Overlay(float[] fArr, byte[] bArr) {
            this(fArr, bArr, bArr);
        }

        private Overlay(float[] fArr, byte[] bArr, byte[] bArr2) {
            this.f6280a = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            this.f6280a.put(fArr, 0, fArr.length);
            this.f6280a.position(0);
            this.f6281b = bArr.length;
            this.f6282c = bArr2.length;
            if (this.f6281b > AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH || this.f6282c > AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH) {
                throw new IllegalArgumentException("Only up to 256 points");
            }
            this.f6283d = Overlay.m9723a(bArr);
            Overlay.m9723a(bArr2);
        }

        private static ByteBuffer m9723a(byte[] bArr) {
            int length = bArr.length;
            ByteBuffer order = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
            order.put(bArr, 0, length);
            order.position(0);
            return order;
        }

        private static byte[] m9724a(int i) {
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) i2;
            }
            return bArr;
        }

        public final boolean m9726a(af afVar, int[] iArr, int i, float[] fArr, int i2, int i3) {
            int limit = this.f6280a.limit() / 3;
            int i4 = Integer.MAX_VALUE;
            int i5 = ExploreByTouchHelper.INVALID_ID;
            int i6 = Integer.MAX_VALUE;
            int i7 = ExploreByTouchHelper.INVALID_ID;
            boolean z = false;
            for (int i8 = 0; i8 < limit; i8++) {
                int i9 = i8 * 3;
                fArr[0] = this.f6280a.get(i9);
                fArr[1] = this.f6280a.get(i9 + 1);
                fArr[2] = this.f6280a.get(i9 + 2);
                fArr[3] = br.DEFAULT_BACKOFF_MULT;
                afVar.m9782a(fArr, 0, fArr, 4);
                i9 = (int) fArr[4];
                int i10 = (int) fArr[5];
                if (!z) {
                    Object obj = (i9 < 0 || i10 < 0 || i9 >= i2 || i10 >= i3) ? null : 1;
                    if (obj == null) {
                        z = false;
                        i4 = Math.min(i4, i9);
                        i5 = Math.max(i5, i9);
                        i6 = Math.min(i6, i10);
                        i7 = Math.max(i7, i10);
                    }
                }
                z = true;
                i4 = Math.min(i4, i9);
                i5 = Math.max(i5, i9);
                i6 = Math.min(i6, i10);
                i7 = Math.max(i7, i10);
            }
            if (z) {
                iArr[i] = i4;
                iArr[i + 1] = i6;
                iArr[i + 2] = i5;
                iArr[i + 3] = i7;
            }
            return z;
        }

        final void m9725a(GL10 gl10, int i) {
            gl10.glEnableClientState(32884);
            gl10.glVertexPointer(3, 5126, 0, this.f6280a);
            gl10.glDisableClientState(32888);
            gl10.glDisable(3553);
            gl10.glDrawElements(6, this.f6281b, 5121, this.f6283d);
        }
    }

    static {
        f6284a = new float[]{0.0f, -0.8f, 0.42096f, -0.1824f, -0.8f, 0.30096f, -0.1824f, -0.8f, 0.2f, 0.0f, -0.8f, 0.32f, 0.1824f, -0.8f, 0.2f, 0.1824f, -0.8f, 0.30096f};
        f6285b = new float[]{0.0f, -0.84000003f, 0.42096f, -0.1824f, -0.84000003f, 0.30096f, -0.1824f, -0.84000003f, 0.2f, 0.0f, -0.84000003f, 0.32f, 0.1824f, -0.84000003f, 0.2f, 0.1824f, -0.84000003f, 0.30096f};
        float[] fArr = new float[]{0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, br.DEFAULT_BACKOFF_MULT};
        byte[] bArr = new byte[]{(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 0};
        bArr = new byte[]{(byte) 0, (byte) 1, (byte) 2, (byte) 0, (byte) 2, (byte) 3, (byte) 0, (byte) 3, (byte) 4, (byte) 0, (byte) 4, (byte) 1};
    }

    public aa(Context context, af afVar, View view) {
        super(view);
        this.f6314z = true;
        this.f6286A = true;
        this.f6288C = context.getResources().getDisplayMetrics().density;
        this.f6296h = new Overlay(f6284a);
        this.f6297i = new Overlay(f6285b);
        this.f6300l = afVar;
        m9730c();
        this.f6303o = context.getResources().getTextArray(R.compass_directions);
        this.f6304p = context.getResources().getTextArray(R.full_compass_directions);
        this.f6305q = new Paint();
        this.f6305q.setAntiAlias(true);
        this.f6305q.setTextAlign(Align.LEFT);
        this.f6305q.setTypeface(Typeface.DEFAULT);
        this.f6305q.setTextSize(100.0f);
        this.f6305q.setARGB(-1, MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK);
        this.f6305q.setStyle(Style.FILL);
        this.f6305q.setStrokeWidth(br.DEFAULT_BACKOFF_MULT);
        this.f6306r = new Paint();
        this.f6306r.setAntiAlias(true);
        this.f6306r.setTextAlign(Align.LEFT);
        this.f6306r.setTypeface(Typeface.DEFAULT);
        this.f6306r.setTextSize(100.0f);
        this.f6306r.setStyle(Style.STROKE);
        this.f6306r.setStrokeWidth(3.0f);
        this.f6306r.setARGB(-1, 0, 0, 0);
        this.f6307s = new Overlay();
        this.f6308t = new Overlay();
        this.f6309u = this.f6308t;
        this.f6301m = new float[8];
    }

    private void m9730c() {
        int i;
        if (((double) this.f6288C) < 2.0d) {
            i = Defaults.RESPONSE_BODY_LIMIT;
        } else {
            i = AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT;
        }
        this.f6302n = new LabelMaker(true, AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT, i, false);
    }

    public final void m9735a(PanoramaConfig panoramaConfig, int i, int i2) {
        boolean z = true;
        this.f6291c = panoramaConfig;
        if (this.f6291c == null || this.f6291c.f6598n != 1) {
            z = false;
        }
        this.f6290E = z;
        this.f6292d = this.f6291c.f6608x;
        this.f6293e = i;
        this.f6294f = i2;
        this.f6295g = ((float) i) / ((float) i2);
        this.f6299k = false;
        this.f6310v = -1;
    }

    public final void m9736a(GL10 gl10) {
        m9728b(gl10);
    }

    private void m9728b(GL10 gl10) {
        this.f6299k = false;
        this.f6298j = false;
        if (this.f6302n != null) {
            this.f6302n.m10019b(gl10);
            this.f6302n = null;
        }
    }

    public final void m9734a(int i, long j) {
        if (i == 0) {
            this.f6313y = j;
        }
    }

    public final PanoramaLink m9733a(int i) {
        try {
            return this.f6292d[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public final void m9737a(GL10 gl10, ay ayVar, long j) {
        if (!this.f6289D && this.f6291c != null) {
            int length;
            int i;
            PanoramaLink panoramaLink;
            Overlay overlay;
            int[] iArr = new int[1];
            gl10.glGetIntegerv(3379, iArr, 0);
            boolean z = this.f6291c.f6609y != null;
            if (iArr[0] < AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT || !z || this.f6291c.f6585a) {
                z = false;
            } else {
                z = true;
            }
            this.f6311w = -1;
            if (j - this.f6313y < 2000) {
                this.f6311w = PanoramaConfig.m9990a(this.f6292d, ayVar.m8750b(), 120.0f);
                long j2 = this.f6313y + 2000;
                if (this.f6312x == 0 || this.f6312x > j2) {
                    this.f6312x = j2;
                }
            }
            if (z) {
                try {
                    if (!this.f6290E) {
                        z = true;
                        if (!(this.f6299k || this.f6291c == null)) {
                            if (this.f6302n == null) {
                                m9730c();
                            }
                            if (!this.f6298j) {
                                this.f6302n.m10016a(gl10);
                                this.f6298j = true;
                            }
                            if (z) {
                                this.f6302n.m10015a();
                                length = this.f6292d.length;
                                this.f6287B = (int[][]) Array.newInstance(Integer.TYPE, new int[]{length, 3});
                                for (i = 0; i < length; i++) {
                                    panoramaLink = this.f6292d[i];
                                    if (panoramaLink.f6625e != null || panoramaLink.f6625e.length() <= 0) {
                                        this.f6287B[i][0] = -1;
                                    } else {
                                        String str = panoramaLink.f6625e + " (" + this.f6303o[panoramaLink.f6622b] + ")";
                                        for (int i2 = 0; i2 < 3; i2++) {
                                            this.f6287B[i][i2] = this.f6302n.m10014a(gl10, str, this.f6305q, this.f6306r);
                                        }
                                    }
                                }
                                this.f6302n.m10020c(gl10);
                            }
                            this.f6299k = true;
                        }
                        this.f6307s.m9717a(this.f6293e, this.f6294f);
                        if (!this.f6290E && this.f6314z) {
                            m9727a(gl10, ayVar);
                        }
                        if (this.f6286A) {
                            m9729b(gl10, ayVar);
                        }
                        synchronized (this.f6309u) {
                            overlay = this.f6308t;
                            this.f6308t = this.f6307s;
                            this.f6307s = overlay;
                        }
                    }
                } catch (OutOfMemoryError e) {
                    m9728b(gl10);
                    m9730c();
                    this.f6289D = true;
                    return;
                }
            }
            z = false;
            if (this.f6302n == null) {
                m9730c();
            }
            if (this.f6298j) {
                this.f6302n.m10016a(gl10);
                this.f6298j = true;
            }
            if (z) {
                this.f6302n.m10015a();
                length = this.f6292d.length;
                this.f6287B = (int[][]) Array.newInstance(Integer.TYPE, new int[]{length, 3});
                while (i < length) {
                    panoramaLink = this.f6292d[i];
                    if (panoramaLink.f6625e != null) {
                    }
                    this.f6287B[i][0] = -1;
                }
                this.f6302n.m10020c(gl10);
            }
            this.f6299k = true;
            this.f6307s.m9717a(this.f6293e, this.f6294f);
            m9727a(gl10, ayVar);
            if (this.f6286A) {
                m9729b(gl10, ayVar);
            }
            synchronized (this.f6309u) {
                overlay = this.f6308t;
                this.f6308t = this.f6307s;
                this.f6307s = overlay;
            }
        }
    }

    protected final int getVirtualViewAt(float f, float f2) {
        int b = this.f6308t.m9721b((int) f, this.f6294f - ((int) f2));
        if (b == -1) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        return b;
    }

    protected final void getVisibleVirtualViews(List<Integer> list) {
        int[] a = this.f6308t.m9719a();
        for (int i = 0; i < this.f6308t.m9720b(); i++) {
            list.add(Integer.valueOf(a[i]));
        }
    }

    protected final boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
        return true;
    }

    protected final void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent) {
        accessibilityEvent.setContentDescription(this.f6304p[this.f6308t.m9716a(i)]);
    }

    protected final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int[] c = this.f6308t.m9722c();
        int i2 = i * 4;
        accessibilityNodeInfoCompat.setBoundsInParent(new Rect(c[i2], this.f6294f - c[i2 + 3], c[i2 + 2], this.f6294f - c[i2 + 1]));
        accessibilityNodeInfoCompat.setFocusable(true);
        accessibilityNodeInfoCompat.setContentDescription(this.f6304p[this.f6308t.m9716a(i)]);
    }

    private void m9727a(GL10 gl10, ay ayVar) {
        if (this.f6287B != null) {
            this.f6302n.m10017a(gl10, (float) this.f6293e, (float) this.f6294f);
            gl10.glMatrixMode(5888);
            gl10.glPushMatrix();
            gl10.glLoadIdentity();
            gl10.glMultMatrixf(ayVar.m8749a(), 0);
            for (int i = 0; i < this.f6292d.length; i++) {
                if (this.f6287B[i][0] != -1) {
                    gl10.glPushMatrix();
                    float o = ar.m8614o(180.0f - this.f6291c.f6604t);
                    gl10.glRotatef(-this.f6291c.f6605u, FloatMath.cos(o), 0.0f, -FloatMath.sin(o));
                    gl10.glRotatef(-this.f6292d[i].f6621a, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f);
                    gl10.glTranslatef(0.0f, 0.0f, -5.0f);
                    gl10.glTranslatef(0.0f, -5.0f, 0.0f);
                    gl10.glRotatef(-90.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f);
                    gl10.glRotatef(90.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT);
                    gl10.glScalef(0.025f, 0.025f, 0.025f);
                    gl10.glTranslatef(0.0f, (-60.0f * this.f6288C) / dm.DEFAULT_BACKOFF_MULT, 0.0f);
                    this.f6302n.m10018a(gl10, this.f6287B[i][0]);
                    gl10.glPopMatrix();
                }
            }
            gl10.glMatrixMode(5888);
            gl10.glPopMatrix();
            this.f6302n.m10021d(gl10);
        }
    }

    private void m9729b(GL10 gl10, ay ayVar) {
        if (this.f6295g != 0.0f) {
            gl10.glMatrixMode(5889);
            gl10.glLoadIdentity();
            GLU.gluPerspective(gl10, ayVar.m8756f() * ag.m9796a(this.f6295g), this.f6295g, 0.1f, 100.0f);
            gl10.glMatrixMode(5888);
            gl10.glLoadIdentity();
            gl10.glTranslatef(0.0f, 0.0f, -2.0f);
            gl10.glMultMatrixf(ayVar.m8749a(), 0);
            gl10.glTranslatef(0.0f, -1.0f, 0.0f);
            gl10.glShadeModel(7424);
            gl10.glEnable(3042);
            gl10.glBlendFunc(770, 771);
            this.f6300l.m9781a(gl10);
            gl10.glMatrixMode(5888);
            int i = 0;
            while (i < this.f6292d.length) {
                Object obj;
                float b = ayVar.m8750b();
                PanoramaLink panoramaLink = this.f6292d[i];
                gl10.glPushMatrix();
                b -= panoramaLink.f6621a;
                float f = 180.0f - panoramaLink.f6621a;
                float o = ar.m8614o(180.0f - this.f6291c.f6604t);
                gl10.glRotatef(-this.f6291c.f6605u, FloatMath.cos(o), 0.0f, -FloatMath.sin(o));
                gl10.glRotatef(f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f);
                int i2 = panoramaLink.f6624d;
                gl10.glColor4x(((i2 >> 16) & MotionEventCompat.ACTION_MASK) << 8, ((i2 >> 8) & MotionEventCompat.ACTION_MASK) << 8, ((i2 >> 0) & MotionEventCompat.ACTION_MASK) << 8, Math.min((int) (((float) (((i2 >> 24) & MotionEventCompat.ACTION_MASK) << 8)) * 1.1f), AccessibilityNodeInfoCompat.ACTION_CUT));
                b = FloatMath.cos(ar.m8614o(b));
                gl10.glTranslatef(0.0f, 0.0f, (((((b * 0.355f) + 0.68f) - ((((dm.DEFAULT_BACKOFF_MULT * b) * b) - br.DEFAULT_BACKOFF_MULT) * 0.32f)) - (0.215f * ((((4.0f * b) * b) - 3.0f) * b))) * 0.25f) + 0.2f);
                Object obj2 = this.f6311w == i ? 1 : null;
                Object obj3 = this.f6310v == i ? 1 : null;
                if (obj3 != null) {
                    obj = null;
                } else {
                    obj = obj2;
                }
                Object obj4 = (obj == null && obj3 == null) ? null : 1;
                if (obj4 != null) {
                    if (obj != null) {
                        gl10.glColor4x(34816, 27904, 44288, AccessibilityNodeInfoCompat.ACTION_CUT);
                    } else {
                        gl10.glColor4x(62976, 35328, 7936, AccessibilityNodeInfoCompat.ACTION_CUT);
                    }
                    gl10.glPushMatrix();
                    gl10.glScalef(1.3f, br.DEFAULT_BACKOFF_MULT, 1.3f);
                } else {
                    gl10.glColor4x(0, 0, 0, AccessibilityNodeInfoCompat.ACTION_PASTE);
                }
                this.f6300l.m9784b(gl10);
                if (!this.f6290E || this.f6295g >= br.DEFAULT_BACKOFF_MULT) {
                    gl10.glTranslatef(0.0f, 0.5f, 0.0f);
                }
                gl10.glColor4x(0, 0, 0, 20480);
                this.f6297i.m9725a(gl10, 6);
                gl10.glColor4x(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT);
                this.f6296h.m9725a(gl10, 6);
                this.f6307s.m9718a(this.f6300l, gl10, this.f6301m, this.f6296h, 0, 64, i, panoramaLink.f6622b);
                if (obj4 != null) {
                    gl10.glPopMatrix();
                }
                gl10.glPopMatrix();
                i++;
            }
            gl10.glDisable(3042);
        }
    }

    public final long m9732a() {
        return this.f6312x;
    }

    public final int m9731a(int i, int i2) {
        int b;
        synchronized (this.f6309u) {
            b = this.f6308t.m9721b(i, i2);
        }
        return b;
    }

    public final void m9739b(int i) {
        this.f6310v = i;
    }

    public final void m9738a(boolean z) {
        this.f6314z = z;
    }

    public final boolean m9741b() {
        return this.f6314z;
    }

    public final void m9740b(boolean z) {
        this.f6286A = z;
    }
}
