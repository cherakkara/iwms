package com.facebook.p022b;

import com.facebook.FacebookSdk;
import com.facebook.FacebookSdkNotInitializedException;
import java.util.Collection;

/* renamed from: com.facebook.b.t */
public final class Validate {
    public static void m1146a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException("Argument '" + str + "' cannot be null");
        }
    }

    public static <T> void m1148a(Collection<T> collection, String str) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Container '" + str + "' cannot be empty");
        }
    }

    public static <T> void m1149b(Collection<T> collection, String str) {
        Validate.m1146a((Object) collection, str);
        for (T t : collection) {
            if (t == null) {
                throw new NullPointerException("Container '" + str + "' cannot contain null values");
            }
        }
    }

    public static <T> void m1150c(Collection<T> collection, String str) {
        Validate.m1149b(collection, str);
        Validate.m1148a((Collection) collection, str);
    }

    public static void m1147a(String str, String str2) {
        if (Utility.m1126a(str)) {
            throw new IllegalArgumentException("Argument '" + str2 + "' cannot be null or empty");
        }
    }

    public static void m1145a() {
        if (!FacebookSdk.m1198a()) {
            throw new FacebookSdkNotInitializedException();
        }
    }
}
