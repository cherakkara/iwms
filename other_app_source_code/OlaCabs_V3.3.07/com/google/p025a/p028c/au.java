package com.google.p025a.p028c;

import com.google.p025a.p026a.Joiner.Joiner;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.bk.Sets;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

/* compiled from: Maps */
/* renamed from: com.google.a.c.au */
public final class au {
    static final Joiner f1786a;

    /* renamed from: com.google.a.c.au.1 */
    static class Maps extends br<Entry<K, V>, K> {
        Maps(Iterator it) {
            super(it);
        }

        K m2800a(Entry<K, V> entry) {
            return entry.getKey();
        }
    }

    /* renamed from: com.google.a.c.au.2 */
    static class Maps extends br<Entry<K, V>, V> {
        Maps(Iterator it) {
            super(it);
        }

        V m2802a(Entry<K, V> entry) {
            return entry.getValue();
        }
    }

    /* renamed from: com.google.a.c.au.3 */
    static class Maps extends bs<V> {
        final /* synthetic */ bs f1785a;

        Maps(bs bsVar) {
            this.f1785a = bsVar;
        }

        public boolean hasNext() {
            return this.f1785a.hasNext();
        }

        public V next() {
            return ((Entry) this.f1785a.next()).getValue();
        }
    }

    /* renamed from: com.google.a.c.au.a */
    static abstract class Maps<K, V> extends Sets<Entry<K, V>> {
        abstract Map<K, V> m2803a();

        Maps() {
        }

        public int size() {
            return m2803a().size();
        }

        public void clear() {
            m2803a().clear();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object key = entry.getKey();
            Object obj2 = m2803a().get(key);
            if (!Objects.m1811a(obj2, entry.getValue())) {
                return false;
            }
            if (obj2 != null || m2803a().containsKey(key)) {
                return true;
            }
            return false;
        }

        public boolean isEmpty() {
            return m2803a().isEmpty();
        }

        public boolean remove(Object obj) {
            if (!contains(obj)) {
                return false;
            }
            return m2803a().keySet().remove(((Entry) obj).getKey());
        }

        public boolean removeAll(Collection<?> collection) {
            boolean removeAll;
            try {
                removeAll = super.removeAll((Collection) Preconditions.m1817a((Object) collection));
            } catch (UnsupportedOperationException e) {
                removeAll = true;
                for (Object remove : collection) {
                    removeAll |= remove(remove);
                }
            }
            return removeAll;
        }

        public boolean retainAll(Collection<?> collection) {
            try {
                return super.retainAll((Collection) Preconditions.m1817a((Object) collection));
            } catch (UnsupportedOperationException e) {
                Collection a = bk.m2871a(collection.size());
                for (Object next : collection) {
                    if (contains(next)) {
                        a.add(((Entry) next).getKey());
                    }
                }
                return m2803a().keySet().retainAll(a);
            }
        }
    }

    /* renamed from: com.google.a.c.au.b */
    static abstract class Maps<K, V> extends Sets<K> {
        abstract Map<K, V> m2804a();

        Maps() {
        }

        public Iterator<K> iterator() {
            return au.m2810a(m2804a().entrySet().iterator());
        }

        public int size() {
            return m2804a().size();
        }

        public boolean isEmpty() {
            return m2804a().isEmpty();
        }

        public boolean contains(Object obj) {
            return m2804a().containsKey(obj);
        }

        public boolean remove(Object obj) {
            if (!contains(obj)) {
                return false;
            }
            m2804a().remove(obj);
            return true;
        }

        public void clear() {
            m2804a().clear();
        }
    }

    public static <K, V> HashMap<K, V> m2807a() {
        return new HashMap();
    }

    public static <K, V> HashMap<K, V> m2808a(int i) {
        return new HashMap(au.m2812b(i));
    }

    static int m2812b(int i) {
        if (i < 3) {
            Preconditions.m1822a(i >= 0);
            return i + 1;
        } else if (i < 1073741824) {
            return (i / 3) + i;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public static <K, V> HashMap<K, V> m2809a(Map<? extends K, ? extends V> map) {
        return new HashMap(map);
    }

    public static <K, V> LinkedHashMap<K, V> m2815b() {
        return new LinkedHashMap();
    }

    public static <K, V> ConcurrentMap<K, V> m2818c() {
        return new as().m2539j();
    }

    public static <K, V> IdentityHashMap<K, V> m2819d() {
        return new IdentityHashMap();
    }

    public static <K, V> Entry<K, V> m2811a(@Nullable K k, @Nullable V v) {
        return new ImmutableEntry(k, v);
    }

    static {
        f1786a = Collections2.f1859a.m1792c("=");
    }

    static <V> V m2806a(Map<?, V> map, Object obj) {
        V v = null;
        Preconditions.m1817a((Object) map);
        try {
            v = map.get(obj);
        } catch (ClassCastException e) {
        } catch (NullPointerException e2) {
        }
        return v;
    }

    static boolean m2816b(Map<?, ?> map, Object obj) {
        boolean z = false;
        Preconditions.m1817a((Object) map);
        try {
            z = map.containsKey(obj);
        } catch (ClassCastException e) {
        } catch (NullPointerException e2) {
        }
        return z;
    }

    static <V> V m2817c(Map<?, V> map, Object obj) {
        V v = null;
        Preconditions.m1817a((Object) map);
        try {
            v = map.remove(obj);
        } catch (ClassCastException e) {
        } catch (NullPointerException e2) {
        }
        return v;
    }

    static boolean m2820d(Map<?, ?> map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return map.entrySet().equals(((Map) obj).entrySet());
    }

    static String m2813b(Map<?, ?> map) {
        StringBuilder append = Collections2.m2931a(map.size()).append('{');
        f1786a.m1798a(append, (Map) map);
        return append.append('}').toString();
    }

    static boolean m2821e(Map<?, ?> map, @Nullable Object obj) {
        return aq.m2504a(au.m2814b(map.entrySet().iterator()), obj);
    }

    static <K, V> Iterator<K> m2810a(Iterator<Entry<K, V>> it) {
        return new Maps(it);
    }

    static <K, V> Iterator<V> m2814b(Iterator<Entry<K, V>> it) {
        return new Maps(it);
    }

    static <K, V> bs<V> m2805a(bs<Entry<K, V>> bsVar) {
        return new Maps(bsVar);
    }
}
