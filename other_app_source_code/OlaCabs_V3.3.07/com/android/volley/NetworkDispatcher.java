package com.android.volley;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

/* renamed from: com.android.volley.g */
public class NetworkDispatcher extends Thread {
    private final BlockingQueue<Request<?>> f492a;
    private final Network f493b;
    private final Cache f494c;
    private final ResponseDelivery f495d;
    private volatile boolean f496e;

    public NetworkDispatcher(BlockingQueue<Request<?>> blockingQueue, Network network, Cache cache, ResponseDelivery responseDelivery) {
        this.f496e = false;
        this.f492a = blockingQueue;
        this.f493b = network;
        this.f494c = cache;
        this.f495d = responseDelivery;
    }

    public void m574a() {
        this.f496e = true;
        interrupt();
    }

    @TargetApi(14)
    private void m572a(Request<?> request) {
        if (VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                Request request = (Request) this.f492a.take();
                try {
                    request.addMarker("network-queue-take");
                    if (request.isCanceled()) {
                        request.finish("network-discard-cancelled");
                    } else {
                        m572a(request);
                        NetworkResponse a = this.f493b.m571a(request);
                        request.addMarker("network-http-complete");
                        if (a.f500d && request.hasHadResponseDelivered()) {
                            request.finish("not-modified");
                        } else {
                            Response parseNetworkResponse = request.parseNetworkResponse(a);
                            request.addMarker("network-parse-complete");
                            if (request.shouldCache() && parseNetworkResponse.f523b != null) {
                                this.f494c.m561a(request.getCacheKey(), parseNetworkResponse.f523b);
                                request.addMarker("network-cache-written");
                            }
                            request.markDelivered();
                            this.f495d.m565a(request, parseNetworkResponse);
                        }
                    }
                } catch (VolleyError e) {
                    e.m556a(SystemClock.elapsedRealtime() - elapsedRealtime);
                    m573a(request, e);
                } catch (Throwable e2) {
                    VolleyLog.m593a(e2, "Unhandled exception %s", e2.toString());
                    VolleyError volleyError = new VolleyError(e2);
                    volleyError.m556a(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.f495d.m567a(request, volleyError);
                }
            } catch (InterruptedException e3) {
                if (this.f496e) {
                    return;
                }
            }
        }
    }

    private void m573a(Request<?> request, VolleyError volleyError) {
        this.f495d.m567a((Request) request, request.parseNetworkError(volleyError));
    }
}
