package com.leanplum;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import org.apache.http.protocol.HTTP;

/* renamed from: com.leanplum.p */
final class C0666p {
    private static final List<Integer> f8854n;
    private static final List<Integer> f8855o;
    private aV f8856a;
    private boolean f8857b;
    private int f8858c;
    private boolean f8859d;
    private boolean f8860e;
    private int f8861f;
    private int f8862g;
    private int f8863h;
    private int f8864i;
    private byte[] f8865j;
    private byte[] f8866k;
    private boolean f8867l;
    private ByteArrayOutputStream f8868m;

    static {
        f8854n = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10)});
        f8855o = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2)});
    }

    public C0666p(aV aVVar) {
        this.f8857b = true;
        this.f8865j = new byte[0];
        this.f8866k = new byte[0];
        this.f8867l = false;
        this.f8868m = new ByteArrayOutputStream();
        this.f8856a = aVVar;
    }

    private static byte[] m12786a(byte[] bArr, byte[] bArr2, int i) {
        if (bArr2.length != 0) {
            for (int i2 = 0; i2 < bArr.length - i; i2++) {
                bArr[i + i2] = (byte) (bArr[i + i2] ^ bArr2[i2 % 4]);
            }
        }
        return bArr;
    }

    public final void m12788a(C0667q c0667q) {
        while (c0667q.available() != -1) {
            int i;
            int i2;
            switch (this.f8858c) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    byte readByte = c0667q.readByte();
                    i = (readByte & 64) == 64 ? 1 : 0;
                    int i3 = (readByte & 32) == 32 ? 1 : 0;
                    i2 = (readByte & 16) == 16 ? 1 : 0;
                    if (i == 0 && i3 == 0 && i2 == 0) {
                        this.f8859d = (readByte & AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) == AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS;
                        this.f8861f = readByte & 15;
                        this.f8865j = new byte[0];
                        this.f8866k = new byte[0];
                        if (!f8854n.contains(Integer.valueOf(this.f8861f))) {
                            throw new C0668r("Bad opcode");
                        } else if (f8855o.contains(Integer.valueOf(this.f8861f)) || this.f8859d) {
                            this.f8858c = 1;
                            break;
                        } else {
                            throw new C0668r("Expected non-final packet");
                        }
                    }
                    throw new C0668r("RSV not zero");
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    byte readByte2 = c0667q.readByte();
                    this.f8860e = (readByte2 & AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) == AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS;
                    this.f8863h = readByte2 & TransportMediator.KEYCODE_MEDIA_PAUSE;
                    if (this.f8863h >= 0 && this.f8863h <= 125) {
                        this.f8858c = this.f8860e ? 3 : 4;
                        break;
                    }
                    this.f8862g = this.f8863h == TransportMediator.KEYCODE_MEDIA_PLAY ? 2 : 8;
                    this.f8858c = 2;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    byte[] a = c0667q.m12790a(this.f8862g);
                    int length = a.length;
                    if (a.length >= length) {
                        long j = 0;
                        for (i2 = 0; i2 < length; i2++) {
                            j += (long) ((a[i2] & MotionEventCompat.ACTION_MASK) << (((length - 1) - i2) << 3));
                        }
                        if (j >= 0 && j <= 2147483647L) {
                            this.f8863h = (int) j;
                            this.f8858c = this.f8860e ? 3 : 4;
                            break;
                        }
                        throw new C0668r("Bad integer: " + j);
                    }
                    throw new IllegalArgumentException("length must be less than or equal to b.length");
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    this.f8865j = c0667q.m12790a(4);
                    this.f8858c = 4;
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    this.f8866k = c0667q.m12790a(this.f8863h);
                    Object a2 = C0666p.m12786a(this.f8866k, this.f8865j, 0);
                    i2 = this.f8861f;
                    if (i2 == 0) {
                        if (this.f8864i == 0) {
                            throw new C0668r("Mode was not set.");
                        }
                        this.f8868m.write(a2);
                        if (this.f8859d) {
                            byte[] toByteArray = this.f8868m.toByteArray();
                            if (this.f8864i == 1) {
                                this.f8856a.m12701a().m12643a(C0666p.m12784a(toByteArray));
                            } else {
                                this.f8856a.m12701a().m12640a();
                            }
                            this.f8864i = 0;
                            this.f8868m.reset();
                        }
                    } else if (i2 == 1) {
                        if (this.f8859d) {
                            this.f8856a.m12701a().m12643a(C0666p.m12784a((byte[]) a2));
                        } else {
                            this.f8864i = 1;
                            this.f8868m.write(a2);
                        }
                    } else if (i2 == 2) {
                        if (this.f8859d) {
                            this.f8856a.m12701a().m12640a();
                        } else {
                            this.f8864i = 2;
                            this.f8868m.write(a2);
                        }
                    } else if (i2 == 8) {
                        String a3;
                        i2 = a2.length >= 2 ? (a2[0] * AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH) + a2[1] : 0;
                        if (a2.length > 2) {
                            i = a2.length;
                            if (2 > i) {
                                throw new IllegalArgumentException();
                            }
                            int length2 = a2.length;
                            if (2 > length2) {
                                throw new ArrayIndexOutOfBoundsException();
                            }
                            i -= 2;
                            length2 = Math.min(i, length2 - 2);
                            byte[] bArr = new byte[i];
                            System.arraycopy(a2, 2, bArr, 0, length2);
                            a3 = C0666p.m12784a(bArr);
                        } else {
                            a3 = null;
                        }
                        this.f8856a.m12701a().m12641a(i2, a3);
                    } else if (i2 == 9) {
                        if (a2.length > 125) {
                            throw new C0668r("Ping payload too large");
                        }
                        this.f8856a.m12703a(m12785a(a2, 10, -1));
                    }
                    this.f8858c = 0;
                    break;
                default:
                    break;
            }
        }
        this.f8856a.m12701a().m12641a(0, "EOF");
    }

    public final byte[] m12789a(String str) {
        return m12785a((Object) str, 1, -1);
    }

    private byte[] m12785a(Object obj, int i, int i2) {
        if (obj instanceof String) {
            obj = C0666p.m12787b((String) obj);
        } else {
            byte[] bArr = (byte[]) obj;
        }
        int i3 = i2 > 0 ? 2 : 0;
        int length = obj.length + i3;
        int i4 = length <= 125 ? 2 : length <= SupportMenu.USER_MASK ? 4 : 10;
        int i5 = i4 + (this.f8857b ? 4 : 0);
        int i6 = this.f8857b ? AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS : 0;
        byte[] bArr2 = new byte[(length + i5)];
        bArr2[0] = (byte) (((byte) i) | -128);
        if (length <= 125) {
            bArr2[1] = (byte) (i6 | length);
        } else if (length <= SupportMenu.USER_MASK) {
            bArr2[1] = (byte) (i6 | TransportMediator.KEYCODE_MEDIA_PLAY);
            bArr2[2] = (byte) ((int) Math.floor((double) (length / AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH)));
            bArr2[3] = (byte) length;
        } else {
            bArr2[1] = (byte) (i6 | TransportMediator.KEYCODE_MEDIA_PAUSE);
            bArr2[2] = (byte) ((int) Math.floor(((double) length) / Math.pow(2.0d, 56.0d)));
            bArr2[3] = (byte) ((int) Math.floor(((double) length) / Math.pow(2.0d, 48.0d)));
            bArr2[4] = (byte) ((int) Math.floor(((double) length) / Math.pow(2.0d, 40.0d)));
            bArr2[5] = (byte) ((int) Math.floor(((double) length) / Math.pow(2.0d, 32.0d)));
            bArr2[6] = (byte) ((int) Math.floor(((double) length) / Math.pow(2.0d, 24.0d)));
            bArr2[7] = (byte) ((int) Math.floor(((double) length) / Math.pow(2.0d, 16.0d)));
            bArr2[8] = (byte) ((int) Math.floor(((double) length) / Math.pow(2.0d, 8.0d)));
            bArr2[9] = (byte) length;
        }
        if (i2 > 0) {
            bArr2[i5] = (byte) ((int) Math.floor((double) (i2 / AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH)));
            bArr2[i5 + 1] = (byte) i2;
        }
        System.arraycopy(obj, 0, bArr2, i3 + i5, obj.length);
        if (this.f8857b) {
            byte[] bArr3 = new byte[]{(byte) ((int) Math.floor(Math.random() * 256.0d)), (byte) ((int) Math.floor(Math.random() * 256.0d)), (byte) ((int) Math.floor(Math.random() * 256.0d)), (byte) ((int) Math.floor(Math.random() * 256.0d))};
            System.arraycopy(bArr3, 0, bArr2, i4, bArr3.length);
            C0666p.m12786a(bArr2, bArr3, i5);
        }
        return bArr2;
    }

    private static String m12784a(byte[] bArr) {
        try {
            return new String(bArr, HTTP.UTF_8);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] m12787b(String str) {
        try {
            return str.getBytes(HTTP.UTF_8);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
