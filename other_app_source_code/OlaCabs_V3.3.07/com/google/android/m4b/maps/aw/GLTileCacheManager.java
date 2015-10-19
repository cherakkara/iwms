package com.google.android.m4b.maps.aw;

import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.Pair;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.au.Bitmask;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ba.GLTile;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p054p.MathUtil;
import com.google.p025a.p028c.ar;
import com.newrelic.agent.android.api.v1.Defaults;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/* renamed from: com.google.android.m4b.maps.aw.a */
public final class GLTileCacheManager {
    protected static final ac f4755a;
    private static GLTileCacheManager f4756b;
    private final Clock f4757c;
    private final Map<Pair<Long, GLTileCacheManager>, GLTileCacheManager> f4758d;
    private final int f4759e;
    private final int f4760f;
    private int f4761g;
    private int f4762h;
    private final List<Long> f4763i;

    /* renamed from: com.google.android.m4b.maps.aw.a.a */
    static class GLTileCacheManager {
        public GLTile f4742a;
        public int f4743b;
        public int f4744c;
        public long f4745d;

        public GLTileCacheManager(GLTile gLTile, int i, int i2, long j) {
            this.f4742a = null;
            this.f4743b = 0;
            this.f4744c = 0;
            this.f4745d = j;
        }

        public GLTileCacheManager(GLTileCacheManager gLTileCacheManager) {
            this.f4742a = gLTileCacheManager.f4742a;
            this.f4743b = gLTileCacheManager.f4743b;
            this.f4744c = gLTileCacheManager.f4744c;
            this.f4745d = gLTileCacheManager.f4745d;
        }
    }

    /* renamed from: com.google.android.m4b.maps.aw.a.b */
    static class GLTileCacheManager implements Comparable<GLTileCacheManager> {
        public Pair<Long, GLTileCacheManager> f4746a;
        public ac f4747b;
        private GLTileCacheManager f4748c;

        public final /* synthetic */ int compareTo(Object obj) {
            return Long.valueOf(this.f4748c.f4745d).compareTo(Long.valueOf(((GLTileCacheManager) obj).f4748c.f4745d));
        }

        public GLTileCacheManager(Pair<Long, GLTileCacheManager> pair, ac acVar, GLTileCacheManager gLTileCacheManager) {
            this.f4746a = pair;
            this.f4747b = acVar;
            this.f4748c = gLTileCacheManager;
        }
    }

    /* renamed from: com.google.android.m4b.maps.aw.a.c */
    class GLTileCacheManager extends LRUCache<ac, GLTileCacheManager> {
        private final Long f4749a;
        private final List<GLTileCacheManager> f4750b;
        private /* synthetic */ GLTileCacheManager f4751c;

        protected final /* synthetic */ void m7373b(Object obj, Object obj2) {
            GLTileCacheManager gLTileCacheManager = (GLTileCacheManager) obj2;
            super.m6236b((ac) obj, gLTileCacheManager);
            this.f4751c.f4761g = this.f4751c.f4761g - gLTileCacheManager.f4743b;
            this.f4751c.f4762h = this.f4751c.f4762h - gLTileCacheManager.f4744c;
            if (gLTileCacheManager.f4742a != null) {
                gLTileCacheManager.f4743b = 0;
                gLTileCacheManager.f4744c = 0;
                m7371a(gLTileCacheManager);
            }
        }

        public GLTileCacheManager(GLTileCacheManager gLTileCacheManager, long j) {
            this.f4751c = gLTileCacheManager;
            super(Integer.MAX_VALUE);
            this.f4750b = new ArrayList();
            this.f4749a = Long.valueOf(j);
        }

        public final void m7374f() {
            List<ac> arrayList = new ArrayList(m6234b());
            LRUCache.LRUCache e = m6241e();
            while (e.hasNext()) {
                LRUCache.LRUCache a = e.m6623a();
                if (a.f4080a == GLTileCacheManager.f4755a) {
                    break;
                }
                GLTileCacheManager gLTileCacheManager = (GLTileCacheManager) a.f4081b;
                GLTile gLTile = gLTileCacheManager.f4742a;
                if (gLTile == null || !gLTile.m7842a()) {
                    arrayList.add(a.f4080a);
                } else {
                    gLTile.m6660b(GLState.m7502b(this.f4749a.longValue()));
                    this.f4751c.f4761g = this.f4751c.f4761g - gLTileCacheManager.f4743b;
                    gLTileCacheManager.f4743b = 0;
                }
            }
            for (ac c : arrayList) {
                m6238c(c);
            }
        }

        public final LRUCache.LRUCache<ac, GLTileCacheManager> m7375g() {
            LRUCache.LRUCache e = m6241e();
            return e.hasNext() ? e.m6623a() : null;
        }

        public final void m7376h() {
            long b = this.f4751c.f4757c.m10152b();
            GLTileCacheManager gLTileCacheManager = (GLTileCacheManager) m6235b((Object) GLTileCacheManager.f4755a);
            if (gLTileCacheManager == null) {
                m6239c(GLTileCacheManager.f4755a, new GLTileCacheManager(null, 0, 0, b));
            } else {
                gLTileCacheManager.f4745d = b;
            }
        }

        public final void m7371a(GLTileCacheManager gLTileCacheManager) {
            this.f4750b.add(gLTileCacheManager);
        }

        public final void m7372a(GLState gLState) {
            for (GLTileCacheManager gLTileCacheManager : this.f4750b) {
                gLTileCacheManager.f4742a.m6661c(gLState);
                this.f4751c.f4761g = this.f4751c.f4761g - gLTileCacheManager.f4743b;
                this.f4751c.f4762h = this.f4751c.f4762h - gLTileCacheManager.f4744c;
            }
            this.f4750b.clear();
        }
    }

    /* renamed from: com.google.android.m4b.maps.aw.a.d */
    public static class GLTileCacheManager implements Comparable<GLTileCacheManager> {
        private final int f4752a;
        private final Bitmask f4753b;
        private final String f4754c;

        public final /* synthetic */ int compareTo(Object obj) {
            GLTileCacheManager gLTileCacheManager = (GLTileCacheManager) obj;
            return this.f4752a != gLTileCacheManager.f4752a ? this.f4752a - gLTileCacheManager.f4752a : this.f4753b.m6610a(gLTileCacheManager.f4753b);
        }

        public GLTileCacheManager(ai aiVar, Set<ai> set) {
            this(aiVar, null, Bitmask.f4072a);
        }

        public GLTileCacheManager(ai aiVar, Set<ai> set, Bitmask bitmask) {
            if (aiVar.f3447A) {
                throw new IllegalArgumentException("Modifier tile types not allowed as base");
            }
            int d = 1 << aiVar.m5513d();
            StringBuilder stringBuilder = new StringBuilder(aiVar.toString());
            if (!(set == null || set.isEmpty())) {
                stringBuilder.append(" with modifiers ");
                Iterator it = set.iterator();
                int i = d;
                while (it.hasNext()) {
                    ai aiVar2 = (ai) it.next();
                    if (aiVar2.f3447A) {
                        i |= 1 << aiVar2.m5513d();
                        stringBuilder.append(aiVar2.toString());
                        if (it.hasNext()) {
                            stringBuilder.append(", ");
                        }
                    } else {
                        throw new IllegalArgumentException("Only modifier tile types allowed");
                    }
                }
                d = i;
            }
            if (bitmask != Bitmask.f4072a) {
                stringBuilder.append(" with mask " + bitmask);
            }
            this.f4752a = d;
            this.f4754c = stringBuilder.toString();
            this.f4753b = bitmask;
        }

        public final boolean equals(Object obj) {
            return (obj instanceof GLTileCacheManager) && ((GLTileCacheManager) obj).f4752a == this.f4752a && ((GLTileCacheManager) obj).f4753b.equals(this.f4753b);
        }

        public final int hashCode() {
            return (this.f4752a * 33) ^ this.f4753b.hashCode();
        }

        public final String toString() {
            return this.f4754c;
        }
    }

    static {
        f4755a = new ac(-1, 0, 0);
    }

    public static synchronized GLTileCacheManager m7380a() {
        GLTileCacheManager gLTileCacheManager;
        synchronized (GLTileCacheManager.class) {
            gLTileCacheManager = f4756b;
        }
        return gLTileCacheManager;
    }

    public static synchronized void m7383a(Clock clock) {
        synchronized (GLTileCacheManager.class) {
            if (f4756b == null) {
                int f = VectorGlobalState.m7290f();
                f4756b = new GLTileCacheManager(clock, ((f * Defaults.RESPONSE_BODY_LIMIT) * Defaults.RESPONSE_BODY_LIMIT) / 2, (((f * Defaults.RESPONSE_BODY_LIMIT) * Defaults.RESPONSE_BODY_LIMIT) * 3) / 16);
            }
        }
    }

    private GLTileCacheManager(Clock clock, int i, int i2) {
        this.f4758d = new HashMap();
        this.f4761g = 0;
        this.f4762h = 0;
        this.f4763i = ar.m2515a();
        this.f4757c = clock;
        this.f4759e = i;
        this.f4760f = i2;
    }

    public final synchronized void m7389a(GLState gLState) {
        long a = GLState.m7500a(gLState);
        this.f4763i.add(Long.valueOf(a));
        List<Pair> arrayList = new ArrayList();
        for (Entry entry : this.f4758d.entrySet()) {
            if (((Long) ((Pair) entry.getKey()).first).longValue() == a) {
                ((GLTileCacheManager) entry.getValue()).m6231a();
                ((GLTileCacheManager) entry.getValue()).m7372a(null);
                arrayList.add(entry.getKey());
            }
        }
        for (Pair remove : arrayList) {
            this.f4758d.remove(remove);
        }
    }

    public final synchronized void m7390a(GLState gLState, GLTileCacheManager gLTileCacheManager) {
        Pair create = Pair.create(Long.valueOf(GLState.m7500a(gLState)), gLTileCacheManager);
        GLTileCacheManager gLTileCacheManager2 = (GLTileCacheManager) this.f4758d.get(create);
        if (gLTileCacheManager2 != null) {
            gLTileCacheManager2.m6231a();
            gLTileCacheManager2.m7372a(gLState);
            this.f4758d.remove(create);
        }
    }

    public final synchronized void m7396b(GLState gLState, GLTileCacheManager gLTileCacheManager) {
        Pair create = Pair.create(Long.valueOf(GLState.m7500a(gLState)), gLTileCacheManager);
        GLTileCacheManager gLTileCacheManager2 = (GLTileCacheManager) this.f4758d.get(create);
        if (gLTileCacheManager2 != null) {
            gLTileCacheManager2.m6238c(f4755a);
            gLTileCacheManager2.m7374f();
            gLTileCacheManager2.m7372a(gLState);
            if (gLTileCacheManager2.m6234b() == 0) {
                this.f4758d.remove(create);
            }
        }
    }

    public final synchronized void m7395b(GLState gLState) {
        if (gLState != null) {
            long a = GLState.m7500a(gLState);
            for (Entry entry : this.f4758d.entrySet()) {
                if (((Long) ((Pair) entry.getKey()).first).longValue() == a) {
                    ((GLTileCacheManager) entry.getValue()).m6238c(f4755a);
                    ((GLTileCacheManager) entry.getValue()).m7372a(gLState);
                }
            }
        }
    }

    public final synchronized void m7399c(GLState gLState, GLTileCacheManager gLTileCacheManager) {
        GLTileCacheManager gLTileCacheManager2 = (GLTileCacheManager) this.f4758d.get(Pair.create(Long.valueOf(GLState.m7500a(gLState)), gLTileCacheManager));
        if (gLTileCacheManager2 != null) {
            gLTileCacheManager2.m7374f();
        }
    }

    public final synchronized void m7391a(GLState gLState, GLTileCacheManager gLTileCacheManager, ac acVar, GLTile gLTile) {
        GLTileCacheManager a;
        long a2 = GLState.m7500a(gLState);
        Pair create = Pair.create(Long.valueOf(a2), gLTileCacheManager);
        GLTileCacheManager gLTileCacheManager2 = (GLTileCacheManager) this.f4758d.get(create);
        if (gLTileCacheManager2 == null) {
            a = m7379a(a2, create);
        } else {
            a = gLTileCacheManager2;
        }
        if (a != null) {
            GLTileCacheManager gLTileCacheManager3 = (GLTileCacheManager) a.m6230a((Object) acVar);
            if (gLTileCacheManager3 != null) {
                if (gLTileCacheManager3.f4742a != null) {
                    a.m7371a(new GLTileCacheManager(gLTileCacheManager3));
                }
                gLTileCacheManager3.f4742a = gLTile;
                gLTileCacheManager3.f4743b = gLTile.m7851f();
                gLTileCacheManager3.f4744c = gLTile.m7852g();
                this.f4761g += gLTileCacheManager3.f4743b;
                this.f4762h = gLTileCacheManager3.f4744c + this.f4762h;
                m7382a(this.f4759e, this.f4760f);
            }
        }
    }

    private GLTileCacheManager m7379a(long j, Pair<Long, GLTileCacheManager> pair) {
        if (this.f4763i.contains(Long.valueOf(j))) {
            return null;
        }
        GLTileCacheManager gLTileCacheManager = new GLTileCacheManager(this, j);
        this.f4758d.put(pair, gLTileCacheManager);
        return gLTileCacheManager;
    }

    private synchronized void m7382a(int i, int i2) {
        if (this.f4761g > i || this.f4762h > i2) {
            SortedSet treeSet = new TreeSet();
            for (Entry entry : this.f4758d.entrySet()) {
                LRUCache.LRUCache g = ((GLTileCacheManager) entry.getValue()).m7375g();
                if (!(g == null || g.f4080a == f4755a)) {
                    treeSet.add(new GLTileCacheManager((Pair) entry.getKey(), (ac) g.f4080a, (GLTileCacheManager) g.f4081b));
                }
            }
            List<Pair> arrayList = new ArrayList();
            while (!treeSet.isEmpty() && (this.f4761g > i || this.f4762h > i2)) {
                GLTileCacheManager gLTileCacheManager = (GLTileCacheManager) treeSet.first();
                GLTileCacheManager gLTileCacheManager2 = (GLTileCacheManager) this.f4758d.get(gLTileCacheManager.f4746a);
                gLTileCacheManager2.m6238c(gLTileCacheManager.f4747b);
                if (gLTileCacheManager2.m6234b() == 0 && gLTileCacheManager2.f4750b.isEmpty()) {
                    arrayList.add(gLTileCacheManager.f4746a);
                }
                treeSet.remove(gLTileCacheManager);
                LRUCache.LRUCache g2 = gLTileCacheManager2.m7375g();
                if (!(g2 == null || g2.f4080a == f4755a)) {
                    treeSet.add(new GLTileCacheManager(gLTileCacheManager.f4746a, (ac) g2.f4080a, (GLTileCacheManager) g2.f4081b));
                }
            }
            for (Pair remove : arrayList) {
                this.f4758d.remove(remove);
            }
        }
    }

    public final synchronized GLTile m7388a(GLState gLState, GLTileCacheManager gLTileCacheManager, ac acVar, boolean z) {
        GLTile gLTile;
        GLTileCacheManager b = m7386b(gLState, gLTileCacheManager, acVar, z);
        if (b != null) {
            gLTile = b.f4742a;
        } else {
            gLTile = null;
        }
        return gLTile;
    }

    private synchronized GLTileCacheManager m7386b(GLState gLState, GLTileCacheManager gLTileCacheManager, ac acVar, boolean z) {
        GLTileCacheManager gLTileCacheManager2;
        long a = GLState.m7500a(gLState);
        Pair create = Pair.create(Long.valueOf(a), gLTileCacheManager);
        GLTileCacheManager gLTileCacheManager3 = (GLTileCacheManager) this.f4758d.get(create);
        if (gLTileCacheManager3 == null) {
            if (z) {
                gLTileCacheManager3 = m7379a(a, create);
                if (gLTileCacheManager3 == null) {
                    gLTileCacheManager2 = null;
                }
            } else {
                gLTileCacheManager2 = null;
            }
        }
        GLTileCacheManager gLTileCacheManager4 = gLTileCacheManager3;
        gLTileCacheManager2 = (GLTileCacheManager) gLTileCacheManager4.m6235b((Object) acVar);
        if (gLTileCacheManager2 != null) {
            gLTileCacheManager2.f4745d = this.f4757c.m10152b();
        } else {
            if (z) {
                gLTileCacheManager4.m6239c(acVar, new GLTileCacheManager(null, 0, 0, this.f4757c.m10152b()));
            }
            gLTileCacheManager2 = null;
        }
        return gLTileCacheManager2;
    }

    public final synchronized void m7392a(GLState gLState, GLTileCacheManager gLTileCacheManager, List<ac> list) {
        GLTileCacheManager gLTileCacheManager2 = (GLTileCacheManager) this.f4758d.get(Pair.create(Long.valueOf(GLState.m7500a(gLState)), gLTileCacheManager));
        if (gLTileCacheManager2 != null) {
            int i = 0;
            for (ac a : list) {
                int i2;
                GLTileCacheManager gLTileCacheManager3 = (GLTileCacheManager) gLTileCacheManager2.m6230a((Object) a);
                if (gLTileCacheManager3 == null || gLTileCacheManager3.f4742a == null || gLTileCacheManager3.f4743b != 0) {
                    i2 = i;
                } else {
                    i2 = gLTileCacheManager3.f4744c + i;
                }
                i = i2;
            }
            m7382a(this.f4759e - i, this.f4760f);
        }
    }

    public final synchronized void m7397b(GLState gLState, GLTileCacheManager gLTileCacheManager, List<ac> list) {
        GLTileCacheManager gLTileCacheManager2 = (GLTileCacheManager) this.f4758d.get(Pair.create(Long.valueOf(GLState.m7500a(gLState)), gLTileCacheManager));
        if (gLTileCacheManager2 != null) {
            for (ac a : list) {
                GLTileCacheManager gLTileCacheManager3 = (GLTileCacheManager) gLTileCacheManager2.m6230a((Object) a);
                if (!(gLTileCacheManager3 == null || gLTileCacheManager3.f4742a == null || gLTileCacheManager3.f4743b != 0)) {
                    gLTileCacheManager3.f4743b = gLTileCacheManager3.f4742a.m7851f();
                    this.f4761g += gLTileCacheManager3.f4743b;
                    int i = gLTileCacheManager3.f4744c;
                    int g = gLTileCacheManager3.f4742a.m7852g();
                    gLTileCacheManager3.f4744c = g;
                    this.f4762h = (this.f4762h - i) + g;
                }
            }
            m7382a(this.f4759e, this.f4760f);
        }
    }

    public final synchronized void m7400d(GLState gLState, GLTileCacheManager gLTileCacheManager) {
        long a = GLState.m7500a(gLState);
        Pair create = Pair.create(Long.valueOf(a), gLTileCacheManager);
        GLTileCacheManager gLTileCacheManager2 = (GLTileCacheManager) this.f4758d.get(create);
        if (gLTileCacheManager2 == null) {
            gLTileCacheManager2 = m7379a(a, create);
        }
        if (gLTileCacheManager2 != null) {
            gLTileCacheManager2.m7376h();
            gLTileCacheManager2.m7372a(gLState);
        }
    }

    public final synchronized void m7393a(boolean z) {
        if (z) {
            m7382a(this.f4761g, 0);
        } else {
            m7382a(this.f4761g, this.f4762h / 2);
        }
    }

    public final void m7394b() {
        m7382a(0, this.f4762h);
    }

    public final synchronized String m7398c() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        for (Entry entry : this.f4758d.entrySet()) {
            int b = ((GLTileCacheManager) entry.getValue()).m6234b();
            if (b != 0) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(" + ");
                }
                stringBuilder.append(b).append(" ").append(entry.getKey());
            }
        }
        if (stringBuilder.length() == 0) {
            stringBuilder.append("no");
        }
        stringBuilder.append(" tiles use ");
        stringBuilder.append(GLTileCacheManager.m7381a(this.f4761g)).append("/").append(GLTileCacheManager.m7381a(this.f4759e)).append("M GL, ").append(GLTileCacheManager.m7381a(this.f4762h)).append("/").append(GLTileCacheManager.m7381a(this.f4760f)).append("M J+N");
        return stringBuilder.toString();
    }

    private static String m7381a(int i) {
        int a = MathUtil.m11079a(i * 10, AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START);
        return (a / 10) + "." + (a % 10);
    }
}
