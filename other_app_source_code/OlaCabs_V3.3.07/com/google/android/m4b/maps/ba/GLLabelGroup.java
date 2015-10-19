package com.google.android.m4b.maps.ba;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import com.google.android.m4b.maps.an.Road;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.an.bk;
import com.google.android.m4b.maps.an.bk.LabelGroup;
import com.google.android.m4b.maps.an.bm;
import com.google.android.m4b.maps.at.Resource;
import com.google.android.m4b.maps.at.ResourceManager;
import com.google.android.m4b.maps.au.ParameterManager;
import com.google.android.m4b.maps.av.TextGenerator;
import com.google.android.m4b.maps.av.ac;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.GmmVertexDataBuilder;
import com.google.android.m4b.maps.ay.Texture;
import com.google.android.m4b.maps.az.InterleavedVBO;
import com.google.android.m4b.maps.az.TexCoordBufferInterface;
import com.google.android.m4b.maps.az.VertexBufferInterface;
import com.google.android.m4b.maps.bc.LabelTheme;
import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.Iterator;
import javax.microedition.khronos.opengles.GL10;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

/* renamed from: com.google.android.m4b.maps.ba.i */
public final class GLLabelGroup implements GLDrawable {
    private GLLabelGroup f5154a;
    private final GLLabelGroup f5155b;
    private final ArrayList<ArrayList<GLLabelGroup>> f5156c;
    private final ArrayList<Texture> f5157d;
    private final VertexBufferInterface f5158e;
    private final TexCoordBufferInterface f5159f;
    private final GmmVertexDataBuilder f5160g;
    private final InterleavedVBO f5161h;
    private float f5162i;
    private float f5163j;
    private float f5164k;
    private float f5165l;
    private boolean f5166m;
    private boolean f5167n;

    /* renamed from: com.google.android.m4b.maps.ba.i.1 */
    static /* synthetic */ class GLLabelGroup {
        static final /* synthetic */ int[] f5130a;

        static {
            f5130a = new int[GLLabelGroup.values().length];
            try {
                f5130a[GLLabelGroup.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5130a[GLLabelGroup.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f5130a[GLLabelGroup.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.i.e */
    interface GLLabelGroup {
        float m7942a();

        Texture m7943a(ac acVar);

        Texture m7944a(GLState gLState, ac acVar);

        float m7945b();

        float m7946c();

        float m7947d();

        float m7948e();

        void m7949f();
    }

    /* renamed from: com.google.android.m4b.maps.ba.i.a */
    public static class GLLabelGroup implements GLLabelGroup {
        private final Bitmap f5131a;
        private final Texture.Texture<Bitmap> f5132b;
        private final int f5133c;
        private final int f5134d;

        GLLabelGroup(Bitmap bitmap, float f, Texture.Texture<Bitmap> texture) {
            this.f5131a = bitmap;
            this.f5132b = texture;
            this.f5133c = (int) (((float) this.f5131a.getWidth()) * f);
            this.f5134d = (int) (((float) this.f5131a.getHeight()) * f);
        }

        public final float m7950a() {
            return (float) this.f5133c;
        }

        public final float m7953b() {
            return (float) this.f5134d;
        }

        public final float m7954c() {
            return 0.0f;
        }

        public final float m7955d() {
            return 0.0f;
        }

        public final float m7956e() {
            return (float) this.f5134d;
        }

        public final Texture m7952a(GLState gLState, ac acVar) {
            boolean z = true;
            Texture texture = (Texture) this.f5132b.m6235b((Object) this.f5131a);
            if (texture == null) {
                texture = new Texture(gLState, false);
                texture.m7621c(true);
                texture.m7624d(false);
                if (VERSION.SDK_INT < 9) {
                    z = false;
                }
                if (!z || DeviceSpecificInfo.f8008c) {
                    texture.m7617b(this.f5131a);
                } else {
                    texture.m7611a(this.f5131a);
                }
                this.f5132b.m6239c(this.f5131a, texture);
            }
            texture.m7625e();
            return texture;
        }

        public final Texture m7951a(ac acVar) {
            Texture texture = (Texture) this.f5132b.m6235b((Object) this.f5131a);
            if (texture != null) {
                texture.m7625e();
            }
            return texture;
        }

        public final void m7957f() {
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.i.b */
    static class GLLabelGroup implements GLLabelGroup {
        private float f5135a;

        GLLabelGroup(float f) {
            this.f5135a = f;
        }

        public final float m7958a() {
            return this.f5135a;
        }

        public final float m7961b() {
            return 0.0f;
        }

        public final float m7962c() {
            return 0.0f;
        }

        public final float m7963d() {
            return 0.0f;
        }

        public final float m7964e() {
            return 0.0f;
        }

        public final Texture m7960a(GLState gLState, ac acVar) {
            return null;
        }

        public final Texture m7959a(ac acVar) {
            return null;
        }

        public final void m7965f() {
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.i.c */
    public enum GLLabelGroup {
        CENTER,
        LEFT,
        RIGHT;

        public static GLLabelGroup m7966a(int i) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    return CENTER;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    return LEFT;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    return RIGHT;
                default:
                    throw new IllegalArgumentException("Unknown justification");
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.i.d */
    public static class GLLabelGroup implements GLLabelGroup {
        private final TextGenerator f5140a;
        private final String f5141b;
        private final int f5142c;
        private final Style f5143d;
        private final TextGenerator.TextGenerator f5144e;
        private final float f5145f;
        private final float f5146g;
        private final float f5147h;
        private final float f5148i;
        private final int f5149j;

        GLLabelGroup(TextGenerator textGenerator, String str, int i, Style style, TextGenerator.TextGenerator textGenerator2) {
            this.f5140a = textGenerator;
            this.f5141b = str;
            this.f5142c = i;
            this.f5143d = style;
            float f = br.DEFAULT_BACKOFF_MULT;
            this.f5149j = style.m6099f() ? style.m6103j().m6119b() : 0;
            if (style.m6098e()) {
                f = style.m6102i().m6127g();
            }
            this.f5144e = textGenerator2;
            float[] a = textGenerator.m7268a(str, textGenerator2, this.f5143d != null ? this.f5143d.m6102i() : null, (float) i, true, f);
            if (textGenerator2 == TextGenerator.f4631b) {
                this.f5145f = a[0] * 0.8f;
            } else {
                this.f5145f = a[0];
            }
            this.f5146g = a[1];
            this.f5147h = a[2];
            this.f5148i = a[3];
        }

        public final float m7967a() {
            return this.f5145f;
        }

        public final float m7970b() {
            return this.f5146g;
        }

        public final float m7971c() {
            return this.f5147h;
        }

        public final float m7972d() {
            return this.f5148i;
        }

        public final float m7973e() {
            return (this.f5146g - this.f5147h) - this.f5148i;
        }

        public final Texture m7969a(GLState gLState, ac acVar) {
            int b = GLLabel.m7900b(this.f5143d, acVar);
            int a = GLLabel.m7898a(this.f5143d, acVar);
            if (this.f5149j != 0) {
                b = 0;
                if (acVar == ac.HYBRID || acVar == ac.NIGHT) {
                    a = GLLabel.m7899b(this.f5149j);
                }
            }
            return this.f5140a.m7263a(gLState, this.f5141b, this.f5144e, this.f5143d != null ? this.f5143d.m6102i() : null, (float) this.f5142c, a, b, this.f5149j);
        }

        public final Texture m7968a(ac acVar) {
            return this.f5140a.m7264a(this.f5141b, this.f5144e, this.f5143d != null ? this.f5143d.m6102i() : null, (float) this.f5142c, GLLabel.m7898a(this.f5143d, acVar), GLLabel.m7900b(this.f5143d, acVar), this.f5149j);
        }

        public final void m7974f() {
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.i.f */
    public enum GLLabelGroup {
        CENTER,
        TOP,
        BOTTOM;

        public static GLLabelGroup m7975a(int i) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    return CENTER;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    return TOP;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    return BOTTOM;
                default:
                    throw new IllegalArgumentException("Unknown alignment");
            }
        }
    }

    GLLabelGroup(ArrayList<ArrayList<GLLabelGroup>> arrayList, GLLabelGroup gLLabelGroup, GLLabelGroup gLLabelGroup2) {
        this.f5156c = arrayList;
        this.f5157d = new ArrayList(arrayList.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        this.f5160g = new GmmVertexDataBuilder(arrayList.size() * 4, 9, false);
        this.f5161h = new InterleavedVBO(this.f5160g.m7585d());
        this.f5158e = this.f5160g;
        this.f5159f = this.f5160g;
        this.f5154a = gLLabelGroup;
        this.f5155b = gLLabelGroup2;
        m7978d();
        this.f5166m = false;
        this.f5167n = true;
    }

    public static GLLabelGroup m7976a(bk bkVar, bc bcVar, Camera camera, Texture.Texture<Bitmap> texture, TextGenerator textGenerator, LabelTheme labelTheme) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(arrayList2);
        int i = 0;
        ArrayList arrayList3 = arrayList2;
        while (i < bkVar.m5841b()) {
            LabelGroup a = bkVar.m5839a(i);
            if (a.m5825a()) {
                Object obj;
                Style j = a.m5834j();
                String i2 = a.m5833i();
                if (i2 == null || i2.length() == 0 || textGenerator == null || j == null || !j.m6098e()) {
                    obj = null;
                } else {
                    obj = (j.m6102i().m6126f() <= 0 || (j.m6102i().m6124d() & ViewCompat.MEASURED_STATE_MASK) == 0) ? null : 1;
                }
                if (obj != null) {
                    TextGenerator.TextGenerator textGenerator2 = labelTheme.f5415d;
                    if (bcVar instanceof Road) {
                        textGenerator2 = labelTheme.f5412a;
                    } else if (bcVar instanceof bm) {
                        textGenerator2 = labelTheme.f5419h;
                    }
                    arrayList3.add(new GLLabelGroup(textGenerator, a.m5833i(), GLPointLabel.m7920a(j, labelTheme, camera.m7439g()), j, textGenerator2));
                }
                arrayList2 = arrayList3;
            } else if (a.m5826b()) {
                Resource a2 = ResourceManager.m6599c().m6600a(a.m5831g(), null, false);
                if (!a2.m6586b()) {
                    return null;
                }
                Bitmap c = a2.m6587c();
                float h = a.m5832h();
                ParameterManager.m6641a().m6625a();
                if (a.m5831g().contains("/road_shields/")) {
                    h *= labelTheme.f5424m;
                } else {
                    h *= labelTheme.f5425n;
                }
                arrayList3.add(new GLLabelGroup(c, h * camera.m7439g(), texture));
                arrayList2 = arrayList3;
            } else if (a.m5829e()) {
                arrayList3.add(new GLLabelGroup(a.m5835k()));
                arrayList2 = arrayList3;
            } else if (a.m5830f()) {
                arrayList3 = new ArrayList();
                arrayList.add(arrayList3);
                arrayList2 = arrayList3;
            } else {
                arrayList2 = arrayList3;
            }
            i++;
            arrayList3 = arrayList2;
        }
        return new GLLabelGroup(arrayList, GLLabelGroup.m7966a(bkVar.m5842c().m5419a()), GLLabelGroup.m7975a(bkVar.m5842c().m5420b()));
    }

    private void m7978d() {
        float f = 0.0f;
        this.f5162i = 0.0f;
        float f2 = 0.0f;
        for (int i = 0; i < this.f5156c.size(); i++) {
            Iterator it = ((ArrayList) this.f5156c.get(i)).iterator();
            float f3 = 0.0f;
            float f4 = 0.0f;
            while (it.hasNext()) {
                GLLabelGroup gLLabelGroup = (GLLabelGroup) it.next();
                f4 += gLLabelGroup.m7942a();
                f3 = Math.max(f3, gLLabelGroup.m7948e());
            }
            this.f5162i = Math.max(this.f5162i, f4);
            f2 += f3;
        }
        ArrayList arrayList = (ArrayList) this.f5156c.get(0);
        ArrayList arrayList2 = (ArrayList) this.f5156c.get(this.f5156c.size() - 1);
        this.f5164k = 0.0f;
        this.f5165l = 0.0f;
        Iterator it2;
        float f5;
        float f6;
        switch (GLLabelGroup.f5130a[this.f5155b.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    this.f5164k = Math.max(this.f5164k, ((GLLabelGroup) it2.next()).m7946c());
                }
                it2 = arrayList2.iterator();
                f5 = 0.0f;
                while (it2.hasNext()) {
                    gLLabelGroup = (GLLabelGroup) it2.next();
                    f3 = gLLabelGroup.m7948e();
                    f5 = Math.max(f5, f3);
                    f = Math.max(f, gLLabelGroup.m7947d() + f3);
                }
                if (f > f5) {
                    this.f5165l = f - f5;
                    break;
                }
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                Iterator it3 = arrayList.iterator();
                f6 = 0.0f;
                while (it3.hasNext()) {
                    gLLabelGroup = (GLLabelGroup) it3.next();
                    f4 = gLLabelGroup.m7948e();
                    f6 = Math.max(f6, f4);
                    f = Math.max(f, gLLabelGroup.m7946c() + f4);
                }
                if (f > f6) {
                    this.f5164k = f - f6;
                }
                Iterator it4 = arrayList2.iterator();
                while (it4.hasNext()) {
                    this.f5165l = Math.max(this.f5165l, ((GLLabelGroup) it4.next()).m7947d());
                }
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                Iterator it5 = arrayList.iterator();
                f6 = 0.0f;
                f3 = 0.0f;
                while (it5.hasNext()) {
                    gLLabelGroup = (GLLabelGroup) it5.next();
                    float e = gLLabelGroup.m7948e() / dm.DEFAULT_BACKOFF_MULT;
                    f3 = Math.max(f3, e);
                    f6 = Math.max(f6, gLLabelGroup.m7946c() + e);
                }
                if (f6 > f3) {
                    this.f5164k = f6 - f3;
                }
                it2 = arrayList2.iterator();
                f5 = 0.0f;
                while (it2.hasNext()) {
                    gLLabelGroup = (GLLabelGroup) it2.next();
                    f3 = gLLabelGroup.m7948e() / dm.DEFAULT_BACKOFF_MULT;
                    f5 = Math.max(f5, f3);
                    f = Math.max(f, gLLabelGroup.m7947d() + f3);
                }
                if (f > f5) {
                    this.f5165l = f - f5;
                    break;
                }
                break;
        }
        this.f5163j = (this.f5164k + f2) + this.f5165l;
    }

    public final void m7983b(GLState gLState) {
        Iterator it = this.f5157d.iterator();
        while (it.hasNext()) {
            ((Texture) it.next()).m7626f();
        }
        this.f5157d.clear();
        Iterator it2 = this.f5156c.iterator();
        while (it2.hasNext()) {
            ArrayList arrayList = (ArrayList) it2.next();
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                ((GLLabelGroup) it3.next()).m7949f();
            }
            arrayList.clear();
        }
        this.f5156c.clear();
        this.f5161h.m7687d(gLState);
    }

    public final void m7984c(GLState gLState) {
        m7983b(gLState);
        this.f5161h.m7686c(gLState);
    }

    public final float m7979a() {
        return this.f5162i;
    }

    public final float m7982b() {
        return this.f5163j;
    }

    public final boolean m7985c() {
        return this.f5156c.isEmpty() || this.f5162i == 0.0f || this.f5163j == 0.0f;
    }

    public final void m7981a(GLLabelGroup gLLabelGroup) {
        if (this.f5154a != gLLabelGroup) {
            this.f5167n = true;
        }
        this.f5154a = gLLabelGroup;
    }

    public final void m7980a(GLState gLState, Camera camera, ad adVar) {
        int i = 0;
        if (!this.f5166m) {
            ac a = adVar.m6701a();
            if (this.f5157d.isEmpty()) {
                Iterator it;
                GLLabelGroup gLLabelGroup;
                int i2;
                boolean z;
                Iterator it2 = this.f5156c.iterator();
                while (it2.hasNext()) {
                    it = ((ArrayList) it2.next()).iterator();
                    while (it.hasNext()) {
                        gLLabelGroup = (GLLabelGroup) it.next();
                        if (!(gLLabelGroup instanceof GLLabelGroup)) {
                            Texture a2 = gLLabelGroup.m7943a(a);
                            if (a2 == null) {
                                it2 = this.f5157d.iterator();
                                while (it2.hasNext()) {
                                    ((Texture) it2.next()).m7626f();
                                }
                                this.f5157d.clear();
                                i2 = 0;
                                if (i2 == 0) {
                                    if (!gLState.m7516a((int) AbstractSpiCall.DEFAULT_TIMEOUT)) {
                                        i2 = 0;
                                        if (i2 == 0) {
                                            return;
                                        }
                                    } else if (this.f5157d.isEmpty()) {
                                        throw new IllegalStateException("this.textureArray should be empty on initialize.");
                                    } else {
                                        it2 = this.f5156c.iterator();
                                        while (it2.hasNext()) {
                                            it = ((ArrayList) it2.next()).iterator();
                                            while (it.hasNext()) {
                                                gLLabelGroup = (GLLabelGroup) it.next();
                                                if (!(gLLabelGroup instanceof GLLabelGroup)) {
                                                    this.f5157d.add(gLLabelGroup.m7944a(gLState, a));
                                                }
                                            }
                                        }
                                    }
                                }
                                m7977a(gLState);
                                this.f5166m = true;
                                z = true;
                                if (i2 == 0) {
                                    return;
                                }
                            }
                            this.f5157d.add(a2);
                        }
                    }
                }
                z = true;
                if (i2 == 0) {
                    if (!gLState.m7516a((int) AbstractSpiCall.DEFAULT_TIMEOUT)) {
                        i2 = 0;
                        if (i2 == 0) {
                            return;
                        }
                    } else if (this.f5157d.isEmpty()) {
                        it2 = this.f5156c.iterator();
                        while (it2.hasNext()) {
                            it = ((ArrayList) it2.next()).iterator();
                            while (it.hasNext()) {
                                gLLabelGroup = (GLLabelGroup) it.next();
                                if (!(gLLabelGroup instanceof GLLabelGroup)) {
                                    this.f5157d.add(gLLabelGroup.m7944a(gLState, a));
                                }
                            }
                        }
                    } else {
                        throw new IllegalStateException("this.textureArray should be empty on initialize.");
                    }
                }
                m7977a(gLState);
                this.f5166m = true;
                z = true;
                if (i2 == 0) {
                    return;
                }
            }
            throw new IllegalStateException("this.textureArray should be empty.");
        }
        if (this.f5167n) {
            m7977a(gLState);
        }
        GL10 x = gLState.m7541x();
        if (this.f5161h.m7682a() != 0) {
            this.f5161h.m7683a(gLState);
            while (i < this.f5157d.size()) {
                ((Texture) this.f5157d.get(i)).m7613a(x);
                x.glDrawArrays(6, i * 4, 4);
                i++;
            }
        }
    }

    private void m7977a(GLState gLState) {
        this.f5160g.m7588f();
        this.f5161h.m7685b(gLState);
        this.f5160g.m7573a(this.f5157d.size() * 4);
        float f = this.f5163j - this.f5164k;
        int i = 0;
        int i2 = 0;
        while (i2 < this.f5156c.size()) {
            ArrayList arrayList = (ArrayList) this.f5156c.get(i2);
            Iterator it = arrayList.iterator();
            float f2 = 0.0f;
            float f3 = 0.0f;
            while (it.hasNext()) {
                GLLabelGroup gLLabelGroup = (GLLabelGroup) it.next();
                f2 = Math.max(f2, gLLabelGroup.m7948e());
                f3 = gLLabelGroup.m7942a() + f3;
            }
            float f4 = 0.0f;
            if (this.f5154a == GLLabelGroup.CENTER) {
                f4 = (this.f5162i - f3) / dm.DEFAULT_BACKOFF_MULT;
            } else if (this.f5154a == GLLabelGroup.RIGHT) {
                f4 = this.f5162i - f3;
            }
            Iterator it2 = arrayList.iterator();
            f3 = f4;
            while (it2.hasNext()) {
                gLLabelGroup = (GLLabelGroup) it2.next();
                if (gLLabelGroup instanceof GLLabelGroup) {
                    f3 += gLLabelGroup.m7942a();
                } else {
                    float e;
                    float a = gLLabelGroup.m7942a();
                    float b = gLLabelGroup.m7945b();
                    if (this.f5155b == GLLabelGroup.CENTER) {
                        e = f - ((f2 - gLLabelGroup.m7948e()) / dm.DEFAULT_BACKOFF_MULT);
                    } else if (this.f5155b == GLLabelGroup.BOTTOM) {
                        e = f - (f2 - gLLabelGroup.m7948e());
                    } else {
                        e = f;
                    }
                    float c = e + gLLabelGroup.m7946c();
                    int i3 = i + 1;
                    Texture texture = (Texture) this.f5157d.get(i);
                    float b2 = texture.m7615b();
                    e = texture.m7619c();
                    this.f5158e.m7566a(f3, 0.0f, c - b);
                    this.f5158e.m7566a(f3 + a, 0.0f, c - b);
                    this.f5158e.m7566a(a + f3, 0.0f, c);
                    this.f5158e.m7566a(f3, 0.0f, c);
                    this.f5159f.m7559a(0.0f, e);
                    this.f5159f.m7559a(b2, e);
                    this.f5159f.m7559a(b2, 0.0f);
                    this.f5159f.m7559a(0.0f, 0.0f);
                    f3 += gLLabelGroup.m7942a();
                    i = i3;
                }
            }
            i2++;
            f -= f2;
        }
        this.f5167n = false;
        this.f5160g.m7583c();
        this.f5161h.m7684a(this.f5160g.m7587e());
    }
}
