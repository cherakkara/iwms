package com.google.android.m4b.maps.ag;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ah;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.ar;
import com.google.android.m4b.maps.av.ZoomTable;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.p040u.Config;
import com.olacabs.customer.p076d.br;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.ag.e */
public final class SingleZoomTileCoordGenerator extends ZoomTableTileCoordGenerator {
    private boolean f3085d;
    private long f3086e;
    private ar f3087f;
    private ar f3088g;
    private List<ac> f3089h;
    private List<ac> f3090i;

    public SingleZoomTileCoordGenerator(ai aiVar, ah ahVar) {
        super(aiVar, ahVar);
        this.f3086e = 0;
        boolean z = (aiVar == ai.f3432j || aiVar == ai.f3433k || aiVar == ai.f3434l) && Config.m11320a().m11335i() > 1.0d;
        this.f3085d = z;
    }

    public final List<ac> m4909a(Camera camera) {
        ar u = camera.m7453u();
        if (this.f3087f != null && u.equals(this.f3087f)) {
            if (this.f3089h.isEmpty() ? true : ((ac) this.f3089h.get(0)).m5447j().equals(this.b.m5476a())) {
                return this.f3089h;
            }
        }
        this.f3086e++;
        List a = ac.m5427a(u.m5662a(), m4906c(camera), this.b.m5476a());
        int i = (camera.m7443k() == 0.0f && camera.m7442j() == 0.0f) ? 1 : 0;
        if (i == 0) {
            SingleZoomTileCoordGenerator.m4905a(u, a);
        }
        this.f3089h = a;
        this.f3087f = u;
        return this.f3089h;
    }

    public final long m4908a() {
        return this.f3086e;
    }

    public final List<ac> m4910b(Camera camera) {
        ar u = camera.m7453u();
        if (this.f3088g != null && u.equals(this.f3088g)) {
            return this.f3090i;
        }
        List b = ac.m5430b(u.m5662a(), m4906c(camera));
        Object obj = (camera.m7443k() == 0.0f && camera.m7442j() == 0.0f) ? 1 : null;
        if (obj == null) {
            SingleZoomTileCoordGenerator.m4905a(u, b);
        }
        this.f3088g = u;
        this.f3090i = b;
        return this.f3090i;
    }

    private static void m4905a(ar arVar, ArrayList<ac> arrayList) {
        int i;
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            ac acVar = (ac) arrayList.get(i2);
            if (arVar.m5656b(acVar.m5446i())) {
                int i4 = i3 + 1;
                arrayList.set(i3, acVar);
                i = i4;
            } else {
                i = i3;
            }
            i2++;
            i3 = i;
        }
        for (i = size - 1; i >= i3; i--) {
            arrayList.remove(i);
        }
    }

    public final float m4907a(Point point) {
        if (this.f3085d) {
            return super.m4892a(point) - br.DEFAULT_BACKOFF_MULT;
        }
        return super.m4892a(point);
    }

    private int m4906c(Camera camera) {
        float l = camera.m7444l();
        ZoomTable a = this.c.m7365a(camera.m7430b(), this.a);
        if (a != null) {
            return a.m7352a(l);
        }
        return (int) l;
    }
}
