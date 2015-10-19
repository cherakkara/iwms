package com.google.android.m4b.maps.al;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Region2D;
import com.google.android.m4b.maps.an.al;
import com.google.android.m4b.maps.an.av;
import com.google.android.m4b.maps.an.ay;
import com.google.android.m4b.maps.an.az;
import com.google.p025a.p028c.ar;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.al.g */
public final class IndoorLevelOutline implements ay {
    private final ay f3305a;

    /* renamed from: com.google.android.m4b.maps.al.g.a */
    public static class IndoorLevelOutline {
        private final List<al> f3304a;

        public IndoorLevelOutline() {
            this.f3304a = ar.m2515a();
        }

        public final void m5312a(av avVar) {
            this.f3304a.add(avVar.m5694a());
        }

        public final IndoorLevelOutline m5311a() {
            return new IndoorLevelOutline(this.f3304a);
        }
    }

    public IndoorLevelOutline(List<al> list) {
        int i = 0;
        for (al a : list) {
            i = a.m5571a() + i;
        }
        ay azVar = new az(i);
        for (al a2 : list) {
            a2.m5574a(azVar);
        }
        this.f3305a = azVar;
    }

    public final Rectangle2D m5316a() {
        return this.f3305a.m5313a();
    }

    public final boolean m5317a(Point point) {
        return this.f3305a.m5314a(point);
    }

    public final boolean m5318a(Region2D region2D) {
        return this.f3305a.m5315a(region2D);
    }
}
