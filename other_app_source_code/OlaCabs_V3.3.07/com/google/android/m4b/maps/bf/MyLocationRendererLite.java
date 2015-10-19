package com.google.android.m4b.maps.bf;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.location.Location;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.be.ag.MyLocationLayerImpl;
import com.google.android.m4b.maps.be.am.ProjectionDelegate;
import com.google.android.m4b.maps.be.bw;
import com.google.android.m4b.maps.model.LatLng;
import com.google.p025a.p026a.Preconditions;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.bf.g */
public final class MyLocationRendererLite implements MyLocationLayerImpl {
    private final MapRendererViewLite f6164a;
    private final Resources f6165b;
    private boolean f6166c;
    private Paint f6167d;
    private Location f6168e;
    private Bitmap f6169f;
    private Bitmap f6170g;

    public MyLocationRendererLite(MapRendererViewLite mapRendererViewLite) {
        this.f6166c = false;
        this.f6167d = new Paint();
        this.f6164a = (MapRendererViewLite) Preconditions.m1817a((Object) mapRendererViewLite);
        this.f6165b = mapRendererViewLite.getResources();
    }

    public final void m9663a() {
        if (!this.f6166c) {
            this.f6164a.invalidate();
        }
        this.f6166c = true;
    }

    public final void m9666b() {
        if (this.f6166c) {
            this.f6164a.invalidate();
        }
        this.f6166c = false;
    }

    public final void m9665a(Location location) {
        if (location != this.f6168e) {
            this.f6164a.invalidate();
        }
        this.f6168e = location;
    }

    public final void m9664a(Canvas canvas, ProjectionDelegate projectionDelegate) {
        if (this.f6166c && this.f6168e != null) {
            Object obj;
            LatLng latLng = new LatLng(this.f6168e.getLatitude(), this.f6168e.getLongitude());
            Point a = projectionDelegate.m8550a(latLng);
            if (this.f6168e.hasAccuracy()) {
                float f = (float) (a.y - projectionDelegate.m8550a(new LatLng(bw.m9061a((double) this.f6168e.getAccuracy()) + latLng.f7554a, latLng.f7555b)).y);
                this.f6167d.setStyle(Style.STROKE);
                this.f6167d.setStrokeWidth(dm.DEFAULT_BACKOFF_MULT);
                this.f6167d.setColor(this.f6165b.getColor(R.accuracy_circle_line_color));
                canvas.drawCircle((float) a.x, (float) a.y, f, this.f6167d);
                this.f6167d.setStyle(Style.FILL);
                this.f6167d.setColor(this.f6165b.getColor(R.accuracy_circle_fill_color));
                canvas.drawCircle((float) a.x, (float) a.y, f, this.f6167d);
                this.f6167d.reset();
            }
            Matrix matrix = new Matrix();
            if (this.f6168e.hasBearing()) {
                matrix.setRotate(this.f6168e.getBearing());
                if (this.f6170g == null) {
                    this.f6170g = BitmapFactoryInstrumentation.decodeResource(this.f6165b, R.chevron);
                }
                obj = this.f6170g;
            } else {
                if (this.f6169f == null) {
                    this.f6169f = BitmapFactoryInstrumentation.decodeResource(this.f6165b, R.blue_dot);
                }
                obj = this.f6169f;
            }
            Preconditions.m1817a(obj);
            matrix.preTranslate((float) ((-obj.getWidth()) / 2), (float) ((-obj.getHeight()) / 2));
            float dimension = this.f6165b.getDimension(R.vm_mylocation_dot_size);
            matrix.postScale(dimension / ((float) obj.getWidth()), dimension / ((float) obj.getHeight()));
            matrix.postTranslate((float) a.x, (float) a.y);
            canvas.drawBitmap(obj, matrix, this.f6167d);
        }
    }
}
