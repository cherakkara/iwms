package com.google.android.m4b.maps.ax;

import android.content.res.Resources;
import com.google.android.m4b.maps.an.Point;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.ax.e */
public final class CameraPositionSanitizer {
    private volatile float f4813a;
    private final Resources f4814b;
    private CameraPositionSanitizer f4815c;

    /* renamed from: com.google.android.m4b.maps.ax.e.a */
    public interface CameraPositionSanitizer {
        float m7221a(Point point);

        float m7222c();
    }

    public CameraPositionSanitizer(Resources resources) {
        this.f4813a = 75.0f;
        if (resources != null) {
            int i = resources.getDisplayMetrics().densityDpi;
        }
        this.f4814b = resources;
    }

    public final void m7467a(CameraPositionSanitizer cameraPositionSanitizer) {
        this.f4815c = cameraPositionSanitizer;
    }

    public final CameraPositionSanitizer m7465a() {
        return this.f4815c;
    }

    public final void m7466a(float f) {
        this.f4813a = f;
    }

    public final CameraPosition m7464a(CameraPosition cameraPosition) {
        float min;
        float max;
        if (this.f4815c != null) {
            min = Math.min(21.0f, this.f4815c.m7221a(cameraPosition.m7460c()));
            max = Math.max(dm.DEFAULT_BACKOFF_MULT, this.f4815c.m7222c());
        } else {
            min = 21.0f;
            max = dm.DEFAULT_BACKOFF_MULT;
        }
        min = Math.max(Math.min(cameraPosition.m7457a(), min), max);
        float f = this.f4813a;
        max = cameraPosition.m7457a();
        max = max >= 16.0f ? 75.0f : max > 14.0f ? (((max - 14.0f) * 30.0f) / dm.DEFAULT_BACKOFF_MULT) + 45.0f : max > 10.0f ? (((max - 10.0f) * 15.0f) / 4.0f) + 30.0f : 30.0f;
        float max2 = Math.max(Math.min(cameraPosition.m7461d(), Math.min(f, max)), 0.0f);
        Point c = cameraPosition.m7460c();
        if (this.f4814b != null) {
            c.m5947a(c, min, ((float) this.f4814b.getDisplayMetrics().heightPixels) / this.f4814b.getDisplayMetrics().density);
        } else {
            c.m5963h(c);
        }
        return new CameraPosition(c, min, max2, cameraPosition.m7462e(), cameraPosition.m7463f());
    }
}
