package com.facebook.p022b;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.newrelic.agent.android.api.v1.Defaults;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* renamed from: com.facebook.b.l */
public final class FileLruCache {
    static final String f797a;
    private static final AtomicLong f798b;
    private final String f799c;
    private final FileLruCache f800d;
    private final File f801e;
    private boolean f802f;
    private boolean f803g;
    private final Object f804h;
    private AtomicLong f805i;

    /* renamed from: com.facebook.b.l.e */
    private interface FileLruCache {
        void m973a();
    }

    /* renamed from: com.facebook.b.l.1 */
    class FileLruCache implements FileLruCache {
        final /* synthetic */ long f782a;
        final /* synthetic */ File f783b;
        final /* synthetic */ String f784c;
        final /* synthetic */ FileLruCache f785d;

        FileLruCache(FileLruCache fileLruCache, long j, File file, String str) {
            this.f785d = fileLruCache;
            this.f782a = j;
            this.f783b = file;
            this.f784c = str;
        }

        public void m974a() {
            if (this.f782a < this.f785d.f805i.get()) {
                this.f783b.delete();
            } else {
                this.f785d.m988a(this.f784c, this.f783b);
            }
        }
    }

    /* renamed from: com.facebook.b.l.2 */
    class FileLruCache implements Runnable {
        final /* synthetic */ File[] f786a;
        final /* synthetic */ FileLruCache f787b;

        FileLruCache(FileLruCache fileLruCache, File[] fileArr) {
            this.f787b = fileLruCache;
            this.f786a = fileArr;
        }

        public void run() {
            for (File delete : this.f786a) {
                delete.delete();
            }
        }
    }

    /* renamed from: com.facebook.b.l.3 */
    class FileLruCache implements Runnable {
        final /* synthetic */ FileLruCache f788a;

        FileLruCache(FileLruCache fileLruCache) {
            this.f788a = fileLruCache;
        }

        public void run() {
            this.f788a.m992d();
        }
    }

    /* renamed from: com.facebook.b.l.a */
    private static class FileLruCache {
        private static final FilenameFilter f789a;
        private static final FilenameFilter f790b;

        /* renamed from: com.facebook.b.l.a.1 */
        static class FileLruCache implements FilenameFilter {
            FileLruCache() {
            }

            public boolean accept(File file, String str) {
                return !str.startsWith("buffer");
            }
        }

        /* renamed from: com.facebook.b.l.a.2 */
        static class FileLruCache implements FilenameFilter {
            FileLruCache() {
            }

            public boolean accept(File file, String str) {
                return str.startsWith("buffer");
            }
        }

        static {
            f789a = new FileLruCache();
            f790b = new FileLruCache();
        }

        static void m976a(File file) {
            File[] listFiles = file.listFiles(FileLruCache.m978b());
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }

        static FilenameFilter m975a() {
            return f789a;
        }

        static FilenameFilter m978b() {
            return f790b;
        }

        static File m977b(File file) {
            return new File(file, "buffer" + Long.valueOf(FileLruCache.f798b.incrementAndGet()).toString());
        }
    }

    /* renamed from: com.facebook.b.l.b */
    private static class FileLruCache extends OutputStream {
        final OutputStream f791a;
        final FileLruCache f792b;

        FileLruCache(OutputStream outputStream, FileLruCache fileLruCache) {
            this.f791a = outputStream;
            this.f792b = fileLruCache;
        }

        public void close() throws IOException {
            try {
                this.f791a.close();
            } finally {
                this.f792b.m973a();
            }
        }

        public void flush() throws IOException {
            this.f791a.flush();
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.f791a.write(bArr, i, i2);
        }

        public void write(byte[] bArr) throws IOException {
            this.f791a.write(bArr);
        }

        public void write(int i) throws IOException {
            this.f791a.write(i);
        }
    }

    /* renamed from: com.facebook.b.l.c */
    public static final class FileLruCache {
        private int f793a;
        private int f794b;

        public FileLruCache() {
            this.f794b = Defaults.RESPONSE_BODY_LIMIT;
            this.f793a = AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START;
        }

        int m979a() {
            return this.f793a;
        }

        int m980b() {
            return this.f794b;
        }
    }

    /* renamed from: com.facebook.b.l.d */
    private static final class FileLruCache implements Comparable<FileLruCache> {
        private final File f795a;
        private final long f796b;

        public /* synthetic */ int compareTo(Object obj) {
            return m981a((FileLruCache) obj);
        }

        FileLruCache(File file) {
            this.f795a = file;
            this.f796b = file.lastModified();
        }

        File m982a() {
            return this.f795a;
        }

        long m983b() {
            return this.f796b;
        }

        public int m981a(FileLruCache fileLruCache) {
            if (m983b() < fileLruCache.m983b()) {
                return -1;
            }
            if (m983b() > fileLruCache.m983b()) {
                return 1;
            }
            return m982a().compareTo(fileLruCache.m982a());
        }

        public boolean equals(Object obj) {
            return (obj instanceof FileLruCache) && m981a((FileLruCache) obj) == 0;
        }

        public int hashCode() {
            return ((this.f795a.hashCode() + 1073) * 37) + ((int) (this.f796b % 2147483647L));
        }
    }

    /* renamed from: com.facebook.b.l.f */
    private static final class FileLruCache {
        static void m985a(OutputStream outputStream, JSONObject jSONObject) throws IOException {
            byte[] bytes = (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject)).getBytes();
            outputStream.write(0);
            outputStream.write((bytes.length >> 16) & MotionEventCompat.ACTION_MASK);
            outputStream.write((bytes.length >> 8) & MotionEventCompat.ACTION_MASK);
            outputStream.write((bytes.length >> 0) & MotionEventCompat.ACTION_MASK);
            outputStream.write(bytes);
        }

        static JSONObject m984a(InputStream inputStream) throws IOException {
            int i = 0;
            if (inputStream.read() != 0) {
                return null;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < 3; i3++) {
                int read = inputStream.read();
                if (read == -1) {
                    Logger.m999a(LoggingBehavior.CACHE, FileLruCache.f797a, "readHeader: stream.read returned -1 while reading header size");
                    return null;
                }
                i2 = (i2 << 8) + (read & MotionEventCompat.ACTION_MASK);
            }
            byte[] bArr = new byte[i2];
            while (i < bArr.length) {
                i2 = inputStream.read(bArr, i, bArr.length - i);
                if (i2 < 1) {
                    Logger.m999a(LoggingBehavior.CACHE, FileLruCache.f797a, "readHeader: stream.read stopped at " + Integer.valueOf(i) + " when expected " + bArr.length);
                    return null;
                }
                i += i2;
            }
            try {
                Object nextValue = new JSONTokener(new String(bArr)).nextValue();
                if (nextValue instanceof JSONObject) {
                    return (JSONObject) nextValue;
                }
                Logger.m999a(LoggingBehavior.CACHE, FileLruCache.f797a, "readHeader: expected JSONObject, got " + nextValue.getClass().getCanonicalName());
                return null;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    static {
        f797a = FileLruCache.class.getSimpleName();
        f798b = new AtomicLong();
    }

    public FileLruCache(String str, FileLruCache fileLruCache) {
        this.f805i = new AtomicLong(0);
        this.f799c = str;
        this.f800d = fileLruCache;
        this.f801e = new File(FacebookSdk.m1213k(), str);
        this.f804h = new Object();
        if (this.f801e.mkdirs() || this.f801e.isDirectory()) {
            FileLruCache.m976a(this.f801e);
        }
    }

    public InputStream m993a(String str) throws IOException {
        return m994a(str, null);
    }

    public InputStream m994a(String str, String str2) throws IOException {
        File file = new File(this.f801e, Utility.m1129b(str));
        try {
            InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD);
            try {
                JSONObject a = FileLruCache.m984a(bufferedInputStream);
                if (a == null) {
                    return null;
                }
                String optString = a.optString("key");
                if (optString == null || !optString.equals(str)) {
                    bufferedInputStream.close();
                    return null;
                }
                String optString2 = a.optString("tag", null);
                if ((str2 != null || optString2 == null) && (str2 == null || str2.equals(optString2))) {
                    long time = new Date().getTime();
                    Logger.m999a(LoggingBehavior.CACHE, f797a, "Setting lastModified to " + Long.valueOf(time) + " for " + file.getName());
                    file.setLastModified(time);
                    return bufferedInputStream;
                }
                bufferedInputStream.close();
                return null;
            } finally {
                bufferedInputStream.close();
            }
        } catch (IOException e) {
            return null;
        }
    }

    public OutputStream m996b(String str) throws IOException {
        return m997b(str, null);
    }

    public OutputStream m997b(String str, String str2) throws IOException {
        File b = FileLruCache.m977b(this.f801e);
        b.delete();
        if (b.createNewFile()) {
            try {
                OutputStream bufferedOutputStream = new BufferedOutputStream(new FileLruCache(new FileOutputStream(b), new FileLruCache(this, System.currentTimeMillis(), b, str)), AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("key", str);
                    if (!Utility.m1126a(str2)) {
                        jSONObject.put("tag", str2);
                    }
                    FileLruCache.m985a(bufferedOutputStream, jSONObject);
                    return bufferedOutputStream;
                } catch (JSONException e) {
                    Logger.m998a(LoggingBehavior.CACHE, 5, f797a, "Error creating JSON header for cache file: " + e);
                    throw new IOException(e.getMessage());
                } catch (Throwable th) {
                    bufferedOutputStream.close();
                }
            } catch (FileNotFoundException e2) {
                Logger.m998a(LoggingBehavior.CACHE, 5, f797a, "Error creating buffer output stream: " + e2);
                throw new IOException(e2.getMessage());
            }
        }
        throw new IOException("Could not create file at " + b.getAbsolutePath());
    }

    public void m995a() {
        File[] listFiles = this.f801e.listFiles(FileLruCache.m975a());
        this.f805i.set(System.currentTimeMillis());
        if (listFiles != null) {
            FacebookSdk.m1206d().execute(new FileLruCache(this, listFiles));
        }
    }

    private void m988a(String str, File file) {
        if (!file.renameTo(new File(this.f801e, Utility.m1129b(str)))) {
            file.delete();
        }
        m991c();
    }

    public String toString() {
        return "{FileLruCache: tag:" + this.f799c + " file:" + this.f801e.getName() + "}";
    }

    private void m991c() {
        synchronized (this.f804h) {
            if (!this.f802f) {
                this.f802f = true;
                FacebookSdk.m1206d().execute(new FileLruCache(this));
            }
        }
    }

    private void m992d() {
        synchronized (this.f804h) {
            this.f802f = false;
            this.f803g = true;
        }
        try {
            File file;
            Logger.m999a(LoggingBehavior.CACHE, f797a, "trim started");
            PriorityQueue priorityQueue = new PriorityQueue();
            long j = 0;
            long j2 = 0;
            File[] listFiles = this.f801e.listFiles(FileLruCache.m975a());
            if (listFiles != null) {
                int length = listFiles.length;
                int i = 0;
                while (i < length) {
                    file = listFiles[i];
                    FileLruCache fileLruCache = new FileLruCache(file);
                    priorityQueue.add(fileLruCache);
                    Logger.m999a(LoggingBehavior.CACHE, f797a, "  trim considering time=" + Long.valueOf(fileLruCache.m983b()) + " name=" + fileLruCache.m982a().getName());
                    long length2 = file.length() + j;
                    i++;
                    j2 = 1 + j2;
                    j = length2;
                }
            }
            long j3 = j;
            j = j2;
            while (true) {
                if (j3 <= ((long) this.f800d.m979a()) && j <= ((long) this.f800d.m980b())) {
                    break;
                }
                file = ((FileLruCache) priorityQueue.remove()).m982a();
                Logger.m999a(LoggingBehavior.CACHE, f797a, "  trim removing " + file.getName());
                j3 -= file.length();
                j2 = j - 1;
                file.delete();
                j = j2;
            }
            synchronized (this.f804h) {
                this.f803g = false;
                this.f804h.notifyAll();
            }
        } catch (Throwable th) {
            synchronized (this.f804h) {
            }
            this.f803g = false;
            this.f804h.notifyAll();
        }
    }
}
