package p000a.p001a.p002a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* renamed from: a.a.a.f */
final class HandlerPoster extends Handler {
    private final PendingPostQueue f41a;
    private final int f42b;
    private final EventBus f43c;
    private boolean f44d;

    HandlerPoster(EventBus eventBus, Looper looper, int i) {
        super(looper);
        this.f43c = eventBus;
        this.f42b = i;
        this.f41a = new PendingPostQueue();
    }

    void m19a(Subscription subscription, Object obj) {
        PendingPost a = PendingPost.m20a(subscription, obj);
        synchronized (this) {
            this.f41a.m24a(a);
            if (!this.f44d) {
                this.f44d = true;
                if (!sendMessage(obtainMessage())) {
                    throw new EventBusException("Could not send handler message");
                }
            }
        }
    }

    public void handleMessage(Message message) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            do {
                PendingPost a = this.f41a.m22a();
                if (a == null) {
                    synchronized (this) {
                        a = this.f41a.m22a();
                        if (a == null) {
                            this.f44d = false;
                            this.f44d = false;
                            return;
                        }
                    }
                }
                this.f43c.m13a(a);
            } while (SystemClock.uptimeMillis() - uptimeMillis < ((long) this.f42b));
            if (sendMessage(obtainMessage())) {
                this.f44d = true;
                return;
            }
            throw new EventBusException("Could not send handler message");
        } catch (Throwable th) {
            this.f44d = false;
        }
    }
}
