package com.google.gson.b;

import com.google.gson.b.C$Gson$Preconditions;
import com.google.gson.b.C$Gson$Types;
import com.google.gson.p064b.$Gson$Types$.Gson.Types;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

/* renamed from: com.google.gson.b.b */
public final class C$Gson$Types {
    static final Type[] f8394a;

    /* renamed from: com.google.gson.b.b.a */
    private static final class Types implements Serializable, GenericArrayType {
        private final Type f8388a;

        public Types(Type type) {
            this.f8388a = C$Gson$Types.m12232d(type);
        }

        public Type getGenericComponentType() {
            return this.f8388a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && C$Gson$Types.m12226a((Type) this, (GenericArrayType) obj);
        }

        public int hashCode() {
            return this.f8388a.hashCode();
        }

        public String toString() {
            return C$Gson$Types.m12234f(this.f8388a) + "[]";
        }
    }

    /* renamed from: com.google.gson.b.b.b */
    private static final class Types implements Serializable, ParameterizedType {
        private final Type f8389a;
        private final Type f8390b;
        private final Type[] f8391c;

        public Types(Type type, Type type2, Type... typeArr) {
            int i = 0;
            if (type2 instanceof Class) {
                boolean z;
                Class cls = (Class) type2;
                int i2 = (Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null) ? 1 : 0;
                if (type == null && i2 == 0) {
                    z = false;
                } else {
                    z = true;
                }
                C$Gson$Preconditions.m12215a(z);
            }
            this.f8389a = type == null ? null : C$Gson$Types.m12232d(type);
            this.f8390b = C$Gson$Types.m12232d(type2);
            this.f8391c = (Type[]) typeArr.clone();
            while (i < this.f8391c.length) {
                C$Gson$Preconditions.m12214a(this.f8391c[i]);
                C$Gson$Types.m12237i(this.f8391c[i]);
                this.f8391c[i] = C$Gson$Types.m12232d(this.f8391c[i]);
                i++;
            }
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.f8391c.clone();
        }

        public Type getRawType() {
            return this.f8390b;
        }

        public Type getOwnerType() {
            return this.f8389a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && C$Gson$Types.m12226a((Type) this, (ParameterizedType) obj);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.f8391c) ^ this.f8390b.hashCode()) ^ C$Gson$Types.m12227b(this.f8389a);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder((this.f8391c.length + 1) * 30);
            stringBuilder.append(C$Gson$Types.m12234f(this.f8390b));
            if (this.f8391c.length == 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append("<").append(C$Gson$Types.m12234f(this.f8391c[0]));
            for (int i = 1; i < this.f8391c.length; i++) {
                stringBuilder.append(", ").append(C$Gson$Types.m12234f(this.f8391c[i]));
            }
            return stringBuilder.append(">").toString();
        }
    }

    /* renamed from: com.google.gson.b.b.c */
    private static final class Types implements Serializable, WildcardType {
        private final Type f8392a;
        private final Type f8393b;

        public Types(Type[] typeArr, Type[] typeArr2) {
            boolean z;
            boolean z2 = true;
            C$Gson$Preconditions.m12215a(typeArr2.length <= 1);
            if (typeArr.length == 1) {
                z = true;
            } else {
                z = false;
            }
            C$Gson$Preconditions.m12215a(z);
            if (typeArr2.length == 1) {
                C$Gson$Preconditions.m12214a(typeArr2[0]);
                C$Gson$Types.m12237i(typeArr2[0]);
                if (typeArr[0] != Object.class) {
                    z2 = false;
                }
                C$Gson$Preconditions.m12215a(z2);
                this.f8393b = C$Gson$Types.m12232d(typeArr2[0]);
                this.f8392a = Object.class;
                return;
            }
            C$Gson$Preconditions.m12214a(typeArr[0]);
            C$Gson$Types.m12237i(typeArr[0]);
            this.f8393b = null;
            this.f8392a = C$Gson$Types.m12232d(typeArr[0]);
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.f8392a};
        }

        public Type[] getLowerBounds() {
            if (this.f8393b == null) {
                return C$Gson$Types.f8394a;
            }
            return new Type[]{this.f8393b};
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && C$Gson$Types.m12226a((Type) this, (WildcardType) obj);
        }

        public int hashCode() {
            return (this.f8393b != null ? this.f8393b.hashCode() + 31 : 1) ^ (this.f8392a.hashCode() + 31);
        }

        public String toString() {
            if (this.f8393b != null) {
                return "? super " + C$Gson$Types.m12234f(this.f8393b);
            }
            if (this.f8392a == Object.class) {
                return "?";
            }
            return "? extends " + C$Gson$Types.m12234f(this.f8392a);
        }
    }

    static {
        f8394a = new Type[0];
    }

    public static ParameterizedType m12220a(Type type, Type type2, Type... typeArr) {
        return new Types(type, type2, typeArr);
    }

    public static GenericArrayType m12219a(Type type) {
        return new Types(type);
    }

    public static WildcardType m12229b(Type type) {
        return new Types(new Type[]{type}, f8394a);
    }

    public static WildcardType m12231c(Type type) {
        return new Types(new Type[]{Object.class}, new Type[]{type});
    }

    public static Type m12232d(Type type) {
        if (type instanceof Class) {
            Types types;
            Class cls = (Class) type;
            if (cls.isArray()) {
                types = new Types(C$Gson$Types.m12232d(cls.getComponentType()));
            } else {
                Object obj = cls;
            }
            return types;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new Types(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new Types(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new Types(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    public static Class<?> m12233e(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            C$Gson$Preconditions.m12215a(rawType instanceof Class);
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(C$Gson$Types.m12233e(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return C$Gson$Types.m12233e(((WildcardType) type).getUpperBounds()[0]);
            }
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
        }
    }

    static boolean m12225a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean m12226a(Type type, Type type2) {
        boolean z = true;
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            if (!(C$Gson$Types.m12225a(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments()))) {
                z = false;
            }
            return z;
        } else if (type instanceof GenericArrayType) {
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return C$Gson$Types.m12226a(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
        } else if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            if (!(Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds()))) {
                z = false;
            }
            return z;
        } else if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (!(typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName()))) {
                z = false;
            }
            return z;
        }
    }

    private static int m12227b(Object obj) {
        return obj != null ? obj.hashCode() : 0;
    }

    public static String m12234f(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    static Type m12222a(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(interfaces[i])) {
                    return C$Gson$Types.m12222a(cls.getGenericInterfaces()[i], interfaces[i], (Class) cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return C$Gson$Types.m12222a(cls.getGenericSuperclass(), (Class) superclass, (Class) cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    static Type m12228b(Type type, Class<?> cls, Class<?> cls2) {
        C$Gson$Preconditions.m12215a(cls2.isAssignableFrom(cls));
        return C$Gson$Types.m12223a(type, (Class) cls, C$Gson$Types.m12222a(type, (Class) cls, (Class) cls2));
    }

    public static Type m12235g(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    public static Type m12221a(Type type, Class<?> cls) {
        Type b = C$Gson$Types.m12228b(type, cls, Collection.class);
        if (b instanceof WildcardType) {
            b = ((WildcardType) b).getUpperBounds()[0];
        }
        if (b instanceof ParameterizedType) {
            return ((ParameterizedType) b).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static Type[] m12230b(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type b = C$Gson$Types.m12228b(type, cls, Map.class);
        if (b instanceof ParameterizedType) {
            return ((ParameterizedType) b).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    public static Type m12223a(Type type, Class<?> cls, Type type2) {
        Type type3 = type2;
        while (type3 instanceof TypeVariable) {
            type3 = (TypeVariable) type3;
            type2 = C$Gson$Types.m12224a(type, (Class) cls, (TypeVariable) type3);
            if (type2 == type3) {
                return type2;
            }
            type3 = type2;
        }
        Type componentType;
        Type a;
        if ((type3 instanceof Class) && ((Class) type3).isArray()) {
            Class cls2 = (Class) type3;
            componentType = cls2.getComponentType();
            a = C$Gson$Types.m12223a(type, (Class) cls, componentType);
            if (componentType != a) {
                return C$Gson$Types.m12219a(a);
            }
            return cls2;
        } else if (type3 instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type3;
            componentType = genericArrayType.getGenericComponentType();
            a = C$Gson$Types.m12223a(type, (Class) cls, componentType);
            if (componentType != a) {
                return C$Gson$Types.m12219a(a);
            }
            return genericArrayType;
        } else if (type3 instanceof ParameterizedType) {
            int i;
            ParameterizedType parameterizedType = (ParameterizedType) type3;
            componentType = parameterizedType.getOwnerType();
            Type a2 = C$Gson$Types.m12223a(type, (Class) cls, componentType);
            if (a2 != componentType) {
                i = 1;
            } else {
                i = 0;
            }
            r4 = parameterizedType.getActualTypeArguments();
            int length = r4.length;
            int i2 = i;
            r1 = r4;
            for (int i3 = 0; i3 < length; i3++) {
                Type a3 = C$Gson$Types.m12223a(type, (Class) cls, r1[i3]);
                if (a3 != r1[i3]) {
                    if (i2 == 0) {
                        r1 = (Type[]) r1.clone();
                        i2 = 1;
                    }
                    r1[i3] = a3;
                }
            }
            if (i2 != 0) {
                return C$Gson$Types.m12220a(a2, parameterizedType.getRawType(), r1);
            }
            return parameterizedType;
        } else if (!(type3 instanceof WildcardType)) {
            return type3;
        } else {
            WildcardType wildcardType = (WildcardType) type3;
            r1 = wildcardType.getLowerBounds();
            r4 = wildcardType.getUpperBounds();
            if (r1.length == 1) {
                a = C$Gson$Types.m12223a(type, (Class) cls, r1[0]);
                if (a != r1[0]) {
                    return C$Gson$Types.m12231c(a);
                }
                return wildcardType;
            } else if (r4.length != 1) {
                return wildcardType;
            } else {
                componentType = C$Gson$Types.m12223a(type, (Class) cls, r4[0]);
                if (componentType != r4[0]) {
                    return C$Gson$Types.m12229b(componentType);
                }
                return wildcardType;
            }
        }
    }

    static Type m12224a(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class a = C$Gson$Types.m12218a((TypeVariable) typeVariable);
        if (a == null) {
            return typeVariable;
        }
        Type a2 = C$Gson$Types.m12222a(type, (Class) cls, a);
        if (!(a2 instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) a2).getActualTypeArguments()[C$Gson$Types.m12217a(a.getTypeParameters(), (Object) typeVariable)];
    }

    private static int m12217a(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private static Class<?> m12218a(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        return genericDeclaration instanceof Class ? (Class) genericDeclaration : null;
    }

    private static void m12237i(Type type) {
        boolean z = ((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true;
        C$Gson$Preconditions.m12215a(z);
    }
}
