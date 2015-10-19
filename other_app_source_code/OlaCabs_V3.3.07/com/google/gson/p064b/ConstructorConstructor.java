package com.google.gson.p064b;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.p066c.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/* renamed from: com.google.gson.b.c */
public final class ConstructorConstructor {
    private final Map<Type, InstanceCreator<?>> f8416a;

    /* renamed from: com.google.gson.b.c.1 */
    class ConstructorConstructor implements ObjectConstructor<T> {
        final /* synthetic */ InstanceCreator f8398a;
        final /* synthetic */ Type f8399b;
        final /* synthetic */ ConstructorConstructor f8400c;

        ConstructorConstructor(ConstructorConstructor constructorConstructor, InstanceCreator instanceCreator, Type type) {
            this.f8400c = constructorConstructor;
            this.f8398a = instanceCreator;
            this.f8399b = type;
        }

        public T m12242a() {
            return this.f8398a.m12356a(this.f8399b);
        }
    }

    /* renamed from: com.google.gson.b.c.2 */
    class ConstructorConstructor implements ObjectConstructor<T> {
        final /* synthetic */ ConstructorConstructor f8401a;

        ConstructorConstructor(ConstructorConstructor constructorConstructor) {
            this.f8401a = constructorConstructor;
        }

        public T m12243a() {
            return new LinkedHashMap();
        }
    }

    /* renamed from: com.google.gson.b.c.3 */
    class ConstructorConstructor implements ObjectConstructor<T> {
        final /* synthetic */ ConstructorConstructor f8402a;

        ConstructorConstructor(ConstructorConstructor constructorConstructor) {
            this.f8402a = constructorConstructor;
        }

        public T m12244a() {
            return new LinkedTreeMap();
        }
    }

    /* renamed from: com.google.gson.b.c.4 */
    class ConstructorConstructor implements ObjectConstructor<T> {
        final /* synthetic */ Class f8403a;
        final /* synthetic */ Type f8404b;
        final /* synthetic */ ConstructorConstructor f8405c;
        private final UnsafeAllocator f8406d;

        ConstructorConstructor(ConstructorConstructor constructorConstructor, Class cls, Type type) {
            this.f8405c = constructorConstructor;
            this.f8403a = cls;
            this.f8404b = type;
            this.f8406d = UnsafeAllocator.m12289a();
        }

        public T m12245a() {
            try {
                return this.f8406d.m12290a(this.f8403a);
            } catch (Throwable e) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.f8404b + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", e);
            }
        }
    }

    /* renamed from: com.google.gson.b.c.5 */
    class ConstructorConstructor implements ObjectConstructor<T> {
        final /* synthetic */ InstanceCreator f8407a;
        final /* synthetic */ Type f8408b;
        final /* synthetic */ ConstructorConstructor f8409c;

        ConstructorConstructor(ConstructorConstructor constructorConstructor, InstanceCreator instanceCreator, Type type) {
            this.f8409c = constructorConstructor;
            this.f8407a = instanceCreator;
            this.f8408b = type;
        }

        public T m12246a() {
            return this.f8407a.m12356a(this.f8408b);
        }
    }

    /* renamed from: com.google.gson.b.c.6 */
    class ConstructorConstructor implements ObjectConstructor<T> {
        final /* synthetic */ Constructor f8410a;
        final /* synthetic */ ConstructorConstructor f8411b;

        ConstructorConstructor(ConstructorConstructor constructorConstructor, Constructor constructor) {
            this.f8411b = constructorConstructor;
            this.f8410a = constructor;
        }

        public T m12247a() {
            try {
                return this.f8410a.newInstance(null);
            } catch (Throwable e) {
                throw new RuntimeException("Failed to invoke " + this.f8410a + " with no args", e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException("Failed to invoke " + this.f8410a + " with no args", e2.getTargetException());
            } catch (IllegalAccessException e3) {
                throw new AssertionError(e3);
            }
        }
    }

    /* renamed from: com.google.gson.b.c.7 */
    class ConstructorConstructor implements ObjectConstructor<T> {
        final /* synthetic */ ConstructorConstructor f8412a;

        ConstructorConstructor(ConstructorConstructor constructorConstructor) {
            this.f8412a = constructorConstructor;
        }

        public T m12248a() {
            return new TreeSet();
        }
    }

    /* renamed from: com.google.gson.b.c.8 */
    class ConstructorConstructor implements ObjectConstructor<T> {
        final /* synthetic */ Type f8413a;
        final /* synthetic */ ConstructorConstructor f8414b;

        ConstructorConstructor(ConstructorConstructor constructorConstructor, Type type) {
            this.f8414b = constructorConstructor;
            this.f8413a = type;
        }

        public T m12249a() {
            if (this.f8413a instanceof ParameterizedType) {
                Type type = ((ParameterizedType) this.f8413a).getActualTypeArguments()[0];
                if (type instanceof Class) {
                    return EnumSet.noneOf((Class) type);
                }
                throw new JsonIOException("Invalid EnumSet type: " + this.f8413a.toString());
            }
            throw new JsonIOException("Invalid EnumSet type: " + this.f8413a.toString());
        }
    }

    /* renamed from: com.google.gson.b.c.9 */
    class ConstructorConstructor implements ObjectConstructor<T> {
        final /* synthetic */ ConstructorConstructor f8415a;

        ConstructorConstructor(ConstructorConstructor constructorConstructor) {
            this.f8415a = constructorConstructor;
        }

        public T m12250a() {
            return new LinkedHashSet();
        }
    }

    public ConstructorConstructor(Map<Type, InstanceCreator<?>> map) {
        this.f8416a = map;
    }

    public <T> ObjectConstructor<T> m12254a(TypeToken<T> typeToken) {
        Type b = typeToken.m12301b();
        Class a = typeToken.m12300a();
        InstanceCreator instanceCreator = (InstanceCreator) this.f8416a.get(b);
        if (instanceCreator != null) {
            return new ConstructorConstructor(this, instanceCreator, b);
        }
        instanceCreator = (InstanceCreator) this.f8416a.get(a);
        if (instanceCreator != null) {
            return new ConstructorConstructor(this, instanceCreator, b);
        }
        ObjectConstructor<T> a2 = m12251a(a);
        if (a2 != null) {
            return a2;
        }
        a2 = m12252a(b, a);
        return a2 == null ? m12253b(b, a) : a2;
    }

    private <T> ObjectConstructor<T> m12251a(Class<? super T> cls) {
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new ConstructorConstructor(this, declaredConstructor);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private <T> ObjectConstructor<T> m12252a(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new ConstructorConstructor(this);
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new ConstructorConstructor(this, type);
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new ConstructorConstructor(this);
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new ObjectConstructor<T>() {
                    final /* synthetic */ com.google.gson.p064b.ConstructorConstructor f8395a;

                    {
                        this.f8395a = r1;
                    }

                    public T m12239a() {
                        return new LinkedList();
                    }
                };
            }
            return new ObjectConstructor<T>() {
                final /* synthetic */ com.google.gson.p064b.ConstructorConstructor f8396a;

                {
                    this.f8396a = r1;
                }

                public T m12240a() {
                    return new ArrayList();
                }
            };
        } else if (!Map.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (SortedMap.class.isAssignableFrom(cls)) {
                return new ObjectConstructor<T>() {
                    final /* synthetic */ com.google.gson.p064b.ConstructorConstructor f8397a;

                    {
                        this.f8397a = r1;
                    }

                    public T m12241a() {
                        return new TreeMap();
                    }
                };
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(TypeToken.m12297a(((ParameterizedType) type).getActualTypeArguments()[0]).m12300a())) {
                return new ConstructorConstructor(this);
            }
            return new ConstructorConstructor(this);
        }
    }

    private <T> ObjectConstructor<T> m12253b(Type type, Class<? super T> cls) {
        return new ConstructorConstructor(this, cls, type);
    }

    public String toString() {
        return this.f8416a.toString();
    }
}
