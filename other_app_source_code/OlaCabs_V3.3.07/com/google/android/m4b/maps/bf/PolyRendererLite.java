package com.google.android.m4b.maps.bf;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Point;
import com.google.android.m4b.maps.be.aj;
import com.google.android.m4b.maps.be.aj.PolyModel;
import com.google.android.m4b.maps.model.LatLng;
import com.olacabs.customer.p076d.dm;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.bf.j */
public final class PolyRendererLite implements PolyModel, RenderableLite {
    private final aj f6183a;
    private final boolean f6184b;
    private OverlayRendererManagerLite f6185c;
    private Paint f6186d;

    public PolyRendererLite(aj ajVar, boolean z, OverlayRendererManagerLite overlayRendererManagerLite) {
        this.f6186d = new Paint();
        this.f6183a = ajVar;
        this.f6185c = overlayRendererManagerLite;
        this.f6184b = z;
        this.f6185c.m9676a((RenderableLite) this);
    }

    public final void m9692a(int i) {
        this.f6185c.m9679b();
    }

    public final void m9691a() {
        this.f6185c.m9681b((RenderableLite) this);
    }

    private static void m9690a(Path path, List<LatLng> list, ProjectionLite projectionLite) {
        float b = (float) projectionLite.m9700b();
        int ceil = (int) Math.ceil((double) ((((float) projectionLite.f6194f) - b) / (dm.DEFAULT_BACKOFF_MULT * b)));
        for (int i = -ceil; i <= ceil; i++) {
            Point a = projectionLite.m9697a((LatLng) list.get(0));
            path.moveTo(((float) a.x) + (((float) i) * b), (float) a.y);
            int i2 = 1;
            Point point = a;
            int i3 = 0;
            Point point2 = a;
            while (i2 < list.size()) {
                int i4;
                point2 = projectionLite.m9697a((LatLng) list.get(i2));
                if (((float) (point2.x - point.x)) > b / dm.DEFAULT_BACKOFF_MULT) {
                    i4 = i3 - 1;
                } else if (((float) (point2.x - point.x)) < (-b) / dm.DEFAULT_BACKOFF_MULT) {
                    i4 = i3 + 1;
                } else {
                    i4 = i3;
                }
                path.lineTo(((float) point2.x) + (((float) (i + i4)) * b), (float) point2.y);
                i2++;
                i3 = i4;
                point = point2;
            }
            if (((float) a.x) == ((float) point2.x) + (((float) i3) * b) && a.y == point2.y) {
                path.close();
            }
        }
    }

    public final void m9693a(Canvas canvas, ProjectionLite projectionLite) {
        if (this.f6183a.m8450h()) {
            Path path = new Path();
            PolyRendererLite.m9690a(path, this.f6183a.m8445b(), projectionLite);
            for (List a : this.f6183a.m8446d()) {
                PolyRendererLite.m9690a(path, a, projectionLite);
            }
            this.f6186d.setAntiAlias(true);
            path.setFillType(FillType.EVEN_ODD);
            if (this.f6184b && this.f6183a.m8449g() != 0) {
                this.f6186d.setColor(this.f6183a.m8449g());
                this.f6186d.setStyle(Style.FILL);
                canvas.drawPath(path, this.f6186d);
            }
            if (this.f6183a.m8448f() != 0) {
                this.f6186d.setColor(this.f6183a.m8448f());
                this.f6186d.setStrokeWidth(this.f6183a.m8447e());
                this.f6186d.setStyle(Style.STROKE);
                canvas.drawPath(path, this.f6186d);
            }
        }
    }

    public final float m9694b() {
        return this.f6183a.m8451i();
    }
}
