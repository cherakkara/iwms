package com.google.android.m4b.maps.av;

import android.graphics.Bitmap;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.al.IndoorElevation;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ar;
import com.google.android.m4b.maps.an.as;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.Texture;
import com.google.android.m4b.maps.ba.GLMarker;
import com.google.android.m4b.maps.be.ad;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.google.android.m4b.maps.p058v.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: GLMarkerOverlay */
public final class ak extends BubbleBlowerOverlay {
    private final LinkedList<GLMarker> f4248b;
    private boolean f4249c;
    private final HashMap<Object, GLMarker> f4250d;
    private final HashMap<Bitmap, Texture> f4251e;
    private ar f4252f;
    private List<GLMarker> f4253g;
    private final GLOverlay f4254h;
    private int f4255i;
    private boolean f4256j;
    private GLMarker f4257k;
    private GLMarkerOverlay f4258l;

    /* renamed from: com.google.android.m4b.maps.av.ak.a */
    public interface GLMarkerOverlay {
        void m6810c(GLMarker gLMarker);

        void m6811d(GLMarker gLMarker);

        void m6812e(GLMarker gLMarker);
    }

    public ak(GLOverlay gLOverlay, ai aiVar) {
        super(aiVar);
        this.f4248b = new LinkedList();
        this.f4250d = new HashMap();
        this.f4251e = new HashMap();
        this.f4253g = com.google.p025a.p028c.ar.m2515a();
        this.f4255i = 0;
        this.f4256j = false;
        this.f4254h = gLOverlay;
    }

    public final synchronized void m6828a(GLMarker gLMarker) {
        if (!this.f4250d.containsKey(gLMarker)) {
            gLMarker.m8050a(this);
            this.f4248b.add(gLMarker);
            this.f4250d.put(gLMarker, gLMarker);
            m6822k();
        }
    }

    private void m6822k() {
        this.f4253g.clear();
        this.f4249c = true;
    }

    public final void m6831b(GLMarker gLMarker) {
        synchronized (this.a) {
            synchronized (this) {
                if (this.f4250d.containsKey(gLMarker)) {
                    this.f4250d.remove(gLMarker);
                    this.f4248b.remove(gLMarker);
                    if (gLMarker.m8075l()) {
                        gLMarker.m8066d(false);
                        this.a.m6797c();
                    }
                    m6821d(gLMarker);
                    m6822k();
                }
            }
        }
    }

    public final synchronized void m6834c(GLMarker gLMarker) {
        if (this.f4250d.containsKey(gLMarker)) {
            this.f4250d.remove(gLMarker);
            this.f4248b.remove(gLMarker);
            m6821d(gLMarker);
            m6822k();
        }
    }

    private void m6821d(GLMarker gLMarker) {
        if (gLMarker.m8077n() == 0) {
            this.f4251e.remove(gLMarker.m8071h());
        }
        if (gLMarker.m8078o() == 0) {
            this.f4251e.remove(gLMarker.m8072i());
        }
    }

    public final synchronized void m6833c() {
        m6822k();
    }

    public final GLOverlay m6837d() {
        return this.f4254h;
    }

    public final void m6826a(GLState gLState) {
        synchronized (this.a) {
            synchronized (this) {
                m6823o();
            }
        }
    }

    private void m6820d(float f, float f2, Camera camera) {
        this.f4257k.m8049a(camera.m7435d(f, f2 - 70.0f));
    }

    public final boolean j_() {
        return this.f4256j;
    }

    public final void m6824a(GLMarkerOverlay gLMarkerOverlay) {
        this.f4258l = gLMarkerOverlay;
    }

    public final boolean m6836c(float f, float f2, Camera camera) {
        if (!this.f4256j) {
            return false;
        }
        m6820d(f, f2, camera);
        if (this.f4258l != null) {
            this.f4258l.m6811d(this.f4257k);
        }
        return true;
    }

    public final boolean m6832b(float f, float f2, Point point, Camera camera) {
        synchronized (this) {
            for (GLMarker gLMarker : this.f4253g) {
                if (gLMarker.m8055a() && gLMarker.m8056a(f, f2, point, camera)) {
                    this.f4256j = true;
                    this.f4257k = gLMarker;
                    m6820d(f, f2, camera);
                    if (this.f4258l != null) {
                        this.f4258l.m6810c(this.f4257k);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public final boolean m6835c(float f, float f2, Point point, Camera camera) {
        if (!this.f4256j) {
            return false;
        }
        m6820d(f, f2, camera);
        GLMarker gLMarker = this.f4257k;
        if (this.f4258l != null) {
            this.f4258l.m6812e(this.f4257k);
        }
        this.f4257k = null;
        this.f4256j = false;
        return true;
    }

    public final boolean m6830a(Camera camera, GLState gLState) {
        return true;
    }

    public final void m6827a(GLState gLState, Camera camera, ad adVar) {
        if (adVar.m6704b() == 0 && this.f4248b.size() > 0) {
            synchronized (this) {
                m6825a(camera);
                m6818b(camera);
                m6819c(camera);
                if (this.f4253g.size() == 0) {
                    return;
                }
                GL10 x = gLState.m7541x();
                gLState.m7533p();
                x.glBlendFunc(1, 771);
                x.glTexEnvx(8960, 8704, 7681);
                gLState.f4852f.m7736d(gLState);
                ad adVar2 = new ad(adVar);
                adVar2.m6702a(0);
                for (GLMarker gLMarker : this.f4253g) {
                    if (gLMarker.m8072i() != null) {
                        gLMarker.m8051a(gLState, camera, adVar2);
                    }
                }
                adVar2.m6702a(1);
                GLMarker gLMarker2 = null;
                for (GLMarker gLMarker3 : this.f4253g) {
                    if (gLMarker3.m8075l()) {
                        gLMarker2 = gLMarker3;
                    } else {
                        gLMarker3.m8051a(gLState, camera, adVar2);
                    }
                }
                if (gLMarker2 != null) {
                    gLMarker2.m8051a(gLState, camera, adVar2);
                }
            }
        }
    }

    public final synchronized void m6829a(List<af> list, float f, float f2, Point point, Camera camera, int i) {
        m6825a(camera);
        for (BubbleBlower bubbleBlower : this.f4253g) {
            if (bubbleBlower.m7166k()) {
                int a = bubbleBlower.m7159a(f, f2, camera);
                if (a < i) {
                    list.add(new af(bubbleBlower, this, a));
                }
            }
        }
    }

    public final HashMap<Bitmap, Texture> m6838i() {
        return this.f4251e;
    }

    private void m6818b(Camera camera) {
        IndoorState a = IndoorState.m5334a();
        for (GLMarker gLMarker : this.f4253g) {
            IndoorLevelReference g = gLMarker.m8070g();
            if (g != null) {
                IndoorElevation d = a.m5362d(g.m11306a());
                if (d != null) {
                    synchronized (gLMarker) {
                        Point f = gLMarker.m8069f();
                        f.m5944a((int) d.m5299a(camera, f));
                        gLMarker.m8049a(f);
                    }
                } else {
                    continue;
                }
            }
        }
    }

    final void m6825a(Camera camera) {
        ar u = camera.m7453u();
        if (this.f4249c || !u.equals(this.f4252f)) {
            if (this.f4253g == null) {
                this.f4253g = new ArrayList();
            } else {
                this.f4253g.clear();
            }
            as a = u.m5662a();
            ar a2 = ar.m5659a(u, camera.m7430b(), 0.2f);
            Iterator it = this.f4248b.iterator();
            while (it.hasNext()) {
                try {
                    GLMarker gLMarker = (GLMarker) it.next();
                    if (gLMarker.m8067d() && gLMarker.m8071h() != null) {
                        Point point = new Point();
                        gLMarker.m8069f().m5965i(point);
                        if ((a.m5678a(point) && u.m5665a(point)) || (a2.m5665a(point) && gLMarker.m8057a(camera))) {
                            this.f4253g.add(gLMarker);
                        }
                    }
                } catch (RuntimeException e) {
                    int size = this.f4253g.size();
                    StringBuilder stringBuilder = new StringBuilder(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
                    StringBuilder append = stringBuilder.append("#:");
                    int i = this.f4255i;
                    this.f4255i = i + 1;
                    append.append(i).append(" T:").append(Thread.currentThread().getName()).append(" E:").append(e.toString()).append(" C:").append(it.getClass().getName()).append(" numM:").append(size);
                    Util.m11555b("GLMarkerOverlay", stringBuilder.toString());
                }
            }
            this.f4252f = u;
            this.f4249c = false;
        }
    }

    public final synchronized List<ad> m6839j() {
        List<ad> a;
        a = com.google.p025a.p028c.ar.m2515a();
        for (GLMarker q : this.f4253g) {
            a.add(q.m8080q());
        }
        return a;
    }

    private void m6819c(Camera camera) {
        for (GLMarker b : this.f4253g) {
            b.m8063b(camera);
        }
        if (this.f4253g.size() > 1) {
            Collections.sort(this.f4253g);
        }
    }

    private synchronized void m6823o() {
        Iterator it = this.f4248b.iterator();
        while (it.hasNext()) {
            GLMarker gLMarker = (GLMarker) it.next();
            gLMarker.m8077n();
            gLMarker.m8078o();
        }
        this.f4251e.clear();
    }
}
