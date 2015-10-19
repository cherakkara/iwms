package com.google.android.m4b.maps.be;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.google.android.m4b.maps.p038a.Cache;
import com.google.android.m4b.maps.p038a.ExecutorDelivery;
import com.google.android.m4b.maps.p038a.Network;
import com.google.android.m4b.maps.p038a.Request;
import com.google.android.m4b.maps.p038a.RequestQueue;
import com.google.android.m4b.maps.p038a.Response;
import com.google.android.m4b.maps.p038a.ResponseDelivery;
import com.google.android.m4b.maps.p038a.VolleyError;
import com.google.p025a.p034i.p035a.MoreExecutors;

/* compiled from: CachingRequestQueue */
public final class bo extends RequestQueue {
    private final LruCache<String, Response<?>> f5845a;
    private final ExecutorDelivery f5846b;

    /* renamed from: com.google.android.m4b.maps.be.bo.1 */
    static class CachingRequestQueue extends LruCache<String, Response<?>> {
        CachingRequestQueue(int i) {
            super(i);
        }

        protected final /* synthetic */ int sizeOf(Object obj, Object obj2) {
            Response response = (Response) obj2;
            if (response.f2928a instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) response.f2928a;
                return bitmap.getHeight() * bitmap.getRowBytes();
            } else if (response.f2928a instanceof String) {
                return ((String) response.f2928a).getBytes().length;
            } else {
                throw new IllegalStateException("LruCache does not have a sizeOf implementation for: " + response.f2928a);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bo.a */
    static class CachingRequestQueue implements ResponseDelivery {
        private final LruCache<String, Response<?>> f5843a;
        private final ResponseDelivery f5844b;

        public CachingRequestQueue(LruCache<String, Response<?>> lruCache, ResponseDelivery responseDelivery) {
            this.f5843a = lruCache;
            this.f5844b = responseDelivery;
        }

        public final void m8924a(Request<?> request, Response<?> response) {
            this.f5843a.put(request.m4707c(), response);
            this.f5844b.m4684a((Request) request, (Response) response);
        }

        public final void m8925a(Request<?> request, Response<?> response, Runnable runnable) {
            this.f5843a.put(request.m4707c(), response);
            this.f5844b.m4685a(request, response, runnable);
        }

        public final void m8926a(Request<?> request, VolleyError volleyError) {
            this.f5844b.m4686a((Request) request, volleyError);
        }
    }

    bo(Cache cache, Network network, LruCache<String, Response<?>> lruCache, ResponseDelivery responseDelivery) {
        super(cache, network, 4, new CachingRequestQueue(lruCache, responseDelivery));
        this.f5845a = lruCache;
        this.f5846b = new ExecutorDelivery(MoreExecutors.m3141a());
    }

    public final Request m8927a(Request request) {
        Response response = (Response) this.f5845a.get(request.m4707c());
        if (response == null) {
            return super.m4721a(request);
        }
        this.f5846b.m4687a(request, response);
        return request;
    }
}
