package com.google.android.m4b.maps.cc;

import android.os.IBinder;
import com.google.android.m4b.maps.cc.IObjectWrapper.IObjectWrapper;
import java.lang.reflect.Field;

/* renamed from: com.google.android.m4b.maps.cc.d */
public final class ObjectWrapper<T> extends IObjectWrapper {
    private final T f7262a;

    private ObjectWrapper(T t) {
        this.f7262a = t;
    }

    public static <T> IObjectWrapper m10130a(T t) {
        return new ObjectWrapper(t);
    }

    public static <T> T m10131a(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper instanceof ObjectWrapper) {
            return ((ObjectWrapper) iObjectWrapper).f7262a;
        }
        IBinder asBinder = iObjectWrapper.asBinder();
        Field[] declaredFields = asBinder.getClass().getDeclaredFields();
        if (declaredFields.length == 1) {
            Field field = declaredFields[0];
            if (field.isAccessible()) {
                throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly one declared *private* field for the wrapped object. Preferably, this is an instance of the ObjectWrapper<T> class.");
            }
            field.setAccessible(true);
            try {
                return field.get(asBinder);
            } catch (Throwable e) {
                throw new IllegalArgumentException("Binder object is null.", e);
            } catch (Throwable e2) {
                throw new IllegalArgumentException("remoteBinder is the wrong class.", e2);
            } catch (Throwable e22) {
                throw new IllegalArgumentException("Could not access the field in remoteBinder.", e22);
            }
        }
        throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly *one* declared private field for the wrapped object.  Preferably, this is an instance of the ObjectWrapper<T> class.");
    }
}
