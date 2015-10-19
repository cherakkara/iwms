package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.google.p025a.p026a.Objects;
import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.an.f */
public final class MyLocationMarker {
    private Point f3635a;
    private float f3636b;
    private int f3637c;
    private Point f3638d;
    private float f3639e;
    private boolean f3640f;
    private IndoorLevelReference f3641g;
    private boolean f3642h;
    private float f3643i;
    private boolean f3644j;
    private float f3645k;

    public MyLocationMarker() {
        m5903l();
    }

    public MyLocationMarker(Point point, float f, int i) {
        m5908a(point, f, i);
    }

    public final Point m5904a() {
        return this.f3635a;
    }

    public final float m5910b() {
        return this.f3636b;
    }

    public final int m5911c() {
        return this.f3637c;
    }

    public final Point m5912d() {
        return (Point) Objects.m1812b(this.f3638d, this.f3635a);
    }

    public final void m5907a(Point point) {
        this.f3638d = point;
    }

    public final boolean m5913e() {
        return this.f3640f;
    }

    public final void m5909a(boolean z) {
        this.f3640f = z;
    }

    public final IndoorLevelReference m5914f() {
        return this.f3641g;
    }

    public final boolean m5915g() {
        return this.f3642h;
    }

    public final float m5916h() {
        return this.f3643i;
    }

    public final boolean m5917i() {
        return this.f3644j;
    }

    public final float m5918j() {
        return this.f3645k;
    }

    public final void m5905a(float f) {
        this.f3645k = Math.min(br.DEFAULT_BACKOFF_MULT, Math.max(0.0f, f));
    }

    public final void m5906a(MyLocationMarker myLocationMarker) {
        if (myLocationMarker == null) {
            m5903l();
            return;
        }
        m5908a(myLocationMarker.f3635a, myLocationMarker.f3636b, myLocationMarker.f3637c);
        this.f3638d = myLocationMarker.f3638d == null ? null : new Point(myLocationMarker.f3638d);
        this.f3639e = myLocationMarker.f3639e;
        this.f3640f = myLocationMarker.f3640f;
        this.f3641g = myLocationMarker.f3641g;
        this.f3642h = myLocationMarker.f3642h;
        this.f3643i = myLocationMarker.f3643i;
        this.f3644j = myLocationMarker.f3644j;
        this.f3645k = myLocationMarker.f3645k;
    }

    public final void m5908a(Point point, float f, int i) {
        this.f3635a = point == null ? null : new Point(point);
        this.f3636b = f;
        this.f3637c = i;
    }

    private void m5903l() {
        this.f3635a = null;
        this.f3636b = 0.0f;
        this.f3637c = -1;
        this.f3638d = null;
        this.f3639e = 0.0f;
        this.f3640f = false;
        this.f3641g = null;
        this.f3642h = false;
        this.f3643i = 0.0f;
        this.f3644j = false;
        this.f3645k = br.DEFAULT_BACKOFF_MULT;
    }

    public final boolean m5919k() {
        return this.f3635a != null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MyLocationMarker myLocationMarker = (MyLocationMarker) obj;
        if (Objects.m1811a(this.f3635a, myLocationMarker.f3635a) && this.f3636b == myLocationMarker.f3636b && this.f3637c == myLocationMarker.f3637c && Objects.m1811a(this.f3638d, myLocationMarker.f3638d) && this.f3639e == myLocationMarker.f3639e && this.f3640f == myLocationMarker.f3640f && Objects.m1811a(this.f3641g, myLocationMarker.f3641g) && this.f3642h == myLocationMarker.f3642h && this.f3643i == myLocationMarker.f3643i && this.f3644j == myLocationMarker.f3644j && this.f3645k == myLocationMarker.f3645k) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.m1808a(this.f3635a, Float.valueOf(this.f3636b), Integer.valueOf(this.f3637c), Float.valueOf(this.f3639e), Boolean.valueOf(this.f3640f), this.f3641g, Boolean.valueOf(this.f3642h), Float.valueOf(this.f3643i), Boolean.valueOf(this.f3644j), Float.valueOf(this.f3645k));
    }

    public final String toString() {
        Objects.Objects a = Objects.m1809a((Object) this);
        a.m1806a("@", this.f3635a.m5966j());
        a.m1804a("Accuracy", this.f3637c);
        if (this.f3638d != null) {
            a.m1806a("Accuracy point", this.f3638d.m5966j());
        }
        a.m1803a("Accuracy emphasis", this.f3639e);
        a.m1807a("Use bearing", this.f3640f);
        if (this.f3640f) {
            a.m1803a("Bearing", this.f3636b);
        }
        a.m1803a("Brightness", this.f3645k);
        a.m1803a("Height", this.f3643i);
        a.m1806a("Level", this.f3641g);
        a.m1807a("Stale", this.f3644j);
        return a.toString();
    }
}
