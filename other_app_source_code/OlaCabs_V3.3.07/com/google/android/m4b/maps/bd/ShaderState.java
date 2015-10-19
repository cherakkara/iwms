package com.google.android.m4b.maps.bd;

import com.google.android.m4b.maps.bd.Entity.Entity;

/* renamed from: com.google.android.m4b.maps.bd.q */
public abstract class ShaderState extends EntityState {
    private ShaderProgram f4927d;
    private Class<? extends ShaderProgram> f4928e;

    public final boolean m7597a(EntityRenderer entityRenderer, Entity entity) {
        boolean a = super.m7594a(entityRenderer, entity);
        if (this.f4927d == null) {
            this.f4927d = entityRenderer.m8236d().m8231a(this.f4928e);
        }
        if (a) {
            this.f4927d.m8252a(entity);
        }
        return a;
    }
}
