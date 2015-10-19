package com.google.gson.p064b;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

/* renamed from: com.google.gson.b.g */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean f8447f;
    private static final Comparator<Comparable> f8448g;
    Comparator<? super K> f8449a;
    LinkedTreeMap<K, V> f8450b;
    int f8451c;
    int f8452d;
    final LinkedTreeMap<K, V> f8453e;
    private LinkedTreeMap f8454h;
    private LinkedTreeMap f8455i;

    /* renamed from: com.google.gson.b.g.1 */
    static class LinkedTreeMap implements Comparator<Comparable> {
        LinkedTreeMap() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m12268a((Comparable) obj, (Comparable) obj2);
        }

        public int m12268a(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    /* renamed from: com.google.gson.b.g.c */
    private abstract class LinkedTreeMap<T> implements Iterator<T> {
        LinkedTreeMap<K, V> f8431b;
        LinkedTreeMap<K, V> f8432c;
        int f8433d;
        final /* synthetic */ LinkedTreeMap f8434e;

        private LinkedTreeMap(LinkedTreeMap linkedTreeMap) {
            this.f8434e = linkedTreeMap;
            this.f8431b = this.f8434e.f8453e.f8442d;
            this.f8432c = null;
            this.f8433d = this.f8434e.f8452d;
        }

        public final boolean hasNext() {
            return this.f8431b != this.f8434e.f8453e;
        }

        final LinkedTreeMap<K, V> m12269b() {
            LinkedTreeMap<K, V> linkedTreeMap = this.f8431b;
            if (linkedTreeMap == this.f8434e.f8453e) {
                throw new NoSuchElementException();
            } else if (this.f8434e.f8452d != this.f8433d) {
                throw new ConcurrentModificationException();
            } else {
                this.f8431b = linkedTreeMap.f8442d;
                this.f8432c = linkedTreeMap;
                return linkedTreeMap;
            }
        }

        public final void remove() {
            if (this.f8432c == null) {
                throw new IllegalStateException();
            }
            this.f8434e.m12281a(this.f8432c, true);
            this.f8432c = null;
            this.f8433d = this.f8434e.f8452d;
        }
    }

    /* renamed from: com.google.gson.b.g.a */
    class LinkedTreeMap extends AbstractSet<Entry<K, V>> {
        final /* synthetic */ LinkedTreeMap f8436a;

        /* renamed from: com.google.gson.b.g.a.1 */
        class LinkedTreeMap extends LinkedTreeMap<Entry<K, V>> {
            final /* synthetic */ LinkedTreeMap f8435a;

            LinkedTreeMap(LinkedTreeMap linkedTreeMap) {
                this.f8435a = linkedTreeMap;
                super(null);
            }

            public /* synthetic */ Object next() {
                return m12270a();
            }

            public Entry<K, V> m12270a() {
                return m12269b();
            }
        }

        LinkedTreeMap(LinkedTreeMap linkedTreeMap) {
            this.f8436a = linkedTreeMap;
        }

        public int size() {
            return this.f8436a.f8451c;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new LinkedTreeMap(this);
        }

        public boolean contains(Object obj) {
            return (obj instanceof Entry) && this.f8436a.m12280a((Entry) obj) != null;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            LinkedTreeMap a = this.f8436a.m12280a((Entry) obj);
            if (a == null) {
                return false;
            }
            this.f8436a.m12281a(a, true);
            return true;
        }

        public void clear() {
            this.f8436a.clear();
        }
    }

    /* renamed from: com.google.gson.b.g.b */
    final class LinkedTreeMap extends AbstractSet<K> {
        final /* synthetic */ LinkedTreeMap f8438a;

        /* renamed from: com.google.gson.b.g.b.1 */
        class LinkedTreeMap extends LinkedTreeMap<K> {
            final /* synthetic */ LinkedTreeMap f8437a;

            LinkedTreeMap(LinkedTreeMap linkedTreeMap) {
                this.f8437a = linkedTreeMap;
                super(null);
            }

            public K next() {
                return m12269b().f8444f;
            }
        }

        LinkedTreeMap(LinkedTreeMap linkedTreeMap) {
            this.f8438a = linkedTreeMap;
        }

        public int size() {
            return this.f8438a.f8451c;
        }

        public Iterator<K> iterator() {
            return new LinkedTreeMap(this);
        }

        public boolean contains(Object obj) {
            return this.f8438a.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return this.f8438a.m12282b(obj) != null;
        }

        public void clear() {
            this.f8438a.clear();
        }
    }

    /* renamed from: com.google.gson.b.g.d */
    static final class LinkedTreeMap<K, V> implements Entry<K, V> {
        LinkedTreeMap<K, V> f8439a;
        LinkedTreeMap<K, V> f8440b;
        LinkedTreeMap<K, V> f8441c;
        LinkedTreeMap<K, V> f8442d;
        LinkedTreeMap<K, V> f8443e;
        final K f8444f;
        V f8445g;
        int f8446h;

        LinkedTreeMap() {
            this.f8444f = null;
            this.f8443e = this;
            this.f8442d = this;
        }

        LinkedTreeMap(LinkedTreeMap<K, V> linkedTreeMap, K k, LinkedTreeMap<K, V> linkedTreeMap2, LinkedTreeMap<K, V> linkedTreeMap3) {
            this.f8439a = linkedTreeMap;
            this.f8444f = k;
            this.f8446h = 1;
            this.f8442d = linkedTreeMap2;
            this.f8443e = linkedTreeMap3;
            linkedTreeMap3.f8442d = this;
            linkedTreeMap2.f8443e = this;
        }

        public K getKey() {
            return this.f8444f;
        }

        public V getValue() {
            return this.f8445g;
        }

        public V setValue(V v) {
            V v2 = this.f8445g;
            this.f8445g = v;
            return v2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.f8444f == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!this.f8444f.equals(entry.getKey())) {
                return false;
            }
            if (this.f8445g == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!this.f8445g.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.f8444f == null ? 0 : this.f8444f.hashCode();
            if (this.f8445g != null) {
                i = this.f8445g.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return this.f8444f + "=" + this.f8445g;
        }

        public LinkedTreeMap<K, V> m12271a() {
            LinkedTreeMap<K, V> linkedTreeMap;
            for (LinkedTreeMap<K, V> linkedTreeMap2 = this.f8440b; linkedTreeMap2 != null; linkedTreeMap2 = linkedTreeMap2.f8440b) {
                linkedTreeMap = linkedTreeMap2;
            }
            return linkedTreeMap;
        }

        public LinkedTreeMap<K, V> m12272b() {
            LinkedTreeMap<K, V> linkedTreeMap;
            for (LinkedTreeMap<K, V> linkedTreeMap2 = this.f8441c; linkedTreeMap2 != null; linkedTreeMap2 = linkedTreeMap2.f8441c) {
                linkedTreeMap = linkedTreeMap2;
            }
            return linkedTreeMap;
        }
    }

    static {
        f8447f = !LinkedTreeMap.class.desiredAssertionStatus();
        f8448g = new LinkedTreeMap();
    }

    public LinkedTreeMap() {
        this(f8448g);
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.f8451c = 0;
        this.f8452d = 0;
        this.f8453e = new LinkedTreeMap();
        if (comparator == null) {
            comparator = f8448g;
        }
        this.f8449a = comparator;
    }

    public int size() {
        return this.f8451c;
    }

    public V get(Object obj) {
        LinkedTreeMap a = m12278a(obj);
        return a != null ? a.f8445g : null;
    }

    public boolean containsKey(Object obj) {
        return m12278a(obj) != null;
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        LinkedTreeMap a = m12279a((Object) k, true);
        V v2 = a.f8445g;
        a.f8445g = v;
        return v2;
    }

    public void clear() {
        this.f8450b = null;
        this.f8451c = 0;
        this.f8452d++;
        LinkedTreeMap linkedTreeMap = this.f8453e;
        linkedTreeMap.f8443e = linkedTreeMap;
        linkedTreeMap.f8442d = linkedTreeMap;
    }

    public V remove(Object obj) {
        LinkedTreeMap b = m12282b(obj);
        return b != null ? b.f8445g : null;
    }

    LinkedTreeMap<K, V> m12279a(K k, boolean z) {
        int i;
        Comparator comparator = this.f8449a;
        LinkedTreeMap<K, V> linkedTreeMap = this.f8450b;
        if (linkedTreeMap != null) {
            int compareTo;
            Comparable comparable = comparator == f8448g ? (Comparable) k : null;
            while (true) {
                compareTo = comparable != null ? comparable.compareTo(linkedTreeMap.f8444f) : comparator.compare(k, linkedTreeMap.f8444f);
                if (compareTo == 0) {
                    return linkedTreeMap;
                }
                LinkedTreeMap<K, V> linkedTreeMap2 = compareTo < 0 ? linkedTreeMap.f8440b : linkedTreeMap.f8441c;
                if (linkedTreeMap2 == null) {
                    break;
                }
                linkedTreeMap = linkedTreeMap2;
            }
            int i2 = compareTo;
            LinkedTreeMap linkedTreeMap3 = linkedTreeMap;
            i = i2;
        } else {
            LinkedTreeMap<K, V> linkedTreeMap4 = linkedTreeMap;
            i = 0;
        }
        if (!z) {
            return null;
        }
        LinkedTreeMap<K, V> linkedTreeMap5;
        LinkedTreeMap linkedTreeMap6 = this.f8453e;
        if (linkedTreeMap3 != null) {
            linkedTreeMap5 = new LinkedTreeMap(linkedTreeMap3, k, linkedTreeMap6, linkedTreeMap6.f8443e);
            if (i < 0) {
                linkedTreeMap3.f8440b = linkedTreeMap5;
            } else {
                linkedTreeMap3.f8441c = linkedTreeMap5;
            }
            m12277b(linkedTreeMap3, true);
        } else if (comparator != f8448g || (k instanceof Comparable)) {
            linkedTreeMap5 = new LinkedTreeMap(linkedTreeMap3, k, linkedTreeMap6, linkedTreeMap6.f8443e);
            this.f8450b = linkedTreeMap5;
        } else {
            throw new ClassCastException(k.getClass().getName() + " is not Comparable");
        }
        this.f8451c++;
        this.f8452d++;
        return linkedTreeMap5;
    }

    LinkedTreeMap<K, V> m12278a(Object obj) {
        LinkedTreeMap<K, V> linkedTreeMap = null;
        if (obj != null) {
            try {
                linkedTreeMap = m12279a(obj, false);
            } catch (ClassCastException e) {
            }
        }
        return linkedTreeMap;
    }

    LinkedTreeMap<K, V> m12280a(Entry<?, ?> entry) {
        LinkedTreeMap<K, V> a = m12278a(entry.getKey());
        Object obj = (a == null || !m12275a(a.f8445g, entry.getValue())) ? null : 1;
        return obj != null ? a : null;
    }

    private boolean m12275a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    void m12281a(LinkedTreeMap<K, V> linkedTreeMap, boolean z) {
        int i = 0;
        if (z) {
            linkedTreeMap.f8443e.f8442d = linkedTreeMap.f8442d;
            linkedTreeMap.f8442d.f8443e = linkedTreeMap.f8443e;
        }
        LinkedTreeMap linkedTreeMap2 = linkedTreeMap.f8440b;
        LinkedTreeMap linkedTreeMap3 = linkedTreeMap.f8441c;
        LinkedTreeMap linkedTreeMap4 = linkedTreeMap.f8439a;
        if (linkedTreeMap2 == null || linkedTreeMap3 == null) {
            if (linkedTreeMap2 != null) {
                m12274a((LinkedTreeMap) linkedTreeMap, linkedTreeMap2);
                linkedTreeMap.f8440b = null;
            } else if (linkedTreeMap3 != null) {
                m12274a((LinkedTreeMap) linkedTreeMap, linkedTreeMap3);
                linkedTreeMap.f8441c = null;
            } else {
                m12274a((LinkedTreeMap) linkedTreeMap, null);
            }
            m12277b(linkedTreeMap4, false);
            this.f8451c--;
            this.f8452d++;
            return;
        }
        int i2;
        linkedTreeMap2 = linkedTreeMap2.f8446h > linkedTreeMap3.f8446h ? linkedTreeMap2.m12272b() : linkedTreeMap3.m12271a();
        m12281a(linkedTreeMap2, false);
        linkedTreeMap4 = linkedTreeMap.f8440b;
        if (linkedTreeMap4 != null) {
            i2 = linkedTreeMap4.f8446h;
            linkedTreeMap2.f8440b = linkedTreeMap4;
            linkedTreeMap4.f8439a = linkedTreeMap2;
            linkedTreeMap.f8440b = null;
        } else {
            i2 = 0;
        }
        linkedTreeMap4 = linkedTreeMap.f8441c;
        if (linkedTreeMap4 != null) {
            i = linkedTreeMap4.f8446h;
            linkedTreeMap2.f8441c = linkedTreeMap4;
            linkedTreeMap4.f8439a = linkedTreeMap2;
            linkedTreeMap.f8441c = null;
        }
        linkedTreeMap2.f8446h = Math.max(i2, i) + 1;
        m12274a((LinkedTreeMap) linkedTreeMap, linkedTreeMap2);
    }

    LinkedTreeMap<K, V> m12282b(Object obj) {
        LinkedTreeMap a = m12278a(obj);
        if (a != null) {
            m12281a(a, true);
        }
        return a;
    }

    private void m12274a(LinkedTreeMap<K, V> linkedTreeMap, LinkedTreeMap<K, V> linkedTreeMap2) {
        LinkedTreeMap linkedTreeMap3 = linkedTreeMap.f8439a;
        linkedTreeMap.f8439a = null;
        if (linkedTreeMap2 != null) {
            linkedTreeMap2.f8439a = linkedTreeMap3;
        }
        if (linkedTreeMap3 == null) {
            this.f8450b = linkedTreeMap2;
        } else if (linkedTreeMap3.f8440b == linkedTreeMap) {
            linkedTreeMap3.f8440b = linkedTreeMap2;
        } else if (f8447f || linkedTreeMap3.f8441c == linkedTreeMap) {
            linkedTreeMap3.f8441c = linkedTreeMap2;
        } else {
            throw new AssertionError();
        }
    }

    private void m12277b(LinkedTreeMap<K, V> linkedTreeMap, boolean z) {
        LinkedTreeMap linkedTreeMap2;
        while (linkedTreeMap2 != null) {
            int i;
            LinkedTreeMap linkedTreeMap3 = linkedTreeMap2.f8440b;
            LinkedTreeMap linkedTreeMap4 = linkedTreeMap2.f8441c;
            int i2 = linkedTreeMap3 != null ? linkedTreeMap3.f8446h : 0;
            if (linkedTreeMap4 != null) {
                i = linkedTreeMap4.f8446h;
            } else {
                i = 0;
            }
            int i3 = i2 - i;
            LinkedTreeMap linkedTreeMap5;
            if (i3 == -2) {
                linkedTreeMap3 = linkedTreeMap4.f8440b;
                linkedTreeMap5 = linkedTreeMap4.f8441c;
                if (linkedTreeMap5 != null) {
                    i2 = linkedTreeMap5.f8446h;
                } else {
                    i2 = 0;
                }
                if (linkedTreeMap3 != null) {
                    i = linkedTreeMap3.f8446h;
                } else {
                    i = 0;
                }
                i -= i2;
                if (i == -1 || (i == 0 && !z)) {
                    m12273a(linkedTreeMap2);
                } else if (f8447f || i == 1) {
                    m12276b(linkedTreeMap4);
                    m12273a(linkedTreeMap2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                linkedTreeMap4 = linkedTreeMap3.f8440b;
                linkedTreeMap5 = linkedTreeMap3.f8441c;
                i2 = linkedTreeMap5 != null ? linkedTreeMap5.f8446h : 0;
                if (linkedTreeMap4 != null) {
                    i = linkedTreeMap4.f8446h;
                } else {
                    i = 0;
                }
                i -= i2;
                if (i == 1 || (i == 0 && !z)) {
                    m12276b(linkedTreeMap2);
                } else if (f8447f || i == -1) {
                    m12273a(linkedTreeMap3);
                    m12276b(linkedTreeMap2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                linkedTreeMap2.f8446h = i2 + 1;
                if (z) {
                    return;
                }
            } else if (f8447f || i3 == -1 || i3 == 1) {
                linkedTreeMap2.f8446h = Math.max(i2, i) + 1;
                if (!z) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            linkedTreeMap2 = linkedTreeMap2.f8439a;
        }
    }

    private void m12273a(LinkedTreeMap<K, V> linkedTreeMap) {
        int i;
        int i2 = 0;
        LinkedTreeMap linkedTreeMap2 = linkedTreeMap.f8440b;
        LinkedTreeMap linkedTreeMap3 = linkedTreeMap.f8441c;
        LinkedTreeMap linkedTreeMap4 = linkedTreeMap3.f8440b;
        LinkedTreeMap linkedTreeMap5 = linkedTreeMap3.f8441c;
        linkedTreeMap.f8441c = linkedTreeMap4;
        if (linkedTreeMap4 != null) {
            linkedTreeMap4.f8439a = linkedTreeMap;
        }
        m12274a((LinkedTreeMap) linkedTreeMap, linkedTreeMap3);
        linkedTreeMap3.f8440b = linkedTreeMap;
        linkedTreeMap.f8439a = linkedTreeMap3;
        if (linkedTreeMap2 != null) {
            i = linkedTreeMap2.f8446h;
        } else {
            i = 0;
        }
        linkedTreeMap.f8446h = Math.max(i, linkedTreeMap4 != null ? linkedTreeMap4.f8446h : 0) + 1;
        int i3 = linkedTreeMap.f8446h;
        if (linkedTreeMap5 != null) {
            i2 = linkedTreeMap5.f8446h;
        }
        linkedTreeMap3.f8446h = Math.max(i3, i2) + 1;
    }

    private void m12276b(LinkedTreeMap<K, V> linkedTreeMap) {
        int i;
        int i2 = 0;
        LinkedTreeMap linkedTreeMap2 = linkedTreeMap.f8440b;
        LinkedTreeMap linkedTreeMap3 = linkedTreeMap.f8441c;
        LinkedTreeMap linkedTreeMap4 = linkedTreeMap2.f8440b;
        LinkedTreeMap linkedTreeMap5 = linkedTreeMap2.f8441c;
        linkedTreeMap.f8440b = linkedTreeMap5;
        if (linkedTreeMap5 != null) {
            linkedTreeMap5.f8439a = linkedTreeMap;
        }
        m12274a((LinkedTreeMap) linkedTreeMap, linkedTreeMap2);
        linkedTreeMap2.f8441c = linkedTreeMap;
        linkedTreeMap.f8439a = linkedTreeMap2;
        if (linkedTreeMap3 != null) {
            i = linkedTreeMap3.f8446h;
        } else {
            i = 0;
        }
        linkedTreeMap.f8446h = Math.max(i, linkedTreeMap5 != null ? linkedTreeMap5.f8446h : 0) + 1;
        int i3 = linkedTreeMap.f8446h;
        if (linkedTreeMap4 != null) {
            i2 = linkedTreeMap4.f8446h;
        }
        linkedTreeMap2.f8446h = Math.max(i3, i2) + 1;
    }

    public Set<Entry<K, V>> entrySet() {
        Set set = this.f8454h;
        if (set != null) {
            return set;
        }
        Set<Entry<K, V>> linkedTreeMap = new LinkedTreeMap(this);
        this.f8454h = linkedTreeMap;
        return linkedTreeMap;
    }

    public Set<K> keySet() {
        Set set = this.f8455i;
        if (set != null) {
            return set;
        }
        Set<K> linkedTreeMap = new LinkedTreeMap(this);
        this.f8455i = linkedTreeMap;
        return linkedTreeMap;
    }
}
