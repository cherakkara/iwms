package com.google.android.m4b.maps.p038a;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.google.android.m4b.maps.a.l */
public class RequestQueue {
    private AtomicInteger f2918a;
    private final Map<String, Queue<Request>> f2919b;
    private final Set<Request> f2920c;
    private final PriorityBlockingQueue<Request> f2921d;
    private final PriorityBlockingQueue<Request> f2922e;
    private final Cache f2923f;
    private final Network f2924g;
    private final ResponseDelivery f2925h;
    private NetworkDispatcher[] f2926i;
    private CacheDispatcher f2927j;

    public RequestQueue(Cache cache, Network network, int i, ResponseDelivery responseDelivery) {
        this.f2918a = new AtomicInteger();
        this.f2919b = new HashMap();
        this.f2920c = new HashSet();
        this.f2921d = new PriorityBlockingQueue();
        this.f2922e = new PriorityBlockingQueue();
        this.f2923f = cache;
        this.f2924g = network;
        this.f2926i = new NetworkDispatcher[4];
        this.f2925h = responseDelivery;
    }

    public final void m4722a() {
        m4723b();
        this.f2927j = new CacheDispatcher(this.f2921d, this.f2922e, this.f2923f, this.f2925h);
        this.f2927j.start();
        for (int i = 0; i < this.f2926i.length; i++) {
            NetworkDispatcher networkDispatcher = new NetworkDispatcher(this.f2922e, this.f2924g, this.f2923f, this.f2925h);
            this.f2926i[i] = networkDispatcher;
            networkDispatcher.start();
        }
    }

    public final void m4723b() {
        if (this.f2927j != null) {
            this.f2927j.m4683a();
        }
        for (int i = 0; i < this.f2926i.length; i++) {
            if (this.f2926i[i] != null) {
                this.f2926i[i].m4691a();
            }
        }
    }

    public Request m4721a(Request request) {
        request.m4700a(this);
        synchronized (this.f2920c) {
            this.f2920c.add(request);
        }
        request.m4698a(this.f2918a.incrementAndGet());
        request.m4703a("add-to-queue");
        if (request.m4715l()) {
            synchronized (this.f2919b) {
                String d = request.m4708d();
                if (this.f2919b.containsKey(d)) {
                    Queue queue = (Queue) this.f2919b.get(d);
                    if (queue == null) {
                        queue = new LinkedList();
                    }
                    queue.add(request);
                    this.f2919b.put(d, queue);
                    if (VolleyLog.f2942a) {
                        VolleyLog.m4735a("Request for cacheKey=%s is in flight, putting on hold.", d);
                    }
                } else {
                    this.f2919b.put(d, null);
                    this.f2921d.add(request);
                }
            }
        } else {
            this.f2922e.add(request);
        }
        return request;
    }

    final void m4724b(Request request) {
        synchronized (this.f2920c) {
            this.f2920c.remove(request);
        }
        if (request.m4715l()) {
            synchronized (this.f2919b) {
                Queue queue = (Queue) this.f2919b.remove(request.m4708d());
                if (queue != null) {
                    if (VolleyLog.f2942a) {
                        VolleyLog.m4735a("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queue.size()), r2);
                    }
                    this.f2921d.addAll(queue);
                }
            }
        }
    }
}
