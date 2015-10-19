package com.google.android.m4b.maps.ba;

import android.graphics.Bitmap;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.al.IndoorElevation;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.an.AbsolutePosition;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.PointOfInterest;
import com.google.android.m4b.maps.an.Region2D;
import com.google.android.m4b.maps.an.Road;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.at;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.an.bd;
import com.google.android.m4b.maps.an.bk;
import com.google.android.m4b.maps.an.bk.LabelGroup;
import com.google.android.m4b.maps.av.Renderer;
import com.google.android.m4b.maps.av.TextGenerator;
import com.google.android.m4b.maps.av.Transform;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.PointPool;
import com.google.android.m4b.maps.ay.Texture.Texture;
import com.google.android.m4b.maps.ba.GLLabelGroup.GLLabelGroup;
import com.google.android.m4b.maps.bc.KeyedLabelSource;
import com.google.android.m4b.maps.bc.LabelSource;
import com.google.android.m4b.maps.bc.LabelTheme;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.android.m4b.maps.p060x.Fade;
import com.google.p025a.p029d.EventBus;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.sothree.slidinguppanel.p086a.R.R;
import javax.microedition.khronos.opengles.GL10;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.ba.n */
public class GLPointLabel extends GLLabel {
    private static final GLPointLabel[] f5104D;
    private static final GLPointLabel[] f5105E;
    private float f5106A;
    private final float f5107B;
    private final float[] f5108C;
    protected AbsolutePosition f5109h;
    protected GLLabelGroup f5110i;
    protected GLLabelGroup f5111j;
    protected GLPointLabel f5112k;
    protected float f5113l;
    protected float f5114m;
    protected float f5115n;
    protected float f5116o;
    private AbsolutePosition f5117p;
    private bd f5118q;
    private final String f5119r;
    private final boolean f5120s;
    private boolean f5121t;
    private float f5122u;
    private boolean f5123v;
    private final GLPointLabel[] f5124w;
    private int f5125x;
    private Fade f5126y;
    private boolean f5127z;

    /* renamed from: com.google.android.m4b.maps.ba.n.1 */
    static /* synthetic */ class GLPointLabel {
        static final /* synthetic */ int[] f5287a;
        static final /* synthetic */ int[] f5288b;

        static {
            f5288b = new int[GLLabelGroup.values().length];
            try {
                f5288b[GLLabelGroup.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5288b[GLLabelGroup.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f5288b[GLLabelGroup.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            f5287a = new int[GLPointLabel.values().length];
            try {
                f5287a[GLPointLabel.AT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f5287a[GLPointLabel.RIGHT_OF_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f5287a[GLPointLabel.LEFT_OF_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f5287a[GLPointLabel.BELOW_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f5287a[GLPointLabel.ABOVE_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f5287a[GLPointLabel.BOTTOM_RIGHT.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f5287a[GLPointLabel.BOTTOM_LEFT.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f5287a[GLPointLabel.TOP_RIGHT.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f5287a[GLPointLabel.TOP_LEFT.ordinal()] = 9;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.n.a */
    public enum GLPointLabel {
        AT_CENTER,
        ABOVE_CENTER,
        RIGHT_OF_CENTER,
        BELOW_CENTER,
        LEFT_OF_CENTER,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP_RIGHT,
        TOP_LEFT;

        public static GLPointLabel m8081a(int i) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    return AT_CENTER;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    return LEFT_OF_CENTER;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    return RIGHT_OF_CENTER;
                case HTTP.HT /*9*/:
                    return ABOVE_CENTER;
                case HTTP.LF /*10*/:
                    return TOP_LEFT;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    return TOP_RIGHT;
                case HTTP.CR /*13*/:
                    return BELOW_CENTER;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    return BOTTOM_LEFT;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                    return BOTTOM_RIGHT;
                default:
                    throw new IllegalArgumentException("Unknown position");
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.n.b */
    public static class GLPointLabel {
        public final GLPointLabel f5299a;
        public final GLLabelGroup f5300b;

        public GLPointLabel(GLPointLabel gLPointLabel, GLLabelGroup gLLabelGroup) {
            this.f5299a = gLPointLabel;
            this.f5300b = gLLabelGroup;
        }
    }

    static {
        f5104D = new GLPointLabel[0];
        f5105E = new GLPointLabel[]{new GLPointLabel(GLPointLabel.BELOW_CENTER, GLLabelGroup.CENTER), new GLPointLabel(GLPointLabel.ABOVE_CENTER, GLLabelGroup.CENTER), new GLPointLabel(GLPointLabel.LEFT_OF_CENTER, GLLabelGroup.RIGHT), new GLPointLabel(GLPointLabel.RIGHT_OF_CENTER, GLLabelGroup.LEFT)};
    }

    public static GLPointLabel m7921a(PointOfInterest pointOfInterest, LabelSource labelSource, boolean z, Camera camera, Texture<Bitmap> texture, TextGenerator textGenerator, LabelTheme labelTheme, EventBus eventBus) {
        GLLabelGroup a = GLLabelGroup.m7976a(pointOfInterest.m5982m(), pointOfInterest, camera, texture, textGenerator, labelTheme);
        if (a == null || a.m7985c()) {
            return null;
        }
        GLPointLabel[] gLPointLabelArr;
        int i;
        GLLabelGroup gLLabelGroup = null;
        if (pointOfInterest.m5983n() != null) {
            gLLabelGroup = GLLabelGroup.m7976a(pointOfInterest.m5983n(), pointOfInterest, camera, texture, textGenerator, labelTheme);
            if (gLLabelGroup == null) {
                return null;
            }
            if (gLLabelGroup.m7985c()) {
                gLLabelGroup = null;
            }
        }
        if (pointOfInterest.m5984o().length != 0 || gLLabelGroup == null) {
            gLPointLabelArr = new GLPointLabel[pointOfInterest.m5984o().length];
            for (i = 0; i < gLPointLabelArr.length; i++) {
                gLPointLabelArr[i] = new GLPointLabel(GLPointLabel.m8081a(pointOfInterest.m5984o()[i].m5422d()), GLLabelGroup.m7966a(pointOfInterest.m5984o()[i].m5419a()));
            }
        } else {
            gLPointLabelArr = f5105E;
        }
        boolean z2 = gLLabelGroup == null || gLPointLabelArr.length == 1;
        AbsolutePosition absolutePosition = pointOfInterest.m5981l()[0];
        Point b = absolutePosition.m5406b();
        StringBuilder stringBuilder = new StringBuilder();
        for (i = 0; i < pointOfInterest.m5982m().m5841b(); i++) {
            LabelGroup a2 = pointOfInterest.m5982m().m5839a(i);
            if (a2.m5826b()) {
                stringBuilder.append(a2.m5831g());
            }
        }
        for (i = 0; i < pointOfInterest.m5983n().m5841b(); i++) {
            a2 = pointOfInterest.m5983n().m5839a(i);
            if (a2.m5826b()) {
                stringBuilder.append(a2.m5831g());
            }
        }
        if (pointOfInterest.m5973d() != null) {
            stringBuilder.append(pointOfInterest.m5973d().m11294a());
        }
        if (pointOfInterest.m5972c().length() > 0) {
            stringBuilder.append(pointOfInterest.m5972c());
        } else if (pointOfInterest.m5968a().m5439b() > 13) {
            stringBuilder.append(b.toString());
        }
        String stringBuilder2 = stringBuilder.toString();
        boolean z3 = pointOfInterest.m5985p() && pointOfInterest.m5972c().length() > 0 && labelTheme.f5427p;
        if (z3) {
            return new GLInteractivePoiLabel(pointOfInterest, labelSource, stringBuilder2, absolutePosition, null, pointOfInterest.m5978i(), pointOfInterest.m5979j(), z, z2, a, gLLabelGroup, gLPointLabelArr, labelTheme.f5428q);
        }
        return new GLPointLabel(pointOfInterest, labelSource, stringBuilder2, absolutePosition, null, pointOfInterest.m5978i(), pointOfInterest.m5979j(), z, z2, a, gLLabelGroup, gLPointLabelArr, labelTheme.f5428q, z3, eventBus);
    }

    public static GLPointLabel m7922a(Road road, int i, LabelSource labelSource, Point point, Point point2, boolean z, LabelTheme labelTheme, Camera camera, Texture<Bitmap> texture) {
        bk a = road.m6057a(i);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < a.m5841b(); i2++) {
            LabelGroup a2 = a.m5839a(i2);
            if (a2.m5825a()) {
                return null;
            }
            if (a2.m5826b()) {
                stringBuilder.append(a2.m5831g());
            }
        }
        if (stringBuilder.length() == 0) {
            return null;
        }
        GLLabelGroup a3 = GLLabelGroup.m7976a(a, road, camera, texture, null, labelTheme);
        if (a3 == null) {
            return null;
        }
        return new GLPointLabel(road, labelSource, stringBuilder.toString(), new AbsolutePosition(point, 0, 0.0f, null, 0.0f, 0.0f, 0.0f), point2 == null ? null : new AbsolutePosition(point2, 0, 0.0f, null, 0.0f, 0.0f, 0.0f), -1.0f, -1.0f, z, false, a3, null, f5104D, labelTheme.f5428q, false, null);
    }

    GLPointLabel(bc bcVar, LabelSource labelSource, String str, AbsolutePosition absolutePosition, AbsolutePosition absolutePosition2, float f, float f2, boolean z, boolean z2, GLLabelGroup gLLabelGroup, GLLabelGroup gLLabelGroup2, GLPointLabel[] gLPointLabelArr, boolean z3, boolean z4, EventBus eventBus) {
        super(bcVar, labelSource, bcVar.m5548e(), f, f2, bcVar.m5549f(), z, z3);
        this.f5122u = -1.0f;
        this.f5108C = new float[8];
        this.f5119r = str;
        this.f5109h = absolutePosition;
        this.f5117p = absolutePosition2;
        this.f5110i = gLLabelGroup;
        this.f5111j = gLLabelGroup2;
        this.f5120s = z2;
        if (this.f5111j == null) {
            gLPointLabelArr = f5104D;
        }
        this.f5124w = gLPointLabelArr;
        this.f5125x = 0;
        if (this.f5111j != null) {
            this.f5112k = this.f5124w[this.f5125x];
            this.f5111j.m7981a(this.f5112k.f5300b);
        }
        this.f5123v = false;
        float f3 = 0.0f;
        if (gLLabelGroup != null) {
            f3 = 0.0f + (gLLabelGroup.m7979a() * gLLabelGroup.m7982b());
        }
        if (gLLabelGroup2 != null) {
            f3 += gLLabelGroup2.m7979a() * gLLabelGroup2.m7982b();
        }
        this.f5107B = f3;
    }

    public final float m7929n() {
        return this.f5107B;
    }

    public final String m7931t() {
        return this.f5119r;
    }

    public boolean m7925a(Camera camera, GLState gLState) {
        float a;
        if (m7909i() && this.f && this.f5122u > 0.0f) {
            a = m7919a(camera) / this.f5122u;
            this.g = GLLabel.m7896a(a);
            if (a < 0.25f || a > dm.DEFAULT_BACKOFF_MULT) {
                return false;
            }
            return true;
        }
        this.g = AccessibilityNodeInfoCompat.ACTION_CUT;
        boolean z = camera.m7443k() == 0.0f && camera.m7442j() == 0.0f;
        float l = camera.m7444l();
        if (this.f5118q != null && this.f5127z && z && l == this.f5106A) {
            return true;
        }
        if (this.f5118q != null && this.f5109h.m5405a() && l == this.f5106A) {
            bd bdVar = this.f5118q;
            return true;
        }
        int a2;
        int b;
        this.f5127z = z;
        this.f5106A = l;
        if (this.f5110i != null) {
            a2 = ((int) this.f5110i.m7979a()) >> 1;
            b = ((int) this.f5110i.m7982b()) >> 1;
        } else {
            b = 0;
            a2 = 0;
        }
        if (this.f5111j != null) {
            int i;
            int a3 = ((int) this.f5111j.m7979a()) >> 1;
            int b2 = ((int) this.f5111j.m7982b()) >> 1;
            int i2 = a2 > a3 ? a2 : a3;
            if (b > b2) {
                i = b;
            } else {
                i = b2;
            }
            switch (GLPointLabel.f5287a[this.f5112k.f5299a.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    this.f5113l = (float) (-a2);
                    this.f5114m = (float) ((a3 * 2) + a2);
                    this.f5115n = (float) (-i);
                    this.f5116o = (float) i;
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    this.f5113l = (float) ((-a2) - (a3 * 2));
                    this.f5114m = (float) a2;
                    this.f5115n = (float) (-i);
                    this.f5116o = (float) i;
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    this.f5115n = (float) (-b);
                    this.f5116o = (float) (b + (b2 * 2));
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    this.f5115n = (float) ((-b) - (b2 * 2));
                    this.f5116o = (float) b;
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    this.f5113l = (float) (-a2);
                    this.f5114m = (float) ((a3 * 2) + a2);
                    this.f5115n = (float) (-b);
                    this.f5116o = (float) (b + (b2 * 2));
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    this.f5113l = (float) ((-a2) - (a3 * 2));
                    this.f5114m = (float) a2;
                    this.f5115n = (float) (-b);
                    this.f5116o = (float) (b + (b2 * 2));
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    this.f5113l = (float) (-a2);
                    this.f5114m = (float) ((a3 * 2) + a2);
                    this.f5115n = (float) ((-b) - (b2 * 2));
                    this.f5116o = (float) b;
                    break;
                case HTTP.HT /*9*/:
                    this.f5113l = (float) ((-a2) - (a3 * 2));
                    this.f5114m = (float) a2;
                    this.f5115n = (float) ((-b) - (b2 * 2));
                    this.f5116o = (float) b;
                    break;
                default:
                    this.f5113l = (float) (-i2);
                    this.f5114m = (float) i2;
                    this.f5115n = (float) (-i);
                    this.f5116o = (float) i;
                    break;
            }
            if (this.f5112k.f5299a == GLPointLabel.BELOW_CENTER || this.f5112k.f5299a == GLPointLabel.ABOVE_CENTER) {
                switch (GLPointLabel.f5288b[this.f5112k.f5300b.ordinal()]) {
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        this.f5113l = (float) ((-a2) - 10);
                        this.f5114m = Math.max(this.f5113l + ((float) (a3 * 2)), (float) a2);
                        break;
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        this.f5114m = (float) (a2 + 10);
                        this.f5113l = Math.min(this.f5114m - ((float) (a3 * 2)), (float) (-a2));
                        break;
                    case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                        this.f5113l = (float) (-i2);
                        this.f5114m = (float) i2;
                        break;
                    default:
                        break;
                }
            }
        }
        this.f5113l = (float) (-a2);
        this.f5114m = (float) a2;
        this.f5115n = (float) (-b);
        this.f5116o = (float) b;
        Point.m5936b(camera.m7446n(), this.f5109h.m5406b(), ((PointPool) Renderer.f4572b.get()).f4913a);
        this.f5122u = m7919a(camera);
        if (!this.f5120s) {
            a = camera.m7441i() / camera.m7420a(this.f5109h.m5406b(), true);
            this.f5113l *= a;
            this.f5114m *= a;
            this.f5115n *= a;
            this.f5116o = a * this.f5116o;
        }
        if (this.c != null && (this.c instanceof KeyedLabelSource)) {
            Object a4 = ((KeyedLabelSource) this.c).m8185a();
            if (a4 != null && (a4 instanceof FeatureId)) {
                IndoorElevation d = IndoorState.m5334a().m5362d((FeatureId) a4);
                if (d != null) {
                    this.f5109h.m5406b().m5944a((int) d.m5299a(camera, this.f5109h.m5406b()));
                }
            }
        }
        bdVar = this.f5118q;
        if (this.f5109h.m5405a()) {
            if (this.f5109h.m5405a()) {
                a = this.f5109h.m5407c();
                float f = a < 270.0f ? a + 90.0f : a - 270.0f;
                l = f < 270.0f ? f + 90.0f : f - 270.0f;
                float a5 = camera.m7420a(this.f5109h.m5406b(), true);
                PointPool pointPool = (PointPool) Renderer.f4572b.get();
                Point point = pointPool.f4913a;
                point.m5943a(f, camera.m7419a(this.f5113l, a5));
                Point point2 = pointPool.f4914b;
                point2.m5943a(f, camera.m7419a(this.f5114m, a5));
                Point point3 = pointPool.f4915c;
                point3.m5943a(l, camera.m7419a(this.f5115n, a5));
                Point point4 = pointPool.f4916d;
                point4.m5943a(l, camera.m7419a(this.f5116o, a5));
                Point point5 = pointPool.f4917e;
                Point.m5931a(this.f5109h.m5406b(), point3, point5);
                Point point6 = pointPool.f4918f;
                Point.m5931a(this.f5109h.m5406b(), point4, point6);
                bdVar = bd.m5753a(point6.m5957e(point), point6.m5957e(point2), point5.m5957e(point), point5.m5957e(point2));
            } else {
                bdVar = null;
            }
            this.f5118q = bdVar;
        } else {
            camera.m7426a(this.f5109h.m5406b(), this.f5108C);
            a = this.f5108C[0];
            l = this.f5108C[1];
            this.f5118q = camera.m7423a(this.f5113l + a, a + this.f5114m, this.f5115n + l, l + this.f5116o);
        }
        if (this.f5118q == null) {
            return false;
        }
        return true;
    }

    public final Region2D m7930o() {
        return this.f5118q;
    }

    private float m7919a(Camera camera) {
        if (this.f5120s) {
            return camera.m7419a((float) br.DEFAULT_BACKOFF_MULT, camera.m7420a(this.f5109h.m5406b(), true));
        }
        return camera.m7419a((float) br.DEFAULT_BACKOFF_MULT, camera.m7441i());
    }

    public final void m7926b(GLState gLState) {
        super.m7904b(gLState);
        if (this.f5110i != null) {
            this.f5110i.m7983b(gLState);
        }
        if (this.f5111j != null) {
            this.f5111j.m7983b(gLState);
        }
    }

    public final void m7928c(GLState gLState) {
        super.m7907c(gLState);
        if (this.f5110i != null) {
            this.f5110i.m7984c(gLState);
        }
        if (this.f5111j != null) {
            this.f5111j.m7984c(gLState);
        }
    }

    public final void m7923a(GLState gLState, Camera camera, ad adVar) {
        int a;
        float c;
        float a2;
        float b;
        if (!this.f5123v) {
            adVar.m6701a();
            if (this.e) {
                this.f5126y = new Fade(500, Fade.Fade.FADE_IN);
            }
            this.f5123v = true;
        }
        GL10 x = gLState.m7541x();
        x.glPushMatrix();
        GL10 x2 = gLState.m7541x();
        Point b2 = this.f5109h.m5406b();
        if (!camera.m7436d() && ((!this.f5109h.m5405a() || this.f5109h.m5407c() - camera.m7442j() == 0.0f) && camera.m7443k() == 0.0f)) {
            camera.m7426a(this.f5109h.m5406b(), this.f5108C);
            b2 = camera.m7435d((float) Math.round(this.f5108C[0]), (float) Math.round(this.f5108C[1]));
        }
        Transform.m7275a(gLState, camera, b2, this.f5122u);
        if (this.f5126y != null) {
            a = this.f5126y.m11584a(gLState);
            if (a == AccessibilityNodeInfoCompat.ACTION_CUT) {
                this.f5126y = null;
            }
        } else {
            a = this.g;
        }
        gLState.m7541x().glColor4x(a, a, a, a);
        if (this.f5109h.m5405a()) {
            c = this.f5109h.m5407c() - camera.m7442j();
            if (c < 0.0f) {
                c += 360.0f;
            }
            float c2 = this.f5109h.m5407c();
            c = (this.f5121t || c <= 90.0f || c >= 270.0f) ? c2 : 180.0f + c2;
            if (c >= 360.0f) {
                c -= 360.0f;
            }
            x2.glRotatef(c, 0.0f, 0.0f, -1.0f);
            x2.glRotatef(-90.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f);
        } else {
            Transform.m7278a(x2, camera);
        }
        if (this.f5110i != null) {
            a2 = this.f5110i.m7979a();
            b = this.f5110i.m7982b();
            c2 = this.f5110i.m7979a() / dm.DEFAULT_BACKOFF_MULT;
            c = this.f5110i.m7982b() / dm.DEFAULT_BACKOFF_MULT;
            x2.glTranslatef(-c2, 0.0f, -c);
            this.f5110i.m7980a(gLState, camera, adVar);
        } else {
            c = 0.0f;
            c2 = 0.0f;
            b = 0.0f;
            a2 = 0.0f;
        }
        if (this.f5111j != null) {
            switch (GLPointLabel.f5287a[this.f5112k.f5299a.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    b = c - (this.f5111j.m7982b() / dm.DEFAULT_BACKOFF_MULT);
                    c = c2 - (this.f5111j.m7979a() / dm.DEFAULT_BACKOFF_MULT);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    b = c - (this.f5111j.m7982b() / dm.DEFAULT_BACKOFF_MULT);
                    c = a2;
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    b = c - (this.f5111j.m7982b() / dm.DEFAULT_BACKOFF_MULT);
                    c = -this.f5111j.m7979a();
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    b = -this.f5111j.m7982b();
                    c = 0.0f;
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    c = 0.0f;
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    b = -this.f5111j.m7982b();
                    c = a2;
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    c = -this.f5111j.m7979a();
                    b = -this.f5111j.m7982b();
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    c = a2;
                    break;
                case HTTP.HT /*9*/:
                    c = -this.f5111j.m7979a();
                    break;
                default:
                    b = 0.0f;
                    c = 0.0f;
                    break;
            }
            if (this.f5112k.f5299a == GLPointLabel.BELOW_CENTER || this.f5112k.f5299a == GLPointLabel.ABOVE_CENTER) {
                switch (GLPointLabel.f5288b[this.f5112k.f5300b.ordinal()]) {
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        c = -10.0f;
                        break;
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        c = (10.0f + a2) - this.f5111j.m7979a();
                        break;
                    case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                        c = c2 - (this.f5111j.m7979a() / dm.DEFAULT_BACKOFF_MULT);
                        break;
                }
            }
            x2.glTranslatef(c, 0.0f, b);
            this.f5111j.m7980a(gLState, camera, adVar);
        }
        x.glPopMatrix();
    }

    static int m7920a(Style style, LabelTheme labelTheme, float f) {
        return (int) GLLabel.m7895a(style, labelTheme.f5416e, labelTheme.f5417f, labelTheme.f5418g, f);
    }

    public final boolean m7924a(at atVar) {
        return atVar.m5654a(this.f5109h.m5406b());
    }

    public final boolean m7927b(Camera camera, GLState gLState) {
        if (this.f5125x + 1 < this.f5124w.length) {
            GLPointLabel[] gLPointLabelArr = this.f5124w;
            int i = this.f5125x + 1;
            this.f5125x = i;
            this.f5112k = gLPointLabelArr[i];
            this.f5111j.m7981a(this.f5112k.f5300b);
            this.f5118q = null;
            m7925a(camera, gLState);
            return true;
        } else if (this.f5117p == null) {
            return false;
        } else {
            this.f5109h = this.f5117p;
            this.f5117p = null;
            if (this.f5124w.length > 1) {
                this.f5125x = 0;
                this.f5112k = this.f5124w[0];
                this.f5111j.m7981a(this.f5112k.f5300b);
            }
            this.f5118q = null;
            m7925a(camera, gLState);
            return true;
        }
    }

    public final boolean m7932w() {
        if (this.f5109h == null) {
            return false;
        }
        return this.f5109h.m5405a();
    }
}
