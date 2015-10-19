package com.google.android.m4b.maps.p060x;

import android.view.animation.Interpolator;

/* renamed from: com.google.android.m4b.maps.x.i */
public abstract class ObjectAnimation<V> extends StateAnimation {
    protected V f8026a;
    protected V f8027b;
    protected V f8028c;
    protected boolean f8029d;

    protected abstract void m11572a(long j);

    protected abstract void m11573a(V v);

    protected abstract void m11577b(V v);

    protected abstract void m11578c(V v);

    public ObjectAnimation(Interpolator interpolator) {
        this.f8029d = false;
        setInterpolator(interpolator);
    }

    public final void m11574a(V v, V v2) {
        if (v != null && v2 != null) {
            m11573a((Object) v);
            m11577b((Object) v2);
            m11578c(v);
            this.f8029d = true;
        }
    }

    public final void m11579d(V v) {
        if (v != null) {
            if (this.f8029d) {
                m11573a(this.f8028c);
                m11577b((Object) v);
                return;
            }
            m11574a(v, v);
        }
    }

    public boolean isInitialized() {
        return this.f8029d;
    }

    public final V m11571a() {
        return this.f8027b;
    }

    public final V m11575b() {
        return this.f8028c;
    }

    public final void m11576b(long j) {
        m11572a(j);
    }
}
