package com.google.p025a.p033h;

import com.google.p025a.p026a.Function;
import com.google.p025a.p026a.Joiner;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p026a.Predicates;
import com.google.p025a.p028c.ac;
import com.google.p025a.p028c.ac.ImmutableList;
import com.google.p025a.p028c.ap;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nullable;

/* renamed from: com.google.a.h.f */
final class Types {
    private static final Function<Type, String> f1945a;
    private static final Joiner f1946b;

    /* renamed from: com.google.a.h.f.1 */
    static class Types implements Function<Type, String> {
        Types() {
        }

        public String m3080a(Type type) {
            return Types.m3108d(type);
        }
    }

    /* renamed from: com.google.a.h.f.a */
    private enum Types {
        OWNED_BY_ENCLOSING_CLASS {
            @Nullable
            Class<?> m3083a(Class<?> cls) {
                return cls.getEnclosingClass();
            }
        },
        LOCAL_CLASS_HAS_NO_OWNER {
            @Nullable
            Class<?> m3084a(Class<?> cls) {
                if (cls.isLocalClass()) {
                    return null;
                }
                return cls.getEnclosingClass();
            }
        };
        
        static final Types f1930c;

        /* renamed from: com.google.a.h.f.a.a */
        class Types<T> {
            Types() {
            }
        }

        /* renamed from: com.google.a.h.f.a.3 */
        static class Types extends Types<String> {
            Types() {
            }
        }

        @Nullable
        abstract Class<?> m3082a(Class<?> cls);

        static {
            f1930c = Types.m3081a();
        }

        private static Types m3081a() {
            ParameterizedType parameterizedType = (ParameterizedType) new Types().getClass().getGenericSuperclass();
            for (Types types : Types.values()) {
                if (types.m3082a(Types.class) == parameterizedType.getOwnerType()) {
                    return types;
                }
            }
            throw new AssertionError();
        }
    }

    /* renamed from: com.google.a.h.f.b */
    private static final class Types implements Serializable, GenericArrayType {
        private final Type f1932a;

        Types(Type type) {
            this.f1932a = Types.f1935c.m3087b(type);
        }

        public Type getGenericComponentType() {
            return this.f1932a;
        }

        public String toString() {
            return Types.m3108d(this.f1932a) + "[]";
        }

        public int hashCode() {
            return this.f1932a.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof GenericArrayType)) {
                return false;
            }
            return Objects.m1811a(getGenericComponentType(), ((GenericArrayType) obj).getGenericComponentType());
        }
    }

    /* renamed from: com.google.a.h.f.c */
    enum Types {
        JAVA6 {
            /* synthetic */ Type m3088a(Type type) {
                return m3090c(type);
            }

            GenericArrayType m3090c(Type type) {
                return new Types(type);
            }

            Type m3089b(Type type) {
                Preconditions.m1817a((Object) type);
                if (!(type instanceof Class)) {
                    return type;
                }
                Class cls = (Class) type;
                if (cls.isArray()) {
                    return new Types(cls.getComponentType());
                }
                return type;
            }
        },
        JAVA7 {
            Type m3091a(Type type) {
                if (type instanceof Class) {
                    return Types.m3094a((Class) type);
                }
                return new Types(type);
            }

            Type m3092b(Type type) {
                return (Type) Preconditions.m1817a((Object) type);
            }
        };
        
        static final Types f1935c;

        /* renamed from: com.google.a.h.f.c.1 */
        static class Types extends TypeCapture<int[]> {
            Types() {
            }
        }

        abstract Type m3086a(Type type);

        abstract Type m3087b(Type type);

        final ac<Type> m3085a(Type[] typeArr) {
            ImmutableList h = ac.m2346h();
            for (Type b : typeArr) {
                h.m2337b(m3087b(b));
            }
            return h.m2332a();
        }
    }

    /* renamed from: com.google.a.h.f.d */
    private static final class Types implements Serializable, ParameterizedType {
        private final Type f1937a;
        private final ac<Type> f1938b;
        private final Class<?> f1939c;

        Types(@Nullable Type type, Class<?> cls, Type[] typeArr) {
            Preconditions.m1817a((Object) cls);
            Preconditions.m1822a(typeArr.length == cls.getTypeParameters().length);
            Types.m3105b(typeArr, "type parameter");
            this.f1937a = type;
            this.f1939c = cls;
            this.f1938b = Types.f1935c.m3085a(typeArr);
        }

        public Type[] getActualTypeArguments() {
            return Types.m3106b(this.f1938b);
        }

        public Type getRawType() {
            return this.f1939c;
        }

        public Type getOwnerType() {
            return this.f1937a;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.f1937a != null) {
                stringBuilder.append(Types.m3108d(this.f1937a)).append('.');
            }
            stringBuilder.append(this.f1939c.getName()).append('<').append(Types.f1946b.m1785a(ap.m2482a(this.f1938b, Types.f1945a))).append('>');
            return stringBuilder.toString();
        }

        public int hashCode() {
            return ((this.f1937a == null ? 0 : this.f1937a.hashCode()) ^ this.f1938b.hashCode()) ^ this.f1939c.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) obj;
            if (getRawType().equals(parameterizedType.getRawType()) && Objects.m1811a(getOwnerType(), parameterizedType.getOwnerType()) && Arrays.equals(getActualTypeArguments(), parameterizedType.getActualTypeArguments())) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: com.google.a.h.f.e */
    private static final class Types<D extends GenericDeclaration> implements TypeVariable<D> {
        private final D f1940a;
        private final String f1941b;
        private final ac<Type> f1942c;

        Types(D d, String str, Type[] typeArr) {
            Types.m3105b(typeArr, "bound for type variable");
            this.f1940a = (GenericDeclaration) Preconditions.m1817a((Object) d);
            this.f1941b = (String) Preconditions.m1817a((Object) str);
            this.f1942c = ac.m2341a((Object[]) typeArr);
        }

        public Type[] getBounds() {
            return Types.m3106b(this.f1942c);
        }

        public D getGenericDeclaration() {
            return this.f1940a;
        }

        public String getName() {
            return this.f1941b;
        }

        public String toString() {
            return this.f1941b;
        }

        public int hashCode() {
            return this.f1940a.hashCode() ^ this.f1941b.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof TypeVariable)) {
                return false;
            }
            TypeVariable typeVariable = (TypeVariable) obj;
            if (this.f1941b.equals(typeVariable.getName()) && this.f1940a.equals(typeVariable.getGenericDeclaration())) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: com.google.a.h.f.f */
    static final class Types implements Serializable, WildcardType {
        private final ac<Type> f1943a;
        private final ac<Type> f1944b;

        Types(Type[] typeArr, Type[] typeArr2) {
            Types.m3105b(typeArr, "lower bound for wildcard");
            Types.m3105b(typeArr2, "upper bound for wildcard");
            this.f1943a = Types.f1935c.m3085a(typeArr);
            this.f1944b = Types.f1935c.m3085a(typeArr2);
        }

        public Type[] getLowerBounds() {
            return Types.m3106b(this.f1943a);
        }

        public Type[] getUpperBounds() {
            return Types.m3106b(this.f1944b);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) obj;
            if (this.f1943a.equals(Arrays.asList(wildcardType.getLowerBounds())) && this.f1944b.equals(Arrays.asList(wildcardType.getUpperBounds()))) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.f1943a.hashCode() ^ this.f1944b.hashCode();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("?");
            Iterator it = this.f1943a.iterator();
            while (it.hasNext()) {
                stringBuilder.append(" super ").append(Types.m3108d((Type) it.next()));
            }
            for (Type d : Types.m3103b(this.f1944b)) {
                stringBuilder.append(" extends ").append(Types.m3108d(d));
            }
            return stringBuilder.toString();
        }
    }

    static {
        f1945a = new Types();
        f1946b = Joiner.m1780a(", ").m1791b("null");
    }

    static Type m3098a(Type type) {
        boolean z = true;
        if (!(type instanceof WildcardType)) {
            return Types.f1935c.m3086a(type);
        }
        WildcardType wildcardType = (WildcardType) type;
        Type[] lowerBounds = wildcardType.getLowerBounds();
        Preconditions.m1823a(lowerBounds.length <= 1, (Object) "Wildcard cannot have more than one lower bounds.");
        if (lowerBounds.length == 1) {
            return Types.m3107c(Types.m3098a(lowerBounds[0]));
        }
        Type[] upperBounds = wildcardType.getUpperBounds();
        if (upperBounds.length != 1) {
            z = false;
        }
        Preconditions.m1823a(z, (Object) "Wildcard should have only one upper bound.");
        return Types.m3104b(Types.m3098a(upperBounds[0]));
    }

    static ParameterizedType m3097a(@Nullable Type type, Class<?> cls, Type... typeArr) {
        if (type == null) {
            return Types.m3096a((Class) cls, typeArr);
        }
        boolean z;
        Preconditions.m1817a((Object) typeArr);
        if (cls.getEnclosingClass() != null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.m1824a(z, "Owner type for unenclosed %s", cls);
        return new Types(type, cls, typeArr);
    }

    static ParameterizedType m3096a(Class<?> cls, Type... typeArr) {
        return new Types(Types.f1930c.m3082a(cls), cls, typeArr);
    }

    static <D extends GenericDeclaration> TypeVariable<D> m3099a(D d, String str, Type... typeArr) {
        if (typeArr.length == 0) {
            typeArr = new Type[]{Object.class};
        }
        return new Types(d, str, typeArr);
    }

    static WildcardType m3104b(Type type) {
        return new Types(new Type[0], new Type[]{type});
    }

    static WildcardType m3107c(Type type) {
        return new Types(new Type[]{type}, new Type[]{Object.class});
    }

    static String m3108d(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    private static Type[] m3106b(Collection<Type> collection) {
        return (Type[]) collection.toArray(new Type[collection.size()]);
    }

    private static Iterable<Type> m3103b(Iterable<Type> iterable) {
        return ap.m2483a((Iterable) iterable, Predicates.m1841a(Predicates.m1842a((Object) Object.class)));
    }

    private static void m3105b(Type[] typeArr, String str) {
        for (Type type : typeArr) {
            if (type instanceof Class) {
                boolean z;
                if (((Class) type).isPrimitive()) {
                    z = false;
                } else {
                    z = true;
                }
                Preconditions.m1824a(z, "Primitive type '%s' used as %s", (Class) type, str);
            }
        }
    }

    static Class<?> m3094a(Class<?> cls) {
        return Array.newInstance(cls, 0).getClass();
    }
}
