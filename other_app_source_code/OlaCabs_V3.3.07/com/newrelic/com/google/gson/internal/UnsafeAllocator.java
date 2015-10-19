package com.newrelic.com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class UnsafeAllocator {

    /* renamed from: com.newrelic.com.google.gson.internal.UnsafeAllocator.1 */
    static class C07761 extends UnsafeAllocator {
        final /* synthetic */ Method val$allocateInstance;
        final /* synthetic */ Object val$unsafe;

        C07761(Method method, Object obj) {
            this.val$allocateInstance = method;
            this.val$unsafe = obj;
        }

        public <T> T newInstance(Class<T> cls) throws Exception {
            return this.val$allocateInstance.invoke(this.val$unsafe, new Object[]{cls});
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.UnsafeAllocator.2 */
    static class C07772 extends UnsafeAllocator {
        final /* synthetic */ Method val$newInstance;

        C07772(Method method) {
            this.val$newInstance = method;
        }

        public <T> T newInstance(Class<T> cls) throws Exception {
            return this.val$newInstance.invoke(null, new Object[]{cls, Object.class});
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.UnsafeAllocator.3 */
    static class C07783 extends UnsafeAllocator {
        final /* synthetic */ int val$constructorId;
        final /* synthetic */ Method val$newInstance;

        C07783(Method method, int i) {
            this.val$newInstance = method;
            this.val$constructorId = i;
        }

        public <T> T newInstance(Class<T> cls) throws Exception {
            return this.val$newInstance.invoke(null, new Object[]{cls, Integer.valueOf(this.val$constructorId)});
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.UnsafeAllocator.4 */
    static class C07794 extends UnsafeAllocator {
        C07794() {
        }

        public <T> T newInstance(Class<T> cls) {
            throw new UnsupportedOperationException("Cannot allocate " + cls);
        }
    }

    public abstract <T> T newInstance(Class<T> cls) throws Exception;

    public static UnsafeAllocator create() {
        try {
            Class cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return new C07761(cls.getMethod("allocateInstance", new Class[]{Class.class}), declaredField.get(null));
        } catch (Exception e) {
            try {
                Method declaredMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Class.class});
                declaredMethod.setAccessible(true);
                return new C07772(declaredMethod);
            } catch (Exception e2) {
                try {
                    Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[]{Class.class});
                    declaredMethod2.setAccessible(true);
                    int intValue = ((Integer) declaredMethod2.invoke(null, new Object[]{Object.class})).intValue();
                    Method declaredMethod3 = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Integer.TYPE});
                    declaredMethod3.setAccessible(true);
                    return new C07783(declaredMethod3, intValue);
                } catch (Exception e3) {
                    return new C07794();
                }
            }
        }
    }
}
