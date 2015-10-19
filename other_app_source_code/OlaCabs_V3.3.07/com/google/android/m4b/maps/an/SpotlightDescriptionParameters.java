package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.p025a.p026a.Objects;
import com.newrelic.agent.android.instrumentation.Trace;

/* renamed from: com.google.android.m4b.maps.an.q */
public final class SpotlightDescriptionParameters implements af {
    private final ProtoBuf f3693a;

    public final /* synthetic */ int compareTo(Object obj) {
        af afVar = (af) obj;
        return afVar == null ? 1 : toString().compareTo(afVar.toString());
    }

    public SpotlightDescriptionParameters(ProtoBuf protoBuf) {
        this.f3693a = protoBuf;
    }

    public final TileParameters m6071a() {
        return TileParameters.SPOTLIGHT_DIFFTILE;
    }

    public final boolean m6073a(af afVar) {
        return equals(afVar);
    }

    public final String toString() {
        if (this.f3693a == null) {
            return Trace.NULL;
        }
        return this.f3693a.toString().substring(0, 20);
    }

    public final int hashCode() {
        return (this.f3693a == null ? Trace.NULL : this.f3693a.toString()).hashCode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            if (this.f3693a != null) {
                return false;
            }
            return true;
        } else if (!(obj instanceof SpotlightDescriptionParameters)) {
            return false;
        } else {
            return Objects.m1811a(this.f3693a.toString(), ((SpotlightDescriptionParameters) obj).f3693a.toString());
        }
    }

    public final boolean m6074a(ai aiVar) {
        return aiVar == ai.f3444v && this.f3693a != null;
    }

    public final void m6072a(ProtoBuf protoBuf) {
        protoBuf.m10196b(27, this.f3693a);
    }
}
