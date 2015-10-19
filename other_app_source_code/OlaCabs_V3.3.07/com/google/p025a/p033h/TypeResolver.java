package com.google.p025a.p033h;

import com.google.p025a.p026a.Joiner;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ad;
import com.google.p025a.p028c.ad.ImmutableMap;
import com.google.p025a.p028c.au;
import com.google.p025a.p028c.bk;
import com.google.p025a.p033h.Types.Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

/* renamed from: com.google.a.h.d */
class TypeResolver {
    private final ad<TypeVariable<?>, Type> f1909a;

    /* renamed from: com.google.a.h.d.1 */
    class TypeResolver extends TypeResolver {
        final /* synthetic */ TypeVariable f1910a;
        final /* synthetic */ TypeResolver f1911b;
        final /* synthetic */ TypeResolver f1912c;

        TypeResolver(TypeResolver typeResolver, ad adVar, TypeVariable typeVariable, TypeResolver typeResolver2) {
            this.f1912c = typeResolver;
            this.f1910a = typeVariable;
            this.f1911b = typeResolver2;
            super(null);
        }

        Type m3027a(TypeVariable<?> typeVariable, TypeResolver typeResolver) {
            return typeVariable.getGenericDeclaration().equals(this.f1910a.getGenericDeclaration()) ? typeVariable : this.f1911b.m3025a(typeVariable, typeResolver);
        }
    }

    /* renamed from: com.google.a.h.d.a */
    private static final class TypeResolver {
        private static final TypeResolver f1913a;
        private final Map<TypeVariable<?>, Type> f1914b;
        private final Set<Type> f1915c;

        private TypeResolver() {
            this.f1914b = au.m2807a();
            this.f1915c = bk.m2870a();
        }

        static {
            f1913a = new TypeResolver();
        }

        static ad<TypeVariable<?>, Type> m3028a(Type type) {
            TypeResolver typeResolver = new TypeResolver();
            typeResolver.m3032b(f1913a.m3035a(type));
            return ad.m2307a(typeResolver.f1914b);
        }

        private void m3032b(Type type) {
            int i = 0;
            if (!this.f1915c.add(type)) {
                return;
            }
            if (type instanceof ParameterizedType) {
                m3030a((ParameterizedType) type);
            } else if (type instanceof Class) {
                m3029a((Class) type);
            } else if (type instanceof TypeVariable) {
                r1 = ((TypeVariable) type).getBounds();
                r2 = r1.length;
                while (i < r2) {
                    m3032b(r1[i]);
                    i++;
                }
            } else if (type instanceof WildcardType) {
                r1 = ((WildcardType) type).getUpperBounds();
                r2 = r1.length;
                while (i < r2) {
                    m3032b(r1[i]);
                    i++;
                }
            }
        }

        private void m3029a(Class<?> cls) {
            m3032b(cls.getGenericSuperclass());
            for (Type b : cls.getGenericInterfaces()) {
                m3032b(b);
            }
        }

        private void m3030a(ParameterizedType parameterizedType) {
            boolean z;
            int i = 0;
            Class cls = (Class) parameterizedType.getRawType();
            TypeVariable[] typeParameters = cls.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (typeParameters.length == actualTypeArguments.length) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.m1828b(z);
            while (i < typeParameters.length) {
                m3031a(typeParameters[i], actualTypeArguments[i]);
                i++;
            }
            m3029a(cls);
            m3032b(parameterizedType.getOwnerType());
        }

        private void m3031a(TypeVariable<?> typeVariable, Type type) {
            if (!this.f1914b.containsKey(typeVariable)) {
                Object obj = type;
                while (obj != null) {
                    if (typeVariable.equals(obj)) {
                        while (type != null) {
                            type = (Type) this.f1914b.remove(type);
                        }
                        return;
                    }
                    Type type2 = (Type) this.f1914b.get(obj);
                }
                this.f1914b.put(typeVariable, type);
            }
        }
    }

    /* renamed from: com.google.a.h.d.b */
    private static final class TypeResolver {
        private final AtomicInteger f1916a;

        private TypeResolver() {
            this.f1916a = new AtomicInteger();
        }

        Type m3035a(Type type) {
            Preconditions.m1817a((Object) type);
            if ((type instanceof Class) || (type instanceof TypeVariable)) {
                return type;
            }
            if (type instanceof GenericArrayType) {
                return Types.m3098a(m3035a(((GenericArrayType) type).getGenericComponentType()));
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                return Types.m3097a(m3034b(parameterizedType.getOwnerType()), (Class) parameterizedType.getRawType(), m3033a(parameterizedType.getActualTypeArguments()));
            } else if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                if (wildcardType.getLowerBounds().length != 0) {
                    return type;
                }
                return Types.m3099a((GenericDeclaration) TypeResolver.class, "capture#" + this.f1916a.incrementAndGet() + "-of ? extends " + Joiner.m1779a('&').m1788a(wildcardType.getUpperBounds()), wildcardType.getUpperBounds());
            } else {
                throw new AssertionError("must have been one of the known types");
            }
        }

        private Type m3034b(@Nullable Type type) {
            if (type == null) {
                return null;
            }
            return m3035a(type);
        }

        private Type[] m3033a(Type[] typeArr) {
            Type[] typeArr2 = new Type[typeArr.length];
            for (int i = 0; i < typeArr.length; i++) {
                typeArr2[i] = m3035a(typeArr[i]);
            }
            return typeArr2;
        }
    }

    public TypeResolver() {
        this.f1909a = ad.m2310i();
    }

    private TypeResolver(ad<TypeVariable<?>, Type> adVar) {
        this.f1909a = adVar;
    }

    static TypeResolver m3019a(Type type) {
        return new TypeResolver().m3024a(TypeResolver.m3028a(type));
    }

    final TypeResolver m3024a(Map<? extends TypeVariable<?>, ? extends Type> map) {
        ImmutableMap j = ad.m2311j();
        j.m2363a(this.f1909a);
        for (Entry entry : map.entrySet()) {
            boolean z;
            TypeVariable typeVariable = (TypeVariable) entry.getKey();
            Type type = (Type) entry.getValue();
            if (typeVariable.equals(type)) {
                z = false;
            } else {
                z = true;
            }
            Preconditions.m1824a(z, "Type variable %s bound to itself", typeVariable);
            j.m2362a(typeVariable, type);
        }
        return new TypeResolver(j.m2364a());
    }

    public final Type m3026b(Type type) {
        Preconditions.m1817a((Object) type);
        if (type instanceof TypeVariable) {
            return m3022a((TypeVariable) type);
        }
        if (type instanceof ParameterizedType) {
            return m3020a((ParameterizedType) type);
        }
        if (type instanceof GenericArrayType) {
            return m3021a((GenericArrayType) type);
        }
        if (!(type instanceof WildcardType)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        return new Types(m3023a(wildcardType.getLowerBounds()), m3023a(wildcardType.getUpperBounds()));
    }

    private Type[] m3023a(Type[] typeArr) {
        Type[] typeArr2 = new Type[typeArr.length];
        for (int i = 0; i < typeArr.length; i++) {
            typeArr2[i] = m3026b(typeArr[i]);
        }
        return typeArr2;
    }

    private Type m3021a(GenericArrayType genericArrayType) {
        return Types.m3098a(m3026b(genericArrayType.getGenericComponentType()));
    }

    private Type m3022a(TypeVariable<?> typeVariable) {
        return m3025a(typeVariable, new TypeResolver(this, this.f1909a, typeVariable, this));
    }

    Type m3025a(TypeVariable<?> typeVariable, TypeResolver typeResolver) {
        Preconditions.m1817a((Object) typeResolver);
        Type type = (Type) this.f1909a.get(typeVariable);
        if (type != null) {
            return typeResolver.m3026b(type);
        }
        Type[] bounds = typeVariable.getBounds();
        if (bounds.length == 0) {
            return typeVariable;
        }
        return Types.m3099a(typeVariable.getGenericDeclaration(), typeVariable.getName(), typeResolver.m3023a(bounds));
    }

    private ParameterizedType m3020a(ParameterizedType parameterizedType) {
        Type ownerType = parameterizedType.getOwnerType();
        Type b = ownerType == null ? null : m3026b(ownerType);
        ownerType = m3026b(parameterizedType.getRawType());
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Type[] typeArr = new Type[actualTypeArguments.length];
        for (int i = 0; i < actualTypeArguments.length; i++) {
            typeArr[i] = m3026b(actualTypeArguments[i]);
        }
        return Types.m3097a(b, (Class) ownerType, typeArr);
    }
}
