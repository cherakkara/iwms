package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.annotation.Nullable;

/* compiled from: ImmutableEnumMap */
/* renamed from: com.google.a.c.aa */
final class aa<K extends Enum<K>, V> extends ad<K, V> {
    private final transient EnumMap<K, V> f1592a;

    /* renamed from: com.google.a.c.aa.1 */
    class ImmutableEnumMap extends ai<K> {
        final /* synthetic */ aa f1585a;

        ImmutableEnumMap(aa aaVar) {
            this.f1585a = aaVar;
        }

        public /* synthetic */ Iterator iterator() {
            return m2301b();
        }

        public boolean contains(Object obj) {
            return this.f1585a.f1592a.containsKey(obj);
        }

        public int size() {
            return this.f1585a.size();
        }

        public bs<K> m2301b() {
            return aq.m2498a(this.f1585a.f1592a.keySet().iterator());
        }

        boolean m2300a() {
            return true;
        }
    }

    /* renamed from: com.google.a.c.aa.2 */
    class ImmutableEnumMap extends ae<K, V> {
        final /* synthetic */ aa f1588a;

        /* renamed from: com.google.a.c.aa.2.1 */
        class ImmutableEnumMap extends bs<Entry<K, V>> {
            final /* synthetic */ ImmutableEnumMap f1586a;
            private final Iterator<Entry<K, V>> f1587b;

            ImmutableEnumMap(ImmutableEnumMap immutableEnumMap) {
                this.f1586a = immutableEnumMap;
                this.f1587b = this.f1586a.f1588a.f1592a.entrySet().iterator();
            }

            public /* synthetic */ Object next() {
                return m2302a();
            }

            public boolean hasNext() {
                return this.f1587b.hasNext();
            }

            public Entry<K, V> m2302a() {
                Entry entry = (Entry) this.f1587b.next();
                return au.m2811a(entry.getKey(), entry.getValue());
            }
        }

        ImmutableEnumMap(aa aaVar) {
            this.f1588a = aaVar;
        }

        public /* synthetic */ Iterator iterator() {
            return m2305b();
        }

        ad<K, V> m2306e() {
            return this.f1588a;
        }

        public bs<Entry<K, V>> m2305b() {
            return new ImmutableEnumMap(this);
        }
    }

    static <K extends Enum<K>, V> ad<K, V> m2318a(EnumMap<K, V> enumMap) {
        switch (enumMap.size()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return ad.m2310i();
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                Entry entry = (Entry) ap.m2486b(enumMap.entrySet());
                return ad.m2308b(entry.getKey(), entry.getValue());
            default:
                return new aa(enumMap);
        }
    }

    private aa(EnumMap<K, V> enumMap) {
        this.f1592a = enumMap;
        Preconditions.m1822a(!enumMap.isEmpty());
    }

    ai<K> m2320a() {
        return new ImmutableEnumMap(this);
    }

    public int size() {
        return this.f1592a.size();
    }

    public boolean containsKey(@Nullable Object obj) {
        return this.f1592a.containsKey(obj);
    }

    public V get(Object obj) {
        return this.f1592a.get(obj);
    }

    ai<Entry<K, V>> m2321c() {
        return new ImmutableEnumMap(this);
    }

    boolean m2322e() {
        return false;
    }
}
