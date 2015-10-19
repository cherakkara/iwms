package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.bx.MapsEngine;
import com.google.android.m4b.maps.p040u.BaseDataRequest;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.be.u */
public final class MapsEngineFeatureDataRequestBatch extends BaseDataRequest {
    private final MapsEngineFeatureDataRequestBatch f6077a;
    private final List<MapsEngineFeatureImpl> f6078b;

    /* renamed from: com.google.android.m4b.maps.be.u.a */
    public interface MapsEngineFeatureDataRequestBatch {
        void m9535a();
    }

    public final int m9539i() {
        return 149;
    }

    public final void m9536a(DataOutput dataOutput) {
        ProtoBuf protoBuf = new ProtoBuf(MapsEngine.f7109a);
        for (MapsEngineFeatureImpl mapsEngineFeatureImpl : this.f6078b) {
            ProtoBuf protoBuf2 = new ProtoBuf(MapsEngine.f7110b);
            protoBuf2.m10190a(1, mapsEngineFeatureImpl.m9548a().m8278a());
            protoBuf2.m10191a(2, mapsEngineFeatureImpl.m9552c());
            protoBuf.m10190a(3, protoBuf2);
        }
        ProtoBufUtil.m10228a(dataOutput, protoBuf);
    }

    public final boolean m9537a(DataInput dataInput) {
        ProtoBuf a = ProtoBufUtil.m10226a(MapsEngine.f7111c, dataInput);
        for (int i = 0; i < a.m10215k(4); i++) {
            ProtoBuf c = a.m10202c(4, i);
            if (c.m10204d(1) == 1) {
                ((MapsEngineFeatureImpl) this.f6078b.get(i)).m9549a(c.m10212h(2), c.m10212h(3));
            }
        }
        return true;
    }

    public final void m9538g() {
        MapsEngineFeatureDataRequestBatch mapsEngineFeatureDataRequestBatch = this.f6077a;
        List list = this.f6078b;
        mapsEngineFeatureDataRequestBatch.m9535a();
    }

    public MapsEngineFeatureDataRequestBatch(List<MapsEngineFeatureImpl> list, MapsEngineFeatureDataRequestBatch mapsEngineFeatureDataRequestBatch) {
        this.f6077a = mapsEngineFeatureDataRequestBatch;
        this.f6078b = list;
    }
}
