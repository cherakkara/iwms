package com.google.android.m4b.maps.bd;

import com.google.android.m4b.maps.bd.Entity.Entity;

/* renamed from: com.google.android.m4b.maps.bd.t */
public abstract class VertexData {
    private boolean f5527a;
    private int f5528b;

    protected VertexData() {
        this.f5527a = false;
        this.f5528b = 0;
    }

    protected boolean m8254a(EntityRenderer entityRenderer, Entity entity) {
        if (entity.f5481d == this.f5527a && !entity.f5482e) {
            return false;
        }
        if (!entity.f5481d && !entity.f5482e && this.f5528b != 0) {
            return false;
        }
        this.f5527a = entity.f5481d;
        return true;
    }

    final void m8253a() {
        this.f5528b--;
    }

    final void m8255b() {
        this.f5528b++;
    }
}
