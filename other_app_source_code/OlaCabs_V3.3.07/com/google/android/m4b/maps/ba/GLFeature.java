package com.google.android.m4b.maps.ba;

import com.google.p025a.p028c.ar;
import java.util.List;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.ba.d */
public abstract class GLFeature implements GLDrawable {
    protected List f5046a;
    private final Set<String> f5047b;

    public abstract int m7810a();

    public abstract int m7811b();

    public GLFeature(Set<String> set) {
        this.f5046a = ar.m2515a();
        this.f5047b = set;
    }

    public final Set<String> m7812d() {
        return this.f5047b;
    }
}
