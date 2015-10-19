package com.google.p025a.p033h;

import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p026a.Predicate;
import com.google.p025a.p028c.FluentIterable;
import com.google.p025a.p028c.ForwardingSet;
import com.google.p025a.p028c.ac;
import com.google.p025a.p028c.ac.ImmutableList;
import com.google.p025a.p028c.ai;
import com.google.p025a.p028c.ai.ImmutableSet;
import com.google.p025a.p028c.au;
import com.google.p025a.p028c.az;
import com.google.p025a.p033h.Invokable.Invokable;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: com.google.a.h.e */
public abstract class TypeToken<T> extends TypeCapture<T> implements Serializable {
    private final Type f1917a;
    private transient TypeResolver f1918b;

    /* renamed from: com.google.a.h.e.1 */
    class TypeToken extends Invokable<T> {
    }

    /* renamed from: com.google.a.h.e.a */
    private static final class TypeToken<T> extends TypeToken<T> {
        TypeToken(Type type) {
            super(null);
        }
    }

    /* renamed from: com.google.a.h.e.b */
    private static abstract class TypeToken<K> {
        static final TypeToken<TypeToken<?>> f1919a;
        static final TypeToken<Class<?>> f1920b;

        /* renamed from: com.google.a.h.e.b.1 */
        static class TypeToken extends TypeToken<TypeToken<?>> {
            TypeToken() {
                super();
            }

            /* synthetic */ Class m3060b(Object obj) {
                return m3059a((TypeToken) obj);
            }

            /* synthetic */ Iterable m3063c(Object obj) {
                return m3061b((TypeToken) obj);
            }

            /* synthetic */ Object m3064d(Object obj) {
                return m3062c((TypeToken) obj);
            }

            Class<?> m3059a(TypeToken<?> typeToken) {
                return typeToken.m3048b();
            }

            Iterable<? extends TypeToken<?>> m3061b(TypeToken<?> typeToken) {
                return typeToken.m3050d();
            }

            @Nullable
            TypeToken<?> m3062c(TypeToken<?> typeToken) {
                return typeToken.m3049c();
            }
        }

        /* renamed from: com.google.a.h.e.b.2 */
        static class TypeToken extends TypeToken<Class<?>> {
            TypeToken() {
                super();
            }

            /* synthetic */ Class m3066b(Object obj) {
                return m3065a((Class) obj);
            }

            /* synthetic */ Iterable m3069c(Object obj) {
                return m3067b((Class) obj);
            }

            /* synthetic */ Object m3070d(Object obj) {
                return m3068c((Class) obj);
            }

            Class<?> m3065a(Class<?> cls) {
                return cls;
            }

            Iterable<? extends Class<?>> m3067b(Class<?> cls) {
                List arrayList = new ArrayList();
                for (Object add : cls.getInterfaces()) {
                    arrayList.add(add);
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Nullable
            Class<?> m3068c(Class<?> cls) {
                return cls.getSuperclass();
            }
        }

        /* renamed from: com.google.a.h.e.b.3 */
        static class TypeToken extends az<K> {
            final /* synthetic */ Comparator f1921a;
            final /* synthetic */ Map f1922b;

            TypeToken(Comparator comparator, Map map) {
                this.f1921a = comparator;
                this.f1922b = map;
            }

            public int compare(K k, K k2) {
                return this.f1921a.compare(this.f1922b.get(k), this.f1922b.get(k2));
            }
        }

        abstract Class<?> m3056b(K k);

        abstract Iterable<? extends K> m3057c(K k);

        @Nullable
        abstract K m3058d(K k);

        private TypeToken() {
        }

        static {
            f1919a = new TypeToken();
            f1920b = new TypeToken();
        }

        final ac<K> m3055a(K k) {
            return m3054a(ac.m2339a((Object) k));
        }

        ac<K> m3054a(Iterable<? extends K> iterable) {
            Map a = au.m2807a();
            for (Object a2 : iterable) {
                m3052a(a2, a);
            }
            return TypeToken.m3053a(a, az.m2824b().m2826a());
        }

        private int m3052a(K k, Map<? super K, Integer> map) {
            Integer num = (Integer) map.get(this);
            if (num != null) {
                return num.intValue();
            }
            int i = m3056b(k).isInterface() ? 1 : 0;
            for (Object a : m3057c(k)) {
                i = Math.max(i, m3052a(a, (Map) map));
            }
            Object d = m3058d(k);
            if (d != null) {
                i = Math.max(i, m3052a(d, (Map) map));
            }
            map.put(k, Integer.valueOf(i + 1));
            return i + 1;
        }

        private static <K, V> ac<K> m3053a(Map<K, V> map, Comparator<? super V> comparator) {
            return new TypeToken(comparator, map).m2825a(map.keySet());
        }
    }

    /* renamed from: com.google.a.h.e.c */
    private enum TypeToken implements Predicate<TypeToken<?>> {
        IGNORE_TYPE_VARIABLE_OR_WILDCARD {
            public boolean m3071a(TypeToken<?> typeToken) {
                return ((typeToken.f1917a instanceof TypeVariable) || (typeToken.f1917a instanceof WildcardType)) ? false : true;
            }
        },
        INTERFACE_ONLY {
            public boolean m3073a(TypeToken<?> typeToken) {
                return typeToken.m3048b().isInterface();
            }
        };
    }

    /* renamed from: com.google.a.h.e.d */
    public class TypeToken extends ForwardingSet<TypeToken<? super T>> implements Serializable {
        final /* synthetic */ TypeToken f1926a;
        private transient ai<TypeToken<? super T>> f1927b;

        protected /* synthetic */ Collection m3075a() {
            return m3077c();
        }

        protected /* synthetic */ Object m3076b() {
            return m3077c();
        }

        TypeToken(TypeToken typeToken) {
            this.f1926a = typeToken;
        }

        protected Set<TypeToken<? super T>> m3077c() {
            Set<TypeToken<? super T>> set = this.f1927b;
            if (set != null) {
                return set;
            }
            Set a = FluentIterable.m2479a(TypeToken.f1919a.m3055a(this.f1926a)).m2481a(TypeToken.IGNORE_TYPE_VARIABLE_OR_WILDCARD).m2480a();
            this.f1927b = a;
            return a;
        }

        public Set<Class<? super T>> m3078d() {
            return ai.m2291a(TypeToken.f1920b.m3054a(this.f1926a.m3045f()));
        }
    }

    protected TypeToken() {
        this.f1917a = m3018a();
        Preconditions.m1830b(!(this.f1917a instanceof TypeVariable), "Cannot construct a TypeToken for a type variable.\nYou probably meant to call new TypeToken<%s>(getClass()) that can resolve the type variable for you.\nIf you do need to create a TypeToken of a type variable, please use TypeToken.of() instead.", this.f1917a);
    }

    private TypeToken(Type type) {
        this.f1917a = (Type) Preconditions.m1817a((Object) type);
    }

    public static <T> TypeToken<T> m3038a(Class<T> cls) {
        return new TypeToken(cls);
    }

    public static TypeToken<?> m3039a(Type type) {
        return new TypeToken(type);
    }

    public final Class<? super T> m3048b() {
        return TypeToken.m3042c(this.f1917a);
    }

    private ai<Class<? super T>> m3045f() {
        return TypeToken.m3043d(this.f1917a);
    }

    public final TypeToken<?> m3047b(Type type) {
        Preconditions.m1817a((Object) type);
        TypeResolver typeResolver = this.f1918b;
        if (typeResolver == null) {
            typeResolver = TypeResolver.m3019a(this.f1917a);
            this.f1918b = typeResolver;
        }
        return TypeToken.m3039a(typeResolver.m3026b(type));
    }

    private TypeToken<?> m3044e(Type type) {
        TypeToken<?> b = m3047b(type);
        b.f1918b = this.f1918b;
        return b;
    }

    @Nullable
    final TypeToken<? super T> m3049c() {
        if (this.f1917a instanceof TypeVariable) {
            return m3046f(((TypeVariable) this.f1917a).getBounds()[0]);
        }
        if (this.f1917a instanceof WildcardType) {
            return m3046f(((WildcardType) this.f1917a).getUpperBounds()[0]);
        }
        Type genericSuperclass = m3048b().getGenericSuperclass();
        if (genericSuperclass == null) {
            return null;
        }
        return m3044e(genericSuperclass);
    }

    @Nullable
    private TypeToken<? super T> m3046f(Type type) {
        TypeToken<? super T> a = TypeToken.m3039a(type);
        if (a.m3048b().isInterface()) {
            return null;
        }
        return a;
    }

    final ac<TypeToken<? super T>> m3050d() {
        if (this.f1917a instanceof TypeVariable) {
            return m3036a(((TypeVariable) this.f1917a).getBounds());
        }
        if (this.f1917a instanceof WildcardType) {
            return m3036a(((WildcardType) this.f1917a).getUpperBounds());
        }
        ImmutableList h = ac.m2346h();
        for (Type e : m3048b().getGenericInterfaces()) {
            h.m2337b(m3044e(e));
        }
        return h.m2332a();
    }

    private ac<TypeToken<? super T>> m3036a(Type[] typeArr) {
        ImmutableList h = ac.m2346h();
        for (Type a : typeArr) {
            Object a2 = TypeToken.m3039a(a);
            if (a2.m3048b().isInterface()) {
                h.m2337b(a2);
            }
        }
        return h.m2332a();
    }

    public final TypeToken m3051e() {
        return new TypeToken(this);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof TypeToken)) {
            return false;
        }
        return this.f1917a.equals(((TypeToken) obj).f1917a);
    }

    public int hashCode() {
        return this.f1917a.hashCode();
    }

    public String toString() {
        return Types.m3108d(this.f1917a);
    }

    static Class<?> m3042c(Type type) {
        return (Class) TypeToken.m3043d(type).m2298b().next();
    }

    static ai<Class<?>> m3043d(Type type) {
        if (type instanceof Class) {
            return ai.m2294b((Class) type);
        }
        if (type instanceof ParameterizedType) {
            return ai.m2294b((Class) ((ParameterizedType) type).getRawType());
        }
        if (type instanceof GenericArrayType) {
            return ai.m2294b(Types.m3094a(TypeToken.m3042c(((GenericArrayType) type).getGenericComponentType())));
        }
        if (type instanceof TypeVariable) {
            return TypeToken.m3040b(((TypeVariable) type).getBounds());
        }
        if (type instanceof WildcardType) {
            return TypeToken.m3040b(((WildcardType) type).getUpperBounds());
        }
        throw new AssertionError(type + " unsupported");
    }

    private static ai<Class<?>> m3040b(Type[] typeArr) {
        ImmutableSet h = ai.m2297h();
        for (Type d : typeArr) {
            h.m2429b(TypeToken.m3043d(d));
        }
        return h.m2425a();
    }
}
