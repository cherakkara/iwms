package p000a.p001a.p002a;

import java.lang.reflect.Method;

/* renamed from: a.a.a.k */
final class SubscriberMethod {
    final Method f57a;
    final ThreadMode f58b;
    final Class<?> f59c;
    String f60d;

    SubscriberMethod(Method method, ThreadMode threadMode, Class<?> cls) {
        this.f57a = method;
        this.f58b = threadMode;
        this.f59c = cls;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SubscriberMethod)) {
            return false;
        }
        m25a();
        SubscriberMethod subscriberMethod = (SubscriberMethod) obj;
        subscriberMethod.m25a();
        return this.f60d.equals(subscriberMethod.f60d);
    }

    private synchronized void m25a() {
        if (this.f60d == null) {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append(this.f57a.getDeclaringClass().getName());
            stringBuilder.append('#').append(this.f57a.getName());
            stringBuilder.append('(').append(this.f59c.getName());
            this.f60d = stringBuilder.toString();
        }
    }

    public int hashCode() {
        return this.f57a.hashCode();
    }
}
