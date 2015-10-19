package com.google.android.m4b.maps.bj;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/* renamed from: com.google.android.m4b.maps.bj.r */
public final class HttpCache {
    private final HttpCache f6615a;
    private final HashSet<String> f6616b;
    private final int f6617c;
    private File f6618d;
    private final String f6619e;

    /* renamed from: com.google.android.m4b.maps.bj.r.a */
    public static class HttpCache {
    }

    /* renamed from: com.google.android.m4b.maps.bj.r.b */
    static final class HttpCache implements Comparable<HttpCache> {
        public final File f6611a;
        public final String f6612b;
        public final long f6613c;
        boolean f6614d;

        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            HttpCache httpCache = (HttpCache) obj;
            if (this.f6613c < httpCache.f6613c) {
                return -1;
            }
            return this.f6613c > httpCache.f6613c ? 1 : this.f6612b.compareTo(httpCache.f6612b);
        }

        public HttpCache(File file) {
            this.f6614d = true;
            this.f6611a = file;
            this.f6612b = file.getName();
            this.f6613c = file.lastModified();
        }

        public final int hashCode() {
            return this.f6612b.hashCode();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.f6612b.equals(((HttpCache) obj).f6612b);
        }

        public final String toString() {
            return this.f6612b;
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.r.c */
    static class HttpCache extends LRUCache<String, HttpCache> {
        protected final /* synthetic */ void m10001a(Object obj, Object obj2) {
            HttpCache httpCache = (HttpCache) obj2;
            if (httpCache.f6614d && !httpCache.f6611a.delete()) {
                new StringBuilder("Failed to delete cache file ").append(httpCache.f6611a);
            }
        }

        public HttpCache(int i) {
            super(i);
        }

        public final void m10000a(HttpCache httpCache) {
            HttpCache httpCache2 = (HttpCache) m9921a((Object) httpCache.f6612b);
            if (httpCache2 != null) {
                httpCache2.f6614d = false;
            }
            m9925b(httpCache.f6612b, httpCache);
        }
    }

    public HttpCache(int i, String str, int i2) {
        this.f6616b = new HashSet();
        this.f6615a = new HttpCache(100);
        this.f6617c = 3;
        this.f6619e = str;
        m10003a();
    }

    public static void m10005a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public final byte[] m10012a(String str, boolean z, HttpCache httpCache, String str2, long j) {
        if (str2 != null && str2.length() < 5) {
            throw new IllegalArgumentException("persistentKey");
        } else if (7200000 < 1) {
            throw new IllegalArgumentException("shelfLife");
        } else if (this.f6618d == null || str2 == null) {
            return null;
        } else {
            byte[] b = m10009b(str2, 7200000);
            if (b != null) {
                return b;
            }
            HttpCache.m10007b("read");
            return null;
        }
    }

    private synchronized void m10004a(HttpCache httpCache) {
        if (httpCache != null) {
            this.f6615a.m9923b((Object) httpCache.f6612b);
        }
    }

    public final synchronized boolean m10011a(String str, long j) {
        boolean z;
        if (str != null) {
            if (str.length() >= 5) {
                if (7190000 < 1) {
                    throw new IllegalArgumentException("shelfLife");
                }
                HttpCache httpCache = (HttpCache) this.f6615a.m9921a((Object) str);
                z = httpCache != null && System.currentTimeMillis() - httpCache.f6613c <= 7190000;
            }
        }
        throw new IllegalArgumentException("persistentKey");
        return z;
    }

    private synchronized byte[] m10009b(String str, long j) {
        byte[] bArr;
        HttpCache httpCache = (HttpCache) this.f6615a.m9921a((Object) str);
        if (httpCache == null) {
            bArr = null;
        } else {
            bArr = m10006a(httpCache, j);
        }
        return bArr;
    }

    private byte[] m10006a(HttpCache httpCache, long j) {
        Closeable dataInputStream;
        Object e;
        Throwable th;
        if (System.currentTimeMillis() - httpCache.f6613c > j) {
            new StringBuilder("Expired cache file: ").append(httpCache);
            m10004a(httpCache);
            return null;
        }
        try {
            dataInputStream = new DataInputStream(new FileInputStream(httpCache.f6611a));
            try {
                byte[] bArr = new byte[((int) httpCache.f6611a.length())];
                dataInputStream.readFully(bArr);
                HttpCache.m10005a(dataInputStream);
                return bArr;
            } catch (FileNotFoundException e2) {
                e = e2;
                try {
                    new StringBuilder("Could not find cache file: ").append(e);
                    HttpCache.m10005a(dataInputStream);
                    m10004a(httpCache);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    HttpCache.m10005a(dataInputStream);
                    m10004a(httpCache);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                new StringBuilder("Could not read cache file: ").append(e);
                HttpCache.m10005a(dataInputStream);
                m10004a(httpCache);
                return null;
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            dataInputStream = null;
            new StringBuilder("Could not find cache file: ").append(e);
            HttpCache.m10005a(dataInputStream);
            m10004a(httpCache);
            return null;
        } catch (IOException e5) {
            e = e5;
            dataInputStream = null;
            new StringBuilder("Could not read cache file: ").append(e);
            HttpCache.m10005a(dataInputStream);
            m10004a(httpCache);
            return null;
        } catch (Throwable th3) {
            dataInputStream = null;
            th = th3;
            HttpCache.m10005a(dataInputStream);
            m10004a(httpCache);
            throw th;
        }
    }

    private File m10002a(String str) {
        return new File(this.f6618d, str);
    }

    public final void m10010a(byte[] bArr, String str) {
        if (str == null || str.length() < 5) {
            throw new IllegalArgumentException("persistentKey");
        } else if (this.f6618d != null) {
            m10008b(bArr, str);
        }
    }

    private void m10008b(byte[] bArr, String str) {
        Throwable th;
        File file;
        Closeable closeable = null;
        File a;
        try {
            a = m10002a(str);
            try {
                synchronized (this) {
                    try {
                        Closeable fileOutputStream = new FileOutputStream(a);
                        try {
                            fileOutputStream.write(bArr);
                            HttpCache.m10005a(fileOutputStream);
                            this.f6615a.m10000a(new HttpCache(a));
                            HttpCache.m10005a(null);
                        } catch (Throwable th2) {
                            th = th2;
                            closeable = fileOutputStream;
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                }
            } catch (FileNotFoundException e) {
                file = a;
                try {
                    throw new IOException("Couldn't create cache file " + file);
                } catch (Throwable th4) {
                    a = file;
                    th = th4;
                    HttpCache.m10005a(closeable);
                    if (a != null) {
                        a.delete();
                    }
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                HttpCache.m10005a(closeable);
                if (a != null) {
                    a.delete();
                }
                throw th;
            }
        } catch (FileNotFoundException e2) {
            file = null;
            throw new IOException("Couldn't create cache file " + file);
        } catch (Throwable th6) {
            th = th6;
            a = null;
            HttpCache.m10005a(closeable);
            if (a != null) {
                a.delete();
            }
            throw th;
        }
    }

    private synchronized void m10003a() {
        int i = 0;
        synchronized (this) {
            File file = new File(this.f6619e);
            if (file.isDirectory() || file.mkdirs()) {
                int i2;
                System.currentTimeMillis();
                String[] list = file.list();
                System.currentTimeMillis();
                this.f6618d = file;
                HttpCache[] httpCacheArr = new HttpCache[list.length];
                for (i2 = 0; i2 < list.length; i2++) {
                    httpCacheArr[i2] = new HttpCache(m10002a(list[i2]));
                }
                System.currentTimeMillis();
                Arrays.sort(httpCacheArr);
                System.currentTimeMillis();
                i2 = httpCacheArr.length;
                while (i < i2) {
                    HttpCache httpCache = httpCacheArr[i];
                    if (httpCache.f6611a.isFile()) {
                        this.f6615a.m10000a(httpCache);
                    }
                    i++;
                }
            } else {
                new StringBuilder("Could not open cache directory ").append(this.f6619e);
            }
        }
    }

    private static void m10007b(String str) {
        if (Thread.interrupted()) {
            throw new InterruptedException(str);
        }
    }
}
