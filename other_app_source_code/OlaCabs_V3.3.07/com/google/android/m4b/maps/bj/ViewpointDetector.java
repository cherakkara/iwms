package com.google.android.m4b.maps.bj;

import com.google.android.m4b.maps.be.ar;
import com.google.android.m4b.maps.be.ay;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.bj.i */
public final class ViewpointDetector {
    private UsageReportRequest f6508a;
    private float f6509b;
    private boolean f6510c;
    private String f6511d;
    private int f6512e;
    private float f6513f;
    private float f6514g;
    private float f6515h;
    private float f6516i;
    private boolean f6517j;

    public ViewpointDetector() {
        this.f6508a = new UsageReportRequest();
        this.f6509b = -1.0f;
        this.f6510c = false;
        this.f6511d = Trace.NULL;
    }

    public final void m9942a(float f) {
        this.f6509b = f;
    }

    public final void m9944a(PanoramaConfig panoramaConfig, ay ayVar) {
        String str = panoramaConfig.f6592h;
        if (!this.f6511d.equals(str)) {
            this.f6510c = panoramaConfig.f6585a;
            this.f6511d = str;
            this.f6512e = panoramaConfig.f6598n;
            m9941b(ayVar);
            int i = this.f6512e;
            m9940a(0, 0, 1);
        }
    }

    public final void m9943a(ay ayVar) {
        if (!this.f6517j) {
            m9941b(ayVar);
        } else if (Math.abs(this.f6515h - ayVar.m8755e()) > 0.999f) {
            r0 = this.f6512e;
            m9940a(0, 1, 0);
            m9941b(ayVar);
        } else {
            if (this.f6517j) {
                if (this.f6509b < 0.0f) {
                    new IllegalStateException("FOV not set").printStackTrace();
                } else {
                    float b = ayVar.m8750b();
                    float d = ayVar.m8754d();
                    if (!(this.f6513f == b && this.f6514g == d)) {
                        if (ar.m8613n((float) Math.acos((double) ((ar.m8608i(this.f6513f - b) * (ar.m8608i(d) * ar.m8608i(this.f6514g))) + (ar.m8607h(this.f6514g) * ar.m8607h(d))))) * dm.DEFAULT_BACKOFF_MULT > this.f6509b * this.f6516i) {
                            r0 = 1;
                            if (r0 != 0) {
                                r0 = this.f6512e;
                                m9940a(1, 0, 0);
                                m9941b(ayVar);
                            }
                        }
                    }
                }
            }
            r0 = 0;
            if (r0 != 0) {
                r0 = this.f6512e;
                m9940a(1, 0, 0);
                m9941b(ayVar);
            }
        }
    }

    private void m9940a(int i, int i2, int i3) {
        if (!this.f6510c && !this.f6508a.m9937a(this.f6512e, i, i2, i3)) {
            this.f6508a = new UsageReportRequest();
            this.f6508a.m9937a(this.f6512e, i, i2, i3);
        }
    }

    private void m9941b(ay ayVar) {
        this.f6517j = ayVar != null;
        if (this.f6517j) {
            this.f6513f = ayVar.m8750b();
            this.f6514g = ayVar.m8754d();
            this.f6515h = ayVar.m8755e();
            this.f6516i = ayVar.m8756f();
        }
    }
}
