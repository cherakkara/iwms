package com.google.android.m4b.maps.bd;

import java.lang.reflect.Array;

/* renamed from: com.google.android.m4b.maps.bd.f */
public class Entity {
    volatile Transform3D f5483a;
    protected boolean f5484b;
    private final EntityState[][] f5485c;
    private final VertexData[] f5486d;
    private Transform3D[] f5487e;
    private float[][] f5488f;
    private byte f5489g;
    private EntityRenderer f5490h;

    /* renamed from: com.google.android.m4b.maps.bd.f.a */
    public enum Entity {
        NOT_LIVE(false, false),
        NOT_LIVE_WITH_NEW_CONTEXT(false, true),
        LIVE(true, false),
        LIVE_WITH_NEW_CONTEXT(true, true);
        
        public final boolean f5481d;
        public final boolean f5482e;

        private Entity(boolean z, boolean z2) {
            this.f5481d = z;
            this.f5482e = z2;
        }
    }

    public Entity() {
        this.f5484b = false;
        this.f5489g = (byte) 0;
        this.f5486d = new VertexData[5];
        this.f5485c = (EntityState[][]) Array.newInstance(EntityState.class, new int[]{5, EntityState.f4923a});
        this.f5483a = new Transform3D();
        this.f5487e = new Transform3D[5];
        this.f5488f = new float[5][];
    }

    final boolean m8224a(EntityRenderer entityRenderer, Entity entity) {
        if (entity.f5481d == this.f5484b && !entity.f5482e) {
            return false;
        }
        this.f5490h = entityRenderer;
        VertexData[] vertexDataArr = this.f5486d;
        for (int i = 0; i < 5; i++) {
            VertexData vertexData = vertexDataArr[i];
            if (vertexData != null) {
                if (!entity.f5482e) {
                    if (entity.f5481d) {
                        vertexData.m8255b();
                    } else {
                        vertexData.m8253a();
                    }
                }
                vertexData.m8254a(entityRenderer, entity);
            }
        }
        EntityRenderer.m8233c();
        for (EntityState[] entityStateArr : this.f5485c) {
            for (EntityState entityState : r3[r2]) {
                if (entityState != null) {
                    if (!entity.f5482e) {
                        if (entity.f5481d) {
                            entityState.m7595i();
                        } else {
                            entityState.m7596j();
                        }
                    }
                    entityState.m7594a(entityRenderer, entity);
                }
            }
        }
        EntityRenderer.m8233c();
        this.f5484b = entity.f5481d;
        return true;
    }

    public final byte m8223a() {
        return this.f5489g;
    }
}
