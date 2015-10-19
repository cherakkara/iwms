package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.p025a.p026a.Objects;
import com.newrelic.agent.android.instrumentation.Trace;

/* compiled from: HighlightParameters */
public final class be implements af {
    private final String f3565a;

    /* renamed from: com.google.android.m4b.maps.an.be.a */
    public static class HighlightParameters {
        private String f3564a;

        public final HighlightParameters m5762a(String str) {
            this.f3564a = str;
            return this;
        }

        public final be m5763a() {
            return new be(this.f3564a);
        }
    }

    public final /* synthetic */ int compareTo(Object obj) {
        af afVar = (af) obj;
        return afVar == null ? 1 : toString().compareTo(afVar.toString());
    }

    protected be(String str) {
        this.f3565a = str;
    }

    public final TileParameters m5764a() {
        return TileParameters.HIGHLIGHT;
    }

    public final boolean m5766a(af afVar) {
        return equals(afVar);
    }

    public final String toString() {
        return this.f3565a == null ? Trace.NULL : this.f3565a;
    }

    public final int hashCode() {
        return Objects.m1808a(this.f3565a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            if (this.f3565a != null) {
                return false;
            }
            return true;
        } else if (obj instanceof be) {
            return Objects.m1811a(this.f3565a, ((be) obj).f3565a);
        } else {
            return false;
        }
    }

    public final boolean m5767a(ai aiVar) {
        return (aiVar != ai.f3441s || this.f3565a == null || "0x0:0x0".equals(this.f3565a)) ? false : true;
    }

    public final void m5765a(ProtoBuf protoBuf) {
        protoBuf.m10197b(9, this.f3565a);
    }
}
