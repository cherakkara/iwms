package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.bx.Geometry;
import com.google.android.m4b.maps.bx.af;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.p040u.BaseDataRequest;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.newrelic.agent.android.instrumentation.Trace;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.OutputStream;

/* compiled from: ReverseGeocodeDataRequest */
public final class ap extends BaseDataRequest {
    private final LatLng f5663a;
    private final double f5664b;
    private final double f5665c;
    private final float f5666d;
    private volatile boolean f5667e;
    private ReverseGeocodeDataRequest f5668f;
    private int f5669g;
    private ReverseGeocodeDataRequest[] f5670h;

    /* renamed from: com.google.android.m4b.maps.be.ap.a */
    public interface ReverseGeocodeDataRequest {
        void m8412a(ap apVar);
    }

    /* renamed from: com.google.android.m4b.maps.be.ap.b */
    public static class ReverseGeocodeDataRequest {
        private final String[] f5662a;

        public ReverseGeocodeDataRequest(int i, String[] strArr, ProtoBuf protoBuf) {
            this.f5662a = strArr;
        }

        public final String m8579a() {
            return (this.f5662a.length > 0 ? this.f5662a[0] : Trace.NULL) + ", " + (this.f5662a.length > 1 ? this.f5662a[1] : Trace.NULL);
        }
    }

    public ap(LatLng latLng, float f) {
        this.f5667e = false;
        this.f5670h = null;
        this.f5663a = latLng;
        this.f5664b = 1.0E-6d;
        this.f5665c = 1.0E-6d;
        this.f5666d = f;
    }

    public final ReverseGeocodeDataRequest m8583a(int i) {
        if (this.f5670h.length <= 0) {
            return null;
        }
        return this.f5670h[0];
    }

    public final int m8589j() {
        return this.f5670h != null ? this.f5670h.length : 0;
    }

    public final int m8588i() {
        return 50;
    }

    public final void m8585a(DataOutput dataOutput) {
        ProtoBuf protoBuf = new ProtoBuf(af.f6820a);
        protoBuf.m10196b(1, m8581a(this.f5663a));
        LatLng latLng = this.f5663a;
        float f = this.f5666d;
        ProtoBuf protoBuf2 = new ProtoBuf(Geometry.f7068e);
        protoBuf2.m10196b(1, m8581a(latLng));
        protoBuf2.m10210f(2, m8580a(1.0E-6d));
        protoBuf2.m10210f(3, m8580a(1.0E-6d));
        if (f > 0.0f) {
            protoBuf2.m10210f(4, (int) f);
        }
        protoBuf.m10196b(3, protoBuf2);
        protoBuf.m10185a(4, true);
        protoBuf.m10193a((OutputStream) dataOutput);
    }

    private static ProtoBuf m8581a(LatLng latLng) {
        ProtoBuf protoBuf = new ProtoBuf(Geometry.f7067d);
        protoBuf.m10210f(1, 1);
        ProtoBuf protoBuf2 = new ProtoBuf(Geometry.f7064a);
        protoBuf2.m10210f(1, m8580a(latLng.f7554a));
        protoBuf2.m10210f(2, m8580a(latLng.f7555b));
        protoBuf.m10196b(2, protoBuf2);
        return protoBuf;
    }

    private static int m8580a(double d) {
        return (int) Math.round(1000000.0d * d);
    }

    public final boolean m8586a(DataInput dataInput) {
        ProtoBuf a = ProtoBufUtil.m10226a(af.f6821b, dataInput);
        this.f5669g = a.m10204d(1);
        switch (this.f5669g) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                int k = a.m10215k(2);
                this.f5670h = new ReverseGeocodeDataRequest[k];
                for (int i = 0; i < k; i++) {
                    ProtoBuf c = a.m10202c(2, i);
                    int d = c.m10211g(1).m10204d(1);
                    String a2 = m8582a(2, 0, c);
                    String a3 = m8582a(2, 1, c);
                    this.f5670h[i] = new ReverseGeocodeDataRequest(d, new String[]{a2, a3}, c.m10214j(3) ? c.m10211g(3) : null);
                }
                break;
        }
        this.f5667e = true;
        return true;
    }

    public final void m8587g() {
        if (this.f5668f != null) {
            this.f5668f.m8412a(this);
        }
    }

    private static String m8582a(int i, int i2, ProtoBuf protoBuf) {
        if (protoBuf == null) {
            return Trace.NULL;
        }
        return protoBuf.m10215k(2) > i2 ? protoBuf.m10205d(2, i2) : Trace.NULL;
    }

    public final void m8584a(ReverseGeocodeDataRequest reverseGeocodeDataRequest) {
        this.f5668f = reverseGeocodeDataRequest;
    }
}
