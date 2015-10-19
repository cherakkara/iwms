package com.google.gson.p064b;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* renamed from: com.google.gson.b.k */
public abstract class UnsafeAllocator {

    /* renamed from: com.google.gson.b.k.1 */
    static class UnsafeAllocator extends UnsafeAllocator {
        final /* synthetic */ Method f8461a;
        final /* synthetic */ Object f8462b;

        UnsafeAllocator(Method method, Object obj) {
            this.f8461a = method;
            this.f8462b = obj;
        }

        public <T> T m12291a(Class<T> cls) throws Exception {
            return this.f8461a.invoke(this.f8462b, new Object[]{cls});
        }
    }

    /* renamed from: com.google.gson.b.k.2 */
    static class UnsafeAllocator extends UnsafeAllocator {
        final /* synthetic */ Method f8463a;
        final /* synthetic */ int f8464b;

        UnsafeAllocator(Method method, int i) {
            this.f8463a = method;
            this.f8464b = i;
        }

        public <T> T m12292a(Class<T> cls) throws Exception {
            return this.f8463a.invoke(null, new Object[]{cls, Integer.valueOf(this.f8464b)});
        }
    }

    /* renamed from: com.google.gson.b.k.3 */
    static class UnsafeAllocator extends UnsafeAllocator {
        final /* synthetic */ Method f8465a;

        UnsafeAllocator(Method method) {
            this.f8465a = method;
        }

        public <T> T m12293a(Class<T> cls) throws Exception {
            return this.f8465a.invoke(null, new Object[]{cls, Object.class});
        }
    }

    /* renamed from: com.google.gson.b.k.4 */
    static class UnsafeAllocator extends UnsafeAllocator {
        UnsafeAllocator() {
        }

        public <T> T m12294a(Class<T> cls) {
            throw new UnsupportedOperationException("Cannot allocate " + cls);
        }
    }

    public abstract <T> T m12290a(Class<T> cls) throws Exception;

    public static UnsafeAllocator m12289a() {
        try {
            Class cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return new UnsafeAllocator(cls.getMethod("allocateInstance", new Class[]{Class.class}), declaredField.get(null));
        } catch (Exception e) {
            try {
                Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[]{Class.class});
                declaredMethod.setAccessible(true);
                int intValue = ((Integer) declaredMethod.invoke(null, new Object[]{Object.class})).intValue();
                Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Integer.TYPE});
                declaredMethod2.setAccessible(true);
                return new UnsafeAllocator(declaredMethod2, intValue);
            } catch (Exception e2) {
                try {
                    Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Class.class});
                    declaredMethod3.setAccessible(true);
                    return new UnsafeAllocator(declaredMethod3);
                } catch (Exception e3) {
                    return new UnsafeAllocator();
                }
            }
        }
    }
}
