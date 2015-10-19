package com.android.volley.toolbox;

import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import com.android.volley.Cache;
import com.android.volley.VolleyLog;
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

/* renamed from: com.android.volley.toolbox.c */
public class DiskBasedCache implements Cache {
    private final Map<String, DiskBasedCache> f561a;
    private long f562b;
    private final File f563c;
    private final int f564d;

    /* renamed from: com.android.volley.toolbox.c.a */
    static class DiskBasedCache {
        public long f553a;
        public String f554b;
        public String f555c;
        public long f556d;
        public long f557e;
        public long f558f;
        public Map<String, String> f559g;

        private DiskBasedCache() {
        }

        public DiskBasedCache(String str, Cache.Cache cache) {
            this.f554b = str;
            this.f553a = (long) cache.f467a.length;
            this.f555c = cache.f468b;
            this.f556d = cache.f469c;
            this.f557e = cache.f470d;
            this.f558f = cache.f471e;
            this.f559g = cache.f472f;
        }

        public static DiskBasedCache m616a(InputStream inputStream) throws IOException {
            DiskBasedCache diskBasedCache = new DiskBasedCache();
            if (DiskBasedCache.m620a(inputStream) != 538183203) {
                throw new IOException();
            }
            diskBasedCache.f554b = DiskBasedCache.m629c(inputStream);
            diskBasedCache.f555c = DiskBasedCache.m629c(inputStream);
            if (diskBasedCache.f555c.equals(Trace.NULL)) {
                diskBasedCache.f555c = null;
            }
            diskBasedCache.f556d = DiskBasedCache.m628b(inputStream);
            diskBasedCache.f557e = DiskBasedCache.m628b(inputStream);
            diskBasedCache.f558f = DiskBasedCache.m628b(inputStream);
            diskBasedCache.f559g = DiskBasedCache.m631d(inputStream);
            return diskBasedCache;
        }

        public Cache.Cache m617a(byte[] bArr) {
            Cache.Cache cache = new Cache.Cache();
            cache.f467a = bArr;
            cache.f468b = this.f555c;
            cache.f469c = this.f556d;
            cache.f470d = this.f557e;
            cache.f471e = this.f558f;
            cache.f472f = this.f559g;
            return cache;
        }

        public boolean m618a(OutputStream outputStream) {
            try {
                DiskBasedCache.m622a(outputStream, 538183203);
                DiskBasedCache.m624a(outputStream, this.f554b);
                DiskBasedCache.m624a(outputStream, this.f555c == null ? Trace.NULL : this.f555c);
                DiskBasedCache.m623a(outputStream, this.f556d);
                DiskBasedCache.m623a(outputStream, this.f557e);
                DiskBasedCache.m623a(outputStream, this.f558f);
                DiskBasedCache.m626a(this.f559g, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                VolleyLog.m594b("%s", e.toString());
                return false;
            }
        }
    }

    /* renamed from: com.android.volley.toolbox.c.b */
    private static class DiskBasedCache extends FilterInputStream {
        private int f560a;

        private DiskBasedCache(InputStream inputStream) {
            super(inputStream);
            this.f560a = 0;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.f560a++;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.f560a += read;
            }
            return read;
        }
    }

    public DiskBasedCache(File file, int i) {
        this.f561a = new LinkedHashMap(16, 0.75f, true);
        this.f562b = 0;
        this.f563c = file;
        this.f564d = i;
    }

    public DiskBasedCache(File file) {
        this(file, 5242880);
    }

    public synchronized Cache.Cache m634a(String str) {
        Cache.Cache cache;
        DiskBasedCache diskBasedCache;
        IOException e;
        Throwable th;
        DiskBasedCache diskBasedCache2 = (DiskBasedCache) this.f561a.get(str);
        if (diskBasedCache2 == null) {
            cache = null;
        } else {
            File c = m638c(str);
            try {
                diskBasedCache = new DiskBasedCache(null);
                try {
                    DiskBasedCache.m616a((InputStream) diskBasedCache);
                    cache = diskBasedCache2.m617a(DiskBasedCache.m627a((InputStream) diskBasedCache, (int) (c.length() - ((long) diskBasedCache.f560a))));
                    if (diskBasedCache != null) {
                        try {
                            diskBasedCache.close();
                        } catch (IOException e2) {
                            cache = null;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    try {
                        VolleyLog.m594b("%s: %s", c.getAbsolutePath(), e.toString());
                        m637b(str);
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
                        if (diskBasedCache != null) {
                            try {
                                diskBasedCache.close();
                            } catch (IOException e5) {
                                cache = null;
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e6) {
                e = e6;
                diskBasedCache = null;
                VolleyLog.m594b("%s: %s", c.getAbsolutePath(), e.toString());
                m637b(str);
                if (diskBasedCache != null) {
                    diskBasedCache.close();
                }
                cache = null;
                return cache;
            } catch (Throwable th3) {
                th = th3;
                diskBasedCache = null;
                if (diskBasedCache != null) {
                    diskBasedCache.close();
                }
                throw th;
            }
        }
        return cache;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void m635a() {
        /*
        r9 = this;
        r0 = 0;
        monitor-enter(r9);
        r1 = r9.f563c;	 Catch:{ all -> 0x006c }
        r1 = r1.exists();	 Catch:{ all -> 0x006c }
        if (r1 != 0) goto L_0x0025;
    L_0x000a:
        r0 = r9.f563c;	 Catch:{ all -> 0x006c }
        r0 = r0.mkdirs();	 Catch:{ all -> 0x006c }
        if (r0 != 0) goto L_0x0023;
    L_0x0012:
        r0 = "Unable to create cache dir %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x006c }
        r2 = 0;
        r3 = r9.f563c;	 Catch:{ all -> 0x006c }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x006c }
        r1[r2] = r3;	 Catch:{ all -> 0x006c }
        com.android.volley.VolleyLog.m595c(r0, r1);	 Catch:{ all -> 0x006c }
    L_0x0023:
        monitor-exit(r9);
        return;
    L_0x0025:
        r1 = r9.f563c;	 Catch:{ all -> 0x006c }
        r3 = r1.listFiles();	 Catch:{ all -> 0x006c }
        if (r3 == 0) goto L_0x0023;
    L_0x002d:
        r4 = r3.length;	 Catch:{ all -> 0x006c }
        r2 = r0;
    L_0x002f:
        if (r2 >= r4) goto L_0x0023;
    L_0x0031:
        r5 = r3[r2];	 Catch:{ all -> 0x006c }
        r1 = 0;
        r0 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x0056, all -> 0x0065 }
        r6 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0056, all -> 0x0065 }
        r6.<init>(r5);	 Catch:{ IOException -> 0x0056, all -> 0x0065 }
        r0.<init>(r6);	 Catch:{ IOException -> 0x0056, all -> 0x0065 }
        r1 = com.android.volley.toolbox.DiskBasedCache.DiskBasedCache.m616a(r0);	 Catch:{ IOException -> 0x0078 }
        r6 = r5.length();	 Catch:{ IOException -> 0x0078 }
        r1.f553a = r6;	 Catch:{ IOException -> 0x0078 }
        r6 = r1.f554b;	 Catch:{ IOException -> 0x0078 }
        r9.m625a(r6, r1);	 Catch:{ IOException -> 0x0078 }
        if (r0 == 0) goto L_0x0052;
    L_0x004f:
        r0.close();	 Catch:{ IOException -> 0x006f }
    L_0x0052:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x002f;
    L_0x0056:
        r0 = move-exception;
        r0 = r1;
    L_0x0058:
        if (r5 == 0) goto L_0x005d;
    L_0x005a:
        r5.delete();	 Catch:{ all -> 0x0073 }
    L_0x005d:
        if (r0 == 0) goto L_0x0052;
    L_0x005f:
        r0.close();	 Catch:{ IOException -> 0x0063 }
        goto L_0x0052;
    L_0x0063:
        r0 = move-exception;
        goto L_0x0052;
    L_0x0065:
        r0 = move-exception;
    L_0x0066:
        if (r1 == 0) goto L_0x006b;
    L_0x0068:
        r1.close();	 Catch:{ IOException -> 0x0071 }
    L_0x006b:
        throw r0;	 Catch:{ all -> 0x006c }
    L_0x006c:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x006f:
        r0 = move-exception;
        goto L_0x0052;
    L_0x0071:
        r1 = move-exception;
        goto L_0x006b;
    L_0x0073:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0066;
    L_0x0078:
        r1 = move-exception;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.c.a():void");
    }

    public synchronized void m636a(String str, Cache.Cache cache) {
        m621a(cache.f467a.length);
        File c = m638c(str);
        try {
            OutputStream fileOutputStream = new FileOutputStream(c);
            DiskBasedCache diskBasedCache = new DiskBasedCache(str, cache);
            if (diskBasedCache.m618a(fileOutputStream)) {
                fileOutputStream.write(cache.f467a);
                fileOutputStream.close();
                m625a(str, diskBasedCache);
            } else {
                fileOutputStream.close();
                VolleyLog.m594b("Failed to write header for %s", c.getAbsolutePath());
                throw new IOException();
            }
        } catch (IOException e) {
            if (!c.delete()) {
                VolleyLog.m594b("Could not clean up file %s", c.getAbsolutePath());
            }
        }
    }

    public synchronized void m637b(String str) {
        boolean delete = m638c(str).delete();
        m633e(str);
        if (!delete) {
            VolleyLog.m594b("Could not delete cache entry for key=%s, filename=%s", str, m630d(str));
        }
    }

    private String m630d(String str) {
        int length = str.length() / 2;
        return String.valueOf(str.substring(0, length).hashCode()) + String.valueOf(str.substring(length).hashCode());
    }

    public File m638c(String str) {
        return new File(this.f563c, m630d(str));
    }

    private void m621a(int i) {
        if (this.f562b + ((long) i) >= ((long) this.f564d)) {
            int i2;
            if (VolleyLog.f533b) {
                VolleyLog.m592a("Pruning old cache entries.", new Object[0]);
            }
            long j = this.f562b;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator it = this.f561a.entrySet().iterator();
            int i3 = 0;
            while (it.hasNext()) {
                DiskBasedCache diskBasedCache = (DiskBasedCache) ((Entry) it.next()).getValue();
                if (m638c(diskBasedCache.f554b).delete()) {
                    this.f562b -= diskBasedCache.f553a;
                } else {
                    VolleyLog.m594b("Could not delete cache entry for key=%s, filename=%s", diskBasedCache.f554b, m630d(diskBasedCache.f554b));
                }
                it.remove();
                i2 = i3 + 1;
                if (((float) (this.f562b + ((long) i))) < ((float) this.f564d) * 0.9f) {
                    break;
                }
                i3 = i2;
            }
            i2 = i3;
            if (VolleyLog.f533b) {
                VolleyLog.m592a("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.f562b - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    private void m625a(String str, DiskBasedCache diskBasedCache) {
        if (this.f561a.containsKey(str)) {
            DiskBasedCache diskBasedCache2 = (DiskBasedCache) this.f561a.get(str);
            this.f562b = (diskBasedCache.f553a - diskBasedCache2.f553a) + this.f562b;
        } else {
            this.f562b += diskBasedCache.f553a;
        }
        this.f561a.put(str, diskBasedCache);
    }

    private void m633e(String str) {
        DiskBasedCache diskBasedCache = (DiskBasedCache) this.f561a.get(str);
        if (diskBasedCache != null) {
            this.f562b -= diskBasedCache.f553a;
            this.f561a.remove(str);
        }
    }

    private static byte[] m627a(InputStream inputStream, int i) throws IOException {
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

    private static int m632e(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void m622a(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 0) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 8) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 16) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 24) & MotionEventCompat.ACTION_MASK);
    }

    static int m620a(InputStream inputStream) throws IOException {
        return (((0 | (DiskBasedCache.m632e(inputStream) << 0)) | (DiskBasedCache.m632e(inputStream) << 8)) | (DiskBasedCache.m632e(inputStream) << 16)) | (DiskBasedCache.m632e(inputStream) << 24);
    }

    static void m623a(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) (j >>> null)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static long m628b(InputStream inputStream) throws IOException {
        return (((((((0 | ((((long) DiskBasedCache.m632e(inputStream)) & 255) << null)) | ((((long) DiskBasedCache.m632e(inputStream)) & 255) << 8)) | ((((long) DiskBasedCache.m632e(inputStream)) & 255) << 16)) | ((((long) DiskBasedCache.m632e(inputStream)) & 255) << 24)) | ((((long) DiskBasedCache.m632e(inputStream)) & 255) << 32)) | ((((long) DiskBasedCache.m632e(inputStream)) & 255) << 40)) | ((((long) DiskBasedCache.m632e(inputStream)) & 255) << 48)) | ((((long) DiskBasedCache.m632e(inputStream)) & 255) << 56);
    }

    static void m624a(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes(HTTP.UTF_8);
        DiskBasedCache.m623a(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static String m629c(InputStream inputStream) throws IOException {
        return new String(DiskBasedCache.m627a(inputStream, (int) DiskBasedCache.m628b(inputStream)), HTTP.UTF_8);
    }

    static void m626a(Map<String, String> map, OutputStream outputStream) throws IOException {
        if (map != null) {
            DiskBasedCache.m622a(outputStream, map.size());
            for (Entry entry : map.entrySet()) {
                DiskBasedCache.m624a(outputStream, (String) entry.getKey());
                DiskBasedCache.m624a(outputStream, (String) entry.getValue());
            }
            return;
        }
        DiskBasedCache.m622a(outputStream, 0);
    }

    static Map<String, String> m631d(InputStream inputStream) throws IOException {
        int a = DiskBasedCache.m620a(inputStream);
        Map<String, String> emptyMap = a == 0 ? Collections.emptyMap() : new HashMap(a);
        for (int i = 0; i < a; i++) {
            emptyMap.put(DiskBasedCache.m629c(inputStream).intern(), DiskBasedCache.m629c(inputStream).intern());
        }
        return emptyMap;
    }
}
