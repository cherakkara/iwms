package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.aa.ServerControlledParametersManager;
import com.google.android.m4b.maps.ap.TileCrypt;
import com.google.android.m4b.maps.au.Inflate.Inflate;
import com.google.android.m4b.maps.au.SharedBufferHolder;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p049i.ByteArrayDataInput;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.android.m4b.maps.p058v.GmmSettings;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.DataInput;
import java.io.IOException;
import java.util.Iterator;
import java.util.zip.DataFormatException;
import org.apache.http.protocol.HTTP;

/* compiled from: VectorTile */
public class aq implements bb {
    private final ac f3494a;
    private final int f3495b;
    private final byte f3496c;
    private final bc[] f3497d;
    private final long f3498e;
    private final String[] f3499f;
    private final String[] f3500g;
    private final int f3501h;
    private final ai f3502i;
    private final ap[] f3503j;
    private final int f3504k;
    private long f3505l;

    /* renamed from: com.google.android.m4b.maps.an.aq.a */
    public static class VectorTile {
        private ac f3480a;
        private int f3481b;
        private int f3482c;
        private bc[] f3483d;
        private StyleTable f3484e;
        private long f3485f;
        private String[] f3486g;
        private String[] f3487h;
        private int f3488i;
        private ai f3489j;
        private long f3490k;

        public VectorTile() {
            this.f3482c = -1;
            this.f3485f = -1;
            this.f3488i = -1;
            this.f3489j = ai.f3423a;
            this.f3490k = -1;
        }

        public final VectorTile m5605a(StyleTable styleTable) {
            this.f3484e = styleTable;
            return this;
        }

        public final VectorTile m5602a(long j) {
            this.f3485f = j;
            return this;
        }

        public final VectorTile m5601a(int i) {
            this.f3482c = i;
            return this;
        }

        public final VectorTile m5610b(long j) {
            this.f3490k = j;
            return this;
        }

        public final VectorTile m5603a(ac acVar) {
            this.f3480a = acVar;
            return this;
        }

        public final VectorTile m5609b(int i) {
            this.f3481b = i;
            return this;
        }

        public final VectorTile m5607a(String[] strArr) {
            this.f3486g = strArr;
            return this;
        }

        public final VectorTile m5611b(String[] strArr) {
            this.f3487h = strArr;
            return this;
        }

        public final VectorTile m5612c(int i) {
            this.f3488i = i;
            return this;
        }

        public final VectorTile m5606a(bc[] bcVarArr) {
            this.f3483d = bcVarArr;
            return this;
        }

        public final VectorTile m5604a(ai aiVar) {
            this.f3489j = aiVar;
            return this;
        }

        public final aq m5608a() {
            return new aq(this.f3484e, null, this.f3480a, this.f3481b, (byte) 0, this.f3482c, this.f3486g == null ? new String[0] : this.f3486g, this.f3487h == null ? new String[0] : this.f3487h, this.f3488i, this.f3483d == null ? new bc[0] : this.f3483d, this.f3489j, null, this.f3485f, this.f3490k);
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.aq.b */
    public interface VectorTile extends Iterator<bc> {
        bc m5613a();

        void m5614b();

        void m5615c();
    }

    /* renamed from: com.google.android.m4b.maps.an.aq.c */
    class VectorTile implements VectorTile {
        private int f3491a;
        private int f3492b;
        private /* synthetic */ aq f3493c;

        private VectorTile(aq aqVar) {
            this.f3493c = aqVar;
            this.f3491a = 0;
            this.f3492b = 0;
        }

        public final /* synthetic */ Object next() {
            bc[] a = this.f3493c.f3497d;
            int i = this.f3491a;
            this.f3491a = i + 1;
            return a[i];
        }

        public final boolean hasNext() {
            return this.f3491a < this.f3493c.f3497d.length;
        }

        public final void remove() {
            throw new UnsupportedOperationException("remove() not supported");
        }

        public final bc m5616a() {
            return this.f3493c.f3497d[this.f3491a];
        }

        public final void m5617b() {
            this.f3492b = this.f3491a;
        }

        public final void m5618c() {
            this.f3491a = this.f3492b;
        }
    }

    protected aq(StyleTable styleTable, String[] strArr, ac acVar, int i, byte b, int i2, String[] strArr2, String[] strArr3, int i3, bc[] bcVarArr, ai aiVar, ap[] apVarArr, long j, long j2) {
        this.f3494a = acVar;
        this.f3495b = i;
        this.f3496c = b;
        this.f3499f = strArr2;
        this.f3500g = strArr3;
        this.f3501h = i3;
        this.f3497d = bcVarArr;
        this.f3502i = aiVar;
        this.f3503j = apVarArr;
        this.f3504k = i2;
        this.f3498e = j;
        this.f3505l = j2;
    }

    private static aq m5621a(ac acVar, DataInput dataInput, int i, byte b, int i2, int i3, ai aiVar, long j, long j2) {
        m5624a(dataInput);
        ac a = ac.m5425a(dataInput);
        if (a.m5440c() == acVar.m5440c() && a.m5441d() == acVar.m5441d() && a.m5439b() == acVar.m5439b()) {
            int i4;
            String[] strArr;
            StyleEntryTable a2;
            int readUnsignedByte = dataInput.readUnsignedByte();
            if (readUnsignedByte > 0) {
                readUnsignedByte += ActivityTrace.MAX_TRACES;
            }
            int a3 = an.m5578a(dataInput);
            String[] strArr2 = new String[a3];
            for (i4 = 0; i4 < a3; i4++) {
                strArr2[i4] = dataInput.readUTF().intern();
            }
            a3 = an.m5578a(dataInput);
            String[] strArr3 = new String[a3];
            for (i4 = 0; i4 < a3; i4++) {
                strArr3[i4] = dataInput.readUTF().intern();
            }
            if (i == 11) {
                a3 = an.m5578a(dataInput);
                for (i4 = 0; i4 < a3; i4++) {
                    dataInput.readUTF();
                }
            }
            StyleTable a4 = StyleTable.m6115a(dataInput, i);
            if (i == 11) {
                strArr = new String[0];
                a2 = StyleEntryTable.m6109a(dataInput, a4);
            } else {
                a2 = null;
                a3 = an.m5578a(dataInput);
                strArr = new String[a3];
                for (i4 = 0; i4 < a3; i4++) {
                    strArr[i4] = dataInput.readUTF().intern();
                }
            }
            ae aeVar = new ae(i, acVar, a4, strArr, a2);
            int a5 = an.m5578a(dataInput);
            bc[] bcVarArr = new bc[a5];
            for (a3 = 0; a3 < a5; a3++) {
                bcVarArr[a3] = m5623a(dataInput, aeVar);
            }
            a5 = an.m5578a(dataInput);
            ap[] apVarArr = new ap[a5];
            for (a3 = 0; a3 < a5; a3++) {
                apVarArr[a3] = ap.m5593a(dataInput, aeVar);
            }
            return new aq(a4, strArr, acVar, i2, b, i3, strArr2, strArr3, readUnsignedByte, bcVarArr, aiVar, apVarArr, j, j2);
        }
        throw new IOException("Expected tile coords: " + acVar + " but received " + a);
    }

    public static aq m5622a(ac acVar, byte[] bArr, int i, ai aiVar, long j, long j2) {
        DataInput byteArrayDataInput = new ByteArrayDataInput(bArr);
        byteArrayDataInput.skipBytes(i);
        int readInt = byteArrayDataInput.readInt();
        int readInt2 = byteArrayDataInput.readInt();
        m5624a(byteArrayDataInput);
        int readUnsignedShort = byteArrayDataInput.readUnsignedShort();
        if (readUnsignedShort == 10 || readUnsignedShort == 11) {
            int readInt3 = byteArrayDataInput.readInt();
            long readLong = byteArrayDataInput.readLong();
            int readUnsignedByte = byteArrayDataInput.readUnsignedByte();
            long[] jArr = new long[]{(long) readInt, (long) readInt2, (long) readUnsignedShort, (long) readInt3, readLong, (long) readUnsignedByte};
            readUnsignedByte = (int) jArr[0];
            int i2 = (int) jArr[1];
            readInt3 = (int) jArr[2];
            int i3 = (int) jArr[3];
            readLong = jArr[4];
            byte b = (byte) ((int) jArr[5]);
            int i4 = i + 27;
            int length = bArr.length - i4;
            TileCrypt tileCrypt = new TileCrypt();
            byte[] bArr2 = new byte[40];
            TileCrypt.m6299a(acVar.m5440c(), acVar.m5441d(), acVar.m5439b(), readInt3, i3, readLong, bArr2);
            tileCrypt.m6303a(bArr2, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
            tileCrypt.m6304a(bArr, i4, length);
            try {
                Inflate a = com.google.android.m4b.maps.au.Inflate.m6622a(bArr, i4, length);
                byte[] a2 = a.m6620a();
                readInt = a.m6621b();
                Object byteArrayDataInput2 = new ByteArrayDataInput(a2);
                aq a3 = m5621a(acVar, byteArrayDataInput2, readInt3, b, readUnsignedByte, i2, aiVar, j, j2);
                if (byteArrayDataInput2.m10512a() != readInt) {
                    throw new IOException("Byte stream not fully read for: " + a3.f3494a);
                }
                SharedBufferHolder.m6655a(a2);
                return a3;
            } catch (DataFormatException e) {
                throw new IOException(e.getMessage());
            }
        }
        throw new IOException("Version mismatch: 10 or 11 expected, " + readUnsignedShort + " found");
    }

    static bc m5623a(DataInput dataInput, ae aeVar) {
        RenderOpInfo renderOpInfo;
        FeatureId featureId = null;
        int i = 0;
        int a = an.m5578a(dataInput);
        if (aeVar.m5449a() == 11) {
            int a2 = an.m5578a(dataInput);
            dataInput.skipBytes(an.m5578a(dataInput));
            renderOpInfo = new RenderOpInfo(a2);
        } else {
            renderOpInfo = new RenderOpInfo(-1);
        }
        int a3;
        StyleInfo a4;
        switch (a) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                if (aeVar.m5449a() == 10) {
                    a3 = an.m5578a(dataInput);
                    while (i < a3) {
                        an.m5578a(dataInput);
                        i++;
                    }
                }
                return new ba(renderOpInfo.m6056a(), Style.m6090a());
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                Polyline a5 = Polyline.m6011a(dataInput, aeVar.m5451b());
                a4 = StyleInfo.m6111a(dataInput, aeVar);
                int a6 = an.m5578a(dataInput);
                bk[] bkVarArr = new bk[a6];
                for (a3 = 0; a3 < a6; a3++) {
                    bkVarArr[a3] = bk.m5837a(dataInput, aeVar, a4);
                }
                int a7 = an.m5578a(dataInput);
                byte readByte = dataInput.readByte();
                a3 = dataInput.readInt();
                if (ModelUtil.m5887a(1, a3)) {
                    featureId = FeatureId.m11290a(dataInput);
                } else if (ModelUtil.m5887a(2, a3)) {
                    featureId = FeatureId.m11293b(dataInput);
                }
                int i2 = a3 >>> 2;
                a3 = an.m5578a(dataInput);
                int[] iArr = new int[a3];
                while (i < a3) {
                    iArr[i] = an.m5578a(dataInput);
                    i++;
                }
                return new Road(renderOpInfo.m6056a(), featureId, a5, bkVarArr, a4.m6112a(), a4.m6114c(), a4.m6113b(), a7, readByte, i2, iArr);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                al a8 = al.m5569a(dataInput, aeVar.m5451b());
                byte[] bArr = new byte[a8.m5571a()];
                dataInput.readFully(bArr);
                a4 = StyleInfo.m6111a(dataInput, aeVar);
                byte readByte2 = dataInput.readByte();
                int readInt = dataInput.readInt();
                if (ModelUtil.m5887a(1, readInt)) {
                    featureId = FeatureId.m11290a(dataInput);
                } else if (ModelUtil.m5887a(2, readInt)) {
                    featureId = FeatureId.m11293b(dataInput);
                }
                a3 = an.m5578a(dataInput);
                int[] iArr2 = new int[a3];
                while (i < a3) {
                    iArr2[i] = an.m5578a(dataInput);
                    i++;
                }
                return new av(renderOpInfo.m6056a(), featureId, a8, bArr, a4.m6112a(), a4.m6114c(), a4.m6113b(), readByte2, readInt, iArr2);
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return aw.m5707a(dataInput, aeVar, renderOpInfo);
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                Polyline a9 = Polyline.m6011a(dataInput, aeVar.m5451b());
                StyleInfo a10 = StyleInfo.m6111a(dataInput, aeVar);
                byte readByte3 = dataInput.readByte();
                int readInt2 = dataInput.readInt();
                a3 = an.m5578a(dataInput);
                int[] iArr3 = new int[a3];
                while (i < a3) {
                    iArr3[i] = an.m5578a(dataInput);
                    i++;
                }
                return new bo(renderOpInfo.m6056a(), a9, a10.m6112a(), a10.m6114c(), a10.m6113b(), readByte3, readInt2, iArr3);
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                int a11 = an.m5578a(dataInput);
                byte[] bArr2 = new byte[dataInput.readInt()];
                dataInput.readFully(bArr2);
                byte readByte4 = dataInput.readByte();
                a3 = an.m5578a(dataInput);
                int[] iArr4 = new int[a3];
                while (i < a3) {
                    iArr4[i] = an.m5578a(dataInput);
                    i++;
                }
                return new Raster(renderOpInfo.m6056a(), a11, bArr2, readByte4, Style.m6090a(), iArr4);
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                return PointOfInterest.m5967a(dataInput, aeVar, renderOpInfo);
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                return bm.m5848a(dataInput, aeVar, renderOpInfo);
            case HTTP.HT /*9*/:
                return aj.m5552a(dataInput, aeVar, renderOpInfo);
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                return bn.m5861b(dataInput, aeVar, renderOpInfo);
            default:
                throw new IOException("Unknown feature type: " + a);
        }
    }

    public final ac m5630a() {
        return this.f3494a;
    }

    public final int m5636c() {
        return this.f3495b;
    }

    public final byte m5645l() {
        return this.f3496c;
    }

    private boolean m5627j() {
        return (this.f3496c & 2) != 0;
    }

    public final boolean m5639e() {
        if (ServerControlledParametersManager.m4808c() == null || ServerControlledParametersManager.m4808c().m4822c()) {
            boolean z;
            if ((this.f3496c & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            if (z || !m5627j()) {
                return true;
            }
            return false;
        } else if (m5627j()) {
            return false;
        } else {
            return true;
        }
    }

    public String[] m5640f() {
        return this.f3499f;
    }

    public final int m5646m() {
        return this.f3501h;
    }

    public String[] m5641g() {
        return this.f3500g;
    }

    public int m5642h() {
        return this.f3497d.length;
    }

    public bc m5631a(int i) {
        return this.f3497d[i];
    }

    protected final bc[] m5647n() {
        return this.f3497d;
    }

    public VectorTile m5643i() {
        return new VectorTile();
    }

    public final int m5648o() {
        return this.f3503j != null ? this.f3503j.length : 0;
    }

    public final ap m5634b(int i) {
        return this.f3503j != null ? this.f3503j[i] : null;
    }

    private static void m5624a(DataInput dataInput) {
        int readInt = dataInput.readInt();
        if (readInt != 1146241364) {
            throw new IOException("TILE_MAGIC expected. Found: " + readInt);
        }
    }

    public long m5644k() {
        return this.f3498e;
    }

    public boolean m5632a(Clock clock) {
        return this.f3498e >= 0 && clock.m10152b() > this.f3498e;
    }

    public final ai m5633b() {
        return this.f3502i;
    }

    public final int m5638d() {
        return this.f3504k;
    }

    public final boolean m5635b(Clock clock) {
        return this.f3505l >= 0 && clock.m10152b() > this.f3505l;
    }

    public final long m5649p() {
        return this.f3505l;
    }

    public final void m5637c(Clock clock) {
        if (m5629r() > 0) {
            this.f3505l = clock.m10152b() + m5629r();
        } else {
            this.f3505l = -1;
        }
    }

    private static long m5629r() {
        GmmSettings.m11527a();
        return (!GmmSettings.m11532f() || ServerControlledParametersManager.m4808c() == null) ? -1 : ServerControlledParametersManager.m4808c().m4820a();
    }

    public static int m5628q() {
        if (m5629r() == -1) {
            return -1;
        }
        return (int) (m5629r() / Constants.MILLIS_IN_AN_HOUR);
    }

    public static boolean m5625a(aa aaVar) {
        return (aaVar.m5411b() == ai.f3439q && ((aq) aaVar).m5648o() == 0) || (aaVar instanceof MockTile);
    }
}
