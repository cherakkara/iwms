package com.google.android.m4b.maps.ar;

import android.os.Process;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.ad.RandomAccessible.RandomAccessible;
import com.google.android.m4b.maps.ar.GenericDiskCache.GenericDiskCache;
import com.google.android.m4b.maps.au.DriveAboutThread;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufType;
import com.google.android.m4b.maps.p058v.Util;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.http.protocol.HttpRequestExecutor;

/* renamed from: com.google.android.m4b.maps.ar.a */
public final class DiskProtoBufCache {
    private static final Locale f3860a;
    private GenericDiskCache f3861b;
    private final String f3862c;
    private final Clock f3863d;
    private final Map<String, DiskProtoBufCache> f3864e;
    private final ProtoBufType f3865f;
    private final int f3866g;
    private final long f3867h;
    private DiskProtoBufCache f3868i;

    /* renamed from: com.google.android.m4b.maps.ar.a.a */
    static class DiskProtoBufCache extends DriveAboutThread {
        private final int f3853a;
        private final DiskProtoBufCache f3854b;

        DiskProtoBufCache(String str, int i, DiskProtoBufCache diskProtoBufCache) {
            super("CacheCommitter:" + str);
            this.f3853a = i;
            this.f3854b = diskProtoBufCache;
            start();
        }

        public final void m6334f() {
            try {
                Process.setThreadPriority(VectorGlobalState.m7288d() + 1);
            } catch (SecurityException e) {
                Util.m11550a(getName(), "Could not set thread priority: " + e);
            }
            do {
                try {
                    DiskProtoBufCache.sleep((long) this.f3853a);
                } catch (InterruptedException e2) {
                    return;
                }
            } while (!this.f3854b.m6338b());
        }
    }

    /* renamed from: com.google.android.m4b.maps.ar.a.b */
    static class DiskProtoBufCache {
        final String f3855a;
        final ProtoBuf f3856b;
        final long f3857c;

        DiskProtoBufCache(String str, ProtoBuf protoBuf, long j) {
            this.f3855a = str;
            this.f3856b = protoBuf;
            this.f3857c = j;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ar.a.c */
    public static class DiskProtoBufCache {
        public final ProtoBuf f3858a;
        public final long f3859b;

        DiskProtoBufCache(ProtoBuf protoBuf, long j) {
            this.f3858a = (ProtoBuf) Preconditions.m1817a((Object) protoBuf);
            this.f3859b = j;
        }
    }

    static {
        f3860a = new Locale(Trace.NULL);
    }

    public DiskProtoBufCache(Clock clock, String str, ProtoBufType protoBufType, int i, long j) {
        this.f3863d = clock;
        this.f3862c = str;
        this.f3864e = Collections.synchronizedMap(au.m2807a());
        this.f3865f = protoBufType;
        this.f3866g = HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE;
        this.f3867h = 86400000;
    }

    public final boolean m6342a(File file) {
        GenericDiskCache a;
        RandomAccessible randomAccessible = new RandomAccessible(file);
        SystemClock.uptimeMillis();
        try {
            a = GenericDiskCache.m6410a(this.f3862c, randomAccessible, null);
        } catch (IOException e) {
            try {
                a = GenericDiskCache.m6409a(this.f3862c, 4090, -1, f3860a, randomAccessible, null);
            } catch (Throwable e2) {
                Util.m11552a("DiskProtoBufCache", e2);
                return false;
            }
        }
        SystemClock.uptimeMillis();
        this.f3861b = a;
        return true;
    }

    private long m6335a(long j) {
        return this.f3867h == 0 ? -1 : this.f3867h + j;
    }

    public final void m6340a(String str, ProtoBuf protoBuf) {
        if (this.f3861b != null) {
            synchronized (this.f3864e) {
                if (this.f3864e.size() < AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                    this.f3864e.put(str, new DiskProtoBufCache(str, protoBuf, this.f3863d.m10151a()));
                }
                if (this.f3868i == null) {
                    this.f3868i = new DiskProtoBufCache(this.f3862c, this.f3866g, this);
                }
            }
        }
    }

    public final DiskProtoBufCache m6339a(String str) {
        if (this.f3861b == null) {
            return null;
        }
        DiskProtoBufCache diskProtoBufCache = (DiskProtoBufCache) this.f3864e.get(str);
        if (diskProtoBufCache != null) {
            return new DiskProtoBufCache(diskProtoBufCache.f3856b, m6335a(diskProtoBufCache.f3857c));
        }
        byte[] a = this.f3861b.m6451a(Util.m11543a(str), str);
        if (a == null) {
            return null;
        }
        InputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(a));
        try {
            dataInputStream.readInt();
            long readLong = dataInputStream.readLong();
            ProtoBuf protoBuf = new ProtoBuf(this.f3865f);
            protoBuf.m10180a(dataInputStream, dataInputStream.readInt());
            return new DiskProtoBufCache(protoBuf, m6335a(readLong));
        } catch (Throwable e) {
            Util.m11552a("DiskProtoBufCache", e);
            return null;
        }
    }

    public final synchronized boolean m6343a(Locale locale) {
        boolean z = false;
        synchronized (this) {
            if (this.f3861b != null) {
                if (this.f3861b.m6454c().equals(locale)) {
                    z = true;
                } else {
                    try {
                        this.f3861b.m6450a(this.f3861b.m6446a(), locale);
                        this.f3864e.clear();
                        z = true;
                    } catch (Throwable e) {
                        Util.m11552a("DiskProtoBufCache", e);
                    }
                }
            }
        }
        return z;
    }

    public final synchronized boolean m6341a() {
        boolean z = false;
        synchronized (this) {
            if (this.f3861b != null) {
                try {
                    this.f3861b.m6450a(this.f3861b.m6446a(), this.f3861b.m6454c());
                    this.f3864e.clear();
                    z = true;
                } catch (Throwable e) {
                    Util.m11552a("DiskProtoBufCache", e);
                }
            }
        }
        return z;
    }

    private static List<GenericDiskCache> m6336a(List<DiskProtoBufCache> list) {
        List<GenericDiskCache> b = ar.m2523b(list.size());
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (DiskProtoBufCache diskProtoBufCache : list) {
            long a = Util.m11543a(diskProtoBufCache.f3855a);
            String str = diskProtoBufCache.f3855a;
            try {
                dataOutputStream.writeInt(-1);
                dataOutputStream.writeLong(diskProtoBufCache.f3857c);
                diskProtoBufCache.f3856b.m10193a(dataOutputStream);
                dataOutputStream.flush();
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.reset();
                b.add(GenericDiskCache.m6404a(a, str, toByteArray));
            } catch (Throwable e) {
                Util.m11552a("DiskProtoBufCache", e);
            }
        }
        return b;
    }

    private synchronized boolean m6338b() {
        boolean z;
        synchronized (this.f3864e) {
            if (this.f3864e.isEmpty()) {
                this.f3868i = null;
                z = true;
            } else {
                List<DiskProtoBufCache> a = ar.m2516a(this.f3864e.values());
                Collection a2 = DiskProtoBufCache.m6336a((List) a);
                SystemClock.uptimeMillis();
                if (a2.size() > 0) {
                    try {
                        this.f3861b.m6448a(a2);
                    } catch (Throwable e) {
                        Util.m11552a("DiskProtoBufCache", e);
                    }
                }
                SystemClock.uptimeMillis();
                synchronized (this.f3864e) {
                    for (DiskProtoBufCache diskProtoBufCache : a) {
                        if (diskProtoBufCache == this.f3864e.get(diskProtoBufCache.f3855a)) {
                            this.f3864e.remove(diskProtoBufCache.f3855a);
                        }
                    }
                    if (this.f3864e.isEmpty()) {
                        this.f3868i = null;
                        z = true;
                    } else {
                        z = false;
                    }
                }
            }
        }
        return z;
    }
}
