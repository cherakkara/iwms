package com.google.p025a.p028c;

import com.google.a.c.c$f.a;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.au.Maps;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.c */
abstract class AbstractMapBasedMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private transient Map<K, Collection<V>> f1617a;
    private transient int f1618b;

    /* renamed from: com.google.a.c.c.a */
    private class AbstractMapBasedMultimap extends AbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> f1831a;
        transient Set<Entry<K, Collection<V>>> f1832b;
        final /* synthetic */ AbstractMapBasedMultimap f1833c;

        /* renamed from: com.google.a.c.c.a.a */
        class AbstractMapBasedMultimap extends Maps<K, Collection<V>> {
            final /* synthetic */ AbstractMapBasedMultimap f1827a;

            AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap) {
                this.f1827a = abstractMapBasedMultimap;
            }

            Map<K, Collection<V>> m2907a() {
                return this.f1827a;
            }

            public Iterator<Entry<K, Collection<V>>> iterator() {
                return new AbstractMapBasedMultimap(this.f1827a);
            }

            public boolean contains(Object obj) {
                return Collections2.m2933a(this.f1827a.f1831a.entrySet(), obj);
            }

            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                this.f1827a.f1833c.m2398c(((Entry) obj).getKey());
                return true;
            }
        }

        /* renamed from: com.google.a.c.c.a.b */
        class AbstractMapBasedMultimap implements Iterator<Entry<K, Collection<V>>> {
            final Iterator<Entry<K, Collection<V>>> f1828a;
            Collection<V> f1829b;
            final /* synthetic */ AbstractMapBasedMultimap f1830c;

            AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap) {
                this.f1830c = abstractMapBasedMultimap;
                this.f1828a = this.f1830c.f1831a.entrySet().iterator();
            }

            public /* synthetic */ Object next() {
                return m2908a();
            }

            public boolean hasNext() {
                return this.f1828a.hasNext();
            }

            public Entry<K, Collection<V>> m2908a() {
                Entry entry = (Entry) this.f1828a.next();
                this.f1829b = (Collection) entry.getValue();
                return this.f1830c.m2910a(entry);
            }

            public void remove() {
                this.f1828a.remove();
                AbstractMapBasedMultimap.m2396b(this.f1830c.f1833c, this.f1829b.size());
                this.f1829b.clear();
            }
        }

        public /* synthetic */ Object get(Object obj) {
            return m2909a(obj);
        }

        public /* synthetic */ Object remove(Object obj) {
            return m2911b(obj);
        }

        AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, Map<K, Collection<V>> map) {
            this.f1833c = abstractMapBasedMultimap;
            this.f1831a = map;
        }

        public Set<Entry<K, Collection<V>>> entrySet() {
            Set<Entry<K, Collection<V>>> set = this.f1832b;
            if (set != null) {
                return set;
            }
            set = new AbstractMapBasedMultimap(this);
            this.f1832b = set;
            return set;
        }

        public boolean containsKey(Object obj) {
            return au.m2816b(this.f1831a, obj);
        }

        public Collection<V> m2909a(Object obj) {
            Collection collection = (Collection) au.m2806a(this.f1831a, obj);
            if (collection == null) {
                return null;
            }
            return this.f1833c.m2401a(obj, collection);
        }

        public Set<K> keySet() {
            return this.f1833c.m2386g();
        }

        public int size() {
            return this.f1831a.size();
        }

        public Collection<V> m2911b(Object obj) {
            Collection collection = (Collection) this.f1831a.remove(obj);
            if (collection == null) {
                return null;
            }
            Collection<V> a = this.f1833c.m2399a();
            a.addAll(collection);
            AbstractMapBasedMultimap.m2396b(this.f1833c, collection.size());
            collection.clear();
            return a;
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || this.f1831a.equals(obj);
        }

        public int hashCode() {
            return this.f1831a.hashCode();
        }

        public String toString() {
            return this.f1831a.toString();
        }

        public void clear() {
            if (this.f1831a == this.f1833c.f1617a) {
                this.f1833c.m2405c();
            } else {
                aq.m2509d(new AbstractMapBasedMultimap(this));
            }
        }

        Entry<K, Collection<V>> m2910a(Entry<K, Collection<V>> entry) {
            Object key = entry.getKey();
            return au.m2811a(key, this.f1833c.m2401a(key, (Collection) entry.getValue()));
        }
    }

    /* renamed from: com.google.a.c.c.b */
    private class AbstractMapBasedMultimap extends Maps<K, Collection<V>> {
        final Map<K, Collection<V>> f1837a;
        final /* synthetic */ AbstractMapBasedMultimap f1838b;

        /* renamed from: com.google.a.c.c.b.1 */
        class AbstractMapBasedMultimap implements Iterator<K> {
            Entry<K, Collection<V>> f1834a;
            final /* synthetic */ Iterator f1835b;
            final /* synthetic */ AbstractMapBasedMultimap f1836c;

            AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, Iterator it) {
                this.f1836c = abstractMapBasedMultimap;
                this.f1835b = it;
            }

            public boolean hasNext() {
                return this.f1835b.hasNext();
            }

            public K next() {
                this.f1834a = (Entry) this.f1835b.next();
                return this.f1834a.getKey();
            }

            public void remove() {
                aq.m2502a(this.f1834a != null);
                Collection collection = (Collection) this.f1834a.getValue();
                this.f1835b.remove();
                AbstractMapBasedMultimap.m2396b(this.f1836c.f1838b, collection.size());
                collection.clear();
            }
        }

        AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, Map<K, Collection<V>> map) {
            this.f1838b = abstractMapBasedMultimap;
            this.f1837a = map;
        }

        Map<K, Collection<V>> m2912a() {
            return this.f1837a;
        }

        public Iterator<K> iterator() {
            return new AbstractMapBasedMultimap(this, this.f1837a.entrySet().iterator());
        }

        public boolean remove(Object obj) {
            int i;
            Collection collection = (Collection) this.f1837a.remove(obj);
            if (collection != null) {
                int size = collection.size();
                collection.clear();
                AbstractMapBasedMultimap.m2396b(this.f1838b, size);
                i = size;
            } else {
                i = 0;
            }
            return i > 0;
        }

        public void clear() {
            aq.m2509d(iterator());
        }

        public boolean containsAll(Collection<?> collection) {
            return this.f1837a.keySet().containsAll(collection);
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || this.f1837a.keySet().equals(obj);
        }

        public int hashCode() {
            return this.f1837a.keySet().hashCode();
        }
    }

    /* renamed from: com.google.a.c.c.f */
    private class AbstractMapBasedMultimap extends AbstractCollection<V> {
        final K f1839b;
        Collection<V> f1840c;
        final AbstractMapBasedMultimap f1841d;
        final Collection<V> f1842e;
        final /* synthetic */ AbstractMapBasedMultimap f1843f;

        /* renamed from: com.google.a.c.c.f.a */
        class AbstractMapBasedMultimap implements Iterator<V> {
            final Iterator<V> f1849a;
            final Collection<V> f1850b;
            final /* synthetic */ AbstractMapBasedMultimap f1851c;

            AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap) {
                this.f1851c = abstractMapBasedMultimap;
                this.f1850b = this.f1851c.f1840c;
                this.f1849a = abstractMapBasedMultimap.f1843f.m2391a(abstractMapBasedMultimap.f1840c);
            }

            AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, Iterator<V> it) {
                this.f1851c = abstractMapBasedMultimap;
                this.f1850b = this.f1851c.f1840c;
                this.f1849a = it;
            }

            void m2924a() {
                this.f1851c.m2913a();
                if (this.f1851c.f1840c != this.f1850b) {
                    throw new ConcurrentModificationException();
                }
            }

            public boolean hasNext() {
                m2924a();
                return this.f1849a.hasNext();
            }

            public V next() {
                m2924a();
                return this.f1849a.next();
            }

            public void remove() {
                this.f1849a.remove();
                this.f1851c.f1843f.f1618b = this.f1851c.f1843f.f1618b - 1;
                this.f1851c.m2914b();
            }

            Iterator<V> m2925b() {
                m2924a();
                return this.f1849a;
            }
        }

        AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, @Nullable K k, Collection<V> collection, @Nullable AbstractMapBasedMultimap abstractMapBasedMultimap2) {
            this.f1843f = abstractMapBasedMultimap;
            this.f1839b = k;
            this.f1840c = collection;
            this.f1841d = abstractMapBasedMultimap2;
            this.f1842e = abstractMapBasedMultimap2 == null ? null : abstractMapBasedMultimap2.m2917e();
        }

        void m2913a() {
            if (this.f1841d != null) {
                this.f1841d.m2913a();
                if (this.f1841d.m2917e() != this.f1842e) {
                    throw new ConcurrentModificationException();
                }
            } else if (this.f1840c.isEmpty()) {
                Collection collection = (Collection) this.f1843f.f1617a.get(this.f1839b);
                if (collection != null) {
                    this.f1840c = collection;
                }
            }
        }

        void m2914b() {
            if (this.f1841d != null) {
                this.f1841d.m2914b();
            } else if (this.f1840c.isEmpty()) {
                this.f1843f.f1617a.remove(this.f1839b);
            }
        }

        K m2915c() {
            return this.f1839b;
        }

        void m2916d() {
            if (this.f1841d != null) {
                this.f1841d.m2916d();
            } else {
                this.f1843f.f1617a.put(this.f1839b, this.f1840c);
            }
        }

        public int size() {
            m2913a();
            return this.f1840c.size();
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            m2913a();
            return this.f1840c.equals(obj);
        }

        public int hashCode() {
            m2913a();
            return this.f1840c.hashCode();
        }

        public String toString() {
            m2913a();
            return this.f1840c.toString();
        }

        Collection<V> m2917e() {
            return this.f1840c;
        }

        public Iterator<V> iterator() {
            m2913a();
            return new AbstractMapBasedMultimap(this);
        }

        public boolean add(V v) {
            m2913a();
            boolean isEmpty = this.f1840c.isEmpty();
            boolean add = this.f1840c.add(v);
            if (add) {
                this.f1843f.f1618b = this.f1843f.f1618b + 1;
                if (isEmpty) {
                    m2916d();
                }
            }
            return add;
        }

        AbstractMapBasedMultimap m2918f() {
            return this.f1841d;
        }

        public boolean addAll(Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = this.f1840c.addAll(collection);
            if (!addAll) {
                return addAll;
            }
            AbstractMapBasedMultimap.m2388a(this.f1843f, this.f1840c.size() - size);
            if (size != 0) {
                return addAll;
            }
            m2916d();
            return addAll;
        }

        public boolean contains(Object obj) {
            m2913a();
            return this.f1840c.contains(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            m2913a();
            return this.f1840c.containsAll(collection);
        }

        public void clear() {
            int size = size();
            if (size != 0) {
                this.f1840c.clear();
                AbstractMapBasedMultimap.m2396b(this.f1843f, size);
                m2914b();
            }
        }

        public boolean remove(Object obj) {
            m2913a();
            boolean remove = this.f1840c.remove(obj);
            if (remove) {
                this.f1851c.f1843f.f1618b = this.f1843f.f1618b - 1;
                m2914b();
            }
            return remove;
        }

        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAll = this.f1840c.removeAll(collection);
            if (!removeAll) {
                return removeAll;
            }
            AbstractMapBasedMultimap.m2388a(this.f1843f, this.f1840c.size() - size);
            m2914b();
            return removeAll;
        }

        public boolean retainAll(Collection<?> collection) {
            Preconditions.m1817a((Object) collection);
            int size = size();
            boolean retainAll = this.f1840c.retainAll(collection);
            if (retainAll) {
                AbstractMapBasedMultimap.m2388a(this.f1843f, this.f1840c.size() - size);
                m2914b();
            }
            return retainAll;
        }
    }

    /* renamed from: com.google.a.c.c.g */
    private class AbstractMapBasedMultimap extends AbstractMapBasedMultimap implements List<V> {
        final /* synthetic */ AbstractMapBasedMultimap f1844g;

        /* renamed from: com.google.a.c.c.g.a */
        private class AbstractMapBasedMultimap extends a implements ListIterator<V> {
            final /* synthetic */ AbstractMapBasedMultimap f1852d;

            AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap) {
                this.f1852d = abstractMapBasedMultimap;
                super(abstractMapBasedMultimap);
            }

            public AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, int i) {
                this.f1852d = abstractMapBasedMultimap;
                super(abstractMapBasedMultimap, abstractMapBasedMultimap.m2919g().listIterator(i));
            }

            private ListIterator<V> m2926c() {
                return (ListIterator) b();
            }

            public boolean hasPrevious() {
                return m2926c().hasPrevious();
            }

            public V previous() {
                return m2926c().previous();
            }

            public int nextIndex() {
                return m2926c().nextIndex();
            }

            public int previousIndex() {
                return m2926c().previousIndex();
            }

            public void set(V v) {
                m2926c().set(v);
            }

            public void add(V v) {
                boolean isEmpty = this.f1852d.isEmpty();
                m2926c().add(v);
                this.f1843f.f1618b = this.f1852d.f1844g.f1618b + 1;
                if (isEmpty) {
                    this.f1852d.m2916d();
                }
            }
        }

        AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, @Nullable K k, List<V> list, @Nullable AbstractMapBasedMultimap abstractMapBasedMultimap2) {
            this.f1844g = abstractMapBasedMultimap;
            super(abstractMapBasedMultimap, k, list, abstractMapBasedMultimap2);
        }

        List<V> m2919g() {
            return (List) m2917e();
        }

        public boolean addAll(int i, Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = m2919g().addAll(i, collection);
            if (!addAll) {
                return addAll;
            }
            AbstractMapBasedMultimap.m2388a(this.f1844g, m2917e().size() - size);
            if (size != 0) {
                return addAll;
            }
            m2916d();
            return addAll;
        }

        public V get(int i) {
            m2913a();
            return m2919g().get(i);
        }

        public V set(int i, V v) {
            m2913a();
            return m2919g().set(i, v);
        }

        public void add(int i, V v) {
            m2913a();
            boolean isEmpty = m2917e().isEmpty();
            m2919g().add(i, v);
            this.f1843f.f1618b = this.f1844g.f1618b + 1;
            if (isEmpty) {
                m2916d();
            }
        }

        public V remove(int i) {
            m2913a();
            V remove = m2919g().remove(i);
            this.f1851c.f1843f.f1618b = this.f1844g.f1618b - 1;
            m2914b();
            return remove;
        }

        public int indexOf(Object obj) {
            m2913a();
            return m2919g().indexOf(obj);
        }

        public int lastIndexOf(Object obj) {
            m2913a();
            return m2919g().lastIndexOf(obj);
        }

        public ListIterator<V> listIterator() {
            m2913a();
            return new AbstractMapBasedMultimap(this);
        }

        public ListIterator<V> listIterator(int i) {
            m2913a();
            return new AbstractMapBasedMultimap(this, i);
        }

        public List<V> subList(int i, int i2) {
            AbstractMapBasedMultimap f;
            m2913a();
            AbstractMapBasedMultimap abstractMapBasedMultimap = this.f1844g;
            Object c = m2915c();
            List subList = m2919g().subList(i, i2);
            if (m2918f() != null) {
                f = m2918f();
            }
            return abstractMapBasedMultimap.m2393a(c, subList, f);
        }
    }

    /* renamed from: com.google.a.c.c.c */
    private class AbstractMapBasedMultimap extends AbstractMapBasedMultimap implements RandomAccess {
        final /* synthetic */ AbstractMapBasedMultimap f1845a;

        AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, @Nullable K k, List<V> list, @Nullable AbstractMapBasedMultimap abstractMapBasedMultimap2) {
            this.f1845a = abstractMapBasedMultimap;
            super(abstractMapBasedMultimap, k, list, abstractMapBasedMultimap2);
        }
    }

    /* renamed from: com.google.a.c.c.d */
    private class AbstractMapBasedMultimap extends AbstractMapBasedMultimap implements SortedMap<K, Collection<V>> {
        SortedSet<K> f1846d;
        final /* synthetic */ AbstractMapBasedMultimap f1847e;

        public /* synthetic */ Set keySet() {
            return m2921b();
        }

        AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, SortedMap<K, Collection<V>> sortedMap) {
            this.f1847e = abstractMapBasedMultimap;
            super(abstractMapBasedMultimap, sortedMap);
        }

        SortedMap<K, Collection<V>> m2920a() {
            return (SortedMap) this.a;
        }

        public Comparator<? super K> comparator() {
            return m2920a().comparator();
        }

        public K firstKey() {
            return m2920a().firstKey();
        }

        public K lastKey() {
            return m2920a().lastKey();
        }

        public SortedMap<K, Collection<V>> headMap(K k) {
            return new AbstractMapBasedMultimap(this.f1847e, m2920a().headMap(k));
        }

        public SortedMap<K, Collection<V>> subMap(K k, K k2) {
            return new AbstractMapBasedMultimap(this.f1847e, m2920a().subMap(k, k2));
        }

        public SortedMap<K, Collection<V>> tailMap(K k) {
            return new AbstractMapBasedMultimap(this.f1847e, m2920a().tailMap(k));
        }

        public SortedSet<K> m2921b() {
            SortedSet<K> sortedSet = this.f1846d;
            if (sortedSet != null) {
                return sortedSet;
            }
            sortedSet = m2922c();
            this.f1846d = sortedSet;
            return sortedSet;
        }

        SortedSet<K> m2922c() {
            return new AbstractMapBasedMultimap(this.f1847e, m2920a());
        }
    }

    /* renamed from: com.google.a.c.c.e */
    private class AbstractMapBasedMultimap extends AbstractMapBasedMultimap implements SortedSet<K> {
        final /* synthetic */ AbstractMapBasedMultimap f1848c;

        AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, SortedMap<K, Collection<V>> sortedMap) {
            this.f1848c = abstractMapBasedMultimap;
            super(abstractMapBasedMultimap, sortedMap);
        }

        SortedMap<K, Collection<V>> m2923b() {
            return (SortedMap) this.a;
        }

        public Comparator<? super K> comparator() {
            return m2923b().comparator();
        }

        public K first() {
            return m2923b().firstKey();
        }

        public SortedSet<K> headSet(K k) {
            return new AbstractMapBasedMultimap(this.f1848c, m2923b().headMap(k));
        }

        public K last() {
            return m2923b().lastKey();
        }

        public SortedSet<K> subSet(K k, K k2) {
            return new AbstractMapBasedMultimap(this.f1848c, m2923b().subMap(k, k2));
        }

        public SortedSet<K> tailSet(K k) {
            return new AbstractMapBasedMultimap(this.f1848c, m2923b().tailMap(k));
        }
    }

    /* renamed from: com.google.a.c.c.h */
    private class AbstractMapBasedMultimap extends AbstractMapBasedMultimap implements Set<V> {
        final /* synthetic */ AbstractMapBasedMultimap f1853a;

        AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, @Nullable K k, Set<V> set) {
            this.f1853a = abstractMapBasedMultimap;
            super(abstractMapBasedMultimap, k, set, null);
        }

        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean a = bk.m2876a((Set) this.c, (Collection) collection);
            if (!a) {
                return a;
            }
            AbstractMapBasedMultimap.m2388a(this.f1853a, this.c.size() - size);
            m2914b();
            return a;
        }
    }

    /* renamed from: com.google.a.c.c.i */
    private class AbstractMapBasedMultimap extends AbstractMapBasedMultimap implements SortedSet<V> {
        final /* synthetic */ AbstractMapBasedMultimap f1854a;

        AbstractMapBasedMultimap(AbstractMapBasedMultimap abstractMapBasedMultimap, @Nullable K k, SortedSet<V> sortedSet, @Nullable AbstractMapBasedMultimap abstractMapBasedMultimap2) {
            this.f1854a = abstractMapBasedMultimap;
            super(abstractMapBasedMultimap, k, sortedSet, abstractMapBasedMultimap2);
        }

        SortedSet<V> m2927g() {
            return (SortedSet) m2917e();
        }

        public Comparator<? super V> comparator() {
            return m2927g().comparator();
        }

        public V first() {
            m2913a();
            return m2927g().first();
        }

        public V last() {
            m2913a();
            return m2927g().last();
        }

        public SortedSet<V> headSet(V v) {
            AbstractMapBasedMultimap f;
            m2913a();
            AbstractMapBasedMultimap abstractMapBasedMultimap = this.f1854a;
            Object c = m2915c();
            SortedSet headSet = m2927g().headSet(v);
            if (m2918f() != null) {
                f = m2918f();
            }
            return new AbstractMapBasedMultimap(abstractMapBasedMultimap, c, headSet, f);
        }

        public SortedSet<V> subSet(V v, V v2) {
            AbstractMapBasedMultimap f;
            m2913a();
            AbstractMapBasedMultimap abstractMapBasedMultimap = this.f1854a;
            Object c = m2915c();
            SortedSet subSet = m2927g().subSet(v, v2);
            if (m2918f() != null) {
                f = m2918f();
            }
            return new AbstractMapBasedMultimap(abstractMapBasedMultimap, c, subSet, f);
        }

        public SortedSet<V> tailSet(V v) {
            AbstractMapBasedMultimap f;
            m2913a();
            AbstractMapBasedMultimap abstractMapBasedMultimap = this.f1854a;
            Object c = m2915c();
            SortedSet tailSet = m2927g().tailSet(v);
            if (m2918f() != null) {
                f = m2918f();
            }
            return new AbstractMapBasedMultimap(abstractMapBasedMultimap, c, tailSet, f);
        }
    }

    abstract Collection<V> m2399a();

    static /* synthetic */ int m2388a(AbstractMapBasedMultimap abstractMapBasedMultimap, int i) {
        int i2 = abstractMapBasedMultimap.f1618b + i;
        abstractMapBasedMultimap.f1618b = i2;
        return i2;
    }

    static /* synthetic */ int m2396b(AbstractMapBasedMultimap abstractMapBasedMultimap, int i) {
        int i2 = abstractMapBasedMultimap.f1618b - i;
        abstractMapBasedMultimap.f1618b = i2;
        return i2;
    }

    protected AbstractMapBasedMultimap(Map<K, Collection<V>> map) {
        Preconditions.m1822a(map.isEmpty());
        this.f1617a = map;
    }

    Collection<V> m2400a(@Nullable K k) {
        return m2399a();
    }

    public int m2403b() {
        return this.f1618b;
    }

    public boolean m2402a(@Nullable K k, @Nullable V v) {
        Collection collection = (Collection) this.f1617a.get(k);
        if (collection == null) {
            collection = m2400a((Object) k);
            if (collection.add(v)) {
                this.f1618b++;
                this.f1617a.put(k, collection);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(v)) {
            return false;
        } else {
            this.f1618b++;
            return true;
        }
    }

    public void m2405c() {
        for (Collection clear : this.f1617a.values()) {
            clear.clear();
        }
        this.f1617a.clear();
        this.f1618b = 0;
    }

    public Collection<V> m2404b(@Nullable K k) {
        Collection collection = (Collection) this.f1617a.get(k);
        if (collection == null) {
            collection = m2400a((Object) k);
        }
        return m2401a((Object) k, collection);
    }

    Collection<V> m2401a(@Nullable K k, Collection<V> collection) {
        if (collection instanceof SortedSet) {
            return new AbstractMapBasedMultimap(this, k, (SortedSet) collection, null);
        }
        if (collection instanceof Set) {
            return new AbstractMapBasedMultimap(this, k, (Set) collection);
        }
        if (collection instanceof List) {
            return m2393a(k, (List) collection, null);
        }
        return new AbstractMapBasedMultimap(this, k, collection, null);
    }

    private List<V> m2393a(@Nullable K k, List<V> list, @Nullable AbstractMapBasedMultimap abstractMapBasedMultimap) {
        return list instanceof RandomAccess ? new AbstractMapBasedMultimap(this, k, list, abstractMapBasedMultimap) : new AbstractMapBasedMultimap(this, k, list, abstractMapBasedMultimap);
    }

    private Iterator<V> m2391a(Collection<V> collection) {
        return collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    Set<K> m2406d() {
        return this.f1617a instanceof SortedMap ? new AbstractMapBasedMultimap(this, (SortedMap) this.f1617a) : new AbstractMapBasedMultimap(this, this.f1617a);
    }

    private int m2398c(Object obj) {
        Collection collection = (Collection) au.m2817c(this.f1617a, obj);
        int i = 0;
        if (collection != null) {
            i = collection.size();
            collection.clear();
            this.f1618b -= i;
        }
        return i;
    }

    Map<K, Collection<V>> m2407e() {
        return this.f1617a instanceof SortedMap ? new AbstractMapBasedMultimap(this, (SortedMap) this.f1617a) : new AbstractMapBasedMultimap(this, this.f1617a);
    }
}
