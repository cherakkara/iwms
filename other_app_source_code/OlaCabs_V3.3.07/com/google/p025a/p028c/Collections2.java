package com.google.p025a.p028c;

import com.google.p025a.p026a.Function;
import com.google.p025a.p026a.Joiner;
import com.google.p025a.p026a.Preconditions;
import java.util.Collection;

/* renamed from: com.google.a.c.i */
public final class Collections2 {
    static final Joiner f1859a;

    /* renamed from: com.google.a.c.i.1 */
    static class Collections2 implements Function<Object, Object> {
        final /* synthetic */ Collection f1858a;

        Collections2(Collection collection) {
            this.f1858a = collection;
        }

        public Object m2929a(Object obj) {
            return obj == this.f1858a ? "(this Collection)" : obj;
        }
    }

    static boolean m2933a(Collection<?> collection, Object obj) {
        boolean z = false;
        Preconditions.m1817a((Object) collection);
        try {
            z = collection.contains(obj);
        } catch (ClassCastException e) {
        } catch (NullPointerException e2) {
        }
        return z;
    }

    static boolean m2934a(Collection<?> collection, Collection<?> collection2) {
        Preconditions.m1817a((Object) collection);
        for (Object contains : collection2) {
            if (!collection.contains(contains)) {
                return false;
            }
        }
        return true;
    }

    static String m2930a(Collection<?> collection) {
        StringBuilder append = Collections2.m2931a(collection.size()).append('[');
        f1859a.m1789a(append, ap.m2482a((Iterable) collection, new Collections2(collection)));
        return append.append(']').toString();
    }

    static StringBuilder m2931a(int i) {
        Preconditions.m1823a(i >= 0, (Object) "size must be non-negative");
        return new StringBuilder((int) Math.min(((long) i) * 8, 1073741824));
    }

    static <T> Collection<T> m2932a(Iterable<T> iterable) {
        return (Collection) iterable;
    }

    static {
        f1859a = Joiner.m1780a(", ").m1791b("null");
    }
}
