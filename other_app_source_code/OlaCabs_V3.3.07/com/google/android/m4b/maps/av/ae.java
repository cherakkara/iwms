package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.ax.Camera;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FeatureClusterManager */
public final class ae {
    private final List<af> f4138a;
    private int f4139b;
    private float[] f4140c;
    private boolean f4141d;

    public ae() {
        this.f4138a = new ArrayList();
        this.f4139b = -1;
        this.f4141d = true;
    }

    public final void m6707a(boolean z) {
        this.f4141d = z;
    }

    public final boolean m6708a(float f, float f2, Point point, Camera camera, List<BubbleBlowerOverlay> list) {
        af afVar;
        int g = (int) (camera.m7439g() * 30.0f);
        g *= g;
        if (!(this.f4141d || this.f4140c == null)) {
            float f3 = this.f4140c[0];
            float f4 = this.f4140c[1];
            if (((int) (((f3 - f) * (f3 - f)) + ((f4 - f2) * (f4 - f2)))) <= g) {
                for (af afVar2 : this.f4138a) {
                    afVar2.m6710a(f, f2, point, camera);
                }
                this.f4139b = m6706a();
                if (this.f4139b != -1) {
                    return false;
                }
                afVar2 = (af) this.f4138a.get(this.f4139b);
                afVar2.m6711a(true);
                return afVar2.m6713c();
            }
        }
        this.f4141d = false;
        this.f4138a.clear();
        if (this.f4140c == null) {
            this.f4140c = new float[2];
        }
        this.f4140c[0] = f;
        this.f4140c[1] = f2;
        g = (int) (camera.m7439g() * 30.0f);
        int i = g * g;
        for (BubbleBlowerOverlay a : list) {
            a.m6814a(this.f4138a, f, f2, point, camera, i);
        }
        this.f4139b = m6706a();
        if (this.f4139b != -1) {
            return false;
        }
        afVar2 = (af) this.f4138a.get(this.f4139b);
        afVar2.m6711a(true);
        return afVar2.m6713c();
    }

    private int m6706a() {
        while (this.f4138a.size() != 0) {
            if (this.f4138a.size() == 1) {
                return 0;
            }
            int i = Integer.MAX_VALUE;
            int i2 = -1;
            for (int i3 = 0; i3 < this.f4138a.size(); i3++) {
                af afVar = (af) this.f4138a.get(i3);
                if (!afVar.m6712b() && afVar.m6709a() < r3) {
                    i = afVar.m6709a();
                    i2 = i3;
                }
            }
            if (i2 != -1) {
                return i2;
            }
            for (af afVar2 : this.f4138a) {
                afVar2.m6711a(false);
            }
            if (this.f4139b != -1) {
                ((af) this.f4138a.get(this.f4139b)).m6711a(true);
            }
        }
        return -1;
    }
}
