package com.google.p025a.p029d;

import com.google.p025a.p027b.CacheBuilder;
import com.google.p025a.p027b.CacheLoader;
import com.google.p025a.p027b.LoadingCache;
import com.google.p025a.p033h.TypeToken;
import java.util.Set;

/* renamed from: com.google.a.d.a */
public class EventBus {
    private static final LoadingCache<Class<?>, Set<Class<?>>> f1871a;

    /* renamed from: com.google.a.d.a.1 */
    static class EventBus extends CacheLoader<Class<?>, Set<Class<?>>> {
        EventBus() {
        }

        public Set<Class<?>> m2977a(Class<?> cls) {
            return TypeToken.m3038a((Class) cls).m3051e().m3078d();
        }
    }

    static {
        f1871a = CacheBuilder.m1895a().m1906h().m1899a(new EventBus());
    }
}
