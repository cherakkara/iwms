package p004b.p005a.p006a.p007a.p008a.p009a;

import android.content.Context;

/* renamed from: b.a.a.a.a.a.a */
public abstract class AbstractValueCache<T> implements ValueCache<T> {
    private final ValueCache<T> f113a;

    protected abstract T m115a(Context context);

    protected abstract void m117a(Context context, T t);

    public AbstractValueCache(ValueCache<T> valueCache) {
        this.f113a = valueCache;
    }

    public final synchronized T m116a(Context context, ValueLoader<T> valueLoader) throws Exception {
        T a;
        a = m115a(context);
        if (a == null) {
            a = this.f113a != null ? this.f113a.m113a(context, valueLoader) : valueLoader.load(context);
            m114b(context, a);
        }
        return a;
    }

    private void m114b(Context context, T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        m117a(context, (Object) t);
    }
}
