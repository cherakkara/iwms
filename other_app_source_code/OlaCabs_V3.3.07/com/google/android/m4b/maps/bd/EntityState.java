package com.google.android.m4b.maps.bd;

import com.google.android.m4b.maps.bd.Entity.Entity;

/* renamed from: com.google.android.m4b.maps.bd.h */
public abstract class EntityState {
    public static final int f4923a;
    protected boolean f4924b;
    final EntityState f4925c;
    private int f4926d;

    /* renamed from: com.google.android.m4b.maps.bd.h.a */
    public enum EntityState {
        PICK(0),
        TEXTURE0(1),
        TEXTURE1(2),
        SHADER(3),
        STENCIL(4),
        POLYGON(5),
        BLEND(6);
        
        private final int f5508h;

        public final int m8237a() {
            return this.f5508h;
        }

        private EntityState(int i) {
            this.f5508h = i;
        }
    }

    static {
        f4923a = EntityState.values().length;
        EntityState.TEXTURE0.m8237a();
    }

    protected EntityState(EntityState entityState) {
        this.f4924b = false;
        this.f4926d = 0;
        this.f4925c = entityState;
    }

    boolean m7594a(EntityRenderer entityRenderer, Entity entity) {
        if (entity.f5481d == this.f4924b && !entity.f5482e) {
            return false;
        }
        if (!entity.f5481d && !entity.f5482e && this.f4926d != 0) {
            return false;
        }
        this.f4924b = entity.f5481d;
        boolean z = this.f4924b;
        return true;
    }

    final void m7595i() {
        this.f4926d++;
    }

    final void m7596j() {
        this.f4926d--;
    }
}
