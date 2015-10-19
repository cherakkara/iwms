package com.google.android.m4b.maps.ao;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline.Polyline;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.an;
import com.google.android.m4b.maps.an.bb;
import com.google.android.m4b.maps.ap.TileCrypt;
import com.google.android.m4b.maps.au.Inflate.Inflate;
import com.google.android.m4b.maps.bi.ExportablePgraph;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.google.android.m4b.maps.p049i.ByteArrayDataInput;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* renamed from: com.google.android.m4b.maps.ao.b */
public final class RoadGraphPiece implements bb {
    private final ac f3728a;
    private final int f3729b;
    private final long f3730c;
    private final Segment[] f3731d;

    private RoadGraphPiece(ac acVar, ProtoBuf protoBuf, long j) {
        this.f3728a = acVar;
        this.f3729b = protoBuf.m10204d(1);
        this.f3730c = j;
        this.f3731d = new Segment[protoBuf.m10215k(2)];
        m6130a(protoBuf, m6132a(protoBuf));
        m6133b(protoBuf);
    }

    public static RoadGraphPiece m6129a(ac acVar, byte[] bArr, int i, long j) {
        int i2 = i + 10;
        byte[] bArr2 = new byte[32];
        TileCrypt.m6300a(acVar.m5440c(), acVar.m5441d(), acVar.m5439b(), RoadGraphPiece.m6128a(bArr, i), bArr2);
        TileCrypt tileCrypt = new TileCrypt();
        tileCrypt.m6305b(bArr2, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        tileCrypt.m6304a(bArr, i2, bArr.length - i2);
        int length = bArr.length - i2;
        Inflater inflater = new Inflater(true);
        try {
            Inflate a = com.google.android.m4b.maps.au.Inflate.m6622a(bArr, i2, length);
            byte[] a2 = a.m6620a();
            length = a.m6621b();
            ProtoBuf protoBuf = new ProtoBuf(ExportablePgraph.f6252a);
            protoBuf.m10180a(new ByteArrayInputStream(a2), length);
            RoadGraphPiece roadGraphPiece = new RoadGraphPiece(acVar, protoBuf, j);
            inflater.end();
            return roadGraphPiece;
        } catch (DataFormatException e) {
            throw new IOException(e.getMessage());
        } catch (Throwable th) {
            inflater.end();
        }
    }

    public static int m6128a(byte[] bArr, int i) {
        ByteArrayDataInput byteArrayDataInput = new ByteArrayDataInput(bArr);
        byteArrayDataInput.skipBytes(i);
        int readInt = byteArrayDataInput.readInt();
        if (readInt != 1162889042) {
            throw new IOException("FORMAT_MAGIC expected. Found: " + readInt);
        }
        readInt = byteArrayDataInput.readUnsignedShort();
        if (readInt == 1) {
            return byteArrayDataInput.readInt();
        }
        throw new IOException("Version mismatch: 1 expected, " + readInt + " found");
    }

    private Point[] m6132a(ProtoBuf protoBuf) {
        Point[] pointArr = new Point[this.f3731d.length];
        int k = protoBuf.m10215k(4);
        for (int i = 0; i < k; i++) {
            ProtoBuf c = protoBuf.m10202c(4, i);
            Point a = Point.m5924a(c.m10204d(1), c.m10204d(2));
            int[] a2 = RoadGraphPiece.m6131a(c.m10203c(3));
            for (int i2 : a2) {
                pointArr[i2] = a;
            }
        }
        return pointArr;
    }

    private void m6130a(ProtoBuf protoBuf, Point[] pointArr) {
        for (int i = 0; i < this.f3731d.length / 2; i++) {
            SegmentName[] segmentNameArr;
            int i2;
            int a;
            DataInput dataInput;
            int i3 = i * 2;
            int i4 = (i * 2) + 1;
            ProtoBuf c = protoBuf.m10202c(2, i3);
            int a2 = ProtoBufUtil.m10221a(c, 2);
            ProtoBuf c2 = protoBuf.m10202c(2, i4);
            int a3 = ProtoBufUtil.m10221a(c2, 2);
            byte[] bArr = null;
            ProtoBuf c3 = protoBuf.m10202c(3, i);
            if (c3.m10214j(1)) {
                bArr = c3.m10203c(1);
            }
            int a4 = ProtoBufUtil.m10222a(c3, 2, 0);
            int a5 = ProtoBufUtil.m10222a(c3, 3, 0);
            int k = c.m10215k(3);
            boolean z = a5 == 1;
            if (k > 0) {
                segmentNameArr = new SegmentName[k];
                for (i2 = 0; i2 < k; i2++) {
                    ProtoBuf c4 = protoBuf.m10202c(5, c.m10195b(3, i2));
                    String str = null;
                    if (c4.m10214j(2)) {
                        str = c4.m10212h(2).intern();
                    }
                    segmentNameArr[i2] = new SegmentName(c4.m10212h(1).intern(), str, z);
                }
            } else {
                segmentNameArr = new SegmentName[]{Segment.f3732a};
            }
            Point point = pointArr[i4];
            Point point2 = pointArr[i3];
            if (bArr != null) {
                DataInput dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
                DataInput dataInput2 = dataInputStream;
                a = an.m5578a(dataInputStream);
                dataInput = dataInput2;
            } else {
                dataInput = null;
                a = 0;
            }
            Polyline polyline = new Polyline(a + 2);
            if (point != null) {
                polyline.m6006a(point);
            }
            int i5 = 0;
            int i6 = 0;
            for (i2 = 0; i2 < a; i2++) {
                i5 += an.m5580b(dataInput);
                i6 += an.m5580b(dataInput);
                polyline.m6006a(Point.m5939c(i5, i6));
            }
            if (point2 != null) {
                polyline.m6006a(point2);
            }
            com.google.android.m4b.maps.an.Polyline c5 = polyline.m6008c();
            if (point == null && point2 == null) {
                throw new IOException("Both polyline endpoints are missing for segment: " + this.f3731d[i3] + " in tile: " + this.f3728a);
            }
            int i7;
            int i8;
            if (point == null) {
                a = 2;
                i7 = 5;
            } else if (point2 == null) {
                a = 1;
                i7 = 6;
            } else {
                i7 = 4;
                a = 0;
            }
            if ((ProtoBufUtil.m10222a(c, 4, 0) & 1) != 0) {
                k = a | 8;
            } else {
                k = a;
            }
            if ((ProtoBufUtil.m10222a(c2, 4, 0) & 1) != 0) {
                i8 = i7 | 8;
            } else {
                i8 = i7;
            }
            this.f3731d[i3] = new Segment(Segment.m6144a(this.f3728a, i3), segmentNameArr, c5, k, a2, a5, a4);
            this.f3731d[i4] = new Segment(Segment.m6144a(this.f3728a, i4), segmentNameArr, c5, i8, a3, a5, a4);
            if (c5.m6020b() < 2) {
                throw new IOException("Segment polyline had fewer than two points for segment: " + this.f3731d[i3] + " in tile: " + this.f3728a);
            }
        }
    }

    private void m6133b(ProtoBuf protoBuf) {
        int k = protoBuf.m10215k(4);
        Point point = new Point();
        point = new Point();
        for (int i = 0; i < k; i++) {
            ProtoBuf c = protoBuf.m10202c(4, i);
            int[] a = RoadGraphPiece.m6131a(c.m10203c(3));
            int[] a2 = RoadGraphPiece.m6131a(c.m10203c(4));
            int i2 = 0;
            Object obj = new Arc[a.length];
            int i3;
            for (int i4 = 0; i4 < a.length; i4 = i3 + 1) {
                Segment segment = this.f3731d[a[i4]];
                int i5 = 0;
                i3 = i4;
                i4 = 0;
                while (i4 < a.length) {
                    if (i2 >= a2.length) {
                        i4 = a.length;
                        i3 = i4;
                    } else {
                        int i6 = a2[i2];
                        if (i6 != 0) {
                            int i7 = i5 + 1;
                            obj[i5] = new Arc(this.f3731d[a[i4] ^ 1], i6);
                            i5 = i7;
                        }
                    }
                    i4++;
                    i2++;
                }
                Arc[] arcArr = new Arc[i5];
                System.arraycopy(obj, 0, arcArr, 0, arcArr.length);
                segment.m6147a(arcArr);
            }
        }
    }

    private static int[] m6131a(byte[] bArr) {
        DataInput dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        int a = an.m5578a(dataInputStream);
        int[] iArr = new int[a];
        for (int i = 0; i < a; i++) {
            iArr[i] = an.m5578a(dataInputStream);
        }
        return iArr;
    }

    public final boolean m6135a(Clock clock) {
        return this.f3730c >= 0 && clock.m10152b() > this.f3730c;
    }

    public final ac m6134a() {
        return this.f3728a;
    }

    public final int m6138c() {
        return this.f3729b;
    }

    public final ai m6136b() {
        return ai.f3431i;
    }

    public final int m6140d() {
        return -1;
    }

    public final boolean m6137b(Clock clock) {
        return false;
    }

    public final void m6139c(Clock clock) {
    }

    public final long m6143p() {
        return -1;
    }

    public final long m6142k() {
        return this.f3730c;
    }

    public final boolean m6141e() {
        return false;
    }
}
