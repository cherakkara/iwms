package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.ap.VectorModifier;
import com.google.android.m4b.maps.an.aq.VectorTile;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.android.m4b.maps.p058v.Util;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.bk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.an.e */
public final class MutableVectorTile extends aq {
    private final List<bc> f3629a;
    private List<bc> f3630b;
    private Set<FeatureId> f3631c;
    private List<String> f3632d;
    private List<String> f3633e;
    private long f3634f;

    /* renamed from: com.google.android.m4b.maps.an.e.a */
    class MutableVectorTile implements VectorTile {
        private int f3626a;
        private int f3627b;
        private /* synthetic */ MutableVectorTile f3628c;

        private MutableVectorTile(MutableVectorTile mutableVectorTile) {
            this.f3628c = mutableVectorTile;
            this.f3626a = 0;
            this.f3627b = 0;
        }

        public final /* synthetic */ Object next() {
            List a = this.f3628c.f3630b;
            int i = this.f3626a;
            this.f3626a = i + 1;
            return (bc) a.get(i);
        }

        public final boolean hasNext() {
            return this.f3626a < this.f3628c.f3630b.size();
        }

        public final void remove() {
            throw new UnsupportedOperationException("remove() not supported");
        }

        public final bc m5888a() {
            return (bc) this.f3628c.f3630b.get(this.f3626a);
        }

        public final void m5889b() {
            this.f3627b = this.f3626a;
        }

        public final void m5890c() {
            this.f3626a = this.f3627b;
        }
    }

    public static aq m5891a(aq aqVar, aq aqVar2) {
        long k;
        int i = 0;
        long k2 = aqVar.m5644k();
        if (k2 < 0 || (aqVar2.m5644k() >= 0 && aqVar2.m5644k() < k2)) {
            k = aqVar2.m5644k();
        } else {
            k = k2;
        }
        if (aqVar2.m5648o() == 0 && k == aqVar.m5644k()) {
            return aqVar;
        }
        if (aqVar2.m5648o() > 0) {
            aqVar = MutableVectorTile.m5894b(aqVar);
            aqVar.f3631c = new HashSet();
            ArrayList arrayList = new ArrayList();
            ArrayList a = ar.m2515a();
            ArrayList a2 = ar.m2515a();
            for (int i2 = 0; i2 < aqVar2.m5648o(); i2++) {
                ap b = aqVar2.m5634b(i2);
                if (b instanceof VectorModifier) {
                    arrayList.add((VectorModifier) b);
                } else if (b instanceof VectorModifier) {
                    aqVar.f3631c.add(((VectorModifier) b).m5600a());
                } else if (b instanceof VectorModifier) {
                    a.add((VectorModifier) b);
                } else if (b instanceof VectorModifier) {
                    a2.add((VectorModifier) b);
                } else {
                    throw new IllegalArgumentException("Wrong modifier: " + b);
                }
            }
            Iterator it = aqVar.f3630b.iterator();
            while (it.hasNext()) {
                if (aqVar.f3631c.contains(((bc) it.next()).m5547d())) {
                    it.remove();
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                int i3;
                VectorModifier vectorModifier = (VectorModifier) it2.next();
                for (i3 = 0; i3 < vectorModifier.m5595a().m5550k().length; i3++) {
                    int[] k3 = vectorModifier.m5595a().m5550k();
                    k3[i3] = k3[i3] + aqVar.f3632d.size();
                }
                if (!vectorModifier.m5596b() || vectorModifier.m5597c() >= aqVar.f3629a.size()) {
                    if (vectorModifier.m5597c() >= aqVar.f3629a.size()) {
                        Util.m11550a("MutableVectorTile", "Invalid plane index on tile " + aqVar2.m5633b() + " at " + aqVar2.m5630a());
                    }
                    aqVar.f3630b.add(vectorModifier.m5595a());
                } else {
                    i3 = aqVar.f3630b.indexOf((bc) aqVar.f3629a.get(vectorModifier.m5597c()));
                    if (i3 < 0) {
                        aqVar.f3630b.add(vectorModifier.m5595a());
                    } else if (vectorModifier.m5598d()) {
                        aqVar.f3630b.add(i3, vectorModifier.m5595a());
                    } else {
                        aqVar.f3630b.add(i3 + 1, vectorModifier.m5595a());
                    }
                }
            }
            Iterator it3 = a2.iterator();
            while (it3.hasNext()) {
                it3.next();
            }
            it = a.iterator();
            while (it.hasNext()) {
                aqVar.f3630b.add(0, ((VectorModifier) it.next()).m5599a());
            }
            String[] f = aqVar2.m5640f();
            while (i < f.length) {
                if (!aqVar.f3633e.contains(f[i])) {
                    aqVar.f3633e.add(f[i]);
                }
                i++;
            }
            aqVar.f3632d.addAll(Arrays.asList(aqVar2.m5641g()));
            aqVar.f3634f = k;
            return aqVar;
        } else if (!(aqVar instanceof MutableVectorTile)) {
            return new VectorTile().m5603a(aqVar.m5630a()).m5609b(aqVar.m5636c()).m5601a(aqVar.m5638d()).m5607a(aqVar.m5640f()).m5611b(aqVar.m5641g()).m5612c(aqVar.m5646m()).m5606a(aqVar.m5647n()).m5604a(aqVar.m5633b()).m5602a(k).m5610b(aqVar.m5649p()).m5608a();
        } else {
            ((MutableVectorTile) aqVar).f3634f = k;
            return aqVar;
        }
    }

    private static MutableVectorTile m5894b(aq aqVar) {
        return aqVar instanceof MutableVectorTile ? (MutableVectorTile) aqVar : new MutableVectorTile(aqVar);
    }

    private MutableVectorTile(aq aqVar) {
        super(null, null, aqVar.m5630a(), aqVar.m5636c(), aqVar.m5645l(), aqVar.m5638d(), null, null, aqVar.m5646m(), null, aqVar.m5633b(), null, -1, aqVar.m5649p());
        this.f3631c = bk.m2870a();
        this.f3634f = -1;
        this.f3629a = Collections.unmodifiableList(Arrays.asList(aqVar.m5647n()));
        this.f3630b = new ArrayList();
        VectorTile i = aqVar.m5643i();
        while (i.hasNext()) {
            this.f3630b.add(i.next());
        }
        this.f3632d = new ArrayList();
        if (aqVar.m5641g() != null) {
            this.f3632d.addAll(Arrays.asList(aqVar.m5641g()));
        }
        this.f3633e = new ArrayList();
        if (aqVar.m5640f() != null) {
            this.f3633e.addAll(Arrays.asList(aqVar.m5640f()));
        }
        this.f3634f = aqVar.m5644k();
    }

    public static aq m5893b(aq aqVar, aq aqVar2) {
        aq b = MutableVectorTile.m5894b(aqVar);
        for (bc bcVar : aqVar2.m5647n()) {
            if (bcVar.m5546b() == 6) {
                for (int i = 0; i < b.f3630b.size(); i++) {
                    if (((bc) b.f3630b.get(i)).m5546b() == 6) {
                        b.f3630b.set(i, bcVar);
                        break;
                    }
                }
                b.f3630b.add(bcVar);
            } else {
                b.f3630b.add(bcVar);
            }
        }
        return MutableVectorTile.m5891a(b, aqVar2);
    }

    public final String[] m5897f() {
        return (String[]) this.f3633e.toArray(new String[this.f3633e.size()]);
    }

    public final String[] m5898g() {
        return (String[]) this.f3632d.toArray(new String[this.f3632d.size()]);
    }

    public final int m5899h() {
        return this.f3630b.size();
    }

    public final bc m5895a(int i) {
        return (bc) this.f3630b.get(i);
    }

    public final VectorTile m5900i() {
        return new MutableVectorTile();
    }

    public final Set<FeatureId> m5901j() {
        return Collections.unmodifiableSet(this.f3631c);
    }

    public final long m5902k() {
        return this.f3634f;
    }

    public final boolean m5896a(Clock clock) {
        return this.f3634f >= 0 && clock.m10152b() > this.f3634f;
    }
}
