package com.google.android.m4b.maps.at;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.at.a */
public final class Resource {
    private final ArrayList<ResourceListener> f4053a;
    private int f4054b;
    private boolean f4055c;
    private byte[] f4056d;
    private WeakReference<Bitmap> f4057e;
    private final CountDownLatch f4058f;
    private long f4059g;
    private long f4060h;

    public Resource() {
        this.f4054b = 0;
        this.f4058f = new CountDownLatch(1);
        this.f4053a = new ArrayList();
        this.f4060h = -1;
    }

    public final void m6583a(boolean z) {
        this.f4055c = z;
    }

    public final boolean m6584a() {
        return this.f4055c;
    }

    public final boolean m6586b() {
        return (this.f4054b == 0 || this.f4054b == 1) ? false : true;
    }

    public final Bitmap m6587c() {
        Bitmap bitmap = this.f4057e != null ? (Bitmap) this.f4057e.get() : null;
        if (bitmap == null && this.f4054b == 2 && this.f4056d != null) {
            synchronized (this) {
                if (this.f4057e != null) {
                    bitmap = (Bitmap) this.f4057e.get();
                } else {
                    bitmap = null;
                }
                if (bitmap == null && this.f4056d != null) {
                    Options options = new Options();
                    options.inPurgeable = true;
                    options.inInputShareable = true;
                    bitmap = BitmapFactoryInstrumentation.decodeByteArray(this.f4056d, 0, this.f4056d.length, options);
                    if (bitmap == null) {
                        this.f4054b = 1;
                        this.f4056d = null;
                    }
                    this.f4057e = new WeakReference(bitmap);
                }
            }
        }
        return bitmap;
    }

    public final byte[] m6588d() {
        if (this.f4054b == 3) {
            return this.f4056d;
        }
        return null;
    }

    public final long m6589e() {
        return this.f4059g;
    }

    public final void m6581a(long j) {
        this.f4060h = j;
    }

    public final long m6590f() {
        return this.f4060h;
    }

    final void m6582a(ResourceListener resourceListener) {
        synchronized (this.f4053a) {
            this.f4053a.add(resourceListener);
        }
    }

    public final boolean m6585a(ProtoBuf protoBuf) {
        int d = protoBuf.m10204d(3);
        String h = protoBuf.m10212h(7);
        if (d != HttpStatus.SC_OK || h == null) {
            if (d != HttpStatus.SC_NOT_MODIFIED) {
                this.f4054b = 1;
            }
            return false;
        }
        this.f4059g = protoBuf.m10207e(4);
        String toLowerCase = h.toLowerCase();
        if (toLowerCase.startsWith("image/")) {
            this.f4056d = protoBuf.m10203c(6);
            this.f4054b = 2;
        } else if (toLowerCase.equals("application/binary")) {
            this.f4056d = protoBuf.m10203c(6);
            this.f4054b = 3;
        } else {
            this.f4054b = 1;
        }
        if (this.f4054b != 1) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void m6591g() {
        /*
        r3 = this;
        r2 = r3.f4053a;
        monitor-enter(r2);
        r0 = 0;
        r1 = r0;
    L_0x0005:
        r0 = r3.f4053a;	 Catch:{ all -> 0x0028 }
        r0 = r0.size();	 Catch:{ all -> 0x0028 }
        if (r1 >= r0) goto L_0x001c;
    L_0x000d:
        r0 = r3.f4053a;	 Catch:{ all -> 0x0028 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x0028 }
        r0 = (com.google.android.m4b.maps.at.ResourceListener) r0;	 Catch:{ all -> 0x0028 }
        r0.m5369a(r3);	 Catch:{ all -> 0x0028 }
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0005;
    L_0x001c:
        r0 = r3.f4053a;	 Catch:{ all -> 0x0028 }
        r0.clear();	 Catch:{ all -> 0x0028 }
        monitor-exit(r2);	 Catch:{ all -> 0x0028 }
        r0 = r3.f4058f;
        r0.countDown();
        return;
    L_0x0028:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.at.a.g():void");
    }
}
