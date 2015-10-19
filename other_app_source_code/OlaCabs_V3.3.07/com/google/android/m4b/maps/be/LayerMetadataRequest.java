package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.bx.LayerMetadata;
import com.google.android.m4b.maps.bx.Tile;
import com.google.android.m4b.maps.model.MapsEngineLayerInfo;
import com.google.android.m4b.maps.p040u.BaseDataRequest;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.DataInput;
import java.io.DataOutput;

/* renamed from: com.google.android.m4b.maps.be.j */
final class LayerMetadataRequest extends BaseDataRequest {
    private final MapsEngineLayerInfo f6023a;
    private final ac f6024b;
    private int f6025c;
    private ProtoBuf f6026d;
    private long f6027e;

    LayerMetadataRequest(MapsEngineLayerInfo mapsEngineLayerInfo, ac acVar) {
        this.f6023a = mapsEngineLayerInfo;
        this.f6024b = acVar;
    }

    public final int m9400i() {
        return 148;
    }

    public final void m9397a(DataOutput dataOutput) {
        ProtoBuf protoBuf = new ProtoBuf(LayerMetadata.f7101a);
        ProtoBuf protoBuf2;
        if (this.f6023a.m10748b() != null) {
            protoBuf2 = new ProtoBuf(LayerMetadata.f7102b);
            protoBuf2.m10197b(1, this.f6023a.m10748b());
            protoBuf2.m10197b(2, this.f6023a.m10749c());
            protoBuf2.m10197b(3, this.f6023a.m10750d());
            protoBuf.m10196b(2, protoBuf2);
        } else {
            protoBuf2 = new ProtoBuf(LayerMetadata.f7103c);
            protoBuf2.m10197b(1, this.f6023a.m10751e());
            protoBuf2.m10197b(2, this.f6023a.m10750d());
            protoBuf.m10196b(4, protoBuf2);
        }
        ProtoBufUtil.m10228a(dataOutput, protoBuf);
    }

    public final boolean m9398a(DataInput dataInput) {
        ProtoBuf a = ProtoBufUtil.m10226a(LayerMetadata.f7104d, dataInput);
        this.f6025c = a.m10204d(1);
        if (this.f6025c == 1) {
            ProtoBuf protoBuf = new ProtoBuf(Tile.f6893i);
            ProtoBuf g;
            if (a.m10214j(4)) {
                g = a.m10211g(4);
                if (g.m10214j(1)) {
                    protoBuf.m10197b(1, "ft:cw:" + g.m10207e(1));
                    LayerMetadataRequest.m9396a(protoBuf, "y", String.valueOf(g.m10207e(2)));
                    LayerMetadataRequest.m9396a(protoBuf, "tmplt", String.valueOf(g.m10207e(3)));
                    if (a.m10214j(2)) {
                        LayerMetadataRequest.m9396a(protoBuf, "cw_token", a.m10212h(2));
                    }
                } else {
                    protoBuf.m10197b(1, "vdb:" + g.m10212h(5));
                }
            } else if (a.m10214j(5)) {
                g = a.m10211g(5);
                protoBuf.m10197b(1, g.m10212h(1));
                LayerMetadataRequest.m9396a(protoBuf, "v", String.valueOf(g.m10204d(2)));
                if (a.m10214j(2)) {
                    LayerMetadataRequest.m9396a(protoBuf, "authToken", a.m10212h(2));
                }
            }
            this.f6026d = protoBuf;
            this.f6027e = ProtoBufUtil.m10224a(a, 3, (long) Constants.MILLIS_IN_AN_HOUR);
        } else {
            this.f6026d = null;
            this.f6027e = -1;
        }
        return true;
    }

    public final void m9399g() {
        switch (this.f6025c) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f6024b.m8300a(this.f6026d, this.f6027e);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                this.f6024b.m8302c();
            default:
                this.f6024b.m8303d();
        }
    }

    private static void m9396a(ProtoBuf protoBuf, String str, String str2) {
        ProtoBuf protoBuf2 = new ProtoBuf(Tile.f6894j);
        protoBuf2.m10197b(1, str);
        protoBuf2.m10197b(2, str2);
        protoBuf.m10190a(2, protoBuf2);
    }
}
