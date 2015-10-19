package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.al.IndoorElevation;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.ColorUtil;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.VertexDataCache;
import com.google.android.m4b.maps.az.IndexBuffer;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.olacabs.customer.p076d.br;
import javax.microedition.khronos.opengles.GL10;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.av.a */
public final class AccuracyCircleOverlay extends al {
    private static VertexBuffer f4113h;
    private static IndexBuffer f4114i;
    private static VertexBuffer f4115j;
    private static IndexBuffer f4116k;
    private Point f4117a;
    private FeatureId f4118b;
    private Rectangle2D f4119c;
    private int f4120d;
    private float f4121e;
    private int f4122f;
    private int f4123g;
    private float f4124l;

    static {
        f4113h = new VertexBuffer(100);
        f4114i = new IndexBuffer(100);
        f4115j = new VertexBuffer(HttpStatus.SC_SWITCHING_PROTOCOLS);
        f4116k = new IndexBuffer(HttpStatus.SC_PROCESSING);
        VertexDataCache.m7638a(f4113h, f4114i);
        VertexDataCache.m7639b(f4115j, f4116k);
    }

    public AccuracyCircleOverlay(Point point, int i, int i2, int i3, FeatureId featureId, String str) {
        this.f4117a = null;
        this.f4120d = 0;
        m6685c();
        this.f4122f = 0;
        this.f4123g = 0;
        this.f4124l = br.DEFAULT_BACKOFF_MULT;
        this.f4118b = null;
    }

    public final GLOverlay m6694d() {
        return GLOverlay.UNUSED;
    }

    public final boolean m6690a(Camera camera, GLState gLState) {
        return true;
    }

    public final void m6688a(GLState gLState) {
    }

    public final void m6692b(GLState gLState) {
    }

    public final void m6689a(GLState gLState, Camera camera, ad adVar) {
        if (adVar.m6704b() <= 0 && this.f4117a != null && this.f4121e != 0.0f && camera.m7453u().m5662a().m5681b(this.f4119c)) {
            GL10 x = gLState.m7541x();
            x.glPushMatrix();
            if (this.f4118b != null) {
                IndoorElevation d = IndoorState.m5334a().m5362d(this.f4118b);
                if (d != null) {
                    this.f4117a.m5944a((int) d.m5299a(camera, this.f4117a));
                }
            }
            float r = camera.m7450r();
            Transform.m7275a(gLState, camera, this.f4117a, r);
            r = this.f4121e / r;
            GL10 x2 = gLState.m7541x();
            x2.glScalef(r, r, r);
            x2.glBlendFunc(770, 771);
            f4115j.m7736d(gLState);
            ColorUtil.m7485a(x2, this.f4123g);
            f4116k.m7665a(gLState, 6);
            f4113h.m7736d(gLState);
            ColorUtil.m7485a(x2, this.f4122f);
            x2.glLineWidth(this.f4124l);
            f4114i.m7665a(gLState, 2);
            x.glPopMatrix();
        }
    }

    public final void m6687a(Point point, int i) {
        if (point != this.f4117a || this.f4120d != i) {
            this.f4117a = point;
            this.f4120d = i;
            m6685c();
        }
    }

    public final void m6691b(int i) {
        this.f4122f = i;
    }

    public final void m6693c(int i) {
        this.f4123g = i;
    }

    public final void m6686a(float f) {
        this.f4124l = f;
    }

    private void m6685c() {
        int i = this.f4120d / 2;
        Point point = this.f4117a;
        float e = (point == null || i == 0) ? 0.0f : ((float) i) * ((float) point.m5956e());
        this.f4121e = e;
        if (this.f4117a != null) {
            this.f4119c = Rectangle2D.m6040a(this.f4117a, ((int) this.f4121e) + 1);
        }
    }
}
