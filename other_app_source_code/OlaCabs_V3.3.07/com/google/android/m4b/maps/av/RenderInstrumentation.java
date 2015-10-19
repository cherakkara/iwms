package com.google.android.m4b.maps.av;

/* renamed from: com.google.android.m4b.maps.av.g */
public abstract class RenderInstrumentation {
    private Renderer f4542a;
    private boolean f4543b;

    protected RenderInstrumentation() {
    }

    protected void m7204d() {
    }

    protected boolean m7200a(boolean z) {
        return true;
    }

    final void m7202b(boolean z) {
        if (m7200a(z)) {
            this.f4542a.m7241a(null);
            synchronized (this) {
                notifyAll();
            }
        }
    }

    public final void m7199a(Renderer renderer) {
        this.f4542a = renderer;
    }

    final boolean m7205e() {
        this.f4543b = false;
        return false;
    }

    public void m7203c() {
    }

    public void m7201b() {
    }
}
