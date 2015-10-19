package com.google.android.m4b.maps.bj;

import com.google.android.m4b.maps.bx.Streetview;
import com.google.android.m4b.maps.p040u.BaseDataRequest;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.OutputStream;

/* renamed from: com.google.android.m4b.maps.bj.h */
public final class UsageReportRequest extends BaseDataRequest {
    private boolean f6504a;
    private boolean f6505b;
    private final UsageReportRequest f6506c;
    private final UsageReportRequest f6507d;

    /* renamed from: com.google.android.m4b.maps.bj.h.a */
    static class UsageReportRequest {
        int f6500a;
        int f6501b;
        int f6502c;
        private int f6503d;

        UsageReportRequest(int i) {
            this.f6503d = i;
        }

        final boolean m9934a() {
            return this.f6500a <= 0 && this.f6501b <= 0 && this.f6502c <= 0;
        }

        private static void m9932a(int i, ProtoBuf protoBuf, int i2) {
            if (i > 0) {
                protoBuf.m10210f(i2, i);
            }
        }

        final void m9933a(ProtoBuf protoBuf) {
            if (!m9934a()) {
                ProtoBuf c = ProtoBufUtil.m10231c(protoBuf, 4);
                UsageReportRequest.m9932a(this.f6503d, c, 1);
                UsageReportRequest.m9932a(this.f6500a, c, 2);
                UsageReportRequest.m9932a(this.f6501b, c, 3);
                UsageReportRequest.m9932a(this.f6502c, c, 4);
            }
        }
    }

    public UsageReportRequest() {
        this.f6504a = false;
        this.f6505b = false;
        this.f6506c = new UsageReportRequest(0);
        this.f6507d = new UsageReportRequest(1);
    }

    public final boolean m9937a(int i, int i2, int i3, int i4) {
        boolean z = false;
        synchronized (this) {
            if (this.f6505b) {
                return false;
            }
            boolean z2;
            UsageReportRequest usageReportRequest = i == 1 ? this.f6507d : this.f6506c;
            if (m9936a() || i4 <= 0) {
                z2 = false;
            } else {
                z2 = true;
            }
            usageReportRequest.f6500a += i2;
            usageReportRequest.f6501b += i3;
            usageReportRequest.f6502c += i4;
            if (!(this.f6504a || (this.f6507d.m9934a() && this.f6506c.m9934a()))) {
                this.f6504a = true;
                z = true;
            }
            DataRequestDispatcher a = DataRequestDispatcher.m11393a();
            if (a != null) {
                if (z) {
                    a.m11451c((DataRequest) this);
                } else if (z2) {
                    a.m11454d();
                    a.m11456e();
                }
            }
            return true;
        }
    }

    public final int m9939i() {
        return 45;
    }

    public final synchronized boolean m9936a() {
        boolean z;
        z = this.f6506c.f6502c > 0 || this.f6507d.f6502c > 0;
        return z;
    }

    public final boolean m9938a(DataInput dataInput) {
        ProtoBufUtil.m10226a(Streetview.f6857e, dataInput);
        return true;
    }

    public final synchronized void m9935a(DataOutput dataOutput) {
        this.f6505b = true;
        ProtoBuf protoBuf = new ProtoBuf(Streetview.f6856d);
        this.f6506c.m9933a(protoBuf);
        this.f6507d.m9933a(protoBuf);
        protoBuf.m10193a((OutputStream) dataOutput);
    }
}
