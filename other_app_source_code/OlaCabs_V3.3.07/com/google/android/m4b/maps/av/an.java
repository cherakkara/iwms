package com.google.android.m4b.maps.av;

import android.support.v4.view.MotionEventCompat;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.bd;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.az.ColorBuffer;
import com.google.android.m4b.maps.az.VertexBuffer;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: GLSkyPlane */
public final class an extends al {
    private static final float f4314a;
    private static final float f4315b;
    private Point f4316c;
    private int f4317d;
    private ac f4318e;
    private boolean f4319f;
    private final VertexBuffer f4320g;
    private final ColorBuffer f4321h;
    private final ColorBuffer f4322i;
    private final Point f4323j;
    private final Point f4324k;

    static {
        f4314a = (float) Math.tan(0.08726646259971647d);
        f4315b = (float) Math.tan(0.05235987755982989d);
    }

    public an() {
        this.f4317d = 1;
        this.f4318e = ac.NONE;
        this.f4323j = new Point();
        this.f4324k = new Point();
        this.f4320g = new VertexBuffer(6);
        this.f4321h = new ColorBuffer(6);
        int a = m6864a(Renderer.m7228a(ac.NORMAL));
        this.f4321h.m7646a(a, 2);
        this.f4321h.m7646a(a | MotionEventCompat.ACTION_MASK, 4);
        this.f4322i = new ColorBuffer(6);
        a = m6864a(Renderer.m7228a(ac.NIGHT));
        this.f4322i.m7646a(a, 2);
        this.f4322i.m7646a(a | MotionEventCompat.ACTION_MASK, 4);
    }

    public final GLOverlay m6867d() {
        return GLOverlay.SKY;
    }

    public final void m6865a(GLState gLState, Camera camera, ad adVar) {
        boolean z;
        ac a = adVar.m6701a();
        if (this.f4319f || a != this.f4318e) {
            this.f4318e = a;
            this.f4319f = false;
            z = true;
        } else {
            z = false;
        }
        if (z) {
            ac a2 = adVar.m6701a();
            this.f4320g.m7730a(gLState);
            if (!(a2 == ac.NONE || a2 == ac.RASTER_ONLY || camera.m7443k() == 0.0f)) {
                float b = Camera.m7416b((float) camera.m7446n().m5962h()) - (a2 == ac.HYBRID ? 3.0f : 5.0f);
                if (camera.m7443k() + (camera.m7440h() * 0.5f) > b) {
                    int i = (int) camera.m7441i();
                    this.f4317d = i;
                    bd bdVar = (bd) camera.m7433c(b).m5668c();
                    this.f4316c = bdVar.m5760d();
                    this.f4323j.m5955d(0, 0);
                    Point.m5936b(bdVar.m5759c(), this.f4316c, this.f4324k);
                    this.f4320g.m7728a(this.f4323j, this.f4317d);
                    this.f4320g.m7728a(this.f4324k, this.f4317d);
                    int c = (int) ((a2 == ac.HYBRID ? f4315b : f4314a) * this.f4316c.m5951c(camera.m7446n()));
                    this.f4323j.m5944a(c);
                    this.f4324k.m5944a(c);
                    this.f4320g.m7728a(this.f4323j, this.f4317d);
                    this.f4320g.m7728a(this.f4324k, this.f4317d);
                    this.f4323j.m5944a(i);
                    this.f4324k.m5944a(i);
                    this.f4320g.m7728a(this.f4323j, this.f4317d);
                    this.f4320g.m7728a(this.f4324k, this.f4317d);
                }
            }
        }
        if (this.f4320g.m7725a() != 0) {
            GL10 x = gLState.m7541x();
            x.glPushMatrix();
            Transform.m7275a(gLState, camera, this.f4316c, (float) this.f4317d);
            gLState.m7531n();
            gLState.m7536s();
            x.glBlendFunc(770, 771);
            this.f4320g.m7736d(gLState);
            if (adVar.m6701a() == ac.NIGHT) {
                this.f4322i.m7652c(gLState);
            } else {
                this.f4321h.m7652c(gLState);
            }
            x.glDrawArrays(5, 0, this.f4320g.m7725a());
            x.glPopMatrix();
        }
    }

    public final boolean m6866a(Camera camera, GLState gLState) {
        this.f4319f = true;
        return true;
    }

    private static int m6864a(int[] iArr) {
        return (((iArr[0] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) << 16) | ((iArr[1] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) << 8)) | (iArr[2] & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
    }
}
