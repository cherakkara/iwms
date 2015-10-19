package p000a.p001a.p002a;

/* renamed from: a.a.a.m */
final class Subscription {
    final Object f63a;
    final SubscriberMethod f64b;
    final int f65c;
    volatile boolean f66d;

    Subscription(Object obj, SubscriberMethod subscriberMethod, int i) {
        this.f63a = obj;
        this.f64b = subscriberMethod;
        this.f65c = i;
        this.f66d = true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        if (this.f63a == subscription.f63a && this.f64b.equals(subscription.f64b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.f63a.hashCode() + this.f64b.f60d.hashCode();
    }
}
