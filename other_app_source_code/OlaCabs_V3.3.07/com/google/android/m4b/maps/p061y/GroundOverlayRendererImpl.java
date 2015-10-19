package com.google.android.m4b.maps.p061y;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.as;
import com.google.android.m4b.maps.av.RepaintCallback;
import com.google.android.m4b.maps.av.Transform;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.Texture;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.google.android.m4b.maps.be.cb;
import com.google.android.m4b.maps.be.cb.GroundOverlayImpl;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.olacabs.customer.p076d.br;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.y.g */
public final class GroundOverlayRendererImpl implements GroundOverlayImpl, Overlay {
    private final OverlayRendererManagerImpl f8082a;
    private final cb f8083b;
    private Point f8084c;
    private as f8085d;
    private VertexBuffer f8086e;
    private Texture f8087f;
    private TexCoordBuffer f8088g;
    private float f8089h;

    public GroundOverlayRendererImpl(OverlayRendererManagerImpl overlayRendererManagerImpl, cb cbVar) {
        this.f8084c = new Point();
        this.f8082a = overlayRendererManagerImpl;
        this.f8083b = cbVar;
        m11695a(-1);
    }

    public final synchronized void m11695a(int i) {
        if ((i & 16) != 0) {
            LatLngBounds g = this.f8083b.m9338g();
            Point e = g.f7562b.f7555b < g.f7561a.f7555b ? ConversionUtils.m11640b(g.f7562b).m5957e(new Point(1073741824, 0)) : ConversionUtils.m11640b(g.f7562b);
            Point b = ConversionUtils.m11640b(g.f7561a);
            double k = (double) this.f8083b.m9342k();
            double l = (double) this.f8083b.m9343l();
            this.f8084c = new Point((int) ((k * ((double) e.m5958f())) + ((1.0d - k) * ((double) b.m5958f()))), (int) ((((double) b.m5960g()) * l) + ((1.0d - l) * ((double) e.m5960g()))));
        }
        if ((i & 23) != 0) {
            this.f8086e = new VertexBuffer(4);
            this.f8086e.m7726a(-this.f8083b.m9342k(), this.f8083b.m9343l(), 0.0f);
            this.f8086e.m7726a(-this.f8083b.m9342k(), this.f8083b.m9343l() - br.DEFAULT_BACKOFF_MULT, 0.0f);
            this.f8086e.m7726a(br.DEFAULT_BACKOFF_MULT - this.f8083b.m9342k(), this.f8083b.m9343l(), 0.0f);
            this.f8086e.m7726a(br.DEFAULT_BACKOFF_MULT - this.f8083b.m9342k(), this.f8083b.m9343l() - br.DEFAULT_BACKOFF_MULT, 0.0f);
            as a = ConversionUtils.m11632a(this.f8083b.m9338g());
            if (this.f8083b.m9336e() == 0.0f) {
                this.f8085d = a;
            } else {
                double hypot = Math.hypot((double) a.m5683d(), (double) a.m5684e());
                Point point = new Point((int) hypot, (int) hypot);
                this.f8085d = as.m5674a(new Rectangle2D(this.f8084c.m5959f(point), this.f8084c.m5957e(point)));
            }
        }
        if ((i & 4) != 0) {
            m11693g();
        }
        if ((i & 8) != 0) {
            synchronized (this.f8082a) {
                this.f8089h = this.f8083b.m9347p();
                this.f8082a.m11801c();
            }
        }
        this.f8082a.m11800b();
    }

    public final void m11694a() {
        synchronized (this.f8082a) {
            this.f8082a.m11796a((Overlay) this);
        }
        this.f8082a.m11800b();
    }

    public final void m11699a(GLState gLState, RepaintCallback repaintCallback) {
    }

    public final synchronized void m11698a(GLState gLState) {
        m11693g();
    }

    public final void m11701a(boolean z) {
    }

    public final void m11703b() {
    }

    public final void m11704b(int i) {
    }

    public final synchronized void m11700a(GLState gLState, Camera camera, ad adVar) {
        synchronized (this.f8083b) {
            if (!this.f8083b.m9349r()) {
            } else if (camera.m7453u().m5656b(this.f8085d.m5680b()) || this.f8085d.m5685f().m5958f() > this.f8085d.m5686g().m5958f()) {
                float b;
                GL10 x = gLState.m7541x();
                this.f8086e.m7736d(gLState);
                gLState.m7533p();
                if (this.f8087f == null) {
                    this.f8087f = new Texture(gLState);
                    this.f8087f.m7621c(true);
                    this.f8087f.m7624d(true);
                    this.f8087f.m7611a(this.f8083b.m9350s().m8903c());
                }
                if (this.f8088g == null) {
                    this.f8088g = new TexCoordBuffer(8);
                    b = this.f8087f.m7615b();
                    float c = this.f8087f.m7619c();
                    this.f8088g.m7692a(0.0f, 0.0f);
                    this.f8088g.m7692a(0.0f, c);
                    this.f8088g.m7692a(b, 0.0f);
                    this.f8088g.m7692a(b, c);
                }
                x.glBlendFunc(1, 771);
                x.glTexEnvx(8960, 8704, 8448);
                b = this.f8083b.m9345n();
                x.glColor4f(br.DEFAULT_BACKOFF_MULT - b, br.DEFAULT_BACKOFF_MULT - b, br.DEFAULT_BACKOFF_MULT - b, br.DEFAULT_BACKOFF_MULT - b);
                x.glPushMatrix();
                Transform.m7275a(gLState, camera, this.f8084c, br.DEFAULT_BACKOFF_MULT);
                x.glRotatef(this.f8083b.m9336e(), 0.0f, 0.0f, -1.0f);
                as a = ConversionUtils.m11632a(this.f8083b.m9338g());
                x.glScalef((float) a.m5683d(), (float) a.m5684e(), br.DEFAULT_BACKOFF_MULT);
                this.f8088g.m7706d(gLState);
                this.f8087f.m7613a(x);
                x.glDrawArrays(5, 0, 4);
                x.glPopMatrix();
            }
        }
    }

    public final void m11697a(Camera camera, GLState gLState) {
    }

    public final boolean m11702a(float f, float f2, Point point, Camera camera) {
        return false;
    }

    public final void m11696a(long j) {
    }

    public final boolean m11705c() {
        return true;
    }

    public final synchronized void m11706d() {
        m11693g();
    }

    public final String m11707e() {
        return this.f8083b.m9320a();
    }

    public final float m11708f() {
        return this.f8089h;
    }

    private void m11693g() {
        if (this.f8087f != null) {
            this.f8087f.m7626f();
            this.f8087f = null;
        }
    }
}
