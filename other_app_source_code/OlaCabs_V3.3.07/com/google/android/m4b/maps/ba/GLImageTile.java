package com.google.android.m4b.maps.ba;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.bg;
import com.google.android.m4b.maps.an.bp;
import com.google.android.m4b.maps.av.RankMergingFeatureIterator;
import com.google.android.m4b.maps.av.Transform;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.bc.LabelSource;
import com.google.android.m4b.maps.cm.Clock;
import java.util.Collection;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ba.f */
public final class GLImageTile implements GLTile {
    private final Rectangle2D f5082a;
    private final ac f5083b;
    private final ai f5084c;
    private String[] f5085d;
    private String[] f5086e;
    private int f5087f;
    private GLRaster f5088g;
    private List<bp> f5089h;
    private final float[] f5090i;
    private long f5091j;
    private GLLabel f5092k;

    public static GLImageTile m7873a(aa aaVar, GLState gLState) {
        GLImageTile gLImageTile = new GLImageTile(aaVar.m5409a(), aaVar.m5411b());
        if (aaVar instanceof bg) {
            bg bgVar = (bg) aaVar;
            gLImageTile.f5088g = GLRaster.m8083a(bgVar.m5782j(), gLImageTile.f5083b, gLState);
            gLImageTile.f5085d = bgVar.m5778f();
            gLImageTile.f5086e = bgVar.m5779g();
            gLImageTile.f5087f = bgVar.m5780h();
            gLImageTile.f5089h = bgVar.m5781i();
        } else {
            gLImageTile.f5085d = new String[0];
            gLImageTile.f5086e = new String[0];
            gLImageTile.f5087f = -1;
        }
        return gLImageTile;
    }

    private GLImageTile(ac acVar, ai aiVar) {
        this.f5090i = new float[4];
        this.f5091j = 0;
        this.f5083b = acVar;
        this.f5084c = aiVar;
        this.f5082a = acVar.m5446i();
    }

    public final void m7885b(GLState gLState) {
        if (this.f5088g != null) {
            this.f5088g.m8090b(gLState);
        }
        if (this.f5092k != null) {
            this.f5092k.m7904b(gLState);
            this.f5092k = null;
        }
    }

    public final void m7888c(GLState gLState) {
        if (this.f5088g != null) {
            this.f5088g.m8091c(gLState);
        }
        if (this.f5092k != null) {
            this.f5092k.m7907c(gLState);
            this.f5092k = null;
        }
    }

    public final boolean m7881a() {
        return true;
    }

    public final boolean m7882a(RankMergingFeatureIterator rankMergingFeatureIterator) {
        return false;
    }

    public final void m7878a(LabelSource labelSource) {
    }

    public final int m7874a(Camera camera, com.google.android.m4b.maps.av.ac acVar) {
        if (this.f5088g != null) {
            return 2;
        }
        return 0;
    }

    public final void m7886b(GLState gLState, Camera camera, ad adVar) {
        adVar.m6701a();
        adVar.m6704b();
        GLRaster.m8084a(gLState);
    }

    public final void m7877a(GLState gLState, Camera camera, ad adVar) {
        if (this.f5088g != null || adVar.m6704b() != 1) {
            GL10 x = gLState.m7541x();
            x.glPushMatrix();
            if (camera.m7421a() != this.f5091j) {
                this.f5091j = camera.m7421a();
                Point c = this.f5082a.m6050c();
                if (!camera.m7436d() && camera.m7443k() == 0.0f && camera.m7442j() == 0.0f && camera.m7444l() == ((float) ((int) camera.m7444l()))) {
                    camera.m7426a(c, gLState.f4856j);
                    c = camera.m7435d((float) Math.round(gLState.f4856j[0]), (float) Math.round(gLState.f4856j[1]));
                }
                Transform.m7277a(gLState, camera, c, (float) this.f5082a.m6053f(), this.f5090i);
            }
            Transform.m7279a(x, this.f5090i);
            if (adVar.m6704b() == 1) {
                this.f5088g.m8088a(gLState, camera, adVar);
            } else if (adVar.m6704b() == 15) {
                GLTileBounds.f5350a.m8116a(gLState, camera, adVar);
            }
            x.glPopMatrix();
        }
    }

    public final ac m7884b() {
        return this.f5083b;
    }

    public final void m7880a(boolean z) {
    }

    public final boolean m7883a(Clock clock) {
        return false;
    }

    public final boolean m7887b(Clock clock) {
        return false;
    }

    public final boolean m7889c() {
        return this.f5088g != null && this.f5088g.m8092c();
    }

    public final void m7890d() {
        if (this.f5088g != null) {
            this.f5088g.m8093e();
        }
    }

    public final void m7876a(long j) {
        if (this.f5088g != null) {
            this.f5088g.m8087a(3000);
        }
    }

    public final void m7875a(int i, Collection<String> collection) {
        for (Object add : this.f5085d) {
            collection.add(add);
        }
    }

    public final void m7879a(Collection<String> collection) {
        for (Object add : this.f5086e) {
            collection.add(add);
        }
    }

    public final int m7891e() {
        return this.f5087f;
    }

    public final List<bp> m7894h() {
        return this.f5089h;
    }

    public final int m7892f() {
        return this.f5088g == null ? 0 : this.f5088g.m8086a();
    }

    public final int m7893g() {
        if (this.f5088g != null) {
            return this.f5088g.m8089b() + 136;
        }
        return 136;
    }
}
