package com.google.android.m4b.maps.ba;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.FloatMath;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.aq.VectorTile;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.an.bm;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.GeometryUtil;
import com.google.android.m4b.maps.ay.Texture;
import com.google.android.m4b.maps.az.IndexBuffer;
import com.google.android.m4b.maps.az.IndexVBO;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.az.TexCoordVBO;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.google.android.m4b.maps.az.VertexVBO;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.android.m4b.maps.p058v.Util;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.google.p025a.p028c.bk;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ba.j */
public final class GLLineGroup extends GLFeature {
    private static float f5189b;
    private static final Map<GLState, Map<GLLineGroup, Texture>> f5190o;
    private final ac f5191c;
    private final List<GLLineGroup> f5192d;
    private final VertexBuffer f5193e;
    private final IndexBuffer f5194f;
    private final TexCoordBuffer f5195g;
    private Texture f5196h;
    private final int f5197i;
    private final int f5198j;
    private final boolean f5199k;
    private final float f5200l;
    private FeatureId f5201m;
    private final GLLineGroup f5202n;

    /* renamed from: com.google.android.m4b.maps.ba.j.a */
    public static class GLLineGroup {
        private ArrayList<GLLineGroup> f5168a;
        private final ac f5169b;
        private final String[] f5170c;

        public GLLineGroup(ac acVar, String[] strArr) {
            this.f5168a = new ArrayList();
            this.f5169b = acVar;
            this.f5170c = strArr;
        }

        public final void m7986a(VectorTile vectorTile) {
            GLLineGroup gLLineGroup = null;
            while (vectorTile.hasNext()) {
                bc a = vectorTile.m5613a();
                if (a instanceof bm) {
                    GLLineGroup gLLineGroup2;
                    bm bmVar = (bm) a;
                    if (gLLineGroup == null || !gLLineGroup.f5173a.equals(bmVar.m5851a())) {
                        gLLineGroup = new GLLineGroup(bmVar, this.f5170c);
                        this.f5168a.add(gLLineGroup);
                        gLLineGroup2 = gLLineGroup;
                    } else {
                        gLLineGroup.m7992a(bmVar);
                        gLLineGroup2 = gLLineGroup;
                    }
                    vectorTile.next();
                    gLLineGroup = gLLineGroup2;
                } else {
                    return;
                }
            }
        }

        public final void m7987a(bc bcVar) {
            this.f5168a.add(new GLLineGroup((bm) bcVar, this.f5170c));
        }

        public final GLLineGroup[] m7988a(int i) {
            ArrayList arrayList = new ArrayList();
            Iterator it = this.f5168a.iterator();
            ArrayList a = ar.m2515a();
            GLLineGroup gLLineGroup = new GLLineGroup();
            HashSet a2 = bk.m2870a();
            List a3 = ar.m2515a();
            GLLineGroup gLLineGroup2 = new GLLineGroup();
            Set a4 = bk.m2870a();
            HashSet hashSet = a2;
            List list = a;
            Object obj = null;
            while (it.hasNext()) {
                Object obj2 = (GLLineGroup) it.next();
                if (obj2.m7994c() != null) {
                    GLLineGroup gLLineGroup3 = new GLLineGroup();
                    GLLineGroup.m8006a(obj2.m7991a(), gLLineGroup3);
                    arrayList.add(new GLLineGroup(gLLineGroup3, com.google.p025a.p028c.ac.m2339a(obj2), obj2.m7993b(), i, (byte) 0));
                } else if (obj2.m7996e() > 1) {
                    GLLineGroup gLLineGroup4 = new GLLineGroup(obj2);
                    r1 = (obj == null || gLLineGroup4.equals(obj)) ? null : 1;
                    if (!(GLLineGroup.m8006a(obj2.m7991a(), gLLineGroup2) && r1 == null)) {
                        arrayList.add(new GLLineGroup(gLLineGroup2, a3, a4, i, (byte) 0));
                        a3 = ar.m2515a();
                        gLLineGroup2.m7989a();
                        GLLineGroup.m8006a(obj2.m7991a(), gLLineGroup2);
                        a4 = bk.m2870a();
                    }
                    a3.add(obj2);
                    a4.addAll(obj2.m7993b());
                    obj = gLLineGroup4;
                } else {
                    List list2;
                    Set a5;
                    if (GLLineGroup.m8006a(obj2.m7991a(), gLLineGroup)) {
                        r1 = hashSet;
                        list2 = list;
                    } else {
                        arrayList.add(new GLLineGroup(gLLineGroup, list, hashSet, i, (byte) 0));
                        list2 = ar.m2515a();
                        gLLineGroup.m7989a();
                        GLLineGroup.m8006a(obj2.m7991a(), gLLineGroup);
                        a5 = bk.m2870a();
                    }
                    list2.add(obj2);
                    a5.addAll(obj2.m7993b());
                    hashSet = a5;
                    list = list2;
                }
            }
            if (!list.isEmpty()) {
                arrayList.add(new GLLineGroup(gLLineGroup, list, hashSet, i, (byte) 0));
            }
            if (!a3.isEmpty()) {
                arrayList.add(new GLLineGroup(gLLineGroup2, a3, a4, i, (byte) 0));
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return (GLLineGroup[]) arrayList.toArray(new GLLineGroup[arrayList.size()]);
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.j.b */
    static class GLLineGroup {
        int f5171a;
        int f5172b;

        GLLineGroup() {
        }

        public final void m7989a() {
            this.f5172b = 0;
            this.f5171a = 0;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.j.c */
    public static class GLLineGroup {
        private final Polyline f5173a;
        private final ArrayList<GLLineGroup> f5174b;
        private final ArrayList<GLLineGroup> f5175c;
        private final HashSet<String> f5176d;
        private final String[] f5177e;
        private FeatureId f5178f;

        public GLLineGroup(bm bmVar, String[] strArr) {
            this.f5174b = new ArrayList(2);
            this.f5175c = new ArrayList(2);
            this.f5176d = new HashSet();
            this.f5173a = bmVar.m5851a();
            this.f5177e = strArr;
            m7992a(bmVar);
        }

        private GLLineGroup() {
            this.f5174b = new ArrayList(2);
            this.f5175c = new ArrayList(2);
            this.f5176d = new HashSet();
            this.f5173a = null;
            this.f5177e = null;
        }

        public final void m7992a(bm bmVar) {
            for (int i : bmVar.m5859k()) {
                if (i >= 0 && i < this.f5177e.length) {
                    this.f5176d.add(this.f5177e[i]);
                }
            }
            Style e = bmVar.m5855e();
            float g = bmVar.m5857g();
            if (e.m6094b() == 1) {
                this.f5175c.add(new GLLineGroup(g, e, 0));
            } else if (e.m6094b() > 1) {
                this.f5174b.add(new GLLineGroup(g, e, 0));
                this.f5175c.add(new GLLineGroup(g, e, 1));
            }
            if (bmVar.m5858h()) {
                this.f5178f = bmVar.m5854d();
            }
        }

        public final Polyline m7991a() {
            return this.f5173a;
        }

        public final Set<String> m7993b() {
            return this.f5176d;
        }

        public final FeatureId m7994c() {
            return this.f5178f;
        }

        public final float m7995d() {
            Iterator it = this.f5174b.iterator();
            float f = 0.0f;
            while (it.hasNext()) {
                GLLineGroup gLLineGroup = (GLLineGroup) it.next();
                float abs = (gLLineGroup.f5180b / dm.DEFAULT_BACKOFF_MULT) + Math.abs(gLLineGroup.f5179a);
                if (abs <= f) {
                    abs = f;
                }
                f = abs;
            }
            it = this.f5175c.iterator();
            while (it.hasNext()) {
                gLLineGroup = (GLLineGroup) it.next();
                abs = (gLLineGroup.f5180b / dm.DEFAULT_BACKOFF_MULT) + Math.abs(gLLineGroup.f5179a);
                if (abs > f) {
                    f = abs;
                }
            }
            return f * dm.DEFAULT_BACKOFF_MULT;
        }

        public final int m7996e() {
            int i = 1;
            int size = this.f5174b.size() + this.f5175c.size();
            if (size != 0) {
                int i2;
                int i3;
                int i4;
                int[] iArr = new int[size];
                for (int i5 = 0; i5 < size; i5++) {
                    GLLineGroup gLLineGroup;
                    if (i5 < this.f5174b.size()) {
                        gLLineGroup = (GLLineGroup) this.f5174b.get(i5);
                    } else {
                        gLLineGroup = (GLLineGroup) this.f5175c.get(i5 - this.f5174b.size());
                    }
                    if (gLLineGroup.f5182d == null) {
                        i2 = 1;
                    } else {
                        int[] iArr2 = gLLineGroup.f5182d;
                        i3 = 0;
                        i4 = 0;
                        while (i3 < iArr2.length) {
                            int i6 = iArr2[i3] + i4;
                            i3++;
                            i4 = i6;
                        }
                        if (gLLineGroup.f5182d.length % 2 == 1) {
                            i2 = i4 * 2;
                        } else {
                            i2 = i4;
                        }
                    }
                    iArr[i5] = i2;
                }
                i4 = 1;
                i = iArr[0];
                while (i4 < size) {
                    i2 = iArr[i4];
                    i3 = i * i2;
                    if (i <= 0 || i2 <= 0) {
                        throw new IllegalArgumentException("Greatest common divisor should be computed on numbers greater than zero.");
                    }
                    while (i2 != 0) {
                        int i7 = i % i2;
                        i = i2;
                        i2 = i7;
                    }
                    i4++;
                    i = i3 / i;
                }
            }
            return i;
        }

        public final ArrayList<GLLineGroup> m7997f() {
            return this.f5174b;
        }

        public final ArrayList<GLLineGroup> m7998g() {
            return this.f5175c;
        }

        public final int m7999h() {
            int i = 208;
            if (this.f5173a != null) {
                i = this.f5173a.m6032i() + 208;
            }
            return i + ((this.f5174b.size() + this.f5175c.size()) * 24);
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.j.d */
    static class GLLineGroup {
        final float f5179a;
        final float f5180b;
        final int f5181c;
        final int[] f5182d;
        private final int f5183e;

        public GLLineGroup(float f, Style style, int i) {
            int[] iArr = null;
            this.f5179a = f;
            this.f5180b = style.m6095b(i).m6084c();
            this.f5181c = style.m6095b(i).m6083b();
            if (style.m6097d()) {
                if (style.m6094b() > i) {
                    int[] d = style.m6095b(i).m6085d();
                    if (d.length != 0) {
                        iArr = d;
                    }
                    this.f5182d = iArr;
                    this.f5183e = (this.f5182d == null ? Arrays.hashCode(this.f5182d) : 0) + (((((Float.floatToIntBits(this.f5179a) * 31) + Float.floatToIntBits(this.f5180b)) * 31) + this.f5181c) * 31);
                }
                Util.m11550a("GLLineGroup", "Invalid stroke index : " + i + "  available strokes : " + style.m6094b());
            }
            this.f5182d = null;
            if (this.f5182d == null) {
            }
            this.f5183e = (this.f5182d == null ? Arrays.hashCode(this.f5182d) : 0) + (((((Float.floatToIntBits(this.f5179a) * 31) + Float.floatToIntBits(this.f5180b)) * 31) + this.f5181c) * 31);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            GLLineGroup gLLineGroup = (GLLineGroup) obj;
            if (this.f5181c != gLLineGroup.f5181c) {
                return false;
            }
            if (Float.compare(gLLineGroup.f5179a, this.f5179a) != 0) {
                return false;
            }
            if (Float.compare(gLLineGroup.f5180b, this.f5180b) != 0) {
                return false;
            }
            if (Arrays.equals(this.f5182d, gLLineGroup.f5182d)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return this.f5183e;
        }

        public final String toString() {
            return "c:" + this.f5181c + " w:" + this.f5180b + " s:" + this.f5179a + " d:" + Arrays.toString(this.f5182d);
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.j.e */
    static class GLLineGroup {
        private final List<GLLineGroup> f5184a;
        private final List<GLLineGroup> f5185b;
        private final int f5186c;
        private final float f5187d;
        private final int f5188e;

        public GLLineGroup(List<GLLineGroup> list, float f, int i, boolean z) {
            this.f5184a = ar.m2515a();
            this.f5185b = ar.m2515a();
            this.f5187d = f;
            this.f5186c = i;
            GLLineGroup gLLineGroup;
            if (z) {
                gLLineGroup = (GLLineGroup) list.get(0);
                this.f5185b.addAll(gLLineGroup.m7997f());
                this.f5184a.addAll(gLLineGroup.m7998g());
            } else {
                for (GLLineGroup gLLineGroup2 : list) {
                    this.f5185b.addAll(gLLineGroup2.m7997f());
                    this.f5184a.addAll(gLLineGroup2.m7998g());
                }
            }
            this.f5188e = m8000a();
        }

        public GLLineGroup(GLLineGroup gLLineGroup) {
            this.f5184a = ar.m2515a();
            this.f5185b = ar.m2515a();
            this.f5187d = 0.0f;
            this.f5186c = 0;
            this.f5185b.addAll(gLLineGroup.m7997f());
            this.f5184a.addAll(gLLineGroup.m7998g());
            this.f5188e = m8000a();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            GLLineGroup gLLineGroup = (GLLineGroup) obj;
            if (Float.compare(gLLineGroup.f5187d, this.f5187d) != 0) {
                return false;
            }
            if (this.f5186c != gLLineGroup.f5186c) {
                return false;
            }
            if (!this.f5184a.equals(gLLineGroup.f5184a)) {
                return false;
            }
            if (this.f5185b.equals(gLLineGroup.f5185b)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return this.f5188e;
        }

        private int m8000a() {
            return (((((this.f5184a.hashCode() * 31) + this.f5185b.hashCode()) * 31) + this.f5186c) * 31) + Float.floatToIntBits(this.f5187d);
        }
    }

    static {
        f5189b = br.DEFAULT_BACKOFF_MULT;
        f5190o = new WeakHashMap();
    }

    private static synchronized Texture m8001a(GLState gLState, GLLineGroup gLLineGroup) {
        Texture texture;
        synchronized (GLLineGroup.class) {
            Map map = (Map) f5190o.get(gLState);
            if (map == null) {
                texture = null;
            } else {
                texture = (Texture) map.get(gLLineGroup);
            }
        }
        return texture;
    }

    private static synchronized void m8004a(GLState gLState, GLLineGroup gLLineGroup, Texture texture) {
        synchronized (GLLineGroup.class) {
            Map map = (Map) f5190o.get(gLState);
            if (map == null) {
                map = au.m2807a();
                f5190o.put(gLState, map);
            }
            map.put(gLLineGroup, texture);
        }
    }

    private static synchronized void m8007b(GLState gLState, GLLineGroup gLLineGroup) {
        synchronized (GLLineGroup.class) {
            Map map = (Map) f5190o.get(gLState);
            if (map != null) {
                map.remove(gLLineGroup);
            }
        }
    }

    private GLLineGroup(ac acVar, GLLineGroup gLLineGroup, List<GLLineGroup> list, Set<String> set, int i) {
        int i2;
        super(set);
        this.f5191c = acVar;
        this.f5193e = new VertexVBO(gLLineGroup.f5171a, true);
        this.f5194f = new IndexVBO(gLLineGroup.f5172b, true);
        this.f5195g = new TexCoordVBO(gLLineGroup.f5171a, true);
        this.f5192d = list;
        int i3 = 0;
        float f = br.DEFAULT_BACKOFF_MULT;
        int e = ((GLLineGroup) this.f5192d.get(0)).m7996e();
        this.f5199k = e > 1;
        if (this.f5199k) {
            i3 = ((int) Math.ceil((double) (e / 16))) * 2;
            f = Math.max(br.DEFAULT_BACKOFF_MULT, ((GLLineGroup) this.f5192d.get(0)).m7995d());
            i3 += 0;
            i2 = 0;
        } else {
            for (GLLineGroup gLLineGroup2 : this.f5192d) {
                i3 += gLLineGroup2.m7996e();
                f = Math.max(f, gLLineGroup2.m7995d());
            }
            i2 = 0;
        }
        while ((1 << i2) < i3) {
            i2++;
        }
        this.f5197i = i2;
        float ceil = FloatMath.ceil(f5189b * f) + dm.DEFAULT_BACKOFF_MULT;
        this.f5198j = Texture.m7603a((int) ceil, 8);
        this.f5200l = ((float) this.f5198j) / ceil;
        this.f5202n = new GLLineGroup(this.f5192d, this.f5200l, this.f5197i, this.f5199k);
        float f2 = ((ceil * ((float) acVar.m5446i().m6053f())) / 256.0f) * 0.5f;
        GeometryUtil a = GeometryUtil.m7546a();
        for (int i4 = 0; i4 < list.size(); i4++) {
            Rectangle2D i5 = acVar.m5446i();
            GLLineGroup gLLineGroup3 = (GLLineGroup) list.get(i4);
            Polyline a2 = gLLineGroup3.m7991a();
            int b = a2.m6020b();
            if (b >= 2) {
                this.f5201m = gLLineGroup3.m7994c();
                Point c = i5.m6050c();
                int f3 = i5.m6053f();
                if (this.f5199k) {
                    a.m7553a(a2, f2, true, c, f3, 2048.0f / ((float) gLLineGroup3.m7996e()), this.f5193e, this.f5194f, this.f5195g);
                } else {
                    a.m7553a(a2, f2, true, c, f3, (float) br.DEFAULT_BACKOFF_MULT, this.f5193e, this.f5194f, null);
                    GeometryUtil.m7547a(b, true, this.f5197i, new int[]{i4}, this.f5195g);
                }
            }
        }
        ac acVar2 = this.f5191c;
    }

    public static void m8003a(GLState gLState) {
        GL10 x = gLState.m7541x();
        gLState.m7533p();
        x.glBlendFunc(1, 771);
        x.glTexEnvx(8960, 8704, 8448);
        gLState.m7541x().glColor4x(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT);
    }

    public final void m8010a(GLState gLState, Camera camera, ad adVar) {
        if (!adVar.m6705c().m7212c()) {
            return;
        }
        if (this.f5201m == null || adVar.m6705c().m7210b(this.f5201m) != null) {
            int max = Math.max(0, Math.round((camera.m7444l() - ((float) this.f5191c.m5439b())) / 0.5f));
            if (this.f5196h == null) {
                this.f5196h = GLLineGroup.m8001a(gLState, this.f5202n);
                if (this.f5196h == null) {
                    Bitmap createBitmap = Bitmap.createBitmap(this.f5198j, 1 << this.f5197i, Config.ARGB_4444);
                    createBitmap.eraseColor(0);
                    List list = this.f5192d;
                    float f = this.f5200l;
                    boolean z = this.f5199k;
                    Canvas canvas = new Canvas(createBitmap);
                    Paint paint = new Paint();
                    paint.setStrokeWidth(br.DEFAULT_BACKOFF_MULT);
                    paint.setAntiAlias(true);
                    float width = ((float) createBitmap.getWidth()) * 0.5f;
                    int size = z ? 1 : list.size();
                    for (int i = 0; i < size; i++) {
                        GLLineGroup gLLineGroup = (GLLineGroup) list.get(i);
                        int e = gLLineGroup.m7996e();
                        float f2 = (float) i;
                        GLLineGroup.m8005a(gLLineGroup.m7997f(), canvas, paint, width, f2, f, e);
                        GLLineGroup.m8005a(gLLineGroup.m7998g(), canvas, paint, width, f2, f, e);
                    }
                    this.f5196h = new Texture(gLState);
                    GLLineGroup.m8004a(gLState, this.f5202n, this.f5196h);
                    this.f5196h.m7621c(true);
                    this.f5196h.m7618b(true);
                    this.f5196h.m7617b(createBitmap);
                    createBitmap.recycle();
                } else {
                    this.f5196h.m7625e();
                }
            }
            if (max != 0) {
                gLState.m7541x().glMatrixMode(5890);
                gLState.m7541x().glLoadIdentity();
                gLState.m7541x().glTranslatef(0.5f, 0.5f, 0.0f);
                gLState.m7541x().glScalef(br.DEFAULT_BACKOFF_MULT + ((float) max), this.f5199k ? ((float) max) + br.DEFAULT_BACKOFF_MULT : br.DEFAULT_BACKOFF_MULT, 0.0f);
                gLState.m7541x().glTranslatef(-0.5f, -0.5f, 0.0f);
                gLState.m7541x().glMatrixMode(5888);
            }
            this.f5196h.m7613a(gLState.m7541x());
            this.f5193e.m7736d(gLState);
            this.f5195g.m7706d(gLState);
            this.f5194f.m7665a(gLState, 4);
            if (max != 0) {
                gLState.m7541x().glMatrixMode(5890);
                gLState.m7541x().glLoadIdentity();
                gLState.m7541x().glMatrixMode(5888);
            }
        }
    }

    private void m8008d(GLState gLState) {
        if (this.f5196h != null) {
            this.f5196h.m7626f();
            if (this.f5196h.m7627g() == 0) {
                GLLineGroup.m8007b(gLState, this.f5202n);
            }
            this.f5196h = null;
        }
    }

    public final void m8012b(GLState gLState) {
        m8008d(gLState);
        this.f5193e.m7732b(gLState);
        this.f5194f.m7671b(gLState);
        this.f5195g.m7702b(gLState);
    }

    public final void m8013c(GLState gLState) {
        m8008d(gLState);
        this.f5193e.m7734c(gLState);
        this.f5194f.m7673c(gLState);
        this.f5195g.m7705c(gLState);
    }

    private static void m8005a(ArrayList<GLLineGroup> arrayList, Canvas canvas, Paint paint, float f, float f2, float f3, int i) {
        float height = (((float) canvas.getHeight()) / ((float) i)) * 16.0f;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            GLLineGroup gLLineGroup = (GLLineGroup) arrayList.get(i2);
            float f4 = f + (gLLineGroup.f5179a * f3);
            float f5 = (gLLineGroup.f5180b * f3) * 0.5f;
            paint.setColor(gLLineGroup.f5181c);
            int[] iArr = gLLineGroup.f5182d;
            if (iArr != null) {
                int i3 = 0;
                Object obj = 1;
                float f6 = f2;
                while (i3 < i) {
                    int i4 = 0;
                    Object obj2 = obj;
                    int i5 = i3;
                    float f7 = f6;
                    while (i4 <= iArr.length % 2) {
                        int length = iArr.length;
                        int i6 = 0;
                        Object obj3 = obj2;
                        int i7 = i5;
                        while (i6 < length) {
                            int i8 = iArr[i6];
                            float f8 = f7 + ((((float) i8) / 16.0f) * height);
                            if (obj3 != null) {
                                canvas.drawRect(f4 - f5, f7, f4 + f5, f8, paint);
                            }
                            i7 += i8;
                            i6++;
                            obj3 = obj3 == null ? 1 : null;
                            f7 = f8;
                        }
                        i4++;
                        i5 = i7;
                        obj2 = obj3;
                    }
                    f6 = f7;
                    i3 = i5;
                    obj = obj2;
                }
            } else if (i == 1) {
                canvas.drawLine(f4 - f5, f2 + 0.5f, f4 + f5, f2 + 0.5f, paint);
            } else {
                canvas.drawRect(f4 - f5, 0.0f, f4 + f5, (float) canvas.getHeight(), paint);
            }
        }
    }

    static boolean m8006a(Polyline polyline, GLLineGroup gLLineGroup) {
        int i = 0;
        int b = polyline.m6020b() - 1;
        if (b <= 0) {
            return true;
        }
        int i2 = b * 5;
        if (gLLineGroup.f5171a > 0 && gLLineGroup.f5171a + i2 > AccessibilityNodeInfoCompat.ACTION_COPY) {
            return false;
        }
        gLLineGroup.f5171a = i2 + gLLineGroup.f5171a;
        i2 = gLLineGroup.f5172b;
        b = ((b * 3) - 1) * 3;
        if (polyline.m6028e()) {
            i = 3;
        }
        gLLineGroup.f5172b = (i + b) + i2;
        return true;
    }

    public static void m8002a(float f) {
        f5189b = f;
    }

    public final int m8009a() {
        int c = (this.f5193e.m7733c() + this.f5194f.m7672c()) + this.f5195g.m7701b();
        if (this.f5196h != null) {
            return c + this.f5196h.m7628h();
        }
        return c;
    }

    public final int m8011b() {
        int i = 608;
        for (GLLineGroup h : this.f5192d) {
            i = h.m7999h() + i;
        }
        return ((this.f5193e.m7735d() + this.f5194f.m7674d()) + this.f5195g.m7703c()) + i;
    }
}
