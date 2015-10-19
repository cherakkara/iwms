package com.android.volley;

import android.os.Handler;
import java.util.concurrent.Executor;

/* renamed from: com.android.volley.e */
public class ExecutorDelivery implements ResponseDelivery {
    private final Executor f491a;

    /* renamed from: com.android.volley.e.1 */
    class ExecutorDelivery implements Executor {
        final /* synthetic */ Handler f485a;
        final /* synthetic */ ExecutorDelivery f486b;

        ExecutorDelivery(ExecutorDelivery executorDelivery, Handler handler) {
            this.f486b = executorDelivery;
            this.f485a = handler;
        }

        public void execute(Runnable runnable) {
            this.f485a.post(runnable);
        }
    }

    /* renamed from: com.android.volley.e.a */
    private class ExecutorDelivery implements Runnable {
        final /* synthetic */ ExecutorDelivery f487a;
        private final Request f488b;
        private final Response f489c;
        private final Runnable f490d;

        public ExecutorDelivery(ExecutorDelivery executorDelivery, Request request, Response response, Runnable runnable) {
            this.f487a = executorDelivery;
            this.f488b = request;
            this.f489c = response;
            this.f490d = runnable;
        }

        public void run() {
            if (this.f488b.isCanceled()) {
                this.f488b.finish("canceled-at-delivery");
                return;
            }
            if (this.f489c.m588a()) {
                this.f488b.deliverResponse(this.f489c.f522a);
            } else {
                this.f488b.deliverError(this.f489c.f524c);
            }
            if (this.f489c.f525d) {
                this.f488b.addMarker("intermediate-response");
            } else {
                this.f488b.finish("done");
            }
            if (this.f490d != null) {
                this.f490d.run();
            }
        }
    }

    public ExecutorDelivery(Handler handler) {
        this.f491a = new ExecutorDelivery(this, handler);
    }

    public void m568a(Request<?> request, Response<?> response) {
        m569a(request, response, null);
    }

    public void m569a(Request<?> request, Response<?> response, Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        this.f491a.execute(new ExecutorDelivery(this, request, response, runnable));
    }

    public void m570a(Request<?> request, VolleyError volleyError) {
        request.addMarker("post-error");
        this.f491a.execute(new ExecutorDelivery(this, request, Response.m586a(volleyError), null));
    }
}
