package com.google.gson.p064b;

import com.google.gson.b.C$Gson$Preconditions;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.gson.b.i */
public final class Primitives {
    private static final Map<Class<?>, Class<?>> f8456a;
    private static final Map<Class<?>, Class<?>> f8457b;

    static {
        Map hashMap = new HashMap(16);
        Map hashMap2 = new HashMap(16);
        Primitives.m12284a(hashMap, hashMap2, Boolean.TYPE, Boolean.class);
        Primitives.m12284a(hashMap, hashMap2, Byte.TYPE, Byte.class);
        Primitives.m12284a(hashMap, hashMap2, Character.TYPE, Character.class);
        Primitives.m12284a(hashMap, hashMap2, Double.TYPE, Double.class);
        Primitives.m12284a(hashMap, hashMap2, Float.TYPE, Float.class);
        Primitives.m12284a(hashMap, hashMap2, Integer.TYPE, Integer.class);
        Primitives.m12284a(hashMap, hashMap2, Long.TYPE, Long.class);
        Primitives.m12284a(hashMap, hashMap2, Short.TYPE, Short.class);
        Primitives.m12284a(hashMap, hashMap2, Void.TYPE, Void.class);
        f8456a = Collections.unmodifiableMap(hashMap);
        f8457b = Collections.unmodifiableMap(hashMap2);
    }

    private static void m12284a(Map<Class<?>, Class<?>> map, Map<Class<?>, Class<?>> map2, Class<?> cls, Class<?> cls2) {
        map.put(cls, cls2);
        map2.put(cls2, cls);
    }

    public static boolean m12285a(Type type) {
        return f8456a.containsKey(type);
    }

    public static <T> Class<T> m12283a(Class<T> cls) {
        Class<T> cls2 = (Class) f8456a.get(C$Gson$Preconditions.m12214a((Object) cls));
        return cls2 == null ? cls : cls2;
    }
}
