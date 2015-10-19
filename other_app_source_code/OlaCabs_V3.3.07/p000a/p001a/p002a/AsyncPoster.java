package p000a.p001a.p002a;

/* renamed from: a.a.a.a */
class AsyncPoster implements Runnable {
    private final PendingPostQueue f0a;
    private final EventBus f1b;

    AsyncPoster(EventBus eventBus) {
        this.f1b = eventBus;
        this.f0a = new PendingPostQueue();
    }

    public void m0a(Subscription subscription, Object obj) {
        this.f0a.m24a(PendingPost.m20a(subscription, obj));
        this.f1b.m16b().execute(this);
    }

    public void run() {
        PendingPost a = this.f0a.m22a();
        if (a == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.f1b.m13a(a);
    }
}
