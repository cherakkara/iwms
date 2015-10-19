package com.google.android.m4b.maps.aq;

import com.google.android.m4b.maps.an.bh;
import com.google.android.m4b.maps.bx.Indoorbuilding;
import com.google.android.m4b.maps.p040u.BaseDataRequest;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.p025a.p028c.ar;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.aq.c */
public final class IndoorBuildingRequest extends BaseDataRequest {
    private final FeatureId f3849a;
    private final List<IndoorBuildingCallback> f3850b;
    private ProtoBuf f3851c;
    private boolean f3852d;

    public IndoorBuildingRequest(FeatureId featureId) {
        this.f3849a = featureId;
        this.f3850b = ar.m2524b();
    }

    public final int m6328i() {
        return 118;
    }

    public final void m6326a(DataOutput dataOutput) {
        ProtoBuf protoBuf = new ProtoBuf(Indoorbuilding.f7072a);
        protoBuf.m10197b(1, this.f3849a.toString());
        ProtoBufUtil.m10228a(dataOutput, protoBuf);
    }

    public final boolean m6327a(DataInput dataInput) {
        this.f3851c = ProtoBufUtil.m10226a(Indoorbuilding.f7073b, dataInput);
        return true;
    }

    public final FeatureId m6329j() {
        return this.f3849a;
    }

    public final void m6325a(IndoorBuildingCallback indoorBuildingCallback) {
        if (indoorBuildingCallback != null) {
            this.f3850b.add(indoorBuildingCallback);
        }
    }

    public final void m6330k() {
        this.f3852d = true;
    }

    public final boolean m6331l() {
        return this.f3852d;
    }

    private int m6323o() {
        if (this.f3851c == null) {
            return 1;
        }
        switch (this.f3851c.m10204d(1)) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return 0;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return 2;
            default:
                return 1;
        }
    }

    public final ProtoBuf m6332m() {
        if (this.f3851c == null) {
            return null;
        }
        return this.f3851c.m10211g(2);
    }

    public final boolean m6333n() {
        return m6323o() == 2;
    }

    public final void m6324a(bh bhVar) {
        int i;
        int o = m6323o();
        if (bhVar == null && o == 0) {
            i = 1;
        } else {
            i = o;
        }
        for (IndoorBuildingCallback a : this.f3850b) {
            a.m5266a(this.f3849a, i, bhVar);
        }
    }
}
