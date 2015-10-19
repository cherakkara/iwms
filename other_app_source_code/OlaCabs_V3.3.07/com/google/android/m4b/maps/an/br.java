package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.p025a.p026a.Objects;

/* compiled from: MapsEngineParameters */
public final class br implements af {
    private final ProtoBuf f3625a;

    public final /* synthetic */ int compareTo(Object obj) {
        af afVar = (af) obj;
        return afVar == null ? 1 : toString().compareTo(afVar.toString());
    }

    public br(ProtoBuf protoBuf) {
        this.f3625a = protoBuf;
    }

    public final TileParameters m5875a() {
        return TileParameters.MAPS_ENGINE;
    }

    public final boolean m5877a(af afVar) {
        return equals(afVar);
    }

    public final boolean m5878a(ai aiVar) {
        return aiVar == ai.f3446x || aiVar == ai.f3445w;
    }

    public final void m5876a(ProtoBuf protoBuf) {
        protoBuf.m10196b(29, this.f3625a);
    }

    public final ProtoBuf m5879b() {
        return this.f3625a;
    }

    public final String toString() {
        return this.f3625a.toString();
    }

    public final int hashCode() {
        return Objects.m1808a(this.f3625a.toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof br)) {
            return false;
        }
        return Objects.m1811a(this.f3625a.toString(), ((br) obj).f3625a.toString());
    }
}
