package com.google.android.m4b.maps.ag;

import android.util.FloatMath;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ah;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.ar;
import com.google.android.m4b.maps.an.bd;
import com.google.android.m4b.maps.ax.Camera;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.ag.c */
public final class MultiZoomTileCoordGenerator extends ZoomTableTileCoordGenerator {
    private ar f3075d;
    private final List<ac> f3076e;
    private final Point f3077f;
    private Camera f3078g;
    private ar f3079h;
    private float f3080i;
    private final float f3081j;
    private long f3082k;

    public MultiZoomTileCoordGenerator(ai aiVar, int i, ah ahVar) {
        super(aiVar, ahVar);
        this.f3076e = new ArrayList();
        this.f3077f = new Point();
        this.f3082k = 0;
        this.f3081j = (float) (i * i);
    }

    public final List<ac> m4898a(Camera camera) {
        ar u = camera.m7453u();
        if (this.f3075d != null && u.equals(this.f3075d)) {
            if (this.f3076e.isEmpty() ? true : ((ac) this.f3076e.get(0)).m5447j().equals(this.b.m5476a())) {
                return this.f3076e;
            }
        }
        this.f3082k++;
        bd bdVar = (bd) u.m5668c();
        int c = (int) camera.m7432c(bdVar.m5760d().m5951c(bdVar.m5759c()), (float) camera.m7437e());
        this.f3076e.clear();
        this.f3078g = camera;
        this.f3079h = camera.m7453u();
        this.f3080i = FloatMath.cos(camera.m7443k() * 0.017453292f);
        ArrayList a = ac.m5427a(u.m5662a(), c, this.b.m5476a());
        for (int i = 0; i < a.size(); i++) {
            m4896a((ac) a.get(i), camera.m7430b(), false);
        }
        this.f3075d = u;
        return this.f3076e;
    }

    public final long m4897a() {
        return this.f3082k;
    }

    private void m4896a(ac acVar, Point point, boolean z) {
        if (!z || this.f3079h.m5656b(acVar.m5446i())) {
            int b = acVar.m5439b();
            int i = 536870912 >> b;
            this.f3077f.m5955d(acVar.m5442e() + i, acVar.m5443f() + i);
            float b2 = this.f3078g.m7429b((float) (i * 2), this.f3078g.m7420a(this.f3077f, true));
            if (b2 * (this.f3080i * b2) < this.f3081j || b >= 30) {
                this.f3076e.add(acVar);
                return;
            }
            List<ac> b3 = m4895b(acVar, point);
            if (b3.isEmpty()) {
                this.f3076e.add(acVar);
                return;
            }
            for (ac a : b3) {
                m4896a(a, point, true);
            }
        }
    }
}
