package p000a.p001a.p002a;

/* renamed from: a.a.a.j */
public final class SubscriberExceptionEvent {
    public final EventBus f53a;
    public final Throwable f54b;
    public final Object f55c;
    public final Object f56d;

    public SubscriberExceptionEvent(EventBus eventBus, Throwable th, Object obj, Object obj2) {
        this.f53a = eventBus;
        this.f54b = th;
        this.f55c = obj;
        this.f56d = obj2;
    }
}
