package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.p025a.p026a.Objects;

/* compiled from: TileState */
public class ah {
    private final ag f3404a;

    public ah() {
        this.f3404a = new ag();
    }

    public boolean m5479a(ag agVar, TileParameters tileParameters) {
        af a = ag.m5468a(agVar, tileParameters);
        af a2 = ag.m5468a(this.f3404a, tileParameters);
        if (a2 == null) {
            return a == null || a.m5466a(a2);
        } else {
            return a2.m5466a(a);
        }
    }

    public boolean m5478a(ag agVar) {
        if (agVar == null) {
            agVar = new ag();
        }
        for (TileParameters a : agVar.m5472a()) {
            if (!m5479a(agVar, a)) {
                return false;
            }
        }
        return true;
    }

    public ag m5476a() {
        ag agVar;
        synchronized (this.f3404a) {
            agVar = new ag(this.f3404a);
        }
        return agVar;
    }

    public boolean m5477a(af afVar) {
        boolean z = false;
        if (afVar != null) {
            synchronized (this.f3404a) {
                if (Objects.m1811a(this.f3404a.m5470a(afVar.m5464a()), afVar)) {
                } else {
                    this.f3404a.m5473a(afVar);
                    z = true;
                }
            }
        }
        return z;
    }

    public long m5480b() {
        long hashCode;
        synchronized (this.f3404a) {
            hashCode = (long) this.f3404a.hashCode();
        }
        return hashCode;
    }
}
