package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.ax.Camera;

/* compiled from: FeatureClusterMember */
public final class af {
    private final BubbleBlower f4142a;
    private final BubbleBlowerOverlay f4143b;
    private int f4144c;
    private boolean f4145d;

    public af(BubbleBlower bubbleBlower, BubbleBlowerOverlay bubbleBlowerOverlay, int i) {
        this.f4142a = bubbleBlower;
        this.f4143b = bubbleBlowerOverlay;
        this.f4144c = i;
    }

    public final void m6710a(float f, float f2, Point point, Camera camera) {
        this.f4144c = this.f4142a.m7159a(f, f2, camera);
        if (this.f4144c < Integer.MAX_VALUE && this.f4143b.m6817n() > 0) {
            float g = camera.m7439g() * 5.0f;
            g *= g;
            this.f4144c = (((int) g) * this.f4143b.m6817n()) + this.f4144c;
        }
    }

    public final int m6709a() {
        return this.f4144c;
    }

    public final void m6711a(boolean z) {
        this.f4145d = z;
    }

    public final boolean m6712b() {
        return this.f4145d;
    }

    public final boolean m6713c() {
        return this.f4143b.m6815a(this.f4142a);
    }
}
