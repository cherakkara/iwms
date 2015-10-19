package com.google.android.m4b.maps.p038a;

import android.os.Handler;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.m4b.maps.a.d */
public final class ExecutorDelivery implements ResponseDelivery {
    private final Executor f2888a;

    /* renamed from: com.google.android.m4b.maps.a.d.1 */
    class ExecutorDelivery implements Executor {
        private /* synthetic */ Handler f2884a;

        ExecutorDelivery(ExecutorDelivery executorDelivery, Handler handler) {
            this.f2884a = handler;
        }

        public final void execute(Runnable runnable) {
            this.f2884a.post(runnable);
        }
    }

    /* renamed from: com.google.android.m4b.maps.a.d.a */
    class ExecutorDelivery implements Runnable {
        private final Request f2885a;
        private final Response f2886b;
        private final Runnable f2887c;

        public ExecutorDelivery(ExecutorDelivery executorDelivery, Request request, Response response, Runnable runnable) {
            this.f2885a = request;
            this.f2886b = response;
            this.f2887c = runnable;
        }

        public final void run() {
            if (this.f2885a.m4711g()) {
                this.f2885a.m4706b("canceled-at-delivery");
                return;
            }
            if ((this.f2886b.f2930c == null ? 1 : null) != null) {
                this.f2885a.m4702a(this.f2886b.f2928a);
            } else {
                this.f2885a.m4705b(this.f2886b.f2930c);
            }
            if (this.f2886b.f2931d) {
                this.f2885a.m4703a("intermediate-response");
            } else {
                this.f2885a.m4706b("done");
            }
            if (this.f2887c != null) {
                this.f2887c.run();
            }
        }
    }

    public ExecutorDelivery(Handler handler) {
        this.f2888a = new ExecutorDelivery(this, handler);
    }

    public ExecutorDelivery(Executor executor) {
        this.f2888a = executor;
    }

    public final void m4687a(Request<?> request, Response<?> response) {
        m4688a(request, response, null);
    }

    public final void m4688a(Request<?> request, Response<?> response, Runnable runnable) {
        request.m4719p();
        request.m4703a("post-response");
        this.f2888a.execute(new ExecutorDelivery(this, request, response, runnable));
    }

    public final void m4689a(Request<?> request, VolleyError volleyError) {
        request.m4703a("post-error");
        this.f2888a.execute(new ExecutorDelivery(this, request, Response.m4727a(volleyError), null));
    }
}
