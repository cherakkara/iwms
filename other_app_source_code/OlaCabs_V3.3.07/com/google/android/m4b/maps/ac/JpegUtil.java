package com.google.android.m4b.maps.ac;

import android.support.v4.view.MotionEventCompat;
import com.olacabs.customer.p076d.by;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.ac.c */
public final class JpegUtil {
    private static final byte[][] f3056a;
    private static final int[] f3057b;

    static {
        f3056a = new byte[][]{new byte[]{(byte) 16, (byte) 11, (byte) 12, (byte) 14, (byte) 12, (byte) 10, (byte) 16, (byte) 14, (byte) 13, (byte) 14, (byte) 18, (byte) 17, (byte) 16, (byte) 19, (byte) 24, (byte) 40, (byte) 26, (byte) 24, (byte) 22, (byte) 22, (byte) 24, (byte) 49, (byte) 35, (byte) 37, (byte) 29, (byte) 40, (byte) 58, (byte) 51, (byte) 61, (byte) 60, (byte) 57, (byte) 51, (byte) 56, (byte) 55, (byte) 64, (byte) 72, (byte) 92, (byte) 78, (byte) 64, (byte) 68, (byte) 87, (byte) 69, (byte) 55, (byte) 56, (byte) 80, (byte) 109, (byte) 81, (byte) 87, (byte) 95, (byte) 98, (byte) 103, (byte) 104, (byte) 103, (byte) 62, (byte) 77, (byte) 113, (byte) 121, (byte) 112, (byte) 100, (byte) 120, (byte) 92, (byte) 101, (byte) 103, (byte) 99}, new byte[]{(byte) 17, (byte) 18, (byte) 18, (byte) 24, (byte) 21, (byte) 24, (byte) 47, (byte) 26, (byte) 26, (byte) 47, (byte) 99, (byte) 66, (byte) 56, (byte) 66, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99, (byte) 99}};
        f3057b = new int[]{-1, 1677721601, 838860801, 559240577, 419430401, 335544321, 279620289, 239674513, 209715201, 186413505, 167772161, 152520145, 139810145, 129055513, 119837257, 111848105, 104857601, 98689505, 93206753, 88301137, 83886081, 79891505, 76260073, 72944417, 69905073, 67108865, 64527757, 62137837, 59918629, 57852473, 55924053, 54120053, 52428801, 50840049, 49344753, 47934905, 46603377, 45343829, 44150569, 43018505, 41943041, 40920041, 39945753, 39016781, 38130037, 37282705, 36472209, 35696205, 34952537, 34239217, 33554433, 32883345, 32212257, 31541169, 30870077, 30198989, 29527901, 28856813, 28185725, 27514637, 26843545, 26172457, 25501369, 24830281, 24159193, 23488105, 22817013, 22145925, 21474837, 20803749, 20132661, 19461573, 18790481, 18119393, 17448305, 16777217, 16106129, 15435041, 14763953, 14092861, 13421773, 12750685, 12079597, 11408509, 10737421, 10066329, 9395241, 8724153, 8053065, 7381977, 6710889, 6039797, 5368709, 4697621, 4026533, 3355445, 2684357, 2013265, 1342177, 671089, 1};
    }

    public static synchronized byte[] m4855a(int i, int i2, int i3) {
        byte[] bArr;
        synchronized (JpegUtil.class) {
            bArr = new byte[64];
            byte[] bArr2 = f3056a[i];
            for (int i4 = 0; i4 < 64; i4++) {
                int i5;
                int i6 = bArr2[i4] & MotionEventCompat.ACTION_MASK;
                switch (i3) {
                    case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                        if (i6 != 99 || i2 != 36) {
                            i5 = (int) ((((((long) i6) * ((long) f3057b[i2])) / 16777216) + 1) / 2);
                            break;
                        }
                        i5 = 138;
                        break;
                        break;
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        i5 = (((i2 < 50 ? Math.min(by.DEFAULT_TIMEOUT_MS / i2, by.DEFAULT_TIMEOUT_MS) : Math.max(200 - (i2 * 2), 0)) * i6) + 50) / 100;
                        break;
                    default:
                        throw new IllegalArgumentException("qualityAlgorithm");
                }
                if (i5 <= 0) {
                    i5 = 1;
                } else if (i5 > MotionEventCompat.ACTION_MASK) {
                    i5 = MotionEventCompat.ACTION_MASK;
                }
                bArr[i4] = (byte) i5;
            }
        }
        return bArr;
    }

    private static byte[] m4857a(byte[] bArr, int i, int i2) {
        if (bArr[0] == -1 && bArr[1] == -40) {
            Object obj = new byte[i2];
            System.arraycopy(bArr, 0, obj, 0, i2);
            return obj;
        } else if (bArr[0] == 67 && bArr[1] == 74 && bArr[2] == 80 && bArr[3] == 71) {
            int i3 = bArr[4] & MotionEventCompat.ACTION_MASK;
            int i4 = (bArr[6] & MotionEventCompat.ACTION_MASK) | ((bArr[5] & MotionEventCompat.ACTION_MASK) << 8);
            int i5 = (bArr[8] & MotionEventCompat.ACTION_MASK) | ((bArr[7] & MotionEventCompat.ACTION_MASK) << 8);
            int i6 = bArr[9] & MotionEventCompat.ACTION_MASK;
            int i7 = bArr[10] & MotionEventCompat.ACTION_MASK;
            try {
                int a = GenerateJpegHeader.m4847a(i3);
                Object obj2 = new byte[((a + i2) - 11)];
                JpegHeaderParams jpegHeaderParams = new JpegHeaderParams(i3, i4, i5, i6, i7, a);
                int i8 = i2 - 11;
                i4 = jpegHeaderParams.m4850a();
                i5 = jpegHeaderParams.m4851b();
                i6 = jpegHeaderParams.m4852c();
                i7 = jpegHeaderParams.m4853d();
                a = jpegHeaderParams.m4854e();
                if (i4 != 0) {
                    throw new IllegalArgumentException("variant");
                }
                System.arraycopy(bArr, 11, obj2, GenerateJpegHeader.m4847a(i4) + 0, i8);
                GenerateJpegHeader.m4848a(obj2, 0, i4, i5, i6, i7, a);
                return obj2;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unknown variant " + i3);
            }
        } else {
            throw new IllegalArgumentException("Input is not in compact JPEG format");
        }
    }

    public static byte[] m4856a(byte[] bArr) {
        return JpegUtil.m4857a(bArr, 0, bArr.length);
    }
}
