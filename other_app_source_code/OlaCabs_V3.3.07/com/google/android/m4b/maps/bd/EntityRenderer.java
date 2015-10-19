package com.google.android.m4b.maps.bd;

import android.opengl.GLES20;
import com.google.android.m4b.maps.bd.Entity.Entity;
import com.sothree.slidinguppanel.p086a.R.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.bd.g */
public final class EntityRenderer {
    private static String f5496d;
    private final EntityRenderer f5497a;
    private int f5498b;
    private int f5499c;

    /* renamed from: com.google.android.m4b.maps.bd.g.a */
    public static class EntityRenderer {
        public int f5491a;
        private Object f5492b;
        private EntityRenderer f5493c;

        public final Entity m8226a() {
            return (Entity) this.f5492b;
        }

        public final Camera3D m8227b() {
            return (Camera3D) this.f5492b;
        }

        public final Behavior m8228c() {
            return (Behavior) this.f5492b;
        }
    }

    /* renamed from: com.google.android.m4b.maps.bd.g.b */
    public static class EntityRenderer {
        private EntityRenderer f5494a;
        private EntityRenderer f5495b;

        private synchronized EntityRenderer m8229a() {
            EntityRenderer entityRenderer;
            entityRenderer = this.f5494a;
            if (entityRenderer != null) {
                this.f5494a = entityRenderer.f5493c;
                if (this.f5494a == null) {
                    this.f5495b = null;
                }
            }
            return entityRenderer;
        }
    }

    /* renamed from: com.google.android.m4b.maps.bd.g.c */
    public class EntityRenderer implements ShaderManager {
        public final ShaderProgram m8232a(Class<? extends ShaderProgram> cls) {
            Map map = null;
            WeakReference weakReference = (WeakReference) map.get(cls);
            if (weakReference != null) {
                ShaderProgram shaderProgram = (ShaderProgram) weakReference.get();
            } else {
                Object obj = map;
            }
            if (shaderProgram != null) {
                return shaderProgram;
            }
            try {
                shaderProgram = (ShaderProgram) cls.newInstance();
                map.put(cls, new WeakReference(shaderProgram));
                return shaderProgram;
            } catch (InstantiationException e) {
                return map;
            } catch (IllegalAccessException e2) {
                return map;
            }
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        arrayList = new ArrayList();
        f5496d = null;
    }

    final void m8234a() {
        Set<RenderTarget> set = null;
        while (true) {
            EntityRenderer a = set.m8229a();
            if (a != null) {
                try {
                    byte a2;
                    int i;
                    BehaviorManagerImpl behaviorManagerImpl;
                    switch (a.f5491a) {
                        case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                            a.m8226a().m8224a(this, Entity.LIVE);
                            a2 = a.m8226a().m8223a();
                            for (i = 0; i < 5; i++) {
                                if (((1 << i) & a2) != 0) {
                                    a.m8226a();
                                }
                            }
                            break;
                        case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                            a.m8226a().m8224a(this, Entity.LIVE);
                            a2 = a.m8226a().m8223a();
                            for (i = 0; i < 5; i++) {
                                if (((1 << i) & a2) != 0) {
                                    a.m8226a();
                                }
                            }
                            break;
                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                            behaviorManagerImpl = null;
                            behaviorManagerImpl.m8221a(a.m8228c());
                            break;
                        case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                            behaviorManagerImpl = null;
                            behaviorManagerImpl.m8222b(a.m8228c());
                            break;
                        case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                            a.m8227b().m7403a(this, Entity.LIVE);
                            Set set2 = null;
                            set2.add(a.m8227b().m7405x());
                            set2 = null;
                            set2.add(a.m8227b());
                            a2 = a.m8227b().m7407z();
                            for (i = 0; i < 5; i++) {
                                if (((1 << i) & a2) != 0) {
                                    RenderBin renderBin = null;
                                    renderBin.m8248a(a.m8227b());
                                }
                            }
                            break;
                        case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                            throw new UnsupportedOperationException("Remove camera not implemented");
                        default:
                            break;
                    }
                } catch (Exception e) {
                }
            } else {
                set.m8220a();
                for (RenderTarget a3 : set) {
                    a3.m8238a();
                }
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((Camera3D) it.next()).m7406y();
                }
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    it2.next();
                }
                it2 = set.iterator();
                while (it2.hasNext()) {
                    it2.next();
                }
                this.f5499c++;
                return;
            }
        }
    }

    final void m8235b() {
        Map map = null;
        EntityRenderer entityRenderer = this.f5497a;
        for (WeakReference weakReference : map.values()) {
            ShaderProgram shaderProgram = (ShaderProgram) weakReference.get();
            if (shaderProgram != null) {
                shaderProgram.m8252a(Entity.NOT_LIVE_WITH_NEW_CONTEXT);
            }
        }
        for (int i = 0; i < EntityState.f4923a; i++) {
            map[i] = map;
        }
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(3415, iArr, 0);
        this.f5498b = iArr[0];
        Math.pow(2.0d, (double) (this.f5498b - 1));
        if (f5496d == null) {
            f5496d = GLES20.glGetString(7939);
        }
    }

    public static void m8233c() {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Thread.dumpStack();
            System.exit(1);
            throw new GLException(glGetError);
        }
    }

    public final ShaderManager m8236d() {
        return this.f5497a;
    }
}
