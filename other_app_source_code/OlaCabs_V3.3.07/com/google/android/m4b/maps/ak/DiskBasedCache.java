package com.google.android.m4b.maps.ak;

import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import com.google.android.m4b.maps.p038a.Cache;
import com.google.android.m4b.maps.p038a.VolleyLog;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.ak.c */
public final class DiskBasedCache implements Cache {
    private final Map<String, DiskBasedCache> f3260a;
    private long f3261b;
    private final File f3262c;
    private final int f3263d;

    /* renamed from: com.google.android.m4b.maps.ak.c.a */
    static class DiskBasedCache {
        public long f3252a;
        public String f3253b;
        public String f3254c;
        public long f3255d;
        public long f3256e;
        public long f3257f;
        public Map<String, String> f3258g;

        private DiskBasedCache() {
        }

        public DiskBasedCache(String str, Cache.Cache cache) {
            this.f3253b = str;
            this.f3252a = (long) cache.f2870a.length;
            this.f3254c = cache.f2871b;
            this.f3255d = cache.f2872c;
            this.f3256e = cache.f2873d;
            this.f3257f = cache.f2874e;
            this.f3258g = cache.f2875f;
        }

        public static DiskBasedCache m5220a(InputStream inputStream) {
            DiskBasedCache diskBasedCache = new DiskBasedCache();
            if (DiskBasedCache.m5223a(inputStream) != 538051844) {
                throw new IOException();
            }
            diskBasedCache.f3253b = DiskBasedCache.m5231c(inputStream);
            diskBasedCache.f3254c = DiskBasedCache.m5231c(inputStream);
            if (diskBasedCache.f3254c.equals(Trace.NULL)) {
                diskBasedCache.f3254c = null;
            }
            diskBasedCache.f3255d = DiskBasedCache.m5229b(inputStream);
            diskBasedCache.f3256e = DiskBasedCache.m5229b(inputStream);
            diskBasedCache.f3257f = DiskBasedCache.m5229b(inputStream);
            diskBasedCache.f3258g = DiskBasedCache.m5234d(inputStream);
            return diskBasedCache;
        }

        public final boolean m5221a(OutputStream outputStream) {
            try {
                DiskBasedCache.m5224a(outputStream, 538051844);
                DiskBasedCache.m5226a(outputStream, this.f3253b);
                DiskBasedCache.m5226a(outputStream, this.f3254c == null ? Trace.NULL : this.f3254c);
                DiskBasedCache.m5225a(outputStream, this.f3255d);
                DiskBasedCache.m5225a(outputStream, this.f3256e);
                DiskBasedCache.m5225a(outputStream, this.f3257f);
                Map map = this.f3258g;
                if (map != null) {
                    DiskBasedCache.m5224a(outputStream, map.size());
                    for (Entry entry : map.entrySet()) {
                        DiskBasedCache.m5226a(outputStream, (String) entry.getKey());
                        DiskBasedCache.m5226a(outputStream, (String) entry.getValue());
                    }
                } else {
                    DiskBasedCache.m5224a(outputStream, 0);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                VolleyLog.m4737b("%s", e.toString());
                return false;
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ak.c.b */
    static class DiskBasedCache extends FilterInputStream {
        private int f3259a;

        private DiskBasedCache(InputStream inputStream) {
            super(inputStream);
            this.f3259a = 0;
        }

        public final int read() {
            int read = super.read();
            if (read != -1) {
                this.f3259a++;
            }
            return read;
        }

        public final int read(byte[] bArr, int i, int i2) {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.f3259a += read;
            }
            return read;
        }
    }

    public DiskBasedCache(File file, int i) {
        this.f3260a = new LinkedHashMap(16, 0.75f, true);
        this.f3261b = 0;
        this.f3262c = file;
        this.f3263d = 20971520;
    }

    public final synchronized Cache.Cache m5236a(String str) {
        Cache.Cache cache;
        IOException e;
        DiskBasedCache diskBasedCache;
        Throwable th;
        DiskBasedCache diskBasedCache2 = (DiskBasedCache) this.f3260a.get(str);
        if (diskBasedCache2 == null) {
            cache = null;
        } else {
            File d = m5233d(str);
            DiskBasedCache diskBasedCache3;
            try {
                diskBasedCache3 = new DiskBasedCache((byte) 0);
                try {
                    DiskBasedCache.m5220a((InputStream) diskBasedCache3);
                    byte[] a = DiskBasedCache.m5228a((InputStream) diskBasedCache3, (int) (d.length() - ((long) diskBasedCache3.f3259a)));
                    Cache.Cache cache2 = new Cache.Cache();
                    cache2.f2870a = a;
                    cache2.f2871b = diskBasedCache2.f3254c;
                    cache2.f2872c = diskBasedCache2.f3255d;
                    cache2.f2873d = diskBasedCache2.f3256e;
                    cache2.f2874e = diskBasedCache2.f3257f;
                    cache2.f2875f = diskBasedCache2.f3258g;
                    try {
                        diskBasedCache3.close();
                        cache = cache2;
                    } catch (IOException e2) {
                        cache = null;
                    }
                } catch (IOException e3) {
                    e = e3;
                    diskBasedCache = diskBasedCache3;
                    try {
                        VolleyLog.m4737b("%s: %s", d.getAbsolutePath(), e.toString());
                        m5230b(str);
                        if (diskBasedCache != null) {
                            try {
                                diskBasedCache.close();
                            } catch (IOException e4) {
                                cache = null;
                            }
                        }
                        cache = null;
                        return cache;
                    } catch (Throwable th2) {
                        th = th2;
                        diskBasedCache3 = diskBasedCache;
                        if (diskBasedCache3 != null) {
                            try {
                                diskBasedCache3.close();
                            } catch (IOException e5) {
                                cache = null;
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (diskBasedCache3 != null) {
                        diskBasedCache3.close();
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
                diskBasedCache = null;
                VolleyLog.m4737b("%s: %s", d.getAbsolutePath(), e.toString());
                m5230b(str);
                if (diskBasedCache != null) {
                    diskBasedCache.close();
                }
                cache = null;
                return cache;
            } catch (Throwable th4) {
                th = th4;
                diskBasedCache3 = null;
                if (diskBasedCache3 != null) {
                    diskBasedCache3.close();
                }
                throw th;
            }
        }
        return cache;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void m5237a() {
        /*
        r9 = this;
        r0 = 0;
        monitor-enter(r9);
        r1 = r9.f3262c;	 Catch:{ all -> 0x0065 }
        r1 = r1.exists();	 Catch:{ all -> 0x0065 }
        if (r1 != 0) goto L_0x0025;
    L_0x000a:
        r0 = r9.f3262c;	 Catch:{ all -> 0x0065 }
        r0 = r0.mkdirs();	 Catch:{ all -> 0x0065 }
        if (r0 != 0) goto L_0x0023;
    L_0x0012:
        r0 = "Unable to create cache dir %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0065 }
        r2 = 0;
        r3 = r9.f3262c;	 Catch:{ all -> 0x0065 }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x0065 }
        r1[r2] = r3;	 Catch:{ all -> 0x0065 }
        com.google.android.m4b.maps.p038a.VolleyLog.m4738c(r0, r1);	 Catch:{ all -> 0x0065 }
    L_0x0023:
        monitor-exit(r9);
        return;
    L_0x0025:
        r1 = r9.f3262c;	 Catch:{ all -> 0x0065 }
        r3 = r1.listFiles();	 Catch:{ all -> 0x0065 }
        if (r3 == 0) goto L_0x0023;
    L_0x002d:
        r4 = r3.length;	 Catch:{ all -> 0x0065 }
        r2 = r0;
    L_0x002f:
        if (r2 >= r4) goto L_0x0023;
    L_0x0031:
        r5 = r3[r2];	 Catch:{ all -> 0x0065 }
        r1 = 0;
        r0 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x004f, all -> 0x005e }
        r0.<init>(r5);	 Catch:{ IOException -> 0x004f, all -> 0x005e }
        r1 = com.google.android.m4b.maps.ak.DiskBasedCache.DiskBasedCache.m5220a(r0);	 Catch:{ IOException -> 0x0071 }
        r6 = r5.length();	 Catch:{ IOException -> 0x0071 }
        r1.f3252a = r6;	 Catch:{ IOException -> 0x0071 }
        r6 = r1.f3253b;	 Catch:{ IOException -> 0x0071 }
        r9.m5227a(r6, r1);	 Catch:{ IOException -> 0x0071 }
        r0.close();	 Catch:{ IOException -> 0x0068 }
    L_0x004b:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x002f;
    L_0x004f:
        r0 = move-exception;
        r0 = r1;
    L_0x0051:
        if (r5 == 0) goto L_0x0056;
    L_0x0053:
        r5.delete();	 Catch:{ all -> 0x006c }
    L_0x0056:
        if (r0 == 0) goto L_0x004b;
    L_0x0058:
        r0.close();	 Catch:{ IOException -> 0x005c }
        goto L_0x004b;
    L_0x005c:
        r0 = move-exception;
        goto L_0x004b;
    L_0x005e:
        r0 = move-exception;
    L_0x005f:
        if (r1 == 0) goto L_0x0064;
    L_0x0061:
        r1.close();	 Catch:{ IOException -> 0x006a }
    L_0x0064:
        throw r0;	 Catch:{ all -> 0x0065 }
    L_0x0065:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x0068:
        r0 = move-exception;
        goto L_0x004b;
    L_0x006a:
        r1 = move-exception;
        goto L_0x0064;
    L_0x006c:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x005f;
    L_0x0071:
        r1 = move-exception;
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ak.c.a():void");
    }

    public final synchronized void m5238a(String str, Cache.Cache cache) {
        int i = 0;
        synchronized (this) {
            int length = cache.f2870a.length;
            if (this.f3261b + ((long) length) >= ((long) this.f3263d)) {
                int i2;
                if (VolleyLog.f2942a) {
                    VolleyLog.m4735a("Pruning old cache entries.", new Object[0]);
                }
                long j = this.f3261b;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                Iterator it = this.f3260a.entrySet().iterator();
                while (it.hasNext()) {
                    DiskBasedCache diskBasedCache = (DiskBasedCache) ((Entry) it.next()).getValue();
                    if (m5233d(diskBasedCache.f3253b).delete()) {
                        this.f3261b -= diskBasedCache.f3252a;
                    } else {
                        VolleyLog.m4737b("Could not delete cache entry for key=%s, filename=%s", diskBasedCache.f3253b, DiskBasedCache.m5232c(diskBasedCache.f3253b));
                    }
                    it.remove();
                    i2 = i + 1;
                    if (((float) (this.f3261b + ((long) length))) >= ((float) this.f3263d) * 0.9f) {
                        i = i2;
                    }
                    if (VolleyLog.f2942a) {
                        VolleyLog.m4735a("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.f3261b - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                    }
                }
                i2 = i;
                if (VolleyLog.f2942a) {
                    VolleyLog.m4735a("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.f3261b - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                }
            }
            File d = m5233d(str);
            try {
                OutputStream fileOutputStream = new FileOutputStream(d);
                DiskBasedCache diskBasedCache2 = new DiskBasedCache(str, cache);
                diskBasedCache2.m5221a(fileOutputStream);
                fileOutputStream.write(cache.f2870a);
                fileOutputStream.close();
                m5227a(str, diskBasedCache2);
            } catch (IOException e) {
                if (!d.delete()) {
                    VolleyLog.m4737b("Could not clean up file %s", d.getAbsolutePath());
                }
            }
        }
    }

    private synchronized void m5230b(String str) {
        boolean delete = m5233d(str).delete();
        DiskBasedCache diskBasedCache = (DiskBasedCache) this.f3260a.get(str);
        if (diskBasedCache != null) {
            this.f3261b -= diskBasedCache.f3252a;
            this.f3260a.remove(str);
        }
        if (!delete) {
            VolleyLog.m4737b("Could not delete cache entry for key=%s, filename=%s", str, DiskBasedCache.m5232c(str));
        }
    }

    private static String m5232c(String str) {
        int length = str.length() / 2;
        return String.valueOf(str.substring(0, length).hashCode()) + String.valueOf(str.substring(length).hashCode());
    }

    private File m5233d(String str) {
        return new File(this.f3262c, DiskBasedCache.m5232c(str));
    }

    private void m5227a(String str, DiskBasedCache diskBasedCache) {
        if (this.f3260a.containsKey(str)) {
            DiskBasedCache diskBasedCache2 = (DiskBasedCache) this.f3260a.get(str);
            this.f3261b = (diskBasedCache.f3252a - diskBasedCache2.f3252a) + this.f3261b;
        } else {
            this.f3261b += diskBasedCache.f3252a;
        }
        this.f3260a.put(str, diskBasedCache);
    }

    private static byte[] m5228a(InputStream inputStream, int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException("Expected " + i + " bytes, read " + i2 + " bytes");
    }

    private static int m5235e(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void m5224a(OutputStream outputStream, int i) {
        outputStream.write((i >> 0) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 8) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 16) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 24) & MotionEventCompat.ACTION_MASK);
    }

    static int m5223a(InputStream inputStream) {
        return ((((DiskBasedCache.m5235e(inputStream) << 0) | 0) | (DiskBasedCache.m5235e(inputStream) << 8)) | (DiskBasedCache.m5235e(inputStream) << 16)) | (DiskBasedCache.m5235e(inputStream) << 24);
    }

    static void m5225a(OutputStream outputStream, long j) {
        outputStream.write((byte) ((int) (j >>> null)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static long m5229b(InputStream inputStream) {
        return (((((((0 | ((((long) DiskBasedCache.m5235e(inputStream)) & 255) << null)) | ((((long) DiskBasedCache.m5235e(inputStream)) & 255) << 8)) | ((((long) DiskBasedCache.m5235e(inputStream)) & 255) << 16)) | ((((long) DiskBasedCache.m5235e(inputStream)) & 255) << 24)) | ((((long) DiskBasedCache.m5235e(inputStream)) & 255) << 32)) | ((((long) DiskBasedCache.m5235e(inputStream)) & 255) << 40)) | ((((long) DiskBasedCache.m5235e(inputStream)) & 255) << 48)) | ((((long) DiskBasedCache.m5235e(inputStream)) & 255) << 56);
    }

    static void m5226a(OutputStream outputStream, String str) {
        byte[] bytes = str.getBytes(HTTP.UTF_8);
        DiskBasedCache.m5225a(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static String m5231c(InputStream inputStream) {
        return new String(DiskBasedCache.m5228a(inputStream, (int) DiskBasedCache.m5229b(inputStream)), HTTP.UTF_8);
    }

    static Map<String, String> m5234d(InputStream inputStream) {
        int a = DiskBasedCache.m5223a(inputStream);
        Map<String, String> emptyMap = a == 0 ? Collections.emptyMap() : new HashMap(a);
        for (int i = 0; i < a; i++) {
            emptyMap.put(DiskBasedCache.m5231c(inputStream).intern(), DiskBasedCache.m5231c(inputStream).intern());
        }
        return emptyMap;
    }
}
