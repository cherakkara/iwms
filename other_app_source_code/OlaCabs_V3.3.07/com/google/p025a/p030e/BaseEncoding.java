package com.google.p025a.p030e;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.p025a.p026a.CharMatcher;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p030e.GwtWorkarounds.GwtWorkarounds;
import com.google.p025a.p031f.IntMath;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.Nullable;

/* renamed from: com.google.a.e.a */
public abstract class BaseEncoding {
    private static final BaseEncoding f1885a;
    private static final BaseEncoding f1886b;
    private static final BaseEncoding f1887c;
    private static final BaseEncoding f1888d;
    private static final BaseEncoding f1889e;

    /* renamed from: com.google.a.e.a.a */
    private static final class BaseEncoding extends CharMatcher {
        final int f1872p;
        final int f1873q;
        final int f1874r;
        final int f1875s;
        private final String f1876t;
        private final char[] f1877u;
        private final byte[] f1878v;
        private final boolean[] f1879w;

        BaseEncoding(String str, char[] cArr) {
            int i = 0;
            this.f1876t = (String) Preconditions.m1817a((Object) str);
            this.f1877u = (char[]) Preconditions.m1817a((Object) cArr);
            try {
                this.f1873q = IntMath.m3005a(cArr.length, RoundingMode.UNNECESSARY);
                int min = Math.min(8, Integer.lowestOneBit(this.f1873q));
                this.f1874r = 8 / min;
                this.f1875s = this.f1873q / min;
                this.f1872p = cArr.length - 1;
                byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS];
                Arrays.fill(bArr, (byte) -1);
                for (min = 0; min < cArr.length; min++) {
                    char c = cArr[min];
                    Preconditions.m1824a(CharMatcher.f1321b.m1741a(c), "Non-ASCII character: %s", Character.valueOf(c));
                    Preconditions.m1824a(bArr[c] == (byte) -1, "Duplicate character: %s", Character.valueOf(c));
                    bArr[c] = (byte) min;
                }
                this.f1878v = bArr;
                boolean[] zArr = new boolean[this.f1874r];
                while (i < this.f1875s) {
                    zArr[IntMath.m3004a(i * 8, this.f1873q, RoundingMode.CEILING)] = true;
                    i++;
                }
                this.f1879w = zArr;
            } catch (Throwable e) {
                throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e);
            }
        }

        char m2978a(int i) {
            return this.f1877u[i];
        }

        public boolean m2979a(char c) {
            return CharMatcher.f1321b.m1741a(c) && this.f1878v[c] != -1;
        }

        public String toString() {
            return this.f1876t;
        }
    }

    /* renamed from: com.google.a.e.a.b */
    static final class BaseEncoding extends BaseEncoding {
        private final BaseEncoding f1890a;
        @Nullable
        private final Character f1891b;

        /* renamed from: com.google.a.e.a.b.1 */
        class BaseEncoding implements GwtWorkarounds {
            int f1880a;
            int f1881b;
            int f1882c;
            final /* synthetic */ GwtWorkarounds f1883d;
            final /* synthetic */ BaseEncoding f1884e;

            BaseEncoding(BaseEncoding baseEncoding, GwtWorkarounds gwtWorkarounds) {
                this.f1884e = baseEncoding;
                this.f1883d = gwtWorkarounds;
                this.f1880a = 0;
                this.f1881b = 0;
                this.f1882c = 0;
            }

            public void m2983a(byte b) throws IOException {
                this.f1880a <<= 8;
                this.f1880a |= b & MotionEventCompat.ACTION_MASK;
                this.f1881b += 8;
                while (this.f1881b >= this.f1884e.f1890a.f1873q) {
                    this.f1883d.m2996a(this.f1884e.f1890a.m2978a((this.f1880a >> (this.f1881b - this.f1884e.f1890a.f1873q)) & this.f1884e.f1890a.f1872p));
                    this.f1882c++;
                    this.f1881b -= this.f1884e.f1890a.f1873q;
                }
            }

            public void m2982a() throws IOException {
                if (this.f1881b > 0) {
                    this.f1883d.m2996a(this.f1884e.f1890a.m2978a((this.f1880a << (this.f1884e.f1890a.f1873q - this.f1881b)) & this.f1884e.f1890a.f1872p));
                    this.f1882c++;
                    if (this.f1884e.f1891b != null) {
                        while (this.f1882c % this.f1884e.f1890a.f1874r != 0) {
                            this.f1883d.m2996a(this.f1884e.f1891b.charValue());
                            this.f1882c++;
                        }
                    }
                }
                this.f1883d.m2995a();
            }
        }

        BaseEncoding(String str, String str2, @Nullable Character ch) {
            this(new BaseEncoding(str, str2.toCharArray()), ch);
        }

        BaseEncoding(BaseEncoding baseEncoding, Character ch) {
            boolean z;
            this.f1890a = (BaseEncoding) Preconditions.m1817a((Object) baseEncoding);
            if (ch == null || !baseEncoding.m2979a(ch.charValue())) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.m1824a(z, "Padding character %s was already in alphabet", ch);
            this.f1891b = ch;
        }

        int m2991a(int i) {
            return this.f1890a.f1874r * IntMath.m3004a(i, this.f1890a.f1875s, RoundingMode.CEILING);
        }

        GwtWorkarounds m2992a(GwtWorkarounds gwtWorkarounds) {
            Preconditions.m1817a((Object) gwtWorkarounds);
            return new BaseEncoding(this, gwtWorkarounds);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("BaseEncoding.");
            stringBuilder.append(this.f1890a.toString());
            if (8 % this.f1890a.f1873q != 0) {
                if (this.f1891b == null) {
                    stringBuilder.append(".omitPadding()");
                } else {
                    stringBuilder.append(".withPadChar(").append(this.f1891b).append(')');
                }
            }
            return stringBuilder.toString();
        }
    }

    abstract int m2985a(int i);

    abstract GwtWorkarounds m2986a(GwtWorkarounds gwtWorkarounds);

    BaseEncoding() {
    }

    public String m2987a(byte[] bArr) {
        return m2988a((byte[]) Preconditions.m1817a((Object) bArr), 0, bArr.length);
    }

    public final String m2988a(byte[] bArr, int i, int i2) {
        Preconditions.m1817a((Object) bArr);
        Preconditions.m1821a(i, i + i2, bArr.length);
        GwtWorkarounds a = GwtWorkarounds.m2999a(m2985a(i2));
        GwtWorkarounds a2 = m2986a(a);
        int i3 = 0;
        while (i3 < i2) {
            try {
                a2.m2981a(bArr[i + i3]);
                i3++;
            } catch (IOException e) {
                throw new AssertionError("impossible");
            }
        }
        a2.m2980a();
        return a.toString();
    }

    static {
        f1885a = new BaseEncoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", Character.valueOf('='));
        f1886b = new BaseEncoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", Character.valueOf('='));
        f1887c = new BaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", Character.valueOf('='));
        f1888d = new BaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", Character.valueOf('='));
        f1889e = new BaseEncoding("base16()", "0123456789ABCDEF", null);
    }

    public static BaseEncoding m2984a() {
        return f1886b;
    }
}
