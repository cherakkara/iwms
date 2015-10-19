package com.google.p025a.p034i.p035a;

import javax.annotation.Nullable;

/* renamed from: com.google.a.i.a.k */
public final class SettableFuture<V> extends AbstractFuture<V> {
    public static <V> SettableFuture<V> m3143b() {
        return new SettableFuture();
    }

    private SettableFuture() {
    }

    public boolean m3144a(@Nullable V v) {
        return super.m3122a((Object) v);
    }

    public boolean m3145a(Throwable th) {
        return super.m3123a(th);
    }
}
