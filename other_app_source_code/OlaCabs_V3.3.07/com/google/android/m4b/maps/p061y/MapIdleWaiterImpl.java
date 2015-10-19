package com.google.android.m4b.maps.p061y;

import android.os.Handler;
import android.os.RemoteException;
import com.google.android.m4b.maps.av.RenderInstrumentation;
import com.google.android.m4b.maps.be.MapIdleWaiter;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.ah;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.y.i */
public final class MapIdleWaiterImpl extends RenderInstrumentation implements MapIdleWaiter, Runnable {
    private final Handler f8103a;
    private ah f8104b;
    private ah f8105c;
    private boolean f8106d;
    private boolean f8107e;
    private boolean f8108f;
    private boolean f8109g;
    private boolean f8110h;

    static {
        MapIdleWaiter.class.getSimpleName();
    }

    private MapIdleWaiterImpl(Handler handler) {
        this.f8106d = false;
        this.f8107e = false;
        this.f8108f = false;
        this.f8109g = false;
        this.f8110h = false;
        Preconditions.m1818a((Object) handler, (Object) "Handler is null");
        this.f8103a = handler;
    }

    public static MapIdleWaiterImpl m11731a(VectorMapView vectorMapView, Handler handler) {
        RenderInstrumentation mapIdleWaiterImpl = new MapIdleWaiterImpl(handler);
        vectorMapView.m11863a(mapIdleWaiterImpl);
        return mapIdleWaiterImpl;
    }

    public final void m11734a(ah ahVar) {
        synchronized (this) {
            this.f8104b = ahVar;
        }
        if (m11732f()) {
            run();
        }
    }

    public final void m11737b(ah ahVar) {
        synchronized (this) {
            this.f8105c = ahVar;
        }
        if (m11732f()) {
            run();
        }
    }

    private synchronized boolean m11732f() {
        boolean z;
        z = (this.f8106d || this.f8107e || this.f8108f || this.f8109g || !this.f8110h) ? false : true;
        return z;
    }

    public final synchronized void m11733a() {
        this.f8106d = true;
    }

    public final synchronized void m11736b() {
        this.f8106d = false;
        this.f8107e = true;
    }

    public final synchronized void m11738c() {
        this.f8107e = false;
        this.f8108f = true;
    }

    protected final synchronized void m11739d() {
        this.f8108f = false;
        this.f8109g = true;
    }

    protected final synchronized boolean m11735a(boolean z) {
        this.f8109g = false;
        this.f8110h = z;
        if (m11732f() && !(this.f8104b == null && this.f8105c == null)) {
            this.f8103a.post(this);
        }
        return false;
    }

    public final void run() {
        List<ah> a = ar.m2515a();
        synchronized (this) {
            if (this.f8104b != null) {
                a.add(this.f8104b);
                this.f8104b = null;
            }
            if (this.f8105c != null) {
                a.add(this.f8105c);
                this.f8105c = null;
            }
        }
        for (ah a2 : a) {
            try {
                a2.m9072a();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }
}
