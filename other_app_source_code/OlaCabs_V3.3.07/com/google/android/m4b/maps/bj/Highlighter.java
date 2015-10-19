package com.google.android.m4b.maps.bj;

/* renamed from: com.google.android.m4b.maps.bj.p */
public final class Highlighter {
    private boolean f6577a;
    private int f6578b;
    private boolean f6579c;
    private ag f6580d;

    public Highlighter() {
        m9980b();
    }

    public final void m9982a(ag agVar) {
        this.f6580d = agVar;
        m9980b();
    }

    public final boolean m9983a() {
        return this.f6577a;
    }

    public final boolean m9984a(float f, float f2) {
        if (!m9981c()) {
            return false;
        }
        int a = this.f6580d.m9817a((int) f, (int) f2);
        if (a != -1) {
            this.f6577a = true;
            this.f6578b = a;
            this.f6580d.m9829b(this.f6578b);
            this.f6579c = true;
        } else {
            m9980b();
        }
        return this.f6577a;
    }

    public final boolean m9985b(float f, float f2) {
        if (m9981c() && this.f6577a) {
            boolean z = this.f6580d.m9817a((int) f, (int) f2) == this.f6578b;
            if (z != this.f6579c) {
                this.f6579c = z;
                if (z) {
                    this.f6580d.m9829b(this.f6578b);
                } else {
                    this.f6580d.m9829b(-1);
                }
            }
        }
        return this.f6577a;
    }

    public final int m9986c(float f, float f2) {
        if (m9981c() && this.f6577a) {
            if (this.f6579c) {
                this.f6580d.m9829b(-1);
            }
            int a = this.f6580d.m9817a((int) f, (int) f2);
            if (a == this.f6578b) {
                m9980b();
                return a;
            }
        }
        m9980b();
        return -1;
    }

    private void m9980b() {
        this.f6577a = false;
        this.f6578b = -1;
        this.f6579c = false;
    }

    private boolean m9981c() {
        return this.f6580d != null;
    }
}
