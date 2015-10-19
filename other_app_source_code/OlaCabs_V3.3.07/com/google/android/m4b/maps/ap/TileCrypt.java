package com.google.android.m4b.maps.ap;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.InputStream;

/* renamed from: com.google.android.m4b.maps.ap.i */
public final class TileCrypt {
    private static final byte[] f3826a;
    private static final byte[] f3827e;
    private static final byte[] f3828f;
    private final byte[] f3829b;
    private int f3830c;
    private int f3831d;

    static {
        f3826a = new byte[AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH];
        for (int i = 0; i < AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH; i++) {
            f3826a[i] = (byte) i;
        }
        f3827e = new byte[]{(byte) 90, (byte) -18, (byte) 3, (byte) -99, (byte) 14, (byte) -41, (byte) 106, (byte) -78, (byte) 116, (byte) 63, (byte) 54, (byte) 80, (byte) 40, (byte) -121, (byte) -32, (byte) -17};
        f3828f = new byte[16];
    }

    public TileCrypt() {
        this.f3829b = new byte[AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH];
    }

    public final void m6303a(byte[] bArr, int i) {
        System.arraycopy(f3826a, 0, this.f3829b, 0, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        int i2 = 0;
        for (int i3 = 0; i3 < AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH; i3++) {
            i2 = ((i2 + this.f3829b[i3]) + bArr[i3 % 40]) & MotionEventCompat.ACTION_MASK;
            byte b = this.f3829b[i3];
            this.f3829b[i3] = this.f3829b[i2];
            this.f3829b[i2] = b;
        }
        this.f3830c = 0;
        this.f3831d = 0;
        m6298a((int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
    }

    public final void m6305b(byte[] bArr, int i) {
        System.arraycopy(f3826a, 0, this.f3829b, 0, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        int i2 = 0;
        for (int i3 = 0; i3 < AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH; i3++) {
            i2 = ((i2 + this.f3829b[i3]) + bArr[i3 & 31]) & MotionEventCompat.ACTION_MASK;
            byte b = this.f3829b[i3];
            this.f3829b[i3] = this.f3829b[i2];
            this.f3829b[i2] = b;
        }
        this.f3830c = 0;
        this.f3831d = 0;
        m6298a((int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
    }

    private void m6298a(int i) {
        int i2 = this.f3830c;
        int i3 = this.f3831d;
        for (int i4 = 0; i4 < i; i4++) {
            i2 = (i2 + 1) & MotionEventCompat.ACTION_MASK;
            i3 = (i3 + this.f3829b[i2]) & MotionEventCompat.ACTION_MASK;
            byte b = this.f3829b[i2];
            this.f3829b[i2] = this.f3829b[i3];
            this.f3829b[i3] = b;
        }
        this.f3830c = i2;
        this.f3831d = i3;
    }

    public final void m6304a(byte[] bArr, int i, int i2) {
        int i3 = this.f3830c;
        int i4 = this.f3831d;
        int i5 = 0;
        while (i5 < i2) {
            i3 = (i3 + 1) & MotionEventCompat.ACTION_MASK;
            i4 = (i4 + this.f3829b[i3]) & MotionEventCompat.ACTION_MASK;
            byte b = this.f3829b[i3];
            byte b2 = this.f3829b[i4];
            this.f3829b[i3] = b2;
            this.f3829b[i4] = b;
            int i6 = i + 1;
            bArr[i] = (byte) (this.f3829b[(b + b2) & MotionEventCompat.ACTION_MASK] ^ bArr[i]);
            i5++;
            i = i6;
        }
        this.f3830c = i3;
        this.f3831d = i4;
    }

    public static void m6299a(int i, int i2, int i3, int i4, int i5, long j, byte[] bArr) {
        TileCrypt.m6302a(bArr);
        if (i4 < 8) {
            i4 = 0;
        }
        bArr[16] = (byte) (i >> 24);
        bArr[17] = (byte) (i >> 16);
        bArr[18] = (byte) (i >> 8);
        bArr[19] = (byte) i;
        bArr[20] = (byte) (i2 >> 24);
        bArr[21] = (byte) (i2 >> 16);
        bArr[22] = (byte) (i2 >> 8);
        bArr[23] = (byte) i2;
        bArr[24] = (byte) (i3 >> 24);
        bArr[25] = (byte) (i3 >> 16);
        bArr[26] = (byte) (i3 >> 8);
        bArr[27] = (byte) i3;
        int i6 = i4 & SupportMenu.USER_MASK;
        bArr[28] = (byte) (i6 >> 8);
        bArr[29] = (byte) i6;
        i6 = i5 & SupportMenu.USER_MASK;
        bArr[30] = (byte) (i6 >> 8);
        bArr[31] = (byte) i6;
        bArr[32] = (byte) ((int) (j >> 56));
        bArr[33] = (byte) ((int) (j >> 48));
        bArr[34] = (byte) ((int) (j >> 40));
        bArr[35] = (byte) ((int) (j >> 32));
        bArr[36] = (byte) ((int) (j >> 24));
        bArr[37] = (byte) ((int) (j >> 16));
        bArr[38] = (byte) ((int) (j >> 8));
        bArr[39] = (byte) ((int) j);
    }

    public static void m6300a(int i, int i2, int i3, int i4, byte[] bArr) {
        TileCrypt.m6302a(bArr);
        bArr[16] = (byte) (i >> 24);
        bArr[17] = (byte) (i >> 16);
        bArr[18] = (byte) (i >> 8);
        bArr[19] = (byte) i;
        bArr[20] = (byte) (i2 >> 24);
        bArr[21] = (byte) (i2 >> 16);
        bArr[22] = (byte) (i2 >> 8);
        bArr[23] = (byte) i2;
        bArr[24] = (byte) (i3 >> 24);
        bArr[25] = (byte) (i3 >> 16);
        bArr[26] = (byte) (i3 >> 8);
        bArr[27] = (byte) i3;
        bArr[28] = (byte) (i4 >> 24);
        bArr[29] = (byte) (i4 >> 16);
        bArr[30] = (byte) (i4 >> 8);
        bArr[31] = (byte) i4;
    }

    public static void m6301a(InputStream inputStream) {
        inputStream.read(f3828f);
        inputStream.close();
    }

    private static void m6302a(byte[] bArr) {
        int i = 0;
        while (true) {
            byte[] bArr2 = f3827e;
            if (i < 16) {
                bArr[i] = (byte) ((f3827e[i] * 47) ^ f3828f[i]);
                i++;
            } else {
                return;
            }
        }
    }
}
