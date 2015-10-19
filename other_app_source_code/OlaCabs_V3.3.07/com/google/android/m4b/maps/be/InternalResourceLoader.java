package com.google.android.m4b.maps.be;

import android.content.res.Resources;
import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.android.m4b.maps.be.i */
public final class InternalResourceLoader {
    private static Resources f6021a;
    private static Resources f6022b;

    public static Resources m9392a() {
        return (Resources) Preconditions.m1818a(f6021a, (Object) "Library Resources have not been initialized");
    }

    static Resources m9394b() {
        return (Resources) Preconditions.m1818a(f6022b, (Object) "Client App Resources have not been initialized");
    }

    public static void m9393a(Resources resources) {
        f6021a = resources;
    }

    public static void m9395b(Resources resources) {
        f6022b = resources;
    }
}
