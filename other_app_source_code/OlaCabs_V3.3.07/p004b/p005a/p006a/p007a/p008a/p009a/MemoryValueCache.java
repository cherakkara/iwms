package p004b.p005a.p006a.p007a.p008a.p009a;

import android.content.Context;

/* renamed from: b.a.a.a.a.a.b */
public class MemoryValueCache<T> extends AbstractValueCache<T> {
    private T f114a;

    public MemoryValueCache() {
        this(null);
    }

    public MemoryValueCache(ValueCache<T> valueCache) {
        super(valueCache);
    }

    protected T m118a(Context context) {
        return this.f114a;
    }

    protected void m119a(Context context, T t) {
        this.f114a = t;
    }
}
