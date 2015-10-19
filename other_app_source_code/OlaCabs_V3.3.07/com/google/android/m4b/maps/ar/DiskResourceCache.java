package com.google.android.m4b.maps.ar;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.ad.RandomAccessible.RandomAccessible;
import com.google.android.m4b.maps.at.Resource;
import com.google.android.m4b.maps.bx.ae;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p058v.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.ar.b */
public final class DiskResourceCache {
    private static final Locale f3869a;
    private final int f3870b;
    private final GenericDiskCache f3871c;
    private final Clock f3872d;
    private boolean f3873e;

    static {
        f3869a = Locale.ENGLISH;
    }

    private DiskResourceCache(int i, File file, Clock clock) {
        GenericDiskCache a;
        this.f3870b = AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
        this.f3872d = clock;
        this.f3872d.m10153c();
        RandomAccessible randomAccessible = new RandomAccessible(file);
        try {
            a = GenericDiskCache.m6410a("r", randomAccessible, null);
        } catch (IOException e) {
            try {
                a = GenericDiskCache.m6409a("r", this.f3870b, 0, f3869a, randomAccessible, null);
            } catch (Throwable e2) {
                Util.m11552a("DiskResourceCache", e2);
                a = null;
            }
        }
        if (a != null) {
            this.f3872d.m10153c();
            this.f3873e = true;
        }
        this.f3871c = a;
    }

    public static DiskResourceCache m6344a(File file) {
        return new DiskResourceCache(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY, file, Config.m11320a().m11334h());
    }

    public final synchronized Resource m6345a(String str) {
        Resource resource = null;
        synchronized (this) {
            if (this.f3873e) {
                byte[] a = this.f3871c.m6451a(Util.m11543a(str), null);
                if (a != null && a.length > 9 && a[0] == 1) {
                    long c = GenericDiskCache.m6430c(a, 1);
                    ProtoBuf protoBuf = new ProtoBuf(ae.f6818b);
                    try {
                        protoBuf.m10187a(new ByteArrayInputStream(a, 9, a.length - 9));
                        if (str.equals(protoBuf.m10212h(2))) {
                            resource = new Resource();
                            resource.m6583a(true);
                            resource.m6585a(protoBuf);
                            resource.m6581a(c);
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
        return resource;
    }

    public final synchronized void m6347a(ProtoBuf protoBuf) {
        if (this.f3873e) {
            String h = protoBuf.m10212h(2);
            try {
                long a = this.f3872d.m10151a();
                Object d = protoBuf.m10206d();
                byte[] bArr = new byte[(d.length + 9)];
                bArr[0] = (byte) 1;
                GenericDiskCache.m6420a(bArr, 1, a);
                System.arraycopy(d, 0, bArr, 9, d.length);
                Collection arrayList = new ArrayList(1);
                arrayList.add(GenericDiskCache.m6405a(Util.m11543a(h), bArr));
                this.f3871c.m6448a(arrayList);
            } catch (IOException e) {
                Util.m11550a("DiskResourceCache", "Error inserting: " + h + " : " + e);
            }
        }
    }

    public final synchronized void m6346a() {
        if (this.f3873e) {
            try {
                this.f3871c.m6450a(0, f3869a);
            } catch (IOException e) {
                Util.m11550a("DiskResourceCache", "Clearing cache: " + e);
            }
        }
    }
}
