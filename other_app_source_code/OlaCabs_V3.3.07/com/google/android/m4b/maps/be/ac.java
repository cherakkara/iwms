package com.google.android.m4b.maps.be;

import android.os.RemoteException;
import com.google.android.m4b.maps.model.MapsEngineLayerInfo;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p042r.ab;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.olacabs.customer.utils.Constants;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: MapsEngineParameterProvider */
public final class ac implements Runnable {
    private final ScheduledExecutorService f5551a;
    private volatile ScheduledFuture f5552b;
    private ProtoBuf f5553c;
    private MapsEngineParameterProvider f5554d;
    private final DataRequestDispatcher f5555e;
    private final ab f5556f;
    private final MapsEngineLayerInfo f5557g;
    private volatile boolean f5558h;
    private volatile String f5559i;
    private int f5560j;

    /* renamed from: com.google.android.m4b.maps.be.ac.a */
    public interface MapsEngineParameterProvider {
        void m8261a(ProtoBuf protoBuf);
    }

    ac(MapsEngineLayerInfo mapsEngineLayerInfo, ab abVar, ScheduledExecutorService scheduledExecutorService, DataRequestDispatcher dataRequestDispatcher) {
        this.f5560j = 0;
        this.f5551a = scheduledExecutorService;
        this.f5557g = mapsEngineLayerInfo;
        this.f5556f = abVar;
        this.f5555e = dataRequestDispatcher;
    }

    public final synchronized void m8299a(MapsEngineParameterProvider mapsEngineParameterProvider) {
        this.f5554d = mapsEngineParameterProvider;
    }

    public final void m8298a() {
        this.f5552b = this.f5551a.schedule(this, 0, TimeUnit.MILLISECONDS);
    }

    public final void m8301b() {
        if (this.f5552b != null) {
            this.f5552b.cancel(true);
        }
    }

    public final void run() {
        try {
            if (this.f5558h) {
                this.f5556f.m9185a(this.f5559i);
                this.f5558h = false;
            }
            this.f5559i = this.f5556f.m9186i();
            if (this.f5559i != null) {
                this.f5555e.m11445a(this.f5559i);
            }
            this.f5555e.m11451c(new LayerMetadataRequest(this.f5557g, this));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    final void m8302c() {
        this.f5558h = true;
        long pow = (long) (200.0d * Math.pow(2.0d, (double) this.f5560j));
        if (pow < Constants.MILLIS_IN_A_MINUTE) {
            this.f5552b = this.f5551a.schedule(this, pow, TimeUnit.MILLISECONDS);
            this.f5560j++;
        }
    }

    final void m8300a(ProtoBuf protoBuf, long j) {
        this.f5560j = 0;
        synchronized (this) {
            this.f5553c = protoBuf;
            if (this.f5554d != null) {
                this.f5554d.m8261a(protoBuf);
            }
        }
        if (j > 0) {
            this.f5552b = this.f5551a.schedule(this, j, TimeUnit.MILLISECONDS);
        }
    }

    final void m8303d() {
        this.f5560j = 0;
    }

    public final synchronized ProtoBuf m8304e() {
        return this.f5553c;
    }
}
