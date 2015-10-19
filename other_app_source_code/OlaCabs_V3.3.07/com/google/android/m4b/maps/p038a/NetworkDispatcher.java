package com.google.android.m4b.maps.p038a;

import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import java.util.concurrent.BlockingQueue;

/* renamed from: com.google.android.m4b.maps.a.f */
public final class NetworkDispatcher extends Thread {
    private final BlockingQueue<Request> f2889a;
    private final Network f2890b;
    private final Cache f2891c;
    private final ResponseDelivery f2892d;
    private volatile boolean f2893e;

    public NetworkDispatcher(BlockingQueue<Request> blockingQueue, Network network, Cache cache, ResponseDelivery responseDelivery) {
        this.f2893e = false;
        this.f2889a = blockingQueue;
        this.f2890b = network;
        this.f2891c = cache;
        this.f2892d = responseDelivery;
    }

    public final void m4691a() {
        this.f2893e = true;
        interrupt();
    }

    public final void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                Request request = (Request) this.f2889a.take();
                try {
                    request.m4703a("network-queue-take");
                    if (request.m4711g()) {
                        request.m4706b("network-discard-cancelled");
                    } else {
                        if (VERSION.SDK_INT >= 14) {
                            TrafficStats.setThreadStatsTag(request.m4704b());
                        }
                        NetworkResponse a = this.f2890b.m4690a(request);
                        request.m4703a("network-http-complete");
                        if (a.f2896c && request.m4720q()) {
                            request.m4706b("not-modified");
                        } else {
                            Response a2 = request.m4697a(a);
                            request.m4703a("network-parse-complete");
                            if (request.m4715l() && a2.f2929b != null) {
                                this.f2891c.m4681a(request.m4708d(), a2.f2929b);
                                request.m4703a("network-cache-written");
                            }
                            request.m4719p();
                            this.f2892d.m4684a(request, a2);
                        }
                    }
                } catch (VolleyError e) {
                    this.f2892d.m4686a(request, Request.m4692a(e));
                } catch (Throwable e2) {
                    VolleyLog.m4736a(e2, "Unhandled exception %s", e2.toString());
                    this.f2892d.m4686a(request, new VolleyError(e2));
                }
            } catch (InterruptedException e3) {
                if (this.f2893e) {
                    return;
                }
            }
        }
    }
}
