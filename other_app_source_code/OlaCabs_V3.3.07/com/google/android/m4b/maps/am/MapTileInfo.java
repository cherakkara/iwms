package com.google.android.m4b.maps.am;

import android.support.v4.view.MotionEventCompat;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.bp;
import com.google.android.m4b.maps.an.bq;
import com.google.android.m4b.maps.au.GeometryConverter;
import com.google.android.m4b.maps.bx.Tile;
import com.google.android.m4b.maps.p040u.ExceptionReporter;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/* renamed from: com.google.android.m4b.maps.am.b */
public final class MapTileInfo {
    private static final int f3361a;
    private static byte[] f3362b;
    private ProtoBuf f3363c;
    private ProtoBuf f3364d;
    private ProtoBuf f3365e;
    private ProtoBuf f3366f;

    static {
        f3361a = 31 - Integer.numberOfLeadingZeros(AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        f3362b = new byte[]{(byte) 76, (byte) 84, (byte) 73, (byte) 80, (byte) 10};
    }

    public final byte[] m5397a(byte[] bArr) {
        int i;
        Throwable th;
        Object obj = null;
        byte[] bArr2 = f3362b;
        if (bArr.length >= bArr2.length + 0) {
            for (i = 0; i < bArr2.length; i++) {
                if (bArr[i + 0] != bArr2[i]) {
                    break;
                }
            }
            int i2 = 1;
        }
        if (obj == null) {
            return bArr;
        }
        try {
            InputStream gZIPInputStream;
            byte[] bArr3 = f3362b;
            Object obj2 = new byte[4];
            System.arraycopy(bArr, 5, obj2, 0, 4);
            i = (obj2[3] & MotionEventCompat.ACTION_MASK) | ((((obj2[0] & MotionEventCompat.ACTION_MASK) << 24) | ((obj2[1] & MotionEventCompat.ACTION_MASK) << 16)) | ((obj2[2] & MotionEventCompat.ACTION_MASK) << 8));
            int abs = Math.abs(i);
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr, 9, abs);
            if (i < 0) {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            } else {
                gZIPInputStream = byteArrayInputStream;
            }
            this.f3363c = new ProtoBuf(Tile.f6885a);
            this.f3363c.m10187a(gZIPInputStream);
            gZIPInputStream.close();
            this.f3364d = null;
            bArr3 = f3362b;
            i2 = abs + 9;
            obj2 = new byte[(bArr.length - i2)];
            try {
                System.arraycopy(bArr, i2, obj2, 0, obj2.length);
                return obj2;
            } catch (Throwable e) {
                Object obj3 = obj2;
                th = e;
                ExceptionReporter.m11467a("IOException reading map tile info", th);
                return bArr;
            }
        } catch (IOException e2) {
            th = e2;
            ExceptionReporter.m11467a("IOException reading map tile info", th);
            return bArr;
        }
    }

    private ProtoBuf m5396e() {
        if (this.f3364d == null && this.f3363c != null && this.f3363c.m10214j(4)) {
            this.f3364d = this.f3363c.m10211g(4);
        }
        return this.f3364d;
    }

    public final String[] m5398a() {
        int i = 0;
        ProtoBuf e = m5396e();
        if (e == null) {
            return new String[0];
        }
        int k = e.m10215k(1);
        String[] strArr = new String[k];
        while (i < k) {
            strArr[i] = e.m10205d(1, i);
            i++;
        }
        return strArr;
    }

    public final String[] m5399b() {
        int i = 0;
        ProtoBuf e = m5396e();
        if (e == null) {
            return new String[0];
        }
        int k = e.m10215k(2);
        String[] strArr = new String[k];
        while (i < k) {
            strArr[i] = e.m10205d(2, i);
            i++;
        }
        return strArr;
    }

    public final int m5400c() {
        ProtoBuf e = m5396e();
        if (e == null || !e.m10214j(3)) {
            return -1;
        }
        int d = e.m10204d(3);
        if (d != 0) {
            return d;
        }
        UserEventReporter.m11506a("year=0", toString());
        return -1;
    }

    public final List<bp> m5401d() {
        if (this.f3365e == null && this.f3363c != null && this.f3363c.m10214j(5)) {
            this.f3365e = this.f3363c.m10211g(5);
        }
        ProtoBuf protoBuf = this.f3365e;
        if (protoBuf == null) {
            return ar.m2515a();
        }
        if (this.f3366f == null && this.f3363c != null && this.f3363c.m10214j(1)) {
            this.f3366f = this.f3363c.m10211g(1);
        }
        int a = ProtoBufUtil.m10221a(this.f3366f, 4);
        List<ProtoBuf> a2 = ar.m2515a();
        int k = this.f3363c.m10215k(3);
        for (int i = 0; i < k; i++) {
            a2.add(this.f3363c.m10202c(3, i));
        }
        List<bp> a3 = ar.m2515a();
        int k2 = protoBuf.m10215k(1);
        for (int i2 = 0; i2 < k2; i2++) {
            ProtoBuf c = protoBuf.m10202c(1, i2);
            List a4 = ar.m2515a();
            Map a5 = au.m2807a();
            for (ProtoBuf protoBuf2 : a2) {
                ProtoBuf protoBuf22;
                Point a6;
                Rectangle2D a7;
                String h = protoBuf22.m10202c(2, 0).m10212h(2);
                if (protoBuf22.m10204d(1) == 0) {
                    protoBuf22 = protoBuf22.m10211g(3);
                    a6 = GeometryConverter.m6619a(protoBuf22.m10211g(31));
                    Point point = new Point(MapTileInfo.m5395a(ProtoBufUtil.m10221a(protoBuf22, 32), a) / 2, MapTileInfo.m5395a(ProtoBufUtil.m10221a(protoBuf22, 33), a) / 2);
                    a7 = Rectangle2D.m6041a(a6.m5957e(point), a6.m5959f(point));
                } else {
                    a7 = null;
                    a6 = null;
                }
                a5.put(h, new bq(h, a6, a7));
            }
            if (c != null) {
                int k3 = c.m10215k(1);
                for (k = 0; k < k3; k++) {
                    String h2 = c.m10202c(1, k).m10212h(1);
                    Object obj = (bq) a5.get(h2);
                    if (obj == null) {
                        obj = new bq(h2, null, null);
                    }
                    a4.add(obj);
                }
            }
            byte[] bArr = null;
            if (c != null) {
                String h3 = c.m10212h(2);
                bArr = h3 == null ? new byte[0] : h3.getBytes();
            }
            a3.add(new bp(a4, bArr));
        }
        return a3;
    }

    private static int m5395a(int i, int i2) {
        return i << ((30 - i2) - f3361a);
    }
}
