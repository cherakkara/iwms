package com.google.android.m4b.maps.p061y;

import android.os.Handler;
import android.os.RemoteException;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.be.IndoorStateInterface;
import com.google.android.m4b.maps.be.IndoorStateListener;
import com.google.android.m4b.maps.be.be;
import com.google.android.m4b.maps.be.ce;
import com.google.android.m4b.maps.be.cf;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.ad;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.bk;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.google.android.m4b.maps.y.h */
public final class IndoorStateAdapter implements IndoorStateInterface {
    private static long f8094a;
    private static final AtomicLong f8095b;
    private final IndoorState f8096c;
    private final long f8097d;
    private ad f8098e;
    private final Set<IndoorStateListener> f8099f;
    private final IndoorStateListener f8100g;
    private final Handler f8101h;
    private final be f8102i;

    /* renamed from: com.google.android.m4b.maps.y.h.1 */
    class IndoorStateAdapter implements IndoorStateListener {
        final /* synthetic */ IndoorStateAdapter f8093a;

        /* renamed from: com.google.android.m4b.maps.y.h.1.1 */
        class IndoorStateAdapter implements Runnable {
            private /* synthetic */ IndoorStateAdapter f8090a;

            IndoorStateAdapter(IndoorStateAdapter indoorStateAdapter) {
                this.f8090a = indoorStateAdapter;
            }

            public final void run() {
                synchronized (this.f8090a.f8093a) {
                    try {
                        if (this.f8090a.f8093a.f8098e != null) {
                            this.f8090a.f8093a.f8098e.m11091a();
                        }
                        for (IndoorStateListener a : this.f8090a.f8093a.f8099f) {
                            a.m9378a();
                        }
                    } catch (RemoteException e) {
                        throw new RuntimeRemoteException(e);
                    }
                }
            }
        }

        /* renamed from: com.google.android.m4b.maps.y.h.1.2 */
        class IndoorStateAdapter implements Runnable {
            private /* synthetic */ cf f8091a;
            private /* synthetic */ IndoorStateAdapter f8092b;

            IndoorStateAdapter(IndoorStateAdapter indoorStateAdapter, cf cfVar) {
                this.f8092b = indoorStateAdapter;
                this.f8091a = cfVar;
            }

            public final void run() {
                synchronized (this.f8092b.f8093a) {
                    try {
                        if (this.f8092b.f8093a.f8098e != null) {
                            this.f8092b.f8093a.f8098e.m11092a(new ce(this.f8092b.f8093a, this.f8091a, this.f8092b.f8093a.f8102i));
                        }
                        for (IndoorStateListener a : this.f8092b.f8093a.f8099f) {
                            a.m9379a(this.f8091a);
                        }
                    } catch (RemoteException e) {
                        throw new RuntimeRemoteException(e);
                    }
                }
            }
        }

        IndoorStateAdapter(IndoorStateAdapter indoorStateAdapter) {
            this.f8093a = indoorStateAdapter;
        }

        public final void m11709a() {
            if (this.f8093a.m11729e()) {
                this.f8093a.f8101h.post(new IndoorStateAdapter(this));
            }
        }

        public final void m11710a(cf cfVar) {
            if (this.f8093a.m11729e()) {
                this.f8093a.f8101h.post(new IndoorStateAdapter(this, cfVar));
            }
        }
    }

    static {
        f8094a = -1;
        f8095b = new AtomicLong(0);
    }

    public static IndoorStateAdapter m11712a(IndoorState indoorState, Handler handler, be beVar) {
        IndoorStateAdapter indoorStateAdapter = new IndoorStateAdapter(indoorState, handler, beVar);
        indoorStateAdapter.f8096c.m5350a(indoorStateAdapter.f8100g);
        return indoorStateAdapter;
    }

    private IndoorStateAdapter(IndoorState indoorState, Handler handler, be beVar) {
        this.f8097d = f8095b.getAndIncrement();
        this.f8099f = bk.m2870a();
        this.f8100g = new IndoorStateAdapter(this);
        this.f8096c = (IndoorState) Preconditions.m1817a((Object) indoorState);
        this.f8101h = (Handler) Preconditions.m1817a((Object) handler);
        this.f8102i = (be) Preconditions.m1817a((Object) beVar);
    }

    public final boolean m11721a() {
        if (f8094a != this.f8097d && f8094a != -1) {
            return false;
        }
        f8094a = this.f8097d;
        return true;
    }

    public final void m11723b() {
        if (f8094a == this.f8097d) {
            f8094a = -1;
        }
    }

    public final cf m11725c() {
        if (m11729e()) {
            return this.f8096c.m5360c();
        }
        return null;
    }

    public final int m11716a(cf cfVar) {
        if (!m11729e()) {
            return -1;
        }
        IndoorLevelReference a = this.f8096c.m5347a(cfVar.m5783a());
        return a != null ? cfVar.m5784b(a) : IndoorState.m5340c(cfVar);
    }

    public final int m11722b(cf cfVar) {
        if (!m11729e()) {
            return -1;
        }
        IndoorState indoorState = this.f8096c;
        return IndoorState.m5340c(cfVar);
    }

    public final boolean m11726c(cf cfVar) {
        if (!m11729e()) {
            return false;
        }
        IndoorState indoorState = this.f8096c;
        return IndoorState.m5341d(cfVar);
    }

    public final synchronized void m11719a(ad adVar) {
        this.f8098e = adVar;
    }

    public final boolean m11728d() {
        if (m11729e()) {
            return this.f8096c.m5363d();
        }
        return false;
    }

    public final boolean m11729e() {
        return this.f8097d == f8094a;
    }

    public final IndoorState m11730f() {
        return this.f8096c;
    }

    public final void m11718a(IndoorStateListener indoorStateListener) {
        this.f8099f.add(indoorStateListener);
    }

    public final void m11724b(IndoorStateListener indoorStateListener) {
        this.f8099f.remove(indoorStateListener);
    }

    public final IndoorLevelReference m11717a(FeatureId featureId) {
        if (m11729e()) {
            return this.f8096c.m5347a(featureId);
        }
        return null;
    }

    public final void m11727d(cf cfVar) {
        if (m11729e()) {
            this.f8096c.m5349a(cfVar);
        }
    }

    public final void m11720a(IndoorLevelReference indoorLevelReference) {
        if (m11729e()) {
            this.f8096c.m5352a(indoorLevelReference);
        }
    }
}
