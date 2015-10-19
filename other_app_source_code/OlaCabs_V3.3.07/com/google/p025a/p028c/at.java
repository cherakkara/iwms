package com.google.p025a.p028c;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.p025a.p026a.Equivalence;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p026a.Ticker;
import com.google.p025a.p028c.GenericMapMaker.GenericMapMaker;
import com.google.p025a.p028c.as.MapMaker;
import com.google.p025a.p032g.Ints;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* compiled from: MapMakerInternalMap */
/* renamed from: com.google.a.c.at */
class at<K, V> extends AbstractMap<K, V> implements Serializable, ConcurrentMap<K, V> {
    static final MapMakerInternalMap<Object, Object> f1764p;
    static final Queue<? extends Object> f1765q;
    private static final Logger f1766u;
    final transient int f1767a;
    final transient int f1768b;
    final transient MapMakerInternalMap<K, V>[] f1769c;
    final int f1770d;
    final Equivalence<Object> f1771e;
    final Equivalence<Object> f1772f;
    final MapMakerInternalMap f1773g;
    final MapMakerInternalMap f1774h;
    final int f1775i;
    final long f1776j;
    final long f1777k;
    final Queue<MapMaker<K, V>> f1778l;
    final MapMaker<K, V> f1779m;
    final transient MapMakerInternalMap f1780n;
    final Ticker f1781o;
    transient Set<K> f1782r;
    transient Collection<V> f1783s;
    transient Set<Entry<K, V>> f1784t;

    /* renamed from: com.google.a.c.at.u */
    interface MapMakerInternalMap<K, V> {
        MapMakerInternalMap<K, V> m2540a();

        MapMakerInternalMap<K, V> m2541a(ReferenceQueue<V> referenceQueue, @Nullable V v, MapMakerInternalMap<K, V> mapMakerInternalMap);

        void m2542a(@Nullable MapMakerInternalMap<K, V> mapMakerInternalMap);

        boolean m2543b();

        V get();
    }

    /* renamed from: com.google.a.c.at.1 */
    static class MapMakerInternalMap implements MapMakerInternalMap<Object, Object> {
        MapMakerInternalMap() {
        }

        public Object get() {
            return null;
        }

        public MapMakerInternalMap<Object, Object> m2544a() {
            return null;
        }

        public MapMakerInternalMap<Object, Object> m2545a(ReferenceQueue<Object> referenceQueue, @Nullable Object obj, MapMakerInternalMap<Object, Object> mapMakerInternalMap) {
            return this;
        }

        public boolean m2547b() {
            return false;
        }

        public void m2546a(MapMakerInternalMap<Object, Object> mapMakerInternalMap) {
        }
    }

    /* renamed from: com.google.a.c.at.2 */
    static class MapMakerInternalMap extends AbstractQueue<Object> {
        MapMakerInternalMap() {
        }

        public boolean offer(Object obj) {
            return true;
        }

        public Object peek() {
            return null;
        }

        public Object poll() {
            return null;
        }

        public int size() {
            return 0;
        }

        public Iterator<Object> iterator() {
            return aq.m2496a();
        }
    }

    /* renamed from: com.google.a.c.at.k */
    interface MapMakerInternalMap<K, V> {
        MapMakerInternalMap<K, V> m2548a();

        void m2549a(long j);

        void m2550a(MapMakerInternalMap<K, V> mapMakerInternalMap);

        void m2551a(MapMakerInternalMap<K, V> mapMakerInternalMap);

        MapMakerInternalMap<K, V> m2552b();

        void m2553b(MapMakerInternalMap<K, V> mapMakerInternalMap);

        int m2554c();

        void m2555c(MapMakerInternalMap<K, V> mapMakerInternalMap);

        K m2556d();

        void m2557d(MapMakerInternalMap<K, V> mapMakerInternalMap);

        long m2558e();

        MapMakerInternalMap<K, V> m2559f();

        MapMakerInternalMap<K, V> m2560g();

        MapMakerInternalMap<K, V> m2561h();

        MapMakerInternalMap<K, V> m2562i();
    }

    /* renamed from: com.google.a.c.at.a */
    static abstract class MapMakerInternalMap<K, V> implements MapMakerInternalMap<K, V> {
        MapMakerInternalMap() {
        }

        public MapMakerInternalMap<K, V> m2563a() {
            throw new UnsupportedOperationException();
        }

        public void m2566a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2567b() {
            throw new UnsupportedOperationException();
        }

        public int m2569c() {
            throw new UnsupportedOperationException();
        }

        public K m2571d() {
            throw new UnsupportedOperationException();
        }

        public long m2573e() {
            throw new UnsupportedOperationException();
        }

        public void m2564a(long j) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2574f() {
            throw new UnsupportedOperationException();
        }

        public void m2565a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2575g() {
            throw new UnsupportedOperationException();
        }

        public void m2568b(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2576h() {
            throw new UnsupportedOperationException();
        }

        public void m2570c(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2577i() {
            throw new UnsupportedOperationException();
        }

        public void m2572d(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }
    }

    /* compiled from: MapMakerInternalMap */
    /* renamed from: com.google.a.c.at.aa */
    static final class aa<K, V> extends WeakReference<V> implements MapMakerInternalMap<K, V> {
        final MapMakerInternalMap<K, V> f1679a;

        aa(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap<K, V> mapMakerInternalMap) {
            super(v, referenceQueue);
            this.f1679a = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2578a() {
            return this.f1679a;
        }

        public void m2580a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            clear();
        }

        public MapMakerInternalMap<K, V> m2579a(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap<K, V> mapMakerInternalMap) {
            return new aa(referenceQueue, v, mapMakerInternalMap);
        }

        public boolean m2581b() {
            return false;
        }
    }

    /* compiled from: MapMakerInternalMap */
    /* renamed from: com.google.a.c.at.ab */
    final class ab extends AbstractMapEntry<K, V> {
        final K f1680a;
        V f1681b;
        final /* synthetic */ at f1682c;

        ab(at atVar, K k, V v) {
            this.f1682c = atVar;
            this.f1680a = k;
            this.f1681b = v;
        }

        public K getKey() {
            return this.f1680a;
        }

        public V getValue() {
            return this.f1681b;
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.f1680a.equals(entry.getKey()) && this.f1681b.equals(entry.getValue())) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.f1680a.hashCode() ^ this.f1681b.hashCode();
        }

        public V setValue(V v) {
            V put = this.f1682c.put(this.f1680a, v);
            this.f1681b = v;
            return put;
        }
    }

    /* renamed from: com.google.a.c.at.b */
    enum MapMakerInternalMap {
        STRONG {
            <K, V> MapMakerInternalMap<K, V> m2587a(MapMakerInternalMap<K, V> mapMakerInternalMap, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap2) {
                return new MapMakerInternalMap(k, i, mapMakerInternalMap2);
            }
        },
        STRONG_EXPIRABLE {
            <K, V> MapMakerInternalMap<K, V> m2589a(MapMakerInternalMap<K, V> mapMakerInternalMap, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap2) {
                return new MapMakerInternalMap(k, i, mapMakerInternalMap2);
            }

            <K, V> MapMakerInternalMap<K, V> m2588a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2, MapMakerInternalMap<K, V> mapMakerInternalMap3) {
                MapMakerInternalMap<K, V> a = super.m2583a((MapMakerInternalMap) mapMakerInternalMap, (MapMakerInternalMap) mapMakerInternalMap2, (MapMakerInternalMap) mapMakerInternalMap3);
                m2585a(mapMakerInternalMap2, a);
                return a;
            }
        },
        STRONG_EVICTABLE {
            <K, V> MapMakerInternalMap<K, V> m2591a(MapMakerInternalMap<K, V> mapMakerInternalMap, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap2) {
                return new MapMakerInternalMap(k, i, mapMakerInternalMap2);
            }

            <K, V> MapMakerInternalMap<K, V> m2590a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2, MapMakerInternalMap<K, V> mapMakerInternalMap3) {
                MapMakerInternalMap<K, V> a = super.m2583a((MapMakerInternalMap) mapMakerInternalMap, (MapMakerInternalMap) mapMakerInternalMap2, (MapMakerInternalMap) mapMakerInternalMap3);
                m2586b(mapMakerInternalMap2, a);
                return a;
            }
        },
        STRONG_EXPIRABLE_EVICTABLE {
            <K, V> MapMakerInternalMap<K, V> m2593a(MapMakerInternalMap<K, V> mapMakerInternalMap, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap2) {
                return new MapMakerInternalMap(k, i, mapMakerInternalMap2);
            }

            <K, V> MapMakerInternalMap<K, V> m2592a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2, MapMakerInternalMap<K, V> mapMakerInternalMap3) {
                MapMakerInternalMap<K, V> a = super.m2583a((MapMakerInternalMap) mapMakerInternalMap, (MapMakerInternalMap) mapMakerInternalMap2, (MapMakerInternalMap) mapMakerInternalMap3);
                m2585a(mapMakerInternalMap2, a);
                m2586b(mapMakerInternalMap2, a);
                return a;
            }
        },
        WEAK {
            <K, V> MapMakerInternalMap<K, V> m2594a(MapMakerInternalMap<K, V> mapMakerInternalMap, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap2) {
                return new MapMakerInternalMap(mapMakerInternalMap.f1723g, k, i, mapMakerInternalMap2);
            }
        },
        WEAK_EXPIRABLE {
            <K, V> MapMakerInternalMap<K, V> m2596a(MapMakerInternalMap<K, V> mapMakerInternalMap, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap2) {
                return new MapMakerInternalMap(mapMakerInternalMap.f1723g, k, i, mapMakerInternalMap2);
            }

            <K, V> MapMakerInternalMap<K, V> m2595a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2, MapMakerInternalMap<K, V> mapMakerInternalMap3) {
                MapMakerInternalMap<K, V> a = super.m2583a((MapMakerInternalMap) mapMakerInternalMap, (MapMakerInternalMap) mapMakerInternalMap2, (MapMakerInternalMap) mapMakerInternalMap3);
                m2585a(mapMakerInternalMap2, a);
                return a;
            }
        },
        WEAK_EVICTABLE {
            <K, V> MapMakerInternalMap<K, V> m2598a(MapMakerInternalMap<K, V> mapMakerInternalMap, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap2) {
                return new MapMakerInternalMap(mapMakerInternalMap.f1723g, k, i, mapMakerInternalMap2);
            }

            <K, V> MapMakerInternalMap<K, V> m2597a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2, MapMakerInternalMap<K, V> mapMakerInternalMap3) {
                MapMakerInternalMap<K, V> a = super.m2583a((MapMakerInternalMap) mapMakerInternalMap, (MapMakerInternalMap) mapMakerInternalMap2, (MapMakerInternalMap) mapMakerInternalMap3);
                m2586b(mapMakerInternalMap2, a);
                return a;
            }
        },
        WEAK_EXPIRABLE_EVICTABLE {
            <K, V> MapMakerInternalMap<K, V> m2600a(MapMakerInternalMap<K, V> mapMakerInternalMap, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap2) {
                return new MapMakerInternalMap(mapMakerInternalMap.f1723g, k, i, mapMakerInternalMap2);
            }

            <K, V> MapMakerInternalMap<K, V> m2599a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2, MapMakerInternalMap<K, V> mapMakerInternalMap3) {
                MapMakerInternalMap<K, V> a = super.m2583a((MapMakerInternalMap) mapMakerInternalMap, (MapMakerInternalMap) mapMakerInternalMap2, (MapMakerInternalMap) mapMakerInternalMap3);
                m2585a(mapMakerInternalMap2, a);
                m2586b(mapMakerInternalMap2, a);
                return a;
            }
        };
        
        static final MapMakerInternalMap[][] f1691i;

        abstract <K, V> MapMakerInternalMap<K, V> m2584a(MapMakerInternalMap<K, V> mapMakerInternalMap, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap2);

        static {
            r0 = new MapMakerInternalMap[3][];
            r0[0] = new MapMakerInternalMap[]{STRONG, STRONG_EXPIRABLE, STRONG_EVICTABLE, STRONG_EXPIRABLE_EVICTABLE};
            r0[1] = new MapMakerInternalMap[0];
            r0[2] = new MapMakerInternalMap[]{WEAK, WEAK_EXPIRABLE, WEAK_EVICTABLE, WEAK_EXPIRABLE_EVICTABLE};
            f1691i = r0;
        }

        static MapMakerInternalMap m2582a(MapMakerInternalMap mapMakerInternalMap, boolean z, boolean z2) {
            int i;
            int i2 = 0;
            if (z) {
                i = 1;
            } else {
                i = 0;
            }
            if (z2) {
                i2 = 2;
            }
            return f1691i[mapMakerInternalMap.ordinal()][i2 | i];
        }

        @GuardedBy("Segment.this")
        <K, V> MapMakerInternalMap<K, V> m2583a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2, MapMakerInternalMap<K, V> mapMakerInternalMap3) {
            return m2584a(mapMakerInternalMap, mapMakerInternalMap2.m2556d(), mapMakerInternalMap2.m2554c(), mapMakerInternalMap3);
        }

        @GuardedBy("Segment.this")
        <K, V> void m2585a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2) {
            mapMakerInternalMap2.m2549a(mapMakerInternalMap.m2558e());
            at.m2776a(mapMakerInternalMap.m2560g(), (MapMakerInternalMap) mapMakerInternalMap2);
            at.m2776a((MapMakerInternalMap) mapMakerInternalMap2, mapMakerInternalMap.m2559f());
            at.m2778d(mapMakerInternalMap);
        }

        @GuardedBy("Segment.this")
        <K, V> void m2586b(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2) {
            at.m2777b(mapMakerInternalMap.m2562i(), mapMakerInternalMap2);
            at.m2777b(mapMakerInternalMap2, mapMakerInternalMap.m2561h());
            at.m2779e(mapMakerInternalMap);
        }
    }

    /* renamed from: com.google.a.c.at.g */
    abstract class MapMakerInternalMap<E> implements Iterator<E> {
        int f1693b;
        int f1694c;
        MapMakerInternalMap<K, V> f1695d;
        AtomicReferenceArray<MapMakerInternalMap<K, V>> f1696e;
        MapMakerInternalMap<K, V> f1697f;
        ab f1698g;
        ab f1699h;
        final /* synthetic */ at f1700i;

        MapMakerInternalMap(at atVar) {
            this.f1700i = atVar;
            this.f1693b = atVar.f1769c.length - 1;
            this.f1694c = -1;
            m2602b();
        }

        final void m2602b() {
            this.f1698g = null;
            if (!m2603c() && !m2604d()) {
                while (this.f1693b >= 0) {
                    MapMakerInternalMap[] mapMakerInternalMapArr = this.f1700i.f1769c;
                    int i = this.f1693b;
                    this.f1693b = i - 1;
                    this.f1695d = mapMakerInternalMapArr[i];
                    if (this.f1695d.f1718b != 0) {
                        this.f1696e = this.f1695d.f1721e;
                        this.f1694c = this.f1696e.length() - 1;
                        if (m2604d()) {
                            return;
                        }
                    }
                }
            }
        }

        boolean m2603c() {
            if (this.f1697f != null) {
                this.f1697f = this.f1697f.m2552b();
                while (this.f1697f != null) {
                    if (m2601a(this.f1697f)) {
                        return true;
                    }
                    this.f1697f = this.f1697f.m2552b();
                }
            }
            return false;
        }

        boolean m2604d() {
            while (this.f1694c >= 0) {
                AtomicReferenceArray atomicReferenceArray = this.f1696e;
                int i = this.f1694c;
                this.f1694c = i - 1;
                MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) atomicReferenceArray.get(i);
                this.f1697f = mapMakerInternalMap;
                if (mapMakerInternalMap != null && (m2601a(this.f1697f) || m2603c())) {
                    return true;
                }
            }
            return false;
        }

        boolean m2601a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            try {
                Object d = mapMakerInternalMap.m2556d();
                Object b = this.f1700i.m2790b((MapMakerInternalMap) mapMakerInternalMap);
                if (b != null) {
                    this.f1698g = new ab(this.f1700i, d, b);
                    return true;
                }
                this.f1695d.m2683n();
                return false;
            } finally {
                this.f1695d.m2683n();
            }
        }

        public boolean hasNext() {
            return this.f1698g != null;
        }

        ab m2605e() {
            if (this.f1698g == null) {
                throw new NoSuchElementException();
            }
            this.f1699h = this.f1698g;
            m2602b();
            return this.f1699h;
        }

        public void remove() {
            Preconditions.m1828b(this.f1699h != null);
            this.f1700i.remove(this.f1699h.getKey());
            this.f1699h = null;
        }
    }

    /* renamed from: com.google.a.c.at.c */
    final class MapMakerInternalMap extends MapMakerInternalMap<Entry<K, V>> {
        final /* synthetic */ at f1701a;

        MapMakerInternalMap(at atVar) {
            this.f1701a = atVar;
            super(atVar);
        }

        public /* synthetic */ Object next() {
            return m2606a();
        }

        public Entry<K, V> m2606a() {
            return m2605e();
        }
    }

    /* renamed from: com.google.a.c.at.d */
    final class MapMakerInternalMap extends AbstractSet<Entry<K, V>> {
        final /* synthetic */ at f1702a;

        MapMakerInternalMap(at atVar) {
            this.f1702a = atVar;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new MapMakerInternalMap(this.f1702a);
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object key = entry.getKey();
            if (key == null) {
                return false;
            }
            key = this.f1702a.get(key);
            if (key == null || !this.f1702a.f1772f.m1771a(entry.getValue(), key)) {
                return false;
            }
            return true;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object key = entry.getKey();
            if (key == null || !this.f1702a.remove(key, entry.getValue())) {
                return false;
            }
            return true;
        }

        public int size() {
            return this.f1702a.size();
        }

        public boolean isEmpty() {
            return this.f1702a.isEmpty();
        }

        public void clear() {
            this.f1702a.clear();
        }
    }

    /* renamed from: com.google.a.c.at.e */
    static final class MapMakerInternalMap<K, V> extends AbstractQueue<MapMakerInternalMap<K, V>> {
        final MapMakerInternalMap<K, V> f1707a;

        /* renamed from: com.google.a.c.at.e.1 */
        class MapMakerInternalMap extends MapMakerInternalMap<K, V> {
            MapMakerInternalMap<K, V> f1703a;
            MapMakerInternalMap<K, V> f1704b;
            final /* synthetic */ MapMakerInternalMap f1705c;

            MapMakerInternalMap(MapMakerInternalMap mapMakerInternalMap) {
                this.f1705c = mapMakerInternalMap;
                this.f1703a = this;
                this.f1704b = this;
            }

            public MapMakerInternalMap<K, V> m2609h() {
                return this.f1703a;
            }

            public void m2607c(MapMakerInternalMap<K, V> mapMakerInternalMap) {
                this.f1703a = mapMakerInternalMap;
            }

            public MapMakerInternalMap<K, V> m2610i() {
                return this.f1704b;
            }

            public void m2608d(MapMakerInternalMap<K, V> mapMakerInternalMap) {
                this.f1704b = mapMakerInternalMap;
            }
        }

        /* renamed from: com.google.a.c.at.e.2 */
        class MapMakerInternalMap extends AbstractSequentialIterator<MapMakerInternalMap<K, V>> {
            final /* synthetic */ MapMakerInternalMap f1706a;

            MapMakerInternalMap(MapMakerInternalMap mapMakerInternalMap, MapMakerInternalMap mapMakerInternalMap2) {
                this.f1706a = mapMakerInternalMap;
                super(mapMakerInternalMap2);
            }

            protected MapMakerInternalMap<K, V> m2611a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
                MapMakerInternalMap<K, V> h = mapMakerInternalMap.m2561h();
                return h == this.f1706a.f1707a ? null : h;
            }
        }

        MapMakerInternalMap() {
            this.f1707a = new MapMakerInternalMap(this);
        }

        public /* synthetic */ boolean offer(Object obj) {
            return m2614a((MapMakerInternalMap) obj);
        }

        public /* synthetic */ Object peek() {
            return m2613a();
        }

        public /* synthetic */ Object poll() {
            return m2615b();
        }

        public boolean m2614a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            at.m2777b(mapMakerInternalMap.m2562i(), mapMakerInternalMap.m2561h());
            at.m2777b(this.f1707a.m2562i(), mapMakerInternalMap);
            at.m2777b(mapMakerInternalMap, this.f1707a);
            return true;
        }

        public MapMakerInternalMap<K, V> m2613a() {
            MapMakerInternalMap<K, V> h = this.f1707a.m2561h();
            return h == this.f1707a ? null : h;
        }

        public MapMakerInternalMap<K, V> m2615b() {
            MapMakerInternalMap<K, V> h = this.f1707a.m2561h();
            if (h == this.f1707a) {
                return null;
            }
            remove(h);
            return h;
        }

        public boolean remove(Object obj) {
            MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) obj;
            MapMakerInternalMap i = mapMakerInternalMap.m2562i();
            MapMakerInternalMap h = mapMakerInternalMap.m2561h();
            at.m2777b(i, h);
            at.m2779e(mapMakerInternalMap);
            return h != MapMakerInternalMap.INSTANCE;
        }

        public boolean contains(Object obj) {
            return ((MapMakerInternalMap) obj).m2561h() != MapMakerInternalMap.INSTANCE;
        }

        public boolean isEmpty() {
            return this.f1707a.m2561h() == this.f1707a;
        }

        public int size() {
            int i = 0;
            for (MapMakerInternalMap h = this.f1707a.m2561h(); h != this.f1707a; h = h.m2561h()) {
                i++;
            }
            return i;
        }

        public void clear() {
            MapMakerInternalMap h = this.f1707a.m2561h();
            while (h != this.f1707a) {
                MapMakerInternalMap h2 = h.m2561h();
                at.m2779e(h);
                h = h2;
            }
            this.f1707a.m2555c(this.f1707a);
            this.f1707a.m2557d(this.f1707a);
        }

        public Iterator<MapMakerInternalMap<K, V>> iterator() {
            return new MapMakerInternalMap(this, m2613a());
        }
    }

    /* renamed from: com.google.a.c.at.f */
    static final class MapMakerInternalMap<K, V> extends AbstractQueue<MapMakerInternalMap<K, V>> {
        final MapMakerInternalMap<K, V> f1712a;

        /* renamed from: com.google.a.c.at.f.1 */
        class MapMakerInternalMap extends MapMakerInternalMap<K, V> {
            MapMakerInternalMap<K, V> f1708a;
            MapMakerInternalMap<K, V> f1709b;
            final /* synthetic */ MapMakerInternalMap f1710c;

            MapMakerInternalMap(MapMakerInternalMap mapMakerInternalMap) {
                this.f1710c = mapMakerInternalMap;
                this.f1708a = this;
                this.f1709b = this;
            }

            public long m2619e() {
                return Long.MAX_VALUE;
            }

            public void m2616a(long j) {
            }

            public MapMakerInternalMap<K, V> m2620f() {
                return this.f1708a;
            }

            public void m2617a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
                this.f1708a = mapMakerInternalMap;
            }

            public MapMakerInternalMap<K, V> m2621g() {
                return this.f1709b;
            }

            public void m2618b(MapMakerInternalMap<K, V> mapMakerInternalMap) {
                this.f1709b = mapMakerInternalMap;
            }
        }

        /* renamed from: com.google.a.c.at.f.2 */
        class MapMakerInternalMap extends AbstractSequentialIterator<MapMakerInternalMap<K, V>> {
            final /* synthetic */ MapMakerInternalMap f1711a;

            MapMakerInternalMap(MapMakerInternalMap mapMakerInternalMap, MapMakerInternalMap mapMakerInternalMap2) {
                this.f1711a = mapMakerInternalMap;
                super(mapMakerInternalMap2);
            }

            protected MapMakerInternalMap<K, V> m2622a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
                MapMakerInternalMap<K, V> f = mapMakerInternalMap.m2559f();
                return f == this.f1711a.f1712a ? null : f;
            }
        }

        MapMakerInternalMap() {
            this.f1712a = new MapMakerInternalMap(this);
        }

        public /* synthetic */ boolean offer(Object obj) {
            return m2625a((MapMakerInternalMap) obj);
        }

        public /* synthetic */ Object peek() {
            return m2624a();
        }

        public /* synthetic */ Object poll() {
            return m2626b();
        }

        public boolean m2625a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            at.m2776a(mapMakerInternalMap.m2560g(), mapMakerInternalMap.m2559f());
            at.m2776a(this.f1712a.m2560g(), (MapMakerInternalMap) mapMakerInternalMap);
            at.m2776a((MapMakerInternalMap) mapMakerInternalMap, this.f1712a);
            return true;
        }

        public MapMakerInternalMap<K, V> m2624a() {
            MapMakerInternalMap<K, V> f = this.f1712a.m2559f();
            return f == this.f1712a ? null : f;
        }

        public MapMakerInternalMap<K, V> m2626b() {
            MapMakerInternalMap<K, V> f = this.f1712a.m2559f();
            if (f == this.f1712a) {
                return null;
            }
            remove(f);
            return f;
        }

        public boolean remove(Object obj) {
            MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) obj;
            MapMakerInternalMap g = mapMakerInternalMap.m2560g();
            MapMakerInternalMap f = mapMakerInternalMap.m2559f();
            at.m2776a(g, f);
            at.m2778d(mapMakerInternalMap);
            return f != MapMakerInternalMap.INSTANCE;
        }

        public boolean contains(Object obj) {
            return ((MapMakerInternalMap) obj).m2559f() != MapMakerInternalMap.INSTANCE;
        }

        public boolean isEmpty() {
            return this.f1712a.m2559f() == this.f1712a;
        }

        public int size() {
            int i = 0;
            for (MapMakerInternalMap f = this.f1712a.m2559f(); f != this.f1712a; f = f.m2559f()) {
                i++;
            }
            return i;
        }

        public void clear() {
            MapMakerInternalMap f = this.f1712a.m2559f();
            while (f != this.f1712a) {
                MapMakerInternalMap f2 = f.m2559f();
                at.m2778d(f);
                f = f2;
            }
            this.f1712a.m2550a(this.f1712a);
            this.f1712a.m2553b(this.f1712a);
        }

        public Iterator<MapMakerInternalMap<K, V>> iterator() {
            return new MapMakerInternalMap(this, m2624a());
        }
    }

    /* renamed from: com.google.a.c.at.h */
    final class MapMakerInternalMap extends MapMakerInternalMap<K> {
        final /* synthetic */ at f1713a;

        MapMakerInternalMap(at atVar) {
            this.f1713a = atVar;
            super(atVar);
        }

        public K next() {
            return m2605e().getKey();
        }
    }

    /* renamed from: com.google.a.c.at.i */
    final class MapMakerInternalMap extends AbstractSet<K> {
        final /* synthetic */ at f1714a;

        MapMakerInternalMap(at atVar) {
            this.f1714a = atVar;
        }

        public Iterator<K> iterator() {
            return new MapMakerInternalMap(this.f1714a);
        }

        public int size() {
            return this.f1714a.size();
        }

        public boolean isEmpty() {
            return this.f1714a.isEmpty();
        }

        public boolean contains(Object obj) {
            return this.f1714a.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return this.f1714a.remove(obj) != null;
        }

        public void clear() {
            this.f1714a.clear();
        }
    }

    /* renamed from: com.google.a.c.at.j */
    private enum MapMakerInternalMap implements MapMakerInternalMap<Object, Object> {
        INSTANCE;

        public MapMakerInternalMap<Object, Object> m2627a() {
            return null;
        }

        public void m2630a(MapMakerInternalMap<Object, Object> mapMakerInternalMap) {
        }

        public MapMakerInternalMap<Object, Object> m2631b() {
            return null;
        }

        public int m2633c() {
            return 0;
        }

        public Object m2635d() {
            return null;
        }

        public long m2637e() {
            return 0;
        }

        public void m2628a(long j) {
        }

        public MapMakerInternalMap<Object, Object> m2638f() {
            return this;
        }

        public void m2629a(MapMakerInternalMap<Object, Object> mapMakerInternalMap) {
        }

        public MapMakerInternalMap<Object, Object> m2639g() {
            return this;
        }

        public void m2632b(MapMakerInternalMap<Object, Object> mapMakerInternalMap) {
        }

        public MapMakerInternalMap<Object, Object> m2640h() {
            return this;
        }

        public void m2634c(MapMakerInternalMap<Object, Object> mapMakerInternalMap) {
        }

        public MapMakerInternalMap<Object, Object> m2641i() {
            return this;
        }

        public void m2636d(MapMakerInternalMap<Object, Object> mapMakerInternalMap) {
        }
    }

    /* renamed from: com.google.a.c.at.l */
    static class MapMakerInternalMap<K, V> extends ReentrantLock {
        final at<K, V> f1717a;
        volatile int f1718b;
        int f1719c;
        int f1720d;
        volatile AtomicReferenceArray<MapMakerInternalMap<K, V>> f1721e;
        final int f1722f;
        final ReferenceQueue<K> f1723g;
        final ReferenceQueue<V> f1724h;
        final Queue<MapMakerInternalMap<K, V>> f1725i;
        final AtomicInteger f1726j;
        @GuardedBy("Segment.this")
        final Queue<MapMakerInternalMap<K, V>> f1727k;
        @GuardedBy("Segment.this")
        final Queue<MapMakerInternalMap<K, V>> f1728l;

        MapMakerInternalMap(at<K, V> atVar, int i, int i2) {
            ReferenceQueue referenceQueue = null;
            this.f1726j = new AtomicInteger();
            this.f1717a = atVar;
            this.f1722f = i2;
            m2654a(m2647a(i));
            this.f1723g = atVar.m2796e() ? new ReferenceQueue() : null;
            if (atVar.m2797f()) {
                referenceQueue = new ReferenceQueue();
            }
            this.f1724h = referenceQueue;
            Queue concurrentLinkedQueue = (atVar.m2787a() || atVar.m2795d()) ? new ConcurrentLinkedQueue() : at.m2782i();
            this.f1725i = concurrentLinkedQueue;
            this.f1727k = atVar.m2787a() ? new MapMakerInternalMap() : at.m2782i();
            this.f1728l = atVar.m2791b() ? new MapMakerInternalMap() : at.m2782i();
        }

        AtomicReferenceArray<MapMakerInternalMap<K, V>> m2647a(int i) {
            return new AtomicReferenceArray(i);
        }

        void m2654a(AtomicReferenceArray<MapMakerInternalMap<K, V>> atomicReferenceArray) {
            this.f1720d = (atomicReferenceArray.length() * 3) / 4;
            if (this.f1720d == this.f1722f) {
                this.f1720d++;
            }
            this.f1721e = atomicReferenceArray;
        }

        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> m2644a(K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap) {
            return this.f1717a.f1780n.m2584a(this, k, i, mapMakerInternalMap);
        }

        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> m2642a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2) {
            if (mapMakerInternalMap.m2556d() == null) {
                return null;
            }
            MapMakerInternalMap a = mapMakerInternalMap.m2548a();
            Object obj = a.get();
            if (obj == null && !a.m2543b()) {
                return null;
            }
            MapMakerInternalMap<K, V> a2 = this.f1717a.f1780n.m2583a(this, (MapMakerInternalMap) mapMakerInternalMap, (MapMakerInternalMap) mapMakerInternalMap2);
            a2.m2551a(a.m2541a(this.f1724h, obj, a2));
            return a2;
        }

        @GuardedBy("Segment.this")
        void m2652a(MapMakerInternalMap<K, V> mapMakerInternalMap, V v) {
            mapMakerInternalMap.m2551a(this.f1717a.f1774h.m2694a(this, mapMakerInternalMap, v));
            m2668c(mapMakerInternalMap);
        }

        void m2648a() {
            if (tryLock()) {
                try {
                    m2663b();
                } finally {
                    unlock();
                }
            }
        }

        @GuardedBy("Segment.this")
        void m2663b() {
            if (this.f1717a.m2796e()) {
                m2667c();
            }
            if (this.f1717a.m2797f()) {
                m2669d();
            }
        }

        @GuardedBy("Segment.this")
        void m2667c() {
            int i = 0;
            while (true) {
                Reference poll = this.f1723g.poll();
                if (poll != null) {
                    this.f1717a.m2785a((MapMakerInternalMap) poll);
                    int i2 = i + 1;
                    if (i2 != 16) {
                        i = i2;
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        @GuardedBy("Segment.this")
        void m2669d() {
            int i = 0;
            while (true) {
                Reference poll = this.f1724h.poll();
                if (poll != null) {
                    this.f1717a.m2786a((MapMakerInternalMap) poll);
                    int i2 = i + 1;
                    if (i2 != 16) {
                        i = i2;
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        void m2674e() {
            if (this.f1717a.m2796e()) {
                m2675f();
            }
            if (this.f1717a.m2797f()) {
                m2676g();
            }
        }

        void m2675f() {
            do {
            } while (this.f1723g.poll() != null);
        }

        void m2676g() {
            do {
            } while (this.f1724h.poll() != null);
        }

        void m2649a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            if (this.f1717a.m2795d()) {
                m2650a((MapMakerInternalMap) mapMakerInternalMap, this.f1717a.f1776j);
            }
            this.f1725i.add(mapMakerInternalMap);
        }

        @GuardedBy("Segment.this")
        void m2664b(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1727k.add(mapMakerInternalMap);
            if (this.f1717a.m2795d()) {
                m2650a((MapMakerInternalMap) mapMakerInternalMap, this.f1717a.f1776j);
                this.f1728l.add(mapMakerInternalMap);
            }
        }

        @GuardedBy("Segment.this")
        void m2668c(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            m2677h();
            this.f1727k.add(mapMakerInternalMap);
            if (this.f1717a.m2791b()) {
                m2650a((MapMakerInternalMap) mapMakerInternalMap, this.f1717a.m2795d() ? this.f1717a.f1776j : this.f1717a.f1777k);
                this.f1728l.add(mapMakerInternalMap);
            }
        }

        @GuardedBy("Segment.this")
        void m2677h() {
            while (true) {
                MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) this.f1725i.poll();
                if (mapMakerInternalMap != null) {
                    if (this.f1727k.contains(mapMakerInternalMap)) {
                        this.f1727k.add(mapMakerInternalMap);
                    }
                    if (this.f1717a.m2795d() && this.f1728l.contains(mapMakerInternalMap)) {
                        this.f1728l.add(mapMakerInternalMap);
                    }
                } else {
                    return;
                }
            }
        }

        void m2650a(MapMakerInternalMap<K, V> mapMakerInternalMap, long j) {
            mapMakerInternalMap.m2549a(this.f1717a.f1781o.m1871a() + j);
        }

        void m2678i() {
            if (tryLock()) {
                try {
                    m2679j();
                } finally {
                    unlock();
                }
            }
        }

        @GuardedBy("Segment.this")
        void m2679j() {
            m2677h();
            if (!this.f1728l.isEmpty()) {
                long a = this.f1717a.f1781o.m1871a();
                MapMakerInternalMap mapMakerInternalMap;
                do {
                    mapMakerInternalMap = (MapMakerInternalMap) this.f1728l.peek();
                    if (mapMakerInternalMap == null || !this.f1717a.m2788a(mapMakerInternalMap, a)) {
                        return;
                    }
                } while (m2656a(mapMakerInternalMap, mapMakerInternalMap.m2554c(), MapMaker.EXPIRED));
                throw new AssertionError();
            }
        }

        void m2651a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMaker mapMaker) {
            m2653a(mapMakerInternalMap.m2556d(), mapMakerInternalMap.m2554c(), mapMakerInternalMap.m2548a().get(), mapMaker);
        }

        void m2653a(@Nullable K k, int i, @Nullable V v, MapMaker mapMaker) {
            if (this.f1717a.f1778l != at.f1765q) {
                this.f1717a.f1778l.offer(new MapMaker(k, v, mapMaker));
            }
        }

        @GuardedBy("Segment.this")
        boolean m2680k() {
            if (!this.f1717a.m2787a() || this.f1718b < this.f1722f) {
                return false;
            }
            m2677h();
            MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) this.f1727k.remove();
            if (m2656a(mapMakerInternalMap, mapMakerInternalMap.m2554c(), MapMaker.SIZE)) {
                return true;
            }
            throw new AssertionError();
        }

        MapMakerInternalMap<K, V> m2660b(int i) {
            AtomicReferenceArray atomicReferenceArray = this.f1721e;
            return (MapMakerInternalMap) atomicReferenceArray.get((atomicReferenceArray.length() - 1) & i);
        }

        MapMakerInternalMap<K, V> m2643a(Object obj, int i) {
            if (this.f1718b != 0) {
                for (MapMakerInternalMap<K, V> b = m2660b(i); b != null; b = b.m2552b()) {
                    if (b.m2554c() == i) {
                        Object d = b.m2556d();
                        if (d == null) {
                            m2648a();
                        } else if (this.f1717a.f1771e.m1771a(obj, d)) {
                            return b;
                        }
                    }
                }
            }
            return null;
        }

        MapMakerInternalMap<K, V> m2662b(Object obj, int i) {
            MapMakerInternalMap<K, V> a = m2643a(obj, i);
            if (a == null) {
                return null;
            }
            if (!this.f1717a.m2791b() || !this.f1717a.m2793c((MapMakerInternalMap) a)) {
                return a;
            }
            m2678i();
            return null;
        }

        V m2666c(Object obj, int i) {
            try {
                MapMakerInternalMap b = m2662b(obj, i);
                if (b == null) {
                    return null;
                }
                V v = b.m2548a().get();
                if (v != null) {
                    m2649a(b);
                } else {
                    m2648a();
                }
                m2683n();
                return v;
            } finally {
                m2683n();
            }
        }

        boolean m2671d(Object obj, int i) {
            boolean z = false;
            try {
                if (this.f1718b != 0) {
                    MapMakerInternalMap b = m2662b(obj, i);
                    if (b != null) {
                        if (b.m2548a().get() != null) {
                            z = true;
                        }
                        m2683n();
                    }
                } else {
                    m2683n();
                }
                return z;
            } finally {
                m2683n();
            }
        }

        V m2646a(K k, int i, V v, boolean z) {
            V v2 = null;
            lock();
            try {
                int i2;
                m2684o();
                int i3 = this.f1718b + 1;
                if (i3 > this.f1720d) {
                    m2681l();
                    i3 = this.f1718b + 1;
                }
                AtomicReferenceArray atomicReferenceArray = this.f1721e;
                int length = i & (atomicReferenceArray.length() - 1);
                MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) atomicReferenceArray.get(length);
                for (MapMakerInternalMap mapMakerInternalMap2 = mapMakerInternalMap; mapMakerInternalMap2 != null; mapMakerInternalMap2 = mapMakerInternalMap2.m2552b()) {
                    Object d = mapMakerInternalMap2.m2556d();
                    if (mapMakerInternalMap2.m2554c() == i && d != null && this.f1717a.f1771e.m1771a(k, d)) {
                        MapMakerInternalMap a = mapMakerInternalMap2.m2548a();
                        Object obj = a.get();
                        if (obj == null) {
                            this.f1719c++;
                            m2652a(mapMakerInternalMap2, (Object) v);
                            if (!a.m2543b()) {
                                m2653a((Object) k, i, obj, MapMaker.COLLECTED);
                                i3 = this.f1718b;
                            } else if (m2680k()) {
                                i3 = this.f1718b + 1;
                            }
                            this.f1718b = i3;
                            return v2;
                        } else if (z) {
                            m2664b(mapMakerInternalMap2);
                            unlock();
                            m2685p();
                            return obj;
                        } else {
                            this.f1719c++;
                            v2 = MapMaker.REPLACED;
                            m2653a((Object) k, i, obj, (MapMaker) v2);
                            m2652a(mapMakerInternalMap2, (Object) v);
                            unlock();
                            m2685p();
                            return obj;
                        }
                    }
                }
                this.f1719c++;
                mapMakerInternalMap = m2644a((Object) k, i, mapMakerInternalMap);
                m2652a(mapMakerInternalMap, (Object) v);
                atomicReferenceArray.set(length, mapMakerInternalMap);
                if (m2680k()) {
                    i2 = this.f1718b + 1;
                } else {
                    i2 = i3;
                }
                this.f1718b = i2;
                unlock();
                m2685p();
                return null;
            } finally {
                unlock();
                m2685p();
            }
        }

        @GuardedBy("Segment.this")
        void m2681l() {
            AtomicReferenceArray atomicReferenceArray = this.f1721e;
            int length = atomicReferenceArray.length();
            if (length < 1073741824) {
                int i = this.f1718b;
                AtomicReferenceArray a = m2647a(length << 1);
                this.f1720d = (a.length() * 3) / 4;
                int length2 = a.length() - 1;
                int i2 = 0;
                while (i2 < length) {
                    int i3;
                    MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) atomicReferenceArray.get(i2);
                    if (mapMakerInternalMap != null) {
                        MapMakerInternalMap b = mapMakerInternalMap.m2552b();
                        int c = mapMakerInternalMap.m2554c() & length2;
                        if (b == null) {
                            a.set(c, mapMakerInternalMap);
                            i3 = i;
                        } else {
                            MapMakerInternalMap mapMakerInternalMap2;
                            MapMakerInternalMap mapMakerInternalMap3 = mapMakerInternalMap;
                            while (b != null) {
                                i3 = b.m2554c() & length2;
                                if (i3 != c) {
                                    mapMakerInternalMap2 = b;
                                } else {
                                    i3 = c;
                                    mapMakerInternalMap2 = mapMakerInternalMap3;
                                }
                                b = b.m2552b();
                                mapMakerInternalMap3 = mapMakerInternalMap2;
                                c = i3;
                            }
                            a.set(c, mapMakerInternalMap3);
                            mapMakerInternalMap2 = mapMakerInternalMap;
                            i3 = i;
                            while (mapMakerInternalMap2 != mapMakerInternalMap3) {
                                int i4;
                                int c2 = mapMakerInternalMap2.m2554c() & length2;
                                mapMakerInternalMap = m2642a(mapMakerInternalMap2, (MapMakerInternalMap) a.get(c2));
                                if (mapMakerInternalMap != null) {
                                    a.set(c2, mapMakerInternalMap);
                                    i4 = i3;
                                } else {
                                    m2670d(mapMakerInternalMap2);
                                    i4 = i3 - 1;
                                }
                                mapMakerInternalMap2 = mapMakerInternalMap2.m2552b();
                                i3 = i4;
                            }
                        }
                    } else {
                        i3 = i;
                    }
                    i2++;
                    i = i3;
                }
                this.f1721e = a;
                this.f1718b = i;
            }
        }

        boolean m2659a(K k, int i, V v, V v2) {
            lock();
            try {
                m2684o();
                AtomicReferenceArray atomicReferenceArray = this.f1721e;
                int length = i & (atomicReferenceArray.length() - 1);
                MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) atomicReferenceArray.get(length);
                for (MapMakerInternalMap mapMakerInternalMap2 = mapMakerInternalMap; mapMakerInternalMap2 != null; mapMakerInternalMap2 = mapMakerInternalMap2.m2552b()) {
                    Object d = mapMakerInternalMap2.m2556d();
                    if (mapMakerInternalMap2.m2554c() == i && d != null && this.f1717a.f1771e.m1771a(k, d)) {
                        MapMakerInternalMap a = mapMakerInternalMap2.m2548a();
                        Object obj = a.get();
                        if (obj == null) {
                            if (m2657a(a)) {
                                int i2 = this.f1718b - 1;
                                this.f1719c++;
                                m2653a(d, i, obj, MapMaker.COLLECTED);
                                mapMakerInternalMap = m2661b(mapMakerInternalMap, mapMakerInternalMap2);
                                int i3 = this.f1718b - 1;
                                atomicReferenceArray.set(length, mapMakerInternalMap);
                                this.f1718b = i3;
                            }
                            unlock();
                            m2685p();
                            return false;
                        } else if (this.f1717a.f1772f.m1771a(v, obj)) {
                            this.f1719c++;
                            m2653a((Object) k, i, obj, MapMaker.REPLACED);
                            m2652a(mapMakerInternalMap2, (Object) v2);
                            unlock();
                            m2685p();
                            return true;
                        } else {
                            m2664b(mapMakerInternalMap2);
                            unlock();
                            m2685p();
                            return false;
                        }
                    }
                }
                unlock();
                m2685p();
                return false;
            } catch (Throwable th) {
                unlock();
                m2685p();
            }
        }

        V m2645a(K k, int i, V v) {
            lock();
            try {
                m2684o();
                AtomicReferenceArray atomicReferenceArray = this.f1721e;
                int length = i & (atomicReferenceArray.length() - 1);
                MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) atomicReferenceArray.get(length);
                for (MapMakerInternalMap mapMakerInternalMap2 = mapMakerInternalMap; mapMakerInternalMap2 != null; mapMakerInternalMap2 = mapMakerInternalMap2.m2552b()) {
                    Object d = mapMakerInternalMap2.m2556d();
                    if (mapMakerInternalMap2.m2554c() == i && d != null && this.f1717a.f1771e.m1771a(k, d)) {
                        MapMakerInternalMap a = mapMakerInternalMap2.m2548a();
                        V v2 = a.get();
                        if (v2 == null) {
                            if (m2657a(a)) {
                                int i2 = this.f1718b - 1;
                                this.f1719c++;
                                m2653a(d, i, (Object) v2, MapMaker.COLLECTED);
                                int i3 = this.f1718b - 1;
                                atomicReferenceArray.set(length, m2661b(mapMakerInternalMap, mapMakerInternalMap2));
                                this.f1718b = i3;
                            }
                            unlock();
                            m2685p();
                            return null;
                        }
                        this.f1719c++;
                        m2653a((Object) k, i, (Object) v2, MapMaker.REPLACED);
                        m2652a(mapMakerInternalMap2, (Object) v);
                        unlock();
                        m2685p();
                        return v2;
                    }
                }
                unlock();
                m2685p();
                return null;
            } catch (Throwable th) {
                unlock();
                m2685p();
            }
        }

        V m2673e(Object obj, int i) {
            lock();
            try {
                m2684o();
                int i2 = this.f1718b - 1;
                AtomicReferenceArray atomicReferenceArray = this.f1721e;
                int length = i & (atomicReferenceArray.length() - 1);
                MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) atomicReferenceArray.get(length);
                for (MapMakerInternalMap mapMakerInternalMap2 = mapMakerInternalMap; mapMakerInternalMap2 != null; mapMakerInternalMap2 = mapMakerInternalMap2.m2552b()) {
                    Object d = mapMakerInternalMap2.m2556d();
                    V c = mapMakerInternalMap2.m2554c();
                    if (c == i && d != null) {
                        c = this.f1717a.f1771e.m1771a(obj, d);
                        if (c != null) {
                            MapMaker mapMaker;
                            MapMakerInternalMap a = mapMakerInternalMap2.m2548a();
                            c = a.get();
                            if (c != null) {
                                mapMaker = MapMaker.EXPLICIT;
                            } else if (m2657a(a)) {
                                mapMaker = MapMaker.COLLECTED;
                            } else {
                                unlock();
                                m2685p();
                                return null;
                            }
                            this.f1719c++;
                            m2653a(d, i, (Object) c, mapMaker);
                            int i3 = this.f1718b - 1;
                            atomicReferenceArray.set(length, m2661b(mapMakerInternalMap, mapMakerInternalMap2));
                            this.f1718b = i3;
                            return c;
                        }
                    }
                }
                unlock();
                m2685p();
                return null;
            } finally {
                unlock();
                m2685p();
            }
        }

        boolean m2665b(Object obj, int i, Object obj2) {
            lock();
            try {
                m2684o();
                int i2 = this.f1718b - 1;
                AtomicReferenceArray atomicReferenceArray = this.f1721e;
                int length = i & (atomicReferenceArray.length() - 1);
                MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) atomicReferenceArray.get(length);
                for (MapMakerInternalMap mapMakerInternalMap2 = mapMakerInternalMap; mapMakerInternalMap2 != null; mapMakerInternalMap2 = mapMakerInternalMap2.m2552b()) {
                    Object d = mapMakerInternalMap2.m2556d();
                    if (mapMakerInternalMap2.m2554c() == i && d != null && this.f1717a.f1771e.m1771a(obj, d)) {
                        MapMaker mapMaker;
                        MapMakerInternalMap a = mapMakerInternalMap2.m2548a();
                        Object obj3 = a.get();
                        if (this.f1717a.f1772f.m1771a(obj2, obj3)) {
                            mapMaker = MapMaker.EXPLICIT;
                        } else if (m2657a(a)) {
                            mapMaker = MapMaker.COLLECTED;
                        } else {
                            unlock();
                            m2685p();
                            return false;
                        }
                        this.f1719c++;
                        m2653a(d, i, obj3, mapMaker);
                        mapMakerInternalMap = m2661b(mapMakerInternalMap, mapMakerInternalMap2);
                        int i3 = this.f1718b - 1;
                        atomicReferenceArray.set(length, mapMakerInternalMap);
                        this.f1718b = i3;
                        boolean z = mapMaker == MapMaker.EXPLICIT;
                        unlock();
                        m2685p();
                        return z;
                    }
                }
                unlock();
                m2685p();
                return false;
            } catch (Throwable th) {
                unlock();
                m2685p();
            }
        }

        void m2682m() {
            if (this.f1718b != 0) {
                lock();
                try {
                    AtomicReferenceArray atomicReferenceArray = this.f1721e;
                    if (this.f1717a.f1778l != at.f1765q) {
                        for (int i = 0; i < atomicReferenceArray.length(); i++) {
                            for (MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) atomicReferenceArray.get(i); mapMakerInternalMap != null; mapMakerInternalMap = mapMakerInternalMap.m2552b()) {
                                if (!mapMakerInternalMap.m2548a().m2543b()) {
                                    m2651a(mapMakerInternalMap, MapMaker.EXPLICIT);
                                }
                            }
                        }
                    }
                    for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                        atomicReferenceArray.set(i2, null);
                    }
                    m2674e();
                    this.f1727k.clear();
                    this.f1728l.clear();
                    this.f1726j.set(0);
                    this.f1719c++;
                    this.f1718b = 0;
                } finally {
                    unlock();
                    m2685p();
                }
            }
        }

        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> m2661b(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2) {
            this.f1727k.remove(mapMakerInternalMap2);
            this.f1728l.remove(mapMakerInternalMap2);
            int i = this.f1718b;
            MapMakerInternalMap<K, V> b = mapMakerInternalMap2.m2552b();
            MapMakerInternalMap b2;
            while (b2 != mapMakerInternalMap2) {
                int i2;
                MapMakerInternalMap<K, V> a = m2642a(b2, (MapMakerInternalMap) b);
                if (a != null) {
                    i2 = i;
                } else {
                    m2670d(b2);
                    MapMakerInternalMap<K, V> mapMakerInternalMap3 = b;
                    i2 = i - 1;
                    a = mapMakerInternalMap3;
                }
                b2 = b2.m2552b();
                i = i2;
                b = a;
            }
            this.f1718b = i;
            return b;
        }

        void m2670d(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            m2651a((MapMakerInternalMap) mapMakerInternalMap, MapMaker.COLLECTED);
            this.f1727k.remove(mapMakerInternalMap);
            this.f1728l.remove(mapMakerInternalMap);
        }

        boolean m2655a(MapMakerInternalMap<K, V> mapMakerInternalMap, int i) {
            lock();
            try {
                int i2 = this.f1718b - 1;
                AtomicReferenceArray atomicReferenceArray = this.f1721e;
                int length = i & (atomicReferenceArray.length() - 1);
                MapMakerInternalMap mapMakerInternalMap2 = (MapMakerInternalMap) atomicReferenceArray.get(length);
                for (MapMakerInternalMap mapMakerInternalMap3 = mapMakerInternalMap2; mapMakerInternalMap3 != null; mapMakerInternalMap3 = mapMakerInternalMap3.m2552b()) {
                    if (mapMakerInternalMap3 == mapMakerInternalMap) {
                        this.f1719c++;
                        m2653a(mapMakerInternalMap3.m2556d(), i, mapMakerInternalMap3.m2548a().get(), MapMaker.COLLECTED);
                        mapMakerInternalMap2 = m2661b(mapMakerInternalMap2, mapMakerInternalMap3);
                        int i3 = this.f1718b - 1;
                        atomicReferenceArray.set(length, mapMakerInternalMap2);
                        this.f1718b = i3;
                        return true;
                    }
                }
                unlock();
                m2685p();
                return false;
            } finally {
                unlock();
                m2685p();
            }
        }

        boolean m2658a(K k, int i, MapMakerInternalMap<K, V> mapMakerInternalMap) {
            boolean z = false;
            lock();
            try {
                int i2 = this.f1718b - 1;
                AtomicReferenceArray atomicReferenceArray = this.f1721e;
                int length = i & (atomicReferenceArray.length() - 1);
                MapMakerInternalMap mapMakerInternalMap2 = (MapMakerInternalMap) atomicReferenceArray.get(length);
                MapMakerInternalMap mapMakerInternalMap3 = mapMakerInternalMap2;
                while (mapMakerInternalMap3 != null) {
                    Object d = mapMakerInternalMap3.m2556d();
                    if (mapMakerInternalMap3.m2554c() != i || d == null || !this.f1717a.f1771e.m1771a(k, d)) {
                        mapMakerInternalMap3 = mapMakerInternalMap3.m2552b();
                    } else if (mapMakerInternalMap3.m2548a() != mapMakerInternalMap) {
                        return z;
                    } else {
                        this.f1719c++;
                        m2653a((Object) k, i, mapMakerInternalMap.get(), MapMaker.COLLECTED);
                        int i3 = this.f1718b - 1;
                        atomicReferenceArray.set(length, m2661b(mapMakerInternalMap2, mapMakerInternalMap3));
                        this.f1718b = i3;
                        unlock();
                        if (isHeldByCurrentThread()) {
                            return true;
                        }
                        m2685p();
                        return true;
                    }
                }
                unlock();
                if (!isHeldByCurrentThread()) {
                    m2685p();
                }
                return false;
            } finally {
                unlock();
                z = isHeldByCurrentThread();
                if (!z) {
                    m2685p();
                }
            }
        }

        @GuardedBy("Segment.this")
        boolean m2656a(MapMakerInternalMap<K, V> mapMakerInternalMap, int i, MapMaker mapMaker) {
            int i2 = this.f1718b - 1;
            AtomicReferenceArray atomicReferenceArray = this.f1721e;
            int length = i & (atomicReferenceArray.length() - 1);
            MapMakerInternalMap mapMakerInternalMap2 = (MapMakerInternalMap) atomicReferenceArray.get(length);
            for (MapMakerInternalMap mapMakerInternalMap3 = mapMakerInternalMap2; mapMakerInternalMap3 != null; mapMakerInternalMap3 = mapMakerInternalMap3.m2552b()) {
                if (mapMakerInternalMap3 == mapMakerInternalMap) {
                    this.f1719c++;
                    m2653a(mapMakerInternalMap3.m2556d(), i, mapMakerInternalMap3.m2548a().get(), mapMaker);
                    mapMakerInternalMap2 = m2661b(mapMakerInternalMap2, mapMakerInternalMap3);
                    int i3 = this.f1718b - 1;
                    atomicReferenceArray.set(length, mapMakerInternalMap2);
                    this.f1718b = i3;
                    return true;
                }
            }
            return false;
        }

        boolean m2657a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            if (!mapMakerInternalMap.m2543b() && mapMakerInternalMap.get() == null) {
                return true;
            }
            return false;
        }

        V m2672e(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            if (mapMakerInternalMap.m2556d() == null) {
                m2648a();
                return null;
            }
            V v = mapMakerInternalMap.m2548a().get();
            if (v == null) {
                m2648a();
                return null;
            } else if (!this.f1717a.m2791b() || !this.f1717a.m2793c((MapMakerInternalMap) mapMakerInternalMap)) {
                return v;
            } else {
                m2678i();
                return null;
            }
        }

        void m2683n() {
            if ((this.f1726j.incrementAndGet() & 63) == 0) {
                m2686q();
            }
        }

        @GuardedBy("Segment.this")
        void m2684o() {
            m2687r();
        }

        void m2685p() {
            m2688s();
        }

        void m2686q() {
            m2687r();
            m2688s();
        }

        void m2687r() {
            if (tryLock()) {
                try {
                    m2663b();
                    m2679j();
                    this.f1726j.set(0);
                } finally {
                    unlock();
                }
            }
        }

        void m2688s() {
            if (!isHeldByCurrentThread()) {
                this.f1717a.m2798j();
            }
        }
    }

    /* renamed from: com.google.a.c.at.m */
    static final class MapMakerInternalMap<K, V> extends SoftReference<V> implements MapMakerInternalMap<K, V> {
        final MapMakerInternalMap<K, V> f1729a;

        MapMakerInternalMap(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap<K, V> mapMakerInternalMap) {
            super(v, referenceQueue);
            this.f1729a = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2689a() {
            return this.f1729a;
        }

        public void m2691a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            clear();
        }

        public MapMakerInternalMap<K, V> m2690a(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap<K, V> mapMakerInternalMap) {
            return new MapMakerInternalMap(referenceQueue, v, mapMakerInternalMap);
        }

        public boolean m2692b() {
            return false;
        }
    }

    /* renamed from: com.google.a.c.at.n */
    enum MapMakerInternalMap {
        STRONG {
            <K, V> MapMakerInternalMap<K, V> m2696a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2, V v) {
                return new MapMakerInternalMap(v);
            }

            Equivalence<Object> m2695a() {
                return Equivalence.m1768a();
            }
        },
        SOFT {
            <K, V> MapMakerInternalMap<K, V> m2698a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2, V v) {
                return new MapMakerInternalMap(mapMakerInternalMap.f1724h, v, mapMakerInternalMap2);
            }

            Equivalence<Object> m2697a() {
                return Equivalence.m1769b();
            }
        },
        WEAK {
            <K, V> MapMakerInternalMap<K, V> m2700a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2, V v) {
                return new aa(mapMakerInternalMap.f1724h, v, mapMakerInternalMap2);
            }

            Equivalence<Object> m2699a() {
                return Equivalence.m1769b();
            }
        };

        abstract Equivalence<Object> m2693a();

        abstract <K, V> MapMakerInternalMap<K, V> m2694a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2, V v);
    }

    /* renamed from: com.google.a.c.at.o */
    static class MapMakerInternalMap<K, V> implements MapMakerInternalMap<K, V> {
        final K f1734a;
        final int f1735b;
        final MapMakerInternalMap<K, V> f1736c;
        volatile MapMakerInternalMap<K, V> f1737d;

        MapMakerInternalMap(K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1737d = at.m2780g();
            this.f1734a = k;
            this.f1735b = i;
            this.f1736c = mapMakerInternalMap;
        }

        public K m2709d() {
            return this.f1734a;
        }

        public long m2711e() {
            throw new UnsupportedOperationException();
        }

        public void m2702a(long j) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2712f() {
            throw new UnsupportedOperationException();
        }

        public void m2703a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2713g() {
            throw new UnsupportedOperationException();
        }

        public void m2706b(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2714h() {
            throw new UnsupportedOperationException();
        }

        public void m2708c(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2715i() {
            throw new UnsupportedOperationException();
        }

        public void m2710d(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2701a() {
            return this.f1737d;
        }

        public void m2704a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            MapMakerInternalMap mapMakerInternalMap2 = this.f1737d;
            this.f1737d = mapMakerInternalMap;
            mapMakerInternalMap2.m2542a(mapMakerInternalMap);
        }

        public int m2707c() {
            return this.f1735b;
        }

        public MapMakerInternalMap<K, V> m2705b() {
            return this.f1736c;
        }
    }

    /* renamed from: com.google.a.c.at.p */
    static final class MapMakerInternalMap<K, V> extends MapMakerInternalMap<K, V> implements MapMakerInternalMap<K, V> {
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1738e;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1739f;

        MapMakerInternalMap(K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap) {
            super(k, i, mapMakerInternalMap);
            this.f1738e = at.m2781h();
            this.f1739f = at.m2781h();
        }

        public MapMakerInternalMap<K, V> m2718h() {
            return this.f1738e;
        }

        public void m2716c(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1738e = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2719i() {
            return this.f1739f;
        }

        public void m2717d(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1739f = mapMakerInternalMap;
        }
    }

    /* renamed from: com.google.a.c.at.q */
    static final class MapMakerInternalMap<K, V> extends MapMakerInternalMap<K, V> implements MapMakerInternalMap<K, V> {
        volatile long f1740e;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1741f;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1742g;

        MapMakerInternalMap(K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap) {
            super(k, i, mapMakerInternalMap);
            this.f1740e = Long.MAX_VALUE;
            this.f1741f = at.m2781h();
            this.f1742g = at.m2781h();
        }

        public long m2723e() {
            return this.f1740e;
        }

        public void m2720a(long j) {
            this.f1740e = j;
        }

        public MapMakerInternalMap<K, V> m2724f() {
            return this.f1741f;
        }

        public void m2721a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1741f = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2725g() {
            return this.f1742g;
        }

        public void m2722b(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1742g = mapMakerInternalMap;
        }
    }

    /* renamed from: com.google.a.c.at.r */
    static final class MapMakerInternalMap<K, V> extends MapMakerInternalMap<K, V> implements MapMakerInternalMap<K, V> {
        volatile long f1743e;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1744f;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1745g;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1746h;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1747i;

        MapMakerInternalMap(K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap) {
            super(k, i, mapMakerInternalMap);
            this.f1743e = Long.MAX_VALUE;
            this.f1744f = at.m2781h();
            this.f1745g = at.m2781h();
            this.f1746h = at.m2781h();
            this.f1747i = at.m2781h();
        }

        public long m2731e() {
            return this.f1743e;
        }

        public void m2726a(long j) {
            this.f1743e = j;
        }

        public MapMakerInternalMap<K, V> m2732f() {
            return this.f1744f;
        }

        public void m2727a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1744f = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2733g() {
            return this.f1745g;
        }

        public void m2728b(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1745g = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2734h() {
            return this.f1746h;
        }

        public void m2729c(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1746h = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2735i() {
            return this.f1747i;
        }

        public void m2730d(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1747i = mapMakerInternalMap;
        }
    }

    /* renamed from: com.google.a.c.at.s */
    static final class MapMakerInternalMap<K, V> implements MapMakerInternalMap<K, V> {
        final V f1748a;

        MapMakerInternalMap(V v) {
            this.f1748a = v;
        }

        public V get() {
            return this.f1748a;
        }

        public MapMakerInternalMap<K, V> m2736a() {
            return null;
        }

        public MapMakerInternalMap<K, V> m2737a(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap<K, V> mapMakerInternalMap) {
            return this;
        }

        public boolean m2739b() {
            return false;
        }

        public void m2738a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
        }
    }

    /* renamed from: com.google.a.c.at.t */
    final class MapMakerInternalMap extends MapMakerInternalMap<V> {
        final /* synthetic */ at f1749a;

        MapMakerInternalMap(at atVar) {
            this.f1749a = atVar;
            super(atVar);
        }

        public V next() {
            return m2605e().getValue();
        }
    }

    /* renamed from: com.google.a.c.at.v */
    final class MapMakerInternalMap extends AbstractCollection<V> {
        final /* synthetic */ at f1750a;

        MapMakerInternalMap(at atVar) {
            this.f1750a = atVar;
        }

        public Iterator<V> iterator() {
            return new MapMakerInternalMap(this.f1750a);
        }

        public int size() {
            return this.f1750a.size();
        }

        public boolean isEmpty() {
            return this.f1750a.isEmpty();
        }

        public boolean contains(Object obj) {
            return this.f1750a.containsValue(obj);
        }

        public void clear() {
            this.f1750a.clear();
        }
    }

    /* renamed from: com.google.a.c.at.w */
    static class MapMakerInternalMap<K, V> extends WeakReference<K> implements MapMakerInternalMap<K, V> {
        final int f1751a;
        final MapMakerInternalMap<K, V> f1752b;
        volatile MapMakerInternalMap<K, V> f1753c;

        MapMakerInternalMap(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap) {
            super(k, referenceQueue);
            this.f1753c = at.m2780g();
            this.f1751a = i;
            this.f1752b = mapMakerInternalMap;
        }

        public K m2748d() {
            return get();
        }

        public long m2750e() {
            throw new UnsupportedOperationException();
        }

        public void m2741a(long j) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2751f() {
            throw new UnsupportedOperationException();
        }

        public void m2742a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2752g() {
            throw new UnsupportedOperationException();
        }

        public void m2745b(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2753h() {
            throw new UnsupportedOperationException();
        }

        public void m2747c(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2754i() {
            throw new UnsupportedOperationException();
        }

        public void m2749d(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            throw new UnsupportedOperationException();
        }

        public MapMakerInternalMap<K, V> m2740a() {
            return this.f1753c;
        }

        public void m2743a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            MapMakerInternalMap mapMakerInternalMap2 = this.f1753c;
            this.f1753c = mapMakerInternalMap;
            mapMakerInternalMap2.m2542a(mapMakerInternalMap);
        }

        public int m2746c() {
            return this.f1751a;
        }

        public MapMakerInternalMap<K, V> m2744b() {
            return this.f1752b;
        }
    }

    /* renamed from: com.google.a.c.at.x */
    static final class MapMakerInternalMap<K, V> extends MapMakerInternalMap<K, V> implements MapMakerInternalMap<K, V> {
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1754d;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1755e;

        MapMakerInternalMap(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap) {
            super(referenceQueue, k, i, mapMakerInternalMap);
            this.f1754d = at.m2781h();
            this.f1755e = at.m2781h();
        }

        public MapMakerInternalMap<K, V> m2757h() {
            return this.f1754d;
        }

        public void m2755c(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1754d = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2758i() {
            return this.f1755e;
        }

        public void m2756d(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1755e = mapMakerInternalMap;
        }
    }

    /* renamed from: com.google.a.c.at.y */
    static final class MapMakerInternalMap<K, V> extends MapMakerInternalMap<K, V> implements MapMakerInternalMap<K, V> {
        volatile long f1756d;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1757e;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1758f;

        MapMakerInternalMap(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap) {
            super(referenceQueue, k, i, mapMakerInternalMap);
            this.f1756d = Long.MAX_VALUE;
            this.f1757e = at.m2781h();
            this.f1758f = at.m2781h();
        }

        public long m2762e() {
            return this.f1756d;
        }

        public void m2759a(long j) {
            this.f1756d = j;
        }

        public MapMakerInternalMap<K, V> m2763f() {
            return this.f1757e;
        }

        public void m2760a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1757e = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2764g() {
            return this.f1758f;
        }

        public void m2761b(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1758f = mapMakerInternalMap;
        }
    }

    /* renamed from: com.google.a.c.at.z */
    static final class MapMakerInternalMap<K, V> extends MapMakerInternalMap<K, V> implements MapMakerInternalMap<K, V> {
        volatile long f1759d;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1760e;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1761f;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1762g;
        @GuardedBy("Segment.this")
        MapMakerInternalMap<K, V> f1763h;

        MapMakerInternalMap(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable MapMakerInternalMap<K, V> mapMakerInternalMap) {
            super(referenceQueue, k, i, mapMakerInternalMap);
            this.f1759d = Long.MAX_VALUE;
            this.f1760e = at.m2781h();
            this.f1761f = at.m2781h();
            this.f1762g = at.m2781h();
            this.f1763h = at.m2781h();
        }

        public long m2770e() {
            return this.f1759d;
        }

        public void m2765a(long j) {
            this.f1759d = j;
        }

        public MapMakerInternalMap<K, V> m2771f() {
            return this.f1760e;
        }

        public void m2766a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1760e = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2772g() {
            return this.f1761f;
        }

        public void m2767b(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1761f = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2773h() {
            return this.f1762g;
        }

        public void m2768c(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1762g = mapMakerInternalMap;
        }

        public MapMakerInternalMap<K, V> m2774i() {
            return this.f1763h;
        }

        public void m2769d(MapMakerInternalMap<K, V> mapMakerInternalMap) {
            this.f1763h = mapMakerInternalMap;
        }
    }

    static {
        f1766u = Logger.getLogger(at.class.getName());
        f1764p = new MapMakerInternalMap();
        f1765q = new MapMakerInternalMap();
    }

    at(as asVar) {
        Queue i;
        int i2 = 1;
        int i3 = 0;
        this.f1770d = Math.min(asVar.m2533d(), AccessibilityNodeInfoCompat.ACTION_CUT);
        this.f1773g = asVar.m2534e();
        this.f1774h = asVar.m2535f();
        this.f1771e = asVar.m2531b();
        this.f1772f = this.f1774h.m2693a();
        this.f1775i = asVar.f1671e;
        this.f1776j = asVar.m2537h();
        this.f1777k = asVar.m2536g();
        this.f1780n = MapMakerInternalMap.m2582a(this.f1773g, m2791b(), m2787a());
        this.f1781o = asVar.m2538i();
        this.f1779m = asVar.m2530a();
        if (this.f1779m == GenericMapMaker.INSTANCE) {
            i = at.m2782i();
        } else {
            i = new ConcurrentLinkedQueue();
        }
        this.f1778l = i;
        int min = Math.min(asVar.m2532c(), 1073741824);
        if (m2787a()) {
            min = Math.min(min, this.f1775i);
        }
        int i4 = 1;
        int i5 = 0;
        while (i4 < this.f1770d && (!m2787a() || i4 * 2 <= this.f1775i)) {
            i5++;
            i4 <<= 1;
        }
        this.f1768b = 32 - i5;
        this.f1767a = i4 - 1;
        this.f1769c = m2794c(i4);
        i5 = min / i4;
        if (i5 * i4 < min) {
            min = i5 + 1;
        } else {
            min = i5;
        }
        while (i2 < min) {
            i2 <<= 1;
        }
        if (m2787a()) {
            min = (this.f1775i / i4) + 1;
            i4 = this.f1775i % i4;
            while (i3 < this.f1769c.length) {
                if (i3 == i4) {
                    min--;
                }
                this.f1769c[i3] = m2784a(i2, min);
                i3++;
            }
            return;
        }
        while (i3 < this.f1769c.length) {
            this.f1769c[i3] = m2784a(i2, -1);
            i3++;
        }
    }

    boolean m2787a() {
        return this.f1775i != -1;
    }

    boolean m2791b() {
        return m2792c() || m2795d();
    }

    boolean m2792c() {
        return this.f1777k > 0;
    }

    boolean m2795d() {
        return this.f1776j > 0;
    }

    boolean m2796e() {
        return this.f1773g != MapMakerInternalMap.STRONG;
    }

    boolean m2797f() {
        return this.f1774h != MapMakerInternalMap.STRONG;
    }

    static <K, V> MapMakerInternalMap<K, V> m2780g() {
        return f1764p;
    }

    static <K, V> MapMakerInternalMap<K, V> m2781h() {
        return MapMakerInternalMap.INSTANCE;
    }

    static <E> Queue<E> m2782i() {
        return f1765q;
    }

    static int m2775a(int i) {
        int i2 = ((i << 15) ^ -12931) + i;
        i2 ^= i2 >>> 10;
        i2 += i2 << 3;
        i2 ^= i2 >>> 6;
        i2 += (i2 << 2) + (i2 << 14);
        return i2 ^ (i2 >>> 16);
    }

    int m2783a(Object obj) {
        return at.m2775a(this.f1771e.m1770a(obj));
    }

    void m2786a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
        MapMakerInternalMap a = mapMakerInternalMap.m2540a();
        int c = a.m2554c();
        m2789b(c).m2658a(a.m2556d(), c, (MapMakerInternalMap) mapMakerInternalMap);
    }

    void m2785a(MapMakerInternalMap<K, V> mapMakerInternalMap) {
        int c = mapMakerInternalMap.m2554c();
        m2789b(c).m2655a((MapMakerInternalMap) mapMakerInternalMap, c);
    }

    MapMakerInternalMap<K, V> m2789b(int i) {
        return this.f1769c[(i >>> this.f1768b) & this.f1767a];
    }

    MapMakerInternalMap<K, V> m2784a(int i, int i2) {
        return new MapMakerInternalMap(this, i, i2);
    }

    V m2790b(MapMakerInternalMap<K, V> mapMakerInternalMap) {
        if (mapMakerInternalMap.m2556d() == null) {
            return null;
        }
        V v = mapMakerInternalMap.m2548a().get();
        if (v == null) {
            return null;
        }
        if (m2791b() && m2793c((MapMakerInternalMap) mapMakerInternalMap)) {
            return null;
        }
        return v;
    }

    boolean m2793c(MapMakerInternalMap<K, V> mapMakerInternalMap) {
        return m2788a((MapMakerInternalMap) mapMakerInternalMap, this.f1781o.m1871a());
    }

    boolean m2788a(MapMakerInternalMap<K, V> mapMakerInternalMap, long j) {
        return j - mapMakerInternalMap.m2558e() > 0;
    }

    @GuardedBy("Segment.this")
    static <K, V> void m2776a(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2) {
        mapMakerInternalMap.m2550a((MapMakerInternalMap) mapMakerInternalMap2);
        mapMakerInternalMap2.m2553b(mapMakerInternalMap);
    }

    @GuardedBy("Segment.this")
    static <K, V> void m2778d(MapMakerInternalMap<K, V> mapMakerInternalMap) {
        MapMakerInternalMap h = at.m2781h();
        mapMakerInternalMap.m2550a(h);
        mapMakerInternalMap.m2553b(h);
    }

    void m2798j() {
        while (true) {
            MapMaker mapMaker = (MapMaker) this.f1778l.poll();
            if (mapMaker != null) {
                try {
                    this.f1779m.m2529a(mapMaker);
                } catch (Throwable e) {
                    f1766u.log(Level.WARNING, "Exception thrown by removal listener", e);
                }
            } else {
                return;
            }
        }
    }

    @GuardedBy("Segment.this")
    static <K, V> void m2777b(MapMakerInternalMap<K, V> mapMakerInternalMap, MapMakerInternalMap<K, V> mapMakerInternalMap2) {
        mapMakerInternalMap.m2555c(mapMakerInternalMap2);
        mapMakerInternalMap2.m2557d(mapMakerInternalMap);
    }

    @GuardedBy("Segment.this")
    static <K, V> void m2779e(MapMakerInternalMap<K, V> mapMakerInternalMap) {
        MapMakerInternalMap h = at.m2781h();
        mapMakerInternalMap.m2555c(h);
        mapMakerInternalMap.m2557d(h);
    }

    final MapMakerInternalMap<K, V>[] m2794c(int i) {
        return new MapMakerInternalMap[i];
    }

    public boolean isEmpty() {
        int i;
        MapMakerInternalMap[] mapMakerInternalMapArr = this.f1769c;
        long j = 0;
        for (i = 0; i < mapMakerInternalMapArr.length; i++) {
            if (mapMakerInternalMapArr[i].f1718b != 0) {
                return false;
            }
            j += (long) mapMakerInternalMapArr[i].f1719c;
        }
        if (j != 0) {
            for (i = 0; i < mapMakerInternalMapArr.length; i++) {
                if (mapMakerInternalMapArr[i].f1718b != 0) {
                    return false;
                }
                j -= (long) mapMakerInternalMapArr[i].f1719c;
            }
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        long j = 0;
        for (MapMakerInternalMap mapMakerInternalMap : this.f1769c) {
            j += (long) mapMakerInternalMap.f1718b;
        }
        return Ints.m3009a(j);
    }

    public V get(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int a = m2783a(obj);
        return m2789b(a).m2666c(obj, a);
    }

    public boolean containsKey(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        int a = m2783a(obj);
        return m2789b(a).m2671d(obj, a);
    }

    public boolean containsValue(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        MapMakerInternalMap[] mapMakerInternalMapArr = this.f1769c;
        int i = 0;
        long j = -1;
        while (i < 3) {
            long j2 = 0;
            for (MapMakerInternalMap mapMakerInternalMap : mapMakerInternalMapArr) {
                int i2 = mapMakerInternalMap.f1718b;
                AtomicReferenceArray atomicReferenceArray = mapMakerInternalMap.f1721e;
                for (int i3 = 0; i3 < atomicReferenceArray.length(); i3++) {
                    for (MapMakerInternalMap mapMakerInternalMap2 = (MapMakerInternalMap) atomicReferenceArray.get(i3); mapMakerInternalMap2 != null; mapMakerInternalMap2 = mapMakerInternalMap2.m2552b()) {
                        Object e = mapMakerInternalMap.m2672e(mapMakerInternalMap2);
                        if (e != null && this.f1772f.m1771a(obj, e)) {
                            return true;
                        }
                    }
                }
                j2 += (long) mapMakerInternalMap.f1719c;
            }
            if (j2 == j) {
                break;
            }
            i++;
            j = j2;
        }
        return false;
    }

    public V put(K k, V v) {
        Preconditions.m1817a((Object) k);
        Preconditions.m1817a((Object) v);
        int a = m2783a((Object) k);
        return m2789b(a).m2646a((Object) k, a, (Object) v, false);
    }

    public V putIfAbsent(K k, V v) {
        Preconditions.m1817a((Object) k);
        Preconditions.m1817a((Object) v);
        int a = m2783a((Object) k);
        return m2789b(a).m2646a((Object) k, a, (Object) v, true);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public V remove(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int a = m2783a(obj);
        return m2789b(a).m2673e(obj, a);
    }

    public boolean remove(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int a = m2783a(obj);
        return m2789b(a).m2665b(obj, a, obj2);
    }

    public boolean replace(K k, @Nullable V v, V v2) {
        Preconditions.m1817a((Object) k);
        Preconditions.m1817a((Object) v2);
        if (v == null) {
            return false;
        }
        int a = m2783a((Object) k);
        return m2789b(a).m2659a((Object) k, a, (Object) v, (Object) v2);
    }

    public V replace(K k, V v) {
        Preconditions.m1817a((Object) k);
        Preconditions.m1817a((Object) v);
        int a = m2783a((Object) k);
        return m2789b(a).m2645a((Object) k, a, (Object) v);
    }

    public void clear() {
        for (MapMakerInternalMap m : this.f1769c) {
            m.m2682m();
        }
    }

    public Set<K> keySet() {
        Set<K> set = this.f1782r;
        if (set != null) {
            return set;
        }
        set = new MapMakerInternalMap(this);
        this.f1782r = set;
        return set;
    }

    public Collection<V> values() {
        Collection<V> collection = this.f1783s;
        if (collection != null) {
            return collection;
        }
        collection = new MapMakerInternalMap(this);
        this.f1783s = collection;
        return collection;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = this.f1784t;
        if (set != null) {
            return set;
        }
        set = new MapMakerInternalMap(this);
        this.f1784t = set;
        return set;
    }
}
