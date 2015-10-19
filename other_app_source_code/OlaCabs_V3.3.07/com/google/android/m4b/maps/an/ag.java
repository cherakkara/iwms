package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.p025a.p028c.au;
import com.newrelic.agent.android.instrumentation.Trace;
import java.util.Map;
import java.util.Set;

/* compiled from: TileParametersCollection */
public final class ag implements Comparable<ag> {
    private final Map<TileParameters, af> f3403a;

    public final /* synthetic */ int compareTo(Object obj) {
        return m5469a((ag) obj);
    }

    public ag() {
        this.f3403a = au.m2807a();
    }

    public ag(ag agVar) {
        this.f3403a = au.m2809a(agVar.f3403a);
    }

    public final af m5470a(TileParameters tileParameters) {
        return (af) this.f3403a.get(tileParameters);
    }

    public static af m5468a(ag agVar, TileParameters tileParameters) {
        if (agVar == null) {
            return null;
        }
        return agVar.m5470a(tileParameters);
    }

    public final Set<TileParameters> m5472a() {
        return this.f3403a.keySet();
    }

    public final void m5473a(af afVar) {
        this.f3403a.put(afVar.m5464a(), afVar);
    }

    public final int hashCode() {
        int hashCode = (this.f3403a == null || this.f3403a.isEmpty()) ? 0 : this.f3403a.hashCode();
        return hashCode + 31;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return this.f3403a.isEmpty();
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.f3403a.equals(((ag) obj).f3403a);
    }

    public final String toString() {
        return this.f3403a.isEmpty() ? Trace.NULL : this.f3403a.toString();
    }

    public final int m5469a(ag agVar) {
        for (TileParameters tileParameters : TileParameters.values()) {
            af a = m5470a(tileParameters);
            af a2 = agVar.m5470a(tileParameters);
            if (a != null) {
                int compareTo = a.compareTo(a2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (a2 != null) {
                return -1;
            }
        }
        return 0;
    }

    public final void m5474a(ai aiVar, ProtoBuf protoBuf) {
        for (af afVar : this.f3403a.values()) {
            if (afVar.m5467a(aiVar)) {
                afVar.m5465a(protoBuf);
            }
        }
    }

    public final boolean m5475b() {
        return this.f3403a.isEmpty();
    }

    public final ag m5471a(ai aiVar) {
        ag agVar = new ag();
        for (af afVar : this.f3403a.values()) {
            if (afVar.m5467a(aiVar)) {
                agVar.m5473a(afVar);
            }
        }
        return agVar;
    }
}
