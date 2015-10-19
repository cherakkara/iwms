package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.p025a.p026a.Objects;
import com.newrelic.agent.android.instrumentation.Trace;

/* compiled from: AlternatePaintfeParameters */
public final class au implements af {
    private final String f3526a;

    /* renamed from: com.google.android.m4b.maps.an.au.a */
    public static class AlternatePaintfeParameters {
        private String f3525a;

        public final AlternatePaintfeParameters m5688a(String str) {
            this.f3525a = str;
            return this;
        }

        public final au m5689a() {
            return new au(this.f3525a);
        }
    }

    public final /* synthetic */ int compareTo(Object obj) {
        af afVar = (af) obj;
        return afVar == null ? 1 : toString().compareTo(afVar.toString());
    }

    protected au(String str) {
        this.f3526a = str;
    }

    public final TileParameters m5690a() {
        return TileParameters.ALTERNATE_PAINTFE;
    }

    public final boolean m5692a(af afVar) {
        return equals(afVar);
    }

    public final String toString() {
        return this.f3526a == null ? Trace.NULL : this.f3526a;
    }

    public final int hashCode() {
        return Objects.m1808a(this.f3526a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            if (this.f3526a != null) {
                return false;
            }
            return true;
        } else if (obj instanceof au) {
            return Objects.m1811a(this.f3526a, ((au) obj).f3526a);
        } else {
            return false;
        }
    }

    public final boolean m5693a(ai aiVar) {
        return this.f3526a != null;
    }

    public final void m5691a(ProtoBuf protoBuf) {
        protoBuf.m10197b(13, this.f3526a);
    }
}
