package com.google.android.m4b.maps.bd;

import android.opengl.GLES20;
import com.google.android.m4b.maps.bd.Entity.Entity;
import com.google.p025a.p028c.ar;
import com.olacabs.customer.p076d.br;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.bd.n */
public abstract class RenderTarget {
    private Object f5509a;
    private int f5510b;
    private int f5511c;
    private volatile boolean f5512d;
    private boolean f5513e;
    private float[] f5514f;
    private final List<Camera3D> f5515g;

    public RenderTarget() {
        this.f5509a = new Object();
        this.f5510b = 0;
        this.f5511c = 0;
        this.f5512d = true;
        this.f5513e = false;
        this.f5514f = new float[4];
        this.f5515g = ar.m2515a();
        if (this.f5513e) {
            BehaviorManagerImpl.m8219b();
        }
        this.f5514f[0] = 0.0f;
        this.f5514f[1] = 0.0f;
        this.f5514f[2] = 0.0f;
        this.f5514f[3] = br.DEFAULT_BACKOFF_MULT;
    }

    public final int m8242b() {
        return this.f5510b;
    }

    public final int m8244c() {
        return this.f5511c;
    }

    final boolean m8241a(Entity entity) {
        if (entity.f5481d == this.f5513e && !entity.f5482e) {
            return false;
        }
        this.f5513e = entity.f5481d;
        return true;
    }

    void m8238a() {
        if (this.f5512d) {
            synchronized (this.f5509a) {
                int i = this.f5510b;
                i = this.f5511c;
                this.f5512d = false;
            }
        }
        GLES20.glClearColor(this.f5514f[0], this.f5514f[1], this.f5514f[2], this.f5514f[3]);
        GLES20.glClearStencil(0);
        GLES20.glClear(17664);
        GLES20.glEnable(2929);
        GLES20.glEnable(2884);
    }

    protected final void m8239a(int i, int i2) {
        synchronized (this.f5509a) {
            this.f5510b = i;
            this.f5511c = i2;
            this.f5512d = true;
        }
        synchronized (this.f5515g) {
            if (!this.f5515g.isEmpty()) {
                for (Camera3D a : this.f5515g) {
                    a.m7402a(this.f5510b, this.f5511c);
                }
            }
        }
    }

    final void m8240a(Camera3D camera3D) {
        synchronized (this.f5515g) {
            this.f5515g.add(camera3D);
        }
    }

    final void m8243b(Camera3D camera3D) {
        synchronized (this.f5515g) {
            this.f5515g.remove(camera3D);
        }
    }
}
