package com.google.android.m4b.maps.p061y;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.ba.GLMarker;
import com.google.android.m4b.maps.be.ad;
import com.google.android.m4b.maps.be.ad.MarkerImpl;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.p057t.GeoPoint;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.v1.Defaults;

/* renamed from: com.google.android.m4b.maps.y.e */
public final class GLMarkerRenderer implements MarkerImpl {
    private GLMarker f8078a;
    private final ad f8079b;
    private final GLMarkerManager f8080c;

    public GLMarkerRenderer(ad adVar, GLMarkerManager gLMarkerManager) {
        this.f8079b = adVar;
        this.f8080c = gLMarkerManager;
    }

    public final void m11668a() {
        Bitmap f = this.f8079b.m8370f();
        this.f8078a = m11667a(this.f8079b.m8350a(), this.f8079b.m8369e(), f, this.f8079b.m8372h(), this.f8079b.m8374j(), this.f8079b.m8376l(), this.f8079b.m8378n(), this.f8079b.m8384t(), this.f8079b.m8389y(), this.f8079b.m8342A(), this.f8079b.m8344C(), this.f8079b.m8346E());
        this.f8080c.m11656a(this);
        this.f8080c.m11653a();
    }

    public final void m11669a(int i) {
        if ((i & 1) != 0) {
            this.f8078a.m8049a(ConversionUtils.m11640b(this.f8079b.m8368d()));
            this.f8080c.m11657b();
            this.f8080c.m11653a();
        }
        if ((i & 2) != 0) {
            String o = this.f8079b.m8379o();
            this.f8079b.m8381q();
            this.f8080c.m11655a(this.f8078a);
            this.f8078a = m11667a(this.f8079b.m8350a(), this.f8079b.m8369e(), this.f8079b.m8370f(), this.f8079b.m8371g(), this.f8079b.m8373i(), this.f8079b.m8375k(), this.f8079b.m8377m(), this.f8079b.m8384t(), this.f8079b.m8389y(), this.f8079b.m8342A(), this.f8079b.m8344C(), this.f8079b.m8346E());
            this.f8078a.m8053a(o);
            GLMarker gLMarker = this.f8078a;
            this.f8080c.m11656a(this);
            this.f8080c.m11653a();
        }
        if ((i & 4) != 0) {
            this.f8078a.m8048a(Math.round(this.f8079b.m8372h() * ((float) this.f8078a.m8071h().getWidth())), Math.round(this.f8079b.m8374j() * ((float) this.f8078a.m8071h().getHeight())));
            this.f8080c.m11653a();
        }
        if ((i & 8) != 0) {
            this.f8078a.m8062b(!this.f8079b.m8342A());
            this.f8080c.m11653a();
        }
        if ((i & 16) != 0) {
            this.f8078a.m8047a(this.f8079b.m8344C());
            this.f8080c.m11653a();
        }
        if ((i & 64) != 0) {
            boolean y = this.f8079b.m8389y();
            if (!y) {
                this.f8080c.m11663c(this);
            }
            this.f8078a.m8065c(y);
            this.f8080c.m11653a();
            this.f8080c.m11657b();
        }
        if ((i & Defaults.RESPONSE_BODY_LIMIT) != 0) {
            this.f8078a.m8059b(this.f8079b.m8346E());
            this.f8080c.m11653a();
        }
        if ((i & AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY) != 0) {
            this.f8078a.m8060b(Math.round(this.f8079b.m8376l() * ((float) this.f8078a.m8071h().getWidth())), Math.round(this.f8079b.m8378n() * ((float) this.f8078a.m8071h().getHeight())));
            this.f8080c.m11653a();
        }
        if ((i & AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) != 0) {
            this.f8078a.m8053a(this.f8079b.m8380p());
            this.f8080c.m11653a();
        }
        if ((i & 32) != 0) {
            this.f8078a.m8054a(this.f8079b.m8384t());
        }
        if ((i & AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH) != 0) {
            gLMarker = this.f8078a;
            this.f8079b.m8382r();
            this.f8080c.m11653a();
        }
    }

    public final void m11670b() {
        this.f8080c.m11655a(this.f8078a);
        this.f8080c.m11653a();
    }

    private GLMarker m11667a(String str, LatLng latLng, Bitmap bitmap, float f, float f2, float f3, float f4, boolean z, boolean z2, boolean z3, float f5, float f6) {
        GLMarker gLMarker = new GLMarker(ConversionUtils.m11640b(latLng), bitmap, null, Math.round(((float) bitmap.getWidth()) * f), Math.round(((float) bitmap.getHeight()) * f2), null, null, false);
        gLMarker.m8061b(str);
        gLMarker.m8054a(z);
        gLMarker.m8065c(z2);
        gLMarker.m8062b(!z3);
        gLMarker.m8047a(f5);
        gLMarker.m8060b(Math.round(((float) bitmap.getWidth()) * f3), Math.round(((float) bitmap.getHeight()) * f4));
        gLMarker.m8059b(f6);
        gLMarker.m8052a(this.f8079b);
        return gLMarker;
    }

    public final void m11671c() {
        this.f8080c.m11659b(this);
        this.f8080c.m11653a();
    }

    public final void m11672d() {
        this.f8080c.m11663c(this);
        this.f8080c.m11653a();
    }

    public final void m11673e() {
        ad adVar = this.f8079b;
        GeoPoint e = this.f8078a.m8068e();
        adVar.m8363b(new LatLng(((double) e.m11304a()) * 1.0E-6d, ((double) e.m11305b()) * 1.0E-6d));
    }

    public final boolean m11674f() {
        return this.f8080c.m11660b(this.f8078a);
    }

    public final GLMarker m11675g() {
        return this.f8078a;
    }

    public final ad m11676h() {
        return this.f8079b;
    }

    public final Rect m11677i() {
        return this.f8078a.m8079p();
    }
}
