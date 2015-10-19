package com.google.android.m4b.maps.bf;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.be.ad;
import com.google.android.m4b.maps.be.ad.MarkerImpl;
import com.google.android.m4b.maps.be.am.ProjectionDelegate;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.v1.Defaults;

/* renamed from: com.google.android.m4b.maps.bf.f */
public final class MarkerRendererLite implements MarkerImpl {
    private final ad f6159a;
    private final OverlayRendererManagerLite f6160b;
    private Paint f6161c;
    private Point f6162d;
    private Point f6163e;

    public MarkerRendererLite(ad adVar, OverlayRendererManagerLite overlayRendererManagerLite) {
        this.f6161c = new Paint();
        this.f6162d = new Point(-2, -2);
        this.f6163e = new Point(-1, -1);
        this.f6159a = adVar;
        this.f6160b = overlayRendererManagerLite;
    }

    public final void m9653a() {
        this.f6160b.m9675a(this);
    }

    public final void m9654a(int i) {
        if ((i & 1) != 0) {
            this.f6160b.m9679b();
        }
        if ((i & 2) != 0) {
            this.f6160b.m9679b();
        }
        if ((i & 4) != 0) {
            this.f6160b.m9679b();
        }
        if ((i & 64) != 0) {
            if (!this.f6159a.m8389y()) {
                this.f6160b.m9685d(this);
            }
            this.f6160b.m9679b();
        }
        if ((i & Defaults.RESPONSE_BODY_LIMIT) != 0) {
            this.f6160b.m9679b();
        }
        if ((i & AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY) != 0) {
            this.f6160b.m9679b();
        }
        if ((i & AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) != 0) {
            this.f6160b.m9679b();
        }
        if ((i & AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH) != 0) {
            this.f6160b.m9679b();
        }
    }

    public final void m9656b() {
        this.f6160b.m9680b(this);
    }

    public final void m9659e() {
    }

    public final boolean m9660f() {
        return this.f6160b.m9687e(this);
    }

    public final void m9655a(Canvas canvas, ProjectionDelegate projectionDelegate) {
        Bitmap f = this.f6159a.m8370f();
        int h = (int) (this.f6159a.m8372h() * ((float) f.getWidth()));
        int j = (int) (this.f6159a.m8374j() * ((float) f.getHeight()));
        Point a = projectionDelegate.m8550a(this.f6159a.m8369e());
        this.f6162d = new Point(a.x - h, a.y - j);
        this.f6163e = new Point((f.getWidth() - h) + a.x, (f.getHeight() - j) + a.y);
        if (f != null && this.f6159a.m8388x()) {
            this.f6161c.setAlpha((int) (255.0f * this.f6159a.m8346E()));
            canvas.drawBitmap(f, (float) this.f6162d.x, (float) this.f6162d.y, this.f6161c);
        }
    }

    public final ad m9661g() {
        return this.f6159a;
    }

    public final Rect m9662i() {
        return new Rect(this.f6162d.x, this.f6162d.y, this.f6163e.x, this.f6163e.y);
    }

    public final void m9657c() {
        this.f6160b.m9683c(this);
    }

    public final void m9658d() {
        this.f6160b.m9685d(this);
    }
}
