package com.google.android.m4b.maps.p038a;

import android.os.Process;
import com.google.android.m4b.maps.p038a.Cache.Cache;
import java.util.concurrent.BlockingQueue;

/* renamed from: com.google.android.m4b.maps.a.c */
public final class CacheDispatcher extends Thread {
    private static final boolean f2878a;
    private final BlockingQueue<Request> f2879b;
    private final BlockingQueue<Request> f2880c;
    private final Cache f2881d;
    private final ResponseDelivery f2882e;
    private volatile boolean f2883f;

    /* renamed from: com.google.android.m4b.maps.a.c.1 */
    class CacheDispatcher implements Runnable {
        private /* synthetic */ Request f2876a;
        private /* synthetic */ CacheDispatcher f2877b;

        CacheDispatcher(CacheDispatcher cacheDispatcher, Request request) {
            this.f2877b = cacheDispatcher;
            this.f2876a = request;
        }

        public final void run() {
            try {
                this.f2877b.f2880c.put(this.f2876a);
            } catch (InterruptedException e) {
            }
        }
    }

    static {
        f2878a = VolleyLog.f2942a;
    }

    public CacheDispatcher(BlockingQueue<Request> blockingQueue, BlockingQueue<Request> blockingQueue2, Cache cache, ResponseDelivery responseDelivery) {
        this.f2883f = false;
        this.f2879b = blockingQueue;
        this.f2880c = blockingQueue2;
        this.f2881d = cache;
        this.f2882e = responseDelivery;
    }

    public final void m4683a() {
        this.f2883f = true;
        interrupt();
    }

    public final void run() {
        if (f2878a) {
            VolleyLog.m4735a("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.f2881d.m4680a();
        while (true) {
            try {
                Request request = (Request) this.f2879b.take();
                request.m4703a("cache-queue-take");
                if (request.m4711g()) {
                    request.m4706b("cache-discard-canceled");
                } else {
                    Cache a = this.f2881d.m4679a(request.m4708d());
                    if (a == null) {
                        request.m4703a("cache-miss");
                        this.f2880c.put(request);
                    } else {
                        int i;
                        if (a.f2873d < System.currentTimeMillis()) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                            request.m4703a("cache-hit-expired");
                            request.m4699a(a);
                            this.f2880c.put(request);
                        } else {
                            request.m4703a("cache-hit");
                            Response a2 = request.m4697a(new NetworkResponse(a.f2870a, a.f2875f));
                            request.m4703a("cache-hit-parsed");
                            if (a.f2874e < System.currentTimeMillis()) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i == 0) {
                                this.f2882e.m4684a(request, a2);
                            } else {
                                request.m4703a("cache-hit-refresh-needed");
                                request.m4699a(a);
                                a2.f2931d = true;
                                this.f2882e.m4685a(request, a2, new CacheDispatcher(this, request));
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                if (this.f2883f) {
                    return;
                }
            }
        }
    }
}
