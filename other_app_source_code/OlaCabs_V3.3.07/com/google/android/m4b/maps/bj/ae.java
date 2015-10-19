package com.google.android.m4b.maps.bj;

import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.ac.JpegUtil;
import com.google.android.m4b.maps.bx.ClientCapabilities;
import com.google.android.m4b.maps.bx.Streetview;
import com.google.android.m4b.maps.p040u.BaseDataRequest;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.newrelic.agent.android.tracing.ActivityTrace;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.OutputStream;

/* compiled from: PanoramaRequest */
public final class ae extends BaseDataRequest {
    private static int f6335a;
    private final PanoramaRequest f6336b;
    private final ProtoBuf f6337c;
    private String f6338d;
    private int f6339e;
    private ProtoBuf f6340f;
    private boolean f6341g;
    private boolean f6342h;
    private StringBuilder f6343i;

    /* renamed from: com.google.android.m4b.maps.bj.ae.a */
    public interface PanoramaRequest {
        void m9752a();

        void m9753a(PanoramaConfig panoramaConfig, ProtoBuf protoBuf);

        void m9754a(String str, int i, int i2, int i3, int i4, byte[] bArr);
    }

    static {
        f6335a = 0;
    }

    public ae(PanoramaRequest panoramaRequest, String str) {
        this(panoramaRequest);
        m9770a(str, "panoId");
        this.f6338d = str;
        this.f6337c.m10191a(1, str);
        this.f6343i.append(str);
    }

    public ae(PanoramaRequest panoramaRequest, MapPoint mapPoint) {
        this(panoramaRequest);
        m9770a(mapPoint, "mapPoint");
        this.f6337c.m10190a(2, mapPoint.m10028a());
        this.f6343i.append("@ ").append(mapPoint);
    }

    public ae(PanoramaRequest panoramaRequest, MapPoint mapPoint, int i) {
        this(panoramaRequest);
        m9770a(mapPoint, "mapPoint");
        this.f6337c.m10190a(2, mapPoint.m10028a());
        this.f6337c.m10189a(63, i);
        this.f6343i.append("@ ").append(mapPoint);
    }

    private ae(PanoramaRequest panoramaRequest) {
        this.f6339e = -1;
        this.f6341g = true;
        m9770a(panoramaRequest, "listener");
        this.f6336b = panoramaRequest;
        this.f6337c = new ProtoBuf(Streetview.f6854b);
        this.f6337c.m10185a(51, true);
        this.f6337c.m10210f(58, 4);
        this.f6337c.m10189a(59, 3);
        this.f6337c.m10189a(59, 4);
        this.f6343i = new StringBuilder("PR sending request ");
    }

    private static void m9770a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public final ae m9773a(boolean z) {
        this.f6341g = z;
        return this;
    }

    public final ae m9778j() {
        this.f6343i.append(" config");
        ProtoBuf b = ProtoBufUtil.m10229b(this.f6337c, 17);
        b.m10185a(56, true);
        b.m10185a(57, true);
        return this;
    }

    public final ae m9771a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("zoom < 0");
        } else if (this.f6340f != null) {
            throw new IllegalStateException("already requested tiles");
        } else {
            this.f6343i.append(" tiles @ zoom ").append(i);
            this.f6339e = i;
            this.f6340f = ProtoBufUtil.m10229b(this.f6337c, 33);
            ProtoBuf protoBuf = new ProtoBuf(ClientCapabilities.f6951a);
            protoBuf.m10210f(16, 3);
            this.f6340f.m10196b(34, protoBuf);
            this.f6340f.m10210f(35, AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
            this.f6340f.m10210f(36, i);
            return this;
        }
    }

    public final ae m9772a(int i, int i2, int i3) {
        if (this.f6340f == null) {
            throw new IllegalStateException("must call addTilesRequest first");
        }
        this.f6343i.append(" (" + i + ", " + i2 + ") face " + i3);
        ProtoBuf c = ProtoBufUtil.m10231c(this.f6340f, 37);
        c.m10210f(39, i);
        c.m10210f(40, i2);
        if (i3 != -1) {
            c.m10210f(60, i3);
        }
        return this;
    }

    public final void m9779k() {
        if (this.f6342h) {
            throw new IllegalStateException("already queued");
        }
        this.f6342h = true;
        StringBuilder stringBuilder = this.f6343i;
        this.f6343i = null;
        if (f6335a > 0 && this.f6337c.m10214j(17)) {
            try {
                Thread.sleep((long) f6335a);
            } catch (InterruptedException e) {
            }
            f6335a = 0;
        }
        DataRequestDispatcher.m11393a().m11451c((DataRequest) this);
    }

    public final int m9777i() {
        return 40;
    }

    public final boolean m9775a() {
        return this.f6341g;
    }

    public final boolean m9776a(DataInput dataInput) {
        SystemClock.uptimeMillis();
        ProtoBuf a = ProtoBufUtil.m10226a(Streetview.f6855c, dataInput);
        String h = a.m10212h(2);
        ProtoBuf g = a.m10211g(3);
        if (g != null) {
            PanoramaConfig panoramaConfig = new PanoramaConfig(g);
            String str = panoramaConfig.f6592h;
            int i = ((panoramaConfig.f6586b & 2) != 0 ? 1 : null) == null ? 0 : panoramaConfig.f6587c == 0 ? ActivityTrace.MAX_TRACES : panoramaConfig.f6587c;
            f6335a = i;
            if (!str.equals(h)) {
                new StringBuilder("PR received config ").append(str).append(" != response ").append(h);
            }
            if (!(this.f6338d == null || str.equals(this.f6338d))) {
                new StringBuilder("PR received config ").append(str).append(" != request ").append(this.f6338d);
            }
            this.f6336b.m9753a(panoramaConfig, g);
        }
        ProtoBuf g2 = a.m10211g(17);
        if (g2 != null) {
            int d = g2.m10204d(19);
            if (d != this.f6339e) {
                new StringBuilder("PR received zoom ").append(d).append(" != request ").append(this.f6339e);
            }
            int k = g2.m10215k(22);
            for (int i2 = 0; i2 < k; i2++) {
                ProtoBuf c = g2.m10202c(22, i2);
                int d2 = c.m10204d(23);
                int d3 = c.m10204d(24);
                int d4 = c.m10214j(26) ? c.m10204d(26) : -1;
                byte[] c2 = c.m10203c(25);
                if (c2.length > 1 && c2[0] == 67) {
                    c2 = JpegUtil.m4856a(c2);
                }
                this.f6336b.m9754a(h, d, d2, d3, d4, c2);
            }
        }
        a.m10200b(34);
        this.f6336b.m9752a();
        return true;
    }

    public final void m9774a(DataOutput dataOutput) {
        this.f6337c.m10193a((OutputStream) dataOutput);
    }
}
