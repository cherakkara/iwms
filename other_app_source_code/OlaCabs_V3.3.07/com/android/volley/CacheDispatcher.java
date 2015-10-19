package com.android.volley;

import android.os.Process;
import com.android.volley.Cache.Cache;
import java.util.concurrent.BlockingQueue;

/* renamed from: com.android.volley.c */
public class CacheDispatcher extends Thread {
    private static final boolean f475a;
    private final BlockingQueue<Request<?>> f476b;
    private final BlockingQueue<Request<?>> f477c;
    private final Cache f478d;
    private final ResponseDelivery f479e;
    private volatile boolean f480f;

    /* renamed from: com.android.volley.c.1 */
    class CacheDispatcher implements Runnable {
        final /* synthetic */ Request f473a;
        final /* synthetic */ CacheDispatcher f474b;

        CacheDispatcher(CacheDispatcher cacheDispatcher, Request request) {
            this.f474b = cacheDispatcher;
            this.f473a = request;
        }

        public void run() {
            try {
                this.f474b.f477c.put(this.f473a);
            } catch (InterruptedException e) {
            }
        }
    }

    static {
        f475a = VolleyLog.f533b;
    }

    public CacheDispatcher(BlockingQueue<Request<?>> blockingQueue, BlockingQueue<Request<?>> blockingQueue2, Cache cache, ResponseDelivery responseDelivery) {
        this.f480f = false;
        this.f476b = blockingQueue;
        this.f477c = blockingQueue2;
        this.f478d = cache;
        this.f479e = responseDelivery;
    }

    public void m563a() {
        this.f480f = true;
        interrupt();
    }

    public void run() {
        if (f475a) {
            VolleyLog.m592a("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.f478d.m560a();
        while (true) {
            try {
                Request request = (Request) this.f476b.take();
                request.addMarker("cache-queue-take");
                if (request.isCanceled()) {
                    request.finish("cache-discard-canceled");
                } else {
                    Cache a = this.f478d.m559a(request.getCacheKey());
                    if (a == null) {
                        request.addMarker("cache-miss");
                        this.f477c.put(request);
                    } else if (a.m557a()) {
                        request.addMarker("cache-hit-expired");
                        request.setCacheEntry(a);
                        this.f477c.put(request);
                    } else {
                        request.addMarker("cache-hit");
                        Response parseNetworkResponse = request.parseNetworkResponse(new NetworkResponse(a.f467a, a.f472f));
                        request.addMarker("cache-hit-parsed");
                        if (a.m558b()) {
                            request.addMarker("cache-hit-refresh-needed");
                            request.setCacheEntry(a);
                            parseNetworkResponse.f525d = true;
                            this.f479e.m566a(request, parseNetworkResponse, new CacheDispatcher(this, request));
                        } else {
                            this.f479e.m565a(request, parseNetworkResponse);
                        }
                    }
                }
            } catch (InterruptedException e) {
                if (this.f480f) {
                    return;
                }
            }
        }
    }
}
