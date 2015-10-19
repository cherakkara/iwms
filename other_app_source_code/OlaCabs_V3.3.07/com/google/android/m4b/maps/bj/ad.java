package com.google.android.m4b.maps.bj;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.SystemClock;
import android.util.Pair;
import com.google.android.m4b.maps.bj.ae.PanoramaRequest;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

/* compiled from: PanoramaManager */
public final class ad {
    private final HttpCache f6332a;
    private Object f6333b;
    private final HashSet<Object> f6334c;

    /* renamed from: com.google.android.m4b.maps.bj.ad.a */
    class PanoramaManager implements Runnable {
        private final PanoramaManager f6322a;
        private final String f6323b;
        private boolean f6324c;
        private /* synthetic */ ad f6325d;

        public PanoramaManager(ad adVar, PanoramaManager panoramaManager, String str, boolean z) {
            this.f6325d = adVar;
            this.f6322a = panoramaManager;
            this.f6323b = str;
            ad.m9764b(str, "persistentKey");
            this.f6324c = z;
        }

        public final void run() {
            Closeable byteArrayInputStream;
            PanoramaConfig a;
            Object b;
            Object b2;
            Throwable th;
            PanoramaConfig panoramaConfig = null;
            Thread.currentThread().getName();
            try {
                byteArrayInputStream = new ByteArrayInputStream(this.f6325d.f6332a.m10012a(null, false, null, this.f6323b, 7200000));
                a = PanoramaConfig.m9991a((InputStream) byteArrayInputStream);
                try {
                    HttpCache.m10005a(byteArrayInputStream);
                    b = ad.m9762b(this.f6325d);
                    if (this.f6323b != null && this.f6323b.equals(b)) {
                        a.f6599o = this.f6324c;
                        this.f6322a.m9750a(false, a);
                    }
                    this.f6325d.m9763b(this.f6323b);
                } catch (IOException e) {
                    b = ad.m9762b(this.f6325d);
                    if (this.f6323b != null && this.f6323b.equals(b)) {
                        a.f6599o = this.f6324c;
                        this.f6322a.m9750a(false, a);
                    }
                    this.f6325d.m9763b(this.f6323b);
                } catch (InterruptedException e2) {
                    try {
                        Thread.currentThread().interrupt();
                        b2 = ad.m9762b(this.f6325d);
                        if (this.f6323b != null && this.f6323b.equals(b2)) {
                            null.f6599o = this.f6324c;
                            this.f6322a.m9750a(true, null);
                        }
                        this.f6325d.m9763b(this.f6323b);
                    } catch (Throwable th2) {
                        panoramaConfig = a;
                        th = th2;
                        b = ad.m9762b(this.f6325d);
                        if (this.f6323b != null && this.f6323b.equals(b)) {
                            panoramaConfig.f6599o = this.f6324c;
                            this.f6322a.m9750a(false, panoramaConfig);
                        }
                        this.f6325d.m9763b(this.f6323b);
                        throw th;
                    }
                }
            } catch (IOException e3) {
                a = null;
                b = ad.m9762b(this.f6325d);
                a.f6599o = this.f6324c;
                this.f6322a.m9750a(false, a);
                this.f6325d.m9763b(this.f6323b);
            } catch (InterruptedException e4) {
                a = null;
                Thread.currentThread().interrupt();
                b2 = ad.m9762b(this.f6325d);
                null.f6599o = this.f6324c;
                this.f6322a.m9750a(true, null);
                this.f6325d.m9763b(this.f6323b);
            } catch (Throwable th3) {
                th = th3;
                b = ad.m9762b(this.f6325d);
                panoramaConfig.f6599o = this.f6324c;
                this.f6322a.m9750a(false, panoramaConfig);
                this.f6325d.m9763b(this.f6323b);
                throw th;
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.ad.b */
    public interface PanoramaManager {
        void m9750a(boolean z, PanoramaConfig panoramaConfig);
    }

    /* renamed from: com.google.android.m4b.maps.bj.ad.c */
    public interface PanoramaManager {
        void m9751a(ac acVar, Bitmap bitmap);
    }

    /* renamed from: com.google.android.m4b.maps.bj.ad.d */
    class PanoramaManager implements PanoramaRequest {
        private final PanoramaManager f6326a;
        private final PanoramaManager f6327b;
        private final Object f6328c;
        private int f6329d;
        private boolean f6330e;
        private /* synthetic */ ad f6331f;

        public PanoramaManager(ad adVar, PanoramaManager panoramaManager, PanoramaManager panoramaManager2, Object obj, boolean z) {
            this.f6331f = adVar;
            this.f6329d = 0;
            this.f6326a = panoramaManager;
            this.f6327b = panoramaManager2;
            this.f6328c = obj;
            this.f6330e = z;
        }

        public final void m9756a(PanoramaConfig panoramaConfig, ProtoBuf protoBuf) {
            this.f6329d++;
            if (!(this.f6326a == null || this.f6328c == null || !this.f6328c.equals(this.f6331f.f6333b))) {
                panoramaConfig.f6599o = this.f6330e;
                this.f6326a.m9750a(false, panoramaConfig);
            }
            String a = PanoramaConfig.m9992a(panoramaConfig.f6592h);
            try {
                SystemClock.uptimeMillis();
                this.f6331f.f6332a.m10010a(protoBuf.m10206d(), a);
            } catch (IOException e) {
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
            }
        }

        public final void m9757a(String str, int i, int i2, int i3, int i4, byte[] bArr) {
            ac acVar = new ac(str, i2, i3, i4, i);
            if (this.f6327b != null) {
                Options options = new Options();
                options.inDither = true;
                options.inPreferredConfig = Config.RGB_565;
                options.inPurgeable = true;
                this.f6327b.m9751a(acVar, BitmapFactoryInstrumentation.decodeByteArray(bArr, 0, bArr.length, options));
            }
            try {
                this.f6331f.f6332a.m10010a(bArr, acVar.m9744a());
            } catch (InterruptedException e) {
                new StringBuilder("PM failed to cache tile ").append(acVar);
            } catch (IOException e2) {
                new StringBuilder("PM was interrupted caching tile ").append(acVar);
                Thread.currentThread().interrupt();
            }
        }

        public final void m9755a() {
            if (!(this.f6326a == null || this.f6329d == 1 || this.f6328c == null || !this.f6328c.equals(this.f6331f.f6333b))) {
                this.f6326a.m9750a(true, null);
            }
            this.f6331f.m9763b(this.f6328c);
        }
    }

    static /* synthetic */ Object m9762b(ad adVar) {
        return adVar.f6333b instanceof String ? PanoramaConfig.m9992a((String) adVar.f6333b) : adVar.f6333b;
    }

    public ad(HttpCache httpCache) {
        this.f6334c = new HashSet();
        this.f6332a = httpCache;
    }

    private static void m9764b(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    private synchronized boolean m9761a(Object obj) {
        boolean z;
        if (this.f6334c.contains(obj)) {
            z = true;
        } else {
            this.f6334c.add(obj);
            z = false;
        }
        return z;
    }

    private synchronized void m9763b(Object obj) {
        this.f6334c.remove(obj);
    }

    public final boolean m9767a(PanoramaManager panoramaManager, String str, MapPoint mapPoint, Integer num, boolean z, boolean z2) {
        if (str != null) {
            m9764b(panoramaManager, "listener");
            m9764b(str, "panoId");
            String a = PanoramaConfig.m9992a(str);
            this.f6333b = str;
            if (m9761a((Object) a)) {
                return false;
            }
            if (this.f6332a.m10011a(a, 7190000)) {
                new Thread(new PanoramaManager(this, panoramaManager, a, z2), "Config-loader").start();
                return true;
            }
            new ae(new PanoramaManager(this, panoramaManager, null, str, z2), str).m9778j().m9773a(z).m9779k();
            return true;
        } else if (num != null) {
            int intValue = num.intValue();
            m9764b(panoramaManager, "listener");
            m9764b(mapPoint, "mapPoint");
            Object pair = new Pair(mapPoint, Integer.valueOf(intValue));
            this.f6333b = pair;
            if (m9761a(pair)) {
                return false;
            }
            new ae(new PanoramaManager(this, panoramaManager, null, pair, true), mapPoint, intValue).m9778j().m9773a(z).m9779k();
            return true;
        } else {
            m9764b(panoramaManager, "listener");
            m9764b(mapPoint, "mapPoint");
            this.f6333b = mapPoint;
            if (m9761a((Object) mapPoint)) {
                return false;
            }
            new ae(new PanoramaManager(this, panoramaManager, null, mapPoint, true), mapPoint).m9778j().m9773a(z).m9779k();
            return true;
        }
    }

    public final boolean m9768a(PanoramaManager panoramaManager, ac acVar, boolean z) {
        if (m9761a((Object) acVar)) {
            return false;
        }
        new ae(new PanoramaManager(this, null, panoramaManager, acVar, false), acVar.m9745b()).m9771a(acVar.m9749f()).m9772a(acVar.m9746c(), acVar.m9747d(), acVar.m9748e()).m9773a(z).m9779k();
        return true;
    }

    public final boolean m9769a(PanoramaManager panoramaManager, String str, int i, int i2, int i3, int i4, boolean z) {
        m9764b(panoramaManager, "listener");
        m9764b(str, "panoId");
        return m9768a(panoramaManager, new ac(str, 0, 0, i4, 0), z);
    }

    final void m9766a(String str) {
        this.f6333b = str;
    }
}
