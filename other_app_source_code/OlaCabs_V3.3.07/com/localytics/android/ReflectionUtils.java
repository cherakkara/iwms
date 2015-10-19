package com.localytics.android;

final class ReflectionUtils {
    private ReflectionUtils() {
        throw new UnsupportedOperationException("This class is non-instantiable");
    }

    static <T> T tryInvokeStatic(Class<?> cls, String str, Class<?>[] clsArr, Object[] objArr) {
        return helper(null, cls, null, str, clsArr, objArr);
    }

    private static <T> T helper(Object obj, Class<?> cls, String str, String str2, Class<?>[] clsArr, Object[] objArr) {
        if (cls == null) {
            if (obj != null) {
                cls = obj.getClass();
            } else {
                cls = Class.forName(str);
            }
        }
        try {
            return cls.getMethod(str2, clsArr).invoke(obj, objArr);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        } catch (Throwable e22) {
            throw new RuntimeException(e22);
        } catch (Throwable e222) {
            throw new RuntimeException(e222);
        }
    }

    static <T> T tryInvokeStatic(String str, String str2, Class<?>[] clsArr, Object[] objArr) {
        return helper(null, null, str, str2, clsArr, objArr);
    }

    static <T> T tryInvokeInstance(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        return helper(obj, null, null, str, clsArr, objArr);
    }

    static <T> T tryInvokeConstructor(String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getDeclaredConstructor(clsArr).newInstance(objArr);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        } catch (Throwable e22) {
            throw new RuntimeException(e22);
        } catch (Throwable e222) {
            throw new RuntimeException(e222);
        } catch (Throwable e2222) {
            throw new RuntimeException(e2222);
        }
    }

    static Object tryGetStaticField(String str, String str2) {
        try {
            return Class.forName(str).getField(str2).get(null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        } catch (Throwable e22) {
            throw new RuntimeException(e22);
        }
    }
}
