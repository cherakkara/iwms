package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.p025a.p026a.Objects;
import com.newrelic.agent.android.instrumentation.Trace;

/* renamed from: com.google.android.m4b.maps.an.r */
public final class SpotlightParameters implements af {
    private final String f3695a;

    /* renamed from: com.google.android.m4b.maps.an.r.a */
    public static class SpotlightParameters {
        private String f3694a;

        public final SpotlightParameters m6075a(String str) {
            this.f3694a = str;
            return this;
        }

        public final SpotlightParameters m6076a() {
            return new SpotlightParameters(this.f3694a);
        }
    }

    public final /* synthetic */ int compareTo(Object obj) {
        af afVar = (af) obj;
        return afVar == null ? 1 : toString().compareTo(afVar.toString());
    }

    protected SpotlightParameters(String str) {
        this.f3695a = str;
    }

    public final TileParameters m6077a() {
        return TileParameters.SPOTLIGHT;
    }

    public final boolean m6079a(af afVar) {
        return equals(afVar);
    }

    public final String toString() {
        return this.f3695a == null ? Trace.NULL : this.f3695a;
    }

    public final int hashCode() {
        return (this.f3695a == null ? 0 : this.f3695a.hashCode()) + 31;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            if (this.f3695a != null) {
                return false;
            }
            return true;
        } else if (!(obj instanceof SpotlightParameters)) {
            return false;
        } else {
            return Objects.m1811a(this.f3695a, ((SpotlightParameters) obj).f3695a);
        }
    }

    public final boolean m6080a(ai aiVar) {
        return aiVar == ai.f3440r && this.f3695a != null;
    }

    public final void m6078a(ProtoBuf protoBuf) {
        protoBuf.m10197b(10, this.f3695a);
    }
}
