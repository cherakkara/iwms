package com.google.android.m4b.maps.al;

import com.google.android.m4b.maps.an.bh;
import com.google.android.m4b.maps.an.bi;
import com.google.android.m4b.maps.ap.IndoorBuildingStore;
import com.google.android.m4b.maps.aq.IndoorBuildingCallback;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.android.m4b.maps.be.IndoorLevelInterface;
import com.google.android.m4b.maps.be.IndoorStateListener;
import com.google.android.m4b.maps.be.cf;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.google.p025a.p028c.ac;
import com.google.p025a.p028c.ai;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.google.p025a.p028c.bk;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: com.google.android.m4b.maps.al.i */
public final class IndoorState implements IndoorBuildingCallback {
    private static IndoorState f3315c;
    private static final IndoorLevelReference f3316q;
    private final Map<IndoorState, Object> f3317a;
    private final Map<IndoorStateListener, Object> f3318b;
    private final LRUCache<FeatureId, IndoorLevelReference> f3319d;
    private final LRUCache<FeatureId, IndoorLevelReference> f3320e;
    private FeatureId f3321f;
    private IndoorLevelReference f3322g;
    private bh f3323h;
    private final Set<FeatureId> f3324i;
    private final List<cf> f3325j;
    private final Set<FeatureId> f3326k;
    private final Object f3327l;
    private final IndoorBuildingStore f3328m;
    private final Map<Integer, IndoorElevation> f3329n;
    private final Map<FeatureId, IndoorElevation> f3330o;
    private final BuildingBoundProvider f3331p;
    private volatile IndoorLevelReference f3332r;
    private volatile IndoorLevelReference f3333s;

    /* renamed from: com.google.android.m4b.maps.al.i.1 */
    class IndoorState implements IndoorBuildingCallback {
        private /* synthetic */ IndoorState f3314a;

        IndoorState(IndoorState indoorState) {
            this.f3314a = indoorState;
        }

        public final void m5330a(FeatureId featureId, int i, bh bhVar) {
            if (i != 2 && i == 0) {
                this.f3314a.m5342e(bhVar);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.al.i.a */
    public interface IndoorState {
        void m5331a(IndoorState indoorState);

        void m5332a(cf cfVar);

        void m5333c();
    }

    static {
        f3316q = new IndoorLevelReference(new FeatureId(0, 0), 0);
    }

    public static IndoorState m5335a(IndoorBuildingStore indoorBuildingStore) {
        if (f3315c == null) {
            f3315c = new IndoorState(indoorBuildingStore);
        }
        return f3315c;
    }

    public static IndoorState m5334a() {
        return f3315c;
    }

    private IndoorState(IndoorBuildingStore indoorBuildingStore) {
        this.f3317a = Collections.synchronizedMap(new WeakHashMap());
        this.f3318b = Collections.synchronizedMap(new WeakHashMap());
        this.f3324i = bk.m2870a();
        this.f3325j = ar.m2515a();
        this.f3326k = bk.m2870a();
        this.f3327l = new Object();
        this.f3329n = au.m2807a();
        this.f3330o = Collections.synchronizedMap(new HashMap());
        this.f3319d = new LRUCache(100);
        this.f3320e = new LRUCache(100);
        this.f3328m = indoorBuildingStore;
        this.f3331p = new CompositeBuildingBoundProvider();
    }

    public final void m5348a(IndoorState indoorState) {
        this.f3317a.put(indoorState, null);
    }

    public final void m5358b(IndoorState indoorState) {
        this.f3317a.remove(indoorState);
    }

    public final void m5350a(IndoorStateListener indoorStateListener) {
        this.f3318b.put(indoorStateListener, null);
    }

    public final void m5351a(FeatureId featureId, int i, bh bhVar) {
        IndoorLevelReference indoorLevelReference = null;
        if (i != 2 && i == 0) {
            Object obj;
            synchronized (this.f3327l) {
                if (this.f3322g != null && this.f3322g.m11306a().equals(featureId)) {
                    indoorLevelReference = this.f3322g;
                    this.f3322g = null;
                }
            }
            if (indoorLevelReference != null) {
                bi a = bhVar.m5791a(indoorLevelReference);
                if (a != null) {
                    for (FeatureId featureId2 : a.m5810c()) {
                        if (m5339a(featureId2, a.m5808a())) {
                            m5338a(featureId2, new IndoorState(this));
                        }
                    }
                }
            }
            m5336a(bhVar);
            synchronized (this.f3327l) {
                if (featureId.equals(this.f3321f)) {
                    if (this.f3323h == null || !bhVar.m5792a().equals(this.f3323h.m5792a())) {
                        if (!bhVar.m5795b().isEmpty()) {
                            this.f3323h = bhVar;
                            int i2 = 1;
                        } else if (this.f3323h != null) {
                            this.f3323h = null;
                            obj = 1;
                        }
                        this.f3321f = null;
                    }
                    obj = null;
                    this.f3321f = null;
                } else {
                    obj = null;
                }
            }
            if (obj != null) {
                m5343i();
            }
            synchronized (this.f3327l) {
                if (this.f3324i.contains(featureId)) {
                    this.f3324i.remove(featureId);
                    this.f3325j.add(bhVar);
                    obj = 1;
                } else {
                    obj = null;
                }
            }
            if (obj != null) {
                m5344j();
            }
        }
    }

    private void m5338a(FeatureId featureId, IndoorBuildingCallback indoorBuildingCallback) {
        if (!this.f3328m.m6269b(featureId)) {
            this.f3328m.m6265a(featureId, indoorBuildingCallback);
        }
    }

    public final void m5352a(IndoorLevelReference indoorLevelReference) {
        synchronized (this.f3327l) {
            if (indoorLevelReference.equals(this.f3322g) || this.f3319d.m6240d().contains(indoorLevelReference)) {
                return;
            }
            this.f3322g = indoorLevelReference;
            m5338a(indoorLevelReference.m11306a(), (IndoorBuildingCallback) this);
        }
    }

    public final void m5349a(cf cfVar) {
        if (cfVar != null) {
            m5339a(cfVar.m5783a(), f3316q);
            m5342e(cfVar);
        }
    }

    public final IndoorLevelInterface m5355b(cf cfVar) {
        IndoorLevelReference a = m5347a(cfVar.m5783a());
        if (a != null) {
            return cfVar.m5786c(a);
        }
        return null;
    }

    public static int m5340c(cf cfVar) {
        return cfVar.m5787d();
    }

    public static boolean m5341d(cf cfVar) {
        return cfVar.m5788e();
    }

    public final void m5353a(IndoorLevelReference indoorLevelReference, IndoorLevelReference indoorLevelReference2) {
        this.f3332r = indoorLevelReference;
        this.f3333s = indoorLevelReference2;
        m5345k();
    }

    public final void m5357b() {
        this.f3332r = null;
        this.f3333s = null;
        m5345k();
    }

    public final boolean m5359b(IndoorLevelReference indoorLevelReference, IndoorLevelReference indoorLevelReference2) {
        return indoorLevelReference != null && indoorLevelReference2 != null && indoorLevelReference.equals(this.f3332r) && indoorLevelReference2.equals(this.f3333s);
    }

    public final IndoorLevelReference m5347a(FeatureId featureId) {
        IndoorLevelReference indoorLevelReference;
        synchronized (this.f3319d) {
            indoorLevelReference = (IndoorLevelReference) this.f3319d.m6235b((Object) featureId);
        }
        if (indoorLevelReference == null) {
            bh a = this.f3328m.m6262a(featureId);
            if (a != null) {
                indoorLevelReference = m5336a(a);
            } else {
                m5338a(featureId, (IndoorBuildingCallback) this);
            }
        }
        return indoorLevelReference == f3316q ? null : indoorLevelReference;
    }

    public final IndoorLevelReference m5356b(FeatureId featureId) {
        IndoorLevelReference indoorLevelReference;
        synchronized (this.f3319d) {
            indoorLevelReference = (IndoorLevelReference) this.f3320e.m6235b((Object) featureId);
            if (indoorLevelReference == f3316q) {
                indoorLevelReference = null;
            }
        }
        return indoorLevelReference;
    }

    public final void m5361c(FeatureId featureId) {
        if (featureId == null) {
            Object obj = null;
            synchronized (this.f3327l) {
                if (this.f3323h != null) {
                    this.f3321f = null;
                    this.f3323h = null;
                    obj = 1;
                }
            }
            if (obj != null) {
                m5343i();
                return;
            }
            return;
        }
        synchronized (this.f3327l) {
            if (featureId.equals(this.f3321f) || (this.f3323h != null && this.f3323h.m5792a().equals(featureId))) {
                return;
            }
            this.f3321f = featureId;
            m5338a(featureId, (IndoorBuildingCallback) this);
        }
    }

    public final bh m5360c() {
        bh bhVar;
        synchronized (this.f3327l) {
            bhVar = this.f3323h;
        }
        return bhVar;
    }

    private void m5343i() {
        synchronized (this.f3318b) {
            for (IndoorState a : this.f3317a.keySet()) {
                a.m5331a(this);
            }
            for (IndoorStateListener a2 : this.f3318b.keySet()) {
                a2.m9378a();
            }
        }
    }

    public final void m5354a(Set<FeatureId> set) {
        synchronized (this.f3327l) {
            if (set == null) {
                set = ai.m2296g();
            }
            if (set.equals(this.f3326k)) {
                return;
            }
            this.f3326k.clear();
            this.f3326k.addAll(set);
            this.f3324i.clear();
            this.f3324i.addAll(set);
            this.f3325j.clear();
            for (FeatureId featureId : this.f3326k) {
                bh a = this.f3328m.m6262a(featureId);
                if (a != null) {
                    this.f3325j.add(a);
                    this.f3324i.remove(featureId);
                } else {
                    m5338a(featureId, (IndoorBuildingCallback) this);
                }
            }
            m5344j();
        }
    }

    public final boolean m5363d() {
        boolean z;
        synchronized (this.f3327l) {
            z = !this.f3325j.isEmpty();
        }
        return z;
    }

    public final List<cf> m5364e() {
        List a;
        synchronized (this.f3327l) {
            a = ac.m2340a(this.f3325j);
        }
        return a;
    }

    public final Set<IndoorLevelInterface> m5365f() {
        List<cf> e = m5364e();
        Set<IndoorLevelInterface> a = bk.m2870a();
        for (cf b : e) {
            IndoorLevelInterface b2 = m5355b(b);
            if (b2 != null) {
                a.add(b2);
            }
        }
        return a;
    }

    private void m5344j() {
        m5345k();
        synchronized (this.f3318b) {
            for (IndoorState c : this.f3317a.keySet()) {
                c.m5333c();
            }
            Iterator it = this.f3318b.keySet().iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    private IndoorLevelReference m5336a(bh bhVar) {
        IndoorLevelReference indoorLevelReference;
        Object obj = null;
        synchronized (this.f3319d) {
            indoorLevelReference = (IndoorLevelReference) this.f3319d.m6235b(bhVar.m5792a());
            if (indoorLevelReference == null) {
                bi c = bhVar.m5796c();
                indoorLevelReference = c == null ? f3316q : c.m5808a();
                this.f3319d.m6239c(bhVar.m5792a(), indoorLevelReference);
                if (indoorLevelReference != f3316q) {
                    obj = 1;
                }
            }
        }
        if (obj != null) {
            m5342e(bhVar);
        }
        return indoorLevelReference;
    }

    private void m5342e(cf cfVar) {
        m5345k();
        synchronized (this.f3318b) {
            for (IndoorState a : this.f3317a.keySet()) {
                a.m5332a(cfVar);
            }
            for (IndoorStateListener a2 : this.f3318b.keySet()) {
                a2.m9379a(cfVar);
            }
        }
    }

    private boolean m5339a(FeatureId featureId, IndoorLevelReference indoorLevelReference) {
        synchronized (this.f3319d) {
            IndoorLevelReference indoorLevelReference2 = (IndoorLevelReference) this.f3319d.m6235b((Object) featureId);
            if (indoorLevelReference.equals(indoorLevelReference2)) {
                return false;
            }
            this.f3319d.m6239c(featureId, indoorLevelReference);
            if (indoorLevelReference2 != null) {
                synchronized (this.f3319d) {
                    this.f3320e.m6239c(featureId, indoorLevelReference2);
                    bi c = this.f3328m.m6270c(indoorLevelReference2.m11306a());
                    if (c == null) {
                    } else {
                        List g;
                        if (indoorLevelReference == f3316q) {
                            g = ac.m2345g();
                        } else {
                            bi c2 = this.f3328m.m6270c(indoorLevelReference.m11306a());
                            if (c2 == null) {
                            } else {
                                g = c2.m5810c();
                            }
                        }
                        for (Object obj : c.m5810c()) {
                            if (!(obj.equals(featureId) || r1.contains(obj))) {
                                this.f3320e.m6239c(obj, this.f3319d.m6235b(obj));
                                this.f3319d.m6239c(obj, f3316q);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

    public final IndoorElevation m5346a(FeatureId featureId, boolean z, boolean z2, boolean z3) {
        synchronized (this.f3330o) {
            IndoorElevation indoorElevation;
            IndoorElevation indoorElevation2 = (IndoorElevation) this.f3330o.get(featureId);
            if (!z2 || indoorElevation2 == null || indoorElevation2.m5310f().size() <= 1) {
                indoorElevation = null;
            } else {
                indoorElevation = indoorElevation2;
                indoorElevation2 = null;
            }
            if (indoorElevation2 != null) {
                return indoorElevation2;
            }
            bh a = this.f3328m.m6262a(featureId);
            if (a == null) {
                m5338a(featureId, null);
                return null;
            }
            IndoorLevelInterface a2 = a.m5790a(featureId);
            if (a2 == null) {
                return null;
            }
            IndoorElevation indoorElevation3 = new IndoorElevation(a2);
            if (z3) {
                this.f3330o.put(featureId, indoorElevation3);
                if (indoorElevation != null) {
                    indoorElevation = indoorElevation.m5300a(featureId);
                    for (FeatureId put : indoorElevation.m5310f()) {
                        this.f3330o.put(put, indoorElevation);
                    }
                }
            }
            return indoorElevation3;
        }
    }

    public final IndoorElevation m5362d(FeatureId featureId) {
        return m5346a(featureId, true, false, false);
    }

    public final Set<IndoorElevation> m5366g() {
        Set a;
        synchronized (this.f3330o) {
            a = ai.m2291a(this.f3330o.values());
        }
        return a;
    }

    private void m5345k() {
        synchronized (this.f3330o) {
            this.f3329n.clear();
            this.f3330o.clear();
            for (IndoorLevelInterface indoorLevelInterface : m5365f()) {
                IndoorLevelReference a = indoorLevelInterface.m5802a();
                Object obj = (a == null || !(a.equals(this.f3332r) || a.equals(this.f3333s))) ? null : 1;
                if (obj == null) {
                    obj = (IndoorElevation) this.f3329n.get(Integer.valueOf(indoorLevelInterface.m5806f()));
                    if (obj == null) {
                        obj = new IndoorElevation(indoorLevelInterface);
                        this.f3329n.put(Integer.valueOf(indoorLevelInterface.m5806f()), obj);
                    } else {
                        obj.m5305a(indoorLevelInterface);
                    }
                    this.f3330o.put(indoorLevelInterface.m5803b(), obj);
                }
            }
        }
    }

    public final BuildingBoundProvider m5367h() {
        return this.f3331p;
    }
}
