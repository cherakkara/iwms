package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.android.volley.m */
public class RequestQueue {
    private AtomicInteger f512a;
    private final Map<String, Queue<Request<?>>> f513b;
    private final Set<Request<?>> f514c;
    private final PriorityBlockingQueue<Request<?>> f515d;
    private final PriorityBlockingQueue<Request<?>> f516e;
    private final Cache f517f;
    private final Network f518g;
    private final ResponseDelivery f519h;
    private NetworkDispatcher[] f520i;
    private CacheDispatcher f521j;

    /* renamed from: com.android.volley.m.a */
    public interface RequestQueue {
        boolean m575a(Request<?> request);
    }

    /* renamed from: com.android.volley.m.1 */
    class RequestQueue implements RequestQueue {
        final /* synthetic */ Object f510a;
        final /* synthetic */ RequestQueue f511b;

        RequestQueue(RequestQueue requestQueue, Object obj) {
            this.f511b = requestQueue;
            this.f510a = obj;
        }

        public boolean m576a(Request<?> request) {
            return request.getTag() == this.f510a;
        }
    }

    public RequestQueue(Cache cache, Network network, int i, ResponseDelivery responseDelivery) {
        this.f512a = new AtomicInteger();
        this.f513b = new HashMap();
        this.f514c = new HashSet();
        this.f515d = new PriorityBlockingQueue();
        this.f516e = new PriorityBlockingQueue();
        this.f517f = cache;
        this.f518g = network;
        this.f520i = new NetworkDispatcher[i];
        this.f519h = responseDelivery;
    }

    public RequestQueue(Cache cache, Network network, int i) {
        this(cache, network, i, new ExecutorDelivery(new Handler(Looper.getMainLooper())));
    }

    public RequestQueue(Cache cache, Network network) {
        this(cache, network, 4);
    }

    public void m578a() {
        m581b();
        this.f521j = new CacheDispatcher(this.f515d, this.f516e, this.f517f, this.f519h);
        this.f521j.start();
        for (int i = 0; i < this.f520i.length; i++) {
            NetworkDispatcher networkDispatcher = new NetworkDispatcher(this.f516e, this.f518g, this.f517f, this.f519h);
            this.f520i[i] = networkDispatcher;
            networkDispatcher.start();
        }
    }

    public void m581b() {
        if (this.f521j != null) {
            this.f521j.m563a();
        }
        for (int i = 0; i < this.f520i.length; i++) {
            if (this.f520i[i] != null) {
                this.f520i[i].m574a();
            }
        }
    }

    public int m583c() {
        return this.f512a.incrementAndGet();
    }

    public void m579a(RequestQueue requestQueue) {
        synchronized (this.f514c) {
            for (Request request : this.f514c) {
                if (requestQueue.m575a(request)) {
                    request.cancel();
                }
            }
        }
    }

    public void m580a(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        m579a(new RequestQueue(this, obj));
    }

    public <T> Request<T> m577a(Request<T> request) {
        request.setRequestQueue(this);
        synchronized (this.f514c) {
            this.f514c.add(request);
        }
        request.setSequence(m583c());
        request.addMarker("add-to-queue");
        if (request.shouldCache()) {
            synchronized (this.f513b) {
                String cacheKey = request.getCacheKey();
                if (this.f513b.containsKey(cacheKey)) {
                    Queue queue = (Queue) this.f513b.get(cacheKey);
                    if (queue == null) {
                        queue = new LinkedList();
                    }
                    queue.add(request);
                    this.f513b.put(cacheKey, queue);
                    if (VolleyLog.f533b) {
                        VolleyLog.m592a("Request for cacheKey=%s is in flight, putting on hold.", cacheKey);
                    }
                } else {
                    this.f513b.put(cacheKey, null);
                    this.f515d.add(request);
                }
            }
        } else {
            this.f516e.add(request);
        }
        return request;
    }

    void m582b(Request<?> request) {
        synchronized (this.f514c) {
            this.f514c.remove(request);
        }
        if (request.shouldCache()) {
            synchronized (this.f513b) {
                Queue queue = (Queue) this.f513b.remove(request.getCacheKey());
                if (queue != null) {
                    if (VolleyLog.f533b) {
                        VolleyLog.m592a("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queue.size()), r2);
                    }
                    this.f515d.addAll(queue);
                }
            }
        }
    }
}
