package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.images.C0406a.C0405a;
import com.google.android.gms.common.images.C0406a.C0407b;
import com.google.android.gms.common.internal.C0416e;
import com.google.android.gms.internal.C0401d;
import com.google.android.gms.internal.C0498c;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public final class ImageManager {
    private static final Object f2210a;
    private static HashSet<Uri> f2211b;
    private final Context f2212c;
    private final Handler f2213d;
    private final ExecutorService f2214e;
    private final C0402b f2215f;
    private final C0498c f2216g;
    private final Map<C0406a, ImageReceiver> f2217h;
    private final Map<Uri, ImageReceiver> f2218i;
    private final Map<Uri, Long> f2219j;

    private final class ImageReceiver extends ResultReceiver {
        final /* synthetic */ ImageManager f2191a;
        private final Uri f2192b;
        private final ArrayList<C0406a> f2193c;

        public void onReceiveResult(int i, Bundle bundle) {
            this.f2191a.f2214e.execute(new C0403c(this.f2191a, this.f2192b, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    /* renamed from: com.google.android.gms.common.images.ImageManager.a */
    public interface C0400a {
        void m3516a(Uri uri, Drawable drawable, boolean z);
    }

    /* renamed from: com.google.android.gms.common.images.ImageManager.b */
    private static final class C0402b extends C0401d<C0405a, Bitmap> {
        protected int m3525a(C0405a c0405a, Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        protected /* synthetic */ int m3526a(Object obj, Object obj2) {
            return m3525a((C0405a) obj, (Bitmap) obj2);
        }

        protected void m3527a(boolean z, C0405a c0405a, Bitmap bitmap, Bitmap bitmap2) {
            super.m3522a(z, c0405a, bitmap, bitmap2);
        }

        protected /* synthetic */ void m3528a(boolean z, Object obj, Object obj2, Object obj3) {
            m3527a(z, (C0405a) obj, (Bitmap) obj2, (Bitmap) obj3);
        }
    }

    /* renamed from: com.google.android.gms.common.images.ImageManager.c */
    private final class C0403c implements Runnable {
        final /* synthetic */ ImageManager f2202a;
        private final Uri f2203b;
        private final ParcelFileDescriptor f2204c;

        public C0403c(ImageManager imageManager, Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.f2202a = imageManager;
            this.f2203b = uri;
            this.f2204c = parcelFileDescriptor;
        }

        public void run() {
            C0416e.m3568b("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmap = null;
            if (this.f2204c != null) {
                try {
                    bitmap = BitmapFactoryInstrumentation.decodeFileDescriptor(this.f2204c.getFileDescriptor());
                } catch (Throwable e) {
                    Log.e("ImageManager", "OOM while loading bitmap for uri: " + this.f2203b, e);
                    z = true;
                }
                try {
                    this.f2204c.close();
                } catch (Throwable e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.f2202a.f2213d.post(new C0404d(this.f2202a, this.f2203b, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                Log.w("ImageManager", "Latch interrupted while posting " + this.f2203b);
            }
        }
    }

    /* renamed from: com.google.android.gms.common.images.ImageManager.d */
    private final class C0404d implements Runnable {
        final /* synthetic */ ImageManager f2205a;
        private final Uri f2206b;
        private final Bitmap f2207c;
        private final CountDownLatch f2208d;
        private boolean f2209e;

        public C0404d(ImageManager imageManager, Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.f2205a = imageManager;
            this.f2206b = uri;
            this.f2207c = bitmap;
            this.f2209e = z;
            this.f2208d = countDownLatch;
        }

        private void m3529a(ImageReceiver imageReceiver, boolean z) {
            ArrayList a = imageReceiver.f2193c;
            int size = a.size();
            for (int i = 0; i < size; i++) {
                C0406a c0406a = (C0406a) a.get(i);
                if (z) {
                    c0406a.m3546a(this.f2205a.f2212c, this.f2207c, false);
                } else {
                    this.f2205a.f2219j.put(this.f2206b, Long.valueOf(SystemClock.elapsedRealtime()));
                    c0406a.m3547a(this.f2205a.f2212c, this.f2205a.f2216g, false);
                }
                if (!(c0406a instanceof C0407b)) {
                    this.f2205a.f2217h.remove(c0406a);
                }
            }
        }

        public void run() {
            C0416e.m3567a("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.f2207c != null;
            if (this.f2205a.f2215f != null) {
                if (this.f2209e) {
                    this.f2205a.f2215f.m3520a();
                    System.gc();
                    this.f2209e = false;
                    this.f2205a.f2213d.post(this);
                    return;
                } else if (z) {
                    this.f2205a.f2215f.m3524b(new C0405a(this.f2206b), this.f2207c);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) this.f2205a.f2218i.remove(this.f2206b);
            if (imageReceiver != null) {
                m3529a(imageReceiver, z);
            }
            this.f2208d.countDown();
            synchronized (ImageManager.f2210a) {
                ImageManager.f2211b.remove(this.f2206b);
            }
        }
    }

    static {
        f2210a = new Object();
        f2211b = new HashSet();
    }
}
