package com.google.android.m4b.maps.bj;

import android.opengl.Matrix;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.FloatMath;
import com.google.android.m4b.maps.be.ar;
import com.google.android.m4b.maps.bx.Streetview;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p061y.ConversionUtils;
import com.olacabs.customer.p076d.br;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.bj.q */
public final class PanoramaConfig implements Parcelable {
    public static final Creator<PanoramaConfig> CREATOR;
    public int f6581A;
    private float f6582B;
    private float[] f6583C;
    private long f6584D;
    public boolean f6585a;
    public int f6586b;
    public int f6587c;
    public int f6588d;
    public int f6589e;
    public int f6590f;
    public int f6591g;
    public String f6592h;
    public LatLng f6593i;
    public int f6594j;
    public String f6595k;
    public String f6596l;
    public String f6597m;
    public int f6598n;
    public boolean f6599o;
    public boolean f6600p;
    public int f6601q;
    public List<ac> f6602r;
    public float f6603s;
    public float f6604t;
    public float f6605u;
    public float f6606v;
    public float f6607w;
    public PanoramaLink[] f6608x;
    public DepthMap f6609y;
    public float f6610z;

    /* renamed from: com.google.android.m4b.maps.bj.q.1 */
    static class PanoramaConfig implements Creator<PanoramaConfig> {
        PanoramaConfig() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new PanoramaConfig(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new PanoramaConfig[i];
        }
    }

    public static PanoramaConfig m9991a(InputStream inputStream) {
        SystemClock.uptimeMillis();
        ProtoBuf protoBuf = new ProtoBuf(Streetview.f6853a);
        protoBuf.m10187a(inputStream);
        return new PanoramaConfig(protoBuf);
    }

    public PanoramaConfig(Parcel parcel) {
        this.f6599o = false;
        this.f6600p = false;
        this.f6584D = System.currentTimeMillis();
        if (parcel.readInt() != 6) {
            throw new IllegalArgumentException("unknown version");
        }
        this.f6585a = parcel.readInt() != 0;
        this.f6586b = parcel.readInt();
        this.f6587c = 0;
        this.f6588d = parcel.readInt();
        this.f6589e = parcel.readInt();
        this.f6590f = parcel.readInt();
        this.f6591g = parcel.readInt();
        this.f6592h = parcel.readString();
        this.f6593i = new LatLng(((double) parcel.readInt()) * 1.0E-6d, ((double) parcel.readInt()) * 1.0E-6d);
        this.f6594j = parcel.readInt();
        this.f6595k = parcel.readString();
        this.f6596l = parcel.readString();
        this.f6597m = parcel.readString();
        this.f6598n = parcel.readInt();
        this.f6601q = parcel.readInt();
        this.f6603s = parcel.readFloat();
        this.f6604t = parcel.readFloat();
        this.f6605u = parcel.readFloat();
        this.f6606v = parcel.readFloat();
        this.f6607w = parcel.readFloat();
        this.f6608x = (PanoramaLink[]) parcel.createTypedArray(PanoramaLink.CREATOR);
        this.f6584D = parcel.readLong();
        try {
            this.f6609y = new DepthMap(parcel.createByteArray(), parcel.createByteArray());
        } catch (IOException e) {
            this.f6609y = null;
        }
        m9993e();
    }

    public PanoramaConfig(ProtoBuf protoBuf) {
        this.f6599o = false;
        this.f6600p = false;
        this.f6584D = System.currentTimeMillis();
        ProtoBuf g = protoBuf.m10211g(1);
        ProtoBuf g2 = protoBuf.m10211g(33);
        ProtoBuf g3 = protoBuf.m10211g(49);
        ProtoBuf g4 = protoBuf.m10211g(58);
        this.f6585a = g.m10200b(2);
        this.f6586b = g.m10204d(3);
        this.f6587c = PanoramaConfig.m9989a(g, 17, 0, 100000);
        if (this.f6586b != 0) {
            new StringBuilder("infoLevel=").append(this.f6586b).append(", infoValue=").append(this.f6587c);
        }
        this.f6588d = PanoramaConfig.m9989a(g, 4, 1, (int) AccessibilityNodeInfoCompat.ACTION_PASTE);
        this.f6589e = PanoramaConfig.m9989a(g, 5, 1, (int) AccessibilityNodeInfoCompat.ACTION_PASTE);
        this.f6590f = PanoramaConfig.m9989a(g, 6, 1, 2880);
        this.f6591g = PanoramaConfig.m9989a(g, 7, 1, 2880);
        this.f6592h = g.m10212h(8);
        this.f6594j = PanoramaConfig.m9989a(g, 9, 1, 14);
        ProtoBuf g5 = g.m10211g(10);
        this.f6593i = new LatLng(((double) g5.m10204d(1)) * 1.0E-6d, ((double) g5.m10204d(2)) * 1.0E-6d);
        this.f6595k = g.m10212h(11);
        this.f6596l = g.m10212h(12);
        this.f6597m = g.m10212h(13);
        this.f6598n = g.m10204d(61);
        this.f6603s = PanoramaConfig.m9987a(g2.m10204d(34));
        this.f6604t = PanoramaConfig.m9987a(g2.m10204d(35));
        float d = ((float) g2.m10204d(36)) * 1.0E-6f;
        if (d < -89.9f) {
            d = -89.9f;
        } else if (d > 89.9f) {
            d = 89.9f;
        }
        this.f6605u = d;
        this.f6601q = 0;
        if (g2.m10214j(38)) {
            switch (g2.m10204d(38)) {
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    this.f6601q = 0;
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    this.f6601q = 1;
                    break;
            }
        }
        this.f6606v = PanoramaConfig.m9988a(g2, 40, -90000000);
        this.f6607w = PanoramaConfig.m9988a(g2, 41, 90000000);
        if (this.f6606v > this.f6607w) {
            d = this.f6606v;
            this.f6606v = this.f6607w;
            this.f6607w = d;
        }
        int k = g3.m10215k(52);
        this.f6608x = new PanoramaLink[k];
        for (int i = 0; i < k; i++) {
            this.f6608x[i] = new PanoramaLink(g3.m10202c(52, i));
        }
        if (g4 != null && g4.m10214j(59) && g4.m10214j(60)) {
            try {
                this.f6609y = new DepthMap(g4.m10203c(59), g4.m10203c(60));
            } catch (IOException e) {
                this.f6609y = null;
            }
        }
        m9993e();
    }

    private void m9993e() {
        this.f6582B = ar.m8614o(this.f6604t);
        if (this.f6582B < 0.0f) {
            this.f6582B += 6.2831855f;
        }
        this.f6610z = ar.m8614o(this.f6605u);
        this.f6581A = ar.m8595a(this.f6588d, this.f6589e, this.f6590f, this.f6591g);
        this.f6602r = new ArrayList();
        switch (this.f6601q) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                this.f6602r.add(new ac(this.f6592h, 0, 0, -1, 0));
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f6602r.add(new ac(this.f6592h, 0, 0, 1, 0));
                this.f6602r.add(new ac(this.f6592h, 0, 0, 2, 0));
                this.f6602r.add(new ac(this.f6592h, 0, 0, 3, 0));
                this.f6602r.add(new ac(this.f6592h, 0, 0, 4, 0));
                this.f6602r.add(new ac(this.f6592h, 0, 0, 5, 0));
                this.f6602r.add(new ac(this.f6592h, 0, 0, 6, 0));
            default:
                throw new IllegalStateException("Unrecognized projection type.");
        }
    }

    public final String toString() {
        return this.f6592h + " text=\"" + this.f6596l + "\"";
    }

    public static String m9992a(String str) {
        return str == null ? null : "config_" + str;
    }

    public final boolean m9996a() {
        return System.currentTimeMillis() - this.f6584D > 7200000;
    }

    public final boolean m9997b() {
        return (this.f6586b & 64) != 0;
    }

    public final boolean m9998c() {
        return (this.f6586b & 65) != 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(6);
        parcel.writeInt(this.f6585a ? 1 : 0);
        parcel.writeInt(this.f6586b);
        parcel.writeInt(this.f6588d);
        parcel.writeInt(this.f6589e);
        parcel.writeInt(this.f6590f);
        parcel.writeInt(this.f6591g);
        parcel.writeString(this.f6592h);
        parcel.writeInt(ConversionUtils.m11630a(this.f6593i.f7554a));
        parcel.writeInt(ConversionUtils.m11630a(this.f6593i.f7555b));
        parcel.writeInt(this.f6594j);
        parcel.writeString(this.f6595k);
        parcel.writeString(this.f6596l);
        parcel.writeString(this.f6597m);
        parcel.writeInt(this.f6598n);
        parcel.writeInt(this.f6601q);
        parcel.writeFloat(this.f6603s);
        parcel.writeFloat(this.f6604t);
        parcel.writeFloat(this.f6605u);
        parcel.writeFloat(this.f6606v);
        parcel.writeFloat(this.f6607w);
        parcel.writeTypedArray(this.f6608x, i);
        parcel.writeLong(this.f6584D);
        if (this.f6609y != null) {
            parcel.writeByteArray(this.f6609y.m9965c());
            parcel.writeByteArray(this.f6609y.m9966d());
            return;
        }
        parcel.writeByteArray(new byte[0]);
        parcel.writeByteArray(new byte[0]);
    }

    public final int describeContents() {
        return 0;
    }

    static {
        CREATOR = new PanoramaConfig();
    }

    public final float[] m9999d() {
        if (this.f6583C == null) {
            this.f6583C = new float[16];
            float f = this.f6604t - this.f6603s;
            Matrix.setRotateM(this.f6583C, 0, f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f);
            Matrix.rotateM(this.f6583C, 0, this.f6605u, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f);
            Matrix.rotateM(this.f6583C, 0, -f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f);
        }
        return this.f6583C;
    }

    public final void m9995a(float f, float f2, float[] fArr) {
        fArr[0] = (f - ar.m8611l(this.f6603s)) - 0.5f;
        fArr[1] = f2 - (FloatMath.cos(ar.m8610k(f) - ar.m8614o(this.f6604t)) * ar.m8609j(this.f6610z));
    }

    public final void m9994a(float f, float f2, float f3, float[] fArr) {
        int i = 0;
        float[] fArr2 = new float[]{f, f2, f3, br.DEFAULT_BACKOFF_MULT};
        float[] fArr3 = new float[16];
        Matrix.setRotateM(fArr3, 0, this.f6603s + 180.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f);
        Matrix.multiplyMM(fArr3, 0, fArr3, 0, m9999d(), 0);
        Matrix.multiplyMV(fArr2, 0, fArr3, 0, fArr2, 0);
        while (i < 3) {
            fArr[i] = fArr2[i];
            i++;
        }
    }

    public static int m9990a(PanoramaLink[] panoramaLinkArr, float f, float f2) {
        int i = -1;
        float f3 = 120.0f;
        if (panoramaLinkArr != null) {
            int length = panoramaLinkArr.length;
            int i2 = 0;
            while (i2 < length) {
                int i3;
                float a = ar.m8590a(f - panoramaLinkArr[i2].f6621a);
                if (a > 180.0f) {
                    a = 360.0f - a;
                }
                if (a < f3) {
                    i3 = i2;
                } else {
                    a = f3;
                    i3 = i;
                }
                i2++;
                i = i3;
                f3 = a;
            }
        }
        return i;
    }

    private static int m9989a(ProtoBuf protoBuf, int i, int i2, int i3) {
        int d = protoBuf.m10204d(i);
        if (d < i2) {
            return i2;
        }
        return d > i3 ? i3 : d;
    }

    private static float m9988a(ProtoBuf protoBuf, int i, int i2) {
        int a = PanoramaConfig.m9989a(protoBuf, i, -90000000, 90000000);
        if (a != 0) {
            i2 = a;
        }
        return ((float) i2) * 1.0E-6f;
    }

    public static float m9987a(int i) {
        return ((float) (i % 360000000)) * 1.0E-6f;
    }
}
