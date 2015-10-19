package com.google.android.m4b.maps.ar;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.aa.ServerControlledParametersManager;
import com.google.android.m4b.maps.aa.VectorMapsParameters;
import com.google.android.m4b.maps.ad.RandomAccessible.RandomAccessible;
import com.google.android.m4b.maps.ap.RefCountUtil;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p041b.Utf8Encoder;
import com.google.android.m4b.maps.p049i.ByteArrayDataInput;
import com.google.android.m4b.maps.p058v.Util;
import com.newrelic.agent.android.api.v1.Defaults;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.zip.CRC32;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.ar.e */
public class GenericDiskCache {
    private static /* synthetic */ boolean f3921C;
    private static final byte[] f3922a;
    private static int f3923b;
    private static int f3924c;
    private static int f3925d;
    private Set<Integer> f3926A;
    private int f3927B;
    private final String f3928e;
    private final RandomAccessible f3929f;
    private com.google.android.m4b.maps.ad.RandomAccessible f3930g;
    private GenericDiskCache f3931h;
    private final GenericDiskCache f3932i;
    private final GenericDiskCache f3933j;
    private final com.google.android.m4b.maps.ad.RandomAccessible[] f3934k;
    private final LRUCache<Long, GenericDiskCache> f3935l;
    private final Set<GenericDiskCache> f3936m;
    private final ReentrantLock f3937n;
    private final ReentrantReadWriteLock f3938o;
    private boolean f3939p;
    private int f3940q;
    private int f3941r;
    private int f3942s;
    private int f3943t;
    private int f3944u;
    private int f3945v;
    private int f3946w;
    private boolean f3947x;
    private RemovalListener f3948y;
    private GenericDiskCacheListener f3949z;

    /* renamed from: com.google.android.m4b.maps.ar.e.a */
    static class GenericDiskCache {
        final byte[] f3874a;
        int f3875b;

        GenericDiskCache(int i, int i2) {
            this.f3874a = new byte[(i * Defaults.RESPONSE_BODY_LIMIT)];
            this.f3875b = i2;
        }

        final void m6365a(GenericDiskCache genericDiskCache) {
            m6364a(genericDiskCache.f3907b);
            for (int i = 0; i < genericDiskCache.m6386b(); i++) {
                long b = genericDiskCache.m6387b(i);
                if (b != -1) {
                    int a = genericDiskCache.f3907b;
                    int[] a2 = GenericDiskCache.m6363a(b);
                    a *= Defaults.RESPONSE_BODY_LIMIT;
                    for (int i2 = 0; i2 < 3; i2++) {
                        int i3 = a2[i2] >> 3;
                        int i4 = a2[i2] & 7;
                        byte[] bArr = this.f3874a;
                        i3 += a;
                        bArr[i3] = (byte) ((1 << i4) | bArr[i3]);
                    }
                }
            }
            if (genericDiskCache.f3907b >= this.f3875b) {
                this.f3875b = genericDiskCache.f3907b + 1;
            }
        }

        final void m6364a(int i) {
            int i2 = i * Defaults.RESPONSE_BODY_LIMIT;
            Arrays.fill(this.f3874a, i2, i2 + 1020, (byte) 0);
        }

        static int[] m6363a(long j) {
            int i = 0;
            int[] iArr = new int[3];
            long rotateRight = Long.rotateRight(j, 22);
            iArr[0] = (int) (rotateRight % 8147);
            rotateRight = Long.rotateRight(rotateRight, 22);
            iArr[1] = (int) (rotateRight % 8147);
            iArr[2] = (int) (Long.rotateRight(rotateRight, 22) % 8147);
            while (i < 3) {
                if (iArr[i] < 0) {
                    iArr[i] = iArr[i] + 8147;
                }
                i++;
            }
            return iArr;
        }

        final boolean m6366a(int[] iArr, int i) {
            int i2 = i * Defaults.RESPONSE_BODY_LIMIT;
            for (int i3 = 0; i3 < iArr.length; i3++) {
                if ((this.f3874a[(iArr[i3] >> 3) + i2] & (1 << (iArr[i3] & 7))) == 0) {
                    return false;
                }
            }
            return true;
        }

        final void m6367b(int i) {
            int i2 = i * Defaults.RESPONSE_BODY_LIMIT;
            GenericDiskCache.m6419a(this.f3874a, i2 + 1020, GenericDiskCache.m6429c(this.f3874a, i2, 1020));
        }
    }

    /* renamed from: com.google.android.m4b.maps.ar.e.b */
    static final class GenericDiskCache {
        private static final int f3876j;
        final int f3877a;
        final int f3878b;
        final int f3879c;
        final int f3880d;
        final boolean f3881e;
        final int f3882f;
        final long f3883g;
        final Locale f3884h;
        final int f3885i;
        private int f3886k;
        private int f3887l;

        static {
            f3876j = 43;
        }

        GenericDiskCache(int i, int i2, int i3, int i4, boolean z, int i5, long j, Locale locale) {
            this.f3877a = i;
            this.f3878b = i2;
            this.f3879c = i3;
            this.f3880d = i4;
            this.f3881e = z;
            this.f3882f = i5;
            this.f3884h = locale;
            this.f3883g = j;
            byte[] a = m6368a();
            this.f3886k = a.length;
            GenericDiskCache.m6419a(a, 4, this.f3886k);
            this.f3887l = GenericDiskCache.m6429c(a, 0, a.length - 4);
            this.f3885i = (((((this.f3879c * Defaults.RESPONSE_BODY_LIMIT) - 1) / AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD) + 1) * AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD) + AccessibilityNodeInfoCompat.ACTION_COPY;
        }

        GenericDiskCache(byte[] bArr, int i) {
            this.f3877a = GenericDiskCache.m6402a(bArr, 0);
            if (this.f3877a == 1) {
                throw new IOException("Can't parse header for old schema");
            }
            this.f3886k = GenericDiskCache.m6402a(bArr, 4);
            if (this.f3886k < 43 || this.f3886k + 0 > AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD) {
                throw new IOException("Wrong header size: " + this.f3886k);
            }
            this.f3887l = GenericDiskCache.m6402a(bArr, (this.f3886k + 0) - 4);
            int c = GenericDiskCache.m6429c(bArr, 0, this.f3886k - 4);
            if (this.f3887l != c) {
                throw new IOException("Checksum mismatch " + this.f3887l + " vs " + c);
            }
            ByteArrayDataInput byteArrayDataInput = new ByteArrayDataInput(bArr);
            byteArrayDataInput.skipBytes(8);
            this.f3878b = byteArrayDataInput.readInt();
            this.f3879c = byteArrayDataInput.readInt();
            this.f3880d = byteArrayDataInput.readInt();
            this.f3881e = byteArrayDataInput.readBoolean();
            this.f3882f = byteArrayDataInput.readInt();
            this.f3883g = byteArrayDataInput.readLong();
            this.f3884h = new Locale(byteArrayDataInput.readUTF(), byteArrayDataInput.readUTF(), byteArrayDataInput.readUTF());
            this.f3885i = (((((this.f3879c * Defaults.RESPONSE_BODY_LIMIT) - 1) / AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD) + 1) * AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD) + AccessibilityNodeInfoCompat.ACTION_COPY;
        }

        final int m6369a(byte[] bArr, int i) {
            Object a = m6368a();
            System.arraycopy(a, 0, bArr, 0, a.length);
            return a.length;
        }

        private byte[] m6368a() {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutput dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(this.f3877a);
            dataOutputStream.writeInt(this.f3886k);
            dataOutputStream.writeInt(this.f3878b);
            dataOutputStream.writeInt(this.f3879c);
            dataOutputStream.writeInt(this.f3880d);
            dataOutputStream.writeBoolean(this.f3881e);
            dataOutputStream.writeInt(this.f3882f);
            dataOutputStream.writeLong(this.f3883g);
            dataOutputStream.writeUTF(this.f3884h.getLanguage());
            dataOutputStream.writeUTF(this.f3884h.getCountry());
            dataOutputStream.writeUTF(this.f3884h.getVariant());
            dataOutputStream.writeInt(this.f3887l);
            return byteArrayOutputStream.toByteArray();
        }

        public final String toString() {
            return "CatalogVersion:" + this.f3877a + " BlockSize:" + this.f3878b + " MaxShardCount:" + this.f3879c + " RecordsPerBlock: " + this.f3880d + " AutoConfig: " + this.f3881e + " DataVersion:" + this.f3882f + " CacheCreationTimeMs:" + this.f3883g + " Checksum:" + this.f3887l;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ar.e.c */
    public static class GenericDiskCache {
        public final GenericDiskCache f3888a;
        public final byte[] f3889b;
        public final int f3890c;
        public final byte[] f3891d;

        private GenericDiskCache(GenericDiskCache genericDiskCache, byte[] bArr) {
            this(genericDiskCache, 0, bArr);
        }

        private GenericDiskCache(GenericDiskCache genericDiskCache, int i, byte[] bArr) {
            if (bArr.length > ViewCompat.MEASURED_SIZE_MASK) {
                throw new IllegalArgumentException("data too large");
            }
            this.f3888a = genericDiskCache;
            this.f3889b = genericDiskCache.m6371b();
            this.f3890c = i;
            this.f3891d = bArr;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ar.e.d */
    static class GenericDiskCache {
        private final long f3892a;
        private final byte[] f3893b;

        GenericDiskCache(long j, byte[] bArr) {
            this.f3892a = j;
            if (bArr == null) {
                this.f3893b = GenericDiskCache.f3922a;
            } else {
                this.f3893b = bArr;
            }
        }

        final long m6370a() {
            return this.f3892a;
        }

        final byte[] m6371b() {
            return this.f3893b;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            GenericDiskCache genericDiskCache = (GenericDiskCache) obj;
            if (this.f3892a == genericDiskCache.f3892a) {
                return Arrays.equals(this.f3893b, genericDiskCache.f3893b);
            }
            return false;
        }

        public final int hashCode() {
            return (((int) (this.f3892a ^ (this.f3892a >>> 32))) * 31) + Arrays.hashCode(this.f3893b);
        }
    }

    /* renamed from: com.google.android.m4b.maps.ar.e.e */
    static final class GenericDiskCache {
        private final byte[] f3894a;
        private int f3895b;
        private final com.google.android.m4b.maps.ad.RandomAccessible f3896c;
        private int f3897d;

        GenericDiskCache(com.google.android.m4b.maps.ad.RandomAccessible randomAccessible, int i, byte[] bArr) {
            this.f3894a = bArr;
            this.f3895b = 0;
            this.f3896c = randomAccessible;
            this.f3897d = i;
        }

        final void m6373a(byte[] bArr) {
            if (bArr.length + this.f3895b > this.f3894a.length) {
                m6372a();
            }
            if (bArr.length > this.f3894a.length) {
                synchronized (this.f3896c) {
                    this.f3896c.m4861a((long) this.f3897d);
                    this.f3896c.m4862a(bArr);
                    this.f3896c.m4864b();
                }
                this.f3897d += bArr.length;
                return;
            }
            System.arraycopy(bArr, 0, this.f3894a, this.f3895b, bArr.length);
            this.f3895b += bArr.length;
        }

        final void m6372a() {
            if (this.f3895b != 0) {
                synchronized (this.f3896c) {
                    this.f3896c.m4861a((long) this.f3897d);
                    this.f3896c.m4866b(this.f3894a, 0, this.f3895b);
                    this.f3896c.m4864b();
                }
                this.f3897d += this.f3895b;
                this.f3895b = 0;
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ar.e.f */
    static class GenericDiskCache {
        final long f3898a;
        final int f3899b;
        final int f3900c;
        final int f3901d;
        final int f3902e;
        final int f3903f;
        final int f3904g;
        final int f3905h;

        GenericDiskCache(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
            this.f3898a = j;
            this.f3899b = i;
            this.f3900c = i4;
            this.f3901d = i2;
            this.f3902e = i3;
            this.f3903f = i5;
            this.f3904g = i6;
            this.f3905h = i7;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            GenericDiskCache genericDiskCache = (GenericDiskCache) obj;
            if (this.f3904g == genericDiskCache.f3904g && this.f3905h == genericDiskCache.f3905h) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return (this.f3904g << 16) + this.f3905h;
        }

        final void m6378a(byte[] bArr, int i) {
            GenericDiskCache.m6420a(bArr, i, this.f3898a);
            int i2 = i + 8;
            int i3 = (this.f3899b << 5) | this.f3900c;
            if ((i3 >>> 5) != this.f3899b) {
                throw new IllegalArgumentException("Could not pack data offset of " + this.f3899b);
            } else if ((i3 & 31) != this.f3900c) {
                throw new IllegalArgumentException("Could not pack refCount of " + this.f3900c);
            } else {
                GenericDiskCache.m6419a(bArr, i2, i3);
                i2 += 4;
                GenericDiskCache.m6419a(bArr, i2, (this.f3901d << 24) | this.f3902e);
                GenericDiskCache.m6419a(bArr, i2 + 4, this.f3903f);
            }
        }

        static GenericDiskCache m6374a(byte[] bArr, int i, int i2, int i3) {
            long c = GenericDiskCache.m6430c(bArr, i);
            int i4 = i + 8;
            int a = GenericDiskCache.m6402a(bArr, i4);
            i4 += 4;
            int a2 = GenericDiskCache.m6402a(bArr, i4);
            return new GenericDiskCache(c, a >>> 5, a2 >>> 24, a2 & ViewCompat.MEASURED_SIZE_MASK, a & 31, GenericDiskCache.m6402a(bArr, i4 + 4), i2, i3);
        }

        static int m6375b(byte[] bArr, int i) {
            return GenericDiskCache.m6402a(bArr, (i + 8) + 4) >>> 24;
        }

        static int m6376c(byte[] bArr, int i) {
            return GenericDiskCache.m6402a(bArr, (i + 8) + 4) & ViewCompat.MEASURED_SIZE_MASK;
        }

        static int m6377d(byte[] bArr, int i) {
            return GenericDiskCache.m6402a(bArr, i + 8) & 31;
        }

        public final String toString() {
            return "ID:" + this.f3898a + " Off:" + this.f3899b + " KeyLen:" + this.f3901d + " DataLen:" + this.f3902e + " Checksum:" + this.f3903f + " Shard:" + this.f3904g + " ShardIndex:" + this.f3905h;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ar.e.g */
    static class GenericDiskCache {
        private final byte[] f3906a;
        private final int f3907b;
        private int f3908c;
        private int f3909d;
        private int f3910e;

        GenericDiskCache(int i) {
            this(i, new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD]);
        }

        private GenericDiskCache(int i, byte[] bArr) {
            this.f3909d = -1;
            this.f3910e = -1;
            this.f3906a = bArr;
            this.f3907b = i;
            this.f3908c = 0;
        }

        GenericDiskCache(byte[] bArr) {
            this.f3909d = -1;
            this.f3910e = -1;
            this.f3906a = bArr;
            this.f3907b = GenericDiskCache.m6402a(this.f3906a, 0);
            this.f3908c = GenericDiskCache.m6402a(this.f3906a, 4);
        }

        final void m6383a(com.google.android.m4b.maps.ad.RandomAccessible randomAccessible) {
            GenericDiskCache.m6419a(this.f3906a, 0, this.f3907b);
            GenericDiskCache.m6419a(this.f3906a, 4, this.f3908c);
            GenericDiskCache.m6419a(this.f3906a, 8188, GenericDiskCache.m6429c(this.f3906a, 0, 8188));
            randomAccessible.m4862a(this.f3906a);
        }

        final int m6381a() {
            return this.f3907b;
        }

        final int m6386b() {
            return this.f3908c;
        }

        final void m6382a(int i) {
            GenericDiskCache.m6420a(this.f3906a, (i * 20) + 8, -1);
        }

        final long m6387b(int i) {
            return GenericDiskCache.m6430c(this.f3906a, (i * 20) + 8);
        }

        final int m6389c(int i) {
            return GenericDiskCache.m6375b(this.f3906a, (i * 20) + 8);
        }

        final int m6391d(int i) {
            return GenericDiskCache.m6377d(this.f3906a, (i * 20) + 8);
        }

        final GenericDiskCache m6392e(int i) {
            return GenericDiskCache.m6374a(this.f3906a, (i * 20) + 8, this.f3907b, i);
        }

        final void m6384a(GenericDiskCache genericDiskCache) {
            m6385a(genericDiskCache, this.f3908c);
            this.f3908c++;
            this.f3909d = (genericDiskCache.f3899b + genericDiskCache.f3901d) + genericDiskCache.f3902e;
            this.f3910e = -1;
        }

        final void m6385a(GenericDiskCache genericDiskCache, int i) {
            genericDiskCache.m6378a(this.f3906a, (i * 20) + 8);
        }

        final int m6388c() {
            if (this.f3908c == 0) {
                return 0;
            }
            if (this.f3909d < 0) {
                GenericDiskCache e = m6392e(this.f3908c - 1);
                this.f3909d = e.f3902e + (e.f3899b + e.f3901d);
            }
            return this.f3909d;
        }

        final int m6390d() {
            int i = 0;
            if (this.f3910e == -1) {
                this.f3910e = 0;
                while (i < this.f3908c) {
                    if (m6391d(i) > 0) {
                        this.f3910e += m6389c(i) + GenericDiskCache.m6376c(this.f3906a, (i * 20) + 8);
                    }
                    i++;
                }
            }
            return this.f3910e;
        }

        public final String toString() {
            return "ID:" + this.f3907b + " Size:" + this.f3908c;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ar.e.h */
    static class GenericDiskCache {
        final GenericDiskCache f3911a;
        final GenericDiskCache f3912b;
        final int f3913c;

        GenericDiskCache(GenericDiskCache genericDiskCache, GenericDiskCache genericDiskCache2, int i) {
            this.f3911a = genericDiskCache;
            this.f3912b = genericDiskCache2;
            this.f3913c = i;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ar.e.i */
    static class GenericDiskCache {
        final int[] f3914a;
        final int[] f3915b;
        final int[] f3916c;
        final int[] f3917d;
        final int[] f3918e;
        int f3919f;
        int f3920g;

        GenericDiskCache(int i) {
            this.f3914a = new int[i];
            this.f3915b = new int[i];
            this.f3916c = new int[i];
            this.f3917d = new int[i];
            this.f3918e = new int[i];
            this.f3919f = 0;
            this.f3920g = 0;
        }

        final void m6396a(com.google.android.m4b.maps.ad.RandomAccessible randomAccessible) {
            int i = 0;
            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
            int i2 = 0;
            while (i < this.f3914a.length) {
                GenericDiskCache.m6419a(bArr, i2, this.f3914a[i]);
                int i3 = i2 + 4;
                GenericDiskCache.m6419a(bArr, i3, this.f3915b[i]);
                i3 += 4;
                GenericDiskCache.m6428b(bArr, i3, this.f3916c[i]);
                i3 += 2;
                GenericDiskCache.m6428b(bArr, i3, this.f3917d[i]);
                i3 += 2;
                GenericDiskCache.m6419a(bArr, i3, this.f3918e[i]);
                i3 += 4;
                GenericDiskCache.m6419a(bArr, i3, GenericDiskCache.m6429c(bArr, i3 - 16, 16));
                i2 += 20;
                i++;
            }
            randomAccessible.m4862a(bArr);
        }

        final void m6399b(com.google.android.m4b.maps.ad.RandomAccessible randomAccessible) {
            int i = 0;
            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
            randomAccessible.m4865b(bArr);
            this.f3919f = 0;
            this.f3920g = 0;
            int i2 = 0;
            while (i < this.f3914a.length) {
                this.f3914a[i] = GenericDiskCache.m6402a(bArr, i2);
                i2 += 4;
                this.f3915b[i] = GenericDiskCache.m6402a(bArr, i2);
                i2 += 4;
                this.f3916c[i] = GenericDiskCache.m6423b(bArr, i2);
                i2 += 2;
                this.f3917d[i] = GenericDiskCache.m6423b(bArr, i2);
                i2 += 2;
                this.f3918e[i] = GenericDiskCache.m6402a(bArr, i2);
                i2 += 4;
                if (GenericDiskCache.m6402a(bArr, i2) != GenericDiskCache.m6429c(bArr, i2 - 16, 16)) {
                    m6395a(i);
                } else {
                    if (this.f3917d[i] > 0) {
                        this.f3919f = i + 1;
                    }
                    if (this.f3918e[i] > 0) {
                        this.f3920g++;
                    }
                }
                i2 += 4;
                i++;
            }
        }

        final void m6397a(GenericDiskCache genericDiskCache) {
            int i = 0;
            int a = genericDiskCache.f3907b;
            if (this.f3918e[a] > 0) {
                this.f3920g--;
            }
            this.f3914a[a] = 0;
            this.f3915b[a] = genericDiskCache.m6388c();
            this.f3916c[a] = genericDiskCache.f3908c;
            this.f3917d[a] = 0;
            this.f3918e[a] = 0;
            while (i < genericDiskCache.f3908c) {
                if (genericDiskCache.m6387b(i) != -1) {
                    int[] iArr = this.f3917d;
                    iArr[a] = iArr[a] + 1;
                    if (genericDiskCache.m6391d(i) > 0) {
                        iArr = this.f3918e;
                        iArr[a] = iArr[a] + 1;
                    }
                }
                i++;
            }
            if (this.f3917d[a] > 0 && a >= this.f3919f) {
                this.f3919f = a + 1;
            }
            if (this.f3918e[a] > 0) {
                this.f3920g++;
            }
        }

        final void m6395a(int i) {
            this.f3914a[i] = 0;
            this.f3916c[i] = 0;
            this.f3917d[i] = 0;
            if (this.f3918e[i] > 0) {
                this.f3920g--;
            }
            this.f3918e[i] = 0;
            this.f3915b[i] = -1;
        }

        final boolean m6400b(int i) {
            return this.f3917d[i] != 0;
        }

        final int m6393a(Set<Integer> set) {
            int i = Integer.MAX_VALUE;
            int i2 = -1;
            int i3 = 0;
            while (i3 < this.f3919f) {
                if (m6400b(i3) && this.f3918e[i3] == 0 && this.f3914a[i3] < r2 && (set == null || !set.contains(Integer.valueOf(i3)))) {
                    i = this.f3914a[i3];
                    i2 = i3;
                }
                i3++;
            }
            return i2;
        }

        final long m6394a() {
            long j = 0;
            for (int i = 0; i < this.f3919f; i++) {
                long j2;
                if (this.f3915b[i] > 0) {
                    j2 = (long) this.f3915b[i];
                } else {
                    j2 = 0;
                }
                j += j2;
            }
            return j;
        }

        final int m6398b() {
            int i = 0;
            for (int i2 = 0; i2 < this.f3919f; i2++) {
                int i3;
                if (m6400b(i2)) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                i += i3;
            }
            return i;
        }
    }

    static {
        f3921C = !GenericDiskCache.class.desiredAssertionStatus();
        f3922a = new byte[0];
        f3923b = 20;
        f3924c = 81800;
        f3925d = 20;
    }

    private GenericDiskCache(String str, GenericDiskCache genericDiskCache, GenericDiskCache genericDiskCache2, GenericDiskCache genericDiskCache3, com.google.android.m4b.maps.ad.RandomAccessible randomAccessible, RandomAccessible randomAccessible2, GenericDiskCacheListener genericDiskCacheListener) {
        this.f3937n = new ReentrantLock();
        this.f3938o = new ReentrantReadWriteLock(true);
        this.f3946w = -1;
        this.f3947x = false;
        this.f3948y = null;
        this.f3949z = null;
        this.f3927B = 4;
        this.f3928e = str;
        this.f3931h = genericDiskCache;
        this.f3932i = genericDiskCache2;
        this.f3933j = genericDiskCache3;
        this.f3930g = randomAccessible;
        this.f3929f = randomAccessible2;
        this.f3934k = new com.google.android.m4b.maps.ad.RandomAccessible[genericDiskCache.f3879c];
        this.f3935l = new LRUCache(Math.min(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT, m6440h()));
        this.f3936m = Collections.synchronizedSet(new HashSet());
        this.f3949z = genericDiskCacheListener;
        this.f3937n.lock();
        Object obj = null;
        for (int i = 0; i < this.f3931h.f3879c; i++) {
            if ((this.f3932i.f3915b[i] == -1 ? 1 : null) == null) {
                if (this.f3932i.m6400b(i)) {
                    GenericDiskCache genericDiskCache4 = this.f3933j;
                    int i2 = i * Defaults.RESPONSE_BODY_LIMIT;
                    if ((GenericDiskCache.m6429c(genericDiskCache4.f3874a, i2, 1020) == GenericDiskCache.m6402a(genericDiskCache4.f3874a, i2 + 1020) ? 1 : null) != null) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            new StringBuilder("Rebuilding inconsistent shard: ").append(i);
            obj = 1;
            this.f3940q++;
            try {
                GenericDiskCache b = m6424b(i);
                this.f3932i.m6397a(b);
                this.f3933j.m6365a(b);
                m6432c(i);
            } catch (Throwable e) {
                Util.m11552a("Rebuilding shard: " + i, e);
                m6434d(i);
            } catch (Throwable th) {
                this.f3937n.unlock();
            }
        }
        if (obj != null) {
            m6443k();
        }
        this.f3937n.unlock();
    }

    public static GenericDiskCache m6409a(String str, int i, int i2, Locale locale, RandomAccessible randomAccessible, GenericDiskCacheListener genericDiskCacheListener) {
        int i3;
        boolean z = false;
        if (i == -1) {
            i3 = 81800;
            z = true;
        } else {
            i3 = i;
        }
        if (i3 < 4) {
            i3 = 4;
        }
        if (z || i3 <= 81800) {
            int max = Math.max(4, ((i3 - 1) / HttpStatus.SC_CONFLICT) + 1);
            int i4 = ((i3 - 1) / max) + 1;
            if (!f3921C && (max < 4 || max > HttpStatus.SC_CONFLICT)) {
                throw new AssertionError();
            } else if (!f3921C && !z && max > 35) {
                throw new AssertionError();
            } else if (f3921C || (i4 > 0 && i4 <= HttpStatus.SC_CONFLICT)) {
                randomAccessible.m4859a(str + ".m");
                com.google.android.m4b.maps.ad.RandomAccessible a = randomAccessible.m4858a(str + ".m", true);
                GenericDiskCache genericDiskCache = new GenericDiskCache((GenericDiskCache.m6437f() << 16) + 20, AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD, max, i4, z, i2, Config.m11320a().m11334h().m10151a(), locale);
                GenericDiskCache genericDiskCache2 = new GenericDiskCache(max);
                GenericDiskCache genericDiskCache3 = new GenericDiskCache(max, 0);
                GenericDiskCache.m6414a(genericDiskCache, genericDiskCache2, genericDiskCache3, a);
                a.m4864b();
                return new GenericDiskCache(str, genericDiskCache, genericDiskCache2, genericDiskCache3, a, randomAccessible, genericDiskCacheListener);
            } else {
                throw new AssertionError();
            }
        }
        throw new IllegalArgumentException("Number of records must be between 4 and " + 81800);
    }

    public static GenericDiskCache m6410a(String str, RandomAccessible randomAccessible, GenericDiskCacheListener genericDiskCacheListener) {
        com.google.android.m4b.maps.ad.RandomAccessible a = randomAccessible.m4858a(str + ".m", true);
        byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
        a.m4865b(bArr);
        GenericDiskCache genericDiskCache = new GenericDiskCache(bArr, 0);
        int f = GenericDiskCache.m6437f();
        int i = (f << 16) + 20;
        int i2 = (genericDiskCache.f3877a >> 16) & SupportMenu.USER_MASK;
        int i3 = genericDiskCache.f3877a & SupportMenu.USER_MASK;
        if (i2 == 0 && i3 != 20) {
            throw new IOException("Invalid Cache Header(1): " + genericDiskCache + "; cached sever schema is zero but client schema part doesn't match: cachedClientSchema = " + i3 + ", expectedClientSchema = " + 20);
        } else if ((i2 == 0 || genericDiskCache.f3877a == i) && genericDiskCache.f3878b == AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD) {
            GenericDiskCache genericDiskCache2 = new GenericDiskCache(genericDiskCache.f3879c);
            genericDiskCache2.m6399b(a);
            GenericDiskCache genericDiskCache3 = new GenericDiskCache(genericDiskCache.f3879c, genericDiskCache2.f3919f);
            a.m4863a(genericDiskCache3.f3874a, 0, genericDiskCache3.f3874a.length);
            GenericDiskCache genericDiskCache4 = new GenericDiskCache(str, genericDiskCache, genericDiskCache2, genericDiskCache3, a, randomAccessible, genericDiskCacheListener);
            if (i2 == 0 && f != 0) {
                genericDiskCache4.m6411a(genericDiskCache4.f3931h.f3882f, i);
            }
            return genericDiskCache4;
        } else {
            throw new IOException("Invalid Cache Header(2): " + genericDiskCache + ", expect expectedSchema=" + i + ", mBlockSize=8192");
        }
    }

    private static int m6437f() {
        VectorMapsParameters c = ServerControlledParametersManager.m4808c();
        if (c != null) {
            return c.m4821b() & SupportMenu.USER_MASK;
        }
        throw new IllegalStateException("VectorMapsParameters is null");
    }

    private static void m6414a(GenericDiskCache genericDiskCache, GenericDiskCache genericDiskCache2, GenericDiskCache genericDiskCache3, com.google.android.m4b.maps.ad.RandomAccessible randomAccessible) {
        int i = 0;
        byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
        genericDiskCache.m6369a(bArr, 0);
        randomAccessible.m4862a(bArr);
        genericDiskCache2.m6396a(randomAccessible);
        while (i < genericDiskCache3.f3875b) {
            genericDiskCache3.m6367b(i);
            i++;
        }
        randomAccessible.m4862a(genericDiskCache3.f3874a);
    }

    public final int m6446a() {
        return this.f3931h.f3882f;
    }

    public final long m6452b() {
        return this.f3931h.f3883g;
    }

    public final Locale m6454c() {
        return this.f3931h.f3884h;
    }

    private int m6439g() {
        int i = 0;
        this.f3938o.readLock().lock();
        int i2;
        try {
            GenericDiskCache genericDiskCache = this.f3932i;
            i2 = 0;
            while (i < genericDiskCache.f3919f) {
                i2 += genericDiskCache.f3917d[i];
                i++;
            }
            return i2;
        } finally {
            i2 = this.f3938o.readLock();
            i2.unlock();
        }
    }

    private int m6440h() {
        return this.f3931h.f3879c * this.f3931h.f3880d;
    }

    public final void m6455d() {
        IOException iOException = null;
        this.f3937n.lock();
        try {
            if (this.f3939p) {
                this.f3937n.unlock();
                return;
            }
            this.f3939p = true;
            this.f3938o.writeLock().lock();
            try {
                m6443k();
            } catch (IOException e) {
                iOException = e;
            }
            try {
                this.f3930g.m4860a();
            } catch (IOException e2) {
                iOException = e2;
            }
            for (int i = 0; i < this.f3934k.length; i++) {
                if (this.f3934k[i] != null) {
                    try {
                        this.f3934k[i].m4860a();
                    } catch (IOException e3) {
                        iOException = e3;
                    }
                    this.f3934k[i] = null;
                }
            }
            if (iOException != null) {
                throw iOException;
            }
            this.f3938o.writeLock().unlock();
            this.f3937n.unlock();
        } catch (Throwable th) {
            this.f3937n.unlock();
        }
    }

    public final void m6450a(int i, Locale locale) {
        this.f3937n.lock();
        try {
            this.f3939p = true;
            this.f3938o.writeLock().lock();
            try {
                if (this.f3949z != null) {
                    this.f3949z.m6456a();
                }
                synchronized (this.f3935l) {
                    this.f3935l.m6231a();
                }
                this.f3936m.clear();
                for (int i2 = 0; i2 < this.f3934k.length; i2++) {
                    if (this.f3934k[i2] != null) {
                        this.f3934k[i2].m4860a();
                        this.f3934k[i2] = null;
                    }
                    this.f3929f.m4859a(m6438f(i2));
                }
                this.f3930g.m4860a();
                this.f3929f.m4859a(this.f3928e + ".m");
                this.f3930g = this.f3929f.m4858a(this.f3928e + ".m", true);
                this.f3931h = new GenericDiskCache(20 + (GenericDiskCache.m6437f() << 16), this.f3931h.f3878b, this.f3931h.f3879c, this.f3931h.f3880d, this.f3931h.f3881e, i, Config.m11320a().m11334h().m10151a(), locale);
                GenericDiskCache genericDiskCache = this.f3932i;
                Arrays.fill(genericDiskCache.f3914a, 0);
                Arrays.fill(genericDiskCache.f3915b, 0);
                Arrays.fill(genericDiskCache.f3916c, 0);
                Arrays.fill(genericDiskCache.f3917d, 0);
                Arrays.fill(genericDiskCache.f3918e, 0);
                genericDiskCache.f3919f = 0;
                genericDiskCache.f3920g = 0;
                Arrays.fill(this.f3933j.f3874a, (byte) 0);
                GenericDiskCache.m6414a(this.f3931h, this.f3932i, this.f3933j, this.f3930g);
                this.f3930g.m4864b();
                this.f3939p = false;
                this.f3938o.writeLock().unlock();
            } catch (IOException e) {
                try {
                    m6455d();
                    throw e;
                } catch (Throwable th) {
                    this.f3938o.writeLock().unlock();
                }
            }
        } finally {
            this.f3937n.unlock();
        }
    }

    private void m6411a(int i, int i2) {
        this.f3937n.lock();
        try {
            if (!(i == this.f3931h.f3882f && i2 == this.f3931h.f3877a)) {
                GenericDiskCache genericDiskCache = new GenericDiskCache(i2, this.f3931h.f3878b, this.f3931h.f3879c, this.f3931h.f3880d, this.f3931h.f3881e, i, this.f3931h.f3883g, this.f3931h.f3884h);
                byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
                genericDiskCache.m6369a(bArr, 0);
                synchronized (this.f3930g) {
                    this.f3930g.m4861a(0);
                    this.f3930g.m4862a(bArr);
                    this.f3930g.m4864b();
                }
                this.f3931h = genericDiskCache;
            }
            this.f3937n.unlock();
        } catch (IOException e) {
            try {
                m6455d();
                throw e;
            } catch (Throwable th) {
                this.f3937n.unlock();
            }
        }
    }

    public final void m6449a(int i) {
        m6411a(i, this.f3931h.f3877a);
    }

    private GenericDiskCache m6424b(int i) {
        GenericDiskCache genericDiskCache;
        synchronized (this.f3930g) {
            this.f3930g.m4861a((long) ((i * AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD) + this.f3931h.f3885i));
            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
            this.f3930g.m4865b(bArr);
            int c = GenericDiskCache.m6429c(bArr, 0, 8188);
            int a = GenericDiskCache.m6402a(bArr, 8188);
            if (c != a) {
                throw new IOException("Unexpected checksum: " + c + ", expected: " + a);
            }
            genericDiskCache = new GenericDiskCache(bArr);
        }
        return genericDiskCache;
    }

    private void m6418a(GenericDiskCache genericDiskCache, boolean z) {
        int i = this.f3932i.f3914a[genericDiskCache.f3907b];
        this.f3938o.writeLock().lock();
        try {
            this.f3932i.m6395a(genericDiskCache.f3907b);
            m6443k();
            synchronized (this.f3930g) {
                this.f3930g.m4861a((long) ((genericDiskCache.f3907b * AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD) + this.f3931h.f3885i));
                genericDiskCache.m6383a(this.f3930g);
                this.f3930g.m4864b();
            }
            this.f3933j.m6365a(genericDiskCache);
            this.f3932i.m6397a(genericDiskCache);
            if (z) {
                GenericDiskCache genericDiskCache2 = this.f3932i;
                genericDiskCache2.f3914a[genericDiskCache.f3907b] = m6445m();
            } else {
                GenericDiskCache genericDiskCache3 = this.f3932i;
                genericDiskCache3.f3914a[genericDiskCache.f3907b] = i;
            }
            this.f3938o.writeLock().unlock();
            m6432c(genericDiskCache.f3907b);
            m6443k();
        } catch (Throwable th) {
            this.f3938o.writeLock().unlock();
        }
    }

    private void m6416a(GenericDiskCache genericDiskCache, IOException iOException) {
        this.f3941r++;
        Util.m11552a("Cache:" + this.f3928e, (Throwable) iOException);
        this.f3936m.add(genericDiskCache);
    }

    private byte[] m6422a(GenericDiskCache genericDiskCache) {
        if (genericDiskCache.f3901d == 0) {
            return f3922a;
        }
        try {
            byte[] bArr = new byte[genericDiskCache.f3901d];
            GenericDiskCache.m6413a(m6435e(genericDiskCache.f3904g), genericDiskCache.f3899b, bArr);
            return bArr;
        } catch (IOException e) {
            m6416a(genericDiskCache, e);
            throw e;
        }
    }

    private void m6441i() {
        if (this.f3936m.size() != 0) {
            ArrayList arrayList;
            synchronized (this.f3936m) {
                arrayList = new ArrayList(this.f3936m);
                this.f3936m.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GenericDiskCache genericDiskCache = (GenericDiskCache) it.next();
                try {
                    GenericDiskCache b = m6424b(genericDiskCache.f3904g);
                    if (b.m6386b() > genericDiskCache.f3905h && b.m6392e(genericDiskCache.f3905h).f3898a == genericDiskCache.f3898a) {
                        b.m6382a(genericDiskCache.f3905h);
                        synchronized (this.f3935l) {
                            this.f3935l.m6238c(Long.valueOf(genericDiskCache.f3898a));
                        }
                        m6418a(b, false);
                        if (this.f3949z != null) {
                            this.f3949z.m6458a(genericDiskCache.f3898a);
                        }
                    }
                } catch (Throwable e) {
                    Util.m11552a("Cache:" + this.f3928e, e);
                }
            }
        }
    }

    private void m6442j() {
        if (this.f3936m.size() != 0 && this.f3937n.tryLock()) {
            try {
                m6441i();
            } finally {
                this.f3937n.unlock();
            }
        }
    }

    private boolean m6421a(GenericDiskCache genericDiskCache, GenericDiskCache genericDiskCache2) {
        boolean z = false;
        byte[] b = genericDiskCache2.m6371b();
        if (b.length != genericDiskCache.f3901d) {
            return z;
        }
        if (b.length == 0) {
            return true;
        }
        try {
            return Arrays.equals(b, m6422a(genericDiskCache));
        } catch (IOException e) {
            return z;
        }
    }

    private GenericDiskCache m6408a(GenericDiskCache genericDiskCache, int i) {
        GenericDiskCache genericDiskCache2 = null;
        long a = genericDiskCache.m6370a();
        this.f3943t++;
        try {
            GenericDiskCache b = m6424b(i);
            int b2 = b.m6386b();
            List<GenericDiskCache> arrayList = new ArrayList(b2);
            int i2 = 0;
            while (i2 < b2) {
                GenericDiskCache genericDiskCache3;
                GenericDiskCache e = b.m6392e(i2);
                if (e.f3898a != -1) {
                    arrayList.add(e);
                    if (e.f3898a == genericDiskCache.m6370a() && m6421a(e, genericDiskCache)) {
                        genericDiskCache3 = new GenericDiskCache(b, e, i2);
                        i2++;
                        genericDiskCache2 = genericDiskCache3;
                    }
                }
                genericDiskCache3 = genericDiskCache2;
                i2++;
                genericDiskCache2 = genericDiskCache3;
            }
            if (genericDiskCache2 != null) {
                for (GenericDiskCache genericDiskCache4 : arrayList) {
                    synchronized (this.f3935l) {
                        this.f3935l.m6239c(Long.valueOf(genericDiskCache4.f3898a), genericDiskCache4);
                    }
                }
                synchronized (this.f3935l) {
                    this.f3935l.m6239c(Long.valueOf(genericDiskCache2.f3912b.f3898a), genericDiskCache2.f3912b);
                }
            } else {
                this.f3942s++;
            }
        } catch (Throwable e2) {
            Util.m11552a("lookupShardRecordIndexFromShard: " + a + " : " + i, e2);
            this.f3938o.readLock().unlock();
            if (this.f3937n.tryLock()) {
                m6434d(i);
                this.f3937n.unlock();
            }
            this.f3938o.readLock().lock();
        } catch (Throwable th) {
            this.f3937n.unlock();
        }
        return genericDiskCache2;
    }

    private GenericDiskCache m6406a(GenericDiskCache genericDiskCache) {
        long a = genericDiskCache.m6370a();
        synchronized (this.f3935l) {
            GenericDiskCache genericDiskCache2 = (GenericDiskCache) this.f3935l.m6235b(Long.valueOf(a));
        }
        if (!(genericDiskCache2 == null || m6421a(genericDiskCache2, genericDiskCache))) {
            genericDiskCache2 = null;
        }
        if (!(genericDiskCache2 == null || this.f3932i.m6400b(genericDiskCache2.f3904g))) {
            synchronized (this.f3935l) {
                this.f3935l.m6238c(Long.valueOf(a));
            }
            genericDiskCache2 = null;
        }
        if (genericDiskCache2 != null) {
            return genericDiskCache2;
        }
        GenericDiskCache b = m6425b(genericDiskCache);
        if (b != null) {
            return b.f3912b;
        }
        return genericDiskCache2;
    }

    private GenericDiskCache m6425b(GenericDiskCache genericDiskCache) {
        int[] a = GenericDiskCache.m6363a(genericDiskCache.m6370a());
        int i = this.f3932i.f3919f;
        int i2 = 0;
        while (i2 < i) {
            if (this.f3932i.m6400b(i2) && this.f3933j.m6366a(a, i2)) {
                GenericDiskCache a2 = m6408a(genericDiskCache, i2);
                if (a2 != null) {
                    return a2;
                }
            }
            i2++;
        }
        return null;
    }

    private static GenericDiskCache m6431c(long j, String str) {
        byte[] bArr = f3922a;
        if (str != null) {
            bArr = Utf8Encoder.m7790a(str);
        }
        return new GenericDiskCache(j, bArr);
    }

    public final byte[] m6451a(long j, String str) {
        this.f3938o.readLock().lock();
        try {
            if (this.f3939p) {
                return null;
            }
            byte[] c = m6433c(GenericDiskCache.m6431c(j, str));
            this.f3938o.readLock().unlock();
            m6442j();
            return c;
        } finally {
            this.f3938o.readLock().unlock();
            m6442j();
        }
    }

    private byte[] m6433c(GenericDiskCache genericDiskCache) {
        GenericDiskCache a = m6406a(genericDiskCache);
        if (a != null) {
            try {
                byte[] bArr = new byte[a.f3902e];
                GenericDiskCache.m6413a(m6435e(a.f3904g), a.f3899b + a.f3901d, bArr);
                int c = GenericDiskCache.m6429c(bArr, 0, bArr.length);
                if (c != a.f3903f) {
                    throw new IOException("Checksum mismatch: " + c + " record [" + a + "]");
                }
                GenericDiskCache genericDiskCache2 = this.f3932i;
                genericDiskCache2.f3914a[a.f3904g] = m6445m();
                return bArr;
            } catch (IOException e) {
                m6416a(a, e);
            }
        }
        return null;
    }

    public final boolean m6453b(long j, String str) {
        this.f3938o.readLock().lock();
        try {
            boolean z = m6406a(GenericDiskCache.m6431c(j, str)) != null;
            this.f3938o.readLock().unlock();
            m6442j();
            return z;
        } catch (Throwable th) {
            this.f3938o.readLock().unlock();
            m6442j();
        }
    }

    public static GenericDiskCache m6404a(long j, String str, byte[] bArr) {
        return new GenericDiskCache(bArr, (byte) 0);
    }

    public static GenericDiskCache m6405a(long j, byte[] bArr) {
        return GenericDiskCache.m6404a(j, null, bArr);
    }

    public static GenericDiskCache m6403a(long j, String str, int i, byte[] bArr) {
        return new GenericDiskCache(i, bArr, (byte) 0);
    }

    public final int m6448a(Collection<GenericDiskCache> collection) {
        this.f3937n.lock();
        if (this.f3939p) {
            return -1;
        }
        Collection<GenericDiskCache> b = m6426b((Collection) collection);
        m6441i();
        int i = 0;
        for (GenericDiskCache genericDiskCache : b) {
            i = (genericDiskCache.f3889b.length + genericDiskCache.f3891d.length) + i;
        }
        byte[] bArr = new byte[Math.min(i, AccessibilityNodeInfoCompat.ACTION_SET_SELECTION)];
        this.f3926A = new HashSet();
        GenericDiskCache l = m6444l();
        if (l == null) {
            this.f3926A = null;
            this.f3937n.unlock();
            return -1;
        }
        List<Long> arrayList = new ArrayList();
        GenericDiskCache genericDiskCache2 = new GenericDiskCache(m6435e(l.m6381a()), l.m6388c(), bArr);
        for (GenericDiskCache genericDiskCache3 : b) {
            GenericDiskCache genericDiskCache4;
            GenericDiskCache genericDiskCache5;
            int i2 = genericDiskCache3.f3890c & 31;
            if (l.m6386b() >= this.f3931h.f3880d || l.m6388c() > 134217727) {
                m6415a(genericDiskCache2, l);
                if (this.f3949z != null) {
                    for (Long longValue : arrayList) {
                        this.f3949z.m6459a(longValue.longValue(), l.m6381a());
                    }
                }
                arrayList.clear();
                l = m6444l();
                if (l == null) {
                    this.f3926A = null;
                    this.f3937n.unlock();
                    return -1;
                }
                try {
                    genericDiskCache4 = new GenericDiskCache(m6435e(l.m6381a()), l.m6388c(), bArr);
                    genericDiskCache5 = l;
                } finally {
                    this.f3926A = null;
                    this.f3937n.unlock();
                }
            } else {
                genericDiskCache4 = genericDiskCache2;
                genericDiskCache5 = l;
            }
            genericDiskCache4.m6373a(genericDiskCache3.f3889b);
            genericDiskCache4.m6373a(genericDiskCache3.f3891d);
            GenericDiskCache genericDiskCache6 = new GenericDiskCache(genericDiskCache3.f3888a.m6370a(), genericDiskCache5.m6388c(), genericDiskCache3.f3889b.length, genericDiskCache3.f3891d.length, i2, GenericDiskCache.m6429c(genericDiskCache3.f3891d, 0, genericDiskCache3.f3891d.length), genericDiskCache5.m6381a(), genericDiskCache5.m6386b());
            genericDiskCache5.m6384a(genericDiskCache6);
            arrayList.add(Long.valueOf(genericDiskCache6.f3898a));
            genericDiskCache2 = genericDiskCache4;
            l = genericDiskCache5;
        }
        m6415a(genericDiskCache2, l);
        if (this.f3949z != null) {
            for (Long longValue2 : arrayList) {
                this.f3949z.m6459a(longValue2.longValue(), l.m6381a());
            }
        }
        this.f3926A = null;
        this.f3937n.unlock();
        return 0;
    }

    private void m6415a(GenericDiskCache genericDiskCache, GenericDiskCache genericDiskCache2) {
        genericDiskCache.m6372a();
        m6418a(genericDiskCache2, true);
    }

    public final int m6447a(long j, String str, int i) {
        this.f3937n.lock();
        try {
            GenericDiskCache b = m6425b(GenericDiskCache.m6431c(j, str));
            if (b == null) {
                return -1;
            }
            int i2 = this.f3932i.f3918e[b.f3911a.m6381a()];
            GenericDiskCache genericDiskCache = b.f3912b;
            int a = RefCountUtil.m6290a(genericDiskCache.f3900c, i) & 31;
            b.f3911a.m6385a(new GenericDiskCache(genericDiskCache.f3898a, genericDiskCache.f3899b, genericDiskCache.f3901d, genericDiskCache.f3902e, a, genericDiskCache.f3903f, genericDiskCache.f3904g, genericDiskCache.f3905h), b.f3913c);
            m6418a(b.f3911a, true);
            if (i2 == 0 && this.f3932i.f3918e[b.f3911a.m6381a()] == 1) {
                m6427b(false);
            }
            this.f3937n.unlock();
            return a;
        } finally {
            this.f3937n.unlock();
        }
    }

    private void m6432c(int i) {
        synchronized (this.f3930g) {
            this.f3930g.m4861a((long) ((i * Defaults.RESPONSE_BODY_LIMIT) + AccessibilityNodeInfoCompat.ACTION_COPY));
            GenericDiskCache genericDiskCache = this.f3933j;
            com.google.android.m4b.maps.ad.RandomAccessible randomAccessible = this.f3930g;
            genericDiskCache.m6367b(i);
            randomAccessible.m4866b(genericDiskCache.f3874a, i * Defaults.RESPONSE_BODY_LIMIT, Defaults.RESPONSE_BODY_LIMIT);
            this.f3930g.m4864b();
        }
    }

    private void m6443k() {
        synchronized (this.f3930g) {
            this.f3930g.m4861a(8192);
            this.f3932i.m6396a(this.f3930g);
            this.f3930g.m4864b();
        }
    }

    private GenericDiskCache m6444l() {
        int i = 0;
        if (f3921C || this.f3926A != null) {
            GenericDiskCache b;
            GenericDiskCache genericDiskCache;
            int i2 = 0;
            while (i2 < this.f3932i.f3919f) {
                if (this.f3932i.f3916c[i2] >= this.f3931h.f3880d || this.f3932i.f3915b[i2] > 134217727) {
                    i2++;
                } else {
                    try {
                        b = m6424b(i2);
                        break;
                    } catch (Throwable e) {
                        Util.m11552a("allocateShardToUse: " + i2, e);
                        b = null;
                    }
                }
            }
            b = null;
            i2 = -1;
            if (i2 == -1) {
                boolean z;
                int a;
                if (this.f3932i.m6398b() >= 20) {
                    long c = Util.m11557c();
                    long a2 = this.f3932i.m6394a();
                    if (((long) (((double) (c + a2)) * 0.25d)) < a2) {
                        z = true;
                        m6427b(z);
                        if (this.f3931h.f3881e && z) {
                            while (i < 2) {
                                a = this.f3932i.m6393a(this.f3926A);
                                if (a != -1) {
                                    m6434d(a);
                                    if (this.f3934k[a] != null) {
                                        this.f3934k[a].m4860a();
                                        this.f3934k[a] = null;
                                    }
                                    this.f3929f.m4859a(m6438f(a));
                                }
                                i++;
                            }
                        }
                        i2 = m6401a(z);
                        if (i2 == -1) {
                            Util.m11559c("Cache:" + this.f3928e, "Tile store full, unable to allocate shard");
                            return null;
                        }
                    }
                }
                z = false;
                m6427b(z);
                while (i < 2) {
                    a = this.f3932i.m6393a(this.f3926A);
                    if (a != -1) {
                        m6434d(a);
                        if (this.f3934k[a] != null) {
                            this.f3934k[a].m4860a();
                            this.f3934k[a] = null;
                        }
                        this.f3929f.m4859a(m6438f(a));
                    }
                    i++;
                }
                i2 = m6401a(z);
                if (i2 == -1) {
                    Util.m11559c("Cache:" + this.f3928e, "Tile store full, unable to allocate shard");
                    return null;
                }
            }
            i = i2;
            if (b == null || b.m6386b() != this.f3932i.f3916c[i]) {
                genericDiskCache = new GenericDiskCache(i);
            } else {
                genericDiskCache = b;
            }
            this.f3926A.add(Integer.valueOf(i));
            return genericDiskCache;
        }
        throw new AssertionError();
    }

    private int m6401a(boolean z) {
        int i = 0;
        while (i < this.f3932i.f3919f) {
            if (!this.f3932i.m6400b(i)) {
                break;
            }
            i++;
        }
        if (z) {
            i = this.f3932i.m6393a(this.f3926A);
            if (i != -1) {
                m6434d(i);
                return i;
            }
        }
        if (this.f3932i.f3919f < this.f3931h.f3879c) {
            this.f3938o.writeLock().lock();
            try {
                GenericDiskCache genericDiskCache = this.f3932i;
                i = genericDiskCache.f3919f;
                genericDiskCache.f3919f = i + 1;
                genericDiskCache.f3914a[i] = 0;
                genericDiskCache.f3915b[i] = 0;
                genericDiskCache.f3916c[i] = 0;
                genericDiskCache.f3917d[i] = 0;
                if (genericDiskCache.f3918e[i] > 0) {
                    genericDiskCache.f3920g--;
                }
                genericDiskCache.f3918e[i] = 0;
                this.f3933j.m6364a(i);
                this.f3933j.f3875b = i + 1;
            } finally {
                this.f3938o.writeLock().unlock();
            }
        } else {
            i = this.f3932i.m6393a(this.f3926A);
            if (i != -1) {
                m6434d(i);
            }
        }
        return i;
    }

    private void m6417a(GenericDiskCache genericDiskCache, GenericDiskCache genericDiskCache2, GenericDiskCache genericDiskCache3) {
        com.google.android.m4b.maps.ad.RandomAccessible e = m6435e(genericDiskCache.m6381a());
        int i = 0;
        while (i < genericDiskCache.m6386b()) {
            if (genericDiskCache.m6387b(i) != -1 && genericDiskCache.m6391d(i) > 0) {
                if (genericDiskCache2.m6386b() >= this.f3931h.f3880d || genericDiskCache2.m6388c() >= 134217727) {
                    throw new IOException("Couldn't fit refcounted records into collecting shard");
                }
                GenericDiskCache e2 = genericDiskCache.m6392e(i);
                byte[] bArr = new byte[(e2.f3901d + e2.f3902e)];
                GenericDiskCache.m6413a(e, e2.f3899b, bArr);
                genericDiskCache3.m6373a(bArr);
                GenericDiskCache genericDiskCache4 = new GenericDiskCache(e2.f3898a, genericDiskCache2.m6388c(), e2.f3901d, e2.f3902e, e2.f3900c, e2.f3903f, genericDiskCache2.m6381a(), genericDiskCache2.m6386b());
                genericDiskCache2.m6384a(genericDiskCache4);
                if (this.f3949z != null) {
                    this.f3949z.m6460b(genericDiskCache4.f3898a, genericDiskCache4.f3904g);
                }
            }
            i++;
        }
    }

    private GenericDiskCache m6407a(int i, int i2, int i3) {
        int min = Math.min((this.f3931h.f3880d * 50) / 100, this.f3931h.f3880d - i2);
        int i4 = 134217727 - i3;
        while (i < this.f3932i.f3919f) {
            if (this.f3932i.f3918e[i] > 0 && this.f3932i.f3918e[i] <= min && (this.f3926A == null || !this.f3926A.contains(Integer.valueOf(i)))) {
                GenericDiskCache b = m6424b(i);
                if (b.m6390d() <= i4) {
                    return b;
                }
            }
            i++;
        }
        return null;
    }

    private void m6427b(boolean z) {
        Throwable e;
        if (this.f3931h.f3879c > this.f3927B) {
            int i;
            if (z) {
                i = this.f3932i.f3919f;
            } else {
                i = this.f3931h.f3879c;
            }
            i -= this.f3932i.f3920g;
            if (this.f3926A != null) {
                i -= this.f3926A.size();
            }
            if (i < this.f3927B) {
                System.currentTimeMillis();
                GenericDiskCache genericDiskCache;
                try {
                    GenericDiskCache a = m6407a(0, 0, 0);
                    if (a != null) {
                        i = a.m6381a();
                        GenericDiskCache a2 = m6407a(i + 1, this.f3932i.f3918e[i], a.m6390d());
                        if (a2 != null) {
                            int a3 = m6401a(z);
                            if (a3 != -1) {
                                genericDiskCache = new GenericDiskCache(a3);
                                GenericDiskCache genericDiskCache2 = new GenericDiskCache(m6435e(a3), 0, new byte[AccessibilityNodeInfoCompat.ACTION_SET_SELECTION]);
                                Set<Integer> hashSet = new HashSet();
                                while (a != null) {
                                    m6417a(a, genericDiskCache, genericDiskCache2);
                                    hashSet.add(Integer.valueOf(a.m6381a()));
                                    if (hashSet.size() >= 4) {
                                        break;
                                    } else if (a2 != null) {
                                        a = a2;
                                        a2 = null;
                                    } else {
                                        try {
                                            a = m6407a(a.m6381a() + 1, genericDiskCache.m6386b(), genericDiskCache.m6388c());
                                        } catch (IOException e2) {
                                            e = e2;
                                        }
                                    }
                                }
                                m6415a(genericDiskCache2, genericDiskCache);
                                for (Integer intValue : hashSet) {
                                    int intValue2 = intValue.intValue();
                                    try {
                                        GenericDiskCache b = m6424b(intValue2);
                                        i = 0;
                                        while (i < b.m6386b()) {
                                            if (b.m6387b(i) != -1 && b.m6391d(i) > 0) {
                                                b.m6382a(i);
                                            }
                                            i++;
                                        }
                                        m6418a(b, false);
                                    } catch (IOException e3) {
                                        m6434d(intValue2);
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException e4) {
                    e = e4;
                    genericDiskCache = null;
                    if (genericDiskCache != null) {
                        m6434d(genericDiskCache.m6381a());
                    }
                    Util.m11552a("Failed to combine refCounted records", e);
                }
            }
        }
    }

    private static void m6412a(int i, GenericDiskCache genericDiskCache, Map<GenericDiskCache, Integer> map) {
        Object valueOf;
        Integer num = (Integer) map.get(genericDiskCache);
        if (num != null) {
            valueOf = Integer.valueOf(RefCountUtil.m6290a(i, num.intValue()));
        } else {
            valueOf = Integer.valueOf(i);
        }
        map.put(genericDiskCache, valueOf);
    }

    private Collection<GenericDiskCache> m6426b(Collection<GenericDiskCache> collection) {
        Set<Long> hashSet = new HashSet(collection.size());
        Set hashSet2 = new HashSet(collection.size());
        Map hashMap = new HashMap(collection.size());
        List arrayList = new ArrayList(collection);
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            GenericDiskCache genericDiskCache = (GenericDiskCache) it.next();
            if (genericDiskCache.f3888a.m6370a() == -1 || genericDiskCache.f3889b.length > MotionEventCompat.ACTION_MASK) {
                it.remove();
            } else if (hashSet2.contains(genericDiskCache.f3888a)) {
                it.remove();
                if (genericDiskCache.f3890c > 0) {
                    GenericDiskCache.m6412a(genericDiskCache.f3890c, genericDiskCache.f3888a, hashMap);
                }
            } else {
                hashSet.add(Long.valueOf(genericDiskCache.f3888a.m6370a()));
                hashSet2.add(genericDiskCache.f3888a);
            }
        }
        int i = 0;
        while (i < this.f3932i.f3919f) {
            Object obj;
            int i2;
            for (Long l : hashSet) {
                if (this.f3932i.m6400b(i) && this.f3933j.m6366a(GenericDiskCache.m6363a(l.longValue()), i)) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj != null) {
                GenericDiskCache b;
                GenericDiskCache genericDiskCache2 = null;
                try {
                    b = m6424b(i);
                } catch (Throwable e) {
                    Util.m11552a("removeOldRecordsAndFilterInsertions: " + i, e);
                    m6434d(i);
                    b = genericDiskCache2;
                }
                if (b != null) {
                    obj = null;
                    for (i2 = 0; i2 < b.m6386b(); i2++) {
                        long b2 = b.m6387b(i2);
                        if (hashSet.contains(Long.valueOf(b2))) {
                            byte[] bArr = f3922a;
                            if (b.m6389c(i2) > 0) {
                                try {
                                    bArr = m6422a(b.m6392e(i2));
                                } catch (IOException e2) {
                                }
                            }
                            GenericDiskCache genericDiskCache3 = new GenericDiskCache(b2, bArr);
                            if (hashSet2.contains(genericDiskCache3)) {
                                this.f3945v++;
                                if (this.f3949z != null) {
                                    this.f3949z.m6458a(b2);
                                }
                                synchronized (this.f3935l) {
                                    this.f3935l.m6238c(Long.valueOf(b2));
                                }
                                int d = b.m6391d(i2);
                                if (d > 0) {
                                    GenericDiskCache.m6412a(d, genericDiskCache3, hashMap);
                                }
                                b.m6382a(i2);
                                obj = 1;
                            } else {
                                continue;
                            }
                        }
                    }
                    if (obj != null) {
                        m6418a(b, false);
                    } else {
                        this.f3944u++;
                    }
                } else {
                    continue;
                }
            }
            i++;
        }
        Collection arrayList2 = new ArrayList(arrayList.size());
        for (i2 = arrayList.size() - 1; i2 >= 0; i2--) {
            obj = (GenericDiskCache) arrayList.get(i2);
            Integer num = (Integer) hashMap.get(obj.f3888a);
            if (num != null) {
                obj = new GenericDiskCache(Integer.valueOf(RefCountUtil.m6290a(num.intValue(), obj.f3890c)).intValue(), obj.f3891d, (byte) 0);
            }
            arrayList2.add(obj);
        }
        return arrayList2;
    }

    private void m6434d(int i) {
        try {
            m6418a(new GenericDiskCache(i), false);
            synchronized (this.f3935l) {
                this.f3935l.m6231a();
            }
            this.f3936m.clear();
            if (this.f3949z != null) {
                this.f3949z.m6457a(i);
            }
        } catch (Throwable e) {
            Util.m11552a("Cache:" + this.f3928e, e);
        }
    }

    private com.google.android.m4b.maps.ad.RandomAccessible m6435e(int i) {
        com.google.android.m4b.maps.ad.RandomAccessible randomAccessible;
        synchronized (this.f3934k) {
            if (this.f3934k[i] == null) {
                this.f3934k[i] = this.f3929f.m4858a(m6438f(i), true);
            }
            randomAccessible = this.f3934k[i];
        }
        return randomAccessible;
    }

    private String m6438f(int i) {
        return this.f3928e + '.' + i;
    }

    private int m6445m() {
        if (this.f3946w >= 0) {
            return this.f3946w;
        }
        return (int) (System.currentTimeMillis() / 1000);
    }

    public String toString() {
        return "[" + this.f3928e + " ver:" + this.f3931h.f3882f + " locale: " + this.f3931h.f3884h + " auto:" + this.f3931h.f3881e + " size:" + m6439g() + " max:" + m6440h() + " max_shards:" + this.f3931h.f3879c + "]";
    }

    private static void m6413a(com.google.android.m4b.maps.ad.RandomAccessible randomAccessible, int i, byte[] bArr) {
        synchronized (randomAccessible) {
            randomAccessible.m4861a((long) i);
            randomAccessible.m4865b(bArr);
        }
    }

    public static int m6402a(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        i2 = (bArr[i2] & MotionEventCompat.ACTION_MASK) << 16;
        return ((i2 | ((bArr[i] & MotionEventCompat.ACTION_MASK) << 24)) | ((bArr[i3] & MotionEventCompat.ACTION_MASK) << 8)) | (bArr[i3 + 1] & MotionEventCompat.ACTION_MASK);
    }

    public static void m6419a(byte[] bArr, int i, int i2) {
        int i3 = i + 1;
        bArr[i] = (byte) (i2 >> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i2 >> 16);
        i3 = i4 + 1;
        bArr[i4] = (byte) (i2 >> 8);
        bArr[i3] = (byte) i2;
    }

    public static int m6423b(byte[] bArr, int i) {
        return (bArr[i + 1] & MotionEventCompat.ACTION_MASK) | ((bArr[i] & MotionEventCompat.ACTION_MASK) << 8);
    }

    public static void m6428b(byte[] bArr, int i, int i2) {
        int i3 = i + 1;
        bArr[i] = (byte) (i2 >> 8);
        bArr[i3] = (byte) i2;
    }

    public static long m6430c(byte[] bArr, int i) {
        return (((long) GenericDiskCache.m6402a(bArr, i)) << 32) | (((long) GenericDiskCache.m6402a(bArr, i + 4)) & 4294967295L);
    }

    public static void m6420a(byte[] bArr, int i, long j) {
        GenericDiskCache.m6419a(bArr, i, (int) (j >> 32));
        GenericDiskCache.m6419a(bArr, i + 4, (int) j);
    }

    static int m6429c(byte[] bArr, int i, int i2) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr, i, i2);
        return (int) crc32.getValue();
    }
}
