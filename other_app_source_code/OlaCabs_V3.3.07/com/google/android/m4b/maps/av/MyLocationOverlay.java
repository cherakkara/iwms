package com.google.android.m4b.maps.av;

import android.content.res.Resources;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.al.IndoorElevation;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.an.MyLocationMarker;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.RenderPass.RenderPass;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ax.CameraPosition;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.Texture;
import com.google.android.m4b.maps.az.ColorBuffer;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.google.android.m4b.maps.p060x.MyLocationMarkerAnimation;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ac;
import com.google.p025a.p028c.au;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.av.d */
public final class MyLocationOverlay extends BubbleBlowerOverlay implements AnimationCallback, BubbleBlower {
    private static final float f4517b;
    private static ColorBuffer f4518w;
    private final Resources f4519c;
    private final boolean f4520d;
    private int f4521e;
    private volatile RepaintCallback f4522f;
    private final Map<Integer, Texture> f4523g;
    private List<MyLocationOverlay> f4524h;
    private MyLocationMarkerAnimation f4525i;
    private AccuracyCircleOverlay f4526j;
    private final MyLocationMarker f4527k;
    private final MyLocationMarker f4528l;
    private final MyLocationMarker f4529m;
    private boolean f4530n;
    private volatile IndoorLevelReference f4531o;
    private volatile boolean f4532p;
    private float f4533q;
    private float f4534r;
    private float f4535s;
    private final int f4536t;
    private float f4537u;
    private final Point f4538v;

    /* renamed from: com.google.android.m4b.maps.av.d.a */
    public static class MyLocationOverlay {
        public final boolean f4507a;
        public final int f4508b;
        public final int f4509c;
        public final int f4510d;
        public final int f4511e;
        public final int f4512f;
        public final int f4513g;
        private final Boolean f4514h;
        private final Boolean f4515i;
        private final Boolean f4516j;

        /* renamed from: com.google.android.m4b.maps.av.d.a.a */
        public static class MyLocationOverlay {
            private Boolean f4501a;
            private Integer f4502b;
            private Integer f4503c;
            private boolean f4504d;
            private int f4505e;
            private int f4506f;

            public final MyLocationOverlay m7155a(boolean z) {
                this.f4501a = Boolean.valueOf(z);
                return this;
            }

            public final MyLocationOverlay m7153a(int i) {
                this.f4502b = Integer.valueOf(i);
                this.f4503c = Integer.valueOf(i);
                return this;
            }

            public final MyLocationOverlay m7154a(int i, int i2) {
                this.f4505e = i;
                this.f4506f = i2;
                return this;
            }

            public final MyLocationOverlay m7152a() {
                this.f4504d = true;
                return this;
            }

            public final MyLocationOverlay m7156b() {
                this.f4504d = false;
                return this;
            }

            public final MyLocationOverlay m7157c() {
                boolean z;
                if (this.f4503c != null) {
                    z = true;
                } else {
                    z = false;
                }
                Preconditions.m1829b(z, (Object) "Texture ID must be specified.");
                return new MyLocationOverlay(this.f4501a, null, null, this.f4504d, this.f4502b.intValue(), this.f4503c.intValue(), 0, 0, this.f4505e, this.f4506f);
            }
        }

        public MyLocationOverlay(Boolean bool, Boolean bool2, Boolean bool3, boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            this.f4514h = bool;
            this.f4515i = bool2;
            this.f4516j = bool3;
            this.f4507a = z;
            this.f4508b = i;
            this.f4509c = i2;
            this.f4510d = i3;
            this.f4511e = i4;
            this.f4512f = i5;
            this.f4513g = i6;
        }

        public final boolean m7158a(boolean z, boolean z2, boolean z3) {
            if (this.f4514h != null && this.f4514h.booleanValue() != z) {
                return false;
            }
            if (this.f4515i != null && this.f4515i.booleanValue() != z2) {
                return false;
            }
            if (this.f4516j == null || this.f4516j.booleanValue() == z3) {
                return true;
            }
            return false;
        }
    }

    static {
        f4517b = dm.DEFAULT_BACKOFF_MULT;
        ColorBuffer colorBuffer = new ColorBuffer(4);
        f4518w = colorBuffer;
        colorBuffer.m7650b(1931574222, 4);
    }

    public MyLocationOverlay(Resources resources, ai aiVar, boolean z) {
        super(aiVar);
        this.f4523g = au.m2807a();
        this.f4530n = false;
        this.f4532p = true;
        this.f4538v = new Point();
        this.f4527k = new MyLocationMarker();
        this.f4528l = new MyLocationMarker();
        this.f4529m = new MyLocationMarker();
        this.f4519c = resources;
        this.f4520d = z;
        m7169a(new MyLocationOverlay().m7155a(true).m7152a().m7153a(R.chevron).m7154a(resources.getColor(R.accuracy_circle_line_color), resources.getColor(R.accuracy_circle_fill_color)).m7157c(), new MyLocationOverlay().m7155a(false).m7156b().m7153a(R.blue_dot).m7154a(resources.getColor(R.accuracy_circle_line_color), resources.getColor(R.accuracy_circle_fill_color)).m7157c());
        this.f4533q = 64.0f;
        this.f4534r = 0.5f;
        this.f4535s = 0.5f;
        this.f4536t = this.f4519c.getDimensionPixelSize(R.dav_my_location_bubble_y_offset);
        this.f4525i = new MyLocationMarkerAnimation();
    }

    public final void m7178a(GLState gLState, RepaintCallback repaintCallback) {
        this.f4522f = repaintCallback;
    }

    public final GLOverlay m7187d() {
        return GLOverlay.MY_LOCATION_OVERLAY_VECTORMAPS;
    }

    public final boolean m7182a(Camera camera, GLState gLState) {
        float f = 0.8f;
        Point f2 = m7188f();
        if (f2 != null) {
            ai.m6783a(camera, f2, 0, Math.round(((float) this.f4536t) * this.f4537u), this.f4538v);
        }
        float l = camera.m7444l();
        if (l >= 14.0f) {
            f = br.DEFAULT_BACKOFF_MULT;
        } else if (l >= 10.0f) {
            f = 0.8f + ((l - 10.0f) * 0.049999997f);
        }
        this.f4537u = f;
        MyLocationMarkerAnimation myLocationMarkerAnimation = this.f4525i;
        return true;
    }

    public final synchronized void m7176a(MyLocationMarker myLocationMarker) {
        if (!(this.f4527k.m5919k() == myLocationMarker.m5919k() && Objects.m1811a(this.f4527k.m5914f(), myLocationMarker.m5914f()) && this.f4527k.m5915g() == myLocationMarker.m5915g())) {
            this.f4532p = true;
        }
        this.f4527k.m5906a(myLocationMarker);
        if (this.f4527k.m5919k()) {
            this.f4525i.m11594b(this.f4527k);
        } else {
            this.f4525i = new MyLocationMarkerAnimation();
        }
        if (this.f4522f != null) {
            this.f4522f.m6765a(false, false);
        }
    }

    private synchronized void m7169a(MyLocationOverlay... myLocationOverlayArr) {
        this.f4524h = ac.m2341a((Object[]) myLocationOverlayArr);
        m7171o();
    }

    private synchronized void m7171o() {
        for (Texture f : this.f4523g.values()) {
            f.m7626f();
        }
        this.f4523g.clear();
    }

    public final void m7175a(float f, int i, int i2) {
        this.f4533q = 0.5f * f;
        this.f4534r = ((float) i) / 100.0f;
        this.f4535s = ((float) i2) / 100.0f;
    }

    public final void m7185b() {
    }

    public final void m7186c() {
    }

    public final synchronized Point m7188f() {
        return this.f4527k.m5904a();
    }

    public final synchronized IndoorLevelReference m7189g() {
        return m7172p().m5914f();
    }

    public final boolean m7181a(Camera camera) {
        Point f = m7188f();
        if (f == null) {
            return false;
        }
        int[] b = camera.m7431b(f);
        int ceil = (int) Math.ceil((double) ((m7173q().f4507a ? this.f4535s : this.f4534r) * (this.f4533q * this.f4537u)));
        return b[0] - ceil < camera.m7437e() && b[0] + ceil >= 0 && b[1] - ceil < camera.m7438f() && ceil + b[1] >= 0;
    }

    public final Point m7192j() {
        return this.f4538v;
    }

    public final boolean m7193k() {
        return this.f4520d;
    }

    public final int m7174a(float f, float f2, Camera camera) {
        synchronized (this) {
            if (this.f4527k.m5919k()) {
                int[] b = camera.m7431b(this.f4527k.m5904a());
                return (int) (((f2 - ((float) b[1])) * (f2 - ((float) b[1]))) + ((f - ((float) b[0])) * (f - ((float) b[0]))));
            }
            return Integer.MAX_VALUE;
        }
    }

    public final void m7180a(List<af> list, float f, float f2, Point point, Camera camera, int i) {
        if (this.f4520d) {
            int a = m7174a(f, f2, camera);
            if (a < i) {
                list.add(new af(this, this, a));
            }
        }
    }

    public final void m7177a(GLState gLState) {
        m7171o();
        this.f4532p = true;
    }

    public final boolean m7183a(List<RenderPass> list) {
        if (!this.f4532p) {
            return false;
        }
        this.f4532p = false;
        this.f4531o = m7189g();
        IndoorElevation indoorElevation = null;
        if (this.f4531o != null) {
            indoorElevation = IndoorState.m5334a().m5362d(this.f4531o.m11306a());
        }
        list.clear();
        if (this.f4531o == null || indoorElevation == null) {
            return super.m6673a((List) list);
        }
        list.add(m6664a(RenderPass.DEFAULT, indoorElevation));
        return true;
    }

    public final void m7179a(GLState gLState, Camera camera, ad adVar) {
        if (adVar.m6704b() <= 0 && this.f4527k.m5919k()) {
            synchronized (this) {
                this.f4521e = 0;
                if (this.f4521e != 0 && this.f4525i.m11593a(this.f4528l) && this.f4528l.m5919k()) {
                    this.f4529m.m5906a(this.f4528l);
                    gLState.m7514a(gLState.m7522e() + 200);
                } else {
                    this.f4529m.m5906a(this.f4527k);
                }
                if (this.f4529m.m5919k()) {
                    GL10 x = gLState.m7541x();
                    x.glPushMatrix();
                    RenderTweak renderTweak = null;
                    if (this.f4531o != null) {
                        renderTweak = adVar.m6705c().m7207a(this.f4531o.m11306a());
                        if (renderTweak != null) {
                            renderTweak.m5295a(gLState, camera, adVar, this.f4529m.m5904a());
                        }
                    }
                    m7170b(gLState, camera, adVar);
                    if (renderTweak != null) {
                        renderTweak.m5294a(gLState, adVar);
                    }
                    x.glPopMatrix();
                    return;
                }
            }
        }
    }

    private synchronized void m7170b(GLState gLState, Camera camera, ad adVar) {
        if (this.f4529m.m5911c() > 0) {
            if (this.f4526j == null) {
                this.f4526j = new AccuracyCircleOverlay(null, 0, 0, 0, null, "MyLocation");
                this.f4526j.m6686a((float) dm.DEFAULT_BACKOFF_MULT);
            }
            this.f4526j.m6687a(this.f4529m.m5912d(), this.f4529m.m5911c() * 2);
            MyLocationOverlay q = m7173q();
            this.f4526j.m6691b(q.f4512f);
            this.f4526j.m6693c(q.f4513g);
            this.f4526j.m6689a(gLState, camera, adVar);
        }
        float a = camera.m7419a(this.f4533q, camera.m7420a(this.f4529m.m5904a(), true)) * this.f4537u;
        GL10 x = gLState.m7541x();
        Transform.m7275a(gLState, camera, this.f4529m.m5904a(), a);
        gLState.m7533p();
        gLState.f4853g.m7736d(gLState);
        gLState.f4849c.m7706d(gLState);
        MyLocationOverlay q2 = m7173q();
        Object obj = q2.f4509c != q2.f4508b ? 1 : null;
        x.glBlendFunc(1, 771);
        x.glTexEnvx(8960, 8704, 7681);
        x.glColor4x(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT);
        if (q2.f4510d != 0) {
            x.glTexEnvx(8960, 8704, 8448);
            m7168a(gLState, q2.f4510d).m7613a(x);
            float h = (this.f4529m.m5916h() * 10.0f) + br.DEFAULT_BACKOFF_MULT;
            float h2 = br.DEFAULT_BACKOFF_MULT - (this.f4529m.m5916h() * 3.0f);
            float h3 = this.f4529m.m5916h() * 4.0f;
            float h4 = this.f4529m.m5916h() * -4.0f;
            x.glColor4f(h2, h2, h2, h2);
            x.glTranslatef(h3, h4, 0.0f);
            x.glScalef(h, h, h);
            x.glDrawArrays(5, 0, 4);
            x.glScalef(br.DEFAULT_BACKOFF_MULT / h, br.DEFAULT_BACKOFF_MULT / h, br.DEFAULT_BACKOFF_MULT / h);
            x.glTranslatef(-h3, -h4, 0.0f);
            x.glColor4x(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT);
            x.glTexEnvx(8960, 8704, 7681);
        }
        if (this.f4529m.m5916h() != 0.0f) {
            x.glTranslatef(0.0f, 0.0f, this.f4529m.m5916h() * ((float) this.f4529m.m5904a().m5956e()));
        }
        if (this.f4529m.m5913e() && q2.f4511e != 0) {
            x.glRotatef(-this.f4529m.m5910b(), 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT);
            x.glScalef(dm.DEFAULT_BACKOFF_MULT, dm.DEFAULT_BACKOFF_MULT, dm.DEFAULT_BACKOFF_MULT);
            m7168a(gLState, q2.f4511e).m7613a(x);
            x.glDrawArrays(5, 0, 4);
            x.glScalef(0.5f, 0.5f, 0.5f);
            x.glRotatef(this.f4529m.m5910b(), 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT);
        }
        if (m7173q().f4507a) {
            x.glRotatef(-this.f4529m.m5910b(), 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT);
        } else {
            x.glRotatef(-camera.m7442j(), 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT);
            x.glRotatef(camera.m7443k(), br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f);
        }
        if (obj == null || this.f4529m.m5918j() != br.DEFAULT_BACKOFF_MULT) {
            m7168a(gLState, q2.f4508b).m7613a(x);
        } else {
            m7168a(gLState, q2.f4509c).m7613a(x);
        }
        x.glDrawArrays(5, 0, 4);
    }

    public final AnimationCallback m7190h() {
        return this;
    }

    public final int m7184b(Camera camera) {
        return this.f4521e;
    }

    public final CameraPosition m7191i() {
        return null;
    }

    private synchronized Texture m7168a(GLState gLState, int i) {
        Texture texture;
        texture = (Texture) this.f4523g.get(Integer.valueOf(i));
        if (texture == null) {
            texture = new Texture(gLState);
            texture.m7621c(true);
            texture.m7610a(this.f4519c, i);
            this.f4523g.put(Integer.valueOf(i), texture);
        }
        return texture;
    }

    private synchronized MyLocationMarker m7172p() {
        return this.f4527k;
    }

    private MyLocationOverlay m7173q() {
        boolean e;
        boolean g;
        boolean i;
        synchronized (this) {
            MyLocationMarker p = m7172p();
            e = p.m5913e();
            g = p.m5915g();
            i = p.m5917i();
        }
        return m7167a(e, i, g);
    }

    private synchronized MyLocationOverlay m7167a(boolean z, boolean z2, boolean z3) {
        MyLocationOverlay myLocationOverlay;
        for (MyLocationOverlay myLocationOverlay2 : this.f4524h) {
            if (myLocationOverlay2.m7158a(z, z2, z3)) {
                break;
            }
        }
        myLocationOverlay2 = null;
        return myLocationOverlay2;
    }
}
