package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.google.p025a.p026a.Objects;

/* compiled from: IndoorParameters */
public final class bj implements af {
    private final IndoorLevelReference f3589a;

    /* renamed from: com.google.android.m4b.maps.an.bj.a */
    public static class IndoorParameters {
        private IndoorLevelReference f3588a;

        public final IndoorParameters m5814a(IndoorLevelReference indoorLevelReference) {
            this.f3588a = indoorLevelReference;
            return this;
        }

        public final bj m5815a() {
            return new bj(this.f3588a);
        }
    }

    public final /* synthetic */ int compareTo(Object obj) {
        af afVar = (af) obj;
        return afVar == null ? 1 : toString().compareTo(afVar.toString());
    }

    protected bj(IndoorLevelReference indoorLevelReference) {
        this.f3589a = indoorLevelReference;
    }

    public final TileParameters m5817a() {
        return TileParameters.INDOOR;
    }

    public final FeatureId m5821b() {
        return this.f3589a.m11306a();
    }

    public final boolean m5819a(af afVar) {
        return true;
    }

    public final String toString() {
        return this.f3589a.m11306a().toString();
    }

    public final int hashCode() {
        return (this.f3589a == null ? 0 : this.f3589a.m11306a().hashCode()) + 31;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            if (this.f3589a != null) {
                return false;
            }
            return true;
        } else if (obj instanceof bj) {
            return Objects.m1811a(this.f3589a.m11306a(), ((bj) obj).f3589a.m11306a());
        } else {
            return false;
        }
    }

    public final boolean m5820a(ai aiVar) {
        return aiVar == ai.f3436n && this.f3589a != null;
    }

    public final void m5818a(ProtoBuf protoBuf) {
        protoBuf.m10197b(6, this.f3589a.m11306a().toString());
    }

    public final IndoorLevelReference m5822c() {
        return this.f3589a;
    }

    public static bj m5816a(IndoorLevelReference indoorLevelReference) {
        return new IndoorParameters().m5814a(indoorLevelReference).m5815a();
    }
}
