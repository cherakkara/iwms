package com.google.android.m4b.maps.ap;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.google.android.m4b.maps.an.bh;
import com.google.android.m4b.maps.an.bi;
import com.google.android.m4b.maps.aq.IndoorBuildingCallback;
import com.google.android.m4b.maps.aq.IndoorBuildingRequest;
import com.google.android.m4b.maps.ar.IndoorBuildingCache;
import com.google.android.m4b.maps.au.DriveAboutThread;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.DataRequestListener;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.android.m4b.maps.p058v.Util;
import com.google.p025a.p028c.au;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.File;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.google.android.m4b.maps.ap.d */
public final class IndoorBuildingStore extends DriveAboutThread implements DataRequestListener {
    private static IndoorBuildingStore f3812a;
    private final Clock f3813b;
    private final DataRequestDispatcher f3814c;
    private final IndoorBuildingCache f3815d;
    private final File f3816e;
    private boolean f3817f;
    private Handler f3818g;
    private final Map<FeatureId, IndoorBuildingRequest> f3819h;
    private boolean f3820i;

    /* renamed from: com.google.android.m4b.maps.ap.d.1 */
    class IndoorBuildingStore extends Handler {
        private /* synthetic */ IndoorBuildingStore f3809a;

        IndoorBuildingStore(IndoorBuildingStore indoorBuildingStore) {
            this.f3809a = indoorBuildingStore;
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    IndoorBuildingStore.m6257a(this.f3809a, (IndoorBuildingStore) message.obj);
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    IndoorBuildingStore.m6256a(this.f3809a);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    IndoorBuildingStore.m6258a(this.f3809a, (IndoorBuildingRequest) message.obj);
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    IndoorBuildingStore.m6259b(this.f3809a, (IndoorBuildingRequest) message.obj);
                default:
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ap.d.a */
    static class IndoorBuildingStore {
        public final FeatureId f3810a;
        public final IndoorBuildingCallback f3811b;

        public IndoorBuildingStore(FeatureId featureId, IndoorBuildingCallback indoorBuildingCallback) {
            this.f3810a = featureId;
            this.f3811b = indoorBuildingCallback;
        }
    }

    static /* synthetic */ void m6256a(IndoorBuildingStore indoorBuildingStore) {
        indoorBuildingStore.f3820i = false;
        indoorBuildingStore.f3814c.m11454d();
        try {
            for (DataRequest dataRequest : indoorBuildingStore.f3819h.values()) {
                if (!dataRequest.m6331l()) {
                    indoorBuildingStore.f3814c.m11451c(dataRequest);
                    dataRequest.m6330k();
                }
            }
        } finally {
            indoorBuildingStore.f3814c.m11456e();
        }
    }

    static /* synthetic */ void m6257a(IndoorBuildingStore indoorBuildingStore, IndoorBuildingStore indoorBuildingStore2) {
        FeatureId featureId = indoorBuildingStore2.f3810a;
        IndoorBuildingCallback indoorBuildingCallback = indoorBuildingStore2.f3811b;
        bh a = indoorBuildingStore.f3815d.m6469a(featureId);
        if (a != null) {
            if (indoorBuildingCallback != null) {
                IndoorBuildingCache indoorBuildingCache = indoorBuildingStore.f3815d;
                if (IndoorBuildingCache.m6467a(a)) {
                    indoorBuildingCallback.m5266a(featureId, 2, null);
                } else {
                    indoorBuildingCallback.m5266a(featureId, 0, a);
                }
            }
            if (!a.m5793a(indoorBuildingStore.f3813b)) {
                return;
            }
        }
        IndoorBuildingRequest indoorBuildingRequest = (IndoorBuildingRequest) indoorBuildingStore.f3819h.get(featureId);
        if (indoorBuildingRequest == null) {
            indoorBuildingRequest = new IndoorBuildingRequest(featureId);
            indoorBuildingStore.f3819h.put(featureId, indoorBuildingRequest);
        }
        if (indoorBuildingCallback != null) {
            indoorBuildingRequest.m6325a(indoorBuildingCallback);
        }
        if (!indoorBuildingRequest.m6331l() && !indoorBuildingStore.f3820i) {
            indoorBuildingStore.f3818g.sendEmptyMessageDelayed(1, 50);
            indoorBuildingStore.f3820i = true;
        }
    }

    static /* synthetic */ void m6258a(IndoorBuildingStore indoorBuildingStore, IndoorBuildingRequest indoorBuildingRequest) {
        indoorBuildingStore.f3819h.remove(indoorBuildingRequest.m6329j());
        bh bhVar = null;
        if (indoorBuildingRequest.m6333n()) {
            indoorBuildingStore.f3815d.m6475c(indoorBuildingRequest.m6329j());
        } else {
            ProtoBuf m = indoorBuildingRequest.m6332m();
            if (m != null) {
                bhVar = indoorBuildingStore.f3815d.m6470a(indoorBuildingRequest.m6329j(), m);
            }
        }
        indoorBuildingRequest.m6324a(bhVar);
    }

    static /* synthetic */ void m6259b(IndoorBuildingStore indoorBuildingStore, IndoorBuildingRequest indoorBuildingRequest) {
        indoorBuildingStore.f3819h.remove(indoorBuildingRequest.m6329j());
        indoorBuildingRequest.m6324a(null);
    }

    private IndoorBuildingStore(DataRequestDispatcher dataRequestDispatcher, File file, Locale locale, Clock clock) {
        super("ibs");
        this.f3814c = dataRequestDispatcher;
        this.f3813b = clock;
        this.f3815d = new IndoorBuildingCache(locale, this.f3813b);
        this.f3816e = file;
        this.f3819h = au.m2815b();
    }

    public static IndoorBuildingStore m6255a(DataRequestDispatcher dataRequestDispatcher, File file, Locale locale, Clock clock) {
        if (f3812a == null) {
            f3812a = new IndoorBuildingStore(dataRequestDispatcher, file, locale, clock);
        }
        return f3812a;
    }

    public static IndoorBuildingStore m6260c() {
        return f3812a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m6271d() {
        /*
        r1 = this;
        r1.start();
        monitor-enter(r1);	 Catch:{ InterruptedException -> 0x000f }
    L_0x0004:
        r0 = r1.f3818g;	 Catch:{ all -> 0x000c }
        if (r0 != 0) goto L_0x001d;
    L_0x0008:
        r1.wait();	 Catch:{ all -> 0x000c }
        goto L_0x0004;
    L_0x000c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ InterruptedException -> 0x000f }
        throw r0;	 Catch:{ InterruptedException -> 0x000f }
    L_0x000f:
        r0 = move-exception;
        r0 = java.lang.Thread.currentThread();
        r0.interrupt();
    L_0x0017:
        r0 = r1.f3814c;
        r0.m11444a(r1);
        return;
    L_0x001d:
        monitor-exit(r1);	 Catch:{ all -> 0x000c }
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ap.d.d():void");
    }

    public final void m6273f() {
        try {
            Process.setThreadPriority(VectorGlobalState.m7288d());
        } catch (SecurityException e) {
            Util.m11550a(getName(), "Could not set thread priority: " + e);
        }
        Looper.prepare();
        this.f3818g = new IndoorBuildingStore(this);
        synchronized (this) {
            notifyAll();
        }
        if (Util.m11556b()) {
            this.f3815d.m6472a(this.f3816e);
        }
        synchronized (this) {
            this.f3817f = true;
            notifyAll();
        }
        Looper.loop();
    }

    public final void m6265a(FeatureId featureId, IndoorBuildingCallback indoorBuildingCallback) {
        this.f3818g.sendMessage(this.f3818g.obtainMessage(0, new IndoorBuildingStore(featureId, indoorBuildingCallback)));
    }

    public final bh m6262a(FeatureId featureId) {
        bh b = this.f3815d.m6473b(featureId);
        IndoorBuildingCache indoorBuildingCache = this.f3815d;
        if (IndoorBuildingCache.m6467a(b)) {
            return null;
        }
        return b;
    }

    public final boolean m6269b(FeatureId featureId) {
        bh b = this.f3815d.m6473b(featureId);
        IndoorBuildingCache indoorBuildingCache = this.f3815d;
        return IndoorBuildingCache.m6467a(b);
    }

    public final bi m6270c(FeatureId featureId) {
        bh a = m6262a(featureId);
        if (a != null) {
            return a.m5790a(featureId);
        }
        return null;
    }

    public final void m6266a(DataRequest dataRequest) {
        if (dataRequest.m4778i() == 118) {
            this.f3818g.sendMessage(this.f3818g.obtainMessage(2, dataRequest));
        }
    }

    public final void m6264a(int i, boolean z, String str) {
    }

    public final void m6263a() {
    }

    public final void m6267b() {
    }

    public final void m6268b(DataRequest dataRequest) {
        if (dataRequest.m4778i() == 118) {
            this.f3818g.sendMessage(this.f3818g.obtainMessage(3, dataRequest));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m6261h() {
        /*
        r1 = this;
        monitor-enter(r1);	 Catch:{ InterruptedException -> 0x000c }
    L_0x0001:
        r0 = r1.f3817f;	 Catch:{ all -> 0x0009 }
        if (r0 != 0) goto L_0x0015;
    L_0x0005:
        r1.wait();	 Catch:{ all -> 0x0009 }
        goto L_0x0001;
    L_0x0009:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ InterruptedException -> 0x000c }
        throw r0;	 Catch:{ InterruptedException -> 0x000c }
    L_0x000c:
        r0 = move-exception;
        r0 = java.lang.Thread.currentThread();
        r0.interrupt();
    L_0x0014:
        return;
    L_0x0015:
        monitor-exit(r1);	 Catch:{ all -> 0x0009 }
        goto L_0x0014;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ap.d.h():void");
    }

    public final void m6272e() {
        m6261h();
        this.f3815d.m6471a();
    }

    public final void m6274g() {
        m6261h();
        this.f3815d.m6474b();
    }
}
