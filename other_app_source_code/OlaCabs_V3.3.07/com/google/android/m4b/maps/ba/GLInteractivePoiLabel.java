package com.google.android.m4b.maps.ba;

import com.google.android.m4b.maps.an.AbsolutePosition;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.PointOfInterest;
import com.google.android.m4b.maps.av.BubbleBlower;
import com.google.android.m4b.maps.av.ai;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ba.GLPointLabel.GLPointLabel;
import com.google.android.m4b.maps.bc.LabelSource;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.ba.g */
public final class GLInteractivePoiLabel extends GLPointLabel implements BubbleBlower {
    private final Point f5128p;

    GLInteractivePoiLabel(PointOfInterest pointOfInterest, LabelSource labelSource, String str, AbsolutePosition absolutePosition, AbsolutePosition absolutePosition2, float f, float f2, boolean z, boolean z2, GLLabelGroup gLLabelGroup, GLLabelGroup gLLabelGroup2, GLPointLabel[] gLPointLabelArr, boolean z3) {
        super(pointOfInterest, labelSource, str, absolutePosition, null, f, f2, z, z2, gLLabelGroup, gLLabelGroup2, gLPointLabelArr, z3, true, null);
        this.f5128p = new Point();
    }

    public final void m7936b() {
    }

    public final void m7937c() {
    }

    public final Point m7938f() {
        return this.h.m5406b();
    }

    public final IndoorLevelReference m7939g() {
        return null;
    }

    public final boolean m7934a(Camera camera) {
        int[] b = camera.m7431b(this.h.m5406b());
        float f = (float) b[0];
        float f2 = (float) b[1];
        if (this.l + f >= ((float) camera.m7437e()) || f + this.m < 0.0f || this.n + f2 >= ((float) camera.m7438f()) || f2 + this.o < 0.0f) {
            return false;
        }
        return true;
    }

    public final Point m7940j() {
        return this.f5128p;
    }

    public final boolean m7941k() {
        return true;
    }

    public final int m7933a(float f, float f2, Camera camera) {
        int[] b = camera.m7431b(this.h.m5406b());
        float f3 = f - ((float) b[0]);
        float f4 = f2 - ((float) b[1]);
        return (int) ((f4 * f4) + (f3 * f3));
    }

    public final boolean m7935a(Camera camera, GLState gLState) {
        Point b = this.h.m5406b();
        float b2 = this.i.m7982b() / dm.DEFAULT_BACKOFF_MULT;
        if (this.j != null && this.k.f5299a == GLPointLabel.ABOVE_CENTER) {
            b2 += this.j.m7982b();
        }
        ai.m6783a(camera, b, 0, (int) b2, this.f5128p);
        return super.m7925a(camera, gLState);
    }
}
